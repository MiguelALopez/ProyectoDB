/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: RutaDAO.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        String query = "INSERT INTO ruta (ruta_nombre, ruta_descripcion, ruta_estado) VALUES (?,?,?);";
        
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
            
            st.setBoolean(3, ruta.isEstado());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean modificarRuta(Ruta ruta)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "UPDATE ruta SET "
                //+ "ruta_nombre = ?, "
                + "ruta_descripcion = ?, "
                + "ruta_estado = ? "
                + "WHERE ruta_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(3, ruta.getNombre());
            
            if (ruta.getDescripcion().isEmpty())
            {
                st.setNull(1, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(1, ruta.getDescripcion());
            }
            
            st.setBoolean(2, ruta.isEstado());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean eliminarRuta(String nombre)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        //String query1 = "UPDATE bus SET ruta_nombre = ? WHERE ruta_nombre = ?;";
        //String query2 = "DELETE FROM tarjeta_ruta WHERE ruta_nombre = ?;";
        //String query3 = "DELETE FROM estacion_ruta WHERE ruta_nombre = ?;";
        String query = "UPDATE ruta SET ruta_estado = ? WHERE ruta_nombre = ?;";
        
        try
        {
            /*
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
            */
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setBoolean(1, false);
            st.setString(2, nombre);
            int resultado = st.executeUpdate();
            
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                ruta = new Ruta(tabla.getString(1), tabla.getString(2), tabla.getBoolean(3));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (conexionBD != null)
            {
                conexionBD.cerrarConexion();
            }
        }
        
        return ruta;
    }
    
    public ArrayList<Ruta> consultarRutas()
    {
        conexionBD.conectar();        
        ArrayList<Ruta> lista = null;
        
        String query = "SELECT * FROM ruta ORDER BY ruta_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Ruta(tabla.getString(1), tabla.getString(2), tabla.getBoolean(3)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Ruta> consultarRutas(String estacion)
    {
        conexionBD.conectar();        
        ArrayList<Ruta> lista = null;
        
        String query = "SELECT ruta_nombre, ruta_descripcion, ruta_estado "
                + "FROM ruta NATURAL JOIN estacion_ruta "
                + "WHERE estacion_nombre = ?"
                + "ORDER BY ruta_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, estacion);
            
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Ruta(tabla.getString(1), tabla.getString(2), tabla.getBoolean(3)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Ruta> consultarRutas(String estacion, boolean estado)
    {
        conexionBD.conectar();        
        ArrayList<Ruta> lista = null;
        
        String query = "SELECT ruta_nombre, ruta_descripcion, ruta_estado "
                + "FROM ruta NATURAL JOIN estacion_ruta "
                + "WHERE estacion_nombre = ? AND ruta_estado = ?"
                + "ORDER BY ruta_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, estacion);
            st.setBoolean(2, estado);
            
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Ruta(tabla.getString(1), tabla.getString(2), tabla.getBoolean(3)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Ruta> consultarRutas(boolean estado)
    {
        conexionBD.conectar();        
        ArrayList<Ruta> lista = null;
        
        String query = "SELECT * FROM ruta "
                + "WHERE ruta_estado = ?"
                + "ORDER BY ruta_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setBoolean(1, estado);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Ruta(tabla.getString(1), tabla.getString(2), tabla.getBoolean(3)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Ruta> consultarRutasOrigenDestino(String origen, String destino)
    {
        conexionBD.conectar();        
        ArrayList<Ruta> lista = null;
        
        String query = "SELECT ruta_nombre, ruta_descripcion, ruta_estado "
                + "FROM ruta NATURAL JOIN estacion_ruta "
                + "WHERE estacion_nombre = ? "
                + "INTERSECT "
                + "SELECT ruta_nombre, ruta_descripcion, ruta_estado "
                + "FROM ruta NATURAL JOIN estacion_ruta "
                + "WHERE estacion_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, origen);
            st.setString(2, destino);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Ruta(tabla.getString(1), tabla.getString(2), tabla.getBoolean(3)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RutaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
