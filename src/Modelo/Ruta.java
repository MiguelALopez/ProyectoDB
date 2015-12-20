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

    public Ruta(String nombre, String descripcion) 
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
}
