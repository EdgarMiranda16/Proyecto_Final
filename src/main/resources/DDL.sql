-- Insertar perfiles
INSERT INTO perfiles (nombre_perfil, descripcion) VALUES ('Administrador', 'Acceso completo al sistema');
INSERT INTO perfiles (nombre_perfil, descripcion) VALUES ('Gerente', 'Acceso a reportes y estadísticas');
INSERT INTO perfiles (nombre_perfil, descripcion) VALUES ('Vendedor', 'Acceso limitado');

-- Insertar usuarios
INSERT INTO usuarios (perfil_id, usuario, password, email) VALUES (1, 'admin', '123456', 'admin@gmail.com');
INSERT INTO usuarios (perfil_id, usuario, password, email) VALUES (2, 'gerente', '123456', 'gerente@gmail.com');
INSERT INTO usuarios (perfil_id, usuario, password, email) VALUES (3, 'vendedor', '123456', 'vendedor@gmail.com');

-- Insertar productos
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('lejía', 'Producto de limpieza', 5.00, 100);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('escobas', 'Producto de limpieza', 3.00, 200);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('trapeadores', 'Producto de limpieza', 4.50, 150);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('ayudín', 'Producto de limpieza', 2.50, 250);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('detergentes', 'Producto de limpieza', 6.00, 180);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('ambientadores', 'Producto de limpieza', 3.50, 120);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('desodorantes', 'Producto de limpieza', 2.00, 200);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('cepillos', 'Producto de limpieza', 1.50, 300);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('pastas dentales', 'Producto de higiene personal', 1.20, 400);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('recogedores', 'Producto de limpieza', 2.80, 220);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('limpia lunas', 'Producto de limpieza', 3.00, 130);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('plumeros', 'Producto de limpieza', 1.80, 150);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('guantes de lavado', 'Producto de limpieza', 1.50, 180);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('escobillas', 'Producto de limpieza', 1.70, 160);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('jabones', 'Producto de higiene personal', 1.00, 500);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('champús', 'Producto de higiene personal', 3.20, 220);
INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES ('alcohol', 'Producto de limpieza', 2.20, 300);
