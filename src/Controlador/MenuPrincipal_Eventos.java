/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.MenuPrincipal;
import Vista.ModuloBuses;
import Vista.ModuloEmpleados;
import Vista.ModuloEstaciones;
import Vista.ModuloEstacionesRutas;
import Vista.ModuloReportes;
import Vista.ModuloRutas;
import Vista.ModuloTurnos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class MenuPrincipal_Eventos 
{
    private MenuPrincipal menuPrincipal;
    
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
    }
}
