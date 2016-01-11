/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 11-ene-2016
 * Nombre del Archivo: TarjetaRuta.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Modelo;

import java.sql.Date;
import java.sql.Time;

public class TarjetaRuta {
    private String tarjeta_id;
    private String ruta_nombre;
    private Date fecha;
    private Time hora;

    /**
     * Constructor usado para llevar registro de las rutas tomadas por un pasajero
     * @param tarjeta_id numero de identificacion de la tarjeta del pasajero
     * @param ruta_nombre nombre de la ruta abordada por el pasajaero
     * @param fecha fecha en la cual fue abordada la ruta
     * @param hora hora en la cual fue abordada la ruta
     */
    public TarjetaRuta(String tarjeta_id, String ruta_nombre, Date fecha, Time hora) {
        this.tarjeta_id = tarjeta_id;
        this.ruta_nombre = ruta_nombre;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters and setters

    public String getTarjeta_id() {
        return tarjeta_id;
    }

    public void setTarjeta_id(String tarjeta_id) {
        this.tarjeta_id = tarjeta_id;
    }

    public String getRuta_nombre() {
        return ruta_nombre;
    }

    public void setRuta_nombre(String ruta_nombre) {
        this.ruta_nombre = ruta_nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}
