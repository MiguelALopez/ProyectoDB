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
public class RutaDAO 
{
    ConexionBD conexionBD;

    public RutaDAO() 
    {
        this.conexionBD = new ConexionBD();
    }
    
    public boolean insertarRuta(Ruta ruta)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "INSERT INTO ruta (ruta_nombre, ruta_descripcion) VALUES (?,?);";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, ruta.getNombre());
            
            if (ruta.getDescripcion().isEmpty())
            {
                st.setNull(2, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(2, ruta.getDescripcion());
            }
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return exito;
    }
    
    public boolean modificarRuta(Ruta ruta)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "UPDATE ruta SET "
                //+ "ruta_nombre = ?, "
                + "ruta_descripcion = ?, "
                + "WHERE ruta_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(2, ruta.getNombre());
            
            if (ruta.getDescripcion().isEmpty())
            {
                st.setNull(1, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(1, ruta.getDescripcion());
            }
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return exito;
    }
    
    public boolean eliminarRuta(String nombre)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query1 = "UPDATE bus SET ruta_nombre = ? WHERE ruta_nombre = ?;";
        String query2 = "DELETE FROM tarjeta_ruta WHERE ruta_nombre = ?;";
        String query3 = "DELETE FROM estacion_ruta WHERE ruta_nombre = ?;";
        String query = "DELETE FROM ruta WHERE ruta_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query1);
            st.setNull(1, java.sql.Types.VARCHAR);
            st.setString(2, nombre);
            int resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, nombre);
            resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, nombre);
            resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, nombre);
            resultado = st.executeUpdate();
            
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return exito;
    }
    
    public Ruta consultarRuta(String nombre)
    {
        conexionBD.conectar();        
        Ruta ruta = null;
        
        String query = "SELECT * FROM ruta WHERE ruta_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);            
            st.setString(1, nombre);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                ruta = new Ruta(tabla.getString(1), tabla.getString(2));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return ruta;
    }
    
    public ArrayList<Ruta> consultarRutas()
    {
        conexionBD.conectar();        
        ArrayList<Ruta> lista = null;
        
        String query = "SELECT * FROM ruta;";
        
        try
        {
            Statement st = conexionBD.conexion.createStatement();
            ResultSet tabla = st.executeQuery(query);
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Ruta(tabla.getString(1), tabla.getString(2)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return lista;
    }
    
    public ArrayList<Ruta> consultarRutas(String estacion)
    {
        conexionBD.conectar();        
        ArrayList<Ruta> lista = null;
        
        String query = "SELECT ruta_nombre, ruta_descripcion FROM ruta NATURAL JOIN estacion_ruta WHERE estacion_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, estacion);
            
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Ruta(tabla.getString(1), tabla.getString(2)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return lista;
    }
}
