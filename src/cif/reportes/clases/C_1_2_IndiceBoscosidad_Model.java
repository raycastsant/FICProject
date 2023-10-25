package cif.reportes.clases;

import cif.reportes.objects.Obj_Tac_SGeog;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
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
public class C_1_2_IndiceBoscosidad_Model extends AbstractCritReport
{
    public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
    {
     return getReportModel(user, anno, desglose, bu);
    }

    @Override
    protected TableModel getModel_US()
    {
       String sql = getSQLReplacement(ReportQuerys.SQL_1_2_US, user.getIdEntidad(), anno.toString());
       db.executeQueryUperCase(sql);
       
       return db;
    }

    @Override
    protected TableModel getModel_AP()
    {
      String sql = getSQLReplacement(ReportQuerys.SQL_1_2_AP, user.getIdEntidad(), anno.toString());
      db.executeQueryUperCase(sql);
      
      return db;
    }

    @Override
    protected TableModel getModel_EFI()
    {
       DefaultTableModel tm = new DefaultTableModel();
       String sql = getSQLReplacement(ReportQuerys.SQL_1_2_EFI_Subgrupo, user.getIdEntidad(), anno.toString());

       db.executeQueryUperCase(sql);
       if(!db.isEmpty())
       {
          tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

          sql = getSQLReplacement(ReportQuerys.SQL_1_2_EFI_TOTAL, user.getIdEntidad(), anno.toString());
          db.executeQuery(sql);

          Object []row = new String []{"TOTAL","","","", db.getValueAt(0,1).toString()};
          tm.addRow(row);
       }

       return tm;
    }

    @Override
    protected TableModel getModel_MUN()
    {
//           String prov = "";
           String mun = "";
           String supGeog = "";
           String tipoEntidad = "";
           Object []row = null;
           Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                              CONSTANTS.IND_BOSC2_COLUMN_NAME};
           DefaultTableModel deftm = new DefaultTableModel(columNames, 0);
//           Double totSupGeog = new Double(0);

           String sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
//               prov = db.getValueAt(0,0).toString();
               mun = db.getValueAt(0,0).toString();
               supGeog = db.getValueAt(0,1).toString();
               supGeog = Redondear(Double.parseDouble(supGeog)).toString();
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               row = new String []{"", mun, tipoEntidad, supGeog};
               deftm.addRow(row);
//               totSupGeog += Double.parseDouble(supGeog);
           }

           sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               mun = db.getValueAt(0,0).toString();
               supGeog = db.getValueAt(0,1).toString();
               supGeog = Redondear(Double.parseDouble(supGeog)).toString();
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               row = new String []{"", mun, tipoEntidad, supGeog};
               deftm.addRow(row);
//               totSupGeog += Double.parseDouble(supGeog);
           }

           sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               mun = db.getValueAt(0,0).toString();
               supGeog = db.getValueAt(0,1).toString();
               supGeog = Redondear(Double.parseDouble(supGeog)).toString();
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               row = new String []{"", mun, tipoEntidad, supGeog};
               deftm.addRow(row);
//               totSupGeog += Double.parseDouble(supGeog);
           }

           //Totales  //              //                    //               //             //                    //
               double tac = 0;
               double sgeog = 0;
               sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   tac = Double.parseDouble(db.getValueAt(0,0).toString());
                   sgeog = Double.parseDouble(db.getValueAt(0,1).toString());
               }

               sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   tac += Double.parseDouble(db.getValueAt(0,0).toString());
                   sgeog += Double.parseDouble(db.getValueAt(0,1).toString());
               }

               sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
               {
                   tac += Double.parseDouble(db.getValueAt(0,0).toString());
                   sgeog += Double.parseDouble(db.getValueAt(0,1).toString());
               }

           Double totSupGeog = (tac/sgeog)*100;
           totSupGeog = Redondear(totSupGeog);
           row = new String []{"TOTAL", "", "TOTAL", totSupGeog.toString()};
           deftm.addRow(row);

           return deftm;
    }

    @Override
    protected TableModel getModel_PROV()
    {
       HashMap<String, Obj_Tac_SGeog> hashValues = new HashMap<String, Obj_Tac_SGeog>();
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                          CONSTANTS.IND_BOSC2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_2_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = ReportQuerys.SQL_1_2_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = ReportQuerys.SQL_1_2_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

              //Agregando filas
               Object [] lista = hashValues.keySet().toArray() ;
               double tottac = 0;
               double totsgeog = 0;
               for(int i=0; i<lista.length; i++)
               {
                double tac = hashValues.get(lista[i].toString()).getTac();
                double sg = hashValues.get(lista[i].toString()).getSgeog();
                Double ind = Redondear((tac/sg)*100);
                deftm.addRow(new String []{"", lista[i].toString(), ind.toString()});
                tottac += tac;
                totsgeog += sg;
               }

           Double IND = Redondear((tottac/totsgeog)*100);
           row = new String []{"", "TOTAL", IND.toString()};
           
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
       HashMap<String, HashMap<String, Obj_Tac_SGeog>> municipios = new HashMap<String, HashMap<String, Obj_Tac_SGeog>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                              CONSTANTS.IND_BOSC2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_2_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_2_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_2_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

              //Agregando filas
               Object [] listaMun = municipios.keySet().toArray();
               double tottac = 0;
               double totsgeog = 0;
               for(int i=0; i<listaMun.length; i++)
               {
                mun = listaMun[i].toString();
                HashMap<String, Obj_Tac_SGeog> entidades = municipios.get(mun);
                Object [] listaEntidades = entidades.keySet().toArray();
                
                    for(int k=0; k<listaEntidades.length; k++)
                    {
                        double tac = entidades.get(listaEntidades[k].toString()).getTac();
                        double sg = entidades.get(listaEntidades[k].toString()).getSgeog();
                        Double ind = Redondear((tac/sg)*100);

                        deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), ind.toString()});
                        tottac += tac;
                        totsgeog += sg;
                    }
               }

           Double IND = Redondear((tottac/totsgeog)*100);
           row = new String []{"", "TOTAL", "", IND.toString()};
           
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC()
    {
       Object []row = null;
       HashMap<String, Obj_Tac_SGeog> hashValues = new HashMap<String, Obj_Tac_SGeog>();
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.IND_BOSC2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = getSQLReplacement(ReportQuerys.SQL_1_2_PROV_SubGrupoUS, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = getSQLReplacement(ReportQuerys.SQL_1_2_PROV_SubGrupoAP, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = getSQLReplacement(ReportQuerys.SQL_1_2_PROV_SubGrupoOTROS, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

              //Agregando filas
               Object [] lista = hashValues.keySet().toArray() ;
               double tottac = 0;
               double totsgeog = 0;
               for(int i=0; i<lista.length; i++)
               {
                double tac = hashValues.get(lista[i].toString()).getTac();
                double sg = hashValues.get(lista[i].toString()).getSgeog();
                Double ind = Redondear((tac/sg)*100);
                deftm.addRow(new String []{lista[i].toString(), ind.toString()});
                tottac += tac;
                totsgeog += sg;
               }

           Double IND = Redondear((tottac/totsgeog)*100);
           row = new String []{"TOTAL", IND.toString()};

       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC_DESGLOSE()
    {   
       Object []row = null;
       String tipoEntidad = "";

     //Provincia, [ Municipio, [ Entidad, Obj_Tac_SGeog] ]                                       prov            mun             ent
       HashMap<String, HashMap<String, HashMap<String, Obj_Tac_SGeog>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Tac_SGeog>>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.IND_BOSC2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_2_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
               sql = getSQLReplacement(sql, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

               sql = ReportQuerys.SQL_1_2_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
               sql = getSQLReplacement(sql, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

               sql = ReportQuerys.SQL_1_2_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
               sql = getSQLReplacement(sql, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

              //Agregando filas
               Object [] listaProvincias = provincias.keySet().toArray();
               double Subtottac = 0;
               double Subtotsgeog = 0;
               double tottac = 0;
               double totsgeog = 0;
               String prov = "";
               String mun = "";
               for(int i=0; i<listaProvincias.length; i++) //Provincias
               {
                prov = listaProvincias[i].toString();
                HashMap<String, HashMap<String, Obj_Tac_SGeog>> municipios = provincias.get(prov);
                Object [] listaMunicipios = municipios.keySet().toArray();

                    for(int j=0; j<listaMunicipios.length; j++)   //Municipios
                    {
                     mun = listaMunicipios[j].toString();
                     HashMap<String, Obj_Tac_SGeog> entidades = municipios.get(mun);
                     Object [] listaEntidades = entidades.keySet().toArray();

                        for(int k=0; k<listaEntidades.length; k++)  //Entidades
                        {
                            double tac = entidades.get(listaEntidades[k].toString()).getTac();
                            double sg = entidades.get(listaEntidades[k].toString()).getSgeog();
                            Double ind = Redondear((tac/sg)*100);

                            deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), ind.toString()});
                            Subtottac += tac;
                            Subtotsgeog += sg;
                        }
                     
                        Double ind = Redondear((Subtottac/Subtotsgeog)*100);
                        deftm.addRow(new String []{"", "Sub-Total", "", ind.toString()});
                        tottac += Subtottac;
                        totsgeog += Subtotsgeog;
                        Subtottac = 0;
                        Subtotsgeog = 0;
                    }
               }

           Double IND = Redondear((tottac/totsgeog)*100);
           row = new String []{"TOTAL", "", "", IND.toString()};

       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
    {
       String supGeog = "";
       String entidad = "";
       Object []columNames = null;   
       DefaultTableModel deftm = null;   
       String sql = "";
//       Double totSupGeog = new Double(0);

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.IND_BOSC2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.IND_BOSC2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.IND_BOSC2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_2_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

              supGeog = db.getValueAt(i,1).toString();
              supGeog = Redondear(Double.parseDouble(supGeog)).toString();
              deftm.addRow(new String []{entidad, supGeog});
//              totSupGeog += Double.parseDouble(supGeog);
             }
//             totSupGeog = Redondear(totSupGeog);
//             deftm.addRow(new String []{"TOTAL", totSupGeog.toString()});
        }

       return deftm;
    }

    private void updateProvincia_HASH(HashMap<String, Obj_Tac_SGeog> hashValues)
    {
                 String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
                 double tac = 0;
                 double sgeog = 0;
                 for(int i=0; i<db.getRowCount(); i++)
                 {
                   tac = Double.parseDouble(db.getValueAt(i,0).toString());
                   sgeog = Double.parseDouble(db.getValueAt(i,1).toString());
                   Ent = db.getValueAt(i,2).toString();

                   if(hashValues.containsKey(Ent))
                   {
                    tac = tac + hashValues.get(Ent).getTac();
                    sgeog = sgeog + hashValues.get(Ent).getSgeog();
                   }

                   hashValues.put(Ent, new Obj_Tac_SGeog(tac, sgeog));
                 }
    }

    private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Tac_SGeog>> municipios, String tipoEntidad)
    {
                 String mun = "";
                 double tac = 0;
                 double sgeog = 0;
                 for(int i=0; i<db.getRowCount(); i++)
                 {
                   tac = Double.parseDouble(db.getValueAt(i,0).toString());
                   sgeog = Double.parseDouble(db.getValueAt(i,1).toString());
                   mun = db.getValueAt(i,2).toString();

                   if(municipios.containsKey(mun))
                   {
                        if(municipios.get(mun).containsKey(tipoEntidad))
                        {
                            tac = tac + municipios.get(mun).get(tipoEntidad).getTac();
                            sgeog = sgeog + municipios.get(mun).get(tipoEntidad).getSgeog();
                        }

                        municipios.get(mun).put(tipoEntidad, new Obj_Tac_SGeog(tac, sgeog));
                   }
                   else
                   {
                    HashMap<String, Obj_Tac_SGeog> entidad = new HashMap<String, Obj_Tac_SGeog>();
                    entidad.put(tipoEntidad, new Obj_Tac_SGeog(tac, sgeog));
                    municipios.put(mun, entidad);
                   }
                 }
    }

    private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Tac_SGeog>>> provincias, String tipoEntidad)
    {
                 String prov = "";
                 String mun = "";
                 double tac = 0;
                 double sgeog = 0;
                 for(int i=0; i<db.getRowCount(); i++)
                 {
                   tac = Double.parseDouble(db.getValueAt(i,0).toString());
                   sgeog = Double.parseDouble(db.getValueAt(i,1).toString());
                   prov = db.getValueAt(i,2).toString();
                   mun = db.getValueAt(i,3).toString();

                   if(provincias.containsKey(prov)) //Si ya esta la provincia
                   {
                        if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
                        {
                            if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                            {
                                tac = tac + provincias.get(prov).get(mun).get(tipoEntidad).getTac();
                                sgeog = sgeog + provincias.get(prov).get(mun).get(tipoEntidad).getSgeog();
                            }

                            provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Tac_SGeog(tac, sgeog));
                        }
                        else
                        {
                            HashMap<String, Obj_Tac_SGeog> entidad = new HashMap<String, Obj_Tac_SGeog>();
                            entidad.put(tipoEntidad, new Obj_Tac_SGeog(tac, sgeog));
                            provincias.get(prov).put(mun, entidad);        
                        }
                   }
                   else
                   {
                    HashMap<String, Obj_Tac_SGeog> entidad = new HashMap<String, Obj_Tac_SGeog>();
                    entidad.put(tipoEntidad, new Obj_Tac_SGeog(tac, sgeog));
                    HashMap<String, HashMap<String, Obj_Tac_SGeog>> municipio = new HashMap<String, HashMap<String, Obj_Tac_SGeog>>();
                    municipio.put(mun, entidad);
                    provincias.put(prov, municipio);
                   }
                 }
    }
}