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