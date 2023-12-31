/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador3_1_2.java
 *
 * Created on 27/10/2012, 07:30:22 PM
 */

package cif.gui.Criterio_3;

import cif.bd.DBAccess;
import cif.gui.JF_Principal;
import cif.manage.Manage;
import cif.manage.User;
import cif.utiles.Adv_ComboBox;
import cif.utiles.EspecieEndm;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author casa
 */
public class Indicador3_1_2 extends javax.swing.JPanel {
    private final String id;
    private final JF_Principal princ;
    private final String tablename;
    private final String tag;
    private final Object[][] result312;
    private Object[][] idEspEndm;
    EspecieEndm objEE[];
    ArrayList<EspecieEndm> adatos;

    /** Creates new form Indicador3_1_2 */
    public Indicador3_1_2(String id,JF_Principal princ,String tablename,String tag,Object[][] result312) {
        this.id=id;
        this.princ=princ;
        this.tablename=tablename;
        this.tag=tag;
        this.result312=result312;
        //initComponents();
        init();
        //******//******************//************//
        adatos=new  ArrayList<EspecieEndm>();
        DefaultListModel mdff=new DefaultListModel();
        jListDatos.setModel(mdff);
        createEspEndm();
        ShowEspEndm();
        jListEspEndm.setSelectedIndex(0);
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
        jBDer = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListDatos = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListEspEndm = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBIzq = new javax.swing.JButton();
        jBAllIzq = new javax.swing.JButton();
        jButtonAllDer = new javax.swing.JButton();
        jBAsignar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jBAceptar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));

        jBDer.setText("-->");
        jBDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDerActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jListDatos);

        jScrollPane1.setViewportView(jListEspEndm);

        jLabel2.setText("Especies Endémicas");

        jLabel3.setText("Datos");

        jBIzq.setText("<--");
        jBIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIzqActionPerformed(evt);
            }
        });

        jBAllIzq.setText("<<--");
        jBAllIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAllIzqActionPerformed(evt);
            }
        });

        jButtonAllDer.setText("-->>");
        jButtonAllDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAllDerActionPerformed(evt);
            }
        });

        jBAsignar.setText("Asignar");
        jBAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAsignarActionPerformed(evt);
            }
        });

        jLabel4.setText("Programa de Conservación");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBDer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonAllDer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBIzq, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBAllIzq, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBAsignar)
                                .addGap(31, 31, 31))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addGap(211, 211, 211)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(42, 42, 42)
                        .addComponent(jBAsignar))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jBDer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAllDer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBIzq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAllIzq)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jBAceptar.setText("Aceptar");
        jBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(532, Short.MAX_VALUE)
                .addComponent(jBAceptar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

     private void init() {

        jPanel1 = new javax.swing.JPanel();
        jBDer = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListDatos = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListEspEndm = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBIzq = new javax.swing.JButton();
        jBAllIzq = new javax.swing.JButton();
        jButtonAllDer = new javax.swing.JButton();
        jBAsignar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new Adv_ComboBox();
        fillCombobox();
        jBAceptar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));

        jBDer.setText("-->");
        jBDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDerActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jListDatos);

        jScrollPane1.setViewportView(jListEspEndm);

        jLabel2.setText("Especies Endémicas");

        jLabel3.setText("Datos");

        jBIzq.setText("<--");
        jBIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIzqActionPerformed(evt);
            }
        });

        jBAllIzq.setText("<<--");
        jBAllIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAllIzqActionPerformed(evt);
            }
        });

        jButtonAllDer.setText("-->>");
        jButtonAllDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAllDerActionPerformed(evt);
            }
        });

        jBAsignar.setText("Asignar");
        jBAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAsignarActionPerformed(evt);
            }
        });

        jLabel4.setText("Programa de Conservación");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAllDer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBDer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jBAllIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBIzq, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jBAsignar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jBDer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAllDer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBIzq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAllIzq)
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBAsignar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jBAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-accept.png")));
        jBAceptar.setText("Aceptar");
        jBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(495, Short.MAX_VALUE)
                .addComponent(jBAceptar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-

    private void jBAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAsignarActionPerformed
    int idesp = java.lang.Integer.parseInt(jComboBox1.getSelectedValue().toString());
    String name=jComboBox1.getSelectedItem().toString();
        if (!jListDatos.isSelectionEmpty())
        {
            adatos.get(jListDatos.getSelectedIndex()).setValor(idesp);
           ((DefaultListModel)jListDatos.getModel()).setElementAt(adatos.get(jListDatos.getSelectedIndex()).getNombre()+" --- Prog de Conserv: "+name ,jListDatos.getSelectedIndex());
            jComboBox1.setSelectedIndex(0);
        }
        else
        {
           JOptionPane.showConfirmDialog(this, "Debe seleccionar una Formación \n\r Forestal en la casilla: Datos.", "Atención!!!", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jBAsignarActionPerformed

    private void jBDerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDerActionPerformed
        // TODO add your handling code here:
        if(jListEspEndm.isSelectionEmpty())
        {
            return;
        }
        Derecha(jListEspEndm.getSelectedIndex());
    }//GEN-LAST:event_jBDerActionPerformed

    private void jBIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIzqActionPerformed
         if (jListDatos.isSelectionEmpty())
        {
            return;
        }
        Izquierda(jListDatos.getSelectedIndex());
    }//GEN-LAST:event_jBIzqActionPerformed

    private void jButtonAllDerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAllDerActionPerformed
        // TODO add your handling code here:
        while (jListEspEndm.getModel().getSize()>0) {
         jListEspEndm.setSelectedIndex(0);
         Derecha(0);
        }
    }//GEN-LAST:event_jButtonAllDerActionPerformed

    private void jBAllIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAllIzqActionPerformed
        while (jListDatos.getModel().getSize()>0) {
        Izquierda(0);
        }
    }//GEN-LAST:event_jBAllIzqActionPerformed

    private void jBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceptarActionPerformed
        // TODO add your handling code here:
        int existe;
        Manage manager = Manage.getInstance();
        User user = manager.getUser();
        ArrayList errors = new ArrayList();
        ArrayList<String> nameEE=new ArrayList<String>();
                for (int i = 0; i < adatos.size(); i++)
                {
                try {
                    String select = "select id, id_espendm, anno,  municipio";
                    String where = "where id='" + id + "' and municipio='" + user.getMunicipio() + "' and  id_espendm='" + adatos.get(i).getId() + "' and  anno=" + princ.getSelectedYear() + " ";
                    existe = DBAccess.verificarPkey2(select, tablename, where);
                    if (existe != 1) {
                        DBAccess.insertEspEndm(id, princ.getSelectedYear(),  adatos.get(i).getId(),  adatos.get(i).getValor(), tablename);
                    } else {
                        errors.add(new Integer(i));
                        nameEE.add(DBAccess.getnameIdentidad(adatos.get(i).getId(), "c3_1_2_especendem"));
                        continue;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Indicador3_1_2.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        if (errors.size() > 0)
            {
            String msg ="";
            String mfsg="";
            String saltoLine = null;
            String ap="\n\rpara esta entidad en el año "+princ.getSelectedYear()+".";
            String msgff="Ya existe(n) Especie(s) Endémica(s): \n\r";
            for (Integer i =0; i < errors.size(); i++)
            {
               Double multp4=Double.valueOf(i.doubleValue()/4);
                Integer multp4_I=(int) Math.floor(multp4);//integer
                Double retornomultp4=multp4-multp4_I;//se resta el decim-integ
                if(i!=0 && (retornomultp4).equals(0.0))//si el resultado es entero
                {
                    if(i!=errors.size()-1)
                  {
                      saltoLine="\n\r";
                  }
                  else if(i==errors.size()-1)
                  {
                      saltoLine="";
                  }
                }
                else
                {
                   if(i!=errors.size()-1)
                  {
                      saltoLine="-";
                  }
                  else if(i==errors.size()-1)
                  {
                      saltoLine="";
                  }
                }
                msg = msg+nameEE.get(i)+saltoLine;
            }
                mfsg = msgff+msg+ap;
                JOptionPane.showConfirmDialog(this, mfsg, "Error!!!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            }
            adatos.clear();
            ((DefaultListModel)jListDatos.getModel()).removeAllElements();
            ((DefaultListModel)jListEspEndm.getModel()).removeAllElements();
            ShowEspEndm();
            jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_jBAceptarActionPerformed

    private void createEspEndm()
    {
        try {
            String where="where active=true";
            idEspEndm = DBAccess.selectNomenclator("c3_1_2_especendem",where);
            objEE=new EspecieEndm[idEspEndm.length];
            for (int i = 0; i < idEspEndm.length; i++) {
            objEE[i]=new EspecieEndm(idEspEndm[i][0].toString(), idEspEndm[i][1].toString(),0);
            System.out.println(objEE[i].getNombre());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Indicador3_1_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ShowEspEndm()
    {
        DefaultListModel modeloEE = new DefaultListModel();
        for (int j = 0; j < objEE.length; j++) {
                modeloEE.addElement(objEE[j]);
            }
         jListEspEndm.setModel(modeloEE);       
    }

    private void fillCombobox() {
            jComboBox1.removeAllItems();
            Object datos[][];
        try {
                datos = DBAccess.selectNomenclator("si_no","");
                for (int i = 0; i < datos.length; i++) {
                    jComboBox1.addItem(datos[i][0].toString(), datos[i][1].toString());
                }
        } catch (SQLException ex) {
            Logger.getLogger(Indicador3_1_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Derecha(int index)
    {   
        DefaultListModel dmEE=(DefaultListModel) jListEspEndm.getModel();
        EspecieEndm elemEE=(EspecieEndm) dmEE.get(index);
        adatos.add(elemEE);
        ((DefaultListModel)jListDatos.getModel()).addElement(elemEE.getNombre()+" --- Prog de Conserv: No");
        dmEE.remove(index);
        jListDatos.setSelectedIndex(0);
        if (jListEspEndm.getModel().getSize()>0) {
           jListEspEndm.setSelectedIndex(0);
        }
    }

    private void Izquierda(int index)
    {
        DefaultListModel dfmEE=(DefaultListModel) jListEspEndm.getModel();
        dfmEE.addElement(adatos.get(index));
        adatos.remove(index);
        ((DefaultListModel) jListDatos.getModel()).remove(index);
        if (jListDatos.getModel().getSize()>0) {
           jListDatos.setSelectedIndex(0);
        }
        jListEspEndm.setSelectedIndex(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jBAllIzq;
    private javax.swing.JButton jBAsignar;
    private javax.swing.JButton jBDer;
    private javax.swing.JButton jBIzq;
    private javax.swing.JButton jButtonAllDer;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jListDatos;
    private javax.swing.JList jListEspEndm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
    public Adv_ComboBox jComboBox1;
}
