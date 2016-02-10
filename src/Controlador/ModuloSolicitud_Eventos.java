/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author AndresFelipe
 */

import Modelo.*;
import Modelo.SolicitudDAO;
import Vista.ModuloSolicitud;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModuloSolicitud_Eventos
{
    private ModuloSolicitud moduloSolicitud;
    private String tipo = "";
    private String id = "";

    public ModuloSolicitud_Eventos(final ModuloSolicitud moduloSolicitud)
    {
        this.moduloSolicitud = moduloSolicitud;
        
        // Asignacion de fecha automaticamente
        DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date date = new Date();
        this.moduloSolicitud.tfGenFecha.setText(fecha.format(date));
        
        this.moduloSolicitud.bConResponder.setEnabled(false);
        
        ActionListener alCerrar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cerrar();
            }
        };
        
        this.moduloSolicitud.bConCerrar.addActionListener(alCerrar);
        this.moduloSolicitud.bGenCerrar.addActionListener(alCerrar);
        
        this.moduloSolicitud.bEstacion.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tipo = "estacion";
                fListar();
            }
        });
        
        this.moduloSolicitud.bPasajero.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tipo = "pasajero";
                fListar();
            }
        });
        
        this.moduloSolicitud.bListarSeleccionar.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fSeleccion();
            }
        });
        
        this.moduloSolicitud.bConBuscar.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tipo = "solicitud";
                fListar();
            }
        });
        
        this.moduloSolicitud.bListarCancelar.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cancelar();
            }
        });
        
        this.moduloSolicitud.bGenAgregar.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                agregarSolicitud();
            }
        });
        
        this.moduloSolicitud.bConResponder.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mostrarResponder();
            }
        });
        
        this.moduloSolicitud.bResponder.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                responder();
            }
        });
        
    }
    /**
     * Limpia los campos del panel Generar
     */
    private void genLimpiarCampos()
    {
        this.moduloSolicitud.tfGenEstacion.setText("");
        this.moduloSolicitud.tfGenPasajero.setText("");
        this.moduloSolicitud.tfGenMotivo.setText("");
        this.moduloSolicitud.taGenDescripcion.setText("");
    }
    /**
     * Limpia los campos del panel Consultar
     */
    private void conLimpiarCampos()
    {
        this.moduloSolicitud.tfConEstacion.setText("");
        this.moduloSolicitud.tfConPasajero.setText("");
        this.moduloSolicitud.tfConMotivo.setText("");
        this.moduloSolicitud.taConDescripcion.setText("");
    }
    
    private void resLimpiarCampos()
    {
        this.moduloSolicitud.taRespuesta.setText("");
    }
    /**
     * Cerrar formulario
     */
    private void cerrar()
    {
        genLimpiarCampos();
        conLimpiarCampos();
        this.moduloSolicitud.setVisible(false);
    }
    /**
     * Listar los datos de estaciones o pasajeros segun corresponda
     */
    private void fListar()
    {
        this.moduloSolicitud.fListar.setVisible(true);
        this.moduloSolicitud.fListar.setLocationRelativeTo(null);
        if ( this.tipo.equals("estacion") )
        {
            EstacionDAO estDAO = new EstacionDAO();
            ArrayList<Estacion> estaciones = estDAO.consultarEstaciones();
            
            if (estaciones != null)
            {
                DefaultTableModel model = (DefaultTableModel) this.moduloSolicitud.tSeleccionar.getModel();
                model.setColumnCount(3);
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(0).setHeaderValue("Nombre");
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(1).setHeaderValue("Ubicacion");
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(2).setHeaderValue("Estado");
                model.setRowCount(estaciones.size());

                for (int i = 0; i < estaciones.size(); i++) 
                {
                    this.moduloSolicitud.tSeleccionar.setValueAt(estaciones.get(i).getNombre(), i, 0);
                    this.moduloSolicitud.tSeleccionar.setValueAt(estaciones.get(i).getUbicacion(), i, 1);
                    this.moduloSolicitud.tSeleccionar.setValueAt(estaciones.get(i).getEstado(), i, 2);
                }
            }
            
        }
        else if ( this.tipo.equals("pasajero") )
        {
            PasajeroDAO pasDAO = new PasajeroDAO();
            ArrayList<Pasajero> pasajeros = pasDAO.consultarPasajeros();
            
            if (pasajeros != null)
            {
                DefaultTableModel model = (DefaultTableModel) this.moduloSolicitud.tSeleccionar.getModel();
                model.setColumnCount(3);
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(0).setHeaderValue("Cedula");
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(1).setHeaderValue("Nombre");
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(2).setHeaderValue("Tarjeta");
                model.setRowCount(pasajeros.size());

                for (int i = 0; i < pasajeros.size(); i++) 
                {
                    this.moduloSolicitud.tSeleccionar.setValueAt(pasajeros.get(i).getId(), i, 0);
                    this.moduloSolicitud.tSeleccionar.setValueAt(pasajeros.get(i).getNombre(), i, 1);
                    this.moduloSolicitud.tSeleccionar.setValueAt(pasajeros.get(i).getTarjeta(), i, 2);
                }
            }
        }
        
        else if ( this.tipo.equals("solicitud"))
        {
            SolicitudDAO solDAO = new SolicitudDAO();
            ArrayList<Solicitud> solicitudes = solDAO.consultarSolicitudes();
            
            if (solicitudes != null)
            {
                DefaultTableModel model = (DefaultTableModel) this.moduloSolicitud.tSeleccionar.getModel();
                model.setColumnCount(5);
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(0).setHeaderValue("Id");
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(1).setHeaderValue("Motivo");
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(2).setHeaderValue("Descripcion");
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(3).setHeaderValue("Fecha");
                this.moduloSolicitud.tSeleccionar.getColumnModel().getColumn(4).setHeaderValue("Estado");
                
                model.setRowCount(solicitudes.size());

                for (int i = 0; i < solicitudes.size(); i++) 
                {
                    this.moduloSolicitud.tSeleccionar.setValueAt(solicitudes.get(i).getId(), i, 0);
                    this.moduloSolicitud.tSeleccionar.setValueAt(solicitudes.get(i).getMotivo(), i, 1);
                    this.moduloSolicitud.tSeleccionar.setValueAt(solicitudes.get(i).getDescripcion(), i, 2);
                    this.moduloSolicitud.tSeleccionar.setValueAt(solicitudes.get(i).getFecha(), i, 3);
                    this.moduloSolicitud.tSeleccionar.setValueAt(solicitudes.get(i).getEstado(), i, 4);
                }
            }
        }
    }
    /**
     * Capturar el id seleccionado y colocarlo en el campo correspondiente
     */
    private void fSeleccion()
    {
        int row = this.moduloSolicitud.tSeleccionar.getSelectedRow();
        
        if( row != -1 )
        {
            String id = (String) this.moduloSolicitud.tSeleccionar.getValueAt(row, 0);
            if ( this.tipo.equals("estacion") )
            {
                this.moduloSolicitud.tfGenEstacion.setText(id);
            }
            else if (this.tipo.equals("pasajero"))
            {
                this.moduloSolicitud.tfGenPasajero.setText(id);
            }
            else if ( this.tipo.equals("solicitud") )
            {
                this.moduloSolicitud.tfConNumero.setText(id);
                consultaSolicitud();
            }
        }
        
        this.tipo = "";
        this.moduloSolicitud.bConResponder.setEnabled(true);
        this.moduloSolicitud.fListar.setVisible(false);
    }
    /**
     * Agregar la solicitud a la base de datos
     */
    private void agregarSolicitud()
    {
        int op = JOptionPane.showConfirmDialog(moduloSolicitud, "¿Desea agregar la solicitud a la Base de Datos?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        
        if (op == JOptionPane.YES_OPTION)
        {
            Solicitud sol;
            String Datos[] = {"","","","","",""};
            // String motivo,descripcion,fecha,estado,pasajero,estacion;
            // motivo
            Datos[0] = this.moduloSolicitud.tfGenMotivo.getText();
            // descripcion
            Datos[1] = this.moduloSolicitud.taGenDescripcion.getText();
            // fecha
            Datos[2] = this.moduloSolicitud.tfGenFecha.getText();
            //estado
            Datos[3] = "ACTIVO";
            // pasajero
            Datos[4] = this.moduloSolicitud.tfGenPasajero.getText();
            // estacion
            Datos[5] = this.moduloSolicitud.tfGenEstacion.getText();
            
            // Verificar que todos los campos hayan sido escritos
            boolean flag = true;
            for (int i = 0; i < Datos.length; i++)
            {
                if (Datos[i].isEmpty())
                    flag = false;
                
            }
            if(flag)
            {
                sol = new Solicitud("", Datos[0], Datos[1], Datos[2], Datos[3], Datos[4], Datos[5]);
            
                SolicitudDAO solDAO = new SolicitudDAO();

                boolean resultado = solDAO.insertarSolicitud(sol);

                if (resultado)
                {
                    JOptionPane.showMessageDialog(moduloSolicitud, "Solicitud agregada satisfactoriamente", "", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloSolicitud, "Error al intentar agregar la solicitud", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloSolicitud, "Por favor digite todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    /**
     
     */
    private void consultaSolicitud()
    {
        this.id = this.moduloSolicitud.tfConNumero.getText();
        SolicitudDAO solDAO = new SolicitudDAO();
        
        Solicitud sol = solDAO.consultarSolicitud(this.id);
        
        if( sol != null )
        {
            this.moduloSolicitud.tfConEstacion.setText(sol.getEstacion());
            this.moduloSolicitud.tfConFecha.setText(sol.getFecha());
            this.moduloSolicitud.tfConMotivo.setText(sol.getMotivo());
            this.moduloSolicitud.tfConPasajero.setText(sol.getPasajero());
            this.moduloSolicitud.taConDescripcion.setText(sol.getDescripcion());
        }
        
    }
    /**
     * Ocultar el panel de seleccion
     */
    private void cancelar()
    {
        this.moduloSolicitud.fListar.setVisible(false);
    }
    /**
     * Crear respuesta a la solicitud
     */
    private void responder()
    {
        String respuesta = this.moduloSolicitud.taRespuesta.getText();
        int op = JOptionPane.showConfirmDialog(moduloSolicitud, "¿Desea agregar la respuesta?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        boolean go = true;
        
        if (this.moduloSolicitud.taRespuesta.getText().isEmpty())
        {
            go = false;
            JOptionPane.showMessageDialog(moduloSolicitud, "Digite una respuesta", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if( (op == JOptionPane.YES_OPTION) && go)
        {
            SolicitudDAO solDAO = new SolicitudDAO();

            boolean medida = solDAO.insertarMedida(id, respuesta);
            if( !medida )
            {
                JOptionPane.showMessageDialog(moduloSolicitud, "Error al agregar respuesta","Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                this.moduloSolicitud.fResponder.setVisible(false);
                this.id = null;
                JOptionPane.showMessageDialog(moduloSolicitud, "Solicitud agregada correctamente");
            }
        }
    }
    
    private void mostrarResponder()
    {
        this.moduloSolicitud.fResponder.setVisible(true);
        this.moduloSolicitud.fResponder.setLocationRelativeTo(null);
    }
}