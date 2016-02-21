/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
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
     * Metodo encargado de insertar un pasajero en la base de datos, si el pasajero ya existe y se encuentra inactivo se reactiva con una nueva tarjeta
     * @param pasajero: parametro que contiene el pasajero a insertar
     * @return retorna true si se completo la insercion correctamente
     */
    public boolean insertarPasajero(Pasajero pasajero){

        boolean exito = false;
        String query1 = "(SELECT tarjeta_id FROM venta NATURAL JOIN tarjeta WHERE tarjeta_id = ? AND tarjeta_estado = ?)" +
                "EXCEPT (SELECT tarjeta_id FROM venta NATURAL JOIN tarjeta NATURAL JOIN pasajero WHERE tarjeta_id = ?);";
        String query2 = "SELECT * FROM pasajero WHERE pasajero_id = ?;";
        String query3 = "INSERT INTO pasajero(pasajero_id, pasajero_nombre, pasajero_telefono, pasajero_direccion, pasajero_email, tarjeta_id, pasajero_estado) "
                + "VALUES (?,?,?,?,?,?,?);";
        String query4 = "UPDATE pasajero SET pasajero_nombre = ?, pasajero_telefono = ?, pasajero_direccion = ?, pasajero_email = ?, tarjeta_id = ?, pasajero_estado = ?" +
                "WHERE pasajero_id = ?;";

        conexionBD.conectar();
        try{
            PreparedStatement st = conexionBD.conexion.prepareStatement(query1);
            st.setString(1, pasajero.getTarjeta());
            st.setString(2, "ACTIVA");
            st.setString(3, pasajero.getTarjeta());
            ResultSet result1 = st.executeQuery();
            if (result1.next()) { // Se verifica que la tarjeta se halla vendido, esta activa y no le pertenece a alguien
                st = conexionBD.conexion.prepareStatement(query2);
                st.setString(1, pasajero.getId());
                ResultSet result2 = st.executeQuery();
                if (!result2.next()) { // Se verifica que el usuario no exista
                    st = conexionBD.conexion.prepareStatement(query3);
                    st.setString(1, pasajero.getId());
                    st.setString(2, pasajero.getNombre());
                    st.setString(3, pasajero.getTelefono());
                    st.setString(4, pasajero.getDireccion());
                    st.setString(5, pasajero.getEmail());
                    st.setString(6, pasajero.getTarjeta());
                    st.setBoolean(7, pasajero.isEstado());
                    st.executeUpdate();
                    exito = true;
                } else if (!result2.getBoolean(7)) { // Si existe el usuario se verifica que esta inactivo
                    st = conexionBD.conexion.prepareStatement(query4);
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
        }catch (SQLException e){
            e.printStackTrace();
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

        String query1 = "UPDATE pasajero SET "
                + "pasajero_nombre = ?, "
                + "pasajero_telefono = ?, "
                + "pasajero_direccion = ?, "
                + "pasajero_email = ?, "
                + "pasajero_estado = ? "
                + "WHERE tarjeta_id = ?;";

        String query2 = "UPDATE tarjeta SET " +
                "tarjeta_estado = ? WHERE tarjeta_id = ?;";

        try{
            PreparedStatement st = conexionBD.conexion.prepareStatement(query1);
            st.setString(1, pasajero.getNombre());
            st.setString(2, pasajero.getTelefono());
            st.setString(3, pasajero.getDireccion());
            st.setString(4, pasajero.getEmail());
            st.setBoolean(5, pasajero.isEstado());
            st.setString(6, pasajero.getTarjeta());
            st.executeUpdate();

            String estado = "ACTIVA";
            if (!pasajero.isEstado()){
                estado = "BLOQUEADA";
            }
            st = conexionBD.conexion.prepareStatement(query2);
            st.setString(1, estado);
            st.setString(2, pasajero.getTarjeta());
            st.executeUpdate();
            exito = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            conexionBD.cerrarConexion();
        }

        return exito;
    }

    /**
     * Metodo encargado de consultar un determinado pasajero atravez del id de su tarjeta
     * @param id identificacion encargada de buscar un usuario puede ser el ID de la tarjeta o el ID del pasajero
     * @param typeID encargado de identificar que tipo de ID se ingresa si es un ID tarjeta o una cedula del pasajero,
     *               si el TRUE identifica la cedula de un pasajero, si es FALSE identifica el id ID de una terjeta
     * @return retorna el pasajero asociado al id de la tarjeta, o null si no se encuentra
     * @throws NullPointerException Puede retornar null si no se encuentra el pasajero
     */
    public Pasajero consultarPasajero(String id, boolean typeID)
    {
        conexionBD.conectar();
        Pasajero pasajero = null;
        String query;
        if (typeID) {
            query = "SELECT * FROM pasajero "
                    + "WHERE pasajero_id = ?;";
        }else {
            query = "SELECT * FROM pasajero "
                    + "WHERE tarjeta_id = ?;";
        }
        try 
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setString(1, id);
            ResultSet tabla = st.executeQuery();
            
            if (tabla.next())
            {
                pasajero = new Pasajero(tabla.getString(1), tabla.getString(2), tabla.getString(3),
                        tabla.getString(4), tabla.getString(5), tabla.getString(6),
                        tabla.getBoolean(7));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            conexionBD.cerrarConexion();
        }
        
        return pasajero;
    }

    /**
     * Metodo encargado de buscar a todos los pasajaros que hacen parte de la base de datos
     * @return retorna a todos los pasajeros de la base de datos
     * @throws NullPointerException puede ocasionar fallos si la lista esta vacia
     */
    public ArrayList<Pasajero> consultarPasajeros() 
    {
        conexionBD.conectar();
        ArrayList<Pasajero> lista = null;

        String query = "SELECT * FROM pasajero;";

        try 
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            ResultSet tabla = st.executeQuery();

            lista = new ArrayList<>();

            while (tabla.next())
            {
                lista.add(new Pasajero(tabla.getString(1), tabla.getString(2), tabla.getString(3),
                        tabla.getString(4), tabla.getString(5), tabla.getString(6),
                        tabla.getBoolean(7)));
            }

        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            conexionBD.cerrarConexion();
        }
        
        return lista;
    }
    
    public ArrayList<Pasajero> consultarPasajeros(boolean estado) 
    {
        conexionBD.conectar();
        ArrayList<Pasajero> lista = null;

        String query = "SELECT * FROM pasajero WHERE pasajero_estado = ?;";

        try 
        {
            PreparedStatement st = conexionBD.conexion.prepareStatement(query);
            st.setBoolean(1, estado);
            ResultSet tabla = st.executeQuery();

            lista = new ArrayList<>();

            while (tabla.next())
            {
                lista.add(new Pasajero(tabla.getString(1), tabla.getString(2), tabla.getString(3),
                        tabla.getString(4), tabla.getString(5), tabla.getString(6),
                        tabla.getBoolean(7)));
            }

        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            conexionBD.cerrarConexion();
        }
        
        return lista;
    }
}
