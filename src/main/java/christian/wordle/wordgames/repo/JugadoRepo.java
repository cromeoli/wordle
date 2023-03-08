package christian.wordle.wordgames.repo;

import christian.wordle.wordgames.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadoRepo extends JpaRepository <Jugador, Long> {
}
