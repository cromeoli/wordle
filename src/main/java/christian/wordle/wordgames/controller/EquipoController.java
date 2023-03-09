package christian.wordle.wordgames.controller;

import christian.wordle.wordgames.model.Equipo;
import christian.wordle.wordgames.model.Jugador;
import christian.wordle.wordgames.repo.EquipoRepo;
import christian.wordle.wordgames.repo.JugadoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController @RequiredArgsConstructor
public class EquipoController {

    private final EquipoRepo equipoRepo;
    private final JugadoRepo jugadorRepo;

    @GetMapping("/equipos")
    public ResponseEntity<?> getAllTeams(){

        List<Equipo> allTeams = equipoRepo.findAll();

        if(allTeams.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allTeams);
    }

    @GetMapping("/equipos/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id){

        Equipo team = equipoRepo.findById(id).orElse(null);

        return (team == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(team);
    }


    @GetMapping("/equipos/jugadores/{id}")
    public ResponseEntity<?> getTeamPlayers(@PathVariable Long id){
        Equipo equipo = equipoRepo.findById(id).orElse(null);

        if(equipo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Jugador> allPlayers = jugadorRepo.findAll();

        List<Jugador> playersInTeam = new ArrayList<>();

        for ( Jugador player : allPlayers ){
            if(player.getEquipo() != null){
                if(player.getEquipo().getId() == id){
                    playersInTeam.add(player);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(playersInTeam);

    }

    @PostMapping("/equipos")
    public ResponseEntity<?> addTeam(@RequestBody Equipo newTeam){
        if(newTeam.getPuntos() != null){
            newTeam.setPuntos(0L);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(equipoRepo.save(newTeam));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id){
        Equipo deletedTeam = equipoRepo.findById(id).orElse(null);

        if(deletedTeam == null){
            return ResponseEntity.notFound().build();
        }

        equipoRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deletedTeam);
    }

    @PutMapping("equipos/{id}")
    public ResponseEntity<?> updateTeam(@RequestBody Equipo teamNewData, @PathVariable Long id){
        Equipo team = equipoRepo.findById(id).orElse(null);

        if(team == null){
           return ResponseEntity.notFound().build();
        }

        // Los compruebo individualmente para poder modificar tan solo parcialmente

        if (teamNewData.getNombre() != null)
            team.setNombre(teamNewData.getNombre());

        if (teamNewData.getLogo() != null)
            team.setLogo(teamNewData.getLogo());

        equipoRepo.save(team);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(team);
    }

}
