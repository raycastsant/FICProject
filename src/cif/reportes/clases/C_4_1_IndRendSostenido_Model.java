 package cif.reportes.clases;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.CONSTANTS;
import cif.reportes.ReportQuerys;
import cif.reportes.objects.Obj_IRS;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;

/**
* @author Raisel
*/
public class C_4_1_IndRendSostenido_Model extends AbstractCritReport
{
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException
 {
    return getReportModel(user, anno, desglose, bu);
 }

 @Override
  protected TableModel getModel_US()
 {
    String sql = getSQLReplacement(ReportQuerys.SQL_4_1_US, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    return db;
 }

 @Override
 protected TableModel getModel_AP()
 {
   String sql = getSQLReplacement(ReportQuerys.SQL_4_1_AP, user.getIdEntidad(), anno.toString());
   db.executeQueryUperCase(sql);

   return db;
 }

 @Override
 protected TableModel getModel_EFI()
 {
    DefaultTableModel tm = new DefaultTableModel();

    String sql = getSQLReplacement(ReportQuerys.SQL_4_1_EFI_Subgrupo, user.getIdEntidad(), anno.toString());
    db.executeQueryUperCase(sql);
    if(!db.isEmpty())
    {
     tm = new javax.swing.table.DefaultTableModel(db.getAllRows(), db.getColumnNames());

     sql = getSQLReplacement(ReportQuerys.SQL_4_1_EFI_TOTAL, user.getIdEntidad(), anno.toString());
     db.executeQuery(sql);

     Object []row = new String []{"TOTAL", "" , "" , "" , db.getValueAt(0,1).toString(), db.getValueAt(0,2).toString(), db.getValueAt(0,3).toString()};
     tm.addRow(row);
    }

    return tm;
 }

 @Override
 protected TableModel getModel_MUN()
 {
   String mun = "";
   String irsProd = "";
   String irsProt = "";
   String irstotal = "";

   String tipoEntidad = "";
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.IRS_BOSQ_PROD2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROT2_COLUMN_NAME,
                                      CONSTANTS.IRS_TOTAl2_COLUMN_NAME};
   DefaultTableModel deftm= new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_SubGrupoUS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       irsProd = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       irsProt = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       irstotal = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
       deftm.addRow(new String []{"", mun, tipoEntidad, irsProd, irsProt, irstotal});
   }

   sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_SubGrupoAP, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       irsProd = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       irsProt = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       irstotal = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
       deftm.addRow(new String []{"", mun, tipoEntidad, irsProd, irsProt, irstotal});
   }

   sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_SubGrupoOTROS, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
   {
       mun = db.getValueAt(0,0).toString();
       irsProd = Redondear(Double.parseDouble(db.getValueAt(0,1).toString())).toString();
       irsProt = Redondear(Double.parseDouble(db.getValueAt(0,2).toString())).toString();
       irstotal = Redondear(Double.parseDouble(db.getValueAt(0,3).toString())).toString();

       tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
       deftm.addRow(new String []{"", mun, tipoEntidad, irsProd, irsProt, irstotal});
   }

   //Totales  //              //                    //               //             //                    //
       double cortEjecBProd = 0;
       double cortPermBProd = 0;
       double cortEjecBProt = 0;
       double cortPermBProt = 0;
       sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_US_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           cortEjecBProd = Double.parseDouble(db.getValueAt(0,0).toString());
           cortPermBProd = Double.parseDouble(db.getValueAt(0,1).toString());
           cortEjecBProt = Double.parseDouble(db.getValueAt(0,2).toString());
           cortPermBProt = Double.parseDouble(db.getValueAt(0,3).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_AP_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           cortEjecBProd += Double.parseDouble(db.getValueAt(0,0).toString());
           cortPermBProd += Double.parseDouble(db.getValueAt(0,1).toString());
           cortEjecBProt += Double.parseDouble(db.getValueAt(0,2).toString());
           cortPermBProt += Double.parseDouble(db.getValueAt(0,3).toString());
       }

       sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_OTROS_SubValues, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
       {
           cortEjecBProd += Double.parseDouble(db.getValueAt(0,0).toString());
           cortPermBProd += Double.parseDouble(db.getValueAt(0,1).toString());
           cortEjecBProt += Double.parseDouble(db.getValueAt(0,2).toString());
           cortPermBProt += Double.parseDouble(db.getValueAt(0,3).toString());
       }

   double IRSBProd = 0;
   double IRSBProt = 0;
   double IRSBTotal = 0;
   
   if(cortPermBProd != 0)
    IRSBProd = (cortEjecBProd/cortPermBProd)*100;

   if(cortPermBProt != 0)
    IRSBProt = (cortEjecBProt/cortPermBProt)*100;

   if(cortPermBProd!=0 || cortPermBProt!=0)
    IRSBTotal = ((cortEjecBProd+cortEjecBProt)/(cortPermBProd+cortPermBProt))*100;

   deftm.addRow(new String []{"TOTAL", "", "TOTAL", Redondear(IRSBProd).toString(),
                              Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});

   return deftm;
}

@Override
protected TableModel getModel_PROV()
{
   Object []row = null;
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME,
                                      CONSTANTS.IRS_BOSQ_PROD2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROT2_COLUMN_NAME,
                                      CONSTANTS.IRS_TOTAl2_COLUMN_NAME};
   HashMap<String, Obj_IRS> hashValues = new HashMap<String, Obj_IRS>();

   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

       String sql = ReportQuerys.SQL_4_1_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_4_1_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

       sql = ReportQuerys.SQL_4_1_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
       sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
       db.executeQuery(sql);
       if(!db.isEmpty())
        updateProvincia_HASH(hashValues);

      //Agregando filas
       Object [] lista = hashValues.keySet().toArray() ;
       double totcortEjecBProd = 0;
       double totcortPermBProd = 0;
       double totcortEjecBProt = 0;
       double totcortPermBProt = 0;

       for(int i=0; i<lista.length; i++)
       {
        double cortEjecBProd = hashValues.get(lista[i].toString()).getCortEjecBProd();
        double cortPermBProd = hashValues.get(lista[i].toString()).getCortPermBProd();
        double cortEjecBProt = hashValues.get(lista[i].toString()).getCortEjecBProt();
        double cortPermBProt = hashValues.get(lista[i].toString()).getCortPermBProt();

       double IRSBProd = 0;
       double IRSBProt = 0;
       double IRSBTotal = 0;

       if(cortPermBProd != 0)
        IRSBProd = (cortEjecBProd/cortPermBProd)*100;

       if(cortPermBProt != 0)
        IRSBProt = (cortEjecBProt/cortPermBProt)*100;

       if(cortPermBProd!=0 || cortPermBProt!=0)
        IRSBTotal = ((cortEjecBProd+cortEjecBProt)/(cortPermBProd+cortPermBProt))*100;

        deftm.addRow(new String []{"", lista[i].toString(), Redondear(IRSBProd).toString(),
                                   Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});
        totcortEjecBProd += cortEjecBProd;
        totcortPermBProd += cortPermBProd;
        totcortEjecBProt += cortEjecBProt;
        totcortPermBProt += cortPermBProt;
       }

    double IRSBProd = 0;
    double IRSBProt = 0;
    double IRSBTotal = 0;

    if(totcortPermBProd != 0)
     IRSBProd = (totcortEjecBProd/totcortPermBProd)*100;

    if(totcortPermBProt != 0)
     IRSBProt = (totcortEjecBProt/totcortPermBProt)*100;

    if(totcortPermBProd!=0 || totcortPermBProt!=0)
     IRSBTotal = ((totcortEjecBProd+totcortEjecBProt)/(totcortPermBProd+totcortPermBProt))*100;

    row = new String []{"", "TOTAL", Redondear(IRSBProd).toString(),
                        Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()};

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
   HashMap<String, HashMap<String, Obj_IRS>> municipios = new HashMap<String, HashMap<String, Obj_IRS>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                      CONSTANTS.IRS_BOSQ_PROD2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROT2_COLUMN_NAME,
                                      CONSTANTS.IRS_TOTAl2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_4_1_MUN_US_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_4_1_MUN_AP_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

   sql = ReportQuerys.SQL_4_1_MUN_OTROS_SubValues.replaceFirst("where municipios.id", "where provincias.id");
   sql = getSQLReplacement(sql, user.getIdEntidad(), anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateProvincia_Desglose_HASH(municipios, tipoEntidad);

  //Agregando filas
   Object [] listaMun = municipios.keySet().toArray();
   double totcortEjecBProd = 0;
   double totcortPermBProd = 0;
   double totcortEjecBProt = 0;
   double totcortPermBProt = 0;

   for(int i=0; i<listaMun.length; i++)
   {
    mun = listaMun[i].toString();
    HashMap<String, Obj_IRS> entidades = municipios.get(mun);
    Object [] listaEntidades = entidades.keySet().toArray();

        for(int k=0; k<listaEntidades.length; k++)
        {
            double cortEjecBProd = entidades.get(listaEntidades[k].toString()).getCortEjecBProd();
            double cortPermBProd = entidades.get(listaEntidades[k].toString()).getCortPermBProd();
            double cortEjecBProt = entidades.get(listaEntidades[k].toString()).getCortEjecBProt();
            double cortPermBProt = entidades.get(listaEntidades[k].toString()).getCortPermBProt();

           double IRSBProd = 0;
           double IRSBProt = 0;
           double IRSBTotal = 0;

           if(cortPermBProd != 0)
            IRSBProd = (cortEjecBProd/cortPermBProd)*100;

           if(cortPermBProt != 0)
            IRSBProt = (cortEjecBProt/cortPermBProt)*100;

           if(cortPermBProd!=0 || cortPermBProt!=0)
            IRSBTotal = ((cortEjecBProd+cortEjecBProt)/(cortPermBProd+cortPermBProt))*100;

            deftm.addRow(new String []{"", mun, listaEntidades[k].toString(), Redondear(IRSBProd).toString(),
                                       Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});
            totcortEjecBProd += cortEjecBProd;
            totcortPermBProd += cortPermBProd;
            totcortEjecBProt += cortEjecBProt;
            totcortPermBProt += cortPermBProt;
        }
   }

    double IRSBProd = 0;
    double IRSBProt = 0;
    double IRSBTotal = 0;

    if(totcortPermBProd != 0)
     IRSBProd = (totcortEjecBProd/totcortPermBProd)*100;

    if(totcortPermBProt != 0)
     IRSBProt = (totcortEjecBProt/totcortPermBProt)*100;

    if(totcortPermBProd!=0 || totcortPermBProt!=0)
     IRSBTotal = ((totcortEjecBProd+totcortEjecBProt)/(totcortPermBProd+totcortPermBProt))*100;
    
    row = new String []{"", "TOTAL", "", Redondear(IRSBProd).toString(),
                        Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()};

   deftm.addRow(row);

   return deftm;
}

@Override
protected TableModel getModel_NAC()
{
   HashMap<String, Obj_IRS> hashValues = new HashMap<String, Obj_IRS>();
   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROD2_COLUMN_NAME,
                                      CONSTANTS.IRS_BOSQ_PROT2_COLUMN_NAME, CONSTANTS.IRS_TOTAl2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = getSQLReplacement(ReportQuerys.SQL_4_1_PROV_SubGrupoUS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_4_1_PROV_SubGrupoAP, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

   sql = getSQLReplacement(ReportQuerys.SQL_4_1_PROV_SubGrupoOTROS, anno.toString());
   db.executeQuery(sql);
   if(!db.isEmpty())
    updateProvincia_HASH(hashValues);

  //Agregando filas
   Object [] lista = hashValues.keySet().toArray() ;
   double totcortEjecBProd = 0;
   double totcortPermBProd = 0;
   double totcortEjecBProt = 0;
   double totcortPermBProt = 0;

   for(int i=0; i<lista.length; i++)
   {
       double cortEjecBProd = hashValues.get(lista[i].toString()).getCortEjecBProd();
       double cortPermBProd = hashValues.get(lista[i].toString()).getCortPermBProd();
       double cortEjecBProt = hashValues.get(lista[i].toString()).getCortEjecBProt();
       double cortPermBProt = hashValues.get(lista[i].toString()).getCortPermBProt();

       double IRSBProd = 0;
       double IRSBProt = 0;
       double IRSBTotal = 0;

       if(cortPermBProd != 0)
        IRSBProd = (cortEjecBProd/cortPermBProd)*100;

       if(cortPermBProt != 0)
        IRSBProt = (cortEjecBProt/cortPermBProt)*100;

       if(cortPermBProd!=0 || cortPermBProt!=0)
        IRSBTotal = ((cortEjecBProd+cortEjecBProt)/(cortPermBProd+cortPermBProt))*100;

        deftm.addRow(new String []{lista[i].toString(), Redondear(IRSBProd).toString(),
                                   Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});
        totcortEjecBProd += cortEjecBProd;
        totcortPermBProd += cortPermBProd;
        totcortEjecBProt += cortEjecBProt;
        totcortPermBProt += cortPermBProt;
   }

    double IRSBProd = 0;
    double IRSBProt = 0;
    double IRSBTotal = 0;

    if(totcortPermBProd != 0)
     IRSBProd = (totcortEjecBProd/totcortPermBProd)*100;

    if(totcortPermBProt != 0)
     IRSBProt = (totcortEjecBProt/totcortPermBProt)*100;

    if(totcortPermBProd!=0 || totcortPermBProt!=0)
     IRSBTotal = ((totcortEjecBProd+totcortEjecBProt)/(totcortPermBProd+totcortPermBProt))*100;

   deftm.addRow(new String []{"TOTAL", Redondear(IRSBProd).toString(),
                              Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});

   return deftm;
}

@Override
protected TableModel getModel_NAC_DESGLOSE()
{
  String tipoEntidad = "";

 //Provincia, [ Municipio, [ Entidad, Obj_Tac_SCP] ]                                   prov            mun             ent
   HashMap<String, HashMap<String, HashMap<String, Obj_IRS>>> provincias = new HashMap<String, HashMap<String, HashMap<String, Obj_IRS>>>();

   Object []columNames = new String[]{CONSTANTS.PROVINCIAS_COLUMN_NAME, CONSTANTS.MUNICIPIOS_COLUMN_NAME, CONSTANTS.TIPOENTIDAD2_COLUMN_NAME,
                                     CONSTANTS.IRS_BOSQ_PROD2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROT2_COLUMN_NAME, CONSTANTS.IRS_TOTAl2_COLUMN_NAME};
   DefaultTableModel deftm = new DefaultTableModel(columNames, 0);

   String sql = ReportQuerys.SQL_4_1_PROV_SubGrupoUS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_UNIDADES_SILVICOLAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_4_1_PROV_SubGrupoAP.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_AREAS_PROTEGIDAS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

   sql = ReportQuerys.SQL_4_1_PROV_SubGrupoOTROS.replaceAll("provincias.nombre", "provincias.nombre, municipios.nombre");
   sql = getSQLReplacement(sql, anno.toString());
   db.executeQuery(sql);
   tipoEntidad = CONSTANTS.ENTIDAD_OTROS;
   if(!db.isEmpty())
    updateNacional_Desglose_HASH(provincias, tipoEntidad);

  //Agregando filas
   Object [] listaProvincias = provincias.keySet().toArray();
   double subtotcortEjecBProd = 0;
   double subtotcortPermBProd = 0;
   double subtotcortEjecBProt = 0;
   double subtotcortPermBProt = 0;

   double totcortEjecBProd = 0;
   double totcortPermBProd = 0;
   double totcortEjecBProt = 0;
   double totcortPermBProt = 0;
   String prov = "";
   String mun = "";
   for(int i=0; i<listaProvincias.length; i++) //Provincias
   {
    prov = listaProvincias[i].toString();
    HashMap<String, HashMap<String, Obj_IRS>> municipios = provincias.get(prov);
    Object [] listaMunicipios = municipios.keySet().toArray();

        for(int j=0; j<listaMunicipios.length; j++)   //Municipios
        {
         mun = listaMunicipios[j].toString();
         HashMap<String, Obj_IRS> entidades = municipios.get(mun);
         Object [] listaEntidades = entidades.keySet().toArray();

            for(int k=0; k<listaEntidades.length; k++)  //Entidades
            {
                double cortEjecBProd = entidades.get(listaEntidades[k].toString()).getCortEjecBProd();
                double cortPermBProd = entidades.get(listaEntidades[k].toString()).getCortPermBProd();
                double cortEjecBProt = entidades.get(listaEntidades[k].toString()).getCortEjecBProt();
                double cortPermBProt = entidades.get(listaEntidades[k].toString()).getCortPermBProt();

               double IRSBProd = 0;
               double IRSBProt = 0;
               double IRSBTotal = 0;

               if(cortPermBProd != 0)
                IRSBProd = (cortEjecBProd/cortPermBProd)*100;

               if(cortPermBProt != 0)
                IRSBProt = (cortEjecBProt/cortPermBProt)*100;

               if(cortPermBProd!=0 || cortPermBProt!=0)
                IRSBTotal = ((cortEjecBProd+cortEjecBProt)/(cortPermBProd+cortPermBProt))*100;

                deftm.addRow(new String []{prov, mun, listaEntidades[k].toString(), Redondear(IRSBProd).toString(),
                                           Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});
                subtotcortEjecBProd += cortEjecBProd;
                subtotcortPermBProd += cortPermBProd;
                subtotcortEjecBProt += cortEjecBProt;
                subtotcortPermBProt += cortPermBProt;
            }

            double IRSBProd = 0;
            double IRSBProt = 0;
            double IRSBTotal = 0;

            if(subtotcortPermBProd != 0)
             IRSBProd = (subtotcortEjecBProd/subtotcortPermBProd)*100;

            if(subtotcortPermBProt != 0)
             IRSBProt = (subtotcortEjecBProt/subtotcortPermBProt)*100;

            if(subtotcortPermBProd!=0 || subtotcortPermBProt!=0)
             IRSBTotal = ((subtotcortEjecBProd+subtotcortEjecBProt)/(subtotcortPermBProd+subtotcortPermBProt))*100;

            deftm.addRow(new String []{"", "Sub-Total", "", Redondear(IRSBProd).toString(),
                                       Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});
            totcortEjecBProd += subtotcortEjecBProd;
            totcortPermBProd += subtotcortPermBProd;
            totcortEjecBProt += subtotcortEjecBProt;
            totcortPermBProt += subtotcortPermBProt;

            subtotcortEjecBProd = 0;
            subtotcortPermBProd = 0;
            subtotcortEjecBProt = 0;
            subtotcortPermBProt = 0;
        }
   }

    double IRSBProd = 0;
    double IRSBProt = 0;
    double IRSBTotal = 0;

    if(totcortPermBProd != 0)
     IRSBProd = (totcortEjecBProd/totcortPermBProd)*100;

    if(totcortPermBProt != 0)
     IRSBProt = (totcortEjecBProt/totcortPermBProt)*100;

    if(totcortPermBProd!=0 || totcortPermBProt!=0)
     IRSBTotal = ((totcortEjecBProd+totcortEjecBProt)/(totcortPermBProd+totcortPermBProt))*100;

    deftm.addRow(new String []{"TOTAL", "", "", Redondear(IRSBProd).toString(),
                               Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});

   return deftm;
}

@Override
protected TableModel getModel_MUN_Especific_Entity(BaseUnits bu)
{
   double cortEjecBProd = 0;
   double cortPermBProd = 0;
   double cortEjecBProt = 0;
   double cortPermBProt = 0;

   String entidad = "";
   Object []columNames = null;
   DefaultTableModel deftm = null;
   String sql = "";

   double totcortEjecBProd = 0;
   double totcortPermBProd = 0;
   double totcortEjecBProt = 0;
   double totcortPermBProt = 0;

   if(bu.equals(BaseUnits.Unidad_Silvicola))
   {
    columNames = new String[]{CONSTANTS.US2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROD2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROT2_COLUMN_NAME,
                              CONSTANTS.IRS_TOTAl2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_SubGrupoUS_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Area_Protegida))
   {
    columNames = new String[]{CONSTANTS.AP2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROD2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROT2_COLUMN_NAME,
                              CONSTANTS.IRS_TOTAl2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_SubGrupoAP_Entities, user.getIdEntidad(), anno.toString());
   }
   else
   if(bu.equals(BaseUnits.Otros))
   {
    columNames = new String[]{CONSTANTS.OTROS_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROD2_COLUMN_NAME, CONSTANTS.IRS_BOSQ_PROT2_COLUMN_NAME,
                              CONSTANTS.IRS_TOTAl2_COLUMN_NAME};
    sql = getSQLReplacement(ReportQuerys.SQL_4_1_MUN_SubGrupoOTROS_Entities, user.getIdEntidad(), anno.toString());
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

           cortEjecBProd = Double.parseDouble(db.getValueAt(i,1).toString());
           cortPermBProd = Double.parseDouble(db.getValueAt(i,2).toString());
           cortEjecBProt = Double.parseDouble(db.getValueAt(i,3).toString());
           cortPermBProt = Double.parseDouble(db.getValueAt(i,4).toString());

           double IRSBProd = 0;
           double IRSBProt = 0;
           double IRSBTotal = 0;

           if(cortPermBProd != 0)
            IRSBProd = (cortEjecBProd/cortPermBProd)*100;

           if(cortPermBProt != 0)
            IRSBProt = (cortEjecBProt/cortPermBProt)*100;

           if(cortPermBProd!=0 || cortPermBProt!=0)
            IRSBTotal = ((cortEjecBProd+cortEjecBProt)/(cortPermBProd+cortPermBProt))*100;

            deftm.addRow(new String []{entidad, Redondear(IRSBProd).toString(),
                                       Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});
            totcortEjecBProd += cortEjecBProd;
            totcortPermBProd += cortPermBProd;
            totcortEjecBProt += cortEjecBProt;
            totcortPermBProt += cortPermBProt;
         }

        double IRSBProd = 0;
        double IRSBProt = 0;
        double IRSBTotal = 0;

        if(totcortPermBProd != 0)
         IRSBProd = (totcortEjecBProd/totcortPermBProd)*100;

        if(totcortPermBProt != 0)
         IRSBProt = (totcortEjecBProt/totcortPermBProt)*100;

        if(totcortPermBProd!=0 || totcortPermBProt!=0)
         IRSBTotal = ((totcortEjecBProd+totcortEjecBProt)/(totcortPermBProd+totcortPermBProt))*100;

        deftm.addRow(new String []{"TOTAL", Redondear(IRSBProd).toString(),
                                   Redondear(IRSBProt).toString(), Redondear(IRSBTotal).toString()});
    }

   return deftm;
}

private void updateProvincia_HASH(HashMap<String, Obj_IRS> hashValues)
{
     String Ent = "";  //Puede ser municipio o provincia depende del nivel de utilizacion
     double cortEjecBProd = 0;
     double cortPermBProd = 0;
     double cortEjecBProt = 0;
     double cortPermBProt = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       cortEjecBProd = Double.parseDouble(db.getValueAt(i,0).toString());
       cortPermBProd = Double.parseDouble(db.getValueAt(i,1).toString());
       cortEjecBProt = Double.parseDouble(db.getValueAt(i,2).toString());
       cortPermBProt = Double.parseDouble(db.getValueAt(i,3).toString());
       Ent = db.getValueAt(i,4).toString();

       if(hashValues.containsKey(Ent))
       {
        cortEjecBProd = cortEjecBProd + hashValues.get(Ent).getCortEjecBProd();
        cortPermBProd = cortPermBProd + hashValues.get(Ent).getCortPermBProd();
        cortEjecBProt = cortEjecBProt + hashValues.get(Ent).getCortEjecBProt();
        cortPermBProt = cortPermBProt + hashValues.get(Ent).getCortPermBProt();
       }

       hashValues.put(Ent, new Obj_IRS(cortEjecBProd, cortPermBProd, cortEjecBProt, cortPermBProt));
     }
}

private void updateProvincia_Desglose_HASH(HashMap<String, HashMap<String, Obj_IRS>> municipios, String tipoEntidad)
{
     String mun = "";
     double cortEjecBProd = 0;
     double cortPermBProd = 0;
     double cortEjecBProt = 0;
     double cortPermBProt = 0;
     
     for(int i=0; i<db.getRowCount(); i++)
     {
       cortEjecBProd = Double.parseDouble(db.getValueAt(i,0).toString());
       cortPermBProd = Double.parseDouble(db.getValueAt(i,1).toString());
       cortEjecBProt = Double.parseDouble(db.getValueAt(i,2).toString());
       cortPermBProt = Double.parseDouble(db.getValueAt(i,3).toString());
       mun = db.getValueAt(i,4).toString();

       if(municipios.containsKey(mun))
       {
            if(municipios.get(mun).containsKey(tipoEntidad))
            {
                cortEjecBProd = cortEjecBProd + municipios.get(mun).get(tipoEntidad).getCortEjecBProd();
                cortPermBProd = cortPermBProd + municipios.get(mun).get(tipoEntidad).getCortPermBProd();
                cortEjecBProt = cortEjecBProt + municipios.get(mun).get(tipoEntidad).getCortEjecBProt();
                cortPermBProt = cortPermBProt + municipios.get(mun).get(tipoEntidad).getCortPermBProt();
            }

            municipios.get(mun).put(tipoEntidad, new Obj_IRS(cortEjecBProd, cortPermBProd, cortEjecBProt, cortPermBProt));
       }
       else
       {
        HashMap<String, Obj_IRS> entidad = new HashMap<String, Obj_IRS>();
        entidad.put(tipoEntidad, new Obj_IRS(cortEjecBProd, cortPermBProd, cortEjecBProt, cortPermBProt));
        municipios.put(mun, entidad);
       }
     }
}

private void updateNacional_Desglose_HASH(HashMap<String, HashMap<String, HashMap<String, Obj_IRS>>> provincias, String tipoEntidad)
{
     String prov = "";
     String mun = "";
     double cortEjecBProd = 0;
     double cortPermBProd = 0;
     double cortEjecBProt = 0;
     double cortPermBProt = 0;

     for(int i=0; i<db.getRowCount(); i++)
     {
       cortEjecBProd = Double.parseDouble(db.getValueAt(i,0).toString());
       cortPermBProd = Double.parseDouble(db.getValueAt(i,1).toString());
       cortEjecBProt = Double.parseDouble(db.getValueAt(i,2).toString());
       cortPermBProt = Double.parseDouble(db.getValueAt(i,3).toString());
       prov = db.getValueAt(i,4).toString();
       mun = db.getValueAt(i,5).toString();

       if(provincias.containsKey(prov)) //Si ya esta la provincia
       {
            if(provincias.get(prov).containsKey(mun))  //Si en esa provincia esta este municipio
            {
                if(provincias.get(prov).get(mun).containsKey(tipoEntidad))  //Si en ese municipio esta esa entidad
                {
                    cortEjecBProd = cortEjecBProd + provincias.get(prov).get(mun).get(tipoEntidad).getCortEjecBProd();
                    cortPermBProd = cortPermBProd + provincias.get(prov).get(mun).get(tipoEntidad).getCortPermBProd();
                    cortEjecBProt = cortEjecBProt + provincias.get(prov).get(mun).get(tipoEntidad).getCortEjecBProt();
                    cortPermBProt = cortPermBProt + provincias.get(prov).get(mun).get(tipoEntidad).getCortPermBProt();
                }

                provincias.get(prov).get(mun).put(tipoEntidad, new Obj_IRS(cortEjecBProd, cortPermBProd, cortEjecBProt, cortPermBProt));
            }
            else
            {
                HashMap<String, Obj_IRS> entidad = new HashMap<String, Obj_IRS>();
                entidad.put(tipoEntidad, new Obj_IRS(cortEjecBProd, cortPermBProd, cortEjecBProt, cortPermBProt));
                provincias.get(prov).put(mun, entidad);
            }
       }
       else
       {
        HashMap<String, Obj_IRS> entidad = new HashMap<String, Obj_IRS>();
        entidad.put(tipoEntidad, new Obj_IRS(cortEjecBProd, cortPermBProd, cortEjecBProt, cortPermBProt));
        HashMap<String, HashMap<String, Obj_IRS>> municipio = new HashMap<String, HashMap<String, Obj_IRS>>();
        municipio.put(mun, entidad);
        provincias.put(prov, municipio);
       }
     }
}
}