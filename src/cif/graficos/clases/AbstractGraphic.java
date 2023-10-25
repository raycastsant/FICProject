/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.graficos.clases;

import cif.graficos.interfaces.IGraphic;
import cif.bd.DBAccess;
import cif.bd.JDBCAdapter;
import cif.graficos.enums.EGraphicTypes;
import cif.graficos.gui.jDGraphicSeleccion;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.manage.UserTypes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author Raisel
 */
public abstract class AbstractGraphic implements IGraphic
{
    protected String title;
    protected String frameTitle;
    protected JDBCAdapter db;
    protected User user;
//    protected Integer anno;
    protected BaseUnits bu;
    protected EGraphicTypes type;
    protected String selectedYears;
//    protected String []yearsArray;
    protected ArrayList<Integer> colIndexes;
    protected String YLabelValue;
    protected String tempTableName;
    protected String tempTableScript;
    

    /**Crea un gráfico de pastel*/
    private void build3DPie()
    {
        int lastIndex = db.getColumnCount()-1;  

        DefaultPieDataset data = new DefaultPieDataset();

        if(selectedYears.indexOf(',') > 0)     //Más de un año seleccionado
        {
            int index = colIndexes.get(0);
            String colname = db.getColumnName(index);
            title = "Representación del valor "+colname;
            for(int i=0; i<db.getRowCount(); i++)
            {
             String year = db.getValueAt(i, lastIndex).toString();
             Double value = Double.parseDouble(db.getValueAt(i, index).toString());
             data.setValue(year, value);
            }
        }
        else                       //Solo un año seleccionado
        {
            title = "Valores para el año "+selectedYears;
            for(int i=0; i<db.getRowCount(); i++)
            {
                 String valueYear = db.getValueAt(i, lastIndex).toString();
                 if(selectedYears.equals(valueYear))
                 {
                     for(int k=0; k<colIndexes.size(); k++)
                     {
                         Double value = Double.parseDouble(db.getValueAt(i, k).toString());
                         String columnName = JDBCAdapter.UpCaseFirstCharacter(db.getColumnName(colIndexes.get(k)));
                         data.setValue(columnName, value);
                     }
                 }
            }
        }
        
        JFreeChart chart = ChartFactory.createPieChart3D(title, data, true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2} (ha)"));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.8f);

        ChartFrame frame = new ChartFrame(frameTitle, chart);
        frame.pack();
        frame.setVisible(true);
    }

    /**Crea un gráfico de barras*/
    private void build3DBar()
    {
     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
     Double value;
     int lastIndex = db.getColumnCount()-1;

     for(int i=0; i<db.getRowCount(); i++)
     {
         String year = db.getValueAt(i, lastIndex).toString();
         for(int k=0; k<colIndexes.size(); k++)
         {
           int c = colIndexes.get(k);
           value = Double.parseDouble(db.getValueAt(i, c).toString());
           dataset.setValue(value, db.getColumnName(c), year);
         }
     }
         
        JFreeChart chart = ChartFactory.createBarChart3D("Gráfico de barras", "Años", YLabelValue, dataset, PlotOrientation.VERTICAL, true,
                                                         true, false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangeGridlinePaint(Color.BLACK);

        CategoryItemRenderer renderer = plot.getRenderer();
        CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0.00"));
        renderer.setItemLabelGenerator(generator);
        renderer.setItemLabelsVisible(true);

        ChartFrame frame = new ChartFrame(frameTitle, chart);
        frame.pack();
        frame.setVisible(true);
    }

    /**Crea un gráfico de líneas*/
    private void buildLines()
    {
     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
     Double value;
     int lastIndex = db.getColumnCount()-1;

     for(int i=0; i<db.getRowCount(); i++)
     {
         String year = db.getValueAt(i, lastIndex).toString();
         for(int k=0; k<colIndexes.size(); k++)
         {
           int c = colIndexes.get(k);
           value = Double.parseDouble(db.getValueAt(i, c).toString());
           dataset.setValue(value, db.getColumnName(c), year);
         }
     }

        JFreeChart chart = ChartFactory.createLineChart("Gráfico de líneas", "Años", YLabelValue, dataset, PlotOrientation.VERTICAL, true,
                                                         true, false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangeGridlinePaint(Color.BLACK);

        CategoryItemRenderer renderer = plot.getRenderer();
        CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0.00"));
        renderer.setItemLabelGenerator(generator);
        renderer.setItemLabelsVisible(true);

        LineAndShapeRenderer renderer1 = (LineAndShapeRenderer) plot.getRenderer();
        renderer1.setStroke(new BasicStroke(2f, BasicStroke.JOIN_ROUND, BasicStroke.JOIN_BEVEL));
        renderer1.setShapesVisible(true);
        renderer1.setDrawOutlines(true);
        renderer1.setUseFillPaint(true);
        renderer1.setFillPaint(Color.BLACK);   //color de relleno del cuadro
        renderer1.setSeriesPaint(0,Color.BLACK);

        ChartFrame frame = new ChartFrame(frameTitle, chart);
        frame.pack();
        frame.setVisible(true);
    }

    private void addValuesToHashSet(String sql, HashSet<String> hashset)
    {
         db.executeQuery(sql);
         if(!db.isEmpty())
         {
           for(int i=0; i<db.getRowCount(); i++)
            hashset.add(db.getValueAt(i,0).toString());
         }
    }

    protected abstract void drawGraphic_AP();
    
    protected abstract void drawGraphic_US();

    protected abstract void drawGraphic_EFI();

    protected abstract void drawGraphic_MUN() throws SQLException;

    protected abstract void drawGraphic_MUN_Especific_Entity(BaseUnits bu);

    protected abstract void drawGraphic_PROV();

    protected abstract void drawGraphic_NAC();

    /**Reemplaza la entidad y los años pasados
     * por parámetros en una consulta SQL*/
    protected String getSQLReplacement(String _sql, String entity, String annos)
    {
       String sql = null;
       sql = _sql.replaceFirst(":id", entity);
       sql = sql.replaceFirst(":anno", annos);

       return sql;
    }

    /**Reemplaza los años pasados
     * por parámetros en una consulta SQL*/
    protected String getSQLReplacement(String _sql, String annos)
    {
       String sql = null;
       sql = _sql.replaceFirst(":anno", annos);

       return sql;
    }

      /**Reemplaza el id de entidad pasado por parametro*/
    protected String getSQLReplacementIdEnt(String _sql, String id)
    {
       String sql = null;
       sql = _sql.replaceFirst(":id", id);

       return sql;
    }

    /**Obtiene los años para los que existen datos*/
    protected String[]getYears(String sqlYears)
    {
     String []result = null;

     db.executeQuery(sqlYears);
     if(!db.isEmpty())
     {
       result = new String[db.getRowCount()];
       for(int i=0; i<db.getRowCount(); i++)
        result[i] = db.getValueAt(i,0).toString();
     }
    
     return result;
    }

    /**Obtiene los años para los que existen datos*/
    protected String[] getYears(String sqlYearsUS, String sqlYearsAP, String sqlYearsOTROS)
    {
     HashSet<String> annos = new HashSet<String>();
     
     addValuesToHashSet(sqlYearsUS, annos);
     addValuesToHashSet(sqlYearsAP, annos);
     addValuesToHashSet(sqlYearsOTROS, annos);

     Object []array = annos.toArray();
     String []result = new String[array.length];

     
//     for(int i=0; i<array.length; i++)
//     {
        Object temp;
        int t = array.length;
        for (int i = 1; i < t; i++)
        {
            for (int k = t- 1; k >= i; k--)
            {
                if(Integer.parseInt(array[k].toString()) < Integer.parseInt(array[k-1].toString()))
                {
                    temp = array[k];
                    array[k] = array[k-1];
                    array[k-1] = temp;
                }//fin if
            }// fin 2 for
        }//fin 1 for
//     }

       for(int i=0; i<array.length; i++)
        result[i] = array[i].toString();

     if(result.length < 1)
      result = null;
    
     return result;
    }
    
    /**Obtiene los valores de un arreglo Integer
     en una cadena String separados por ,*/
    protected String getIntegerArrayAsString(int []array)
    {
     String res = ""   ;

     if(array != null)
     for(int i=0; i<array.length; i++)
      res += array[i]+",";

     res = res.substring(0, res.length()-1);

     return res;
    }

    /**Crear el gráfico para el nivel establecido*/
    protected void Draw(String []columns)
    {
     jDGraphicSeleccion dialog = new jDGraphicSeleccion(null, true, this, columns);
     dialog.setVisible(true);

      if(type == EGraphicTypes.PASTEL)
       build3DPie();
      else
      if(type == EGraphicTypes.BARRAS)
       build3DBar();
      else
      if(type == EGraphicTypes.LINEAS)
       buildLines();
    }

    protected void verifyTempTableExistence()
    {
     db.executeQuery("select * from pg_tables where tablename='"+tempTableName+"'");
     if(db.isEmpty())
      db.executeQuery(tempTableScript);
     else
      db.executeQuery("delete from "+tempTableName);
    }

    /**Método principal.
     Distribuye las llamadas de creación de los gráficos
     según el nivel seleccionado*/
    public void showGraphic(User user, BaseUnits bu) throws SQLException
    {
     this.user = user;
     this.bu = bu;

     db = DBAccess.getconnection();

          if(user.getType() == UserTypes.Area_Protegida) //Reporte por Area_Protegida-------------------------------------------------------------------
          {
           drawGraphic_AP();
          }
          else
          if(user.getType() == UserTypes.Unidad_Silvicola) //Reporte por Unidad_Silvicola --------------------------------------------------------------
          {
           drawGraphic_US();
          }
          else
          if(user.getType() == UserTypes.EFI) //Reporte por EFI ------------------------------------------------------------------------------------
          {
            drawGraphic_EFI();
          }
          else
          if(user.getType() == UserTypes.Municipal) //Reporte por Municipio --------------------------------------------------------------------------
          {
            if(bu == null)
             drawGraphic_MUN();
            else
             drawGraphic_MUN_Especific_Entity(bu);
          }
          else
          if(user.getType() == UserTypes.Provincial) //Reporte por Provincia --------------------------------------------------------------------------
          {
            drawGraphic_PROV();
          }
          else
          if(user.getType() == UserTypes.Nacional) //Reporte Nacional --------------------------------------------------------------------------
          {
            drawGraphic_NAC();
          }
    }

    public void setType(EGraphicTypes type)
    {
     this.type = type;
    }

    public void setSelectedYears(String value)
    {
     this.selectedYears = value;
    }

    public void setColumnIndexes(ArrayList<Integer> list)
    {
     this.colIndexes = list;
    }

    public String getSelecteYears()
    {
      return selectedYears;
    }
}