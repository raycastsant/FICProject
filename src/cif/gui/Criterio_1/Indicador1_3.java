/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador1_3.java
 *
 * Created on 25/10/2012, 11:17:30 PM
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
public class Indicador1_3 extends javax.swing.JPanel {
    private final String id;
    private final String tablename;
    private final String tag;
    private final Object[][] result13;
    JF_Principal princ;
    private int anno;

    /** Creates new form Indicador1_3 */
    public Indicador1_3(String id,JF_Principal princ,String tablename,String tag,Object[][] result13) {
        this.id=id;
        this.princ=princ;
        this.tablename=tablename;
        this.tag=tag;
        this.result13=result13;
        initComponents();
        anno=princ.getSelectedYear();
        if (tag.equals("modify")) {
            this.jTextField_Bralo.setText(result13[0][0].toString());
            this.jTextField_Calvero.setText(result13[0][1].toString());
            this.jTextField_LugarTalado.setText(result13[0][2].toString());
            this.jTextField_PlantJ.setText(result13[0][3].toString());
            this.jTextField_Bmuertos.setText(result13[0][4].toString());
            this.jTextField_Bquemados.setText(result13[0][5].toString());
            this.jTextField_XMogote.setText(result13[0][6].toString());
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
        jTextField_Bralo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_LugarTalado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Calvero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_PlantJ = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Bmuertos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_Bquemados = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_XMogote = new javax.swing.JTextField();
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

        jLabel2.setText("Bosque ralo (ha):");

        jTextField_Bralo.setText("0.0");
        jTextField_Bralo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_BraloKeyTyped(evt);
            }
        });

        jLabel3.setText("Lugar talado (ha):");

        jTextField_LugarTalado.setText("0.0");
        jTextField_LugarTalado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_LugarTaladoKeyTyped(evt);
            }
        });

        jLabel4.setText("Calvero (ha):");

        jTextField_Calvero.setText("0.0");
        jTextField_Calvero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_CalveroKeyTyped1(evt);
            }
        });

        jLabel5.setText("Plantación joven(ha):");

        jTextField_PlantJ.setText("0.0");
        jTextField_PlantJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_PlantJKeyTyped(evt);
            }
        });

        jLabel6.setText("Plantaciones y bosques muertos (ha):");

        jTextField_Bmuertos.setText("0.0");
        jTextField_Bmuertos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_BmuertosKeyTyped(evt);
            }
        });

        jLabel7.setText("Superficie quemada (ha):");

        jTextField_Bquemados.setText("0.0");
        jTextField_Bquemados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_BquemadosKeyTyped(evt);
            }
        });

        jLabel8.setText("Xerófilo de mogote (ha):");

        jTextField_XMogote.setText("0.0");
        jTextField_XMogote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_XMogoteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Bralo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_LugarTalado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Calvero, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_PlantJ, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Bmuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Bquemados, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_XMogote, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_Bralo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_LugarTalado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_Calvero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_PlantJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_Bmuertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_Bquemados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_XMogote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addComponent(jButton_Aceptar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Aceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AceptarActionPerformed
        Double bosqralo=java.lang.Double.parseDouble(jTextField_Bralo.getText());
        Double calvero=java.lang.Double.parseDouble(jTextField_Calvero.getText());
        Double lugtalado=java.lang.Double.parseDouble(jTextField_LugarTalado.getText());
        Double plantjoven=java.lang.Double.parseDouble(jTextField_PlantJ.getText());
        Double plantmuertos=java.lang.Double.parseDouble(jTextField_Bmuertos.getText());
        Double superfquem=java.lang.Double.parseDouble(jTextField_Bquemados.getText());
        Double xmogote=java.lang.Double.parseDouble(jTextField_XMogote.getText());
        int existe;
        try{
            if(tag.equals("insert"))
            {
                existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                if(existe!=1)
                {
                    DBAccess.insertRelTACSCF(id,princ.getSelectedYear(), bosqralo, calvero,lugtalado,plantjoven,plantmuertos,superfquem,xmogote, tablename);
                    jTextField_Bralo.setText("0.0");
                    jTextField_Calvero.setText("0.0");
                    jTextField_LugarTalado.setText("0.0");
                    jTextField_PlantJ.setText("0.0");
                    jTextField_Bmuertos.setText("0.0");
                    jTextField_Bquemados.setText("0.0");
                    jTextField_XMogote.setText("0.0");
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
                    DBAccess.updateRelTACSCF(id,princ.getSelectedYear(),anno, bosqralo, calvero,lugtalado,plantjoven,plantmuertos,superfquem,xmogote, tablename);
                    JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamente.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    anno=princ.getSelectedYear();
                }
                else
                {
                    existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                    if(existe!=1)
                    {
                        DBAccess.updateRelTACSCF(id,princ.getSelectedYear(),anno, bosqralo, calvero,lugtalado,plantjoven,plantmuertos,superfquem,xmogote, tablename);
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
                Logger.getLogger(Indicador1_3.class.getName()).log(Level.SEVERE, null, ex);
            }
       
}//GEN-LAST:event_jButton_AceptarActionPerformed

    private void jTextField_BraloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BraloKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_Bralo.getText());
    }//GEN-LAST:event_jTextField_BraloKeyTyped

    private void jTextField_LugarTaladoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_LugarTaladoKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_LugarTalado.getText());
    }//GEN-LAST:event_jTextField_LugarTaladoKeyTyped

    private void jTextField_CalveroKeyTyped1(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_CalveroKeyTyped1
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_Calvero.getText());
    }//GEN-LAST:event_jTextField_CalveroKeyTyped1

    private void jTextField_PlantJKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PlantJKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_PlantJ.getText());
    }//GEN-LAST:event_jTextField_PlantJKeyTyped

    private void jTextField_BmuertosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BmuertosKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_Bmuertos.getText());
    }//GEN-LAST:event_jTextField_BmuertosKeyTyped

    private void jTextField_BquemadosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BquemadosKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_Bquemados.getText());
    }//GEN-LAST:event_jTextField_BquemadosKeyTyped

    private void jTextField_XMogoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_XMogoteKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_XMogote.getText());
    }//GEN-LAST:event_jTextField_XMogoteKeyTyped

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        if(evt.getAncestorParent() instanceof Container)
        {
            jTextField_Bralo.requestFocusInWindow();
        }
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Aceptar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTextField_Bmuertos;
    public javax.swing.JTextField jTextField_Bquemados;
    public javax.swing.JTextField jTextField_Bralo;
    public javax.swing.JTextField jTextField_Calvero;
    public javax.swing.JTextField jTextField_LugarTalado;
    public javax.swing.JTextField jTextField_PlantJ;
    public javax.swing.JTextField jTextField_XMogote;
    // End of variables declaration//GEN-END:variables

}
