 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Act_Min;
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
public class C_3_4_AreasAfectActvMinera_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_3_4_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_3_4_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_3_4_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_3_4_EFI_TOTAL, user.getIdEntidad(), anno.toString());
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
   String supAfect = "";
   String supRecult = "";

   String tipoEntidad = "";
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUPERFICIE_AFECTADA2_COLUMN_NAME, CONSTANTS.SUPERFICIE_RECULTIVADA2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supAfect = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supRecult = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       row = new String []{"", mun, tipoEntidad, supAfect, supRecult};
       deftm.addRow(row);
   }

   sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supAfect = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supRecult = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       row = new String []{"", mun, tipoEntidad, supAfect, supRecult};
       deftm.addRow(row);
   }

   sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supAfect = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supRecult = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       row = new String []{"", mun, tipoEntidad, supAfect, supRecult};
       deftm.addRow(row);
   }

   //Totales  //              //                    //               //             //                    //
       double safect = 0;
       double scult = 0;
       sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           safect = Double.parseDouble(db.getValueAt(0,0).toString());
           scult = Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           safect += Double.parseDouble(db.getValueAt(0,0).toString());
           scult += Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           safect += Double.parseDouble(db.getValueAt(0,0).toString());
           scult += Double.parseDouble(db.getValueAt(0,1).toString());
       }

   double SRecultiv = 0;
   if(safect != 0)
    SRecultiv = (scult/safect)*100;

   row = new String []{"TOTAL", "", "TOTAL", Redondear(safect).toString(), Redondear(SRecultiv).toString()};
   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                      CONSTANTS.SUPERFICIE_AFECTADA2_COLUMN_NAME, CONSTANTS.SUPERFICIE_RECULTIVADA2_COLUMN_NAME};
   HashMap<String, Obj_Act_Min> hashValues = new HashMap<String, Obj_Act_Min>();

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_3_4_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_3_4_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_3_4_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

      //Agregando filas
       Object [] lista = hashValues.keySet().toArray() ;
       double totsafect = 0;
       double totscult = 0;

       for(int i=0; i<lista.length; i++)
       {
        double safect = hashValues.get(lista[i].toString()).getSafect();
        double scult = hashValues.get(lista[i].toString()).getScult();

       double SRecultiv = 0;
       if(safect != 0)
        SRecultiv = (scult/safect)*100;

        deftm.addRow(new String []{"", lista[i].toString(), Redondear(safect).toString(), Redondear(SRecultiv).toString()});
        totsafect += safect;
        totscult += scult;
       }

   double SRecultiv = 0;
   if(totsafect != 0)
    SRecultiv = (totscult/totsafect)*100;

    row = new String []{"", "TOTAL", Redondear(totsafect).toString(), Redondear(SRecultiv).toString()};

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
   HashMap<String, HashMap<String, Obj_Act_Min>> municipios = new HashMap<String, HashMap<String, Obj_Act_Min>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUPERFICIE_AFECTADA2_COLUMN_NAME, CONSTANTS.SUPERFICIE_RECULTIVADA2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_3_4_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_3_4_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_3_4_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

  //Agregando filas
   Object [] listaMun = municipios.keySet().toArray();
   double totsafect = 0;
   double totscult = 0;

   for(int i=0; i<listaMun.length; i++)
   {
    mun = listaMun[i].toString();
    HashMap<String, Obj_Act_Min> entidades = municipios.get(mun);
    Object [] listaEntidades = entidades.keySet().toArray();

        for(int k=0; k<listaEntidades.length; k++)
        {
            double safect = entidades.get(listaEntidades[k].toString()).getSafect();
            double scult = entidades.get(listaEntidades[k].toString()).getScult();

           double SRecultiv = 0;
           if(safect != 0)
            SRecultiv = (scult/safect)*100;

            deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), Redondear(safect).toString(), Redondear(SRecultiv).toString()});
            totsafect += safect;
            totscult += scult;
        }
   }

   double SRecultiv = 0;
   if(totsafect != 0)
    SRecultiv = (totscult/totsafect)*100;

    row = new String []{"", "TOTAL", "", Redondear(totsafect).toString(), Redondear(SRecultiv).toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   HashMap<String, Obj_Act_Min> hashValues = new HashMap<String, Obj_Act_Min>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.SUPERFICIE_AFECTADA2_COLUMN_NAME,
                                      CONSTANTS.SUPERFICIE_RECULTIVADA2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_3_4_PROV_SubGrupoUS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_3_4_PROV_SubGrupoAP, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_3_4_PROV_SubGrupoOTROS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

  //Agregando filas
   Object [] lista = hashValues.keySet().toArray() ;
   double totsafect = 0;
   double totscult = 0;

   for(int i=0; i<lista.length; i++)
   {
        double safect = hashValues.get(lista[i].toString()).getSafect();
        double scult = hashValues.get(lista[i].toString()).getScult();

       double SRecultiv = 0;
       if(safect != 0)
        SRecultiv = (scult/safect)*100;

        deftm.addRow(new String []{lista[i].toString(), Redondear(safect).toString(), Redondear(SRecultiv).toString()});
        totsafect += safect;
        totscult += scult;
   }

   double SRecultiv = 0;
   if(totsafect != 0)
    SRecultiv = (totscult/totsafect)*100;

   deftm.addRow(new String []{"TOTAL", Redondear(totsafect).toString(), Redondear(SRecultiv).toString()});

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
  String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                       prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_Act_Min>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Act_Min>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUPERFICIE_AFECTADA2_COLUMN_NAME, CONSTANTS.SUPERFICIE_RECULTIVADA2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_3_4_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_3_4_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_3_4_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

  //Agregando filas
   Object [] listaProvincias = provincias.keySet().toArray();
   double subtotsafect = 0;
   double subtotscult = 0;

   double totsafect = 0;
   double totscult = 0;
   String prov = "";
   String mun = "";
   for(int i=0; i<listaProvincias.length; i++) //Provincias
   {
    prov = listaProvincias[i].toString();
    HashMap<String, HashMap<String, Obj_Act_Min>> municipios = provincias.get(prov);
    Object [] listaMunicipios = municipios.keySet().toArray();

        for(int j=0; j<listaMunicipios.length; j++)   //Municipios
        {
         mun = listaMunicipios[j].toString();
         HashMap<String, Obj_Act_Min> entidades = municipios.get(mun);
         Object [] listaEntidades = entidades.keySet().toArray();

            for(int k=0; k<listaEntidades.length; k++)  //Entidades
            {
                double safect = entidades.get(listaEntidades[k].toString()).getSafect();
                double scult = entidades.get(listaEntidades[k].toString()).getScult();

               double SRecultiv = 0;
               if(safect != 0)
                SRecultiv = (scult/safect)*100;

                deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), Redondear(safect).toString(), Redondear(SRecultiv).toString()});
                subtotsafect += safect;
                subtotscult += scult;
            }

           double SRecultiv = 0;
           if(subtotsafect != 0)
            SRecultiv = (subtotscult/subtotsafect)*100;

            deftm.addRow(new String []{"", "Sub-Total", "", Redondear(subtotsafect).toString(), Redondear(SRecultiv).toString()});
            totsafect += subtotsafect;
            totscult += subtotscult;

            subtotsafect = 0;
            subtotscult = 0;
        }
   }

   double SRecultiv = 0;
   if(totsafect != 0)
    SRecultiv = (totscult/totsafect)*100;

    deftm.addRow(new String []{"TOTAL", "", "", Redondear(totsafect).toString(), Redondear(SRecultiv).toString()});

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   double safect = 0;
   double scult = 0;

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   double totsafect = 0;
   double totscult = 0;

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.SUPERFICIE_AFECTADA2_COLUMN_NAME, CONSTANTS.SUPERFICIE_RECULTIVADA2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.SUPERFICIE_AFECTADA2_COLUMN_NAME, CONSTANTS.SUPERFICIE_RECULTIVADA2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.SUPERFICIE_AFECTADA2_COLUMN_NAME, CONSTANTS.SUPERFICIE_RECULTIVADA2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_3_4_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

           safect = Double.parseDouble(db.getValueAt(i,1).toString());
           scult = Double.parseDouble(db.getValueAt(i,2).toString());

           double SRecultiv = 0;
           if(safect != 0)
            SRecultiv = (scult/safect)*100;

            deftm.addRow(new String []{entidad, Redondear(safect).toString(), Redondear(SRecultiv).toString()});
            totsafect += safect;
            totscult += scult;
         }

       double SRecultiv = 0;
       if(totsafect != 0)
        SRecultiv = (totscult/totsafect)*100;

        deftm.addRow(new String []{"TOTAL", Redondear(totsafect).toString(), Redondear(SRecultiv).toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_Act_Min> hashValues)
{
     String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
     double safect = 0;
     double scult = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       safect = Double.parseDouble(db.getValueAt(i,0).toString());
       scult = Double.parseDouble(db.getValueAt(i,1).toString());
       Ent = db.getValueAt(i,2).toString();

       if(hashValues.containsKey(Ent))
       {
        safect = safect + hashValues.get(Ent).getSafect();
        scult = scult + hashValues.get(Ent).getScult();
       }

       hashValues.put(Ent, new Obj_Act_Min(safect, scult));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Act_Min>> municipios, String tipoEntidad)
{
     String mun = "";
     double safect = 0;
     double scult = 0;
     
     for(int i=0; i<db.getRowCount(); i++)
     {
       safect = Double.parseDouble(db.getValueAt(i,0).toString());
       scult = Double.parseDouble(db.getValueAt(i,1).toString());
       mun = db.getValueAt(i,2).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                safect = safect + municipios.get(mun).get(tipoEntidad).getSafect();
                scult = scult + municipios.get(mun).get(tipoEntidad).getScult();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_Act_Min(safect, scult));
       }
       else
       {
        HashMap<String, Obj_Act_Min> entidad = new HashMap<String, Obj_Act_Min>();
        entidad.put(tipoEntidad, new Obj_Act_Min(safect, scult));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Act_Min>>> provincias, String tipoEntidad)
{
     String prov = "";
     String mun = "";
     double safect = 0;
     double scult = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       safect = Double.parseDouble(db.getValueAt(i,0).toString());
       scult = Double.parseDouble(db.getValueAt(i,1).toString());
       prov = db.getValueAt(i,2).toString();
       mun = db.getValueAt(i,3).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    safect = safect + provincias.get(prov).get(mun).get(tipoEntidad).getSafect();
                    scult = scult + provincias.get(prov).get(mun).get(tipoEntidad).getScult();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Act_Min(safect, scult));
            }
            else
            {
                HashMap<String, Obj_Act_Min> entidad = new HashMap<String, Obj_Act_Min>();
                entidad.put(tipoEntidad, new Obj_Act_Min(safect, scult));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_Act_Min> entidad = new HashMap<String, Obj_Act_Min>();
        entidad.put(tipoEntidad, new Obj_Act_Min(safect, scult));
        HashMap<String, HashMap<String, Obj_Act_Min>> municipio = new HashMap<String, HashMap<String, Obj_Act_Min>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}
}