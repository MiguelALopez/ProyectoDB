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
import Vista.ModuloEmpleados;
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
public class ModuloEmpleados_Eventos
{
    private final ModuloEmpleados moduloEmpleados;

    public ModuloEmpleados_Eventos(final ModuloEmpleados moduloEmpleados) 
    {
        this.moduloEmpleados = moduloEmpleados;
        
        this.moduloEmpleados.bCrearEmpleado.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    crearEmpleado();
                }
            }
        );
        
        this.moduloEmpleados.cbCrearCargo.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    verificarSeleccionCrear();
                }
            }
        );
        
        this.moduloEmpleados.bSeleccionCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarJefesCrear();
                }
            }
        );
        
        this.moduloEmpleados.bSeleccionarCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    seleccionarJefeCrear();
                }
            }
        );
        
        this.moduloEmpleados.bCancelarCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cerrarSeleccionCrear();
                }
            }
        );
        
        this.moduloEmpleados.bModificarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarEmpleadoModificar();
                }
            }
        );
        
        this.moduloEmpleados.bModificarEmpleado.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    modificarEmpleado();
                }
            }
        );
        
        this.moduloEmpleados.cbModificarCargo.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    verificarSeleccionModificar();
                }
            }
        );
        
        this.moduloEmpleados.bSeleccionModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarJefesModificar();
                }
            }
        );
        
        this.moduloEmpleados.bSeleccionarModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    seleccionarJefeModificar();
                }
            }
        );
        
        this.moduloEmpleados.bCancelarModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cerrarSeleccionModificar();
                }
            }
        );
        
        this.moduloEmpleados.bEliminarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarEmpleadoEliminar();
                }
            }
        );
        
        this.moduloEmpleados.bEliminarEmpleado.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    eliminarEmpleado();
                }
            }
        );
        
        this.moduloEmpleados.bActualizar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarEmpleados();
                }
            }
        );
        
        this.moduloEmpleados.bDetalles.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    verDetalles();
                }
            }
        );
        
        this.moduloEmpleados.bCerrarDetalles.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cerrarDetalles();
                }
            }
        );
        
        this.moduloEmpleados.addWindowListener(
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
    
    public void crearEmpleado()
    {
        if (verificarCamposCrear())
        {
            String id = this.moduloEmpleados.tfCrearID.getText();
            String nombre = this.moduloEmpleados.tfCrearNombre.getText();
            String telefono = this.moduloEmpleados.tfCrearTelefono.getText();
            String direccion = this.moduloEmpleados.tfCrearDireccion.getText();
            String email = this.moduloEmpleados.tfCrearEmail.getText();
            double salario = Double.parseDouble(this.moduloEmpleados.tfCrearSalario.getText());
            String cargo = (String) this.moduloEmpleados.cbCrearCargo.getSelectedItem();
            String jefe = this.moduloEmpleados.tfCrearJefe.getText();
            boolean estado = true;
            
            int op = JOptionPane.showConfirmDialog(moduloEmpleados, "Desea crear el Empleado " + id + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Empleado e = new Empleado(id, nombre, telefono, direccion, email, cargo, salario, jefe, estado);
                
                boolean exito = new EmpleadoDAO().insertarEmpleado(e);
                
                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloEmpleados, "Empleado creado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCrear();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloEmpleados, "Error al crear.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposCrear()
    {
        if (this.moduloEmpleados.tfCrearID.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo ID es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfCrearNombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfCrearTelefono.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Telefono es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try
        {
            Long.parseLong(this.moduloEmpleados.tfCrearTelefono.getText());
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Telefono invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfCrearDireccion.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Direccion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfCrearEmail.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Email es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfCrearSalario.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Salario es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try
        {
            Double.parseDouble(this.moduloEmpleados.tfCrearSalario.getText());
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Salario invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!this.moduloEmpleados.tfCrearJefe.getText().isEmpty())
        {
            Empleado e = new EmpleadoDAO().consultarEmpleado(this.moduloEmpleados.tfCrearJefe.getText());
            
            if (e != null)
            {
                if (!e.getCargo().equals("Director"))
                {
                    JOptionPane.showMessageDialog(moduloEmpleados, "El Empleado no es un Director.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                
                if (!e.isEstado())
                {
                    JOptionPane.showMessageDialog(moduloEmpleados, "El Empleado no esta Activo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEmpleados, "Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    public void limpiarCamposCrear()
    {
        this.moduloEmpleados.tfCrearID.setText("");
        this.moduloEmpleados.tfCrearNombre.setText("");
        this.moduloEmpleados.tfCrearTelefono.setText("");
        this.moduloEmpleados.tfCrearDireccion.setText("");
        this.moduloEmpleados.tfCrearEmail.setText("");
        this.moduloEmpleados.tfCrearSalario.setText("");
        this.moduloEmpleados.cbCrearCargo.setSelectedIndex(0);
        this.moduloEmpleados.tfCrearJefe.setText("");
    }
    
    public void verificarSeleccionCrear()
    {
        this.moduloEmpleados.tfCrearJefe.setText("");
        
        if (this.moduloEmpleados.cbCrearCargo.getSelectedItem().equals("Auxiliar"))
        {
            this.moduloEmpleados.tfCrearJefe.setEditable(true);
            this.moduloEmpleados.bSeleccionCrear.setEnabled(true);
        }
        else
        {
            this.moduloEmpleados.tfCrearJefe.setEditable(false);
            this.moduloEmpleados.bSeleccionCrear.setEnabled(false);
        }
    }
    
    public void consultarJefesCrear()
    {
        ArrayList<Empleado> listaDirectores = new EmpleadoDAO().consultarDirectores();
        
        if(listaDirectores != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloEmpleados.tJefesCrear.getModel();
            model.setRowCount(listaDirectores.size());
            
            for (int i = 0; i < listaDirectores.size(); i++) 
            {
                this.moduloEmpleados.tJefesCrear.setValueAt(listaDirectores.get(i).getId(), i, 0);
                this.moduloEmpleados.tJefesCrear.setValueAt(listaDirectores.get(i).getNombre(), i, 1);
                this.moduloEmpleados.tJefesCrear.setValueAt(listaDirectores.get(i).getCargo(), i, 2);
            }
            
            this.moduloEmpleados.fSelJefeCrear.setLocationRelativeTo(moduloEmpleados);
            this.moduloEmpleados.fSelJefeCrear.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Error al consultar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void seleccionarJefeCrear()
    {
        int row = this.moduloEmpleados.tJefesCrear.getSelectedRow();
        
        if(row != -1)
        {
            this.moduloEmpleados.tfCrearJefe.setText((String) this.moduloEmpleados.tJefesCrear.getValueAt(row, 0));
            
            DefaultTableModel model = (DefaultTableModel) this.moduloEmpleados.tJefesCrear.getModel();
            model.setRowCount(0);
            
            this.moduloEmpleados.fSelJefeCrear.setVisible(false);
        }
	else
	{
	    JOptionPane.showMessageDialog(this.moduloEmpleados.fSelJefeCrear, "Seleccione un director.", "", JOptionPane.ERROR_MESSAGE);
	}
    }
    
    public void cerrarSeleccionCrear()
    {
        this.moduloEmpleados.fSelJefeCrear.setVisible(false);
    }
    
    public void buscarEmpleadoModificar()
    {
        String id = this.moduloEmpleados.tfModificarBuscar.getText();
        
        Empleado e = new EmpleadoDAO().consultarEmpleado(id);
        
        if (e != null)
        {
            this.moduloEmpleados.tfModificarID.setText(e.getId());
            this.moduloEmpleados.tfModificarNombre.setText(e.getNombre());
            this.moduloEmpleados.tfModificarTelefono.setText(e.getTelefono());
            this.moduloEmpleados.tfModificarDireccion.setText(e.getDireccion());
            this.moduloEmpleados.tfModificarEmail.setText(e.getEmail());
            this.moduloEmpleados.tfModificarSalario.setText(String.valueOf(e.getSalario()));
            this.moduloEmpleados.cbModificarCargo.setSelectedItem(e.getCargo());
            this.moduloEmpleados.tfModificarJefe.setText(e.getJefe());
            this.moduloEmpleados.cbModificarEstado.setSelectedItem(e.getEstado());
            
            habilitarCamposModificar(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Error al consultar o Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void habilitarCamposModificar(boolean b)
    {
        //this.moduloEmpleados.tfModificarID.setEditable(b);
        this.moduloEmpleados.tfModificarNombre.setEditable(b);
        this.moduloEmpleados.tfModificarTelefono.setEditable(b);
        this.moduloEmpleados.tfModificarDireccion.setEditable(b);
        this.moduloEmpleados.tfModificarEmail.setEditable(b);
        this.moduloEmpleados.tfModificarSalario.setEditable(b);
        //this.moduloEmpleados.cbModificarCargo.setEnabled(b);
        //this.moduloEmpleados.tfModificarJefe.setEditable(b);
        this.moduloEmpleados.cbModificarEstado.setEnabled(b);
    }
    
    public void consultarJefesModificar()
    {
        ArrayList<Empleado> listaDirectores = new EmpleadoDAO().consultarDirectores();
        
        if(listaDirectores != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloEmpleados.tJefesModificar.getModel();
            model.setRowCount(listaDirectores.size());
            
            for (int i = 0; i < listaDirectores.size(); i++) 
            {
                this.moduloEmpleados.tJefesModificar.setValueAt(listaDirectores.get(i).getId(), i, 0);
                this.moduloEmpleados.tJefesModificar.setValueAt(listaDirectores.get(i).getNombre(), i, 1);
                this.moduloEmpleados.tJefesModificar.setValueAt(listaDirectores.get(i).getCargo(), i, 2);
            }
            
            this.moduloEmpleados.fSelJefeModificar.setLocationRelativeTo(moduloEmpleados);
            this.moduloEmpleados.fSelJefeModificar.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Error al consultar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void seleccionarJefeModificar()
    {
        int row = this.moduloEmpleados.tJefesModificar.getSelectedRow();
        
        if(row != -1)
        {
            this.moduloEmpleados.tfModificarJefe.setText((String) this.moduloEmpleados.tJefesModificar.getValueAt(row, 0));
            
            DefaultTableModel model = (DefaultTableModel) this.moduloEmpleados.tJefesModificar.getModel();
            model.setRowCount(0);
            
            this.moduloEmpleados.fSelJefeModificar.setVisible(false);
        }
	else
	{
	    JOptionPane.showMessageDialog(this.moduloEmpleados.fSelJefeModificar, "Seleccione un director.", "", JOptionPane.ERROR_MESSAGE);
	}
    }
    
    public void cerrarSeleccionModificar()
    {
        this.moduloEmpleados.fSelJefeModificar.setVisible(false);
    }
    
    public void modificarEmpleado()
    {
        if (verificarCamposModificar())
        {
            String id = this.moduloEmpleados.tfModificarID.getText();
            String nombre = this.moduloEmpleados.tfModificarNombre.getText();
            String telefono = this.moduloEmpleados.tfModificarTelefono.getText();
            String direccion = this.moduloEmpleados.tfModificarDireccion.getText();
            String email = this.moduloEmpleados.tfModificarEmail.getText();
            double salario = Double.parseDouble(this.moduloEmpleados.tfModificarSalario.getText());
            String cargo = (String) this.moduloEmpleados.cbModificarCargo.getSelectedItem();
            String jefe = this.moduloEmpleados.tfModificarJefe.getText();
            boolean estado = true;
            
            if (this.moduloEmpleados.cbModificarEstado.getSelectedItem().equals("Activo"))
            {
                estado = true;
            }
            else if (this.moduloEmpleados.cbModificarEstado.getSelectedItem().equals("Inactivo"))
            {
                estado = false;
            }
            
            int op = JOptionPane.showConfirmDialog(moduloEmpleados, "Desea modificar el Empleado " + id + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Empleado e = new Empleado(id, nombre, telefono, direccion, email, cargo, salario, jefe, estado);
                
                boolean exito = new EmpleadoDAO().modificarEmpleado(e);
                
                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloEmpleados, "Empleado modificado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposModificar();
                    habilitarCamposModificar(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloEmpleados, "Error al modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposModificar()
    {
        if (this.moduloEmpleados.tfModificarID.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo ID es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfModificarNombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfModificarTelefono.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Telefono es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try
        {
            Long.parseLong(this.moduloEmpleados.tfModificarTelefono.getText());
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Telefono invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfModificarDireccion.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Direccion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfModificarEmail.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Email es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloEmpleados.tfModificarSalario.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "El campo Salario es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try
        {
            Double.parseDouble(this.moduloEmpleados.tfModificarSalario.getText());
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Salario invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!this.moduloEmpleados.tfModificarJefe.getText().isEmpty())
        {
            Empleado e = new EmpleadoDAO().consultarEmpleado(this.moduloEmpleados.tfModificarJefe.getText());
            
            if (e != null)
            {
                if (!e.getCargo().equals("Director"))
                {
                    JOptionPane.showMessageDialog(moduloEmpleados, "El Empleado no es un Director.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                
                if (!e.isEstado())
                {
                    JOptionPane.showMessageDialog(moduloEmpleados, "El Empleado no esta Activo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEmpleados, "Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    public void limpiarCamposModificar()
    {
        this.moduloEmpleados.tfModificarBuscar.setText("");
        
        this.moduloEmpleados.tfModificarID.setText("");
        this.moduloEmpleados.tfModificarNombre.setText("");
        this.moduloEmpleados.tfModificarTelefono.setText("");
        this.moduloEmpleados.tfModificarDireccion.setText("");
        this.moduloEmpleados.tfModificarEmail.setText("");
        this.moduloEmpleados.tfModificarSalario.setText("");
        this.moduloEmpleados.cbModificarCargo.setSelectedIndex(0);
        this.moduloEmpleados.tfModificarJefe.setText("");
        this.moduloEmpleados.cbModificarEstado.setSelectedIndex(0);
    }
    
    public void verificarSeleccionModificar()
    {
        this.moduloEmpleados.tfModificarJefe.setText("");
        
        if (this.moduloEmpleados.cbModificarCargo.getSelectedItem().equals("Auxiliar"))
        {
            this.moduloEmpleados.tfModificarJefe.setEditable(true);
            this.moduloEmpleados.bSeleccionModificar.setEnabled(true);
        }
        else
        {
            this.moduloEmpleados.tfModificarJefe.setEditable(false);
            this.moduloEmpleados.bSeleccionModificar.setEnabled(false);
        }
    }
    
    public void buscarEmpleadoEliminar()
    {
        String id = this.moduloEmpleados.tfEliminarBuscar.getText();
        
        Empleado e = new EmpleadoDAO().consultarEmpleado(id);
        
        if (e != null)
        {
            if (e.isEstado())
            {
                this.moduloEmpleados.tfEliminarID.setText(e.getId());
                this.moduloEmpleados.tfEliminarNombre.setText(e.getNombre());
                this.moduloEmpleados.tfEliminarTelefono.setText(e.getTelefono());
                this.moduloEmpleados.tfEliminarDireccion.setText(e.getDireccion());
                this.moduloEmpleados.tfEliminarEmail.setText(e.getEmail());
                this.moduloEmpleados.tfEliminarSalario.setText(String.valueOf(e.getSalario()));
                this.moduloEmpleados.tfEliminarCargo.setText(e.getCargo());
                this.moduloEmpleados.tfEliminarJefe.setText(e.getJefe());
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEmpleados, "El Empleado ya esta eliminado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Error a consultar o Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarEmpleado()
    {
        String id = this.moduloEmpleados.tfEliminarID.getText();

        int op = JOptionPane.showConfirmDialog(moduloEmpleados, "Desea eliminar el Empleado " + id + "?", "", JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.YES_OPTION)
        {                
            boolean exito = new EmpleadoDAO().eliminarEmpleado(id);

            if (exito)
            {
                JOptionPane.showMessageDialog(moduloEmpleados, "Empleado eliminado exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposEliminar();
            }
            else
            {
                JOptionPane.showMessageDialog(moduloEmpleados, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void limpiarCamposEliminar()
    {
        this.moduloEmpleados.tfEliminarBuscar.setText("");
        
        this.moduloEmpleados.tfEliminarID.setText("");
        this.moduloEmpleados.tfEliminarNombre.setText("");
        this.moduloEmpleados.tfEliminarTelefono.setText("");
        this.moduloEmpleados.tfEliminarDireccion.setText("");
        this.moduloEmpleados.tfEliminarEmail.setText("");
        this.moduloEmpleados.tfEliminarSalario.setText("");
        this.moduloEmpleados.tfEliminarCargo.setText("");
        this.moduloEmpleados.tfEliminarJefe.setText("");
    }
    
    public void consultarEmpleados()
    {
        ArrayList<Empleado> lista = new EmpleadoDAO().consultarEmpleados();
        
        if (lista != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloEmpleados.tEmpleados.getModel();
            model.setRowCount(lista.size());
            
            for (int i = 0; i < lista.size(); i++) 
            {
                model.setValueAt(lista.get(i).getId(), i, 0);
                model.setValueAt(lista.get(i).getNombre(), i, 1);
                model.setValueAt(lista.get(i).getCargo(), i, 2);
                model.setValueAt(lista.get(i).getEstado(), i, 3);
            }
        }
    }
    
    public void verDetalles()
    {
        int row = moduloEmpleados.tEmpleados.getSelectedRow();
                        
        if (row != -1)
        {
            String cedula = (String) moduloEmpleados.tEmpleados.getValueAt(row, 0);
            
            Empleado usuario = new EmpleadoDAO().consultarEmpleado(cedula);

            if (usuario != null)
            {
                this.moduloEmpleados.tfDetallesID.setText(usuario.getId());
                this.moduloEmpleados.tfDetallesNombre.setText(usuario.getNombre());
                this.moduloEmpleados.tfDetallesTelefono.setText(usuario.getTelefono());
                this.moduloEmpleados.tfDetallesDireccion.setText(usuario.getDireccion());
                this.moduloEmpleados.tfDetallesEmail.setText(usuario.getEmail());
                this.moduloEmpleados.tfDetallesSalario.setText(String.valueOf(usuario.getSalario()));
                this.moduloEmpleados.tfDetallesCargo.setText(usuario.getCargo());
                this.moduloEmpleados.tfDetallesJefe.setText(usuario.getJefe());
                this.moduloEmpleados.tfDetallesEstado.setText(usuario.getEstado());
		
		this.moduloEmpleados.fDetalles.setLocationRelativeTo(moduloEmpleados);
		this.moduloEmpleados.fDetalles.setVisible(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "No ha seleccionado ningun Empleado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void cerrarDetalles()
    {
        this.moduloEmpleados.fDetalles.setVisible(false);
    }
    
    public void cerrarVentana()
    {
        this.moduloEmpleados.fSelJefeCrear.setVisible(false);
        this.moduloEmpleados.fSelJefeModificar.setVisible(false);
        this.moduloEmpleados.fDetalles.setVisible(false);
        this.moduloEmpleados.setVisible(false);
    }
}
