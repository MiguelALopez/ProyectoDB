
import javax.swing.JOptionPane;

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

public class SoftwareMio
{
    public static void main(String[] args) 
    {
        JOptionPane.showMessageDialog(null, "Empiecen pues.");
        
        Interfaz.Interfaz interfaz = new Interfaz.Interfaz();
        interfaz.setLocationRelativeTo(null);
        interfaz.setVisible(true);
    }
}
