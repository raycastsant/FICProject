 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;

/**
*
* @author Raisel
*/
public class C_5_2_Accidentes_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_5_2_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_5_2_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_5_2_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_5_2_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "" , "" , "" , db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString()};
     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
    String prov = "";
    String mun = "";
    String accidentes_par= "";
    String accidentes_mort= "";
    String tipoEntidad = "";
    Object []row = null;
    Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME
                                       , CONSTANTS.ACCIDENTES_PAR2_COLUMN_NAME, CONSTANTS.ACCIDENTES_MORTALES2_COLUMN_NAME};
    DefaultTableModel deftm = new DefaultTableModel(columNames, 0);
    Double totaccidentes_par = new Double(0);
    Double totaccidentes_mort = new Double(0);

  String sql = getSQLReplacement(ReportQuerys.SQL_5_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);
  if(!db.isEmpty())
  {
      prov = db.getValueAt(0,0).toString();
      mun = db.getValueAt(0,1).toString();
      accidentes_par = db.getValueAt(0,2).toString();
      accidentes_mort = db.getValueAt(0,3).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
      row = new String []{prov, mun, tipoEntidad, accidentes_par, accidentes_mort};
      deftm.addRow(row);
      totaccidentes_par+= Double.parseDouble(accidentes_par);
      totaccidentes_mort+= Double.parseDouble(accidentes_mort);
    }

  sql = getSQLReplacement(ReportQuerys.SQL_5_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);

  if(!db.isEmpty())
  {
      prov = db.getValueAt(0,0).toString();
      mun = db.getValueAt(0,1).toString();
      accidentes_par = db.getValueAt(0,2).toString();
      accidentes_mort = db.getValueAt(0,3).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
      row = new String []{prov, mun, tipoEntidad, accidentes_par, accidentes_mort};

      deftm.addRow(row);
      totaccidentes_par+= Double.parseDouble(accidentes_par);
      totaccidentes_mort+= Double.parseDouble(accidentes_mort);
  }

  sql = getSQLReplacement(ReportQuerys.SQL_5_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);

  if(!db.isEmpty())
  {
      prov = db.getValueAt(0,0).toString();
      mun = db.getValueAt(0,1).toString();
      accidentes_par = db.getValueAt(0,2).toString();
      accidentes_mort = db.getValueAt(0,3).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
      row = new String []{prov, mun, tipoEntidad, accidentes_par, accidentes_mort};

      deftm.addRow(row);
      totaccidentes_par+= Double.parseDouble(accidentes_par);
      totaccidentes_mort+= Double.parseDouble(accidentes_mort);
 }

 row = new String []{"TOTAL", "", "", totaccidentes_par.toString(), totaccidentes_mort.toString()};
 deftm.addRow(row);

 return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME
                                       , CONSTANTS.ACCIDENTES_PAR2_COLUMN_NAME, CONSTANTS.ACCIDENTES_MORTALES2_COLUMN_NAME};

  DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

  Totals rowTotals = new Totals(0, 0);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_5_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), deftm, 0, 0);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_5_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totaccidentes_par, rowTotals.totaccidentes_mort);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_5_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totaccidentes_par, rowTotals.totaccidentes_mort);

  row = new String []{"", "TOTAL", rowTotals.totaccidentes_par.toString(), rowTotals.totaccidentes_mort.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_PROV_DESGLOSE()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME
                                       , CONSTANTS.ACCIDENTES_PAR2_COLUMN_NAME, CONSTANTS.ACCIDENTES_MORTALES2_COLUMN_NAME};

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0, 0);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_5_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(),
                                           deftm, 0, 0, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_5_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totaccidentes_par, rowTotals.totaccidentes_mort, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_5_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totaccidentes_par, rowTotals.totaccidentes_mort, CONSTANTS.ENTIDAD_OTROS);

  row = new String []{"", "TOTAL", "", rowTotals.totaccidentes_par.toString(), rowTotals.totaccidentes_mort.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME
                                       , CONSTANTS.ACCIDENTES_PAR2_COLUMN_NAME, CONSTANTS.ACCIDENTES_MORTALES2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0, 0);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_5_2_PROV_SubGrupoUS, anno.toString(), deftm, 0, 0);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_5_2_PROV_SubGrupoAP, anno.toString(), deftm, rowTotals.totaccidentes_par, rowTotals.totaccidentes_mort);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_5_2_PROV_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totaccidentes_par, rowTotals.totaccidentes_mort);

   row = new String []{"TOTAL", rowTotals.totaccidentes_par.toString(), rowTotals.totaccidentes_mort.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME
                                       , CONSTANTS.ACCIDENTES_PAR2_COLUMN_NAME, CONSTANTS.ACCIDENTES_MORTALES2_COLUMN_NAME};

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0, 0);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_5_2_MUN_SubGrupoUS, anno.toString(), deftm, 0, 0, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_5_2_MUN_SubGrupoAP, anno.toString(), deftm, rowTotals.totaccidentes_par, rowTotals.totaccidentes_mort, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_5_2_MUN_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totaccidentes_par, rowTotals.totaccidentes_mort, CONSTANTS.ENTIDAD_OTROS);

   row = new String []{"TOTAL", "", "", rowTotals.totaccidentes_par.toString(), rowTotals.totaccidentes_mort.toString()};

   deftm.addRow(row);

   AddMun_Subtotals(deftm);

   return deftm;
}

private Totals updateProvincia_MunRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm
, double accidentes_parCount, double accidentes_mortCount)
{
   String prov = "";
   String mun = "";

   Double accidentes_par = new Double(0);
   Double accidentes_mort = new Double(0);

   Object []row = null;

   Double totaccidentes_par = accidentes_parCount;
   Double totaccidentes_mort = accidentes_mortCount;

   String sql = SqlPattern.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, Entidad, anno);
   db.executeQuery(sql);

if(!db.isEmpty())
{
    for(int i=0; i<db.getRowCount(); i++)
    {
        boolean rowUpdated = false;

        prov = db.getValueAt(i,0).toString();
        mun = db.getValueAt(i,1).toString();
        accidentes_par = Double.parseDouble(db.getValueAt(i,2).toString());
        totaccidentes_par += accidentes_par;
        accidentes_mort = Double.parseDouble(db.getValueAt(i,3).toString());
        totaccidentes_mort += accidentes_mort;

         if(dftm.getRowCount() > 0)
         {
              String tmMun = "";
              for(int k=0; k<dftm.getRowCount(); k++)
              {
               tmMun = dftm.getValueAt(k,1).toString();
               if(tmMun.equals(mun))
               {
                double tempaccidentes_par = Double.parseDouble(dftm.getValueAt(k,2).toString());
                dftm.setValueAt((tempaccidentes_par+accidentes_par), k, 2);
                double tempaccidentes_mort = Double.parseDouble(dftm.getValueAt(k,3).toString());
                dftm.setValueAt((tempaccidentes_mort+accidentes_mort), k, 3);
                rowUpdated = true;
                break;
               }
              }
         }

         if(!rowUpdated)
         {
          row = new String []{prov, mun, accidentes_par.toString(), accidentes_mort.toString()};
          dftm.addRow(row);
         }
     }
 }

 return new Totals(totaccidentes_par,totaccidentes_mort);
}

private Totals updateProvincia_DesgloseRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm
, double accidentes_parCount, double accidentes_mortCount, String tipoEntidad)
{
   String prov = "";
   String mun = "";

   Double accidentes_par = new Double(0);
   Double totaccidentes_par = accidentes_parCount;
   Double accidentes_mort = new Double(0);
   Double totaccidentes_mort = accidentes_mortCount;

Object []row = null;

String sql = SqlPattern.replaceFirst("where municipios.id", "where provincias.id");
sql = getSQLReplacement(sql, Entidad, anno);
db.executeQuery(sql);

if(!db.isEmpty())
{
    for(int i=0; i<db.getRowCount(); i++)
    {
        boolean rowUpdated = false;
        int rowIndex = dftm.getRowCount();

        prov = db.getValueAt(i,0).toString();
        mun = db.getValueAt(i,1).toString();

        accidentes_par = Double.parseDouble(db.getValueAt(i,2).toString());
        totaccidentes_par += accidentes_par;
        accidentes_mort = Double.parseDouble(db.getValueAt(i,3).toString());
        totaccidentes_mort += accidentes_mort;

        if(dftm.getRowCount() > 0)
        {
             String tmMun = "";
             String tmEntidad = "";
             for(int k=0; k<dftm.getRowCount(); k++)
             {
                  tmMun = dftm.getValueAt(k,1).toString();
                  tmEntidad = dftm.getValueAt(k,2).toString();
                  if((tmMun.equals(mun)) && (tmEntidad.equals(tipoEntidad)))
                  {
                   double tempaccidentes_par = Double.parseDouble(dftm.getValueAt(k,3).toString());
                   dftm.setValueAt((tempaccidentes_par+accidentes_par), k, 3);
                   double tempaccidentes_mort = Double.parseDouble(dftm.getValueAt(k,4).toString());
                   dftm.setValueAt((tempaccidentes_mort+accidentes_mort), k, 4);
                   rowUpdated = true;
                   break;
                  }
                  else
                  if(tmMun.equals(mun))
                  {
                   rowIndex = k+1;
                   break;
                  }
             }
        }

        if(!rowUpdated)
        {
         row = new String []{prov, mun, tipoEntidad
               , accidentes_par.toString(), accidentes_mort.toString()};
         dftm.insertRow(rowIndex, row);
        }
    }
}

    return new Totals(totaccidentes_par,totaccidentes_mort);
}

private Totals updateProvinciaRow(String SqlPattern, String anno, DefaultTableModel dftm
        , double accidentes_parCount, double accidentes_mortCount)
{
   String prov = "";
   Double accidentes_par = new Double(0);
   Double totaccidentes_par = accidentes_parCount;
   Double accidentes_mort = new Double(0);
   Double totaccidentes_mort = accidentes_mortCount;
   Object []row = null;

   String sql = getSQLReplacement(SqlPattern, anno);
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       for(int i=0; i<db.getRowCount(); i++)
       {
           boolean rowUpdated = false;

           prov = db.getValueAt(i,0).toString();
           accidentes_par = Double.parseDouble(db.getValueAt(i,1).toString());
           totaccidentes_par += accidentes_par;
           accidentes_mort = Double.parseDouble(db.getValueAt(i,2).toString());
           totaccidentes_mort += accidentes_mort;

           if(dftm.getRowCount() > 0)
           {
                String tmProv = "";
                for(int k=0; k<dftm.getRowCount(); k++)
                {
                 tmProv = dftm.getValueAt(k,0).toString();
                 if(tmProv.equals(prov))
                 {
                   double tempaccidentes_par = Double.parseDouble(dftm.getValueAt(k,1).toString());
                   dftm.setValueAt((tempaccidentes_par+accidentes_par), k, 1);
                   double tempaccidentes_mort = Double.parseDouble(dftm.getValueAt(k,2).toString());
                   dftm.setValueAt((tempaccidentes_mort+accidentes_mort), k, 2);
                rowUpdated = true;
                break;
               }
              }
         }

           if(!rowUpdated)
           {
            row = new String []{prov, accidentes_par.toString(), accidentes_mort.toString()};
            dftm.addRow(row);
           }
       }
   }

   return new Totals(totaccidentes_par,totaccidentes_mort);
}

private Totals updateDNF_DesgloseRow(String SqlPattern, String anno, DefaultTableModel dftm
        , double accidentes_parCount, double accidentes_mortCount, String tipoEntidad)
{
   String prov = "";
   String mun = "";
   Double accidentes_par = new Double(0);
   Double totaccidentes_par = accidentes_parCount;
   Double accidentes_mort = new Double(0);
   Double totaccidentes_mort = accidentes_mortCount;

   Object []row = null;

   String sql = SqlPattern.replaceFirst("municipios.id=':id' and", "");
   sql = getSQLReplacement(sql, anno);
   db.executeQuery(sql);

   if(!db.isEmpty())
   {
       for(int i=0; i<db.getRowCount(); i++)
       {
           prov = db.getValueAt(i,0).toString();
           mun = db.getValueAt(i,1).toString();
           accidentes_par = Double.parseDouble(db.getValueAt(i,2).toString());
           totaccidentes_par += accidentes_par;
           accidentes_mort = Double.parseDouble(db.getValueAt(i,3).toString());
           totaccidentes_mort += accidentes_mort;

           int rowIndex = getProvincia_Mun_Ent_RowIndex(prov, mun, tipoEntidad, dftm);

           if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
           {
            row = new String []{prov, mun, tipoEntidad, accidentes_par.toString(), accidentes_mort.toString()};
            dftm.addRow(row);
           }
           else
           if(rowIndex >= 0) //insertar
           {
            row = new String []{prov, mun, tipoEntidad, accidentes_par.toString(), accidentes_mort.toString()};

            dftm.insertRow(rowIndex+1, row);
           }
           else     //actualizar
           {
             rowIndex = rowIndex*(-1);
             double tempaccidentes_par = Double.parseDouble(dftm.getValueAt(rowIndex, 3).toString());
             dftm.setValueAt((tempaccidentes_par+accidentes_par), rowIndex, 3);
             double tempaccidentes_mort = Double.parseDouble(dftm.getValueAt(rowIndex, 4).toString());
             dftm.setValueAt((tempaccidentes_mort+accidentes_mort), rowIndex, 4);
           }
       }
   }

   return new Totals(totaccidentes_par,totaccidentes_mort);
}

/**Agrega los subtotales de los municipios a un DefaultTableModel*/
private void AddMun_Subtotals(DefaultTableModel TM)
{
 ArrayList<Row_Index> RI = new ArrayList<Row_Index>();
 HashMap<String, String> mun_id = new HashMap<String, String>();
 String CurrentMun = "";
 String MUN = "";
 String PROV = "";

   Double accidentes_par = new Double(0);
   Double totaccidentes_par = new Double(0);
   Double accidentes_mort = new Double(0);
   Double totaccidentes_mort = new Double(0);

 int rowCount = TM.getRowCount();
 if(TM!=null && rowCount>0)
 {
      mun_id = get_All_Mun_Id();
      CurrentMun = mun_id.get(TM.getValueAt(0,1).toString());

      for(int i=0; i<rowCount; i++)
      {
        String munValue = TM.getValueAt(i,1).toString();
        MUN = mun_id.get(munValue);
        PROV = TM.getValueAt(i,0).toString();
        accidentes_par = Double.parseDouble(TM.getValueAt(i,3).toString());
        accidentes_mort = Double.parseDouble(TM.getValueAt(i,4).toString());

        if(!PROV.equals("TOTAL"))
        {
            if(!CurrentMun.equals(MUN)) //Si cambio el municipio
            {
              CurrentMun = MUN;
              String []row = new String []{"", "Sub-Total", "", totaccidentes_par.toString(), totaccidentes_mort.toString()};
              RI.add(new Row_Index(row, i));
              totaccidentes_par = accidentes_par;
              totaccidentes_mort = accidentes_mort;
            }
            else
            {
              totaccidentes_par += accidentes_par;
              totaccidentes_mort += accidentes_mort;
            }
        }
        else
        {
           String []row = new String []{"", "Sub-Total", "", totaccidentes_par.toString(), totaccidentes_mort.toString()};
           RI.add(new Row_Index(row, i));
        }
      } //Fin for
 }

   insert_Mun_SubTotals(TM, RI);
}

    @Override
    protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
    {
            String accidentes_par= "";
            String accidentes_mort= "";

           String entidad = "";
           Object []columNames = null;
           DefaultTableModel deftm = null;
           String sql = "";

            Double totaccidentes_par = new Double(0);
            Double totaccidentes_mort = new Double(0);

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.ACCIDENTES_PAR2_COLUMN_NAME, CONSTANTS.ACCIDENTES_MORTALES2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_5_2_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.ACCIDENTES_PAR2_COLUMN_NAME, CONSTANTS.ACCIDENTES_MORTALES2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_5_2_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.ACCIDENTES_PAR2_COLUMN_NAME, CONSTANTS.ACCIDENTES_MORTALES2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_5_2_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
       }

        deftm = new DefaultTableModel(columNames, 0);
        db.executeQuery(sql);
        System.out.println(sql);
        if(!db.isEmpty())
        {
             for(int i=0; i<db.getRowCount(); i++)
             {
              Object obj = db.getValueAt(i,0);
              if(obj == null)
               entidad = "";
              else
               entidad = obj.toString();

              accidentes_par = db.getValueAt(i,1).toString();
              accidentes_mort = db.getValueAt(i,2).toString();

              deftm.addRow(new String []{entidad, accidentes_par, accidentes_mort});
              totaccidentes_par+= Double.parseDouble(accidentes_par);
              totaccidentes_mort+= Double.parseDouble(accidentes_mort);
             }

             deftm.addRow(new String []{"TOTAL", totaccidentes_par.toString(), totaccidentes_mort.toString()});
        }

       return deftm;
    }
    
 private class Totals
 {
   Double totaccidentes_par = new Double(0);
   Double totaccidentes_mort = new Double(0);

  public Totals(double accidentes_par,double accidentes_mort)
  {
   totaccidentes_par = accidentes_par;
   totaccidentes_mort = accidentes_mort;
  }
 }
}

