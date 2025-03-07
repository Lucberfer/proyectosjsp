-- Drop database if it exists (opcional)
DROP DATABASE IF EXISTS proyectos;

-- Create database
CREATE DATABASE proyectos;

-- Select the database
USE proyectos;

-- Tabla de Usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

-- Tabla de Proyectos
CREATE TABLE proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    descripcion TEXT,
    fechaInicio DATE,
    fechaFin DATE,
    estado VARCHAR(50),
    usuario_id INT,
    CONSTRAINT fk_proyecto_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE SET NULL
);

-- Tabla de Tareas
CREATE TABLE tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT,
    responsable VARCHAR(100),
    fechaInicio DATE,
    fechaFin DATE,
    estado VARCHAR(50),
    proyecto_id INT,
    CONSTRAINT fk_tarea_proyecto FOREIGN KEY (proyecto_id)
        REFERENCES proyectos(id)
        ON DELETE CASCADE
);
