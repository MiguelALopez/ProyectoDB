/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: Empleado.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Modelo;

public class Empleado 
{
    private String id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;
    private String cargo;
    private double salario;
    private String jefe;
    private boolean estado;

    public Empleado(String id, String nombre, String telefono, String direccion, String email, String cargo, double salario, String jefe, boolean estado) 
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.cargo = cargo;
        this.salario = salario;
        
        if (jefe != null)
        {
            this.jefe = jefe;
        }
        else
        {
            this.jefe  = "";
        }
        
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public boolean isEstado() {
        return estado;
    }
    
    public String getEstado() {
        if (this.estado)
            return "Activo";
        else
            return "Inactivo";
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}