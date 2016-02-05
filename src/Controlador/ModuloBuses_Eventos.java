/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bus;
import Modelo.BusDAO;
import Modelo.Ruta;
import Modelo.RutaDAO;
import Vista.ModuloBuses;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class ModuloBuses_Eventos
{
    private final ModuloBuses moduloBuses;

    public ModuloBuses_Eventos(final ModuloBuses moduloBuses) 
    {
        this.moduloBuses = moduloBuses;
        
        this.moduloBuses.bCrearBus.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    crearBus();
                }                
            }
        );
        
        this.moduloBuses.bSeleccionCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarRutasCrear();
                }
            }
        );
        
        this.moduloBuses.bSeleccionarCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    seleccionarRutasCrear();
                }
            }
        );
        
        this.moduloBuses.bCancelarCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cerrarSeleccionCrear();
                }
            }
        );
        
        this.moduloBuses.bModificarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarBusModificar();
                }                
            }
        );
        
        this.moduloBuses.bModificarBus.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    modificarBus();
                }                
            }
        );
        
        this.moduloBuses.bSeleccionModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarRutasModificar();
                }
            }
        );
        
        this.moduloBuses.bSeleccionarModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    seleccionarRutasModificar();
                }
            }
        );
        
        this.moduloBuses.bCancelarModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cerrarSeleccionModificar();
                }
            }
        );
        
        this.moduloBuses.bEliminarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarBusEliminar();
                }                
            }
        );
        
        this.moduloBuses.bEliminarBus.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    eliminarBus();
                }     
            }
        );
        
        this.moduloBuses.bActualizar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarBuses();
                }    
            }
        );
        
        this.moduloBuses.addWindowListener(
            new WindowListener()
            {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) 
                {
                    cerrarVentana();
                }

                @Override
                public void windowClosed(WindowEvent e) {
                }

                @Override
                public void windowIconified(WindowEvent e) {
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                }

                @Override
                public void windowActivated(WindowEvent e) {
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                }
            }
        );
    }
    
    public void crearBus()
    {
        if (verificarCamposCrear())
        {
            String serial = this.moduloBuses.tfCrearSerial.getText();
            String tipo = (String) this.moduloBuses.cbCrearTipo.getSelectedItem();
            int capacidad = Integer.parseInt(this.moduloBuses.tfCrearCapacidad.getText());
            String ruta = this.moduloBuses.tfCrearRuta.getText();
            boolean estado = true;
            
            int op = JOptionPane.showConfirmDialog(moduloBuses, "Desea crear el Bus " + serial + "?", "", JOptionPane.YES_NO_OPTION);
        
            if (op == JOptionPane.YES_OPTION)
            {
                boolean exito = new BusDAO().insertarBus(new Bus(serial, tipo, capacidad, ruta, estado));

                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloBuses, "Bus creado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCrear();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloBuses, "Error al crear.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposCrear()
    {        
        if (this.moduloBuses.tfCrearSerial.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloBuses, "El campo Serial es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloBuses.tfCrearCapacidad.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloBuses, "El campo Capacidad es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (Integer.parseInt(this.moduloBuses.tfCrearCapacidad.getText()) <= 0)
        {
            JOptionPane.showMessageDialog(moduloBuses, "Capacidad invalida.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!this.moduloBuses.tfCrearRuta.getText().isEmpty())
        {
            Ruta ruta = new RutaDAO().consultarRuta(this.moduloBuses.tfCrearRuta.getText());
            
            if (ruta != null)
            {
                if (!ruta.isEstado())
                {
                    JOptionPane.showMessageDialog(moduloBuses, "La Ruta no esta Activa.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloBuses, "Ruta invalida.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    public void limpiarCamposCrear()
    {
        this.moduloBuses.tfCrearSerial.setText("");
        this.moduloBuses.cbCrearTipo.setSelectedIndex(0);
        this.moduloBuses.tfCrearCapacidad.setText("");
        this.moduloBuses.tfCrearRuta.setText("");
    }
    
    public void consultarRutasCrear()
    {
        ArrayList<Ruta> listaRutas = new RutaDAO().consultarRutas();
        
        if(listaRutas != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloBuses.tRutasCrear.getModel();
            model.setRowCount(listaRutas.size());
            
            for (int i = 0; i < listaRutas.size(); i++) 
            {
                this.moduloBuses.tRutasCrear.setValueAt(listaRutas.get(i).getNombre(), i, 0);
                this.moduloBuses.tRutasCrear.setValueAt(listaRutas.get(i).getDescripcion(), i, 1);
            }
            
            this.moduloBuses.fSelRutaCrear.setLocationRelativeTo(moduloBuses);
            this.moduloBuses.fSelRutaCrear.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloBuses, "Error al consultar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void seleccionarRutasCrear()
    {
        int row = this.moduloBuses.tRutasCrear.getSelectedRow();
        
        if(row != -1)
        {
            this.moduloBuses.tfCrearRuta.setText((String) this.moduloBuses.tRutasCrear.getValueAt(row, 0));
            
            DefaultTableModel model = (DefaultTableModel) this.moduloBuses.tRutasCrear.getModel();
            model.setRowCount(0);
            
            this.moduloBuses.fSelRutaCrear.setVisible(false);
        }
	else
	{
	    JOptionPane.showMessageDialog(this.moduloBuses.fSelRutaCrear, "Seleccione una ruta.", "", JOptionPane.ERROR_MESSAGE);
	}
    }
    
    public void cerrarSeleccionCrear()
    {
        this.moduloBuses.fSelRutaCrear.setVisible(false);
    }
    
    public void buscarBusModificar()
    {
        String serial = this.moduloBuses.tfModificarBuscar.getText();
        
        Bus bus = new BusDAO().consultarBus(serial);
        
        if (bus != null)
        {
            this.moduloBuses.tfModificarSerial.setText(bus.getSerial());
            this.moduloBuses.cbModificarTipo.setSelectedItem(bus.getTipo());
            this.moduloBuses.tfModificarCapacidad.setText(String.valueOf(bus.getCapacidad()));
            this.moduloBuses.tfModificarRuta.setText(bus.getRuta());
            this.moduloBuses.cbModificarEstado.setSelectedItem(bus.getEstado());
            
            habilitarCamposModificar(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloBuses, "Bus inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void habilitarCamposModificar(boolean b)
    {        
        //this.moduloBuses.tfModificarSerial.setEditable(b);
        this.moduloBuses.cbModificarTipo.setEnabled(b);
        this.moduloBuses.tfModificarCapacidad.setEditable(b);
        this.moduloBuses.tfModificarRuta.setEditable(b);
        this.moduloBuses.bSeleccionModificar.setEnabled(b);
        this.moduloBuses.cbModificarEstado.setEnabled(b);
    }
    
    public void modificarBus()
    {
        if (verificarCamposModificar())
        {
            String serial = this.moduloBuses.tfModificarSerial.getText();
            String tipo = (String) this.moduloBuses.cbModificarTipo.getSelectedItem();
            int capacidad = Integer.parseInt(this.moduloBuses.tfModificarCapacidad.getText());
            String ruta = this.moduloBuses.tfModificarRuta.getText();
            boolean estado = true;
            
            if (this.moduloBuses.cbModificarEstado.getSelectedItem().equals("Activo"))
            {
                estado = true;
            }
            else if (this.moduloBuses.cbModificarEstado.getSelectedItem().equals("Inactivo"))
            {
                estado = false;
            }
                
            int op = JOptionPane.showConfirmDialog(moduloBuses, "Desea modificar el Bus " + serial + "?", "", JOptionPane.YES_NO_OPTION);
        
            if (op == JOptionPane.YES_OPTION)
            {
                boolean exito = new BusDAO().modificarBus(new Bus(serial, tipo, capacidad, ruta, estado));

                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloBuses, "Bus modificado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposModificar();
                    habilitarCamposModificar(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloBuses, "Error al modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposModificar()
    {        
        if (this.moduloBuses.tfModificarSerial.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloBuses, "El campo Serial es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloBuses.tfModificarCapacidad.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloBuses, "El campo Capacidad es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (Integer.parseInt(this.moduloBuses.tfModificarCapacidad.getText()) <= 0)
        {
            JOptionPane.showMessageDialog(moduloBuses, "Capacidad invalida.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!this.moduloBuses.tfModificarRuta.getText().isEmpty())
        {
            Ruta ruta = new RutaDAO().consultarRuta(this.moduloBuses.tfModificarRuta.getText());
            
            if (ruta != null)
            {
                if (!ruta.isEstado())
                {
                    JOptionPane.showMessageDialog(moduloBuses, "La Ruta no esta Activa.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloBuses, "Ruta invalida.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    public void limpiarCamposModificar()
    {
        this.moduloBuses.tfModificarBuscar.setText("");
        
        this.moduloBuses.tfModificarSerial.setText("");
        this.moduloBuses.cbModificarTipo.setSelectedIndex(0);
        this.moduloBuses.tfModificarCapacidad.setText("");
        this.moduloBuses.tfModificarRuta.setText("");
        this.moduloBuses.cbModificarEstado.setSelectedIndex(0);
    }
    
    public void consultarRutasModificar()
    {
        ArrayList<Ruta> listaRutas = new RutaDAO().consultarRutas();
        
        if(listaRutas != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloBuses.tRutasModificar.getModel();
            model.setRowCount(listaRutas.size());
            
            for (int i = 0; i < listaRutas.size(); i++) 
            {
                this.moduloBuses.tRutasModificar.setValueAt(listaRutas.get(i).getNombre(), i, 0);
                this.moduloBuses.tRutasModificar.setValueAt(listaRutas.get(i).getDescripcion(), i, 1);
            }
            
            this.moduloBuses.fSelRutaModificar.setLocationRelativeTo(moduloBuses);
            this.moduloBuses.fSelRutaModificar.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloBuses, "Error al consultar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void seleccionarRutasModificar()
    {
        int row = this.moduloBuses.tRutasModificar.getSelectedRow();
        
        if(row != -1)
        {
            this.moduloBuses.tfModificarRuta.setText((String) this.moduloBuses.tRutasModificar.getValueAt(row, 0));
            
            DefaultTableModel model = (DefaultTableModel) this.moduloBuses.tRutasModificar.getModel();
            model.setRowCount(0);
            
            this.moduloBuses.fSelRutaModificar.setVisible(false);
        }
	else
	{
	    JOptionPane.showMessageDialog(this.moduloBuses.fSelRutaModificar, "Seleccione una ruta.", "", JOptionPane.ERROR_MESSAGE);
	}
    }
    
    public void cerrarSeleccionModificar()
    {
        this.moduloBuses.fSelRutaModificar.setVisible(false);
    }
    
    public void buscarBusEliminar()
    {
        String serial = this.moduloBuses.tfEliminarBuscar.getText();
        
        if (!serial.isEmpty())
        {
            Bus bus = new BusDAO().consultarBus(serial);
        
            if (bus != null)
            {
                if (bus.isEstado())
                {
                    this.moduloBuses.tfEliminarSerial.setText(bus.getSerial());
                    this.moduloBuses.tfEliminarTipo.setText(bus.getTipo());
                    this.moduloBuses.tfEliminarCapacidad.setText(String.valueOf(bus.getCapacidad()));
                    this.moduloBuses.tfEliminarRuta.setText(bus.getRuta());
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloBuses, "El bus ya esta eliminado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloBuses, "Bus inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloBuses, "Debe introducir un serial para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarBus()
    {
        String serial = this.moduloBuses.tfEliminarSerial.getText();
        
        if (!serial.isEmpty())
        {
            int op = JOptionPane.showConfirmDialog(moduloBuses, "Desea eliminar el Bus " + serial + "?", "", JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION)
            {
                boolean exito = new BusDAO().eliminarBus(serial);

                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloBuses, "Bus eliminado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposEliminar();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloBuses, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloBuses, "No ha buscado un bus.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiarCamposEliminar()
    {
        this.moduloBuses.tfEliminarBuscar.setText("");
        
        this.moduloBuses.tfEliminarSerial.setText("");
        this.moduloBuses.tfEliminarTipo.setText("");
        this.moduloBuses.tfEliminarCapacidad.setText("");
        this.moduloBuses.tfEliminarRuta.setText("");
    }
    
    public void consultarBuses()
    {
        ArrayList<Bus> lista = new BusDAO().consultarBuses();
        
        if (lista != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloBuses.tBuses.getModel();
            model.setRowCount(lista.size());
            
            for (int i = 0; i < lista.size(); i++) 
            {
                model.setValueAt(lista.get(i).getSerial(), i, 0);
                model.setValueAt(lista.get(i).getTipo(), i, 1);
                model.setValueAt(lista.get(i).getCapacidad(), i, 2);
                model.setValueAt(lista.get(i).getRuta(), i, 3);
                model.setValueAt(lista.get(i).getEstado(), i, 4);
            }
        }
    }
    
    public void cerrarVentana()
    {
        this.moduloBuses.fSelRutaCrear.setVisible(false);
        this.moduloBuses.fSelRutaModificar.setVisible(false);
        this.moduloBuses.setVisible(false);
    }
}
