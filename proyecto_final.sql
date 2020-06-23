DROP DATABASE IF EXISTS proyectofinal; 
CREATE DATABASE proyectofinal;
\c proyectofinal;



DROP TABLE IF EXISTS Categoria;
CREATE TABLE Categoria(
    idCategoria serial PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);


INSERT INTO Categoria(nombre) VALUES('Tenis'),('Ropa'); 


DROP TABLE IF EXISTS Producto;
CREATE TABLE Producto(
    idProducto serial PRIMARY KEY,
    nombre varchar(100) NOT NULL UNIQUE,
    precio NUMERIC NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    stock INTEGER NOT NULL,
    imagen BYTEA,
    idCategoria INTEGER NOT NULL,
    FOREIGN KEY(idCategoria) REFERENCES Categoria(idCategoria) ON DELETE CASCADE ON UPDATE CASCADE
);




DROP TABLE IF EXISTS Cliente;
CREATE TABLE Cliente(
    idCliente serial PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    paterno VARCHAR(50) NOT NULL,
    materno VARCHAR(50) NOT NULL,
    telefono VARCHAR(10),
    email VARCHAR(200) NOT NULL,
    calle VARCHAR(50),
    numero VARCHAR(5),
    estado VARCHAR(50),
    ciudad VARCHAR(50),
    codigoPostal VARCHAR(5),
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(50) NOT NULL
);


INSERT INTO Cliente(nombre, paterno, materno, telefono, email, calle, numero, estado, ciudad, codigoPostal, usuario, contrasena)
	VALUES ('Daniel', 'Guadiana', 'Lumbreras', '5521545410', 'dangu@gmail.com', 'calle generica', '54', 'Ciudad de Mexico', 'Ciudad de Mexico', '15530','dangulu','pass'),
	('Hugo', 'Capetillo', 'Lisama', '5554781260', 'hugoca@gmail.com', 'calle generica', '11', 'Ciudad de Mexico', 'Ciudad de Mexico', '84323','hucali','pass'),
	('Felipe', 'Zosa', 'Duran', '5517895510', 'felipezo@gmail.com', 'calle generica', '14', 'Ciudad de Mexico', 'Ciudad de Mexico', '54333','fezodu','pass'),
	('Gloria', 'Yvanes', 'Berdin', '5500541278', 'gloriyv@gmail.com', 'calle generica', '843', 'Ciudad de Mexico', 'Ciudad de Mexico', '07443','gloryv','pass');



-- Estatus puede ser procesando P, en camino C, entregado E 

DROP TABLE IF EXISTS Orden;
CREATE TABLE Orden(
    idOrden serial PRIMARY KEY,
    estatusOrden VARCHAR(1) NOT NULL,
    fechaOrden DATE NOT NULL,
    fechaEnvio DATE,
    fechaEntrega DATE,
    idCliente INTEGER NOT NULL,
    FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS OrdenArticulos;
CREATE TABLE ordenArticulos(
    idOrden INTEGER NOT NULL,
    idProducto INTEGER NOT NULL,
    idItem INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    precio NUMERIC NOT NULL,
    PRIMARY KEY (idOrden, idItem),
    FOREIGN KEY(idOrden) REFERENCES Orden(idOrden) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(idProducto) REFERENCES Producto(idProducto) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS Administrador;
CREATE TABLE Administrador(
    idAdministrador serial PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    paterno VARCHAR(50) NOT NULL,
    materno VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(50) NOT NULL
);

INSERT INTO Administrador(nombre, paterno, materno, usuario, contrasena)
	VALUES('Ada', 'Sermeno', 'Almendares', 'adseal','T3st3r'),
	('Luis', 'Nevares', 'Turrubiartes', 'lunetur','4dm1n'),
	('Santiago', 'Lisarraga', 'Borrego', 'salibo','S4dm1n');




