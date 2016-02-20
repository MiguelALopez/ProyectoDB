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

    public boolean insertarVentaPersonalizada(Venta venta, Pasajero pasajero){
        boolean exito = false;
        String query1 = "SELECT * FROM venta WHERE tarjeta_id = ?";
        String query2 = "SELECT * FROM pasajero WHERE pasajero_id = ?";
        String query3 = "INSERT INTO venta(venta_fecha, venta_valor, estacion_nombre, tarjeta_id)" +
                "VALUES (?, ?, ?, ?)";
        String query4 = "INSERT INTO pasajero(pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id, pasajero_estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String query5 = "UPDATE pasajero SET pasajero_nombre = ?, pasajero_telefono = ?, pasajero_direccion = ?, pasajero_email = ?, tarjeta_id = ?, pasajero_estado = ?" +
                "WHERE pasajero_id = ?";
        conexionBD.conectar();

        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query1);
            st.setString(1, venta.getTarjeta());
            ResultSet resultSet1 = st.executeQuery();
            if (!resultSet1.next()){
                st = conexionBD.conexion.prepareStatement(query2);
                st.setString(1, pasajero.getId());
                ResultSet resultSet2 = st.executeQuery();
                if (!resultSet2.next()){
                    st = conexionBD.conexion.prepareStatement(query3);
                    st.setTimestamp(1, Timestamp.valueOf(venta.getFecha()));
                    st.setBigDecimal(2, BigDecimal.valueOf(venta.getValor()));
                    st.setString(3, venta.getEstacion());
                    st.setString(4, venta.getTarjeta());

                    st.executeUpdate();

                    st = conexionBD.conexion.prepareStatement(query4);
                    st.setString(1, pasajero.getId());
                    st.setString(2, pasajero.getNombre());
                    st.setString(3, pasajero.getTelefono());
                    st.setString(4, pasajero.getDireccion());
                    st.setString(5, pasajero.getEmail());
                    st.setString(6, pasajero.getTarjeta());
                    st.setBoolean(7, pasajero.isEstado());
                    st.executeUpdate();

                    exito = true;
                }else if (!resultSet2.getBoolean(7)){
                    st = conexionBD.conexion.prepareStatement(query3);
                    st.setTimestamp(1, Timestamp.valueOf(venta.getFecha()));
                    st.setBigDecimal(2, BigDecimal.valueOf(venta.getValor()));
                    st.setString(3, venta.getEstacion());
                    st.setString(4, venta.getTarjeta());

                    st.executeUpdate();

                    st = conexionBD.conexion.prepareStatement(query5);
                    st.setString(1, pasajero.getNombre());
                    st.setString(2, pasajero.getTelefono());
                    st.setString(3, pasajero.getDireccion());
                    st.setString(4, pasajero.getEmail());
                    st.setString(5, pasajero.getTarjeta());
                    st.setBoolean(6, pasajero.isEstado());
                    st.setString(7, pasajero.getId());

                    st.executeUpdate();
                    exito = true;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
