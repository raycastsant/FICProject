/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador5_2.java
 *
 * Created on 27/10/2012, 11:02:07 PM
 */

package cif.gui.Criterio_5;

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
public class Indicador5_2 extends javax.swing.JPanel {
    private final String id;
    private final String tablename;
    private final String tag;
    private final Object[][] result52;
    JF_Principal princ;
    private int anno;

    /** Creates new form Indicador5_2 */
    public Indicador5_2(String id,JF_Principal princ,String tablename,String tag,Object[][] result52) {
        this.id=id;
        this.princ=princ;
        this.tablename=tablename;
        this.tag=tag;
        this.result52=result52;
        initComponents();
        anno=princ.getSelectedYear();
        if (tag.equals("modify")) {
            this.jTextField_AP.setText(result52[0][0].toString());
            this.jTextField_AM.setText(result52[0][1].toString());
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
        jLabel3 = new javax.swing.JLabel();
        jTextField_AM = new javax.swing.JTextField();
        jTextField_AP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                formAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));

        jLabel3.setText("Accidentes mortales(u):");

        jTextField_AM.setText("0");
        jTextField_AM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_AMKeyTyped(evt);
            }
        });

        jTextField_AP.setText("0");
        jTextField_AP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_APKeyTyped(evt);
            }
        });

        jLabel2.setText("Accidentes parciales(u):");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_AP, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_AM, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_AP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_AM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        Integer accidparc=java.lang.Integer.parseInt(jTextField_AP.getText());
        Integer accidmort=java.lang.Integer.parseInt(jTextField_AM.getText());
        int existe;

        try {
            if (tag.equals("insert")) {
                existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                if(existe!=1)
                {
                    DBAccess.insertAccidente(id,anno,accidparc,accidmort,tablename);
                    jTextField_AP.setText("0");
                    jTextField_AM.setText("0");
                }
                else
                {
                    JOptionPane.showConfirmDialog(this, "Ya existen datos para esta entidad en el año "+princ.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }
            } else if (tag.equals("modify")) {
                if(anno==princ.getSelectedYear())
                {
                    DBAccess.updateAccidente(id,princ.getSelectedYear(),anno,accidparc,accidmort,tablename);
                    JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamnete.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    anno=princ.getSelectedYear();
                }
                else
                {
                    existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                    if(existe!=1)
                    {
                      DBAccess.updateAccidente(id, princ.getSelectedYear(), anno, accidparc,accidmort,tablename);
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
                Logger.getLogger(Indicador5_2.class.getName()).log(Level.SEVERE, null, ex);
            }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_APKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_APKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
    }//GEN-LAST:event_jTextField_APKeyTyped

    private void jTextField_AMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_AMKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
    }//GEN-LAST:event_jTextField_AMKeyTyped

    private void formAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorMoved
        if(evt.getAncestorParent() instanceof Container)
        {
            jTextField_AP.requestFocusInWindow();
        }
    }//GEN-LAST:event_formAncestorMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTextField_AM;
    public javax.swing.JTextField jTextField_AP;
    // End of variables declaration//GEN-END:variables

}
