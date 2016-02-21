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

import Modelo.*;
import Vista.ModuloServicios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class ModuloServicios_Eventos 
{
    private final ModuloServicios moduloServicios;
    private final double VALOR_PASAJE;
    private ImageIcon rec;

    public ModuloServicios_Eventos(final ModuloServicios moduloServicios) 
    {
        this.moduloServicios = moduloServicios;
        this.VALOR_PASAJE = 1700;     
        
        this.moduloServicios.tabs.addChangeListener(
            new ChangeListener()
            {
                @Override
                public void stateChanged(ChangeEvent e) 
                {
                    tabChanged();
                }
            }
        );
        
        this.moduloServicios.bConsultarSaldo.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarSaldo();
                }                
            }
        );
        
        this.moduloServicios.bAbordarBuscar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    consultarEstacionesAbordar();
                }                
            }
        );
        
        this.moduloServicios.bSeleccionar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    seleccionarEstacionesAbordar();
                }                
            }
        );
        
        this.moduloServicios.bCancelar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    cerrarSeleccionAbordar();
                }                
            }
        );
        
        this.moduloServicios.bAbordar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    abordarRuta();
                }
            }
        );
        
        this.moduloServicios.cbEstaciones.addItemListener(
            new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e) 
                {
                    consultarRutasEstacion();
                }                
            }
        );
        
        this.moduloServicios.bRecorrido.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    seleccionarRuta();
                }                
            }
        );
        
        this.moduloServicios.cbOrigen.addItemListener(
            new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e) 
                {
                    consultarDestinos();
                }                
            }
        );
        
        this.moduloServicios.cbDestino.addItemListener(
            new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e) 
                {
                    consultarRutasSugeridas();
                }                
            }
        );
        
        this.moduloServicios.bRecorridoSugeridas.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    seleccionarRutaSugerida();
                }                
            }
        );

        this.moduloServicios.bVentaVender.addActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    venderTarjeta();
                }
            }
        );

        this.moduloServicios.bRecargaRecargar.addActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    recargarTarjeta();
                }
            }
        );
        this.moduloServicios.radioPerson.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableCamposVenta(true);
                    }
                }
        );

        this.moduloServicios.radioGenerica.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableCamposVenta(false);
                    }
                }
        );
        
        tabChanged();
    }
    
    private void tabChanged()
    {       
        switch (this.moduloServicios.tabs.getSelectedIndex()) 
        {
            case 0:
            {
                consultarTarjetasVenta();
                consultarEstacionesVenta();
                DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = new Date();
                moduloServicios.tVentaFecha.setText(fecha.format(date));
                break;
            }
            case 1:
            {
                break;
            }
            case 2:
            {
                consultarEstaciones();
                break;
            }
            case 3:
            {
                consultarOrigen();
                break;
            }
            default:
            {
                break;
            }
        }
    }
    
    public void verRecorrido(String ruta)
    {
        try
        {
            rec = new ImageIcon("img/"+ruta+".png");
            this.moduloServicios.lRecorrido.setIcon(rec);
        }
        catch (NullPointerException ex)
        {
            try
            {
                rec = new ImageIcon("img/"+ruta+".jpg");
                this.moduloServicios.lRecorrido.setIcon(rec);
            }
            catch (NullPointerException ex2)
            {
                try
                {
                    rec = new ImageIcon("img/default.png");
                    this.moduloServicios.lRecorrido.setIcon(rec);
                }
                catch (NullPointerException ex3)
                {
                    System.out.println("No image available");
                }
            }
        }
        
        this.moduloServicios.fRecorrido.setLocationRelativeTo(moduloServicios);
        this.moduloServicios.fRecorrido.setVisible(true);
    }

    public boolean verificarCamposVenta()
    {
        boolean exito = true;
        
        if(moduloServicios.comboVentaIdTarjeta.getItemCount() == 0)
        {
            JOptionPane.showMessageDialog(moduloServicios, "No hay tarjetas en el sistema por favor cree nuevas tarjetas.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        else if (moduloServicios.comboVentaEstaciones.getItemCount() == 0)
        {
            JOptionPane.showMessageDialog(moduloServicios, "No hay estaciones en el sistema por favor cree nuevas estaciones.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        
        if (moduloServicios.tVentaValor.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloServicios, "El campo valor es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        else 
        {
            try 
            {
                Double.parseDouble(moduloServicios.tVentaValor.getText());
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(moduloServicios, "El campo valor debe ser numerico.", "Error", JOptionPane.ERROR_MESSAGE);
                exito = false;
            }
        }
        
        return exito;
    }

    public void limpiarCamposVenta(){
        moduloServicios.tVentaCedula.setText("");
        moduloServicios.tVentaNombre.setText("");
        moduloServicios.tVentaTelefono.setText("");
        moduloServicios.tVentaDireccion.setText("");
        moduloServicios.tVentaEmail.setText("");
    }

    public void enableCamposVenta(boolean enable){
        moduloServicios.tVentaCedula.setEnabled(enable);
        moduloServicios.tVentaNombre.setEnabled(enable);
        moduloServicios.tVentaTelefono.setEnabled(enable);
        moduloServicios.tVentaDireccion.setEnabled(enable);
        moduloServicios.tVentaEmail.setEnabled(enable);
        if (!enable){
            limpiarCamposVenta();
        }
    }
    
    public void consultarTarjetasVenta()
    {
        ArrayList<Tarjeta> tarjetas = new TarjetaDAO().consultarTarjetasVendidas(false);
        moduloServicios.comboVentaIdTarjeta.removeAllItems();
        
        if (tarjetas != null)
        {
            for (int i = 0; i < tarjetas.size(); i++) 
            {
                moduloServicios.comboVentaIdTarjeta.addItem(tarjetas.get(i).getId());
            }
        }
    }

    public void consultarEstacionesVenta()
    {
        ArrayList<Estacion> estaciones = new EstacionDAO().consultarEstaciones(true);
        moduloServicios.comboVentaEstaciones.removeAllItems();
        
        if (estaciones != null)
        {
            for (int i = 0; i < estaciones.size(); i++) 
            {
                moduloServicios.comboVentaEstaciones.addItem(estaciones.get(i).getNombre());
            }
        }
    }

    public void venderTarjeta()
    {
        if (verificarCamposVenta()) 
        {
            String tarjetaID = moduloServicios.comboVentaIdTarjeta.getSelectedItem().toString();
            double valor = Double.parseDouble(moduloServicios.tVentaValor.getText());
            String estacion = moduloServicios.comboVentaEstaciones.getSelectedItem().toString();
            String fecha = moduloServicios.tVentaFecha.getText();

            Venta venta = new Venta(fecha, valor, estacion, tarjetaID);

            if (moduloServicios.radioPerson.isSelected()){
                String cedula = moduloServicios.tVentaCedula.getText();
                String nombre = moduloServicios.tVentaNombre.getText();
                String telefono = moduloServicios.tVentaTelefono.getText();
                String direccion = moduloServicios.tVentaDireccion.getText();
                String email = moduloServicios.tVentaEmail.getText();

                Pasajero pasajero = new Pasajero(cedula,nombre,telefono,direccion,email,tarjetaID,true);
                boolean exitoPersonalizada = new VentaDAO().insertarVentaPersonalizada(venta, pasajero);

                if (exitoPersonalizada){
                    JOptionPane.showMessageDialog(moduloServicios, "La venta personalizada fue registrada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    consultarTarjetasVenta();
                }
                else{
                    JOptionPane.showMessageDialog(moduloServicios, "Error al registrar la venta personalizada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else {

                boolean exitoVenta = new VentaDAO().insertarVenta(venta);
                if (exitoVenta){
                    JOptionPane.showMessageDialog(moduloServicios, "Venta registrada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
                    consultarTarjetasVenta();
                }
                else{
                    JOptionPane.showMessageDialog(moduloServicios, "Error al registrar la venta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public boolean verificarCamposRecarga()
    {
        boolean exito = true;
        
        if (moduloServicios.tRecargaTarjeta.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloServicios, "El campo Tarjeta ID es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        else
        {
            try
            {
                Integer.parseInt(moduloServicios.tRecargaTarjeta.getText());
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(moduloServicios, "El campo Tarjeta ID debe ser numerico.", "Error", JOptionPane.ERROR_MESSAGE);
                exito = false;
            }
        }
        
        if (moduloServicios.tRecargaCantidad.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloServicios, "El campo Cantidad es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            exito = false;
        }
        else 
        {
            try
            {
                Double.parseDouble(moduloServicios.tRecargaCantidad.getText());
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(moduloServicios, "El campo Cantidad debe ser numerico.", "Error", JOptionPane.ERROR_MESSAGE);
                exito = false;
            }
        }
        
        return exito;
    }
    
    public void recargarTarjeta()
    {
        if (verificarCamposRecarga()) 
        {
            String tarjetaID = moduloServicios.tRecargaTarjeta.getText();
            double cantidad = Double.parseDouble(moduloServicios.tRecargaCantidad.getText());

            boolean exito = new TarjetaDAO().recargarTarjeta(tarjetaID, cantidad);

            if (exito) 
            {
                JOptionPane.showMessageDialog(moduloServicios, "Recarga registrada exitosamente.", "", JOptionPane.INFORMATION_MESSAGE);
            }
            else 
            {
                JOptionPane.showMessageDialog(moduloServicios, "Error al recargar la tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void consultarSaldo()
    {
        String id = this.moduloServicios.tfConsultarSaldo.getText();
        
        if (!id.isEmpty())
        {
            Tarjeta t = new TarjetaDAO().consultarTarjeta(id, true);
            
            if (t != null)
            {
                String mensaje = "Tarjeta ID: "+t.getId()+"\nSaldo: "+t.getSaldo();
            
                if (new TarjetaDAO().esGenerica(id))
                {
                    JOptionPane.showMessageDialog(moduloServicios, "Tarjeta Generica\n"+mensaje, "", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloServicios, "Tarjeta Personalizada\n"+mensaje, "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloServicios, "Tarjeta inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(moduloServicios, "Debe ingresar un id de tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void consultarEstacionesAbordar()
    {
        ArrayList<Estacion> listaEstaciones = new EstacionDAO().consultarEstaciones(true);
        
        if(listaEstaciones != null)
        {
            DefaultTableModel model = (DefaultTableModel) this.moduloServicios.tEstaciones.getModel();
            model.setRowCount(listaEstaciones.size());
            
            for (int i = 0; i < listaEstaciones.size(); i++) 
            {
                this.moduloServicios.tEstaciones.setValueAt(listaEstaciones.get(i).getNombre(), i, 0);
                this.moduloServicios.tEstaciones.setValueAt(listaEstaciones.get(i).getUbicacion(), i, 1);
            }
            
            this.moduloServicios.fSelEstacion.setLocationRelativeTo(moduloServicios);
            this.moduloServicios.fSelEstacion.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloServicios, "Error al consultar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void seleccionarEstacionesAbordar()
    {
        int row = this.moduloServicios.tEstaciones.getSelectedRow();
        
        if(row != -1)
        {
            this.moduloServicios.tfAbordarEstacion.setText((String) this.moduloServicios.tEstaciones.getValueAt(row, 0));
            
            consultarRutasAbordar();
            
            this.moduloServicios.fSelEstacion.setVisible(false);
            DefaultTableModel model = (DefaultTableModel) this.moduloServicios.tEstaciones.getModel();
            model.setRowCount(0);
        }
	else
	{
	    JOptionPane.showMessageDialog(this.moduloServicios.fSelEstacion, "Seleccione una Estacion.", "", JOptionPane.ERROR_MESSAGE);
	}
    }
    
    public void cerrarSeleccionAbordar()
    {
        this.moduloServicios.fSelEstacion.setVisible(false);
    }
    
    public void consultarRutasAbordar()
    {
        String estacion = this.moduloServicios.tfAbordarEstacion.getText();
        
        if (!estacion.isEmpty())
        {
            ArrayList<Ruta> lista = new RutaDAO().consultarRutas(estacion, true);
            
            if (lista != null)
            {
                this.moduloServicios.cbAbordarRuta.removeAllItems();
                
                for (int i = 0; i < lista.size(); i++) 
                {
                    this.moduloServicios.cbAbordarRuta.addItem(lista.get(i).getNombre());
                }
                
                this.moduloServicios.cbAbordarRuta.setSelectedIndex(0);
            }
        }
        else
        {
            this.moduloServicios.cbAbordarRuta.removeAllItems();
        }
    }
    
    public boolean verificarCamposAbordar()
    {
        if (this.moduloServicios.tfAbordarEstacion.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloServicios, "Debe seleccionar una Estacion.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloServicios.cbAbordarRuta.getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(moduloServicios, "Debe seleccionar una Ruta.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (this.moduloServicios.tfAbordarTarjeta.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(moduloServicios, "Debe ingresar una Tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public void abordarRuta()
    {
        if (verificarCamposAbordar())
        {
            Tarjeta t = new TarjetaDAO().consultarTarjeta(this.moduloServicios.tfAbordarTarjeta.getText(), true);
            
            if (t != null)
            {
                if (t.getEstado().equals("BLOQUEADA"))
                {
                    JOptionPane.showMessageDialog(moduloServicios, "Tarjeta bloqueada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (new TarjetaDAO().esGenerica(t.getId()))
                {
                    if ((t.getSaldo() - this.VALOR_PASAJE) < 0)
                    {
                        JOptionPane.showMessageDialog(moduloServicios, "Tarjeta Generica\nSaldo insuficiente: " + t.getSaldo(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else
                {
                    if ((t.getSaldo() - this.VALOR_PASAJE) < (this.VALOR_PASAJE*(-3)))
                    {
                        JOptionPane.showMessageDialog(moduloServicios, "Tarjeta Personalizada\nSaldo insuficiente: " + t.getSaldo(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                t.setSaldo(t.getSaldo() - this.VALOR_PASAJE);
                
                String ruta = (String) this.moduloServicios.cbAbordarRuta.getSelectedItem();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String fecha = df.format(Calendar.getInstance().getTime());
                TarjetaRuta abordo = new TarjetaRuta(t.getId(), ruta, fecha);
                
                boolean res = new TarjetaRutaDAO().abordarRuta(t, abordo);
                
                if (res)
                {
                    JOptionPane.showMessageDialog(moduloServicios, "Pago Aceptado\nNuevo saldo: " + t.getSaldo(), "", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCamposAbordar();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(moduloServicios, "Tarjeta inexistente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void limpiarCamposAbordar()
    {
        this.moduloServicios.tfAbordarEstacion.setText("");
        this.moduloServicios.cbAbordarRuta.removeAllItems();
        this.moduloServicios.tfAbordarTarjeta.setText("");
    }
    
    public void consultarEstaciones()
    {
        ArrayList<Estacion> lista = new EstacionDAO().consultarEstaciones(true);
            
        if (lista != null)
        {
            this.moduloServicios.cbEstaciones.removeAllItems();

            for (int i = 0; i < lista.size(); i++) 
            {
                this.moduloServicios.cbEstaciones.addItem(lista.get(i).getNombre());
            }

            this.moduloServicios.cbEstaciones.setSelectedIndex(0);
        }
    }
    
    public void consultarRutasEstacion()
    {
        if (this.moduloServicios.cbEstaciones.getSelectedIndex() != -1)
        {
            String estacion = (String) this.moduloServicios.cbEstaciones.getSelectedItem();
            ArrayList<Ruta> lista = new RutaDAO().consultarRutas(estacion, true);

            if (lista != null)
            {
                DefaultTableModel model = (DefaultTableModel) this.moduloServicios.tRutas.getModel();
                model.setRowCount(lista.size());

                for (int i = 0; i < lista.size(); i++) 
                {
                    this.moduloServicios.tRutas.setValueAt(lista.get(i).getNombre(), i, 0);
                    this.moduloServicios.tRutas.setValueAt(lista.get(i).getDescripcion(), i, 1);
                }
            }
        }
    }
    
    public void seleccionarRuta()
    {
        int row = this.moduloServicios.tRutas.getSelectedRow();
        
        if(row != -1)
        {
            String ruta = (String) this.moduloServicios.tRutas.getValueAt(row, 0);
            verRecorrido(ruta);
        }
	else
	{
	    JOptionPane.showMessageDialog(moduloServicios, "Seleccione una Ruta.", "Error", JOptionPane.ERROR_MESSAGE);
	}
    }
    
    public void consultarOrigen()
    {
        ArrayList<Estacion> lista = new EstacionDAO().consultarEstaciones(true);
            
        if (lista != null)
        {
            this.moduloServicios.cbOrigen.removeAllItems();

            for (int i = 0; i < lista.size(); i++) 
            {
                this.moduloServicios.cbOrigen.addItem(lista.get(i).getNombre());
            }

            this.moduloServicios.cbOrigen.setSelectedIndex(0);
        }
    }
    
    public void consultarDestinos()
    {
        if (this.moduloServicios.cbOrigen.getSelectedIndex() != -1)
        {
            String origen = (String) this.moduloServicios.cbOrigen.getSelectedItem();
            ArrayList<Estacion> lista = new EstacionDAO().consultarDestinos(origen);

            if (lista != null)
            {
                this.moduloServicios.cbDestino.removeAllItems();

                for (int i = 0; i < lista.size(); i++) 
                {
                    if (!lista.get(i).getNombre().equals(origen))
                    {
                        this.moduloServicios.cbDestino.addItem(lista.get(i).getNombre());
                    }
                }

                this.moduloServicios.cbDestino.setSelectedIndex(0);
            }
        }
    }
    
    public void consultarRutasSugeridas()
    {
        if (this.moduloServicios.cbDestino.getSelectedIndex() != -1)
        {
            String origen = (String) this.moduloServicios.cbOrigen.getSelectedItem();
            String destino = (String) this.moduloServicios.cbDestino.getSelectedItem();
            ArrayList<Ruta> lista = new RutaDAO().consultarRutasOrigenDestino(origen, destino);

            if (lista != null)
            {
                DefaultTableModel model = (DefaultTableModel) this.moduloServicios.tRutasSugeridas.getModel();
                model.setRowCount(lista.size());

                for (int i = 0; i < lista.size(); i++) 
                {
                    this.moduloServicios.tRutasSugeridas.setValueAt(lista.get(i).getNombre(), i, 0);
                    this.moduloServicios.tRutasSugeridas.setValueAt(lista.get(i).getDescripcion(), i, 1);
                }
            }
        }
    }
    
    public void seleccionarRutaSugerida()
    {
        int row = this.moduloServicios.tRutasSugeridas.getSelectedRow();
        
        if(row != -1)
        {
            String ruta = (String) this.moduloServicios.tRutasSugeridas.getValueAt(row, 0);
            verRecorrido(ruta);
        }
        else
        {
            JOptionPane.showMessageDialog(moduloServicios, "Seleccione una Ruta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
