/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: .java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
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
