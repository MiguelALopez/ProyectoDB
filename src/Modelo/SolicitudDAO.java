/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: SolicitudDAO.java
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

public class SolicitudDAO
{
    ConexionBD conexionBD;
    public SolicitudDAO()
    {
        this.conexionBD = new ConexionBD();
    }
    /**
     * Insertar una solicitud en la base de datos
     * @param solicitud : Objeto solicitud a insertar
     * @return true: si se pudo insertar
     *         false: si no se pudo insertar
     */
    public boolean insertarSolicitud(Solicitud solicitud)
    {
        this.conexionBD.conectar();
        
        boolean exito = false;
        String query ="INSERT INTO solicitud"
            + "(solicitud_motivo, solicitud_descripcion, solicitud_fecha, solicitud_estado, pasajero_id, estacion_nombre) "
            + "VALUES"
            + "(?,?,CAST(? AS TIMESTAMP),?,?,?);";
        
        try
        {
            
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, solicitud.getMotivo());
            st.setString(2, solicitud.getDescripcion());
            
            
            st.setString(3, solicitud.getFecha());
            st.setString(4, solicitud.getEstado());
            st.setString(5, solicitud.getPasajero());
            st.setString(6, solicitud.getEstacion());
            
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
    /**
     * 
     * @param solicitud : Objeto solicitud a actualizar
     * @return true: si se actualiza satisfactoriamente la base de datos
     *         false: si no se actualiza satisfactoriamente la base de datos
     */
    public boolean actualizarSolicitud(Solicitud solicitud)
    {
        boolean exito = false;
        String query ="UPDATE solicitud SET"
            + "solicitud_motivo = ?,"
            + "solicitud_descripcion = ?";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            if (solicitud.getMotivo().isEmpty())
            {
                st.setNull(1, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(1, solicitud.getMotivo());
            }
            
            if (solicitud.getDescripcion().isEmpty())
            {
                st.setNull(2, java.sql.Types.VARCHAR);
            }
            else
            {
                st.setString(2, solicitud.getDescripcion());
            }
            
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
    /**
     * 
     * @return ArrayList : Lista con todas las solicitudes
     */
    public ArrayList<Solicitud> consultarSolicitudes()
    {
        ArrayList<Solicitud> lista = null;
        this.conexionBD.conectar();
        
        String query ="SELECT * FROM solicitud ORDER BY solicitud_id";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Solicitud(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getString(4), tabla.getString(5),tabla.getString(6),tabla.getString(7)));
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
    
    public ArrayList<Solicitud> consultarSolicitudes(String pasajero)
    {
        ArrayList<Solicitud> lista = null;
        this.conexionBD.conectar();
        
        String query ="SELECT * FROM solicitud WHERE pasajero_id = ? ORDER BY solicitud_id";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, pasajero);
            ResultSet tabla = st.executeQuery();
            
            lista = new ArrayList();
            
            while (tabla.next())
            {
                lista.add(new Solicitud(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getString(4), tabla.getString(5),tabla.getString(6),tabla.getString(7)));
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
    
    public Solicitud consultarSolicitud(String id)
    {
        conexionBD.conectar();        
        Solicitud solicitud = null;
        
        String query = "SELECT * FROM solicitud WHERE solicitud_id = ?;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);            
            st.setString(1, id);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                solicitud = new Solicitud(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getString(4), tabla.getString(5), tabla.getString(6), tabla.getString(7));
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
        
        return solicitud;
    }
    
    public boolean insertarMedida(String id, String medida, String estado, String fecha_medida)
    {
        this.conexionBD.conectar();
        boolean exito = false;
        
        String query ="BEGIN;"
            + "INSERT INTO solicitud_medidas (solicitud_id, solicitud_medida, solicitud_medida_fecha) "
            + "VALUES(?,?,CAST(? AS TIMESTAMP));"
            + "UPDATE solicitud SET solicitud_estado = ? WHERE solicitud_id = ?;"
                + "COMMIT;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            
            st.setString(1, id);
            
            st.setString(2, medida);
            st.setString(3, fecha_medida);
            st.setString(4, estado);
            st.setString(5, id);
            
            
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
    
    public String consultarMedida(String id)
    {
        conexionBD.conectar();        
        String medidas = "";
        
        String query = "SELECT * FROM solicitud_medidas WHERE solicitud_id = ? ORDER BY solicitud_medida_fecha ASC;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);            
            st.setString(1, id);
            
            ResultSet tabla = st.executeQuery();
            int indice = 1;
            while (tabla.next())
            {
                medidas += indice + " - " + tabla.getString(2) + "\n";
                indice++;
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
        
        return medidas;
    }
    
    public Solicitud getLastSolByPass(String pasajero)
    {
        Solicitud solicitud = null;
        
        conexionBD.conectar();        
        String medidas = "";
        
        String query = "SELECT * FROM solicitud WHERE pasajero_id = ? ORDER BY solicitud_id DESC LIMIT 1;";
        
        try
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);            
            st.setString(1, pasajero);
            
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                solicitud = new Solicitud(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getString(4), tabla.getString(5), tabla.getString(6), tabla.getString(7));
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
        
        
        return solicitud;
    }
}
