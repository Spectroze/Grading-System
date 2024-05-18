package curriculum_system;


import com.mysql.jdbc.PreparedStatement;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

/**
 *
 * @author Edmar
 */
public class DD4grades2ndsem extends javax.swing.JInternalFrame {

   
    
    Connection con;
    
    public DD4grades2ndsem() {
        initComponents();
        Connect ();
        updateGWAInDatabase();
        populateTable();
        TableActionEvent event=new TableActionEvent() {
            
    
      
            @Override
            public void onEdit(int row) {
    
}


             @Override
            public void onDelete(int row) {
    
}
          
             @Override
            public void onPrint(int row) {
    
}
        };
        table.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));

    }
        
        
    
    
     public void Connect(){
         try {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/curriculum_db","root", "12345");
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(add2ndGrades.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    
private void updateGWAInDatabase() {
    String querySelect = "SELECT fullname, IAS101, MS102, ITTrends100, SA101, ITElec1, MST100, VD101 FROM grades";
    String queryUpdate = "UPDATE grades SET GWA = ? WHERE fullname = ?";
    
    try (Statement stmtSelect = con.createStatement();
         ResultSet rs = stmtSelect.executeQuery(querySelect);
         PreparedStatement stmtUpdate = (PreparedStatement) con.prepareStatement(queryUpdate)) {
        
        while (rs.next()) {
            String fullname = rs.getString("fullname");
            double IAS101 = rs.getDouble("IAS101");
            double MS102 = rs.getDouble("MS102");
            double ITTrends100 = rs.getDouble("ITTrends100");
            double SA101 = rs.getDouble("SA101");
            double ITElec1 = rs.getDouble("ITElec1");
            double MST100 = rs.getDouble("MST100");
            double VD101 = rs.getDouble("VD101");

            // Calculate GWA
            double gwa = (IAS101 + MS102 + ITTrends100 + SA101 + ITElec1 + MST100 + VD101) / 7;

            // Update the GWA in the database
            stmtUpdate.setDouble(1, gwa);
            stmtUpdate.setString(2, fullname);
            stmtUpdate.executeUpdate();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    


private void populateTable() {
    try {
        String query = "SELECT fullname, IAS101, MS102, ITTrends100, SA101, ITElec1, MST100, VD101 FROM grades";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows

            // Format for displaying GWA with two decimal places
            DecimalFormat df = new DecimalFormat("#.##");

            // Iterate over the result set and add rows to the table model
            while (rs.next()) {
                String fullname = rs.getString("fullname");
                double IAS101 = rs.getDouble("IAS101");
                double MS102 = rs.getDouble("MS102");
                double ITTrends100 = rs.getDouble("ITTrends100");
                double SA101 = rs.getDouble("SA101");
                double ITElec1 = rs.getDouble("ITElec1");
                double MST100 = rs.getDouble("MST100");
                double VD101 = rs.getDouble("VD101");

                // Calculate GWA
                double GWA = (IAS101 + MS102 + ITTrends100 + SA101 + ITElec1 + MST100 + VD101) / 7;
                
                // Format GWA to display only two decimal places
                String formattedGWA = df.format(GWA);

                Object[] row = {
                    fullname,
                    IAS101,
                    MS102,
                    ITTrends100,
                    SA101,
                    ITElec1,
                    MST100,
                    VD101,
                    formattedGWA // Use the formatted GWA value
                };
                model.addRow(row);
            }
        }
    } catch (SQLException ex) {
        // Handle any SQL exceptions
        ex.printStackTrace();
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Add = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1128, 800));

        jPanel1.setBackground(new java.awt.Color(29, 6, 94));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("GRADES OF AP");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IT (2).png"))); // NOI18N

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Search ID No.");

        Add.setBackground(new java.awt.Color(255, 153, 0));
        Add.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/adds.png"))); // NOI18N
        Add.setText("ADD");
        Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AddMousePressed(evt);
            }
        });
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("STUDENT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, Short.MAX_VALUE)
                        .addComponent(Add))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 153, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(882, 567));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fullname", "Prac101", "GWA", "ACTION"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(50);
        table.setRowMargin(10);
        table.setSelectionBackground(new java.awt.Color(8, 114, 15));
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
    add4thGrades dialog = new add4thGrades(null, true);
    dialog.setVisible(true);
    
    // After the dialog is closed, update the table
    populateTable();
  
    
   
    }//GEN-LAST:event_AddActionPerformed

    private void AddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

