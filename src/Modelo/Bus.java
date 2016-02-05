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
    private boolean estado;

    public Bus(String serial, String tipo, int capacidad, String ruta, boolean estado) 
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
        
        this.estado = estado;
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
    
    public String getEstado() {
        if (this.estado)
            return "Activo";
        else
            return "Inactivo";
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
