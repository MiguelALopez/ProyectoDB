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
