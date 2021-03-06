/**
 * ********************************************
 * Autor: Camilo Ruiz Casanova - 1324486
 * Autor: Miguel Angel Lopez - 1326691
 * Autor: Andres Felipe Polanco - 1324539
 * Fecha: 03-oct-2015
 * Nombre del Archivo: ModuloEstaciones.java
 * Plan: Ingeniería de Sistemas - 3743
 * Institución Educativa: Universidad del Valle (Cali - Colombia)
 * *********************************************
 */

package Vista;

public class ModuloEstaciones extends javax.swing.JFrame 
{
    /**
     * Creates new form ModuloEstaciones
     */
    public ModuloEstaciones() 
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        fSelDirCrear = new javax.swing.JFrame();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tDirCrear = new javax.swing.JTable();
        bSeleccionarCrear = new javax.swing.JButton();
        bCancelarCrear = new javax.swing.JButton();
        fSelDirModificar = new javax.swing.JFrame();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tDirModificar = new javax.swing.JTable();
        bSeleccionarModificar = new javax.swing.JButton();
        bCancelarModificar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfCrearNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfCrearUbicacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfCrearDirector = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bCrearEstacion = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        bSeleccionCrear = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfModificarNombre = new javax.swing.JTextField();
        tfModificarUbicacion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfModificarDirector = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bModificarEstacion = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbModificarEstado = new javax.swing.JComboBox<>();
        bSeleccionModificar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        bModificarBuscar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        tfModificarBuscar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        tfEliminarNombre = new javax.swing.JTextField();
        tfEliminarUbicacion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfEliminarDirector = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        bEliminarEstacion = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tfEliminarBuscar = new javax.swing.JTextField();
        bEliminarBuscar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tEstaciones = new javax.swing.JTable();
        bActualizar = new javax.swing.JButton();

        fSelDirCrear.setTitle("Seleccionar Jefe");
        fSelDirCrear.setMinimumSize(new java.awt.Dimension(600, 300));
        fSelDirCrear.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Seleccionar Director");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        fSelDirCrear.getContentPane().add(jLabel47, gridBagConstraints);

        tDirCrear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tDirCrear.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CARGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tDirCrear.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tDirCrear);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        fSelDirCrear.getContentPane().add(jScrollPane2, gridBagConstraints);

        bSeleccionarCrear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bSeleccionarCrear.setText("Seleccionar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 5);
        fSelDirCrear.getContentPane().add(bSeleccionarCrear, gridBagConstraints);

        bCancelarCrear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bCancelarCrear.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 10);
        fSelDirCrear.getContentPane().add(bCancelarCrear, gridBagConstraints);

        fSelDirModificar.setTitle("Seleccionar Jefe");
        fSelDirModificar.setMinimumSize(new java.awt.Dimension(600, 300));
        fSelDirModificar.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Seleccionar Director");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        fSelDirModificar.getContentPane().add(jLabel48, gridBagConstraints);

        tDirModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tDirModificar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CARGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tDirModificar.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tDirModificar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        fSelDirModificar.getContentPane().add(jScrollPane3, gridBagConstraints);

        bSeleccionarModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bSeleccionarModificar.setText("Seleccionar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 5);
        fSelDirModificar.getContentPane().add(bSeleccionarModificar, gridBagConstraints);

        bCancelarModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bCancelarModificar.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 10);
        fSelDirModificar.getContentPane().add(bCancelarModificar, gridBagConstraints);

        setMinimumSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nombre de la Estacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        tfCrearNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel1.add(tfCrearNombre, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ubicacion de la Estacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        tfCrearUbicacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel1.add(tfCrearUbicacion, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Director de la Estacion (ID):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        tfCrearDirector.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel1.add(tfCrearDirector, gridBagConstraints);

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Campos Obligatorios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        bCrearEstacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bCrearEstacion.setText("Crear Estacion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(bCrearEstacion, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jSeparator1, gridBagConstraints);

        bSeleccionCrear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bSeleccionCrear.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        jPanel1.add(bSeleccionCrear, gridBagConstraints);

        jTabbedPane1.addTab("Crear Estacion", jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Nombre de la Estacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 0);
        jPanel2.add(jLabel9, gridBagConstraints);

        tfModificarNombre.setEditable(false);
        tfModificarNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel2.add(tfModificarNombre, gridBagConstraints);

        tfModificarUbicacion.setEditable(false);
        tfModificarUbicacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel2.add(tfModificarUbicacion, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Ubicacion de la Estacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(jLabel10, gridBagConstraints);

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        jPanel2.add(jLabel11, gridBagConstraints);

        tfModificarDirector.setEditable(false);
        tfModificarDirector.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel2.add(tfModificarDirector, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Director de la Estacion (ID):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(jLabel12, gridBagConstraints);

        jLabel13.setText("Campos Obligatorios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel2.add(jLabel13, gridBagConstraints);

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel2.add(jLabel14, gridBagConstraints);

        bModificarEstacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bModificarEstacion.setText("Modificar Estacion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel2.add(bModificarEstacion, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jSeparator2, gridBagConstraints);

        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        jPanel2.add(jLabel20, gridBagConstraints);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Estado de la Estacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel2.add(jLabel23, gridBagConstraints);

        cbModificarEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbModificarEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cbModificarEstado.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel2.add(cbModificarEstado, gridBagConstraints);

        bSeleccionModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bSeleccionModificar.setText("...");
        bSeleccionModificar.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        jPanel2.add(bSeleccionModificar, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        bModificarBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bModificarBuscar.setText("Buscar Estacion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 10);
        jPanel5.add(bModificarBuscar, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Buscar Estacion (Nombre):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        jPanel5.add(jLabel15, gridBagConstraints);

        tfModificarBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 5);
        jPanel5.add(tfModificarBuscar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel5, gridBagConstraints);

        jTabbedPane1.addTab("Modificar Estacion", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        tfEliminarNombre.setEditable(false);
        tfEliminarNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel3.add(tfEliminarNombre, gridBagConstraints);

        tfEliminarUbicacion.setEditable(false);
        tfEliminarUbicacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel3.add(tfEliminarUbicacion, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Ubicacion de la Estacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        jPanel3.add(jLabel16, gridBagConstraints);

        tfEliminarDirector.setEditable(false);
        tfEliminarDirector.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanel3.add(tfEliminarDirector, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Director de la Estacion (ID):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        jPanel3.add(jLabel18, gridBagConstraints);

        bEliminarEstacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bEliminarEstacion.setText("Eliminar Estacion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 10, 10);
        jPanel3.add(bEliminarEstacion, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Nombre de la Estacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        jPanel3.add(jLabel21, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Buscar Estacion (Nombre):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        jPanel3.add(jLabel22, gridBagConstraints);

        tfEliminarBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 5);
        jPanel3.add(tfEliminarBuscar, gridBagConstraints);

        bEliminarBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bEliminarBuscar.setText("Buscar Estacion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 10);
        jPanel3.add(bEliminarBuscar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jSeparator3, gridBagConstraints);

        jTabbedPane1.addTab("Eliminar Estacion", jPanel3);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        tEstaciones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tEstaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "UBICACION", "DIRECTOR", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tEstaciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tEstaciones);
        tEstaciones.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        jPanel4.add(jScrollPane1, gridBagConstraints);

        bActualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bActualizar.setText("Actualizar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        jPanel4.add(bActualizar, gridBagConstraints);

        jTabbedPane1.addTab("Consultar Estacion", jPanel4);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bActualizar;
    public javax.swing.JButton bCancelarCrear;
    public javax.swing.JButton bCancelarModificar;
    public javax.swing.JButton bCrearEstacion;
    public javax.swing.JButton bEliminarBuscar;
    public javax.swing.JButton bEliminarEstacion;
    public javax.swing.JButton bModificarBuscar;
    public javax.swing.JButton bModificarEstacion;
    public javax.swing.JButton bSeleccionCrear;
    public javax.swing.JButton bSeleccionModificar;
    public javax.swing.JButton bSeleccionarCrear;
    public javax.swing.JButton bSeleccionarModificar;
    public javax.swing.JComboBox<String> cbModificarEstado;
    public javax.swing.JFrame fSelDirCrear;
    public javax.swing.JFrame fSelDirModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable tDirCrear;
    public javax.swing.JTable tDirModificar;
    public javax.swing.JTable tEstaciones;
    public javax.swing.JTextField tfCrearDirector;
    public javax.swing.JTextField tfCrearNombre;
    public javax.swing.JTextField tfCrearUbicacion;
    public javax.swing.JTextField tfEliminarBuscar;
    public javax.swing.JTextField tfEliminarDirector;
    public javax.swing.JTextField tfEliminarNombre;
    public javax.swing.JTextField tfEliminarUbicacion;
    public javax.swing.JTextField tfModificarBuscar;
    public javax.swing.JTextField tfModificarDirector;
    public javax.swing.JTextField tfModificarNombre;
    public javax.swing.JTextField tfModificarUbicacion;
    // End of variables declaration//GEN-END:variables
}
