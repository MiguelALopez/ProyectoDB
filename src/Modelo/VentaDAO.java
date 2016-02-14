/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 13-feb-2016
 * Nombre del Archivo: VentaDAO.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Modelo;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class VentaDAO {

    ConexionBD conexionBD;

    public VentaDAO(){
        this.conexionBD = new ConexionBD();
    }

    public boolean insertarVenta(Venta venta){
        boolean exito = false;
        String query = "INSERT INTO venta(venta_fecha, venta_valor, estacion_nombre, tarjeta_id)" +
                "VALUES (?,?,?,?);";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);

            st.setTimestamp(1, Timestamp.valueOf(venta.getFecha()));
            st.setBigDecimal(2, BigDecimal.valueOf(venta.getValor()));
            st.setString(3, venta.getEstacion());
            st.setString(4, venta.getTarjeta());

            int resultado = st.executeUpdate();
            exito = true;
        } catch (SQLException e) {
            System.err.println("Error al hacer la consulta ");
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            System.err.println("Error en el formato de la fecha ");
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }
        return exito;
    }

    public Venta consultarVenta(String tarjeta_id){
        Venta venta = null;

        String query = "SELECT * FROM venta WHERE tarjeta_id = ?;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);

            st.setString(1, tarjeta_id);
            ResultSet tabla = st.executeQuery();
            if (tabla.next()){
                venta = new Venta(tabla.getString(1),
                        tabla.getTimestamp(2).toString(),
                        tabla.getBigDecimal(3).doubleValue(),
                        tabla.getString(4),
                        tabla.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }
        return venta;
    }

    public ArrayList<Venta> consultarVentas(){
        ArrayList<Venta> ventas = null;

        String query = "SELECT * FROM venta;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();
            ventas = new ArrayList<>();
            while (tabla.next()){
                ventas.add( new Venta(tabla.getString(1),
                        tabla.getTimestamp(2).toString(),
                        tabla.getBigDecimal(3).doubleValue(),
                        tabla.getString(4),
                        tabla.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conexionBD.conexion != null){
                conexionBD.cerrarConexion();
            }
        }
        return ventas;
    }

    public boolean eliminarVenta(String tarjeta_id){
        boolean exito = false;
        String query = "DELETE FROM venta WHERE tarjeta_id = ?;";
        conexionBD.conectar();
        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);

            st.setString(1, tarjeta_id);

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
}
