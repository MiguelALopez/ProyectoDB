/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        
        String query = "SELECT * FROM estacion_ruta WHERE estacion_nombre = ? AND ruta_nombre = ?;";
        
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
        
        String query = "SELECT * FROM estacion_ruta;";
        
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
