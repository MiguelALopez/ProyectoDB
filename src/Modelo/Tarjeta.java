/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 11-ene-2016
 * Nombre del Archivo: Tarjeta.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Modelo;

public class Tarjeta 
{
    private String id;
    private double saldo;
    private String estado;

    /**
     * Constructor usado para simular una tarjeta del sistema MIO
     * @param id numero de identificacion de la tarjeta
     * @param saldo saldo de la tarjeta
     * @param estado estado de la tarjeta "ACTIVA" o "BLOQUEADA"
     */
    public Tarjeta(String id, double saldo, String estado){
        this.id = id;
        this.saldo = saldo;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    
}
