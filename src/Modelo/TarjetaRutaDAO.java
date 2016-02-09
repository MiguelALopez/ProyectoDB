/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 11-ene-2016
 * Nombre del Archivo: TarjetaRutaDAO.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class TarjetaRutaDAO {

    ConexionBD conexionBD;

    public TarjetaRutaDAO(){
        this.conexionBD = new ConexionBD();
    }

    /**
     * Metodo encargado de insertar un registro de las rutas tomadas en la base de dato
     * @param tarjetaRuta registro nuevo que se insertara en la base de datos
     * @return retorna verdadero si la operacion se realizo con exito false de lo contrario
     */
    public boolean abordarRuta(Tarjeta t, TarjetaRuta tarjetaRuta)
    {
        boolean exito = false;
        String query = "BEGIN;"
                + "UPDATE tarjeta SET "
                + "tarjeta_saldo = ?, "
                + "tarjeta_estado = ? "
                + "WHERE tarjeta_id = ?;"
                + "INSERT INTO tarjeta_ruta(tarjeta_id, ruta_nombre, tarjeta_ruta_fecha) " 
                + "VALUES (?,?,CAST(? AS TIMESTAMP));"
                + "COMMIT;";
        
        conexionBD.conectar();
        
        try 
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setDouble(1, t.getSaldo());
            st.setString(2, t.getEstado());
            st.setString(3, t.getId());            
            st.setString(4, tarjetaRuta.getTarjeta_id());
            st.setString(5, tarjetaRuta.getRuta_nombre());
            st.setString(6, tarjetaRuta.getFecha());
            int res = st.executeUpdate();
            exito = true;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            conexionBD.cerrarConexion();
        }
        
        return exito;
    }

    /**
     * Metodo encargado de consultar todas las rutas abordadas por un usuario
     * @param tarjetaID identificacion de la tarjeta que se le desea hacer el seguimiento
     * @return retorna un ArrayList con los registros de rutas abordadas
     */
    public ArrayList<TarjetaRuta> consultarRegistrosUsuario(String tarjetaID){
        ArrayList<TarjetaRuta> list = null;
        String query = "SELECT * FROM tarjeta_ruta WHERE tarjeta_id = ? ORDER BY tarjeta_ruta_fecha;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, tarjetaID);
            ResultSet tabla = st.executeQuery();

            list = new ArrayList<>();

            while (tabla.next()){
                list.add(new TarjetaRuta(tabla.getString(1), tabla.getString(2), tabla.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexionBD.cerrarConexion();
        }
        return list;
    }
}
