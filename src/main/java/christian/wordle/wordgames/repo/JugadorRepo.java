package christian.wordle.wordgames.repo;

import christian.wordle.wordgames.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepo extends JpaRepository <Jugador, Long> {
}
