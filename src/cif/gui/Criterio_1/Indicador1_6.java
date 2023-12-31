/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador1_6.java
 *
 * Created on 26/10/2012, 12:00:06 AM
 */

package cif.gui.Criterio_1;

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
public class Indicador1_6 extends javax.swing.JPanel {
    private final String id;
    private final String tablename;
    private final String tag;
    private final Object[][] result16;
    JF_Principal princ;
    private  int anno;

    /** Creates new form Indicador1_6 */
    public Indicador1_6(String id,JF_Principal princ,String tablename,String tag,Object[][] result16) {
        this.id=id;
        this.princ=princ;
        this.tablename=tablename;
        this.tag=tag;
        this.result16=result16;
        initComponents();
        anno=princ.getSelectedYear();
        if (tag.equals("modify")) {
            this.jTextField_ArbEx.setText(result16[0][0].toString());
            this.jTextField_ArbFalt.setText(result16[0][1].toString());
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
        jLabel3 = new javax.swing.JLabel();
        jTextField_ArbFalt = new javax.swing.JTextField();
        jTextField_ArbEx = new javax.swing.JTextField();
        jButton_Aceptar = new javax.swing.JButton();

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

        jLabel2.setText("Árboles existentes(u):");

        jLabel3.setText("Árboles faltantes(u):");

        jTextField_ArbFalt.setText("0");
        jTextField_ArbFalt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ArbFaltKeyTyped(evt);
            }
        });

        jTextField_ArbEx.setText("0");
        jTextField_ArbEx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ArbExKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_ArbFalt)
                    .addComponent(jTextField_ArbEx, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_ArbEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_ArbFalt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton_Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-accept.png"))); // NOI18N
        jButton_Aceptar.setText("Aceptar");
        jButton_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_Aceptar)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Aceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AceptarActionPerformed
        // TODO add your handling code here:
        Integer arbol_existe=java.lang.Integer.parseInt(jTextField_ArbEx.getText());
        Integer arbol_falta=java.lang.Integer.parseInt(jTextField_ArbFalt.getText());
        int existe;
        try{
                if(tag.equals("insert"))
                {
                    existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                    if(existe!=1)
                    {
                        DBAccess.insertArbAisl(id,princ.getSelectedYear(),arbol_existe,arbol_falta,tablename);
                        jTextField_ArbEx.setText("0");
                        jTextField_ArbFalt.setText("0");
                    }
                    else
                    {
                        JOptionPane.showConfirmDialog(this, "Ya existen datos para esta entidad en el año "+princ.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(tag.equals("modify"))
                {
                    if(anno==princ.getSelectedYear())
                    {
                        DBAccess.upddateArbAisl(id,princ.getSelectedYear(),anno,arbol_existe,arbol_falta,tablename);
                        JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamente.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        anno=princ.getSelectedYear();
                    }
                    else
                    {
                        existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                        if(existe!=1)
                        {
                            DBAccess.upddateArbAisl(id,princ.getSelectedYear(),anno,arbol_existe,arbol_falta,tablename);
                            JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamente.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                            anno=princ.getSelectedYear();
                        }
                        else
                        {
                            JOptionPane.showConfirmDialog(this, "Ya existen datos para esta entidad en el año "+princ.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        } catch (SQLException ex) {
        Logger.getLogger(Indicador1_6.class.getName()).log(Level.SEVERE, null, ex);
    }

    }//GEN-LAST:event_jButton_AceptarActionPerformed

    private void jTextField_ArbExKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ArbExKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
    }//GEN-LAST:event_jTextField_ArbExKeyTyped

    private void jTextField_ArbFaltKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ArbFaltKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
    }//GEN-LAST:event_jTextField_ArbFaltKeyTyped

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        if(evt.getAncestorParent() instanceof Container)
        {
            jTextField_ArbEx.requestFocusInWindow();
        }
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Aceptar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTextField_ArbEx;
    public javax.swing.JTextField jTextField_ArbFalt;
    // End of variables declaration//GEN-END:variables

}
