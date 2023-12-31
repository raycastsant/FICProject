/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Indicador3_1_1.java
 *
 * Created on 27/10/2012, 06:24:24 PM
 */

package cif.gui.Criterio_3;

import cif.bd.DBAccess;
import cif.gui.JF_Principal;
import cif.manage.Manage;
import cif.manage.User;
import cif.utiles.FormForestal;
import cif.utiles.utiles;
import java.awt.Container;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author casa
 */
public class Indicador3_1_1_Mod extends javax.swing.JPanel {
    private final String tablename;
    JF_Principal princ;
     private  int anno;
    private final Object[][] result311,idsap;
    private Object[][] idFF;
    FormForestal[] objFF;
    Hashtable<String,ArrayList<FormForestal>> datos;
    ArrayList<String> ids;
    //ashtable<String,DefaultListModel> FF;
    /** Creates new form Indicador3_1_1 */
    public Indicador3_1_1_Mod(Object[][] idsap,JF_Principal princ,String tablename,Object[][] result311) {
        this.idsap=idsap;
        this.princ=princ;
        this.tablename=tablename;
        this.result311=result311;
        initComponents();
        DefaultListModel mdff=new DefaultListModel();
        jList_Datos.setModel(mdff);
        anno=princ.getSelectedYear();
        //----------------//*------------------
        datos= new Hashtable<String, ArrayList<FormForestal>>();
        ids=new  ArrayList<String>();
        createFF();
        ShowFF();
        jList_AreaProt.setSelectedIndex(0);
        jList_Datos.setSelectedIndex(0);
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
        jTextField_Sup = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_Datos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_AreaProt = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton_Ok = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del indicador"));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jTextField_Sup.setText("0.0");
        jTextField_Sup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_SupKeyTyped(evt);
            }
        });

        jList_Datos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_Datos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_DatosValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList_Datos);

        jList_AreaProt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_AreaProt.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_AreaProtValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList_AreaProt);

        jLabel1.setText("Áreas Protegidas");

        jLabel3.setText("Datos");

        jButton_Ok.setText("Asignar");
        jButton_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OkActionPerformed(evt);
            }
        });

        jLabel4.setText("Superficie(ha)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addGap(133, 133, 133)
                        .addComponent(jLabel3)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Sup, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton_Ok)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Ok)))
                .addContainerGap())
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

    private void jButton_OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OkActionPerformed
       Double superf=Double.parseDouble(jTextField_Sup.getText());
        if (!jList_Datos.isSelectionEmpty())
        {
         datos.get(ids.get(jList_AreaProt.getSelectedIndex())).get(jList_Datos.getSelectedIndex()).setValor(superf);
         ((DefaultListModel)jList_Datos.getModel()).setElementAt(datos.get(ids.get(jList_AreaProt.getSelectedIndex())).get(jList_Datos.getSelectedIndex()).getNombre()+" --- Superficie: "+superf, jList_Datos.getSelectedIndex());
        }
        else
        {
           JOptionPane.showConfirmDialog(this, "Debe seleccionar una Formación \n\r Forestal en la casilla: Datos.", "Atención!!!", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton_OkActionPerformed

    private void jTextField_SupKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SupKeyTyped
        // TODO add your handling code here:
        utiles.SoloNumber(evt,jTextField_Sup.getText());
    }//GEN-LAST:event_jTextField_SupKeyTyped

    private void jList_AreaProtValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_AreaProtValueChanged
    DefaultListModel modelodatos = new DefaultListModel();
    if(!datos.isEmpty())
    {
        for (int i = 0; i < datos.get(ids.get(jList_AreaProt.getSelectedIndex())).size(); i++)
        {
            Double value=datos.get(ids.get(jList_AreaProt.getSelectedIndex())).get(i).getValor();
            modelodatos.addElement(datos.get(ids.get(jList_AreaProt.getSelectedIndex())).get(i).getNombre() + " --- Superficie: " + value);
        }
        jList_Datos.setModel(modelodatos);
        jList_Datos.setSelectedIndex(0);
        Double valueJtf = datos.get(ids.get(jList_AreaProt.getSelectedIndex())).get(jList_Datos.getSelectedIndex()).getValor();
        jTextField_Sup.setText(valueJtf.toString());
    }
  
    }//GEN-LAST:event_jList_AreaProtValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

            // TODO add your handling code here:
            int existe;
            Boolean existe2=false;
            Boolean exist=false;
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            ArrayList<FormForestal> adatosOK=new ArrayList<FormForestal>();
            ArrayList<String> nameAps=new ArrayList<String>();
            ArrayList<String> nameFF=new ArrayList<String>();
            ArrayList errors = new ArrayList();
            Enumeration<String> allkeys=datos.keys();
            Hashtable<String, ArrayList<String>> err= new Hashtable<String, ArrayList<String>>();
            if(anno==princ.getSelectedYear())
            {
                for (int i = 0; i < ids.size(); i++)
                {
                    adatosOK=datos.get(ids.get(i));
                    for (int j = 0; j < adatosOK.size(); j++)
                    {
                    try {
                        DBAccess.updateFormacForestal(ids.get(i), princ.getSelectedYear(), anno, adatosOK.get(j).getId(), adatosOK.get(j).getValor(), tablename);
                    } catch (SQLException ex) {
                        Logger.getLogger(Indicador3_1_1_Mod.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                }
            }
            else
            {
                for (int k = 0; k < ids.size(); k++)
                {
                    adatosOK=datos.get(ids.get(k));
                    for (int h = 0; h < adatosOK.size(); h++)
                    {
                    try {
                        String select = "select id, id_formforest, anno, area, municipio";
                        String where = "where id='" + ids.get(k) + "' and municipio='" + user.getMunicipio() + "' and  id_formforest='" + adatosOK.get(h).getId() + "' and  anno=" + princ.getSelectedYear() + "";
                        existe = DBAccess.verificarPkey2(select, tablename, where);
                        if (existe == 1)
                        {
                            errors.add(new Integer(h));
                            if(err.size()==0)
                            {
                                nameFF.add(adatosOK.get(h).getNombre());
                                err.put(DBAccess.getnameIdentidad(ids.get(k), "area_protegida"), nameFF);
                            }
                            else
                            {
                                exist=err.containsKey(DBAccess.getnameIdentidad(ids.get(k), "area_protegida"));
                                if(exist)
                                {
                                   nameFF= err.get(DBAccess.getnameIdentidad(ids.get(k), "area_protegida"));
                                   nameFF.add(adatosOK.get(h).getNombre());
                                   err.put(DBAccess.getnameIdentidad(ids.get(k), "area_protegida"), nameFF);
                                }
                                else
                                {
                                    ArrayList<String> otro=new ArrayList<String>();
                                    otro.add(adatosOK.get(h).getNombre());
                                    err.put(DBAccess.getnameIdentidad(ids.get(k), "area_protegida"), otro);
                                }
                            }
                        continue;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Indicador3_1_1_Mod.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                }
            }
            if (errors.size() > 0)
            {
                String[] errkeys=err.keySet().toArray(new String[0]);

                String tres ="";
            String mfsg="";
            String cuatro="";
            String saltoLine;
            String ap="\n\ren el año "+princ.getSelectedYear()+".";
            String[] allkeyserr=datos.keySet().toArray(new String[0]);
                 for (int l = 0; l < errkeys.length; l++)
                {
                try {
                    String idaperr = allkeyserr[l];
                    String nameidaperr = DBAccess.getnameIdentidad(idaperr, "area_protegida");
                    String uno = "\n\r(*)-En el Área Protegida: '" + nameidaperr + "' ya existe(n) la(s) Formación(es) Forestal(es):\n\r";
                    String dos = "";
                    for (Integer i = 0; i < err.get(nameidaperr).size(); i++) {
                        Double multp4 = Double.valueOf(i.doubleValue() / 4);
                        Integer multp4_I = (int) Math.floor(multp4); //integer
                        Double retornomultp4 = multp4 - multp4_I; //se resta el decim-integ
                        if (i != 0 && (retornomultp4).equals(0.0)) {
                            saltoLine = "\n\r";
                            if (i == errors.size() - 1) {
                                saltoLine = "";
                            }
                        } else {
                            saltoLine = "-";
                        }
                        dos = dos + err.get(nameidaperr).get(i) + saltoLine;
                    }
                    tres = uno + dos + tres;
                } catch (SQLException ex) {
                    Logger.getLogger(Indicador3_1_1_Mod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                JOptionPane.showConfirmDialog(this, tres+ap, "Error!!!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                jTextField_Sup.setText("0.0");
            }
             else
                {
                for (int i = 0; i < ids.size(); i++)
                {
                    adatosOK=datos.get(ids.get(i));
                    for (int j = 0; j < adatosOK.size(); j++)
                    {
                    try {
                        DBAccess.updateFormacForestal(ids.get(i), princ.getSelectedYear(), anno, adatosOK.get(j).getId(), adatosOK.get(j).getValor(), tablename);
                    } catch (SQLException ex) {
                        Logger.getLogger(Indicador3_1_1_Mod.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                }
                anno = princ.getSelectedYear();
                JOptionPane.showConfirmDialog(this, "Los datos han sido guardados satisfactoriamente.", "Confirmación!!!", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
                jTextField_Sup.setText("0.0");
             }                             
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList_DatosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_DatosValueChanged
        // TODO add your handling code here:
        Double value=datos.get(ids.get(jList_AreaProt.getSelectedIndex())).get(jList_Datos.getSelectedIndex()).getValor();
        jTextField_Sup.setText(value.toString());
    }//GEN-LAST:event_jList_DatosValueChanged

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        if(evt.getAncestorParent() instanceof Container)
        {
            jTextField_Sup.requestFocusInWindow();
        }
    }//GEN-LAST:event_jPanel1AncestorAdded

    private String[] getAPnames()
    {
       String namesap[] = new String[idsap.length];
        for (int i = 0; i < idsap.length; i++) {
            try {
                //try {
                namesap[i] = DBAccess.getnameIdentidad(idsap[i][0].toString(), "area_protegida");
            } catch (SQLException ex) {
                Logger.getLogger(Indicador3_1_1_Mod.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return namesap;
    }

    private void createFF()
    {
        objFF=new FormForestal[result311.length];
            for (int i = 0; i <result311.length; i++) {
                try {
                    objFF[i] = new FormForestal(result311[i][1].toString(), DBAccess.getnameIdentidad(result311[i][1].toString(), "c3_1_1_formacionforestal"), java.lang.Double.parseDouble(result311[i][2].toString()));
                } catch (SQLException ex) {
                    Logger.getLogger(Indicador3_1_1_Mod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    private void ShowFF()
    {
        DefaultListModel modeloAP = new DefaultListModel();
        ArrayList<FormForestal> arrayFF=new ArrayList<FormForestal>();
        boolean exist;
        for (int j = 0; j < objFF.length; j++) 
        {
            if(datos.size()==0)
            {
                arrayFF.add(objFF[j].Clone());
                datos.put(result311[j][0].toString(), arrayFF);
            }
            else
            {
                exist=datos.containsKey(result311[j][0].toString());
                if(exist)
                {
                   arrayFF= datos.get(result311[j][0].toString());
                   arrayFF.add(objFF[j].Clone());
                   datos.put(result311[j][0].toString(), arrayFF);
                }
                else
                {
                    ArrayList<FormForestal> otro=new ArrayList<FormForestal>();
                    otro.add(objFF[j].Clone());
                    datos.put(result311[j][0].toString(), otro);
                }
            }
        }

        
       //- Enumeration<String> allkeys=datos.keys();
        String[] allkeys=datos.keySet().toArray(new String[0]);
            //while(allkeys.hasMoreElements())
         for (int i = 0; i < allkeys.length; i++)
            {
            try {
                String idapHT = allkeys[i];
                ids.add(idapHT);
                modeloAP.addElement(DBAccess.getnameIdentidad(idapHT, "area_protegida"));
            } catch (SQLException ex) {
                Logger.getLogger(Indicador3_1_1_Mod.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        jList_AreaProt.setModel(modeloAP);
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList_AreaProt;
    private javax.swing.JList jList_Datos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField_Sup;
    // End of variables declaration//GEN-END:variables

}
