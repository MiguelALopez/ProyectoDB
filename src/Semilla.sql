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
    PRIMARY KEY (empleado_id),
    FOREIGN KEY (jefe_empleado_id) REFERENCES empleado (empleado_id)
);

DROP TABLE IF EXISTS estacion CASCADE;
CREATE TABLE estacion (
    estacion_nombre         VARCHAR(50) NOT NULL,
    estacion_ubicacion      VARCHAR(50) NOT NULL,
    director_empleado_id    VARCHAR(15) UNIQUE,
    PRIMARY KEY (estacion_nombre),
    FOREIGN KEY (director_empleado_id) REFERENCES empleado (empleado_id)
);

DROP TABLE IF EXISTS ruta CASCADE;
CREATE TABLE ruta (
    ruta_nombre         VARCHAR(50) NOT NULL,
    ruta_descripcion    TEXT,
    PRIMARY KEY (ruta_nombre)
);

DROP TABLE IF EXISTS bus CASCADE;
CREATE TABLE bus (
    bus_serial      VARCHAR(15) NOT NULL,
    bus_tipo        VARCHAR(30) NOT NULL,
    bus_capacidad   INTEGER     NOT NULL,
    ruta_nombre     VARCHAR(50),
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
    venta_fecha     DATE        NOT NULL,
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
    solicitud_fecha         DATE        NOT NULL,
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
    tarjeta_ruta_fecha  DATE        NOT NULL,
    tarjeta_ruta_hora   TIME        NOT NULL,
    PRIMARY KEY (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha, tarjeta_ruta_hora),
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

--INSERT INTO solicitud (solicitud_motivo, solicitud_descripcion, solicitud_fecha, solicitud_estado)
--VALUES ('ninguno', 'nada que hacer', '17-12-2015', 'nuevo');
--SELECT solicitud_id FROM solicitud;

-- Valores iniciales
INSERT INTO ruta (ruta_nombre, ruta_descripcion) VALUES ('P10A', 'Ruta de norte a sur');
INSERT INTO ruta (ruta_nombre, ruta_descripcion) VALUES ('P10B', 'Ruta de norte a sur');
INSERT INTO ruta (ruta_nombre, ruta_descripcion) VALUES ('P10D', 'Ruta de norte a sur');
INSERT INTO ruta (ruta_nombre, ruta_descripcion) VALUES ('T31', 'Ruta de norte a sur');
INSERT INTO ruta (ruta_nombre, ruta_descripcion) VALUES ('P40B', 'Ruta de norte a sur');
INSERT INTO ruta (ruta_nombre, ruta_descripcion) VALUES ('P40A', 'Ruta de norte a sur');

INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100000, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100001, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100002, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100003, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100004, 1000, 'ACTIVA');
INSERT INTO tarjeta (tarjeta_id, tarjeta_saldo, tarjeta_estado) VALUES (100005, 1000, 'ACTIVA');

INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id)
VALUES ('123', 'pepito1', '1234567', 'Carrera', 'no tengo', 100000);
INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id)
VALUES ('124', 'pepito2', '1234567', 'Carrera', 'no tengo', 100001);
INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id)
VALUES ('125', 'pepito3', '1234567', 'Carrera', 'no tengo', 100002);
INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id)
VALUES ('126', 'pepito4', '1234567', 'Carrera', 'no tengo', 100003);
INSERT INTO pasajero (pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id)
VALUES ('127', 'pepito5', '1234567', 'Carrera', 'no tengo', 100004);

INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha, tarjeta_ruta_hora)
VALUES ('100000','P10A','2015-12-15','12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha, tarjeta_ruta_hora)
VALUES ('100000','P10B','2015-12-17','12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha, tarjeta_ruta_hora)
VALUES ('100000','T31','2015-12-16','12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha, tarjeta_ruta_hora)
VALUES ('100000','P10A','2015-12-18','12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha, tarjeta_ruta_hora)
VALUES ('100001','P10B','2015-12-20','12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha, tarjeta_ruta_hora)
VALUES ('100001','P10B','2015-12-19','12:45:00');
INSERT INTO tarjeta_ruta (tarjeta_id, ruta_nombre, tarjeta_ruta_fecha, tarjeta_ruta_hora)
VALUES ('100001','P10D','2015-12-11','12:45:00');