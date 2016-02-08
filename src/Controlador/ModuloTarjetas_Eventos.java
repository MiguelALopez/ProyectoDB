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
                        limpiarCampos();
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
    }

    public void enableCamposVenta(boolean enable){
        moduloTarjetas.tCrearCedula.setEnabled(enable);
        moduloTarjetas.tCrearNombre.setEnabled(enable);
        moduloTarjetas.tCrearTelefono.setEnabled(enable);
        moduloTarjetas.tCrearDireccion.setEnabled(enable);
        moduloTarjetas.tCrearEmail.setEnabled(enable);
    }

    public void limpiarCampos(){
        moduloTarjetas.tCrearCedula.setText("");
        moduloTarjetas.tCrearNombre.setText("");
        moduloTarjetas.tCrearTelefono.setText("");
        moduloTarjetas.tCrearDireccion.setText("");
        moduloTarjetas.tCrearEmail.setText("");
    }

    public void consultarTarjetasVenta(){
        ArrayList<Tarjeta> tarjetas = new TarjetaDAO().consultarTarjetas();
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
}
