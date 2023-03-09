package christian.wordle.wordgames.controller;


import christian.wordle.wordgames.DTO.NuevaPartidaDTO;
import christian.wordle.wordgames.model.Equipo;
import christian.wordle.wordgames.model.Juego;
import christian.wordle.wordgames.model.Jugador;
import christian.wordle.wordgames.model.Partida;
import christian.wordle.wordgames.repo.EquipoRepo;
import christian.wordle.wordgames.repo.JuegoRepo;
import christian.wordle.wordgames.repo.JugadorRepo;
import christian.wordle.wordgames.repo.PartidaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class PartidaController {

    private final PartidaRepo partidaRepo;
    private final JugadorRepo jugadorRepo;
    private final JuegoRepo juegoRepo;

    private final EquipoRepo equipoRepo;

    @GetMapping("/partidas")
    public ResponseEntity<?> getAllMatches(){

        List<Partida> allMatches = partidaRepo.findAll();

        if(allMatches.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allMatches);

    }

    @GetMapping("/partidas/{id}")
    public ResponseEntity<?> getMatch(@PathVariable Long id){
        Partida match = partidaRepo.findById(id).orElse(null);

        return (match == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(match);
    }

    //TODO Get de todas las partidas de un juego

    /*TODO POST Partida
    * DTO -> jugador_id, juego_id, palabra
    *
    * Crear objeto de tipo partida, juego y jugador
    * Juego = juegorepo.findbyid(newMatchData.getJuegoId())
    * Jugador = jugadorrepo.findbyid(newMatchData.getjugadorId())
    * Partida = new Partida
    *
    * newpartida.setjuego(juego)
    * newpartida.setjugador(jugador)
    * newpartida.setdate(new Date)
    *
    * newpartida.setPalabra(newMatchData.getPalabra)
    *
    * newpartida.setPuntos()
    *
    * añadir puntos equipo
    * añadir puntos jugador
    *
    * */

    @PostMapping("/partidas")
    public ResponseEntity<?> registerMatch(@RequestBody NuevaPartidaDTO newMatchData) {
        // Comprobar si los campos requeridos están presentes en el DTO
        if (newMatchData.getJugador_id() == null || newMatchData.getJuego_id() == null || newMatchData.getPalabra() == null) {
            return ResponseEntity.badRequest().body("Faltan campos requeridos en el DTO.");
        }

        // Buscar el jugador y el juego por sus identificadores
        Jugador jugador = jugadorRepo.findById(newMatchData.getJugador_id())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el jugador con el ID especificado."));
        Juego juego = juegoRepo.findById(newMatchData.getJuego_id())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el juego con el ID especificado."));

        // Crear una nueva partida
        Partida partida = new Partida();
        partida.setJugador(jugador);
        partida.setJuego(juego);
        partida.setDate(new Date());
        partida.setPalabra(newMatchData.getPalabra());

        // Calcular los puntos obtenidos y asignarlos a la partida y al jugador
        int puntos = calcularPuntos(newMatchData.getPalabra());
        partida.setPuntos(puntos);
        jugador.setPuntos(jugador.getPuntos() + puntos);

        // Si el jugador tiene un equipo, aumentar los puntos del equipo
        Equipo equipo = jugador.getEquipo();
        if (equipo != null) {
            equipo.setPuntos(equipo.getPuntos() + puntos);
            equipoRepo.save(equipo);
        }

        // Guardar la partida y el jugador actualizados en la base de datos
        partidaRepo.save(partida);
        jugadorRepo.save(jugador);

        return ResponseEntity.ok(partida);
    }

    // Función auxiliar para calcular los puntos obtenidos en una partida
    private int calcularPuntos(String palabra) {
        Set<Character> letrasDiferentes = new HashSet<>();
        for (int i = 0; i < palabra.length(); i++) {
            letrasDiferentes.add(palabra.charAt(i));
        }
        int longitud = palabra.length();
        int multiplicador = 1;
        if (longitud >= 8 && letrasDiferentes.size() >= 5) {
            multiplicador = 2;
        } else if (longitud >= 6 && letrasDiferentes.size() >= 3) {
            multiplicador = 3 / 2;
        }
        return longitud * multiplicador;
    }

    @DeleteMapping("/partidas/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable Long id){
        Partida matchToDelete = partidaRepo.findById(id).orElse(null);

        if(matchToDelete == null){
            return ResponseEntity.notFound().build();
        }

        partidaRepo.deleteById(id);

        // Quitamos los puntos de la partida elimina al jugador involucrado que la jugó
        Jugador involvedPlayer = matchToDelete.getJugador();
        involvedPlayer.setPuntos(involvedPlayer.getPuntos() - matchToDelete.getPuntos());

        jugadorRepo.save(involvedPlayer);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(matchToDelete);
    }

}
