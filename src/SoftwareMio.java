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

import Vista.MenuPrincipal;
import javax.swing.JOptionPane;


public class SoftwareMio
{
    public static void main(String[] args) 
    {
        // setLookAndFeel();
        JOptionPane.showMessageDialog(null, "Empiecen pues.");
        JOptionPane.showMessageDialog(null, "Miguel dejá la farándula");
        
        MenuPrincipal menuPrincipal = new MenuPrincipal();        
    }
    
    // POR FAVOR DEJAR ESTO, ES PARA QUE SE VEA BONITO :3
    public static void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getClassName());
                if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {   
                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   break;
                } 
            }
        } catch(Exception e) {
          System.out.println("Error setting native LAF: " + e);
        }
    }
}
