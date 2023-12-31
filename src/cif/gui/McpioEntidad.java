/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * McpioEntidad.java
 *
 * Created on 13/12/2012, 01:38:24 PM
 */

package cif.gui;

import cif.bd.DBAccess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

/**
 *
 * @author juliette
 */
public class McpioEntidad extends javax.swing.JDialog {
    String id=null;
    private  Object[][] arrayids;
    String nameids;
    private final String tableEntity;
    private Integer checkNum;
    public boolean  okPressed;
    private JRadioButton JRadioButton;
    ButtonGroup buttonGroup1;
    private JRadioButton tempRB;


    /** Creates new form McpioEntidad */
    public McpioEntidad(Object[][] arrayids,String tableEntity) {
        this.arrayids=arrayids;
        this.tableEntity=tableEntity;
        init();
        jPanelEntity.setLayout(new GridLayout(0, 1, 0, 0));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = getSize();
        Point p = new Point((dim.width/2)-(d.width/2),(dim.height/2)-(d.height/2));
        setLocation(p);
        PutButtons();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelEntity = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Entidades");

        jPanelEntity.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEntity.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelEntity.setAutoscrolls(true);

        javax.swing.GroupLayout jPanelEntityLayout = new javax.swing.GroupLayout(jPanelEntity);
        jPanelEntity.setLayout(jPanelEntityLayout);
        jPanelEntityLayout.setHorizontalGroup(
            jPanelEntityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
        jPanelEntityLayout.setVerticalGroup(
            jPanelEntityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Seleccione una Entidad:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelEntity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelEntity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         if(id==null || id.equals(""))
           {             
             id=tempRB.getName();
             setId(id);
             okPressed=true;
             dispose();
              /* JOptionPane.showConfirmDialog(this, "Debe seleccionar una Entidad.", "Advertencia!!!", JOptionPane.CLOSED_OPTION);
               okPressed=false;      */
           }
       else
        {               
             okPressed=true;
             dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
     private void init() {
         //setSize(175,20);
        jPanelEntity = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        setTitle("Entidades");

        jPanelEntity.setBackground(new java.awt.Color(255, 255, 255));
        JScrollPane jscrollPane= new JScrollPane();
        jscrollPane.setViewportView(jPanelEntity);
        //jPanelEntity.setAutoscrolls(true);

        javax.swing.GroupLayout jPanelEntityLayout = new javax.swing.GroupLayout(jPanelEntity);
        jPanelEntity.setLayout(jPanelEntityLayout);
        jPanelEntityLayout.setHorizontalGroup(
            jPanelEntityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );
        jPanelEntityLayout.setVerticalGroup(
            jPanelEntityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-accept.png")));
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jscrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
 //----------------tarecos del checkbox----------------------
    private void PutButtons()
    {
    	jPanelEntity.removeAll();
        buttonGroup1 = new ButtonGroup();
	for(int i=0; i<arrayids.length; i++)
    	{
            try {
                    nameids = DBAccess.getnameIdentidad(arrayids[i][0].toString(), tableEntity);
                    JRadioButton = new JRadioButton(nameids);
                    JRadioButton.setName(arrayids[i][0].toString());
                    JRadioButton.setBackground(Color.WHITE);
                    JRadioButton.addActionListener(new ItemListenerJRButton(JRadioButton)); 
                    if(i==0)
                    {
                        tempRB=JRadioButton;
                        tempRB.setSelected(true);
                    }
                    buttonGroup1.add(JRadioButton);
                    jPanelEntity.add(JRadioButton);
            } catch (SQLException ex) {
                Logger.getLogger(McpioEntidad.class.getName()).log(Level.SEVERE, null, ex);
            }
      	}
    	jPanelEntity.repaint();
    	jPanelEntity.validate();
	}

    class ItemListenerJRButton implements java.awt.event.ActionListener
    {
    	javax.swing.JRadioButton c_box;
    	public ItemListenerJRButton(JRadioButton cb)
    	{
            c_box = cb;
        }
            /*@Override
            public void itemStateChanged(ItemEvent arg0)
            {
                CheckitemStateChanged(arg0,c_box);
            }*/

        public void actionPerformed(ActionEvent e) {
           if(c_box.isSelected())
    	{
            //Integer n=java.lang.Integer.parseInt(check.getName());
            //id=arrayids[n][0].toString();
               id=c_box.getName();
               setId(id);
    	}
        else
        {
            id="";
            setId(id);
        }
        }
    }


    /*private void CheckitemStateChanged(java.awt.event.ItemEvent evt,javax.swing.JCheckBox check)
    {
    	if(check.isSelected())
    	{
            Integer n=java.lang.Integer.parseInt(check.getName());
            id=arrayids[n][0].toString();      
    	}
        else
        {
            id="";
        }
    	
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelEntity;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JLabel jLabel2;
}
