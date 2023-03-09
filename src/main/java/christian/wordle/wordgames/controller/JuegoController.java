package christian.wordle.wordgames.controller;


import christian.wordle.wordgames.model.Juego;
import christian.wordle.wordgames.repo.JuegoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JuegoController {
    
    private final JuegoRepo juegoRepo;
    
    @GetMapping("/juegos")
    public ResponseEntity<?> getAllGames(){
        List<Juego> allGames = juegoRepo.findAll();
        
        if(allGames.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(allGames);
    }
    
    @GetMapping("juegos/{id}")
    public ResponseEntity<?> getGame(@PathVariable Long id){
        Juego game = juegoRepo.findById(id).orElse(null);

        if(game == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(game);
    }
    
    
}
