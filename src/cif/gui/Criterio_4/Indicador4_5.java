/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador4_5.java
 *
 * Created on 27/10/2012, 10:40:53 PM
 */

package cif.gui.Criterio_4;

import cif.bd.DBAccess;
import cif.gui.JF_Principal;
import cif.utiles.utiles;
import java.awt.Container;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author casa
 */
public class Indicador4_5 extends javax.swing.JPanel {
    private final String id;
    private final String tablename;
    private final String tag;
//    private final Object[][] result45;
    JF_Principal princ;
    private  int anno;

    /** Creates new form Indicador4_5 */
    public Indicador4_5(String id,JF_Principal princ,String tablename,String tag,Object[][] result45)
    {
        this.id=id;
        this.princ=princ;
        this.tablename=tablename;
        this.tag=tag;
//        this.result45=result45;

        initComponents();
        
        anno=princ.getSelectedYear();

        if (tag.equals("modify"))
        {
            this.jTFCantFF.setText(result45[0][0].toString());
            this.jTFSupFF.setText(result45[0][1].toString());
            this.jTFSupPotASP.setText(result45[0][2].toString());
            this.jTFSupManAsp.setText(result45[0][3].toString());
            this.jTFSupPotFF.setText(result45[0][4].toString());
            this.jTFAreaBnFF.setText(result45[0][5].toString());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTFCantFF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFSupPotASP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTFSupFF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTFSupManAsp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTFAreaBnFF = new javax.swing.JTextField();
        jTFSupPotFF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));

        jLabel2.setText("Cantidad fincas forestales(u):");

        jTFCantFF.setText("0");
        jTFCantFF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCantFFKeyTyped(evt);
            }
        });

        jLabel5.setText("Superficie potencial agrosilvopatoriles(ha):");

        jTFSupPotASP.setText("0.0");
        jTFSupPotASP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFSupPotASPKeyTyped(evt);
            }
        });

        jLabel6.setText("Superficie potencial fincas forestales(ha):");

        jLabel7.setText("Superficie fincas forestales(ha):");

        jTFSupFF.setText("0.0");
        jTFSupFF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFSupFFKeyTyped(evt);
            }
        });

        jLabel8.setText("Superficie manejada agrosilvopatoriles(ha):");

        jTFSupManAsp.setText("0.0");
        jTFSupManAsp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFSupManAspKeyTyped(evt);
            }
        });

        jLabel1.setText("Área bosques naturales fincas forestales(ha):");

        jTFAreaBnFF.setText("0.0");
        jTFAreaBnFF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFAreaBnFFKeyTyped(evt);
            }
        });

        jTFSupPotFF.setText("0.0");
        jTFSupPotFF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFSupPotFFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(1, 1, 1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFSupFF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jTFCantFF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5)
                        .addGap(1, 1, 1)
                        .addComponent(jTFSupPotASP, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addGap(1, 1, 1)
                        .addComponent(jTFAreaBnFF, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFSupPotFF, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFSupManAsp, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFCantFF))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFSupFF)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFAreaBnFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFSupPotFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFSupManAsp, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFSupPotASP, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel5)))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-accept.png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(285, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Integer cantfincaforst=java.lang.Integer.parseInt(jTFCantFF.getText());
        Double supffincaforst=java.lang.Double.parseDouble(jTFSupFF.getText());
        Double supfpotagrosilvp=java.lang.Double.parseDouble(jTFSupPotASP.getText());
        Double supfmanagrosilvp=java.lang.Double.parseDouble(jTFSupManAsp.getText());
        Double supfpotfincaforst=java.lang.Double.parseDouble(jTFSupPotFF.getText());
        Double areabncaforst=java.lang.Double.parseDouble(jTFAreaBnFF.getText());
        int existe;
        try {
            if (tag.equals("insert")) {
                existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                if(existe!=1)
                {
                    DBAccess.insertSistAgrosilvopast(id,princ.getSelectedYear(),cantfincaforst,supffincaforst,supfpotagrosilvp,supfmanagrosilvp,supfpotfincaforst,areabncaforst,tablename);
                    jTFCantFF.setText("0");
                    jTFSupFF.setText("0.0");
                    jTFSupPotASP.setText("0.0");
                    jTFSupManAsp.setText("0.0");
                    jTFSupPotFF.setText("0.0");
                    jTFAreaBnFF.setText("0.0");
                }
                else
                {
                    JOptionPane.showConfirmDialog(this, "Ya existen datos para esta entidad en el año "+princ.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            if (tag.equals("modify"))
            {
                if(anno==princ.getSelectedYear())
                {
                    DBAccess.updateSistAgrosilvopast(id,princ.getSelectedYear(),anno,cantfincaforst,supffincaforst,supfpotagrosilvp,supfmanagrosilvp,supfpotfincaforst,areabncaforst,tablename);
                    JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamnete.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    anno=princ.getSelectedYear();
                }
                else
                {
                    existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                    if(existe!=1)
                    {
                        DBAccess.updateSistAgrosilvopast(id,princ.getSelectedYear(),anno,cantfincaforst,supffincaforst,supfpotagrosilvp,supfmanagrosilvp,supfpotfincaforst,areabncaforst,tablename);
                        JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamnete.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        anno=princ.getSelectedYear();
                    }
                    else
                    {
                        JOptionPane.showConfirmDialog(this, "Ya existen datos para esta entidad en el año "+princ.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                    }
                }
             }
           } catch (SQLException ex) {
                Logger.getLogger(Indicador4_5.class.getName()).log(Level.SEVERE, null, ex);
           }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTFCantFFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCantFFKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
    }//GEN-LAST:event_jTFCantFFKeyTyped

    private void jTFSupFFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSupFFKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTFSupFF.getText());
    }//GEN-LAST:event_jTFSupFFKeyTyped

    private void jTFSupPotASPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSupPotASPKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTFSupPotASP.getText());
    }//GEN-LAST:event_jTFSupPotASPKeyTyped

    private void jTFSupManAspKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSupManAspKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTFSupManAsp.getText());
    }//GEN-LAST:event_jTFSupManAspKeyTyped

    private void jTFSupPotFFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSupPotFFKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTFSupPotFF.getText());
    }//GEN-LAST:event_jTFSupPotFFKeyTyped

    private void jTFAreaBnFFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFAreaBnFFKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTFAreaBnFF.getText());
    }//GEN-LAST:event_jTFAreaBnFFKeyTyped

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        if(evt.getAncestorParent() instanceof Container)
        {
            jTFCantFF.requestFocusInWindow();
        }
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTFAreaBnFF;
    public javax.swing.JTextField jTFCantFF;
    public javax.swing.JTextField jTFSupFF;
    public javax.swing.JTextField jTFSupManAsp;
    public javax.swing.JTextField jTFSupPotASP;
    public javax.swing.JTextField jTFSupPotFF;
    // End of variables declaration//GEN-END:variables

}
