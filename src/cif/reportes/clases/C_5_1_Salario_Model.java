 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Salario;
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
public class C_5_1_Salario_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_5_1_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_5_1_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_5_1_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_5_1_EFI_TOTAL, user.getIdEntidad(), anno.toString());
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
   String salariomm = "";

   String tipoEntidad = "";
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SALARIO_MED_MENSUAL2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       salariomm = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       row = new String []{"", mun, tipoEntidad, salariomm};
       deftm.addRow(row);
   }

   sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       salariomm = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       row = new String []{"", mun, tipoEntidad, salariomm};
       deftm.addRow(row);
   }

   sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       salariomm = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       row = new String []{"", mun, tipoEntidad, salariomm};
       deftm.addRow(row);
   }

   //Totales  //              //                    //               //             //                    //
       double saldev = 0;
       double promtrab = 0;
       sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           saldev = Double.parseDouble(db.getValueAt(0,0).toString());
           promtrab = Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           saldev += Double.parseDouble(db.getValueAt(0,0).toString());
           promtrab += Double.parseDouble(db.getValueAt(0,1).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           saldev += Double.parseDouble(db.getValueAt(0,0).toString());
           promtrab += Double.parseDouble(db.getValueAt(0,1).toString());
       }

   double SAL = 0;
   if(promtrab != 0)
    SAL = (saldev/(promtrab*12));

   row = new String []{"TOTAL", "", "TOTAL", Redondear(SAL).toString()};
   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                      CONSTANTS.SALARIO_MED_MENSUAL2_COLUMN_NAME};
   HashMap<String, Obj_Salario> hashValues = new HashMap<String, Obj_Salario>();

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_5_1_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_5_1_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_5_1_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

      //Agregando filas
       Object [] lista = hashValues.keySet().toArray() ;
       double totsaldev = 0;
       double totpromtrab = 0;

       for(int i=0; i<lista.length; i++)
       {
        double saldev = hashValues.get(lista[i].toString()).getSaldev();
        double promtrab = hashValues.get(lista[i].toString()).getPromtrab();

       double SAL = 0;
       if(promtrab != 0)
        SAL = (saldev/(promtrab*12));

        deftm.addRow(new String []{"", lista[i].toString(), Redondear(SAL).toString()});
        totsaldev += saldev;
        totpromtrab += promtrab;
       }

       double SAL = 0;
       if(totpromtrab != 0)
        SAL = (totsaldev/(totpromtrab*12));

    row = new String []{"", "TOTAL", Redondear(SAL).toString()};

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
   HashMap<String, HashMap<String, Obj_Salario>> municipios = new HashMap<String, HashMap<String, Obj_Salario>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SALARIO_MED_MENSUAL2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_5_1_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_5_1_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_5_1_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

  //Agregando filas
   Object [] listaMun = municipios.keySet().toArray();
   double totsaldev = 0;
   double totpromtrab = 0;

   for(int i=0; i<listaMun.length; i++)
   {
    mun = listaMun[i].toString();
    HashMap<String, Obj_Salario> entidades = municipios.get(mun);
    Object [] listaEntidades = entidades.keySet().toArray();

        for(int k=0; k<listaEntidades.length; k++)
        {
            double saldev = entidades.get(listaEntidades[k].toString()).getSaldev();
            double promtrab = entidades.get(listaEntidades[k].toString()).getPromtrab();

           double SAL = 0;
           if(promtrab != 0)
            SAL = (saldev/(promtrab*12));

            deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), Redondear(SAL).toString()});
            totsaldev += saldev;
            totpromtrab += promtrab;
        }
   }

       double SAL = 0;
       if(totpromtrab != 0)
        SAL = (totsaldev/(totpromtrab*12));

       row = new String []{"", "TOTAL", "", Redondear(SAL).toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   HashMap<String, Obj_Salario> hashValues = new HashMap<String, Obj_Salario>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.SALARIO_MED_MENSUAL2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_5_1_PROV_SubGrupoUS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_5_1_PROV_SubGrupoAP, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_5_1_PROV_SubGrupoOTROS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

  //Agregando filas
   Object [] lista = hashValues.keySet().toArray() ;
   double totsaldev = 0;
   double totpromtrab = 0;

   for(int i=0; i<lista.length; i++)
   {
        double saldev = hashValues.get(lista[i].toString()).getSaldev();
        double promtrab = hashValues.get(lista[i].toString()).getPromtrab();

       double SAL = 0;
       if(promtrab != 0)
        SAL = (saldev/(promtrab*12));

        deftm.addRow(new String []{lista[i].toString(), Redondear(SAL).toString()});
        totsaldev += saldev;
        totpromtrab += promtrab;
   }

   double SAL = 0;
   if(totpromtrab != 0)
    SAL = (totsaldev/(totpromtrab*12));

   deftm.addRow(new String []{"TOTAL", Redondear(SAL).toString()});

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
  String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                       prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_Salario>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Salario>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SALARIO_MED_MENSUAL2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_5_1_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_5_1_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_5_1_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

  //Agregando filas
   Object [] listaProvincias = provincias.keySet().toArray();
   double subtotsaldev = 0;
   double subtotpromtrab = 0;

   double totsaldev = 0;
   double totpromtrab = 0;
   String prov = "";
   String mun = "";
   for(int i=0; i<listaProvincias.length; i++) //Provincias
   {
    prov = listaProvincias[i].toString();
    HashMap<String, HashMap<String, Obj_Salario>> municipios = provincias.get(prov);
    Object [] listaMunicipios = municipios.keySet().toArray();

        for(int j=0; j<listaMunicipios.length; j++)   //Municipios
        {
         mun = listaMunicipios[j].toString();
         HashMap<String, Obj_Salario> entidades = municipios.get(mun);
         Object [] listaEntidades = entidades.keySet().toArray();

            for(int k=0; k<listaEntidades.length; k++)  //Entidades
            {
                double saldev = entidades.get(listaEntidades[k].toString()).getSaldev();
                double promtrab = entidades.get(listaEntidades[k].toString()).getPromtrab();

               double SAL = 0;
               if(promtrab != 0)
                SAL = (saldev/(promtrab*12));

                deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), Redondear(SAL).toString()});
                subtotsaldev += saldev;
                subtotpromtrab += promtrab;
            }

           double SAL = 0;
           if(totpromtrab != 0)
            SAL = (totsaldev/(totpromtrab*12));

            deftm.addRow(new String []{"", "Sub-Total", "", Redondear(SAL).toString()});
            totsaldev += subtotsaldev;
            totpromtrab += subtotpromtrab;

            subtotsaldev = 0;
            subtotpromtrab = 0;
        }
   }

   double SAL = 0;
   if(totpromtrab != 0)
    SAL = (totsaldev/(totpromtrab*12));

    deftm.addRow(new String []{"TOTAL", "", "", Redondear(SAL).toString()});

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   double saldev = 0;
   double promtrab = 0;

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   double totsaldev = 0;
   double totpromtrab = 0;

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.SALARIO_MED_MENSUAL2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.SALARIO_MED_MENSUAL2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.SALARIO_MED_MENSUAL2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_5_1_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

           saldev = Double.parseDouble(db.getValueAt(i,1).toString());
           promtrab = Double.parseDouble(db.getValueAt(i,2).toString());

           double SAL = 0;
           if(promtrab != 0)
            SAL = (saldev/(promtrab*12));

            deftm.addRow(new String []{entidad, Redondear(SAL).toString()});
            totsaldev += saldev;
            totpromtrab += promtrab;
         }

       double SAL = 0;
       if(totpromtrab != 0)
        SAL = (totsaldev/(totpromtrab*12));

        deftm.addRow(new String []{"TOTAL", Redondear(SAL).toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_Salario> hashValues)
{
     String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
     double saldev = 0;
     double promtrab = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       saldev = Double.parseDouble(db.getValueAt(i,0).toString());
       promtrab = Double.parseDouble(db.getValueAt(i,1).toString());
       Ent = db.getValueAt(i,2).toString();

       if(hashValues.containsKey(Ent))
       {
        saldev = saldev + hashValues.get(Ent).getSaldev();
        promtrab = promtrab + hashValues.get(Ent).getPromtrab();
       }

       hashValues.put(Ent, new Obj_Salario(saldev, promtrab));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Salario>> municipios, String tipoEntidad)
{
     String mun = "";
     double saldev = 0;
     double promtrab = 0;
     
     for(int i=0; i<db.getRowCount(); i++)
     {
       saldev = Double.parseDouble(db.getValueAt(i,0).toString());
       promtrab = Double.parseDouble(db.getValueAt(i,1).toString());
       mun = db.getValueAt(i,2).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                saldev = saldev + municipios.get(mun).get(tipoEntidad).getSaldev();
                promtrab = promtrab + municipios.get(mun).get(tipoEntidad).getPromtrab();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_Salario(saldev, promtrab));
       }
       else
       {
        HashMap<String, Obj_Salario> entidad = new HashMap<String, Obj_Salario>();
        entidad.put(tipoEntidad, new Obj_Salario(saldev, promtrab));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Salario>>> provincias, String tipoEntidad)
{
     String prov = "";
     String mun = "";
     double saldev = 0;
     double promtrab = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       saldev = Double.parseDouble(db.getValueAt(i,0).toString());
       promtrab = Double.parseDouble(db.getValueAt(i,1).toString());
       prov = db.getValueAt(i,2).toString();
       mun = db.getValueAt(i,3).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    saldev = saldev + provincias.get(prov).get(mun).get(tipoEntidad).getSaldev();
                    promtrab = promtrab + provincias.get(prov).get(mun).get(tipoEntidad).getPromtrab();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Salario(saldev, promtrab));
            }
            else
            {
                HashMap<String, Obj_Salario> entidad = new HashMap<String, Obj_Salario>();
                entidad.put(tipoEntidad, new Obj_Salario(saldev, promtrab));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_Salario> entidad = new HashMap<String, Obj_Salario>();
        entidad.put(tipoEntidad, new Obj_Salario(saldev, promtrab));
        HashMap<String, HashMap<String, Obj_Salario>> municipio = new HashMap<String, HashMap<String, Obj_Salario>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}
}