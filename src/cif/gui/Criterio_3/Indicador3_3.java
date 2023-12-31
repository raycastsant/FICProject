/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador3_3.java
 *
 * Created on 27/10/2012, 08:45:12 PM
 */

package cif.gui.Criterio_3;

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
public class Indicador3_3 extends javax.swing.JPanel {
    private final String id;
    private final String tablename;
    private final String tag;
    private final Object[][] result33;
    JF_Principal princ;
    private  int anno;

    /** Creates new form Indicador3_3 */
    public Indicador3_3(String id,JF_Principal princ,String tablename,String tag,Object[][] result33) {
        this.id=id;
        this.princ=princ;
        this.tablename=tablename;
        this.tag=tag;
        this.result33=result33;
        initComponents();
        anno=princ.getSelectedYear();
        if (tag.equals("modify")) {
            this.jTextField_LCP.setText(result33[0][0].toString());
            this.jTextField_ARF.setText(result33[0][1].toString());
            this.jTextField_LTLC.setText(result33[0][2].toString());
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
        jLabel1 = new javax.swing.JLabel();
        jTextField_LCP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_ARF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_LTLC = new javax.swing.JTextField();
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

        jLabel1.setText("Longitud de costa protegida(km):");

        jTextField_LCP.setText("0.0");
        jTextField_LCP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_LCPKeyTyped(evt);
            }
        });

        jLabel2.setText("Área real de la faja(ha):");

        jTextField_ARF.setText("0.0");
        jTextField_ARF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ARFKeyTyped(evt);
            }
        });

        jLabel3.setText("Longitud total línea de costa(m):");

        jTextField_LTLC.setText("0.0");
        jTextField_LTLC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_LTLCKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_LCP, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jTextField_ARF)
                    .addComponent(jTextField_LTLC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_LCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_ARF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_LTLC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Double longcostprot=java.lang.Double.parseDouble(jTextField_LCP.getText());
        Double arfaj=java.lang.Double.parseDouble(jTextField_ARF.getText());
        Double longtotlin=java.lang.Double.parseDouble(jTextField_LTLC.getText());
        int existe;
        try {
            if (tag.equals("insert"))
            {
                existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                if(existe!=1)
                {
                    DBAccess.insertFajaCostera(id,anno,longcostprot,arfaj,longtotlin,tablename);
                    jTextField_LCP.setText("0.0");
                    jTextField_ARF.setText("0.0");
                    jTextField_LTLC.setText("0.0");
                }
                else
                {
                    JOptionPane.showConfirmDialog(this, "Ya existen datos para esta entidad en el año "+princ.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }
              } else if (tag.equals("modify"))
              {
                    if(anno==princ.getSelectedYear())
                    {
                        DBAccess.updateFajaCostera(id,princ.getSelectedYear(),anno,longcostprot,arfaj,longtotlin,tablename);
                        JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamente.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        anno=princ.getSelectedYear();
                    }
                    else
                    {
                        existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                        if(existe!=1)
                        {
                            DBAccess.updateFajaCostera(id,princ.getSelectedYear(),anno,longcostprot,arfaj,longtotlin,tablename);
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
                Logger.getLogger(Indicador3_3.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_LCPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_LCPKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_LCP.getText());
    }//GEN-LAST:event_jTextField_LCPKeyTyped

    private void jTextField_ARFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ARFKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_ARF.getText());
    }//GEN-LAST:event_jTextField_ARFKeyTyped

    private void jTextField_LTLCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_LTLCKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_LTLC.getText());
    }//GEN-LAST:event_jTextField_LTLCKeyTyped

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        if(evt.getAncestorParent() instanceof Container)
        {
            jTextField_LCP.requestFocusInWindow();
        }
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTextField_ARF;
    public javax.swing.JTextField jTextField_LCP;
    public javax.swing.JTextField jTextField_LTLC;
    // End of variables declaration//GEN-END:variables

}
