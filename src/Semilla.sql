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
    empleado_email      VARCHAR(100),
    empleado_salario    MONEY           NOT NULL,
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

DROP TABLE IF EXISTS tarjeta CASCADE;
CREATE TABLE tarjeta (
    tarjeta_id              VARCHAR(15) NOT NULL,
    tarjeta_saldo           MONEY       NOT NULL,
    tarjeta_estado          VARCHAR(30) NOT NULL,
    tarjeta_fecha_venta     DATE,
    tarjeta_precio_venta    MONEY,
    estacion_nombre         VARCHAR(50),
    PRIMARY KEY (tarjeta_id),
    FOREIGN KEY (estacion_nombre) REFERENCES estacion (estacion_nombre)
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

DROP TABLE IF EXISTS ruta_estacion CASCADE;
CREATE TABLE ruta_estacion (
    ruta_nombre     VARCHAR(50) NOT NULL,
    estacion_nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (ruta_nombre, estacion_nombre),
    FOREIGN KEY (ruta_nombre) REFERENCES ruta (ruta_nombre),
    FOREIGN KEY (estacion_nombre) REFERENCES estacion (estacion_nombre)
);

--INSERT INTO solicitud (solicitud_motivo, solicitud_descripcion, solicitud_fecha, solicitud_estado)
--VALUES ('ninguno', 'nada que hacer', '17-12-2015', 'nuevo');
--SELECT solicitud_id FROM solicitud;