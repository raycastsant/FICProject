package cif.reportes.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

  public class TotalCellRender extends DefaultTableCellRenderer
  {
        int ColorRowIndex;

        public TotalCellRender(int _ColorRowIndex)
        {
         ColorRowIndex = _ColorRowIndex;
        }
        
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
//        {
//            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//            if(row == ColorRowIndex)
//            {
//             cell.setBackground(Color.BLUE);
//             cell.setForeground(Color.WHITE);
//            }
//            else
//            {
//             cell.setBackground(Color.WHITE);
//             cell.setForeground(Color.BLACK);
//            }
            
//            return cell;
//        }

         public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column)
         {
          setEnabled(table == null || table.isEnabled()); // see question above

            if(row == ColorRowIndex)
            {
             setBackground(Color.BLUE);
             setForeground(Color.WHITE);
            }
           else
           {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
           }

          /*if ((row % 2) == 0)
           setBackground(Color.green);
          else
           setBackground(null);*/

          super.getTableCellRendererComponent(table, value, selected, focused, row, column);

          return this;
         }
} 