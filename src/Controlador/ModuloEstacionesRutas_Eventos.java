/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: ModuloEstacionesRutas_Eventos.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Controlador;

import Modelo.Estacion;
import Modelo.EstacionDAO;
import Modelo.EstacionRuta;
import Modelo.EstacionRutaDAO;
import Modelo.Ruta;
import Modelo.RutaDAO;
import Vista.ModuloEstacionesRutas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModuloEstacionesRutas_Eventos
{
    private final ModuloEstacionesRutas moduloEstacionesRutas;

    public ModuloEstacionesRutas_Eventos(final ModuloEstacionesRutas moduloEstacionesRutas) 
    {
        this.moduloEstacionesRutas = moduloEstacionesRutas;
        
        this.moduloEstacionesRutas.bAsignar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    asignar();
                }                
            }
        );
        
        this.moduloEstacionesRutas.bEliminar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    eliminar();
                }                
            }
        );
        
        this.moduloEstacionesRutas.bActualizar1.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultar();
                }                
            }
        );
        
        this.moduloEstacionesRutas.bBuscarEstacion.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarPorEstacion();
                }                
            }
        );
        
        this.moduloEstacionesRutas.bBuscarRuta.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarPorRuta();
                }                
            }
        );
    }
    
    public void asignar()
    {
        if (verificarCamposCrear())
        {
            String estacion = this.moduloEstacionesRutas.tfCrearNombreEstacion.getText();
            String ruta = this.moduloEstacionesRutas.tfCrearNombreRuta.getText();
            
            int op = JOptionPane.showConfirmDialog(moduloEstacionesRutas, "Desea asignar la Ruta " + ruta + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                EstacionRuta er = new EstacionRuta(estacion, ruta);
                
                boolean exito = new EstacionRutaDAO().insertarEstacionRuta(er);
                
                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloEstacionesRutas, "Ruta asignada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCrear();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloEstacionesRutas, "Error al asignar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposCrear()
    {
        if (this.moduloEstacionesRutas.tfCrearNombreEstacion.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEstacionesRutas, "El campo Estacion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEstacionesRutas.tfCrearNombreRuta.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEstacionesRutas, "El campo Ruta es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public void limpiarCamposCrear()
    {
        this.moduloEstacionesRutas.tfCrearNombreEstacion.setText("");
        this.moduloEstacionesRutas.tfCrearNombreRuta.setText("");
    }
    
    public void eliminar()
    {
        String estacion = this.moduloEstacionesRutas.tfEliminarNombreEstacion.getText();
        String ruta = this.moduloEstacionesRutas.tfEliminarNombreRuta.getText();
        
        if (estacion.isEmpty() || ruta.isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEstacionesRutas, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        else if (new EstacionRutaDAO().consultarEstacionRuta(estacion, ruta) == null)
        {
            JOptionPane.showMessageDialog(moduloEstacionesRutas, "Asignacion inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int op = JOptionPane.showConfirmDialog(moduloEstacionesRutas, "Desea eliminar la Ruta " + ruta + " de la Estacion " + estacion + "?", "", JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.YES_OPTION)
        {
            boolean exito = new EstacionRutaDAO().eliminarEstacionRuta(estacion, ruta);

            if (exito)
            {
                JOptionPane.showMessageDialog(moduloEstacionesRutas, "Asignacion eliminada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposEliminar();
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEstacionesRutas, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void limpiarCamposEliminar()
    {
        this.moduloEstacionesRutas.tfEliminarNombreEstacion.setText("");
        this.moduloEstacionesRutas.tfEliminarNombreRuta.setText("");
    }
    
    public void consultar()
    {
        ArrayList<EstacionRuta> lista = new EstacionRutaDAO().consultarEstacionRutas();
        
        if (lista != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloEstacionesRutas.tEstacionesRutas.getModel();
            model.setRowCount(lista.size());
            
            for (int i = 0; i < lista.size(); i++) 
            {
                model.setValueAt(lista.get(i).getEstacion(), i, 0);
                model.setValueAt(lista.get(i).getRuta(), i, 1);
            }
        }
    }
    
    public void buscarPorEstacion()
    {
        String estacion = this.moduloEstacionesRutas.tfBuscarEstacion.getText();
        
        if (!estacion.isEmpty())
        {
            ArrayList<Ruta> lista = new RutaDAO().consultarRutas(estacion);

            if (lista != null)
            {
                DefaultTableModel model = (DefaultTableModel) this.moduloEstacionesRutas.tRutas.getModel();
                model.setRowCount(lista.size());

                for (int i = 0; i < lista.size(); i++) 
                {
                    model.setValueAt(lista.get(i).getNombre(), i, 0);
                    model.setValueAt(lista.get(i).getDescripcion(), i, 1);
                    model.setValueAt(lista.get(i).getEstado(), i, 2);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEstacionesRutas, "Debe ingresar un nombre para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buscarPorRuta()
    {
        String ruta = this.moduloEstacionesRutas.tfBuscarRuta.getText();
        
        if (!ruta.isEmpty())
        {
            ArrayList<Estacion> lista = new EstacionDAO().consultarEstaciones(ruta);

            if (lista != null)
            {
                DefaultTableModel model = (DefaultTableModel) this.moduloEstacionesRutas.tEstaciones.getModel();
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
        else
        {
            JOptionPane.showMessageDialog(moduloEstacionesRutas, "Debe ingresar un nombre para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
