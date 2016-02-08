/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 09-ene-2016
 * Nombre del Archivo: TarjetaDAO.java
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
            conexionBD.cerrarConexion();
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
            conexionBD.cerrarConexion();
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

        String query1 = "DELETE FROM solicitud WHERE pasajero_id = (SELECT pasajero_id FROM pasajero WHERE tarjeta_id = ?);";
        String query2 = "DELETE FROM pasajero WHERE tarjeta_id = ?";
        String query3 = "DELETE FROM tarjeta WHERE tarjeta_id = ?;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query1);
            st.setString(1, id);
            st.executeUpdate();

            st = conexionBD.conexion.prepareStatement(query2);
            st.setString(1,id);
            st.executeUpdate();

            st = conexionBD.conexion.prepareStatement(query3);
            st.setString(1, id);
            st.executeUpdate();

            exito = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexionBD.cerrarConexion();
        }
        return exito;
    }

    public ArrayList<Tarjeta> consultarTarjetas(){
        ArrayList<Tarjeta> tarjetas = null;

        String query = "SELECT * FROM tarjeta WHERE tarjeta_estado <> 'BLOQUEADA'";
        conexionBD.conectar();
        try {
            Statement st = conexionBD.conexion.createStatement();
            ResultSet tabla = st.executeQuery(query);

            tarjetas = new ArrayList<>();

            while (tabla.next()){
                tarjetas.add(new Tarjeta(tabla.getString(1),
                        tabla.getInt(2),
                        tabla.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tarjetas;
    }
}
