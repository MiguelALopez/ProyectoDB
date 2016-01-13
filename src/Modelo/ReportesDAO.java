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
public class ReportesDAO 
{
    ConexionBD conexionBD;

    public ReportesDAO() 
    {
        this.conexionBD = new ConexionBD();
    }
    
    public ArrayList<Pasajero> reporte1()
    {
        return new PasajeroDAO().consultarPasajeros();
    }
    
    public ArrayList<ArrayList<String>> reporte2(String fecha)
    {
        conexionBD.conectar();        
        ArrayList<ArrayList<String>> lista = null;
        
        String query = "SELECT ruta_nombre, COUNT(*) "
                + "FROM tarjeta_ruta "
                + "WHERE tarjeta_ruta_fecha = DATE(?) "                
                + "GROUP BY ruta_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, fecha);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                ArrayList<String> a = new ArrayList();
                a.add(tabla.getString(1));
                a.add(tabla.getString(2));
                lista.add(a);
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<ArrayList<String>> reporte3(String fecha)
    {
        conexionBD.conectar();        
        ArrayList<ArrayList<String>> lista = null;
        
        String query = "SELECT estacion_nombre, SUM(venta_valor) "
                + "FROM venta "
                + "WHERE venta_fecha = ? "                
                + "GROUP BY estacion_nombre;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, fecha);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                ArrayList<String> a = new ArrayList();
                a.add(tabla.getString(1));
                a.add(tabla.getString(2));
                lista.add(a);
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<ArrayList<String>> reporte4()
    {
        conexionBD.conectar();        
        ArrayList<ArrayList<String>> lista = null;
        
        String query = "SELECT bus.bus_serial, bus.ruta_nombre, empleado.empleado_id, empleado.empleado_nombre "
                + "FROM bus, turno, empleado "
                + "WHERE bus.bus_serial = turno.bus_serial AND turno.conductor_empleado_id = empleado.empleado_id AND bus.bus_tipo = 'Articulado';";
        
        String query2 = "(SELECT bus_serial, ruta_nombre FROM bus WHERE bus_tipo = 'Articulado') "
                + "EXCEPT "
                + "(SELECT bus_serial, ruta_nombre FROM bus NATURAL JOIN turno WHERE bus_tipo = 'Articulado');";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                ArrayList<String> a = new ArrayList();
                a.add(tabla.getString(1));
                a.add(tabla.getString(2));
                a.add(tabla.getString(3));
                a.add(tabla.getString(4));
                lista.add(a);
            }
            
            st = conexionBD.conexion.prepareStatement(query2);
            tabla = st.executeQuery();
            
            while (tabla.next())
            {
                ArrayList<String> a = new ArrayList();
                a.add(tabla.getString(1));
                a.add(tabla.getString(2));
                a.add("");
                a.add("");
                lista.add(a);
            } 
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Ruta> reporte5_1()
    {
        return new RutaDAO().consultarRutas();
    }
    
    public ArrayList<Estacion> reporte5_2()
    {
        return new EstacionDAO().consultarEstaciones();
    }
    
    public ArrayList<ArrayList<String>> reporte6()
    {
        conexionBD.conectar();        
        ArrayList<ArrayList<String>> lista = null;
        
        String query = "SELECT ruta_nombre, COUNT(*) "
                + "FROM ruta NATURAL JOIN tarjeta_ruta "
                + "GROUP BY ruta_nombre "
                + "ORDER BY COUNT(*) DESC;";
        
        String query2 = "(SELECT ruta_nombre FROM ruta) "
                + "EXCEPT "
                + "(SELECT ruta_nombre FROM ruta NATURAL JOIN tarjeta_ruta);";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                ArrayList<String> a = new ArrayList();
                a.add(tabla.getString(1));
                a.add(tabla.getString(2));
                lista.add(a);
            }
            
            st = conexionBD.conexion.prepareStatement(query2);
            tabla = st.executeQuery();
            
            while (tabla.next())
            {
                ArrayList<String> a = new ArrayList();
                a.add(tabla.getString(1));
                a.add("0");
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Solicitud> reporte7(String tarjeta)
    {
        //return new SolicitudDAO().consultarSolicitudes(tarjeta);
        return null;
    }
}
