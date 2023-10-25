 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Bosq_Afect;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
*
* @author Raisel
*/
public class C_2_2_BosquesAfectados_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
 protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_2_2_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_2_2_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_2_2_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_2_2_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "" , "" , "" , db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString(),
                                  db.getValueAt(0,3).toString(), db.getValueAt(0,4).toString()};
     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
   String mun = "";
   String invespex = "";
   String librepast = "";
   String plagas= "";
   String otras = "";

   String tipoEntidad = "";   
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.INVASION_ESP_EXOT2_COLUMN_NAME, CONSTANTS.LIBRE_PAST2_COLUMN_NAME,
                                      CONSTANTS.PLAGAS_ENFERMEDADES2_COLUMN_NAME, CONSTANTS.OTRAS_CAUSAS2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       invespex = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       librepast = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       plagas = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
       otras = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       deftm.addRow(new String []{"", mun, tipoEntidad, invespex, librepast, plagas, otras});
   }

   sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       invespex = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       librepast = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       plagas = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
       otras = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       deftm.addRow(new String []{"", mun, tipoEntidad, invespex, librepast, plagas, otras});
   }

   sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       invespex = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       librepast = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       plagas = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
       otras = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       deftm.addRow(new String []{"", mun, tipoEntidad, invespex, librepast, plagas, otras});
   }

   //Totales  //              //                    //               //             //                    //
       double tac = 0;
       double totinvespex = 0;
       double totlibrepast = 0;
       Double totplagas = new Double(0);
       Double tototras = new Double(0);
       sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           tac = Double.parseDouble(db.getValueAt(0,0).toString());
           totinvespex = Double.parseDouble(db.getValueAt(0,1).toString());
           totlibrepast = Double.parseDouble(db.getValueAt(0,2).toString());
           totplagas = Double.parseDouble(db.getValueAt(0,3).toString());
           tototras = Double.parseDouble(db.getValueAt(0,4).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           tac += Double.parseDouble(db.getValueAt(0,0).toString());
           totinvespex += Double.parseDouble(db.getValueAt(0,1).toString());
           totlibrepast += Double.parseDouble(db.getValueAt(0,2).toString());
           totplagas += Double.parseDouble(db.getValueAt(0,3).toString());
           tototras += Double.parseDouble(db.getValueAt(0,4).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           tac += Double.parseDouble(db.getValueAt(0,0).toString());
           totinvespex += Double.parseDouble(db.getValueAt(0,1).toString());
           totlibrepast += Double.parseDouble(db.getValueAt(0,2).toString());
           totplagas += Double.parseDouble(db.getValueAt(0,3).toString());
           tototras += Double.parseDouble(db.getValueAt(0,4).toString());
       }

   Double INVESP = new Double(0);
   Double LIBPAST = new Double(0);
   
   if(totinvespex != 0)
    INVESP = Redondear((totinvespex/tac)*100);

   if(totinvespex != 0)
    LIBPAST = Redondear((totlibrepast/tac)*100);
   
   deftm.addRow(new String []{"TOTAL", "", "TOTAL", INVESP.toString(), LIBPAST.toString(), totplagas.toString(), tototras.toString()});

   return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                      CONSTANTS.INVASION_ESP_EXOT2_COLUMN_NAME, CONSTANTS.LIBRE_PAST2_COLUMN_NAME,
                                      CONSTANTS.PLAGAS_ENFERMEDADES2_COLUMN_NAME, CONSTANTS.OTRAS_CAUSAS2_COLUMN_NAME};
   HashMap<String, Obj_Bosq_Afect> hashValues = new HashMap<String, Obj_Bosq_Afect>();

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

           String sql = ReportQuerys.SQL_2_2_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
           sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

           sql = ReportQuerys.SQL_2_2_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
           sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

           sql = ReportQuerys.SQL_2_2_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
           sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

          //Agregando filas
           Object [] lista = hashValues.keySet().toArray() ;
           double tottac = 0;
           double totinvespex = 0;
           double totlibrepast = 0;
           Double totplagas = new Double(0);
           Double tototras = new Double(0);

           for(int i=0; i<lista.length; i++)
           {
            double tac = hashValues.get(lista[i].toString()).getTac();
            double invespex = hashValues.get(lista[i].toString()).getInvespex();
            double librepast = hashValues.get(lista[i].toString()).getLibrepast();
            Double plagas = hashValues.get(lista[i].toString()).getPlagas();
            Double otras = hashValues.get(lista[i].toString()).getOtras();

            Double INVESP = new Double(0);
            Double LIBPAST = new Double(0);

            if(invespex != 0)
             INVESP = Redondear((invespex/tac)*100);

            if(invespex != 0)
             LIBPAST = Redondear((librepast/tac)*100);

            deftm.addRow(new String []{"", lista[i].toString(), INVESP.toString(), LIBPAST.toString(), plagas.toString(), otras.toString()});
            tottac += tac;
            totinvespex += invespex;
            totlibrepast += librepast;
            totplagas += plagas;
            tototras += otras;
           }

        Double INVESP = new Double(0);
        Double LIBPAST = new Double(0);

        if(totinvespex != 0)
         INVESP = Redondear((totinvespex/tottac)*100);

        if(totinvespex != 0)
         LIBPAST = Redondear((totlibrepast/tottac)*100);
        
        row = new String []{"", "TOTAL", INVESP.toString(), LIBPAST.toString(), totplagas.toString(), tototras.toString()};

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
   HashMap<String, HashMap<String, Obj_Bosq_Afect>> municipios = new HashMap<String, HashMap<String, Obj_Bosq_Afect>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.INVASION_ESP_EXOT2_COLUMN_NAME, CONSTANTS.LIBRE_PAST2_COLUMN_NAME,
                                      CONSTANTS.PLAGAS_ENFERMEDADES2_COLUMN_NAME, CONSTANTS.OTRAS_CAUSAS2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

           String sql = ReportQuerys.SQL_2_2_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
           sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
           if(!db.isEmpty())
            updateProvincia_Desglose_HASH(municipios, tipoEntidad);

           sql = ReportQuerys.SQL_2_2_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
           sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
           if(!db.isEmpty())
            updateProvincia_Desglose_HASH(municipios, tipoEntidad);

           sql = ReportQuerys.SQL_2_2_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
           sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
           if(!db.isEmpty())
            updateProvincia_Desglose_HASH(municipios, tipoEntidad);

         //Agregando filas
           Object [] listaMun = municipios.keySet().toArray();
           double tottac = 0;
           double totinvespex = 0;
           double totlibrepast = 0;
           Double totplagas = new Double(0);
           Double tototras = new Double(0);

           for(int i=0; i<listaMun.length; i++)
           {
            mun = listaMun[i].toString();
            HashMap<String, Obj_Bosq_Afect> entidades = municipios.get(mun);
            Object [] listaEntidades = entidades.keySet().toArray();

                for(int k=0; k<listaEntidades.length; k++)
                {
                    double tac = entidades.get(listaEntidades[k].toString()).getTac();
                    double invespex = entidades.get(listaEntidades[k].toString()).getInvespex();
                    double librepast = entidades.get(listaEntidades[k].toString()).getLibrepast();
                    Double plagas = entidades.get(listaEntidades[k].toString()).getPlagas();
                    Double otras = entidades.get(listaEntidades[k].toString()).getOtras();

                    Double INVESP = new Double(0);
                    Double LIBPAST = new Double(0);

                    if(invespex != 0)
                     INVESP = Redondear((invespex/tac)*100);

                    if(invespex != 0)
                     LIBPAST = Redondear((librepast/tac)*100);

                    deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), INVESP.toString(), LIBPAST.toString(), plagas.toString(), otras.toString()});
                    tottac += tac;
                    totinvespex += invespex;
                    totlibrepast += librepast;
                    totplagas += plagas;
                    tototras += otras;
                }
           }

            Double INVESP = new Double(0);
            Double LIBPAST = new Double(0);

            if(totinvespex != 0)
             INVESP = Redondear((totinvespex/tottac)*100);

            if(totinvespex != 0)
             LIBPAST = Redondear((totlibrepast/tottac)*100);
            row = new String []{"", "TOTAL", "", INVESP.toString(), LIBPAST.toString(), totplagas.toString(), tototras.toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   Object []row = null;
   HashMap<String, Obj_Bosq_Afect> hashValues = new HashMap<String, Obj_Bosq_Afect>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.INVASION_ESP_EXOT2_COLUMN_NAME, CONSTANTS.LIBRE_PAST2_COLUMN_NAME,
                                      CONSTANTS.PLAGAS_ENFERMEDADES2_COLUMN_NAME, CONSTANTS.OTRAS_CAUSAS2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

           String sql = getSQLReplacement(ReportQuerys.SQL_2_2_PROV_SubGrupoUS, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

           sql = getSQLReplacement(ReportQuerys.SQL_2_2_PROV_SubGrupoAP, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

           sql = getSQLReplacement(ReportQuerys.SQL_2_2_PROV_SubGrupoOTROS, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

          //Agregando filas
           Object [] lista = hashValues.keySet().toArray() ;
           double tottac = 0;
           double totinvespex = 0;
           double totlibrepast = 0;
           Double totplagas = new Double(0);
           Double tototras = new Double(0);

           for(int i=0; i<lista.length; i++)
           {
                double tac = hashValues.get(lista[i].toString()).getTac();
                double invespex = hashValues.get(lista[i].toString()).getInvespex();
                double librepast = hashValues.get(lista[i].toString()).getLibrepast();
                Double plagas = hashValues.get(lista[i].toString()).getPlagas();
                Double otras = hashValues.get(lista[i].toString()).getOtras();

                Double INVESP = new Double(0);
                Double LIBPAST = new Double(0);

                if(invespex != 0)
                 INVESP = Redondear((invespex/tac)*100);

                if(invespex != 0)
                 LIBPAST = Redondear((librepast/tac)*100);

                deftm.addRow(new String []{lista[i].toString(), INVESP.toString(), LIBPAST.toString(), plagas.toString(), otras.toString()});
                tottac += tac;
                totinvespex += invespex;
                totlibrepast += librepast;
                totplagas += plagas;
                tototras += otras;
           }

        Double INVESP = new Double(0);
        Double LIBPAST = new Double(0);

        if(totinvespex != 0)
         INVESP = Redondear((totinvespex/tottac)*100);

        if(totinvespex != 0)
         LIBPAST = Redondear((totlibrepast/tottac)*100);
        
        row = new String []{"TOTAL", INVESP.toString(), LIBPAST.toString(), totplagas.toString(), tototras.toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
   Object []row = null;
   String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                          prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_Bosq_Afect>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Bosq_Afect>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.INVASION_ESP_EXOT2_COLUMN_NAME, CONSTANTS.LIBRE_PAST2_COLUMN_NAME,
                                      CONSTANTS.PLAGAS_ENFERMEDADES2_COLUMN_NAME, CONSTANTS.OTRAS_CAUSAS2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

           String sql = ReportQuerys.SQL_2_2_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
           sql = getSQLReplacement(sql, anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
           if(!db.isEmpty())
            updateNacional_Desglose_HASH(provincias, tipoEntidad);

           sql = ReportQuerys.SQL_2_2_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
           sql = getSQLReplacement(sql, anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
           if(!db.isEmpty())
            updateNacional_Desglose_HASH(provincias, tipoEntidad);

           sql = ReportQuerys.SQL_2_2_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
           sql = getSQLReplacement(sql, anno.toString());
           db.executeQuery(sql);
           tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
           if(!db.isEmpty())
            updateNacional_Desglose_HASH(provincias, tipoEntidad);

         //Agregando filas
           Object [] listaProvincias = provincias.keySet().toArray();
           double Subtottac = 0;
           double Subtotinvespex = 0;
           double Subtotlibrepast = 0;
           Double Subtotplagas = new Double(0);
           Double Subtototras = new Double(0);

           double tottac = 0;
           double totinvespex = 0;
           double totlibrepast = 0;
           Double totplagas = new Double(0);
           Double tototras = new Double(0);
           String prov = "";
           String mun = "";
           for(int i=0; i<listaProvincias.length; i++) //Provincias
           {
            prov = listaProvincias[i].toString();
            HashMap<String, HashMap<String, Obj_Bosq_Afect>> municipios = provincias.get(prov);
            Object [] listaMunicipios = municipios.keySet().toArray();

                for(int j=0; j<listaMunicipios.length; j++)   //Municipios
                {
                 mun = listaMunicipios[j].toString();
                 HashMap<String, Obj_Bosq_Afect> entidades = municipios.get(mun);
                 Object [] listaEntidades = entidades.keySet().toArray();

                    for(int k=0; k<listaEntidades.length; k++)  //Entidades
                    {
                        double tac = entidades.get(listaEntidades[k].toString()).getTac();
                        double invespex = entidades.get(listaEntidades[k].toString()).getInvespex();
                        double librepast = entidades.get(listaEntidades[k].toString()).getLibrepast();
                        Double plagas = entidades.get(listaEntidades[k].toString()).getPlagas();
                        Double otras = entidades.get(listaEntidades[k].toString()).getOtras();

                        Double INVESP = new Double(0);
                        Double LIBPAST = new Double(0);

                        if(invespex != 0)
                         INVESP = Redondear((invespex/tac)*100);

                        if(invespex != 0)
                         LIBPAST = Redondear((librepast/tac)*100);
                        
                        deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), INVESP.toString(), LIBPAST.toString(), plagas.toString(), otras.toString()});
                        Subtottac += tac;
                        Subtotinvespex += invespex;
                        Subtotlibrepast += librepast;
                        Subtotplagas += plagas;
                        Subtototras += otras;
                    }

                    Double INVESP = new Double(0);
                    Double LIBPAST = new Double(0);

                    if(Subtotinvespex != 0)
                     INVESP = Redondear((Subtotinvespex/Subtottac)*100);

                    if(Subtotinvespex != 0)
                     LIBPAST = Redondear((Subtotlibrepast/Subtottac)*100);

                    deftm.addRow(new String []{"", "Sub-Total", "", INVESP.toString(), LIBPAST.toString(), Subtotplagas.toString(), Subtototras.toString()});
                    tottac += Subtottac;
                    totinvespex += Subtotinvespex;
                    totlibrepast += Subtotlibrepast;
                    totplagas += Subtotplagas;
                    tototras += Subtototras;

                    Subtottac = 0;
                    Subtotinvespex = 0;
                    Subtotlibrepast = 0;
                    Subtotplagas = new Double(0);
                    Subtototras = new Double(0);
                }
           }

            Double INVESP = new Double(0);
            Double LIBPAST = new Double(0);

            if(totinvespex != 0)
             INVESP = Redondear((totinvespex/tottac)*100);

            if(totinvespex != 0)
             LIBPAST = Redondear((totlibrepast/tottac)*100);
            
            row = new String []{"TOTAL", "", "", INVESP.toString(), LIBPAST.toString(), totplagas.toString(), tototras.toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   Double tac = new Double(0);
   Double invespex = new Double(0);
   Double librepast = new Double(0);
   Double plagas = new Double(0);
   Double otras = new Double(0);

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   Double tottac = new Double(0);
   double totinvespex = 0;
   double totlibrepast = 0;
   Double totplagas = new Double(0);
   Double tototras = new Double(0);

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.INVASION_ESP_EXOT2_COLUMN_NAME, CONSTANTS.LIBRE_PAST2_COLUMN_NAME,
                              CONSTANTS.PLAGAS_ENFERMEDADES2_COLUMN_NAME, CONSTANTS.OTRAS_CAUSAS2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.INVASION_ESP_EXOT2_COLUMN_NAME, CONSTANTS.LIBRE_PAST2_COLUMN_NAME,
                              CONSTANTS.PLAGAS_ENFERMEDADES2_COLUMN_NAME, CONSTANTS.OTRAS_CAUSAS2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.INVASION_ESP_EXOT2_COLUMN_NAME, CONSTANTS.LIBRE_PAST2_COLUMN_NAME,
                              CONSTANTS.PLAGAS_ENFERMEDADES2_COLUMN_NAME, CONSTANTS.OTRAS_CAUSAS2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_2_2_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

           tac = Double.parseDouble(db.getValueAt(i,1).toString());
           invespex = Double.parseDouble(db.getValueAt(i,2).toString());
           librepast = Double.parseDouble(db.getValueAt(i,3).toString());
           plagas = Double.parseDouble(db.getValueAt(i,4).toString());
           otras = Double.parseDouble(db.getValueAt(i,5).toString());

           Double INVESP = new Double(0);
           Double LIBPAST = new Double(0);

           if(invespex != 0)
            INVESP = Redondear((invespex/tac)*100);

           if(invespex != 0)
            LIBPAST = Redondear((librepast/tac)*100);
           
           deftm.addRow(new String []{entidad, INVESP.toString(), LIBPAST.toString(), plagas.toString(), otras.toString()});
           tottac += tac;
           totinvespex += invespex;
           totlibrepast += librepast;
           totplagas += plagas;
           tototras += otras;
         }

        Double INVESP = new Double(0);
        Double LIBPAST = new Double(0);

        if(totinvespex != 0)
         INVESP = Redondear((totinvespex/tottac)*100);

        if(totinvespex != 0)
         LIBPAST = Redondear((totlibrepast/tottac)*100);
        
        deftm.addRow(new String []{"TOTAL", INVESP.toString(), LIBPAST.toString(), totplagas.toString(), tototras.toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_Bosq_Afect> hashValues)
{
     String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
     double tac = 0;
     double invespex = 0;
     double librepast = 0;
     double plagas = 0;
     double otras = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       tac = Double.parseDouble(db.getValueAt(i,0).toString());
       invespex = Double.parseDouble(db.getValueAt(i,1).toString());
       librepast = Double.parseDouble(db.getValueAt(i,2).toString());
       plagas = Double.parseDouble(db.getValueAt(i,3).toString());
       otras = Double.parseDouble(db.getValueAt(i,4).toString());
       Ent = db.getValueAt(i,5).toString();

       if(hashValues.containsKey(Ent))
       {
        tac = tac + hashValues.get(Ent).getTac();
        invespex = invespex + hashValues.get(Ent).getInvespex();
        librepast = librepast + hashValues.get(Ent).getLibrepast();
        plagas = plagas + hashValues.get(Ent).getPlagas();
        otras = otras + hashValues.get(Ent).getOtras();
       }

       hashValues.put(Ent, new Obj_Bosq_Afect(tac, invespex, librepast, plagas, otras));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Bosq_Afect>> municipios, String tipoEntidad)
{
     String mun = "";
     double tac = 0;
     double invespex = 0;
     double librepast = 0;
     double plagas = 0;
     double otras = 0;
     for(int i=0; i<db.getRowCount(); i++)
     {
       tac = Double.parseDouble(db.getValueAt(i,0).toString());
       invespex = Double.parseDouble(db.getValueAt(i,1).toString());
       librepast = Double.parseDouble(db.getValueAt(i,2).toString());
       plagas = Double.parseDouble(db.getValueAt(i,3).toString());
       otras = Double.parseDouble(db.getValueAt(i,4).toString());
       mun = db.getValueAt(i,5).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                tac = tac + municipios.get(mun).get(tipoEntidad).getTac();
                invespex = invespex + municipios.get(mun).get(tipoEntidad).getInvespex();
                librepast = librepast + municipios.get(mun).get(tipoEntidad).getLibrepast();
                plagas = plagas + municipios.get(mun).get(tipoEntidad).getPlagas();
                otras = otras + municipios.get(mun).get(tipoEntidad).getOtras();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_Bosq_Afect(tac, invespex, librepast, plagas, otras));
       }
       else
       {
        HashMap<String, Obj_Bosq_Afect> entidad = new HashMap<String, Obj_Bosq_Afect>();
        entidad.put(tipoEntidad, new Obj_Bosq_Afect(tac, invespex, librepast, plagas, otras));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Bosq_Afect>>> provincias, String tipoEntidad)
{
     String prov = "";
     String mun = "";
     double tac = 0;
     double invespex = 0;
     double librepast = 0;
     double plagas = 0;
     double otras = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       tac = Double.parseDouble(db.getValueAt(i,0).toString());
       invespex = Double.parseDouble(db.getValueAt(i,1).toString());
       librepast = Double.parseDouble(db.getValueAt(i,2).toString());
       plagas = Double.parseDouble(db.getValueAt(i,3).toString());
       otras = Double.parseDouble(db.getValueAt(i,4).toString());
       prov = db.getValueAt(i,5).toString();
       mun = db.getValueAt(i,6).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    tac = tac + provincias.get(prov).get(mun).get(tipoEntidad).getTac();
                    invespex = invespex + provincias.get(prov).get(mun).get(tipoEntidad).getInvespex();
                    librepast = librepast + provincias.get(prov).get(mun).get(tipoEntidad).getLibrepast();
                    plagas = plagas + provincias.get(prov).get(mun).get(tipoEntidad).getPlagas();
                    otras = otras + provincias.get(prov).get(mun).get(tipoEntidad).getOtras();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Bosq_Afect(tac, invespex, librepast, plagas, otras));
            }
            else
            {
                HashMap<String, Obj_Bosq_Afect> entidad = new HashMap<String, Obj_Bosq_Afect>();
                entidad.put(tipoEntidad, new Obj_Bosq_Afect(tac, invespex, librepast, plagas, otras));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_Bosq_Afect> entidad = new HashMap<String, Obj_Bosq_Afect>();
        entidad.put(tipoEntidad, new Obj_Bosq_Afect(tac, invespex, librepast, plagas, otras));
        HashMap<String, HashMap<String, Obj_Bosq_Afect>> municipio = new HashMap<String, HashMap<String, Obj_Bosq_Afect>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}
}