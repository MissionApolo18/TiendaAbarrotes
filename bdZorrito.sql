create database abarrote_zorro;

use abarrote_zorro;

CREATE DATABASE abarrote_zorro;
USE abarrote_zorro;

-- Tabla de roles de usuario
CREATE TABLE rol (
  id_rol INT PRIMARY KEY AUTO_INCREMENT,
  rol VARCHAR(255) NOT NULL
);

-- Tabla de usuarios (trabajadoras en caja, admins, etc.)
CREATE TABLE usuarios (
  id_usuario INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL UNIQUE,
  rol INT NOT NULL,
  FOREIGN KEY (rol) REFERENCES rol(id_rol)
);

-- Clientes que compran
CREATE TABLE cliente (
  id_cliente INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  correo VARCHAR(255) UNIQUE,
  telefono VARCHAR(20) UNIQUE
);

-- Productos en inventario
CREATE TABLE producto (
  id_producto INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  precio_unitario_venta DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL,
  imagen_url VARCHAR(255)
);

-- Ventas realizadas
CREATE TABLE venta (
  id_venta INT PRIMARY KEY AUTO_INCREMENT,
  id_cliente INT NOT NULL,
  id_usuario INT NOT NULL,
  fecha DATETIME NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Detalles de cada venta
CREATE TABLE detalle_venta (
  id_detalle INT PRIMARY KEY AUTO_INCREMENT,
  id_venta INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  precio_unitario DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_venta) REFERENCES venta(id_venta),
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- Distribuidores que proveen productos
CREATE TABLE distribuidor (
  id_distribuidor INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255) NOT NULL,
  correo VARCHAR(255)
);

-- Pedidos hechos a distribuidores
CREATE TABLE pedido_distribuidor (
  id_pedido INT PRIMARY KEY AUTO_INCREMENT,
  id_distribuidor INT NOT NULL,
  fecha DATETIME NOT NULL,
  FOREIGN KEY (id_distribuidor) REFERENCES distribuidor(id_distribuidor)
);

-- Detalles de los pedidos
CREATE TABLE detalle_pedido_distribuidor (
  id_detalle INT PRIMARY KEY AUTO_INCREMENT,
  id_pedido INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  precio_unitario DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_pedido) REFERENCES pedido_distribuidor(id_pedido),
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- Métodos de pago
CREATE TABLE metodo_pago (
  id_metodo INT PRIMARY KEY AUTO_INCREMENT,
  descripcion VARCHAR(255) NOT NULL
);

-- Pagos realizados
CREATE TABLE pago (
  id_pago INT PRIMARY KEY AUTO_INCREMENT,
  id_venta INT NOT NULL,
  id_metodo INT NOT NULL,
  total_pagado DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (id_venta) REFERENCES venta(id_venta),
  FOREIGN KEY (id_metodo) REFERENCES metodo_pago(id_metodo)
);

-- Datos de tarjeta si el pago fue con tarjeta
CREATE TABLE tarjeta (
  id_tarjeta INT PRIMARY KEY AUTO_INCREMENT,
  id_pago INT NOT NULL,
  tipo_tarjeta VARCHAR(255),
  digitos_tarjeta VARCHAR(4),
  banco VARCHAR(255),
  ccv VARCHAR(3),
  FOREIGN KEY (id_pago) REFERENCES pago(id_pago)
);

CREATE TABLE corte_inventario (
  id_corte INT PRIMARY KEY AUTO_INCREMENT,
  fecha DATE NOT NULL,
  tipo ENUM('inicio', 'fin') NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL,
  FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

