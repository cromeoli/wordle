package christian.wordle.wordgames.controller;


import christian.wordle.wordgames.DTO.NuevoJugadorDTO;
import christian.wordle.wordgames.model.Equipo;
import christian.wordle.wordgames.model.Jugador;
import christian.wordle.wordgames.repo.EquipoRepo;
import christian.wordle.wordgames.repo.JugadoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor
public class JugadorController {

    private final JugadoRepo jugadoRepo;
    private final EquipoRepo equipoRepo;

    @GetMapping("/jugadores")
    public ResponseEntity<?> getAllPlayers(){
        List<Jugador> allPlayers = jugadoRepo.findAll();

        if(allPlayers.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allPlayers);
    }

    @GetMapping("/jugadores/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable Long id){
        Jugador player = jugadoRepo.findById(id).orElse(null);

        return (player == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(player);
    }


    @PostMapping("/jugadores")
    public ResponseEntity<?> addPlayer(@RequestBody NuevoJugadorDTO newPlayerData){

        Jugador newPlayer = new Jugador(); // Se crea default con puntos 0, por lo que no hago set

        Equipo team = null;
        if(newPlayerData.getEquipo_id() != null){

            team = equipoRepo.findById(newPlayerData.getEquipo_id())
                    .orElse(null);
        }

        if(newPlayerData.getNombre() != null && newPlayerData.getClave() != null){

            if (newPlayerData.getAdmin() == 1 || newPlayerData.getAdmin() == 0) {
                newPlayer.setAdmin(newPlayer.getAdmin());
            } else {
                newPlayer.setAdmin(0); // Error "El formato del jugador debe ser..."
            }

            newPlayer.setNombre(newPlayerData.getNombre());
            newPlayer.setEquipo(team);
            newPlayer.setClave(newPlayerData.getClave());
            newPlayer.setAvatar(newPlayerData.getAvatar());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(jugadoRepo.save(newPlayer));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @DeleteMapping("/jugadores/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id){
        Jugador deletedPlayer = jugadoRepo.findById(id).orElse(null);

        if(deletedPlayer == null){
            return ResponseEntity.notFound().build();
        }

        jugadoRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deletedPlayer);
    }

    @PutMapping("jugadores/{id}")
    public ResponseEntity<?> updateTeam(@RequestBody NuevoJugadorDTO playerNewData, @PathVariable Long id){
        Jugador playerToModify = jugadoRepo.findById(id).orElse(null);


        if(playerNewData.getEquipo_id() != null){
            Equipo team = equipoRepo.findById(playerNewData.getEquipo_id()).orElse(null);
            if(team != null){
                playerToModify.setEquipo(team);
            }
        }

        if(playerToModify == null){
            return ResponseEntity.notFound().build();
        }


        if (playerNewData.getAdmin() == 0 || playerNewData.getAdmin() == 1)
            playerToModify.setAdmin(playerNewData.getAdmin());

        if (playerNewData.getNombre() != null)
            playerToModify.setNombre(playerNewData.getNombre());

        if (playerNewData.getClave() != null)
            playerToModify.setClave(playerNewData.getClave());

        if (playerNewData.getAvatar() != null)
            playerToModify.setAvatar(playerNewData.getAvatar());

        if (playerNewData.getPuntos() != null)
            playerToModify.setPuntos((playerNewData.getPuntos().intValue()));

        jugadoRepo.save(playerToModify);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(playerToModify);
    }
}
