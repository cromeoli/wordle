-- Juegos de prueba
INSERT INTO juego (nombre, instrucciones, intentosMax, dificultad)
VALUES ('Wordle', 'Adivina la palabra oculta en 6 intentos', 6, 'Fácil');

INSERT INTO juego (nombre, instrucciones, intentosMax, dificultad)
VALUES ('Ahorcado', 'Adivina la palabra oculta letra por letra antes de que se complete el dibujo', 8, 'Medio');

INSERT INTO juego (nombre, instrucciones, intentosMax, dificultad)
VALUES ('Absurdle', 'Crea la palabra más larga posible con las letras dadas', 1, 'Difícil');



-- Partidas de prueba
INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2022-01-01 14:30:00', 10, 'GATO', 1, 1);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2022-01-02 16:45:00', 5, 'PERRO', 1, 1);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2022-01-03 09:00:00', 10, 'MONTAÑA', 1, 2);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2022-01-04 13:15:00', 2, 'HOSPITAL', 1, 2);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2022-01-05 17:30:00', 8, 'PANADERO', 1, 3);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2022-01-06 11:45:00', 5, 'ALFOMBRILLA', 1, 3);