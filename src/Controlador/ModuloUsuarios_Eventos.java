/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 10-ene-2016
 * Nombre del Archivo: ModuloUsuarios_Eventos.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */
package Controlador;

import Modelo.Pasajero;
import Modelo.PasajeroDAO;
import Vista.ModuloUsuarios;

import javax.swing.*;

public class ModuloUsuarios_Eventos {

    private ModuloUsuarios moduloUsuarios;

    public ModuloUsuarios_Eventos(final ModuloUsuarios moduloUsuarios){
        this.moduloUsuarios = moduloUsuarios;

        this.moduloUsuarios.bCrearUsuario.addActionListener(
                e -> {
                    crearUsuario();
                }
        );

        this.moduloUsuarios.bModifBuscar.addActionListener(
                e1 -> {
                    modifBuscarUsuario();
                }
        );

        this.moduloUsuarios.bModifCancelar.addActionListener(
                e1 -> {
                    limpiarCamposModificar();
                    enableModificar(false);
                }
        );

        this.moduloUsuarios.bModificarUsuario.addActionListener(
                e -> {
                    modificarUsuario();
                }
        );
    }

    public void limpiarCamposCrear(){
        moduloUsuarios.tCrearCedula.setText("");
        moduloUsuarios.tCrearNombre.setText("");
        moduloUsuarios.tCrearTelefono.setText("");
        moduloUsuarios.tCrearDireccion.setText("");
        moduloUsuarios.tCrearEmail.setText("");
        moduloUsuarios.tCrearTarjeta.setText("");
    }

    public void limpiarCamposModificar(){
        moduloUsuarios.tModifBuscarTarjeta.setText("");
        moduloUsuarios.tModifCedula.setText("");
        moduloUsuarios.tModifNombre.setText("");
        moduloUsuarios.tModifTelefono.setText("");
        moduloUsuarios.tModifDireccion.setText("");
        moduloUsuarios.tModifEmail.setText("");
        moduloUsuarios.tModifTarjeta.setText("");
    }

    public boolean verificarCamposCrear(){
        boolean exito = true;

        if (moduloUsuarios.tCrearCedula.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo cedula es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tCrearNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tCrearTelefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo telefono es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tCrearDireccion.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo direccion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tCrearEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo email es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tCrearTarjeta.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo ID tarjeta es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        return exito;
    }

    public boolean verificarCarmposModif(){
        boolean exito = true;

        if (moduloUsuarios.tModifCedula.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo cedula es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tModifNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tModifTelefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo telefono es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tModifDireccion.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo direccion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tModifEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo email es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloUsuarios.tModifTarjeta.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloUsuarios, "El campo ID tarjeta es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        return exito;

    }

    public void crearUsuario(){
        if (verificarCamposCrear()){
            String id = moduloUsuarios.tCrearCedula.getText();
            String nombre = moduloUsuarios.tCrearNombre.getText();
            String telefono = moduloUsuarios.tCrearTelefono.getText();
            String direccion = moduloUsuarios.tCrearDireccion.getText();
            String email = moduloUsuarios.tCrearEmail.getText();
            String tarjeta = moduloUsuarios.tCrearTarjeta.getText();

            int op = JOptionPane.showConfirmDialog(moduloUsuarios, "Desea crear el usuario " + id + "?", "", JOptionPane.YES_NO_OPTION );
            if (op == JOptionPane.YES_OPTION){
                Pasajero pasajero = new Pasajero(id,nombre,telefono,direccion,email,tarjeta);

                boolean exito = new PasajeroDAO().insertarPasajero(pasajero);

                if (exito){
                    JOptionPane.showMessageDialog(moduloUsuarios, "Usuario creado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCrear();
                }else {
                    JOptionPane.showMessageDialog(moduloUsuarios, "Error al crear el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void enableModificar(boolean enable){
        moduloUsuarios.bModifCancelar.setEnabled(enable);
        moduloUsuarios.tModifCedula.setEnabled(enable);
        moduloUsuarios.tModifNombre.setEnabled(enable);
        moduloUsuarios.tModifTelefono.setEnabled(enable);
        moduloUsuarios.tModifDireccion.setEnabled(enable);
        moduloUsuarios.tModifEmail.setEnabled(enable);
        moduloUsuarios.tModifTarjeta.setEnabled(enable);
        moduloUsuarios.tModifBuscarTarjeta.setEnabled(!enable);
    }

    public void modifBuscarUsuario(){
        if (!moduloUsuarios.tModifBuscarTarjeta.getText().isEmpty()){
            String tarjeta_id = moduloUsuarios.tModifBuscarTarjeta.getText();
            Pasajero pasajero = new PasajeroDAO().consultarPasajero(tarjeta_id);
            if (pasajero != null){
                enableModificar(true);

                moduloUsuarios.tModifCedula.setText(pasajero.getId());
                moduloUsuarios.tModifNombre.setText(pasajero.getNombre());
                moduloUsuarios.tModifTelefono.setText(pasajero.getTelefono());
                moduloUsuarios.tModifDireccion.setText(pasajero.getDireccion());
                moduloUsuarios.tModifEmail.setText(pasajero.getEmail());
                moduloUsuarios.tModifTarjeta.setText(pasajero.getTarjeta());

            }else {
                JOptionPane.showMessageDialog(moduloUsuarios, "No se encontro el pasajero.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(moduloUsuarios, "Es necesaria la tarjeta asociada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarUsuario(){
        if (verificarCarmposModif()){
            String id = moduloUsuarios.tModifCedula.getText();
            String nombre = moduloUsuarios.tModifNombre.getText();
            String telefono = moduloUsuarios.tModifTelefono.getText();
            String direccion = moduloUsuarios.tModifDireccion.getText();
            String email = moduloUsuarios.tModifEmail.getText();
            String tarjeta = moduloUsuarios.tModifTarjeta.getText();

            Pasajero pasajero = new Pasajero(id,nombre,telefono,direccion,email,tarjeta);

            boolean exito = new PasajeroDAO().modificarPasajero(pasajero);

            if (exito){
                JOptionPane.showMessageDialog(moduloUsuarios, "El usuario fue modificado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposModificar();
                enableModificar(false);
            }else {
                JOptionPane.showMessageDialog(moduloUsuarios, "Error al modificar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
