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
public class C_4_4_ProductForestalNoMad_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_4_4_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_4_4_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_4_4_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_4_4_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "", "", "", "", db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString(), db.getValueAt(0,3).toString()};
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
    String cantidad= "";
    String potencial= "";
    String valor= "";
    String tipoEntidad = "";
    Object []row = null;
    Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.PROD_FOREST_NO_MAD2_COLUMN_NAME
                                       , CONSTANTS.CANTIDAD2_4_4_COLUMN_NAME, CONSTANTS.POTENCIAL2_COLUMN_NAME, CONSTANTS.VALOR2_COLUMN_NAME};
    DefaultTableModel deftm = new DefaultTableModel(columNames, 0);
    Double totcantidad = new Double(0);
    Double totpotencial = new Double(0);
    Double totvalor = new Double(0);

  String sql = getSQLReplacement(ReportQuerys.SQL_4_4_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);
  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      cantidad = db.getValueAt(i,3).toString();
      potencial = db.getValueAt(i,4).toString();
      valor = db.getValueAt(i,5).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
      row = new String []{prov, mun, tipoEntidad, fkValue, cantidad, potencial, valor};
      deftm.addRow(row);
      totcantidad+= Double.parseDouble(cantidad);
      totpotencial+= Double.parseDouble(potencial);
      totvalor+= Double.parseDouble(valor);
   }
  }

  sql = getSQLReplacement(ReportQuerys.SQL_4_4_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);

  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      cantidad = db.getValueAt(i,3).toString();
      potencial = db.getValueAt(i,4).toString();
      valor = db.getValueAt(i,5).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
      row = new String []{prov, mun, tipoEntidad, fkValue, cantidad, potencial, valor};

      deftm.addRow(row);
      totcantidad+= Double.parseDouble(cantidad);
      totpotencial+= Double.parseDouble(potencial);
      totvalor+= Double.parseDouble(valor);
   }
  }

  sql = getSQLReplacement(ReportQuerys.SQL_4_4_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);

  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      cantidad = db.getValueAt(i,3).toString();
      potencial = db.getValueAt(i,4).toString();
      valor = db.getValueAt(i,5).toString();
      tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
      row = new String []{prov, mun, tipoEntidad, fkValue, cantidad, potencial, valor};

      deftm.addRow(row);
      totcantidad+= Double.parseDouble(cantidad);
      totpotencial+= Double.parseDouble(potencial);
      totvalor+= Double.parseDouble(valor);
   }
 }

 row = new String []{"TOTAL", "", "", "", totcantidad.toString(), totpotencial.toString(), totvalor.toString()};
 deftm.addRow(row);

 return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.PROD_FOREST_NO_MAD2_COLUMN_NAME
                                       , CONSTANTS.CANTIDAD2_4_4_COLUMN_NAME, CONSTANTS.POTENCIAL2_COLUMN_NAME, CONSTANTS.VALOR2_COLUMN_NAME};

  DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

  Totals rowTotals = new Totals(0, 0, 0);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_4_4_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), deftm, 0, 0, 0);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_4_4_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totcantidad, rowTotals.totpotencial, rowTotals.totvalor);

  rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_4_4_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totcantidad, rowTotals.totpotencial, rowTotals.totvalor);

  row = new String []{"", "TOTAL", "", rowTotals.totcantidad.toString(), rowTotals.totpotencial.toString(), rowTotals.totvalor.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_PROV_DESGLOSE()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.PROD_FOREST_NO_MAD2_COLUMN_NAME
                                       , CONSTANTS.CANTIDAD2_4_4_COLUMN_NAME, CONSTANTS.POTENCIAL2_COLUMN_NAME, CONSTANTS.VALOR2_COLUMN_NAME};

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0, 0, 0);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_4_4_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(),
                                           deftm, 0, 0, 0, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_4_4_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totcantidad, rowTotals.totpotencial, rowTotals.totvalor, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

   rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_4_4_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totcantidad, rowTotals.totpotencial, rowTotals.totvalor, CONSTANTS.ENTIDAD_OTROS);

  row = new String []{"", "TOTAL", "", "", rowTotals.totcantidad.toString(), rowTotals.totpotencial.toString(), rowTotals.totvalor.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.PROD_FOREST_NO_MAD2_COLUMN_NAME
                                       , CONSTANTS.CANTIDAD2_4_4_COLUMN_NAME, CONSTANTS.POTENCIAL2_COLUMN_NAME, CONSTANTS.VALOR2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0, 0, 0);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_4_4_PROV_SubGrupoUS, anno.toString(), deftm, 0, 0, 0);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_4_4_PROV_SubGrupoAP, anno.toString(), deftm, rowTotals.totcantidad, rowTotals.totpotencial, rowTotals.totvalor);

   rowTotals = updateProvinciaRow(ReportQuerys.SQL_4_4_PROV_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totcantidad, rowTotals.totpotencial, rowTotals.totvalor);

   row = new String []{"TOTAL", "", rowTotals.totcantidad.toString(), rowTotals.totpotencial.toString(), rowTotals.totvalor.toString()};
  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.PROD_FOREST_NO_MAD2_COLUMN_NAME
                                       , CONSTANTS.CANTIDAD2_4_4_COLUMN_NAME, CONSTANTS.POTENCIAL2_COLUMN_NAME, CONSTANTS.VALOR2_COLUMN_NAME};

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   Totals rowTotals = new Totals(0, 0, 0);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_4_4_MUN_SubGrupoUS, anno.toString(), deftm, 0, 0, 0, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_4_4_MUN_SubGrupoAP, anno.toString(), deftm, rowTotals.totcantidad, rowTotals.totpotencial, rowTotals.totvalor, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

   rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_4_4_MUN_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totcantidad, rowTotals.totpotencial, rowTotals.totvalor, CONSTANTS.ENTIDAD_OTROS);

   row = new String []{"TOTAL", "", "", "", rowTotals.totcantidad.toString(), rowTotals.totpotencial.toString(), rowTotals.totvalor.toString()};

   deftm.addRow(row);

   AddMun_Subtotals(deftm);

   return deftm;
}

private Totals updateProvincia_MunRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm
, double cantidadCount, double potencialCount, double valorCount)
{
   String prov = "";
   String mun = "";
   String fkValue = "";

   Double cantidad = new Double(0);
   Double potencial = new Double(0);
   Double valor = new Double(0);

   Object []row = null;

   Double totcantidad = cantidadCount;
   Double totpotencial = potencialCount;
   Double totvalor = valorCount;

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
        cantidad = Double.parseDouble(db.getValueAt(i,3).toString());
        totcantidad += cantidad;
        potencial = Double.parseDouble(db.getValueAt(i,4).toString());
        totpotencial += potencial;
        valor = Double.parseDouble(db.getValueAt(i,5).toString());
        totvalor += valor;

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
                double tempcantidad = Double.parseDouble(dftm.getValueAt(k,3).toString());
                dftm.setValueAt((tempcantidad+cantidad), k, 3);
                double temppotencial = Double.parseDouble(dftm.getValueAt(k,4).toString());
                dftm.setValueAt((temppotencial+potencial), k, 4);
                double tempvalor = Double.parseDouble(dftm.getValueAt(k,5).toString());
                dftm.setValueAt((tempvalor+valor), k, 5);
                rowUpdated = true;
                break;
               }
              }
         }

         if(!rowUpdated)
         {
          row = new String []{prov, mun, fkValue, cantidad.toString(), potencial.toString(), valor.toString()};
          dftm.addRow(row);
         }
     }
 }

 return new Totals(totcantidad,totpotencial,totvalor);
}

private Totals updateProvincia_DesgloseRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm
, double cantidadCount, double potencialCount, double valorCount, String tipoEntidad)
{
   String prov = "";
   String mun = "";
   String fkvalue = "";

   Double cantidad = new Double(0);
   Double totcantidad = cantidadCount;
   Double potencial = new Double(0);
   Double totpotencial = potencialCount;
   Double valor = new Double(0);
   Double totvalor = valorCount;

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

        cantidad = Double.parseDouble(db.getValueAt(i,3).toString());
        totcantidad += cantidad;
        potencial = Double.parseDouble(db.getValueAt(i,4).toString());
        totpotencial += potencial;
        valor = Double.parseDouble(db.getValueAt(i,5).toString());
        totvalor += valor;

        int rowIndex = getProvincia_Mun_Ent_FKValue_RowIndex(prov, mun, tipoEntidad, dftm);

        if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
        {
         row = new String []{prov, mun, tipoEntidad, fkvalue
               , cantidad.toString(), potencial.toString(), valor.toString()};
         dftm.addRow(row);
        }
        else
        if(rowIndex >= 0) //insertar
        {
         row = new String []{prov, mun, tipoEntidad, fkvalue
               , cantidad.toString(), potencial.toString(), valor.toString()};
         dftm.insertRow(rowIndex+1, row);
        }
    }
}

    return new Totals(totcantidad,totpotencial,totvalor);
}

private Totals updateProvinciaRow(String SqlPattern, String anno, DefaultTableModel dftm
        , double cantidadCount, double potencialCount, double valorCount)
{
   String prov = "";
   String fkvalue = "";
   Double cantidad = new Double(0);
   Double totcantidad = cantidadCount;
   Double potencial = new Double(0);
   Double totpotencial = potencialCount;
   Double valor = new Double(0);
   Double totvalor = valorCount;
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
           cantidad = Double.parseDouble(db.getValueAt(i,2).toString());
           totcantidad += cantidad;
           potencial = Double.parseDouble(db.getValueAt(i,3).toString());
           totpotencial += potencial;
           valor = Double.parseDouble(db.getValueAt(i,4).toString());
           totvalor += valor;

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
                   double tempcantidad = Double.parseDouble(dftm.getValueAt(k,2).toString());
                   dftm.setValueAt((tempcantidad+cantidad), k, 2);
                   double temppotencial = Double.parseDouble(dftm.getValueAt(k,3).toString());
                   dftm.setValueAt((temppotencial+potencial), k, 3);
                   double tempvalor = Double.parseDouble(dftm.getValueAt(k,4).toString());
                   dftm.setValueAt((tempvalor+valor), k, 4);
                   rowUpdated = true;
                   break;
                 }
                }
           }

           if(!rowUpdated)
           {
            row = new String []{prov, fkvalue, cantidad.toString(), potencial.toString(), valor.toString()};
            dftm.addRow(row);
           }
       }
   }

   return new Totals(totcantidad,totpotencial,totvalor);
}

private Totals updateDNF_DesgloseRow(String SqlPattern, String anno, DefaultTableModel dftm
        , double cantidadCount, double potencialCount, double valorCount, String tipoEntidad)
{
   String prov = "";
   String mun = "";
   String fkvalue = "";
   Double cantidad = new Double(0);
   Double totcantidad = cantidadCount;
   Double potencial = new Double(0);
   Double totpotencial = potencialCount;
   Double valor = new Double(0);
   Double totvalor = valorCount;

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
           cantidad = Double.parseDouble(db.getValueAt(i,3).toString());
           totcantidad += cantidad;
           potencial = Double.parseDouble(db.getValueAt(i,4).toString());
           totpotencial += potencial;
           valor = Double.parseDouble(db.getValueAt(i,5).toString());
           totvalor += valor;

           int rowIndex = getProvincia_Mun_Ent_FKValue_RowIndex(prov, mun, tipoEntidad, dftm);

           if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
           {
            row = new String []{prov, mun, tipoEntidad, fkvalue, cantidad.toString(), potencial.toString(), valor.toString()};
            dftm.addRow(row);
           }
           else
           if(rowIndex >= 0) //insertar
           {
            row = new String []{prov, mun, tipoEntidad, fkvalue, cantidad.toString(), potencial.toString(), valor.toString()};

            dftm.insertRow(rowIndex+1, row);
           }
           else     //actualizar
           {
             rowIndex = rowIndex*(-1);
             double tempcantidad = Double.parseDouble(dftm.getValueAt(rowIndex, 4).toString());
             dftm.setValueAt((tempcantidad+cantidad), rowIndex, 4);
             double temppotencial = Double.parseDouble(dftm.getValueAt(rowIndex, 5).toString());
             dftm.setValueAt((temppotencial+potencial), rowIndex, 5);
             double tempvalor = Double.parseDouble(dftm.getValueAt(rowIndex, 6).toString());
             dftm.setValueAt((tempvalor+valor), rowIndex, 6);
           }
       }
   }

   return new Totals(totcantidad,totpotencial,totvalor);
}

/**Agrega los subtotales de los municipios a un DefaultTableModel*/
private void AddMun_Subtotals(DefaultTableModel TM)
{
 ArrayList<Row_Index> RI = new ArrayList<Row_Index>();
 HashMap<String, String> mun_id = new HashMap<String, String>();
 String CurrentMun = "";
 String MUN = "";
 String PROV = "";

   Double cantidad = new Double(0);
   Double totcantidad = new Double(0);
   Double potencial = new Double(0);
   Double totpotencial = new Double(0);
   Double valor = new Double(0);
   Double totvalor = new Double(0);

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
        cantidad = Double.parseDouble(TM.getValueAt(i,4).toString());
        potencial = Double.parseDouble(TM.getValueAt(i,5).toString());
        valor = Double.parseDouble(TM.getValueAt(i,6).toString());

        if(!PROV.equals("TOTAL"))
        {
            if(!CurrentMun.equals(MUN)) //Si cambio el municipio
            {
              CurrentMun = MUN;
              String []row = new String []{"", "Sub-Total", "", "", totcantidad.toString(), totpotencial.toString(), totvalor.toString()};
              RI.add(new Row_Index(row, i));
              totcantidad = cantidad;
              totpotencial = potencial;
              totvalor = valor;
            }
            else
            {
              totcantidad += cantidad;
              totpotencial += potencial;
              totvalor += valor;
            }
        }
        else
        {
           String []row = new String []{"", "Sub-Total", "", "", totcantidad.toString(), totpotencial.toString(), totvalor.toString()};
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
        String cantidad= "";
        String potencial= "";
        String valor= "";

        String entidad = "";
        Object []columNames = null;
        DefaultTableModel deftm = null;
        String sql = "";

        Double totcantidad = new Double(0);
        Double totpotencial = new Double(0);
        Double totvalor = new Double(0);

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.PROD_FOREST_NO_MAD2_COLUMN_NAME,
                                  CONSTANTS.CANTIDAD2_4_4_COLUMN_NAME, CONSTANTS.POTENCIAL2_COLUMN_NAME, CONSTANTS.VALOR2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_4_4_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.PROD_FOREST_NO_MAD2_COLUMN_NAME,
                                  CONSTANTS.CANTIDAD2_4_4_COLUMN_NAME, CONSTANTS.POTENCIAL2_COLUMN_NAME, CONSTANTS.VALOR2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_4_4_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.PROD_FOREST_NO_MAD2_COLUMN_NAME,
                                  CONSTANTS.CANTIDAD2_4_4_COLUMN_NAME, CONSTANTS.POTENCIAL2_COLUMN_NAME, CONSTANTS.VALOR2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_4_4_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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
              cantidad = db.getValueAt(i,2).toString();
              potencial = db.getValueAt(i,3).toString();
              valor = db.getValueAt(i,4).toString();

              deftm.addRow(new String []{entidad, fkValue, cantidad, potencial, valor});
              totcantidad+= Double.parseDouble(cantidad);
              totpotencial+= Double.parseDouble(potencial);
              totvalor+= Double.parseDouble(valor);
             }

             deftm.addRow(new String []{"TOTAL", "", totcantidad.toString(), totpotencial.toString(), totvalor.toString()});
        }

       return deftm;
    }
 
 private class Totals
 {
   Double totcantidad = new Double(0);
   Double totpotencial = new Double(0);
   Double totvalor = new Double(0);

  public Totals(double cantidad,double potencial,double valor)
  {
   totcantidad = cantidad;
   totpotencial = potencial;
   totvalor = valor;
  }
 }
}