/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: .java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Modelo;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TarjetaDAO {
    ConexionBD conexionBD;

    public TarjetaDAO(){
        this.conexionBD = new ConexionBD();
    }

    /**
     * Metodo encargado de insertar varias tarjetas nueva en la base de datos
     * @return retorna verdadero en caso de que se hallan insertado con exito y falso de lo contrario
     */
    public boolean insertarTarjetas(ArrayList<Tarjeta> tarjetas){
        boolean exito = false;

        String query = "INSERT INTO tarjeta(tarjeta_saldo, tarjeta_estado)" +
                "VALUES (?,?);";

        conexionBD.conectar();
        try {
            PreparedStatement st;
            for (int i = 0; i < tarjetas.size(); i++) {
                st = conexionBD.conexion.prepareStatement(query);
                st.setBigDecimal(1, BigDecimal.valueOf(tarjetas.get(i).getSaldo()));
                st.setString(2, tarjetas.get(i).getEstado());

                int resultado = st.executeUpdate();
            }
            exito = true;
        } catch (SQLException e) {
            System.err.println("Error al insertar una tarjeta");
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }
        return exito;
    }
    
    public boolean modificarTarjeta(Tarjeta t){
        boolean exito = false;

        String query = "UPDATE tarjeta SET "
                + "tarjeta_saldo = ?, "
                + "tarjeta_estado = ? "
                + "WHERE tarjeta_id = ?;";

        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setDouble(1, t.getSaldo());
            st.setString(2, t.getEstado());
            st.setString(3, t.getId());
            int resultado = st.executeUpdate();            
            exito = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }
        return exito;
    }

    /**
     * Metodo encargado de cambiar el estado de una tarjeta especifica
     * @param id identificacion de la tarjeta que se desea modificar
     * @param estado nuevo estado que se desea agregar
     * @return retorna verdadero si la operacion se realizo con exito
     */
    public boolean cambiarEstadoTarjeta(String id, String estado){
        boolean exito = false;

        String query = "UPDATE tarjeta SET tarjeta_estado = ? WHERE tarjeta_id = ?;";

        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);

            st.setString(1, estado);
            st.setString(2, id);

            int resultado = st.executeUpdate();
            exito = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }
        return exito;
    }

    /**
     * Metodo encargado de eliminar una tarjeta con el pasajero y solicitud asociada a esta
     * @param id identificacion de la tarjeta que se desea eliminar
     * @return retorna true si se completo la tarea exitosamente
     */
    public boolean eliminarTarjeta(String id){
        boolean exito = false;

        String query1 = "UPDATE tarjeta SET tarjeta_estado = ? WHERE tarjeta_id = ?;";
        String query2 = "UPDATE pasajero SET pasajero_estado = ? WHERE tarjeta_id = ?;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query1);
            st.setString(1, "BLOQUEADA");
            st.setString(2, id);
            int resultado = st.executeUpdate();
            if (resultado == 0){
                return false;
            }

            st = conexionBD.conexion.prepareStatement(query2);
            st.setBoolean(1, false);
            st.setString(2, id);
            st.executeUpdate();

            exito = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }
        return exito;
    }

    public ArrayList<Tarjeta> consultarTarjetas(){
        ArrayList<Tarjeta> tarjetas = null;

        String query = "SELECT * FROM tarjeta ORDER BY tarjeta_id;";
        conexionBD.conectar();
        try {
            Statement st = conexionBD.conexion.createStatement();
            ResultSet tabla = st.executeQuery(query);

            tarjetas = new ArrayList<>();

            while (tabla.next()){
                tarjetas.add(new Tarjeta(tabla.getString(1),
                        tabla.getDouble(2),
                        tabla.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }

        return tarjetas;
    }
    
    public ArrayList<Tarjeta> consultarTarjetasVendidas(boolean vendida)
    {
        ArrayList<Tarjeta> tarjetas = null;
        String query;
        if (vendida){
            query = "SELECT tarjeta_id, tarjeta_saldo, tarjeta_estado "
                    + "FROM tarjeta NATURAL JOIN venta WHERE tarjeta_estado <> 'BLOQUEADA';";
        }else {
            query = "SELECT * FROM tarjeta WHERE tarjeta_estado <> 'BLOQUEADA' "
                    + "EXCEPT "
                    + "SELECT tarjeta_id, tarjeta_saldo, tarjeta_estado "
                    + "FROM tarjeta NATURAL JOIN venta ORDER BY tarjeta_id;";
        }

        conexionBD.conectar();
        try 
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();

            tarjetas = new ArrayList<>();

            while (tabla.next())
            {
                tarjetas.add(new Tarjeta(tabla.getString(1), tabla.getDouble(2), tabla.getString(3)));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }

        return tarjetas;
    }
    
    public Tarjeta consultarTarjetaVendida(String id){
        Tarjeta t = null;

        String query = "SELECT tarjeta_id, tarjeta_saldo, tarjeta_estado "
                + "FROM tarjeta NATURAL JOIN venta "
                + "WHERE tarjeta_id = ?;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, id);
            ResultSet tabla = st.executeQuery();

            if (tabla.next()){
                t = new Tarjeta(tabla.getString(1), tabla.getDouble(2), tabla.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }

        return t;
    }
    
    public Tarjeta consultaTarjeta(String id)
    {
        Tarjeta t = null;

        String query = "SELECT tarjeta_id, tarjeta_saldo, tarjeta_estado "
                + "FROM tarjeta "
                + "WHERE tarjeta_id=?;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, id);
            ResultSet tabla = st.executeQuery();

            if (tabla.next()){
                t = new Tarjeta(tabla.getString(1), tabla.getDouble(2), tabla.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }

        return t;
    }
    
    public boolean esGenerica(String id)
    {
        return new PasajeroDAO().consultarPasajero(id) == null;
    }

    public boolean recargarTarjeta(String id, double cantidad){
        boolean exito = false;
        String query = "SELECT tarjeta_saldo FROM tarjeta WHERE tarjeta_id = ?;";
        String query2 = "UPDATE tarjeta SET tarjeta_saldo = ? WHERE tarjeta_id = ?;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1,id);
            ResultSet tabla = st.executeQuery();
            int saldo = -1;
            if (tabla.next()){
                saldo = tabla.getInt(1);
                st = conexionBD.conexion.prepareStatement(query2);
                st.setBigDecimal(1, BigDecimal.valueOf(saldo + cantidad));
                st.executeUpdate();
                exito = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }
        return exito;
    }
}
