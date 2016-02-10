/**
 * Author:  Camilo Ruiz Casanova
 * Created: 17-dic-2015
 */

DROP TABLE IF EXISTS empleado CASCADE;
CREATE TABLE empleado (
    empleado_id         VARCHAR(15)     NOT NULL,
    empleado_nombre     VARCHAR(50)     NOT NULL,
    empleado_telefono   VARCHAR(15)     NOT NULL,
    empleado_direccion  VARCHAR(100)    NOT NULL,
    empleado_email      VARCHAR(100)    NOT NULL,
    empleado_cargo      VARCHAR(50)     NOT NULL,
    empleado_salario    DECIMAL         NOT NULL,
    jefe_empleado_id    VARCHAR(15),
    empleado_estado     BOOLEAN         NOT NULL,
    PRIMARY KEY (empleado_id),
    FOREIGN KEY (jefe_empleado_id) REFERENCES empleado (empleado_id)
);

DROP TABLE IF EXISTS estacion CASCADE;
CREATE TABLE estacion (
    estacion_nombre         VARCHAR(50) NOT NULL,
    estacion_ubicacion      VARCHAR(50) NOT NULL,
    director_empleado_id    VARCHAR(15) UNIQUE,
    estacion_estado         BOOLEAN     NOT NULL,
    PRIMARY KEY (estacion_nombre),
    FOREIGN KEY (director_empleado_id) REFERENCES empleado (empleado_id)
);

DROP TABLE IF EXISTS ruta CASCADE;
CREATE TABLE ruta (
    ruta_nombre         VARCHAR(50) NOT NULL,
    ruta_descripcion    TEXT        NOT NULL,
    ruta_estado         BOOLEAN     NOT NULL,
    PRIMARY KEY (ruta_nombre)
);

DROP TABLE IF EXISTS bus CASCADE;
CREATE TABLE bus (
    bus_serial      VARCHAR(15) NOT NULL,
    bus_tipo        VARCHAR(30) NOT NULL,
    bus_capacidad   INTEGER     NOT NULL,
    ruta_nombre     VARCHAR(50),
    bus_estado      BOOLEAN     NOT NULL,
    PRIMARY KEY (bus_serial),
    FOREIGN KEY (ruta_nombre) REFERENCES ruta (ruta_nombre)
);

DROP SEQUENCE IF EXISTS tarjeta_seq CASCADE;
CREATE SEQUENCE tarjeta_seq
START WITH 100000;

DROP TABLE IF EXISTS tarjeta CASCADE;
CREATE TABLE tarjeta (
    tarjeta_id              VARCHAR DEFAULT nextval('tarjeta_seq'::regclass)  NOT NULL,
    tarjeta_saldo           DECIMAL     NOT NULL,
    tarjeta_estado          VARCHAR(30) NOT NULL,
    PRIMARY KEY (tarjeta_id)
);

DROP TABLE IF EXISTS pasajero CASCADE;
CREATE TABLE pasajero (
    pasajero_id         VARCHAR(15)     NOT NULL,
    pasajero_nombre     VARCHAR(50)     NOT NULL,
    pasajero_telefono   VARCHAR(15)     NOT NULL,
    pasajero_direccion  VARCHAR(100)    NOT NULL,
    pasajero_email      VARCHAR(100)    NOT NULL,
    tarjeta_id          VARCHAR(15)     UNIQUE NOT NULL,
    pasajero_estado     BOOLEAN         NOT NULL,
    PRIMARY KEY (pasajero_id),
    FOREIGN KEY (tarjeta_id) REFERENCES tarjeta (tarjeta_id)
);

DROP TABLE IF EXISTS turno CASCADE;
CREATE TABLE turno (
    bus_serial              VARCHAR(15) NOT NULL,
    turno_turno             VARCHAR(30) NOT NULL,
    conductor_empleado_id   VARCHAR(15) UNIQUE NOT NULL,
    PRIMARY KEY (bus_serial, turno_turno),
    FOREIGN KEY (conductor_empleado_id) REFERENCES empleado (empleado_id)
);

DROP TABLE IF EXISTS venta CASCADE;
CREATE TABLE venta (
    venta_id        SERIAL      NOT NULL,
    venta_fecha     TIMESTAMP   NOT NULL,
    venta_valor     DECIMAL     NOT NULL,
    estacion_nombre VARCHAR(50) NOT NULL,
    tarjeta_id      VARCHAR(15) UNIQUE NOT NULL,
    PRIMARY KEY (venta_id),
    FOREIGN KEY (estacion_nombre) REFERENCES estacion (estacion_nombre),
    FOREIGN KEY (tarjeta_id) REFERENCES tarjeta (tarjeta_id)
);

DROP SEQUENCE IF EXISTS solicitud_seq CASCADE;
CREATE SEQUENCE solicitud_seq
    START WITH 100000;

DROP TABLE IF EXISTS solicitud CASCADE;
CREATE TABLE solicitud (
    solicitud_id            VARCHAR DEFAULT nextval('solicitud_seq'::regclass)  NOT NULL,
    solicitud_motivo        VARCHAR(30) NOT NULL,
    solicitud_descripcion   TEXT        NOT NULL,
    solicitud_fecha         TIMESTAMP   NOT NULL,
    solicitud_estado        VARCHAR(30) NOT NULL,
    pasajero_id             VARCHAR(15) NOT NULL,
    estacion_nombre         VARCHAR(50) NOT NULL,
    PRIMARY KEY (solicitud_id),
    FOREIGN KEY (pasajero_id) REFERENCES pasajero (pasajero_id),
    FOREIGN KEY (estacion_nombre) REFERENCES estacion (estacion_nombre)
);

DROP TABLE IF EXISTS solicitud_medidas CASCADE;
CREATE TABLE solicitud_medidas (
    solicitud_id        VARCHAR NOT NULL,
    solicitud_medida    TEXT    NOT NULL,
    PRIMARY KEY (solicitud_id, solicitud_medida),
    FOREIGN KEY (solicitud_id) REFERENCES solicitud (solicitud_id)
);

DROP TABLE IF EXISTS tarjeta_ruta CASCADE;
CREATE TABLE tarjeta_ruta (
    tarjeta_id          VARCHAR(15) NOT NULL,
    ruta_nombre         VARCHAR(50) NOT NULL,
    tarjeta_ruta_fecha  TIMESTAMP   NOT NULL,
    PRIMARY KEY (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha),
    FOREIGN KEY (tarjeta_id) REFERENCES tarjeta (tarjeta_id),
    FOREIGN KEY (ruta_nombre) REFERENCES ruta (ruta_nombre)
);

DROP TABLE IF EXISTS estacion_ruta CASCADE;
CREATE TABLE estacion_ruta (
    estacion_nombre VARCHAR(50) NOT NULL,
    ruta_nombre     VARCHAR(50) NOT NULL,    
    PRIMARY KEY (ruta_nombre, estacion_nombre),
    FOREIGN KEY (ruta_nombre) REFERENCES ruta (ruta_nombre),
    FOREIGN KEY (estacion_nombre) REFERENCES estacion (estacion_nombre)
);


-- Valores iniciales
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('1', 'Anne Diaz', '1111111', 'Calle 1', '1@1.com', 'Director', 10000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('2', 'Patricia Torres', '2222222', 'Calle 2', '2@2.com', 'Director', 5000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('3', 'Eugene Phillips', '3333333', 'Calle 3', '3@3.com', 'Director', 5000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('4', 'Irene Ward', '4444444', 'Calle 4', '4@4.com', 'Director', 5000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('5', 'Jack Long', '5555555', 'Calle 5', '5@5.com', 'Director', 5000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('6', 'Rebecca Morris', '6666666', 'Calle 6', '6@6.com', 'Auxiliar', 2000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('7', 'Craig Foster', '7777777', 'Calle 7', '7@7.com', 'Auxiliar', 2000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('8', 'James Cooper', '8888888', 'Calle 8', '8@8.com', 'Auxiliar', 2000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('9', 'Bobby Brown', '9999999', 'Calle 9', '9@9.com', 'Auxiliar', 2000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('10', 'Lillian Thompson', '142131', 'Calle 10', '10@10.com', 'Auxiliar', 2000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('11', 'Phyllis Stewart', '64563456', 'Calle 11', '11@11.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('12', 'Frank Butler', '63213', 'Calle 12', '12@12.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('13', 'Deborah Wright', '312312', 'Calle 13', '13@13.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('14', 'Roger Perry', '57657', 'Calle 14', '14@14.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('15', 'Johnny Parker', '41124', 'Calle 15', '15@15.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('16', 'Wanda Kelly', '1234123', 'Calle 16', '16@16.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('17', 'Amy Wilson', '765753', 'Calle 17', '17@17.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('18', 'Sharon King', '154347', 'Calle 18', '18@18.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('19', 'Karen Edwards', '659760', 'Calle 19', '19@19.com', 'Conductor', 3000, TRUE);
INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, empleado_email, empleado_cargo, empleado_salario, empleado_estado)
VALUES ('20', 'Richard Adams', '675674', 'Calle 20', '20@20.com', 'Conductor', 3000, TRUE);

INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('E21', 'TERMINAL MENGA - UNIVERSIDADES', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('E27', 'TERMINAL MENGA - CAPRI', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('E31', 'EXPRESO CHIMINANGOS - UNIVERSIDADES', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('E37', 'CHIMINANGOS - UNIDAD DEPORTIVA', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('E52', 'NUEVO LATIR - TERMINAL CALIPSO - TERMINAL', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('T31', 'CHIMINANGOS - UNIVERSIDADES', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('T40', 'T. ANDRES SANIN - CENTRO', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('T47A', 'T. ANDRES SANIN - UNIDAD DEPORTIVA', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('T47B', 'T. ANDRES SANIN - UNIDAD DEPORTIVA', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('T50', 'NUEVO LATIR - CENTRO', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('T57A', 'NUEVO LATIR - UNIDAD DEPORTIVA', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P12A', 'UNIVERSIDADES - TERMINAL ANDRES SANIN - CENTRO EMPRESA', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P14A', 'UNIVERSIDADES - TERMINAL ANDRES SANIN', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P21B', 'TERMINAL MENGA - UNIVERSIDADES', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P24A', 'TERMINAL MENGA - TERMINAL ANDRES SANIN', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P24C', 'TERMINAL MENGA - TERMINAL ANDRES SANIN - DECEPAZ', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P27C', 'TERMINAL MENGA - UNIVERSIDADES', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P30A', 'FLORA INDUSTRIAL - TERMINAL MENGA - CENTRO', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P40A', 'TERMINAL ANDRES SANIN - CENTRO', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P47A', 'TERMINAL ANDRES SANIN - UNIDAD DEPORTIVA', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P47B', 'T. ANDRES SANIN - UNIDAD DEPORTIVA', TRUE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P47C', 'T. ANDRES SANIN - CAPRI', TRUE);

INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('E27B', 'AV LAS AMERICAS - UNIDAD DEPORTIVA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('T42', 'PIZAMOS - CENTRO - TERMINAL', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P10A', 'UNIVERSIDADES - CENTRO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P10B', 'UNIVERSIDADES - EL INGENIO - CENTRO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P10D', 'UNIVERSIDADES - CENTRO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P17', 'P.U.J. - ICESI - UNIDAD DEPORTIVA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P21A', 'UNIVERSIDADES - CENTRO - AV LAS AMERICAS', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P24B', 'C.A.M. - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P30B', 'C.A.M. - CENTRO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P40B', 'T. ANDRES SANIN - SENA - CENTRO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P52A', 'NUEVO LATIR - AV. DE LAS AMERICAS', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P52D', 'CIUDAD CORDOBA - CENTRO - TERMINAL', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P57', 'LOS MANGOS - UNIDAD DEPORTIVA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P71', 'CANEY - UNIDAD DEPORTIVA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('P72', 'CAPRI - CENTRO EMPRESA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A01A', 'SAN BOSCO - CAM - CENTRO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A02', 'ATENAS - ZOOLOGICO - SAN BOSCO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A03', 'NACIONAL - SANTA LIBRADA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A04', 'BELLA VISTA - MANZANA DEL SABER', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A05', 'LAS AMERICAS - C.A.M. - LA PORTADA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A06', 'AGUACATAL - SAN BOSCO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A07A', 'CONQUISTADORES - SANTA ELENA - CENTRO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A07B', 'SAN JUDAS TADEO - CENTRO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A11', 'ICESI - PUJ - UNIVERSIDADES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A14A', 'USB - UNIVERSIDADES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A17A', 'UAO - SAN MARTIN - UNIVERSIDADES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A21', 'CIUDADELA FLORALIA - TERMINAL MENGA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A22', 'BRISAS DE LOS ALAMOS - TERMINAL MENGA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A23', 'CIUDADELA LOS ALAMOS - TERMINAL MENGA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A33', 'CIUDADELA FLORALIA - CHIMINANGOS', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A35B', 'JORGE ELIECER - FLORA INDUSTRIAL', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A41A', 'PIZAMOS - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A41B', 'DECEPAZ - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A42A', 'TERMINAL ANDRES SANIN - LA CASONA - NUEVO LATIR', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A42B', 'LA CASONA - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A44A', 'MANUELA BELTRAN - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A44B', 'PUERTAS DEL SOL - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A45A', 'ALFONSO LOPEZ - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A47', 'PIZAMOS - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A53', 'AMANECER - LOS LAGOS - ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A71', 'LOS CHORROS - TERMINAL CAÑAVERALEJO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A72A', 'JARDINES DE LA AURORA - TERMINAL CAÑAVERALEJO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A72B', 'EL CORTIJO - TERMINAL CAÑAVERALEJO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A73', 'NAPOLES - CALDAS - CAÑAVERALEJO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A75', 'LA SIRENA - TERMINAL CAÑAVERALEJO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A85', 'SANTA MONICA - TERMINAL ANDRES SANIN', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A12A', 'ALTOS DE LA LUISA - ESTACION MELENDEZ', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A12B', 'LAS PALMAS - ESTACION MELENDEZ', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A12C', 'MELENDEZ - CAPRI', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A12D', 'ALTOS DE SANTA ELENA - ESTACION MELENDEZ', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A13A', 'CANEY - UNIVERSIDADES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A13B', 'VEGAS DE COMFANDI - CANEY', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A13C', 'LILI - UNIVERSIDADES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A14B', 'LA VORAGINE - UNIVERSIDADES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A17B', 'UNIVERSIDADES - HORMIGUERO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A17C', 'UNIVERSIDADES - BOCHALEMA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A18', 'HACIENDA EL CASTILLO - UNIVERSIDADES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A19A', 'COMFANDI PANCE - UNIVERSIDADES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A19B', 'LA BUITRERA - ESTACION BUITRERA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A24', 'SAN MIGUEL - EL BOSQUE - VIPASA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A31', 'FLORALIA - CHIMINANGOS', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A32', 'CHIMINANGOS - FLORA INDUSTRIAL', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A34', 'ALCAZARES - CHIMINANGOS', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A35A', 'CALIMIO NORTE - CHIMINANGOS', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A36', 'GUAYACANES - CHIMINANGOS', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A37A', 'SENA - SALOMIA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A37B', 'SENA - FLORA INDUSTRIAL', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A45B', 'SAN MARINO - VILLA COLOMBIA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A52', 'HOSPITAL DUARTE CANCINO - NUEVO LATIR', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A55', 'COMPARTIR - MANUELA BELTRAN - NUEVO LATIR', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A56', 'PIZAMOS - NUEVO LATIR', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A57', 'VILLA LUZ - NUEVO LATIR', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A70', 'MORTIÑAL - TEQUENDAMA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A76', 'HUV - TERMINAL CAÑAVERALEJO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A77', 'CIUDAD 2000 - REFUGIO', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A78A', 'LOS CHORROS - CALDAS', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A84A', 'LLANO VERDE - SANTA MONICA', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A86', 'MORICHAL - CONQUISTADORES', FALSE);
INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES ('A87', 'SENA ORIENTE - TERMINAL CALIPSO', FALSE);

INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('UNIVERSIDADES', 'Cr. 100 - Cl. 16', TRUE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('UNIDAD DEPORTIVA', 'Cl. 5 - Cr. 52', TRUE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CENTRO', 'Cl. 13 - Cr. 8', TRUE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CALIPSO', 'SIN DETALLES', TRUE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('NUEVO LATIR', 'Cr. 28D - Cl. 80', TRUE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('ANDRES SANIN', 'Cl. 75 - Cr. 19', TRUE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CHIMINANGOS', 'Cr. 1 - Cl. 62', TRUE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('MENGA', 'Av. 3 - Cl. 70N', TRUE);

INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('UNIVALLE', 'Cr. 100 - Cl. 13', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('BUITRERA', 'Cr. 100 - Cl. 11A', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('MELENDEZ', 'Cl. 5 - Cr. 94', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CAPRI', 'Cl. 5 - Cr. 78', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CALDAS', 'Cl. 5 - Cr. 70', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('REFUGIO', 'Cl. 5 - Cr. 66', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('PAMPALINDA', 'Cl. 5 - Cr. 62', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('PLAZA DE TOROS', 'Cl. 5 - Cr. 56', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CAÑAVERALEJO', 'Cl. 5 - Cr. 52', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('LIDO', 'Cl. 5 - Cr. 44', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('TEQUENDAMA', 'Cl. 5 - Cr. 39', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('ESTADIO', 'Cl. 5 - Cr. 34', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('MANZANA DEL SABER', 'Cl. 5 - Cr. 27', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SANTA LIBRADA', 'Cl. 5 - Cr. 22', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SAN BOSCO', 'Cr. 15 - Cl. 9', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SAN PASCUAL', 'Cr. 17 - Cl. 13/Cl. 15', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CIEN PALOS', 'Cr. 17 - Cl. 18', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('PRIMITIVO', 'Tr. 25 - Dg. 18', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SANTA MONICA', 'Tr. 25 - Cr. 28', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('VILLANUEVA', 'Tr. 25 - Cl. 32', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CONQUISTADORES', 'Tr. 29 - Cl. 44', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('TRONCAL UNIDA', 'Cr. 28D - Cl. 72I', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('AMANECER', 'Cr. 28D - Cl. 72U', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('BELALCAZAR', 'Cr. 15 - Cl. 21', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('FLORESTA', 'Cr. 15 - Cl. 30', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('ATANASIO GIRARDOT', 'Cr. 15 - Cl. 34', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('CHAPINERO', 'Cr. 15 - Cl. 44', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('VILLA COLOMBIA', 'Cr. 15 - Cl. 52', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('EL TREBOL', 'Cr. 15 - Cl. 58', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('7 DE AGOSTO', 'Dg. 15 - Cl. 71A', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('FRAY DAMIAN', 'Cl. 13 - Cr. 15', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SANTA ROSA', 'Cl. 13 - Cr. 8', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('PLAZA CAYZEDO', 'Cl. 13 - Cr. 4', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('LA ERMITA', 'Cl. 13 - Cr. 19', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SUCRE', 'Cl. 15 - Cr. 15', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('PETECUY', 'Cl. 15 - Cr. 8', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SAN PEDRO', 'Cl. 15 - Cr. 4', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('TORRE DE CALI', 'Av. 3 - Cl. 15', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SAN NICOLAS', 'Cr. 1 - Cl. 21', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('RIO CALI', 'Cr. 4N - Cl. 23', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('PILOTO', 'Cr. 1 - Cl. 24', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('FATIMA', 'Cr. 1 - Cl. 30', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('MANZANARES', 'Cr. 1 - Cl. 39', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('POPULAR', 'Cr. 1 - Cl. 44', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('SALOMIA', 'Cr. 1 - Cl. 47', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('FLORA INDUSTRIAL', 'Cr. 1 - Cl. 56', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('VERSALLES', 'Av. 3 - Cl. 21', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('LAS AMERICAS', 'Av. 3 - Cl. 23', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('PRADOS DEL NORTE', 'Av. 3 - Cl. 38', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('VIPASA', 'Av. 3 - Cl. 44', FALSE);
INSERT INTO estacion (estacion_nombre, estacion_ubicacion, estacion_estado) 
VALUES ('ALAMOS', 'Av. 3 - Cl. 52', FALSE);

INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre)
VALUES ('UNIVERSIDADES', 'E21'), ('UNIVERSIDADES', 'E31'), ('UNIVERSIDADES', 'T31'), ('UNIVERSIDADES', 'P12A'), 
('UNIVERSIDADES', 'P14A'), ('UNIVERSIDADES', 'P21B'), ('UNIVERSIDADES', 'P27C');
INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre)
VALUES ('UNIDAD DEPORTIVA', 'E21'), ('UNIDAD DEPORTIVA', 'E27'), ('UNIDAD DEPORTIVA', 'E31'),
('UNIDAD DEPORTIVA', 'E37'), ('UNIDAD DEPORTIVA', 'T31'), ('UNIDAD DEPORTIVA', 'T47A'), ('UNIDAD DEPORTIVA', 'T47B'),
('UNIDAD DEPORTIVA', 'T57A'), ('UNIDAD DEPORTIVA', 'P47A'), ('UNIDAD DEPORTIVA', 'P47B');
INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre)
VALUES ('CENTRO', 'E37'), ('CENTRO', 'T31'), ('CENTRO', 'T40'), ('CENTRO', 'T50'), ('CENTRO', 'P30A'), ('CENTRO', 'P40A');
INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre)
VALUES ('CALIPSO', 'E52'), ('CALIPSO', 'T50'), ('CALIPSO', 'T57A'), ('CALIPSO', 'P21B'), ('CALIPSO', 'P47B');
INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre)
VALUES ('NUEVO LATIR', 'T50'), ('NUEVO LATIR', 'T57A'), ('NUEVO LATIR', 'E52'), ('NUEVO LATIR', 'P12A'), ('NUEVO LATIR', 'P47C');
INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre)
VALUES ('ANDRES SANIN', 'T40'), ('ANDRES SANIN', 'T47A'), ('ANDRES SANIN', 'T47B'), ('ANDRES SANIN', 'P12A'),
('ANDRES SANIN', 'P14A'), ('ANDRES SANIN', 'P24A'), ('ANDRES SANIN', 'P24C'), ('ANDRES SANIN', 'P40A'),
('ANDRES SANIN', 'P47A'), ('ANDRES SANIN', 'P47B'), ('ANDRES SANIN', 'P47C');
INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre)
VALUES ('CHIMINANGOS', 'E31'), ('CHIMINANGOS', 'E37'), ('CHIMINANGOS', 'T31'), ('CHIMINANGOS', 'P12A'), ('CHIMINANGOS', 'P24A'),
('CHIMINANGOS', 'P24C'), ('CHIMINANGOS', 'P30A');
INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre)
VALUES ('MENGA', 'E21'), ('MENGA', 'E27'), ('MENGA', 'P12A'), ('MENGA', 'P21B'), ('MENGA', 'P24A'), ('MENGA', 'P24C'),
('MENGA', 'P27C'), ('MENGA', 'P30A');

INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100000, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100001, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100002, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100003, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100004, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100005, 1000, 'ACTIVA');

INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id, pasajero_estado)
VALUES ('123', 'pepito1', '1234567', 'Carrera', 'no tengo', 100000, TRUE);
INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id, pasajero_estado)
VALUES ('124', 'pepito2', '1234567', 'Carrera', 'no tengo', 100001, TRUE);
INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id, pasajero_estado)
VALUES ('125', 'pepito3', '1234567', 'Carrera', 'no tengo', 100002, TRUE);
INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id, pasajero_estado)
VALUES ('126', 'pepito4', '1234567', 'Carrera', 'no tengo', 100003, TRUE);
INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id, pasajero_estado)
VALUES ('127', 'pepito5', '1234567', 'Carrera', 'no tengo', 100004, FALSE);

INSERT INTO venta (venta_fecha, venta_valor, estacion_nombre, tarjeta_id)
VALUES ('2015-11-30 12:00:00', 2000, 'UNIVERSIDADES', '100000');
INSERT INTO venta (venta_fecha, venta_valor, estacion_nombre, tarjeta_id)
VALUES ('2015-11-30 12:03:00', 2000, 'UNIVERSIDADES', '100001');
INSERT INTO venta (venta_fecha, venta_valor, estacion_nombre, tarjeta_id)
VALUES ('2015-11-30 12:10:00', 2000, 'UNIVERSIDADES', '100005');

INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha)
VALUES ('100000','P10A','2015-12-15 12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha)
VALUES ('100000','P10B','2015-12-17 12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha)
VALUES ('100000','T31','2015-12-16 12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha)
VALUES ('100000','P10A','2015-12-18 12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha)
VALUES ('100001','P10B','2015-12-20 12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha)
VALUES ('100001','P10B','2015-12-19 12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha)
VALUES ('100001','P10D','2015-12-11 12:45:00');