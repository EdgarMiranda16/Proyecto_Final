-- Insertar perfiles
INSERT INTO perfiles (nombre_perfil, descripcion) VALUES ('Administrador', 'Acceso completo al sistema');
INSERT INTO perfiles (nombre_perfil, descripcion) VALUES ('Gerente', 'Acceso a reportes y estadísticas');
INSERT INTO perfiles (nombre_perfil, descripcion) VALUES ('Vendedor', 'Acceso limitado');

-- Insertar usuarios
INSERT INTO usuarios (perfil_id, usuario, password, email) VALUES (1, 'admin', '123456', 'admin@gmail.com');
INSERT INTO usuarios (perfil_id, usuario, password, email) VALUES (2, 'gerente', '123456', 'gerente@gmail.com');
INSERT INTO usuarios (perfil_id, usuario, password, email) VALUES (3, 'vendedor', '123456', 'vendedor@gmail.com');