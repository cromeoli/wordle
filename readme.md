# Partida

| Endpoint          | Resultado                               | Método |
|-------------------|-----------------------------------------|:------:|
| `/partidas`       | Muestra todas las partidas              |  GET   |
| `/partidas/{id}`  | Mostrar una única partida por su `{id}` |  GET   |
| `/partidas`       | Crear una partida                       |  POST  |
| `/partidas/{id}`  | Eliminar un partida                     | DELETE |

# Juegos

| Endpoint       | Resultado                            | Método |
|----------------|--------------------------------------|:------:|
| `/juegos`      | Muestra todos los juegos             |  GET   |
| `/juegos/{id}` | Mostrar un único juego por su `{id}` |  GET   |

# Jugadores
He añadido un ranking de jugadores del que más puntos tiene al que menos.
Lo he añadido aquí porque no tenía tanto sentido hacerlo sin una partida que variase los puntos.

| Endpoint         | Resultado                                                           | Método |
|------------------|---------------------------------------------------------------------|:------:|
| `/jugadores/ranking`       | Muestra los jugadores ordenados de mayor a menor cantidad de puntos |  GET   |

## Archivo peticiones insomnia
- exportPartidasJuegos

## Datos de prueba

- datosDePrueba.sql

Se han añadido datos de prueba nuevos para las partidas y 
