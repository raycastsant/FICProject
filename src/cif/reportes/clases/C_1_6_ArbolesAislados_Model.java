 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Arb_Aisl;
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
public class C_1_6_ArbolesAislados_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_1_6_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_1_6_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_1_6_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_1_6_EFI_TOTAL, user.getIdEntidad(), anno.toString());
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
    String cantArb = "";
    String nivelComp = "";
    String tipoEntidad = "";
    Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME
                                       , CONSTANTS.ARBOL_EXISTE2_COLUMN_NAME, CONSTANTS.NIVEL_COMPORTAMIENTO2_COLUMN_NAME};
    DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

      String sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
      db.executeQuery(sql);
      if(!db.isEmpty())
      {
          mun = db.getValueAt(0,0).toString();
          cantArb = db.getValueAt(0,1).toString();
          nivelComp = db.getValueAt(0,2).toString();
          tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
          deftm.addRow(new String []{"", mun, tipoEntidad, cantArb, nivelComp});
      }

      sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
      db.executeQuery(sql);

      if(!db.isEmpty())
      {
          mun = db.getValueAt(0,0).toString();
          cantArb = db.getValueAt(0,1).toString();
          nivelComp = db.getValueAt(0,2).toString();
          tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
          deftm.addRow(new String []{"", mun, tipoEntidad, cantArb, nivelComp});
      }

      sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
      db.executeQuery(sql);

      if(!db.isEmpty())
      {
          mun = db.getValueAt(0,0).toString();
          cantArb = db.getValueAt(0,1).toString();
          nivelComp = db.getValueAt(0,2).toString();
          tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
          deftm.addRow(new String []{"", mun, tipoEntidad, cantArb, nivelComp});
     }

     //Totales  //              //                    //               //             //                    //
       double arbexist = 0;
       double arbfalt = 0;
       sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           arbexist = Double.parseDouble(db.getValueAt(0,0).toString());
           arbfalt = Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           arbexist += Double.parseDouble(db.getValueAt(0,0).toString());
           arbfalt += Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           arbexist += Double.parseDouble(db.getValueAt(0,0).toString());
           arbfalt += Double.parseDouble(db.getValueAt(0,1).toString());
       }

     Double nivComp = new Double(0);
     if(arbexist!=0 || arbfalt!=0)
      nivComp = Redondear((arbexist/(arbexist+arbfalt))*100);

     deftm.addRow(new String []{"", "", "TOTAL", Redondear(arbexist).toString(), nivComp.toString()});

 return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                      CONSTANTS.ARBOL_EXISTE2_COLUMN_NAME, CONSTANTS.NIVEL_COMPORTAMIENTO2_COLUMN_NAME};

  DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

  HashMap<String, Obj_Arb_Aisl> hashValues = new HashMap<String, Obj_Arb_Aisl>();

   String sql = ReportQuerys.SQL_1_6_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = ReportQuerys.SQL_1_6_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = ReportQuerys.SQL_1_6_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

  //Agregando filas
   Object [] lista = hashValues.keySet().toArray() ;
   Double totarbExist = new Double(0);
   int totarbFalt = 0;

   for(int i=0; i<lista.length; i++)
   {
        Double arbExist = hashValues.get(lista[i].toString()).getArbExist();
        double arbFalt = hashValues.get(lista[i].toString()).getArbFalt();

        Double nivComp = new Double(0);
        if(arbExist!=0 || arbFalt!=0)
          nivComp = Redondear((arbExist/(arbExist+arbFalt))*100);
         
        deftm.addRow(new String []{"", lista[i].toString(), arbExist.toString(), nivComp.toString()});
        totarbExist += arbExist;
        totarbFalt += arbFalt;
   }

    Double nivComp = new Double(0);
    if(totarbExist!=0 || totarbFalt!=0)
     nivComp = Redondear((totarbExist/(totarbExist+totarbFalt))*100);
    
    deftm.addRow(new String []{"", "TOTAL", totarbExist.toString(), nivComp.toString()});

  return deftm;
}

@Override
protected TableModel getModel_PROV_DESGLOSE()
{
       String mun = "";
       String tipoEntidad = "";

       //Municipio, [Entidad, Obj_Tac_SGeog]
       HashMap<String, HashMap<String, Obj_Arb_Aisl>> municipios = new HashMap<String, HashMap<String, Obj_Arb_Aisl>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.ARBOL_EXISTE2_COLUMN_NAME, CONSTANTS.NIVEL_COMPORTAMIENTO2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_6_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_6_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_6_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

              //Agregando filas
               Object [] listaMun = municipios.keySet().toArray();
               Double totarbExist = new Double(0);
               int totarbFalt = 0;

               for(int i=0; i<listaMun.length; i++)
               {
                mun = listaMun[i].toString();
                HashMap<String, Obj_Arb_Aisl> entidades = municipios.get(mun);
                Object [] listaEntidades = entidades.keySet().toArray();

                    for(int k=0; k<listaEntidades.length; k++)
                    {
                        Double arbExist = entidades.get(listaEntidades[k].toString()).getArbExist();
                        double arbFalt = entidades.get(listaEntidades[k].toString()).getArbFalt();

                        Double nivComp = new Double(0);
                        if(arbExist!=0 || arbFalt!=0)
                          nivComp = Redondear((arbExist/(arbExist+arbFalt))*100);

                        deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), arbExist.toString(), nivComp.toString()});
                        totarbExist += arbExist;
                        totarbFalt += arbFalt;
                    }
               }

                Double nivComp = new Double(0);
                if(totarbExist!=0 || totarbFalt!=0)
                 nivComp = Redondear((totarbExist/(totarbExist+totarbFalt))*100);

                deftm.addRow(new String []{"", "TOTAL", "", totarbExist.toString(), nivComp.toString()});

       return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   HashMap<String, Obj_Arb_Aisl> hashValues = new HashMap<String, Obj_Arb_Aisl>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.ARBOL_EXISTE2_COLUMN_NAME,
                                      CONSTANTS.NIVEL_COMPORTAMIENTO2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

           String sql = getSQLReplacement(ReportQuerys.SQL_1_6_PROV_SubGrupoUS, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

           sql = getSQLReplacement(ReportQuerys.SQL_1_6_PROV_SubGrupoAP, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

           sql = getSQLReplacement(ReportQuerys.SQL_1_6_PROV_SubGrupoOTROS, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

          //Agregando filas
           Object [] lista = hashValues.keySet().toArray() ;
           Double totarbExist = new Double(0);
           int totarbFalt = 0;

           for(int i=0; i<lista.length; i++)
           {
                Double arbExist = hashValues.get(lista[i].toString()).getArbExist();
                double arbFalt = hashValues.get(lista[i].toString()).getArbFalt();

                Double nivComp = new Double(0);
                if(arbExist!=0 || arbFalt!=0)
                  nivComp = Redondear((arbExist/(arbExist+arbFalt))*100);

                deftm.addRow(new String []{lista[i].toString(), arbExist.toString(), nivComp.toString()});
                totarbExist += arbExist;
                totarbFalt += arbFalt;
           }

            Double nivComp = new Double(0);
            if(totarbExist!=0 || totarbFalt!=0)
             nivComp = Redondear((totarbExist/(totarbExist+totarbFalt))*100);

            deftm.addRow(new String []{"TOTAL", totarbExist.toString(), nivComp.toString()});

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
   Object []row = null;
   String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                        prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_Arb_Aisl>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Arb_Aisl>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.ARBOL_EXISTE2_COLUMN_NAME, CONSTANTS.NIVEL_COMPORTAMIENTO2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

           String sql = ReportQuerys.SQL_1_6_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
           sql = getSQLReplacement(sql, anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
           if(!db.isEmpty())
            updateNacional_Desglose_HASH(provincias, tipoEntidad);

           sql = ReportQuerys.SQL_1_6_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
           sql = getSQLReplacement(sql, anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
           if(!db.isEmpty())
            updateNacional_Desglose_HASH(provincias, tipoEntidad);

           sql = ReportQuerys.SQL_1_6_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
           sql = getSQLReplacement(sql, anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
           if(!db.isEmpty())
            updateNacional_Desglose_HASH(provincias, tipoEntidad);

          //Agregando filas
           Object [] listaProvincias = provincias.keySet().toArray();
           Double SubtotarbExist = new Double(0);
           int SubtotarbFalt = 0;

           Double totarbExist = new Double(0);
           int totarbFalt = 0;
           String prov = "";
           String mun = "";
           for(int i=0; i<listaProvincias.length; i++) //Provincias
           {
            prov = listaProvincias[i].toString();
            HashMap<String, HashMap<String, Obj_Arb_Aisl>> municipios = provincias.get(prov);
            Object [] listaMunicipios = municipios.keySet().toArray();

                for(int j=0; j<listaMunicipios.length; j++)   //Municipios
                {
                 mun = listaMunicipios[j].toString();
                 HashMap<String, Obj_Arb_Aisl> entidades = municipios.get(mun);
                 Object [] listaEntidades = entidades.keySet().toArray();

                    for(int k=0; k<listaEntidades.length; k++)  //Entidades
                    {
                        Double arbExist = entidades.get(listaEntidades[k].toString()).getArbExist();
                        double arbFalt = entidades.get(listaEntidades[k].toString()).getArbFalt();

                        Double nivComp = new Double(0);
                        if(arbExist!=0 || arbFalt!=0)
                          nivComp = Redondear((arbExist/(arbExist+arbFalt))*100);

                        deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), arbExist.toString(), nivComp.toString()});
                        SubtotarbExist += arbExist;
                        SubtotarbFalt += arbFalt;
                    }

                    Double nivComp = new Double(0);
                    if(SubtotarbExist!=0 || SubtotarbFalt!=0)
                     nivComp = Redondear((SubtotarbExist/(SubtotarbExist+SubtotarbFalt))*100);

                    deftm.addRow(new String []{"", "Sub-Total", "", SubtotarbExist.toString(), nivComp.toString()});

                    totarbExist += SubtotarbExist;
                    totarbFalt += SubtotarbFalt;

                    SubtotarbExist = new Double(0);
                    SubtotarbFalt = 0;
                }
           }

            Double nivComp = new Double(0);
            if(totarbExist!=0 || totarbFalt!=0)
             nivComp = Redondear((totarbExist/(totarbExist+totarbFalt))*100);

            row = new String []{"TOTAL", "", "", totarbExist.toString(), nivComp.toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   Double arbExist = new Double(0);
   int arbFalt = 0;

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   Double totarbExist = new Double(0);
   int totarbFalt = 0;

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.ARBOL_EXISTE2_COLUMN_NAME, CONSTANTS.NIVEL_COMPORTAMIENTO2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.ARBOL_EXISTE2_COLUMN_NAME, CONSTANTS.NIVEL_COMPORTAMIENTO2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.ARBOL_EXISTE2_COLUMN_NAME, CONSTANTS.NIVEL_COMPORTAMIENTO2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_1_6_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

            arbExist += Double.parseDouble(db.getValueAt(i,1).toString());
            arbFalt += Double.parseDouble(db.getValueAt(i,2).toString());

            Double nivComp = new Double(0);
            if(arbExist!=0 || arbFalt!=0)
              nivComp = Redondear((arbExist/(arbExist+arbFalt))*100);

            deftm.addRow(new String []{entidad, arbExist.toString(), nivComp.toString()});
            totarbExist += arbExist;
            totarbFalt += arbFalt;
         }

        Double nivComp = new Double(0);
        if(totarbExist!=0 || totarbFalt!=0)
         nivComp = Redondear((totarbExist/(totarbExist+totarbFalt))*100);

        deftm.addRow(new String []{"TOTAL", totarbExist.toString(), nivComp.toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_Arb_Aisl> hashValues)
{
   String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
   double arbExist = 0;
   double arbFalt = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       arbExist = Double.parseDouble(db.getValueAt(i,0).toString());
       arbFalt = Double.parseDouble(db.getValueAt(i,1).toString());
       Ent = db.getValueAt(i,2).toString();

       if(hashValues.containsKey(Ent))
       {
        arbExist = arbExist + hashValues.get(Ent).getArbExist();
        arbFalt = arbFalt + hashValues.get(Ent).getArbFalt();
       }

       hashValues.put(Ent, new Obj_Arb_Aisl(arbExist, arbFalt));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Arb_Aisl>> municipios, String tipoEntidad)
{
   String mun = "";
   double arbExist = 0;
   double arbFalt = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       arbExist = Double.parseDouble(db.getValueAt(i,0).toString());
       arbFalt = Double.parseDouble(db.getValueAt(i,1).toString());
       mun = db.getValueAt(i,2).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                arbExist = arbExist + municipios.get(mun).get(tipoEntidad).getArbExist();
                arbFalt = arbFalt + municipios.get(mun).get(tipoEntidad).getArbFalt();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_Arb_Aisl(arbExist, arbFalt));
       }
       else
       {
        HashMap<String, Obj_Arb_Aisl> entidad = new HashMap<String, Obj_Arb_Aisl>();
        entidad.put(tipoEntidad, new Obj_Arb_Aisl(arbExist, arbFalt));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Arb_Aisl>>> provincias, String tipoEntidad)
{
   String prov = "";
   String mun = "";
   double arbExist = 0;
   double arbFalt = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       arbExist = Double.parseDouble(db.getValueAt(i,0).toString());
       arbFalt = Double.parseDouble(db.getValueAt(i,1).toString());
       prov = db.getValueAt(i,2).toString();
       mun = db.getValueAt(i,3).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    arbExist = arbExist + provincias.get(prov).get(mun).get(tipoEntidad).getArbExist();
                    arbFalt = arbFalt + provincias.get(prov).get(mun).get(tipoEntidad).getArbFalt();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Arb_Aisl(arbExist, arbFalt));
            }
            else
            {
                HashMap<String, Obj_Arb_Aisl> entidad = new HashMap<String, Obj_Arb_Aisl>();
                entidad.put(tipoEntidad, new Obj_Arb_Aisl(arbExist, arbFalt));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_Arb_Aisl> entidad = new HashMap<String, Obj_Arb_Aisl>();
        entidad.put(tipoEntidad, new Obj_Arb_Aisl(arbExist, arbFalt));
        HashMap<String, HashMap<String, Obj_Arb_Aisl>> municipio = new HashMap<String, HashMap<String, Obj_Arb_Aisl>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}
}

