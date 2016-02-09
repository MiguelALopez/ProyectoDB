/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class EstacionDAO 
{
    ConexionBD conexionBD;

    public EstacionDAO() 
    {
        this.conexionBD = new ConexionBD();
    }
    
    public boolean insertarEstacion(Estacion estacion)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "INSERT INTO estacion (estacion_nombre, estacion_ubicacion, director_empleado_id, estacion_estado) "
                + "VALUES (?,?,?,?);";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, estacion.getNombre());
            st.setString(2, estacion.getUbicacion());
            
            if (estacion.getDirector().isEmpty())
            {
                st.setNull(3, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(3, estacion.getDirector());
            }
            
            st.setBoolean(4, estacion.isEstado());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean modificarEstacion(Estacion estacion)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "UPDATE estacion SET "
                //+ "estacion_nombre = ?, "
                + "estacion_ubicacion = ?, "
                + "director_empleado_id = ?, "
                + "estacion_estado = ? "
                + "WHERE estacion_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(4, estacion.getNombre());
            st.setString(1, estacion.getUbicacion());
            
            if (estacion.getDirector().isEmpty())
            {
                st.setNull(2, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(2, estacion.getDirector());
            }
            
            st.setBoolean(3, estacion.isEstado());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean eliminarEstacion(String nombre)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        //String query1 = "DELETE FROM venta WHERE estacion_nombre = ?;";
        //String query2 = "DELETE FROM solicitud WHERE estacion_nombre = ?;";
        //String query3 = "DELETE FROM estacion_ruta WHERE estacion_nombre = ?;";
        String query = "UPDATE estacion SET estacion_estado = ? WHERE estacion_nombre = ?;";
        
        try
        {
            /*
            PreparedStatement st = conexionBD.conexion.prepareStatement(query1);
            st.setString(1, nombre);            
            int resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query2);
            st.setString(1, nombre);            
            resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query3);
            st.setString(1, nombre);            
            resultado = st.executeUpdate();
            */
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setBoolean(1, false);
            st.setString(2, nombre);
            int resultado = st.executeUpdate();
            
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public Estacion consultarEstacion(String nombre)
    {
        conexionBD.conectar();        
        Estacion estacion = null;
        
        String query = "SELECT * FROM estacion WHERE estacion_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);            
            st.setString(1, nombre);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                estacion = new Estacion(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getBoolean(4));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (conexionBD != null)
            {
                conexionBD.cerrarConexion();
            }
        }
        
        return estacion;
    }
    
    public ArrayList<Estacion> consultarEstaciones()
    {
        conexionBD.conectar();        
        ArrayList<Estacion> lista = null;
        
        String query = "SELECT * FROM estacion ORDER BY estacion_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Estacion(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getBoolean(4)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Estacion> consultarEstaciones(String ruta)
    {
        conexionBD.conectar();        
        ArrayList<Estacion> lista = null;
        
        String query = "SELECT estacion_nombre, estacion_ubicacion, director_empleado_id, estacion_estado "
                + "FROM estacion NATURAL JOIN estacion_ruta "
                + "WHERE ruta_nombre = ? "
                + "ORDER BY estacion_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, ruta);
            
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Estacion(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getBoolean(4)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Estacion> consultarEstaciones(boolean estado)
    {
        conexionBD.conectar();        
        ArrayList<Estacion> lista = null;
        
        String query = "SELECT * FROM estacion "
                + "WHERE estacion_estado = ?"
                + "ORDER BY estacion_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setBoolean(1, estado);
            
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Estacion(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getBoolean(4)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Estacion> consultarDestinos(String origen)
    {
        conexionBD.conectar();        
        ArrayList<Estacion> lista = null;
        
        String query = "SELECT DISTINCT estacion_nombre, estacion_ubicacion, director_empleado_id, estacion_estado "
                + "FROM (SELECT ruta_nombre "
                        + "FROM estacion NATURAL JOIN estacion_ruta "
                        + "WHERE estacion_nombre = ?) AS T NATURAL JOIN estacion_ruta NATURAL JOIN estacion "
                + "ORDER BY estacion_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, origen);
            
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Estacion(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getBoolean(4)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstacionDAO.class.getName()).log(Level.SEVERE, null, ex);
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
