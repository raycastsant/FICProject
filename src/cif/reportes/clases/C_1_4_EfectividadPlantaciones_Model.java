package cif.reportes.clases;

import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Efect_Plant;
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
public class C_1_4_EfectividadPlantaciones_Model extends AbstractCritReport implements IReportModel
{
    public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
    {
      return getReportModel(user, anno, desglose, bu);
    }

    @Override
    protected TableModel getModel_AP()
    {
        Object []columNames = new String[]{CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.ANNO_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME,
                                              CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME,
                                              CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
        DefaultTableModel deftm= new DefaultTableModel(columNames, 0);
        String sql = getSQLReplacement(ReportQuerys.SQL_1_4_AP, user.getIdEntidad(), anno.toString());

           db.executeQueryUperCase(sql);
           if(!db.isEmpty())
           {
               double slog = 0;
               double splant = 0;
               double plantviv = 0;
               double plantm = 0;
               double perddin = 0;
               double perdhect = 0;

               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperddin = 0;
               double Totperdhect = 0;
               String antMun = db.getValueAt(0,0).toString();
               String currentMun = "";
               String esp = "";
               double espPrecio = 0;

            int count = db.getRowCount();
            for(int i=0; i<count; i++)
            {
             if(i>0)
              antMun = db.getValueAt(i-1,0).toString();

             currentMun = db.getValueAt(i,0).toString();
             slog = Double.parseDouble(db.getValueAt(i,2).toString());
             splant = Double.parseDouble(db.getValueAt(i,3).toString());
             plantviv = Double.parseDouble(db.getValueAt(i,4).toString());
             plantm = Double.parseDouble(db.getValueAt(i,5).toString());
             perdhect = Double.parseDouble(db.getValueAt(i,6).toString());
             espPrecio = Double.parseDouble(db.getValueAt(i,7).toString());
             perddin = perdhect*espPrecio;
             esp = db.getValueAt(i,8).toString();

             Totslog += slog;
             Totsplant += splant;
             Totplantviv += plantviv;
             Totplantm += plantm;
             Totperddin += perddin;
             Totperdhect += perdhect;
             
             if(currentMun.equals(antMun))
             {
              deftm.addRow(new String []{currentMun, anno.toString(), esp, Redondear((slog/splant)*100).toString(), Redondear((plantviv/(plantviv+plantm))*100).toString(),
                                         Redondear(perddin).toString(), Redondear(perdhect).toString()});     
             }
             else
             {               
              deftm.addRow(new String []{esp, anno.toString(), currentMun, Redondear((slog/splant)*100).toString(), Redondear((plantviv/(plantviv+plantm))*100).toString(),
                                         Redondear(perddin).toString(), Redondear(perdhect).toString()});
             }
            }
             deftm.addRow(new String []{"TOTAL", "", "", Redondear((Totslog/Totsplant)*100).toString(), Redondear((Totplantviv/(Totplantviv+Totplantm))*100).toString(),
                                        Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});
           }

        return deftm;
    }

    @Override
    protected TableModel getModel_US()
    {
        Object []columNames = new String[]{CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.ANNO_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME,
                                              CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME,
                                              CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
        DefaultTableModel deftm= new DefaultTableModel(columNames, 0);
        String sql = getSQLReplacement(ReportQuerys.SQL_1_4_US, user.getIdEntidad(), anno.toString());

           db.executeQueryUperCase(sql);
           if(!db.isEmpty())
           {
               double slog = 0;
               double splant = 0;
               double plantviv = 0;
               double plantm = 0;
               double perddin = 0;
               double perdhect = 0;

               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperddin = 0;
               double Totperdhect = 0;
               String antMun = db.getValueAt(0,0).toString();
               String currentMun = "";
               String esp = "";
               double espPrecio = 0;

            int count = db.getRowCount();
            int cantMun = 0;
            for(int i=0; i<count; i++)
            {
             if(i>0)
              antMun = db.getValueAt(i-1,0).toString();

             currentMun = db.getValueAt(i,0).toString();
             slog = Double.parseDouble(db.getValueAt(i,2).toString());
             splant = Double.parseDouble(db.getValueAt(i,3).toString());
             plantviv = Double.parseDouble(db.getValueAt(i,4).toString());
             plantm = Double.parseDouble(db.getValueAt(i,5).toString());
             perdhect = Double.parseDouble(db.getValueAt(i,6).toString());
             espPrecio = Double.parseDouble(db.getValueAt(i,7).toString());
             perddin = perdhect*espPrecio;
             esp = db.getValueAt(i,8).toString();

             Totslog += slog;
             Totsplant += splant;
             Totplantviv += plantviv;
             Totplantm += plantm;
             Totperddin += perddin;
             Totperdhect += perdhect;

             if(currentMun.equals(antMun))
             {
              cantMun++;
              deftm.addRow(new String []{currentMun, anno.toString(), esp, Redondear((slog/splant)*100).toString(), Redondear((plantviv/(plantviv+plantm))*100).toString(),
                                         Redondear(perddin).toString(), Redondear(perdhect).toString()});
             }
             else
             {
              deftm.addRow(new String []{currentMun, anno.toString(), esp, Redondear((slog/splant)*100).toString(), Redondear((plantviv/(plantviv+plantm))*100).toString(),
                                         Redondear(perddin).toString(), Redondear(perdhect).toString()});
             }
            }
             deftm.addRow(new String []{"TOTAL", "", "", Redondear((Totslog/Totsplant)*100).toString(), Redondear((Totplantviv/(Totplantviv+Totplantm))*100).toString(),
                                        Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});
           }

        return deftm;
    }

    @Override
    protected TableModel getModel_EFI()
    {
       String mun = "";
       String us = "";
       
      //Municipio, [US, [especie, Obj_Efect_Plant] ]
       HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>> municipios = new HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>>();
       
       Object []columNames = new String[]{CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.ANNO_COLUMN_NAME, CONSTANTS.EFI_COLUMN_NAME,
                                          CONSTANTS.US2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME,
                                                  CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME,
                                                  CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               db.executeQuery(getSQLReplacement(ReportQuerys.SQL_1_4_EFI, user.getIdEntidad(), anno.toString()));
               if(!db.isEmpty())
                updateEFI_HASH(municipios);

              //Agregando filas
               Object [] listaMun = municipios.keySet().toArray();
               double Subtslog = 0;
               double Subtsplant = 0;
               double Subtplantviv = 0;
               double Subtplantm = 0;
               double Subtperdhect = 0;
               double Subtperddin = 0;

               double SubtMunslog = 0;
               double SubtMunsplant = 0;
               double SubtMunplantviv = 0;
               double SubtMunplantm = 0;
               double SubtMunperdhect = 0;
               double SubtMunperddin = 0;
               
               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperdhect = 0;
               double Totperddin = 0;

               for(int i=0; i<listaMun.length; i++)
               {
                mun = listaMun[i].toString();
                HashMap<String, HashMap<String, Obj_Efect_Plant>> unidadesSilv = municipios.get(mun);
                Object [] listaUs = unidadesSilv.keySet().toArray();

                    for(int j=0; j<listaUs.length; j++)   //US
                    {
                     us = listaUs[j].toString();
                     HashMap<String, Obj_Efect_Plant> especies = unidadesSilv.get(us);
                     Object [] listaEspecies = especies.keySet().toArray();

                        for(int k=0; k<listaEspecies.length; k++)  //Especies
                        {
                            String esp = listaEspecies[k].toString();
                            double slog = especies.get(esp).getSlog();
                            double splant = especies.get(esp).getSplant();
                            double plantviv = especies.get(esp).getPlantviv();
                            double plantm = especies.get(esp).getPlantm();
                            double perdhect = especies.get(esp).getPerdhect();
                            double perddin = especies.get(esp).getPerddin();

                            Double log3erCont = new Double(0);
                            Double sup3erCont = new Double(0);

                            if(splant != 0)
                             log3erCont = Redondear((slog/splant)*100);

                            if((plantviv!=0) || (plantm!=0))
                             sup3erCont = Redondear((plantviv/(plantviv+plantm))*100);

                            deftm.addRow(new String []{mun, anno.toString(), "", us, esp, log3erCont.toString(),
                                                       sup3erCont.toString(), Redondear(perddin).toString(), Redondear(perdhect).toString()});

                            Subtslog += slog;
                            Subtsplant += splant;
                            Subtplantviv += plantviv;
                            Subtplantm += plantm;
                            Subtperdhect += perdhect;
                            Subtperddin += perddin;
                        }
                     
                       //Subtotal de US
                         Double log3erCont = new Double(0);
                         Double sup3erCont = new Double(0);

                         if(Subtsplant != 0)
                          log3erCont = Redondear((Subtslog/Subtsplant)*100);

                         if((Subtplantviv!=0) || (Subtplantm!=0))
                          sup3erCont = Redondear((Subtplantviv/(Subtplantviv+Subtplantm))*100);

                         deftm.addRow(new String []{"", "", "", CONSTANTS.VALUE_SUB_TOTAL_US, "", log3erCont.toString(), sup3erCont.toString(),
                                                    Redondear(Subtperddin).toString(), Redondear(Subtperdhect).toString()});

                         SubtMunslog += Subtslog;
                         SubtMunsplant += Subtsplant;
                         SubtMunplantviv += Subtplantviv;
                         SubtMunplantm += Subtplantm;
                         SubtMunperdhect += Subtperdhect;
                         SubtMunperddin += Subtperddin;

                         Subtslog = 0;
                         Subtsplant = 0;
                         Subtplantviv = 0;
                         Subtplantm = 0;
                         Subtperdhect = 0;
                         Subtperddin = 0;
                    }
                
                 //Subtotal de Municipio
                   Double log3erCont = new Double(0);
                   Double sup3erCont = new Double(0);

                   if(SubtMunsplant != 0)
                    log3erCont = Redondear((SubtMunslog/SubtMunsplant)*100);

                   if((SubtMunplantviv!=0) || (SubtMunplantm!=0))
                    sup3erCont = Redondear((SubtMunplantviv/(SubtMunplantviv+SubtMunplantm))*100);
                   
                   deftm.addRow(new String []{CONSTANTS.VALUE_SUB_TOTAL_MUN, "", "", "", "", log3erCont.toString(), sup3erCont.toString(),
                                                    Redondear(SubtMunperddin).toString(), Redondear(SubtMunperdhect).toString()});

                   Totslog += SubtMunslog;
                   Totsplant += SubtMunsplant;
                   Totplantviv += SubtMunplantviv;
                   Totplantm += SubtMunplantm;
                   Totperdhect += SubtMunperdhect;
                   Totperddin += SubtMunperddin;

                   SubtMunslog = 0;
                   SubtMunsplant = 0;
                   SubtMunplantviv = 0;
                   SubtMunplantm = 0;
                   SubtMunperdhect = 0;
                   SubtMunperddin = 0;
               }

             //Total
               Double log3erCont = new Double(0);
               Double sup3erCont = new Double(0);

               if(Totsplant != 0)
                log3erCont = Redondear((Totslog/Totsplant)*100);

               if((Totplantviv!=0) || (Totplantm!=0))
                sup3erCont = Redondear((Totplantviv/(Totplantviv+Totplantm))*100);

               deftm.addRow(new String []{CONSTANTS.VALUE_TOTAL, "", "", "", "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});

       return deftm;
    }

    private void updateEFI_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>> municipios)
    {
                 String mun = "";
                 String us = "";
                 double slog = 0;
                 double splant = 0;
                 double plantviv = 0;
                 double plantm = 0;
                 double perddin = 0;
                 double perdhect = 0;
                 double espprecio = 0;
                 String esp = "";
                 
                 for(int i=0; i<db.getRowCount(); i++)
                 {
                     mun = db.getValueAt(i,0).toString();
//                     anno = db.getValueAt(i,1).toString();
//                     efi = db.getValueAt(i,2).toString();
                     us = db.getValueAt(i,3).toString();
                     slog = Double.parseDouble(db.getValueAt(i,4).toString());
                     splant = Double.parseDouble(db.getValueAt(i,5).toString());
                     plantviv = Double.parseDouble(db.getValueAt(i,6).toString());
                     plantm = Double.parseDouble(db.getValueAt(i,7).toString());
                     espprecio = Double.parseDouble(db.getValueAt(i,8).toString());
                     esp = db.getValueAt(i,9).toString();
                     
                     perdhect = splant-slog;
                     perddin = espprecio*perdhect;

                   if(municipios.containsKey(mun)) //Si ya esta el municipio
                   {
                        if(municipios.get(mun).containsKey(us))  //Si en ese municipio ya esta la us
                        {
                            if(municipios.get(mun).get(us).containsKey(esp))  //Si en esa US ya esta esta especie
                            {
                                slog = slog + municipios.get(mun).get(us).get(esp).getSlog();
                                splant = splant + municipios.get(mun).get(us).get(esp).getSplant();
                                plantviv = plantviv + municipios.get(mun).get(us).get(esp).getPlantviv();
                                plantm = plantm + municipios.get(mun).get(us).get(esp).getPlantm();
                                perdhect = perdhect + municipios.get(mun).get(us).get(esp).getPerdhect();
                                perddin = perddin + municipios.get(mun).get(us).get(esp).getPerddin();
                            }

                            municipios.get(mun).get(us).put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                        }
                        else
                        {
                            HashMap<String, Obj_Efect_Plant> especie = new HashMap<String, Obj_Efect_Plant>();
                            especie.put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                            municipios.get(mun).put(us, especie);
                        }
                   }
                   else
                   {
                    HashMap<String, Obj_Efect_Plant> especie = new HashMap<String, Obj_Efect_Plant>();
                    especie.put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                    HashMap<String, HashMap<String, Obj_Efect_Plant>> usilv = new HashMap<String, HashMap<String, Obj_Efect_Plant>>();
                    usilv.put(us, especie);
                    municipios.put(mun, usilv);
                   }
                  }
     }

    @Override
    protected TableModel getModel_MUN()
    {
       String mun = "";
       String tipoEntidad = "";

       //Municipio, [Tipo de entidad, [especie, Obj_Efect_Plant] ]
       HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>> municipios = new HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                          CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME,
                                          CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = getSQLReplacement(ReportQuerys.SQL_1_4_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateProv_Desglose_OR_Mun_HASH(municipios, tipoEntidad);

               sql = getSQLReplacement(ReportQuerys.SQL_1_4_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateProv_Desglose_OR_Mun_HASH(municipios, tipoEntidad);

               sql = getSQLReplacement(ReportQuerys.SQL_1_4_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateProv_Desglose_OR_Mun_HASH(municipios, tipoEntidad);

              //Agregando filas
               Object [] listaMun = municipios.keySet().toArray();
               double Subtslog = 0;
               double Subtsplant = 0;
               double Subtplantviv = 0;
               double Subtplantm = 0;
               double Subtperdhect = 0;
               double Subtperddin = 0;

//               double SubtMunslog = 0;
//               double SubtMunsplant = 0;
//               double SubtMunplantviv = 0;
//               double SubtMunplantm = 0;
//               double SubtMunperdhect = 0;
//               double SubtMunperddin = 0;

               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperdhect = 0;
               double Totperddin = 0;

               for(int i=0; i<listaMun.length; i++)
               {
                mun = listaMun[i].toString();
                HashMap<String, HashMap<String, Obj_Efect_Plant>> entidades = municipios.get(mun);
                Object [] listaEntidades = entidades.keySet().toArray();

                    for(int j=0; j<listaEntidades.length; j++)   //Entidad
                    {
                     tipoEntidad = listaEntidades[j].toString();
                     HashMap<String, Obj_Efect_Plant> especies = entidades.get(tipoEntidad);
                     Object [] listaEspecies = especies.keySet().toArray();

                        for(int k=0; k<listaEspecies.length; k++)  //Especies
                        {
                            String esp = listaEspecies[k].toString();
                            double slog = especies.get(esp).getSlog();
                            double splant = especies.get(esp).getSplant();
                            double plantviv = especies.get(esp).getPlantviv();
                            double plantm = especies.get(esp).getPlantm();
                            double perdhect = especies.get(esp).getPerdhect();
                            double perddin = especies.get(esp).getPerddin();

                            Double log3erCont = new Double(0);
                            Double sup3erCont = new Double(0);

                            if(splant != 0)
                             log3erCont = Redondear((slog/splant)*100);

                            if((plantviv!=0) || (plantm!=0))
                             sup3erCont = Redondear((plantviv/(plantviv+plantm))*100);

                            deftm.addRow(new String []{"", mun, tipoEntidad, esp, log3erCont.toString(),
                                                       sup3erCont.toString(), Redondear(perddin).toString(), Redondear(perdhect).toString()});

                            Subtslog += slog;
                            Subtsplant += splant;
                            Subtplantviv += plantviv;
                            Subtplantm += plantm;
                            Subtperdhect += perdhect;
                            Subtperddin += perddin;
                        }

                       //Subtotal de US
                         Double log3erCont = new Double(0);
                         Double sup3erCont = new Double(0);

                         if(Subtsplant != 0)
                          log3erCont = Redondear((Subtslog/Subtsplant)*100);

                         if((Subtplantviv!=0) || (Subtplantm!=0))
                          sup3erCont = Redondear((Subtplantviv/(Subtplantviv+Subtplantm))*100);

                         deftm.addRow(new String []{"", "", "", CONSTANTS.VALUE_SUB_TOTAL, log3erCont.toString(), sup3erCont.toString(),
                                                    Redondear(Subtperddin).toString(), Redondear(Subtperdhect).toString()});

                         Totslog += Subtslog;
                         Totsplant += Subtsplant;
                         Totplantviv += Subtplantviv;
                         Totplantm += Subtplantm;
                         Totperdhect += Subtperdhect;
                         Totperddin += Subtperddin;

                         Subtslog = 0;
                         Subtsplant = 0;
                         Subtplantviv = 0;
                         Subtplantm = 0;
                         Subtperdhect = 0;
                         Subtperddin = 0;
                    }

                 //Subtotal de Municipio
//                   Double log3erCont = new Double(0);
//                   Double sup3erCont = new Double(0);
//
//                   if(SubtMunsplant != 0)
//                    log3erCont = Redondear((SubtMunslog/SubtMunsplant)*100);
//
//                   if((SubtMunplantviv!=0) || (SubtMunplantm!=0))
//                    sup3erCont = Redondear((SubtMunplantviv/(SubtMunplantviv+SubtMunplantm))*100);
//
//                   deftm.addRow(new String []{"", "", CONSTANTS.VALUE_SUB_TOTAL_MUN, "", log3erCont.toString(), sup3erCont.toString(),
//                                                    Redondear(SubtMunperddin).toString(), Redondear(SubtMunperdhect).toString()});

//                   Totslog += SubtMunslog;
//                   Totsplant += SubtMunsplant;
//                   Totplantviv += SubtMunplantviv;
//                   Totplantm += SubtMunplantm;
//                   Totperdhect += SubtMunperdhect;
//                   Totperddin += SubtMunperddin;
//
//                   SubtMunslog = 0;
//                   SubtMunsplant = 0;
//                   SubtMunplantviv = 0;
//                   SubtMunplantm = 0;
//                   SubtMunperdhect = 0;
//                   SubtMunperddin = 0;
               }

             //Total
               Double log3erCont = new Double(0);
               Double sup3erCont = new Double(0);

               if(Totsplant != 0)
                log3erCont = Redondear((Totslog/Totsplant)*100);

               if((Totplantviv!=0) || (Totplantm!=0))
                sup3erCont = Redondear((Totplantviv/(Totplantviv+Totplantm))*100);

               deftm.addRow(new String []{CONSTANTS.VALUE_TOTAL, "", CONSTANTS.VALUE_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});

       return deftm;
    }

    @Override
    protected TableModel getModel_PROV()
    {     
       HashMap<String, HashMap<String, Obj_Efect_Plant>> municipios = new HashMap<String, HashMap<String, Obj_Efect_Plant>>();
       
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME,
                                          CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};

       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_4_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateValues_HASH(municipios);

               sql = ReportQuerys.SQL_1_4_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateValues_HASH(municipios);

               sql = ReportQuerys.SQL_1_4_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateValues_HASH(municipios);

              //Agregando filas
               Object [] listaMun = municipios.keySet().toArray();
               double Subtslog = 0;
               double Subtsplant = 0;
               double Subtplantviv = 0;
               double Subtplantm = 0;
               double Subtperdhect = 0;
               double Subtperddin = 0;

               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperdhect = 0;
               double Totperddin = 0;

               for(int i=0; i<listaMun.length; i++)
               {
                String mun = listaMun[i].toString();
                HashMap<String, Obj_Efect_Plant> especies = municipios.get(mun);
                Object [] listaEspecies = especies.keySet().toArray();

                    for(int k=0; k<listaEspecies.length; k++)  //Especies
                    {
                        String esp = listaEspecies[k].toString();
                        double slog = especies.get(esp).getSlog();
                        double splant = especies.get(esp).getSplant();
                        double plantviv = especies.get(esp).getPlantviv();
                        double plantm = especies.get(esp).getPlantm();
                        double perdhect = especies.get(esp).getPerdhect();
                        double perddin = especies.get(esp).getPerddin();

                        Double log3erCont = new Double(0);
                        Double sup3erCont = new Double(0);

                        if(splant != 0)
                         log3erCont = Redondear((slog/splant)*100);

                        if((plantviv!=0) || (plantm!=0))
                         sup3erCont = Redondear((plantviv/(plantviv+plantm))*100);

                        deftm.addRow(new String []{"", mun, esp, log3erCont.toString(), sup3erCont.toString(),
                                                  Redondear(perddin).toString(), Redondear(perdhect).toString()});

                        Subtslog += slog;
                        Subtsplant += splant;
                        Subtplantviv += plantviv;
                        Subtplantm += plantm;
                        Subtperdhect += perdhect;
                        Subtperddin += perddin;
                    }
                   //Subtotal de Mun
                     Double log3erCont = new Double(0);
                     Double sup3erCont = new Double(0);

                     if(Subtsplant != 0)
                      log3erCont = Redondear((Subtslog/Subtsplant)*100);

                     if((Subtplantviv!=0) || (Subtplantm!=0))
                      sup3erCont = Redondear((Subtplantviv/(Subtplantviv+Subtplantm))*100);

                     deftm.addRow(new String []{"", CONSTANTS.VALUE_SUB_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Subtperddin).toString(), Redondear(Subtperdhect).toString()});

                     Totslog += Subtslog;
                     Totsplant += Subtsplant;
                     Totplantviv += Subtplantviv;
                     Totplantm += Subtplantm;
                     Totperdhect += Subtperdhect;
                     Totperddin += Subtperddin;

                     Subtslog = 0;
                     Subtsplant = 0;
                     Subtplantviv = 0;
                     Subtplantm = 0;
                     Subtperdhect = 0;
                     Subtperddin = 0;
               }

             //Total
               Double log3erCont = new Double(0);
               Double sup3erCont = new Double(0);

               if(Totsplant != 0)
                log3erCont = Redondear((Totslog/Totsplant)*100);

               if((Totplantviv!=0) || (Totplantm!=0))
                sup3erCont = Redondear((Totplantviv/(Totplantviv+Totplantm))*100);

               deftm.addRow(new String []{"", CONSTANTS.VALUE_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});
       return deftm;
    }

    @Override
    protected TableModel getModel_PROV_DESGLOSE()
    {
       String mun = "";
       String tipoEntidad = "";

       //Municipio, [Tipo de entidad, [especie, Obj_Efect_Plant] ]
       HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>> municipios = new HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                          CONSTANTS.TIPOENTIDAD2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME,
                                          CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = ReportQuerys.SQL_1_4_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateProv_Desglose_OR_Mun_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_4_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateProv_Desglose_OR_Mun_HASH(municipios, tipoEntidad);

               sql = ReportQuerys.SQL_1_4_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
               sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateProv_Desglose_OR_Mun_HASH(municipios, tipoEntidad);

              //Agregando filas
               Object [] listaMun = municipios.keySet().toArray();
               double Subtslog = 0;
               double Subtsplant = 0;
               double Subtplantviv = 0;
               double Subtplantm = 0;
               double Subtperdhect = 0;
               double Subtperddin = 0;

               double SubtMunslog = 0;
               double SubtMunsplant = 0;
               double SubtMunplantviv = 0;
               double SubtMunplantm = 0;
               double SubtMunperdhect = 0;
               double SubtMunperddin = 0;

               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperdhect = 0;
               double Totperddin = 0;

               for(int i=0; i<listaMun.length; i++)
               {
                mun = listaMun[i].toString();
                HashMap<String, HashMap<String, Obj_Efect_Plant>> entidades = municipios.get(mun);
                Object [] listaEntidades = entidades.keySet().toArray();

                    for(int j=0; j<listaEntidades.length; j++)   //Entidad
                    {
                     tipoEntidad = listaEntidades[j].toString();
                     HashMap<String, Obj_Efect_Plant> especies = entidades.get(tipoEntidad);
                     Object [] listaEspecies = especies.keySet().toArray();

                        for(int k=0; k<listaEspecies.length; k++)  //Especies
                        {
                            String esp = listaEspecies[k].toString();
                            double slog = especies.get(esp).getSlog();
                            double splant = especies.get(esp).getSplant();
                            double plantviv = especies.get(esp).getPlantviv();
                            double plantm = especies.get(esp).getPlantm();
                            double perdhect = especies.get(esp).getPerdhect();
                            double perddin = especies.get(esp).getPerddin();

                            Double log3erCont = new Double(0);
                            Double sup3erCont = new Double(0);

                            if(splant != 0)
                             log3erCont = Redondear((slog/splant)*100);

                            if((plantviv!=0) || (plantm!=0))
                             sup3erCont = Redondear((plantviv/(plantviv+plantm))*100);

                            deftm.addRow(new String []{"", mun, tipoEntidad, esp, log3erCont.toString(),
                                                       sup3erCont.toString(), Redondear(perddin).toString(), Redondear(perdhect).toString()});

                            Subtslog += slog;
                            Subtsplant += splant;
                            Subtplantviv += plantviv;
                            Subtplantm += plantm;
                            Subtperdhect += perdhect;
                            Subtperddin += perddin;
                        }

                       //Subtotal de Entidades
                         Double log3erCont = new Double(0);
                         Double sup3erCont = new Double(0);

                         if(Subtsplant != 0)
                          log3erCont = Redondear((Subtslog/Subtsplant)*100);

                         if((Subtplantviv!=0) || (Subtplantm!=0))
                          sup3erCont = Redondear((Subtplantviv/(Subtplantviv+Subtplantm))*100);

                         deftm.addRow(new String []{"", "", CONSTANTS.VALUE_SUB_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                    Redondear(Subtperddin).toString(), Redondear(Subtperdhect).toString()});

                         SubtMunslog += Subtslog;
                         SubtMunsplant += Subtsplant;
                         SubtMunplantviv += Subtplantviv;
                         SubtMunplantm += Subtplantm;
                         SubtMunperdhect += Subtperdhect;
                         SubtMunperddin += Subtperddin;

                         Subtslog = 0;
                         Subtsplant = 0;
                         Subtplantviv = 0;
                         Subtplantm = 0;
                         Subtperdhect = 0;
                         Subtperddin = 0;
                    }

                 //Subtotal de Municipio
                   Double log3erCont = new Double(0);
                   Double sup3erCont = new Double(0);

                   if(SubtMunsplant != 0)
                    log3erCont = Redondear((SubtMunslog/SubtMunsplant)*100);

                   if((SubtMunplantviv!=0) || (SubtMunplantm!=0))
                    sup3erCont = Redondear((SubtMunplantviv/(SubtMunplantviv+SubtMunplantm))*100);

                   deftm.addRow(new String []{"", CONSTANTS.VALUE_SUB_TOTAL_MUN, "", "", log3erCont.toString(), sup3erCont.toString(),
                                                    Redondear(SubtMunperddin).toString(), Redondear(SubtMunperdhect).toString()});

                   Totslog += SubtMunslog;
                   Totsplant += SubtMunsplant;
                   Totplantviv += SubtMunplantviv;
                   Totplantm += SubtMunplantm;
                   Totperdhect += SubtMunperdhect;
                   Totperddin += SubtMunperddin;

                   SubtMunslog = 0;
                   SubtMunsplant = 0;
                   SubtMunplantviv = 0;
                   SubtMunplantm = 0;
                   SubtMunperdhect = 0;
                   SubtMunperddin = 0;
               }

             //Total
               Double log3erCont = new Double(0);
               Double sup3erCont = new Double(0);

               if(Totsplant != 0)
                log3erCont = Redondear((Totslog/Totsplant)*100);

               if((Totplantviv!=0) || (Totplantm!=0))
                sup3erCont = Redondear((Totplantviv/(Totplantviv+Totplantm))*100);

               deftm.addRow(new String []{CONSTANTS.VALUE_TOTAL, CONSTANTS.VALUE_TOTAL, "", "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});

       return deftm;
    }

    @Override
    protected TableModel getModel_NAC()
    {
       HashMap<String, HashMap<String, Obj_Efect_Plant>> provincias = new HashMap<String, HashMap<String, Obj_Efect_Plant>>();
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME,
                                          CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = getSQLReplacement(ReportQuerys.SQL_1_4_PROV_SubGrupoUS, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateValues_HASH(provincias);

               sql = getSQLReplacement(ReportQuerys.SQL_1_4_PROV_SubGrupoAP, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateValues_HASH(provincias);

               sql = getSQLReplacement(ReportQuerys.SQL_1_4_PROV_SubGrupoOTROS, anno.toString());
               db.executeQuery(sql);
               if(!db.isEmpty())
                updateValues_HASH(provincias);

             //Agregando filas
               Object [] listaProv = provincias.keySet().toArray();
               double Subtslog = 0;
               double Subtsplant = 0;
               double Subtplantviv = 0;
               double Subtplantm = 0;
               double Subtperdhect = 0;
               double Subtperddin = 0;

               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperdhect = 0;
               double Totperddin = 0;

               for(int i=0; i<listaProv.length; i++)
               {
                String prov = listaProv[i].toString();
                HashMap<String, Obj_Efect_Plant> especies = provincias.get(prov);
                Object [] listaEspecies = especies.keySet().toArray();

                    for(int k=0; k<listaEspecies.length; k++)  //Especies
                    {
                        String esp = listaEspecies[k].toString();
                        double slog = especies.get(esp).getSlog();
                        double splant = especies.get(esp).getSplant();
                        double plantviv = especies.get(esp).getPlantviv();
                        double plantm = especies.get(esp).getPlantm();
                        double perdhect = especies.get(esp).getPerdhect();
                        double perddin = especies.get(esp).getPerddin();

                        Double log3erCont = new Double(0);
                        Double sup3erCont = new Double(0);

                        if(splant != 0)
                         log3erCont = Redondear((slog/splant)*100);

                        if((plantviv!=0) || (plantm!=0))
                         sup3erCont = Redondear((plantviv/(plantviv+plantm))*100);

                        deftm.addRow(new String []{prov, esp, log3erCont.toString(), sup3erCont.toString(),
                                                  Redondear(perddin).toString(), Redondear(perdhect).toString()});

                        Subtslog += slog;
                        Subtsplant += splant;
                        Subtplantviv += plantviv;
                        Subtplantm += plantm;
                        Subtperdhect += perdhect;
                        Subtperddin += perddin;
                    }
                   //Subtotal de Prov
                     Double log3erCont = new Double(0);
                     Double sup3erCont = new Double(0);

                     if(Subtsplant != 0)
                      log3erCont = Redondear((Subtslog/Subtsplant)*100);

                     if((Subtplantviv!=0) || (Subtplantm!=0))
                      sup3erCont = Redondear((Subtplantviv/(Subtplantviv+Subtplantm))*100);

                     deftm.addRow(new String []{CONSTANTS.VALUE_SUB_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Subtperddin).toString(), Redondear(Subtperdhect).toString()});

                     Totslog += Subtslog;
                     Totsplant += Subtsplant;
                     Totplantviv += Subtplantviv;
                     Totplantm += Subtplantm;
                     Totperdhect += Subtperdhect;
                     Totperddin += Subtperddin;

                     Subtslog = 0;
                     Subtsplant = 0;
                     Subtplantviv = 0;
                     Subtplantm = 0;
                     Subtperdhect = 0;
                     Subtperddin = 0;
               }

             //Total
               Double log3erCont = new Double(0);
               Double sup3erCont = new Double(0);

               if(Totsplant != 0)
                log3erCont = Redondear((Totslog/Totsplant)*100);

               if((Totplantviv!=0) || (Totplantm!=0))
                sup3erCont = Redondear((Totplantviv/(Totplantviv+Totplantm))*100);

               deftm.addRow(new String []{CONSTANTS.VALUE_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});
       return deftm;
    }

    @Override
    protected TableModel getModel_NAC_DESGLOSE()
    {
       String tipoEntidad = "";

     //Provincia, [ Municipio, [ Entidad, [Especie, Obj_Tac_SCP] ]                                                  prov            mun             ent            especie
       HashMap<String, HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>>> provincias = new HashMap<String, HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>>>();

//     //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                            prov            mun             ent
//       HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>>();

       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.ESPECIE_COLUMN_NAME,
                                          CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME,
                                          CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

               String sql = getSQLReplacement(ReportQuerys.SQL_1_4_PROV_SubGrupoUS, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

               sql = getSQLReplacement(ReportQuerys.SQL_1_4_PROV_SubGrupoAP, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

               sql = getSQLReplacement(ReportQuerys.SQL_1_4_PROV_SubGrupoOTROS, anno.toString());
               db.executeQuery(sql);
               tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
               if(!db.isEmpty())
                updateNacional_Desglose_HASH(provincias, tipoEntidad);

              //Agregando filas
               Object [] listaProvincias = provincias.keySet().toArray();
               double Subtslog = 0;
               double Subtsplant = 0;
               double Subtplantviv = 0;
               double Subtplantm = 0;
               double Subtperdhect = 0;
               double Subtperddin = 0;

               double SubtMunslog = 0;
               double SubtMunsplant = 0;
               double SubtMunplantviv = 0;
               double SubtMunplantm = 0;
               double SubtMunperdhect = 0;
               double SubtMunperddin = 0;

               double SubProvtslog = 0;
               double SubProvtsplant = 0;
               double SubProvtplantviv = 0;
               double SubProvtplantm = 0;
               double SubProvtperdhect = 0;
               double SubProvtperddin = 0;

               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperdhect = 0;
               double Totperddin = 0;
               String prov = "";
               String mun = "";
               String esp = "";
               for(int i=0; i<listaProvincias.length; i++) //Provincias
               {
                prov = listaProvincias[i].toString();
                HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>> municipios = provincias.get(prov);
                Object [] listaMunicipios = municipios.keySet().toArray();

                    for(int j=0; j<listaMunicipios.length; j++)   //Municipios
                    {
                     mun = listaMunicipios[j].toString();
                     HashMap<String, HashMap<String, Obj_Efect_Plant>> entidades = municipios.get(mun);
                     Object [] listaEntidades = entidades.keySet().toArray();

                        for(int h=0; h<listaEntidades.length; h++)  //Entidades
                        {
                            String ent = listaEntidades[h].toString();
                            HashMap<String, Obj_Efect_Plant> especies = entidades.get(ent);
                            Object [] listaEspecies = especies.keySet().toArray();

                            for(int k=0; k<listaEspecies.length; k++)  //Especies
                            {

                                esp = listaEspecies[k].toString();
                                double slog = especies.get(esp).getSlog();
                                double splant = especies.get(esp).getSplant();
                                double plantviv = especies.get(esp).getPlantviv();
                                double plantm = especies.get(esp).getPlantm();
                                double perdhect = especies.get(esp).getPerdhect();
                                double perddin = especies.get(esp).getPerddin();

                                Double log3erCont = new Double(0);
                                Double sup3erCont = new Double(0);

                                if(splant != 0)
                                 log3erCont = Redondear((slog/splant)*100);

                                if((plantviv!=0) || (plantm!=0))
                                 sup3erCont = Redondear((plantviv/(plantviv+plantm))*100);

                                deftm.addRow(new String []{prov, mun, ent, esp, log3erCont.toString(),
                                                           sup3erCont.toString(), Redondear(perddin).toString(), Redondear(perdhect).toString()});

                                Subtslog += slog;
                                Subtsplant += splant;
                                Subtplantviv += plantviv;
                                Subtplantm += plantm;
                                Subtperdhect += perdhect;
                                Subtperddin += perddin;
                            }

                           //Subtotal de Entidades
                             Double log3erCont = new Double(0);
                             Double sup3erCont = new Double(0);

                             if(Subtsplant != 0)
                              log3erCont = Redondear((Subtslog/Subtsplant)*100);

                             if((Subtplantviv!=0) || (Subtplantm!=0))
                              sup3erCont = Redondear((Subtplantviv/(Subtplantviv+Subtplantm))*100);

                             deftm.addRow(new String []{"", "", CONSTANTS.VALUE_SUB_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                        Redondear(Subtperddin).toString(), Redondear(Subtperdhect).toString()});

                             SubtMunslog += Subtslog;
                             SubtMunsplant += Subtsplant;
                             SubtMunplantviv += Subtplantviv;
                             SubtMunplantm += Subtplantm;
                             SubtMunperdhect += Subtperdhect;
                             SubtMunperddin += Subtperddin;

                             Subtslog = 0;
                             Subtsplant = 0;
                             Subtplantviv = 0;
                             Subtplantm = 0;
                             Subtperdhect = 0;
                             Subtperddin = 0;
                        }

                       //Subtotal de Municipios
                         Double log3erCont = new Double(0);
                         Double sup3erCont = new Double(0);

                         if(Subtsplant != 0)
                          log3erCont = Redondear((SubtMunslog/SubtMunsplant)*100);

                         if((SubtMunplantviv!=0) || (SubtMunplantm!=0))
                          sup3erCont = Redondear((SubtMunplantviv/(SubtMunplantviv+SubtMunplantm))*100);

                         deftm.addRow(new String []{"", CONSTANTS.VALUE_SUB_TOTAL, "", "", log3erCont.toString(), sup3erCont.toString(),
                                                    Redondear(SubtMunperddin).toString(), Redondear(SubtMunperdhect).toString()});

                         SubProvtslog += SubtMunslog;
                         SubProvtsplant += SubtMunsplant;
                         SubProvtplantviv += SubtMunplantviv;
                         SubProvtplantm += SubtMunplantm;
                         SubProvtperdhect += SubtMunperdhect;
                         SubProvtperddin += SubtMunperddin;

                         SubtMunslog = 0;
                         SubtMunsplant = 0;
                         SubtMunplantviv = 0;
                         SubtMunplantm = 0;
                         SubtMunperdhect = 0;
                         SubtMunperddin = 0;
                    }

                   //Subtotal de Provincias
                     Double log3erCont = new Double(0);
                     Double sup3erCont = new Double(0);

                     if(SubProvtsplant != 0)
                      log3erCont = Redondear((SubProvtslog/SubProvtsplant)*100);

                     if((SubProvtplantviv!=0) || (SubProvtplantm!=0))
                      sup3erCont = Redondear((SubProvtplantviv/(SubProvtplantviv+SubProvtplantm))*100);

                     deftm.addRow(new String []{ CONSTANTS.VALUE_SUB_TOTAL_PROV, "", "", "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(SubProvtperddin).toString(), Redondear(SubProvtperdhect).toString()});

                     Totslog += SubProvtslog;
                     Totsplant += SubProvtsplant;
                     Totplantviv += SubProvtplantviv;
                     Totplantm += SubProvtplantm;
                     Totperdhect += SubProvtperdhect;
                     Totperddin += SubProvtperddin;

                     SubProvtslog = 0;
                     SubProvtsplant = 0;
                     SubProvtplantviv = 0;
                     SubProvtplantm = 0;
                     SubProvtperdhect = 0;
                     SubProvtperddin = 0;
               }

             //Total
               Double log3erCont = new Double(0);
               Double sup3erCont = new Double(0);

               if(Totsplant != 0)
                log3erCont = Redondear((Totslog/Totsplant)*100);

               if((Totplantviv!=0) || (Totplantm!=0))
                sup3erCont = Redondear((Totplantviv/(Totplantviv+Totplantm))*100);

               deftm.addRow(new String []{CONSTANTS.VALUE_TOTAL, "", "", "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});

       return deftm;
    }

    @Override
    protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
    {
       HashMap<String, HashMap<String, Obj_Efect_Plant>> entidades = new HashMap<String, HashMap<String, Obj_Efect_Plant>>();
        
       String esp = "";
       String ent = "";
       Object []columNames = null;
       DefaultTableModel deftm = null;
       String sql = "";

       if(bu.equals(BaseUnits.Unidad_Silvicola))
       {
        columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME, CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME,
                                  CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_4_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Area_Protegida))
       {
        columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME, CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME,
                                  CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_4_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
       }
       else
       if(bu.equals(BaseUnits.Otros))
       {
        columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.ESPECIE_COLUMN_NAME, CONSTANTS.LOGRO_TERCER_CONTEO2_COLUMN_NAME,
                                  CONSTANTS.SUP_TERCER_CONTEO2_COLUMN_NAME, CONSTANTS.PERDIDA_DINERO2_COLUMN_NAME, CONSTANTS.PERDIDA_HECT2_COLUMN_NAME};
        sql = getSQLReplacement(ReportQuerys.SQL_1_4_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
       }

        deftm = new DefaultTableModel(columNames, 0);

        db.executeQuery(sql);
        
        if(!db.isEmpty())
        {
             updateValues_HASH(entidades);
             
             //Agregando filas
               Object [] listaEntidades = entidades.keySet().toArray();
               double Subtslog = 0;
               double Subtsplant = 0;
               double Subtplantviv = 0;
               double Subtplantm = 0;
               double Subtperdhect = 0;
               double Subtperddin = 0;

               double Totslog = 0;
               double Totsplant = 0;
               double Totplantviv = 0;
               double Totplantm = 0;
               double Totperdhect = 0;
               double Totperddin = 0;

               for(int i=0; i<listaEntidades.length; i++)
               {
                ent = listaEntidades[i].toString();
                HashMap<String, Obj_Efect_Plant> especies = entidades.get(ent);
                Object [] listaEspecies = especies.keySet().toArray();

                    for(int k=0; k<listaEspecies.length; k++)  //Especies
                    {
                        esp = listaEspecies[k].toString();
                        double slog = especies.get(esp).getSlog();
                        double splant = especies.get(esp).getSplant();
                        double plantviv = especies.get(esp).getPlantviv();
                        double plantm = especies.get(esp).getPlantm();
                        double perdhect = especies.get(esp).getPerdhect();
                        double perddin = especies.get(esp).getPerddin();

                        Double log3erCont = new Double(0);
                        Double sup3erCont = new Double(0);

                        if(splant != 0)
                         log3erCont = Redondear((slog/splant)*100);

                        if((plantviv!=0) || (plantm!=0))
                         sup3erCont = Redondear((plantviv/(plantviv+plantm))*100);

                        deftm.addRow(new String []{ent, esp, log3erCont.toString(), sup3erCont.toString(),
                                                  Redondear(perddin).toString(), Redondear(perdhect).toString()});

                        Subtslog += slog;
                        Subtsplant += splant;
                        Subtplantviv += plantviv;
                        Subtplantm += plantm;
                        Subtperdhect += perdhect;
                        Subtperddin += perddin;
                    }
                   //Subtotal de Entidad
                     Double log3erCont = new Double(0);
                     Double sup3erCont = new Double(0);

                     if(Subtsplant != 0)
                      log3erCont = Redondear((Subtslog/Subtsplant)*100);

                     if((Subtplantviv!=0) || (Subtplantm!=0))
                      sup3erCont = Redondear((Subtplantviv/(Subtplantviv+Subtplantm))*100);

                     deftm.addRow(new String []{CONSTANTS.VALUE_SUB_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Subtperddin).toString(), Redondear(Subtperdhect).toString()});

                     Totslog += Subtslog;
                     Totsplant += Subtsplant;
                     Totplantviv += Subtplantviv;
                     Totplantm += Subtplantm;
                     Totperdhect += Subtperdhect;
                     Totperddin += Subtperddin;

                     Subtslog = 0;
                     Subtsplant = 0;
                     Subtplantviv = 0;
                     Subtplantm = 0;
                     Subtperdhect = 0;
                     Subtperddin = 0;
               }

             //Total
               Double log3erCont = new Double(0);
               Double sup3erCont = new Double(0);

               if(Totsplant != 0)
                log3erCont = Redondear((Totslog/Totsplant)*100);

               if((Totplantviv!=0) || (Totplantm!=0))
                sup3erCont = Redondear((Totplantviv/(Totplantviv+Totplantm))*100);

               deftm.addRow(new String []{CONSTANTS.VALUE_TOTAL, "", log3erCont.toString(), sup3erCont.toString(),
                                                Redondear(Totperddin).toString(), Redondear(Totperdhect).toString()});
     }
        return deftm;
    }

    private void updateValues_HASH(HashMap<String, HashMap<String, Obj_Efect_Plant>> hashValues)
    {
                 String ent = "";  //Puede ser provincia, municipio, US, Ap u Otros. Depende del nivel de utilizacion
                 double slog = 0;
                 double splant = 0;
                 double plantviv = 0;
                 double plantm = 0;
                 double perddin = 0;
                 double perdhect = 0;
                 double espprecio = 0;
                 String esp = "";
                 for(int i=0; i<db.getRowCount(); i++)
                 {
                     slog = Double.parseDouble(db.getValueAt(i,0).toString());
                     splant = Double.parseDouble(db.getValueAt(i,1).toString());
                     plantviv = Double.parseDouble(db.getValueAt(i,2).toString());
                     plantm = Double.parseDouble(db.getValueAt(i,3).toString());
                     espprecio = Double.parseDouble(db.getValueAt(i,4).toString());
                     esp = db.getValueAt(i,5).toString();
                     ent = db.getValueAt(i,6).toString();

                     perdhect = splant-slog;
                     perddin = espprecio*perdhect;

                   if(hashValues.containsKey(ent))
                   {
                        if(hashValues.get(ent).containsKey(esp))
                        {
                            slog = slog + hashValues.get(ent).get(esp).getSlog();
                            splant = splant + hashValues.get(ent).get(esp).getSplant();
                            plantviv = plantviv + hashValues.get(ent).get(esp).getPlantviv();
                            plantm = plantm + hashValues.get(ent).get(esp).getPlantm();
                            perdhect = perdhect + hashValues.get(ent).get(esp).getPerdhect();
                            perddin = perddin + hashValues.get(ent).get(esp).getPerddin();
                        }

                        hashValues.get(ent).put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                   }
                   else
                   {
                    HashMap<String, Obj_Efect_Plant> especie = new HashMap<String, Obj_Efect_Plant>();
                    especie.put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                    hashValues.put(ent, especie);
                   }
                 }
    }

    /**Puede utilizarse tanto para el nivel de
     * municipio como para el desglose por provincias*/
    private void updateProv_Desglose_OR_Mun_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>> municipios, String tipoEntidad)
    {
                 String mun = "";
                 double slog = 0;
                 double splant = 0;
                 double plantviv = 0;
                 double plantm = 0;
                 double perddin = 0;
                 double perdhect = 0;
                 double espprecio = 0;
                 String esp = "";
                 for(int i=0; i<db.getRowCount(); i++)
                 {
                     slog = Double.parseDouble(db.getValueAt(i,0).toString());
                     splant = Double.parseDouble(db.getValueAt(i,1).toString());
                     plantviv = Double.parseDouble(db.getValueAt(i,2).toString());
                     plantm = Double.parseDouble(db.getValueAt(i,3).toString());
                     espprecio = Double.parseDouble(db.getValueAt(i,4).toString());
                     esp = db.getValueAt(i,5).toString();
                     mun = db.getValueAt(i,6).toString();

                     perdhect = splant-slog;
                     perddin = espprecio*perdhect;

                   if(municipios.containsKey(mun)) //Si ya esta el municipio
                   {
                        if(municipios.get(mun).containsKey(tipoEntidad))  //Si en ese municipio ya esta la entidad
                        {
                            if(municipios.get(mun).get(tipoEntidad).containsKey(esp))  //Si en esa entidad ya esta esta especie
                            {
                                slog = slog + municipios.get(mun).get(tipoEntidad).get(esp).getSlog();
                                splant = splant + municipios.get(mun).get(tipoEntidad).get(esp).getSplant();
                                plantviv = plantviv + municipios.get(mun).get(tipoEntidad).get(esp).getPlantviv();
                                plantm = plantm + municipios.get(mun).get(tipoEntidad).get(esp).getPlantm();
                                perdhect = perdhect + municipios.get(mun).get(tipoEntidad).get(esp).getPerdhect();
                                perddin = perddin + municipios.get(mun).get(tipoEntidad).get(esp).getPerddin();
                            }

                            municipios.get(mun).get(tipoEntidad).put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                        }
                        else
                        {
                            HashMap<String, Obj_Efect_Plant> especie = new HashMap<String, Obj_Efect_Plant>();
                            especie.put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                            municipios.get(mun).put(tipoEntidad, especie);
                        }
                   }
                   else
                   {
                    HashMap<String, Obj_Efect_Plant> especie = new HashMap<String, Obj_Efect_Plant>();
                    especie.put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                    HashMap<String, HashMap<String, Obj_Efect_Plant>> entidad = new HashMap<String, HashMap<String, Obj_Efect_Plant>>();
                    entidad.put(tipoEntidad, especie);
                    municipios.put(mun, entidad);
                   }
                  }
    }

    private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>>> provincias, String tipoEntidad)
    {
                 String prov = "";
                 String mun = "";
                 double slog = 0;
                 double splant = 0;
                 double plantviv = 0;
                 double plantm = 0;
                 double perddin = 0;
                 double perdhect = 0;
                 double espprecio = 0;
                 String esp = "";

                 for(int i=0; i<db.getRowCount(); i++)
                 {
                     slog = Double.parseDouble(db.getValueAt(i,0).toString());
                     splant = Double.parseDouble(db.getValueAt(i,1).toString());
                     plantviv = Double.parseDouble(db.getValueAt(i,2).toString());
                     plantm = Double.parseDouble(db.getValueAt(i,3).toString());
                     espprecio = Double.parseDouble(db.getValueAt(i,4).toString());
                     esp = db.getValueAt(i,5).toString();
                     prov = db.getValueAt(i,6).toString();
                     mun = db.getValueAt(i,7).toString();

                     perdhect = splant-slog;
                     perddin = espprecio*perdhect;

                   if(provincias.containsKey(prov)) //Si ya esta la provincia
                   {
                        if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
                        {
                            if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                            {
                                if(provincias.get(prov).get(mun).get(tipoEntidad).containsKey(esp))  //Si esa entidad esta esa especie
                                {
                                    slog = slog + provincias.get(prov).get(mun).get(tipoEntidad).get(esp).getSlog();
                                    splant = splant + provincias.get(prov).get(mun).get(tipoEntidad).get(esp).getSplant();
                                    plantviv = plantviv + provincias.get(prov).get(mun).get(tipoEntidad).get(esp).getPlantviv();
                                    plantm = plantm + provincias.get(prov).get(mun).get(tipoEntidad).get(esp).getPlantm();
                                    perdhect = perdhect + provincias.get(prov).get(mun).get(tipoEntidad).get(esp).getPerdhect();
                                    perddin = perddin + provincias.get(prov).get(mun).get(tipoEntidad).get(esp).getPerddin();
                                }

                                provincias.get(prov).get(mun).get(tipoEntidad).put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                            }
                            else
                            {
                                HashMap<String, Obj_Efect_Plant> especie = new HashMap<String, Obj_Efect_Plant>();
                                especie.put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                                provincias.get(prov).get(mun).put(tipoEntidad, especie);
                            }
                        }
                        else
                        {
                            HashMap<String, Obj_Efect_Plant> especie = new HashMap<String, Obj_Efect_Plant>();
                            especie.put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                            HashMap<String, HashMap<String, Obj_Efect_Plant>> entidad = new HashMap<String, HashMap<String, Obj_Efect_Plant>>();
                            entidad.put(tipoEntidad, especie);
                            provincias.get(prov).put(mun, entidad);
                        }
                   }
                   else
                   {
                        HashMap<String, Obj_Efect_Plant> especie = new HashMap<String, Obj_Efect_Plant>();
                        especie.put(esp, new Obj_Efect_Plant(slog, splant, plantviv, plantm, perddin, perdhect));
                        HashMap<String, HashMap<String, Obj_Efect_Plant>> entidad = new HashMap<String, HashMap<String, Obj_Efect_Plant>>();
                        entidad.put(tipoEntidad, especie);
                        HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>> municipio = new HashMap<String, HashMap<String, HashMap<String, Obj_Efect_Plant>>>();
                        municipio.put(mun, entidad);
                        provincias.put(prov, municipio);
                   }
                 }
    }
}