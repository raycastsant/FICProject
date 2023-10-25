 package cif.reportes.clases;
 
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Porc_Mad_Ext;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;

/**
*
* @author Raisel
*/
public class C_4_2_PorcMadeXtrBosqNat_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_4_2_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_4_2_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_4_2_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_4_2_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "" , "" , "" , db.getValueAt(0,1).toString()};
     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
   String mun = "";
   String pme = "";

   String tipoEntidad = "";
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.PORC_MADERA_EXT2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       pme = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       row = new String []{"", mun, tipoEntidad, pme};
       deftm.addRow(row);
   }

   sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       pme = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       row = new String []{"", mun, tipoEntidad, pme};
       deftm.addRow(row);
   }

   sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       pme = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       row = new String []{"", mun, tipoEntidad, pme};
       deftm.addRow(row);
   }

   //Totales  //              //                    //               //             //                    //
       double sumCortas = 0;
       double vol = 0;
       sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           sumCortas = Double.parseDouble(db.getValueAt(0,0).toString());
           vol = Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           sumCortas += Double.parseDouble(db.getValueAt(0,0).toString());
           vol += Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           sumCortas += Double.parseDouble(db.getValueAt(0,0).toString());
           vol += Double.parseDouble(db.getValueAt(0,1).toString());
       }

   double PME = 0;
   if(sumCortas != 0)
    PME = (vol/sumCortas)*100;

   row = new String []{"TOTAL", "", "TOTAL", Redondear(PME).toString()};
   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                      CONSTANTS.PORC_MADERA_EXT2_COLUMN_NAME};
   HashMap<String, Obj_Porc_Mad_Ext> hashValues = new HashMap<String, Obj_Porc_Mad_Ext>();

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_4_2_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_4_2_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_4_2_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

      //Agregando filas
       Object [] lista = hashValues.keySet().toArray() ;
       double totsumCortas = 0;
       double totvol = 0;

       for(int i=0; i<lista.length; i++)
       {
        double sumCortas = hashValues.get(lista[i].toString()).getSumCortas();
        double vol = hashValues.get(lista[i].toString()).getVol();

       double PME = 0;
       if(sumCortas != 0)
        PME = (vol/sumCortas)*100;

        deftm.addRow(new String []{"", lista[i].toString(), Redondear(PME).toString()});
        totsumCortas += sumCortas;
        totvol += vol;
       }

   double PME = 0;
   if(totsumCortas != 0)
    PME = (totvol/totsumCortas)*100;

    row = new String []{"", "TOTAL", Redondear(PME).toString()};

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
   HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>> municipios = new HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.PORC_MADERA_EXT2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_4_2_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_4_2_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_4_2_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

  //Agregando filas
   Object [] listaMun = municipios.keySet().toArray();
   double totsumCortas = 0;
   double totvol = 0;

   for(int i=0; i<listaMun.length; i++)
   {
    mun = listaMun[i].toString();
    HashMap<String, Obj_Porc_Mad_Ext> entidades = municipios.get(mun);
    Object [] listaEntidades = entidades.keySet().toArray();

        for(int k=0; k<listaEntidades.length; k++)
        {
           double sumCortas = entidades.get(listaEntidades[k].toString()).getSumCortas();
           double vol = entidades.get(listaEntidades[k].toString()).getVol();

           double PME = 0;
           if(sumCortas != 0)
            PME = (vol/sumCortas)*100;

            deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), Redondear(PME).toString()});
            totsumCortas += sumCortas;
            totvol += vol;
        }
   }

    double PME = 0;
    if(totsumCortas != 0)
     PME = (totvol/totsumCortas)*100;

    row = new String []{"", "TOTAL", "", Redondear(PME).toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   HashMap<String, Obj_Porc_Mad_Ext> hashValues = new HashMap<String, Obj_Porc_Mad_Ext>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.PORC_MADERA_EXT2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_4_2_PROV_SubGrupoUS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_4_2_PROV_SubGrupoAP, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_4_2_PROV_SubGrupoOTROS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

  //Agregando filas
   Object [] lista = hashValues.keySet().toArray() ;
   double totsumCortas = 0;
   double totvol = 0;

   for(int i=0; i<lista.length; i++)
   {
       double sumCortas = hashValues.get(lista[i].toString()).getSumCortas();
       double vol = hashValues.get(lista[i].toString()).getVol();

       double PME = 0;
       if(sumCortas != 0)
        PME = (vol/sumCortas)*100;

        deftm.addRow(new String []{lista[i].toString(), Redondear(PME).toString()});
        totsumCortas += sumCortas;
        totvol += vol;
   }

    double PME = 0;
    if(totsumCortas != 0)
     PME = (totvol/totsumCortas)*100;

   deftm.addRow(new String []{"TOTAL", Redondear(PME).toString()});

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
  String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                            prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.PORC_MADERA_EXT2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_4_2_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_4_2_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_4_2_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

  //Agregando filas
   Object [] listaProvincias = provincias.keySet().toArray();
   double subtotsumCortas = 0;
   double subtotvol = 0;

   double totsumCortas = 0;
   double totvol = 0;
   String prov = "";
   String mun = "";
   for(int i=0; i<listaProvincias.length; i++) //Provincias
   {
    prov = listaProvincias[i].toString();
    HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>> municipios = provincias.get(prov);
    Object [] listaMunicipios = municipios.keySet().toArray();

        for(int j=0; j<listaMunicipios.length; j++)   //Municipios
        {
         mun = listaMunicipios[j].toString();
         HashMap<String, Obj_Porc_Mad_Ext> entidades = municipios.get(mun);
         Object [] listaEntidades = entidades.keySet().toArray();

            for(int k=0; k<listaEntidades.length; k++)  //Entidades
            {
                double sumCortas = entidades.get(listaEntidades[k].toString()).getSumCortas();
                double vol = entidades.get(listaEntidades[k].toString()).getVol();

               double PME = 0;
               if(sumCortas != 0)
                PME = (vol/sumCortas)*100;

                deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), Redondear(PME).toString()});
                subtotsumCortas += sumCortas;
                subtotvol += vol;
            }

           double PME = 0;
           if(subtotsumCortas != 0)
            PME = (subtotvol/subtotsumCortas)*100;

            deftm.addRow(new String []{"", "Sub-Total", "", Redondear(PME).toString()});
            totsumCortas += subtotsumCortas;
            totvol += subtotvol;

            subtotsumCortas = 0;
            subtotvol = 0;
        }
   }

    double PME = 0;
    if(totsumCortas != 0)
     PME = (totvol/totsumCortas)*100;

    deftm.addRow(new String []{"TOTAL", "", "", Redondear(PME).toString()});

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   double sumCortas = 0;
   double vol = 0;

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   double totsumCortas = 0;
   double totvol = 0;

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.PORC_MADERA_EXT2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.PORC_MADERA_EXT2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.PORC_MADERA_EXT2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_2_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

           sumCortas = Double.parseDouble(db.getValueAt(i,1).toString());
           vol = Double.parseDouble(db.getValueAt(i,2).toString());

           double PME = 0;
           if(sumCortas != 0)
            PME = (vol/sumCortas)*100;

            deftm.addRow(new String []{entidad, Redondear(PME).toString()});
            totsumCortas += sumCortas;
            totvol += vol;
         }

        double PME = 0;
        if(totsumCortas != 0)
         PME = (totvol/totsumCortas)*100;

        deftm.addRow(new String []{"TOTAL", Redondear(PME).toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_Porc_Mad_Ext> hashValues)
{
     String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
     double sumCortas = 0;
     double vol = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       sumCortas = Double.parseDouble(db.getValueAt(i,0).toString());
       vol = Double.parseDouble(db.getValueAt(i,1).toString());
       Ent = db.getValueAt(i,2).toString();

       if(hashValues.containsKey(Ent))
       {
        sumCortas = sumCortas + hashValues.get(Ent).getSumCortas();
        vol = vol + hashValues.get(Ent).getVol();
       }

       hashValues.put(Ent, new Obj_Porc_Mad_Ext(sumCortas, vol));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>> municipios, String tipoEntidad)
{
     String mun = "";
     double sumCortas = 0;
     double vol = 0;
     
     for(int i=0; i<db.getRowCount(); i++)
     {
       sumCortas = Double.parseDouble(db.getValueAt(i,0).toString());
       vol = Double.parseDouble(db.getValueAt(i,1).toString());
       mun = db.getValueAt(i,2).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                sumCortas = sumCortas + municipios.get(mun).get(tipoEntidad).getSumCortas();
                vol = vol + municipios.get(mun).get(tipoEntidad).getVol();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_Porc_Mad_Ext(sumCortas, vol));
       }
       else
       {
        HashMap<String, Obj_Porc_Mad_Ext> entidad = new HashMap<String, Obj_Porc_Mad_Ext>();
        entidad.put(tipoEntidad, new Obj_Porc_Mad_Ext(sumCortas, vol));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>>> provincias, String tipoEntidad)
{
     String prov = "";
     String mun = "";
     double sumCortas = 0;
     double vol = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       sumCortas = Double.parseDouble(db.getValueAt(i,0).toString());
       vol = Double.parseDouble(db.getValueAt(i,1).toString());
       prov = db.getValueAt(i,2).toString();
       mun = db.getValueAt(i,3).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    sumCortas = sumCortas + provincias.get(prov).get(mun).get(tipoEntidad).getSumCortas();
                    vol = vol + provincias.get(prov).get(mun).get(tipoEntidad).getVol();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Porc_Mad_Ext(sumCortas, vol));
            }
            else
            {
                HashMap<String, Obj_Porc_Mad_Ext> entidad = new HashMap<String, Obj_Porc_Mad_Ext>();
                entidad.put(tipoEntidad, new Obj_Porc_Mad_Ext(sumCortas, vol));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_Porc_Mad_Ext> entidad = new HashMap<String, Obj_Porc_Mad_Ext>();
        entidad.put(tipoEntidad, new Obj_Porc_Mad_Ext(sumCortas, vol));
        HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>> municipio = new HashMap<String, HashMap<String, Obj_Porc_Mad_Ext>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}
}

