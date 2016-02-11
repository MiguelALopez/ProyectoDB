/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estacion;
import Modelo.Pasajero;
import Modelo.ReportesDAO;
import Modelo.Ruta;
import Modelo.Solicitud;
import Vista.ModuloReportes;
import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.VerticalAlignment;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class ModuloReportes_Eventos 
{
    private final ModuloReportes moduloReportes;
    
    public ModuloReportes_Eventos(final ModuloReportes moduloReportes)
    {
        this.moduloReportes = moduloReportes;
        
        this.moduloReportes.rbReporte1.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    moduloReportes.tfFecha.setText("");
                    moduloReportes.tfPasajeroID.setText("");
                    moduloReportes.tfFecha.setEditable(false);
                    moduloReportes.tfPasajeroID.setEditable(false);
                }                
            }
        );
        
        this.moduloReportes.rbReporte2.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    moduloReportes.tfFecha.setText("");
                    moduloReportes.tfPasajeroID.setText("");
                    moduloReportes.tfFecha.setEditable(true);
                    moduloReportes.tfPasajeroID.setEditable(false);
                }                
            }
        );
        
        this.moduloReportes.rbReporte3.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    moduloReportes.tfFecha.setText("");
                    moduloReportes.tfPasajeroID.setText("");
                    moduloReportes.tfFecha.setEditable(true);
                    moduloReportes.tfPasajeroID.setEditable(false);
                }                
            }
        );
        
        this.moduloReportes.rbReporte4.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    moduloReportes.tfFecha.setText("");
                    moduloReportes.tfPasajeroID.setText("");
                    moduloReportes.tfFecha.setEditable(false);
                    moduloReportes.tfPasajeroID.setEditable(false);
                }                
            }
        );
        
        this.moduloReportes.rbReporte5.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    moduloReportes.tfFecha.setText("");
                    moduloReportes.tfPasajeroID.setText("");
                    moduloReportes.tfFecha.setEditable(false);
                    moduloReportes.tfPasajeroID.setEditable(false);
                }                
            }
        );
        
        this.moduloReportes.rbReporte6.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    moduloReportes.tfFecha.setText("");
                    moduloReportes.tfPasajeroID.setText("");
                    moduloReportes.tfFecha.setEditable(false);
                    moduloReportes.tfPasajeroID.setEditable(false);
                }                
            }
        );
        
        this.moduloReportes.rbReporte7.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    moduloReportes.tfFecha.setText("");
                    moduloReportes.tfPasajeroID.setText("");
                    moduloReportes.tfFecha.setEditable(false);
                    moduloReportes.tfPasajeroID.setEditable(true);
                }                
            }
        );
        
        this.moduloReportes.bGenerar.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    generarReporte();
                }                
            }
        );
    }
    
    public void generarReporte()
    {
        try
        {
            if (this.moduloReportes.rbReporte1.isSelected())
            {
                reporte1();
            }
            else if (this.moduloReportes.rbReporte2.isSelected())
            {
                String fecha = this.moduloReportes.tfFecha.getText();
                
                if (!fecha.isEmpty())
                {
                    reporte2(fecha);                    
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloReportes, "Ingrese una Fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (this.moduloReportes.rbReporte3.isSelected())
            {
                String fecha = this.moduloReportes.tfFecha.getText();
                
                if (!fecha.isEmpty())
                {
                    reporte3(fecha);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloReportes, "Ingrese una Fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (this.moduloReportes.rbReporte4.isSelected())
            {
                reporte4();
            }
            else if (this.moduloReportes.rbReporte5.isSelected())
            {
                reporte5();
            }
            else if (this.moduloReportes.rbReporte6.isSelected())
            {
                reporte6();
            }
            else if (this.moduloReportes.rbReporte7.isSelected())
            {
                String pasajero = this.moduloReportes.tfPasajeroID.getText();
                
                if (!pasajero.isEmpty())
                {
                    reporte7(pasajero);
                }
                else
                {
                    JOptionPane.showMessageDialog(moduloReportes, "Ingrese un ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ModuloReportes_Eventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reporte1() throws IOException
    {
        //margenes
        float margin = 10;

        //inicializacion del documento
        PDDocument doc = new PDDocument();
        PDPage page = agregarPagina(doc);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

        //inicializacion de la tabla
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        BaseTable table  = new BaseTable(yStart,yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);

        //fila de titulo
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Reporte 1 - Pasajeros del Sistema con sus numeros de Tarjetas Personalizadas");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(28);
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.setHeader(headerRow);

        //fila de los campos
        Row<PDPage> campos = table.createRow(15f);

        cell = campos.createCell((100 / 3), "ID", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);        

        cell = campos.createCell((100 / 3), "NOMBRE", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);
        
        cell = campos.createCell((100 / 3), "ID DE TARJETA", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        ArrayList<Pasajero> lista = new ReportesDAO().reporte1();
        
        Row<PDPage> row; 
        
        if (lista != null)
        {
            for (Pasajero p : lista) 
            {
                row = table.createRow(15f);

                cell = row.createCell((100 / 3), p.getId());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 3), p.getNombre());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 3), p.getTarjeta());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
            }
            
            row = table.createRow(15f);            
            cell = row.createCell(100, "<< " + lista.size() + " filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }
        else
        {
            row = table.createRow(15f);
            cell = row.createCell(100, "<< 0 filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }

        table.draw();

        //cerrar flujo y guardar pdf
        File file = new File("Reporte1.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        doc.save(file);
        doc.close();
        
        Desktop.getDesktop().open(file);
    }
    
    public void reporte2(String fecha) throws IOException
    {
        //margenes
        float margin = 10;

        //inicializacion del documento
        PDDocument doc = new PDDocument();
        PDPage page = agregarPagina(doc);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

        //inicializacion de la tabla
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        BaseTable table  = new BaseTable(yStart,yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);

        //fila de titulo
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Reporte 2 - Total de Pasajeros movilizados por Fechas y Rutas");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(28);
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.setHeader(headerRow);
        
        Row<PDPage> row = table.createRow(15f);
        cell = row.createCell(20, "FECHA");
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell = row.createCell(80, fecha);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);

        //fila de los campos
        Row<PDPage> campos = table.createRow(15f);

        cell = campos.createCell((100 / 2), "RUTA", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        cell = campos.createCell((100 / 2), "PASAJEROS MOVILIZADOS", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        ArrayList<Pair> lista = new ReportesDAO().reporte2(fecha);
        
        if (lista != null)
        {
            for (Pair p : lista) 
            {
                row = table.createRow(15f);

                cell = row.createCell((100 / 2), String.valueOf(p.getKey()));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 2), String.valueOf(p.getValue()));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
            }
            
            row = table.createRow(15f);            
            cell = row.createCell(100, "<< " + lista.size() + " filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }
        else
        {
            row = table.createRow(15f);
            cell = row.createCell(100, "<< 0 filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }

        table.draw();

        //cerrar flujo y guardar pdf
        File file = new File("Reporte2.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        doc.save(file);
        doc.close();
        
        Desktop.getDesktop().open(file);
    }
    
    public void reporte3(String fecha) throws IOException
    {
        //margenes
        float margin = 10;

        //inicializacion del documento
        PDDocument doc = new PDDocument();
        PDPage page = agregarPagina(doc);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

        //inicializacion de la tabla
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        BaseTable table  = new BaseTable(yStart,yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);

        //fila de titulo
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Reporte 3 - Valor en Tarjetas vendidas por Estaciones y Fechas");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(28);
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.setHeader(headerRow);
        
        Row<PDPage> row = table.createRow(15f);
        cell = row.createCell(20, "FECHA");
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell = row.createCell(80, fecha);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);

        //fila de los campos
        Row<PDPage> campos = table.createRow(15f);

        cell = campos.createCell((100 / 2), "ESTACION", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        cell = campos.createCell((100 / 2), "VALOR", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        ArrayList<Pair> lista = new ReportesDAO().reporte3(fecha);
        
        if (lista != null)
        {
            for (Pair p : lista) 
            {
                row = table.createRow(15f);

                cell = row.createCell((100 / 2), String.valueOf(p.getKey()));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 2), String.valueOf(p.getValue()));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
            }
            
            row = table.createRow(15f);            
            cell = row.createCell(100, "<< " + lista.size() + " filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }
        else
        {
            row = table.createRow(15f);
            cell = row.createCell(100, "<< 0 filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }

        table.draw();

        //cerrar flujo y guardar pdf
        File file = new File("Reporte3.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        doc.save(file);
        doc.close();
        
        Desktop.getDesktop().open(file);
    }
    
    public void reporte4() throws IOException
    {
        //margenes
        float margin = 10;

        //inicializacion del documento
        PDDocument doc = new PDDocument();
        PDPage page = agregarPagina(doc);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

        //inicializacion de la tabla
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        BaseTable table  = new BaseTable(yStart,yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);

        //fila de titulo
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Reporte 4 - Listado de Buses Arrticulados del Sistema con sus Conductores asociados");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(28);
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.setHeader(headerRow);
        
        Row<PDPage> row;

        //fila de los campos
        Row<PDPage> campos = table.createRow(15f);

        cell = campos.createCell((100 / 4), "SERIAL DEL BUS", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        cell = campos.createCell((100 / 4), "RUTA DEL BUS", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);
        
        cell = campos.createCell((100 / 4), "ID DEL CONDUCTOR", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);
        
        cell = campos.createCell((100 / 4), "NOMBRE DEL CONDUCTOR", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        ArrayList<ArrayList<String>> lista = new ReportesDAO().reporte4();
        //ArrayList<ArrayList<String>> lista = new ArrayList();
        
        if (lista != null)
        {
            for (ArrayList<String> p : lista) 
            {
                row = table.createRow(15f);

                cell = row.createCell((100 / 4), p.get(0));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 4), p.get(1));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
                
                cell = row.createCell((100 / 4), p.get(2));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
                
                cell = row.createCell((100 / 4), p.get(3));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
            }
            
            row = table.createRow(15f);            
            cell = row.createCell(100, "<< " + lista.size() + " filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }
        else
        {
            row = table.createRow(15f);
            cell = row.createCell(100, "<< 0 filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }

        table.draw();

        //cerrar flujo y guardar pdf
        File file = new File("Reporte4.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        doc.save(file);
        doc.close();
        
        Desktop.getDesktop().open(file);
    }
    
    public void reporte5() throws IOException
    {
        //margenes
        float margin = 10;

        //inicializacion del documento
        PDDocument doc = new PDDocument();
        PDPage page = agregarPagina(doc);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

        //inicializacion de la tabla
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        BaseTable table  = new BaseTable(yStart,yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);

        //fila de titulo
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Reporte 5.1 - Listado de todas las Rutas del Sistema");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(28);
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.setHeader(headerRow);
        
        Row<PDPage> row;

        //fila de los campos
        Row<PDPage> campos = table.createRow(15f);

        cell = campos.createCell((100 / 2), "NOMBRE", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        cell = campos.createCell((100 / 2), "DESCRIPCION", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        ArrayList<Ruta> lista = new ReportesDAO().reporte5_1();
        
        if (lista != null)
        {
            for (Ruta p : lista) 
            {
                row = table.createRow(15f);

                cell = row.createCell((100 / 2), p.getNombre());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 2), p.getDescripcion());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
            }
            
            row = table.createRow(15f);            
            cell = row.createCell(100, "<< " + lista.size() + " filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }
        else
        {
            row = table.createRow(15f);
            cell = row.createCell(100, "<< 0 filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }

        table.draw();

        //cerrar flujo y guardar pdf
        File file = new File("Reporte5_1.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        doc.save(file);
        doc.close();
        
        Desktop.getDesktop().open(file);

        //inicializacion del documento
        doc = new PDDocument();
        page = agregarPagina(doc);
        yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

        //inicializacion de la tabla
        tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        yStart = yStartNewPage;
        table  = new BaseTable(yStart,yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);

        //fila de titulo
        headerRow = table.createRow(15f);
        cell = headerRow.createCell(100, "Reporte 5.2 - Listado de todas las Estaciones del Sistema");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(28);
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.setHeader(headerRow);

        //fila de los campos
        campos = table.createRow(15f);

        cell = campos.createCell((100 / 3), "NOMBRE", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        cell = campos.createCell((100 / 3), "UBICACION", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);
        
        cell = campos.createCell((100 / 3), "DIRECTOR", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        ArrayList<Estacion> lista2 = new ReportesDAO().reporte5_2();
        
        if (lista != null)
        {
            for (Estacion p : lista2) 
            {
                row = table.createRow(15f);

                cell = row.createCell((100 / 3), p.getNombre());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 3), p.getUbicacion());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
                
                cell = row.createCell((100 / 3), p.getDirector());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
            }
            
            row = table.createRow(15f);            
            cell = row.createCell(100, "<< " + lista2.size() + " filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }
        else
        {
            row = table.createRow(15f);
            cell = row.createCell(100, "<< 0 filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }

        table.draw();

        //cerrar flujo y guardar pdf
        file = new File("Reporte5_2.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        doc.save(file);
        doc.close();
        
        Desktop.getDesktop().open(file);
    }
    
    public void reporte6() throws IOException
    {
        //margenes
        float margin = 10;

        //inicializacion del documento
        PDDocument doc = new PDDocument();
        PDPage page = agregarPagina(doc);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

        //inicializacion de la tabla
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        BaseTable table  = new BaseTable(yStart,yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);

        //fila de titulo
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Reporte 6 - Rutas con mayor demanda de Pasajeros");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(28);
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.setHeader(headerRow);
        
        Row<PDPage> row;

        //fila de los campos
        Row<PDPage> campos = table.createRow(15f);

        cell = campos.createCell((100 / 2), "RUTA", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        cell = campos.createCell((100 / 2), "DEMANDA", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        ArrayList<Pair> lista = new ReportesDAO().reporte6();
        
        if (lista != null)
        {
            for (Pair p : lista) 
            {
                row = table.createRow(15f);

                cell = row.createCell((100 / 2), String.valueOf(p.getKey()));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 2), String.valueOf(p.getValue()));
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
            }
            
            row = table.createRow(15f);            
            cell = row.createCell(100, "<< " + lista.size() + " filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }
        else
        {
            row = table.createRow(15f);
            cell = row.createCell(100, "<< 0 filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }

        table.draw();

        //cerrar flujo y guardar pdf
        File file = new File("Reporte6.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        doc.save(file);
        doc.close();
        
        Desktop.getDesktop().open(file);
    }
    
    public void reporte7(String pasajero) throws IOException
    {
        //margenes
        float margin = 10;

        //inicializacion del documento
        PDDocument doc = new PDDocument();
        PDPage page = agregarPagina(doc);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

        //inicializacion de la tabla
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        BaseTable table  = new BaseTable(yStart,yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, drawContent);

        //fila de titulo
        Row<PDPage> headerRow = table.createRow(15f);
        Cell<PDPage> cell = headerRow.createCell(100, "Reporte 7 - Reclamos asociados a un Pasajero");
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(28);
        cell.setFillColor(Color.BLACK);
        cell.setTextColor(Color.WHITE);

        table.setHeader(headerRow);
        
        Row<PDPage> row = table.createRow(15f);
        cell = row.createCell(20, "PASAJERO");
        cell.setFontSize(12);
        cell = row.createCell(80, pasajero);
        cell.setFontSize(12);

        //fila de los campos
        Row<PDPage> campos = table.createRow(15f);

        cell = campos.createCell((100 / 4), "ID", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);        

        cell = campos.createCell((100 / 4), "MOTIVO", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);
        
        cell = campos.createCell((100 / 4), "FECHA", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);
        
        cell = campos.createCell((100 / 4), "ESTADO", HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setFont(PDType1Font.HELVETICA);
        cell.setFontSize(12);
        cell.setFillColor(Color.LIGHT_GRAY);

        ArrayList<Solicitud> lista = new ReportesDAO().reporte7(pasajero);
        
        if (lista != null)
        {
            for (Solicitud p : lista) 
            {
                row = table.createRow(15f);

                cell = row.createCell((100 / 4), p.getId());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 4), p.getMotivo());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);

                cell = row.createCell((100 / 4), p.getFecha());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
                
                cell = row.createCell((100 / 4), p.getEstado());
                cell.setFont(PDType1Font.HELVETICA);
                cell.setFontSize(11);
            }
            
            row = table.createRow(15f);            
            cell = row.createCell(100, "<< " + lista.size() + " filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }
        else
        {
            row = table.createRow(15f);
            cell = row.createCell(100, "<< 0 filas >>");
            cell.setFont(PDType1Font.HELVETICA);
            cell.setFontSize(11);
        }

        table.draw();

        //cerrar flujo y guardar pdf
        File file = new File("Reporte7.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        doc.save(file);
        doc.close();
        
        Desktop.getDesktop().open(file);
    }
       
    private static PDPage agregarPagina(PDDocument doc) 
    {
        PDPage page = new PDPage();
        doc.addPage(page);
        return page;
    }
}
