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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class EstacionRutaDAO 
{
    ConexionBD conexionBD;
    
    public EstacionRutaDAO() 
    {
        this.conexionBD = new ConexionBD();
    }
    
    public boolean insertarEstacionRuta(EstacionRuta er)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "INSERT INTO estacion_ruta (estacion_nombre, ruta_nombre) VALUES (?,?);";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, er.getEstacion());
            st.setString(2, er.getRuta());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionRutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (conexionBD != null)
            {
                conexionBD.cerrarConexion();
            }
        }
        
        return exito;
    }
    
    public boolean eliminarEstacionRuta(String estacion, String ruta)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "DELETE FROM estacion_ruta WHERE estacion_nombre = ? AND ruta_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, estacion);
            st.setString(2, ruta);
            
            int resultado = st.executeUpdate();            
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionRutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (conexionBD != null)
            {
                conexionBD.cerrarConexion();
            }
        }
        
        return exito;
    }
    
    public EstacionRuta consultarEstacionRuta(String estacion, String ruta)
    {
        conexionBD.conectar();
        EstacionRuta er = null;
        
        String query = "SELECT * FROM estacion_ruta "
                + "WHERE estacion_nombre = ? AND ruta_nombre = ? "
                + "ORDER BY estacion_nombre, ruta_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, estacion);
            st.setString(2, ruta);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                er = new EstacionRuta(tabla.getString(1), tabla.getString(2));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionRutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (conexionBD != null)
            {
                conexionBD.cerrarConexion();
            }
        }
        
        return er;
    }
    
    public ArrayList<EstacionRuta> consultarEstacionRutas()
    {
        conexionBD.conectar();        
        ArrayList<EstacionRuta> lista = null;
        
        String query = "SELECT * FROM estacion_ruta ORDER BY estacion_nombre, ruta_nombre;";
        
        try
        {
            Statement st = conexionBD.conexion.createStatement();
            ResultSet tabla = st.executeQuery(query);
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new EstacionRuta(tabla.getString(1), tabla.getString(2)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionRutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (conexionBD != null)
            {
                conexionBD.cerrarConexion();
            }
        }
        
        return lista;
    }
}
