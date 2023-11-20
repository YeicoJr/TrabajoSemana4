
CREATE DATABASE IF NOT EXISTS db_Distribuidora;
USE db_Distribuidora;


CREATE TABLE IF NOT EXISTS Sucursales (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

-- Crear la tabla Productos
CREATE TABLE IF NOT EXISTS Productos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL
);


CREATE TABLE IF NOT EXISTS Compras (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL(10, 2) NOT NULL,
    fechaCompra DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (idProducto) REFERENCES Productos(id)
);


CREATE TABLE IF NOT EXISTS Ventas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL(10, 2) NOT NULL,
    fechaVenta DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (idProducto) REFERENCES Productos(id)
);

INSERT INTO Sucursales (nombre, direccion, telefono) VALUES
    ('Sucursal A', 'Dirección A', '123-456-7890'),
    ('Sucursal B', 'Dirección B', '987-654-3210'),
    ('Sucursal C', 'Dirección C', '555-123-4567');

INSERT INTO Productos (nombre, descripcion, precio) VALUES
    ('POLLO', 'Descripción del Producto 1', 15.000),
    ('CUY', 'Descripción del Producto 2', 30.000),
    ('EMBUTIDOS', 'Descripción del Producto 3', 150.000);

SELECT * FROM compras;
SELECT * FROM ventas;
