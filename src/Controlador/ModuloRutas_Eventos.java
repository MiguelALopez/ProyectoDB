/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: SoftwareMio.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Controlador;

import Modelo.Bus;
import Modelo.BusDAO;
import Modelo.Ruta;
import Modelo.RutaDAO;
import Vista.ModuloRutas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class ModuloRutas_Eventos
{
    private final ModuloRutas moduloRutas;

    public ModuloRutas_Eventos(final ModuloRutas moduloRutas) 
    {
        this.moduloRutas = moduloRutas;
        
        this.moduloRutas.bCargarImagenCrear.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cargarImagenCrear();
                }                
            }
        );
        
        this.moduloRutas.bCrearRuta.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    crearRuta();
                }                
            }
        );
        
        this.moduloRutas.tfModificarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarRutaModificar();
                }                
            }
        );
        
        this.moduloRutas.bModificarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarRutaModificar();
                }                
            }
        );
        
        this.moduloRutas.bCargarImagenModificar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cargarImagenModificar();
                }                
            }
        );
        
        this.moduloRutas.bModificarRuta.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    modificarRuta();
                }                
            }
        );
        
        this.moduloRutas.tfEliminarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarRutaEliminar();
                }                
            }
        );
        
        this.moduloRutas.bEliminarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarRutaEliminar();
                }                
            }
        );
        
        this.moduloRutas.bEliminarRuta.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    eliminarRuta();
                }                
            }
        );
        
        this.moduloRutas.bActualizar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarRutas();
                }                
            }
        );
        
        this.moduloRutas.tfConsultarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarRutaConsultar();
                }                
            }
        );
        
        this.moduloRutas.bConsultarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    buscarRutaConsultar();
                }                
            }
        );
    }
    
    public void cargarImagenCrear()
    {
        this.moduloRutas.fileChooser.setFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
        int op = this.moduloRutas.fileChooser.showOpenDialog(moduloRutas);
        
        if (op == JFileChooser.APPROVE_OPTION)
        {
            String imagen = this.moduloRutas.fileChooser.getSelectedFile().getAbsolutePath();
            this.moduloRutas.tfImagenCrear.setText(imagen);
        }
    }
    
    public void crearRuta()
    {
        if (verificarCamposCrear())
        {
            String nombre = this.moduloRutas.tfCrearNombre.getText();
            String descripcion = this.moduloRutas.taCrearDescripcion.getText();
            boolean estado = true;
            
            int op = JOptionPane.showConfirmDialog(moduloRutas, "Desea crear la Ruta " + nombre + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Ruta e = new Ruta(nombre, descripcion, estado);
                
                boolean exito = new RutaDAO().insertarRuta(e);
                
                if (exito)
                {
                    File src = new File(this.moduloRutas.tfImagenCrear.getText());
                    File dest = new File("img/"+e.getNombre()+".jpg");
                    
                    try 
                    {
                        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(ModuloRutas_Eventos.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(moduloRutas, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    JOptionPane.showMessageDialog(moduloRutas, "Ruta creada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposCrear();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloRutas, "Error al crear.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposCrear()
    {
        if (this.moduloRutas.tfCrearNombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloRutas, "El campo Nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloRutas.taCrearDescripcion.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloRutas, "El campo Descripcion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public void limpiarCamposCrear()
    {
        this.moduloRutas.tfCrearNombre.setText("");
        this.moduloRutas.taCrearDescripcion.setText("");
        this.moduloRutas.tfImagenCrear.setText("");
    }
    
    public void buscarRutaModificar()
    {
        String nombre = this.moduloRutas.tfModificarBuscar.getText();
        
        Ruta e = new RutaDAO().consultarRuta(nombre);
        
        if (e != null)
        {
            this.moduloRutas.tfModificarNombre.setText(e.getNombre());
            this.moduloRutas.taModificarDescripcion.setText(e.getDescripcion());
            
            try
            {
                ImageIcon im = new ImageIcon("img/"+e.getNombre()+".png");
                this.moduloRutas.tfImagenModificar.setText("img/"+e.getNombre()+".png");
            }
            catch (NullPointerException ex)
            {
                try
                {
                    ImageIcon im = new ImageIcon("img/"+e.getNombre()+".jpg");
                    this.moduloRutas.tfImagenModificar.setText("img/"+e.getNombre()+".jpg");
                }
                catch (NullPointerException ex2)
                {
                    this.moduloRutas.tfImagenModificar.setText("");
                }
            }
            
            habilitarCamposModificar(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloRutas, "Ruta inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void habilitarCamposModificar(boolean b)
    {
        //this.moduloRutas.tfModificarNombre.setEditable(b);
        this.moduloRutas.taModificarDescripcion.setEditable(b);
        this.moduloRutas.cbModificarEstado.setEnabled(b);
        this.moduloRutas.bCargarImagenModificar.setEnabled(b);
    }
    
    public void cargarImagenModificar()
    {
        this.moduloRutas.fileChooser.setFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
        int op = this.moduloRutas.fileChooser.showOpenDialog(moduloRutas);
        
        if (op == JFileChooser.APPROVE_OPTION)
        {
            String imagen = this.moduloRutas.fileChooser.getSelectedFile().getAbsolutePath();
            this.moduloRutas.tfImagenModificar.setText(imagen);
        }
    }
    
    public void modificarRuta()
    {
        if (verificarCamposModificar())
        {
            String nombre = this.moduloRutas.tfModificarNombre.getText();
            String descripcion = this.moduloRutas.taModificarDescripcion.getText();
            boolean estado = true;
            
            if (this.moduloRutas.cbModificarEstado.getSelectedItem().equals("Activo"))
            {
                estado = true;
            }
            else if (this.moduloRutas.cbModificarEstado.getSelectedItem().equals("Inactivo"))
            {
                estado = false;
            }
            
            int op = JOptionPane.showConfirmDialog(moduloRutas, "Desea modificar la Ruta " + nombre + "?", "", JOptionPane.YES_NO_OPTION);
            
            if (op == JOptionPane.YES_OPTION)
            {
                Ruta e = new Ruta(nombre, descripcion, estado);
                
                boolean exito = new RutaDAO().modificarRuta(e);
                
                if (exito)
                {
                    File src = new File(this.moduloRutas.tfImagenModificar.getText());
                    File dest = new File("img/"+e.getNombre()+".jpg");
                    
                    try 
                    {
                        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(ModuloRutas_Eventos.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(moduloRutas, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    JOptionPane.showMessageDialog(moduloRutas, "Ruta modificada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposModificar();
                    habilitarCamposModificar(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloRutas, "Error al modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean verificarCamposModificar()
    {
        if (this.moduloRutas.tfModificarNombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloRutas, "El campo Nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloRutas.taModificarDescripcion.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloRutas, "El campo Descripcion es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public void limpiarCamposModificar()
    {
        this.moduloRutas.tfModificarBuscar.setText("");
        
        this.moduloRutas.tfModificarNombre.setText("");
        this.moduloRutas.taModificarDescripcion.setText("");
        this.moduloRutas.cbModificarEstado.setSelectedIndex(0);
        this.moduloRutas.tfImagenModificar.setText("");
    }
    
    public void buscarRutaEliminar()
    {
        String nombre = this.moduloRutas.tfEliminarBuscar.getText();
        
        if (!nombre.isEmpty())
        {
            Ruta e = new RutaDAO().consultarRuta(nombre);
        
            if (e != null)
            {
                if (e.isEstado())
                {
                    this.moduloRutas.tfEliminarNombre.setText(e.getNombre());
                    this.moduloRutas.taEliminarDescripcion.setText(e.getDescripcion());
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloRutas, "La Ruta ya esta eliminada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloRutas, "Ruta inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloRutas, "Debe introducir un nombre para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarRuta()
    {
        String nombre = this.moduloRutas.tfEliminarNombre.getText();
        
        if (!nombre.isEmpty())
        {
            int op = JOptionPane.showConfirmDialog(moduloRutas, "Desea eliminar la Ruta " + nombre + "?", "", JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION)
            {
                boolean exito = new RutaDAO().eliminarRuta(nombre);

                if (exito)
                {
                    JOptionPane.showMessageDialog(moduloRutas, "Ruta eliminada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposEliminar();
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloRutas, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloRutas, "No ha buscado una ruta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiarCamposEliminar()
    {
        this.moduloRutas.tfEliminarBuscar.setText("");
        
        this.moduloRutas.tfEliminarNombre.setText("");
        this.moduloRutas.taEliminarDescripcion.setText("");
    }
    
    public void consultarRutas()
    {
        ArrayList<Ruta> lista = new RutaDAO().consultarRutas();
        
        if (lista != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloRutas.tRutas.getModel();
            model.setRowCount(lista.size());
            
            for (int i = 0; i < lista.size(); i++) 
            {
                model.setValueAt(lista.get(i).getNombre(), i, 0);
                model.setValueAt(lista.get(i).getDescripcion(), i, 1);
                model.setValueAt(lista.get(i).getEstado(), i, 2);
            }
        }
    }
    
    public void buscarRutaConsultar()
    {
        String nombre = this.moduloRutas.tfConsultarBuscar.getText();
        
        Ruta e = new RutaDAO().consultarRuta(nombre);
        
        if (e != null)
        {
            consultarBusesRuta();
        }
        else
        {
            JOptionPane.showMessageDialog(moduloRutas, "Ruta inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void consultarBusesRuta()
    {
        String ruta = this.moduloRutas.tfConsultarBuscar.getText();
        
        ArrayList<Bus> lista = new BusDAO().consultarBuses(ruta);
        
        if (lista != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloRutas.tBuses.getModel();
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
