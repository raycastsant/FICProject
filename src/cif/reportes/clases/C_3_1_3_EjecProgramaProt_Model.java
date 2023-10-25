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
public class C_3_1_3_EjecProgramaProt_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "", "", "", "", db.getValueAt(0,1).toString()};
     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
    String prov = "";
    String mun = "";
    String fkValue = "";
    String superficie= "";
    String tipoEntidad = "";
    Object []row = null;
    Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.PROGRAMA_PROT2_COLUMN_NAME
                                       , CONSTANTS.SUPERFICIE2_COLUMN_NAME};
    DefaultTableModel deftm = new DefaultTableModel(columNames, 0);
    Double totsuperficie = new Double(0);

  String sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);
  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      superficie = db.getValueAt(i,3).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
      row = new String []{prov, mun, tipoEntidad, fkValue, superficie};
      deftm.addRow(row);
      totsuperficie+= Double.parseDouble(superficie);
   }
  }

  sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);

  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      superficie = db.getValueAt(i,3).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
      row = new String []{prov, mun, tipoEntidad, fkValue, superficie};

      deftm.addRow(row);
      totsuperficie+= Double.parseDouble(superficie);
   }
  }

  sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);

  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      superficie = db.getValueAt(i,3).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
      row = new String []{prov, mun, tipoEntidad, fkValue, superficie};

      deftm.addRow(row);
      totsuperficie+= Double.parseDouble(superficie);
   }
 }

 row = new String []{"TOTAL", "", "", "", totsuperficie.toString()};
 deftm.addRow(row);

 return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.PROGRAMA_PROT2_COLUMN_NAME
                                       , CONSTANTS.SUPERFICIE2_COLUMN_NAME};

  DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

  Totals rowTotals = new Totals(0);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), deftm, 0);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totsuperficie);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totsuperficie);

  row = new String []{"", "TOTAL", "", rowTotals.totsuperficie.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_PROV_DESGLOSE()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.PROGRAMA_PROT2_COLUMN_NAME
                                       , CONSTANTS.SUPERFICIE2_COLUMN_NAME};

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(),
                                           deftm, 0, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totsuperficie, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totsuperficie, CONSTANTS.ENTIDAD_OTROS);

  row = new String []{"", "TOTAL", "", "", rowTotals.totsuperficie.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.PROGRAMA_PROT2_COLUMN_NAME
                                       , CONSTANTS.SUPERFICIE2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_3_1_3_PROV_SubGrupoUS, anno.toString(), deftm, 0);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_3_1_3_PROV_SubGrupoAP, anno.toString(), deftm, rowTotals.totsuperficie);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_3_1_3_PROV_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totsuperficie);

   row = new String []{"TOTAL", "", rowTotals.totsuperficie.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.PROGRAMA_PROT2_COLUMN_NAME
                                       , CONSTANTS.SUPERFICIE2_COLUMN_NAME};

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoUS, anno.toString(), deftm, 0, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoAP, anno.toString(), deftm, rowTotals.totsuperficie, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_3_1_3_MUN_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totsuperficie, CONSTANTS.ENTIDAD_OTROS);

   row = new String []{"TOTAL", "", "", "", rowTotals.totsuperficie.toString()};

   deftm.addRow(row);

   AddMun_Subtotals(deftm);

   return deftm;
}

private Totals updateProvincia_MunRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm
, double superficieCount)
{
   String prov = "";
   String mun = "";
   String fkValue = "";

   Double superficie = new Double(0);

   Object []row = null;

   Double totsuperficie = superficieCount;

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
        fkValue = db.getValueAt(i,2).toString();
        superficie = Double.parseDouble(db.getValueAt(i,3).toString());
        totsuperficie += superficie;

         if(dftm.getRowCount() > 0)
         {
              String tmMun = "";
              String tmfkValue = "";
              for(int k=0; k<dftm.getRowCount(); k++)
              {
               tmMun = dftm.getValueAt(k,1).toString();
               tmfkValue = dftm.getValueAt(k,2).toString();
               if(tmMun.equals(mun) && tmfkValue.equals(fkValue))
               {
                double tempsuperficie = Double.parseDouble(dftm.getValueAt(k,3).toString());
                dftm.setValueAt((tempsuperficie+superficie), k, 3);
                rowUpdated = true;
                break;
               }
              }
         }

         if(!rowUpdated)
         {
          row = new String []{prov, mun, fkValue, superficie.toString()};
          dftm.addRow(row);
         }
     }
 }

 return new Totals(totsuperficie);
}

private Totals updateProvincia_DesgloseRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm
, double superficieCount, String tipoEntidad)
{
   String prov = "";
   String mun = "";
   String fkvalue = "";

   Double superficie = new Double(0);
   Double totsuperficie = superficieCount;

Object []row = null;

String sql = SqlPattern.replaceFirst("where municipios.id", "where provincias.id");
sql = getSQLReplacement(sql, Entidad, anno);
db.executeQuery(sql);

if(!db.isEmpty())
{
    for(int i=0; i<db.getRowCount(); i++)
    {
        prov = db.getValueAt(i,0).toString();
        mun = db.getValueAt(i,1).toString();
        fkvalue = db.getValueAt(i,2).toString();

        superficie = Double.parseDouble(db.getValueAt(i,3).toString());
        totsuperficie += superficie;

        int rowIndex = getProvincia_Mun_Ent_FKValue_RowIndex(prov, mun, tipoEntidad, dftm);

        if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
        {
         row = new String []{prov, mun, tipoEntidad, fkvalue
               , superficie.toString()};
         dftm.addRow(row);
        }
        else
        if(rowIndex >= 0) //insertar
        {
         row = new String []{prov, mun, tipoEntidad, fkvalue
               , superficie.toString()};
         dftm.insertRow(rowIndex+1, row);
        }
    }
}

    return new Totals(totsuperficie);
}

private Totals updateProvinciaRow(String SqlPattern, String anno, DefaultTableModel dftm
        , double superficieCount)
{
   String prov = "";
   String fkvalue = "";
   Double superficie = new Double(0);
   Double totsuperficie = superficieCount;
   Object []row = null;

   String sql = getSQLReplacement(SqlPattern, anno);
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       for(int i=0; i<db.getRowCount(); i++)
       {
           boolean rowUpdated = false;

           prov = db.getValueAt(i,0).toString();
           fkvalue = db.getValueAt(i,1).toString();
           superficie = Double.parseDouble(db.getValueAt(i,2).toString());
           totsuperficie += superficie;

           if(dftm.getRowCount() > 0)
           {
                String tmProv = "";
                String tmfkValue = "";
                for(int k=0; k<dftm.getRowCount(); k++)
                {
                 tmProv = dftm.getValueAt(k,0).toString();
                 tmfkValue = dftm.getValueAt(k,1).toString();
                 if(tmProv.equals(prov) && tmfkValue.equals(fkvalue))
                 {
                   double tempsuperficie = Double.parseDouble(dftm.getValueAt(k,2).toString());
                   dftm.setValueAt((tempsuperficie+superficie), k, 2);
                   rowUpdated = true;
                   break;
                 }
                }
           }

           if(!rowUpdated)
           {
            row = new String []{prov, fkvalue, superficie.toString()};
            dftm.addRow(row);
           }
       }
   }

   return new Totals(totsuperficie);
}

private Totals updateDNF_DesgloseRow(String SqlPattern, String anno, DefaultTableModel dftm
        , double superficieCount, String tipoEntidad)
{
   String prov = "";
   String mun = "";
   String fkvalue = "";
   Double superficie = new Double(0);
   Double totsuperficie = superficieCount;

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
           fkvalue = db.getValueAt(i,2).toString();
           superficie = Double.parseDouble(db.getValueAt(i,3).toString());
           totsuperficie += superficie;

           int rowIndex = getProvincia_Mun_Ent_FKValue_RowIndex(prov, mun, tipoEntidad, dftm);

           if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
           {
            row = new String []{prov, mun, tipoEntidad, fkvalue, superficie.toString()};
            dftm.addRow(row);
           }
           else
           if(rowIndex >= 0) //insertar
           {
            row = new String []{prov, mun, tipoEntidad, fkvalue, superficie.toString()};

            dftm.insertRow(rowIndex+1, row);
           }
           else     //actualizar
           {
             rowIndex = rowIndex*(-1);
             double tempsuperficie = Double.parseDouble(dftm.getValueAt(rowIndex, 4).toString());
             dftm.setValueAt((tempsuperficie+superficie), rowIndex, 4);
           }
       }
   }

   return new Totals(totsuperficie);
}

/**Agrega los subtotales de los municipios a un DefaultTableModel*/
private void AddMun_Subtotals(DefaultTableModel TM)
{
 ArrayList<Row_Index> RI = new ArrayList<Row_Index>();
 HashMap<String, String> mun_id = new HashMap<String, String>();
 String CurrentMun = "";
 String MUN = "";
 String PROV = "";

   Double superficie = new Double(0);
   Double totsuperficie = new Double(0);

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
        superficie = Double.parseDouble(TM.getValueAt(i,4).toString());

        if(!PROV.equals("TOTAL"))
        {
            if(!CurrentMun.equals(MUN)) //Si cambio el municipio
            {
              CurrentMun = MUN;
              String []row = new String []{"", "Sub-Total", "", "", totsuperficie.toString()};
              RI.add(new Row_Index(row, i));
              totsuperficie = superficie;
            }
            else
            {
              totsuperficie += superficie;
            }
        }
        else
        {
           String []row = new String []{"", "Sub-Total", "", "", totsuperficie.toString()};
           RI.add(new Row_Index(row, i));
        }
      } //Fin for
 }

   insert_Mun_SubTotals(TM, RI);
}

    @Override
    protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
    {
        String fkValue = "";
        String superficie= "";

        String entidad = "";
        Object []columNames = null;
        DefaultTableModel deftm = null;
        String sql = "";

        Double totsuperficie = new Double(0);

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.PROGRAMA_PROT2_COLUMN_NAME
                                       , CONSTANTS.SUPERFICIE2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.PROGRAMA_PROT2_COLUMN_NAME
                                       , CONSTANTS.SUPERFICIE2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.PROGRAMA_PROT2_COLUMN_NAME
                                       , CONSTANTS.SUPERFICIE2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_3_1_3_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

              fkValue = db.getValueAt(i,1).toString();
              superficie = db.getValueAt(i,2).toString();

              deftm.addRow(new String []{entidad, fkValue, superficie});
              totsuperficie += Double.parseDouble(superficie);
             }

             deftm.addRow(new String []{"TOTAL", "", totsuperficie.toString()});
        }

       return deftm;
    }
    
 private class Totals
 {
   Double totsuperficie = new Double(0);

  public Totals(double superficie)
  {
   totsuperficie = superficie;
  }
 }
}

