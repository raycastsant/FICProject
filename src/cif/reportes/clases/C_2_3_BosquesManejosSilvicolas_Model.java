 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Bosq_Afect;
import cif.reportes.objects.Obj_Bosq_Man_Silv;
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
public class C_2_3_BosquesManejosSilvicolas_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_2_3_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_2_3_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_2_3_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_2_3_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "" , "" , "" , db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString(),
                                 db.getValueAt(0,3).toString(), db.getValueAt(0,4).toString(), db.getValueAt(0,5).toString()};
     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
       String mun = "";
       String total = "";
       String raleo = "";
       String limpia = "";
       String poda = "";
       String reconst = "";

       String tipoEntidad = "";
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                          CONSTANTS.TOTAL2_COLUMN_NAME, CONSTANTS.RALEO2_COLUMN_NAME, CONSTANTS.LIMPIA2_COLUMN_NAME,
                                          CONSTANTS.PODA2_COLUMN_NAME, CONSTANTS.RECONSTRUCCION2_COLUMN_NAME};
       DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

       String sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           mun = db.getValueAt(0,0).toString();
           total = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
           raleo = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
           limpia = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
           poda = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();
           reconst = Redondear(Double.parseDouble(db.getValueAt(0,5).toString())).toString();

           tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
           row = new String []{"", mun, tipoEntidad, total, raleo, limpia, poda, reconst};
           deftm.addRow(row);
       }

       sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           mun = db.getValueAt(0,0).toString();
           total = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
           raleo = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
           limpia = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
           poda = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();
           reconst = Redondear(Double.parseDouble(db.getValueAt(0,5).toString())).toString();

           tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
           row = new String []{"", mun, tipoEntidad, total, raleo, limpia, poda, reconst};
           deftm.addRow(row);
       }

       sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           mun = db.getValueAt(0,0).toString();
           total = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
           raleo = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
           limpia = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
           poda = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();
           reconst = Redondear(Double.parseDouble(db.getValueAt(0,5).toString())).toString();

           tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
           row = new String []{"", mun, tipoEntidad, total, raleo, limpia, poda, reconst};
           deftm.addRow(row);
       }

         //Totales  //              //                    //               //             //                    //
           double sejecRaleo = 0;
           double sejecLimp = 0;
           double sejecPoda = 0;
           double sejecReconst = 0;
           double necanRaleo = 0;
           double necanLimp = 0;
           double necanPoda = 0;
           double necanReconst = 0;
           sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               sejecRaleo = Double.parseDouble(db.getValueAt(0,0).toString());
               sejecLimp = Double.parseDouble(db.getValueAt(0,1).toString());
               sejecPoda = Double.parseDouble(db.getValueAt(0,2).toString());
               sejecReconst = Double.parseDouble(db.getValueAt(0,3).toString());
               necanRaleo = Double.parseDouble(db.getValueAt(0,4).toString());
               necanLimp = Double.parseDouble(db.getValueAt(0,5).toString());
               necanPoda = Double.parseDouble(db.getValueAt(0,6).toString());
               necanReconst = Double.parseDouble(db.getValueAt(0,7).toString());
           }

           sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               sejecRaleo += Double.parseDouble(db.getValueAt(0,0).toString());
               sejecLimp += Double.parseDouble(db.getValueAt(0,1).toString());
               sejecPoda += Double.parseDouble(db.getValueAt(0,2).toString());
               sejecReconst += Double.parseDouble(db.getValueAt(0,3).toString());
               necanRaleo += Double.parseDouble(db.getValueAt(0,4).toString());
               necanLimp += Double.parseDouble(db.getValueAt(0,5).toString());
               necanPoda += Double.parseDouble(db.getValueAt(0,6).toString());
               necanReconst += Double.parseDouble(db.getValueAt(0,7).toString());
           }

           sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
           {
               sejecRaleo += Double.parseDouble(db.getValueAt(0,0).toString());
               sejecLimp += Double.parseDouble(db.getValueAt(0,1).toString());
               sejecPoda += Double.parseDouble(db.getValueAt(0,2).toString());
               sejecReconst += Double.parseDouble(db.getValueAt(0,3).toString());
               necanRaleo += Double.parseDouble(db.getValueAt(0,4).toString());
               necanLimp += Double.parseDouble(db.getValueAt(0,5).toString());
               necanPoda += Double.parseDouble(db.getValueAt(0,6).toString());
               necanReconst += Double.parseDouble(db.getValueAt(0,7).toString());
           }

       Double TOTAL = new Double(0);
       Double RALEO = new Double(0);
       Double LIMPIA = new Double(0);
       Double PODA = new Double(0);
       Double RECONST = new Double(0);

       if(necanRaleo!=0 || necanLimp!=0 || necanPoda!=0 || necanReconst!=0)
        TOTAL = Redondear((sejecRaleo+sejecLimp+sejecPoda+sejecReconst)/(necanRaleo+necanLimp+necanPoda+necanReconst));

       if(necanRaleo != 0)
        RALEO = Redondear(sejecRaleo/necanRaleo);

       if(necanLimp != 0)
        LIMPIA = Redondear(sejecLimp/necanLimp);

       if(necanPoda != 0)
        PODA = Redondear(sejecPoda/necanPoda);

       if(necanReconst != 0)
        RECONST = Redondear(sejecReconst/necanReconst);
       
       row = new String []{"TOTAL", "", "TOTAL", TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                          RECONST.toString()};
       deftm.addRow(row);

       return deftm;
}

@Override
protected TableModel getModel_PROV()
{
       Object []row = null;
       Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                          CONSTANTS.TOTAL2_COLUMN_NAME, CONSTANTS.RALEO2_COLUMN_NAME, CONSTANTS.LIMPIA2_COLUMN_NAME,
                                          CONSTANTS.PODA2_COLUMN_NAME, CONSTANTS.RECONSTRUCCION2_COLUMN_NAME};
       HashMap<String, Obj_Bosq_Man_Silv> hashValues = new HashMap<String, Obj_Bosq_Man_Silv>();

       DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_2_3_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_2_3_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_2_3_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

      //Agregando filas
       Object [] lista = hashValues.keySet().toArray() ;
       double totsejecRaleo = 0;
       double totsejecLimp = 0;
       double totsejecPoda = 0;
       double totsejecReconst = 0;
       double totnecanRaleo = 0;
       double totnecanLimp = 0;
       double totnecanPoda = 0;
       double totnecanReconst = 0;

       for(int i=0; i<lista.length; i++)
       {
        double sejecRaleo = hashValues.get(lista[i].toString()).getSejecRaleo();
        double sejecLimp = hashValues.get(lista[i].toString()).getSejecLimp();
        double sejecPoda = hashValues.get(lista[i].toString()).getSejecPoda();
        double sejecReconst = hashValues.get(lista[i].toString()).getSejecReconst();
        double necanRaleo = hashValues.get(lista[i].toString()).getNecanRaleo();
        double necanLimp = hashValues.get(lista[i].toString()).getNecanLimp();
        double necanPoda = hashValues.get(lista[i].toString()).getNecanPoda();
        double necanReconst = hashValues.get(lista[i].toString()).getNecanReconst();

           Double TOTAL = new Double(0);
           Double RALEO = new Double(0);
           Double LIMPIA = new Double(0);
           Double PODA = new Double(0);
           Double RECONST = new Double(0);

           if(necanRaleo!=0 || necanLimp!=0 || necanPoda!=0 || necanReconst!=0)
            TOTAL = Redondear((sejecRaleo+sejecLimp+sejecPoda+sejecReconst)/(necanRaleo+necanLimp+necanPoda+necanReconst));

           if(necanRaleo != 0)
            RALEO = Redondear(sejecRaleo/necanRaleo);

           if(necanLimp != 0)
            LIMPIA = Redondear(sejecLimp/necanLimp);

           if(necanPoda != 0)
            PODA = Redondear(sejecPoda/necanPoda);

           if(necanReconst != 0)
            RECONST = Redondear(sejecReconst/necanReconst);
           
        deftm.addRow(new String []{"", lista[i].toString(), TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                                   RECONST.toString()});
        totsejecRaleo += sejecRaleo;
        totsejecLimp += sejecLimp;
        totsejecPoda += sejecPoda;
        totsejecReconst += sejecReconst;
        totnecanRaleo += necanRaleo;
        totnecanLimp += necanLimp;
        totnecanPoda += necanPoda;
        totnecanReconst += necanReconst;
       }

       Double TOTAL = new Double(0);
       Double RALEO = new Double(0);
       Double LIMPIA = new Double(0);
       Double PODA = new Double(0);
       Double RECONST = new Double(0);

       if(totnecanRaleo!=0 || totnecanLimp!=0 || totnecanPoda!=0 || totnecanReconst!=0)
        TOTAL = Redondear((totsejecRaleo+totsejecLimp+totsejecPoda+totsejecReconst)/(totnecanRaleo+totnecanLimp+totnecanPoda+totnecanReconst));

       if(totnecanRaleo != 0)
        RALEO = Redondear(totsejecRaleo/totnecanRaleo);

       if(totnecanLimp != 0)
        LIMPIA = Redondear(totsejecLimp/totnecanLimp);

       if(totnecanPoda != 0)
        PODA = Redondear(totsejecPoda/totnecanPoda);

       if(totnecanReconst != 0)
        RECONST = Redondear(totsejecReconst/totnecanReconst);
       
       row = new String []{"", "TOTAL", TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(), RECONST.toString()};
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
   HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>> municipios = new HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.TOTAL2_COLUMN_NAME, CONSTANTS.RALEO2_COLUMN_NAME, CONSTANTS.LIMPIA2_COLUMN_NAME,
                                      CONSTANTS.PODA2_COLUMN_NAME, CONSTANTS.RECONSTRUCCION2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_2_3_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       if(!db.isEmpty())
        updateProvincia_Desglose_HASH(municipios, tipoEntidad);

       sql = ReportQuerys.SQL_2_3_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       if(!db.isEmpty())
        updateProvincia_Desglose_HASH(municipios, tipoEntidad);

       sql = ReportQuerys.SQL_2_3_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       if(!db.isEmpty())
        updateProvincia_Desglose_HASH(municipios, tipoEntidad);

      //Agregando filas
       Object [] listaMun = municipios.keySet().toArray();
       double totsejecRaleo = 0;
       double totsejecLimp = 0;
       double totsejecPoda = 0;
       double totsejecReconst = 0;
       double totnecanRaleo = 0;
       double totnecanLimp = 0;
       double totnecanPoda = 0;
       double totnecanReconst = 0;

       for(int i=0; i<listaMun.length; i++)
       {
        mun = listaMun[i].toString();
        HashMap<String, Obj_Bosq_Man_Silv> entidades = municipios.get(mun);
        Object [] listaEntidades = entidades.keySet().toArray();

            for(int k=0; k<listaEntidades.length; k++)
            {
                double sejecRaleo = entidades.get(listaEntidades[k].toString()).getSejecRaleo();
                double sejecLimp = entidades.get(listaEntidades[k].toString()).getSejecLimp();
                double sejecPoda = entidades.get(listaEntidades[k].toString()).getSejecPoda();
                double sejecReconst = entidades.get(listaEntidades[k].toString()).getSejecReconst();
                double necanRaleo = entidades.get(listaEntidades[k].toString()).getNecanRaleo();
                double necanLimp = entidades.get(listaEntidades[k].toString()).getNecanLimp();
                double necanPoda = entidades.get(listaEntidades[k].toString()).getNecanPoda();
                double necanReconst = entidades.get(listaEntidades[k].toString()).getNecanReconst();

               Double TOTAL = new Double(0);
               Double RALEO = new Double(0);
               Double LIMPIA = new Double(0);
               Double PODA = new Double(0);
               Double RECONST = new Double(0);

               if(necanRaleo!=0 || necanLimp!=0 || necanPoda!=0 || necanReconst!=0)
                TOTAL = Redondear((sejecRaleo+sejecLimp+sejecPoda+sejecReconst)/(necanRaleo+necanLimp+necanPoda+necanReconst));

               if(necanRaleo != 0)
                RALEO = Redondear(sejecRaleo/necanRaleo);

               if(necanLimp != 0)
                LIMPIA = Redondear(sejecLimp/necanLimp);

               if(necanPoda != 0)
                PODA = Redondear(sejecPoda/necanPoda);

               if(necanReconst != 0)
                RECONST = Redondear(sejecReconst/necanReconst);

                deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                                   RECONST.toString()});
                totsejecRaleo += sejecRaleo;
                totsejecLimp += sejecLimp;
                totsejecPoda += sejecPoda;
                totsejecReconst += sejecReconst;
                totnecanRaleo += necanRaleo;
                totnecanLimp += necanLimp;
                totnecanPoda += necanPoda;
                totnecanReconst += necanReconst;
            }
       }

       Double TOTAL = new Double(0);
       Double RALEO = new Double(0);
       Double LIMPIA = new Double(0);
       Double PODA = new Double(0);
       Double RECONST = new Double(0);

       if(totnecanRaleo!=0 || totnecanLimp!=0 || totnecanPoda!=0 || totnecanReconst!=0)
        TOTAL = Redondear((totsejecRaleo+totsejecLimp+totsejecPoda+totsejecReconst)/(totnecanRaleo+totnecanLimp+totnecanPoda+totnecanReconst));

       if(totnecanRaleo != 0)
        RALEO = Redondear(totsejecRaleo/totnecanRaleo);

       if(totnecanLimp != 0)
        LIMPIA = Redondear(totsejecLimp/totnecanLimp);

       if(totnecanPoda != 0)
        PODA = Redondear(totsejecPoda/totnecanPoda);

       if(totnecanReconst != 0)
        RECONST = Redondear(totsejecReconst/totnecanReconst);
       
        row = new String []{"", "TOTAL", "", TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                            RECONST.toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   Object []row = null;
   HashMap<String, Obj_Bosq_Man_Silv> hashValues = new HashMap<String, Obj_Bosq_Man_Silv>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME, CONSTANTS.RALEO2_COLUMN_NAME, CONSTANTS.LIMPIA2_COLUMN_NAME,
                                      CONSTANTS.PODA2_COLUMN_NAME, CONSTANTS.RECONSTRUCCION2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

           String sql = getSQLReplacement(ReportQuerys.SQL_2_3_PROV_SubGrupoUS, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

           sql = getSQLReplacement(ReportQuerys.SQL_2_3_PROV_SubGrupoAP, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

           sql = getSQLReplacement(ReportQuerys.SQL_2_3_PROV_SubGrupoOTROS, anno.toString());
           db.executeQuery(sql);
           if(!db.isEmpty())
            updateProvincia_HASH(hashValues);

          //Agregando filas
           Object [] lista = hashValues.keySet().toArray() ;
           double totsejecRaleo = 0;
           double totsejecLimp = 0;
           double totsejecPoda = 0;
           double totsejecReconst = 0;
           double totnecanRaleo = 0;
           double totnecanLimp = 0;
           double totnecanPoda = 0;
           double totnecanReconst = 0;

           for(int i=0; i<lista.length; i++)
           {
                double sejecRaleo = hashValues.get(lista[i].toString()).getSejecRaleo();
                double sejecLimp = hashValues.get(lista[i].toString()).getSejecLimp();
                double sejecPoda = hashValues.get(lista[i].toString()).getSejecPoda();
                double sejecReconst = hashValues.get(lista[i].toString()).getSejecReconst();
                double necanRaleo = hashValues.get(lista[i].toString()).getNecanRaleo();
                double necanLimp = hashValues.get(lista[i].toString()).getNecanLimp();
                double necanPoda = hashValues.get(lista[i].toString()).getNecanPoda();
                double necanReconst = hashValues.get(lista[i].toString()).getNecanReconst();

               Double TOTAL = new Double(0);
               Double RALEO = new Double(0);
               Double LIMPIA = new Double(0);
               Double PODA = new Double(0);
               Double RECONST = new Double(0);

               if(necanRaleo!=0 || necanLimp!=0 || necanPoda!=0 || necanReconst!=0)
                TOTAL = Redondear((sejecRaleo+sejecLimp+sejecPoda+sejecReconst)/(necanRaleo+necanLimp+necanPoda+necanReconst));

               if(necanRaleo != 0)
                RALEO = Redondear(sejecRaleo/necanRaleo);

               if(necanLimp != 0)
                LIMPIA = Redondear(sejecLimp/necanLimp);

               if(necanPoda != 0)
                PODA = Redondear(sejecPoda/necanPoda);

               if(necanReconst != 0)
                RECONST = Redondear(sejecReconst/necanReconst);

                deftm.addRow(new String []{lista[i].toString(), TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                                           RECONST.toString()});
                totsejecRaleo += sejecRaleo;
                totsejecLimp += sejecLimp;
                totsejecPoda += sejecPoda;
                totsejecReconst += sejecReconst;
                totnecanRaleo += necanRaleo;
                totnecanLimp += necanLimp;
                totnecanPoda += necanPoda;
                totnecanReconst += necanReconst;
           }

           Double TOTAL = new Double(0);
           Double RALEO = new Double(0);
           Double LIMPIA = new Double(0);
           Double PODA = new Double(0);
           Double RECONST = new Double(0);

           if(totnecanRaleo!=0 || totnecanLimp!=0 || totnecanPoda!=0 || totnecanReconst!=0)
            TOTAL = Redondear((totsejecRaleo+totsejecLimp+totsejecPoda+totsejecReconst)/(totnecanRaleo+totnecanLimp+totnecanPoda+totnecanReconst));

           if(totnecanRaleo != 0)
            RALEO = Redondear(totsejecRaleo/totnecanRaleo);

           if(totnecanLimp != 0)
            LIMPIA = Redondear(totsejecLimp/totnecanLimp);

           if(totnecanPoda != 0)
            PODA = Redondear(totsejecPoda/totnecanPoda);

           if(totnecanReconst != 0)
            RECONST = Redondear(totsejecReconst/totnecanReconst);

           row = new String []{"TOTAL", TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                               RECONST.toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
   Object []row = null;
   String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                              prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.TOTAL2_COLUMN_NAME, CONSTANTS.RALEO2_COLUMN_NAME, CONSTANTS.LIMPIA2_COLUMN_NAME,
                                      CONSTANTS.PODA2_COLUMN_NAME, CONSTANTS.RECONSTRUCCION2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_2_3_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
       sql = getSQLReplacement(sql, anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       if(!db.isEmpty())
        updateNacional_Desglose_HASH(provincias, tipoEntidad);

       sql = ReportQuerys.SQL_2_3_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
       sql = getSQLReplacement(sql, anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       if(!db.isEmpty())
        updateNacional_Desglose_HASH(provincias, tipoEntidad);

       sql = ReportQuerys.SQL_2_3_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
       sql = getSQLReplacement(sql, anno.toString());
       db.executeQuery(sql);
       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       if(!db.isEmpty())
        updateNacional_Desglose_HASH(provincias, tipoEntidad);

      //Agregando filas
       Object [] listaProvincias = provincias.keySet().toArray();
       double SubtotsejecRaleo = 0;
       double SubtotsejecLimp = 0;
       double SubtotsejecPoda = 0;
       double SubtotsejecReconst = 0;
       double SubtotnecanRaleo = 0;
       double SubtotnecanLimp = 0;
       double SubtotnecanPoda = 0;
       double SubtotnecanReconst = 0;

       double totsejecRaleo = 0;
       double totsejecLimp = 0;
       double totsejecPoda = 0;
       double totsejecReconst = 0;
       double totnecanRaleo = 0;
       double totnecanLimp = 0;
       double totnecanPoda = 0;
       double totnecanReconst = 0;
       String prov = "";
       String mun = "";
       for(int i=0; i<listaProvincias.length; i++) //Provincias
       {
        prov = listaProvincias[i].toString();
        HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>> municipios = provincias.get(prov);
        Object [] listaMunicipios = municipios.keySet().toArray();

            for(int j=0; j<listaMunicipios.length; j++)   //Municipios
            {
             mun = listaMunicipios[j].toString();
             HashMap<String, Obj_Bosq_Man_Silv> entidades = municipios.get(mun);
             Object [] listaEntidades = entidades.keySet().toArray();

                for(int k=0; k<listaEntidades.length; k++)  //Entidades
                {
                    double sejecRaleo = entidades.get(listaEntidades[k].toString()).getSejecRaleo();
                    double sejecLimp = entidades.get(listaEntidades[k].toString()).getSejecLimp();
                    double sejecPoda = entidades.get(listaEntidades[k].toString()).getSejecPoda();
                    double sejecReconst = entidades.get(listaEntidades[k].toString()).getSejecReconst();
                    double necanRaleo = entidades.get(listaEntidades[k].toString()).getNecanRaleo();
                    double necanLimp = entidades.get(listaEntidades[k].toString()).getNecanLimp();
                    double necanPoda = entidades.get(listaEntidades[k].toString()).getNecanPoda();
                    double necanReconst = entidades.get(listaEntidades[k].toString()).getNecanReconst();

                   Double TOTAL = new Double(0);
                   Double RALEO = new Double(0);
                   Double LIMPIA = new Double(0);
                   Double PODA = new Double(0);
                   Double RECONST = new Double(0);

                   if(necanRaleo!=0 || necanLimp!=0 || necanPoda!=0 || necanReconst!=0)
                    TOTAL = Redondear((sejecRaleo+sejecLimp+sejecPoda+sejecReconst)/(necanRaleo+necanLimp+necanPoda+necanReconst));

                   if(necanRaleo != 0)
                    RALEO = Redondear(sejecRaleo/necanRaleo);

                   if(necanLimp != 0)
                    LIMPIA = Redondear(sejecLimp/necanLimp);

                   if(necanPoda != 0)
                    PODA = Redondear(sejecPoda/necanPoda);

                   if(necanReconst != 0)
                    RECONST = Redondear(sejecReconst/necanReconst);
                   
                    deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                                               RECONST.toString()});
                    SubtotsejecRaleo += sejecRaleo;
                    SubtotsejecLimp += sejecLimp;
                    SubtotsejecPoda += sejecPoda;
                    SubtotsejecReconst += sejecReconst;
                    SubtotnecanRaleo += necanRaleo;
                    SubtotnecanLimp += necanLimp;
                    SubtotnecanPoda += necanPoda;
                    SubtotnecanReconst += necanReconst;
                }

               Double TOTAL = new Double(0);
               Double RALEO = new Double(0);
               Double LIMPIA = new Double(0);
               Double PODA = new Double(0);
               Double RECONST = new Double(0);

               if(SubtotnecanRaleo!=0 || SubtotnecanLimp!=0 || SubtotnecanPoda!=0 || SubtotnecanReconst!=0)
                TOTAL = Redondear((SubtotsejecRaleo+SubtotsejecLimp+SubtotsejecPoda+SubtotsejecReconst)/(SubtotnecanRaleo+SubtotnecanLimp+SubtotnecanPoda+SubtotnecanReconst));

               if(SubtotnecanRaleo != 0)
                RALEO = Redondear(SubtotsejecRaleo/SubtotnecanRaleo);

               if(SubtotnecanLimp != 0)
                LIMPIA = Redondear(SubtotsejecLimp/SubtotnecanLimp);

               if(SubtotnecanPoda != 0)
                PODA = Redondear(SubtotsejecPoda/SubtotnecanPoda);

               if(SubtotnecanReconst != 0)
                RECONST = Redondear(SubtotsejecReconst/SubtotnecanReconst);

               deftm.addRow(new String []{"", "Sub-Total", "", TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                                               RECONST.toString()});
                totsejecRaleo += SubtotsejecRaleo;
                totsejecLimp += SubtotsejecLimp;
                totsejecPoda += SubtotsejecPoda;
                totsejecReconst += SubtotsejecReconst;
                totnecanRaleo += SubtotnecanRaleo;
                totnecanLimp += SubtotnecanLimp;
                totnecanPoda += SubtotnecanPoda;
                totnecanReconst += SubtotnecanReconst;

                SubtotsejecRaleo = 0;
                SubtotsejecLimp = 0;
                SubtotsejecPoda = 0;
                SubtotsejecReconst = 0;
                SubtotnecanRaleo = 0;
                SubtotnecanLimp = 0;
                SubtotnecanPoda = 0;
                SubtotnecanReconst = 0;
            }
       }

       Double TOTAL = new Double(0);
       Double RALEO = new Double(0);
       Double LIMPIA = new Double(0);
       Double PODA = new Double(0);
       Double RECONST = new Double(0);

       if(totnecanRaleo!=0 || totnecanLimp!=0 || totnecanPoda!=0 || totnecanReconst!=0)
        TOTAL = Redondear((totsejecRaleo+totsejecLimp+totsejecPoda+totsejecReconst)/(totnecanRaleo+totnecanLimp+totnecanPoda+totnecanReconst));

       if(totnecanRaleo != 0)
        RALEO = Redondear(totsejecRaleo/totnecanRaleo);

       if(totnecanLimp != 0)
        LIMPIA = Redondear(totsejecLimp/totnecanLimp);

       if(totnecanPoda != 0)
        PODA = Redondear(totsejecPoda/totnecanPoda);

       if(totnecanReconst != 0)
        RECONST = Redondear(totsejecReconst/totnecanReconst);

       row = new String []{"TOTAL", "", "", TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                                        RECONST.toString()};
       deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   double sejecRaleo = 0;
   double sejecLimp = 0;
   double sejecPoda = 0;
   double sejecReconst = 0;
   double necanRaleo = 0;
   double necanLimp = 0;
   double necanPoda = 0;
   double necanReconst = 0;

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   double totsejecRaleo = 0;
   double totsejecLimp = 0;
   double totsejecPoda = 0;
   double totsejecReconst = 0;
   double totnecanRaleo = 0;
   double totnecanLimp = 0;
   double totnecanPoda = 0;
   double totnecanReconst = 0;

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME, CONSTANTS.RALEO2_COLUMN_NAME, CONSTANTS.LIMPIA2_COLUMN_NAME,
                              CONSTANTS.PODA2_COLUMN_NAME, CONSTANTS.RECONSTRUCCION2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME, CONSTANTS.RALEO2_COLUMN_NAME, CONSTANTS.LIMPIA2_COLUMN_NAME,
                              CONSTANTS.PODA2_COLUMN_NAME, CONSTANTS.RECONSTRUCCION2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.TOTAL2_COLUMN_NAME, CONSTANTS.RALEO2_COLUMN_NAME, CONSTANTS.LIMPIA2_COLUMN_NAME,
                              CONSTANTS.PODA2_COLUMN_NAME, CONSTANTS.RECONSTRUCCION2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_2_3_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

           sejecRaleo = Double.parseDouble(db.getValueAt(i,1).toString());
           sejecLimp = Double.parseDouble(db.getValueAt(i,2).toString());
           sejecPoda = Double.parseDouble(db.getValueAt(i,3).toString());
           sejecReconst = Double.parseDouble(db.getValueAt(i,4).toString());
           necanRaleo = Double.parseDouble(db.getValueAt(i,5).toString());
           necanLimp = Double.parseDouble(db.getValueAt(i,6).toString());
           necanPoda = Double.parseDouble(db.getValueAt(i,7).toString());
           necanReconst = Double.parseDouble(db.getValueAt(i,8).toString());

           Double TOTAL = new Double(0);
           Double RALEO = new Double(0);
           Double LIMPIA = new Double(0);
           Double PODA = new Double(0);
           Double RECONST = new Double(0);

           if(necanRaleo!=0 || necanLimp!=0 || necanPoda!=0 || necanReconst!=0)
            TOTAL = Redondear((sejecRaleo+sejecLimp+sejecPoda+sejecReconst)/(necanRaleo+necanLimp+necanPoda+necanReconst));

           if(necanRaleo != 0)
            RALEO = Redondear(sejecRaleo/necanRaleo);

           if(necanLimp != 0)
            LIMPIA = Redondear(sejecLimp/necanLimp);

           if(necanPoda != 0)
            PODA = Redondear(sejecPoda/necanPoda);

           if(necanReconst != 0)
            RECONST = Redondear(sejecReconst/necanReconst);
           
           deftm.addRow(new String []{entidad, TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                                      RECONST.toString()});
            totsejecRaleo += sejecRaleo;
            totsejecLimp += sejecLimp;
            totsejecPoda += sejecPoda;
            totsejecReconst += sejecReconst;
            totnecanRaleo += necanRaleo;
            totnecanLimp += necanLimp;
            totnecanPoda += necanPoda;
            totnecanReconst += necanReconst;
         }

       Double TOTAL = new Double(0);
       Double RALEO = new Double(0);
       Double LIMPIA = new Double(0);
       Double PODA = new Double(0);
       Double RECONST = new Double(0);

       if(totnecanRaleo!=0 || totnecanLimp!=0 || totnecanPoda!=0 || totnecanReconst!=0)
        TOTAL = Redondear((totsejecRaleo+totsejecLimp+totsejecPoda+totsejecReconst)/(totnecanRaleo+totnecanLimp+totnecanPoda+totnecanReconst));

       if(totnecanRaleo != 0)
        RALEO = Redondear(totsejecRaleo/totnecanRaleo);

       if(totnecanLimp != 0)
        LIMPIA = Redondear(totsejecLimp/totnecanLimp);

       if(totnecanPoda != 0)
        PODA = Redondear(totsejecPoda/totnecanPoda);

       if(totnecanReconst != 0)
        RECONST = Redondear(totsejecReconst/totnecanReconst);

       deftm.addRow(new String []{"TOTAL", TOTAL.toString(), RALEO.toString(), LIMPIA.toString(), PODA.toString(),
                                  RECONST.toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_Bosq_Man_Silv> hashValues)
{
       String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
       double sejecRaleo = 0;
       double sejecLimp = 0;
       double sejecPoda = 0;
       double sejecReconst = 0;
       double necanRaleo = 0;
       double necanLimp = 0;
       double necanPoda = 0;
       double necanReconst = 0;
       
     for(int i=0; i<db.getRowCount(); i++)
     {
       sejecRaleo = Double.parseDouble(db.getValueAt(i,0).toString());
       sejecLimp = Double.parseDouble(db.getValueAt(i,1).toString());
       sejecPoda = Double.parseDouble(db.getValueAt(i,2).toString());
       sejecReconst = Double.parseDouble(db.getValueAt(i,3).toString());
       necanRaleo = Double.parseDouble(db.getValueAt(i,4).toString());
       necanLimp = Double.parseDouble(db.getValueAt(i,5).toString());
       necanPoda = Double.parseDouble(db.getValueAt(i,6).toString());
       necanReconst = Double.parseDouble(db.getValueAt(i,7).toString());
       Ent = db.getValueAt(i,8).toString();

       if(hashValues.containsKey(Ent))
       {
        sejecRaleo = sejecRaleo + hashValues.get(Ent).getSejecRaleo();
        sejecLimp = sejecLimp + hashValues.get(Ent).getSejecLimp();
        sejecPoda = sejecPoda + hashValues.get(Ent).getSejecPoda();
        sejecReconst = sejecReconst + hashValues.get(Ent).getSejecReconst();
        necanRaleo = necanRaleo + hashValues.get(Ent).getNecanRaleo();
        necanLimp = necanLimp + hashValues.get(Ent).getNecanLimp();
        necanPoda = necanPoda + hashValues.get(Ent).getNecanPoda();
        necanReconst = necanReconst + hashValues.get(Ent).getNecanReconst();
       }

       hashValues.put(Ent, new Obj_Bosq_Man_Silv(sejecRaleo, sejecLimp, sejecPoda, sejecReconst, necanRaleo, necanLimp, necanPoda, necanReconst));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>> municipios, String tipoEntidad)
{
     String mun = "";
     double sejecRaleo = 0;
     double sejecLimp = 0;
     double sejecPoda = 0;
     double sejecReconst = 0;
     double necanRaleo = 0;
     double necanLimp = 0;
     double necanPoda = 0;
     double necanReconst = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       sejecRaleo = Double.parseDouble(db.getValueAt(i,0).toString());
       sejecLimp = Double.parseDouble(db.getValueAt(i,1).toString());
       sejecPoda = Double.parseDouble(db.getValueAt(i,2).toString());
       sejecReconst = Double.parseDouble(db.getValueAt(i,3).toString());
       necanRaleo = Double.parseDouble(db.getValueAt(i,4).toString());
       necanLimp = Double.parseDouble(db.getValueAt(i,5).toString());
       necanPoda = Double.parseDouble(db.getValueAt(i,6).toString());
       necanReconst = Double.parseDouble(db.getValueAt(i,7).toString());
       mun = db.getValueAt(i,8).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                sejecRaleo = sejecRaleo + municipios.get(mun).get(tipoEntidad).getSejecRaleo();
                sejecLimp = sejecLimp + municipios.get(mun).get(tipoEntidad).getSejecLimp();
                sejecPoda = sejecPoda + municipios.get(mun).get(tipoEntidad).getSejecPoda();
                sejecReconst = sejecReconst + municipios.get(mun).get(tipoEntidad).getSejecReconst();
                necanRaleo = necanRaleo + municipios.get(mun).get(tipoEntidad).getNecanRaleo();
                necanLimp = necanLimp + municipios.get(mun).get(tipoEntidad).getNecanLimp();
                necanPoda = necanPoda + municipios.get(mun).get(tipoEntidad).getNecanPoda();
                necanReconst = necanReconst + municipios.get(mun).get(tipoEntidad).getNecanReconst();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_Bosq_Man_Silv(sejecRaleo, sejecLimp, sejecPoda, sejecReconst, necanRaleo, necanLimp, necanPoda, necanReconst));
       }
       else
       {
        HashMap<String, Obj_Bosq_Man_Silv> entidad = new HashMap<String, Obj_Bosq_Man_Silv>();
        entidad.put(tipoEntidad, new Obj_Bosq_Man_Silv(sejecRaleo, sejecLimp, sejecPoda, sejecReconst, necanRaleo, necanLimp, necanPoda, necanReconst));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>>> provincias, String tipoEntidad)
{
     String prov = "";
     String mun = "";
     double sejecRaleo = 0;
     double sejecLimp = 0;
     double sejecPoda = 0;
     double sejecReconst = 0;
     double necanRaleo = 0;
     double necanLimp = 0;
     double necanPoda = 0;
     double necanReconst = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       sejecRaleo = Double.parseDouble(db.getValueAt(i,0).toString());
       sejecLimp = Double.parseDouble(db.getValueAt(i,1).toString());
       sejecPoda = Double.parseDouble(db.getValueAt(i,2).toString());
       sejecReconst = Double.parseDouble(db.getValueAt(i,3).toString());
       necanRaleo = Double.parseDouble(db.getValueAt(i,4).toString());
       necanLimp = Double.parseDouble(db.getValueAt(i,5).toString());
       necanPoda = Double.parseDouble(db.getValueAt(i,6).toString());
       necanReconst = Double.parseDouble(db.getValueAt(i,7).toString());
       prov = db.getValueAt(i,8).toString();
       mun = db.getValueAt(i,9).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    sejecRaleo = sejecRaleo + provincias.get(prov).get(mun).get(tipoEntidad).getSejecRaleo();
                    sejecLimp = sejecLimp + provincias.get(prov).get(mun).get(tipoEntidad).getSejecLimp();
                    sejecPoda = sejecPoda + provincias.get(prov).get(mun).get(tipoEntidad).getSejecPoda();
                    sejecReconst = sejecReconst + provincias.get(prov).get(mun).get(tipoEntidad).getSejecReconst();
                    necanRaleo = necanRaleo + provincias.get(prov).get(mun).get(tipoEntidad).getNecanRaleo();
                    necanLimp = necanLimp + provincias.get(prov).get(mun).get(tipoEntidad).getNecanLimp();
                    necanPoda = necanPoda + provincias.get(prov).get(mun).get(tipoEntidad).getNecanPoda();
                    necanReconst = necanReconst + provincias.get(prov).get(mun).get(tipoEntidad).getNecanReconst();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Bosq_Man_Silv(sejecRaleo, sejecLimp, sejecPoda, sejecReconst, necanRaleo, necanLimp, necanPoda, necanReconst));
            }
            else
            {
                HashMap<String, Obj_Bosq_Man_Silv> entidad = new HashMap<String, Obj_Bosq_Man_Silv>();
                entidad.put(tipoEntidad, new Obj_Bosq_Man_Silv(sejecRaleo, sejecLimp, sejecPoda, sejecReconst, necanRaleo, necanLimp, necanPoda, necanReconst));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_Bosq_Man_Silv> entidad = new HashMap<String, Obj_Bosq_Man_Silv>();
        entidad.put(tipoEntidad, new Obj_Bosq_Man_Silv(sejecRaleo, sejecLimp, sejecPoda, sejecReconst, necanRaleo, necanLimp, necanPoda, necanReconst));
        HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>> municipio = new HashMap<String, HashMap<String, Obj_Bosq_Man_Silv>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}  
}

