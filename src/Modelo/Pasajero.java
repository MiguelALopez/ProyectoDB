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
public class Pasajero
{
    private String id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;
    private String tarjeta;

    public Pasajero(String id, String nombre, String telefono, String direccion, String email, String tarjeta) 
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.tarjeta = tarjeta;
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
}