/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.ModuloEstaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Camilo Ruiz Casanova
 */
public class ModuloEstaciones_Eventos 
{
    private ModuloEstaciones moduloEstaciones;
    
    public ModuloEstaciones_Eventos(final ModuloEstaciones moduloEstaciones)
    {
        this.moduloEstaciones = moduloEstaciones;
        
        this.moduloEstaciones.bCrearEstacion.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae) 
                {
                    
                }                
            }
        );
    }
}
