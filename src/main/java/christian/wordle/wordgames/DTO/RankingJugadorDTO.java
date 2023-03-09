package christian.wordle.wordgames.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RankingJugadorDTO {
    private String nombre;
    private int puntos;
    private String avatar;
    private String nombreEquipo;
}


