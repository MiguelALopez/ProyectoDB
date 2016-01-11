/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
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
