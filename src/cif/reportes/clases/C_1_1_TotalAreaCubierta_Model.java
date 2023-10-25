/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.clases;

import cif.gui.LoginDialog;
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
public class C_1_1_TotalAreaCubierta_Model extends AbstractCritReport
{
    public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
    {      
      return getReportModel(user, anno, desglose, bu);
    }

    @Override
    protected TableModel getModel_US()
    {
       String sql = getSQLReplacement(ReportQuerys.SQL_1_1_US, user.getIdEntidad(), anno.toString());
       db.executeQueryUperCase(sql);

       return db;
    }

    @Override
    protected TableModel getModel_AP()
    {
      String sql = getSQLReplacement(ReportQuerys.SQL_1_1_AP, user.getIdEntidad(), anno.toString());
      db.executeQueryUperCase(sql);

      return db;
    }

    @Override
    protected TableModel getModel_EFI()
    {
       DefaultTableModel tm = new DefaultTableModel();

       String sql = getSQLReplacement(ReportQuerys.SQL_1_1_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
       System.out.println(sql);
       db.executeQueryUperCase(sql);
       if(!db.isEmpty())
       {
        tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

        sql = getSQLReplacement(ReportQuerys.SQL_1_1_EFI_TOTAL, user.getIdEntidad(), anno.toString());
        db.executeQuery(sql);

        Object []row = new String []{"TOTAL","","","", db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString(), db.getValueAt(0,3).toString()};
        tm.addRow(row);
       }

       return tm;
    }

    @Override
    protected TableModel getModel_MUN()
    {
       String prov = "";
       String mun = "";
       String bn = "";
       String pest = "";
       String tipoEntidad = "";
       String rowTotal = "";
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.BOSQUES_NATURALES2_COLUMN_NAME, CONSTANTS.PLANTACIONES2_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);
       Double totBn = new Double(0);
       Double totPlt = new Double(0);
       Double TOTAL = new Double(0);

       String sql = getSQLReplacement(ReportQuerys.SQL_1_1_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           prov = db.getValueAt(0,0).toString();
           mun = db.getValueAt(0,1).toString();
           bn = db.getValueAt(0,2).toString();
           pest = db.getValueAt(0,3).toString();
           rowTotal = db.getValueAt(0,4).toString();
           tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
           row = new String []{prov, mun, tipoEntidad, bn, pest, rowTotal};
           deftm.addRow(row);
           totBn += Double.parseDouble(bn);
           totPlt += Double.parseDouble(pest);
           TOTAL += Double.parseDouble(rowTotal);
       }

       sql = getSQLReplacement(ReportQuerys.SQL_1_1_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           prov = db.getValueAt(0,0).toString();
           mun = db.getValueAt(0,1).toString();
           bn = db.getValueAt(0,2).toString();
           pest = db.getValueAt(0,3).toString();
           rowTotal = db.getValueAt(0,4).toString();
           tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
           row = new String []{prov, mun, tipoEntidad, bn, pest, rowTotal};
           deftm.addRow(row);
           totBn += Double.parseDouble(bn);
           totPlt += Double.parseDouble(pest);
           TOTAL += Double.parseDouble(rowTotal);
       }

       sql = getSQLReplacement(ReportQuerys.SQL_1_1_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           prov = db.getValueAt(0,0).toString();
           mun = db.getValueAt(0,1).toString();
           bn = db.getValueAt(0,2).toString();
           pest = db.getValueAt(0,3).toString();
           rowTotal = db.getValueAt(0,4).toString();
           tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
           row = new String []{prov, mun, tipoEntidad, bn, pest, rowTotal};
           deftm.addRow(row);
           totBn += Double.parseDouble(bn);
           totPlt += Double.parseDouble(pest);
           TOTAL += Double.parseDouble(rowTotal);
       }

       row = new String []{"TOTAL", "", "TOTAL", totBn.toString(), totPlt.toString(), TOTAL.toString()};
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_PROV()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                          CONSTANTS.BOSQUES_NATURALES2_COLUMN_NAME, CONSTANTS.PLANTACIONES2_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       Totals rowTotals = new Totals(0, 0, 0);

       rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_1_1_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), deftm, 0, 0, 0);

       rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_1_1_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totBn, rowTotals.totPlt, rowTotals.TOTAL);

       rowTotals = updateProvincia_MunRow(ReportQuerys.SQL_1_1_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), deftm, rowTotals.totBn, rowTotals.totPlt, rowTotals.TOTAL);

       row = new String []{"", "TOTAL", rowTotals.totBn.toString(), rowTotals.totPlt.toString(), rowTotals.TOTAL.toString()};
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_PROV_DESGLOSE()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.BOSQUES_NATURALES2_COLUMN_NAME, CONSTANTS.PLANTACIONES2_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);


       Totals rowTotals = new Totals(0, 0, 0);

       rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_1_1_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString(), 
                                               deftm, 0, 0, 0, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

       rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_1_1_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString(), 
                                               deftm, rowTotals.totBn, rowTotals.totPlt, rowTotals.TOTAL, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

       rowTotals = updateProvincia_DesgloseRow(ReportQuerys.SQL_1_1_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString(), 
                                               deftm, rowTotals.totBn, rowTotals.totPlt, rowTotals.TOTAL, CONSTANTS.ENTIDAD_OTROS);

       row = new String []{"", "TOTAL", "", rowTotals.totBn.toString(), rowTotals.totPlt.toString(), rowTotals.TOTAL.toString()};
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.BOSQUES_NATURALES2_COLUMN_NAME,
                                          CONSTANTS.PLANTACIONES2_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME};
       DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

       Totals rowTotals = new Totals(0, 0, 0);

       rowTotals = updateProvinciaRow(ReportQuerys.SQL_1_1_PROV_SubGrupoUS, anno.toString(), deftm, 0, 0, 0);

       rowTotals = updateProvinciaRow(ReportQuerys.SQL_1_1_PROV_SubGrupoAP, anno.toString(), deftm, rowTotals.totBn, rowTotals.totPlt, rowTotals.TOTAL);

       rowTotals = updateProvinciaRow(ReportQuerys.SQL_1_1_PROV_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totBn, rowTotals.totPlt, rowTotals.TOTAL);

       row = new String []{"TOTAL", rowTotals.totBn.toString(), rowTotals.totPlt.toString(), rowTotals.TOTAL.toString()};
       deftm.addRow(row);

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC_DESGLOSE()
    {
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.BOSQUES_NATURALES2_COLUMN_NAME, CONSTANTS.PLANTACIONES2_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);


       Totals rowTotals = new Totals(0, 0, 0);

       rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_1_1_MUN_SubGrupoUS, anno.toString(), deftm, 0, 0, 0, CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS);

       rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_1_1_MUN_SubGrupoAP, anno.toString(), deftm, rowTotals.totBn, rowTotals.totPlt, rowTotals.TOTAL, CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS);

       rowTotals = updateDNF_DesgloseRow(ReportQuerys.SQL_1_1_MUN_SubGrupoOTROS, anno.toString(), deftm, rowTotals.totBn, rowTotals.totPlt, rowTotals.TOTAL, CONSTANTS.ENTIDAD_OTROS);

       row = new String []{"TOTAL", "", "", rowTotals.totBn.toString(), rowTotals.totPlt.toString(), rowTotals.TOTAL.toString()};
       deftm.addRow(row);

       AddMun_Subtotals(deftm);

       return deftm;
    }

    private Totals updateProvincia_MunRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm, double bnCount, double pltCount, double TotCount)
    {
       String prov = "";
       String mun = "";
       Double bn = new Double(0);
       Double pest = new Double(0);
       Double rowTotal = new Double(0);
       Object []row = null;

       Double totBn = bnCount;
       Double totPlt = pltCount;
       Double TOTAL = TotCount;

       String sql = SqlPattern.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, Entidad, anno);
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           for(int i=0; i<db.getRowCount(); i++)
           {
               boolean rowUpdated = false;
               
               prov = db.getValueAt(i,0).toString();
               mun = db.getValueAt(i,1).toString();
               bn = Double.parseDouble(db.getValueAt(i,2).toString());
               pest = Double.parseDouble(db.getValueAt(i,3).toString());
               rowTotal = Double.parseDouble(db.getValueAt(i,4).toString());
               totBn += bn;
               totPlt += pest;
               TOTAL += rowTotal;

               if(dftm.getRowCount() > 0)
               {
                    String tmMun = "";
                    for(int k=0; k<dftm.getRowCount(); k++)
                    {
                     tmMun = dftm.getValueAt(k,1).toString();
                     if(tmMun.equals(mun))
                     {
                      double tempBn = Double.parseDouble(dftm.getValueAt(k,2).toString());
                      double tempPlt = Double.parseDouble(dftm.getValueAt(k,3).toString());
                      double tempRowTotal = Double.parseDouble(dftm.getValueAt(k,4).toString());
                      dftm.setValueAt((tempBn+bn), k, 2);
                      dftm.setValueAt((tempPlt+pest), k, 3);
                      dftm.setValueAt((tempRowTotal+rowTotal), k, 4);
                      rowUpdated = true;
                      break;
                     }
                    }
               }

               if(!rowUpdated)
               {
                row = new String []{prov, mun, bn.toString(), pest.toString(), rowTotal.toString()};
                dftm.addRow(row);
               }
           }

//           row = new String []{"TOTAL", "", totBn.toString(), totPlt.toString()};
//           dftm.addRow(row);
       }

       return new Totals(totBn, totPlt, TOTAL);
   }

    private Totals updateProvincia_DesgloseRow(String SqlPattern, String Entidad, String anno, DefaultTableModel dftm, double bnCount, double pltCount, double TotCount, String tipoEntidad)
    {
       String prov = "";
       String mun = "";
       Double bn = new Double(0);
       Double pest = new Double(0);
       Double rowTotal = new Double(0);
       Object []row = null;

       Double totBn = bnCount;
       Double totPlt = pltCount;
       Double TOTAL = TotCount;

       String sql = SqlPattern.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, Entidad, anno);
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           for(int i=0; i<db.getRowCount(); i++)
           {
               boolean rowUpdated = false;
               int rowIndex = dftm.getRowCount();

               prov = db.getValueAt(i,0).toString();
               mun = db.getValueAt(i,1).toString();
               bn = Double.parseDouble(db.getValueAt(i,2).toString());
               pest = Double.parseDouble(db.getValueAt(i,3).toString());
               rowTotal = Double.parseDouble(db.getValueAt(i,4).toString());
               totBn += bn;
               totPlt += pest;
               TOTAL += rowTotal;

               if(dftm.getRowCount() > 0)
               {
                    String tmMun = "";
                    String tmEntidad = "";
                    for(int k=0; k<dftm.getRowCount(); k++)
                    {
                         tmMun = dftm.getValueAt(k,1).toString();
                         tmEntidad = dftm.getValueAt(k,2).toString();
                         if((tmMun.equals(mun)) && (tmEntidad.equals(tipoEntidad)))
                         {
                          double tempBn = Double.parseDouble(dftm.getValueAt(k,3).toString());
                          double tempPlt = Double.parseDouble(dftm.getValueAt(k,4).toString());
                          double tempRowTotal = Double.parseDouble(dftm.getValueAt(k,5).toString());
                          dftm.setValueAt((tempBn+bn), k, 3);
                          dftm.setValueAt((tempPlt+pest), k, 4);
                          dftm.setValueAt((tempRowTotal+rowTotal), k, 5);
                          rowUpdated = true;
                          break;
                         }
                         else
                         if(tmMun.equals(mun))
                         {
                          rowIndex = k+1;
                          break;
                         }
                    }
               }

               if(!rowUpdated)
               {
                row = new String []{prov, mun, tipoEntidad, bn.toString(), pest.toString(), rowTotal.toString()};
                dftm.insertRow(rowIndex, row);
               }
           }
       }

       return new Totals(totBn, totPlt, TOTAL);
    }

    private Totals updateProvinciaRow(String SqlPattern, String anno, DefaultTableModel dftm, double bnCount, double pltCount, double TotCount)
    {
       String prov = "";
       Double bn = new Double(0);
       Double pest = new Double(0);
       Double rowTot = new Double(0);
       Object []row = null;

       Double totBn = bnCount;
       Double totPlt = pltCount;
       Double TOTAL = TotCount;

       String sql = getSQLReplacement(SqlPattern, anno);
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           for(int i=0; i<db.getRowCount(); i++)
           {
               boolean rowUpdated = false;

               prov = db.getValueAt(i,0).toString();
               bn = Double.parseDouble(db.getValueAt(i,1).toString());
               pest = Double.parseDouble(db.getValueAt(i,2).toString());
               rowTot = Double.parseDouble(db.getValueAt(i,3).toString());
               totBn += bn;
               totPlt += pest;
               TOTAL += rowTot;

               if(dftm.getRowCount() > 0)
               {
                    String tmProv = "";
                    for(int k=0; k<dftm.getRowCount(); k++)
                    {
                     tmProv = dftm.getValueAt(k,0).toString();
                     if(tmProv.equals(prov))
                     {
                      double tempBn = Double.parseDouble(dftm.getValueAt(k,1).toString());
                      double tempPlt = Double.parseDouble(dftm.getValueAt(k,2).toString());
                      double tempRowTot = Double.parseDouble(dftm.getValueAt(k,3).toString());
                      dftm.setValueAt((tempBn+bn), k, 1);
                      dftm.setValueAt((tempPlt+pest), k, 2);
                      dftm.setValueAt((tempRowTot+rowTot), k, 3);
                      rowUpdated = true;
                      break;
                     }
                    }
               }

               if(!rowUpdated)
               {
                row = new String []{prov, bn.toString(), pest.toString(), rowTot.toString()};
                dftm.addRow(row);
               }
           }
       }

       return new Totals(totBn, totPlt, TOTAL);
   }

    private Totals updateDNF_DesgloseRow(String SqlPattern, String anno, DefaultTableModel dftm, double bnCount, double pltCount, double TotCount, String tipoEntidad)
    {
       String prov = "";
       String mun = "";
       Double bn = new Double(0);
       Double pest = new Double(0);
       Double rowTot = new Double(0);
       Object []row = null;

       Double totBn = bnCount;
       Double totPlt = pltCount;
       Double TOTAL = TotCount;

       String sql = SqlPattern.replaceFirst("municipios.id=':id' and", "");
       sql = getSQLReplacement(sql, anno);
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           for(int i=0; i<db.getRowCount(); i++)
           {
//               boolean rowUpdated = false;
//               int rowIndex = dftm.getRowCount();

               prov = db.getValueAt(i,0).toString();
               mun = db.getValueAt(i,1).toString();
               bn = Double.parseDouble(db.getValueAt(i,2).toString());
               pest = Double.parseDouble(db.getValueAt(i,3).toString());
               rowTot = Double.parseDouble(db.getValueAt(i,4).toString());
               totBn += bn;
               totPlt += pest;
               TOTAL += rowTot;

               int rowIndex = getProvincia_Mun_Ent_RowIndex(prov, mun, tipoEntidad, dftm);

               if((rowIndex==0) && (dftm.getRowCount()<=0)) //agregar
               {
                row = new String []{prov, mun, tipoEntidad, bn.toString(), pest.toString(), rowTot.toString()};
                dftm.addRow(row);
               }
               else
               if(rowIndex >= 0) //insertar
               {
                row = new String []{prov, mun, tipoEntidad, bn.toString(), pest.toString(), rowTot.toString()};
                dftm.insertRow(rowIndex+1, row);
               }
               else     //actualizar
               {
                 rowIndex = rowIndex*(-1);
                 double tempBn = Double.parseDouble(dftm.getValueAt(rowIndex, 3).toString());
                 double tempPlt = Double.parseDouble(dftm.getValueAt(rowIndex, 4).toString());
                 double tempRowTot = Double.parseDouble(dftm.getValueAt(rowIndex, 5).toString());
                 dftm.setValueAt((tempBn+bn), rowIndex, 3);
                 dftm.setValueAt((tempPlt+pest), rowIndex, 4);
                 dftm.setValueAt((tempRowTot+rowTot), rowIndex, 5);
               }
           }
       }

       return new Totals(totBn, totPlt, TOTAL);
    }

    /**Agrega los subtotales de los municipios a un DefaultTableModel*/
    private void AddMun_Subtotals(DefaultTableModel TM)
    { 
//     String CurrentProv = "";
//     String Prov = "";
     ArrayList<Row_Index> RI = new ArrayList<Row_Index>();
     HashMap<String, String> mun_id = new HashMap<String, String>();
     String CurrentMun = "";
     String MUN = "";
     String PROV = "";
     Double Bn = new Double(0);
     Double Plt = new Double(0);
     Double rowTot = new Double(0);
     Double totBn = new Double(0);
     Double totPlt = new Double(0);
     Double TOTAL = new Double(0);
     
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
            Bn = Double.parseDouble(TM.getValueAt(i,3).toString());
            Plt = Double.parseDouble(TM.getValueAt(i,4).toString());
            rowTot = Double.parseDouble(TM.getValueAt(i,5).toString());

            if(!PROV.equals("TOTAL"))
            {
                if(!CurrentMun.equals(MUN)) //Si cambio el municipio
                {
                  CurrentMun = MUN;
                  String []row = new String []{"", "Sub-Total", "", totBn.toString(), totPlt.toString(), TOTAL.toString()};
                  RI.add(new Row_Index(row, i));
                  totBn = Bn;
                  totPlt = Plt;
                  TOTAL = rowTot;
                }
                else
                {
                  totBn += Bn;
                  totPlt += Plt;
                  TOTAL += rowTot;
                }
            }
            else
            {
               String []row = new String []{"", "Sub-Total", "", totBn.toString(), totPlt.toString(), TOTAL.toString()};
               RI.add(new Row_Index(row, i));
            }
          } //Fin for
     }

       insert_Mun_SubTotals(TM, RI);
    }

    @Override
    protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
    {
       String bn = "";
       String pest = "";
       String rowTotal = "";
       String entidad = null;
       Object []columNames = null;   //new String[]{CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME, CONSTANTS.PLANTACIONES_COLUMN_NAME};
       DefaultTableModel deftm = null;   //new DefaultTableModel(columNames, 0);
       String sql = "";
       Double totBn = new Double(0);
       Double totPlt = new Double(0);
       Double TOTAL = new Double(0);

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.BOSQUES_NATURALES2_COLUMN_NAME, CONSTANTS.PLANTACIONES2_COLUMN_NAME,
                                  CONSTANTS.TOTAL2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_1_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.BOSQUES_NATURALES2_COLUMN_NAME, CONSTANTS.PLANTACIONES2_COLUMN_NAME,
                                  CONSTANTS.TOTAL2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_1_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.BOSQUES_NATURALES2_COLUMN_NAME, CONSTANTS.PLANTACIONES2_COLUMN_NAME,
                                  CONSTANTS.TOTAL2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_1_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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
              
              bn = db.getValueAt(i,1).toString();
              pest = db.getValueAt(i,2).toString();
              rowTotal = db.getValueAt(i,3).toString();

              deftm.addRow(new String []{entidad, bn, pest, rowTotal});
              totBn += Double.parseDouble(bn);
              totPlt += Double.parseDouble(pest);
              TOTAL += Double.parseDouble(rowTotal);
             }

             deftm.addRow(new String []{"TOTAL", totBn.toString(), totPlt.toString(), TOTAL.toString()});
        }

       return deftm;
    }

    private class Totals
    {
     Double totBn = new Double(0);
     Double totPlt = new Double(0);
     Double TOTAL = new Double(0);

     public Totals(double bn, double plt, double TOT)
     {
      totBn = bn;
      totPlt = plt;
      TOTAL = TOT;
     }
    }
}