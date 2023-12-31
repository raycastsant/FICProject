/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador5_5.java
 *
 * Created on 27/10/2012, 11:35:57 PM
 */

package cif.gui.Criterio_5;

import cif.bd.DBAccess;
import cif.gui.JF_Principal;
import cif.objects.C_5_5_Object;
import cif.utiles.utiles;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Raisel
 */
public class Indicador5_5_Modify extends javax.swing.JPanel
{
    private String id;
    private String mun;
    private final String tablename;
//    private String tag;
    private JF_Principal princ;
    private int anno;

    /** Creates new form Indicador5_4 */
    public Indicador5_5_Modify(C_5_5_Object []categorias, String id, int anno, String tablename, JF_Principal princ, String mun)
    {
        this.id = id;
        this.princ = princ;
        this.tablename = tablename;
//        this.tag = tag;
        this.anno = anno;
        this.mun = mun;

        initComponents();

        if(categorias != null)
        {
            for(int i=0; i<categorias.length; i++)
             addCategoriaToList(categorias[i]);

            jTFTotTrabajadores.setText(categorias[0].getTotalValue().toString());
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
        jScrollPane3 = new javax.swing.JScrollPane();
        modelSeleccionados = new DefaultListModel();
        jLSeleccionados = new javax.swing.JList(modelSeleccionados);
        jLabel3 = new javax.swing.JLabel();
        jBAsignar = new javax.swing.JButton();
        jLCant = new javax.swing.JLabel();
        jTFCant = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTFTotTrabajadores = new javax.swing.JTextField();
        jBAceptar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));

        jLSeleccionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLSeleccionadosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jLSeleccionados);

        jLabel3.setText("Datos");

        jBAsignar.setText("Asignar");
        jBAsignar.setEnabled(false);
        jBAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAsignarActionPerformed(evt);
            }
        });

        jLCant.setText("Cantidad");
        jLCant.setEnabled(false);

        jTFCant.setEnabled(false);
        jTFCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCantKeyTyped(evt);
            }
        });

        jLabel1.setText("Total de trabajadores:");

        jTFTotTrabajadores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFTotTrabajadoresKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFCant, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jTFTotTrabajadores, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLCant)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jBAsignar)
                                .addGap(17, 17, 17))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFTotTrabajadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jLCant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAsignar)
                        .addGap(84, 84, 84))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jBAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-accept.png"))); // NOI18N
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
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(222, Short.MAX_VALUE)
                .addComponent(jBAceptar)
                .addGap(29, 29, 29))
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

    private void jBAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAsignarActionPerformed
        // TODO add your handling code here:
         int index = jLSeleccionados.getSelectedIndex();
         C_5_5_Object object = (C_5_5_Object)jLSeleccionados.getSelectedValue();
         String cantValue = jTFCant.getText();
         int cant = 0;

         if(!cantValue.trim().equals(""))
          cant = Integer.parseInt(cantValue);

         object.setCantMuj(cant);

         modelSeleccionados.removeElement(object);
         modelSeleccionados.add(index, object);

         SetFielValuesEnabled(false);
    }//GEN-LAST:event_jBAsignarActionPerformed

    private void jTFCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCantKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
    }//GEN-LAST:event_jTFCantKeyTyped

    private void jBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceptarActionPerformed
        // TODO add your handling code here:
      try {
            int womanCount = 0;
            int existe = 0;
            String existen = "";
            ArrayList<C_5_5_Object> listaAct = new ArrayList<C_5_5_Object>();

            int total = Integer.parseInt(jTFTotTrabajadores.getText());
            
            for(int i=0; i<modelSeleccionados.size(); i++)
            {
             C_5_5_Object objt = (C_5_5_Object)modelSeleccionados.get(i);
             womanCount += objt.getCantMuj();

             if(anno != princ.getSelectedYear())
              existe = DBAccess.verificarPkey2("select id,anno,municipio ", tablename, " where id='"+id+"' and municipio='"+mun+"' " +
                                              "and anno="+princ.getSelectedYear()+" and id_catg="+objt.getCateg().Id());
             if(existe != 1)
              listaAct.add(objt);
             else
              existen += objt.getCateg().Nombre()+",";

             existe = 0;
            }

            if(total <womanCount)
            {
             JOptionPane.showMessageDialog(null, "La cantidad de mujeres insertadas "+"\n"+"" +
                                                 "debe ser menor o igual que la cantidad total de trabajadores");
             return;
            }

            if(existen.length() > 0)
            {
             existen = existen.substring(0, existen.length()-1);
             String mens = "Las siguientes categorias ya existen en el sistema para el periodo "+princ.getSelectedYear()+": "+"\n"+existen+"\n";
             mens += "Los datos de estos no se actualizarán!!!";
             JOptionPane.showMessageDialog(null, mens);
            }

            if(listaAct.size() > 0)
             ActualizarCategorias(listaAct, total);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar elementos");
        }
}//GEN-LAST:event_jBAceptarActionPerformed

    private void jLSeleccionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLSeleccionadosMouseClicked
        // TODO add your handling code here:
        if(jLSeleccionados.getSelectedIndex() >= 0)
        {
         SetFielValuesEnabled(true);
         SetFieldValues((C_5_5_Object)jLSeleccionados.getSelectedValue());
        }
    }//GEN-LAST:event_jLSeleccionadosMouseClicked

    private void jTFTotTrabajadoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTotTrabajadoresKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumberInteger(evt);
}//GEN-LAST:event_jTFTotTrabajadoresKeyTyped

    private void restartFieldValues()
    {
     jTFCant.setText("0");
    }

    private void SetFielValuesEnabled(boolean flag)
    {
     restartFieldValues();
     jLCant.setEnabled(flag);
     jTFCant.setEnabled(flag);
     jBAsignar.setEnabled(flag);
    }   

    private void addCategoriaToList(C_5_5_Object serv)
    {
     modelSeleccionados.addElement(serv);
    }

    private void SetFieldValues(C_5_5_Object object)
    {
     jTFCant.setText(object.getCantMuj().toString());
    }

    private void ActualizarCategorias(ArrayList<C_5_5_Object> listaAct, int totalValue) throws SQLException
    {
     C_5_5_Object object = null;
     
     for(int i=0; i<listaAct.size(); i++)
     {
      object = (C_5_5_Object)listaAct.get(i);
      DBAccess.updateCatgOcupacional(id, princ.getSelectedYear(), anno, object.getCateg().Id(), object.getCantMuj(), tablename);
     }

     object = (C_5_5_Object)listaAct.get(0);
     DBAccess.updateCatgOcupacionalTOTAL(tablename, object.getTotalId(), totalValue);

     SetFielValuesEnabled(false);
     JOptionPane.showMessageDialog(null, "Elementos actualizados correctamente");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jBAsignar;
    private javax.swing.JLabel jLCant;
    private javax.swing.JList jLSeleccionados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFCant;
    private javax.swing.JTextField jTFTotTrabajadores;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel modelSeleccionados;
}