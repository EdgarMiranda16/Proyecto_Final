/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vistas.secundarias;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javax.swing.table.DefaultTableModel;
import modelos.DetalleVenta;

/**
 *
 * @author kei40
 */
public class VistaReporteVenta extends javax.swing.JDialog {

    DefaultTableModel modelo;
    List<DetalleVenta> registros = new ArrayList<>();

    /**
     * Creates new form VistaUsuario
     */
    public VistaReporteVenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.modelo = (DefaultTableModel) data.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnExportar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        data = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Operaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        btnExportar.setBackground(new java.awt.Color(0, 153, 51));
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("Exportar");

        btnCerrar.setBackground(new java.awt.Color(204, 0, 0));
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportar)
                    .addComponent(btnCerrar))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Registros de Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel5.setText("Buscar");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Producto", "Precio", "Cantidad", "Total", "Fecha Venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(data);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String buscar = String.valueOf(this.txtBuscar.getText());

        Predicate<DetalleVenta> porNombres = e -> e.getVenta().getCliente().getNombres()
                .toLowerCase()
                .contains(buscar.toLowerCase());

        Predicate<DetalleVenta> porApellidos = e -> e.getVenta().getCliente().getApellidos()
                .toLowerCase()
                .contains(buscar.toLowerCase());

        Predicate<DetalleVenta> porCliente = e -> e.getVenta().getCliente().getNombres()
                .concat(" ")
                .concat(e.getVenta().getCliente().getApellidos())
                .toLowerCase()
                .contains(buscar.toLowerCase());

        Predicate<DetalleVenta> porClienteReverso = e -> e.getVenta().getCliente().getApellidos()
                .concat(" ")
                .concat(e.getVenta().getCliente().getNombres())
                .toLowerCase()
                .contains(buscar.toLowerCase());

        Predicate<DetalleVenta> porProducto = e -> e.getProducto().getNombreProducto()
                .toLowerCase()
                .contains(buscar.toLowerCase());

        List<DetalleVenta> filtrados = this.registros.stream()
                .filter(porNombres.or(porApellidos)
                        .or(porCliente)
                        .or(porClienteReverso)
                        .or(porProducto))
                .toList();

        this.modelo.setRowCount(0);
        filtrados.forEach(e -> {
            this.modelo.addRow(new String[]{e.getVenta().getCliente().getNombres().concat(" ").concat(e.getVenta().getCliente().getApellidos()),
                e.getProducto().getNombreProducto(),
                String.valueOf(e.getPrecioUnitario()),
                String.valueOf(e.getCantidad()),
                String.valueOf(e.getSubTotal()),
                e.getVenta().getFechaVenta().toString()});
        });
    }//GEN-LAST:event_txtBuscarKeyReleased

    public void pintar(List<DetalleVenta> registros) {
        this.registros = registros;
        this.modelo.setRowCount(0);
        registros.forEach(e -> {
            this.modelo.addRow(new String[]{e.getVenta().getCliente().getNombres().concat(" ").concat(e.getVenta().getCliente().getApellidos()),
                e.getProducto().getNombreProducto(),
                String.valueOf(e.getPrecioUnitario()),
                String.valueOf(e.getCantidad()),
                String.valueOf(e.getSubTotal()),
                e.getVenta().getFechaVenta().toString()});
        });
    }

    public Predicate<DetalleVenta> exportar() {
        String buscar = String.valueOf(this.txtBuscar.getText());

        Predicate<DetalleVenta> porNombres = e -> e.getVenta().getCliente().getNombres()
                .toLowerCase()
                .contains(buscar.toLowerCase());

        Predicate<DetalleVenta> porApellidos = e -> e.getVenta().getCliente().getApellidos()
                .toLowerCase()
                .contains(buscar.toLowerCase());

        Predicate<DetalleVenta> porCliente = e -> e.getVenta().getCliente().getNombres()
                .concat(" ")
                .concat(e.getVenta().getCliente().getApellidos())
                .toLowerCase()
                .contains(buscar.toLowerCase());

        Predicate<DetalleVenta> porClienteReverso = e -> e.getVenta().getCliente().getApellidos()
                .concat(" ")
                .concat(e.getVenta().getCliente().getNombres())
                .toLowerCase()
                .contains(buscar.toLowerCase());

        Predicate<DetalleVenta> porProducto = e -> e.getProducto().getNombreProducto()
                .toLowerCase()
                .contains(buscar.toLowerCase());

        return porNombres.or(porApellidos)
                .or(porCliente)
                .or(porClienteReverso)
                .or(porProducto);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnExportar;
    private javax.swing.JTable data;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
