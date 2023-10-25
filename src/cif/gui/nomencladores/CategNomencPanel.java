/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProvNomencPanel.java
 *
 * Created on 31/01/2013, 04:22:26 PM
 */

package cif.gui.nomencladores;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.FieldDescriptor;
import cif.adminstration.utiles.Utils;
import cif.bd.BDUtilities;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro
 */
public class CategNomencPanel extends javax.swing.JPanel {

    private static final int INSERT = 0;
    private static final int UPDATE = 1;
    private int action;
    private String ff_id,old_name;
    /** Creates new form ProvNomencPanel */
    public CategNomencPanel()
    {
        initComponents();
        action = INSERT;
    }

    public CategNomencPanel(String nombre)
    {
        initComponents();
        action = UPDATE;
        old_name = nombre;
        name_field.setText(nombre);
        ff_id = DBQueries.getNomencId("c5_5_catgocupacional",nombre);
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
        label_name = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        acept = new javax.swing.JButton();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Categoría Ocupacional"));

        label_name.setText("Nombre:");

        name_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                name_fieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_name, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name_field, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_name)
                    .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-cancel.png"))); // NOI18N
        cancel.setText("Cancelar");
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });
        cancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cancelKeyTyped(evt);
            }
        });

        acept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-accept.png"))); // NOI18N
        acept.setText("Aceptar");
        acept.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptMouseClicked(evt);
            }
        });
        acept.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aceptKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(acept)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(acept))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void aceptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptMouseClicked
        if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            InsertFF();
        }
}//GEN-LAST:event_aceptMouseClicked

    private void aceptKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aceptKeyTyped
        if(evt.getKeyChar() == evt.VK_ENTER) {
            InsertFF();
        }
}//GEN-LAST:event_aceptKeyTyped

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            setVisible(false);
        }
}//GEN-LAST:event_cancelMouseClicked

    private void cancelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelKeyTyped
        if(evt.getKeyChar() == evt.VK_ENTER) {
            setVisible(false);
        }
}//GEN-LAST:event_cancelKeyTyped

    private void name_fieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_name_fieldKeyTyped
        if(!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != ' ')
        {
            evt.consume();
        }
    }//GEN-LAST:event_name_fieldKeyTyped

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        if(evt.getAncestorParent() instanceof Container)
        {
            name_field.requestFocusInWindow();
        }
    }//GEN-LAST:event_formAncestorAdded

    private void InsertFF()
    {
        String name;
        boolean error=false;
        name = name_field.getText().toUpperCase();
        if(name.isEmpty())
        {
            error = true;
            label_name.setForeground(Color.red);
        }
        else
        {
            label_name.setForeground(Color.black);
        }
        if(error)
        {
            JOptionPane.showInternalMessageDialog(Utils.getParentFrame(this).getContentPane(),"Debe ponerle un nombre a la Categoría Ocupacional.", "Error", JOptionPane.ERROR_MESSAGE);
            error = false;
            return;
        }
        else
        {
            try
            {
                if(action == INSERT)
                {
                    if(!DBQueries.ExistNomenc("c5_5_catgocupacional",name))
                    {
                        DBQueries.InsertNomenc("c5_5_catgocupacional",name);
                    }
                    else
                    {
                        ff_id = DBQueries.getNomencId("c5_5_catgocupacional",name);
                        if(!BDUtilities.isActive("c5_5_catgocupacional", ff_id, FieldDescriptor.STRING))
                        {
                            DBQueries.UpdateNomenc("c5_5_catgocupacional",ff_id,name,"true");
                        }
                        else
                        {
                            JOptionPane.showInternalMessageDialog(Utils.getParentFrame(this).getContentPane(),"La categoría ocupacional "+name+ " ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    ResetWindow();
                }
                else
                {
                    if(!DBQueries.ExistNomenc("c5_5_catgocupacional",name))
                    {
                        DBQueries.UpdateNomenc("c5_5_catgocupacional",ff_id,name,"true");
                    }
                    else
                    {
                        String aux = DBQueries.getNomencId("c5_5_catgocupacional",name);
                        if(old_name.equals(name))
                        {
                            DBQueries.UpdateNomenc("c5_5_catgocupacional",ff_id,name,"true");
                        }
                        if(!BDUtilities.isActive("c5_5_catgocupacional", aux, FieldDescriptor.STRING))
                        {
                            DBQueries.UpdateNomenc("c5_5_catgocupacional",ff_id,name,"true");
                            BDUtilities.deleteNomenc("c5_5_catgocupacional", aux, FieldDescriptor.NUMBER);
                        }
                        else
                        {
                            JOptionPane.showInternalMessageDialog(Utils.getParentFrame(this).getContentPane(),"La especie endémica "+name+ " ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showInternalMessageDialog(Utils.getParentFrame(this).getContentPane(),"Los datos se modificaron satisfactoriamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(Exception e)
            {
                JOptionPane.showInternalMessageDialog(Utils.getParentFrame(this).getContentPane(),"Ocurrió el siguiente error \n\r" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void ResetWindow()
    {
        name_field.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acept;
    private javax.swing.JButton cancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_name;
    private javax.swing.JTextField name_field;
    // End of variables declaration//GEN-END:variables

}
