/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: SoftwareMio.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
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
    private boolean estado;
    
    public Estacion(String nombre, String ubicacion, String director, boolean estado) 
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
        
        this.estado = estado;
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
