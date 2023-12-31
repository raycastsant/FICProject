/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador1_4.java
 *
 * Created on 25/10/2012, 11:40:14 PM
 */

package cif.gui.Criterio_1;

import cif.bd.DBAccess;
import cif.gui.JF_Principal;
import cif.manage.Manage;
import cif.manage.User;
import cif.utiles.Adv_ComboBox;
import cif.utiles.utiles;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author casa
 */
public class Indicador1_4 extends javax.swing.JPanel {
    private final String id;
    private final String tablename;
    private final String tag;
    private final Object[][] result14;
    JF_Principal princ;
    private int anno;

    /** Creates new form Indicador1_4 */
    public Indicador1_4(String id,JF_Principal princ,String tablename,String tag,Object[][] result14) {
        this.id=id;
        this.princ=princ;
        this.tablename=tablename;
        this.tag=tag;
        this.result14=result14;
        init();
        anno=princ.getSelectedYear();
        //initComponents();
        if (tag.equals("modify")) {
            this.jTextField_SP.setText(result14[0][0].toString());
            this.jTextField_SL.setText(result14[0][1].toString());
            this.jTextField_PV.setText(result14[0][2].toString());
            this.jTextField_PM.setText(result14[0][3].toString());
            //lenar 1ro el combobox con all especies
            //this.jComboBoxEspecie.setSelectedValue(result14[0][4].toString());
            fillCombobox();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_SP = new javax.swing.JTextField();
        jTextField_SL = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_PV = new javax.swing.JTextField();
        jTextField_PM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton_Aceptar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Logro del tercer conteo"));

        jLabel2.setText("Superficie plantada(ha):");

        jLabel3.setText("Superficie lograda(ha):");

        jTextField_SP.setText("0.0");
        jTextField_SP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_SPKeyTyped(evt);
            }
        });

        jTextField_SL.setText("0.0");
        jTextField_SL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_SLKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_SL)
                    .addComponent(jTextField_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_SL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Supervivencia al tercer conteo"));

        jLabel5.setText("Plantas vivas(u):");

        jLabel4.setText("Plantas muertas(u):");

        jTextField_PV.setText("0");
        jTextField_PV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_PVKeyTyped(evt);
            }
        });

        jTextField_PM.setText("0");
        jTextField_PM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_PMKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_PM, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jTextField_PV, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_PV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_PM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Especie:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(179, 179, 179))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Aceptar)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

     private void init()
    {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_SP = new javax.swing.JTextField();
        jTextField_SL = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_PV = new javax.swing.JTextField();
        jTextField_PM = new javax.swing.JTextField();

        jComboBoxEspecie = new Adv_ComboBox();
        fillCombobox();
               
        jLabel6 = new javax.swing.JLabel();
        jButton_Aceptar = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Logro del tercer conteo"));

        jLabel2.setText("Superficie plantada(ha):");

        jLabel3.setText("Superficie lograda(ha):");
        jTextField_SP.setText("0.0");
        jTextField_SP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_SPKeyTyped(evt);
            }
        });
        jTextField_SL.setText("0.0");
        jTextField_SL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_SLKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_SL)
                    .addComponent(jTextField_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_SL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Supervivencia al tercer conteo"));

        jLabel5.setText("Plantas vivas(u):");

        jLabel4.setText("Plantas muertas(u):");
        jTextField_PV.setText("0");
        jTextField_PV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_PVKeyTyped(evt);
            }
        });
        jTextField_PM.setText("0");
        jTextField_PM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_PMKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_PM, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jTextField_PV, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_PV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_PM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Especie:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jComboBoxEspecie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxEspecieItemStateChanged(evt);
            }
        });
        jButton_Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-accept.png")));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Aceptar)
                .addContainerGap())
        );
    }
    private void jButton_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AceptarActionPerformed
        // TODO add your handling code here:
        Double superf_plant=java.lang.Double.parseDouble(jTextField_SP.getText());
        Double superf_log=java.lang.Double.parseDouble(jTextField_SL.getText());
        Integer plant_vivas=java.lang.Integer.parseInt(jTextField_PV.getText());
        Integer plant_muertas=java.lang.Integer.parseInt(jTextField_PM.getText());
        Integer especie=java.lang.Integer.parseInt(jComboBoxEspecie.getSelectedValue().toString());
        int existe;
        if(superf_plant>superf_log)
        {
            try{
                    if(tag.equals("insert"))
                    {
                        Manage manager = Manage.getInstance();
                        User user = manager.getUser();
                        String select="select id, especie, anno, superf_plant, superf_log, plant_vivas,plant_muertas, municipio";
                        String where="where id='"+id+"' and municipio='"+user.getMunicipio()+"' and  especie='"+especie+"' and  anno="+princ.getSelectedYear()+"";
                        existe=DBAccess.verificarPkey2(select, tablename, where);
                        System.out.println("------------"+existe);
                        if(existe!=1)
                        {
                            DBAccess.insertEfectv(id,princ.getSelectedYear(),superf_plant,superf_log,plant_vivas,plant_muertas,especie,tablename);
                            jTextField_SP.setText("0.0");
                            jTextField_SL.setText("0.0");
                            jTextField_PV.setText("0");
                            jTextField_PM.setText("0");
                            fillCombobox();
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
                            DBAccess.updateEfectv(id,princ.getSelectedYear(),anno,superf_plant,superf_log,plant_vivas,plant_muertas,especie,tablename);
                            fillCombobox();
                            JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamente.", "Confirmación!!!", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                            anno=princ.getSelectedYear();
                        }
                        else
                        {
                            existe=DBAccess.verificarPkey(id, princ.getSelectedYear(), tablename);
                            if(existe!=1)
                            {
                                DBAccess.updateEfectv(id,princ.getSelectedYear(),anno,superf_plant,superf_log,plant_vivas,plant_muertas,especie,tablename);
                                fillCombobox();
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
                    Logger.getLogger(Indicador1_4.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else
        {
            JOptionPane.showConfirmDialog(this, "La superficie lograda no puede \n\rser mayor que la superficie plantada.", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_AceptarActionPerformed

    private void jTextField_SPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SPKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_SP.getText());
    }//GEN-LAST:event_jTextField_SPKeyTyped

    private void jTextField_SLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SLKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_SL.getText());
    }//GEN-LAST:event_jTextField_SLKeyTyped

    private void jTextField_PVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PVKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
    }//GEN-LAST:event_jTextField_PVKeyTyped

    private void jTextField_PMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PMKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
    }//GEN-LAST:event_jTextField_PMKeyTyped

    private void fillCombobox() {
            Object datos[][];
            jComboBoxEspecie.removeAllItems();
        try {
            datos = DBAccess.getComboboxEspecie(tablename,tag,id,princ.getSelectedYear());
            for (int i = 0; i < datos.length; i++)
            {
            jComboBoxEspecie.addItem(datos[i][0].toString(), datos[i][1].toString());
            System.out.println("----------------------1515"+datos.length);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Indicador1_4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jComboBoxEspecieItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.DESELECTED)//si se escogio un dato del combo q no es el q sale por defecto y no te repite la 0operación 2veces
             return;
        if(tag.equals("modify"))
        {
            SelectedCombox();
        }
        jTextField_SP.requestFocusInWindow();
    }

    private void SelectedCombox()
    {
        try {
                int idesp = java.lang.Integer.parseInt(jComboBoxEspecie.getSelectedValue().toString());
                String columns="superf_plant,superf_log,plant_vivas ,plant_muertas";
                Integer ano = null;
                if(tag.equals("insert"))
                {
                    ano=princ.getSelectedYear();
                }
                else if(tag.equals("insert"))
                {
                    ano=anno;
                }
                Object[][] comboxEspc = DBAccess.selectComboxcEsp(id, anno, tablename, idesp,columns);
                if(comboxEspc.length>0)
                {
                    for (int i = 0; i < comboxEspc.length; i++) {
                        jTextField_SP.setText(comboxEspc[0][0].toString());
                        jTextField_SL.setText(comboxEspc[0][1].toString());
                        jTextField_PV.setText(comboxEspc[0][2].toString());
                        jTextField_PM.setText(comboxEspc[0][3].toString());
                    }
                }
                else
                {
                    jTextField_SP.setText("0.0");
                    jTextField_SL.setText("0.0");
                    jTextField_PV.setText("0");
                    jTextField_PM.setText("0");
                }
                System.out.println("----------------------dccdcd");
            } catch (SQLException ex) {
                Logger.getLogger(Indicador1_4.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

   



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Aceptar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField jTextField_PM;
    public javax.swing.JTextField jTextField_PV;
    public javax.swing.JTextField jTextField_SL;
    public javax.swing.JTextField jTextField_SP;
    // End of variables declaration//GEN-END:variables
    public Adv_ComboBox jComboBoxEspecie;

    
}
