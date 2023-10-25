/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jDGraphicSeleccion.java
 *
 * Created on 26-feb-2013, 11:48:25
 */

package cif.graficos.gui;

import cif.graficos.clases.AbstractGraphic;
import cif.graficos.enums.EGraphicTypes;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Raisel
 */
public class jDGraphicSeleccion extends javax.swing.JDialog
{
    private AbstractGraphic graph;
//    private int selectionCount;
    
    /** Creates new form jDGraphicSeleccion */
    public jDGraphicSeleccion(java.awt.Frame parent, boolean modal, AbstractGraphic graph, String []columns)
    {
        super(parent, modal);
        initComponents();
        setTitle("Opciones de gráfico");
        this.graph = graph;
        setLocationRelativeTo(null);
        setResizable(false);

        Object[][] data = new Object[columns.length-1][2];
//        selectionCount = 0;
        for(int i=0; i<columns.length-1; i++)
        {
         data[i][0] = new Boolean(true);
         data[i][1] = columns[i];
//         selectionCount++;
        }

        table.setModel(new MyTableModel(data));
        table.getTableHeader().setReorderingAllowed(false);

        TableColumn columna = table.getColumnModel().getColumn(0);
        columna.setMaxWidth(20);
        columna.setMinWidth(20);
        columna.setPreferredWidth(20);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgtipografico = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jRBPastel = new javax.swing.JRadioButton();
        jRBBarras = new javax.swing.JRadioButton();
        jRBLineas = new javax.swing.JRadioButton();
        jBAceptar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de gráfico"));
        bgtipografico.add(jRBPastel);
        bgtipografico.add(jRBBarras);
        bgtipografico.add(jRBLineas);

        jRBPastel.setText("Pastel");
        jRBPastel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPastelActionPerformed(evt);
            }
        });

        jRBBarras.setSelected(true);
        jRBBarras.setText("Barras");

        jRBLineas.setText("Líneas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRBPastel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jRBBarras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRBLineas)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBPastel)
                    .addComponent(jRBLineas)
                    .addComponent(jRBBarras))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBAceptar.setText("Aceptar");
        jBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAceptarActionPerformed(evt);
            }
        });

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {  },
            new String [] {"Años"}
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAceptar)
                    .addComponent(jBCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAceptarActionPerformed
        // TODO add your handling code here:
      int count = setColumnIndexes();

      if(count<1)
      {
       JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un campo", "Error", JOptionPane.WARNING_MESSAGE);
       return;
      }
      else
      if(count>1 && jRBPastel.isSelected() && graph.getSelecteYears().indexOf(',')>0)
      {
       JOptionPane.showMessageDialog(null, "Para el tipo de gráfico PASTEL debe seleccionar solamente 1 campo", "Error", JOptionPane.WARNING_MESSAGE);
       return;
      }
      
      if(jRBPastel.isSelected())
       graph.setType(EGraphicTypes.PASTEL);
      else
      if(jRBBarras.isSelected())
       graph.setType(EGraphicTypes.BARRAS);
      else
      if(jRBLineas.isSelected())
       graph.setType(EGraphicTypes.LINEAS);
//      else
//       graph.setType(null);

      dispose();
    }//GEN-LAST:event_jBAceptarActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        // TODO add your handling code here:
        graph.setType(null);
        dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        graph.setType(null);
    }//GEN-LAST:event_formWindowClosing

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
//      int row = table.rowAtPoint(evt.getPoint());
//      int col = table.columnAtPoint(evt.getPoint());
//
//      if(col == 0)
//      {
//       boolean flag = Boolean.parseBoolean(table.getValueAt(row, col).toString());
//       if(flag)
//        selectionCount++;
//       else
//        selectionCount--;
//      }
//
//      if(selectionCount>1 && jRBPastel.isSelected())
//      {
//       table.setValueAt(new Boolean("false"), row, col);
//       selectionCount--;
//      }
//      else
//       jRBPastel.setEnabled(selectionCount<=1);
//
//      jBAceptar.setEnabled(selectionCount>0);
//
//      System.out.println(selectionCount);
    }//GEN-LAST:event_tableMouseClicked

    private void jRBPastelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPastelActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jRBPastelActionPerformed

   class MyTableModel extends AbstractTableModel
   {
    private String[] columnNames = {"", "Campos"};

    private Object[][] data;

    public final Object[] longValues = {Boolean.TRUE, "Bn"};

    public MyTableModel(Object[][] data)
    {
     this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void setValueAt(Object value, int row, int col)
    {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col)
    {
      if(col == 0)
       return true;
      else
       return false;
    }
}

   private int setColumnIndexes()
   {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i=0; i<table.getRowCount(); i++)
    {
         boolean selected = Boolean.parseBoolean(table.getValueAt(i,0).toString());
         if(selected)
         {
//           String field = table.getValueAt(i,1).toString();
           list.add(i);
         }
    }

    graph.setColumnIndexes(list);

    return list.size();
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgtipografico;
    private javax.swing.JButton jBAceptar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRBBarras;
    private javax.swing.JRadioButton jRBLineas;
    private javax.swing.JRadioButton jRBPastel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}