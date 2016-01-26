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
public class EmpleadoDAO 
{
    ConexionBD conexionBD;

    public EmpleadoDAO() 
    {
        this.conexionBD = new ConexionBD();
    }
    
    public boolean insertarEmpleado(Empleado empleado)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "INSERT INTO empleado (empleado_id, empleado_nombre, empleado_telefono, empleado_direccion, "
                + "empleado_email, empleado_cargo, empleado_salario, jefe_empleado_id, empleado_estado) "
                + "VALUES (?,?,?,?,?,?,?,?,?);";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, empleado.getId());
            st.setString(2, empleado.getNombre());
            st.setString(3, empleado.getTelefono());
            st.setString(4, empleado.getDireccion());
            st.setString(5, empleado.getEmail());
            st.setString(6, empleado.getCargo());
            st.setObject(7, empleado.getSalario(), java.sql.Types.DECIMAL);
            
            if (empleado.getJefe().isEmpty())
            {
                st.setNull(8, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(8, empleado.getJefe());
            }
            
            st.setBoolean(9, empleado.isEstado());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean modificarEmpleado(Empleado empleado)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "UPDATE empleado SET "
                //+ "empleado_id = ?, "
                + "empleado_nombre = ?, "
                + "empleado_telefono = ?, "
                + "empleado_direccion = ?, "
                + "empleado_email = ?, "
                + "empleado_cargo = ?, "
                + "empleado_salario = ?, "
                + "jefe_empleado_id = ?, "
                + "empleado_estado = ? "
                + "WHERE empleado_id = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, empleado.getNombre());
            st.setString(2, empleado.getTelefono());
            st.setString(3, empleado.getDireccion());
            st.setString(4, empleado.getEmail());
            st.setString(5, empleado.getCargo());
            st.setObject(6, empleado.getSalario(), java.sql.Types.DECIMAL);
            
            if (empleado.getJefe().isEmpty())
            {
                st.setNull(7, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(7, empleado.getJefe());
            }
            
            st.setBoolean(8, empleado.isEstado());            
            st.setString(9, empleado.getId());
            
            int resultado = st.executeUpdate();
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean eliminarEmpleado(String id)
    {
        conexionBD.conectar();
        boolean exito = false;
        
        String query = "UPDATE empleado SET empleado_estado = ? WHERE empleado_id = ?;";
        String query1 = "UPDATE estacion SET director_empleado_id = NULL WHERE director_empleado_id = ?;";
        String query2 = "DELETE FROM turno WHERE conductor_empleado_id = ?;";
        String query3 = "UPDATE empleado SET jefe_empleado_id = NULL WHERE jefe_empleado_id = ?;";        
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setBoolean(1, false);
            st.setString(2, id);
            int resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query1);
            st.setString(1, id);
            resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query2);
            st.setString(1, id);
            resultado = st.executeUpdate();
            
            st = conexionBD.conexion.prepareStatement(query3);
            st.setString(1, id);
            resultado = st.executeUpdate();
            
            exito = true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public Empleado consultarEmpleado(String id)
    {
        conexionBD.conectar();        
        Empleado empleado = null;
        
        String query = "SELECT * FROM empleado WHERE empleado_id = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);            
            st.setString(1, id);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                empleado = new Empleado(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getString(4),
                    tabla.getString(5), tabla.getString(6), tabla.getDouble(7), tabla.getString(8), tabla.getBoolean(9));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (conexionBD != null)
            {
                conexionBD.cerrarConexion();
            }
        }
        
        return empleado;
    }
    
    public ArrayList<Empleado> consultarEmpleados()
    {
        conexionBD.conectar();        
        ArrayList<Empleado> lista = null;
        
        String query = "SELECT * FROM empleado;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Empleado(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getString(4),
                    tabla.getString(5), tabla.getString(6), tabla.getDouble(7), tabla.getString(8), tabla.getBoolean(9)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<Empleado> consultarDirectores()
    {
        conexionBD.conectar();        
        ArrayList<Empleado> lista = null;
        
        String query = "SELECT * FROM empleado WHERE empleado_cargo = 'Director' AND empleado_estado = true;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Empleado(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getString(4),
                    tabla.getString(5), tabla.getString(6), tabla.getDouble(7), tabla.getString(8), tabla.getBoolean(9)));
            }        
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
