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

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Estacion;
import Modelo.EstacionDAO;
import Vista.ModuloEstaciones;
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
public class ModuloEstaciones_Eventos 
{
    private final ModuloEstaciones moduloEstaciones;
    
    public ModuloEstaciones_Eventos(final ModuloEstaciones moduloEstaciones)
    {
        this.moduloEstaciones = moduloEstaciones;
        
        this.moduloEstaciones.bCrearEstacion.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    crearEstacion();
                }                
            }
        );
        
        this.moduloEstaciones.bSeleccionCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarDirCrear();
                }
            }
        );
        
        this.moduloEstaciones.bSeleccionarCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    seleccionarDirCrear();
                }
            }
        );
        
        this.moduloEstaciones.bCancelarCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cerrarSeleccionCrear();
                }
            }
        );
        
        this.moduloEstaciones.bModificarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarEstacionModificar();
                }                
            }
        );
        
        this.moduloEstaciones.bModificarEstacion.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    modificarEstacion();
                }                
            }
        );
        
        this.moduloEstaciones.bSeleccionModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarDirModificar();
                }
            }
        );
        
        this.moduloEstaciones.bSeleccionarModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    seleccionarDirModificar();
                }
            }
        );
        
        this.moduloEstaciones.bCancelarModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cerrarSeleccionModificar();
                }
            }
        );
        
        this.moduloEstaciones.bEliminarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarEstacionEliminar();
                }                
            }
        );
        
        this.moduloEstaciones.bEliminarEstacion.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    eliminarEstacion();
                }                
            }
        );
        
        this.moduloEstaciones.bActualizar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarEstaciones();
                }                
            }
        );
        
        this.moduloEstaciones.addWindowListener(
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
    
    public void crearEstacion()
    {
        if (verificarCamposCrear())
        {
            String nombre = this.moduloEstaciones.tfCrearNombre.getText();
            String ubicacion = this.moduloEstaciones.tfCrearUbicacion.getText();
            String director = this.moduloEstaciones.tfCrearDirector.getText();
            boolean estado = true;
            
            int op = JOptionPane.showConfirmDialog(moduloEstaciones, "Desea crear la Estacion " + nombre + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Estacion e = new Estacion(nombre, ubicacion, director, estado);
                
                boolean exito = new EstacionDAO().insertarEstacion(e);
                
                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "Estacion creada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCrear();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "Error al crear.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposCrear()
    {
        if (this.moduloEstaciones.tfCrearNombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "El campo Nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEstaciones.tfCrearUbicacion.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "El campo Ubicacion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!this.moduloEstaciones.tfCrearDirector.getText().isEmpty())
        {
            Empleado e = new EmpleadoDAO().consultarEmpleado(this.moduloEstaciones.tfCrearDirector.getText());
            
            if (e != null)
            {
                if (!e.getCargo().equals("Director"))
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "El Empleado no es Director.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEstaciones, "Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }            
        }
        
        return true;
    }
    
    public void limpiarCamposCrear()
    {
        this.moduloEstaciones.tfCrearNombre.setText("");
        this.moduloEstaciones.tfCrearUbicacion.setText("");
        this.moduloEstaciones.tfCrearDirector.setText("");
    }
    
    public void consultarDirCrear()
    {
        ArrayList<Empleado> listaDirectores = new EmpleadoDAO().consultarDirectores();
        
        if(listaDirectores != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloEstaciones.tDirCrear.getModel();
            model.setRowCount(listaDirectores.size());
            
            for (int i = 0; i < listaDirectores.size(); i++) 
            {
                this.moduloEstaciones.tDirCrear.setValueAt(listaDirectores.get(i).getId(), i, 0);
                this.moduloEstaciones.tDirCrear.setValueAt(listaDirectores.get(i).getNombre(), i, 1);
                this.moduloEstaciones.tDirCrear.setValueAt(listaDirectores.get(i).getCargo(), i, 2);
            }
            
            this.moduloEstaciones.fSelDirCrear.setLocationRelativeTo(moduloEstaciones);
            this.moduloEstaciones.fSelDirCrear.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "Error al consultar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void seleccionarDirCrear()
    {
        int row = this.moduloEstaciones.tDirCrear.getSelectedRow();
        
        if(row != -1)
        {
            this.moduloEstaciones.tfCrearDirector.setText((String) this.moduloEstaciones.tDirCrear.getValueAt(row, 0));
            
            DefaultTableModel model = (DefaultTableModel) this.moduloEstaciones.tDirCrear.getModel();
            model.setRowCount(0);
            
            this.moduloEstaciones.fSelDirCrear.setVisible(false);
        }
	else
	{
	    JOptionPane.showMessageDialog(this.moduloEstaciones.fSelDirCrear, "Seleccione un director.", "", JOptionPane.ERROR_MESSAGE);
	}
    }
    
    public void cerrarSeleccionCrear()
    {
        this.moduloEstaciones.fSelDirCrear.setVisible(false);
    }
    
    public void buscarEstacionModificar()
    {
        String nombre = this.moduloEstaciones.tfModificarBuscar.getText();
        
        Estacion e = new EstacionDAO().consultarEstacion(nombre);
        
        if (e != null)
        {
            this.moduloEstaciones.tfModificarNombre.setText(e.getNombre());
            this.moduloEstaciones.tfModificarUbicacion.setText(e.getUbicacion());
            this.moduloEstaciones.tfModificarDirector.setText(e.getDirector());
            this.moduloEstaciones.cbModificarEstado.setSelectedItem(e.getEstado());
            
            habilitarCamposModificar(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "Estacion inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void habilitarCamposModificar(boolean b)
    {
        //this.moduloEstaciones.tfModificarNombre.setEditable(b);
        this.moduloEstaciones.tfModificarUbicacion.setEditable(b);
        this.moduloEstaciones.tfModificarDirector.setEditable(b);
        this.moduloEstaciones.bSeleccionModificar.setEnabled(b);
        this.moduloEstaciones.cbModificarEstado.setEnabled(b);
    }
    
    public void modificarEstacion()
    {
        if (verificarCamposModificar())
        {
            String nombre = this.moduloEstaciones.tfModificarNombre.getText();
            String ubicacion = this.moduloEstaciones.tfModificarUbicacion.getText();
            String director = this.moduloEstaciones.tfModificarDirector.getText();
            boolean estado = true;
            
            if (this.moduloEstaciones.cbModificarEstado.getSelectedItem().equals("Activo"))
            {
                estado = true;
            }
            else if(this.moduloEstaciones.cbModificarEstado.getSelectedItem().equals("Inactivo"))
            {
                estado = false;
            }
            
            int op = JOptionPane.showConfirmDialog(moduloEstaciones, "Desea modificar la Estacion " + nombre + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Estacion e = new Estacion(nombre, ubicacion, director, estado);
                
                boolean exito = new EstacionDAO().modificarEstacion(e);
                
                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "Estacion modificada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposModificar();
                    habilitarCamposModificar(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "Error al modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposModificar()
    {
        if (this.moduloEstaciones.tfModificarNombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "El campo Nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEstaciones.tfModificarUbicacion.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "El campo Ubicacion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!this.moduloEstaciones.tfModificarDirector.getText().isEmpty())
        {
            Empleado e = new EmpleadoDAO().consultarEmpleado(this.moduloEstaciones.tfModificarDirector.getText());
            
            if (e != null)
            {
                if (!e.getCargo().equals("Director"))
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "El Empleado no es Director.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEstaciones, "Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }            
        }
        
        return true;
    }
    
    public void limpiarCamposModificar()
    {
        this.moduloEstaciones.tfModificarBuscar.setText("");
        
        this.moduloEstaciones.tfModificarNombre.setText("");
        this.moduloEstaciones.tfModificarUbicacion.setText("");
        this.moduloEstaciones.tfModificarDirector.setText("");
        this.moduloEstaciones.cbModificarEstado.setSelectedIndex(0);
    }
    
    public void consultarDirModificar()
    {
        ArrayList<Empleado> listaDirectores = new EmpleadoDAO().consultarDirectores();
        
        if(listaDirectores != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloEstaciones.tDirModificar.getModel();
            model.setRowCount(listaDirectores.size());
            
            for (int i = 0; i < listaDirectores.size(); i++) 
            {
                this.moduloEstaciones.tDirModificar.setValueAt(listaDirectores.get(i).getId(), i, 0);
                this.moduloEstaciones.tDirModificar.setValueAt(listaDirectores.get(i).getNombre(), i, 1);
                this.moduloEstaciones.tDirModificar.setValueAt(listaDirectores.get(i).getCargo(), i, 2);
            }
            
            this.moduloEstaciones.fSelDirModificar.setLocationRelativeTo(moduloEstaciones);
            this.moduloEstaciones.fSelDirModificar.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "Error al consultar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void seleccionarDirModificar()
    {
        int row = this.moduloEstaciones.tDirModificar.getSelectedRow();
        
        if(row != -1)
        {
            this.moduloEstaciones.tfModificarDirector.setText((String) this.moduloEstaciones.tDirModificar.getValueAt(row, 0));
            
            DefaultTableModel model = (DefaultTableModel) this.moduloEstaciones.tDirModificar.getModel();
            model.setRowCount(0);
            
            this.moduloEstaciones.fSelDirModificar.setVisible(false);
        }
	else
	{
	    JOptionPane.showMessageDialog(this.moduloEstaciones.fSelDirModificar, "Seleccione un director.", "", JOptionPane.ERROR_MESSAGE);
	}
    }
    
    public void cerrarSeleccionModificar()
    {
        this.moduloEstaciones.fSelDirModificar.setVisible(false);
    }
    
    public void buscarEstacionEliminar()
    {
        String nombre = this.moduloEstaciones.tfEliminarBuscar.getText();
        
        if (!nombre.isEmpty())
        {
            Estacion e = new EstacionDAO().consultarEstacion(nombre);
        
            if (e != null)
            {
                if (e.isEstado())
                {
                    this.moduloEstaciones.tfEliminarNombre.setText(e.getNombre());
                    this.moduloEstaciones.tfEliminarUbicacion.setText(e.getUbicacion());
                    this.moduloEstaciones.tfEliminarDirector.setText(e.getDirector());
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "La Estacion ya esta eliminada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEstaciones, "Estacion inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "Debe introducir un nombre para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarEstacion()
    {
        String nombre = this.moduloEstaciones.tfEliminarNombre.getText();
        
        if (!nombre.isEmpty())
        {
            int op = JOptionPane.showConfirmDialog(moduloEstaciones, "Desea eliminar la Estacion " + nombre + "?", "", JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION)
            {
                boolean exito = new EstacionDAO().eliminarEstacion(nombre);

                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "Estacion eliminada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposEliminar();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloEstaciones, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "No ha buscado una estacion.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiarCamposEliminar()
    {
        this.moduloEstaciones.tfEliminarBuscar.setText("");
        
        this.moduloEstaciones.tfEliminarNombre.setText("");
        this.moduloEstaciones.tfEliminarUbicacion.setText("");
        this.moduloEstaciones.tfEliminarDirector.setText("");
    }
    
    public void consultarEstaciones()
    {
        ArrayList<Estacion> lista = new EstacionDAO().consultarEstaciones();
        
        if (lista != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloEstaciones.tEstaciones.getModel();
            model.setRowCount(lista.size());
            
            for (int i = 0; i < lista.size(); i++) 
            {
                model.setValueAt(lista.get(i).getNombre(), i, 0);
                model.setValueAt(lista.get(i).getUbicacion(), i, 1);
                model.setValueAt(lista.get(i).getDirector(), i, 2);
                model.setValueAt(lista.get(i).getEstado(), i, 3);
            }
        }
    }
    
    public void cerrarVentana()
    {
        this.moduloEstaciones.fSelDirCrear.setVisible(false);
        this.moduloEstaciones.fSelDirModificar.setVisible(false);
        this.moduloEstaciones.setVisible(false);
    }
}
