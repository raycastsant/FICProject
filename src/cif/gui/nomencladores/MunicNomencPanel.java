/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MunicNomencPanel.java
 *
 * Created on 30/01/2013, 10:40:07 PM
 */

package cif.gui.nomencladores;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.FieldDescriptor;
import cif.adminstration.utiles.Utils;
import cif.bd.BDUtilities;
import cif.utiles.Adv_ComboBox;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JOptionPane;

/**
 *
 * @author IntelI3
 */
public class MunicNomencPanel extends javax.swing.JPanel {

    /** Creates new form MunicNomencPanel */
    private static final int INSERT = 0;
    private static final int UPDATE = 1;
    private int action;
    private String munic_id,old_name,old_prov;

    public MunicNomencPanel() {
        init();
        Utils.FillCombo(BDUtilities.getProvincias(),prov_field);
        action = INSERT;
    }

    public MunicNomencPanel(String nombre,String prov)
    {
        init();
        Utils.FillCombo(BDUtilities.getProvincias(),prov_field);
        action = UPDATE;
        old_name = nombre;
        old_prov = prov;
        name_field.setText(nombre);
        prov_field.setSelectedItem(prov);
        munic_id = DBQueries.getMunicIdNProv(nombre, prov);
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
        label_prov = new javax.swing.JLabel();
        acept = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setFocusCycleRoot(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Municipio"));

        label_name.setText("Nombre:");

        label_prov.setText("Provincia:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(label_prov, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_name)
                    .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(label_prov)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(acept)
                .addGap(4, 4, 4)
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
                    .addComponent(acept)
                    .addComponent(cancel))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void init() {

        jPanel1 = new javax.swing.JPanel();
        label_name = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();
        label_prov = new javax.swing.JLabel();
        prov_field = new Adv_ComboBox();
        acept = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setFocusCycleRoot(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Municipio"));

        label_name.setText("Nombre:");

        label_prov.setText("Provincia:");

        name_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(label_prov, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prov_field, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name_field, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_name)
                    .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_prov)
                    .addComponent(prov_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(acept)
                .addGap(4, 4, 4)
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
                    .addComponent(acept)
                    .addComponent(cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>


    private void aceptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptMouseClicked
        if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1)
        {
            InsertMunic();
        }
    }//GEN-LAST:event_aceptMouseClicked

    private void aceptKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aceptKeyTyped
        if(evt.getKeyChar() == evt.VK_ENTER)
        {
            InsertMunic();
        }
    }//GEN-LAST:event_aceptKeyTyped

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1)
        {
            setVisible(false);
        }
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelKeyTyped
        if(evt.getKeyChar() == evt.VK_ENTER)
        {
            setVisible(false);
        }
    }//GEN-LAST:event_cancelKeyTyped

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {
        if(evt.getAncestorParent() instanceof Container)
        {
            name_field.requestFocusInWindow();
        }
    }

    private void nameKeyTyped(java.awt.event.KeyEvent evt)
    {
        if(!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != ' ')
        {
            evt.consume();
        }
    }

    private void InsertMunic()
    {
        String name, prov;
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
        prov = prov_field.getSelectedValue().toString();
        if(prov.equals("-1"))
        {
            error = true;
            label_prov.setForeground(Color.red);
        }
        else
        {
            label_prov.setForeground(Color.black);
        }
        if(error)
        {
            JOptionPane.showInternalMessageDialog(Utils.getParentFrame(this).getContentPane(),"Debe especificar un nombre y una provincia para el municipio.", "Error", JOptionPane.ERROR_MESSAGE);
            error = false;
            return;
        }
        else
        {
            try
            {
                if(action == INSERT)
                {
                    if(!DBQueries.ExistMunic(name, prov))
                    {
                        DBQueries.InsertMunic(name,prov);
                    }
                    else
                    {
                        munic_id = DBQueries.getMunicId(name, prov);
                        if(!BDUtilities.isActive("municipios", munic_id, FieldDescriptor.STRING))
                        {
                            DBQueries.UpdateMunic(munic_id,name,prov,"true");
                        }
                        else
                        {
                            JOptionPane.showInternalMessageDialog(Utils.getParentFrame(this).getContentPane(),"Este municipio ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    ResetWindow();
                }
                else
                {
                    if(!DBQueries.ExistMunic(name, prov))
                    {
                        DBQueries.UpdateMunic(munic_id,name,prov,"true");
                    }
                    else
                    {
                        String aux = DBQueries.getMunicId(name, prov);
                        if(old_name.equals(name) && old_prov.equals(prov))
                        {
                            DBQueries.UpdateMunic(munic_id,name,prov,"true");
                        }
                        if(!BDUtilities.isActive("municipios", aux, FieldDescriptor.STRING))
                        {
                            DBQueries.UpdateMunic(munic_id,name,prov,"true");
                            BDUtilities.deleteNomenc("municipios", aux, FieldDescriptor.STRING);
                        }
                        else
                        {
                            JOptionPane.showInternalMessageDialog(Utils.getParentFrame(this).getContentPane(),"Este municipio ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
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
        prov_field.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acept;
    private javax.swing.JButton cancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_prov;
    private javax.swing.JTextField name_field;
    // End of variables declaration//GEN-END:variables
    private Adv_ComboBox prov_field;

}
