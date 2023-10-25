 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Faja_Cost;
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
public class C_3_3_FajaCostera_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_3_3_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_3_3_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_3_3_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_3_3_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "" , "" , "" , db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString()};
     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
   String mun = "";
   String supHa = "";
   String supPorc = "";

   String tipoEntidad = "";
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUP_PROTEGIDA_HA2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_PORC2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supHa = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supPorc = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       row = new String []{"", mun, tipoEntidad, supHa, supPorc};
       deftm.addRow(row);
   }

   sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supHa = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supPorc = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       row = new String []{"", mun, tipoEntidad, supHa, supPorc};
       deftm.addRow(row);
   }

   sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supHa = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supPorc = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       row = new String []{"", mun, tipoEntidad, supHa, supPorc};
       deftm.addRow(row);
   }

   //Totales  //              //                    //               //             //                    //
       double SProtHa = 0;
       double longlin = 0;
       sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           SProtHa = Double.parseDouble(db.getValueAt(0,0).toString());
           longlin = Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           SProtHa += Double.parseDouble(db.getValueAt(0,0).toString());
           longlin += Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           SProtHa += Double.parseDouble(db.getValueAt(0,0).toString());
           longlin += Double.parseDouble(db.getValueAt(0,1).toString());
       }

   Double SProtPorc = new Double(0);
   if(longlin != 0)
    SProtPorc = (SProtHa/((longlin*30)/1000))*100;

   row = new String []{"TOTAL", "", "TOTAL", Redondear(SProtHa).toString(), Redondear(SProtPorc).toString()};
   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                      CONSTANTS.SUP_PROTEGIDA_HA2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_PORC2_COLUMN_NAME};
   HashMap<String, Obj_Faja_Cost> hashValues = new HashMap<String, Obj_Faja_Cost>();

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_3_3_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_3_3_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_3_3_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

      //Agregando filas
       Object [] lista = hashValues.keySet().toArray() ;
       double totSProtHa = 0;
       double totlonglin = 0;

       for(int i=0; i<lista.length; i++)
       {
        double SProtHa = hashValues.get(lista[i].toString()).getSProtHa();
        double longlin = hashValues.get(lista[i].toString()).getLongLin();

       Double SProtPorc = new Double(0);
       if(longlin != 0)
        SProtPorc = (SProtHa/((longlin*30)/1000))*100;

        deftm.addRow(new String []{"", lista[i].toString(), Redondear(SProtHa).toString(), Redondear(SProtPorc).toString()});
        totSProtHa += SProtHa;
        totlonglin += longlin;
       }

   Double SProtPorc = new Double(0);
   if(totlonglin != 0)
    SProtPorc = (totSProtHa/((totlonglin*30)/1000))*100;

    row = new String []{"", "TOTAL", Redondear(totSProtHa).toString(), Redondear(SProtPorc).toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_PROV_DESGLOSE()
{
   Object []row = null;
   String mun = "";
   String tipoEntidad = "";

   //Municipio, [Entidad, Obj_Tac_SGeog]
   HashMap<String, HashMap<String, Obj_Faja_Cost>> municipios = new HashMap<String, HashMap<String, Obj_Faja_Cost>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUP_PROTEGIDA_HA2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_PORC2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_3_3_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_3_3_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_3_3_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

  //Agregando filas
   Object [] listaMun = municipios.keySet().toArray();
   double totSProtHa = 0;
   double totlonglin = 0;

   for(int i=0; i<listaMun.length; i++)
   {
    mun = listaMun[i].toString();
    HashMap<String, Obj_Faja_Cost> entidades = municipios.get(mun);
    Object [] listaEntidades = entidades.keySet().toArray();

        for(int k=0; k<listaEntidades.length; k++)
        {
            double SProtHa = entidades.get(listaEntidades[k].toString()).getSProtHa();
            double longlin = entidades.get(listaEntidades[k].toString()).getLongLin();

           Double SProtPorc = new Double(0);
           if(longlin != 0)
            SProtPorc = (SProtHa/((longlin*30)/1000))*100;

            deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), Redondear(SProtHa).toString(), Redondear(SProtPorc).toString()});
            totSProtHa += SProtHa;
            totlonglin += longlin;
        }
   }

   Double SProtPorc = new Double(0);
   if(totlonglin != 0)
    SProtPorc = (totSProtHa/((totlonglin*30)/1000))*100;

    row = new String []{"", "TOTAL", "", Redondear(totSProtHa).toString(), Redondear(SProtPorc).toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   HashMap<String, Obj_Faja_Cost> hashValues = new HashMap<String, Obj_Faja_Cost>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_HA2_COLUMN_NAME,
                                      CONSTANTS.SUP_PROTEGIDA_PORC2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_3_3_PROV_SubGrupoUS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_3_3_PROV_SubGrupoAP, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_3_3_PROV_SubGrupoOTROS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

  //Agregando filas
   Object [] lista = hashValues.keySet().toArray() ;
   double totSProtHa = 0;
   double totlonglin = 0;

   for(int i=0; i<lista.length; i++)
   {
        double SProtHa = hashValues.get(lista[i].toString()).getSProtHa();
        double longlin = hashValues.get(lista[i].toString()).getLongLin();

       Double SProtPorc = new Double(0);
       if(longlin != 0)
        SProtPorc = (SProtHa/((longlin*30)/1000))*100;

        deftm.addRow(new String []{lista[i].toString(), Redondear(SProtHa).toString(), Redondear(SProtPorc).toString()});
        totSProtHa += SProtHa;
        totlonglin += longlin;
   }

   Double SProtPorc = new Double(0);
   if(totlonglin != 0)
    SProtPorc = (totSProtHa/((totlonglin*30)/1000))*100;

   deftm.addRow(new String []{"TOTAL", Redondear(totSProtHa).toString(), Redondear(SProtPorc).toString()});

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
  String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                         prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_Faja_Cost>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Faja_Cost>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUP_PROTEGIDA_HA2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_PORC2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_3_3_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_3_3_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_3_3_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

  //Agregando filas
   Object [] listaProvincias = provincias.keySet().toArray();
   double subtotSProtHa = 0;
   double subtotlonglin = 0;

   double totSProtHa = 0;
   double totlonglin = 0;
   String prov = "";
   String mun = "";
   for(int i=0; i<listaProvincias.length; i++) //Provincias
   {
    prov = listaProvincias[i].toString();
    HashMap<String, HashMap<String, Obj_Faja_Cost>> municipios = provincias.get(prov);
    Object [] listaMunicipios = municipios.keySet().toArray();

        for(int j=0; j<listaMunicipios.length; j++)   //Municipios
        {
         mun = listaMunicipios[j].toString();
         HashMap<String, Obj_Faja_Cost> entidades = municipios.get(mun);
         Object [] listaEntidades = entidades.keySet().toArray();

            for(int k=0; k<listaEntidades.length; k++)  //Entidades
            {
                double SProtHa = entidades.get(listaEntidades[k].toString()).getSProtHa();
                double longlin = entidades.get(listaEntidades[k].toString()).getLongLin();

               Double SProtPorc = new Double(0);
               if(longlin != 0)
                SProtPorc = (SProtHa/((longlin*30)/1000))*100;

                deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), Redondear(SProtHa).toString(), Redondear(SProtPorc).toString()});
                subtotSProtHa += SProtHa;
                subtotlonglin += longlin;
            }

           Double SProtPorc = new Double(0);
           if(subtotlonglin != 0)
            SProtPorc = (subtotSProtHa/((subtotlonglin*30)/1000))*100;

            deftm.addRow(new String []{"", "Sub-Total", "", Redondear(subtotSProtHa).toString(), Redondear(SProtPorc).toString()});
            totSProtHa += subtotSProtHa;
            totlonglin += subtotlonglin;

            subtotSProtHa = 0;
            subtotlonglin = 0;
        }
   }

   Double SProtPorc = new Double(0);
   if(totlonglin != 0)
    SProtPorc = (totSProtHa/((totlonglin*30)/1000))*100;

    deftm.addRow(new String []{"TOTAL", "", "", Redondear(totSProtHa).toString(), Redondear(SProtPorc).toString()});

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   double SProtHa = 0;
   double longlin = 0;

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   double totSProtHa = 0;
   double totlonglin = 0;

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_HA2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_PORC2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_HA2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_PORC2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_HA2_COLUMN_NAME, CONSTANTS.SUP_PROTEGIDA_PORC2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_3_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

           SProtHa = Double.parseDouble(db.getValueAt(i,1).toString());
           longlin = Double.parseDouble(db.getValueAt(i,2).toString());

           Double SProtPorc = new Double(0);
           if(longlin != 0)
            SProtPorc = (SProtHa/((longlin*30)/1000))*100;

            deftm.addRow(new String []{entidad, Redondear(SProtHa).toString(), Redondear(SProtPorc).toString()});
            totSProtHa += SProtHa;
            totlonglin += longlin;
         }

       Double SProtPorc = new Double(0);
       if(totlonglin != 0)
        SProtPorc = (totSProtHa/((totlonglin*30)/1000))*100;

        deftm.addRow(new String []{"TOTAL", Redondear(totSProtHa).toString(), Redondear(SProtPorc).toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_Faja_Cost> hashValues)
{
     String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
     double SProtHa = 0;
     double longlin = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       SProtHa = Double.parseDouble(db.getValueAt(i,0).toString());
       longlin = Double.parseDouble(db.getValueAt(i,1).toString());
       Ent = db.getValueAt(i,2).toString();

       if(hashValues.containsKey(Ent))
       {
        SProtHa = SProtHa + hashValues.get(Ent).getSProtHa();
        longlin = longlin + hashValues.get(Ent).getLongLin();
       }

       hashValues.put(Ent, new Obj_Faja_Cost(SProtHa, longlin));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Faja_Cost>> municipios, String tipoEntidad)
{
     String mun = "";
     double SProtHa = 0;
     double longlin = 0;
     for(int i=0; i<db.getRowCount(); i++)
     {
       SProtHa = Double.parseDouble(db.getValueAt(i,0).toString());
       longlin = Double.parseDouble(db.getValueAt(i,1).toString());
       mun = db.getValueAt(i,2).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                SProtHa = SProtHa + municipios.get(mun).get(tipoEntidad).getSProtHa();
                longlin = longlin + municipios.get(mun).get(tipoEntidad).getLongLin();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_Faja_Cost(SProtHa, longlin));
       }
       else
       {
        HashMap<String, Obj_Faja_Cost> entidad = new HashMap<String, Obj_Faja_Cost>();
        entidad.put(tipoEntidad, new Obj_Faja_Cost(SProtHa, longlin));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Faja_Cost>>> provincias, String tipoEntidad)
{
     String prov = "";
     String mun = "";
     double SProtHa = 0;
     double longlin = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       SProtHa = Double.parseDouble(db.getValueAt(i,0).toString());
       longlin = Double.parseDouble(db.getValueAt(i,1).toString());
       prov = db.getValueAt(i,2).toString();
       mun = db.getValueAt(i,3).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    SProtHa = SProtHa + provincias.get(prov).get(mun).get(tipoEntidad).getSProtHa();
                    longlin = longlin + provincias.get(prov).get(mun).get(tipoEntidad).getLongLin();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Faja_Cost(SProtHa, longlin));
            }
            else
            {
                HashMap<String, Obj_Faja_Cost> entidad = new HashMap<String, Obj_Faja_Cost>();
                entidad.put(tipoEntidad, new Obj_Faja_Cost(SProtHa, longlin));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_Faja_Cost> entidad = new HashMap<String, Obj_Faja_Cost>();
        entidad.put(tipoEntidad, new Obj_Faja_Cost(SProtHa, longlin));
        HashMap<String, HashMap<String, Obj_Faja_Cost>> municipio = new HashMap<String, HashMap<String, Obj_Faja_Cost>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}
}