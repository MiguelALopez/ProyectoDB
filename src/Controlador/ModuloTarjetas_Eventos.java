/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: SoftwareMio.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Controlador;

import Modelo.Tarjeta;
import Modelo.TarjetaDAO;
import Vista.ModuloTarjetas;
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

public class ModuloTarjetas_Eventos {
    private ModuloTarjetas moduloTarjetas;
    private TableRowSorter sorter;

    public ModuloTarjetas_Eventos(ModuloTarjetas moduloTarjetas) {
        this.moduloTarjetas = moduloTarjetas;
        sorter = new TableRowSorter<>(moduloTarjetas.tableTarjetas.getModel());
        moduloTarjetas.tableTarjetas.setRowSorter(sorter);

        this.moduloTarjetas.jTabbedPane1.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane TabbedPane = (JTabbedPane) e.getSource();
                        int tab = TabbedPane.getSelectedIndex();
                        if (tab == 1){
                            consultarTarjetas();
                        }
                    }
                }
        );

        this.moduloTarjetas.tConsulBuscarTarjeta.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        filtro();
                    }
                }
        );

        this.moduloTarjetas.bCrearTarjeta.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        crearTarjetas();
                    }
                }
        );

        this.moduloTarjetas.bEliminar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eliminarTarjeta();
                    }
                }
        );

        this.moduloTarjetas.bModifiBuscar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        modificarBusqueda();
                    }
                }
        );

        this.moduloTarjetas.bModifiCancelar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        limpiarCamposModificar();
                        enableModificar(false);
                    }
                }
        );
        this.moduloTarjetas.bModificar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        modificarTarjeta();
                    }
                }
        );
    }

    public void limpiarCamposCrear(){
        moduloTarjetas.tCrearSaldo.setText("0");
        moduloTarjetas.spinnerCrearNumTarj.setValue(1);
        moduloTarjetas.comboCrearEstado.setSelectedItem(0);
    }

    public void limpiarCamposModificar(){
        moduloTarjetas.tModifiTarjeta.setText("");
        moduloTarjetas.tModifiSaldo.setText("");
        moduloTarjetas.comboModifiEstado.setSelectedIndex(0);
    }

    public boolean verificarCamposCrear(){
        boolean exito = true;
        if (moduloTarjetas.tCrearSaldo.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloTarjetas, "El campo saldo es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else {
            try {
                Integer.parseInt(moduloTarjetas.tCrearSaldo.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(moduloTarjetas, "El campo saldo debe ser numerico.", "Error", JOptionPane.ERROR_MESSAGE);
                exito = false;
            }
        }
        return exito;
    }

    public boolean verificarCamposEliminar(){
        boolean exito = true;
        if (moduloTarjetas.tEliminarTarjeta.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloTarjetas, "El campo Tarjeta ID es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else{
            try{
                Long.parseLong(moduloTarjetas.tEliminarTarjeta.getText());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(moduloTarjetas, "El campo Tarjeta ID debe ser numerico.", "Error", JOptionPane.ERROR_MESSAGE);
                exito = false;
            }
        }
        return exito;
    }

    public boolean verificarCamposModifiBuscar(){
        boolean exito = true;
        if (moduloTarjetas.tModifiTarjeta.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloTarjetas, "El campo Tarjeta es ID obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else {
            try {
                Double.parseDouble(moduloTarjetas.tModifiTarjeta.getText());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(moduloTarjetas, "El campo Tarjeta ID debe ser numerico.", "Error", JOptionPane.ERROR_MESSAGE);
                exito = false;
            }
        }
        return exito;
    }

    public boolean verificarCamposModificar(){
        boolean exito = true;
        if (moduloTarjetas.tModifiSaldo.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloTarjetas, "El campo saldo es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else {
            try {
                Long.parseLong(moduloTarjetas.tModifiSaldo.getText());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(moduloTarjetas, "El campo saldo debe ser numerico.", "Error", JOptionPane.ERROR_MESSAGE);
                exito = false;
            }
        }
        return exito;
    }

    public void enableModificar(boolean enable){
        moduloTarjetas.tModifiTarjeta.setEnabled(!enable);
        moduloTarjetas.tModifiSaldo.setEnabled(enable);
        moduloTarjetas.comboModifiEstado.setEnabled(enable);
    }

    public void consultarTarjetas(){
        ArrayList<Tarjeta> tarjetas = new TarjetaDAO().consultarTarjetas();

        if (tarjetas != null){
            DefaultTableModel model = (DefaultTableModel) moduloTarjetas.tableTarjetas.getModel();
            model.setRowCount(tarjetas.size());
            for (int i = 0; i < tarjetas.size(); i++) {
                model.setValueAt(tarjetas.get(i).getId(),i,0);
                model.setValueAt(tarjetas.get(i).getSaldo(),i,1);
                model.setValueAt(tarjetas.get(i).getEstado(),i,2);
            }

        }
    }

    public void filtro() {
        sorter.setRowFilter(RowFilter.regexFilter(moduloTarjetas.tConsulBuscarTarjeta.getText(), 0));
    }

    public void crearTarjetas(){
        if (verificarCamposCrear()){
            int saldo = Integer.parseInt(moduloTarjetas.tCrearSaldo.getText());
            String estado = moduloTarjetas.comboCrearEstado.getSelectedItem().toString();
            ArrayList<Tarjeta> tarjetas = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(moduloTarjetas.spinnerCrearNumTarj.getValue().toString()); i++) {
                tarjetas.add(new Tarjeta(saldo, estado));
            }
            boolean exito = new TarjetaDAO().insertarTarjetas(tarjetas);

            if (exito){
                JOptionPane.showMessageDialog(moduloTarjetas, "Tarjetas creadas exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposCrear();
            }else {
                JOptionPane.showMessageDialog(moduloTarjetas, "Error al crear las tarjetas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminarTarjeta(){
        if (verificarCamposEliminar()){
            boolean exito;
            String tarjeta_id = moduloTarjetas.tEliminarTarjeta.getText();
            exito = new TarjetaDAO().eliminarTarjeta(tarjeta_id);

            if (exito){
                JOptionPane.showMessageDialog(moduloTarjetas, "Tarjeta eliminada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                moduloTarjetas.tEliminarTarjeta.setText("");
            }else {
                JOptionPane.showMessageDialog(moduloTarjetas, "Error al eliminar la tarjeta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void modificarBusqueda(){
        if (verificarCamposModifiBuscar()){
            String tarjeta_id = moduloTarjetas.tModifiTarjeta.getText();
            Tarjeta tarjeta = new TarjetaDAO().consultarTarjeta(tarjeta_id, false);
            if (tarjeta != null){
                moduloTarjetas.tModifiSaldo.setText(String.valueOf(tarjeta.getSaldo()));
                if(tarjeta.getEstado().equals("BLOQUEADA")){
                    moduloTarjetas.comboModifiEstado.setSelectedIndex(1);
                }
                enableModificar(true);
            }else {
                JOptionPane.showMessageDialog(moduloTarjetas, "La tarjeta que busca no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void modificarTarjeta(){
        if (verificarCamposModifiBuscar()){
            String id = moduloTarjetas.tModifiTarjeta.getText();
            double saldo = Double.parseDouble(moduloTarjetas.tModifiSaldo.getText());
            String estado = moduloTarjetas.comboModifiEstado.getSelectedItem().toString();
            Tarjeta tarjeta = new Tarjeta(id,saldo,estado);
            boolean exito = new TarjetaDAO().modificarTarjeta(tarjeta);
            if (exito){
                JOptionPane.showMessageDialog(moduloTarjetas, "Tarjeta modificada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposModificar();
                enableModificar(false);
            }else {
                JOptionPane.showMessageDialog(moduloTarjetas, "Error al modificar la tarjeta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
