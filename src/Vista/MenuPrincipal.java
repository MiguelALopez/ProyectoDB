/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;


/**
 *
 * @author Camilo Ruiz Casanova
 */
public class MenuPrincipal extends javax.swing.JFrame 
{    
    /**
     * Creates new form Interfaz
     */
    public MenuPrincipal() 
    {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bEstaciones = new javax.swing.JButton();
        bRutas = new javax.swing.JButton();
        bBuses = new javax.swing.JButton();
        bEstacionRutas = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        bEmpleados = new javax.swing.JButton();
        bTurnos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        bReportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SistemaMIO");
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        panelPrincipal.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA MIO");
        panelPrincipal.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.GridLayout(3, 2, 10, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gestion de Estaciones, Rutas y Buses", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        bEstaciones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bEstaciones.setText("Modulo de Estaciones");
        jPanel2.add(bEstaciones);

        bRutas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bRutas.setText("Modulo de Rutas");
        jPanel2.add(bRutas);

        bBuses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bBuses.setText("Modulo de Buses");
        jPanel2.add(bBuses);

        bEstacionRutas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bEstacionRutas.setText("Gestion de Rutas y Estaciones");
        jPanel2.add(bEstacionRutas);

        jPanel1.add(jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gestion de Empleados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel3.setLayout(new java.awt.GridLayout(2, 1, 0, 10));

        bEmpleados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bEmpleados.setText("Modulo de Empleados");
        jPanel3.add(bEmpleados);

        bTurnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bTurnos.setText("Modulo de Turnos");
        jPanel3.add(bTurnos);

        jPanel1.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "A", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "B", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.add(jPanel5);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "C", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.add(jPanel8);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reportes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel6.setLayout(new java.awt.GridLayout());

        bReportes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bReportes.setText("Modulo de Reportes");
        jPanel6.add(bReportes);

        jPanel1.add(jPanel6);

        panelPrincipal.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bBuses;
    public javax.swing.JButton bEmpleados;
    public javax.swing.JButton bEstacionRutas;
    public javax.swing.JButton bEstaciones;
    public javax.swing.JButton bReportes;
    public javax.swing.JButton bRutas;
    public javax.swing.JButton bTurnos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
