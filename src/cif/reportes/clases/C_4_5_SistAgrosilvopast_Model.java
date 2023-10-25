 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_Sist_ASilv;
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
public class C_4_5_SistAgrosilvopast_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_4_5_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_4_5_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_4_5_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_4_5_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "" , "" , "" , db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString(),
                                  db.getValueAt(0,3).toString(), db.getValueAt(0,4).toString(), db.getValueAt(0,5).toString(),
                                  db.getValueAt(0,6).toString(), db.getValueAt(0,7).toString(), db.getValueAt(0,8).toString()};
     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
   String mun = "";
   String supPotAs = "";
   String supManAs = "";
   String porcSmSp = "";
   String cantFca = "";
   String supPotFca = "";
   String supFca = "";
   String porcFca = "";
   String areaBn = "";

   String tipoEntidad = "";
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUP_POT_AGROSILVOPASTORIL2_COLUMN_NAME, CONSTANTS.SUP_MAN_AGROSILVOPASTORIL2_COLUMN_NAME,
                                      CONSTANTS.PORC_SM_SP2_COLUMN_NAME, CONSTANTS.CANT_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.SUP_POT_FINCA_FORESTAL2_COLUMN_NAME,
                                      CONSTANTS.SUP_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.PORC_FINCA_SUP_FORESTAL2_COLUMN_NAME, CONSTANTS.AREA_BN_FINCA_FORESTAL2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supPotAs = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supManAs = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       porcSmSp = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
       cantFca = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();
       supPotFca = Redondear(Double.parseDouble(db.getValueAt(0,5).toString())).toString();
       supFca = Redondear(Double.parseDouble(db.getValueAt(0,6).toString())).toString();
       porcFca = Redondear(Double.parseDouble(db.getValueAt(0,7).toString())).toString();
       areaBn = Redondear(Double.parseDouble(db.getValueAt(0,8).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       deftm.addRow(new String []{"", mun, tipoEntidad, supPotAs, supManAs, porcSmSp, cantFca, supPotFca, supFca, porcFca, areaBn});
   }

   sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supPotAs = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supManAs = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       porcSmSp = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
       cantFca = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();
       supPotFca = Redondear(Double.parseDouble(db.getValueAt(0,5).toString())).toString();
       supFca = Redondear(Double.parseDouble(db.getValueAt(0,6).toString())).toString();
       porcFca = Redondear(Double.parseDouble(db.getValueAt(0,7).toString())).toString();
       areaBn = Redondear(Double.parseDouble(db.getValueAt(0,8).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       deftm.addRow(new String []{"", mun, tipoEntidad, supPotAs, supManAs, porcSmSp, cantFca, supPotFca, supFca, porcFca, areaBn});
   }

   sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       supPotAs = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       supManAs = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       porcSmSp = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();
       cantFca = Redondear(Double.parseDouble(db.getValueAt(0,4).toString())).toString();
       supPotFca = Redondear(Double.parseDouble(db.getValueAt(0,5).toString())).toString();
       supFca = Redondear(Double.parseDouble(db.getValueAt(0,6).toString())).toString();
       porcFca = Redondear(Double.parseDouble(db.getValueAt(0,7).toString())).toString();
       areaBn = Redondear(Double.parseDouble(db.getValueAt(0,8).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       deftm.addRow(new String []{"", mun, tipoEntidad, supPotAs, supManAs, porcSmSp, cantFca, supPotFca, supFca, porcFca, areaBn});
   }

   //Totales  //              //                    //               //             //                    //
       double sPotAs = 0;
       double sManAs = 0;
       double cantFinc = 0;
       double sPotFinc = 0;
       double sFinc = 0;
       double area = 0;
       sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           sPotAs = Double.parseDouble(db.getValueAt(0,0).toString());
           sManAs = Double.parseDouble(db.getValueAt(0,1).toString());
           cantFinc = Double.parseDouble(db.getValueAt(0,2).toString());
           sPotFinc = Double.parseDouble(db.getValueAt(0,3).toString());
           sFinc = Double.parseDouble(db.getValueAt(0,4).toString());
           area = Double.parseDouble(db.getValueAt(0,5).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           sPotAs += Double.parseDouble(db.getValueAt(0,0).toString());
           sManAs += Double.parseDouble(db.getValueAt(0,1).toString());
           cantFinc += Double.parseDouble(db.getValueAt(0,2).toString());
           sPotFinc += Double.parseDouble(db.getValueAt(0,3).toString());
           sFinc += Double.parseDouble(db.getValueAt(0,4).toString());
           area += Double.parseDouble(db.getValueAt(0,5).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           sPotAs += Double.parseDouble(db.getValueAt(0,0).toString());
           sManAs += Double.parseDouble(db.getValueAt(0,1).toString());
           cantFinc += Double.parseDouble(db.getValueAt(0,2).toString());
           sPotFinc += Double.parseDouble(db.getValueAt(0,3).toString());
           sFinc += Double.parseDouble(db.getValueAt(0,4).toString());
           area += Double.parseDouble(db.getValueAt(0,5).toString());
       }

   double PorcSmSp = 0;
   if(sPotAs != 0)
    PorcSmSp = (sManAs/sPotAs)*100;

   double PorcFcaForest = 0;
   if(sPotAs!=0 || sPotFinc!=0)
    PorcFcaForest = (sFinc/(sPotAs+sPotFinc))*100;

   deftm.addRow(new String []{"TOTAL", "", "TOTAL", Redondear(sPotAs).toString(), Redondear(sManAs).toString(), Redondear(PorcSmSp).toString(),
                              Redondear(cantFinc).toString(), Redondear(sPotFinc).toString(), Redondear(sFinc).toString(),
                              Redondear(PorcFcaForest).toString(), Redondear(area).toString()});

   return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                      CONSTANTS.SUP_POT_AGROSILVOPASTORIL2_COLUMN_NAME, CONSTANTS.SUP_MAN_AGROSILVOPASTORIL2_COLUMN_NAME,
                                      CONSTANTS.PORC_SM_SP2_COLUMN_NAME, CONSTANTS.CANT_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.SUP_POT_FINCA_FORESTAL2_COLUMN_NAME,
                                      CONSTANTS.SUP_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.PORC_FINCA_SUP_FORESTAL2_COLUMN_NAME, CONSTANTS.AREA_BN_FINCA_FORESTAL2_COLUMN_NAME};
   HashMap<String, Obj_Sist_ASilv> hashValues = new HashMap<String, Obj_Sist_ASilv>();

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_4_5_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_4_5_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_4_5_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

      //Agregando filas
       Object [] lista = hashValues.keySet().toArray() ;
       double totsPotAs = 0;
       double totsManAs = 0;
       double totcantFinc = 0;
       double totsPotFinc = 0;
       double totsFinc = 0;
       double totarea = 0;

       for(int i=0; i<lista.length; i++)
       {
        double sPotAs = hashValues.get(lista[i].toString()).getsPotAs();
        double sManAs = hashValues.get(lista[i].toString()).getsManAs();
        double cantFinc = hashValues.get(lista[i].toString()).getCantFinc();
        double sPotFinc = hashValues.get(lista[i].toString()).getsPotFinc();
        double sFinc = hashValues.get(lista[i].toString()).getsFinc();
        double area = hashValues.get(lista[i].toString()).getArea();

       double PorcSmSp = 0;
       if(sPotAs != 0)
        PorcSmSp = (sManAs/sPotAs)*100;

       double PorcFcaForest = 0;
       if(sPotAs!=0 || sPotFinc!=0)
        PorcFcaForest = (sFinc/(sPotAs+sPotFinc))*100;

        deftm.addRow(new String []{"", lista[i].toString(), Redondear(sPotAs).toString(), Redondear(sManAs).toString(), Redondear(PorcSmSp).toString(),
                                   Redondear(cantFinc).toString(), Redondear(sPotFinc).toString(), Redondear(sFinc).toString(),
                                   Redondear(PorcFcaForest).toString(), Redondear(area).toString()});
        totsPotAs += sPotAs;
        totsManAs += sManAs;
        totcantFinc += cantFinc;
        totsPotFinc += sPotFinc;
        totsFinc += sFinc;
        totarea += area;
       }

   double PorcSmSp = 0;
   if(totsPotAs != 0)
    PorcSmSp = (totsManAs/totsPotAs)*100;

   double PorcFcaForest = 0;
   if(totsPotAs!=0 || totsPotFinc!=0)
    PorcFcaForest = (totsFinc/(totsPotAs+totsPotFinc))*100;

    row = new String []{"", "TOTAL", Redondear(totsPotAs).toString(), Redondear(totsManAs).toString(), Redondear(PorcSmSp).toString(),
                        Redondear(totcantFinc).toString(), Redondear(totsPotFinc).toString(), Redondear(totsFinc).toString(),
                        Redondear(PorcFcaForest).toString(), Redondear(totarea).toString()};

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
   HashMap<String, HashMap<String, Obj_Sist_ASilv>> municipios = new HashMap<String, HashMap<String, Obj_Sist_ASilv>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUP_POT_AGROSILVOPASTORIL2_COLUMN_NAME, CONSTANTS.SUP_MAN_AGROSILVOPASTORIL2_COLUMN_NAME,
                                      CONSTANTS.PORC_SM_SP2_COLUMN_NAME, CONSTANTS.CANT_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.SUP_POT_FINCA_FORESTAL2_COLUMN_NAME,
                                      CONSTANTS.SUP_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.PORC_FINCA_SUP_FORESTAL2_COLUMN_NAME, CONSTANTS.AREA_BN_FINCA_FORESTAL2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_4_5_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_4_5_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_4_5_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

  //Agregando filas
   Object [] listaMun = municipios.keySet().toArray();
   double totsPotAs = 0;
   double totsManAs = 0;
   double totcantFinc = 0;
   double totsPotFinc = 0;
   double totsFinc = 0;
   double totarea = 0;

   for(int i=0; i<listaMun.length; i++)
   {
    mun = listaMun[i].toString();
    HashMap<String, Obj_Sist_ASilv> entidades = municipios.get(mun);
    Object [] listaEntidades = entidades.keySet().toArray();

        for(int k=0; k<listaEntidades.length; k++)
        {
            double sPotAs = entidades.get(listaEntidades[k].toString()).getsPotAs();
            double sManAs = entidades.get(listaEntidades[k].toString()).getsManAs();
            double cantFinc = entidades.get(listaEntidades[k].toString()).getCantFinc();
            double sPotFinc = entidades.get(listaEntidades[k].toString()).getsPotFinc();
            double sFinc = entidades.get(listaEntidades[k].toString()).getsFinc();
            double area = entidades.get(listaEntidades[k].toString()).getArea();

           double PorcSmSp = 0;
           if(sPotAs != 0)
            PorcSmSp = (sManAs/sPotAs)*100;

           double PorcFcaForest = 0;
           if(sPotAs!=0 || sPotFinc!=0)
            PorcFcaForest = (sFinc/(sPotAs+sPotFinc))*100;

            deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), Redondear(sPotAs).toString(), Redondear(sManAs).toString(), Redondear(PorcSmSp).toString(),
                                       Redondear(cantFinc).toString(), Redondear(sPotFinc).toString(), Redondear(sFinc).toString(),
                                       Redondear(PorcFcaForest).toString(), Redondear(area).toString()});
            totsPotAs += sPotAs;
            totsManAs += sManAs;
            totcantFinc += cantFinc;
            totsPotFinc += sPotFinc;
            totsFinc += sFinc;
            totarea += area;
        }
   }

   double PorcSmSp = 0;
   if(totsPotAs != 0)
    PorcSmSp = (totsManAs/totsPotAs)*100;

   double PorcFcaForest = 0;
   if(totsPotAs!=0 || totsPotFinc!=0)
    PorcFcaForest = (totsFinc/(totsPotAs+totsPotFinc))*100;

    row = new String []{"", "TOTAL", "", Redondear(totsPotAs).toString(), Redondear(totsManAs).toString(), Redondear(PorcSmSp).toString(),
                        Redondear(totcantFinc).toString(), Redondear(totsPotFinc).toString(), Redondear(totsFinc).toString(),
                        Redondear(PorcFcaForest).toString(), Redondear(totarea).toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   HashMap<String, Obj_Sist_ASilv> hashValues = new HashMap<String, Obj_Sist_ASilv>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.SUP_POT_AGROSILVOPASTORIL2_COLUMN_NAME, CONSTANTS.SUP_MAN_AGROSILVOPASTORIL2_COLUMN_NAME,
                                      CONSTANTS.PORC_SM_SP2_COLUMN_NAME, CONSTANTS.CANT_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.SUP_POT_FINCA_FORESTAL2_COLUMN_NAME,
                                      CONSTANTS.SUP_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.PORC_FINCA_SUP_FORESTAL2_COLUMN_NAME, CONSTANTS.AREA_BN_FINCA_FORESTAL2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_4_5_PROV_SubGrupoUS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_4_5_PROV_SubGrupoAP, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_4_5_PROV_SubGrupoOTROS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

  //Agregando filas
   Object [] lista = hashValues.keySet().toArray() ;
   double totsPotAs = 0;
   double totsManAs = 0;
   double totcantFinc = 0;
   double totsPotFinc = 0;
   double totsFinc = 0;
   double totarea = 0;

   for(int i=0; i<lista.length; i++)
   {
        double sPotAs = hashValues.get(lista[i].toString()).getsPotAs();
        double sManAs = hashValues.get(lista[i].toString()).getsManAs();
        double cantFinc = hashValues.get(lista[i].toString()).getCantFinc();
        double sPotFinc = hashValues.get(lista[i].toString()).getsPotFinc();
        double sFinc = hashValues.get(lista[i].toString()).getsFinc();
        double area = hashValues.get(lista[i].toString()).getArea();

       double PorcSmSp = 0;
       if(sPotAs != 0)
        PorcSmSp = (sManAs/sPotAs)*100;

       double PorcFcaForest = 0;
       if(sPotAs!=0 || sPotFinc!=0)
        PorcFcaForest = (sFinc/(sPotAs+sPotFinc))*100;

        deftm.addRow(new String []{lista[i].toString(), Redondear(sPotAs).toString(), Redondear(sManAs).toString(), Redondear(PorcSmSp).toString(),
                                   Redondear(cantFinc).toString(), Redondear(sPotFinc).toString(), Redondear(sFinc).toString(),
                                   Redondear(PorcFcaForest).toString(), Redondear(area).toString()});
        totsPotAs += sPotAs;
        totsManAs += sManAs;
        totcantFinc += cantFinc;
        totsPotFinc += sPotFinc;
        totsFinc += sFinc;
        totarea += area;
   }

   double PorcSmSp = 0;
   if(totsPotAs != 0)
    PorcSmSp = (totsManAs/totsPotAs)*100;

   double PorcFcaForest = 0;
   if(totsPotAs!=0 || totsPotFinc!=0)
    PorcFcaForest = (totsFinc/(totsPotAs+totsPotFinc))*100;

   deftm.addRow(new String []{"TOTAL", Redondear(totsPotAs).toString(), Redondear(totsManAs).toString(), Redondear(PorcSmSp).toString(),
                              Redondear(totcantFinc).toString(), Redondear(totsPotFinc).toString(), Redondear(totsFinc).toString(),
                              Redondear(PorcFcaForest).toString(), Redondear(totarea).toString()});

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
  String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                          prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_Sist_ASilv>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_Sist_ASilv>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.SUP_POT_AGROSILVOPASTORIL2_COLUMN_NAME, CONSTANTS.SUP_MAN_AGROSILVOPASTORIL2_COLUMN_NAME,
                                      CONSTANTS.PORC_SM_SP2_COLUMN_NAME, CONSTANTS.CANT_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.SUP_POT_FINCA_FORESTAL2_COLUMN_NAME,
                                      CONSTANTS.SUP_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.PORC_FINCA_SUP_FORESTAL2_COLUMN_NAME, CONSTANTS.AREA_BN_FINCA_FORESTAL2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_4_5_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_4_5_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_4_5_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

  //Agregando filas
   Object [] listaProvincias = provincias.keySet().toArray();
   double subtotsPotAs = 0;
   double subtotsManAs = 0;
   double subtotcantFinc = 0;
   double subtotsPotFinc = 0;
   double subtotsFinc = 0;
   double subtotarea = 0;

   double totsPotAs = 0;
   double totsManAs = 0;
   double totcantFinc = 0;
   double totsPotFinc = 0;
   double totsFinc = 0;
   double totarea = 0;
   String prov = "";
   String mun = "";
   for(int i=0; i<listaProvincias.length; i++) //Provincias
   {
    prov = listaProvincias[i].toString();
    HashMap<String, HashMap<String, Obj_Sist_ASilv>> municipios = provincias.get(prov);
    Object [] listaMunicipios = municipios.keySet().toArray();

        for(int j=0; j<listaMunicipios.length; j++)   //Municipios
        {
         mun = listaMunicipios[j].toString();
         HashMap<String, Obj_Sist_ASilv> entidades = municipios.get(mun);
         Object [] listaEntidades = entidades.keySet().toArray();

            for(int k=0; k<listaEntidades.length; k++)  //Entidades
            {
                double sPotAs = entidades.get(listaEntidades[k].toString()).getsPotAs();
                double sManAs = entidades.get(listaEntidades[k].toString()).getsManAs();
                double cantFinc = entidades.get(listaEntidades[k].toString()).getCantFinc();
                double sPotFinc = entidades.get(listaEntidades[k].toString()).getsPotFinc();
                double sFinc = entidades.get(listaEntidades[k].toString()).getsFinc();
                double area = entidades.get(listaEntidades[k].toString()).getArea();

               double PorcSmSp = 0;
               if(sPotAs != 0)
                PorcSmSp = (sManAs/sPotAs)*100;

               double PorcFcaForest = 0;
               if(sPotAs!=0 || sPotFinc!=0)
                PorcFcaForest = (sFinc/(sPotAs+sPotFinc))*100;

                deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), Redondear(sPotAs).toString(), Redondear(sManAs).toString(), Redondear(PorcSmSp).toString(),
                                           Redondear(cantFinc).toString(), Redondear(sPotFinc).toString(), Redondear(sFinc).toString(),
                                           Redondear(PorcFcaForest).toString(), Redondear(area).toString()});
                subtotsPotAs += sPotAs;
                subtotsManAs += sManAs;
                subtotcantFinc += cantFinc;
                subtotsPotFinc += sPotFinc;
                subtotsFinc += sFinc;
                subtotarea += area;
            }

           double PorcSmSp = 0;
           if(subtotsPotAs != 0)
            PorcSmSp = (subtotsManAs/subtotsPotAs)*100;

           double PorcFcaForest = 0;
           if(subtotsPotAs!=0 || subtotsPotFinc!=0)
            PorcFcaForest = (subtotsFinc/(subtotsPotAs+subtotsPotFinc))*100;

            deftm.addRow(new String []{"", "Sub-Total", "", Redondear(subtotsPotAs).toString(), Redondear(subtotsManAs).toString(), Redondear(PorcSmSp).toString(),
                        Redondear(subtotcantFinc).toString(), Redondear(subtotsPotFinc).toString(), Redondear(subtotsFinc).toString(),
                        Redondear(PorcFcaForest).toString(), Redondear(subtotarea).toString()});
            totsPotAs += subtotsPotAs;
            totsManAs += subtotsManAs;
            totcantFinc += subtotcantFinc;
            totsPotFinc += subtotsPotFinc;
            totsFinc += subtotsFinc;
            totarea += subtotarea;

            subtotsPotAs = 0;
            subtotsManAs = 0;
            subtotcantFinc = 0;
            subtotsPotFinc = 0;
            subtotsFinc = 0;
            subtotarea = 0;
        }
   }

   double PorcSmSp = 0;
   if(totsPotAs != 0)
    PorcSmSp = (totsManAs/totsPotAs)*100;

   double PorcFcaForest = 0;
   if(totsPotAs!=0 || totsPotFinc!=0)
    PorcFcaForest = (totsFinc/(totsPotAs+totsPotFinc))*100;

    deftm.addRow(new String []{"TOTAL", "", "", Redondear(totsPotAs).toString(), Redondear(totsManAs).toString(), Redondear(PorcSmSp).toString(),
                               Redondear(totcantFinc).toString(), Redondear(totsPotFinc).toString(), Redondear(totsFinc).toString(),
                               Redondear(PorcFcaForest).toString(), Redondear(totarea).toString()});

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   double sPotAs = 0;
   double sManAs = 0;
   double cantFinc = 0;
   double sPotFinc = 0;
   double sFinc = 0;
   double area = 0;

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   double totsPotAs = 0;
   double totsManAs = 0;
   double totcantFinc = 0;
   double totsPotFinc = 0;
   double totsFinc = 0;
   double totarea = 0;

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.SUP_POT_AGROSILVOPASTORIL2_COLUMN_NAME, CONSTANTS.SUP_MAN_AGROSILVOPASTORIL2_COLUMN_NAME,
                              CONSTANTS.PORC_SM_SP2_COLUMN_NAME, CONSTANTS.CANT_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.SUP_POT_FINCA_FORESTAL2_COLUMN_NAME,
                              CONSTANTS.SUP_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.PORC_FINCA_SUP_FORESTAL2_COLUMN_NAME, CONSTANTS.AREA_BN_FINCA_FORESTAL2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.SUP_POT_AGROSILVOPASTORIL2_COLUMN_NAME, CONSTANTS.SUP_MAN_AGROSILVOPASTORIL2_COLUMN_NAME,
                              CONSTANTS.PORC_SM_SP2_COLUMN_NAME, CONSTANTS.CANT_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.SUP_POT_FINCA_FORESTAL2_COLUMN_NAME,
                              CONSTANTS.SUP_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.PORC_FINCA_SUP_FORESTAL2_COLUMN_NAME, CONSTANTS.AREA_BN_FINCA_FORESTAL2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.SUP_POT_AGROSILVOPASTORIL2_COLUMN_NAME, CONSTANTS.SUP_MAN_AGROSILVOPASTORIL2_COLUMN_NAME,
                              CONSTANTS.PORC_SM_SP2_COLUMN_NAME, CONSTANTS.CANT_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.SUP_POT_FINCA_FORESTAL2_COLUMN_NAME,
                              CONSTANTS.SUP_FINCA_FORESTAL2_COLUMN_NAME, CONSTANTS.PORC_FINCA_SUP_FORESTAL2_COLUMN_NAME, CONSTANTS.AREA_BN_FINCA_FORESTAL2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_5_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

           sPotAs = Double.parseDouble(db.getValueAt(i,1).toString());
           sManAs = Double.parseDouble(db.getValueAt(i,2).toString());
           cantFinc = Double.parseDouble(db.getValueAt(i,3).toString());
           sPotFinc = Double.parseDouble(db.getValueAt(i,4).toString());
           sFinc = Double.parseDouble(db.getValueAt(i,5).toString());
           area = Double.parseDouble(db.getValueAt(i,6).toString());

           double PorcSmSp = 0;
           if(sPotAs != 0)
            PorcSmSp = (sManAs/sPotAs)*100;

           double PorcFcaForest = 0;
           if(sPotAs!=0 || sPotFinc!=0)
            PorcFcaForest = (sFinc/(sPotAs+sPotFinc))*100;

            deftm.addRow(new String []{entidad, Redondear(sPotAs).toString(), Redondear(sManAs).toString(), Redondear(PorcSmSp).toString(),
                                       Redondear(cantFinc).toString(), Redondear(sPotFinc).toString(), Redondear(sFinc).toString(),
                                       Redondear(PorcFcaForest).toString(), Redondear(area).toString()});
            totsPotAs += sPotAs;
            totsManAs += sManAs;
            totcantFinc += cantFinc;
            totsPotFinc += sPotFinc;
            totsFinc += sFinc;
            totarea += area;
         }

       double PorcSmSp = 0;
       if(totsPotAs != 0)
        PorcSmSp = (totsManAs/totsPotAs)*100;

       double PorcFcaForest = 0;
       if(totsPotAs!=0 || totsPotFinc!=0)
        PorcFcaForest = (totsFinc/(totsPotAs+totsPotFinc))*100;

        deftm.addRow(new String []{"TOTAL", Redondear(totsPotAs).toString(), Redondear(totsManAs).toString(), Redondear(PorcSmSp).toString(),
                                   Redondear(totcantFinc).toString(), Redondear(totsPotFinc).toString(), Redondear(totsFinc).toString(),
                                   Redondear(PorcFcaForest).toString(), Redondear(totarea).toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_Sist_ASilv> hashValues)
{
     String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
     double sPotAs = 0;
     double sManAs = 0;
     double cantFinc = 0;
     double sPotFinc = 0;
     double sFinc = 0;
     double area = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       sPotAs = Double.parseDouble(db.getValueAt(i,0).toString());
       sManAs = Double.parseDouble(db.getValueAt(i,1).toString());
       cantFinc = Double.parseDouble(db.getValueAt(i,2).toString());
       sPotFinc = Double.parseDouble(db.getValueAt(i,3).toString());
       sFinc = Double.parseDouble(db.getValueAt(i,4).toString());
       area = Double.parseDouble(db.getValueAt(i,5).toString());
       Ent = db.getValueAt(i,6).toString();

       if(hashValues.containsKey(Ent))
       {
        sPotAs = sPotAs + hashValues.get(Ent).getsPotAs();
        sManAs = sManAs + hashValues.get(Ent).getsManAs();
        cantFinc = cantFinc + hashValues.get(Ent).getCantFinc();
        sPotFinc = sPotFinc + hashValues.get(Ent).getsPotFinc();
        sFinc = sFinc + hashValues.get(Ent).getsFinc();
        area = area + hashValues.get(Ent).getArea();
       }

       hashValues.put(Ent, new Obj_Sist_ASilv(sPotAs, sManAs, cantFinc, sPotFinc, sFinc, area));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_Sist_ASilv>> municipios, String tipoEntidad)
{
     String mun = "";
     double sPotAs = 0;
     double sManAs = 0;
     double cantFinc = 0;
     double sPotFinc = 0;
     double sFinc = 0;
     double area = 0;
     
     for(int i=0; i<db.getRowCount(); i++)
     {
       sPotAs = Double.parseDouble(db.getValueAt(i,0).toString());
       sManAs = Double.parseDouble(db.getValueAt(i,1).toString());
       cantFinc = Double.parseDouble(db.getValueAt(i,2).toString());
       sPotFinc = Double.parseDouble(db.getValueAt(i,3).toString());
       sFinc = Double.parseDouble(db.getValueAt(i,4).toString());
       area = Double.parseDouble(db.getValueAt(i,5).toString());
       mun = db.getValueAt(i,6).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                sPotAs = sPotAs + municipios.get(mun).get(tipoEntidad).getsPotAs();
                sManAs = sManAs + municipios.get(mun).get(tipoEntidad).getsManAs();
                cantFinc = cantFinc + municipios.get(mun).get(tipoEntidad).getCantFinc();
                sPotFinc = sPotFinc + municipios.get(mun).get(tipoEntidad).getsPotFinc();
                sFinc = sFinc + municipios.get(mun).get(tipoEntidad).getsFinc();
                area = area + municipios.get(mun).get(tipoEntidad).getArea();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_Sist_ASilv(sPotAs, sManAs, cantFinc, sPotFinc, sFinc, area));
       }
       else
       {
        HashMap<String, Obj_Sist_ASilv> entidad = new HashMap<String, Obj_Sist_ASilv>();
        entidad.put(tipoEntidad, new Obj_Sist_ASilv(sPotAs, sManAs, cantFinc, sPotFinc, sFinc, area));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_Sist_ASilv>>> provincias, String tipoEntidad)
{
     String prov = "";
     String mun = "";
     double sPotAs = 0;
     double sManAs = 0;
     double cantFinc = 0;
     double sPotFinc = 0;
     double sFinc = 0;
     double area = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       sPotAs = Double.parseDouble(db.getValueAt(i,0).toString());
       sManAs = Double.parseDouble(db.getValueAt(i,1).toString());
       cantFinc = Double.parseDouble(db.getValueAt(i,2).toString());
       sPotFinc = Double.parseDouble(db.getValueAt(i,3).toString());
       sFinc = Double.parseDouble(db.getValueAt(i,4).toString());
       area = Double.parseDouble(db.getValueAt(i,5).toString());
       prov = db.getValueAt(i,6).toString();
       mun = db.getValueAt(i,7).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    sPotAs = sPotAs + provincias.get(prov).get(mun).get(tipoEntidad).getsPotAs();
                    sManAs = sManAs + provincias.get(prov).get(mun).get(tipoEntidad).getsManAs();
                    cantFinc = cantFinc + provincias.get(prov).get(mun).get(tipoEntidad).getCantFinc();
                    sPotFinc = sPotFinc + provincias.get(prov).get(mun).get(tipoEntidad).getsPotFinc();
                    sFinc = sFinc + provincias.get(prov).get(mun).get(tipoEntidad).getsFinc();
                    area = area + provincias.get(prov).get(mun).get(tipoEntidad).getArea();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_Sist_ASilv(sPotAs, sManAs, cantFinc, sPotFinc, sFinc, area));
            }
            else
            {
                HashMap<String, Obj_Sist_ASilv> entidad = new HashMap<String, Obj_Sist_ASilv>();
                entidad.put(tipoEntidad, new Obj_Sist_ASilv(sPotAs, sManAs, cantFinc, sPotFinc, sFinc, area));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_Sist_ASilv> entidad = new HashMap<String, Obj_Sist_ASilv>();
        entidad.put(tipoEntidad, new Obj_Sist_ASilv(sPotAs, sManAs, cantFinc, sPotFinc, sFinc, area));
        HashMap<String, HashMap<String, Obj_Sist_ASilv>> municipio = new HashMap<String, HashMap<String, Obj_Sist_ASilv>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}
}