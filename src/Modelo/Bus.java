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
public class Bus 
{
    private String serial;
    private String tipo;
    private int capacidad;
    private String ruta;

    public Bus(String serial, String tipo, int capacidad, String ruta) 
    {
        this.serial = serial;
        this.tipo = tipo;
        this.capacidad = capacidad;
        
        if (ruta != null)
        {
            this.ruta = ruta;
        }
        else
        {
            this.ruta  = "";
        }
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }    
}
