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
public class C_3_1_2_EspeciesEndemicas_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
 protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

//     sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_EFI_TOTAL, user.getIdEntidad(), anno.toString());
//     db.executeQuery(sql);
//
//     Object []row = new String []{"TOTAL", "", "", "", "", db.getValueAt(0,1).toString()};
//     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
    String prov = "";
    String mun = "";
    String fkValue = "";
    String progconserv= "";
    String tipoEntidad = "";
    Object []row = null;
    Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME
                                       , CONSTANTS.PROG_CONSERVACION2_COLUMN_NAME};
    DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

  String sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);
  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      progconserv = db.getValueAt(i,3).toString();
      tipoEntidad = "Unidad Silvícola: "+ db.getValueAt(i,4).toString();   //CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
      row = new String []{prov, mun, tipoEntidad, fkValue, progconserv};
      deftm.addRow(row);
   }
  }

  sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);

  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      progconserv = db.getValueAt(i,3).toString();
      tipoEntidad = "Área protegida: "+ db.getValueAt(i,4).toString();    //CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
      row = new String []{prov, mun, tipoEntidad, fkValue, progconserv};

      deftm.addRow(row);
   }
  }

  sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
  db.executeQuery(sql);

  if(!db.isEmpty())
  {
   for(int i=0; i<db.getRowCount(); i++)
   {
      prov = db.getValueAt(i,0).toString();
      mun = db.getValueAt(i,1).toString();
      fkValue = db.getValueAt(i,2).toString();
      progconserv = db.getValueAt(i,3).toString();
      tipoEntidad = "Otros: "+ db.getValueAt(i,4).toString();   //CONSTANTS.ENTIDAD_OTROS;
      row = new String []{prov, mun, tipoEntidad, fkValue, progconserv};

      deftm.addRow(row);
   }
 }

// row = new String []{"TOTAL", "", "", "", totprogconserv.toString()};
// deftm.addRow(row);

 return deftm;
}

@Override
protected TableModel getModel_PROV()
{ 
//   Object []row = null;
//   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME
//                                       , CONSTANTS.PROG_CONSERVACION_COLUMN_NAME};
//
//  DefaultTableModel deftm = new DefaultTableModel(columNames, 0);
//
////  Totals rowTotals = new Totals(0);
//
//  updateProvincia_MunRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), deftm);
//
//  updateProvincia_MunRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm);
//
//  updateProvincia_MunRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm);

//  row = new String []{"", "TOTAL", "", rowTotals.totprogconserv.toString()};
//  deftm.addRow(row);

  TableModel deftm = getModel_PROV_DESGLOSE();
  
  return deftm;
}

@Override
protected TableModel getModel_PROV_DESGLOSE()
{
//   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME
                                       , CONSTANTS.PROG_CONSERVACION2_COLUMN_NAME};

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

//   Totals rowTotals = new Totals(0);

   updateProvincia_DesgloseRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), deftm, "Unidad Silvícola: ");

   updateProvincia_DesgloseRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, "Área protegida: ");

   updateProvincia_DesgloseRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, "Otros: ");

//  row = new String []{"", "TOTAL", "", "", rowTotals.totprogconserv.toString()};
//  deftm.addRow(row);

  return deftm;
}

@Override
protected TableModel getModel_NAC()
{
//   Object []row = null;
//   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME
//                                       , CONSTANTS.PROG_CONSERVACION_COLUMN_NAME};
//   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);
//
//   Totals rowTotals = new Totals(0);
//
//   rowTotals = updateProvinciaRow(ReportQuerys.SQL_3_1_2_PROV_SubGrupoUS, anno.toString(), deftm, 0);
//
//   rowTotals = updateProvinciaRow(ReportQuerys.SQL_3_1_2_PROV_SubGrupoAP, anno.toString(), deftm, rowTotals.totprogconserv);
//
//   rowTotals = updateProvinciaRow(ReportQuerys.SQL_3_1_2_PROV_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totprogconserv);
//
//   row = new String []{"TOTAL", "", rowTotals.totprogconserv.toString()};
//  deftm.addRow(row);

  TableModel deftm = getModel_NAC_DESGLOSE();

  return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
//   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME
                                       , CONSTANTS.PROG_CONSERVACION2_COLUMN_NAME};

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

//   Totals rowTotals = new Totals(0);

   updateDNF_DesgloseRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoUS, anno.toString(), deftm, "Unidad Silvícola: ");

   updateDNF_DesgloseRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoAP, anno.toString(), deftm, "Área protegida: ");

   updateDNF_DesgloseRow(ReportQuerys.SQL_3_1_2_MUN_SubGrupoOTROS, anno.toString(), deftm, "Otros: ");

//   row = new String []{"TOTAL", "", "", "", rowTotals.totprogconserv.toString()};
//
//   deftm.addRow(row);
//
//   AddMun_Subtotals(deftm);

   return deftm;
}

private void updateProvincia_DesgloseRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm, String tipoEntidad)
{
   String prov = "";
   String mun = "";
   String fkvalue = "";
   String progconserv = "";

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
            progconserv = db.getValueAt(i,3).toString();

            int rowIndex = getProvincia_Mun_Ent_FKValue_RowIndex(prov, mun, tipoEntidad+db.getValueAt(i,4).toString(), dftm);

            if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
            {
             row = new String []{prov, mun, tipoEntidad+db.getValueAt(i,4).toString(), fkvalue, progconserv.toString()};
             dftm.addRow(row);
            }
            else
            if(rowIndex >= 0) //insertar
            {
             row = new String []{prov, mun, tipoEntidad+db.getValueAt(i,4).toString(), fkvalue, progconserv.toString()};
             dftm.insertRow(rowIndex+1, row);
            }
        }
    }

//    return new Totals(totprogconserv);
}

private void updateDNF_DesgloseRow(String SqlPattern, String anno, DefaultTableModel dftm, String tipoEntidad)
{
   String prov = "";
   String mun = "";
   String fkvalue = "";
   String progconserv = "";

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
           progconserv = db.getValueAt(i,3).toString();

           int rowIndex = getProvincia_Mun_Ent_FKValue_RowIndex(prov, mun, tipoEntidad+db.getValueAt(i,4).toString(), dftm);

           if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
           {
            row = new String []{prov, mun, tipoEntidad+db.getValueAt(i,4).toString(), fkvalue, progconserv.toString()};
            dftm.addRow(row);
           }
           else
           if(rowIndex >= 0) //insertar
           {
            row = new String []{prov, mun, tipoEntidad+db.getValueAt(i,4).toString(), fkvalue, progconserv.toString()};

            dftm.insertRow(rowIndex+1, row);
           }
//           else     //actualizar
//           {
//             rowIndex = rowIndex*(-1);
//             double tempprogconserv = Double.parseDouble(dftm.getValueAt(rowIndex, 4).toString());
//             dftm.setValueAt((tempprogconserv+progconserv), rowIndex, 4);
//           }
       }
   }

//   return new Totals(totprogconserv);
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
    String fkValue = "";
    String progconserv= "";

    String entidad = "";
    Object []columNames = null;
    DefaultTableModel deftm = null;
    String sql = "";

//    Double totprogconserv = new Double(0);

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME, CONSTANTS.PROG_CONSERVACION2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME, CONSTANTS.PROG_CONSERVACION2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME, CONSTANTS.PROG_CONSERVACION2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_1_2_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
   }

    deftm = new DefaultTableModel(columNames, 0);
    db.executeQuery(sql);
    
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
          progconserv = db.getValueAt(i,2).toString();

          deftm.addRow(new String []{entidad, fkValue, progconserv});
//          totprogconserv += Double.parseDouble(progconserv);
         }

//         deftm.addRow(new String []{"TOTAL", "", totprogconserv.toString()});
    }

   return deftm;
}
}