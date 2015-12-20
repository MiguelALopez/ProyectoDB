/***********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 09-oct-2015
 * Nombre del Archivo: .java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle
 **********************************************/

package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class ConexionBD 
{
    private String url; // direccion de la base de datos
    private String user; // usuario de la base de datos
    private String password; // contraseña de la base de datos
    
    public Connection conexion; // conexion a la base de datos
    
    /**
     * constructor para inicializar los valores necesarios
     */
    public ConexionBD()
    {
        
        url = "jdbc:postgresql://localhost:5432/proyectobd";
        user = "postgres";
        password = "postgresql";
        
        /*
        url = "jdbc:postgresql://ec2-107-21-219-142.compute-1.amazonaws.com:5432/dbee7bg2hs2mfe?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        user = "tyjtckgxrzvutm";
        password = "vkbANSMRviN0md_cSyXhrtjpjj";
        */
    }
    
    /**
     * metodo para realizar la coneccion con la base de datos
     */
    public void conectar()
    {
        try 
        {
            // Se carga el driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver Cargado");
        } 
        catch (ClassNotFoundException e) 
        {
            System.err.println("No se pudo cargar el driver.");
        }

        try
        {
            //Crear el objeto de conexion a la base de datos
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion Abierta");            
        } 
        catch(SQLException e) 
        {
            conexion = null;
            System.err.println("No se pudo abrir la bd.");
        }
    }
    
    /**
     * metodo para cerrar una coneccion con la base de datos
     */
    public void cerrarConexion()
    {
        try
        {
           conexion.close();
           System.out.println("Conexión con BD cerrada!");
        }
        catch(SQLException ex)
        {
            System.err.println("No se pudo cerrar la conexion");
        }
    }
    
    public String getUrl() 
    {
        return url;
    }

    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUser() 
    {
        return user;
    }

    public void setUser(String user) 
    {
        this.user = user;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public Connection getConexion() 
    {
        return conexion;
    }

    public void setConexion(Connection conexion) 
    {
        this.conexion = conexion;
    }

    public static void main(String[] args) {
        ConexionBD conexionBD = new ConexionBD();
        conexionBD.conectar();
        conexionBD.cerrarConexion();
    }
}
