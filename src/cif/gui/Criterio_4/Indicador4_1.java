/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador4_1.java
 *
 * Created on 27/10/2012, 09:51:40 PM
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
public class Indicador4_1 extends javax.swing.JPanel {
    private final String id;
    private final String tablename;
    private final String tag;
    private final Object[][] result41;
    JF_Principal princ;
    private  int anno;

    /** Creates new form Indicador4_1 */
    public Indicador4_1(String id,JF_Principal princ,String tablename,String tag,Object[][] result41) {
        this.id=id;
        this.princ=princ;
        this.tablename=tablename;
        this.tag=tag;
        this.result41=result41;
        initComponents();
        anno=princ.getSelectedYear();
        if (tag.equals("modify")) {
            this.jTextField_BPCAE.setText(result41[0][0].toString());
            this.jTextField_BPCAP.setText(result41[0][1].toString());
            this.jTextField_BTCAE.setText(result41[0][2].toString());
            this.jTextField_BTCAP.setText(result41[0][3].toString());
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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_BPCAE = new javax.swing.JTextField();
        jTextField_BPCAP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_BTCAE = new javax.swing.JTextField();
        jTextField_BTCAP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-accept.png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bosques productores"));

        jLabel2.setText("Corta anual ejecutada(m3):");

        jTextField_BPCAE.setText("0.0");
        jTextField_BPCAE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_BPCAEKeyTyped(evt);
            }
        });

        jTextField_BPCAP.setText("0.0");
        jTextField_BPCAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_BPCAPKeyTyped(evt);
            }
        });

        jLabel7.setText("Corta anual permisible(m3):");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_BPCAP, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_BPCAE, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_BPCAE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_BPCAP))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bosques protectores"));

        jLabel3.setText("Corta anual ejecutada(m3):");

        jTextField_BTCAE.setText("0.0");
        jTextField_BTCAE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_BTCAEKeyTyped(evt);
            }
        });

        jTextField_BTCAP.setText("0.0");
        jTextField_BTCAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_BTCAPKeyTyped(evt);
            }
        });

        jLabel8.setText("Corta anual permisible(m3):");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_BTCAP, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_BTCAE, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_BTCAE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_BTCAP))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Double caebprod=java.lang.Double.parseDouble(jTextField_BPCAE.getText());
        Double cap_bprod=java.lang.Double.parseDouble(jTextField_BPCAP.getText());
        Double caebprot=java.lang.Double.parseDouble(jTextField_BTCAE.getText());
        Double cap_bprot=java.lang.Double.parseDouble(jTextField_BTCAP.getText());
        int existe;
        try {
            if (tag.equals("insert")) {
                existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                if(existe!=1)
                {
                    DBAccess.insertIndRendSost(id,princ.getSelectedYear(),caebprod,cap_bprod ,caebprot , cap_bprot,tablename);
                    jTextField_BPCAE.setText("0.0");
                    jTextField_BPCAP.setText("0.0");
                    jTextField_BTCAE.setText("0.0");
                    jTextField_BTCAP.setText("0.0");
                }
                else
                {
                    JOptionPane.showConfirmDialog(this, "Ya existen datos para esta entidad en el año "+princ.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }
                } else if (tag.equals("modify")) {
                    if(anno==princ.getSelectedYear())
                    {
                       DBAccess.updateIndRendSost(id,princ.getSelectedYear(),anno,caebprod,cap_bprod ,caebprot , cap_bprot,tablename);
                        JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamnete.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        anno=princ.getSelectedYear();
                    }
                    else
                    {
                        existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                        if(existe!=1)
                        {
                            DBAccess.updateIndRendSost(id,princ.getSelectedYear(),anno,caebprod,cap_bprod ,caebprot , cap_bprot,tablename);
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
                Logger.getLogger(Indicador4_1.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_BPCAEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BPCAEKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_BPCAE.getText());
    }//GEN-LAST:event_jTextField_BPCAEKeyTyped

    private void jTextField_BPCAPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BPCAPKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_BPCAP.getText());
    }//GEN-LAST:event_jTextField_BPCAPKeyTyped

    private void jTextField_BTCAEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BTCAEKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_BTCAE.getText());
    }//GEN-LAST:event_jTextField_BTCAEKeyTyped

    private void jTextField_BTCAPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BTCAPKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_BTCAP.getText());
    }//GEN-LAST:event_jTextField_BTCAPKeyTyped

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        if(evt.getAncestorParent() instanceof Container)
        {
            jTextField_BPCAE.requestFocusInWindow();
        }
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField jTextField_BPCAE;
    public javax.swing.JTextField jTextField_BPCAP;
    public javax.swing.JTextField jTextField_BTCAE;
    public javax.swing.JTextField jTextField_BTCAP;
    // End of variables declaration//GEN-END:variables

}
