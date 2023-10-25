/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.clases;

import cif.bd.DBAccess;
import cif.bd.JDBCAdapter;
import cif.bd.TableSorter;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.manage.UserTypes;


import cif.reportes.utils.TotalCellRender;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Raisel
 */
public abstract class AbstractCritReport implements IReportModel
{
    private TableModel TM;
    private JTable table;
    protected JDBCAdapter db;
    protected User user;
    protected Integer anno;
    protected boolean desglose;
    protected BaseUnits bu;

    protected abstract TableModel getModel_AP();
    
    protected abstract TableModel getModel_US();

    protected abstract TableModel getModel_EFI();

    protected abstract TableModel getModel_MUN();

    protected abstract TableModel getModel_MUN_Especific_Entity(BaseUnits bu);

    protected abstract TableModel getModel_PROV();

    protected abstract TableModel getModel_PROV_DESGLOSE();

    protected abstract TableModel getModel_NAC();

    protected abstract TableModel getModel_NAC_DESGLOSE();

    protected JTable getReportModel(User _user, Integer _anno, boolean _desglose, BaseUnits _bu) throws SQLException
    {
     this.user = _user;
     this.anno = _anno;
     this.desglose = _desglose;
     this.bu = _bu;

     TM = new DefaultTableModel();
     table = new JTable();
     db = DBAccess.getconnection();

      if(user.getType() == UserTypes.Unidad_Silvicola) //Reporte por Unidad_Silvicola --------------------------------------------------------------
      {
       TM = getModel_US();
       table.setModel(new TableSorter(TM));

       hide_Column(table, 1);
       Set_Color_To_TOTALRow(table, 0, "TOTAL");
      }
      else
      if(user.getType() == UserTypes.Area_Protegida) //Reporte por Area_Protegida-------------------------------------------------------------------
      {
       TM = getModel_AP();
       table.setModel(new TableSorter(TM));

       hide_Column(table, 1);
       Set_Color_To_TOTALRow(table, 0, "TOTAL");
      }
      else
      if(user.getType() == UserTypes.EFI) //Reporte por EFI ------------------------------------------------------------------------------------
      {
       TM = getModel_EFI();
       table.setModel(new TableSorter(TM));

       hide_Column(table, 1);
       hide_Column(table, 1);
//       hide_Column(table, 2);
       Set_Color_To_SUB_TOTALRow(table, 0, "Total");
//       Set_Color_To_TOTALRow(table, 0, "TOTAL");
      }
      else
      if(user.getType() == UserTypes.Municipal) //Reporte por Municipio --------------------------------------------------------------------------
      {
       if(bu == null)
       {
           TM = getModel_MUN();
           table.setModel(new TableSorter(TM));

           hide_Column(table, 0);
           hide_Column(table, 0);
//           hide_Column(table, 1);
           Set_Color_To_SUB_TOTALRow(table, 0, "Total");
       }
       else
       {
           TM = getModel_MUN_Especific_Entity(bu);
           table.setModel(new TableSorter(TM));
           Set_Color_To_TOTALRow(table, 0, "TOTAL");
       }
      }
      else
      if(user.getType() == UserTypes.Provincial) //Reporte por Provincia -----------------------------------------------------------------------
      {
         if(desglose)
         {
          TM = getModel_PROV_DESGLOSE();
          table.setModel(new TableSorter(TM));

          Set_Color_To_SUB_TOTALRow(table, 1, "Total");
//          Set_Color_To_TOTALRow(table, 1, "TOTAL");
         }
         else
         {
          TM = getModel_PROV();
          table.setModel(new TableSorter(TM));

          Set_Color_To_TOTALRow(table, 1, "TOTAL");
         }

         hide_Column(table, 0);
      }
      else
      if(user.getType() == UserTypes.Nacional) //Reporte Nacional -----------------------------------------------------------------------
      {
         if(desglose)
         {
          TM = getModel_NAC_DESGLOSE();
         }
         else
         {
          TM = getModel_NAC();
         }

         table.setModel(new TableSorter(TM));

         Set_Color_To_SUB_TOTALRow(table, 1, "Sub-Total");
      }

      table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

      for(int i=0; i<table.getColumnCount(); i++)
      {
          TableColumn columna = table.getColumnModel().getColumn(i);
          String headerValue = columna.getHeaderValue().toString();
          int preferedSize = headerValue.length()*7;

                for(int k=0; k<table.getRowCount(); k++)
                {
                     Object value = table.getValueAt(k,i);
                     if(value != null)
                     {
                         int valueSize = value.toString().length()*8;
                         if(valueSize > preferedSize)
                          preferedSize = valueSize;
                     }
                }
//          columna.setMaxWidth(headreValue.length()*10);
          columna.setPreferredWidth(preferedSize);
      }

      return table;
    }

    protected String getSQLReplacement(String _sql, String entity, String anno)
    {
       String sql = null;
       sql = _sql.replaceFirst(":id", entity);
       sql = sql.replaceFirst(":anno", anno);

       return sql;
    }

    protected String getSQLReplacement(String _sql, String anno)
    {
       String sql = null;
       sql = _sql.replaceFirst(":anno", anno);

       return sql;
    }

    /**Devuelve el indice de la fila a insertar para el
     desglose Nacional por Provincia-Municipio-Unidad Básica.
     Devuelve el index negativo como señal de que la fila debe actualizarse en vez de insertar una nueva.
     Se asume que el indice de las columnas es el siguiente: Provincia-0, Municipio-1, Tipo de Entidad-2*/
    protected int getProvincia_Mun_Ent_RowIndex(String Prov, String Mun, String Ent, DefaultTableModel tm)
    {
         int rowIndex = 0;     //tm.getRowCount()-1;
         String tmProv = "";
         String tmMun = "";
         String tmEnt = "";

         for(int k=0; k<tm.getRowCount(); k++)
         {
          tmProv = tm.getValueAt(k,0).toString();
          tmMun = tm.getValueAt(k,1).toString();
          tmEnt = tm.getValueAt(k,2).toString();

          if(Prov.equals(tmProv))
          {
            if(Mun.equals(tmMun))
            {
              if(Ent.equals(tmEnt))
              {
                rowIndex = k*(-1);
                break;
              }
              else
               rowIndex = k;
            }
            else
             continue;   // rowIndex = k;
          }
          else
           continue;
         }

         return rowIndex;
    }

    /**Devuelve el indice de la fila a insertar para el
     desglose Provincial para criterios con tablas Foraneas, por Provincia-Municipio-Unidad Básica-Valor de la tabla Foranea.
     Se asume que el indice de las columnas es el siguiente: Provincia-0, Municipio-1, Tipo de Entidad-2 FKValue-3*/
    protected int getProvincia_Mun_Ent_FKValue_RowIndex(String Prov, String Mun, String Ent, DefaultTableModel tm)
    {
         int rowIndex = 0;     //tm.getRowCount()-1;
         String tmProv = "";
         String tmMun = "";
         String tmEnt = "";
//         String tmFKValue = "";

         if(tm.getRowCount()>0)
          rowIndex = tm.getRowCount()-1;

         for(int k=0; k<tm.getRowCount(); k++)
         {
          tmProv = tm.getValueAt(k,0).toString();
          tmMun = tm.getValueAt(k,1).toString();
          tmEnt = tm.getValueAt(k,2).toString();
//          tmFKValue = tm.getValueAt(k,3).toString();

          if(Prov.equals(tmProv))
          {
            if(Mun.equals(tmMun))
            {
              rowIndex = k;
              if(Ent.equals(tmEnt))
               rowIndex = k;
            }
            else
             continue;   // rowIndex = k;
          }
          else
           continue;
         }

         return rowIndex;
    }
    
    private void hide_Column(JTable table, int columnIndex)
    {
     if(table.getColumnCount() > 0)
     {
         TableColumn columna = table.getColumnModel().getColumn(columnIndex);
         table.getColumnModel().removeColumn(columna);
//         columna.setMaxWidth(0);
//         columna.setMinWidth(0);
//         columna.setHeaderValue("");
//         columna.setPreferredWidth(0);
     }   
    }

    private void Set_Color_To_TOTALRow(JTable table, int patternColumn, String patternString)
    {
     if(table.getColumnCount()>0 && table.getRowCount()>0)
     {
         for(int row=0; row<table.getRowCount(); row++)
         {
           String value = table.getValueAt(row, patternColumn).toString();
           if(value.equals(patternString))
           {
            for(int i=0; i<table.getColumnCount(); i++)
             table.getColumnModel().getColumn(i).setCellRenderer(new TotalCellRender(row));  //table.getColumnModel().getColumn(i).setCellRenderer(new TotalCellRender(row));
           }
         }
     }
    }

    private void Set_Color_To_SUB_TOTALRow(JTable table, int subTotalColumn, String subTotalString)
    {
     if(!(this instanceof  C_3_1_2_EspeciesEndemicas_Model))
     {
         HashSet rowsIndexs = new HashSet();
         int rowCount = table.getRowCount();

         for(int row=0; row<rowCount; row++)
         {
           Object value = table.getValueAt(row, subTotalColumn);
           if(value == null)
            continue;

           if(value.toString().equals(subTotalString))
            rowsIndexs.add(row);
           else
           if(row == rowCount-1)
           {
            for(int i=0; i<table.getColumnCount(); i++)
             table.getColumnModel().getColumn(i).setCellRenderer(new SubTotalCellRender(row, rowsIndexs));
           }
         }
     }
    }

    /**Obtener todos los municipios con sus ID en un HashMap.
     FALTA AMARAR LOS MUNICIPIOS A LAS PROVINCIAS PARA QUE
     * SEA EFECTIVO*/
    protected HashMap<String, String> get_All_Mun_Id()
    {
     HashMap<String, String> mun_id = new HashMap<String, String>();

     db.executeQuery("select id, nombre from municipios");
     if(!db.isEmpty())
     {
        for(int i=0; i<db.getRowCount(); i++)
        {
            String id = db.getValueAt(i,0).toString();
            String mun = db.getValueAt(i,1).toString();
            mun_id.put(mun, id);
        }
     }

     return mun_id;
    }

    /**Inserta los subtotales de los municipios*/
    protected void insert_Mun_SubTotals(DefaultTableModel TM, ArrayList<Row_Index> RI)
    {
     int indexStep = 0;
     for(int i=0; i<RI.size(); i++)
     {
      Row_Index rowIndex = RI.get(i);
      TM.insertRow((rowIndex.index+indexStep), rowIndex.row);
      indexStep++;
     }
    }

    protected Double Redondear(Double valor)
    {
        Double res = new Double(0);
        System.out.println(valor);
        
        if(valor!=null && !valor.toString().equals("NaN"))
        {
	      String val = valor+"";
	      BigDecimal big = new BigDecimal(val);
	      big = big.setScale(3, RoundingMode.HALF_UP);

              res = new Double(big.toString());
        }

        return res;
    }

  //=========================================================================
    protected class Row_Index
    {
     String []row;
     int index;

     public Row_Index(String []_row, int _index)
     {
      row = _row;
      index = _index;
     }
    }
}