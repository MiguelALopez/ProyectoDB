/**
 * ********************************************
 * Autor: Miguel Angel Lopez Fernandez
 * Correo: miguel.angel.lopez@correounivalle.edu.co
 * Código: 1326691
 * Fecha: 07-feb-2016
 * Nombre del Archivo: ModuloTarjetas_Eventos.java
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
        consultarTarjetasVenta();

        this.moduloTarjetas.radioGenerica.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableCamposVenta(false);
                        limpiarCamposVentaCrear();
                    }
                }
        );

        this.moduloTarjetas.radioPerson.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableCamposVenta(true);
                    }
                }
        );
        this.moduloTarjetas.jTabbedPane1.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane TabbedPane = (JTabbedPane) e.getSource();
                        int tab = TabbedPane.getSelectedIndex();
                        if (tab == 0) {
                            consultarTarjetasVenta();
                        }else if (tab == 3){
                            consultarTarjetas();
                        }
                    }
                }
        );

        this.moduloTarjetas.bVentaVender.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

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
    }

    public void enableCamposVenta(boolean enable){
        moduloTarjetas.tCrearCedula.setEnabled(enable);
        moduloTarjetas.tCrearNombre.setEnabled(enable);
        moduloTarjetas.tCrearTelefono.setEnabled(enable);
        moduloTarjetas.tCrearDireccion.setEnabled(enable);
        moduloTarjetas.tCrearEmail.setEnabled(enable);
    }

    public void limpiarCamposVentaCrear(){
        moduloTarjetas.tCrearCedula.setText("");
        moduloTarjetas.tCrearNombre.setText("");
        moduloTarjetas.tCrearTelefono.setText("");
        moduloTarjetas.tCrearDireccion.setText("");
        moduloTarjetas.tCrearEmail.setText("");
    }

    public void limpiarCamposCrear(){
        moduloTarjetas.tCrearSaldo.setText("0");
        moduloTarjetas.spinnerCrearNumTarj.setValue(1);
        moduloTarjetas.comboCrearEstado.setSelectedItem(0);
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

    public void consultarTarjetasVenta(){
        ArrayList<Tarjeta> tarjetas = new TarjetaDAO().consultarTarjetasNoVendidas();
        moduloTarjetas.comboVentaIdTarjeta.removeAllItems();
        if (tarjetas != null){
            for (int i = 0; i < tarjetas.size(); i++) {
                moduloTarjetas.comboVentaIdTarjeta.addItem(tarjetas.get(i).getId());
            }
        }
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
}
