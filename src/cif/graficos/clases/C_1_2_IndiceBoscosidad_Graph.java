package cif.graficos.clases;

import cif.graficos.gui.jDYearsSelection;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.ReportQuerys;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Raisel
 */
public class C_1_2_IndiceBoscosidad_Graph extends AbstractGraphic
{
    public void drawGraphic(User user, BaseUnits bu) throws SQLException
    {
     tempTableName = "c_1_2_graphicdata";
     tempTableScript = "CREATE TABLE c_1_2_graphicdata(bosques_nat double precision, plantacion_estb double precision, superficie_geog double precision, anno integer) WITH (OIDS=FALSE); ALTER TABLE c_1_2_graphicdata OWNER TO postgres;";
     showGraphic(user, bu);
    }

    @Override
    protected void drawGraphic_AP()
    {
       String []years = getYears(ReportQuerys.G_SQL_1_2_AP_YEARS);
       if(years != null)
       {
           jDYearsSelection dialog = new jDYearsSelection(null, true, years, this);
           dialog.setVisible(true);

           if(!selectedYears.equals(""))
           {
               String sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_AP, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   frameTitle = "Gráfico del Criterio 1_2. Nivel Áreas protegidas";
                   YLabelValue = "Ha";
                   Draw(db.getColumnNames());
               }
           }
           else
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario");
       }
       else
        JOptionPane.showMessageDialog(null, "No existe información para el criterio seleccionado");
    }

    @Override
    protected void drawGraphic_US()
    {
       String []years = getYears(ReportQuerys.G_SQL_1_2_US_YEARS);
       if(years != null)
       {
           jDYearsSelection dialog = new jDYearsSelection(null, true, years, this);
           dialog.setVisible(true);

           if(!selectedYears.equals(""))
           {
               String sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_US, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   frameTitle = "Gráfico del Criterio 1_2. Nivel Unidad Silvícola";
                   YLabelValue = "Ha";
                   Draw(db.getColumnNames());
               }
           }
           else
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario");
       }
       else
        JOptionPane.showMessageDialog(null, "No existe información para el criterio seleccionado");
    }

    @Override
    protected void drawGraphic_EFI()
    {
       String []years = getYears(ReportQuerys.G_SQL_1_2_US_YEARS);
       if(years != null)
       {
           jDYearsSelection dialog = new jDYearsSelection(null, true, years, this);
           dialog.setVisible(true);

           if(!selectedYears.equals(""))
           {
               String sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_EFI, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   frameTitle = "Gráfico del Criterio 1_2. Nivel EFI";
                   YLabelValue = "Ha";
                   Draw(db.getColumnNames());
               }
           }
           else
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario");
       }
       else
        JOptionPane.showMessageDialog(null, "No existe información para el criterio seleccionado");
    }

    @Override
    protected void drawGraphic_MUN() throws SQLException
    {
       String []years = getYears(getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_MUN_YEARS_US, user.getIdEntidad()), getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_MUN_YEARS_AP, user.getIdEntidad()),
                                 getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_MUN_YEARS_OTROS, user.getIdEntidad()));
       if(years != null)
       {
           jDYearsSelection dialog = new jDYearsSelection(null, true, years, this);
           dialog.setVisible(true);

           if(!selectedYears.equals(""))
           {
               verifyTempTableExistence();

               String sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_MUN_AP, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_MUN_US, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_MUN_OTROS, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = ReportQuerys.G_SQL_1_2_Temp_Table;
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   frameTitle = "Gráfico del Criterio 1_2. Nivel Municipal";
                   YLabelValue = "Ha";
                   Draw(db.getColumnNames());
               }
           }
           else
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario");
       }
       else
        JOptionPane.showMessageDialog(null, "No existe información para el criterio seleccionado");
    }

    @Override
    protected void drawGraphic_MUN_Especific_Entity(BaseUnits bu)
    {
       String []years = null;

       if(bu.equals(BaseUnits.Area_Protegida))
        years = getYears(getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_MUN_YEARS_AP, user.getIdEntidad()));
       else
       if(bu.equals(BaseUnits.Unidad_Silvicola))
        years = getYears(getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_MUN_YEARS_US, user.getIdEntidad()));
       else
       if(bu.equals(BaseUnits.Otros))
        years = getYears(getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_MUN_YEARS_OTROS, user.getIdEntidad()));

       if(years != null)
       {
           jDYearsSelection dialog = new jDYearsSelection(null, true, years, this);
           dialog.setVisible(true);

           if(!selectedYears.equals(""))
           {
               verifyTempTableExistence();

               String sql = "";
               if(bu.equals(BaseUnits.Area_Protegida))
               {
                sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_MUN_AP, user.getIdEntidad(), selectedYears);
                frameTitle = "Gráfico del Criterio 1_2. Nivel Municipal-Área protegida";
               }
               else
               if(bu.equals(BaseUnits.Unidad_Silvicola))
               {
                sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_MUN_US, user.getIdEntidad(), selectedYears);
                frameTitle = "Gráfico del Criterio 1_2. Nivel Municipal-Unidad Silvícola";
               }
               else
               if(bu.equals(BaseUnits.Otros))
               {
                sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_MUN_OTROS, user.getIdEntidad(), selectedYears);
                frameTitle = "Gráfico del Criterio 1_2. Nivel Municipal-Otros";
               }

               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = ReportQuerys.G_SQL_1_2_Temp_Table;
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   YLabelValue = "Ha";
                   Draw(db.getColumnNames());
               }
           }
           else
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario");
       }
       else
        JOptionPane.showMessageDialog(null, "No existe información para el criterio seleccionado");
    }

    @Override
    protected void drawGraphic_PROV()
    {
       String []years = getYears(getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_PROV_YEARS_US, user.getIdEntidad()), getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_PROV_YEARS_AP, user.getIdEntidad()),
                                 getSQLReplacementIdEnt(ReportQuerys.G_SQL_1_2_PROV_YEARS_OTROS, user.getIdEntidad()));
       if(years != null)
       {
           jDYearsSelection dialog = new jDYearsSelection(null, true, years, this);
           dialog.setVisible(true);

           if(!selectedYears.equals(""))
           {
               verifyTempTableExistence();

               String sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_PROV_AP, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_PROV_US, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = getSQLReplacement(ReportQuerys.G_SQL_1_2_PROV_OTROS, user.getIdEntidad(), selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = ReportQuerys.G_SQL_1_2_Temp_Table;
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   frameTitle = "Gráfico del Criterio 1_2. Nivel Provincial";
                   YLabelValue = "Ha";
                   Draw(db.getColumnNames());
              }
           }
           else
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario");
       }
       else
        JOptionPane.showMessageDialog(null, "No existe información para el criterio seleccionado");
    }

    @Override
    protected void drawGraphic_NAC()
    {
       String usyears = ReportQuerys.G_SQL_1_2_PROV_YEARS_US;
       usyears = usyears.replaceFirst("where municipios.provincia=':id'", "");

       String apyears = ReportQuerys.G_SQL_1_2_PROV_YEARS_AP;
       apyears = apyears.replaceFirst("where municipios.provincia=':id'", "");

       String otrosyears = ReportQuerys.G_SQL_1_2_PROV_YEARS_OTROS;
       otrosyears = otrosyears.replaceFirst("where municipios.provincia=':id'", "");

       String []years = getYears(usyears, apyears, otrosyears);
       if(years != null)
       {
           jDYearsSelection dialog = new jDYearsSelection(null, true, years, this);
           dialog.setVisible(true);

           if(!selectedYears.equals(""))
           {
               verifyTempTableExistence();

               String sql = ReportQuerys.G_SQL_1_2_PROV_AP;
               sql = sql.replaceFirst("municipios.provincia=':id' and", "");
               sql = getSQLReplacement(sql, selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = ReportQuerys.G_SQL_1_2_PROV_US;
               sql = sql.replaceFirst("municipios.provincia=':id' and", "");
               sql = getSQLReplacement(sql, selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = ReportQuerys.G_SQL_1_2_PROV_OTROS;
               sql = sql.replaceFirst("municipios.provincia=':id' and", "");
               sql = getSQLReplacement(sql, selectedYears);
               db.executeQuery(sql);
               if(!db.isEmpty())
                manageTempData();

               sql = ReportQuerys.G_SQL_1_2_Temp_Table;
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   frameTitle = "Gráfico del Criterio 1.2. Nivel Nacional";
                   YLabelValue = "ha";
                   Draw(db.getColumnNames());
               }
           }
           else
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario");
       }
       else
        JOptionPane.showMessageDialog(null, "No existe información para el criterio seleccionado");
    }

    private void manageTempData()
    {
        int lastColIndex = db.getColumnCount()-1;
        Object [][]data = db.getAllRows();
        int rowCount = db.getRowCount();

        for(int i=0; i<rowCount; i++)
        {
         String anno = data[i][lastColIndex].toString();
         db.executeQuery("select * from "+tempTableName+" where anno="+anno);

         if(db.isEmpty()) //No existe el año, hay que insertarlo
          db.executeQuery("insert into "+tempTableName+"(bosques_nat, plantacion_estb, superficie_geog, anno) values("+data[i][0]+", "+data[i][1]+", "+data[i][2]+", "+anno+")");
         else
          db.executeQuery("update "+tempTableName+" set bosques_nat="+data[i][0]+"+bosques_nat,plantacion_estb="+data[i][1]+"+plantacion_estb,superficie_geog="+data[i][2]+"+superficie_geog where anno="+anno);
        }
    }
}

