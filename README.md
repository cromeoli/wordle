## CRUD Equipo y Jugador
En esta rama he implementado el modelo y controlador junto al repositorio.

Para crear la base de datos he adjuntado el script SQL
- **wordgamesDB.sql**
 
Para crear la base de datos he adjuntado el script SQL
- **datosDePrueba.sql**

Las peticiones de insomnia las he adjuntado en el archivo: 
- **exportsCRUDJugadorEquipo.json**




### *Jugador*
| Endpoint          | Resultado                                    | Método |
|-------------------|----------------------------------------------|:------:|
| `/jugadores`      | Muestra todos los jugadores                  |  GET   |
| `/jugadores/{id}` | Mostrar un único jugador a partir del `{id}` |  GET   |
| `/jugadores`      | Crear un jugador                             |  POST  |
| `/jugadores/{id}` | Eliminar un jugador                          | DELETE |
| `/jugadores/{id}` | Modificar un jugador                         |  PUT   |


### *Equipo*
| Endpoint                  | Resultado                                   | Método |
|---------------------------|---------------------------------------------|:------:|
| `/equipos`                | Muestra todos los equipos                   |  GET   |
| `/equipos/{id}`           | Mostrar un unico equipo a partir del `{id}` |  GET   |
| `/equipos/jugadores/{id}` | Muestra los jugadores de un equipo concreto |  GET   |
| `/equipos`                | Añadir un equipo                            |  POST  |
| `/equipos/{id}`           | Eliminar un equipo                          | DELETE |
| `/equipos/{id}`           | Modificar un equipo                         |  PUT   |

