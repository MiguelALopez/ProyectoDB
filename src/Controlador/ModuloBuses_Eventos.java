/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bus;
import Modelo.BusDAO;
import Modelo.RutaDAO;
import Vista.ModuloBuses;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camilo Ruiz Casanova
 */
class ModuloBuses_Eventos 
{
    private ModuloBuses moduloBuses;

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
    }
    
    public void crearBus()
    {
        if (verificarCamposCrear())
        {
            String serial = this.moduloBuses.tfCrearSerial.getText();
            String tipo = (String) this.moduloBuses.cbCrearTipo.getSelectedItem();
            int capacidad = Integer.parseInt(this.moduloBuses.tfCrearCapacidad.getText());
            String ruta = this.moduloBuses.tfCrearRuta.getText();
            
            int op = JOptionPane.showConfirmDialog(moduloBuses, "Desea crear el Bus " + serial + ".", "", JOptionPane.YES_NO_OPTION);
        
            if (op == JOptionPane.YES_OPTION)
            {
                boolean exito = new BusDAO().insertarBus(new Bus(serial, tipo, capacidad, ruta));

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
            if (new RutaDAO().consultarRuta(this.moduloBuses.tfCrearRuta.getText()) == null)
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
    }
    
    public void modificarBus()
    {
        if (verificarCamposModificar())
        {
            String serial = this.moduloBuses.tfModificarSerial.getText();
            String tipo = (String) this.moduloBuses.cbModificarTipo.getSelectedItem();
            int capacidad = Integer.parseInt(this.moduloBuses.tfModificarCapacidad.getText());
            String ruta = this.moduloBuses.tfModificarRuta.getText();
                
            int op = JOptionPane.showConfirmDialog(moduloBuses, "Desea modificar el Bus " + serial + ".", "", JOptionPane.YES_NO_OPTION);
        
            if (op == JOptionPane.YES_OPTION)
            {
                boolean exito = new BusDAO().modificarBus(new Bus(serial, tipo, capacidad, ruta));

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
            if (new RutaDAO().consultarRuta(this.moduloBuses.tfModificarRuta.getText()) == null)
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
    }
    
    public void buscarBusEliminar()
    {
        String serial = this.moduloBuses.tfEliminarBuscar.getText();
        
        Bus bus = new BusDAO().consultarBus(serial);
        
        if (bus != null)
        {
            this.moduloBuses.tfEliminarSerial.setText(bus.getSerial());
            this.moduloBuses.tfEliminarTipo.setText(bus.getTipo());
            this.moduloBuses.tfEliminarCapacidad.setText(String.valueOf(bus.getCapacidad()));
            this.moduloBuses.tfEliminarRuta.setText(bus.getRuta());
        }
        else
        {
            JOptionPane.showMessageDialog(moduloBuses, "Bus inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarBus()
    {
        String serial = this.moduloBuses.tfEliminarSerial.getText();
        
        int op = JOptionPane.showConfirmDialog(moduloBuses, "Desea eliminar el Bus " + serial + ".", "", JOptionPane.YES_NO_OPTION);
        
        if (op == JOptionPane.YES_OPTION)
        {
            boolean exito = new BusDAO().eliminarBus(serial);

            if (exito)
            {
                JOptionPane.showMessageDialog(moduloBuses, "Bus eliminado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(moduloBuses, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
            }
        }
    }
}
