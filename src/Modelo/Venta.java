/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class Venta 
{
    private String id;
    private String fecha;
    private double valor;
    private String estacion;
    private String tarjeta;

    public Venta(String fecha, double valor, String estacion, String tarjeta){
        this.fecha = fecha;
        this.valor = valor;
        this.estacion = estacion;
        this.tarjeta = tarjeta;
    }

    public Venta(String id, String fecha, double valor, String estacion, String tarjeta) {
        this.id = id;
        this.fecha = fecha;
        this.valor = valor;
        this.estacion = estacion;
        this.tarjeta = tarjeta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }    
}
