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
public class Estacion 
{
    private String nombre;
    private String ubicacion;
    private String director;

    public Estacion(String nombre, String ubicacion, String director) 
    {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        
        if (director != null)
        {
            this.director = director;
        }
        else
        {
            this.director  = "";
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }    
}
