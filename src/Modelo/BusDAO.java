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
public class BusDAO 
{
    ConexionBD conexionBD;

    public BusDAO() 
    {
        this.conexionBD = new ConexionBD();
    }
    
    public boolean insertarBus(Bus bus)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "INSERT INTO bus (bus_serial, bus_tipo, bus_capacidad, ruta_nombre, bus_estado) VALUES (?,?,?,?,?);";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, bus.getSerial());
            st.setString(2, bus.getTipo());
            st.setInt(3, bus.getCapacidad());
            
            if (bus.getRuta().isEmpty())
            {
                st.setNull(4, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(4, bus.getRuta());
            }
            
            st.setBoolean(5, bus.isEstado());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean modificarBus(Bus bus)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "UPDATE bus SET "
                //+ "bus_serial = ?, "
                + "bus_tipo = ?, "
                + "bus_capacidad = ?, "
                + "ruta_nombre = ?, "
                + "bus_estado = ? "
                + "WHERE bus_serial = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(5, bus.getSerial());
            st.setString(1, bus.getTipo());
            st.setInt(2, bus.getCapacidad());
            
            if (bus.getRuta().isEmpty())
            {
                st.setNull(3, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(3, bus.getRuta());
            }
            
            st.setBoolean(4, bus.isEstado());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean eliminarBus(String serial)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query1 = "DELETE FROM turno WHERE bus_serial = ?;";
        String query = "UPDATE bus SET bus_estado = ? WHERE bus_serial = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query1);
            st.setString(1, serial);
            int resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query);
            st.setBoolean(1, false);
            st.setString(2, serial);
            resultado = st.executeUpdate();
            
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public Bus consultarBus(String serial)
    {
        conexionBD.conectar();
        Bus bus = null;
        
        String query = "SELECT * FROM bus WHERE bus_serial = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);            
            st.setString(1, serial);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                bus = new Bus(tabla.getString(1), tabla.getString(2), tabla.getInt(3), 
                        tabla.getString(4), tabla.getBoolean(5));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (conexionBD != null)
            {
                conexionBD.cerrarConexion();
            }
        }
        
        return bus;
    }
    
    public ArrayList<Bus> consultarBuses()
    {
        conexionBD.conectar();        
        ArrayList<Bus> lista = null;
        
        String query = "SELECT * FROM bus;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Bus(tabla.getString(1), tabla.getString(2), tabla.getInt(3), 
                        tabla.getString(4), tabla.getBoolean(5)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Bus> consultarBuses(String ruta)
    {
        conexionBD.conectar();        
        ArrayList<Bus> lista = null;
        
        String query = "SELECT * FROM bus WHERE ruta_nombre = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, ruta);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Bus(tabla.getString(1), tabla.getString(2), tabla.getInt(3), 
                        tabla.getString(4), tabla.getBoolean(5)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Bus> consultarBuses(String ruta, boolean estado)
    {
        conexionBD.conectar();        
        ArrayList<Bus> lista = null;
        
        String query = "SELECT * FROM bus WHERE ruta_nombre = ? AND bus_estado = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, ruta);
            st.setBoolean(2, estado);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Bus(tabla.getString(1), tabla.getString(2), tabla.getInt(3), 
                        tabla.getString(4), tabla.getBoolean(5)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Bus> consultarBuses(boolean estado)
    {
        conexionBD.conectar();        
        ArrayList<Bus> lista = null;
        
        String query = "SELECT * FROM bus WHERE bus_estado = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setBoolean(1, estado);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Bus(tabla.getString(1), tabla.getString(2), tabla.getInt(3), 
                        tabla.getString(4), tabla.getBoolean(5)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
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
