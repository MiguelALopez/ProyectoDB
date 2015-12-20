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
        
        String query = "INSERT INTO bus (bus_serial, bus_tipo, bus_capacidad, ruta_nombre) VALUES (?,?,?,?);";
        
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
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
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
                + "ruta_nombre = ? "
                + "WHERE bus_serial = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(4, bus.getSerial());
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
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return exito;
    }
    
    public boolean eliminarBus(String serial)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "DELETE FROM bus WHERE bus_serial = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, serial);
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
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
                bus = new Bus(tabla.getString(1), tabla.getString(2), tabla.getInt(3), tabla.getString(4));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return bus;
    }
    
    public ArrayList<Bus> consultarBuses()
    {
        conexionBD.conectar();        
        ArrayList<Bus> lista = null;
        
        String query = "SELECT * FROM bus;";
        
        try
        {
            Statement st = conexionBD.conexion.createStatement();
            ResultSet tabla = st.executeQuery(query);
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Bus(tabla.getString(1), 
                        tabla.getString(2), tabla.getInt(3), 
                        tabla.getString(4)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return lista;
    }
}
