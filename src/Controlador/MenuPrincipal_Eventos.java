/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: MenuPrincipal.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Controlador;

import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal_Eventos 
{
    private final MenuPrincipal menuPrincipal;
    
    public MenuPrincipal_Eventos(final MenuPrincipal menuPrincipal)
    {
        this.menuPrincipal = menuPrincipal;
        
        this.menuPrincipal.bEstaciones.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    ModuloEstaciones m = new ModuloEstaciones();
                    ModuloEstaciones_Eventos me = new ModuloEstaciones_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }                
            }
        );
        
        this.menuPrincipal.bRutas.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    ModuloRutas m = new ModuloRutas();
                    ModuloRutas_Eventos me = new ModuloRutas_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }                
            }
        );
        
        this.menuPrincipal.bBuses.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    ModuloBuses m = new ModuloBuses();
                    ModuloBuses_Eventos me = new ModuloBuses_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }                
            }
        );
        
        this.menuPrincipal.bEstacionRutas.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    ModuloEstacionesRutas m = new ModuloEstacionesRutas();
                    ModuloEstacionesRutas_Eventos me = new ModuloEstacionesRutas_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }                
            }
        );
        
        this.menuPrincipal.bEmpleados.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    ModuloEmpleados m = new ModuloEmpleados();
                    ModuloEmpleados_Eventos me = new ModuloEmpleados_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }                
            }
        );
        
        this.menuPrincipal.bTurnos.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    ModuloTurnos m = new ModuloTurnos();
                    ModuloTurnos_Eventos me = new ModuloTurnos_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }                
            }
        );

        this.menuPrincipal.bPasajeros.addActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    ModuloPasajeros m = new ModuloPasajeros();
                    ModuloPasajeros_Eventos me = new ModuloPasajeros_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }
            }
        );
        
        this.menuPrincipal.bReportes.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    ModuloReportes m = new ModuloReportes();
                    ModuloReportes_Eventos me = new ModuloReportes_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }                
            }
        );

        this.menuPrincipal.bTarjetas.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ModuloTarjetas m = new ModuloTarjetas();
                    ModuloTarjetas_Eventos me = new ModuloTarjetas_Eventos(m);
                    m.setLocationRelativeTo(null);
                    m.setVisible(true);
                }
            }
        );
        
        this.menuPrincipal.bSolicitudes.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ModuloSolicitud s = new ModuloSolicitud();
                    ModuloSolicitud_Eventos se = new ModuloSolicitud_Eventos(s);
                    s.setVisible(true);
                }
            }
        );
        
        this.menuPrincipal.bServicios.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ModuloServicios ms = new ModuloServicios();
                    ModuloServicios_Eventos mse = new ModuloServicios_Eventos(ms);
                    ms.setLocationRelativeTo(null);
                    ms.setVisible(true);
                }
            }
        );
    }
}
