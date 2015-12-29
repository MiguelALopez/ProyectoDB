/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Estacion;
import Modelo.EstacionDAO;
import Vista.ModuloEstaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class ModuloEstaciones_Eventos 
{
    private ModuloEstaciones moduloEstaciones;
    
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
    }
    
    public void crearEstacion()
    {
        if (verificarCamposCrear())
        {
            String nombre = this.moduloEstaciones.tfCrearNombre.getText();
            String ubicacion = this.moduloEstaciones.tfCrearUbicacion.getText();
            String director = this.moduloEstaciones.tfCrearDirector.getText();
            
            int op = JOptionPane.showConfirmDialog(moduloEstaciones, "Desea crear la Estacion " + nombre + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Estacion e = new Estacion(nombre, ubicacion, director);
                
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
    
    public void buscarEstacionModificar()
    {
        String nombre = this.moduloEstaciones.tfModificarBuscar.getText();
        
        Estacion e = new EstacionDAO().consultarEstacion(nombre);
        
        if (e != null)
        {
            this.moduloEstaciones.tfModificarNombre.setText(e.getNombre());
            this.moduloEstaciones.tfModificarUbicacion.setText(e.getUbicacion());
            this.moduloEstaciones.tfModificarDirector.setText(e.getDirector());
            
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
    }
    
    public void modificarEstacion()
    {
        if (verificarCamposModificar())
        {
            String nombre = this.moduloEstaciones.tfModificarNombre.getText();
            String ubicacion = this.moduloEstaciones.tfModificarUbicacion.getText();
            String director = this.moduloEstaciones.tfModificarDirector.getText();
            
            int op = JOptionPane.showConfirmDialog(moduloEstaciones, "Desea modificar la Estacion " + nombre + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Estacion e = new Estacion(nombre, ubicacion, director);
                
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
    }
    
    public void buscarEstacionEliminar()
    {
        String nombre = this.moduloEstaciones.tfEliminarBuscar.getText();
        
        Estacion e = new EstacionDAO().consultarEstacion(nombre);
        
        if (e != null)
        {
            this.moduloEstaciones.tfEliminarNombre.setText(e.getNombre());
            this.moduloEstaciones.tfEliminarUbicacion.setText(e.getUbicacion());
            this.moduloEstaciones.tfEliminarDirector.setText(e.getDirector());
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEstaciones, "Estacion inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarEstacion()
    {
        String nombre = this.moduloEstaciones.tfEliminarNombre.getText();

        int op = JOptionPane.showConfirmDialog(moduloEstaciones, "Desea eliminar la Estacion " + nombre + "?", "", JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.YES_OPTION)
        {
            boolean exito = new EstacionDAO().eliminarEstacion(nombre);

            if (exito)
            {
                JOptionPane.showMessageDialog(moduloEstaciones, "Estacion modificada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposEliminar();
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEstaciones, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
            }
        }
    }
}
