package cif.reportes.clases;

import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Tac_SCP;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Raisel
 */
public class C_1_3_RelacionTac_Model extends AbstractCritReport
{
    public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
    {
     return getReportModel(user, anno, desglose, bu);
    }

    @Override
    protected TableModel getModel_US()
    {
      String sql = getSQLReplacement(ReportQuerys.SQL_1_3_US, user.getIdEntidad(), anno.toString());
      db.executeQueryUperCase(sql);

      return db;
    }

    @Override
    protected TableModel getModel_AP()
    {
      String sql = getSQLReplacement(ReportQuerys.SQL_1_3_AP, user.getIdEntidad(), anno.toString());
      db.executeQueryUperCase(sql);

      return db;
    }

    @Override
    protected TableModel getModel_EFI()
    {
        DefaultTableModel deftm = new DefaultTableModel();

        String sql = getSQLReplacement(ReportQuerys.SQL_1_3_EFI_Subgrupo, user.getIdEntidad(), anno.toString());

        db.executeQueryUperCase(sql);
        if(!db.isEmpty())
        {
         deftm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

         sql = getSQLReplacement(ReportQuerys.SQL_1_3_EFI_TOTAL, user.getIdEntidad(), anno.toString());
         db.executeQuery(sql);

         Object []row = new String []{"TOTAL","","","", db.getValueAt(0,1).toString()};
         deftm.addRow(row);
        }

        return deftm;
    }

    @Override
    protected TableModel getModel_MUN()
    {
       String mun = "";
       String scp = "";

       String tipoEntidad = "";
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.RELAC_TAC_SCP2_COLUMN_NAME};
       DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

       String sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           mun = db.getValueAt(0,0).toString();
           scp = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();

           tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
           row = new String []{"", mun, tipoEntidad, scp};
           deftm.addRow(row);
       }

       sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           mun = db.getValueAt(0,0).toString();
           scp = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
           tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
           row = new String []{"", mun, tipoEntidad, scp};
           deftm.addRow(row);
       }

       sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           mun = db.getValueAt(0,0).toString();
           scp = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
           tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
           row = new String []{"", mun, tipoEntidad, scp};
           deftm.addRow(row);
       }

       //Totales  //              //                    //               //             //                    //
           double tac = 0;
           double totScp = 0;
           sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               tac = Double.parseDouble(db.getValueAt(0,0).toString());
               totScp = Double.parseDouble(db.getValueAt(0,1).toString());
           }

           sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               tac += Double.parseDouble(db.getValueAt(0,0).toString());
               totScp += Double.parseDouble(db.getValueAt(0,1).toString());
           }

           sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               tac += Double.parseDouble(db.getValueAt(0,0).toString());
               totScp += Double.parseDouble(db.getValueAt(0,1).toString());
           }

       Double Scp = Redondear((tac/totScp)*100);
       row = new String []{"TOTAL", "", "TOTAL", Scp.toString()};
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_PROV()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                          CONSTANTS.RELAC_TAC_SCP2_COLUMN_NAME};
       HashMap<String, Obj_Tac_SCP> hashValues = new HashMap<String, Obj_Tac_SCP>();
       
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_3_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = ReportQuerys.SQL_1_3_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

               sql = ReportQuerys.SQL_1_3_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateProvincia_HASH(hashValues);

              //Agregando filas
               Object [] lista = hashValues.keySet().toArray() ;
               double tottac = 0;
               double totscp = 0;
               
               for(int i=0; i<lista.length; i++)
               {
                double tac = hashValues.get(lista[i].toString()).getTac();
                double scp = hashValues.get(lista[i].toString()).getScp();

                Double indScp = Redondear((tac/scp)*100);
                deftm.addRow(new String []{"", lista[i].toString(), indScp.toString()});
                tottac += tac;
                totscp += scp;
               }

                Double indScp = Redondear((tottac/totscp)*100);
                row = new String []{"", "TOTAL", indScp.toString()};

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
       HashMap<String, HashMap<String, Obj_Tac_SCP>> municipios = new HashMap<String, HashMap<String, Obj_Tac_SCP>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.RELAC_TAC_SCP2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_3_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_3_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_3_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateProvincia_Desglose_HASH(municipios, tipoEntidad);

              //Agregando filas
               Object [] listaMun = municipios.keySet().toArray();
               double tottac = 0;
               double totscp = 0;
               
               for(int i=0; i<listaMun.length; i++)
               {
                mun = listaMun[i].toString();
                HashMap<String, Obj_Tac_SCP> entidades = municipios.get(mun);
                Object [] listaEntidades = entidades.keySet().toArray();

                    for(int k=0; k<listaEntidades.length; k++)
                    {
                        double tac = entidades.get(listaEntidades[k].toString()).getTac();
                        double scp = entidades.get(listaEntidades[k].toString()).getScp();
                        
                        Double indScp = Redondear((tac/scp)*100);

                        deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), indScp.toString()});
                        tottac += tac;
                        totscp += scp;
                    }
               }

                Double indScp = Redondear((tottac/totscp)*100);
                row = new String []{"", "TOTAL", "", indScp.toString()};

       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC()
    {
       HashMap<String, Obj_Tac_SCP> hashValues = new HashMap<String, Obj_Tac_SCP>();
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.RELAC_TAC_SCP2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = getSQLReplacement(ReportQuerys.SQL_1_3_PROV_SubGrupoUS, anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = getSQLReplacement(ReportQuerys.SQL_1_3_PROV_SubGrupoAP, anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = getSQLReplacement(ReportQuerys.SQL_1_3_PROV_SubGrupoOTROS, anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

      //Agregando filas
       Object [] lista = hashValues.keySet().toArray() ;
       double tottac = 0;
       double totScp = 0;

       for(int i=0; i<lista.length; i++)
       {
            double tac = hashValues.get(lista[i].toString()).getTac();
            double scp = hashValues.get(lista[i].toString()).getScp();

            Double indScp = Redondear((tac/scp)*100);

            deftm.addRow(new String []{lista[i].toString(), indScp.toString()});
            tottac += tac;
            totScp += scp;
       }

       Double indScp = Redondear((tottac/totScp)*100);
       deftm.addRow(new String []{"TOTAL", indScp.toString()});

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC_DESGLOSE()
    {
       String tipoEntidad = "";

     //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                       prov            mun             ent
       HashMap<String, HashMap<String, HashMap<String, Obj_Tac_SCP>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Tac_SCP>>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.RELAC_TAC_SCP2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_1_3_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
       sql = getSQLReplacement(sql, anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       if(!db.isEmpty())
        updateNacional_Desglose_HASH(provincias, tipoEntidad);

       sql = ReportQuerys.SQL_1_3_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
       sql = getSQLReplacement(sql, anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       if(!db.isEmpty())
        updateNacional_Desglose_HASH(provincias, tipoEntidad);

       sql = ReportQuerys.SQL_1_3_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
       sql = getSQLReplacement(sql, anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       if(!db.isEmpty())
        updateNacional_Desglose_HASH(provincias, tipoEntidad);

      //Agregando filas
       Object [] listaProvincias = provincias.keySet().toArray();
       double Subtottac = 0;
       double Subtotscp = 0;

       double tottac = 0;
       double totscp = 0;
       String prov = "";
       String mun = "";
       for(int i=0; i<listaProvincias.length; i++) //Provincias
       {
        prov = listaProvincias[i].toString();
        HashMap<String, HashMap<String, Obj_Tac_SCP>> municipios = provincias.get(prov);
        Object [] listaMunicipios = municipios.keySet().toArray();

            for(int j=0; j<listaMunicipios.length; j++)   //Municipios
            {
             mun = listaMunicipios[j].toString();
             HashMap<String, Obj_Tac_SCP> entidades = municipios.get(mun);
             Object [] listaEntidades = entidades.keySet().toArray();

                for(int k=0; k<listaEntidades.length; k++)  //Entidades
                {
                    double tac = entidades.get(listaEntidades[k].toString()).getTac();
                    double scp = entidades.get(listaEntidades[k].toString()).getScp();

                    Double indScp = Redondear((tac/scp)*100);
                    deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), indScp.toString()});
                    Subtottac += tac;
                    Subtotscp += scp;
                }

                Double indScp = Redondear((Subtottac/Subtotscp)*100);
                deftm.addRow(new String []{"", "Sub-Total", "", indScp.toString()});
                tottac += Subtottac;
                totscp += Subtotscp;

                Subtottac = 0;
                Subtotscp = 0;
            }
       }

       Double indScp = Redondear((tottac/totscp)*100);
       deftm.addRow(new String []{"TOTAL", "", "", indScp.toString()});

       return deftm;
    }

    @Override
    protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
    {
       Double tac = new Double(0);
       Double scp = new Double(0);

       String entidad = "";
       Object []columNames = null;
       DefaultTableModel deftm = null;
       String sql = "";

       Double totTac = new Double(0);
       Double totScp = new Double(0);

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.RELAC_TAC_SCP2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.RELAC_TAC_SCP2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.RELAC_TAC_SCP2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_3_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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
	       scp = Double.parseDouble(db.getValueAt(i,2).toString());
               deftm.addRow(new String []{entidad, Redondear((tac/scp)*100).toString()});
               totTac += tac;
               totScp += scp;
             }

               totScp = Redondear((totTac/totScp)*100);
               deftm.addRow(new String []{"TOTAL", totScp.toString()});
        }

       return deftm;
    }

    private void updateProvincia_HASH(HashMap<String, Obj_Tac_SCP> hashValues)
    {
         String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
         double tac = 0;
         double scp = 0;
         for(int i=0; i<db.getRowCount(); i++)
         {
           tac = Double.parseDouble(db.getValueAt(i,0).toString());
           scp = Double.parseDouble(db.getValueAt(i,1).toString());
           Ent = db.getValueAt(i,2).toString();

           if(hashValues.containsKey(Ent))
           {
            tac = tac + hashValues.get(Ent).getTac();
            scp = scp + hashValues.get(Ent).getScp();
           }

           hashValues.put(Ent, new Obj_Tac_SCP(tac, scp));
         }
    }

    private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Tac_SCP>> municipios, String tipoEntidad)
    {
         String mun = "";
         double tac = 0;
         double scp = 0;
         for(int i=0; i<db.getRowCount(); i++)
         {
           tac = Double.parseDouble(db.getValueAt(i,0).toString());
           scp = Double.parseDouble(db.getValueAt(i,1).toString());
           mun = db.getValueAt(i,2).toString();

           if(municipios.containsKey(mun))
           {
                if(municipios.get(mun).containsKey(tipoEntidad))
                {
                    tac = tac + municipios.get(mun).get(tipoEntidad).getTac();
                    scp = scp + municipios.get(mun).get(tipoEntidad).getScp();
                }

                municipios.get(mun).put(tipoEntidad, new Obj_Tac_SCP(tac, scp));
           }
           else
           {
            HashMap<String, Obj_Tac_SCP> entidad = new HashMap<String, Obj_Tac_SCP>();
            entidad.put(tipoEntidad, new Obj_Tac_SCP(tac, scp));
            municipios.put(mun, entidad);
           }
         }
    }

    private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Tac_SCP>>> provincias, String tipoEntidad)
    {
         String prov = "";
         String mun = "";
         double tac = 0;
         double scp = 0;

         for(int i=0; i<db.getRowCount(); i++)
         {
           tac = Double.parseDouble(db.getValueAt(i,0).toString());
           scp = Double.parseDouble(db.getValueAt(i,1).toString());
           prov = db.getValueAt(i,2).toString();
           mun = db.getValueAt(i,3).toString();

           if(provincias.containsKey(prov)) //Si ya esta la provincia
           {
                if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
                {
                    if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                    {
                        tac = tac + provincias.get(prov).get(mun).get(tipoEntidad).getTac();
                        scp = scp + provincias.get(prov).get(mun).get(tipoEntidad).getScp();
                    }

                    provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Tac_SCP(tac, scp));
                }
                else
                {
                    HashMap<String, Obj_Tac_SCP> entidad = new HashMap<String, Obj_Tac_SCP>();
                    entidad.put(tipoEntidad, new Obj_Tac_SCP(tac, scp));
                    provincias.get(prov).put(mun, entidad);
                }
           }
           else
           {
            HashMap<String, Obj_Tac_SCP> entidad = new HashMap<String, Obj_Tac_SCP>();
            entidad.put(tipoEntidad, new Obj_Tac_SCP(tac, scp));
            HashMap<String, HashMap<String, Obj_Tac_SCP>> municipio = new HashMap<String, HashMap<String, Obj_Tac_SCP>>();
            municipio.put(mun, entidad);
            provincias.put(prov, municipio);
           }
         }
    }
}