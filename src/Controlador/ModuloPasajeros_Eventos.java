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

package Controlador;

import Modelo.Pasajero;
import Modelo.PasajeroDAO;
import Modelo.TarjetaRuta;
import Modelo.TarjetaRutaDAO;
import Vista.ModuloPasajeros;
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
import javafx.util.Pair;

public class ModuloPasajeros_Eventos 
{
    private final ModuloPasajeros moduloPasajeros;
    private final TableRowSorter sorter;

    public ModuloPasajeros_Eventos(final ModuloPasajeros moduloPasajeros)
    {
        this.moduloPasajeros = moduloPasajeros;
        sorter = new TableRowSorter<>(moduloPasajeros.tablePasajeros.getModel());
        moduloPasajeros.tablePasajeros.setRowSorter(sorter);

        this.moduloPasajeros.bCrearPasajero.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    crearPasajero();
                }
            }
        );
        
        this.moduloPasajeros.tModifBuscarTarjeta.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    modifBuscarPasajero();
                }
            }
        );

        this.moduloPasajeros.bModifBuscar.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    modifBuscarPasajero();
                }
            }
        );

        this.moduloPasajeros.bModifCancelar.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    limpiarCamposModificar();
                    enableModificar(false);
                }
            }
        );

        this.moduloPasajeros.bModificarPasajero.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    modificarPasajero();
                }
            }
        );

        this.moduloPasajeros.jTabbedPane1.addChangeListener
        (
            new ChangeListener() 
            {
                @Override
                public void stateChanged(ChangeEvent e) 
                {
                    if (moduloPasajeros.jTabbedPane1.getSelectedIndex() == 2) 
                    {
                        consultarPasajeros();
                    }
                }
            }
        );

        this.moduloPasajeros.bConsulActualizar.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    consultarPasajeros();
                }
            }
        );

        this.moduloPasajeros.tConsulBuscarTarjeta.addKeyListener
        (
            new KeyAdapter() 
            {
                @Override
                public void keyReleased(KeyEvent e) 
                {
                    filtro();
                }
            }
        );
        
        this.moduloPasajeros.bDetalles.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    verDetalles();
                }
            }
        );
        
        this.moduloPasajeros.bCerrar.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    cerrarDetalles();
                }
            }
        );
        
        this.moduloPasajeros.tHistoryBuscarTarjeta.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    consultarHistorial();
                }
            }
        );

        this.moduloPasajeros.bHistoryBuscar.addActionListener
        (
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    consultarHistorial();
                }
            }
        );
    }

    public void limpiarCamposCrear()
    {
        moduloPasajeros.tCrearCedula.setText("");
        moduloPasajeros.tCrearNombre.setText("");
        moduloPasajeros.tCrearTelefono.setText("");
        moduloPasajeros.tCrearDireccion.setText("");
        moduloPasajeros.tCrearEmail.setText("");
        moduloPasajeros.tCrearTarjeta.setText("");
    }

    public void limpiarCamposModificar()
    {
        moduloPasajeros.tModifBuscarTarjeta.setText("");
        moduloPasajeros.tModifCedula.setText("");
        moduloPasajeros.tModifNombre.setText("");
        moduloPasajeros.tModifTelefono.setText("");
        moduloPasajeros.tModifDireccion.setText("");
        moduloPasajeros.tModifEmail.setText("");
        moduloPasajeros.tModifTarjeta.setText("");
        moduloPasajeros.cbModifEstado.setSelectedIndex(0);
    }

    public boolean verificarCamposCrear()
    {
        boolean exito = true;

        if (moduloPasajeros.tCrearCedula.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo cedula es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tCrearNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tCrearTelefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo telefono es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tCrearDireccion.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo direccion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tCrearEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo email es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tCrearTarjeta.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo ID tarjeta es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        return exito;
    }

    public boolean verificarCarmposModif()
    {
        boolean exito = true;

        if (moduloPasajeros.tModifCedula.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo cedula es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tModifNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tModifTelefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo telefono es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tModifDireccion.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo direccion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tModifEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo email es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }else if (moduloPasajeros.tModifTarjeta.getText().isEmpty()){
            JOptionPane.showMessageDialog(moduloPasajeros, "El campo ID tarjeta es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }

        return exito;
    }

    public void crearPasajero()
    {
        if (verificarCamposCrear())
        {
            String id = moduloPasajeros.tCrearCedula.getText();
            String nombre = moduloPasajeros.tCrearNombre.getText();
            String telefono = moduloPasajeros.tCrearTelefono.getText();
            String direccion = moduloPasajeros.tCrearDireccion.getText();
            String email = moduloPasajeros.tCrearEmail.getText();
            String tarjeta = moduloPasajeros.tCrearTarjeta.getText();
            boolean estado = true;

            int op = JOptionPane.showConfirmDialog(moduloPasajeros, "Desea crear el usuario " + id + "?", "", JOptionPane.YES_NO_OPTION );
            if (op == JOptionPane.YES_OPTION)
            {
                Pasajero pasajero = new Pasajero(id,nombre,telefono,direccion,email,tarjeta,estado);

                boolean exito = new PasajeroDAO().insertarPasajero(pasajero);

                if (exito){
                    JOptionPane.showMessageDialog(moduloPasajeros, "Pasajero creado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCrear();
                }else {
                    JOptionPane.showMessageDialog(moduloPasajeros, "Error al crear el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void enableModificar(boolean enable)
    {
        moduloPasajeros.bModifCancelar.setEnabled(enable);
        //moduloPasajeros.tModifCedula.setEditable(enable);
        moduloPasajeros.tModifNombre.setEditable(enable);
        moduloPasajeros.tModifTelefono.setEditable(enable);
        moduloPasajeros.tModifDireccion.setEditable(enable);
        moduloPasajeros.tModifEmail.setEditable(enable);
        //moduloPasajeros.tModifTarjeta.setEditable(enable);
        moduloPasajeros.cbModifEstado.setEnabled(enable);
        moduloPasajeros.tModifBuscarTarjeta.setEnabled(!enable);
    }

    public void modifBuscarPasajero()
    {
        if (!moduloPasajeros.tModifBuscarTarjeta.getText().isEmpty())
        {
            String tarjeta_id = moduloPasajeros.tModifBuscarTarjeta.getText();
            Pasajero pasajero = new PasajeroDAO().consultarPasajero(tarjeta_id);
            
            if (pasajero != null)
            {
                enableModificar(true);

                moduloPasajeros.tModifCedula.setText(pasajero.getId());
                moduloPasajeros.tModifNombre.setText(pasajero.getNombre());
                moduloPasajeros.tModifTelefono.setText(pasajero.getTelefono());
                moduloPasajeros.tModifDireccion.setText(pasajero.getDireccion());
                moduloPasajeros.tModifEmail.setText(pasajero.getEmail());
                moduloPasajeros.tModifTarjeta.setText(pasajero.getTarjeta());
                moduloPasajeros.cbModifEstado.setSelectedItem(pasajero.getEstado());

            }
            else 
            {
                JOptionPane.showMessageDialog(moduloPasajeros, "No se encontro el pasajero.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else 
        {
            JOptionPane.showMessageDialog(moduloPasajeros, "Es necesaria la tarjeta asociada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarPasajero()
    {
        if (verificarCarmposModif())
        {
            String id = moduloPasajeros.tModifCedula.getText();
            String nombre = moduloPasajeros.tModifNombre.getText();
            String telefono = moduloPasajeros.tModifTelefono.getText();
            String direccion = moduloPasajeros.tModifDireccion.getText();
            String email = moduloPasajeros.tModifEmail.getText();
            String tarjeta = moduloPasajeros.tModifTarjeta.getText();
            boolean estado = true;
            
            if (this.moduloPasajeros.cbModifEstado.getSelectedItem().equals("Activo"))
            {
                estado = true;
            }
            else if (this.moduloPasajeros.cbModifEstado.getSelectedItem().equals("Inactivo"))
            {
                estado = false;
            }
            
            int op = JOptionPane.showConfirmDialog(moduloPasajeros, "Desea modificar el usuario " + id + "?", "", JOptionPane.YES_NO_OPTION );
            if (op == JOptionPane.YES_OPTION)
            {
                Pasajero pasajero = new Pasajero(id,nombre,telefono,direccion,email,tarjeta,estado);

                boolean exito = new PasajeroDAO().modificarPasajero(pasajero);

                if (exito){
                    JOptionPane.showMessageDialog(moduloPasajeros, "El usuario fue modificado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposModificar();
                    enableModificar(false);
                }else {
                    JOptionPane.showMessageDialog(moduloPasajeros, "Error al modificar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void consultarPasajeros()
    {
        ArrayList<Pasajero> lista = new PasajeroDAO().consultarPasajeros();

        if (lista!= null)
        {
            DefaultTableModel model = (DefaultTableModel) moduloPasajeros.tablePasajeros.getModel();
            model.setRowCount(lista.size());

            for (int i = 0; i < lista.size(); i++) 
            {
                model.setValueAt(lista.get(i).getTarjeta(),i,0);
                model.setValueAt(lista.get(i).getId(),i,1);
                model.setValueAt(lista.get(i).getNombre(),i,2);
                model.setValueAt(lista.get(i).getEstado(),i,3);
            }
        }
    }
    
    public void filtro() 
    {
        sorter.setRowFilter(RowFilter.regexFilter(moduloPasajeros.tConsulBuscarTarjeta.getText(), 0));
    }
    
    public void verDetalles()
    {
        int row = moduloPasajeros.tablePasajeros.getSelectedRow();
                        
        if (row != -1)
        {
            String id = (String) moduloPasajeros.tablePasajeros.getValueAt(row, 0);
            
            Pasajero p = new PasajeroDAO().consultarPasajero(id);

            if (p != null)
            {
                this.moduloPasajeros.tfVerId.setText(p.getId());
                this.moduloPasajeros.tfVerNombre.setText(p.getNombre());
                this.moduloPasajeros.tfVerTelefono.setText(p.getTelefono());
                this.moduloPasajeros.tfVerDireccion.setText(p.getDireccion());
                this.moduloPasajeros.tfVerEmail.setText(p.getEmail());
                this.moduloPasajeros.tfVerTarjeta.setText(p.getTarjeta());
                this.moduloPasajeros.tfVerEstado.setText(p.getEstado());
		
		this.moduloPasajeros.fDetalles.setLocationRelativeTo(moduloPasajeros);
		this.moduloPasajeros.fDetalles.setVisible(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloPasajeros, "No ha seleccionado ningun Pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cerrarDetalles()
    {
        this.moduloPasajeros.fDetalles.setVisible(false);
    }

    public void consultarHistorial()
    {
        String id = moduloPasajeros.tHistoryBuscarTarjeta.getText();
        
        if (!id.isEmpty())
        {
            ArrayList<TarjetaRuta> list = new TarjetaRutaDAO().consultarHistorialPasajero(id);
            ArrayList<Pair> frec = new TarjetaRutaDAO().consultarFrecuencias(id);
            
            if (list != null)
            {
                DefaultTableModel model = (DefaultTableModel) moduloPasajeros.tableHistory.getModel();
                model.setRowCount(list.size());

                for (int i = 0; i < list.size(); i++) 
                {
                    model.setValueAt(list.get(i).getRuta_nombre(),i,0);
                    model.setValueAt(list.get(i).getFecha(),i,1);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloPasajeros, "Tarjeta no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            if (frec != null)
            {
                DefaultTableModel model = (DefaultTableModel) moduloPasajeros.tFrecuencia.getModel();
                model.setRowCount(frec.size());

                for (int i = 0; i < frec.size(); i++) 
                {
                    model.setValueAt(frec.get(i).getKey(), i, 0);
                    model.setValueAt(frec.get(i).getValue(), i, 1);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloPasajeros, "Tarjeta no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloPasajeros, "Debe introducir un id de tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
