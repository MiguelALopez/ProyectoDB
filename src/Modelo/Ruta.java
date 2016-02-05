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
public class Ruta 
{
    private String nombre;
    private String descripcion;
    private boolean estado;

    public Ruta(String nombre, String descripcion, boolean estado) 
    {
        this.nombre = nombre;
        
        if (descripcion != null)
        {
            this.descripcion = descripcion;
        }
        else
        {
            this.descripcion  = "";
        }
        
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
