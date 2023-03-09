package christian.wordle.wordgames.repo;

import christian.wordle.wordgames.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepo extends JpaRepository<Partida,Long> {

}
