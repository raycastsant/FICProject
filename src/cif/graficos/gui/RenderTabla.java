/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.graficos.gui;

/**
 *
 * @author Raisel
 */
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderTabla extends JCheckBox implements TableCellRenderer
{
   public RenderTabla()
   {

   }

    public Component getTableCellRendererComponent(JTable table, Object value,
       boolean isSelected, boolean hasFocus, int row, int column)
    {
       this.setSelected((Boolean)value);
       return this;
    }
}