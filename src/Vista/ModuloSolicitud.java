/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author AndresFelipe
 */
public class ModuloSolicitud extends javax.swing.JFrame
{

    /**
     * Creates new form ModuloTarjeta
     */
    public ModuloSolicitud()
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
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        fListar = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tSeleccionar = new javax.swing.JTable();
        bListarCancelar = new javax.swing.JButton();
        bListarSeleccionar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfGenMotivo = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        bGenCerrar = new javax.swing.JButton();
        bGenAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taGenDescripcion = new javax.swing.JTextArea();
        tfGenFecha = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        tfGenPasajero = new javax.swing.JTextField();
        bPasajero = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        bEstacion = new javax.swing.JButton();
        tfGenEstacion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        tfConFecha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfConEstacion = new javax.swing.JTextField();
        tfConPasajero = new javax.swing.JTextField();
        tfConMotivo = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        bConResponder = new javax.swing.JButton();
        bConCerrar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        taConDescripcion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        bConBuscar = new javax.swing.JButton();
        tfConNumero = new javax.swing.JTextField();

        fListar.setMaximumSize(null);
        fListar.setMinimumSize(new java.awt.Dimension(400, 300));
        fListar.setPreferredSize(new java.awt.Dimension(400, 300));
        fListar.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        tSeleccionar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Nombre", "Ubicacion", "Estado"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tSeleccionar.setMaximumSize(new java.awt.Dimension(60, 64));
        tSeleccionar.setPreferredSize(new java.awt.Dimension(60, 64));
        jScrollPane1.setViewportView(tSeleccionar);
        if (tSeleccionar.getColumnModel().getColumnCount() > 0)
        {
            tSeleccionar.getColumnModel().getColumn(0).setHeaderValue("Nombre");
            tSeleccionar.getColumnModel().getColumn(1).setHeaderValue("Ubicacion");
            tSeleccionar.getColumnModel().getColumn(2).setHeaderValue("Estado");
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jScrollPane1, gridBagConstraints);

        bListarCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bListarCancelar.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel5.add(bListarCancelar, gridBagConstraints);

        bListarSeleccionar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bListarSeleccionar.setText("Seleccionar");
        bListarSeleccionar.setMaximumSize(new java.awt.Dimension(85, 25));
        bListarSeleccionar.setMinimumSize(new java.awt.Dimension(85, 25));
        bListarSeleccionar.setPreferredSize(new java.awt.Dimension(85, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel5.add(bListarSeleccionar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        fListar.getContentPane().add(jPanel5, gridBagConstraints);

        setMinimumSize(new java.awt.Dimension(500, 330));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Estación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Pasajero");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Motivo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Descripción");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        jPanel1.add(jLabel5, gridBagConstraints);

        tfGenMotivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel1.add(tfGenMotivo, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        bGenCerrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bGenCerrar.setText("CERRAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 10);
        jPanel6.add(bGenCerrar, gridBagConstraints);

        bGenAgregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bGenAgregar.setText("AGREGAR");
        bGenAgregar.setMaximumSize(new java.awt.Dimension(85, 25));
        bGenAgregar.setMinimumSize(new java.awt.Dimension(85, 25));
        bGenAgregar.setPreferredSize(new java.awt.Dimension(85, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 5);
        jPanel6.add(bGenAgregar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel6, gridBagConstraints);

        taGenDescripcion.setColumns(20);
        taGenDescripcion.setRows(5);
        jScrollPane2.setViewportView(taGenDescripcion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel1.add(jScrollPane2, gridBagConstraints);

        tfGenFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfGenFecha.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        jPanel1.add(tfGenFecha, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        tfGenPasajero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfGenPasajero.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(tfGenPasajero, gridBagConstraints);

        bPasajero.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        jPanel4.add(bPasajero, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        bEstacion.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        jPanel8.add(bEstacion, gridBagConstraints);

        tfGenEstacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfGenEstacion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(tfGenEstacion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel8, gridBagConstraints);

        jTabbedPane1.addTab("Generar", jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        tfConFecha.setColumns(8);
        tfConFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfConFecha.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(tfConFecha, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Estación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Pasajero");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Motivo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        jPanel2.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Descripción");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 5);
        jPanel2.add(jLabel11, gridBagConstraints);

        tfConEstacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfConEstacion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(tfConEstacion, gridBagConstraints);

        tfConPasajero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfConPasajero.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(tfConPasajero, gridBagConstraints);

        tfConMotivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfConMotivo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(tfConMotivo, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        bConResponder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bConResponder.setText("RESPONDER");
        bConResponder.setMaximumSize(new java.awt.Dimension(85, 25));
        bConResponder.setMinimumSize(new java.awt.Dimension(85, 25));
        bConResponder.setPreferredSize(new java.awt.Dimension(85, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 5);
        jPanel7.add(bConResponder, gridBagConstraints);

        bConCerrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bConCerrar.setText("CERRAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 10);
        jPanel7.add(bConCerrar, gridBagConstraints);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("ACTUALIZAR");
        jButton6.setMaximumSize(new java.awt.Dimension(85, 25));
        jButton6.setMinimumSize(new java.awt.Dimension(85, 25));
        jButton6.setPreferredSize(new java.awt.Dimension(85, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        jPanel7.add(jButton6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel7, gridBagConstraints);

        taConDescripcion.setColumns(20);
        taConDescripcion.setRows(5);
        jScrollPane3.setViewportView(taConDescripcion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(jScrollPane3, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Numero");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        bConBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bConBuscar.setText("Buscar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        jPanel3.add(bConBuscar, gridBagConstraints);

        tfConNumero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfConNumero.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        jPanel3.add(tfConNumero, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jTabbedPane1.addTab("Consultar", jPanel2);

        getContentPane().add(jTabbedPane1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ModuloSolicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ModuloSolicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ModuloSolicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ModuloSolicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ModuloSolicitud().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bConBuscar;
    public javax.swing.JButton bConCerrar;
    public javax.swing.JButton bConResponder;
    public javax.swing.JButton bEstacion;
    public javax.swing.JButton bGenAgregar;
    public javax.swing.JButton bGenCerrar;
    public javax.swing.JButton bListarCancelar;
    public javax.swing.JButton bListarSeleccionar;
    public javax.swing.JButton bPasajero;
    public javax.swing.JFrame fListar;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable tSeleccionar;
    public javax.swing.JTextArea taConDescripcion;
    public javax.swing.JTextArea taGenDescripcion;
    public javax.swing.JTextField tfConEstacion;
    public javax.swing.JTextField tfConFecha;
    public javax.swing.JTextField tfConMotivo;
    public javax.swing.JTextField tfConNumero;
    public javax.swing.JTextField tfConPasajero;
    public javax.swing.JTextField tfGenEstacion;
    public javax.swing.JTextField tfGenFecha;
    public javax.swing.JTextField tfGenMotivo;
    public javax.swing.JTextField tfGenPasajero;
    // End of variables declaration//GEN-END:variables
}
