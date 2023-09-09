CREATE DATABASE hotel_alura;
USE hotel_alura;

CREATE TABLE paises(
    id int NOT NULL AUTO_INCREMENT,
    nombre varchar(60) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE huespedes(
    id int NOT NULL AUTO_INCREMENT,
    nombre varchar(100) NOT NULL,
    apellido varchar(150) NOT NULL,
    fechaNacimiento date,
    direccion varchar(100),
    telefono varchar(45),
    email varchar(45),
    pais int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(pais) references paises(id)
);

CREATE TABLE tipoHabitacion(
    id int NOT NULL AUTO_INCREMENT,
    tipo varchar(50) NOT NULL,
    numCamas int NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE habitaciones(
    numHabitacion int NOT NULL,
    tipo int NOT NULL,
    precio numeric(9, 2) NOT NULL,
    estadoOcupada char(2) NOT NULL check(estadoOcupada in ('SI', 'NO')),
    PRIMARY KEY(numHabitacion),
    FOREIGN KEY(tipo) REFERENCES tipoHabitacion(id)
);

CREATE TABLE reservas(
    id int NOT NULL AUTO_INCREMENT,
    fechaEntrada date NOT NULL,
    fechaSalida date NOT NULL,
    importe numeric(9, 2) NOT NULL,
    formaPago varchar(45),
    huesped int NOT NULL,
    habitacion int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(huesped) REFERENCES huespedes(id),
    FOREIGN KEY(habitacion) REFERENCES habitaciones(numHabitacion)
);

CREATE TABLE usuarios(
    id int NOT NULL AUTO_INCREMENT,
    usuario varchar(30) NOT NULL,
    clave  varchar(10) NOT NULL,
    PRIMARY KEY(id)
);

/*usuarios*/
INSERT INTO usuarios (usuario, clave) VALUES('admin','admin');
INSERT INTO usuarios (usuario, clave) VALUES('asistente','asistente');

/*paises*/
INSERT INTO paises (nombre) VALUES('ARGENTINA');
INSERT INTO paises (nombre) VALUES('ALEMANIA');
INSERT INTO paises (nombre) VALUES('BRASIL');
INSERT INTO paises (nombre) VALUES('BOLIVIA');
INSERT INTO paises (nombre) VALUES('COLOMBIA');
INSERT INTO paises (nombre) VALUES('CHILE');
INSERT INTO paises (nombre) VALUES('ECUADOR');
INSERT INTO paises (nombre) VALUES('ESPAÑA');
INSERT INTO paises (nombre) VALUES('ESTADOS UNIDOS');
INSERT INTO paises (nombre) VALUES('FRANCIA');
INSERT INTO paises (nombre) VALUES('ITALIA');
INSERT INTO paises (nombre) VALUES('MEXICO');
INSERT INTO paises (nombre) VALUES('PERU');
INSERT INTO paises (nombre) VALUES('PARAGUAY');
INSERT INTO paises (nombre) VALUES('PORTUGAL');
INSERT INTO paises (nombre) VALUES('URUGUAY');
INSERT INTO paises (nombre) VALUES('VENEZUELA');
    
/*Huespedes*/
INSERT INTO huespedes (nombre,apellido,fechaNacimiento,direccion,telefono,email,pais) VALUES('Felipe', 'Iglesias', '1980-03-23', 'Avda Los Castros, 44', '942344444', 'prueba@gmail.com',2);
INSERT INTO huespedes (nombre,apellido,fechaNacimiento,direccion,telefono,email,pais)VALUES('Luis', 'García', '1977-08-01', 'Calle Mayor, 67 ', '942456444', null,9);
INSERT INTO huespedes (nombre,apellido,fechaNacimiento,direccion,telefono,email,pais)VALUES( 'Ludovic', 'Giuly', '1985-12-11', '18 avenue Alsacen Cour', '37890194', null,6);

/*tipo_habitacion*/
INSERT INTO tipohabitacion(tipo, numCamas) VALUES('Individual', 1);
INSERT INTO tipohabitacion(tipo, numCamas) VALUES('Doble', 2);
INSERT INTO tipohabitacion(tipo, numCamas) VALUES('Matrimonial', 1);

/*habitaciones*/
INSERT INTO habitaciones VALUES(101, 1, 50.00, 'NO');
INSERT INTO habitaciones VALUES(102, 1, 55.00,'NO');
INSERT INTO habitaciones VALUES(103, 1, 60.00, 'NO');
INSERT INTO habitaciones VALUES(104, 2, 100.00,'NO');
INSERT INTO habitaciones VALUES(105, 2, 50.00, 'NO');
INSERT INTO habitaciones VALUES(106, 2, 55.00,'NO');
INSERT INTO habitaciones VALUES(107, 1, 60.00, 'NO');
INSERT INTO habitaciones VALUES(108, 3, 80.00,'NO');
INSERT INTO habitaciones VALUES(109, 3, 90.00,'NO');
INSERT INTO habitaciones VALUES(110, 3, 90.00,'NO');

/*reservas*/
INSERT INTO reservas(fechaentrada, fechasalida, importe, formapago, huesped, habitacion)
VALUES('2023-03-15', '2023-03-25', 220.50,'Dinero en efectivo',1, 101);
INSERT INTO reservas(fechaentrada, fechasalida, importe, formapago, huesped, habitacion)
VALUES('2023-03-15', '2023-03-25', 300.50,'Dinero en efectivo',2, 102);
INSERT INTO reservas(fechaentrada, fechasalida, importe, formapago, huesped, habitacion)
VALUES('2023-02-16', '2023-02-21', 120.00,'Tarjeta de Débito',3, 103);
INSERT INTO reservas(fechaentrada, fechasalida, importe, formapago, huesped, habitacion)
VALUES('2023-08-16', '2023-08-21', 500.00, 'Tarjeta de Débito',2, 104);

