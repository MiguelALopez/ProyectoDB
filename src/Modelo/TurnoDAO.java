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
public class TurnoDAO 
{
    ConexionBD conexionBD;

    public TurnoDAO() 
    {
        this.conexionBD = new ConexionBD();
    }
    
    public boolean insertarTurno(Turno turno)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "INSERT INTO turno (bus_serial, turno_turno, conductor_empleado_id) VALUES (?,?,?);";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, turno.getBus());
            st.setString(2, turno.getTurno());
            st.setString(3, turno.getConductor());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return exito;
    }
    
    public boolean modificarTurno(Turno turno)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "UPDATE turno SET "
                //+ "bus_serial = ?, "
                //+ "turno_turno = ?, "
                + "conductor_empleado_id = ? "
                + "WHERE bus_serial = ? AND turno_turno = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(3, turno.getTurno());
            st.setString(2, turno.getBus());
            st.setString(1, turno.getConductor());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return exito;
    }
    
    public boolean eliminarTurno(String serial, String turno)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "DELETE FROM turno WHERE bus_serial = ? AND turno_turno = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, serial);
            st.setString(2, turno);
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return exito;
    }
    
    public boolean eliminarTurno(String conductor)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "DELETE FROM turno WHERE conductor_empleado_id = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, conductor);
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return exito;
    }
    
    public Turno consultarTurno(String conductor)
    {
        conexionBD.conectar();        
        Turno turno = null;
        
        String query = "SELECT * FROM turno WHERE conductor_empleado_id = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);            
            st.setString(1, conductor);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                turno = new Turno(tabla.getString(1), tabla.getString(2), tabla.getString(3));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return turno;
    }
    
    public Turno consultarTurno(String serial, String turno)
    {
        conexionBD.conectar();        
        Turno t = null;
        
        String query = "SELECT * FROM turno WHERE bus_serial = ? AND turno_turno = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, serial);
            st.setString(2, turno);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                t = new Turno(tabla.getString(1), tabla.getString(2), tabla.getString(3));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return t;
    }
    
    public ArrayList<Turno> consultarTurnos()
    {
        conexionBD.conectar();        
        ArrayList<Turno> lista = null;
        
        String query = "SELECT * FROM turno;";
        
        try
        {
            Statement st = conexionBD.conexion.createStatement();
            ResultSet tabla = st.executeQuery(query);
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Turno(tabla.getString(1), tabla.getString(2), tabla.getString(3)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexionBD.cerrarConexion();
        
        return lista;
    }
}
