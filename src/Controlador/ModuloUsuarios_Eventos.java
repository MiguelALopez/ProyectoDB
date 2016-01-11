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
import Modelo.TarjetaRuta;
import Modelo.TarjetaRutaDAO;
import Vista.ModuloUsuarios;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ModuloUsuarios_Eventos {

    private ModuloUsuarios moduloUsuarios;
    private TableRowSorter sorter;

    public ModuloUsuarios_Eventos(final ModuloUsuarios moduloUsuarios){
        this.moduloUsuarios = moduloUsuarios;
        sorter = new TableRowSorter<>(moduloUsuarios.tableUsuarios.getModel());
        moduloUsuarios.tableUsuarios.setRowSorter(sorter);

        this.moduloUsuarios.bCrearUsuario.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        crearUsuario();
                    }
                }
        );

        this.moduloUsuarios.bModifBuscar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        modifBuscarUsuario();
                    }
                }
        );

        this.moduloUsuarios.bModifCancelar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        limpiarCamposModificar();
                        enableModificar(false);
                    }
                }
        );

        this.moduloUsuarios.bModificarUsuario.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        modificarUsuario();
                    }
                }
        );

        this.moduloUsuarios.jTabbedPane1.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane TabbedPane = (JTabbedPane) e.getSource();
                        int tab = TabbedPane.getSelectedIndex();
                        if (tab == 2) {
                            consultarUsuarios();
                        } else if (tab == 3) {

                        }
                    }
                }
        );

        this.moduloUsuarios.bConsulActualizar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        consultarUsuarios();
                    }
                }
        );

        this.moduloUsuarios.tConsulBuscarTarjeta.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
//                        super.keyReleased(e);
//                        String cadena = (moduloUsuarios.tConsulBuscarTarjeta.getText());
//                        moduloUsuarios.tConsulBuscarTarjeta.setText(cadena);
//                        moduloUsuarios.repaint();
                        filtro();
                    }
                }
        );

        this.moduloUsuarios.bHistoryBuscar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        consutarHistorial();
                    }
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

    public void consultarUsuarios(){
        ArrayList<Pasajero> lista = new PasajeroDAO().consultarPasajeros();

        if (lista!= null){
            DefaultTableModel model = (DefaultTableModel) moduloUsuarios.tableUsuarios.getModel();
            model.setRowCount(lista.size());

            for (int i = 0; i < lista.size(); i++) {
                model.setValueAt(lista.get(i).getId(),i,0);
                model.setValueAt(lista.get(i).getTarjeta(),i,1);
                model.setValueAt(lista.get(i).getNombre(),i,2);
                model.setValueAt(lista.get(i).getTelefono(),i,3);
                model.setValueAt(lista.get(i).getDireccion(),i,4);
                model.setValueAt(lista.get(i).getEmail(),i,5);
            }
        }
    }
    public void filtro() {
        sorter.setRowFilter(RowFilter.regexFilter(moduloUsuarios.tConsulBuscarTarjeta.getText(), 1));
    }

    public void consutarHistorial(){
        ArrayList<TarjetaRuta> list = new TarjetaRutaDAO().consultarRegistrosUsuario(moduloUsuarios.tHistoryBuscarTarjeta.getText());
        if (list != null){
            DefaultTableModel model = (DefaultTableModel) moduloUsuarios.tableHistory.getModel();
            model.setRowCount(list.size());

            for (int i = 0; i < list.size(); i++) {
                model.setValueAt(list.get(i).getRuta_nombre(),i,0);
                model.setValueAt(list.get(i).getFecha(),i,1);
                model.setValueAt(list.get(i).getHora(),i,2);
            }
        }
    }
}
