package christian.wordle.wordgames.repo;

import christian.wordle.wordgames.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepo extends JpaRepository<Juego, Long> {
}
