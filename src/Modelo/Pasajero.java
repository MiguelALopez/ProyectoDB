/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 10-ene-2016
 * Nombre del Archivo: Pasajero.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Modelo;

public class Pasajero
{
    private String id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;
    private String tarjeta;
    private boolean estado;

    /**
     * Constructor usado para simular a un pasajero registrado en el sistema MIO
     * @param id identificacion del pasajero (Cedula)
     * @param nombre nombre del pasajero
     * @param telefono telefono del pasajero
     * @param direccion direccion del pasajero
     * @param email email del pasajero
     * @param tarjeta numero de la tarjeta asignada al pasajero
     */
    public Pasajero(String id, String nombre, String telefono, String direccion, String email, String tarjeta, boolean estado) 
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.tarjeta = tarjeta;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
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
