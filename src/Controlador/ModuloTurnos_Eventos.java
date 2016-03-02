/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: ModuloTurnos_Eventos.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Turno;
import Modelo.TurnoDAO;
import Vista.ModuloTurnos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModuloTurnos_Eventos
{
    private final ModuloTurnos moduloTurnos;
    Turno old;

    public ModuloTurnos_Eventos(final ModuloTurnos moduloTurnos) 
    {
        this.moduloTurnos = moduloTurnos;
        old = null;
        
        this.moduloTurnos.bAsignarTurno.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    crearTurno();
                }                
            }
        );
        
        this.moduloTurnos.bBuscar1.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarTurnoModificar1();
                }                
            }
        );
        
        this.moduloTurnos.bBuscar2.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarTurnoModificar2();
                }                
            }
        );
        
        this.moduloTurnos.bModificarTurno.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    modificarTurno();
                }                
            }
        );
        
        this.moduloTurnos.bBuscar3.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarTurnoEliminar1();
                }                
            }
        );
        
        this.moduloTurnos.bBuscar4.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarTurnoEliminar2();
                }                
            }
        );
        
        this.moduloTurnos.bEliminarTurno.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    eliminarTurno();
                }                
            }
        );
        
        this.moduloTurnos.bActualizar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarTurnos();
                }                
            }
        );
    }
    
    public void crearTurno()
    {
        if (verificarCamposCrear())
        {
            String conductor = this.moduloTurnos.tfCrearID.getText();
            String bus = this.moduloTurnos.tfCrearSerial.getText();
            String turno = (String) this.moduloTurnos.cbCrearTurno.getSelectedItem();
            
            int op = JOptionPane.showConfirmDialog(moduloTurnos, "Desea crear la Turno " + conductor + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Turno e = new Turno(bus, turno, conductor);
                
                boolean exito = new TurnoDAO().insertarTurno(e);
                
                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloTurnos, "Turno creada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCrear();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloTurnos, "Error al crear.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposCrear()
    {
        if (this.moduloTurnos.tfCrearID.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloTurnos, "El campo ID es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            Empleado e = new EmpleadoDAO().consultarEmpleado(this.moduloTurnos.tfCrearID.getText());
            
            if (e != null)
            {
                if (!e.getCargo().equals("Conductor"))
                {
                    JOptionPane.showMessageDialog(moduloTurnos, "El empleado no es conductor.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloTurnos, "Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        if (this.moduloTurnos.tfCrearSerial.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloTurnos, "El campo Serial es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public void limpiarCamposCrear()
    {
        this.moduloTurnos.tfCrearID.setText("");
        this.moduloTurnos.tfCrearSerial.setText("");
        this.moduloTurnos.cbCrearTurno.setSelectedIndex(0);
    }
    
    public void buscarTurnoModificar1()
    {
        String conductor = this.moduloTurnos.tfBuscarID1.getText();
        
        Turno e = new TurnoDAO().consultarTurno(conductor);
        this.old = e;
        
        if (e != null)
        {
            this.moduloTurnos.tfModificarID.setText(e.getConductor());
            this.moduloTurnos.tfModificarSerial.setText(e.getBus());
            this.moduloTurnos.cbModificarTurno.setSelectedItem(e.getTurno());
            
            habilitarCamposModificar(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloTurnos, "Turno inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buscarTurnoModificar2()
    {
        String bus = this.moduloTurnos.tfBuscarSerial1.getText();
        String turno = (String) this.moduloTurnos.cbBuscarTurno1.getSelectedItem();
        
        Turno e = new TurnoDAO().consultarTurno(bus, turno);
        this.old = e;
        
        if (e != null)
        {
            this.moduloTurnos.tfModificarID.setText(e.getConductor());
            this.moduloTurnos.tfModificarSerial.setText(e.getBus());
            this.moduloTurnos.cbModificarTurno.setSelectedItem(e.getTurno());
            
            habilitarCamposModificar(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloTurnos, "Turno inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void habilitarCamposModificar(boolean b)
    {
        this.moduloTurnos.tfModificarID.setEditable(b);
        this.moduloTurnos.tfModificarSerial.setEditable(b);
        this.moduloTurnos.cbModificarTurno.setEnabled(b);
    }
    
    public void modificarTurno()
    {
        if (verificarCamposModificar())
        {
            String conductor = this.moduloTurnos.tfModificarID.getText();
            String bus = this.moduloTurnos.tfModificarSerial.getText();
            String turno = (String) this.moduloTurnos.cbModificarTurno.getSelectedItem();
            
            int op = JOptionPane.showConfirmDialog(moduloTurnos, "Desea modificar la Turno " + conductor + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Turno e = new Turno(bus, turno, conductor);
                
                boolean exito = new TurnoDAO().modificarTurno(this.old, e);
                
                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloTurnos, "Turno modificada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposModificar();
                    habilitarCamposModificar(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloTurnos, "Error al modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposModificar()
    {
        if (this.moduloTurnos.tfModificarID.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloTurnos, "El campo ID es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            Empleado e = new EmpleadoDAO().consultarEmpleado(this.moduloTurnos.tfModificarID.getText());
            
            if (e != null)
            {
                if (!e.getCargo().equals("Conductor"))
                {
                    JOptionPane.showMessageDialog(moduloTurnos, "El empleado no es conductor.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloTurnos, "Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        if (this.moduloTurnos.tfModificarSerial.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloTurnos, "El campo Serial es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public void limpiarCamposModificar()
    {
        this.moduloTurnos.tfBuscarID1.setText("");
        this.moduloTurnos.tfBuscarSerial1.setText("");
        this.moduloTurnos.cbBuscarTurno1.setSelectedIndex(0);
        
        this.moduloTurnos.tfModificarID.setText("");
        this.moduloTurnos.tfModificarSerial.setText("");
        this.moduloTurnos.cbModificarTurno.setSelectedIndex(0);
    }
    
    public void buscarTurnoEliminar1()
    {
        String conductor = this.moduloTurnos.tfBuscarID2.getText();
        
        Turno e = new TurnoDAO().consultarTurno(conductor);
        
        if (e != null)
        {
            this.moduloTurnos.tfEliminarID.setText(e.getConductor());
            this.moduloTurnos.tfEliminarSerial.setText(e.getBus());
            this.moduloTurnos.tfEliminarTurno.setText(e.getTurno());
        }
        else
        {
            JOptionPane.showMessageDialog(moduloTurnos, "Turno inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void buscarTurnoEliminar2()
    {
        String bus = this.moduloTurnos.tfBuscarSerial2.getText();
        String turno = (String) this.moduloTurnos.cbBuscarTurno2.getSelectedItem();
        
        Turno e = new TurnoDAO().consultarTurno(bus, turno);
        
        if (e != null)
        {
            this.moduloTurnos.tfEliminarID.setText(e.getConductor());
            this.moduloTurnos.tfEliminarSerial.setText(e.getBus());
            this.moduloTurnos.tfEliminarTurno.setText(e.getTurno());
        }
        else
        {
            JOptionPane.showMessageDialog(moduloTurnos, "Turno inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarTurno()
    {
        String conductor = this.moduloTurnos.tfEliminarID.getText();
        
        if (conductor.isEmpty())
        {
            JOptionPane.showMessageDialog(moduloTurnos, "No ha buscado ningun turno.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int op = JOptionPane.showConfirmDialog(moduloTurnos, "Desea eliminar la Turno " + conductor + "?", "", JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.YES_OPTION)
        {
            boolean exito = new TurnoDAO().eliminarTurno(conductor);

            if (exito)
            {
                JOptionPane.showMessageDialog(moduloTurnos, "Turno eliminado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposEliminar();
            }
            else
            {
                JOptionPane.showMessageDialog(moduloTurnos, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }        
    }
    
    public void limpiarCamposEliminar()
    {
        this.moduloTurnos.tfBuscarID2.setText("");
        this.moduloTurnos.tfBuscarSerial2.setText("");
        this.moduloTurnos.cbBuscarTurno2.setSelectedIndex(0);
        
        this.moduloTurnos.tfEliminarID.setText("");
        this.moduloTurnos.tfEliminarSerial.setText("");
        this.moduloTurnos.tfEliminarTurno.setText("");
    }
    
    public void consultarTurnos()
    {
        ArrayList<Turno> lista = new TurnoDAO().consultarTurnos();
        
        if (lista != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloTurnos.tTurnos.getModel();
            model.setRowCount(lista.size());
            
            for (int i = 0; i < lista.size(); i++) 
            {
                model.setValueAt(lista.get(i).getBus(), i, 0);
                model.setValueAt(lista.get(i).getTurno(), i, 1);
                model.setValueAt(lista.get(i).getConductor(), i, 2);
            }
        }
    }
}
