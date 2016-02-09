/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 09-ene-2016
 * Nombre del Archivo: PasajeroDAO.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class PasajeroDAO {
    ConexionBD conexionBD;

    public PasajeroDAO(){
        this.conexionBD = new ConexionBD();
    }

    /**
     * Metodo encargado de insertar un pasajero en la base de datos
     * @param pasajero: parametro que contiene el pasajero a insertar
     * @return retorna true si se completo la insercion correctamente
     */
    public boolean insertarPasajero(Pasajero pasajero){
        conexionBD.conectar();
        boolean exito = false;

        String query = "INSERT INTO pasajero(pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id)" +
                "VALUES (?,?,?,?,?,?);";
        try{
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);

            st.setString(1, pasajero.getId());
            st.setString(2, pasajero.getNombre());
            st.setString(3, pasajero.getTelefono());
            st.setString(4, pasajero.getDireccion());
            if (pasajero.getEmail().isEmpty()){ // Por si no tiene email
                st.setNull(5, Types.VARCHAR);
            }else {
                st.setString(5, pasajero.getEmail());
            }
            st.setString(6, pasajero.getTarjeta());

            int resultado = st.executeUpdate();
            exito = true;
        }catch (SQLException e){
            System.err.println("Error al insertar el pasajero");
        }finally {
            conexionBD.cerrarConexion();
        }
        return exito;
    }

    /**
     * Metodo encargado de modificar un pasajero
     * @param pasajero: pasajero que se va a modificar
     * @return retorna true si la operacion se realizo correctamente
     */
    public boolean modificarPasajero(Pasajero pasajero){
        conexionBD.conectar();
        boolean exito = false;

        String query = "UPDATE pasajero SET " +
                "pasajero_id = ?," +
                "pasajero_nombre = ?," +
                "pasajero_telefono = ?," +
                "pasajero_direccion = ?," +
                "pasajero_email = ?" +
                "WHERE tarjeta_id = ?;";

        try{
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);

            st.setString(1, pasajero.getNombre());
            st.setString(2, pasajero.getTelefono());
            st.setString(3, pasajero.getDireccion());
            if (pasajero.getEmail().isEmpty()){ // Por si no tiene email
                st.setNull(4, Types.VARCHAR);
            }else {
                st.setString(4, pasajero.getEmail());
            }
            st.setString(5, pasajero.getTarjeta());

            st.executeUpdate();
            exito = true;
        }catch (SQLException e){
            System.err.println("Error al Modificar un pasajero");
        }finally {
            conexionBD.cerrarConexion();
        }

        return exito;
    }

    /**
     * Metodo encargado de consultar un determinado pasajero atravez del id de su tarjeta
     * @param tarjeta_id identificacion de la terjeta del pasajero
     * @return retorna el pasajero asociado al id de la tarjeta, o null si no se encuentra
     * @throws NullPointerException Puede retornar null si no se encuentra el pasajero
     */
    public Pasajero consultarPasajero(String tarjeta_id) throws NullPointerException{
        conexionBD.conectar();
        Pasajero pasajero = null;

        String query = "SELECT * FROM pasajero "
                + "WHERE tarjeta_id = ?;";

        try {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);

            st.setString(1, tarjeta_id);
            ResultSet tabla = st.executeQuery();
            if (tabla.next()){
                pasajero = new Pasajero(tabla.getString(1),
                        tabla.getString(2),
                        tabla.getString(3),
                        tabla.getString(4),
                        tabla.getString(5),
                        tabla.getString(6));
            }
        }catch (SQLException e){
            System.err.println("Error al buscar al usuario");
        }finally {
            conexionBD.cerrarConexion();
        }
        return pasajero;
    }

    /**
     * Metodo encargado de buscar a todos los pasajaros que hacen parte de la base de datos
     * @return retorna a todos los pasajeros de la base de datos
     * @throws NullPointerException puede ocasionar fallos si la lista esta vacia
     */
    public ArrayList<Pasajero> consultarPasajeros() throws NullPointerException{
        conexionBD.conectar();
        ArrayList<Pasajero> lista = null;

        String query = "SELECT * FROM pasajero;";

        try {
            Statement st = conexionBD.conexion.createStatement();
            ResultSet tabla = st.executeQuery(query);

            lista = new ArrayList<>();

            while (tabla.next()){
                lista.add(new Pasajero(tabla.getString(1),
                        tabla.getString(2),
                        tabla.getString(3),
                        tabla.getString(4),
                        tabla.getString(5),
                        tabla.getString(6)));
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar la lista de pasajeros");
        }finally {
            conexionBD.cerrarConexion();
        }
        return lista;
    }
}
