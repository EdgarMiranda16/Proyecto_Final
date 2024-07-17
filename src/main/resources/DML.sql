CREATE TABLE perfiles
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre_perfil VARCHAR(50) NOT NULL,
    descripcion   TEXT
);

CREATE TABLE usuarios
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    perfil_id      INT,
    usuario        VARCHAR(50)  NOT NULL UNIQUE,
    password       VARCHAR(255) NOT NULL,
    email          VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (perfil_id) REFERENCES perfiles (id)
);

CREATE TABLE clientes
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    nombres        VARCHAR(100) NOT NULL,
    apellidos      VARCHAR(100) NOT NULL,
    direccion      VARCHAR(255),
    telefono       VARCHAR(15),
    email          VARCHAR(100) NOT NULL UNIQUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE productos
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(100)   NOT NULL,
    descripcion     TEXT,
    precio          DECIMAL(10, 2) NOT NULL,
    stock           INT            NOT NULL,
    fecha_agregado  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ventas
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id  INT,
    fecha_venta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total       DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);

CREATE TABLE detalle_ventas
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    venta_id        INT,
    producto_id     INT,
    cantidad        INT            NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal        DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES ventas (id),
    FOREIGN KEY (producto_id) REFERENCES productos (id)
);

