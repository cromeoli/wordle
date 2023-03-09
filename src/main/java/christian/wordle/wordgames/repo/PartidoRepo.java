package christian.wordle.wordgames.repo;

import christian.wordle.wordgames.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepo extends JpaRepository<Partida,Long> {

}
