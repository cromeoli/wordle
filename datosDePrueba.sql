
-- Equipos de prueba
INSERT INTO equipo (nombre, logo, puntos) VALUES ('Los Tucanes', '/logos/tucanes.png', 0);
INSERT INTO equipo (nombre, logo, puntos) VALUES ('Los Tigres', '/logos/tigres.png', 0);
INSERT INTO equipo (nombre, logo, puntos) VALUES ('Los Leones', '/logos/leones.png', 0);
INSERT INTO equipo (nombre, logo, puntos) VALUES ('Las Águilas', '/logos/aguilas.png', 0);
INSERT INTO equipo (nombre, logo, puntos) VALUES ('Los Panteras', '/logos/panteras.png', 0);

-- Jugadores de prueba
INSERT INTO jugador (nombre, avatar, equipo_id, puntos, admin, clave) VALUES ('Juan Perez', '/avatars/juan.png', 1, 100, true, 'contraseña');
INSERT INTO jugador (nombre, avatar, equipo_id, puntos, admin, clave) VALUES ('Ana Garcia', '/avatars/ana.png', 1, 50, false, 'contraseña');
INSERT INTO jugador (nombre, avatar, equipo_id, puntos, admin, clave) VALUES ('Pedro Sanchez', '/avatars/pedro.png', 2, 75, false, 'contraseña');
INSERT INTO jugador (nombre, avatar, equipo_id, puntos, admin, clave) VALUES ('Maria Rodriguez', '/avatars/maria.png', 2, 200, false, 'contraseña');
INSERT INTO jugador (nombre, avatar, equipo_id, puntos, admin, clave) VALUES ('Luisa Martinez', '/avatars/luisa.png', 3, 30, false, 'contraseña');
