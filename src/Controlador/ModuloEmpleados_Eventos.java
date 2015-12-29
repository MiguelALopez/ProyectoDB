/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Vista.ModuloEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camilo Ruiz Casanova
 */
class ModuloEmpleados_Eventos 
{
    private ModuloEmpleados moduloEmpleados;

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
            
            int op = JOptionPane.showConfirmDialog(moduloEmpleados, "Desea crear el Empleado " + id + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Empleado e = new Empleado(id, nombre, telefono, direccion, email, cargo, salario, jefe);
                
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
        if (this.moduloEmpleados.cbCrearCargo.getSelectedItem().equals("Auxiliar"))
        {
            this.moduloEmpleados.tfCrearJefe.setEditable(true);
        }
        else
        {
            this.moduloEmpleados.tfCrearJefe.setEditable(false);
        }
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
            
            habilitarCamposModificar(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloEmpleados, "Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
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
            
            int op = JOptionPane.showConfirmDialog(moduloEmpleados, "Desea modificar el Empleado " + id + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Empleado e = new Empleado(id, nombre, telefono, direccion, email, cargo, salario, jefe);
                
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
    }
    
    public void verificarSeleccionModificar()
    {
        if (this.moduloEmpleados.cbModificarCargo.getSelectedItem().equals("Auxiliar"))
        {
            this.moduloEmpleados.tfModificarJefe.setEditable(true);
        }
        else
        {
            this.moduloEmpleados.tfModificarJefe.setEditable(false);
        }
    }
    
    public void buscarEmpleadoEliminar()
    {
        String id = this.moduloEmpleados.tfEliminarBuscar.getText();
        
        Empleado e = new EmpleadoDAO().consultarEmpleado(id);
        
        if (e != null)
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
            JOptionPane.showMessageDialog(moduloEmpleados, "Empleado inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
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
        this.moduloEmpleados.tfEliminarCargo.setText("");
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
            }
        }
    }
}
