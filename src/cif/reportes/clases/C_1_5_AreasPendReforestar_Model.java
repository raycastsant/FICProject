package cif.reportes.clases;

import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Area_Pend_Ref;
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
public class C_1_5_AreasPendReforestar_Model extends AbstractCritReport implements IReportModel
{
    public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
    {
        return getReportModel(user, anno, desglose, bu);
    }
    
    @Override
    protected TableModel getModel_AP()
    {
      String sql = getSQLReplacement(ReportQuerys.SQL_1_5_AP, user.getIdEntidad(), anno.toString());
      db.executeQueryUperCase(sql);

      return db;
    }

    @Override
    protected TableModel getModel_US()
    {
       String sql = getSQLReplacement(ReportQuerys.SQL_1_5_US, user.getIdEntidad(), anno.toString());
       db.executeQueryUperCase(sql);

       return db;
    }

    @Override
    protected TableModel getModel_EFI()
    {
           DefaultTableModel deftm = new DefaultTableModel();
           String sql = getSQLReplacement(ReportQuerys.SQL_1_5_EFI_Subgrupo, user.getIdEntidad(), anno.toString());

           db.executeQueryUperCase(sql);
           if(!db.isEmpty())
           {
            deftm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

            sql = getSQLReplacement(ReportQuerys.SQL_1_5_EFI_TOTAL, user.getIdEntidad(), anno.toString());
            db.executeQuery(sql);

            Object []row = new String []{"TOTAL","","","", db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString(),
                                         db.getValueAt(0,3).toString(), db.getValueAt(0,4).toString()};
            deftm.addRow(row);
           }

        return deftm;
    }

    @Override
    protected TableModel getModel_MUN()
    {    
           String mun = "";
           String areaTal = "";
           String areaQuem = "";
           String areaPenRef = "";
           String ret = "";

           String tipoEntidad = "";
           Object []row = null;
           Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                              CONSTANTS.AREA_TALADA2_COLUMN_NAME, CONSTANTS.AREA_QUEMADA2_COLUMN_NAME,
                                              CONSTANTS.AREA_PEND_REFOREST2_COLUMN_NAME, CONSTANTS.RET2_COLUMN_NAME};
           DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

           String sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               mun = db.getValueAt(0,0).toString();
               areaTal = db.getValueAt(0,1).toString();
               areaQuem = db.getValueAt(0,2).toString();
               areaPenRef = db.getValueAt(0,3).toString();
               ret = db.getValueAt(0,4).toString();

               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               row = new String []{"", mun, tipoEntidad, areaTal, areaQuem, areaPenRef, ret};
               deftm.addRow(row);
           }

           sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               mun = db.getValueAt(0,0).toString();
               areaTal = db.getValueAt(0,1).toString();
               areaQuem = db.getValueAt(0,2).toString();
               areaPenRef = db.getValueAt(0,3).toString();
               ret = db.getValueAt(0,4).toString();

               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               row = new String []{"", mun, tipoEntidad, areaTal, areaQuem, areaPenRef, ret};
               deftm.addRow(row);
           }

           sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               mun = db.getValueAt(0,0).toString();
               areaTal = db.getValueAt(0,1).toString();
               areaQuem = db.getValueAt(0,2).toString();
               areaPenRef = db.getValueAt(0,3).toString();
               ret = db.getValueAt(0,4).toString();

               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               row = new String []{"", mun, tipoEntidad, areaTal, areaQuem, areaPenRef, ret};
               deftm.addRow(row);
           }

           //Totales  //              //                    //               //             //                    //
               double ataldef = 0;
               double aquem = 0;
               double aest = 0;
               double atal = 0;
               sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   ataldef = Double.parseDouble(db.getValueAt(0,0).toString());
                   aquem = Double.parseDouble(db.getValueAt(0,1).toString());
                   aest = Double.parseDouble(db.getValueAt(0,2).toString());
                   atal = Double.parseDouble(db.getValueAt(0,3).toString());
               }

               sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   ataldef += Double.parseDouble(db.getValueAt(0,0).toString());
                   aquem += Double.parseDouble(db.getValueAt(0,1).toString());
                   aest += Double.parseDouble(db.getValueAt(0,2).toString());
                   atal += Double.parseDouble(db.getValueAt(0,3).toString());
               }

               sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   ataldef += Double.parseDouble(db.getValueAt(0,0).toString());
                   aquem += Double.parseDouble(db.getValueAt(0,1).toString());
                   aest += Double.parseDouble(db.getValueAt(0,2).toString());
                   atal += Double.parseDouble(db.getValueAt(0,3).toString());
               }

             Double RET = new Double(0);
             if(atal != 0)
              RET = Redondear(aest/atal);

           row = new String []{"TOTAL", "", "TOTAL", Redondear(ataldef).toString(), Redondear(aquem).toString(),
                               Redondear(ataldef+aquem).toString(), RET.toString()};
           deftm.addRow(row);

           return deftm;
    }

    @Override
    protected TableModel getModel_PROV()
    {
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                          CONSTANTS.AREA_TALADA2_COLUMN_NAME, CONSTANTS.AREA_QUEMADA2_COLUMN_NAME,
                                          CONSTANTS.AREA_PEND_REFOREST2_COLUMN_NAME, CONSTANTS.RET2_COLUMN_NAME};
       HashMap<String, Obj_Area_Pend_Ref> hashValues = new HashMap<String, Obj_Area_Pend_Ref>();

       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_5_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = ReportQuerys.SQL_1_5_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = ReportQuerys.SQL_1_5_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

              //Agregando filas
               Object [] lista = hashValues.keySet().toArray() ;
               double totataldef = 0;
               double totaquem = 0;
               double totaest = 0;
               double totatal = 0;

               for(int i=0; i<lista.length; i++)
               {
                    double ataldef = hashValues.get(lista[i].toString()).getAtaldef();
                    double aquem = hashValues.get(lista[i].toString()).getAquem();
                    double aest = hashValues.get(lista[i].toString()).getAest();
                    double atal = hashValues.get(lista[i].toString()).getAtal();

                    Double RET = new Double(0);
                    if(atal != 0)
                     RET = Redondear(aest/atal);
                    
                    deftm.addRow(new String []{"", lista[i].toString(), Redondear(ataldef).toString(), Redondear(aquem).toString(),
                                               Redondear(ataldef+aquem).toString(), RET.toString()});
                    totataldef += ataldef;
                    totaquem += aquem;
                    totaest += aest;
                    totatal += atal;
               }

                Double RET = new Double(0);
                if(totatal != 0)
                 RET = Redondear(totaest/totatal);
                deftm.addRow(new String []{"", "TOTAL", Redondear(totataldef).toString(), Redondear(totaquem).toString(),
                                           Redondear(totataldef+totaquem).toString(), RET.toString()});

       return deftm;
    }

    @Override
    protected TableModel getModel_PROV_DESGLOSE()
    {
       Object []row = null;
       String mun = "";
       String tipoEntidad = "";

       //Municipio, [Entidad, Obj_Tac_SGeog]
       HashMap<String, HashMap<String, Obj_Area_Pend_Ref>> municipios = new HashMap<String, HashMap<String, Obj_Area_Pend_Ref>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.AREA_TALADA2_COLUMN_NAME, CONSTANTS.AREA_QUEMADA2_COLUMN_NAME,
                                          CONSTANTS.AREA_PEND_REFOREST2_COLUMN_NAME, CONSTANTS.RET2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_5_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_5_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_5_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

              //Agregando filas
               Object [] listaMun = municipios.keySet().toArray();
               double totataldef = 0;
               double totaquem = 0;
               double totaest = 0;
               double totatal = 0;

               for(int i=0; i<listaMun.length; i++)
               {
                mun = listaMun[i].toString();
                HashMap<String, Obj_Area_Pend_Ref> entidades = municipios.get(mun);
                Object [] listaEntidades = entidades.keySet().toArray();

                    for(int k=0; k<listaEntidades.length; k++)
                    {
                        double ataldef =entidades.get(listaEntidades[k].toString()).getAtaldef();
                        double aquem = entidades.get(listaEntidades[k].toString()).getAquem();
                        double aest = entidades.get(listaEntidades[k].toString()).getAest();
                        double atal = entidades.get(listaEntidades[k].toString()).getAtal();

                        Double RET = new Double(0);
                        if(atal != 0)
                         RET = Redondear(aest/atal);
                        
                        deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), Redondear(ataldef).toString(),
                                                   Redondear(aquem).toString(), Redondear(ataldef+aquem).toString(), RET.toString()});
                        totataldef += ataldef;
                        totaquem += aquem;
                        totaest += aest;
                        totatal += atal;
                    }
               }

                Double RET = new Double(0);
                if(totatal != 0)
                 RET = Redondear(totaest/totatal);
                
                row = new String []{"", "TOTAL", "", Redondear(totataldef).toString(), Redondear(totaquem).toString(),
                                           Redondear(totataldef+totaquem).toString(), RET.toString()};

       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC()
    {
     Object []row = null;
       HashMap<String, Obj_Area_Pend_Ref> hashValues = new HashMap<String, Obj_Area_Pend_Ref>();
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.AREA_TALADA2_COLUMN_NAME,
                                          CONSTANTS.AREA_QUEMADA2_COLUMN_NAME,
                                          CONSTANTS.AREA_PEND_REFOREST2_COLUMN_NAME, CONSTANTS.RET2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = getSQLReplacement(ReportQuerys.SQL_1_5_PROV_SubGrupoUS, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = getSQLReplacement(ReportQuerys.SQL_1_5_PROV_SubGrupoAP, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = getSQLReplacement(ReportQuerys.SQL_1_5_PROV_SubGrupoOTROS, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

              //Agregando filas
               Object [] lista = hashValues.keySet().toArray() ;
               double totataldef = 0;
               double totaquem = 0;
               double totaest = 0;
               double totatal = 0;

               for(int i=0; i<lista.length; i++)
               {
                        double ataldef = hashValues.get(lista[i].toString()).getAtaldef();
                        double aquem = hashValues.get(lista[i].toString()).getAquem();
                        double aest = hashValues.get(lista[i].toString()).getAest();
                        double atal = hashValues.get(lista[i].toString()).getAtal();

                        Double RET = new Double(0);
                        if(atal != 0)
                         RET = Redondear(aest/atal);
                        
                        deftm.addRow(new String []{lista[i].toString(), Redondear(ataldef).toString(),
                                                   Redondear(aquem).toString(), Redondear(ataldef+aquem).toString(), RET.toString()});
                        totataldef += ataldef;
                        totaquem += aquem;
                        totaest += aest;
                        totatal += atal;
               }

                Double RET = new Double(0);
                if(totatal != 0)
                 RET = Redondear(totaest/totatal);

                row = new String []{"TOTAL", Redondear(totataldef).toString(), Redondear(totaquem).toString(),
                                           Redondear(totataldef+totaquem).toString(), RET.toString()};

       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC_DESGLOSE()
    {
       Object []row = null;
       String tipoEntidad = "";

     //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                              prov            mun             ent
       HashMap<String, HashMap<String, HashMap<String, Obj_Area_Pend_Ref>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Area_Pend_Ref>>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.AREA_TALADA2_COLUMN_NAME, CONSTANTS.AREA_QUEMADA2_COLUMN_NAME,
                                          CONSTANTS.AREA_PEND_REFOREST2_COLUMN_NAME, CONSTANTS.RET2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_5_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
               sql = getSQLReplacement(sql, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

               sql = ReportQuerys.SQL_1_5_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
               sql = getSQLReplacement(sql, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

               sql = ReportQuerys.SQL_1_5_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
               sql = getSQLReplacement(sql, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

              //Agregando filas
               Object [] listaProvincias = provincias.keySet().toArray();
               double Subtotataldef = 0;
               double Subtotaquem = 0;
               double Subtotaest = 0;
               double Subtotatal = 0;

               double totataldef = 0;
               double totaquem = 0;
               double totaest = 0;
               double totatal = 0;
               String prov = "";
               String mun = "";
               for(int i=0; i<listaProvincias.length; i++) //Provincias
               {
                prov = listaProvincias[i].toString();
                HashMap<String, HashMap<String, Obj_Area_Pend_Ref>> municipios = provincias.get(prov);
                Object [] listaMunicipios = municipios.keySet().toArray();

                    for(int j=0; j<listaMunicipios.length; j++)   //Municipios
                    {
                     mun = listaMunicipios[j].toString();
                     HashMap<String, Obj_Area_Pend_Ref> entidades = municipios.get(mun);
                     Object [] listaEntidades = entidades.keySet().toArray();

                        for(int k=0; k<listaEntidades.length; k++)  //Entidades
                        {
                            double ataldef = entidades.get(listaEntidades[k].toString()).getAtaldef();
                            double aquem = entidades.get(listaEntidades[k].toString()).getAquem();
                            double aest = entidades.get(listaEntidades[k].toString()).getAest();
                            double atal = entidades.get(listaEntidades[k].toString()).getAtal();

                            Double RET = new Double(0);
                            if(atal != 0)
                             RET = Redondear(aest/atal);
                            
                            deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), Redondear(ataldef).toString(),
                                                   Redondear(aquem).toString(), Redondear(ataldef+aquem).toString(), RET.toString()});
                            Subtotataldef += ataldef;
                            Subtotaquem += aquem;
                            Subtotaest += aest;
                            Subtotatal += atal;
                        }
                     
                        Double RET = new Double(0);
                        if(Subtotatal != 0)
                         RET = Redondear(Subtotaest/Subtotatal);

                        deftm.addRow(new String []{"", "Sub-Total", "", Redondear(Subtotataldef).toString(),Redondear(Subtotaquem).toString(),
                                                  Redondear(Subtotataldef+Subtotaquem).toString(), RET.toString()});
                        totataldef += Subtotataldef;
                        totaquem += Subtotaquem;
                        totaest += Subtotaest;
                        totatal += Subtotatal;

                        Subtotataldef = 0;
                        Subtotaquem = 0;
                        Subtotaest = 0;
                        Subtotatal = 0;
                    }
               }

                Double RET = new Double(0);
                if(totatal != 0)
                 RET = Redondear(totaest/totatal);
                
                row = new String []{"TOTAL", "", "", Redondear(totataldef).toString(),Redondear(totaquem).toString(),
                                                  Redondear(totataldef+totaquem).toString(), RET.toString()};

       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
    {
       Double ataldef = new Double(0);
       Double aquem = new Double(0);
       Double aest = new Double(0);
       Double atal = new Double(0);

       String entidad = "";
       Object []columNames = null;
       DefaultTableModel deftm = null;
       String sql = "";

       double totataldef = 0;
       double totaquem = 0;
       double totaest = 0;
       double totatal = 0;

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.AREA_TALADA2_COLUMN_NAME, CONSTANTS.AREA_QUEMADA2_COLUMN_NAME,
                                  CONSTANTS.AREA_PEND_REFOREST2_COLUMN_NAME, CONSTANTS.RET2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.AREA_TALADA2_COLUMN_NAME, CONSTANTS.AREA_QUEMADA2_COLUMN_NAME,
                                  CONSTANTS.AREA_PEND_REFOREST2_COLUMN_NAME, CONSTANTS.RET2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.AREA_TALADA2_COLUMN_NAME, CONSTANTS.AREA_QUEMADA2_COLUMN_NAME,
                                  CONSTANTS.AREA_PEND_REFOREST2_COLUMN_NAME, CONSTANTS.RET2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_5_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

               ataldef += Double.parseDouble(db.getValueAt(i,1).toString());
               aquem += Double.parseDouble(db.getValueAt(i,2).toString());
               atal += Double.parseDouble(db.getValueAt(i,3).toString());
               aest += Double.parseDouble(db.getValueAt(i,4).toString());

                Double RET = new Double(0);
                if(atal != 0)
                 RET = Redondear(aest/atal);

                deftm.addRow(new String []{entidad, Redondear(ataldef).toString(),
                                           Redondear(aquem).toString(), Redondear(ataldef+aquem).toString(), RET.toString()});
                totataldef += ataldef;
                totaquem += aquem;
                totaest += aest;
                totatal += atal;
             }

                Double RET = new Double(0);
                if(atal != 0)
                 RET = Redondear(totaest/totatal);
                
               deftm.addRow(new String []{"TOTAL", Redondear(totataldef).toString(),
                                          Redondear(totaquem).toString(), Redondear(totataldef+totaquem).toString(), RET.toString()});
        }

       return deftm;
    }

    private void updateProvincia_HASH(HashMap<String, Obj_Area_Pend_Ref> hashValues)
    {
                   String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
                   double ataldef = 0;
                   double aquem = 0;
                   double aest = 0;
                   double atal = 0;
                   
                 for(int i=0; i<db.getRowCount(); i++)
                 {
                   ataldef = Double.parseDouble(db.getValueAt(i,0).toString());
                   aquem = Double.parseDouble(db.getValueAt(i,1).toString());
                   atal = Double.parseDouble(db.getValueAt(i,2).toString());
                   aest = Double.parseDouble(db.getValueAt(i,3).toString());
                   Ent = db.getValueAt(i,4).toString();

                   if(hashValues.containsKey(Ent))
                   {
                    ataldef = ataldef + hashValues.get(Ent).getAtaldef();
                    aquem = aquem + hashValues.get(Ent).getAquem();
                    aest = aest + hashValues.get(Ent).getAest();
                    atal = atal + hashValues.get(Ent).getAtal();
                   }

                   hashValues.put(Ent, new Obj_Area_Pend_Ref(ataldef, aquem, aest, atal));
                 }
    }

    private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Area_Pend_Ref>> municipios, String tipoEntidad)
    {
               String mun = "";
               double ataldef = 0;
               double aquem = 0;
               double aest = 0;
               double atal = 0;
               
                 for(int i=0; i<db.getRowCount(); i++)
                 {
                   ataldef = Double.parseDouble(db.getValueAt(i,0).toString());
                   aquem = Double.parseDouble(db.getValueAt(i,1).toString());
                   atal = Double.parseDouble(db.getValueAt(i,2).toString());
                   aest = Double.parseDouble(db.getValueAt(i,3).toString());
                   mun = db.getValueAt(i,4).toString();

                   if(municipios.containsKey(mun))
                   {
                        if(municipios.get(mun).containsKey(tipoEntidad))
                        {
                            ataldef = ataldef + municipios.get(mun).get(tipoEntidad).getAtaldef();
                            aquem = aquem + municipios.get(mun).get(tipoEntidad).getAquem();
                            aest = aest + municipios.get(mun).get(tipoEntidad).getAest();
                            atal = atal + municipios.get(mun).get(tipoEntidad).getAtal();
                        }

                        municipios.get(mun).put(tipoEntidad, new Obj_Area_Pend_Ref(ataldef, aquem, aest, atal));
                   }
                   else
                   {
                    HashMap<String, Obj_Area_Pend_Ref> entidad = new HashMap<String, Obj_Area_Pend_Ref>();
                    entidad.put(tipoEntidad, new Obj_Area_Pend_Ref(ataldef, aquem, aest, atal));
                    municipios.put(mun, entidad);
                   }
                 }
    }

    private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Area_Pend_Ref>>> provincias, String tipoEntidad)
    {
                 String prov = "";
                 String mun = "";
                   double ataldef = 0;
                   double aquem = 0;
                   double aest = 0;
                   double atal = 0;

                 for(int i=0; i<db.getRowCount(); i++)
                 {
                   ataldef = Double.parseDouble(db.getValueAt(i,0).toString());
                   aquem = Double.parseDouble(db.getValueAt(i,1).toString());
                   atal = Double.parseDouble(db.getValueAt(i,2).toString());
                   aest = Double.parseDouble(db.getValueAt(i,3).toString());
                   prov = db.getValueAt(i,4).toString();
                   mun = db.getValueAt(i,5).toString();

                   if(provincias.containsKey(prov)) //Si ya esta la provincia
                   {
                        if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
                        {
                            if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                            {
                                ataldef = ataldef + provincias.get(prov).get(mun).get(tipoEntidad).getAtaldef();
                                aquem = aquem + provincias.get(prov).get(mun).get(tipoEntidad).getAquem();
                                aest = aest + provincias.get(prov).get(mun).get(tipoEntidad).getAest();
                                atal = atal + provincias.get(prov).get(mun).get(tipoEntidad).getAtal();
                            }

                            provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Area_Pend_Ref(ataldef, aquem, aest, atal));
                        }
                        else
                        {
                            HashMap<String, Obj_Area_Pend_Ref> entidad = new HashMap<String, Obj_Area_Pend_Ref>();
                            entidad.put(tipoEntidad, new Obj_Area_Pend_Ref(ataldef, aquem, aest, atal));
                            provincias.get(prov).put(mun, entidad);
                        }
                   }
                   else
                   {
                    HashMap<String, Obj_Area_Pend_Ref> entidad = new HashMap<String, Obj_Area_Pend_Ref>();
                    entidad.put(tipoEntidad, new Obj_Area_Pend_Ref(ataldef, aquem, aest, atal));
                    HashMap<String, HashMap<String, Obj_Area_Pend_Ref>> municipio = new HashMap<String, HashMap<String, Obj_Area_Pend_Ref>>();
                    municipio.put(mun, entidad);
                    provincias.put(prov, municipio);
                   }
                 }
    }
}