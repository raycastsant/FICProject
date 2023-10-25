package cif.reportes.utils;

import java.awt.Color;
import java.awt.Component;
import java.util.HashSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

  public class SubTotalCellRender extends DefaultTableCellRenderer
  {
        int TotalRowIndex;
        HashSet<Integer> rows;

        public SubTotalCellRender(int _TotalRowIndex, HashSet<Integer> _rows)
        {
         TotalRowIndex = _TotalRowIndex;
         rows = _rows;
        }
        
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
//        {
//            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//            if(rows.contains(row))
//            {
//             cell.setBackground(new Color(170, 210, 208));
//
//            }
//            else
//            if(row == TotalRowIndex)
//            {
//             cell.setBackground(Color.BLUE);
//             cell.setForeground(Color.WHITE);
//            }
//            else
//            {
//             cell.setBackground(Color.WHITE);
//             cell.setForeground(Color.BLACK);
//            }
//
//            return cell;
//        }

         public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column)
         {
          setEnabled(table == null || table.isEnabled()); // see question above

                if(rows.contains(row))
                 setBackground(new Color(170, 210, 208));
                else
                if(row == TotalRowIndex)
                {
                 setBackground(Color.BLUE);
                 setForeground(Color.WHITE);
                }
                else
                {
                 setBackground(Color.WHITE);
                 setForeground(Color.BLACK);
                }

          super.getTableCellRendererComponent(table, value, selected, focused, row, column);

          return this;
         }
} 