package cif.reportes.clases;

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
public class C_2_1_BosquesAfectadosInc_Model extends AbstractCritReport implements IReportModel
{
    public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
    {
      return getReportModel(user, anno, desglose, bu);
    }

    @Override
    protected TableModel getModel_AP()
    {
      String sql = getSQLReplacement(ReportQuerys.SQL_2_1_AP, user.getIdEntidad(), anno.toString());
      db.executeQueryUperCase(sql);

      return db;
    }

    @Override
    protected TableModel getModel_US()
    {
      String sql = getSQLReplacement(ReportQuerys.SQL_2_1_US, user.getIdEntidad(), anno.toString());
      db.executeQueryUperCase(sql);

      return db;
    }

    @Override
    protected TableModel getModel_EFI()
    {
       String cant = "0";
       String distrec = "0";
       String supafect = "0";
       String esp = "";
       Double perddir = new Double(0);
       Double perdind = new Double(0);
       Double haperd = new Double(0);
       Double totperddir = new Double(0);
       Double totperdind = new Double(0);
       Double tothaperd = new Double(0);
       
       String sql = getSQLReplacement(ReportQuerys.SQL_2_1_EFI_Subgrupo, user.getIdEntidad(), anno.toString());

       DefaultTableModel deftm = new DefaultTableModel();

       db.executeQueryUperCase(sql);
       if(!db.isEmpty())
       {
            deftm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

            sql = getSQLReplacement(ReportQuerys.SQL_2_1_EFI_TOTAL1, user.getIdEntidad(), anno.toString());
            db.executeQuery(sql);
            if(!db.isEmpty())
            {
             cant = db.getValueAt(0,1).toString();
             distrec = db.getValueAt(0,2).toString();
             supafect = db.getValueAt(0,3).toString();
            }

            sql = getSQLReplacement(ReportQuerys.SQL_2_1_EFI_TOTAL2, user.getIdEntidad(), anno.toString());
            db.executeQuery(sql);
            if(!db.isEmpty())
            {
             for(int i=0; i<db.getRowCount(); i++)
             {
                 esp = db.getValueAt(i,0).toString();
                 perddir = Double.parseDouble(db.getValueAt(i,1).toString());
                 perdind = Double.parseDouble(db.getValueAt(i,2).toString());
                 haperd = Double.parseDouble(db.getValueAt(i,3).toString());

                 deftm.addRow(new String []{"Total","","","", "", "", "", perddir.toString(), perdind.toString(), haperd.toString(), esp});
                 totperddir += perddir;
                 totperdind += perdind;
                 tothaperd += haperd;
             }
            }

             Object []row = new String []{"TOTAL","","","", cant, distrec, supafect, totperddir.toString(), totperdind.toString(), tothaperd.toString(), ""};
             deftm.addRow(row);
       }

       return deftm;
    }

    @Override
    protected TableModel getModel_MUN()
    {
           String prov = "";
           String mun = "";
           String especie = "";
           String tipoEntidad = "";

           String cant = "";
           String distrec = "";
           String supafect = "";
           String pdir = "";
           String pind = "";
           String hperd = "";

           HashMap<String, Totals> totEspecies = new HashMap<String, Totals>();

           Object []row = null;
           Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                              CONSTANTS.CANTIDAD2_COLUMN_NAME, CONSTANTS.DISTANCIA_RECORR2_COLUMN_NAME, CONSTANTS.SUP_AFECT_TOTAL2_COLUMN_NAME,
                                              CONSTANTS.PERDIDA_DIRECTA2_COLUMN_NAME, CONSTANTS.PERDIDA_INDIRECTA2_COLUMN_NAME, CONSTANTS.HECTAREAS_PERD2_COLUMN_NAME,
                                              CONSTANTS.ESPECIE_COLUMN_NAME};
           DefaultTableModel deftm= new DefaultTableModel(columNames, 0);
           Double totCant = new Double(0);
           Double totDistrec = new Double(0);
           Double totSupafect = new Double(0);
           Double totPDir = new Double(0);
           Double totPInd = new Double(0);
           Double totHPerd = new Double(0);

           String sql = getSQLReplacement(ReportQuerys.SQL_2_1_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               for(int i=0; i<db.getRowCount(); i++)
               {
                   prov = db.getValueAt(i,0).toString();
                   mun = db.getValueAt(i,1).toString();
                   cant = db.getValueAt(i,2).toString();
                   distrec = db.getValueAt(i,3).toString();
                   supafect = db.getValueAt(i,4).toString();
                   pdir = db.getValueAt(i,5).toString();
                   pind = db.getValueAt(i,6).toString();
                   hperd = db.getValueAt(i,7).toString();
                   especie = db.getValueAt(i,8).toString();

                   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
                   row = new String []{prov, mun, tipoEntidad, cant, distrec, supafect, pdir, pind, hperd, especie};
                   deftm.addRow(row);

                   if(i==0)
                   {
                       totCant += Double.parseDouble(cant);
                       totDistrec += Double.parseDouble(distrec);
                       totSupafect += Double.parseDouble(supafect);
                   }
                   
                   totPDir += Double.parseDouble(pdir);
                   totPInd += Double.parseDouble(pind);
                   totHPerd += Double.parseDouble(hperd);

                   Totals tot = totEspecies.get(especie);
                   if(tot == null)
                    totEspecies.put(especie, new Totals(0, 0, 0, Double.parseDouble(pdir), Double.parseDouble(pind), Double.parseDouble(hperd)));
                   else
                    totEspecies.put(especie, new Totals(0, 0, 0, Double.parseDouble(pdir)+tot.totPDir, Double.parseDouble(pind)+tot.totPInd,
                                                        Double.parseDouble(hperd)+tot.totHPerd));
               }
           }

           sql = getSQLReplacement(ReportQuerys.SQL_2_1_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               for(int i=0; i<db.getRowCount(); i++)
               {
                   prov = db.getValueAt(i,0).toString();
                   mun = db.getValueAt(i,1).toString();
                   cant = db.getValueAt(i,2).toString();
                   distrec = db.getValueAt(i,3).toString();
                   supafect = db.getValueAt(i,4).toString();
                   pdir = db.getValueAt(i,5).toString();
                   pind = db.getValueAt(i,6).toString();
                   hperd = db.getValueAt(i,7).toString();
                   especie = db.getValueAt(i,8).toString();

                   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
                   row = new String []{prov, mun, tipoEntidad, cant, distrec, supafect, pdir, pind, hperd, especie};
                   deftm.addRow(row);

                   if(i==0)
                   {
                       totCant += Double.parseDouble(cant);
                       totDistrec += Double.parseDouble(distrec);
                       totSupafect += Double.parseDouble(supafect);
                   }

                   totPDir += Double.parseDouble(pdir);
                   totPInd += Double.parseDouble(pind);
                   totHPerd += Double.parseDouble(hperd);

                   Totals tot = totEspecies.get(especie);
                   if(tot == null)
                    totEspecies.put(especie, new Totals(0, 0, 0, Double.parseDouble(pdir), Double.parseDouble(pind), Double.parseDouble(hperd)));
                   else
                    totEspecies.put(especie, new Totals(0, 0, 0, Double.parseDouble(pdir)+tot.totPDir, Double.parseDouble(pind)+tot.totPInd,
                                                        Double.parseDouble(hperd)+tot.totHPerd));
               }
           }

           sql = getSQLReplacement(ReportQuerys.SQL_2_1_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               for(int i=0; i<db.getRowCount(); i++)
               {
                   prov = db.getValueAt(i,0).toString();
                   mun = db.getValueAt(i,1).toString();
                   cant = db.getValueAt(i,2).toString();
                   distrec = db.getValueAt(i,3).toString();
                   supafect = db.getValueAt(i,4).toString();
                   pdir = db.getValueAt(i,5).toString();
                   pind = db.getValueAt(i,6).toString();
                   hperd = db.getValueAt(i,7).toString();
                   especie = db.getValueAt(i,8).toString();

                   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
                   row = new String []{prov, mun, tipoEntidad, cant, distrec, supafect, pdir, pind, hperd, especie};
                   deftm.addRow(row);

                   if(i==0)
                   {
                       totCant += Double.parseDouble(cant);
                       totDistrec += Double.parseDouble(distrec);
                       totSupafect += Double.parseDouble(supafect);
                   }

                   totPDir += Double.parseDouble(pdir);
                   totPInd += Double.parseDouble(pind);
                   totHPerd += Double.parseDouble(hperd);

                   Totals tot = totEspecies.get(especie);
                   if(tot == null)
                    totEspecies.put(especie, new Totals(0, 0, 0, Double.parseDouble(pdir), Double.parseDouble(pind), Double.parseDouble(hperd)));
                   else
                    totEspecies.put(especie, new Totals(0, 0, 0, Double.parseDouble(pdir)+tot.totPDir, Double.parseDouble(pind)+tot.totPInd,
                                                        Double.parseDouble(hperd)+tot.totHPerd));
               }
           }

           Object []listaesp = totEspecies.keySet().toArray();
           for(int i=0; i<listaesp.length; i++)
           {
             Totals tot = totEspecies.get(listaesp[i].toString());
             deftm.addRow(new String []{"", "", "Total", "", "", "", tot.totPDir.toString(), tot.totPInd.toString(), tot.totHPerd.toString(), listaesp[i].toString()});
           }
           
           row = new String []{"", "", "TOTAL", totCant.toString(), totDistrec.toString(), totSupafect.toString(), totPDir.toString(), totPInd.toString(), totHPerd.toString(), ""};
           deftm.addRow(row);

        return deftm;
    }

    @Override
    protected TableModel getModel_PROV()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                          CONSTANTS.CANTIDAD2_COLUMN_NAME, CONSTANTS.DISTANCIA_RECORR2_COLUMN_NAME, CONSTANTS.SUP_AFECT_TOTAL2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DIRECTA2_COLUMN_NAME, CONSTANTS.PERDIDA_INDIRECTA2_COLUMN_NAME, CONSTANTS.HECTAREAS_PERD2_COLUMN_NAME};
       DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

       Totals rowTotals = new Totals(0,0,0,0,0,0);

       rowTotals = updateProvincia_MunRow_PROV(ReportQuerys.SQL_2_1_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), deftm, rowTotals);

       rowTotals = updateProvincia_MunRow_PROV(ReportQuerys.SQL_2_1_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals);

       rowTotals = updateProvincia_MunRow_PROV(ReportQuerys.SQL_2_1_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals);

       row = new String []{"", "TOTAL", rowTotals.totCant.toString(), rowTotals.totDistrec.toString(), rowTotals.totSupafect.toString(),
                           rowTotals.totPDir.toString(), rowTotals.totPInd.toString(), rowTotals.totHPerd.toString()};
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_PROV_DESGLOSE()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.CANTIDAD2_COLUMN_NAME, CONSTANTS.DISTANCIA_RECORR2_COLUMN_NAME, CONSTANTS.SUP_AFECT_TOTAL2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DIRECTA2_COLUMN_NAME, CONSTANTS.PERDIDA_INDIRECTA2_COLUMN_NAME, CONSTANTS.HECTAREAS_PERD2_COLUMN_NAME,
                                          CONSTANTS.ESPECIE_COLUMN_NAME};
       DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

       Totals rowTotals = new Totals(0,0,0,0,0,0);
       HashMap<String, Totals> totEspecies = new HashMap<String, Totals>();

       rowTotals = updateDesgloseRow_PROV(ReportQuerys.SQL_2_1_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), deftm, rowTotals, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS, totEspecies);

       rowTotals = updateDesgloseRow_PROV(ReportQuerys.SQL_2_1_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS, totEspecies);

       rowTotals = updateDesgloseRow_PROV(ReportQuerys.SQL_2_1_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals, CONSTANTS.ENTIDAD_OTROS, totEspecies);

       Object []listaesp = totEspecies.keySet().toArray();
       for(int i=0; i<listaesp.length; i++)
       {
         Totals tot = totEspecies.get(listaesp[i].toString());
         deftm.addRow(new String []{"", "Total", "", "", "", "", tot.totPDir.toString(), tot.totPInd.toString(), tot.totHPerd.toString(), listaesp[i].toString()});
       }

       row = new String []{"", "TOTAL", "", rowTotals.totCant.toString(), rowTotals.totDistrec.toString(), rowTotals.totSupafect.toString(),
                          rowTotals.totPDir.toString(), rowTotals.totPInd.toString(), rowTotals.totHPerd.toString(), ""};
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.CANTIDAD2_COLUMN_NAME, CONSTANTS.DISTANCIA_RECORR2_COLUMN_NAME, CONSTANTS.SUP_AFECT_TOTAL2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DIRECTA2_COLUMN_NAME, CONSTANTS.PERDIDA_INDIRECTA2_COLUMN_NAME, CONSTANTS.HECTAREAS_PERD2_COLUMN_NAME};
       DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

       Totals rowTotals = new Totals(0,0,0,0,0,0);

       rowTotals = updateProvinciaRow_DNF(ReportQuerys.SQL_2_1_MUN_SubGrupoUS.replaceFirst("municipios.id=':id' and", ""), anno.toString(), deftm, rowTotals);

       rowTotals = updateProvinciaRow_DNF(ReportQuerys.SQL_2_1_MUN_SubGrupoAP.replaceFirst("municipios.id=':id' and", ""), anno.toString(), deftm, rowTotals);

       rowTotals = updateProvinciaRow_DNF(ReportQuerys.SQL_2_1_MUN_SubGrupoOTROS.replaceFirst("municipios.id=':id' and", ""), anno.toString(), deftm, rowTotals);

       row = new String []{"TOTAL", rowTotals.totCant.toString(), rowTotals.totDistrec.toString(), rowTotals.totSupafect.toString(),
                          rowTotals.totPDir.toString(), rowTotals.totPInd.toString(), rowTotals.totHPerd.toString()};
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC_DESGLOSE()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.CANTIDAD2_COLUMN_NAME, CONSTANTS.DISTANCIA_RECORR2_COLUMN_NAME, CONSTANTS.SUP_AFECT_TOTAL2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DIRECTA2_COLUMN_NAME, CONSTANTS.PERDIDA_INDIRECTA2_COLUMN_NAME, CONSTANTS.HECTAREAS_PERD2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME};
       DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

       Totals rowTotals = new Totals(0,0,0,0,0,0);

       rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_2_1_MUN_SubGrupoUS.replaceFirst("municipios.id=':id' and", ""), anno.toString(), deftm, rowTotals, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

       rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_2_1_MUN_SubGrupoAP.replaceFirst("municipios.id=':id' and", ""), anno.toString(), deftm, rowTotals, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

       rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_2_1_MUN_SubGrupoOTROS.replaceFirst("municipios.id=':id' and", ""), anno.toString(), deftm, rowTotals, CONSTANTS.ENTIDAD_OTROS);

       row = new String []{"TOTAL", "", "", rowTotals.totCant.toString(), rowTotals.totDistrec.toString(), rowTotals.totSupafect.toString(),
                          rowTotals.totPDir.toString(), rowTotals.totPInd.toString(), rowTotals.totHPerd.toString(), ""};
       deftm.addRow(row);

       AddMun_Subtotals(deftm);

       return deftm;
    }

    private Totals updateProvincia_MunRow_PROV(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm, Totals tot)
    {
       String prov = "";
       String mun = "";
       Object []row = null;

       Double cant = new Double(0);
       Double distrec = new Double(0);
       Double supafect = new Double(0);
       Double pdir = new Double(0);
       Double pind = new Double(0);
       Double hperd = new Double(0);

       Double totCant = tot.totCant;
       Double totDistrec = tot.totDistrec;
       Double totSupafect = tot.totSupafect;
       Double totPDir = tot.totPDir;
       Double totPInd = tot.totPInd;
       Double totHPerd = tot.totHPerd;

       String sql = SqlPattern.replaceFirst("where municipios.id", "where provincias.id");
//       sql = sql.replaceAll(", especie.nombre", "");

       sql = getSQLReplacement(sql, Entidad, anno);
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           String antMun = "";
           for(int i=0; i<db.getRowCount(); i++)
           {
               boolean rowUpdated = false;

               prov = db.getValueAt(i,0).toString();
               mun = db.getValueAt(i,1).toString();
               cant = Double.parseDouble(db.getValueAt(i,2).toString());
               distrec = Double.parseDouble(db.getValueAt(i,3).toString());
               supafect = Double.parseDouble(db.getValueAt(i,4).toString());
               pdir = Double.parseDouble(db.getValueAt(i,5).toString());
               pind = Double.parseDouble(db.getValueAt(i,6).toString());
               hperd = Double.parseDouble(db.getValueAt(i,7).toString());
               totPDir += pdir;
               totPInd += pind;
               totHPerd += hperd;

               if(!mun.equals(antMun))
               {
                  totCant += cant;
                  totDistrec += distrec;
                  totSupafect += supafect;
                  antMun = mun;
               }

               if(dftm.getRowCount() > 0)
               {
                    String tmMun = "";
                    for(int k=0; k<dftm.getRowCount(); k++)
                    {
                     tmMun = dftm.getValueAt(k,1).toString();
                     if(tmMun.equals(mun))
                     {
                      double tempCant = Double.parseDouble(dftm.getValueAt(k,2).toString());
                      double tempDistrec = Double.parseDouble(dftm.getValueAt(k,3).toString());
                      double tempSupafect = Double.parseDouble(dftm.getValueAt(k,4).toString());
                      double tempPDir = Double.parseDouble(dftm.getValueAt(k,5).toString());
                      double tempPInd = Double.parseDouble(dftm.getValueAt(k,6).toString());
                      double tempHPerd = Double.parseDouble(dftm.getValueAt(k,7).toString());

                      dftm.setValueAt((tempCant+cant), k, 2);
                      dftm.setValueAt((tempDistrec+distrec), k, 3);
                      dftm.setValueAt((tempSupafect+supafect), k, 4);
                      dftm.setValueAt((tempPDir+pdir), k, 5);
                      dftm.setValueAt((tempPInd+pind), k, 6);
                      dftm.setValueAt((tempHPerd+hperd), k, 7);

                      rowUpdated = true;
                      break;
                     }
                    }
               }

               if(!rowUpdated)
               {
                row = new String []{prov, mun, cant.toString(), distrec.toString(), supafect.toString(),
                                    pdir.toString(), pind.toString(), hperd.toString()};
                dftm.addRow(row);
               }
           }
       }

       return new Totals(totCant, totDistrec, totSupafect, totPDir, totPInd, totHPerd);
   }
//
    private Totals updateProvinciaRow_DNF(String SqlPattern, String anno, DefaultTableModel dftm, Totals tot)
    {
       String prov = "";
       Object []row = null;

       Double cant = new Double(0);
       Double distrec = new Double(0);
       Double supafect = new Double(0);
       Double pdir = new Double(0);
       Double pind = new Double(0);
       Double hperd = new Double(0);

       Double totCant = tot.totCant;
       Double totDistrec = tot.totDistrec;
       Double totSupafect = tot.totSupafect;
       Double totPDir = tot.totPDir;
       Double totPInd = tot.totPInd;
       Double totHPerd = tot.totHPerd;

       String sql = getSQLReplacement(SqlPattern, anno);
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
//           String antMun = "";
           for(int i=0; i<db.getRowCount(); i++)
           {
               boolean rowUpdated = false;

               prov = db.getValueAt(i,0).toString();
               String mun = db.getValueAt(i,1).toString();
               cant = Double.parseDouble(db.getValueAt(i,2).toString());
               distrec = Double.parseDouble(db.getValueAt(i,3).toString());
               supafect = Double.parseDouble(db.getValueAt(i,4).toString());
               pdir = Double.parseDouble(db.getValueAt(i,5).toString());
               pind = Double.parseDouble(db.getValueAt(i,6).toString());
               hperd = Double.parseDouble(db.getValueAt(i,7).toString());
               totPDir += pdir;
               totPInd += pind;
               totHPerd += hperd;

//               if(!mun.equals(antMun))
//               {
                  totCant += cant;
                  totDistrec += distrec;
                  totSupafect += supafect;
//                  antMun = mun;
//               }

               if(dftm.getRowCount() > 0)
               {
                    String tmProv = "";
                    for(int k=0; k<dftm.getRowCount(); k++)
                    {
                     tmProv = dftm.getValueAt(k,0).toString();
                     if(tmProv.equals(prov))
                     {
                      double tempCant = Double.parseDouble(dftm.getValueAt(k,1).toString());
                      double tempDistrec = Double.parseDouble(dftm.getValueAt(k,2).toString());
                      double tempSupafect = Double.parseDouble(dftm.getValueAt(k,3).toString());
                      double tempPDir = Double.parseDouble(dftm.getValueAt(k,4).toString());
                      double tempPInd = Double.parseDouble(dftm.getValueAt(k,5).toString());
                      double tempHPerd = Double.parseDouble(dftm.getValueAt(k,6).toString());

                      dftm.setValueAt((tempCant+cant), k, 1);
                      dftm.setValueAt((tempDistrec+distrec), k, 2);
                      dftm.setValueAt((tempSupafect+supafect), k, 3);
                      dftm.setValueAt((tempPDir+pdir), k, 4);
                      dftm.setValueAt((tempPInd+pind), k, 5);
                      dftm.setValueAt((tempHPerd+hperd), k, 6);

                      rowUpdated = true;
                      break;
                     }
                    }
               }

               if(!rowUpdated)
               {
                row = new String []{prov, cant.toString(), distrec.toString(), supafect.toString(), pdir.toString(),
                                    pind.toString(), hperd.toString()};
                dftm.addRow(row);
               }
           }
       }

       return new Totals(totCant, totDistrec, totSupafect, totPDir, totPInd, totHPerd);
   }

    private Totals updateDesgloseRow_PROV(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm, Totals tot, String tipoEntidad, HashMap<String, Totals> totEspecies)
    {
       String prov = "";
       String mun = "";
       Object []row = null;
       String especie = "";

       Double cant = new Double(0);
       Double distrec = new Double(0);
       Double supafect = new Double(0);
       Double pdir = new Double(0);
       Double pind = new Double(0);
       Double hperd = new Double(0);

       Double totCant = tot.totCant;
       Double totDistrec = tot.totDistrec;
       Double totSupafect = tot.totSupafect;
       Double totPDir = tot.totPDir;
       Double totPInd = tot.totPInd;
       Double totHPerd = tot.totHPerd;

       String sql = SqlPattern.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, Entidad, anno);
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           String antMun = "";
           for(int i=0; i<db.getRowCount(); i++)
           {
               int rowIndex = dftm.getRowCount();

               prov = db.getValueAt(i,0).toString();
               mun = db.getValueAt(i,1).toString();
               cant = Double.parseDouble(db.getValueAt(i,2).toString());
               distrec = Double.parseDouble(db.getValueAt(i,3).toString());
               supafect = Double.parseDouble(db.getValueAt(i,4).toString());
               pdir = Double.parseDouble(db.getValueAt(i,5).toString());
               pind = Double.parseDouble(db.getValueAt(i,6).toString());
               hperd = Double.parseDouble(db.getValueAt(i,7).toString());
               especie = db.getValueAt(i,8).toString();
               totPDir += pdir;
               totPInd += pind;
               totHPerd += hperd;

               if(!mun.equals(antMun))
               {
                  totCant += cant;
                  totDistrec += distrec;
                  totSupafect += supafect;
                  antMun = mun;
               }

               Totals totValues = totEspecies.get(especie);
               if(totValues == null)
                totEspecies.put(especie, new Totals(0, 0, 0, pdir, pind, hperd));
               else
                totEspecies.put(especie, new Totals(0, 0, 0, pdir+totValues.totPDir, pind+totValues.totPInd, hperd+totValues.totHPerd));
               
               if(dftm.getRowCount() > 0)
               {
                    String tmMun = "";
                    String tmEntidad = "";

                    for(int k=0; k<dftm.getRowCount(); k++)
                    {
                     tmMun = dftm.getValueAt(k,1).toString();
                     tmEntidad = dftm.getValueAt(k,2).toString();

                     if((tmMun.equals(mun)) && (tmEntidad.equals(tipoEntidad)))  //Busco el indice para que la fila quede dentro del municipio y la entidad comun
                      rowIndex = k+1;
                     else
                     if(tmMun.equals(mun))
                      rowIndex = k+1;
                    }
               }

                row = new String []{prov, mun, tipoEntidad, cant.toString(), distrec.toString(), supafect.toString(), pdir.toString(),
                                    pind.toString(), hperd.toString(), especie};
                dftm.insertRow(rowIndex, row);
           }
       }

       return new Totals(totCant, totDistrec, totSupafect, totPDir, totPInd, totHPerd);
    }

    private Totals updateDNF_DesgloseRow(String SqlPattern, String anno, DefaultTableModel dftm, Totals tot, String tipoEntidad)
    {
       String prov = "";
       String mun = "";
       String especie = "";
       Object []row = null;

       Double cant = new Double(0);
       Double distrec = new Double(0);
       Double supafect = new Double(0);
       Double pdir = new Double(0);
       Double pind = new Double(0);
       Double hperd = new Double(0);

       Double totCant = tot.totCant;
       Double totDistrec = tot.totDistrec;
       Double totSupafect = tot.totSupafect;
       Double totPDir = tot.totPDir;
       Double totPInd = tot.totPInd;
       Double totHPerd = tot.totHPerd;

       String sql = SqlPattern.replaceFirst("municipios.id=':id' and", "");
       sql = getSQLReplacement(sql, anno);
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           for(int i=0; i<db.getRowCount(); i++)
           {
               prov = db.getValueAt(i,0).toString();
               mun = db.getValueAt(i,1).toString();
               cant = Double.parseDouble(db.getValueAt(i,2).toString());
               distrec = Double.parseDouble(db.getValueAt(i,3).toString());
               supafect = Double.parseDouble(db.getValueAt(i,4).toString());
               pdir = Double.parseDouble(db.getValueAt(i,5).toString());
               pind = Double.parseDouble(db.getValueAt(i,6).toString());
               hperd = Double.parseDouble(db.getValueAt(i,7).toString());
               especie = db.getValueAt(i,8).toString();
               totCant += cant;
               totDistrec += distrec;
               totSupafect += supafect;
               totPDir += pdir;
               totPInd += pind;
               totHPerd += hperd;

               int rowIndex = getProvincia_Mun_Ent_RowIndex(prov, mun, tipoEntidad, dftm);

               if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
               {
                row = new String []{prov, mun, tipoEntidad, cant.toString(), distrec.toString(), supafect.toString()
                                    , pdir.toString(), pind.toString(), hperd.toString(), especie};
                dftm.addRow(row);
               }
               else
               if(rowIndex >= 0) //insertar
               {
                row = new String []{prov, mun, tipoEntidad, cant.toString(), distrec.toString(), supafect.toString()
                                    , pdir.toString(), pind.toString(), hperd.toString(), especie};
                dftm.insertRow((rowIndex+1), row);
               }
               else     //actualizar
               {
                      rowIndex = rowIndex*(-1);
                      double tempCant = Double.parseDouble(dftm.getValueAt(rowIndex,3).toString());
                      double tempDistrec = Double.parseDouble(dftm.getValueAt(rowIndex,4).toString());
                      double tempSupafect = Double.parseDouble(dftm.getValueAt(rowIndex,5).toString());
                      double tempPDir = Double.parseDouble(dftm.getValueAt(rowIndex,6).toString());
                      double tempPInd = Double.parseDouble(dftm.getValueAt(rowIndex,7).toString());
                      double tempHPerd = Double.parseDouble(dftm.getValueAt(rowIndex,8).toString());

                      dftm.setValueAt((tempCant+cant), rowIndex, 3);
                      dftm.setValueAt((tempDistrec+distrec), rowIndex, 4);
                      dftm.setValueAt((tempSupafect+supafect), rowIndex, 5);
                      dftm.setValueAt((tempPDir+pdir), rowIndex, 6);
                      dftm.setValueAt((tempPInd+pind), rowIndex, 7);
                      dftm.setValueAt((tempHPerd+hperd), rowIndex, 8);
               }
           }
       }

       return new Totals(totCant, totDistrec, totSupafect, totPDir, totPInd, totHPerd);
    }

   /**Agrega los subtotales de los municipios a un DefaultTableModel*/
    private void AddMun_Subtotals(DefaultTableModel TM)
    {
     ArrayList<Row_Index> RI = new ArrayList<Row_Index>();
     HashMap<String, String> mun_id = new HashMap<String, String>();
     String CurrentMun = "";
     String MUN = "";
     String PROV = "";
     Double PDir = new Double(0);
     Double PInd = new Double(0);
     Double HPerd = new Double(0);
     Double totPDir = new Double(0);
     Double totPInd = new Double(0);
     Double totHPerd = new Double(0);

     int rowCount = TM.getRowCount();
     if(TM!=null && rowCount>0)
     {
          mun_id = get_All_Mun_Id();
          CurrentMun = mun_id.get(TM.getValueAt(0,1).toString());

          for(int i=0; i<rowCount; i++)
          {
            String munValue = TM.getValueAt(i,1).toString();
            MUN = mun_id.get(munValue);
            PROV = TM.getValueAt(i,0).toString();
            PDir = Double.parseDouble(TM.getValueAt(i,3).toString());
            PInd = Double.parseDouble(TM.getValueAt(i,4).toString());
            HPerd = Double.parseDouble(TM.getValueAt(i,5).toString());

            if(!PROV.equals("TOTAL"))
            {
                if(!CurrentMun.equals(MUN)) //Si cambio el municipio
                {
                  CurrentMun = MUN;
                  String []row = new String []{"", "Sub-Total", "", totPDir.toString(), totPInd.toString(), totHPerd.toString(), ""};
                  RI.add(new Row_Index(row, i));
                  totPDir = PDir;
                  totPInd = PInd;
                  totHPerd = HPerd;
                }
                else
                {
                  totPDir += PDir;
                  totPInd += PInd;
                  totHPerd += HPerd;
                }
            }
            else
            {
               String []row = new String []{"", "Sub-Total", "", totPDir.toString(), totPInd.toString(), totHPerd.toString(), ""};
               RI.add(new Row_Index(row, i));
            }
          } //Fin for
     }

       insert_Mun_SubTotals(TM, RI);
    }

    @Override
    protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
    {
           String cant = "";
           String distrec = "";
           String supafect = "";
           String pdir = "";
           String pind = "";
           String hperd = "";
           
           String especie = "";
           String entidad = "";
           Object []columNames = null;
           DefaultTableModel deftm = null;
           String sql = "";

           Double totCant = new Double(0);
           Double totDistrec = new Double(0);
           Double totSupafect = new Double(0);
           Double totPDir = new Double(0);
           Double totPInd = new Double(0);
           Double totHPerd = new Double(0);

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.CANTIDAD2_COLUMN_NAME, CONSTANTS.DISTANCIA_RECORR2_COLUMN_NAME, CONSTANTS.SUP_AFECT_TOTAL2_COLUMN_NAME,
                                  CONSTANTS.PERDIDA_DIRECTA2_COLUMN_NAME, CONSTANTS.PERDIDA_INDIRECTA2_COLUMN_NAME, CONSTANTS.HECTAREAS_PERD2_COLUMN_NAME,
                                  CONSTANTS.ESPECIE_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_2_1_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.CANTIDAD2_COLUMN_NAME, CONSTANTS.DISTANCIA_RECORR2_COLUMN_NAME, CONSTANTS.SUP_AFECT_TOTAL2_COLUMN_NAME,
                                  CONSTANTS.PERDIDA_DIRECTA2_COLUMN_NAME, CONSTANTS.PERDIDA_INDIRECTA2_COLUMN_NAME, CONSTANTS.HECTAREAS_PERD2_COLUMN_NAME,
                                  CONSTANTS.ESPECIE_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_2_1_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.CANTIDAD2_COLUMN_NAME, CONSTANTS.DISTANCIA_RECORR2_COLUMN_NAME, CONSTANTS.SUP_AFECT_TOTAL2_COLUMN_NAME,
                                  CONSTANTS.PERDIDA_DIRECTA2_COLUMN_NAME, CONSTANTS.PERDIDA_INDIRECTA2_COLUMN_NAME, CONSTANTS.HECTAREAS_PERD2_COLUMN_NAME,
                                  CONSTANTS.ESPECIE_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_2_1_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
       }

        deftm = new DefaultTableModel(columNames, 0);
        db.executeQuery(sql);
        System.out.println(sql);
        if(!db.isEmpty())
        {
             for(int i=0; i<db.getRowCount(); i++)
             {
              Object obj = db.getValueAt(i,0);
              if(obj == null)
               entidad = "";
              else
               entidad = obj.toString();

               cant = db.getValueAt(i,1).toString();
               distrec = db.getValueAt(i,2).toString();
               supafect = db.getValueAt(i,3).toString();
               pdir = db.getValueAt(i,4).toString();
               pind = db.getValueAt(i,5).toString();
               hperd = db.getValueAt(i,6).toString();
               especie = db.getValueAt(i,7).toString();

               deftm.addRow(new String []{entidad, cant, distrec, supafect, pdir, pind, hperd, especie});

               totCant += Double.parseDouble(cant);
               totDistrec += Double.parseDouble(distrec);
               totSupafect += Double.parseDouble(supafect);
               totPDir += Double.parseDouble(pdir);
               totPInd += Double.parseDouble(pind);
               totHPerd += Double.parseDouble(hperd);
             }

             deftm.addRow(new String []{"TOTAL", totCant.toString(), totDistrec.toString(), totSupafect.toString(),
                                        totPDir.toString(), totPInd.toString(), totHPerd.toString(), ""});
        }

       return deftm;
    }
       
   //==========================================================================
    private class Totals
    {
      Double totCant = new Double(0);
      Double totDistrec = new Double(0);
      Double totSupafect = new Double(0);
      Double totPDir = new Double(0);
      Double totPInd = new Double(0);
      Double totHPerd = new Double(0);

     public Totals(double _totCant, double _totDistrec, double _totSupafect, double _totPDir, double _totPInd, double _totHPerd)
     {
      totCant = _totCant;
      totDistrec = _totDistrec;
      totSupafect = _totSupafect;
      totPDir = _totPDir;
      totPInd = _totPInd;
      totHPerd = _totHPerd;
     }
    }
}
