package cif.reportes;

/**
 *
 * @author Raisel
 */
public class ReportQuerys
{
 //====================== Criterio 1.1 TotalAreaCubierta =====================================================================================================================================================================
 //----------------------------------------------------------------------------------
     public static final String SQL_1_1_US                                 = "select municipios.nombre as municipio, anno as Año, bosques_nat as "+CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME+", " +
                                                                             "plantacion_estb as "+CONSTANTS.PLANTACIONES_COLUMN_NAME+", (bosques_nat+plantacion_estb) as "+CONSTANTS.TOTAL_COLUMN_NAME+
                                                                             " from c1_1_us_totalareacubierta " +
                                                                             "inner join municipios on c1_1_us_totalareacubierta.municipio=municipios.id where c1_1_us_totalareacubierta.id=':id' " +
                                                                             "and anno=:anno" ;
 //----------------------------------------------------------------------------------
     public static final String SQL_1_1_AP                                 = "select municipios.nombre as municipio, anno as Año, bosques_nat as "+CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME+", " +
                                                                             "plantacion_estb as "+CONSTANTS.PLANTACIONES_COLUMN_NAME+", (bosques_nat+plantacion_estb) as "+CONSTANTS.TOTAL_COLUMN_NAME+
                                                                             " from c1_1_ap_totalareacubierta inner join municipios " +
                                                                             "on c1_1_ap_totalareacubierta.municipio=municipios.id " +
                                                                             "where c1_1_ap_totalareacubierta.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
     public static final String SQL_1_1_EFI_Subgrupo                       = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                             "sum(bosques_nat) as "+CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME+", sum(plantacion_estb) as "+CONSTANTS.PLANTACIONES_COLUMN_NAME+", " +
                                                                             "(sum(bosques_nat)+ sum(plantacion_estb)) as "+CONSTANTS.TOTAL_COLUMN_NAME+
                                                                             " from ((c1_1_us_totalareacubierta inner join usilvicola on c1_1_us_totalareacubierta.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id)inner join municipios on c1_1_us_totalareacubierta.municipio=municipios.id " +
                                                                             "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre" ;

     public static final String SQL_1_1_EFI_TOTAL                          = "select efi.nombre as efi, sum(bosques_nat) as "+CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME+", sum(plantacion_estb) as "+CONSTANTS.PLANTACIONES_COLUMN_NAME+" " +
                                                                             ", (sum(bosques_nat)+ sum(plantacion_estb)) as "+CONSTANTS.TOTAL_COLUMN_NAME+
                                                                             " from (c1_1_us_totalareacubierta inner join usilvicola on c1_1_us_totalareacubierta.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id " +
                                                                             "where efi.id=':id' and anno=:anno group by efi.nombre" ;
////----------------------------------------------------------------------------------
//     public static final String SQL_1_1_OTROS                             = "select municipios.nombre as municipio, anno as Año, bosques_nat, plantacion_estb from c1_1_otros_totalareacubierta inner join municipios " +
//                                                                             "on c1_1_otros_totalareacubierta.municipio=municipios.id " +
//                                                                             "where c1_1_otros_totalareacubierta.id=':_id'" ;
//----------------------------------------------------------------------------------
 public static final String SQL_1_1_MUN_SubGrupoUS                         = "select provincias.nombre, municipios.nombre as municipio, sum(c1_1_us_totalareacubierta.bosques_nat), " +
                                                                             "sum(c1_1_us_totalareacubierta.plantacion_estb), (sum(c1_1_us_totalareacubierta.bosques_nat)+sum(c1_1_us_totalareacubierta.plantacion_estb)) " +
                                                                             "from (c1_1_us_totalareacubierta inner join municipios on " +
                                                                             "c1_1_us_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre;" ;

 public static final String SQL_1_1_MUN_SubGrupoAP                         = "select provincias.nombre, municipios.nombre as municipio, sum(c1_1_ap_totalareacubierta.bosques_nat), " +
                                                                             "sum(c1_1_ap_totalareacubierta.plantacion_estb), (sum(c1_1_ap_totalareacubierta.bosques_nat)+sum(c1_1_ap_totalareacubierta.plantacion_estb)) " +
                                                                             "from (c1_1_ap_totalareacubierta inner join municipios on " +
                                                                             "c1_1_ap_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre;" ;

 public static final String SQL_1_1_MUN_SubGrupoOTROS                     = "select provincias.nombre, municipios.nombre as municipio, sum(c1_1_otros_totalareacubierta.bosques_nat), " +
                                                                             "sum(c1_1_otros_totalareacubierta.plantacion_estb), (sum(c1_1_otros_totalareacubierta.bosques_nat)+sum(c1_1_otros_totalareacubierta.plantacion_estb)) " +
                                                                             "from (c1_1_otros_totalareacubierta inner join municipios on " +
                                                                             "c1_1_otros_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre;" ;
//----------------------------------------------------------------------------------
 public static final String SQL_1_1_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, c1_1_us_totalareacubierta.bosques_nat, " +
                                                                             "c1_1_us_totalareacubierta.plantacion_estb, (bosques_nat+plantacion_estb) from ((c1_1_us_totalareacubierta inner join municipios on " +
                                                                             "c1_1_us_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c1_1_us_totalareacubierta.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno;";

 public static final String SQL_1_1_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, c1_1_ap_totalareacubierta.bosques_nat, " +
                                                                             "c1_1_ap_totalareacubierta.plantacion_estb, (bosques_nat+plantacion_estb) from ((c1_1_ap_totalareacubierta inner join municipios on " +
                                                                             "c1_1_ap_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c1_1_ap_totalareacubierta.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno;";

 public static final String SQL_1_1_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, c1_1_otros_totalareacubierta.bosques_nat, " +
                                                                             "c1_1_otros_totalareacubierta.plantacion_estb, (bosques_nat+plantacion_estb) from ((c1_1_otros_totalareacubierta inner join municipios on " +
                                                                             "c1_1_otros_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c1_1_otros_totalareacubierta.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno;";
 //----------------------------------------------------------------------------------
     public static final String SQL_1_1_PROV_SubGrupoUS                   = "select provincias.nombre, sum(c1_1_us_totalareacubierta.bosques_nat), " +
                                                                             "sum(c1_1_us_totalareacubierta.plantacion_estb), (sum(c1_1_us_totalareacubierta.bosques_nat)+sum(c1_1_us_totalareacubierta.plantacion_estb)) " +
                                                                             "from (c1_1_us_totalareacubierta inner join municipios on " +
                                                                             "c1_1_us_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where anno=:anno group by provincias.nombre;";

     public static final String SQL_1_1_PROV_SubGrupoAP                   = "select provincias.nombre, sum(c1_1_ap_totalareacubierta.bosques_nat), " +
                                                                             "sum(c1_1_ap_totalareacubierta.plantacion_estb), (sum(c1_1_ap_totalareacubierta.bosques_nat)+sum(c1_1_ap_totalareacubierta.plantacion_estb)) " +
                                                                             "from (c1_1_ap_totalareacubierta inner join municipios on " +
                                                                             "c1_1_ap_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where anno=:anno group by provincias.nombre;";

   public static final String SQL_1_1_PROV_SubGrupoOTROS                   = "select provincias.nombre, sum(c1_1_otros_totalareacubierta.bosques_nat), " +
                                                                             "sum(c1_1_otros_totalareacubierta.plantacion_estb), (sum(c1_1_otros_totalareacubierta.bosques_nat)+sum(c1_1_otros_totalareacubierta.plantacion_estb)) " +
                                                                             "from (c1_1_otros_totalareacubierta inner join municipios on " +
                                                                             "c1_1_otros_totalareacubierta.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where anno=:anno group by provincias.nombre;";
   //...................................... SQL para gráficos ........................................................................................................................................................................................
    //......................................................................................................................
     public static final String G_SQL_1_1_AP                               = "select bosques_nat as "+CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME+", plantacion_estb as "+CONSTANTS.PLANTACIONES_COLUMN_NAME+
                                                                             ", (bosques_nat+plantacion_estb) as "+CONSTANTS.TOTAL_COLUMN_NAME+", anno " +
                                                                             "from c1_1_ap_totalareacubierta "+
                                                                             "where c1_1_ap_totalareacubierta.id=':id' and anno in(:anno) order by c1_1_ap_totalareacubierta.anno";

     public static final String G_SQL_1_1_AP_YEARS                         = "select distinct anno from c1_1_ap_totalareacubierta order by anno";
    //......................................................................................................................
     public static final String G_SQL_1_1_US                               = "select bosques_nat as "+CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME+", plantacion_estb as "+CONSTANTS.PLANTACIONES_COLUMN_NAME+//", anno " +
                                                                             ", (bosques_nat+plantacion_estb) as "+CONSTANTS.TOTAL_COLUMN_NAME+", anno " +
                                                                             "from c1_1_us_totalareacubierta "+
                                                                             "where c1_1_us_totalareacubierta.id=':id' and anno in(:anno) order by c1_1_us_totalareacubierta.anno";

     public static final String G_SQL_1_1_US_YEARS                         = "select distinct anno from c1_1_us_totalareacubierta order by anno";

    //......................................................................................................................
     public static final String G_SQL_1_1_OTROS_YEARS                      = "select distinct anno from c1_1_otros_totalareacubierta order by anno";
     
    //......................................................................................................................
     public static final String G_SQL_1_1_EFI                              = "select sum(bosques_nat) as "+CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME+", sum(plantacion_estb) as "+CONSTANTS.PLANTACIONES_COLUMN_NAME+//", anno " +
                                                                             ", sum(bosques_nat+plantacion_estb) as "+CONSTANTS.TOTAL_COLUMN_NAME+", anno " +
                                                                             "from c1_1_us_totalareacubierta inner join usilvicola on c1_1_us_totalareacubierta.id=usilvicola.id "+
                                                                             "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                             "group by usilvicola.efi, anno order by c1_1_us_totalareacubierta.anno";

     public static final String G_SQL_1_1_EFI_YEARS                        = "select distinct anno from c1_1_us_totalareacubierta order by anno";
         //......................................................................................................................
     public static final String G_SQL_1_1_MUN_AP                           = "select sum(bosques_nat), sum(plantacion_estb), sum(bosques_nat+plantacion_estb), anno " +
                                                                             "from c1_1_ap_totalareacubierta inner join municipios on c1_1_ap_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.id=':id' and anno in(:anno) " +
                                                                             "group by municipios.id, anno order by c1_1_ap_totalareacubierta.anno";

     public static final String G_SQL_1_1_MUN_US                           = "select sum(bosques_nat), sum(plantacion_estb), sum(bosques_nat+plantacion_estb), anno " +
                                                                             "from c1_1_us_totalareacubierta inner join municipios on c1_1_us_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.id=':id' and anno in(:anno) " +
                                                                             "group by municipios.id, anno order by c1_1_us_totalareacubierta.anno";

     public static final String G_SQL_1_1_MUN_OTROS                        = "select sum(bosques_nat), sum(plantacion_estb), sum(bosques_nat+plantacion_estb), anno " +
                                                                             "from c1_1_otros_totalareacubierta inner join municipios on c1_1_otros_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.id=':id' and anno in(:anno) " +
                                                                             "group by municipios.id, anno order by c1_1_otros_totalareacubierta.anno";

     public static final String G_SQL_1_1_MUN_YEARS_AP                    = "select distinct anno from c1_1_ap_totalareacubierta where municipio=':id' order by anno";

     public static final String G_SQL_1_1_MUN_YEARS_US                    = "select distinct anno from c1_1_us_totalareacubierta where municipio=':id' order by anno";

     public static final String G_SQL_1_1_MUN_YEARS_OTROS                 = "select distinct anno from c1_1_otros_totalareacubierta where municipio=':id' order by anno";
  //......................................................................................................................
     public static final String G_SQL_1_1_PROV_AP                          = "select sum(bosques_nat), sum(plantacion_estb), sum(bosques_nat+plantacion_estb), anno " +
                                                                             "from c1_1_ap_totalareacubierta inner join municipios on c1_1_ap_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.provincia=':id' and anno in(:anno) " +
                                                                             "group by municipios.provincia, anno order by c1_1_ap_totalareacubierta.anno";

     public static final String G_SQL_1_1_PROV_US                           = "select sum(bosques_nat), sum(plantacion_estb), sum(bosques_nat+plantacion_estb), anno " +
                                                                             "from c1_1_us_totalareacubierta inner join municipios on c1_1_us_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.provincia=':id' and anno in(:anno) " +
                                                                             "group by municipios.provincia, anno order by c1_1_us_totalareacubierta.anno";

     public static final String G_SQL_1_1_PROV_OTROS                        = "select sum(bosques_nat), sum(plantacion_estb), sum(bosques_nat+plantacion_estb), anno " +
                                                                             "from c1_1_otros_totalareacubierta inner join municipios on c1_1_otros_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.provincia=':id' and anno in(:anno) " +
                                                                             "group by municipios.provincia, anno order by c1_1_otros_totalareacubierta.anno";

     public static final String G_SQL_1_1_PROV_YEARS_AP                    = "select distinct anno from c1_1_ap_totalareacubierta inner join municipios on c1_1_ap_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.provincia=':id' order by anno";

     public static final String G_SQL_1_1_PROV_YEARS_US                    = "select distinct anno from c1_1_us_totalareacubierta inner join municipios on c1_1_us_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.provincia=':id' order by anno";

     public static final String G_SQL_1_1_PROV_YEARS_OTROS                 = "select distinct anno from c1_1_otros_totalareacubierta inner join municipios on c1_1_otros_totalareacubierta.municipio=municipios.id "+
                                                                             "where municipios.provincia=':id' order by anno";
         //......................................................................................................................

     public static final String G_SQL_1_1_Temp_Table                       = "select bosques_nat as "+CONSTANTS.BOSQUES_NATURALES_COLUMN_NAME+", plantacion_estb as "+CONSTANTS.PLANTACIONES_COLUMN_NAME+", " +
                                                                             "total as "+CONSTANTS.TOTAL_COLUMN_NAME+", anno " +
                                                                             "from c_1_1_graphicdata order by anno";
    
//====================== Criterio 1.2 Indice de Boscosidad =====================================================================================================================================================================
  //----------------------------------------------------------------------------------
   public static final String SQL_1_2_AP                                   = "select municipios.nombre as municipio, c1_2_ap_indiceboscosidad.anno as Año, ((bosques_nat+plantacion_estb)/superficie_geog)*100 as "+CONSTANTS.IND_BOSC_COLUMN_NAME+
                                                                             " from (c1_2_ap_indiceboscosidad inner join municipios " +
                                                                             "on c1_2_ap_indiceboscosidad.municipio=municipios.id) inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where c1_2_ap_indiceboscosidad.id=':id' and c1_2_ap_indiceboscosidad.anno=:anno";
  //----------------------------------------------------------------------------------
//   public static final String SQL_1_2_US                                   = "select municipios.nombre as municipio, anno as Año, superficie_geog from c1_2_us_indiceboscosidad inner join municipios " +
//                                                                             "on c1_2_us_indiceboscosidad.municipio=municipios.id " +
//                                                                             "where c1_2_us_indiceboscosidad.id=':id' and anno=:anno" ;
   
   public static final String SQL_1_2_US                                   = "select municipios.nombre as municipio, c1_2_us_indiceboscosidad.anno as Año, ((bosques_nat+plantacion_estb)/superficie_geog)*100 as "+CONSTANTS.IND_BOSC_COLUMN_NAME+
                                                                             " from (c1_2_us_indiceboscosidad inner join municipios " +
                                                                             "on c1_2_us_indiceboscosidad.municipio=municipios.id) inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where c1_2_us_indiceboscosidad.id=':id' and c1_2_us_indiceboscosidad.anno=:anno" ;
  //----------------------------------------------------------------------------------
   public static final String SQL_1_2_EFI_Subgrupo                         = "select municipios.nombre as municipio, c1_2_us_indiceboscosidad.anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                             "((sum(bosques_nat)+sum(plantacion_estb))/sum(superficie_geog))*100 as "+CONSTANTS.IND_BOSC_COLUMN_NAME+ " " +
                                                                             "from (((c1_2_us_indiceboscosidad inner join usilvicola on c1_2_us_indiceboscosidad.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id)inner join municipios on c1_2_us_indiceboscosidad.municipio=municipios.id)" +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where efi.id=':id' and c1_2_us_indiceboscosidad.anno=:anno group by municipios.nombre, c1_2_us_indiceboscosidad.anno, efi.nombre, usilvicola.nombre" ;

   public static final String SQL_1_2_EFI_TOTAL                            = "select efi.nombre as efi, ((sum(bosques_nat)+sum(plantacion_estb))/sum(superficie_geog))*100 from ((c1_2_us_indiceboscosidad " +
                                                                             "inner join usilvicola on c1_2_us_indiceboscosidad.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id)" +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where efi.id=':id' and c1_2_us_indiceboscosidad.anno=:anno group by efi.nombre" ;
  //----------------------------------------------------------------------------------
   public static final String SQL_1_2_MUN_SubGrupoUS                       = "select municipios.nombre as municipio, ((sum(bosques_nat)+sum(plantacion_estb))/sum(superficie_geog))*100 " +
                                                                             "from ((c1_2_us_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_us_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_us_indiceboscosidad.anno=:anno group by municipios.nombre;";

   public static final String SQL_1_2_MUN_SubGrupoAP                       = "select municipios.nombre as municipio, ((sum(bosques_nat)+sum(plantacion_estb))/sum(superficie_geog))*100 " +
                                                                             "from ((c1_2_ap_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_ap_indiceboscosidad.anno=:anno group by municipios.nombre;";

   public static final String SQL_1_2_MUN_SubGrupoOTROS                    = "select municipios.nombre as municipio, ((sum(bosques_nat)+sum(plantacion_estb))/sum(superficie_geog))*100 " +
                                                                             "from ((c1_2_otros_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_otros_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_otros_indiceboscosidad.anno=:anno group by municipios.nombre;";
   //----------------------------------------------------------------------------------
   public static final String SQL_1_2_MUN_US_SubValues                     = "select (sum(bosques_nat)+sum(plantacion_estb)) as tacUS, sum(superficie_geog) as sgAp, municipios.nombre as municipio " +
                                                                             "from ((c1_2_us_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_us_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_us_indiceboscosidad.anno=:anno group by municipios.nombre;";

   public static final String SQL_1_2_MUN_AP_SubValues                     = "select (sum(bosques_nat)+sum(plantacion_estb)) as tacAp, sum(superficie_geog) as sgAp, municipios.nombre as municipio " +
                                                                             "from ((c1_2_ap_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_ap_indiceboscosidad.anno=:anno group by municipios.nombre;";

   public static final String SQL_1_2_MUN_OTROS_SubValues                  = "select (sum(bosques_nat)+sum(plantacion_estb)) as tacOtros, sum(superficie_geog) as sgOtros, municipios.nombre as municipio " +
                                                                             "from ((c1_2_otros_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_otros_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_otros_indiceboscosidad.anno=:anno group by municipios.nombre;";
   //----------------------------------------------------------------------------------
 public static final String SQL_1_2_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, ((bosques_nat+plantacion_estb)/superficie_geog)*100 from (((c1_2_us_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_us_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c1_2_us_indiceboscosidad.id=usilvicola.id) inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_us_indiceboscosidad.anno=:anno;";

 public static final String SQL_1_2_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, ((bosques_nat+plantacion_estb)/superficie_geog)*100 from (((c1_2_ap_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c1_2_ap_indiceboscosidad.id=area_protegida.id) inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_ap_indiceboscosidad.anno=:anno;";

 public static final String SQL_1_2_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, ((bosques_nat+plantacion_estb)/superficie_geog)*100 from (((c1_2_otros_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_otros_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c1_2_otros_indiceboscosidad.id=otros.id) inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_2_otros_indiceboscosidad.anno=:anno;";
  //----------------------------------------------------------------------------------
   public static final String SQL_1_2_PROV_SubGrupoUS                      = "select (sum(bosques_nat)+sum(plantacion_estb)) as tacUS, sum(superficie_geog) as sgAp, provincias.nombre " +
                                                                             "from ((c1_2_us_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_us_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where c1_2_us_indiceboscosidad.anno=:anno group by provincias.nombre;";

   public static final String SQL_1_2_PROV_SubGrupoAP                      = "select (sum(bosques_nat)+sum(plantacion_estb)) as tacAp, sum(superficie_geog) as sgAp, provincias.nombre " +
                                                                             "from ((c1_2_ap_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where c1_2_ap_indiceboscosidad.anno=:anno group by provincias.nombre;";

   public static final String SQL_1_2_PROV_SubGrupoOTROS                   = "select (sum(bosques_nat)+sum(plantacion_estb)) as tacOtros, sum(superficie_geog) as sgOtros, provincias.nombre " +
                                                                             "from ((c1_2_otros_indiceboscosidad inner join municipios on " +
                                                                             "c1_2_otros_indiceboscosidad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where c1_2_otros_indiceboscosidad.anno=:anno group by provincias.nombre;";
  //...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_1_2_AP                               = "select case when superficie_geog<>0 then ((bosques_nat+plantacion_estb)/superficie_geog)*100 else 0 end as  "+CONSTANTS.IND_BOSC_COLUMN_NAME+", c1_2_ap_indiceboscosidad.anno " +
                                                                           "from c1_2_ap_indiceboscosidad inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno)"+
                                                                           "where c1_2_ap_indiceboscosidad.id=':id' and c1_2_ap_indiceboscosidad.anno in(:anno) order by c1_2_ap_indiceboscosidad.anno";

   public static final String G_SQL_1_2_AP_YEARS                         = "select distinct c1_2_ap_indiceboscosidad.anno from c1_2_ap_indiceboscosidad inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) order by c1_2_ap_indiceboscosidad.anno";
 //......................................................................................................................
   public static final String G_SQL_1_2_US                               = "select case when superficie_geog<>0 then ((bosques_nat+plantacion_estb)/superficie_geog)*100 else 0 end as  "+CONSTANTS.IND_BOSC_COLUMN_NAME+", c1_2_us_indiceboscosidad.anno " +
                                                                           "from c1_2_us_indiceboscosidad inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno)"+
                                                                           "where c1_2_us_indiceboscosidad.id=':id' and c1_2_us_indiceboscosidad.anno in(:anno) order by c1_2_us_indiceboscosidad.anno";

   public static final String G_SQL_1_2_US_YEARS                         = "select distinct c1_2_us_indiceboscosidad.anno from c1_2_us_indiceboscosidad inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) order by c1_2_us_indiceboscosidad.anno";

  //......................................................................................................................
   public static final String G_SQL_1_2_OTROS_YEARS                      = "select distinct c1_2_otros_indiceboscosidad.anno from c1_2_otros_indiceboscosidad inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno) order by c1_2_otros_indiceboscosidad.anno";

  //......................................................................................................................
   public static final String G_SQL_1_2_EFI                              = "select case when sum(superficie_geog)<>0 then (sum(bosques_nat+plantacion_estb)/sum(superficie_geog))*100 else 0 end as  "+CONSTANTS.IND_BOSC_COLUMN_NAME+", c1_2_us_indiceboscosidad.anno " +
                                                                           "from (c1_2_us_indiceboscosidad inner join usilvicola on c1_2_us_indiceboscosidad.id=usilvicola.id) " +
                                                                           "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) "+
                                                                           "where usilvicola.efi=':id' and c1_2_us_indiceboscosidad.anno in(:anno) " +
                                                                           "group by usilvicola.efi, c1_2_us_indiceboscosidad.anno order by c1_2_us_indiceboscosidad.anno";

   public static final String G_SQL_1_2_EFI_YEARS                        = "select distinct c1_2_us_indiceboscosidad.anno from c1_2_us_indiceboscosidad inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) order by c1_2_us_indiceboscosidad.anno";
       //......................................................................................................................
   public static final String G_SQL_1_2_MUN_AP                           = "select sum(bosques_nat),sum(plantacion_estb),sum(superficie_geog), c1_2_ap_indiceboscosidad.anno " +
                                                                           "from (c1_2_ap_indiceboscosidad inner join municipios on c1_2_ap_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) "+
                                                                           "where municipios.id=':id' and c1_2_ap_indiceboscosidad.anno in(:anno) " +
                                                                           "group by municipios.id, c1_2_ap_indiceboscosidad.anno order by c1_2_ap_indiceboscosidad.anno";

   public static final String G_SQL_1_2_MUN_US                           = "select sum(bosques_nat),sum(plantacion_estb),sum(superficie_geog), c1_2_us_indiceboscosidad.anno " +
                                                                           "from (c1_2_us_indiceboscosidad inner join municipios on c1_2_us_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) "+
                                                                           "where municipios.id=':id' and c1_2_us_indiceboscosidad.anno in(:anno) " +
                                                                           "group by municipios.id, c1_2_us_indiceboscosidad.anno order by c1_2_us_indiceboscosidad.anno";

   public static final String G_SQL_1_2_MUN_OTROS                        = "select sum(bosques_nat),sum(plantacion_estb),sum(superficie_geog), c1_2_otros_indiceboscosidad.anno " +
                                                                           "from (c1_2_otros_indiceboscosidad inner join municipios on c1_2_otros_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno) "+
                                                                           "where municipios.id=':id' and c1_2_otros_indiceboscosidad.anno in(:anno) " +
                                                                           "group by municipios.id, c1_2_otros_indiceboscosidad.anno order by c1_2_otros_indiceboscosidad.anno";

   public static final String G_SQL_1_2_MUN_YEARS_AP                    = "select distinct c1_2_ap_indiceboscosidad.anno from c1_2_ap_indiceboscosidad inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                          "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                          "where c1_2_ap_indiceboscosidad.municipio=':id' order by c1_2_ap_indiceboscosidad.anno";

   public static final String G_SQL_1_2_MUN_YEARS_US                    = "select distinct c1_2_us_indiceboscosidad.anno from c1_2_us_indiceboscosidad inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                          "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) " +
                                                                          "where c1_2_us_indiceboscosidad.municipio=':id' order by c1_2_us_indiceboscosidad.anno";

   public static final String G_SQL_1_2_MUN_YEARS_OTROS                 = "select distinct c1_2_otros_indiceboscosidad.anno from c1_2_otros_indiceboscosidad inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                          "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                          "where c1_2_otros_indiceboscosidad.municipio=':id' order by c1_2_otros_indiceboscosidad.anno";
//......................................................................................................................
   public static final String G_SQL_1_2_PROV_AP                          = "select sum(bosques_nat),sum(plantacion_estb),sum(superficie_geog), and c1_2_ap_indiceboscosidad.anno " +
                                                                           "from (c1_2_ap_indiceboscosidad inner join municipios on c1_2_ap_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' and c1_2_ap_indiceboscosidad.anno in(:anno) " +
                                                                           "group by municipios.provincia, c1_2_ap_indiceboscosidad.anno order by c1_2_ap_indiceboscosidad.anno";

   public static final String G_SQL_1_2_PROV_US                           = "select sum(bosques_nat),sum(plantacion_estb),sum(superficie_geog), c1_2_us_indiceboscosidad.anno " +
                                                                           "from (c1_2_us_indiceboscosidad inner join municipios on c1_2_us_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' and c1_2_us_indiceboscosidad.anno in(:anno) " +
                                                                           "group by municipios.provincia, c1_2_us_indiceboscosidad.anno order by c1_2_us_indiceboscosidad.anno";

   public static final String G_SQL_1_2_PROV_OTROS                       = "select sum(bosques_nat),sum(plantacion_estb),sum(superficie_geog), c1_2_otros_indiceboscosidad.anno " +
                                                                           "from (c1_2_otros_indiceboscosidad inner join municipios on c1_2_otros_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' and c1_2_otros_indiceboscosidad.anno in(:anno) " +
                                                                           "group by municipios.provincia, c1_2_otros_indiceboscosidad.anno order by c1_2_otros_indiceboscosidad.anno";

   public static final String G_SQL_1_2_PROV_YEARS_AP                    = "select distinct c1_2_ap_indiceboscosidad.anno from (c1_2_ap_indiceboscosidad inner join municipios on c1_2_ap_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_ap_totalareacubierta on (c1_2_ap_indiceboscosidad.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_2_ap_indiceboscosidad.municipio=c1_1_ap_totalareacubierta.municipio and c1_2_ap_indiceboscosidad.anno=c1_1_ap_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' order by c1_2_ap_indiceboscosidad.anno";

   public static final String G_SQL_1_2_PROV_YEARS_US                    = "select distinct c1_2_us_indiceboscosidad.anno from (c1_2_us_indiceboscosidad inner join municipios on c1_2_us_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c1_2_us_indiceboscosidad.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_2_us_indiceboscosidad.municipio=c1_1_us_totalareacubierta.municipio and c1_2_us_indiceboscosidad.anno=c1_1_us_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' order by c1_2_us_indiceboscosidad.anno";

   public static final String G_SQL_1_2_PROV_YEARS_OTROS                 = "select distinct c1_2_otros_indiceboscosidad.anno from (c1_2_otros_indiceboscosidad inner join municipios on c1_2_otros_indiceboscosidad.municipio=municipios.id)" +
                                                                           "inner join c1_1_otros_totalareacubierta on (c1_2_otros_indiceboscosidad.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_2_otros_indiceboscosidad.municipio=c1_1_otros_totalareacubierta.municipio and c1_2_otros_indiceboscosidad.anno=c1_1_otros_totalareacubierta.anno)"+
                                                                           "where municipios.provincia=':id' order by c1_2_otros_indiceboscosidad.anno";
       //......................................................................................................................

   public static final String G_SQL_1_2_Temp_Table                       = "select case when superficie_geog<>0 then ((bosques_nat+plantacion_estb)/superficie_geog)*100 else 0 end as  "+CONSTANTS.IND_BOSC_COLUMN_NAME+", anno " +
                                                                           "from c_1_2_graphicdata order by anno";
   
 //====================== Criterio 1.3 Relacion TAC SCP =====================================================================================================================================================================
  //----------------------------------------------------------------------------------
   public static final String SQL_1_3_AP                                   = "select municipios.nombre as municipio, c1_3_ap_relaciontac_scp.anno as Año, " +
                                                                             "((bosques_nat+plantacion_estb)/(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote))*100 as "+CONSTANTS.RELAC_TAC_SCP_COLUMN_NAME+
                                                                             " from (c1_3_ap_relaciontac_scp inner join municipios on c1_3_ap_relaciontac_scp.municipio=municipios.id)" +
                                                                             "inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where c1_3_ap_relaciontac_scp.id=':id' and c1_3_ap_relaciontac_scp.anno=:anno" ;
  //----------------------------------------------------------------------------------
   public static final String SQL_1_3_US                                   = "select municipios.nombre as municipio, c1_3_us_relaciontac_scp.anno as Año, " +
                                                                             "((bosques_nat+plantacion_estb)/(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote))*100 as "+CONSTANTS.RELAC_TAC_SCP_COLUMN_NAME+" " +
                                                                             "from (c1_3_us_relaciontac_scp inner join municipios on c1_3_us_relaciontac_scp.municipio=municipios.id)" +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where c1_3_us_relaciontac_scp.id=':id' and c1_3_us_relaciontac_scp.anno=:anno" ;
  //----------------------------------------------------------------------------------
   public static final String SQL_1_3_EFI_Subgrupo                         = "select municipios.nombre as municipio, c1_3_us_relaciontac_scp.anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                             "((sum(bosques_nat)+sum(plantacion_estb))/sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote))*100 as "+CONSTANTS.RELAC_TAC_SCP_COLUMN_NAME+" " +
                                                                             "from (((c1_3_us_relaciontac_scp inner join usilvicola on c1_3_us_relaciontac_scp.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id)inner join municipios on c1_3_us_relaciontac_scp.municipio=municipios.id)" +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where efi.id=':id' and c1_3_us_relaciontac_scp.anno=:anno group by municipios.nombre, c1_3_us_relaciontac_scp.anno, efi.nombre, usilvicola.nombre" ;

   public static final String SQL_1_3_EFI_TOTAL                            = "select efi.nombre as efi, ((sum(bosques_nat)+sum(plantacion_estb))/sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote))*100 as "+CONSTANTS.RELAC_TAC_SCP_COLUMN_NAME+" " +
                                                                             "from ((c1_3_us_relaciontac_scp inner join usilvicola on c1_3_us_relaciontac_scp.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) "+
                                                                             "where efi.id=':id' and c1_3_us_relaciontac_scp.anno=:anno group by efi.nombre";
  //----------------------------------------------------------------------------------
   public static final String SQL_1_3_MUN_SubGrupoUS                       = "select municipios.nombre as municipio, ((sum(bosques_nat)+sum(plantacion_estb))/sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote))*100 " +
                                                                             "from ((c1_3_us_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_us_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_us_relaciontac_scp.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_1_3_MUN_SubGrupoAP                       = "select municipios.nombre as municipio, ((sum(bosques_nat)+sum(plantacion_estb))/sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote))*100" +
                                                                             "from ((c1_3_ap_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_ap_relaciontac_scp.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_1_3_MUN_SubGrupoOTROS                    = "select municipios.nombre as municipio, ((sum(bosques_nat)+sum(plantacion_estb))/sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote))*100 " +
                                                                             "from ((c1_3_otros_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_otros_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_otros_relaciontac_scp.anno=:anno group by municipios.id, municipios.nombre;";
   //----------------------------------------------------------------------------------
   public static final String SQL_1_3_MUN_US_SubValues                     = "select (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote), municipios.nombre as municipio " +
                                                                             "from ((c1_3_us_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_us_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_us_relaciontac_scp.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_1_3_MUN_AP_SubValues                     = "select (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote), municipios.nombre as municipio " +
                                                                             "from ((c1_3_ap_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_ap_relaciontac_scp.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_1_3_MUN_OTROS_SubValues                  = "select (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote), municipios.nombre as municipio " +
                                                                             "from ((c1_3_otros_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_otros_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_otros_relaciontac_scp.anno=:anno group by municipios.id, municipios.nombre;";
   //----------------------------------------------------------------------------------
 public static final String SQL_1_3_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote) from (((c1_3_us_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_us_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c1_3_us_relaciontac_scp.id=usilvicola.id) inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_us_relaciontac_scp.anno=:anno " +
                                                                             "group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_1_3_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote) from (((c1_3_ap_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c1_3_ap_relaciontac_scp.id=area_protegida.id) inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_ap_relaciontac_scp.anno=:anno " +
                                                                             "group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_1_3_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote) from (((c1_3_otros_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_otros_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c1_3_otros_relaciontac_scp.id=otros.id) inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c1_3_otros_relaciontac_scp.anno=:anno " +
                                                                             "group by otros.id, otros.nombre;";
  //----------------------------------------------------------------------------------
   public static final String SQL_1_3_PROV_SubGrupoUS                      = "select (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote), provincias.nombre " +
                                                                             "from ((c1_3_us_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_us_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where c1_3_us_relaciontac_scp.anno=:anno group by provincias.id, provincias.nombre;";

   public static final String SQL_1_3_PROV_SubGrupoAP                      = "select (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote), provincias.nombre " +
                                                                             "from ((c1_3_ap_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where c1_3_ap_relaciontac_scp.anno=:anno group by provincias.id, provincias.nombre;";

   public static final String SQL_1_3_PROV_SubGrupoOTROS                   = "select (sum(bosques_nat)+sum(plantacion_estb)), sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+" +
                                                                             "superf_quemada+xerofilo_mogote), provincias.nombre " +
                                                                             "from ((c1_3_otros_relaciontac_scp inner join municipios on " +
                                                                             "c1_3_otros_relaciontac_scp.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where c1_3_otros_relaciontac_scp.anno=:anno group by provincias.id, provincias.nombre;";

   //...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_1_3_AP                               = "select case when (bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+superf_quemada+xerofilo_mogote)<>0 then " +
                                                                           "((bosques_nat+plantacion_estb)/(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+superf_quemada+xerofilo_mogote))*100 else 0 end " +
                                                                           "as  "+CONSTANTS.RELAC_TAC_SCP_COLUMN_NAME+", c1_3_ap_relaciontac_scp.anno " +
                                                                           "from c1_3_ap_relaciontac_scp inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno)"+
                                                                           "where c1_3_ap_relaciontac_scp.id=':id' and c1_3_ap_relaciontac_scp.anno in(:anno) order by c1_3_ap_relaciontac_scp.anno";

   public static final String G_SQL_1_3_AP_YEARS                         = "select distinct c1_3_ap_relaciontac_scp.anno from c1_3_ap_relaciontac_scp inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) order by c1_3_ap_relaciontac_scp.anno";
 //......................................................................................................................
   public static final String G_SQL_1_3_US                               = "select case when (bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+superf_quemada+xerofilo_mogote)<>0 then " +
                                                                           "((bosques_nat+plantacion_estb)/(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+superf_quemada+xerofilo_mogote))*100 else 0 end " +
                                                                           "as  "+CONSTANTS.RELAC_TAC_SCP_COLUMN_NAME+", c1_3_us_relaciontac_scp.anno " +
                                                                           "from c1_3_us_relaciontac_scp inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno)"+
                                                                           "where c1_3_us_relaciontac_scp.id=':id' and c1_3_us_relaciontac_scp.anno in(:anno) order by c1_3_us_relaciontac_scp.anno";

   public static final String G_SQL_1_3_US_YEARS                         = "select distinct c1_3_us_relaciontac_scp.anno from c1_3_us_relaciontac_scp inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) order by c1_3_us_relaciontac_scp.anno";

  //......................................................................................................................
   public static final String G_SQL_1_3_OTROS_YEARS                      = "select distinct c1_3_otros_relaciontac_scp.anno from c1_3_otros_relaciontac_scp inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) order by c1_3_otros_relaciontac_scp.anno";

  //......................................................................................................................
   public static final String G_SQL_1_3_EFI                              = "select case when sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+superf_quemada+xerofilo_mogote)<>0 then " +
                                                                           "(sum(bosques_nat+plantacion_estb)/sum(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+superf_quemada+xerofilo_mogote))*100 else 0 end " +
                                                                           "as  "+CONSTANTS.RELAC_TAC_SCP_COLUMN_NAME+", c1_3_us_relaciontac_scp.anno " +
                                                                           "from (c1_3_us_relaciontac_scp inner join usilvicola on c1_3_us_relaciontac_scp.id=usilvicola.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) "+
                                                                           "where usilvicola.efi=':id' and c1_3_us_relaciontac_scp.anno in(:anno) " +
                                                                           "group by usilvicola.efi, c1_3_us_relaciontac_scp.anno order by c1_3_us_relaciontac_scp.anno";

   public static final String G_SQL_1_3_EFI_YEARS                        = "select distinct c1_3_us_relaciontac_scp.anno from c1_3_us_relaciontac_scp inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) order by c1_3_us_relaciontac_scp.anno";
       //......................................................................................................................
   public static final String G_SQL_1_3_MUN_AP                           = "select sum(bosques_nat),sum(plantacion_estb),sum(bosque_ralo),sum(calvero),sum(lugar_talado),sum(plantac_joven)," +
                                                                           "sum(plantac_montes_muertos),sum(superf_quemada),sum(xerofilo_mogote), c1_3_ap_relaciontac_scp.anno " +
                                                                           "from (c1_3_ap_relaciontac_scp inner join municipios on c1_3_ap_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) "+
                                                                           "where municipios.id=':id' and c1_3_ap_relaciontac_scp.anno in(:anno) " +
                                                                           "group by municipios.id, c1_3_ap_relaciontac_scp.anno order by c1_3_ap_relaciontac_scp.anno";

   public static final String G_SQL_1_3_MUN_US                           = "select sum(bosques_nat),sum(plantacion_estb),sum(bosque_ralo),sum(calvero),sum(lugar_talado),sum(plantac_joven), " +
                                                                           "sum(plantac_montes_muertos),sum(superf_quemada),sum(xerofilo_mogote), c1_3_us_relaciontac_scp.anno " +
                                                                           "from (c1_3_us_relaciontac_scp inner join municipios on c1_3_us_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) "+
                                                                           "where municipios.id=':id' and c1_3_us_relaciontac_scp.anno in(:anno) " +
                                                                           "group by municipios.id, c1_3_us_relaciontac_scp.anno order by c1_3_us_relaciontac_scp.anno";

   public static final String G_SQL_1_3_MUN_OTROS                        = "select sum(bosques_nat),sum(plantacion_estb),sum(bosque_ralo),sum(calvero),sum(lugar_talado),sum(plantac_joven)," +
                                                                           "sum(plantac_montes_muertos),sum(superf_quemada),sum(xerofilo_mogote), c1_3_otros_relaciontac_scp.anno " +
                                                                           "from (c1_3_otros_relaciontac_scp inner join municipios on c1_3_otros_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) "+
                                                                           "where municipios.id=':id' and c1_3_otros_relaciontac_scp.anno in(:anno) " +
                                                                           "group by municipios.id, c1_3_otros_relaciontac_scp.anno order by c1_3_otros_relaciontac_scp.anno";

   public static final String G_SQL_1_3_MUN_YEARS_AP                    = "select distinct c1_3_ap_relaciontac_scp.anno from c1_3_ap_relaciontac_scp inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) where c1_3_ap_relaciontac_scp.municipio=':id' " +
                                                                           "order by c1_3_ap_relaciontac_scp.anno";

   public static final String G_SQL_1_3_MUN_YEARS_US                    = "select distinct c1_3_us_relaciontac_scp.anno from c1_3_us_relaciontac_scp inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) where c1_3_us_relaciontac_scp.municipio=':id' " +
                                                                           " order by c1_3_us_relaciontac_scp.anno";

   public static final String G_SQL_1_3_MUN_YEARS_OTROS                 = "select distinct c1_3_otros_relaciontac_scp.anno from c1_3_otros_relaciontac_scp inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) where c1_3_otros_relaciontac_scp.municipio=':id' " +
                                                                           " order by c1_3_otros_relaciontac_scp.anno";
//......................................................................................................................
   public static final String G_SQL_1_3_PROV_AP                          = "select sum(bosques_nat),sum(plantacion_estb),sum(bosque_ralo),sum(calvero),sum(lugar_talado),sum(plantac_joven)," +
                                                                           "sum(plantac_montes_muertos),sum(superf_quemada),sum(xerofilo_mogote), c1_3_ap_relaciontac_scp.anno " +
                                                                           "from (c1_3_ap_relaciontac_scp inner join municipios on c1_3_ap_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' and c1_3_ap_relaciontac_scp.anno in(:anno) " +
                                                                           "group by municipios.provincia, c1_3_ap_relaciontac_scp.anno order by c1_3_ap_relaciontac_scp.anno";

   public static final String G_SQL_1_3_PROV_US                          = "select sum(bosques_nat),sum(plantacion_estb),sum(bosque_ralo),sum(calvero),sum(lugar_talado),sum(plantac_joven)," +
                                                                           "sum(plantac_montes_muertos),sum(superf_quemada),sum(xerofilo_mogote), c1_3_us_relaciontac_scp.anno " +
                                                                           "from (c1_3_us_relaciontac_scp inner join municipios on c1_3_us_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' and c1_3_us_relaciontac_scp.anno in(:anno) " +
                                                                           "group by municipios.provincia, c1_3_us_relaciontac_scp.anno order by c1_3_us_relaciontac_scp.anno";

   public static final String G_SQL_1_3_PROV_OTROS                       = "select sum(bosques_nat),sum(plantacion_estb),sum(bosque_ralo),sum(calvero),sum(lugar_talado),sum(plantac_joven)," +
                                                                           "sum(plantac_montes_muertos),sum(superf_quemada),sum(xerofilo_mogote), c1_3_otros_relaciontac_scp.anno " +
                                                                           "from (c1_3_otros_relaciontac_scp inner join municipios on c1_3_otros_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' and c1_3_otros_relaciontac_scp.anno in(:anno) " +
                                                                           "group by municipios.provincia, c1_3_otros_relaciontac_scp.anno order by c1_3_otros_relaciontac_scp.anno";

   public static final String G_SQL_1_3_PROV_YEARS_AP                    = "select distinct c1_3_ap_relaciontac_scp.anno from (c1_3_ap_relaciontac_scp inner join municipios on c1_3_ap_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_ap_totalareacubierta on (c1_3_ap_relaciontac_scp.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c1_3_ap_relaciontac_scp.municipio=c1_1_ap_totalareacubierta.municipio and c1_3_ap_relaciontac_scp.anno=c1_1_ap_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' order by c1_3_ap_relaciontac_scp.anno";

   public static final String G_SQL_1_3_PROV_YEARS_US                    = "select distinct c1_3_us_relaciontac_scp.anno from (c1_3_us_relaciontac_scp inner join municipios on c1_3_us_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c1_3_us_relaciontac_scp.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c1_3_us_relaciontac_scp.municipio=c1_1_us_totalareacubierta.municipio and c1_3_us_relaciontac_scp.anno=c1_1_us_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' order by c1_3_us_relaciontac_scp.anno";

   public static final String G_SQL_1_3_PROV_YEARS_OTROS                 = "select distinct c1_3_otros_relaciontac_scp.anno from (c1_3_otros_relaciontac_scp inner join municipios on c1_3_otros_relaciontac_scp.municipio=municipios.id)" +
                                                                           "inner join c1_1_otros_totalareacubierta on (c1_3_otros_relaciontac_scp.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c1_3_otros_relaciontac_scp.municipio=c1_1_otros_totalareacubierta.municipio and c1_3_otros_relaciontac_scp.anno=c1_1_otros_totalareacubierta.anno) "+
                                                                           "where municipios.provincia=':id' order by c1_3_otros_relaciontac_scp.anno";
       //......................................................................................................................

   public static final String G_SQL_1_3_Temp_Table                       = "select case when (bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+superf_quemada+xerofilo_mogote)<>0 then " +
                                                                           "((bosques_nat+plantacion_estb)/(bosque_ralo+calvero+lugar_talado+plantac_joven+plantac_montes_muertos+superf_quemada+xerofilo_mogote))*100 else 0 end " +
                                                                           "as  "+CONSTANTS.RELAC_TAC_SCP_COLUMN_NAME+", anno " +
                                                                           "from c_1_3_graphicdata order by anno";   
 //====================== Criterio 1.4 Efectividad de Plantaciones =====================================================================================================================================================================
  //----------------------------------------------------------------------------------
   public static final String SQL_1_4_AP                                   = "select municipios.nombre as municipio, anno, superf_log as "+CONSTANTS.SUP_LOG_COLUMN_NAME+", superf_plant as "+CONSTANTS.SUP_PLANTACION_COLUMN_NAME+", " +
                                                                             "plant_vivas as "+CONSTANTS.PLANTACIONES_VIVAS_COLUMN_NAME+", plant_muertas as "+CONSTANTS.PLANTACIONES_MUERTAS_COLUMN_NAME+", superf_plant-superf_log, especie.precio, especie.nombre " +
                                                                             "from (c1_4_ap_efectividadplantaciones inner join municipios on c1_4_ap_efectividadplantaciones.municipio=municipios.id)inner join especie on " +
                                                                             "c1_4_ap_efectividadplantaciones.especie=especie.id " +
                                                                             "where c1_4_ap_efectividadplantaciones.id=':id' and anno=:anno  " +
                                                                             "order by municipios.nombre;";
  //----------------------------------------------------------------------------------
   public static final String SQL_1_4_US                                   = "select municipios.nombre as municipio, anno, superf_log, superf_plant, plant_vivas, plant_muertas, superf_plant-superf_log, especie.precio, especie.nombre " +
                                                                             "from (c1_4_us_efectividadplantaciones inner join municipios on c1_4_us_efectividadplantaciones.municipio=municipios.id)inner join especie on " +
                                                                             "c1_4_us_efectividadplantaciones.especie=especie.id " +
                                                                             "where c1_4_us_efectividadplantaciones.id=':id' and anno=:anno  " +
                                                                             "order by municipios.nombre;";
  //----------------------------------------------------------------------------------
   public static final String SQL_1_4_EFI                                  = "select municipios.nombre, anno, efi.nombre, usilvicola.nombre, " +
                                                                             "superf_log, superf_plant, plant_vivas, plant_muertas, especie.precio, especie.nombre " +
                                                                             "from (((c1_4_us_efectividadplantaciones inner join usilvicola on c1_4_us_efectividadplantaciones.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id)inner join municipios on c1_4_us_efectividadplantaciones.municipio=municipios.id) " +
                                                                             "inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id " +
                                                                             "where efi.id=':id' and anno=:anno " +
                                                                             "group by municipios.id, municipios.nombre, anno, efi.nombre, usilvicola.nombre, especie.nombre, especie.precio, " +
                                                                             "superf_log, superf_plant, plant_vivas, plant_muertas " +
                                                                             "order by usilvicola.nombre, especie.nombre;";

//   public static final String SQL_1_4_EFI_TOTAL                            = "select efi.nombre as efi, (sum(superf_log)/sum(superf_plant))*100 as "+CONSTANTS.LOGRO_TERCER_CONTEO_COLUMN_NAME+", " +
//                                                                             "round(cast((sum(plant_vivas)/sum(plant_vivas+plant_muertas))*100 as numeric)) as "+CONSTANTS.SUP_TERCER_CONTEO_COLUMN_NAME+" " +
//                                                                             "from (c1_4_us_efectividadplantaciones inner join usilvicola on c1_4_us_efectividadplantaciones.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
//                                                                             "where efi.id=':id' and anno=:anno group by efi.nombre";
  //----------------------------------------------------------------------------------
//   public static final String SQL_1_4_MUN_SubGrupoUS                       = "select municipios.nombre as municipio, (sum(superf_log)/sum(superf_plant))*100, " +
//                                                                             "(sum(plant_vivas)/sum(plant_vivas+plant_muertas))*100, especie.nombre " +
//                                                                             "from ((c1_4_us_efectividadplantaciones inner join municipios on " +
//                                                                             "c1_4_us_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre, especie.nombre;" ;
//
//   public static final String SQL_1_4_MUN_SubGrupoAP                       = "select municipios.nombre as municipio, (sum(superf_log)/sum(superf_plant))*100, " +
//                                                                             "(sum(plant_vivas)/sum(plant_vivas+plant_muertas))*100, especie.nombre " +
//                                                                             "from ((c1_4_ap_efectividadplantaciones inner join municipios on " +
//                                                                             "c1_4_ap_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c1_4_ap_efectividadplantaciones.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by municipios.nombre, especie.nombre;" ;
//
//  public static final String SQL_1_4_MUN_SubGrupoOTROS                     = "select municipios.nombre as municipio, (sum(superf_log)/sum(superf_plant))*100, " +
//                                                                             "(sum(plant_vivas)/sum(plant_vivas+plant_muertas))*100, especie.nombre " +
//                                                                             "from ((c1_4_otros_efectividadplantaciones inner join municipios on " +
//                                                                             "c1_4_otros_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c1_4_otros_efectividadplantaciones.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by municipios.nombre, especie.nombre;" ;
  //----------------------------------------------------------------------------------
   public static final String SQL_1_4_MUN_US_SubValues                     = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, municipios.nombre " +
                                                                             "from ((c1_4_us_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_us_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre, especie.nombre, especie.precio " +
                                                                             "order by especie.nombre;";

   public static final String SQL_1_4_MUN_AP_SubValues                     = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, municipios.nombre " +
                                                                             "from ((c1_4_ap_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_ap_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join especie on c1_4_ap_efectividadplantaciones.especie=especie.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre, especie.nombre, especie.precio " +
                                                                             "order by especie.nombre;";

   public static final String SQL_1_4_MUN_OTROS_SubValues                  = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, municipios.nombre " +
                                                                             "from ((c1_4_otros_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_otros_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join especie on c1_4_otros_efectividadplantaciones.especie=especie.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre, especie.nombre, especie.precio " +
                                                                             "order by especie.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_1_4_MUN_SubGrupoUS_Entities                = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, usilvicola.nombre " +
                                                                             "from (((c1_4_us_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_us_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c1_4_us_efectividadplantaciones.id=usilvicola.id)inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by usilvicola.nombre, especie.nombre, especie.precio;";

 public static final String SQL_1_4_MUN_SubGrupoAP_Entities                = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, area_protegida.nombre " +
                                                                             "from (((c1_4_ap_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_ap_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c1_4_ap_efectividadplantaciones.id=area_protegida.id)inner join especie on c1_4_ap_efectividadplantaciones.especie=especie.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by area_protegida.nombre, especie.nombre, especie.precio;";

 public static final String SQL_1_4_MUN_SubGrupoOTROS_Entities             = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, otros.nombre " +
                                                                             "from (((c1_4_otros_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_otros_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c1_4_otros_efectividadplantaciones.id=otros.id)inner join especie on c1_4_otros_efectividadplantaciones.especie=especie.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by otros.nombre, especie.nombre, especie.precio;";
  //----------------------------------------------------------------------------------
   public static final String SQL_1_4_PROV_SubGrupoUS                      = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, provincias.nombre, municipios.nombre " +
                                                                             "from ((c1_4_us_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_us_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id " +
                                                                             "where anno=:anno group by provincias.id, provincias.nombre, municipios.id, municipios.nombre, " +
                                                                             "especie.nombre, especie.precio order by provincias.nombre, municipios.nombre, especie.nombre;";

   public static final String SQL_1_4_PROV_SubGrupoAP                      = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, provincias.nombre, municipios.nombre " +
                                                                             "from ((c1_4_ap_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_ap_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join especie on c1_4_ap_efectividadplantaciones.especie=especie.id " +
                                                                             "where anno=:anno group by provincias.id, provincias.nombre, municipios.id, municipios.nombre, " +
                                                                             "especie.nombre, especie.precio order by provincias.nombre, municipios.nombre, especie.nombre;";

   public static final String SQL_1_4_PROV_SubGrupoOTROS                   = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, provincias.nombre, municipios.nombre " +
                                                                             "from ((c1_4_otros_efectividadplantaciones inner join municipios on " +
                                                                             "c1_4_otros_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join especie on c1_4_otros_efectividadplantaciones.especie=especie.id " +
                                                                             "where anno=:anno group by provincias.id, provincias.nombre, municipios.id, municipios.nombre, " +
                                                                             "especie.nombre, especie.precio order by provincias.nombre, municipios.nombre, especie.nombre;";
   //----------------------------------------------------------------------------------
//   public static final String SQL_1_4_PROV_SubGrupoUS_ESP                  = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, provincias.nombre" +
//                                                                             ", municipios.nombre " +
//                                                                             "from ((c1_4_us_efectividadplantaciones inner join municipios on " +
//                                                                             "c1_4_us_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by provincias.id, provincias.nombre, municipios.id, municipios.nombre, " +
//                                                                             "especie.nombre, especie.precio order by especie.nombre;";
//
//   public static final String SQL_1_4_PROV_SubGrupoAP_ESP                  = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, provincias.nombre" +
//                                                                             ", municipios.nombre " +
//                                                                             "from ((c1_4_ap_efectividadplantaciones inner join municipios on " +
//                                                                             "c1_4_ap_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c1_4_ap_efectividadplantaciones.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by provincias.id, provincias.nombre, municipios.id, municipios.nombre, " +
//                                                                             "especie.nombre, especie.precio order by especie.nombre;";
//
//   public static final String SQL_1_4_PROV_SubGrupoOTROS_ESP               = "select sum(superf_log), sum(superf_plant), sum(plant_vivas), sum(plant_muertas), especie.precio, especie.nombre, provincias.nombre" +
//                                                                             ", municipios.nombre " +
//                                                                             "from ((c1_4_otros_efectividadplantaciones inner join municipios on " +
//                                                                             "c1_4_otros_efectividadplantaciones.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c1_4_otros_efectividadplantaciones.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by provincias.id, provincias.nombre, municipios.id, municipios.nombre, " +
//                                                                             "especie.nombre, especie.precio order by especie.nombre;";

   //...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_1_4_AP                               = "select case when superf_plant<>0 then superf_log/superf_plant else 0 end as  "+CONSTANTS.LOGRO_TERCER_CONTEO_COLUMN_NAME+",case when (plant_vivas+plant_muertas)<>0 then (plant_vivas/(plant_vivas+plant_muertas))*100 else 0 end as  "+CONSTANTS.SUP_TERCER_CONTEO_COLUMN_NAME+",(superf_plant-superf_log)*precio as  "+CONSTANTS.PERDIDA_DINERO_COLUMN_NAME+",superf_plant-superf_log as  "+CONSTANTS.PERDIDA_HECT_COLUMN_NAME+", anno " +
                                                                           "from c1_4_ap_efectividadplantaciones inner join especie on c1_4_ap_efectividadplantaciones.especie=especie.id "+
                                                                           "where c1_4_ap_efectividadplantaciones.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_1_4_AP_YEARS                         = "select distinct anno from c1_4_ap_efectividadplantaciones order by anno";
 //......................................................................................................................
   public static final String G_SQL_1_4_US                               = "select case when superf_plant<>0 then superf_log/superf_plant else 0 end as  "+CONSTANTS.LOGRO_TERCER_CONTEO_COLUMN_NAME+",case when (plant_vivas+plant_muertas)<>0 then (plant_vivas/(plant_vivas+plant_muertas))*100 else 0 end as  "+CONSTANTS.SUP_TERCER_CONTEO_COLUMN_NAME+",(superf_plant-superf_log)*precio as  "+CONSTANTS.PERDIDA_DINERO_COLUMN_NAME+",superf_plant-superf_log as  "+CONSTANTS.PERDIDA_HECT_COLUMN_NAME+", anno " +
                                                                           "from c1_4_us_efectividadplantaciones inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id "+
                                                                           "where c1_4_us_efectividadplantaciones.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_1_4_US_YEARS                         = "select distinct anno from c1_4_us_efectividadplantaciones order by anno";

  //......................................................................................................................
   public static final String G_SQL_1_4_OTROS_YEARS                      = "select distinct anno from c1_4_otros_efectividadplantaciones order by anno";

  //......................................................................................................................
   public static final String G_SQL_1_4_EFI                              = "select case when sum(superf_plant)<>0 then sum(superf_log)/sum(superf_plant) else 0 end as  "+CONSTANTS.LOGRO_TERCER_CONTEO_COLUMN_NAME+",case when sum(plant_vivas+plant_muertas)<>0 then (sum(plant_vivas)/sum(plant_vivas+plant_muertas))*100 else 0 end as  "+CONSTANTS.SUP_TERCER_CONTEO_COLUMN_NAME+",sum(superf_plant-superf_log)*sum(precio) as  "+CONSTANTS.PERDIDA_DINERO_COLUMN_NAME+",sum(superf_plant-superf_log) as  "+CONSTANTS.PERDIDA_HECT_COLUMN_NAME+", anno " +
                                                                           "from (c1_4_us_efectividadplantaciones inner join usilvicola on c1_4_us_efectividadplantaciones.id=usilvicola.id)" +
                                                                           "inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_1_4_EFI_YEARS                        = "select distinct anno from c1_4_us_efectividadplantaciones order by anno";
       //......................................................................................................................
   public static final String G_SQL_1_4_MUN_AP                           = "select sum(superf_plant),sum(superf_log),sum(plant_vivas),sum(plant_muertas),sum(precio), anno " +
                                                                           "from (c1_4_ap_efectividadplantaciones inner join municipios on c1_4_ap_efectividadplantaciones.municipio=municipios.id)" +
                                                                           "inner join especie on c1_4_ap_efectividadplantaciones.especie=especie.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_4_MUN_US                           = "select sum(superf_plant),sum(superf_log),sum(plant_vivas),sum(plant_muertas),sum(precio), anno " +
                                                                           "from (c1_4_us_efectividadplantaciones inner join municipios on c1_4_us_efectividadplantaciones.municipio=municipios.id)" +
                                                                           "inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_4_MUN_OTROS                        = "select sum(superf_plant),sum(superf_log),sum(plant_vivas),sum(plant_muertas),sum(precio), anno " +
                                                                           "from (c1_4_otros_efectividadplantaciones inner join municipios on c1_4_otros_efectividadplantaciones.municipio=municipios.id)" +
                                                                           "inner join especie on c1_4_otros_efectividadplantaciones.especie=especie.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_4_MUN_YEARS_AP                    = "select distinct anno from c1_4_ap_efectividadplantaciones where municipio=':id' order by anno";

   public static final String G_SQL_1_4_MUN_YEARS_US                    = "select distinct anno from c1_4_us_efectividadplantaciones where municipio=':id' order by anno";

   public static final String G_SQL_1_4_MUN_YEARS_OTROS                 = "select distinct anno from c1_4_otros_efectividadplantaciones where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_1_4_PROV_AP                          = "select sum(superf_plant),sum(superf_log),sum(plant_vivas),sum(plant_muertas),sum(precio), anno " +
                                                                           "from (c1_4_ap_efectividadplantaciones inner join municipios on c1_4_ap_efectividadplantaciones.municipio=municipios.id)" +
                                                                           "inner join especie on c1_4_ap_efectividadplantaciones.especie=especie.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_4_PROV_US                           = "select sum(superf_plant),sum(superf_log),sum(plant_vivas),sum(plant_muertas),sum(precio), anno " +
                                                                           "from (c1_4_us_efectividadplantaciones inner join municipios on c1_4_us_efectividadplantaciones.municipio=municipios.id)" +
                                                                           "inner join especie on c1_4_us_efectividadplantaciones.especie=especie.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_4_PROV_OTROS                        = "select sum(superf_plant),sum(superf_log),sum(plant_vivas),sum(plant_muertas),sum(precio), anno " +
                                                                           "from (c1_4_otros_efectividadplantaciones inner join municipios on c1_4_otros_efectividadplantaciones.municipio=municipios.id)" +
                                                                           "inner join especie on c1_4_otros_efectividadplantaciones.especie=especie.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_4_PROV_YEARS_AP                    = "select distinct anno from c1_4_ap_efectividadplantaciones inner join municipios on c1_4_ap_efectividadplantaciones.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_1_4_PROV_YEARS_US                    = "select distinct anno from c1_4_us_efectividadplantaciones inner join municipios on c1_4_us_efectividadplantaciones.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_1_4_PROV_YEARS_OTROS                 = "select distinct anno from c1_4_otros_efectividadplantaciones inner join municipios on c1_4_otros_efectividadplantaciones.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_1_4_Temp_Table                       = "select case when superf_plant<>0 then superf_log/superf_plant else 0 end as "+CONSTANTS.LOGRO_TERCER_CONTEO_COLUMN_NAME+", " +
                                                                           "case when (plant_vivas+plant_muertas)<>0 then (plant_vivas/(plant_vivas+plant_muertas))*100 else 0 end as "+CONSTANTS.SUP_TERCER_CONTEO_COLUMN_NAME+", " +
                                                                           "(superf_plant-superf_log)*precio as "+CONSTANTS.PERDIDA_DINERO_COLUMN_NAME+",superf_plant-superf_log as  "+CONSTANTS.PERDIDA_HECT_COLUMN_NAME+", anno " +
                                                                           "from c_1_4_graphicdata order by anno";
   
 //====================== Criterio 1.5 Areas Pendientes a Reforestar  =====================================================================================================================================================================
  //----------------------------------------------------------------------------------
   public static final String SQL_1_5_AP                                   = "select municipios.nombre as municipio, anno as Año, area_tal_def as "+CONSTANTS.AREA_TALADA_COLUMN_NAME+" " +
                                                                             ", area_quem_def as "+CONSTANTS.AREA_QUEMADA_COLUMN_NAME+", (area_tal_def+area_quem_def) as "+CONSTANTS.AREA_PEND_REFOREST_COLUMN_NAME+", " +
                                                                             "area_establ/area_tal as "+CONSTANTS.RET_COLUMN_NAME+" " +
                                                                             "from c1_5_ap_areaspendreforestar inner join municipios on c1_5_ap_areaspendreforestar.municipio=municipios.id " +
                                                                             "where c1_5_ap_areaspendreforestar.id=':id' and anno=:anno" ;
  //----------------------------------------------------------------------------------
   public static final String SQL_1_5_US                                   = "select municipios.nombre as municipio, anno as Año, area_tal_def as "+CONSTANTS.AREA_TALADA_COLUMN_NAME+" " +
                                                                             ", area_quem_def as "+CONSTANTS.AREA_QUEMADA_COLUMN_NAME+", (area_tal_def+area_quem_def) as "+CONSTANTS.AREA_PEND_REFOREST_COLUMN_NAME+", " +
                                                                             "area_establ/area_tal as "+CONSTANTS.RET_COLUMN_NAME+" " +
                                                                             "from c1_5_us_areaspendreforestar inner join municipios on c1_5_us_areaspendreforestar.municipio=municipios.id " +
                                                                             "where c1_5_us_areaspendreforestar.id=':id' and anno=:anno" ;
  //----------------------------------------------------------------------------------
   public static final String SQL_1_5_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                             "sum(area_tal_def) as "+CONSTANTS.AREA_TALADA_COLUMN_NAME+" " +
                                                                             ", sum(area_quem_def) as "+CONSTANTS.AREA_QUEMADA_COLUMN_NAME+", sum(area_tal_def+area_quem_def) as "+CONSTANTS.AREA_PEND_REFOREST_COLUMN_NAME+", " +
                                                                             "sum(area_establ)/sum(area_tal) as "+CONSTANTS.RET_COLUMN_NAME+" " +
                                                                             "from ((c1_5_us_areaspendreforestar inner join usilvicola on c1_5_us_areaspendreforestar.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id)inner join municipios on c1_5_us_areaspendreforestar.municipio=municipios.id " +
                                                                             "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre" ;

   public static final String SQL_1_5_EFI_TOTAL                            = "select efi.nombre, sum(area_tal_def), sum(area_quem_def), sum(area_tal_def+area_quem_def), sum(area_establ)/sum(area_tal) " +
                                                                             "from (c1_5_us_areaspendreforestar inner join usilvicola on c1_5_us_areaspendreforestar.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                             "where efi.id=':id' and anno=:anno group by efi.nombre" ;
  //----------------------------------------------------------------------------------
   public static final String SQL_1_5_MUN_SubGrupoUS                       = "select municipios.nombre as municipio, sum(area_tal_def), sum(area_quem_def), sum(area_tal_def+area_quem_def), sum(area_establ)/sum(area_tal) " +
                                                                             "from (c1_5_us_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_us_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;" ;

   public static final String SQL_1_5_MUN_SubGrupoAP                       = "select municipios.nombre as municipio, sum(area_tal_def), sum(area_quem_def), sum(area_tal_def+area_quem_def), sum(area_establ)/sum(area_tal) " +
                                                                             "from (c1_5_ap_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_ap_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id,municipios.nombre;" ;

   public static final String SQL_1_5_MUN_SubGrupoOTROS                    = "select municipios.nombre as municipio, sum(area_tal_def), sum(area_quem_def), sum(area_tal_def+area_quem_def), sum(area_establ)/sum(area_tal) " +
                                                                             "from (c1_5_otros_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_otros_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;" ;
//----------------------------------------------------------------------------------
   public static final String SQL_1_5_MUN_US_SubValues                     = "select sum(area_tal_def), sum(area_quem_def), sum(area_tal), sum(area_establ), municipios.nombre as municipio " +
                                                                             "from (c1_5_us_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_us_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_1_5_MUN_AP_SubValues                     = "select sum(area_tal_def), sum(area_quem_def), sum(area_tal), sum(area_establ), municipios.nombre as municipio " +
                                                                             "from (c1_5_ap_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_ap_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_1_5_MUN_OTROS_SubValues                  = "select sum(area_tal_def), sum(area_quem_def), sum(area_tal), sum(area_establ), municipios.nombre as municipio " +
                                                                             "from (c1_5_otros_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_otros_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_1_5_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, area_tal_def, area_quem_def, area_tal, area_establ from ((c1_5_us_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_us_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c1_5_us_areaspendreforestar.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by usilvicola.id, usilvicola.nombre, area_tal_def, area_quem_def, area_tal, area_establ;";

 public static final String SQL_1_5_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, area_tal_def, area_quem_def, area_tal, area_establ from ((c1_5_ap_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_ap_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c1_5_ap_areaspendreforestar.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by area_protegida.id, area_protegida.nombre, area_tal_def, area_quem_def, area_tal, area_establ;";

 public static final String SQL_1_5_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, area_tal_def, area_quem_def, area_tal, area_establ from ((c1_5_otros_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_otros_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c1_5_otros_areaspendreforestar.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by otros.id, otros.nombre, area_tal_def, area_quem_def, area_tal, area_establ;";
  //----------------------------------------------------------------------------------
   public static final String SQL_1_5_PROV_SubGrupoUS                      = "select sum(area_tal_def), sum(area_quem_def), sum(area_tal), sum(area_establ), provincias.nombre " +
                                                                             "from (c1_5_us_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_us_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where anno=:anno group by provincias.id, provincias.nombre;";

   public static final String SQL_1_5_PROV_SubGrupoAP                      = "select sum(area_tal_def), sum(area_quem_def), sum(area_tal), sum(area_establ), provincias.nombre " +
                                                                             "from (c1_5_ap_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_ap_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where anno=:anno group by provincias.id, provincias.nombre;";

   public static final String SQL_1_5_PROV_SubGrupoOTROS                   = "select sum(area_tal_def), sum(area_quem_def), sum(area_tal), sum(area_establ), provincias.nombre " +
                                                                             "from (c1_5_otros_areaspendreforestar inner join municipios on " +
                                                                             "c1_5_otros_areaspendreforestar.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_1_5_AP                               = "select area_tal_def as  "+CONSTANTS.AREA_TALADA_COLUMN_NAME+",area_quem_def as  "+CONSTANTS.AREA_QUEMADA_COLUMN_NAME+",(area_tal_def+area_quem_def) as  "+CONSTANTS.AREA_PEND_REFOREST_COLUMN_NAME+",case when area_tal<>0 then area_establ/area_tal else 0 end as  "+CONSTANTS.RET_COLUMN_NAME+", anno " +
                                                                           "from c1_5_ap_areaspendreforestar "+
                                                                           "where c1_5_ap_areaspendreforestar.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_1_5_AP_YEARS                         = "select distinct anno from c1_5_ap_areaspendreforestar order by anno";
 //......................................................................................................................
   public static final String G_SQL_1_5_US                               = "select area_tal_def as  "+CONSTANTS.AREA_TALADA_COLUMN_NAME+",area_quem_def as  "+CONSTANTS.AREA_QUEMADA_COLUMN_NAME+",(area_tal_def+area_quem_def) as  "+CONSTANTS.AREA_PEND_REFOREST_COLUMN_NAME+",case when area_tal<>0 then area_establ/area_tal else 0 end as  "+CONSTANTS.RET_COLUMN_NAME+", anno " +
                                                                           "from c1_5_us_areaspendreforestar "+
                                                                           "where c1_5_us_areaspendreforestar.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_1_5_US_YEARS                         = "select distinct anno from c1_5_us_areaspendreforestar order by anno";

  //......................................................................................................................
   public static final String G_SQL_1_5_OTROS_YEARS                      = "select distinct anno from c1_5_otros_areaspendreforestar order by anno";

  //......................................................................................................................
   public static final String G_SQL_1_5_EFI                              = "select sum(area_tal_def) as  "+CONSTANTS.AREA_TALADA_COLUMN_NAME+",sum(area_quem_def) as  "+CONSTANTS.AREA_QUEMADA_COLUMN_NAME+",sum(area_tal_def+area_quem_def) as  "+CONSTANTS.AREA_PEND_REFOREST_COLUMN_NAME+",case when sum(area_tal)<>0 then sum(area_establ)/sum(area_tal) else 0 end as  "+CONSTANTS.RET_COLUMN_NAME+", anno " +
                                                                           "from c1_5_us_areaspendreforestar inner join usilvicola on c1_5_us_areaspendreforestar.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_1_5_EFI_YEARS                        = "select distinct anno from c1_5_us_areaspendreforestar order by anno";
       //......................................................................................................................
   public static final String G_SQL_1_5_MUN_AP                           = "select sum(area_tal_def),sum(area_quem_def),sum(area_tal),sum(area_establ), anno " +
                                                                           "from c1_5_ap_areaspendreforestar inner join municipios on c1_5_ap_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_5_MUN_US                           = "select sum(area_tal_def),sum(area_quem_def),sum(area_tal),sum(area_establ), anno " +
                                                                           "from c1_5_us_areaspendreforestar inner join municipios on c1_5_us_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_5_MUN_OTROS                        = "select sum(area_tal_def),sum(area_quem_def),sum(area_tal),sum(area_establ), anno " +
                                                                           "from c1_5_otros_areaspendreforestar inner join municipios on c1_5_otros_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_5_MUN_YEARS_AP                    = "select distinct anno from c1_5_ap_areaspendreforestar where municipio=':id' order by anno";

   public static final String G_SQL_1_5_MUN_YEARS_US                    = "select distinct anno from c1_5_us_areaspendreforestar where municipio=':id' order by anno";

   public static final String G_SQL_1_5_MUN_YEARS_OTROS                 = "select distinct anno from c1_5_otros_areaspendreforestar where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_1_5_PROV_AP                          = "select sum(area_tal_def),sum(area_quem_def),sum(area_tal),sum(area_establ), anno " +
                                                                           "from c1_5_ap_areaspendreforestar inner join municipios on c1_5_ap_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_5_PROV_US                           = "select sum(area_tal_def),sum(area_quem_def),sum(area_tal),sum(area_establ), anno " +
                                                                           "from c1_5_us_areaspendreforestar inner join municipios on c1_5_us_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_5_PROV_OTROS                        = "select sum(area_tal_def),sum(area_quem_def),sum(area_tal),sum(area_establ), anno " +
                                                                           "from c1_5_otros_areaspendreforestar inner join municipios on c1_5_otros_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_5_PROV_YEARS_AP                    = "select distinct anno from c1_5_ap_areaspendreforestar inner join municipios on c1_5_ap_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_1_5_PROV_YEARS_US                    = "select distinct anno from c1_5_us_areaspendreforestar inner join municipios on c1_5_us_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_1_5_PROV_YEARS_OTROS                 = "select distinct anno from c1_5_otros_areaspendreforestar inner join municipios on c1_5_otros_areaspendreforestar.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_1_5_Temp_Table                       = "select area_tal_def as  "+CONSTANTS.AREA_TALADA_COLUMN_NAME+",area_quem_def as  "+CONSTANTS.AREA_QUEMADA_COLUMN_NAME+",(area_tal_def+area_quem_def) as  "+CONSTANTS.AREA_PEND_REFOREST_COLUMN_NAME+",case when area_tal<>0 then area_establ/area_tal else 0 end as  "+CONSTANTS.RET_COLUMN_NAME+", anno " +
                                                                           "from c_1_5_graphicdata order by anno";

//====================== Criterio 1.6 Árboles aislados =====================================================================================================================================================================
//----------------------------------------------------------------------------------
  public static final String SQL_1_6_AP                                 = "select municipios.nombre as municipio, anno as Año, arbol_existe as "+CONSTANTS.ARBOL_EXISTE_COLUMN_NAME+", " +
                                                                          "case when (arbol_existe<>0 OR arbol_falta<>0) then " +
                                                                          "round(cast((cast(arbol_existe as double precision)/cast(arbol_existe+arbol_falta as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") " +
                                                                          "else 0 end as "+CONSTANTS.NIVEL_COMPORTAMIENTO_COLUMN_NAME+" " +
                                                                          "from c1_6_ap_arbolesaislados inner join municipios on c1_6_ap_arbolesaislados.municipio=municipios.id " +
                                                                          "where c1_6_ap_arbolesaislados.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_1_6_US                                   = "select municipios.nombre as municipio, anno as Año, arbol_existe as "+CONSTANTS.ARBOL_EXISTE_COLUMN_NAME+", " +
                                                                          "case when (arbol_existe<>0 OR arbol_falta<>0) then " +
                                                                          "round(cast((cast(arbol_existe as double precision)/cast(arbol_existe+arbol_falta as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") " +
                                                                          "else 0 end  as "+CONSTANTS.NIVEL_COMPORTAMIENTO_COLUMN_NAME+" " +
                                                                          "from c1_6_us_arbolesaislados inner join municipios on c1_6_us_arbolesaislados.municipio=municipios.id " +
                                                                          "where c1_6_us_arbolesaislados.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_1_6_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                          ", sum(arbol_existe) as "+CONSTANTS.ARBOL_EXISTE_COLUMN_NAME+", " +
                                                                          "case when (sum(arbol_existe)<>0 OR sum(arbol_falta)<>0) then " +
                                                                          "round(cast((cast(sum(arbol_existe) as double precision)/cast(sum(arbol_existe+arbol_falta) as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") " +
                                                                          "else 0 end  as "+CONSTANTS.NIVEL_COMPORTAMIENTO_COLUMN_NAME+" " +
                                                                          "from ((c1_6_us_arbolesaislados inner join usilvicola on c1_6_us_arbolesaislados.id=usilvicola.id) " +
                                                                          "inner join efi on usilvicola.efi=efi.id)inner join municipios on c1_6_us_arbolesaislados.municipio=municipios.id " +
                                                                          "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_1_6_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(arbol_existe) as "+CONSTANTS.ARBOL_EXISTE_COLUMN_NAME+", " +
                                                                          "case when (sum(arbol_existe)<>0 OR sum(arbol_falta)<>0) then " +
                                                                          "round(cast((cast(sum(arbol_existe) as double precision)/cast(sum(arbol_existe+arbol_falta) as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") " +
                                                                          "else 0 end  as "+CONSTANTS.NIVEL_COMPORTAMIENTO_COLUMN_NAME+" " +
                                                                          "from (c1_6_us_arbolesaislados inner join usilvicola on c1_6_us_arbolesaislados.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                          "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_1_6_MUN_SubGrupoUS                       = "select municipios.nombre as municipio" +
                                                                          ", sum(arbol_existe), case when (sum(arbol_existe)<>0 OR sum(arbol_falta)<>0) then " +
                                                                          "round(cast((cast(sum(arbol_existe) as double precision)/cast(sum(arbol_existe+arbol_falta) as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") else 0 end " +
                                                                           "from (c1_6_us_arbolesaislados inner join municipios on " +
                                                                           "c1_6_us_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_1_6_MUN_SubGrupoAP                       = "select municipios.nombre as municipio " +
                                                                          ", sum(arbol_existe), case when (sum(arbol_existe)<>0 OR sum(arbol_falta)<>0) then " +
                                                                          "round(cast((cast(sum(arbol_existe) as double precision)/cast(sum(arbol_existe+arbol_falta) as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") else 0 end " +
                                                                           "from (c1_6_ap_arbolesaislados inner join municipios on " +
                                                                           "c1_6_ap_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_1_6_MUN_SubGrupoOTROS                    = "select municipios.nombre as municipio " +
                                                                          ", sum(arbol_existe), case when (sum(arbol_existe)<>0 OR sum(arbol_falta)<>0) then " +
                                                                          "round(cast((cast(sum(arbol_existe) as double precision)/cast(sum(arbol_existe+arbol_falta) as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") else 0 end " +
                                                                           "from (c1_6_otros_arbolesaislados inner join municipios on " +
                                                                           "c1_6_otros_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_1_6_MUN_US_SubValues                     = "select sum(arbol_existe), sum(arbol_falta), municipios.nombre as municipio " +
                                                                             "from (c1_6_us_arbolesaislados inner join municipios on " +
                                                                             "c1_6_us_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_1_6_MUN_AP_SubValues                     = "select sum(arbol_existe), sum(arbol_falta), municipios.nombre as municipio " +
                                                                             "from (c1_6_ap_arbolesaislados inner join municipios on " +
                                                                             "c1_6_ap_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_1_6_MUN_OTROS_SubValues                  = "select sum(arbol_existe), sum(arbol_falta), municipios.nombre as municipio " +
                                                                             "from (c1_6_otros_arbolesaislados inner join municipios on " +
                                                                             "c1_6_otros_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
 public static final String SQL_1_6_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, arbol_existe, arbol_falta from ((c1_6_us_arbolesaislados inner join municipios on " +
                                                                             "c1_6_us_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c1_6_us_arbolesaislados.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by usilvicola.id, usilvicola.nombre, arbol_existe, arbol_falta;";

 public static final String SQL_1_6_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, arbol_existe, arbol_falta from ((c1_6_ap_arbolesaislados inner join municipios on " +
                                                                             "c1_6_ap_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c1_6_ap_arbolesaislados.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by area_protegida.id, area_protegida.nombre, arbol_existe, arbol_falta;";

 public static final String SQL_1_6_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, arbol_existe, arbol_falta from ((c1_6_otros_arbolesaislados inner join municipios on " +
                                                                             "c1_6_otros_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c1_6_otros_arbolesaislados.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by otros.id, otros.nombre, arbol_existe, arbol_falta;";
//----------------------------------------------------------------------------------
public static final String SQL_1_6_PROV_SubGrupoUS                           = "select sum(arbol_existe), sum(arbol_falta), provincias.nombre " +
                                                                               "from (c1_6_us_arbolesaislados inner join municipios on " +
                                                                               "c1_6_us_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                               "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_1_6_PROV_SubGrupoAP                           = "select sum(arbol_existe), sum(arbol_falta), provincias.nombre " +
                                                                               "from(c1_6_ap_arbolesaislados inner join municipios on " +
                                                                               "c1_6_ap_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                               "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_1_6_PROV_SubGrupoOTROS                        = "select sum(arbol_existe), sum(arbol_falta), provincias.nombre " +
                                                                               "from (c1_6_otros_arbolesaislados inner join municipios on " +
                                                                               "c1_6_otros_arbolesaislados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                               "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_1_6_AP                               = "select arbol_existe as  "+CONSTANTS.ARBOL_EXISTE_COLUMN_NAME+",case when (arbol_existe<>0 OR arbol_falta<>0) then round(cast((cast(arbol_existe as double precision)/cast(arbol_existe+arbol_falta as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") else 0 end as  "+CONSTANTS.NIVEL_COMPORTAMIENTO_COLUMN_NAME+", anno " +
                                                                           "from c1_6_ap_arbolesaislados "+
                                                                           "where c1_6_ap_arbolesaislados.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_1_6_AP_YEARS                         = "select distinct anno from c1_6_ap_arbolesaislados order by anno";
 //......................................................................................................................
   public static final String G_SQL_1_6_US                               = "select arbol_existe as  "+CONSTANTS.ARBOL_EXISTE_COLUMN_NAME+",case when (arbol_existe<>0 OR arbol_falta<>0) then round(cast((cast(arbol_existe as double precision)/cast(arbol_existe+arbol_falta as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") else 0 end as  "+CONSTANTS.NIVEL_COMPORTAMIENTO_COLUMN_NAME+", anno " +
                                                                           "from c1_6_us_arbolesaislados "+
                                                                           "where c1_6_us_arbolesaislados.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_1_6_US_YEARS                         = "select distinct anno from c1_6_us_arbolesaislados order by anno";

  //......................................................................................................................
   public static final String G_SQL_1_6_OTROS_YEARS                      = "select distinct anno from c1_6_otros_arbolesaislados order by anno";

  //......................................................................................................................
   public static final String G_SQL_1_6_EFI                              = "select sum(arbol_existe) as  "+CONSTANTS.ARBOL_EXISTE_COLUMN_NAME+",case when (sum(arbol_existe)<>0 OR sum(arbol_falta)<>0) then round(cast((cast(sum(arbol_existe) as double precision)/cast(sum(arbol_existe+arbol_falta) as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") else 0 end as  "+CONSTANTS.NIVEL_COMPORTAMIENTO_COLUMN_NAME+", anno " +
                                                                           "from c1_6_us_arbolesaislados inner join usilvicola on c1_6_us_arbolesaislados.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_1_6_EFI_YEARS                        = "select distinct anno from c1_6_us_arbolesaislados order by anno";
       //......................................................................................................................
   public static final String G_SQL_1_6_MUN_AP                           = "select sum(arbol_existe),sum(arbol_falta), anno " +
                                                                           "from c1_6_ap_arbolesaislados inner join municipios on c1_6_ap_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_6_MUN_US                           = "select sum(arbol_existe),sum(arbol_falta), anno " +
                                                                           "from c1_6_us_arbolesaislados inner join municipios on c1_6_us_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_6_MUN_OTROS                        = "select sum(arbol_existe),sum(arbol_falta), anno " +
                                                                           "from c1_6_otros_arbolesaislados inner join municipios on c1_6_otros_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_1_6_MUN_YEARS_AP                    = "select distinct anno from c1_6_ap_arbolesaislados where municipio=':id' order by anno";

   public static final String G_SQL_1_6_MUN_YEARS_US                    = "select distinct anno from c1_6_us_arbolesaislados where municipio=':id' order by anno";

   public static final String G_SQL_1_6_MUN_YEARS_OTROS                 = "select distinct anno from c1_6_otros_arbolesaislados where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_1_6_PROV_AP                          = "select sum(arbol_existe),sum(arbol_falta), anno " +
                                                                           "from c1_6_ap_arbolesaislados inner join municipios on c1_6_ap_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_6_PROV_US                           = "select sum(arbol_existe),sum(arbol_falta), anno " +
                                                                           "from c1_6_us_arbolesaislados inner join municipios on c1_6_us_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_6_PROV_OTROS                        = "select sum(arbol_existe),sum(arbol_falta), anno " +
                                                                           "from c1_6_otros_arbolesaislados inner join municipios on c1_6_otros_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_1_6_PROV_YEARS_AP                    = "select distinct anno from c1_6_ap_arbolesaislados inner join municipios on c1_6_ap_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_1_6_PROV_YEARS_US                    = "select distinct anno from c1_6_us_arbolesaislados inner join municipios on c1_6_us_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_1_6_PROV_YEARS_OTROS                 = "select distinct anno from c1_6_otros_arbolesaislados inner join municipios on c1_6_otros_arbolesaislados.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_1_6_Temp_Table                       = "select arbol_existe as  "+CONSTANTS.ARBOL_EXISTE_COLUMN_NAME+",case when (arbol_existe<>0 OR arbol_falta<>0) then round(cast((cast(arbol_existe as double precision)/cast(arbol_existe+arbol_falta as double precision))*100 as numeric) ,"+CONSTANTS.ROUND_PLACES+") else 0 end as  "+CONSTANTS.NIVEL_COMPORTAMIENTO_COLUMN_NAME+", anno " +
                                                                           "from c_1_6_graphicdata order by anno";
   
  //====================== Criterio 2_1 Bosques Afectados por Incendios =====================================================================================================================================================================
  //----------------------------------------------------------------------------------
   public static final String SQL_2_1_AP                                   = "select municipios.nombre as municipio, c2_1_ap_bosquesafectinc.anno as Año, cantidad as "+CONSTANTS.CANTIDAD_COLUMN_NAME+", " +
                                                                             "distanc_recorr as "+CONSTANTS.DISTANCIA_RECORR_COLUMN_NAME+", super_afect_tot as "+CONSTANTS.SUP_AFECT_TOTAL_COLUMN_NAME+", " +
                                                                             "perd_direc as "+CONSTANTS.PERDIDA_DIRECTA_COLUMN_NAME+", perd_indir as "+CONSTANTS.PERDIDA_INDIRECTA_COLUMN_NAME+", " +
                                                                             "hectar_perd as "+CONSTANTS.HECTAREAS_PERD_COLUMN_NAME+", especie.nombre as "+CONSTANTS.ESPECIE_COLUMN_NAME+" "+
                                                                             "from ((c2_1_ap_bosquesafectinc inner join municipios on c2_1_ap_bosquesafectinc.municipio=municipios.id)" +
                                                                             "inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                             "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio)) inner join especie on " +
                                                                             "c2_1_ap_incespecie.especie=especie.id " +
                                                                             "where c2_1_ap_bosquesafectinc.id=':id' and c2_1_ap_bosquesafectinc.anno=:anno";
  //----------------------------------------------------------------------------------
   public static final String SQL_2_1_US                                   = "select municipios.nombre as municipio, c2_1_us_bosquesafectinc.anno as Año, cantidad as "+CONSTANTS.CANTIDAD_COLUMN_NAME+", " +
                                                                             "distanc_recorr as "+CONSTANTS.DISTANCIA_RECORR_COLUMN_NAME+", super_afect_tot as "+CONSTANTS.SUP_AFECT_TOTAL_COLUMN_NAME+", " +
                                                                             "perd_direc as "+CONSTANTS.PERDIDA_DIRECTA_COLUMN_NAME+", perd_indir as "+CONSTANTS.PERDIDA_INDIRECTA_COLUMN_NAME+", " +
                                                                             "hectar_perd as "+CONSTANTS.HECTAREAS_PERD_COLUMN_NAME+", especie.nombre as "+CONSTANTS.ESPECIE_COLUMN_NAME+" "+
                                                                             "from ((c2_1_us_bosquesafectinc inner join municipios on c2_1_us_bosquesafectinc.municipio=municipios.id)" +
                                                                             "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                             "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio))inner join especie on " +
                                                                             "c2_1_us_incespecie.especie=especie.id  " +
                                                                             "where c2_1_us_bosquesafectinc.id=':id' and c2_1_us_bosquesafectinc.anno=:anno";
  //----------------------------------------------------------------------------------
   public static final String SQL_2_1_EFI_Subgrupo                         = "select municipios.nombre as municipio, c2_1_us_bosquesafectinc.anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                             "sum(cantidad) as "+CONSTANTS.CANTIDAD_COLUMN_NAME+", " +
                                                                             "sum(distanc_recorr) as "+CONSTANTS.DISTANCIA_RECORR_COLUMN_NAME+", sum(super_afect_tot) as "+CONSTANTS.SUP_AFECT_TOTAL_COLUMN_NAME+", " +
                                                                             "sum(perd_direc) as "+CONSTANTS.PERDIDA_DIRECTA_COLUMN_NAME+", sum(perd_indir) as "+CONSTANTS.PERDIDA_INDIRECTA_COLUMN_NAME+", " +
                                                                             "sum(hectar_perd) as "+CONSTANTS.HECTAREAS_PERD_COLUMN_NAME+", especie.nombre as "+CONSTANTS.ESPECIE_COLUMN_NAME+" "+
                                                                             "from ((((c2_1_us_bosquesafectinc inner join usilvicola on c2_1_us_bosquesafectinc.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id)inner join municipios on c2_1_us_bosquesafectinc.municipio=municipios.id) " +
                                                                             "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                             "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio)) " +
                                                                             "inner join especie on c2_1_us_incespecie.especie=especie.id " +
                                                                             "where efi.id=':id' and c2_1_us_bosquesafectinc.anno=:anno group by municipios.id, municipios.nombre, c2_1_us_bosquesafectinc.anno, " +
                                                                             "efi.nombre, usilvicola.id, usilvicola.nombre, especie.nombre order by municipios.nombre, c2_1_us_bosquesafectinc.anno, " +
                                                                             "efi.nombre, usilvicola.nombre, especie.nombre";

   public static final String SQL_2_1_EFI_TOTAL1                           = "select efi.nombre, sum(cantidad), sum(distanc_recorr), sum(super_afect_tot) " +
                                                                             "from (c2_1_us_bosquesafectinc inner join usilvicola on c2_1_us_bosquesafectinc.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id " +
                                                                             "where efi.id=':id' and c2_1_us_bosquesafectinc.anno=:anno group by efi.nombre";

   public static final String SQL_2_1_EFI_TOTAL2                           = "select especie.nombre, sum(perd_direc), sum(perd_indir), sum(hectar_perd) " +
                                                                             "from ((((c2_1_us_bosquesafectinc inner join usilvicola on c2_1_us_bosquesafectinc.id=usilvicola.id) " +
                                                                             "inner join efi on usilvicola.efi=efi.id)inner join municipios on c2_1_us_bosquesafectinc.municipio=municipios.id) " +
                                                                             "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                             "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio)) " +
                                                                             "inner join especie on c2_1_us_incespecie.especie=especie.id " +
                                                                             "where efi.id=':id' and c2_1_us_bosquesafectinc.anno=:anno group by especie.nombre";
  //----------------------------------------------------------------------------------
   public static final String SQL_2_1_MUN_SubGrupoUS                       = "select provincias.nombre, municipios.nombre as municipio, sum(cantidad), sum(distanc_recorr), sum(super_afect_tot), " +
                                                                             "sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre as especie " +
                                                                             "from (((c2_1_us_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_us_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                             "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio))inner join especie " +
                                                                             "on c2_1_us_incespecie.especie=especie.id " +
                                                                             "where municipios.id=':id' and c2_1_us_bosquesafectinc.anno=:anno " +
                                                                             "group by provincias.nombre, municipios.id, municipios.nombre, especie.nombre " +
                                                                             "order by provincias.nombre, municipios.id, municipios.nombre, especie.nombre;";

   public static final String SQL_2_1_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre as municipio, sum(cantidad), sum(distanc_recorr), sum(super_afect_tot), " +
                                                                             "sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre as especie " +
                                                                             "from (((c2_1_ap_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_ap_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                             "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio))inner join especie " +
                                                                             "on c2_1_ap_incespecie.especie=especie.id " +
                                                                             "where municipios.id=':id' and c2_1_ap_bosquesafectinc.anno=:anno " +
                                                                             "group by provincias.nombre, municipios.id, municipios.nombre, especie.nombre " +
                                                                             "order by provincias.nombre, municipios.id, municipios.nombre, especie.nombre;";

   public static final String SQL_2_1_MUN_SubGrupoOTROS                    = "select provincias.nombre, municipios.nombre as municipio, sum(cantidad), sum(distanc_recorr), sum(super_afect_tot), " +
                                                                             "sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre as especie " +
                                                                             "from (((c2_1_otros_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_otros_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c2_1_otros_incespecie on (c2_1_otros_bosquesafectinc.id=c2_1_otros_incespecie.id and c2_1_otros_bosquesafectinc.anno" +
                                                                             "=c2_1_otros_incespecie.anno and c2_1_otros_bosquesafectinc.municipio=c2_1_otros_incespecie.municipio))inner join especie " +
                                                                             "on c2_1_otros_incespecie.especie=especie.id " +
                                                                             "where municipios.id=':id' and c2_1_otros_bosquesafectinc.anno=:anno " +
                                                                             "group by provincias.nombre, municipios.id, municipios.nombre, especie.nombre " +
                                                                             "order by provincias.nombre, municipios.id, municipios.nombre, especie.nombre;";
    //----------------------------------------------------------------------------------
 public static final String SQL_2_1_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, cantidad, distanc_recorr, super_afect_tot, " +
                                                                             "perd_direc, perd_indir, hectar_perd, especie.nombre as especie " +
                                                                             "from ((((c2_1_us_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_us_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c2_1_us_bosquesafectinc.id=usilvicola.id)" +
                                                                             "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                             "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio))inner join especie on c2_1_us_incespecie.especie=especie.id " +
                                                                             "where municipios.id=':id' and c2_1_us_bosquesafectinc.anno=:anno;";

 public static final String SQL_2_1_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, cantidad, distanc_recorr, super_afect_tot, " +
                                                                             "perd_direc, perd_indir, hectar_perd, especie.nombre as especie " +
                                                                             "from ((((c2_1_ap_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_ap_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c2_1_ap_bosquesafectinc.id=area_protegida.id)" +
                                                                             "inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                             "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio))inner join especie on c2_1_ap_incespecie.especie=especie.id " +
                                                                             "where municipios.id=':id' and c2_1_ap_bosquesafectinc.anno=:anno;";

 public static final String SQL_2_1_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, cantidad, distanc_recorr, super_afect_tot, " +
                                                                             "perd_direc, perd_indir, hectar_perd, especie.nombre as especie " +
                                                                             "from ((((c2_1_otros_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_otros_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c2_1_otros_bosquesafectinc.id=otros.id)" +
                                                                             "inner join c2_1_otros_incespecie on (c2_1_otros_bosquesafectinc.id=c2_1_otros_incespecie.id and c2_1_otros_bosquesafectinc.anno" +
                                                                             "=c2_1_otros_incespecie.anno and c2_1_otros_bosquesafectinc.municipio=c2_1_otros_incespecie.municipio))inner join especie on c2_1_otros_incespecie.especie=especie.id " +
                                                                             "where municipios.id=':id' and c2_1_otros_bosquesafectinc.anno=:anno;";
  //----------------------------------------------------------------------------------
   public static final String SQL_2_1_PROV_SubGrupoUS                      = "select provincias.nombre, sum(cantidad), sum(distanc_recorr), sum(super_afect_tot), sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre " +
                                                                             "from (((c2_1_us_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_us_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                             "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio))inner join especie on c2_1_us_incespecie.especie=especie.id " +
                                                                             "where c2_1_us_bosquesafectinc.anno=:anno group by provincias.nombre, especie.nombre;";

   public static final String SQL_2_1_PROV_SubGrupoAP                      = "select provincias.nombre, sum(cantidad), sum(distanc_recorr), sum(super_afect_tot), sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre " +
                                                                             "from (((c2_1_ap_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_ap_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                             "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio))inner join especie on c2_1_ap_incespecie.especie=especie.id " +
                                                                             "where c2_1_ap_bosquesafectinc.anno=:anno group by provincias.nombre, especie.nombre;";

   public static final String SQL_2_1_PROV_SubGrupoOTROS                   = "select provincias.nombre, sum(cantidad), sum(distanc_recorr), sum(super_afect_tot), sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre " +
                                                                             "from (((c2_1_otros_bosquesafectinc inner join municipios on " +
                                                                             "c2_1_otros_bosquesafectinc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c2_1_otros_incespecie on (c2_1_otros_bosquesafectinc.id=c2_1_otros_incespecie.id and c2_1_otros_bosquesafectinc.anno" +
                                                                             "=c2_1_otros_incespecie.anno and c2_1_otros_bosquesafectinc.municipio=c2_1_otros_incespecie.municipio))inner join especie on c2_1_otros_incespecie.especie=especie.id " +
                                                                             "where c2_1_otros_bosquesafectinc.anno=:anno group by provincias.nombre, especie.nombre;";

   //...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_2_1_AP                               = "select cantidad as  "+CONSTANTS.CANTIDAD_COLUMN_NAME+",distanc_recorr as  "+CONSTANTS.DISTANCIA_RECORR_COLUMN_NAME+",super_afect_tot as  "+CONSTANTS.SUP_AFECT_TOTAL_COLUMN_NAME+",perd_direc as  "+CONSTANTS.PERDIDA_DIRECTA_COLUMN_NAME+",perd_indir as  "+CONSTANTS.PERDIDA_INDIRECTA_COLUMN_NAME+",hectar_perd as  "+CONSTANTS.HECTAREAS_PERD_COLUMN_NAME+", c2_1_ap_bosquesafectinc.anno " +
                                                                           "from c2_1_ap_bosquesafectinc inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                           "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio)"+
                                                                           "where c2_1_ap_bosquesafectinc.id=':id' and c2_1_ap_bosquesafectinc.anno in(:anno) order by c2_1_ap_bosquesafectinc.anno";

   public static final String G_SQL_2_1_AP_YEARS                         = "select distinct c2_1_ap_bosquesafectinc.anno from c2_1_ap_bosquesafectinc inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                           "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio) order by c2_1_ap_bosquesafectinc.anno";
 //......................................................................................................................
   public static final String G_SQL_2_1_US                               = "select cantidad as  "+CONSTANTS.CANTIDAD_COLUMN_NAME+",distanc_recorr as  "+CONSTANTS.DISTANCIA_RECORR_COLUMN_NAME+",super_afect_tot as  "+CONSTANTS.SUP_AFECT_TOTAL_COLUMN_NAME+"," +
                                                                           "perd_direc as  "+CONSTANTS.PERDIDA_DIRECTA_COLUMN_NAME+",perd_indir as  "+CONSTANTS.PERDIDA_INDIRECTA_COLUMN_NAME+",hectar_perd as  "+CONSTANTS.HECTAREAS_PERD_COLUMN_NAME+", c2_1_us_bosquesafectinc.anno " +
                                                                           "from c2_1_us_bosquesafectinc inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                           "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio) "+
                                                                           "where c2_1_us_bosquesafectinc.id=':id' and c2_1_us_bosquesafectinc.anno in(:anno) order by c2_1_us_bosquesafectinc.anno";

   public static final String G_SQL_2_1_US_YEARS                         = "select distinct c2_1_us_bosquesafectinc.anno from c2_1_us_bosquesafectinc inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                           "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio) order by c2_1_us_bosquesafectinc.anno";

  //......................................................................................................................
   public static final String G_SQL_2_1_OTROS_YEARS                      = "select distinct c2_1_otros_bosquesafectinc.anno from c2_1_otros_bosquesafectinc inner join c2_1_otros_incespecie on (c2_1_otros_bosquesafectinc.id=c2_1_otros_incespecie.id and c2_1_otros_bosquesafectinc.anno" +
                                                                           "=c2_1_otros_incespecie.anno and c2_1_otros_bosquesafectinc.municipio=c2_1_otros_incespecie.municipio) order by c2_1_otros_bosquesafectinc.anno";

  //......................................................................................................................
   public static final String G_SQL_2_1_EFI                              = "select sum(cantidad) as  "+CONSTANTS.CANTIDAD_COLUMN_NAME+",sum(distanc_recorr) as  "+CONSTANTS.DISTANCIA_RECORR_COLUMN_NAME+",sum(super_afect_tot) as  "+
                                                                           CONSTANTS.SUP_AFECT_TOTAL_COLUMN_NAME+",sum(perd_direc) as  "+CONSTANTS.PERDIDA_DIRECTA_COLUMN_NAME+",sum(perd_indir) as  "+CONSTANTS.PERDIDA_INDIRECTA_COLUMN_NAME+
                                                                           ",sum(hectar_perd) as  "+CONSTANTS.HECTAREAS_PERD_COLUMN_NAME+", c2_1_us_bosquesafectinc.anno " +
                                                                           "from (c2_1_us_bosquesafectinc inner join usilvicola on c2_1_us_bosquesafectinc.id=usilvicola.id)" +
                                                                           "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                           "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio) "+
                                                                           "where usilvicola.efi=':id' and c2_1_us_bosquesafectinc.anno in(:anno) " +
                                                                           "group by usilvicola.efi, c2_1_us_bosquesafectinc.anno order by c2_1_us_bosquesafectinc.anno";

   public static final String G_SQL_2_1_EFI_YEARS                        = "select distinct c2_1_us_bosquesafectinc.anno from c2_1_us_bosquesafectinc inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                           "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio) order by c2_1_us_bosquesafectinc.anno";
       //......................................................................................................................
   public static final String G_SQL_2_1_MUN_AP                           = "select sum(cantidad),sum(distanc_recorr),sum(super_afect_tot),sum(perd_direc),sum(perd_indir),sum(hectar_perd), c2_1_ap_bosquesafectinc.anno " +
                                                                           "from (c2_1_ap_bosquesafectinc inner join municipios on c2_1_ap_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                           "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio) "+
                                                                           "where municipios.id=':id' and c2_1_ap_bosquesafectinc.anno in(:anno) " +
                                                                           "group by municipios.id, c2_1_ap_bosquesafectinc.anno order by c2_1_ap_bosquesafectinc.anno";

   public static final String G_SQL_2_1_MUN_US                           = "select sum(cantidad),sum(distanc_recorr),sum(super_afect_tot),sum(perd_direc),sum(perd_indir),sum(hectar_perd), c2_1_us_bosquesafectinc.anno " +
                                                                           "from (c2_1_us_bosquesafectinc inner join municipios on c2_1_us_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                           "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio) "+
                                                                           "where municipios.id=':id' and c2_1_us_bosquesafectinc.anno in(:anno) " +
                                                                           "group by municipios.id, c2_1_us_bosquesafectinc.anno order by c2_1_us_bosquesafectinc.anno";

   public static final String G_SQL_2_1_MUN_OTROS                        = "select sum(cantidad),sum(distanc_recorr),sum(super_afect_tot),sum(perd_direc),sum(perd_indir),sum(hectar_perd), c2_1_otros_bosquesafectinc.anno " +
                                                                           "from (c2_1_otros_bosquesafectinc inner join municipios on c2_1_otros_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_otros_incespecie on (c2_1_otros_bosquesafectinc.id=c2_1_otros_incespecie.id and c2_1_otros_bosquesafectinc.anno" +
                                                                           "=c2_1_otros_incespecie.anno and c2_1_otros_bosquesafectinc.municipio=c2_1_otros_incespecie.municipio) "+
                                                                           "where municipios.id=':id' and c2_1_otros_bosquesafectinc.anno in(:anno) " +
                                                                           "group by municipios.id, c2_1_otros_bosquesafectinc.anno order by c2_1_otros_bosquesafectinc.anno";

   public static final String G_SQL_2_1_MUN_YEARS_AP                    = "select distinct c2_1_ap_bosquesafectinc.anno from c2_1_ap_bosquesafectinc inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                           "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio) " +
                                                                           "where c2_1_ap_bosquesafectinc.municipio=':id' order by c2_1_ap_bosquesafectinc.anno";

   public static final String G_SQL_2_1_MUN_YEARS_US                    = "select distinct c2_1_us_bosquesafectinc.anno from c2_1_us_bosquesafectinc inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                           "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio) " +
                                                                           "where c2_1_us_bosquesafectinc.municipio=':id' order by c2_1_us_bosquesafectinc.anno";

   public static final String G_SQL_2_1_MUN_YEARS_OTROS                 = "select distinct c2_1_otros_bosquesafectinc.anno from c2_1_otros_bosquesafectinc inner join c2_1_otros_incespecie on (c2_1_otros_bosquesafectinc.id=c2_1_otros_incespecie.id and c2_1_otros_bosquesafectinc.anno" +
                                                                           "=c2_1_otros_incespecie.anno and c2_1_otros_bosquesafectinc.municipio=c2_1_otros_incespecie.municipio) " +
                                                                           "where c2_1_otros_bosquesafectinc.municipio=':id' order by c2_1_otros_bosquesafectinc.anno";
//......................................................................................................................
   public static final String G_SQL_2_1_PROV_AP                          = "select sum(cantidad),sum(distanc_recorr),sum(super_afect_tot),sum(perd_direc),sum(perd_indir),sum(hectar_perd), c2_1_ap_bosquesafectinc.anno " +
                                                                           "from (c2_1_ap_bosquesafectinc inner join municipios on c2_1_ap_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                           "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio) "+
                                                                           "where municipios.provincia=':id' and c2_1_ap_bosquesafectinc.anno in(:anno) " +
                                                                           "group by municipios.provincia, c2_1_ap_bosquesafectinc.anno order by c2_1_ap_bosquesafectinc.anno";

   public static final String G_SQL_2_1_PROV_US                           = "select sum(cantidad),sum(distanc_recorr),sum(super_afect_tot),sum(perd_direc),sum(perd_indir),sum(hectar_perd), c2_1_us_bosquesafectinc.anno " +
                                                                           "from (c2_1_us_bosquesafectinc inner join municipios on c2_1_us_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                           "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio) "+
                                                                           "where municipios.provincia=':id' and c2_1_us_bosquesafectinc.anno in(:anno) " +
                                                                           "group by municipios.provincia, c2_1_us_bosquesafectinc.anno order by c2_1_us_bosquesafectinc.anno";

   public static final String G_SQL_2_1_PROV_OTROS                        = "select sum(cantidad),sum(distanc_recorr),sum(super_afect_tot),sum(perd_direc),sum(perd_indir),sum(hectar_perd), c2_1_otros_bosquesafectinc.anno " +
                                                                           "from (c2_1_otros_bosquesafectinc inner join municipios on c2_1_otros_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_otros_incespecie on (c2_1_otros_bosquesafectinc.id=c2_1_otros_incespecie.id and c2_1_otros_bosquesafectinc.anno" +
                                                                           "=c2_1_otros_incespecie.anno and c2_1_otros_bosquesafectinc.municipio=c2_1_otros_incespecie.municipio) "+
                                                                           "where municipios.provincia=':id' and c2_1_otros_bosquesafectinc.anno in(:anno) " +
                                                                           "group by municipios.provincia, c2_1_otros_bosquesafectinc.anno order by c2_1_otros_bosquesafectinc.anno";

   public static final String G_SQL_2_1_PROV_YEARS_AP                    = "select distinct c2_1_ap_bosquesafectinc.anno from (c2_1_ap_bosquesafectinc inner join municipios on c2_1_ap_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_ap_incespecie on (c2_1_ap_bosquesafectinc.id=c2_1_ap_incespecie.id and c2_1_ap_bosquesafectinc.anno" +
                                                                           "=c2_1_ap_incespecie.anno and c2_1_ap_bosquesafectinc.municipio=c2_1_ap_incespecie.municipio) "+
                                                                           "where municipios.provincia=':id' order by c2_1_ap_bosquesafectinc.anno";

   public static final String G_SQL_2_1_PROV_YEARS_US                    = "select distinct c2_1_us_bosquesafectinc.anno from (c2_1_us_bosquesafectinc inner join municipios on c2_1_us_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_us_incespecie on (c2_1_us_bosquesafectinc.id=c2_1_us_incespecie.id and c2_1_us_bosquesafectinc.anno" +
                                                                           "=c2_1_us_incespecie.anno and c2_1_us_bosquesafectinc.municipio=c2_1_us_incespecie.municipio) "+
                                                                           "where municipios.provincia=':id' order by c2_1_us_bosquesafectinc.anno";

   public static final String G_SQL_2_1_PROV_YEARS_OTROS                 = "select distinct c2_1_otros_bosquesafectinc.anno from (c2_1_otros_bosquesafectinc inner join municipios on c2_1_otros_bosquesafectinc.municipio=municipios.id)" +
                                                                           "inner join c2_1_otros_incespecie on (c2_1_otros_bosquesafectinc.id=c2_1_otros_incespecie.id and c2_1_otros_bosquesafectinc.anno" +
                                                                           "=c2_1_otros_incespecie.anno and c2_1_otros_bosquesafectinc.municipio=c2_1_otros_incespecie.municipio) "+
                                                                           "where municipios.provincia=':id' order by c2_1_otros_bosquesafectinc.anno";
       //......................................................................................................................

   public static final String G_SQL_2_1_Temp_Table                       = "select cantidad as  "+CONSTANTS.CANTIDAD_COLUMN_NAME+",distanc_recorr as  "+CONSTANTS.DISTANCIA_RECORR_COLUMN_NAME+",super_afect_tot as " +
                                                                           CONSTANTS.SUP_AFECT_TOTAL_COLUMN_NAME+",perd_direc as  "+CONSTANTS.PERDIDA_DIRECTA_COLUMN_NAME+",perd_indir as " +
                                                                           CONSTANTS.PERDIDA_INDIRECTA_COLUMN_NAME+",hectar_perd as  "+CONSTANTS.HECTAREAS_PERD_COLUMN_NAME+", anno " +
                                                                           "from c_2_1_graphicdata order by anno";

// //====================== Criterio 2_1_2 Incendios Especies =====================================================================================================================================================================
//  //----------------------------------------------------------------------------------
//   public static final String SQL_2_1_2_AP                                 = "select municipios.nombre as municipio, anno as Año, perd_direc, perd_indir, hectar_perd, especie.nombre as especie " +
//                                                                             "from (c2_1_ap_incespecie inner join municipios on c2_1_ap_incespecie.municipio=municipios.id)inner join especie on " +
//                                                                             "c2_1_ap_incespecie.especie=especie.id " +
//                                                                             "where c2_1_ap_incespecie.id=':id' and anno=:anno";
//  //----------------------------------------------------------------------------------
//   public static final String SQL_2_1_2_US                                 = "select municipios.nombre as municipio, anno as Año, perd_direc, perd_indir, hectar_perd, especie.nombre as especie " +
//                                                                             "from (c2_1_us_incespecie inner join municipios on c2_1_us_incespecie.municipio=municipios.id)" +
//                                                                             "inner join especie on c2_1_us_incespecie.especie=especie.id " +
//                                                                             "where c2_1_us_incespecie.id=':id' and anno=:anno";
//  //----------------------------------------------------------------------------------
//   public static final String SQL_2_1_2_EFI_Subgrupo                       = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
//                                                                             "sum(perd_direc) as perd_direc, sum(perd_indir) as perd_indir, sum(hectar_perd) as hectar_perd, especie.nombre as especie " +
//                                                                             "from (((c2_1_us_incespecie inner join usilvicola on c2_1_us_incespecie.id=usilvicola.id) " +
//                                                                             "inner join efi on usilvicola.efi=efi.id)inner join municipios on c2_1_us_incespecie.municipio=municipios.id) " +
//                                                                             "inner join especie on c2_1_us_incespecie.especie=especie.id " +
//                                                                             "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre, especie.nombre";
//
//   public static final String SQL_2_1_2_EFI_TOTAL                          = "select efi.nombre as efi, sum(perd_direc), sum(perd_indir), sum(hectar_perd) " +
//                                                                             "from (c2_1_us_incespecie inner join usilvicola on c2_1_us_incespecie.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
//                                                                             "where efi.id=':id' and anno=:anno group by efi.nombre";
//  //----------------------------------------------------------------------------------
//   public static final String SQL_2_1_2_MUN_SubGrupoUS                     = "select provincias.nombre, municipios.nombre as municipio, sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre " +
//                                                                             "from ((c2_1_us_incespecie inner join municipios on " +
//                                                                             "c2_1_us_incespecie.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c2_1_us_incespecie.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre, especie.nombre;" ;
//
//   public static final String SQL_2_1_2_MUN_SubGrupoAP                     = "select provincias.nombre, municipios.nombre as municipio, sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre " +
//                                                                             "from ((c2_1_ap_incespecie inner join municipios on " +
//                                                                             "c2_1_ap_incespecie.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c2_1_ap_incespecie.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre, especie.nombre;" ;
//
//  public static final String SQL_2_1_2_MUN_SubGrupoOTROS                   = "select provincias.nombre, municipios.nombre as municipio, sum(perd_direc), sum(perd_indir), sum(hectar_perd), especie.nombre " +
//                                                                             "from ((c2_1_otros_incespecie inner join municipios on " +
//                                                                             "c2_1_otros_incespecie.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
//                                                                             "inner join especie on c2_1_otros_incespecie.especie=especie.id " +
//                                                                             "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre, especie.nombre;" ;
//  //----------------------------------------------------------------------------------
//   public static final String SQL_2_1_2_PROV_SubGrupoUS                    = "select provincias.nombre, sum(perd_direc), sum(perd_indir), sum(hectar_perd) " +
//                                                                             "from (c2_1_us_incespecie inner join municipios on " +
//                                                                             "c2_1_us_incespecie.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
//                                                                             "where anno=:anno group by provincias.nombre;";
//
//   public static final String SQL_2_1_2_PROV_SubGrupoAP                    = "select provincias.nombre, sum(perd_direc), sum(perd_indir), sum(hectar_perd) " +
//                                                                             "from (c2_1_ap_incespecie inner join municipios on " +
//                                                                             "c2_1_ap_incespecie.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
//                                                                             "where anno=:anno group by provincias.nombre;";
//
//   public static final String SQL_2_1_2_PROV_SubGrupoOTROS                 = "select provincias.nombre, sum(perd_direc), sum(perd_indir), sum(hectar_perd) " +
//                                                                             "from (c2_1_otros_incespecie inner join municipios on " +
//                                                                             "c2_1_otros_incespecie.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
//                                                                             "where anno=:anno group by provincias.nombre;";
   
//====================== Criterio 2.2 Bosques Afectados =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_2_2_AP                                     = "select municipios.nombre as municipio, c2_2_ap_bosquesafectados.anno as Año, " +
                                                                             "((bosq_nat_exo+plant_estb_exo)/(bosques_nat+plantacion_estb))*100 as "+CONSTANTS.INVASION_ESP_EXOT_COLUMN_NAME+
                                                                             ", ((bosq_nat_past+pant_estb_past)/(bosques_nat+plantacion_estb))*100 as "+CONSTANTS.LIBRE_PAST_COLUMN_NAME+
                                                                             ", plagas_enferm as "+CONSTANTS.PLAGAS_ENFERMEDADES_COLUMN_NAME+", otras_causas as "+CONSTANTS.OTRAS_CAUSAS_COLUMN_NAME+
                                                                             " from (c2_2_ap_bosquesafectados inner join municipios on c2_2_ap_bosquesafectados.municipio=municipios.id)" +
                                                                             "inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where c2_2_ap_bosquesafectados.id=':id' and c2_2_ap_bosquesafectados.anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_2_2_US                                     = "select municipios.nombre as municipio, c2_2_us_bosquesafectados.anno as Año, " +
                                                                             "((bosq_nat_exo+plant_estb_exo)/(bosques_nat+plantacion_estb))*100 as "+CONSTANTS.INVASION_ESP_EXOT_COLUMN_NAME+
                                                                             ", ((bosq_nat_past+pant_estb_past)/(bosques_nat+plantacion_estb))*100 as "+CONSTANTS.LIBRE_PAST_COLUMN_NAME+
                                                                             ", plagas_enferm as "+CONSTANTS.PLAGAS_ENFERMEDADES_COLUMN_NAME+", otras_causas as "+CONSTANTS.OTRAS_CAUSAS_COLUMN_NAME+
                                                                             " from (c2_2_us_bosquesafectados inner join municipios on c2_2_us_bosquesafectados.municipio=municipios.id)" +
                                                                             "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where c2_2_us_bosquesafectados.id=':id' and c2_2_us_bosquesafectados.anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_2_2_EFI_Subgrupo                            = "select municipios.nombre as municipio, c2_2_us_bosquesafectados.anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                             "(sum(bosq_nat_exo+plant_estb_exo)/sum(bosques_nat+plantacion_estb))*100 as "+CONSTANTS.INVASION_ESP_EXOT_COLUMN_NAME+
                                                                             ", (sum(bosq_nat_past+pant_estb_past)/sum(bosques_nat+plantacion_estb))*100 as "+CONSTANTS.LIBRE_PAST_COLUMN_NAME+
                                                                             ", sum(plagas_enferm) as "+CONSTANTS.PLAGAS_ENFERMEDADES_COLUMN_NAME+", sum(otras_causas) as "+CONSTANTS.OTRAS_CAUSAS_COLUMN_NAME+
                                                                             " from (((c2_2_us_bosquesafectados inner join municipios on c2_2_us_bosquesafectados.municipio=municipios.id)" +
                                                                             "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno))" +
                                                                             "inner join usilvicola on c2_2_us_bosquesafectados.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                             "where efi.id=':id' and c2_2_us_bosquesafectados.anno=:anno " +
                                                                             "group by municipios.id, municipios.nombre, c2_2_us_bosquesafectados.anno, efi.nombre, usilvicola.nombre";

public static final String SQL_2_2_EFI_TOTAL                               = "select efi.nombre, " +
                                                                             "(sum(bosq_nat_exo+plant_estb_exo)/sum(bosques_nat+plantacion_estb))*100 as "+CONSTANTS.INVASION_ESP_EXOT_COLUMN_NAME+
                                                                             ", (sum(bosq_nat_past+pant_estb_past)/sum(bosques_nat+plantacion_estb))*100 as "+CONSTANTS.LIBRE_PAST_COLUMN_NAME+
                                                                             ", sum(plagas_enferm) as "+CONSTANTS.PLAGAS_ENFERMEDADES_COLUMN_NAME+", sum(otras_causas) as "+CONSTANTS.OTRAS_CAUSAS_COLUMN_NAME+
                                                                             " from (((c2_2_us_bosquesafectados inner join municipios on c2_2_us_bosquesafectados.municipio=municipios.id)" +
                                                                             "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno))" +
                                                                             "inner join usilvicola on c2_2_us_bosquesafectados.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                             "where efi.id=':id' and c2_2_us_bosquesafectados.anno=:anno " +
                                                                             "group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_2_2_MUN_SubGrupoUS                        = "select municipios.nombre as municipio" +
                                                                           ", (sum(bosq_nat_exo+plant_estb_exo)/sum(bosques_nat+plantacion_estb))*100, (sum(bosq_nat_past+pant_estb_past)/sum(bosques_nat+plantacion_estb))*100, " +
                                                                           "sum(plagas_enferm), sum(otras_causas) " +
                                                                           "from ((c2_2_us_bosquesafectados inner join municipios on " +
                                                                           "c2_2_us_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                           "where municipios.id=':id' and c2_2_us_bosquesafectados.anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_2_2_MUN_SubGrupoAP                        = "select municipios.nombre as municipio " +
                                                                           ", (sum(bosq_nat_exo+plant_estb_exo)/sum(bosques_nat+plantacion_estb))*100, (sum(bosq_nat_past+pant_estb_past)/sum(bosques_nat+plantacion_estb))*100, " +
                                                                           "sum(plagas_enferm), sum(otras_causas) " +
                                                                           "from ((c2_2_ap_bosquesafectados inner join municipios on " +
                                                                           "c2_2_ap_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                           "where municipios.id=':id' and c2_2_ap_bosquesafectados.anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_2_2_MUN_SubGrupoOTROS                     = "select municipios.nombre as municipio " +
                                                                           ", (sum(bosq_nat_exo+plant_estb_exo)/sum(bosques_nat+plantacion_estb))*100, (sum(bosq_nat_past+pant_estb_past)/sum(bosques_nat+plantacion_estb))*100, " +
                                                                           "sum(plagas_enferm), sum(otras_causas) " +
                                                                           "from ((c2_2_otros_bosquesafectados inner join municipios on " +
                                                                           "c2_2_otros_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                           "where municipios.id=':id' and c2_2_otros_bosquesafectados.anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_2_2_MUN_US_SubValues                     = "select sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas), municipios.nombre as municipio " +
                                                                             "from ((c2_2_us_bosquesafectados inner join municipios on " +
                                                                             "c2_2_us_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c2_2_us_bosquesafectados.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_2_2_MUN_AP_SubValues                     = "select sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas), municipios.nombre as municipio " +
                                                                             "from ((c2_2_ap_bosquesafectados inner join municipios on " +
                                                                             "c2_2_ap_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c2_2_ap_bosquesafectados.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_2_2_MUN_OTROS_SubValues                  = "select sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas), municipios.nombre as municipio " +
                                                                             "from ((c2_2_otros_bosquesafectados inner join municipios on " +
                                                                             "c2_2_otros_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where municipios.id=':id' and c2_2_otros_bosquesafectados.anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_2_2_MUN_SubGrupoUS_Entities                 = "select usilvicola.nombre, sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas) " +
                                                                             "from (((c2_2_us_bosquesafectados inner join municipios on " +
                                                                             "c2_2_us_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno))" +
                                                                             "inner join usilvicola on c2_2_us_bosquesafectados.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and c2_2_us_bosquesafectados.anno=:anno " +
                                                                             "group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_2_2_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas) " +
                                                                             "from (((c2_2_ap_bosquesafectados inner join municipios on " +
                                                                             "c2_2_ap_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno))" +
                                                                             "inner join area_protegida on c2_2_ap_bosquesafectados.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and c2_2_ap_bosquesafectados.anno=:anno " +
                                                                             "group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_2_2_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas) " +
                                                                             "from (((c2_2_otros_bosquesafectados inner join municipios on " +
                                                                             "c2_2_otros_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno))" +
                                                                             "inner join otros on c2_2_otros_bosquesafectados.id=otros.id " +
                                                                             "where municipios.id=':id' and c2_2_otros_bosquesafectados.anno=:anno " +
                                                                             "group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_2_2_PROV_SubGrupoUS                         = "select sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas), provincias.nombre " +
                                                                             "from ((c2_2_us_bosquesafectados inner join municipios on " +
                                                                             "c2_2_us_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                             "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                             "where c2_2_us_bosquesafectados.anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_2_2_PROV_SubGrupoAP                         = "select sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas), provincias.nombre " +
                                                                             "from ((c2_2_ap_bosquesafectados inner join municipios on " +
                                                                             "c2_2_ap_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                             "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                             "where c2_2_ap_bosquesafectados.anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_2_2_PROV_SubGrupoOTROS                      = "select sum(bosques_nat)+sum(plantacion_estb), sum(bosq_nat_exo+plant_estb_exo), sum(bosq_nat_past+pant_estb_past), " +
                                                                             "sum(plagas_enferm), sum(otras_causas), provincias.nombre " +
                                                                             "from ((c2_2_otros_bosquesafectados inner join municipios on " +
                                                                             "c2_2_otros_bosquesafectados.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                             "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                             "where c2_2_otros_bosquesafectados.anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_2_2_AP                               = "select case when (bosques_nat+plantacion_estb)<>0 then ((bosq_nat_exo+plant_estb_exo)/(bosques_nat+plantacion_estb))*100 else 0 end as " +
                                                                           " "+CONSTANTS.INVASION_ESP_EXOT_COLUMN_NAME+",case when (bosques_nat+plantacion_estb)<>0 then ((bosq_nat_past+pant_estb_past)/(bosques_nat+plantacion_estb))*100 else 0 end as " +
                                                                           " "+CONSTANTS.LIBRE_PAST_COLUMN_NAME+",plagas_enferm as  "+CONSTANTS.PLAGAS_ENFERMEDADES_COLUMN_NAME+",otras_causas as  "+CONSTANTS.OTRAS_CAUSAS_COLUMN_NAME+", c2_2_ap_bosquesafectados.anno " +
                                                                           "from c2_2_ap_bosquesafectados inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                           "where c2_2_ap_bosquesafectados.id=':id' and c2_2_ap_bosquesafectados.anno in(:anno) order by c2_2_ap_bosquesafectados.anno";

   public static final String G_SQL_2_2_AP_YEARS                         = "select distinct c2_2_ap_bosquesafectados.anno from c2_2_ap_bosquesafectados inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                           "order by c2_2_ap_bosquesafectados.anno";
 //......................................................................................................................
   public static final String G_SQL_2_2_US                               = "select case when (bosques_nat+plantacion_estb)<>0 then ((bosq_nat_exo+plant_estb_exo)/(bosques_nat+plantacion_estb))*100 else 0 end as  "+CONSTANTS.INVASION_ESP_EXOT_COLUMN_NAME+",case when (bosques_nat+plantacion_estb)<>0 then ((bosq_nat_past+pant_estb_past)/(bosques_nat+plantacion_estb))*100 else 0 end as  "+CONSTANTS.LIBRE_PAST_COLUMN_NAME+",plagas_enferm as  "+CONSTANTS.PLAGAS_ENFERMEDADES_COLUMN_NAME+",otras_causas as  "+CONSTANTS.OTRAS_CAUSAS_COLUMN_NAME+", c2_2_us_bosquesafectados.anno " +
                                                                           "from c2_2_us_bosquesafectados inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                           "where c2_2_us_bosquesafectados.id=':id' and c2_2_us_bosquesafectados.anno in(:anno) order by c2_2_us_bosquesafectados.anno";

   public static final String G_SQL_2_2_US_YEARS                         = "select distinct c2_2_us_bosquesafectados.anno from c2_2_us_bosquesafectados inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                           "order by c2_2_us_bosquesafectados.anno";

  //......................................................................................................................
   public static final String G_SQL_2_2_OTROS_YEARS                      = "select distinct c2_2_otros_bosquesafectados.anno from c2_2_otros_bosquesafectados inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                           "order by c2_2_otros_bosquesafectados.anno";

  //......................................................................................................................
   public static final String G_SQL_2_2_EFI                              = "select case when sum(bosques_nat+plantacion_estb)<>0 then (sum(bosq_nat_exo+plant_estb_exo)/sum(bosques_nat+plantacion_estb))*100 else 0 end as  "+CONSTANTS.INVASION_ESP_EXOT_COLUMN_NAME+",case when sum(bosques_nat+plantacion_estb)<>0 then (sum(bosq_nat_past+pant_estb_past)/sum(bosques_nat+plantacion_estb))*100 else 0 end as  "+CONSTANTS.LIBRE_PAST_COLUMN_NAME+",sum(plagas_enferm) as  "+CONSTANTS.PLAGAS_ENFERMEDADES_COLUMN_NAME+",sum(otras_causas) as  "+CONSTANTS.OTRAS_CAUSAS_COLUMN_NAME+", c2_2_us_bosquesafectados.anno " +
                                                                           "from (c2_2_us_bosquesafectados inner join usilvicola on c2_2_us_bosquesafectados.id=usilvicola.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                           "where usilvicola.efi=':id' and c2_2_us_bosquesafectados.anno in(:anno) " +
                                                                           "group by usilvicola.efi, c2_2_us_bosquesafectados.anno order by c2_2_us_bosquesafectados.anno";

   public static final String G_SQL_2_2_EFI_YEARS                        = "select distinct c2_2_us_bosquesafectados.anno from c2_2_us_bosquesafectados inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                           "order by c2_2_us_bosquesafectados.anno";
       //......................................................................................................................
   public static final String G_SQL_2_2_MUN_AP                           = "select sum(bosques_nat),sum(plantacion_estb),sum(bosq_nat_exo),sum(plant_estb_exo),sum(bosq_nat_past),sum(pant_estb_past),sum(plagas_enferm),sum(otras_causas), c2_2_ap_bosquesafectados.anno " +
                                                                           "from (c2_2_ap_bosquesafectados inner join municipios on c2_2_ap_bosquesafectados.municipio=municipios.id)" +
                                                                           "inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                           "where municipios.id=':id' and c2_2_ap_bosquesafectados.anno in(:anno) " +
                                                                           "group by municipios.id, c2_2_ap_bosquesafectados.anno order by c2_2_ap_bosquesafectados.anno";

   public static final String G_SQL_2_2_MUN_US                           = "select sum(bosques_nat),sum(plantacion_estb),sum(bosq_nat_exo),sum(plant_estb_exo),sum(bosq_nat_past),sum(pant_estb_past),sum(plagas_enferm),sum(otras_causas), c2_2_us_bosquesafectados.anno " +
                                                                           "from (c2_2_us_bosquesafectados inner join municipios on c2_2_us_bosquesafectados.municipio=municipios.id)" +
                                                                           "inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                           "where municipios.id=':id' and c2_2_us_bosquesafectados.anno in(:anno) " +
                                                                           "group by municipios.id, c2_2_us_bosquesafectados.anno order by c2_2_us_bosquesafectados.anno";

   public static final String G_SQL_2_2_MUN_OTROS                        = "select sum(bosques_nat),sum(plantacion_estb),sum(bosq_nat_exo),sum(plant_estb_exo),sum(bosq_nat_past),sum(pant_estb_past),sum(plagas_enferm),sum(otras_causas), c2_2_otros_bosquesafectados.anno " +
                                                                           "from (c2_2_otros_bosquesafectados inner join municipios on c2_2_otros_bosquesafectados.municipio=municipios.id)inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno) "+ 
                                                                           "where municipios.id=':id' and c2_2_otros_bosquesafectados.anno in(:anno) " +
                                                                           "group by municipios.id, c2_2_otros_bosquesafectados.anno order by c2_2_otros_bosquesafectados.anno";

   public static final String G_SQL_2_2_MUN_YEARS_AP                    = "select distinct c2_2_ap_bosquesafectados.anno from c2_2_ap_bosquesafectados inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                           "where c2_2_ap_bosquesafectados.municipio=':id' order by c2_2_ap_bosquesafectados.anno";

   public static final String G_SQL_2_2_MUN_YEARS_US                    = "select distinct c2_2_us_bosquesafectados.anno from c2_2_us_bosquesafectados inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                          "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                          "where c2_2_us_bosquesafectados.municipio=':id' order by c2_2_us_bosquesafectados.anno";

   public static final String G_SQL_2_2_MUN_YEARS_OTROS                 = "select distinct c2_2_otros_bosquesafectados.anno from c2_2_otros_bosquesafectados inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                          "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno) " +
                                                                          "where c2_2_otros_bosquesafectados.municipio=':id' order by c2_2_otros_bosquesafectados.anno";
//......................................................................................................................
   public static final String G_SQL_2_2_PROV_AP                          = "select sum(bosques_nat),sum(plantacion_estb),sum(bosq_nat_exo),sum(plant_estb_exo),sum(bosq_nat_past),sum(pant_estb_past),sum(plagas_enferm),sum(otras_causas), c2_2_ap_bosquesafectados.anno " +
                                                                           "from (c2_2_ap_bosquesafectados inner join municipios on c2_2_ap_bosquesafectados.municipio=municipios.id)inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                           "where municipios.provincia=':id' and c2_2_ap_bosquesafectados.anno in(:anno) " +
                                                                           "group by municipios.provincia, c2_2_ap_bosquesafectados.anno order by c2_2_ap_bosquesafectados.anno";

   public static final String G_SQL_2_2_PROV_US                           = "select sum(bosques_nat),sum(plantacion_estb),sum(bosq_nat_exo),sum(plant_estb_exo),sum(bosq_nat_past),sum(pant_estb_past),sum(plagas_enferm),sum(otras_causas), c2_2_us_bosquesafectados.anno " +
                                                                           "from (c2_2_us_bosquesafectados inner join municipios on c2_2_us_bosquesafectados.municipio=municipios.id)inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                           "where municipios.provincia=':id' and c2_2_us_bosquesafectados.anno in(:anno) " +
                                                                           "group by municipios.provincia, c2_2_us_bosquesafectados.anno order by c2_2_us_bosquesafectados.anno";

   public static final String G_SQL_2_2_PROV_OTROS                        = "select sum(bosques_nat),sum(plantacion_estb),sum(bosq_nat_exo),sum(plant_estb_exo),sum(bosq_nat_past),sum(pant_estb_past),sum(plagas_enferm),sum(otras_causas), c2_2_otros_bosquesafectados.anno " +
                                                                           "from (c2_2_otros_bosquesafectados inner join municipios on c2_2_otros_bosquesafectados.municipio=municipios.id)inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno) "+ 
                                                                           "where municipios.provincia=':id' and c2_2_otros_bosquesafectados.anno in(:anno) " +
                                                                           "group by municipios.provincia, c2_2_otros_bosquesafectados.anno order by c2_2_otros_bosquesafectados.anno";

   public static final String G_SQL_2_2_PROV_YEARS_AP                    = "select distinct c2_2_ap_bosquesafectados.anno from (c2_2_ap_bosquesafectados inner join municipios on c2_2_ap_bosquesafectados.municipio=municipios.id)inner join c1_1_ap_totalareacubierta on (c2_2_ap_bosquesafectados.id=c1_1_ap_totalareacubierta.id and " +
                                                                           "c2_2_ap_bosquesafectados.municipio=c1_1_ap_totalareacubierta.municipio and c2_2_ap_bosquesafectados.anno=c1_1_ap_totalareacubierta.anno) " +
                                                                           "where municipios.provincia=':id' order by c2_2_ap_bosquesafectados.anno";

   public static final String G_SQL_2_2_PROV_YEARS_US                    = "select distinct c2_2_us_bosquesafectados.anno from (c2_2_us_bosquesafectados inner join municipios on c2_2_us_bosquesafectados.municipio=municipios.id)inner join c1_1_us_totalareacubierta on (c2_2_us_bosquesafectados.id=c1_1_us_totalareacubierta.id and " +
                                                                           "c2_2_us_bosquesafectados.municipio=c1_1_us_totalareacubierta.municipio and c2_2_us_bosquesafectados.anno=c1_1_us_totalareacubierta.anno) " +
                                                                           "where municipios.provincia=':id' order by c2_2_us_bosquesafectados.anno";

   public static final String G_SQL_2_2_PROV_YEARS_OTROS                 = "select distinct c2_2_otros_bosquesafectados.anno from (c2_2_otros_bosquesafectados inner join municipios on c2_2_otros_bosquesafectados.municipio=municipios.id)inner join c1_1_otros_totalareacubierta on (c2_2_otros_bosquesafectados.id=c1_1_otros_totalareacubierta.id and " +
                                                                           "c2_2_otros_bosquesafectados.municipio=c1_1_otros_totalareacubierta.municipio and c2_2_otros_bosquesafectados.anno=c1_1_otros_totalareacubierta.anno) "+ 
                                                                           "where municipios.provincia=':id' order by c2_2_otros_bosquesafectados.anno";
       //......................................................................................................................

   public static final String G_SQL_2_2_Temp_Table                       = "select case when (bosques_nat+plantacion_estb)<>0 then ((bosq_nat_exo+plant_estb_exo)/(bosques_nat+plantacion_estb))*100 else 0 end as  "+CONSTANTS.INVASION_ESP_EXOT_COLUMN_NAME+",case when (bosques_nat+plantacion_estb)<>0 then ((bosq_nat_past+pant_estb_past)/(bosques_nat+plantacion_estb))*100 else 0 end as  "+CONSTANTS.LIBRE_PAST_COLUMN_NAME+",plagas_enferm as  "+CONSTANTS.PLAGAS_ENFERMEDADES_COLUMN_NAME+",otras_causas as  "+CONSTANTS.OTRAS_CAUSAS_COLUMN_NAME+", anno " +
                                                                           "from c_2_2_graphicdata order by anno";

//====================== Criterio 2.3 Bosques de Manejos Silvicolas =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_2_3_AP                                   = "select municipios.nombre as municipio, anno as Año, case when sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_raleo + superf_ejec_limp + superf_ejec_poda + superf_ejec_rec)/sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec) " +
                                                                           "else 0 end as "+CONSTANTS.TOTAL_COLUMN_NAME+", case when nec_anual_realeo<>0 then superf_ejec_raleo/nec_anual_realeo else 0 end as "+CONSTANTS.RALEO_COLUMN_NAME+", " +
                                                                           "case when nec_anual_limp<>0 then superf_ejec_limp/nec_anual_limp else 0 end as "+CONSTANTS.LIMPIA_COLUMN_NAME+", " +
                                                                           "case when nec_anual_poda<>0 then superf_ejec_poda/nec_anual_poda else 0 end as "+CONSTANTS.PODA_COLUMN_NAME+", case when nec_anual_rec<>0 then " +
                                                                           "superf_ejec_rec/nec_anual_rec else 0 end as "+CONSTANTS.RECONSTRUCCION_COLUMN_NAME+" "+
                                                                           "from c2_3_ap_bosquesmanejslvicolas inner join municipios on c2_3_ap_bosquesmanejslvicolas.municipio=municipios.id " +
                                                                           "where c2_3_ap_bosquesmanejslvicolas.id=':id' and anno=:anno " +
                                                                           "group by anno, municipios.id, municipios.nombre, superf_ejec_raleo, superf_ejec_limp, superf_ejec_poda, " +
                                                                           "superf_ejec_rec, nec_anual_realeo, nec_anual_limp, nec_anual_poda, nec_anual_rec";
//----------------------------------------------------------------------------------
public static final String SQL_2_3_US                                    = "select municipios.nombre as municipio, anno as Año, case when sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_raleo + superf_ejec_limp + superf_ejec_poda + superf_ejec_rec)/sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec) " +
                                                                           "else 0 end as "+CONSTANTS.TOTAL_COLUMN_NAME+", case when nec_anual_realeo<>0 then superf_ejec_raleo/nec_anual_realeo else 0 end as "+CONSTANTS.RALEO_COLUMN_NAME+", " +
                                                                           "case when nec_anual_limp<>0 then superf_ejec_limp/nec_anual_limp else 0 end as "+CONSTANTS.LIMPIA_COLUMN_NAME+", " +
                                                                           "case when nec_anual_poda<>0 then superf_ejec_poda/nec_anual_poda else 0 end as "+CONSTANTS.PODA_COLUMN_NAME+", case when nec_anual_rec<>0 then " +
                                                                           "superf_ejec_rec/nec_anual_rec else 0 end as "+CONSTANTS.RECONSTRUCCION_COLUMN_NAME+" "+
                                                                           " from c2_3_us_bosquesmanejslvicolas inner join municipios on c2_3_us_bosquesmanejslvicolas.municipio=municipios.id " +
                                                                           "where c2_3_us_bosquesmanejslvicolas.id=':id' and anno=:anno " +
                                                                           "group by anno, municipios.id, municipios.nombre, superf_ejec_raleo, superf_ejec_limp, superf_ejec_poda, " +
                                                                           "superf_ejec_rec, nec_anual_realeo, nec_anual_limp, nec_anual_poda, nec_anual_rec";
//----------------------------------------------------------------------------------
public static final String SQL_2_3_EFI_Subgrupo                          = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                           ", case when sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_raleo + superf_ejec_limp + superf_ejec_poda + superf_ejec_rec)/sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec) " +
                                                                           "else 0 end as "+CONSTANTS.TOTAL_COLUMN_NAME+", case when sum(nec_anual_realeo)<>0 then sum(superf_ejec_raleo)/sum(nec_anual_realeo) else 0 end as "+CONSTANTS.RALEO_COLUMN_NAME+", " +
                                                                           "case when sum(nec_anual_limp)<>0 then sum(superf_ejec_limp)/sum(nec_anual_limp) else 0 end as "+CONSTANTS.LIMPIA_COLUMN_NAME+", " +
                                                                           "case when sum(nec_anual_poda)<>0 then sum(superf_ejec_poda)/sum(nec_anual_poda) else 0 end as "+CONSTANTS.PODA_COLUMN_NAME+", case when sum(nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_rec)/sum(nec_anual_rec) else 0 end as "+CONSTANTS.RECONSTRUCCION_COLUMN_NAME+" "+
                                                                           "from ((c2_3_us_bosquesmanejslvicolas inner join usilvicola on c2_3_us_bosquesmanejslvicolas.id=usilvicola.id) " +
                                                                           "inner join efi on usilvicola.efi=efi.id)inner join municipios on c2_3_us_bosquesmanejslvicolas.municipio=municipios.id " +
                                                                           "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_2_3_EFI_TOTAL                             = "select efi.nombre as efi" +
                                                                           ", case when sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_raleo + superf_ejec_limp + superf_ejec_poda + superf_ejec_rec)/sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec) " +
                                                                           "else 0 end, case when sum(nec_anual_realeo)<>0 then sum(superf_ejec_raleo)/sum(nec_anual_realeo) else 0 end, " +
                                                                           "case when sum(nec_anual_limp)<>0 then sum(superf_ejec_limp)/sum(nec_anual_limp) else 0 end, " +
                                                                           "case when sum(nec_anual_poda)<>0 then sum(superf_ejec_poda)/sum(nec_anual_poda) else 0 end, case when sum(nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_rec)/sum(nec_anual_rec) else 0 end "+
                                                                           "from (c2_3_us_bosquesmanejslvicolas inner join usilvicola on c2_3_us_bosquesmanejslvicolas.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_2_3_MUN_SubGrupoUS                        = "select municipios.nombre as municipio" +
                                                                           ", case when sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_raleo + superf_ejec_limp + superf_ejec_poda + superf_ejec_rec)/sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec) " +
                                                                           "else 0 end, case when sum(nec_anual_realeo)<>0 then sum(superf_ejec_raleo)/sum(nec_anual_realeo) else 0 end, " +
                                                                           "case when sum(nec_anual_limp)<>0 then sum(superf_ejec_limp)/sum(nec_anual_limp) else 0 end, " +
                                                                           "case when sum(nec_anual_poda)<>0 then sum(superf_ejec_poda)/sum(nec_anual_poda) else 0 end, case when sum(nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_rec)/sum(nec_anual_rec) else 0 end " +
                                                                           "from (c2_3_us_bosquesmanejslvicolas inner join municipios on " +
                                                                           "c2_3_us_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_2_3_MUN_SubGrupoAP                        = "select municipios.nombre as municipio " +
                                                                           ", case when sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_raleo + superf_ejec_limp + superf_ejec_poda + superf_ejec_rec)/sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec) " +
                                                                           "else 0 end, case when sum(nec_anual_realeo)<>0 then sum(superf_ejec_raleo)/sum(nec_anual_realeo) else 0 end, " +
                                                                           "case when sum(nec_anual_limp)<>0 then sum(superf_ejec_limp)/sum(nec_anual_limp) else 0 end, " +
                                                                           "case when sum(nec_anual_poda)<>0 then sum(superf_ejec_poda)/sum(nec_anual_poda) else 0 end, case when sum(nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_rec)/sum(nec_anual_rec) else 0 end " +
                                                                           "from (c2_3_ap_bosquesmanejslvicolas inner join municipios on " +
                                                                           "c2_3_ap_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_2_3_MUN_SubGrupoOTROS                     = "select municipios.nombre as municipio " +
                                                                           ", case when sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_raleo + superf_ejec_limp + superf_ejec_poda + superf_ejec_rec)/sum(nec_anual_realeo + nec_anual_limp + nec_anual_poda + nec_anual_rec) " +
                                                                           "else 0 end, case when sum(nec_anual_realeo)<>0 then sum(superf_ejec_raleo)/sum(nec_anual_realeo) else 0 end, " +
                                                                           "case when sum(nec_anual_limp)<>0 then sum(superf_ejec_limp)/sum(nec_anual_limp) else 0 end, " +
                                                                           "case when sum(nec_anual_poda)<>0 then sum(superf_ejec_poda)/sum(nec_anual_poda) else 0 end, case when sum(nec_anual_rec)<>0 then " +
                                                                           "sum(superf_ejec_rec)/sum(nec_anual_rec) else 0 end " +
                                                                           "from (c2_3_otros_bosquesmanejslvicolas inner join municipios on " +
                                                                           "c2_3_otros_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_2_3_MUN_US_SubValues                     = "select sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                             "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec), municipios.nombre as municipio " +
                                                                             "from (c2_3_us_bosquesmanejslvicolas inner join municipios on " +
                                                                             "c2_3_us_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_2_3_MUN_AP_SubValues                     = "select sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                             "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec), municipios.nombre as municipio " +
                                                                             "from (c2_3_ap_bosquesmanejslvicolas inner join municipios on " +
                                                                             "c2_3_ap_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_2_3_MUN_OTROS_SubValues                  = "select sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                             "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec), municipios.nombre as municipio " +
                                                                             "from (c2_3_otros_bosquesmanejslvicolas inner join municipios on " +
                                                                             "c2_3_otros_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
 public static final String SQL_2_3_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                             "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec) " +
                                                                             "from ((c2_3_us_bosquesmanejslvicolas inner join municipios on " +
                                                                             "c2_3_us_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c2_3_us_bosquesmanejslvicolas.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by usilvicola.id, usilvicola.nombre;;";

 public static final String SQL_2_3_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                             "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec) " +
                                                                             "from ((c2_3_ap_bosquesmanejslvicolas inner join municipios on " +
                                                                             "c2_3_ap_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c2_3_ap_bosquesmanejslvicolas.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_2_3_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                             "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec) " +
                                                                             "from ((c2_3_otros_bosquesmanejslvicolas inner join municipios on " +
                                                                             "c2_3_otros_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c2_3_otros_bosquesmanejslvicolas.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno " +
                                                                             "group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_2_3_PROV_SubGrupoUS                       = "select sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                           "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec), provincias.nombre " +
                                                                           "from (c2_3_us_bosquesmanejslvicolas inner join municipios on " +
                                                                           "c2_3_us_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_2_3_PROV_SubGrupoAP                       = "select sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                           "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec), provincias.nombre " +
                                                                           "from (c2_3_ap_bosquesmanejslvicolas inner join municipios on " +
                                                                           "c2_3_ap_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_2_3_PROV_SubGrupoOTROS                    = "select sum(superf_ejec_raleo), sum(superf_ejec_limp), sum(superf_ejec_poda), sum(superf_ejec_rec), sum(nec_anual_realeo), " +
                                                                           "sum(nec_anual_limp), sum(nec_anual_poda), sum(nec_anual_rec), provincias.nombre " +
                                                                           "from (c2_3_otros_bosquesmanejslvicolas inner join municipios on " +
                                                                           "c2_3_otros_bosquesmanejslvicolas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";
//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_2_3_AP                               = "select case when nec_anual_realeo<>0 then superf_ejec_raleo/nec_anual_realeo else 0 end as  "+CONSTANTS.RALEO_COLUMN_NAME+",case when nec_anual_limp<>0 then superf_ejec_limp/nec_anual_limp else 0 end as  "+CONSTANTS.LIMPIA_COLUMN_NAME+",case when nec_anual_poda<>0 then superf_ejec_poda/nec_anual_poda else 0 end as  "+CONSTANTS.PODA_COLUMN_NAME+",case when nec_anual_rec<>0 then superf_ejec_rec/nec_anual_rec else 0 end as  "+CONSTANTS.RECONSTRUCCION_COLUMN_NAME+", anno " +
                                                                           "from c2_3_ap_bosquesmanejslvicolas "+
                                                                           "where c2_3_ap_bosquesmanejslvicolas.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_2_3_AP_YEARS                         = "select distinct anno from c2_3_ap_bosquesmanejslvicolas order by anno";
 //......................................................................................................................
   public static final String G_SQL_2_3_US                               = "select case when nec_anual_realeo<>0 then superf_ejec_raleo/nec_anual_realeo else 0 end as  "+CONSTANTS.RALEO_COLUMN_NAME+",case when nec_anual_limp<>0 then superf_ejec_limp/nec_anual_limp else 0 end as  "+CONSTANTS.LIMPIA_COLUMN_NAME+",case when nec_anual_poda<>0 then superf_ejec_poda/nec_anual_poda else 0 end as  "+CONSTANTS.PODA_COLUMN_NAME+",case when nec_anual_rec<>0 then superf_ejec_rec/nec_anual_rec else 0 end as  "+CONSTANTS.RECONSTRUCCION_COLUMN_NAME+", anno " +
                                                                           "from c2_3_us_bosquesmanejslvicolas "+
                                                                           "where c2_3_us_bosquesmanejslvicolas.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_2_3_US_YEARS                         = "select distinct anno from c2_3_us_bosquesmanejslvicolas order by anno";

  //......................................................................................................................
   public static final String G_SQL_2_3_OTROS_YEARS                      = "select distinct anno from c2_3_otros_bosquesmanejslvicolas order by anno";

  //......................................................................................................................
   public static final String G_SQL_2_3_EFI                              = "select case when sum(nec_anual_realeo)<>0 then sum(superf_ejec_raleo)/sum(nec_anual_realeo) else 0 end as  "+CONSTANTS.RALEO_COLUMN_NAME+",case when sum(nec_anual_limp)<>0 then sum(superf_ejec_limp)/sum(nec_anual_limp) else 0 end as  "+CONSTANTS.LIMPIA_COLUMN_NAME+",case when sum(nec_anual_poda)<>0 then sum(superf_ejec_poda)/sum(nec_anual_poda) else 0 end as  "+CONSTANTS.PODA_COLUMN_NAME+",case when sum(nec_anual_rec)<>0 then sum(superf_ejec_rec)/sum(nec_anual_rec) else 0 end as  "+CONSTANTS.RECONSTRUCCION_COLUMN_NAME+", anno " +
                                                                           "from c2_3_us_bosquesmanejslvicolas inner join usilvicola on c2_3_us_bosquesmanejslvicolas.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_2_3_EFI_YEARS                        = "select distinct anno from c2_3_us_bosquesmanejslvicolas order by anno";
       //......................................................................................................................
   public static final String G_SQL_2_3_MUN_AP                           = "select sum(superf_ejec_limp),sum(nec_anual_limp),sum(superf_ejec_poda),sum(nec_anual_poda),sum(superf_ejec_raleo),sum(nec_anual_realeo),sum(superf_ejec_rec),sum(nec_anual_rec), anno " +
                                                                           "from c2_3_ap_bosquesmanejslvicolas inner join municipios on c2_3_ap_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_2_3_MUN_US                           = "select sum(superf_ejec_limp),sum(nec_anual_limp),sum(superf_ejec_poda),sum(nec_anual_poda),sum(superf_ejec_raleo),sum(nec_anual_realeo),sum(superf_ejec_rec),sum(nec_anual_rec), anno " +
                                                                           "from c2_3_us_bosquesmanejslvicolas inner join municipios on c2_3_us_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_2_3_MUN_OTROS                        = "select sum(superf_ejec_limp),sum(nec_anual_limp),sum(superf_ejec_poda),sum(nec_anual_poda),sum(superf_ejec_raleo),sum(nec_anual_realeo),sum(superf_ejec_rec),sum(nec_anual_rec), anno " +
                                                                           "from c2_3_otros_bosquesmanejslvicolas inner join municipios on c2_3_otros_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_2_3_MUN_YEARS_AP                    = "select distinct anno from c2_3_ap_bosquesmanejslvicolas where municipio=':id' order by anno";

   public static final String G_SQL_2_3_MUN_YEARS_US                    = "select distinct anno from c2_3_us_bosquesmanejslvicolas where municipio=':id' order by anno";

   public static final String G_SQL_2_3_MUN_YEARS_OTROS                 = "select distinct anno from c2_3_otros_bosquesmanejslvicolas where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_2_3_PROV_AP                          = "select sum(superf_ejec_limp),sum(nec_anual_limp),sum(superf_ejec_poda),sum(nec_anual_poda),sum(superf_ejec_raleo),sum(nec_anual_realeo),sum(superf_ejec_rec),sum(nec_anual_rec), anno " +
                                                                           "from c2_3_ap_bosquesmanejslvicolas inner join municipios on c2_3_ap_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_2_3_PROV_US                           = "select sum(superf_ejec_limp),sum(nec_anual_limp),sum(superf_ejec_poda),sum(nec_anual_poda),sum(superf_ejec_raleo),sum(nec_anual_realeo),sum(superf_ejec_rec),sum(nec_anual_rec), anno " +
                                                                           "from c2_3_us_bosquesmanejslvicolas inner join municipios on c2_3_us_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_2_3_PROV_OTROS                        = "select sum(superf_ejec_limp),sum(nec_anual_limp),sum(superf_ejec_poda),sum(nec_anual_poda),sum(superf_ejec_raleo),sum(nec_anual_realeo),sum(superf_ejec_rec),sum(nec_anual_rec), anno " +
                                                                           "from c2_3_otros_bosquesmanejslvicolas inner join municipios on c2_3_otros_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_2_3_PROV_YEARS_AP                    = "select distinct anno from c2_3_ap_bosquesmanejslvicolas inner join municipios on c2_3_ap_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_2_3_PROV_YEARS_US                    = "select distinct anno from c2_3_us_bosquesmanejslvicolas inner join municipios on c2_3_us_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_2_3_PROV_YEARS_OTROS                 = "select distinct anno from c2_3_otros_bosquesmanejslvicolas inner join municipios on c2_3_otros_bosquesmanejslvicolas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
  //......................................................................................................................
   public static final String G_SQL_2_3_Temp_Table                       = "select case when nec_anual_realeo<>0 then superf_ejec_raleo/nec_anual_realeo else 0 end as  "+CONSTANTS.RALEO_COLUMN_NAME+",case when nec_anual_limp<>0 then superf_ejec_limp/nec_anual_limp else 0 end as  "+CONSTANTS.LIMPIA_COLUMN_NAME+",case when nec_anual_poda<>0 then superf_ejec_poda/nec_anual_poda else 0 end as  "+CONSTANTS.PODA_COLUMN_NAME+",case when nec_anual_rec<>0 then superf_ejec_rec/nec_anual_rec else 0 end as  "+CONSTANTS.RECONSTRUCCION_COLUMN_NAME+", anno " +
                                                                           "from c_2_3_graphicdata order by anno";

//====================== Criterio 3_1_1 Formacion forestal =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_3_1_1_AP                              = "select municipios.nombre as municipio, anno as Año , c3_1_1_formacionforestal.nombre as "+CONSTANTS.FORMACION_FORESTAL_COLUMN_NAME +
                                                                        ", area as "+CONSTANTS.AREA_COLUMN_NAME+
                                                                        " from (c3_1_1_ap_formacionforestal inner join municipios on c3_1_1_ap_formacionforestal.municipio=municipios.id) " +
                                                                        " inner join c3_1_1_formacionforestal on c3_1_1_ap_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                        "where c3_1_1_ap_formacionforestal.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c3_1_1_formacionforestal.nombre, area " +
                                                                        "order by municipios.nombre, anno, c3_1_1_formacionforestal.nombre, area";
//----------------------------------------------------------------------------------
 public static final String SQL_3_1_1_US                              = "select municipios.nombre as municipio, anno as Año , c3_1_1_formacionforestal.nombre as "+CONSTANTS.FORMACION_FORESTAL_COLUMN_NAME +
                                                                        ", area as "+CONSTANTS.AREA_COLUMN_NAME+
                                                                        " from (c3_1_1_us_formacionforestal inner join municipios on c3_1_1_us_formacionforestal.municipio=municipios.id) " +
                                                                        " inner join c3_1_1_formacionforestal on c3_1_1_us_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                        "where c3_1_1_us_formacionforestal.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c3_1_1_formacionforestal.nombre, area " +
                                                                        "order by municipios.nombre, anno, c3_1_1_formacionforestal.nombre, area";
//----------------------------------------------------------------------------------
public static final String SQL_3_1_1_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                         "c3_1_1_formacionforestal.nombre as "+CONSTANTS.FORMACION_FORESTAL_COLUMN_NAME+", sum(area) as "+CONSTANTS.AREA_COLUMN_NAME+
                                                                         " from (((c3_1_1_us_formacionforestal inner join usilvicola on c3_1_1_us_formacionforestal.id=usilvicola.id) " +
                                                                         "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_1_1_us_formacionforestal.municipio=municipios.id)" +
                                                                         "inner join c3_1_1_formacionforestal on c3_1_1_us_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                         "where efi.id=':id' and anno=:anno " +
                                                                         "group by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c3_1_1_formacionforestal.nombre " +
                                                                         "order by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c3_1_1_formacionforestal.nombre";

public static final String SQL_3_1_1_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(area) as area " +
                                                                           "from (c3_1_1_us_formacionforestal inner join usilvicola on c3_1_1_us_formacionforestal.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";

//----------------------------------------------------------------------------------
   public static final String SQL_3_1_1_MUN_SubGrupoUS                      = "select provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre" +
                                                                              ", sum(area) " +
                                                                              "from ((c3_1_1_us_formacionforestal inner join municipios on " +
                                                                              "c3_1_1_us_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_1_formacionforestal on c3_1_1_us_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre;";

   public static final String SQL_3_1_1_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre" +
                                                                              ", sum(area) " +
                                                                              "from ((c3_1_1_ap_formacionforestal inner join municipios on " +
                                                                              "c3_1_1_ap_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_1_formacionforestal on c3_1_1_ap_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre;";

   public static final String SQL_3_1_1_MUN_SubGrupoOTROS                       = "select provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre" +
                                                                              ", sum(area) " +
                                                                              "from ((c3_1_1_otros_formacionforestal inner join municipios on " +
                                                                              "c3_1_1_otros_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_1_formacionforestal on c3_1_1_otros_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_1_formacionforestal.nombre;";

   ////----------------------------------------------------------------------------------
   public static final String SQL_3_1_1_MUN_SubGrupoUS_Entities             = "select usilvicola.nombre, c3_1_1_formacionforestal.nombre, area " +
                                                                              "from (((c3_1_1_us_formacionforestal inner join municipios on " +
                                                                              "c3_1_1_us_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_1_formacionforestal on c3_1_1_us_formacionforestal.id_formforest=c3_1_1_formacionforestal.id)" +
                                                                              "inner join usilvicola on c3_1_1_us_formacionforestal.id=usilvicola.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_3_1_1_MUN_SubGrupoAP_Entities             = "select area_protegida.nombre, c3_1_1_formacionforestal.nombre, area " +
                                                                              "from (((c3_1_1_ap_formacionforestal inner join municipios on " +
                                                                              "c3_1_1_ap_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_1_formacionforestal on c3_1_1_ap_formacionforestal.id_formforest=c3_1_1_formacionforestal.id)" +
                                                                              "inner join area_protegida on c3_1_1_ap_formacionforestal.id=area_protegida.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_3_1_1_MUN_SubGrupoOTROS_Entities          = "select otros.nombre, c3_1_1_formacionforestal.nombre, area " +
                                                                              "from (((c3_1_1_otros_formacionforestal inner join municipios on " +
                                                                              "c3_1_1_otros_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_1_formacionforestal on c3_1_1_otros_formacionforestal.id_formforest=c3_1_1_formacionforestal.id)" +
                                                                              "inner join otros on c3_1_1_otros_formacionforestal.id=otros.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";
//----------------------------------------------------------------------------------
public static final String SQL_3_1_1_PROV_SubGrupoUS                     = "select provincias.nombre, c3_1_1_formacionforestal.nombre " +
                                                                            ", sum(area) " +
                                                                            "from ((c3_1_1_us_formacionforestal inner join municipios on " +
                                                                            "c3_1_1_us_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_1_formacionforestal on c3_1_1_us_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_1_formacionforestal.nombre " +
                                                                            "order by provincias.nombre, c3_1_1_formacionforestal.nombre";

public static final String SQL_3_1_1_PROV_SubGrupoAP                     = "select provincias.nombre, c3_1_1_formacionforestal.nombre " +
                                                                            ", sum(area) " +
                                                                            "from ((c3_1_1_ap_formacionforestal inner join municipios on " +
                                                                            "c3_1_1_ap_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_1_formacionforestal on c3_1_1_ap_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_1_formacionforestal.nombre " +
                                                                            "order by provincias.nombre, c3_1_1_formacionforestal.nombre";

public static final String SQL_3_1_1_PROV_SubGrupoOTROS                     = "select provincias.nombre, c3_1_1_formacionforestal.nombre " +
                                                                            ", sum(area) " +
                                                                            "from ((c3_1_1_otros_formacionforestal inner join municipios on " +
                                                                            "c3_1_1_otros_formacionforestal.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_1_formacionforestal on c3_1_1_otros_formacionforestal.id_formforest=c3_1_1_formacionforestal.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_1_formacionforestal.nombre " +
                                                                            "order by provincias.nombre, c3_1_1_formacionforestal.nombre";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_3_1_1_AP                               = "select area as  "+CONSTANTS.AREA_COLUMN_NAME+", anno " +
                                                                           "from c3_1_1_ap_formacionforestal "+
                                                                           "where c3_1_1_ap_formacionforestal.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_1_1_AP_YEARS                         = "select distinct anno from c3_1_1_ap_formacionforestal order by anno";
 //......................................................................................................................
   public static final String G_SQL_3_1_1_US                               = "select area as  "+CONSTANTS.AREA_COLUMN_NAME+", anno " +
                                                                           "from c3_1_1_us_formacionforestal "+
                                                                           "where c3_1_1_us_formacionforestal.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_1_1_US_YEARS                         = "select distinct anno from c3_1_1_us_formacionforestal order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_1_1_OTROS_YEARS                      = "select distinct anno from c3_1_1_otros_formacionforestal order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_1_1_EFI                              = "select sum(area) as  "+CONSTANTS.AREA_COLUMN_NAME+", anno " +
                                                                           "from c3_1_1_us_formacionforestal inner join usilvicola on c3_1_1_us_formacionforestal.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_3_1_1_EFI_YEARS                        = "select distinct anno from c3_1_1_us_formacionforestal order by anno";
       //......................................................................................................................
   public static final String G_SQL_3_1_1_MUN_AP                           = "select sum(area), anno " +
                                                                           "from c3_1_1_ap_formacionforestal inner join municipios on c3_1_1_ap_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_1_MUN_US                           = "select sum(area), anno " +
                                                                           "from c3_1_1_us_formacionforestal inner join municipios on c3_1_1_us_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_1_MUN_OTROS                        = "select sum(area), anno " +
                                                                           "from c3_1_1_otros_formacionforestal inner join municipios on c3_1_1_otros_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_1_MUN_YEARS_AP                    = "select distinct anno from c3_1_1_ap_formacionforestal where municipio=':id' order by anno";

   public static final String G_SQL_3_1_1_MUN_YEARS_US                    = "select distinct anno from c3_1_1_us_formacionforestal where municipio=':id' order by anno";

   public static final String G_SQL_3_1_1_MUN_YEARS_OTROS                 = "select distinct anno from c3_1_1_otros_formacionforestal where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_3_1_1_PROV_AP                          = "select sum(area), anno " +
                                                                           "from c3_1_1_ap_formacionforestal inner join municipios on c3_1_1_ap_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_1_PROV_US                           = "select sum(area), anno " +
                                                                           "from c3_1_1_us_formacionforestal inner join municipios on c3_1_1_us_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_1_PROV_OTROS                        = "select sum(area), anno " +
                                                                           "from c3_1_1_otros_formacionforestal inner join municipios on c3_1_1_otros_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_1_PROV_YEARS_AP                    = "select distinct anno from c3_1_1_ap_formacionforestal inner join municipios on c3_1_1_ap_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_1_1_PROV_YEARS_US                    = "select distinct anno from c3_1_1_us_formacionforestal inner join municipios on c3_1_1_us_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_1_1_PROV_YEARS_OTROS                 = "select distinct anno from c3_1_1_otros_formacionforestal inner join municipios on c3_1_1_otros_formacionforestal.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_3_1_1_Temp_Table                       = "select area as  "+CONSTANTS.AREA_COLUMN_NAME+", anno " +
                                                                           "from c_3_1_1_graphicdata order by anno";

//====================== Criterio 3_1_2 Especies Endémicas =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_3_1_2_AP                              = "select municipios.nombre as municipio, anno as Año , c3_1_2_especendem.nombre as "+CONSTANTS.ESPECIE_COLUMN_NAME +
                                                                        ", si_no.nombre as"+CONSTANTS.PROG_CONSERVACION_COLUMN_NAME +
                                                                        " from ((c3_1_2_ap_especendem inner join municipios on c3_1_2_ap_especendem.municipio=municipios.id) " +
                                                                        " inner join c3_1_2_especendem on c3_1_2_ap_especendem.id_espendm=c3_1_2_especendem.id) " +
                                                                        "inner join si_no on c3_1_2_ap_especendem.progconserv=si_no.id " +
                                                                        "where c3_1_2_ap_especendem.id=':id' and anno=:anno " +
                                                                        "order by municipios.nombre, anno, c3_1_2_especendem.nombre, progconserv";
//----------------------------------------------------------------------------------
 public static final String SQL_3_1_2_US                              = "select municipios.nombre as municipio, anno as Año , c3_1_2_especendem.nombre as "+CONSTANTS.ESPECIE_COLUMN_NAME +
                                                                        ", si_no.nombre as"+CONSTANTS.PROG_CONSERVACION_COLUMN_NAME +
                                                                        " from ((c3_1_2_us_especendem inner join municipios on c3_1_2_us_especendem.municipio=municipios.id) " +
                                                                        " inner join c3_1_2_especendem on c3_1_2_us_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                        "inner join si_no on c3_1_2_us_especendem.progconserv=si_no.id " +
                                                                        "where c3_1_2_us_especendem.id=':id' and anno=:anno " +
                                                                        "order by municipios.nombre, anno, c3_1_2_especendem.nombre, progconserv";
//----------------------------------------------------------------------------------
public static final String SQL_3_1_2_EFI_Subgrupo                      = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                         "c3_1_2_especendem.nombre as "+CONSTANTS.ESPECIE_COLUMN_NAME+", si_no.nombre as"+CONSTANTS.PROG_CONSERVACION_COLUMN_NAME +
                                                                         "from ((((c3_1_2_us_especendem inner join usilvicola on c3_1_2_us_especendem.id=usilvicola.id) " +
                                                                         "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_1_2_us_especendem.municipio=municipios.id)" +
                                                                         "inner join c3_1_2_especendem on c3_1_2_us_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                         "inner join si_no on c3_1_2_us_especendem.progconserv=si_no.id " +
                                                                         "where efi.id=':id' and anno=:anno " +
                                                                         "order by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c3_1_2_especendem.nombre, progconserv";

//public static final String SQL_3_1_2_EFI_TOTAL                            = "select efi.nombre as efi" +
//                                                                          ", sum(progconserv) as progconserv " +
//                                                                           "from (c3_1_2_us_especendem inner join usilvicola on c3_1_2_us_especendem.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
//                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
   public static final String SQL_3_1_2_MUN_SubGrupoUS                      = "select provincias.nombre, municipios.nombre, c3_1_2_especendem.nombre, si_no.nombre, usilvicola.nombre " +
                                                                              "from ((((c3_1_2_us_especendem inner join municipios on " +
                                                                              "c3_1_2_us_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_2_especendem on c3_1_2_us_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                              "inner join si_no on c3_1_2_us_especendem.progconserv=si_no.id)inner join usilvicola on c3_1_2_us_especendem.id=usilvicola.id  " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "order by provincias.nombre, municipios.nombre, usilvicola.id, c3_1_2_especendem.id;";

   public static final String SQL_3_1_2_MUN_SubGrupoAP                      = "select provincias.nombre, municipios.nombre, c3_1_2_especendem.nombre, si_no.nombre, area_protegida.nombre " +
                                                                              "from ((((c3_1_2_ap_especendem inner join municipios on " +
                                                                              "c3_1_2_ap_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_2_especendem on c3_1_2_ap_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                              "inner join si_no on c3_1_2_ap_especendem.progconserv=si_no.id)inner join area_protegida on c3_1_2_ap_especendem.id=area_protegida.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "order by provincias.nombre, municipios.nombre, area_protegida.id, c3_1_2_especendem.id;";

   public static final String SQL_3_1_2_MUN_SubGrupoOTROS                   = "select provincias.nombre, municipios.nombre, c3_1_2_especendem.nombre, si_no.nombre, otros.nombre " +
                                                                              "from ((((c3_1_2_otros_especendem inner join municipios on " +
                                                                              "c3_1_2_otros_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_2_especendem on c3_1_2_otros_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                              "inner join si_no on c3_1_2_otros_especendem.progconserv=si_no.id)inner join otros on c3_1_2_otros_especendem.id=otros.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "order by provincias.nombre, municipios.nombre, otros.id, c3_1_2_especendem.id;";
 //----------------------------------------------------------------------------------
   public static final String SQL_3_1_2_MUN_SubGrupoUS_Entities             = "select usilvicola.nombre, c3_1_2_especendem.nombre, si_no.nombre " +
                                                                              "from ((((c3_1_2_us_especendem inner join municipios on " +
                                                                              "c3_1_2_us_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_2_especendem on c3_1_2_us_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                              "inner join usilvicola on c3_1_2_us_especendem.id=usilvicola.id)" +
                                                                              "inner join si_no on c3_1_2_us_especendem.progconserv=si_no.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_3_1_2_MUN_SubGrupoAP_Entities             = "select area_protegida.nombre, c3_1_2_especendem.nombre, si_no.nombre " +
                                                                              "from ((((c3_1_2_ap_especendem inner join municipios on " +
                                                                              "c3_1_2_ap_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_2_especendem on c3_1_2_ap_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                              "inner join area_protegida on c3_1_2_ap_especendem.id=area_protegida.id)" +
                                                                              "inner join si_no on c3_1_2_ap_especendem.progconserv=si_no.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_3_1_2_MUN_SubGrupoOTROS_Entities          = "select otros.nombre, c3_1_2_especendem.nombre, si_no.nombre " +
                                                                              "from ((((c3_1_2_otros_especendem inner join municipios on " +
                                                                              "c3_1_2_otros_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_2_especendem on c3_1_2_otros_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                              "inner join otros on c3_1_2_otros_especendem.id=otros.id)" +
                                                                              "inner join si_no on c3_1_2_otros_especendem.progconserv=si_no.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";
//----------------------------------------------------------------------------------
public static final String SQL_3_1_2_PROV_SubGrupoUS                     = "select provincias.nombre, c3_1_2_especendem.nombre, si_no.nombre " +
                                                                            "from (((c3_1_2_us_especendem inner join municipios on " +
                                                                            "c3_1_2_us_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_2_especendem on c3_1_2_us_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                            "inner join si_no on c3_1_2_us_especendem.progconserv=si_no.id " +
                                                                            "where anno=:anno " +
                                                                            "order by provincias.nombre, c3_1_2_especendem.nombre";

public static final String SQL_3_1_2_PROV_SubGrupoAP                     = "select provincias.nombre, c3_1_2_especendem.nombre, si_no.nombre " +
                                                                            "from (((c3_1_2_ap_especendem inner join municipios on " +
                                                                            "c3_1_2_ap_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_2_especendem on c3_1_2_ap_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                            "inner join si_no on c3_1_2_ap_especendem.progconserv=si_no.id " +
                                                                            "where anno=:anno " +
                                                                            "order by provincias.nombre, c3_1_2_especendem.nombre";

public static final String SQL_3_1_2_PROV_SubGrupoOTROS                   = "select provincias.nombre, c3_1_2_especendem.nombre, si_no.nombre " +
                                                                            "from (((c3_1_2_otros_especendem inner join municipios on " +
                                                                            "c3_1_2_otros_especendem.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_2_especendem on c3_1_2_otros_especendem.id_espendm=c3_1_2_especendem.id)" +
                                                                            "inner join si_no on c3_1_2_otros_especendem.progconserv=si_no.id " +
                                                                            "where anno=:anno " +
                                                                            "order by provincias.nombre, c3_1_2_especendem.nombre";

//====================== Criterio 3_1_3 Ejecución de programa de protección =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_3_1_3_AP                              = "select municipios.nombre as municipio, anno as Año , c3_1_3_ejecprogprotec.nombre as "+CONSTANTS.PROGRAMA_PROT_COLUMN_NAME +
                                                                        ", superficie as "+CONSTANTS.SUPERFICIE_COLUMN_NAME+
                                                                        " from (c3_1_3_ap_ejecprogprotecc inner join municipios on c3_1_3_ap_ejecprogprotecc.municipio=municipios.id) " +
                                                                        " inner join c3_1_3_ejecprogprotec on c3_1_3_ap_ejecprogprotecc.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                        "where c3_1_3_ap_ejecprogprotecc.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c3_1_3_ejecprogprotec.nombre, superficie " +
                                                                        "order by municipios.nombre, anno, c3_1_3_ejecprogprotec.nombre, superficie";
//----------------------------------------------------------------------------------
 public static final String SQL_3_1_3_US                              = "select municipios.nombre as municipio, anno as Año , c3_1_3_ejecprogprotec.nombre as "+CONSTANTS.PROGRAMA_PROT_COLUMN_NAME +
                                                                        ", superficie as "+CONSTANTS.SUPERFICIE_COLUMN_NAME+
                                                                        " from (c3_1_3_us_ejecprogprotec inner join municipios on c3_1_3_us_ejecprogprotec.municipio=municipios.id) " +
                                                                        " inner join c3_1_3_ejecprogprotec on c3_1_3_us_ejecprogprotec.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                        "where c3_1_3_us_ejecprogprotec.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c3_1_3_ejecprogprotec.nombre, superficie " +
                                                                        "order by municipios.nombre, anno, c3_1_3_ejecprogprotec.nombre, superficie";
//----------------------------------------------------------------------------------
public static final String SQL_3_1_3_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                         "c3_1_3_ejecprogprotec.nombre as "+CONSTANTS.PROGRAMA_PROT_COLUMN_NAME+", sum(superficie) as "+CONSTANTS.SUPERFICIE_COLUMN_NAME+
                                                                         "from (((c3_1_3_us_ejecprogprotec inner join usilvicola on c3_1_3_us_ejecprogprotec.id=usilvicola.id) " +
                                                                         "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_1_3_us_ejecprogprotec.municipio=municipios.id)" +
                                                                         "inner join c3_1_3_ejecprogprotec on c3_1_3_us_ejecprogprotec.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                         "where efi.id=':id' and anno=:anno " +
                                                                         "group by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                         "order by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c3_1_3_ejecprogprotec.nombre";

public static final String SQL_3_1_3_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(superficie) as superficie " +
                                                                           "from (c3_1_3_us_ejecprogprotec inner join usilvicola on c3_1_3_us_ejecprogprotec.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
   public static final String SQL_3_1_3_MUN_SubGrupoUS                      = "select provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre" +
                                                                              ", sum(superficie) " +
                                                                              "from ((c3_1_3_us_ejecprogprotec inner join municipios on " +
                                                                              "c3_1_3_us_ejecprogprotec.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_3_ejecprogprotec on c3_1_3_us_ejecprogprotec.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre;";

   public static final String SQL_3_1_3_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre" +
                                                                              ", sum(superficie) " +
                                                                              "from ((c3_1_3_ap_ejecprogprotecc inner join municipios on " +
                                                                              "c3_1_3_ap_ejecprogprotecc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_3_ejecprogprotec on c3_1_3_ap_ejecprogprotecc.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre;";

   public static final String SQL_3_1_3_MUN_SubGrupoOTROS                       = "select provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre" +
                                                                              ", sum(superficie) " +
                                                                              "from ((c3_1_3_otros_ejecprogprotec inner join municipios on " +
                                                                              "c3_1_3_otros_ejecprogprotec.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_3_ejecprogprotec on c3_1_3_otros_ejecprogprotec.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_3_ejecprogprotec.nombre;";

   //----------------------------------------------------------------------------------
   public static final String SQL_3_1_3_MUN_SubGrupoUS_Entities             = "select usilvicola.nombre, c3_1_3_ejecprogprotec.nombre, superficie " +
                                                                              "from (((c3_1_3_us_ejecprogprotec inner join municipios on " +
                                                                              "c3_1_3_us_ejecprogprotec.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_3_ejecprogprotec on c3_1_3_us_ejecprogprotec.id_ejecprgm=c3_1_3_ejecprogprotec.id)" +
                                                                              "inner join usilvicola on c3_1_3_us_ejecprogprotec.id=usilvicola.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_3_1_3_MUN_SubGrupoAP_Entities             = "select area_protegida.nombre, c3_1_3_ejecprogprotec.nombre, superficie " +
                                                                              "from (((c3_1_3_ap_ejecprogprotecc inner join municipios on " +
                                                                              "c3_1_3_ap_ejecprogprotecc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_3_ejecprogprotec on c3_1_3_ap_ejecprogprotecc.id_ejecprgm=c3_1_3_ejecprogprotec.id)" +
                                                                              "inner join area_protegida on c3_1_3_ap_ejecprogprotecc.id=area_protegida.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_3_1_3_MUN_SubGrupoOTROS_Entities          = "select otros.nombre, c3_1_3_ejecprogprotec.nombre, superficie " +
                                                                              "from (((c3_1_3_otros_ejecprogprotec inner join municipios on " +
                                                                              "c3_1_3_otros_ejecprogprotec.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_3_ejecprogprotec on c3_1_3_otros_ejecprogprotec.id_ejecprgm=c3_1_3_ejecprogprotec.id)" +
                                                                              "inner join otros on c3_1_3_otros_ejecprogprotec.id=otros.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";
//----------------------------------------------------------------------------------
public static final String SQL_3_1_3_PROV_SubGrupoUS                     = "select provincias.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                            ", sum(superficie) " +
                                                                            "from ((c3_1_3_us_ejecprogprotec inner join municipios on " +
                                                                            "c3_1_3_us_ejecprogprotec.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_3_ejecprogprotec on c3_1_3_us_ejecprogprotec.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                            "order by provincias.nombre, c3_1_3_ejecprogprotec.nombre";

public static final String SQL_3_1_3_PROV_SubGrupoAP                     = "select provincias.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                            ", sum(superficie) " +
                                                                            "from ((c3_1_3_ap_ejecprogprotecc inner join municipios on " +
                                                                            "c3_1_3_ap_ejecprogprotecc.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_3_ejecprogprotec on c3_1_3_ap_ejecprogprotecc.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                            "order by provincias.nombre, c3_1_3_ejecprogprotec.nombre";

public static final String SQL_3_1_3_PROV_SubGrupoOTROS                     = "select provincias.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                            ", sum(superficie) " +
                                                                            "from ((c3_1_3_otros_ejecprogprotec inner join municipios on " +
                                                                            "c3_1_3_otros_ejecprogprotec.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_3_ejecprogprotec on c3_1_3_otros_ejecprogprotec.id_ejecprgm=c3_1_3_ejecprogprotec.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_3_ejecprogprotec.nombre " +
                                                                            "order by provincias.nombre, c3_1_3_ejecprogprotec.nombre";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_3_1_3_AP                               = "select superficie as  "+CONSTANTS.SUPERFICIE_COLUMN_NAME+", anno " +
                                                                           "from c3_1_3_ap_ejecprogprotecc "+
                                                                           "where c3_1_3_ap_ejecprogprotecc.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_1_3_AP_YEARS                         = "select distinct anno from c3_1_3_ap_ejecprogprotecc order by anno";
 //......................................................................................................................
   public static final String G_SQL_3_1_3_US                               = "select superficie as  "+CONSTANTS.SUPERFICIE_COLUMN_NAME+", anno " +
                                                                           "from c3_1_3_us_ejecprogprotec "+
                                                                           "where c3_1_3_us_ejecprogprotec.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_1_3_US_YEARS                         = "select distinct anno from c3_1_3_us_ejecprogprotec order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_1_3_OTROS_YEARS                      = "select distinct anno from c3_1_3_otros_ejecprogprotec order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_1_3_EFI                              = "select sum(superficie) as  "+CONSTANTS.SUPERFICIE_COLUMN_NAME+", anno " +
                                                                           "from c3_1_3_us_ejecprogprotec inner join usilvicola on c3_1_3_us_ejecprogprotec.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_3_1_3_EFI_YEARS                        = "select distinct anno from c3_1_3_us_ejecprogprotec order by anno";
       //......................................................................................................................
   public static final String G_SQL_3_1_3_MUN_AP                           = "select sum(superficie), anno " +
                                                                           "from c3_1_3_ap_ejecprogprotecc inner join municipios on c3_1_3_ap_ejecprogprotecc.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_3_MUN_US                           = "select sum(superficie), anno " +
                                                                           "from c3_1_3_us_ejecprogprotec inner join municipios on c3_1_3_us_ejecprogprotec.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_3_MUN_OTROS                        = "select sum(superficie), anno " +
                                                                           "from c3_1_3_otros_ejecprogprotec inner join municipios on c3_1_3_otros_ejecprogprotec.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_3_MUN_YEARS_AP                    = "select distinct anno from c3_1_3_ap_ejecprogprotecc where municipio=':id' order by anno";

   public static final String G_SQL_3_1_3_MUN_YEARS_US                    = "select distinct anno from c3_1_3_us_ejecprogprotec where municipio=':id' order by anno";

   public static final String G_SQL_3_1_3_MUN_YEARS_OTROS                 = "select distinct anno from c3_1_3_otros_ejecprogprotec where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_3_1_3_PROV_AP                          = "select sum(superficie), anno " +
                                                                           "from c3_1_3_ap_ejecprogprotecc inner join municipios on c3_1_3_ap_ejecprogprotecc.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_3_PROV_US                           = "select sum(superficie), anno " +
                                                                           "from c3_1_3_us_ejecprogprotec inner join municipios on c3_1_3_us_ejecprogprotec.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_3_PROV_OTROS                        = "select sum(superficie), anno " +
                                                                           "from c3_1_3_otros_ejecprogprotec inner join municipios on c3_1_3_otros_ejecprogprotec.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_3_PROV_YEARS_AP                    = "select distinct anno from c3_1_3_ap_ejecprogprotecc inner join municipios on c3_1_3_ap_ejecprogprotecc.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_1_3_PROV_YEARS_US                    = "select distinct anno from c3_1_3_us_ejecprogprotec inner join municipios on c3_1_3_us_ejecprogprotec.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_1_3_PROV_YEARS_OTROS                 = "select distinct anno from c3_1_3_otros_ejecprogprotec inner join municipios on c3_1_3_otros_ejecprogprotec.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_3_1_3_Temp_Table                       = "select superficie as  "+CONSTANTS.SUPERFICIE_COLUMN_NAME+", anno " +
                                                                           "from c_3_1_3_graphicdata order by anno";

//====================== Criterio 3_1_4 Programa Edu Cext =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_3_1_4_AP                              = "select municipios.nombre as municipio, anno as Año , c3_1_4_progeducext.nombre as "+CONSTANTS.PROG_EDUC_EXT_COLUMN_NAME +
                                                                        ", cant_actv as "+CONSTANTS.CANT_ACTV_COLUMN_NAME+", cant_particp as "+CONSTANTS.CANT_PARTICP_COLUMN_NAME+" " +
                                                                        " from (c3_1_4_ap_progeducext inner join municipios on c3_1_4_ap_progeducext.municipio=municipios.id) " +
                                                                        " inner join c3_1_4_progeducext on c3_1_4_ap_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                        "where c3_1_4_ap_progeducext.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c3_1_4_progeducext.nombre, cant_actv, cant_particp " +
                                                                        "order by municipios.nombre, anno, c3_1_4_progeducext.nombre, cant_actv, cant_particp";
//----------------------------------------------------------------------------------
 public static final String SQL_3_1_4_US                              = "select municipios.nombre as municipio, anno as Año , c3_1_4_progeducext.nombre as "+CONSTANTS.PROG_EDUC_EXT_COLUMN_NAME +
                                                                        ", cant_actv as "+CONSTANTS.CANT_ACTV_COLUMN_NAME+", cant_particp as "+CONSTANTS.CANT_PARTICP_COLUMN_NAME+" " +
                                                                        " from (c3_1_4_us_progeducext inner join municipios on c3_1_4_us_progeducext.municipio=municipios.id) " +
                                                                        " inner join c3_1_4_progeducext on c3_1_4_us_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                        "where c3_1_4_us_progeducext.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c3_1_4_progeducext.nombre, cant_actv, cant_particp " +
                                                                        "order by municipios.nombre, anno, c3_1_4_progeducext.nombre, cant_actv, cant_particp";
//----------------------------------------------------------------------------------
public static final String SQL_3_1_4_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                         "c3_1_4_progeducext.nombre as "+CONSTANTS.PROG_EDUC_EXT_COLUMN_NAME+", sum(cant_actv) as "+CONSTANTS.CANT_ACTV_COLUMN_NAME+", sum(cant_particp) as "+CONSTANTS.CANT_PARTICP_COLUMN_NAME+" " +
                                                                         "from (((c3_1_4_us_progeducext inner join usilvicola on c3_1_4_us_progeducext.id=usilvicola.id) " +
                                                                         "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_1_4_us_progeducext.municipio=municipios.id)" +
                                                                         "inner join c3_1_4_progeducext on c3_1_4_us_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                         "where efi.id=':id' and anno=:anno " +
                                                                         "group by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c3_1_4_progeducext.nombre " +
                                                                         "order by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c3_1_4_progeducext.nombre";

public static final String SQL_3_1_4_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(cant_actv) as cant_actv, sum(cant_particp) as cant_particp " +
                                                                           "from (c3_1_4_us_progeducext inner join usilvicola on c3_1_4_us_progeducext.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
   public static final String SQL_3_1_4_MUN_SubGrupoUS_Entities             = "select usilvicola.nombre, c3_1_4_progeducext.nombre, cant_actv, cant_particp " +
                                                                              "from (((c3_1_4_us_progeducext inner join municipios on " +
                                                                              "c3_1_4_us_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_4_progeducext on c3_1_4_us_progeducext.id_ejeceduc=c3_1_4_progeducext.id)" +
                                                                              "inner join usilvicola on c3_1_4_us_progeducext.id=usilvicola.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_3_1_4_MUN_SubGrupoAP_Entities             = "select area_protegida.nombre, c3_1_4_progeducext.nombre, cant_actv, cant_particp " +
                                                                              "from (((c3_1_4_ap_progeducext inner join municipios on " +
                                                                              "c3_1_4_ap_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_4_progeducext on c3_1_4_ap_progeducext.id_ejeceduc=c3_1_4_progeducext.id)" +
                                                                              "inner join area_protegida on c3_1_4_ap_progeducext.id=area_protegida.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_3_1_4_MUN_SubGrupoOTROS_Entities          = "select otros.nombre, c3_1_4_progeducext.nombre, cant_actv, cant_particp " +
                                                                              "from (((c3_1_4_otros_progeducext inner join municipios on " +
                                                                              "c3_1_4_otros_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_4_progeducext on c3_1_4_otros_progeducext.id_ejeceduc=c3_1_4_progeducext.id)" +
                                                                              "inner join otros on c3_1_4_otros_progeducext.id=otros.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";
   //----------------------------------------------------------------------------------
   public static final String SQL_3_1_4_MUN_SubGrupoUS                      = "select provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre" +
                                                                              ", sum(cant_actv), sum(cant_particp) " +
                                                                              "from ((c3_1_4_us_progeducext inner join municipios on " +
                                                                              "c3_1_4_us_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_4_progeducext on c3_1_4_us_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre;";

   public static final String SQL_3_1_4_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre" +
                                                                              ", sum(cant_actv), sum(cant_particp) " +
                                                                              "from ((c3_1_4_ap_progeducext inner join municipios on " +
                                                                              "c3_1_4_ap_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_4_progeducext on c3_1_4_ap_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre;";

   public static final String SQL_3_1_4_MUN_SubGrupoOTROS                       = "select provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre" +
                                                                              ", sum(cant_actv), sum(cant_particp) " +
                                                                              "from ((c3_1_4_otros_progeducext inner join municipios on " +
                                                                              "c3_1_4_otros_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c3_1_4_progeducext on c3_1_4_otros_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c3_1_4_progeducext.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_3_1_4_PROV_SubGrupoUS                     = "select provincias.nombre, c3_1_4_progeducext.nombre " +
                                                                            ", sum(cant_actv), sum(cant_particp) " +
                                                                            "from ((c3_1_4_us_progeducext inner join municipios on " +
                                                                            "c3_1_4_us_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_4_progeducext on c3_1_4_us_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_4_progeducext.nombre " +
                                                                            "order by provincias.nombre, c3_1_4_progeducext.nombre";

public static final String SQL_3_1_4_PROV_SubGrupoAP                     = "select provincias.nombre, c3_1_4_progeducext.nombre " +
                                                                            ", sum(cant_actv), sum(cant_particp) " +
                                                                            "from ((c3_1_4_ap_progeducext inner join municipios on " +
                                                                            "c3_1_4_ap_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_4_progeducext on c3_1_4_ap_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_4_progeducext.nombre " +
                                                                            "order by provincias.nombre, c3_1_4_progeducext.nombre";

public static final String SQL_3_1_4_PROV_SubGrupoOTROS                     = "select provincias.nombre, c3_1_4_progeducext.nombre " +
                                                                            ", sum(cant_actv), sum(cant_particp) " +
                                                                            "from ((c3_1_4_otros_progeducext inner join municipios on " +
                                                                            "c3_1_4_otros_progeducext.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c3_1_4_progeducext on c3_1_4_otros_progeducext.id_ejeceduc=c3_1_4_progeducext.id " +
                                                                            "where anno=:anno group by provincias.nombre, c3_1_4_progeducext.nombre " +
                                                                            "order by provincias.nombre, c3_1_4_progeducext.nombre";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_3_1_4_AP                               = "select cant_actv as  "+CONSTANTS.CANT_ACTV_COLUMN_NAME+",cant_particp as  "+CONSTANTS.CANT_PARTICP_COLUMN_NAME+", anno " +
                                                                           "from c3_1_4_ap_progeducext "+
                                                                           "where c3_1_4_ap_progeducext.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_1_4_AP_YEARS                         = "select distinct anno from c3_1_4_ap_progeducext order by anno";
 //......................................................................................................................
   public static final String G_SQL_3_1_4_US                               = "select cant_actv as  "+CONSTANTS.CANT_ACTV_COLUMN_NAME+",cant_particp as  "+CONSTANTS.CANT_PARTICP_COLUMN_NAME+", anno " +
                                                                           "from c3_1_4_us_progeducext "+
                                                                           "where c3_1_4_us_progeducext.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_1_4_US_YEARS                         = "select distinct anno from c3_1_4_us_progeducext order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_1_4_OTROS_YEARS                      = "select distinct anno from c3_1_4_otros_progeducext order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_1_4_EFI                              = "select sum(cant_actv) as  "+CONSTANTS.CANT_ACTV_COLUMN_NAME+",sum(cant_particp) as  "+CONSTANTS.CANT_PARTICP_COLUMN_NAME+", anno " +
                                                                           "from c3_1_4_us_progeducext inner join usilvicola on c3_1_4_us_progeducext.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_3_1_4_EFI_YEARS                        = "select distinct anno from c3_1_4_us_progeducext order by anno";
       //......................................................................................................................
   public static final String G_SQL_3_1_4_MUN_AP                           = "select sum(cant_actv),sum(cant_particp), anno " +
                                                                           "from c3_1_4_ap_progeducext inner join municipios on c3_1_4_ap_progeducext.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_4_MUN_US                           = "select sum(cant_actv),sum(cant_particp), anno " +
                                                                           "from c3_1_4_us_progeducext inner join municipios on c3_1_4_us_progeducext.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_4_MUN_OTROS                        = "select sum(cant_actv),sum(cant_particp), anno " +
                                                                           "from c3_1_4_otros_progeducext inner join municipios on c3_1_4_otros_progeducext.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_1_4_MUN_YEARS_AP                    = "select distinct anno from c3_1_4_ap_progeducext where municipio=':id' order by anno";

   public static final String G_SQL_3_1_4_MUN_YEARS_US                    = "select distinct anno from c3_1_4_us_progeducext where municipio=':id' order by anno";

   public static final String G_SQL_3_1_4_MUN_YEARS_OTROS                 = "select distinct anno from c3_1_4_otros_progeducext where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_3_1_4_PROV_AP                          = "select sum(cant_actv),sum(cant_particp), anno " +
                                                                           "from c3_1_4_ap_progeducext inner join municipios on c3_1_4_ap_progeducext.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_4_PROV_US                           = "select sum(cant_actv),sum(cant_particp), anno " +
                                                                           "from c3_1_4_us_progeducext inner join municipios on c3_1_4_us_progeducext.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_4_PROV_OTROS                        = "select sum(cant_actv),sum(cant_particp), anno " +
                                                                           "from c3_1_4_otros_progeducext inner join municipios on c3_1_4_otros_progeducext.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_1_4_PROV_YEARS_AP                    = "select distinct anno from c3_1_4_ap_progeducext inner join municipios on c3_1_4_ap_progeducext.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_1_4_PROV_YEARS_US                    = "select distinct anno from c3_1_4_us_progeducext inner join municipios on c3_1_4_us_progeducext.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_1_4_PROV_YEARS_OTROS                 = "select distinct anno from c3_1_4_otros_progeducext inner join municipios on c3_1_4_otros_progeducext.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_3_1_4_Temp_Table                       = "select cant_actv as  "+CONSTANTS.CANT_ACTV_COLUMN_NAME+",cant_particp as  "+CONSTANTS.CANT_PARTICP_COLUMN_NAME+", anno " +
                                                                           "from c_3_1_4_graphicdata order by anno";

//====================== Criterio 3_2_1 Presas =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_3_2_1_AP                                   = "select municipios.nombre as municipio, anno as Año, (long_perm*anchorefaj)/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                             "case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                             " from c3_2_1_ap_presas inner join municipios on c3_2_1_ap_presas.municipio=municipios.id " +
                                                                             "where c3_2_1_ap_presas.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_1_US                                   = "select municipios.nombre as municipio, anno as Año , (long_perm*anchorefaj)/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                            "case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                            " from c3_2_1_us_presas inner join municipios on c3_2_1_us_presas.municipio=municipios.id " +
                                                                            "where c3_2_1_us_presas.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_1_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                            ", (sum(long_perm)* sum(anchorefaj))/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+ " "+
                                                                            "from ((c3_2_1_us_presas inner join usilvicola on c3_2_1_us_presas.id=usilvicola.id) " +
                                                                            "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_2_1_us_presas.municipio=municipios.id " +
                                                                            "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_3_2_1_EFI_TOTAL                            = "select efi.nombre as efi, (sum(long_perm)* sum(anchorefaj))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_1_us_presas inner join usilvicola on c3_2_1_us_presas.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                            "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_1_MUN_SubGrupoUS                       = "select municipios.nombre, (sum(long_perm)* sum(anchorefaj))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_1_us_presas inner join municipios on " +
                                                                            "c3_2_1_us_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_2_1_MUN_SubGrupoAP                       = "select municipios.nombre, (sum(long_perm)* sum(anchorefaj))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_1_ap_presas inner join municipios on " +
                                                                            "c3_2_1_ap_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_2_1_MUN_SubGrupoOTROS                    = "select municipios.nombre, (sum(long_perm)* sum(anchorefaj))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_1_otros_presas inner join municipios on " +
                                                                            "c3_2_1_otros_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_3_2_1_MUN_US_SubValues                   = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_1_us_presas inner join municipios on " +
                                                                             "c3_2_1_us_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_1_us_presas.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_2_1_MUN_AP_SubValues                   = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_1_ap_presas inner join municipios on " +
                                                                             "c3_2_1_ap_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_1_ap_presas.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_2_1_MUN_OTROS_SubValues                = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_1_otros_presas inner join municipios on " +
                                                                             "c3_2_1_otros_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_1_otros_presas.anno=:anno group by municipios.id, municipios.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_3_2_1_MUN_SubGrupoUS_Entities              = "select usilvicola.nombre, sum(long_perm), sum(anchorefaj), sum(suptotzonprotec) from ((c3_2_1_us_presas inner join municipios on " +
                                                                             "c3_2_1_us_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c3_2_1_us_presas.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_3_2_1_MUN_SubGrupoAP_Entities              = "select area_protegida.nombre, sum(long_perm), sum(anchorefaj), sum(suptotzonprotec) from ((c3_2_1_ap_presas inner join municipios on " +
                                                                             "c3_2_1_ap_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c3_2_1_ap_presas.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_3_2_1_MUN_SubGrupoOTROS_Entities           = "select otros.nombre, sum(long_perm), sum(anchorefaj), sum(suptotzonprotec) from ((c3_2_1_otros_presas inner join municipios on " +
                                                                             "c3_2_1_otros_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c3_2_1_otros_presas.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_1_PROV_SubGrupoUS                      = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), provincias.nombre " +
                                                                           "from (c3_2_1_us_presas inner join municipios on " +
                                                                           "c3_2_1_us_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_2_1_PROV_SubGrupoAP                      = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), provincias.nombre " +
                                                                           "from(c3_2_1_ap_presas inner join municipios on " +
                                                                           "c3_2_1_ap_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_2_1_PROV_SubGrupoOTROS                   = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), provincias.nombre " +
                                                                           "from (c3_2_1_otros_presas inner join municipios on " +
                                                                           "c3_2_1_otros_presas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_3_2_1_AP                               = "select (long_perm*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_1_ap_presas "+
                                                                           "where c3_2_1_ap_presas.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_2_1_AP_YEARS                         = "select distinct anno from c3_2_1_ap_presas order by anno";
 //......................................................................................................................
   public static final String G_SQL_3_2_1_US                               = "select (long_perm*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_1_us_presas "+
                                                                           "where c3_2_1_us_presas.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_2_1_US_YEARS                         = "select distinct anno from c3_2_1_us_presas order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_2_1_OTROS_YEARS                      = "select distinct anno from c3_2_1_otros_presas order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_2_1_EFI                              = "select (sum(long_perm)*sum(anchorefaj))/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when sum(suptotzonprotec)<>0 then (((sum(long_perm)*sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_1_us_presas inner join usilvicola on c3_2_1_us_presas.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_3_2_1_EFI_YEARS                        = "select distinct anno from c3_2_1_us_presas order by anno";
       //......................................................................................................................
   public static final String G_SQL_3_2_1_MUN_AP                           = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_1_ap_presas inner join municipios on c3_2_1_ap_presas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_1_MUN_US                           = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_1_us_presas inner join municipios on c3_2_1_us_presas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_1_MUN_OTROS                        = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_1_otros_presas inner join municipios on c3_2_1_otros_presas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_1_MUN_YEARS_AP                    = "select distinct anno from c3_2_1_ap_presas where municipio=':id' order by anno";

   public static final String G_SQL_3_2_1_MUN_YEARS_US                    = "select distinct anno from c3_2_1_us_presas where municipio=':id' order by anno";

   public static final String G_SQL_3_2_1_MUN_YEARS_OTROS                 = "select distinct anno from c3_2_1_otros_presas where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_3_2_1_PROV_AP                          = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_1_ap_presas inner join municipios on c3_2_1_ap_presas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_1_PROV_US                           = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_1_us_presas inner join municipios on c3_2_1_us_presas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_1_PROV_OTROS                        = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_1_otros_presas inner join municipios on c3_2_1_otros_presas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_1_PROV_YEARS_AP                    = "select distinct anno from c3_2_1_ap_presas inner join municipios on c3_2_1_ap_presas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_2_1_PROV_YEARS_US                    = "select distinct anno from c3_2_1_us_presas inner join municipios on c3_2_1_us_presas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_2_1_PROV_YEARS_OTROS                 = "select distinct anno from c3_2_1_otros_presas inner join municipios on c3_2_1_otros_presas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_3_2_1_Temp_Table                       = "select (long_perm*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c_3_2_1_graphicdata order by anno";

//====================== Criterio 3_2_2 MicroPresas =====================================================================================================================================================================
 //----------------------------------------------------------------------------------
 public static final String SQL_3_2_2_AP                                   = "select municipios.nombre as municipio, anno as Año, (long_perm*anchorefaj)/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                             "case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                             " from c3_2_2_ap_micropresas inner join municipios on c3_2_2_ap_micropresas.municipio=municipios.id " +
                                                                             "where c3_2_2_ap_micropresas.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_2_US                                   = "select municipios.nombre as municipio, anno as Año , (long_perm*anchorefaj)/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                            "case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                            " from c3_2_2_us_micropresas inner join municipios on c3_2_2_us_micropresas.municipio=municipios.id " +
                                                                            "where c3_2_2_us_micropresas.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_2_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                            ", (sum(long_perm)* sum(anchorefaj))/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+ " "+
                                                                            "from ((c3_2_2_us_micropresas inner join usilvicola on c3_2_2_us_micropresas.id=usilvicola.id) " +
                                                                            "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_2_2_us_micropresas.municipio=municipios.id " +
                                                                            "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_3_2_2_EFI_TOTAL                            = "select efi.nombre as efi, (sum(long_perm)* sum(anchorefaj))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_2_us_micropresas inner join usilvicola on c3_2_2_us_micropresas.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                            "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_2_MUN_SubGrupoUS                       = "select municipios.nombre, (sum(long_perm)* sum(anchorefaj))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_2_us_micropresas inner join municipios on " +
                                                                            "c3_2_2_us_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_2_2_MUN_SubGrupoAP                       = "select municipios.nombre, (sum(long_perm)* sum(anchorefaj))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_2_ap_micropresas inner join municipios on " +
                                                                            "c3_2_2_ap_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_2_2_MUN_SubGrupoOTROS                    = "select municipios.nombre, (sum(long_perm)* sum(anchorefaj))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_perm)* sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_2_otros_micropresas inner join municipios on " +
                                                                            "c3_2_2_otros_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_3_2_2_MUN_US_SubValues                   = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_2_us_micropresas inner join municipios on " +
                                                                             "c3_2_2_us_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_2_us_micropresas.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_2_2_MUN_AP_SubValues                   = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_2_ap_micropresas inner join municipios on " +
                                                                             "c3_2_2_ap_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_2_ap_micropresas.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_2_2_MUN_OTROS_SubValues                = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_2_otros_micropresas inner join municipios on " +
                                                                             "c3_2_2_otros_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_2_otros_micropresas.anno=:anno group by municipios.id, municipios.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_3_2_2_MUN_SubGrupoUS_Entities              = "select usilvicola.nombre, sum(long_perm), sum(anchorefaj), sum(suptotzonprotec) from ((c3_2_2_us_micropresas inner join municipios on " +
                                                                             "c3_2_2_us_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c3_2_2_us_micropresas.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_3_2_2_MUN_SubGrupoAP_Entities              = "select area_protegida.nombre, sum(long_perm), sum(anchorefaj), sum(suptotzonprotec) from ((c3_2_2_ap_micropresas inner join municipios on " +
                                                                             "c3_2_2_ap_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c3_2_2_ap_micropresas.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_3_2_2_MUN_SubGrupoOTROS_Entities           = "select otros.nombre, sum(long_perm), sum(anchorefaj), sum(suptotzonprotec) from ((c3_2_2_otros_micropresas inner join municipios on " +
                                                                             "c3_2_2_otros_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c3_2_2_otros_micropresas.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_2_PROV_SubGrupoUS                      = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), provincias.nombre " +
                                                                           "from (c3_2_2_us_micropresas inner join municipios on " +
                                                                           "c3_2_2_us_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_2_2_PROV_SubGrupoAP                      = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), provincias.nombre " +
                                                                           "from(c3_2_2_ap_micropresas inner join municipios on " +
                                                                           "c3_2_2_ap_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_2_2_PROV_SubGrupoOTROS                   = "select sum(long_perm), sum(anchorefaj), sum(suptotzonprotec), provincias.nombre " +
                                                                           "from (c3_2_2_otros_micropresas inner join municipios on " +
                                                                           "c3_2_2_otros_micropresas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_3_2_2_AP                               = "select (long_perm*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_2_ap_micropresas "+
                                                                           "where c3_2_2_ap_micropresas.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_2_2_AP_YEARS                         = "select distinct anno from c3_2_2_ap_micropresas order by anno";
 //......................................................................................................................
   public static final String G_SQL_3_2_2_US                               = "select (long_perm*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_2_us_micropresas "+
                                                                           "where c3_2_2_us_micropresas.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_2_2_US_YEARS                         = "select distinct anno from c3_2_2_us_micropresas order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_2_2_OTROS_YEARS                      = "select distinct anno from c3_2_2_otros_micropresas order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_2_2_EFI                              = "select (sum(long_perm)*sum(anchorefaj))/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when sum(suptotzonprotec)<>0 then (((sum(long_perm)*sum(anchorefaj))/10000) / sum(suptotzonprotec))*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_2_us_micropresas inner join usilvicola on c3_2_2_us_micropresas.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_3_2_2_EFI_YEARS                        = "select distinct anno from c3_2_2_us_micropresas order by anno";
       //......................................................................................................................
   public static final String G_SQL_3_2_2_MUN_AP                           = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_2_ap_micropresas inner join municipios on c3_2_2_ap_micropresas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_2_MUN_US                           = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_2_us_micropresas inner join municipios on c3_2_2_us_micropresas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_2_MUN_OTROS                        = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_2_otros_micropresas inner join municipios on c3_2_2_otros_micropresas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_2_MUN_YEARS_AP                    = "select distinct anno from c3_2_2_ap_micropresas where municipio=':id' order by anno";

   public static final String G_SQL_3_2_2_MUN_YEARS_US                    = "select distinct anno from c3_2_2_us_micropresas where municipio=':id' order by anno";

   public static final String G_SQL_3_2_2_MUN_YEARS_OTROS                 = "select distinct anno from c3_2_2_otros_micropresas where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_3_2_2_PROV_AP                          = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_2_ap_micropresas inner join municipios on c3_2_2_ap_micropresas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_2_PROV_US                           = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_2_us_micropresas inner join municipios on c3_2_2_us_micropresas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_2_PROV_OTROS                        = "select sum(long_perm),sum(anchorefaj),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_2_otros_micropresas inner join municipios on c3_2_2_otros_micropresas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_2_PROV_YEARS_AP                    = "select distinct anno from c3_2_2_ap_micropresas inner join municipios on c3_2_2_ap_micropresas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_2_2_PROV_YEARS_US                    = "select distinct anno from c3_2_2_us_micropresas inner join municipios on c3_2_2_us_micropresas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_2_2_PROV_YEARS_OTROS                 = "select distinct anno from c3_2_2_otros_micropresas inner join municipios on c3_2_2_otros_micropresas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_3_2_2_Temp_Table                       = "select (long_perm*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then (((long_perm*anchorefaj)/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c_3_2_2_graphicdata order by anno";
   
//====================== Criterio 3_2_3 Corriente Fluvial =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_3_2_3_AP                                   = "select municipios.nombre as municipio, anno as Año, ((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                             "case when suptotzonprotec<>0 then ((((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000) / suptotzonprotec)*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                             " from c3_2_3_ap_corrfluvial inner join municipios on c3_2_3_ap_corrfluvial.municipio=municipios.id " +
                                                                             "where c3_2_3_ap_corrfluvial.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_3_US                                    = "select municipios.nombre as municipio, anno as Año, ((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                             "case when suptotzonprotec<>0 then ((((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000) / suptotzonprotec)*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                             " from c3_2_3_us_corrfluvial inner join municipios on c3_2_3_us_corrfluvial.municipio=municipios.id " +
                                                                             "where c3_2_3_us_corrfluvial.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_3_EFI_Subgrupo                         = "select municipios.nombre as municipio,anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                            ", (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000) / sum(suptotzonprotec))*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                            "from ((c3_2_3_us_corrfluvial inner join usilvicola on c3_2_3_us_corrfluvial.id=usilvicola.id) " +
                                                                            "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_2_3_us_corrfluvial.municipio=municipios.id " +
                                                                            "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_3_2_3_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                            ", (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                            "from (c3_2_3_us_corrfluvial inner join usilvicola on c3_2_3_us_corrfluvial.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                            "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_3_MUN_SubGrupoUS                       = "select municipios.nombre as municipio" +
                                                                          ", (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                           "from (c3_2_3_us_corrfluvial inner join municipios on " +
                                                                           "c3_2_3_us_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_2_3_MUN_SubGrupoAP                       = "select municipios.nombre as municipio " +
                                                                          ", (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                           "from (c3_2_3_ap_corrfluvial inner join municipios on " +
                                                                           "c3_2_3_ap_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_2_3_MUN_SubGrupoOTROS                    = "select municipios.nombre as municipio " +
                                                                          ", (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                            "case when sum(suptotzonprotec)<>0 then (((sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000) / sum(suptotzonprotec))*100 else 0 end " +
                                                                           "from (c3_2_3_otros_corrfluvial inner join municipios on " +
                                                                           "c3_2_3_otros_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_3_2_3_MUN_US_SubValues                   = "select (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                             "sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_3_us_corrfluvial inner join municipios on " +
                                                                             "c3_2_3_us_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_3_us_corrfluvial.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_2_3_MUN_AP_SubValues                   = "select (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                             "sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_3_ap_corrfluvial inner join municipios on " +
                                                                             "c3_2_3_ap_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_3_ap_corrfluvial.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_2_3_MUN_OTROS_SubValues                = "select (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                             "sum(suptotzonprotec), municipios.nombre as municipio " +
                                                                             "from ((c3_2_3_otros_corrfluvial inner join municipios on " +
                                                                             "c3_2_3_otros_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_2_3_otros_corrfluvial.anno=:anno group by municipios.id, municipios.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_3_2_3_MUN_SubGrupoUS_Entities              = "select usilvicola.nombre, (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                             "sum(suptotzonprotec) " +
                                                                             "from ((c3_2_3_us_corrfluvial inner join municipios on " +
                                                                             "c3_2_3_us_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c3_2_3_us_corrfluvial.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_3_2_3_MUN_SubGrupoAP_Entities              = "select area_protegida.nombre, (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                             "sum(suptotzonprotec) " +
                                                                             "from ((c3_2_3_ap_corrfluvial inner join municipios on " +
                                                                             "c3_2_3_ap_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c3_2_3_ap_corrfluvial.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_3_2_3_MUN_SubGrupoOTROS_Entities           = "select otros.nombre, (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                             "sum(suptotzonprotec) " +
                                                                             "from ((c3_2_3_otros_corrfluvial inner join municipios on " +
                                                                             "c3_2_3_otros_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c3_2_3_otros_corrfluvial.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_3_2_3_PROV_SubGrupoUS                      = "select (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                            "sum(suptotzonprotec), provincias.nombre " +
                                                                            "from (c3_2_3_us_corrfluvial inner join municipios on " +
                                                                            "c3_2_3_us_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_2_3_PROV_SubGrupoAP                      = "select (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                            "sum(suptotzonprotec), provincias.nombre " +
                                                                            "from(c3_2_3_ap_corrfluvial inner join municipios on " +
                                                                            "c3_2_3_ap_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_2_3_PROV_SubGrupoOTROS                   = "select (sum(long_princ*anchorefaj_princ)+sum(long_1er*anchorefal_1er)+sum(long_2do*anchorefaj_2do))/10000, " +
                                                                            "sum(suptotzonprotec), provincias.nombre " +
                                                                            "from (c3_2_3_otros_corrfluvial inner join municipios on " +
                                                                            "c3_2_3_otros_corrfluvial.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                            "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_3_2_3_AP                               = "select ((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then ((((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_3_ap_corrfluvial "+
                                                                           "where c3_2_3_ap_corrfluvial.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_2_3_AP_YEARS                         = "select distinct anno from c3_2_3_ap_corrfluvial order by anno";
 //......................................................................................................................
   public static final String G_SQL_3_2_3_US                               = "select ((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then ((((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_3_us_corrfluvial "+
                                                                           "where c3_2_3_us_corrfluvial.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_2_3_US_YEARS                         = "select distinct anno from c3_2_3_us_corrfluvial order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_2_3_OTROS_YEARS                      = "select distinct anno from c3_2_3_otros_corrfluvial order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_2_3_EFI                              = "select ((sum(long_princ)*sum(anchorefaj_princ))+(sum(long_1er)*sum(anchorefal_1er))+(sum(long_2do)*sum(anchorefaj_2do)))/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when sum(suptotzonprotec)<>0 then ((((sum(long_princ)*sum(anchorefaj_princ))+(sum(long_1er)*sum(anchorefal_1er))+(sum(long_2do)*sum(anchorefaj_2do)))/10000) / sum(suptotzonprotec))*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_2_3_us_corrfluvial inner join usilvicola on c3_2_3_us_corrfluvial.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_3_2_3_EFI_YEARS                        = "select distinct anno from c3_2_3_us_corrfluvial order by anno";
       //......................................................................................................................
   public static final String G_SQL_3_2_3_MUN_AP                           = "select sum(long_princ),sum(anchorefaj_princ),sum( long_1er),sum(anchorefal_1er),sum(long_2do),sum(anchorefaj_2do),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_3_ap_corrfluvial inner join municipios on c3_2_3_ap_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_3_MUN_US                           = "select sum(long_princ),sum(anchorefaj_princ),sum( long_1er),sum(anchorefal_1er),sum(long_2do),sum(anchorefaj_2do),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_3_us_corrfluvial inner join municipios on c3_2_3_us_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_3_MUN_OTROS                        = "select sum(long_princ),sum(anchorefaj_princ),sum( long_1er),sum(anchorefal_1er),sum(long_2do),sum(anchorefaj_2do),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_3_otros_corrfluvial inner join municipios on c3_2_3_otros_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_2_3_MUN_YEARS_AP                    = "select distinct anno from c3_2_3_ap_corrfluvial where municipio=':id' order by anno";

   public static final String G_SQL_3_2_3_MUN_YEARS_US                    = "select distinct anno from c3_2_3_us_corrfluvial where municipio=':id' order by anno";

   public static final String G_SQL_3_2_3_MUN_YEARS_OTROS                 = "select distinct anno from c3_2_3_otros_corrfluvial where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_3_2_3_PROV_AP                          = "select sum(long_princ),sum(anchorefaj_princ),sum( long_1er),sum(anchorefal_1er),sum(long_2do),sum(anchorefaj_2do),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_3_ap_corrfluvial inner join municipios on c3_2_3_ap_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_3_PROV_US                           = "select sum(long_princ),sum(anchorefaj_princ),sum( long_1er),sum(anchorefal_1er),sum(long_2do),sum(anchorefaj_2do),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_3_us_corrfluvial inner join municipios on c3_2_3_us_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_3_PROV_OTROS                        = "select sum(long_princ),sum(anchorefaj_princ),sum( long_1er),sum(anchorefal_1er),sum(long_2do),sum(anchorefaj_2do),sum(suptotzonprotec), anno " +
                                                                           "from c3_2_3_otros_corrfluvial inner join municipios on c3_2_3_otros_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_2_3_PROV_YEARS_AP                    = "select distinct anno from c3_2_3_ap_corrfluvial inner join municipios on c3_2_3_ap_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_2_3_PROV_YEARS_US                    = "select distinct anno from c3_2_3_us_corrfluvial inner join municipios on c3_2_3_us_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_2_3_PROV_YEARS_OTROS                 = "select distinct anno from c3_2_3_otros_corrfluvial inner join municipios on c3_2_3_otros_corrfluvial.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
  //......................................................................................................................
   public static final String G_SQL_3_2_3_Temp_Table                       = "select ((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when suptotzonprotec<>0 then ((((long_princ*anchorefaj_princ)+(long_1er*anchorefal_1er)+(long_2do*anchorefaj_2do))/10000) / suptotzonprotec)*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c_3_2_3_graphicdata order by anno";

//====================== Criterio 3_3 Faja Costera =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_3_3_AP                                   = "select municipios.nombre as municipio, anno as Año, (long_cost_prot*anchorefaj)/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                           "case when long_tot_lin<>0 then (((long_cost_prot*anchorefaj)/10000) / ((long_tot_lin*30)/10000))*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                           " from c3_3_ap_fajacostera inner join municipios on c3_3_ap_fajacostera.municipio=municipios.id " +
                                                                           "where c3_3_ap_fajacostera.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_3_US                                   = "select municipios.nombre as municipio, anno as Año, (long_cost_prot*anchorefaj)/10000 as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                          "case when long_tot_lin<>0 then (((long_cost_prot*anchorefaj)/10000) / ((long_tot_lin*30)/10000))*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                          " from c3_3_us_fajacostera inner join municipios on c3_3_us_fajacostera.municipio=municipios.id " +
                                                                          "where c3_3_us_fajacostera.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_3_EFI_Subgrupo                          = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                           ", sum((long_cost_prot*anchorefaj)/10000) as "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+", " +
                                                                           "case when sum(long_tot_lin)<>0 then (sum((long_cost_prot*anchorefaj)/10000) / ((sum(long_tot_lin)*30)/10000))*100 else 0 end as "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+
                                                                           "from ((c3_3_us_fajacostera inner join usilvicola on c3_3_us_fajacostera.id=usilvicola.id) " +
                                                                           "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_3_us_fajacostera.municipio=municipios.id " +
                                                                           "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_3_3_EFI_TOTAL                             = "select efi.nombre as efi, sum((long_cost_prot*anchorefaj)/10000), " +
                                                                           "case when sum(long_tot_lin)<>0 then (sum((long_cost_prot*anchorefaj)/10000) / ((sum(long_tot_lin)*30)/10000))*100 else 0 end " +
                                                                           "from (c3_3_us_fajacostera inner join usilvicola on c3_3_us_fajacostera.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_3_3_MUN_SubGrupoUS                        = "select municipios.nombre, sum((long_cost_prot*anchorefaj)/10000), " +
                                                                           "case when sum(long_tot_lin)<>0 then (sum((long_cost_prot*anchorefaj)/10000) / ((sum(long_tot_lin)*30)/10000))*100 else 0 end " +
                                                                           "from (c3_3_us_fajacostera inner join municipios on " +
                                                                           "c3_3_us_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_3_MUN_SubGrupoAP                        = "select municipios.nombre, sum((long_cost_prot*anchorefaj)/10000), " +
                                                                           "case when sum(long_tot_lin)<>0 then (sum((long_cost_prot*anchorefaj)/10000) / ((sum(long_tot_lin)*30)/10000))*100 else 0 end " +
                                                                           "from (c3_3_ap_fajacostera inner join municipios on " +
                                                                           "c3_3_ap_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_3_MUN_SubGrupoOTROS                     = "select municipios.nombre, sum((long_cost_prot*anchorefaj)/10000), " +
                                                                           "case when sum(long_tot_lin)<>0 then (sum((long_cost_prot*anchorefaj)/10000) / ((sum(long_tot_lin)*30)/10000))*100 else 0 end " +
                                                                           "from (c3_3_otros_fajacostera inner join municipios on " +
                                                                           "c3_3_otros_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_3_3_MUN_US_SubValues                     = "select sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin), municipios.nombre as municipio " +
                                                                             "from ((c3_3_us_fajacostera inner join municipios on " +
                                                                             "c3_3_us_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_3_us_fajacostera.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_3_MUN_AP_SubValues                     = "select sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin), municipios.nombre as municipio  " +
                                                                             "from ((c3_3_ap_fajacostera inner join municipios on " +
                                                                             "c3_3_ap_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_3_ap_fajacostera.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_3_MUN_OTROS_SubValues                  = "select sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin), municipios.nombre as municipio  " +
                                                                             "from ((c3_3_otros_fajacostera inner join municipios on " +
                                                                             "c3_3_otros_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_3_otros_fajacostera.anno=:anno group by municipios.id, municipios.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_3_3_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin) " +
                                                                             "from ((c3_3_us_fajacostera inner join municipios on " +
                                                                             "c3_3_us_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c3_3_us_fajacostera.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_3_3_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin) " +
                                                                             "from ((c3_3_ap_fajacostera inner join municipios on " +
                                                                             "c3_3_ap_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c3_3_ap_fajacostera.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_3_3_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin) " +
                                                                             "from ((c3_3_otros_fajacostera inner join municipios on " +
                                                                             "c3_3_otros_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c3_3_otros_fajacostera.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_3_3_PROV_SubGrupoUS                      = "select sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin), provincias.nombre " +
                                                                           "from (c3_3_us_fajacostera inner join municipios on " +
                                                                           "c3_3_us_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_3_PROV_SubGrupoAP                      = "select sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin), provincias.nombre " +
                                                                           "from(c3_3_ap_fajacostera inner join municipios on " +
                                                                           "c3_3_ap_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_3_PROV_SubGrupoOTROS                   = "select sum((long_cost_prot*anchorefaj)/10000), sum(long_tot_lin), provincias.nombre " +
                                                                           "from (c3_3_otros_fajacostera inner join municipios on " +
                                                                           "c3_3_otros_fajacostera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_3_3_AP                               = "select (long_cost_prot*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when long_tot_lin<>0 then (((long_cost_prot*anchorefaj)/10000) / ((long_tot_lin*30)/10000))*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_3_ap_fajacostera "+
                                                                           "where c3_3_ap_fajacostera.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_3_AP_YEARS                         = "select distinct anno from c3_3_ap_fajacostera order by anno";
 //......................................................................................................................
   public static final String G_SQL_3_3_US                               = "select (long_cost_prot*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when long_tot_lin<>0 then (((long_cost_prot*anchorefaj)/10000) / ((long_tot_lin*30)/10000))*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_3_us_fajacostera "+
                                                                           "where c3_3_us_fajacostera.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_3_US_YEARS                         = "select distinct anno from c3_3_us_fajacostera order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_3_OTROS_YEARS                      = "select distinct anno from c3_3_otros_fajacostera order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_3_EFI                              = "select (sum(long_cost_prot)*sum(anchorefaj))/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when sum(long_tot_lin)<>0 then (((sum(long_cost_prot)*sum(anchorefaj))/10000) / ((sum(long_tot_lin)*30)/10000))*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c3_3_us_fajacostera inner join usilvicola on c3_3_us_fajacostera.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_3_3_EFI_YEARS                        = "select distinct anno from c3_3_us_fajacostera order by anno";
       //......................................................................................................................
   public static final String G_SQL_3_3_MUN_AP                           = "select sum(long_cost_prot),sum(anchorefaj),sum( long_tot_lin), anno " +
                                                                           "from c3_3_ap_fajacostera inner join municipios on c3_3_ap_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_3_MUN_US                           = "select sum(long_cost_prot),sum(anchorefaj),sum( long_tot_lin), anno " +
                                                                           "from c3_3_us_fajacostera inner join municipios on c3_3_us_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_3_MUN_OTROS                        = "select sum(long_cost_prot),sum(anchorefaj),sum( long_tot_lin), anno " +
                                                                           "from c3_3_otros_fajacostera inner join municipios on c3_3_otros_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_3_MUN_YEARS_AP                    = "select distinct anno from c3_3_ap_fajacostera where municipio=':id' order by anno";

   public static final String G_SQL_3_3_MUN_YEARS_US                    = "select distinct anno from c3_3_us_fajacostera where municipio=':id' order by anno";

   public static final String G_SQL_3_3_MUN_YEARS_OTROS                 = "select distinct anno from c3_3_otros_fajacostera where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_3_3_PROV_AP                          = "select sum(long_cost_prot),sum(anchorefaj),sum( long_tot_lin), anno " +
                                                                           "from c3_3_ap_fajacostera inner join municipios on c3_3_ap_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_3_PROV_US                           = "select sum(long_cost_prot),sum(anchorefaj),sum( long_tot_lin), anno " +
                                                                           "from c3_3_us_fajacostera inner join municipios on c3_3_us_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_3_PROV_OTROS                        = "select sum(long_cost_prot),sum(anchorefaj),sum( long_tot_lin), anno " +
                                                                           "from c3_3_otros_fajacostera inner join municipios on c3_3_otros_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_3_PROV_YEARS_AP                    = "select distinct anno from c3_3_ap_fajacostera inner join municipios on c3_3_ap_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_3_PROV_YEARS_US                    = "select distinct anno from c3_3_us_fajacostera inner join municipios on c3_3_us_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_3_PROV_YEARS_OTROS                 = "select distinct anno from c3_3_otros_fajacostera inner join municipios on c3_3_otros_fajacostera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_3_3_Temp_Table                       = "select (long_cost_prot*anchorefaj)/10000 as  "+CONSTANTS.SUP_PROTEGIDA_HA_COLUMN_NAME+",case when long_tot_lin<>0 then (((long_cost_prot*anchorefaj)/10000) / ((long_tot_lin*30)/10000))*100 else 0 end as  "+CONSTANTS.SUP_PROTEGIDA_PORC_COLUMN_NAME+", anno " +
                                                                           "from c_3_3_graphicdata order by anno";

//====================== Criterio 3_4 Areas afectadas por actividad minera =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_3_4_AP                                   = "select municipios.nombre as municipio, anno as Año, superf_afect as "+CONSTANTS.SUPERFICIE_AFECTADA_COLUMN_NAME+", " +
                                                                           "case when superf_afect<>0 then (superf_cultv/superf_afect)*100 else 0 end as "+CONSTANTS.SUPERFICIE_RECULTIVADA_COLUMN_NAME+
                                                                           " from c3_4_ap_areasafectactvminera inner join municipios on c3_4_ap_areasafectactvminera.municipio=municipios.id " +
                                                                           "where c3_4_ap_areasafectactvminera.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_4_US                                   = "select municipios.nombre as municipio, anno as Año, superf_afect as "+CONSTANTS.SUPERFICIE_AFECTADA_COLUMN_NAME+", " +
                                                                           "case when superf_afect<>0 then (superf_cultv/superf_afect)*100 else 0 end as "+CONSTANTS.SUPERFICIE_RECULTIVADA_COLUMN_NAME+
                                                                          " from c3_4_us_areasafectactvminera inner join municipios on c3_4_us_areasafectactvminera.municipio=municipios.id " +
                                                                          "where c3_4_us_areasafectactvminera.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_3_4_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                          ", sum(superf_afect) as "+CONSTANTS.SUPERFICIE_AFECTADA_COLUMN_NAME+", " +
                                                                           "case when sum(superf_afect)<>0 then (sum(superf_cultv)/sum(superf_afect))*100 else 0 end as "+CONSTANTS.SUPERFICIE_RECULTIVADA_COLUMN_NAME+
                                                                           "from ((c3_4_us_areasafectactvminera inner join usilvicola on c3_4_us_areasafectactvminera.id=usilvicola.id) " +
                                                                           "inner join efi on usilvicola.efi=efi.id)inner join municipios on c3_4_us_areasafectactvminera.municipio=municipios.id " +
                                                                           "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_3_4_EFI_TOTAL                            = "select efi.nombre as efi, sum(superf_afect), case when sum(superf_afect)<>0 then (sum(superf_cultv)/sum(superf_afect))*100 else 0 end " +
                                                                           "from (c3_4_us_areasafectactvminera inner join usilvicola on c3_4_us_areasafectactvminera.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_3_4_MUN_SubGrupoUS                        = "select municipios.nombre, sum(superf_afect), case when sum(superf_afect)<>0 then (sum(superf_cultv)/sum(superf_afect))*100 else 0 end " +
                                                                           "from (c3_4_us_areasafectactvminera inner join municipios on " +
                                                                           "c3_4_us_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_4_MUN_SubGrupoAP                        = "select municipios.nombre, sum(superf_afect), case when sum(superf_afect)<>0 then (sum(superf_cultv)/sum(superf_afect))*100 else 0 end " +
                                                                           "from (c3_4_ap_areasafectactvminera inner join municipios on " +
                                                                           "c3_4_ap_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_3_4_MUN_SubGrupoOTROS                     = "select municipios.nombre, sum(superf_afect), case when sum(superf_afect)<>0 then (sum(superf_cultv)/sum(superf_afect))*100 else 0 end " +
                                                                           "from (c3_4_otros_areasafectactvminera inner join municipios on " +
                                                                           "c3_4_otros_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_3_4_MUN_US_SubValues                     = "select sum(superf_afect), sum(superf_cultv), municipios.nombre as municipio " +
                                                                             "from ((c3_4_us_areasafectactvminera inner join municipios on " +
                                                                             "c3_4_us_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_4_us_areasafectactvminera.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_4_MUN_AP_SubValues                     = "select sum(superf_afect), sum(superf_cultv), municipios.nombre as municipio " +
                                                                             "from ((c3_4_ap_areasafectactvminera inner join municipios on " +
                                                                             "c3_4_ap_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_4_ap_areasafectactvminera.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_3_4_MUN_OTROS_SubValues                  = "select sum(superf_afect), sum(superf_cultv), municipios.nombre as municipio " +
                                                                             "from ((c3_4_otros_areasafectactvminera inner join municipios on " +
                                                                             "c3_4_otros_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c3_4_otros_areasafectactvminera.anno=:anno group by municipios.id, municipios.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_3_4_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, sum(superf_afect), sum(superf_cultv) " +
                                                                             "from ((c3_4_us_areasafectactvminera inner join municipios on " +
                                                                             "c3_4_us_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c3_4_us_areasafectactvminera.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_3_4_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, sum(superf_afect), sum(superf_cultv) " +
                                                                             "from ((c3_4_ap_areasafectactvminera inner join municipios on " +
                                                                             "c3_4_ap_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c3_4_ap_areasafectactvminera.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_3_4_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, sum(superf_afect), sum(superf_cultv) " +
                                                                             "from ((c3_4_otros_areasafectactvminera inner join municipios on " +
                                                                             "c3_4_otros_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c3_4_otros_areasafectactvminera.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_3_4_PROV_SubGrupoUS                      = "select sum(superf_afect), sum(superf_cultv), provincias.nombre " +
                                                                           "from (c3_4_us_areasafectactvminera inner join municipios on " +
                                                                           "c3_4_us_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_4_PROV_SubGrupoAP                      = "select sum(superf_afect), sum(superf_cultv), provincias.nombre " +
                                                                           "from(c3_4_ap_areasafectactvminera inner join municipios on " +
                                                                           "c3_4_ap_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_3_4_PROV_SubGrupoOTROS                   = "select sum(superf_afect), sum(superf_cultv), provincias.nombre " +
                                                                           "from (c3_4_otros_areasafectactvminera inner join municipios on " +
                                                                           "c3_4_otros_areasafectactvminera.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_3_4_AP                               = "select superf_afect as  "+CONSTANTS.SUPERFICIE_AFECTADA_COLUMN_NAME+",case when superf_afect<>0 then (superf_cultv/superf_afect)*100 else 0 end as  "+CONSTANTS.SUPERFICIE_RECULTIVADA_COLUMN_NAME+", anno " +
                                                                           "from c3_4_ap_areasafectactvminera "+
                                                                           "where c3_4_ap_areasafectactvminera.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_4_AP_YEARS                         = "select distinct anno from c3_4_ap_areasafectactvminera order by anno";
 //......................................................................................................................
   public static final String G_SQL_3_4_US                               = "select superf_afect as  "+CONSTANTS.SUPERFICIE_AFECTADA_COLUMN_NAME+",case when superf_afect<>0 then (superf_cultv/superf_afect)*100 else 0 end as  "+CONSTANTS.SUPERFICIE_RECULTIVADA_COLUMN_NAME+", anno " +
                                                                           "from c3_4_us_areasafectactvminera "+
                                                                           "where c3_4_us_areasafectactvminera.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_3_4_US_YEARS                         = "select distinct anno from c3_4_us_areasafectactvminera order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_4_OTROS_YEARS                      = "select distinct anno from c3_4_otros_areasafectactvminera order by anno";

  //......................................................................................................................
   public static final String G_SQL_3_4_EFI                              = "select sum(superf_afect) as  "+CONSTANTS.SUPERFICIE_AFECTADA_COLUMN_NAME+",case when sum(superf_afect)<>0 then (sum(superf_cultv)/sum(superf_afect))*100 else 0 end as  "+CONSTANTS.SUPERFICIE_RECULTIVADA_COLUMN_NAME+", anno " +
                                                                           "from c3_4_us_areasafectactvminera inner join usilvicola on c3_4_us_areasafectactvminera.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_3_4_EFI_YEARS                        = "select distinct anno from c3_4_us_areasafectactvminera order by anno";
       //......................................................................................................................
   public static final String G_SQL_3_4_MUN_AP                           = "select sum(superf_cultv),sum(superf_afect), anno " +
                                                                           "from c3_4_ap_areasafectactvminera inner join municipios on c3_4_ap_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_4_MUN_US                           = "select sum(superf_cultv),sum(superf_afect), anno " +
                                                                           "from c3_4_us_areasafectactvminera inner join municipios on c3_4_us_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_4_MUN_OTROS                        = "select sum(superf_cultv),sum(superf_afect), anno " +
                                                                           "from c3_4_otros_areasafectactvminera inner join municipios on c3_4_otros_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_3_4_MUN_YEARS_AP                    = "select distinct anno from c3_4_ap_areasafectactvminera where municipio=':id' order by anno";

   public static final String G_SQL_3_4_MUN_YEARS_US                    = "select distinct anno from c3_4_us_areasafectactvminera where municipio=':id' order by anno";

   public static final String G_SQL_3_4_MUN_YEARS_OTROS                 = "select distinct anno from c3_4_otros_areasafectactvminera where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_3_4_PROV_AP                          = "select sum(superf_cultv),sum(superf_afect), anno " +
                                                                           "from c3_4_ap_areasafectactvminera inner join municipios on c3_4_ap_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_4_PROV_US                           = "select sum(superf_cultv),sum(superf_afect), anno " +
                                                                           "from c3_4_us_areasafectactvminera inner join municipios on c3_4_us_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_4_PROV_OTROS                        = "select sum(superf_cultv),sum(superf_afect), anno " +
                                                                           "from c3_4_otros_areasafectactvminera inner join municipios on c3_4_otros_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_3_4_PROV_YEARS_AP                    = "select distinct anno from c3_4_ap_areasafectactvminera inner join municipios on c3_4_ap_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_4_PROV_YEARS_US                    = "select distinct anno from c3_4_us_areasafectactvminera inner join municipios on c3_4_us_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_3_4_PROV_YEARS_OTROS                 = "select distinct anno from c3_4_otros_areasafectactvminera inner join municipios on c3_4_otros_areasafectactvminera.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_3_4_Temp_Table                       = "select superf_afect as  "+CONSTANTS.SUPERFICIE_AFECTADA_COLUMN_NAME+",case when superf_afect<>0 then (superf_cultv/superf_afect)*100 else 0 end as  "+CONSTANTS.SUPERFICIE_RECULTIVADA_COLUMN_NAME+", anno " +
                                                                           "from c_3_4_graphicdata order by anno";

//====================== Criterio 4_1 Indice de rendimiento sostenido =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_4_1_AP                                   = "select municipios.nombre as municipio, anno as Año, case when cortanuperm_bprod<>0 then (cortanuejec_bprod/cortanuperm_bprod)*100 else 0 end as " +
                                                                           CONSTANTS.IRS_BOSQ_PROD_COLUMN_NAME+", case when cortanuperm_bprot<>0 then (cortanuejec_bprot/cortanuperm_bprot)*100 else 0 end as "+CONSTANTS.IRS_BOSQ_PROT_COLUMN_NAME+
                                                                           ", case when (cortanuperm_bprod+cortanuperm_bprot)<>0 then ((cortanuejec_bprod+cortanuejec_bprot)/(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end as "+CONSTANTS.IRS_TOTAL_COLUMN_NAME+" "+
                                                                           "from c4_1_ap_indrendsostenido inner join municipios on c4_1_ap_indrendsostenido.municipio=municipios.id " +
                                                                           "where c4_1_ap_indrendsostenido.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_4_1_US                                   = "select municipios.nombre as municipio, anno as Año, case when cortanuperm_bprod<>0 then (cortanuejec_bprod/cortanuperm_bprod)*100 else 0 end as " +
                                                                           CONSTANTS.IRS_BOSQ_PROD_COLUMN_NAME+", case when cortanuperm_bprot<>0 then (cortanuejec_bprot/cortanuperm_bprot)*100 else 0 end as "+CONSTANTS.IRS_BOSQ_PROT_COLUMN_NAME+
                                                                           ", case when (cortanuperm_bprod+cortanuperm_bprot)<>0 then ((cortanuejec_bprod+cortanuejec_bprot)/(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end as "+CONSTANTS.IRS_TOTAL_COLUMN_NAME+" "+
                                                                          " from c4_1_us_indrendsostenido inner join municipios on c4_1_us_indrendsostenido.municipio=municipios.id " +
                                                                          "where c4_1_us_indrendsostenido.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_4_1_EFI_Subgrupo                          = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                           ", case when sum(cortanuperm_bprod)<>0 then (sum(cortanuejec_bprod)/sum(cortanuperm_bprod))*100 else 0 end as " +
                                                                           CONSTANTS.IRS_BOSQ_PROD_COLUMN_NAME+", case when sum(cortanuperm_bprot)<>0 then (sum(cortanuejec_bprot)/sum(cortanuperm_bprot))*100 else 0 end as "+CONSTANTS.IRS_BOSQ_PROT_COLUMN_NAME+
                                                                           ", case when sum(cortanuperm_bprod+cortanuperm_bprot)<>0 then (sum(cortanuejec_bprod+cortanuejec_bprot)/sum(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end as "+CONSTANTS.IRS_TOTAL_COLUMN_NAME+" "+
                                                                           "from ((c4_1_us_indrendsostenido inner join usilvicola on c4_1_us_indrendsostenido.id=usilvicola.id) " +
                                                                           "inner join efi on usilvicola.efi=efi.id)inner join municipios on c4_1_us_indrendsostenido.municipio=municipios.id " +
                                                                           "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_4_1_EFI_TOTAL                             = "select efi.nombre as efi, case when sum(cortanuperm_bprod)<>0 then (sum(cortanuejec_bprod)/sum(cortanuperm_bprod))*100 else 0 end, " +
                                                                           "case when sum(cortanuperm_bprot)<>0 then (sum(cortanuejec_bprot)/sum(cortanuperm_bprot))*100 else 0 end, "+
                                                                           "case when sum(cortanuperm_bprod+cortanuperm_bprot)<>0 then (sum(cortanuejec_bprod+cortanuejec_bprot)/sum(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end " +
                                                                           "from (c4_1_us_indrendsostenido inner join usilvicola on c4_1_us_indrendsostenido.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_4_1_MUN_SubGrupoUS                        = "select municipios.nombre, case when sum(cortanuperm_bprod)<>0 then (sum(cortanuejec_bprod)/sum(cortanuperm_bprod))*100 else 0 end, " +
                                                                           "case when sum(cortanuperm_bprot)<>0 then (sum(cortanuejec_bprot)/sum(cortanuperm_bprot))*100 else 0 end, "+
                                                                           "case when sum(cortanuperm_bprod+cortanuperm_bprot)<>0 then (sum(cortanuejec_bprod+cortanuejec_bprot)/sum(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end " +
                                                                           "from (c4_1_us_indrendsostenido inner join municipios on " +
                                                                           "c4_1_us_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_4_1_MUN_SubGrupoAP                        = "select municipios.nombre, case when sum(cortanuperm_bprod)<>0 then (sum(cortanuejec_bprod)/sum(cortanuperm_bprod))*100 else 0 end, " +
                                                                           "case when sum(cortanuperm_bprot)<>0 then (sum(cortanuejec_bprot)/sum(cortanuperm_bprot))*100 else 0 end, "+
                                                                           "case when sum(cortanuperm_bprod+cortanuperm_bprot)<>0 then (sum(cortanuejec_bprod+cortanuejec_bprot)/sum(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end " +
                                                                           "from (c4_1_ap_indrendsostenido inner join municipios on " +
                                                                           "c4_1_ap_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_4_1_MUN_SubGrupoOTROS                     = "select municipios.nombre, case when sum(cortanuperm_bprod)<>0 then (sum(cortanuejec_bprod)/sum(cortanuperm_bprod))*100 else 0 end, " +
                                                                           "case when sum(cortanuperm_bprot)<>0 then (sum(cortanuejec_bprot)/sum(cortanuperm_bprot))*100 else 0 end, "+
                                                                           "case when sum(cortanuperm_bprod+cortanuperm_bprot)<>0 then (sum(cortanuejec_bprod+cortanuejec_bprot)/sum(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end " +
                                                                           "from (c4_1_otros_indrendsostenido inner join municipios on " +
                                                                           "c4_1_otros_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_4_1_MUN_US_SubValues                     = "select sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot), municipios.nombre " +
                                                                             "from ((c4_1_us_indrendsostenido inner join municipios on " +
                                                                             "c4_1_us_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c4_1_us_indrendsostenido.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_4_1_MUN_AP_SubValues                     = "select sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot), municipios.nombre " +
                                                                             "from ((c4_1_ap_indrendsostenido inner join municipios on " +
                                                                             "c4_1_ap_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c4_1_ap_indrendsostenido.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_4_1_MUN_OTROS_SubValues                  = "select sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot), municipios.nombre " +
                                                                             "from ((c4_1_otros_indrendsostenido inner join municipios on " +
                                                                             "c4_1_otros_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c4_1_otros_indrendsostenido.anno=:anno group by municipios.id, municipios.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_4_1_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot) " +
                                                                             "from ((c4_1_us_indrendsostenido inner join municipios on " +
                                                                             "c4_1_us_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c4_1_us_indrendsostenido.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_4_1_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot) " +
                                                                             "from ((c4_1_ap_indrendsostenido inner join municipios on " +
                                                                             "c4_1_ap_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c4_1_ap_indrendsostenido.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_4_1_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot) " +
                                                                             "from ((c4_1_otros_indrendsostenido inner join municipios on " +
                                                                             "c4_1_otros_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c4_1_otros_indrendsostenido.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_4_1_PROV_SubGrupoUS                       = "select sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot), provincias.nombre " +
                                                                           "from (c4_1_us_indrendsostenido inner join municipios on " +
                                                                           "c4_1_us_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_4_1_PROV_SubGrupoAP                       = "select sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot), provincias.nombre " +
                                                                           "from(c4_1_ap_indrendsostenido inner join municipios on " +
                                                                           "c4_1_ap_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_4_1_PROV_SubGrupoOTROS                    = "select sum(cortanuejec_bprod), sum(cortanuperm_bprod), sum(cortanuejec_bprot), sum(cortanuperm_bprot), provincias.nombre " +
                                                                           "from (c4_1_otros_indrendsostenido inner join municipios on " +
                                                                           "c4_1_otros_indrendsostenido.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_4_1_AP                               = "select case when cortanuperm_bprod<>0 then (cortanuejec_bprod/cortanuperm_bprod)*100 else 0 end as  "+CONSTANTS.IRS_BOSQ_PROD_COLUMN_NAME+",case when cortanuperm_bprot<>0 then (cortanuejec_bprot/cortanuperm_bprot)*100 else 0 end as  "+CONSTANTS.IRS_BOSQ_PROT_COLUMN_NAME+
                                                                           ", case when (cortanuperm_bprod+cortanuperm_bprot)<>0 then ((cortanuejec_bprod+cortanuejec_bprot)/(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end as "+CONSTANTS.IRS_TOTAL_COLUMN_NAME+" "+", anno " +
                                                                           "from c4_1_ap_indrendsostenido "+
                                                                           "where c4_1_ap_indrendsostenido.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_4_1_AP_YEARS                         = "select distinct anno from c4_1_ap_indrendsostenido order by anno";
 //......................................................................................................................
   public static final String G_SQL_4_1_US                               = "select case when cortanuperm_bprod<>0 then (cortanuejec_bprod/cortanuperm_bprod)*100 else 0 end as  "+CONSTANTS.IRS_BOSQ_PROD_COLUMN_NAME+",case when cortanuperm_bprot<>0 then (cortanuejec_bprot/cortanuperm_bprot)*100 else 0 end as  "+CONSTANTS.IRS_BOSQ_PROT_COLUMN_NAME+
                                                                           ", case when (cortanuperm_bprod+cortanuperm_bprot)<>0 then ((cortanuejec_bprod+cortanuejec_bprot)/(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end as "+CONSTANTS.IRS_TOTAL_COLUMN_NAME+" "+", anno " +
                                                                           "from c4_1_us_indrendsostenido "+
                                                                           "where c4_1_us_indrendsostenido.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_4_1_US_YEARS                         = "select distinct anno from c4_1_us_indrendsostenido order by anno";

  //......................................................................................................................
   public static final String G_SQL_4_1_OTROS_YEARS                      = "select distinct anno from c4_1_otros_indrendsostenido order by anno";

  //......................................................................................................................
   public static final String G_SQL_4_1_EFI                              = "select case when sum(cortanuperm_bprod)<>0 then (sum(cortanuejec_bprod)/sum(cortanuperm_bprod))*100 else 0 end as  "+CONSTANTS.IRS_BOSQ_PROD_COLUMN_NAME+",case when sum(cortanuperm_bprot)<>0 then (sum(cortanuejec_bprot)/sum(cortanuperm_bprot))*100 else 0 end as  "+CONSTANTS.IRS_BOSQ_PROT_COLUMN_NAME+
                                                                           ", case when sum(cortanuperm_bprod+cortanuperm_bprot)<>0 then (sum(cortanuejec_bprod+cortanuejec_bprot)/sum(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end as "+CONSTANTS.IRS_TOTAL_COLUMN_NAME+", anno " +
                                                                           "from c4_1_us_indrendsostenido inner join usilvicola on c4_1_us_indrendsostenido.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_4_1_EFI_YEARS                        = "select distinct anno from c4_1_us_indrendsostenido order by anno";
       //......................................................................................................................
   public static final String G_SQL_4_1_MUN_AP                           = "select sum(cortanuejec_bprod),sum(cortanuperm_bprod),sum(cortanuejec_bprot),sum(cortanuperm_bprot), anno " +
                                                                           "from c4_1_ap_indrendsostenido inner join municipios on c4_1_ap_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_1_MUN_US                           = "select sum(cortanuejec_bprod),sum(cortanuperm_bprod),sum(cortanuejec_bprot),sum(cortanuperm_bprot), anno " +
                                                                           "from c4_1_us_indrendsostenido inner join municipios on c4_1_us_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_1_MUN_OTROS                        = "select sum(cortanuejec_bprod),sum(cortanuperm_bprod),sum(cortanuejec_bprot),sum(cortanuperm_bprot), anno " +
                                                                           "from c4_1_otros_indrendsostenido inner join municipios on c4_1_otros_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_1_MUN_YEARS_AP                    = "select distinct anno from c4_1_ap_indrendsostenido where municipio=':id' order by anno";

   public static final String G_SQL_4_1_MUN_YEARS_US                    = "select distinct anno from c4_1_us_indrendsostenido where municipio=':id' order by anno";

   public static final String G_SQL_4_1_MUN_YEARS_OTROS                 = "select distinct anno from c4_1_otros_indrendsostenido where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_4_1_PROV_AP                          = "select sum(cortanuejec_bprod),sum(cortanuperm_bprod),sum(cortanuejec_bprot),sum(cortanuperm_bprot), anno " +
                                                                           "from c4_1_ap_indrendsostenido inner join municipios on c4_1_ap_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_1_PROV_US                           = "select sum(cortanuejec_bprod),sum(cortanuperm_bprod),sum(cortanuejec_bprot),sum(cortanuperm_bprot), anno " +
                                                                           "from c4_1_us_indrendsostenido inner join municipios on c4_1_us_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_1_PROV_OTROS                        = "select sum(cortanuejec_bprod),sum(cortanuperm_bprod),sum(cortanuejec_bprot),sum(cortanuperm_bprot), anno " +
                                                                           "from c4_1_otros_indrendsostenido inner join municipios on c4_1_otros_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_1_PROV_YEARS_AP                    = "select distinct anno from c4_1_ap_indrendsostenido inner join municipios on c4_1_ap_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_4_1_PROV_YEARS_US                    = "select distinct anno from c4_1_us_indrendsostenido inner join municipios on c4_1_us_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_4_1_PROV_YEARS_OTROS                 = "select distinct anno from c4_1_otros_indrendsostenido inner join municipios on c4_1_otros_indrendsostenido.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_4_1_Temp_Table                       = "select case when cortanuperm_bprod<>0 then (cortanuejec_bprod/cortanuperm_bprod)*100 else 0 end as  "+CONSTANTS.IRS_BOSQ_PROD_COLUMN_NAME+", " +
                                                                           "case when cortanuperm_bprot<>0 then (cortanuejec_bprot/cortanuperm_bprot)*100 else 0 end as  "+CONSTANTS.IRS_BOSQ_PROT_COLUMN_NAME+
                                                                           ", case when (cortanuperm_bprod+cortanuperm_bprot)<>0 then ((cortanuejec_bprod+cortanuejec_bprot)/(cortanuperm_bprod+cortanuperm_bprot))*100 else 0 end as "+CONSTANTS.IRS_TOTAL_COLUMN_NAME+", anno " +
                                                                           "from c_4_1_graphicdata order by anno";

//====================== Criterio 4_2 Porciento de Madera Xtr Bosques Naturales =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_4_2_AP                                   = "select municipios.nombre as municipio, c4_2_ap_porcmadextrbosqnat.anno as Año, case when (cortanuejec_bprod+cortanuejec_bprot)<>0 then " +
                                                                           "(volm_aprov/(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end as "+CONSTANTS.PORC_MADERA_EXT_COLUMN_NAME+
                                                                           " from (c4_2_ap_porcmadextrbosqnat inner join municipios on c4_2_ap_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                           "inner join c4_1_ap_indrendsostenido on c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id " +
                                                                           "where c4_2_ap_porcmadextrbosqnat.id=':id' and c4_2_ap_porcmadextrbosqnat.anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_4_2_US                                   = "select municipios.nombre as municipio, c4_2_us_porcmadextrbosqnat.anno as Año, case when (cortanuejec_bprod+cortanuejec_bprot)<>0 then " +
                                                                          "(volm_aprov/(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end as "+CONSTANTS.PORC_MADERA_EXT_COLUMN_NAME+
                                                                          " from (c4_2_us_porcmadextrbosqnat inner join municipios on c4_2_us_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                          "inner join c4_1_us_indrendsostenido on c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id " +
                                                                          "where c4_2_us_porcmadextrbosqnat.id=':id' and c4_2_us_porcmadextrbosqnat.anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_4_2_EFI_Subgrupo                          = "select municipios.nombre as municipio, c4_2_us_porcmadextrbosqnat.anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                           ", case when sum(cortanuejec_bprod+cortanuejec_bprot)<>0 then " +
                                                                           "(sum(volm_aprov)/sum(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end as "+CONSTANTS.PORC_MADERA_EXT_COLUMN_NAME+
                                                                           "from (((c4_2_us_porcmadextrbosqnat inner join usilvicola on c4_2_us_porcmadextrbosqnat.id=usilvicola.id) " +
                                                                           "inner join efi on usilvicola.efi=efi.id)inner join municipios on c4_2_us_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                           "inner join c4_1_us_indrendsostenido on c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id " +
                                                                           "where efi.id=':id' and c4_2_us_porcmadextrbosqnat.anno=:anno " +
                                                                           "group by municipios.nombre, c4_2_us_porcmadextrbosqnat.anno, efi.nombre, usilvicola.nombre";

public static final String SQL_4_2_EFI_TOTAL                            = "select efi.nombre as efi, case when sum(cortanuejec_bprod+cortanuejec_bprot)<>0 then " +
                                                                           "(sum(volm_aprov)/sum(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end " +
                                                                           "from ((c4_2_us_porcmadextrbosqnat inner join usilvicola on c4_2_us_porcmadextrbosqnat.id=usilvicola.id) " +
                                                                           "inner join efi on usilvicola.efi=efi.id)inner join c4_1_us_indrendsostenido on " +
                                                                           "c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id " +
                                                                           "where efi.id=':id' and c4_2_us_porcmadextrbosqnat.anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_4_2_MUN_SubGrupoUS                        = "select municipios.nombre, case when sum(cortanuejec_bprod+cortanuejec_bprot)<>0 then " +
                                                                           "(sum(volm_aprov)/sum(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end " +
                                                                           "from ((c4_2_us_porcmadextrbosqnat inner join municipios on " +
                                                                           "c4_2_us_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c4_1_us_indrendsostenido on c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id " +
                                                                           "where municipios.id=':id' and c4_2_us_porcmadextrbosqnat.anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_4_2_MUN_SubGrupoAP                        = "select municipios.nombre, case when sum(cortanuejec_bprod+cortanuejec_bprot)<>0 then " +
                                                                           "(sum(volm_aprov)/sum(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end " +
                                                                           "from ((c4_2_ap_porcmadextrbosqnat inner join municipios on " +
                                                                           "c4_2_ap_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c4_1_ap_indrendsostenido on c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id " +
                                                                           "where municipios.id=':id' and c4_2_ap_porcmadextrbosqnat.anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_4_2_MUN_SubGrupoOTROS                     = "select municipios.nombre, case when sum(cortanuejec_bprod+cortanuejec_bprot)<>0 then " +
                                                                           "(sum(volm_aprov)/sum(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end " +
                                                                           "from ((c4_2_otros_porcmadextrbosqnat inner join municipios on " +
                                                                           "c4_2_otros_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c4_1_otros_indrendsostenido on c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id " +
                                                                           "where municipios.id=':id' and c4_2_otros_porcmadextrbosqnat.anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_4_2_MUN_US_SubValues                     = "select sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov), municipios.nombre " +
                                                                             "from ((c4_2_us_porcmadextrbosqnat inner join municipios on " +
                                                                             "c4_2_us_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join c4_1_us_indrendsostenido on c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id " +
                                                                             "where municipios.id=':id' and c4_2_us_porcmadextrbosqnat.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_4_2_MUN_AP_SubValues                     = "select sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov), municipios.nombre " +
                                                                             "from ((c4_2_ap_porcmadextrbosqnat inner join municipios on " +
                                                                             "c4_2_ap_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join c4_1_ap_indrendsostenido on c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id " +
                                                                             "where municipios.id=':id' and c4_2_ap_porcmadextrbosqnat.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_4_2_MUN_OTROS_SubValues                  = "select sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov), municipios.nombre " +
                                                                             "from ((c4_2_otros_porcmadextrbosqnat inner join municipios on " +
                                                                             "c4_2_otros_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join c4_1_otros_indrendsostenido on c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id " +
                                                                             "where municipios.id=':id' and c4_2_otros_porcmadextrbosqnat.anno=:anno group by municipios.id, municipios.nombre;";
 //----------------------------------------------------------------------------------
 public static final String SQL_4_2_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov) " +
                                                                             "from (((c4_2_us_porcmadextrbosqnat inner join municipios on " +
                                                                             "c4_2_us_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c4_2_us_porcmadextrbosqnat.id=usilvicola.id) " +
                                                                             "inner join c4_1_us_indrendsostenido on c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id " +
                                                                             "where municipios.id=':id' and c4_2_us_porcmadextrbosqnat.anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_4_2_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov) " +
                                                                             "from (((c4_2_ap_porcmadextrbosqnat inner join municipios on " +
                                                                             "c4_2_ap_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c4_2_ap_porcmadextrbosqnat.id=area_protegida.id)" +
                                                                             "inner join c4_1_ap_indrendsostenido on c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id " +
                                                                             "where municipios.id=':id' and c4_2_ap_porcmadextrbosqnat.anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_4_2_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov) " +
                                                                             "from (((c4_2_otros_porcmadextrbosqnat inner join municipios on " +
                                                                             "c4_2_otros_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c4_2_otros_porcmadextrbosqnat.id=otros.id)" +
                                                                             "inner join c4_1_otros_indrendsostenido on c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id " +
                                                                             "where municipios.id=':id' and c4_2_otros_porcmadextrbosqnat.anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_4_2_PROV_SubGrupoUS                       = "select sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov), provincias.nombre " +
                                                                           "from ((c4_2_us_porcmadextrbosqnat inner join municipios on " +
                                                                           "c4_2_us_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c4_1_us_indrendsostenido on c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id " +
                                                                           "where c4_2_us_porcmadextrbosqnat.anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_4_2_PROV_SubGrupoAP                       = "select sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov), provincias.nombre " +
                                                                           "from((c4_2_ap_porcmadextrbosqnat inner join municipios on " +
                                                                           "c4_2_ap_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c4_1_ap_indrendsostenido on c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id " +
                                                                           "where c4_2_ap_porcmadextrbosqnat.anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_4_2_PROV_SubGrupoOTROS                    = "select sum(cortanuejec_bprod+cortanuejec_bprot), sum(volm_aprov), provincias.nombre " +
                                                                           "from ((c4_2_otros_porcmadextrbosqnat inner join municipios on " +
                                                                           "c4_2_otros_porcmadextrbosqnat.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                           "inner join c4_1_otros_indrendsostenido on c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id " +
                                                                           "where c4_2_otros_porcmadextrbosqnat.anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_4_2_AP                               = "select case when (cortanuejec_bprod+cortanuejec_bprot)<>0 then (volm_aprov/(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end as  "+CONSTANTS.PORC_MADERA_EXT_COLUMN_NAME+", c4_2_ap_porcmadextrbosqnat.anno " +
                                                                           "from c4_2_ap_porcmadextrbosqnat inner join c4_1_ap_indrendsostenido on (c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id and " +
                                                                           "c4_2_ap_porcmadextrbosqnat.anno=c4_1_ap_indrendsostenido.anno and c4_2_ap_porcmadextrbosqnat.municipio=c4_1_ap_indrendsostenido.municipio)"+
                                                                           "where c4_2_ap_porcmadextrbosqnat.id=':id' and c4_2_ap_porcmadextrbosqnat.anno in(:anno) order by c4_2_ap_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_AP_YEARS                         = "select distinct c4_2_ap_porcmadextrbosqnat.anno from c4_2_ap_porcmadextrbosqnat inner join c4_1_ap_indrendsostenido on (c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id and " +
                                                                           "c4_2_ap_porcmadextrbosqnat.anno=c4_1_ap_indrendsostenido.anno and c4_2_ap_porcmadextrbosqnat.municipio=c4_1_ap_indrendsostenido.municipio) " +
                                                                           "order by c4_2_ap_porcmadextrbosqnat.anno";
 //......................................................................................................................
   public static final String G_SQL_4_2_US                               = "select case when (cortanuejec_bprod+cortanuejec_bprot)<>0 then (volm_aprov/(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end as  "+CONSTANTS.PORC_MADERA_EXT_COLUMN_NAME+", c4_2_us_porcmadextrbosqnat.anno " +
                                                                           "from c4_2_us_porcmadextrbosqnat inner join c4_1_us_indrendsostenido on (c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id and " +
                                                                           "c4_2_us_porcmadextrbosqnat.anno=c4_1_us_indrendsostenido.anno and c4_2_us_porcmadextrbosqnat.municipio=c4_1_us_indrendsostenido.municipio) "+
                                                                           "where c4_2_us_porcmadextrbosqnat.id=':id' and c4_2_us_porcmadextrbosqnat.anno in(:anno) order by c4_2_us_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_US_YEARS                         = "select distinct c4_2_us_porcmadextrbosqnat.anno from c4_2_us_porcmadextrbosqnat inner join c4_1_us_indrendsostenido on (c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id and " +
                                                                           "c4_2_us_porcmadextrbosqnat.anno=c4_1_us_indrendsostenido.anno and c4_2_us_porcmadextrbosqnat.municipio=c4_1_us_indrendsostenido.municipio) " +
                                                                           "order by c4_2_us_porcmadextrbosqnat.anno";

  //......................................................................................................................
   public static final String G_SQL_4_2_OTROS_YEARS                      = "select distinct c4_2_otros_porcmadextrbosqnat.anno from c4_2_otros_porcmadextrbosqnat inner join c4_1_otros_indrendsostenido on (c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id and " +
                                                                           "c4_2_otros_porcmadextrbosqnat.anno=c4_1_otros_indrendsostenido.anno and c4_2_otros_porcmadextrbosqnat.municipio=c4_1_otros_indrendsostenido.municipio) " +
                                                                           "order by c4_2_otros_porcmadextrbosqnat.anno";

  //......................................................................................................................
   public static final String G_SQL_4_2_EFI                              = "select case when sum(cortanuejec_bprod+cortanuejec_bprot)<>0 then (sum(volm_aprov)/sum(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end as  "+CONSTANTS.PORC_MADERA_EXT_COLUMN_NAME+", c4_2_us_porcmadextrbosqnat.anno " +
                                                                           "from (c4_2_us_porcmadextrbosqnat inner join usilvicola on c4_2_us_porcmadextrbosqnat.id=usilvicola.id)" +
                                                                           "inner join c4_1_us_indrendsostenido on (c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id and " +
                                                                           "c4_2_us_porcmadextrbosqnat.anno=c4_1_us_indrendsostenido.anno and c4_2_us_porcmadextrbosqnat.municipio=c4_1_us_indrendsostenido.municipio) "+
                                                                           "where usilvicola.efi=':id' and c4_2_us_porcmadextrbosqnat.anno in(:anno) " +
                                                                           "group by usilvicola.efi, c4_2_us_porcmadextrbosqnat.anno order by c4_2_us_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_EFI_YEARS                        = "select distinct c4_2_us_porcmadextrbosqnat.anno from c4_2_us_porcmadextrbosqnat inner join c4_1_us_indrendsostenido on (c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id and " +
                                                                           "c4_2_us_porcmadextrbosqnat.anno=c4_1_us_indrendsostenido.anno and c4_2_us_porcmadextrbosqnat.municipio=c4_1_us_indrendsostenido.municipio)" +
                                                                           "order by c4_2_us_porcmadextrbosqnat.anno";
       //......................................................................................................................
   public static final String G_SQL_4_2_MUN_AP                           = "select sum(cortanuejec_bprod),sum(cortanuejec_bprot),sum(volm_aprov), c4_2_ap_porcmadextrbosqnat.anno " +
                                                                           "from (c4_2_ap_porcmadextrbosqnat inner join municipios on c4_2_ap_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                           "inner join c4_1_ap_indrendsostenido on (c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id and " +
                                                                           "c4_2_ap_porcmadextrbosqnat.anno=c4_1_ap_indrendsostenido.anno and c4_2_ap_porcmadextrbosqnat.municipio=c4_1_ap_indrendsostenido.municipio) "+
                                                                           "where municipios.id=':id' and c4_2_ap_porcmadextrbosqnat.anno in(:anno) " +
                                                                           "group by municipios.id, c4_2_ap_porcmadextrbosqnat.anno order by c4_2_ap_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_MUN_US                           = "select sum(cortanuejec_bprod),sum(cortanuejec_bprot),sum(volm_aprov), c4_2_us_porcmadextrbosqnat.anno " +
                                                                           "from (c4_2_us_porcmadextrbosqnat inner join municipios on c4_2_us_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                           "inner join c4_1_us_indrendsostenido on (c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id and " +
                                                                           "c4_2_us_porcmadextrbosqnat.anno=c4_1_us_indrendsostenido.anno and c4_2_us_porcmadextrbosqnat.municipio=c4_1_us_indrendsostenido.municipio) "+
                                                                           "where municipios.id=':id' and c4_2_us_porcmadextrbosqnat.anno in(:anno) " +
                                                                           "group by municipios.id, c4_2_us_porcmadextrbosqnat.anno order by c4_2_us_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_MUN_OTROS                        = "select sum(cortanuejec_bprod),sum(cortanuejec_bprot),sum(volm_aprov), c4_2_otros_porcmadextrbosqnat.anno " +
                                                                           "from (c4_2_otros_porcmadextrbosqnat inner join municipios on c4_2_otros_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                           "inner join c4_1_otros_indrendsostenido on (c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id and " +
                                                                           "c4_2_otros_porcmadextrbosqnat.anno=c4_1_otros_indrendsostenido.anno and c4_2_otros_porcmadextrbosqnat.municipio=c4_1_otros_indrendsostenido.municipio) "+
                                                                           "where municipios.id=':id' and c4_2_otros_porcmadextrbosqnat.anno in(:anno) " +
                                                                           "group by municipios.id, c4_2_otros_porcmadextrbosqnat.anno order by c4_2_otros_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_MUN_YEARS_AP                    = "select distinct c4_2_ap_porcmadextrbosqnat.anno from c4_2_ap_porcmadextrbosqnat inner join c4_1_ap_indrendsostenido on (c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id and " +
                                                                          "c4_2_ap_porcmadextrbosqnat.anno=c4_1_ap_indrendsostenido.anno and c4_2_ap_porcmadextrbosqnat.municipio=c4_1_ap_indrendsostenido.municipio) " +
                                                                          "where c4_2_ap_porcmadextrbosqnat.municipio=':id' order by c4_2_ap_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_MUN_YEARS_US                    = "select distinct c4_2_us_porcmadextrbosqnat.anno from c4_2_us_porcmadextrbosqnat inner join c4_1_us_indrendsostenido on (c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id and " +
                                                                          "c4_2_us_porcmadextrbosqnat.anno=c4_1_us_indrendsostenido.anno and c4_2_us_porcmadextrbosqnat.municipio=c4_1_us_indrendsostenido.municipio) " +
                                                                          "where c4_2_us_porcmadextrbosqnat.municipio=':id' order by c4_2_us_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_MUN_YEARS_OTROS                 = "select distinct c4_2_otros_porcmadextrbosqnat.anno from c4_2_otros_porcmadextrbosqnat inner join c4_1_otros_indrendsostenido on (c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id and " +
                                                                          "c4_2_otros_porcmadextrbosqnat.anno=c4_1_otros_indrendsostenido.anno and c4_2_otros_porcmadextrbosqnat.municipio=c4_1_otros_indrendsostenido.municipio) " +
                                                                          "where c4_2_otros_porcmadextrbosqnat.municipio=':id' order by c4_2_otros_porcmadextrbosqnat.anno";
//......................................................................................................................
   public static final String G_SQL_4_2_PROV_AP                          = "select sum(cortanuejec_bprod),sum(cortanuejec_bprot),sum(volm_aprov), c4_2_ap_porcmadextrbosqnat.anno " +
                                                                           "from (c4_2_ap_porcmadextrbosqnat inner join municipios on c4_2_ap_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                           "inner join c4_1_ap_indrendsostenido on (c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id and " +
                                                                           "c4_2_ap_porcmadextrbosqnat.anno=c4_1_ap_indrendsostenido.anno and c4_2_ap_porcmadextrbosqnat.municipio=c4_1_ap_indrendsostenido.municipio) "+
                                                                           "where municipios.provincia=':id' and c4_2_ap_porcmadextrbosqnat.anno in(:anno) " +
                                                                           "group by municipios.provincia, c4_2_ap_porcmadextrbosqnat.anno order by c4_2_ap_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_PROV_US                           = "select sum(cortanuejec_bprod),sum(cortanuejec_bprot),sum(volm_aprov), c4_2_us_porcmadextrbosqnat.anno " +
                                                                           "from (c4_2_us_porcmadextrbosqnat inner join municipios on c4_2_us_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                           "inner join c4_1_us_indrendsostenido on (c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id and " +
                                                                           "c4_2_us_porcmadextrbosqnat.anno=c4_1_us_indrendsostenido.anno and c4_2_us_porcmadextrbosqnat.municipio=c4_1_us_indrendsostenido.municipio) "+
                                                                           "where municipios.provincia=':id' and c4_2_us_porcmadextrbosqnat.anno in(:anno) " +
                                                                           "group by municipios.provincia, c4_2_us_porcmadextrbosqnat.anno order by c4_2_us_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_PROV_OTROS                        = "select sum(cortanuejec_bprod),sum(cortanuejec_bprot),sum(volm_aprov), c4_2_otros_porcmadextrbosqnat.anno " +
                                                                           "from (c4_2_otros_porcmadextrbosqnat inner join municipios on c4_2_otros_porcmadextrbosqnat.municipio=municipios.id)" +
                                                                           "inner join c4_1_otros_indrendsostenido on (c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id and " +
                                                                           "c4_2_otros_porcmadextrbosqnat.anno=c4_1_otros_indrendsostenido.anno and c4_2_otros_porcmadextrbosqnat.municipio=c4_1_otros_indrendsostenido.municipio) "+
                                                                           "where municipios.provincia=':id' and c4_2_otros_porcmadextrbosqnat.anno in(:anno) " +
                                                                           "group by municipios.provincia, c4_2_otros_porcmadextrbosqnat.anno order by c4_2_otros_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_PROV_YEARS_AP                    = "select distinct c4_2_ap_porcmadextrbosqnat.anno from (c4_2_ap_porcmadextrbosqnat inner join c4_1_ap_indrendsostenido on (c4_2_ap_porcmadextrbosqnat.id=c4_1_ap_indrendsostenido.id and " +
                                                                           "c4_2_ap_porcmadextrbosqnat.anno=c4_1_ap_indrendsostenido.anno and c4_2_ap_porcmadextrbosqnat.municipio=c4_1_ap_indrendsostenido.municipio))" +
                                                                           "inner join municipios on c4_2_ap_porcmadextrbosqnat.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by c4_2_ap_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_PROV_YEARS_US                    = "select distinct c4_2_us_porcmadextrbosqnat.anno from (c4_2_us_porcmadextrbosqnat inner join c4_1_us_indrendsostenido on (c4_2_us_porcmadextrbosqnat.id=c4_1_us_indrendsostenido.id and " +
                                                                           "c4_2_us_porcmadextrbosqnat.anno=c4_1_us_indrendsostenido.anno and c4_2_us_porcmadextrbosqnat.municipio=c4_1_us_indrendsostenido.municipio))" +
                                                                           "inner join municipios on c4_2_us_porcmadextrbosqnat.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by c4_2_us_porcmadextrbosqnat.anno";

   public static final String G_SQL_4_2_PROV_YEARS_OTROS                 = "select distinct c4_2_otros_porcmadextrbosqnat.anno from (c4_2_otros_porcmadextrbosqnat inner join c4_1_otros_indrendsostenido on (c4_2_otros_porcmadextrbosqnat.id=c4_1_otros_indrendsostenido.id and " +
                                                                           "c4_2_otros_porcmadextrbosqnat.anno=c4_1_otros_indrendsostenido.anno and c4_2_otros_porcmadextrbosqnat.municipio=c4_1_otros_indrendsostenido.municipio))" +
                                                                           "inner join municipios on c4_2_otros_porcmadextrbosqnat.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by c4_2_otros_porcmadextrbosqnat.anno";
       //......................................................................................................................

   public static final String G_SQL_4_2_Temp_Table                       = "select case when (cortanuejec_bprod+cortanuejec_bprot)<>0 then (volm_aprov/(cortanuejec_bprod+cortanuejec_bprot))*100 else 0 end as  "+CONSTANTS.PORC_MADERA_EXT_COLUMN_NAME+", anno " +
                                                                           "from c_4_2_graphicdata order by anno";

//====================== Criterio 4_3 Talas =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_4_3_AP                              = "select municipios.nombre as municipio, anno as Año , c4_3_talas.nombre as "+CONSTANTS.TALA_COLUMN_NAME +
                                                                        ", superftot as "+CONSTANTS.SUPERFICIE_TOTAL_COLUMN_NAME+", superftecninad as "+CONSTANTS.SUPERFICIE_TECNADEC_COLUMN_NAME+", superftot-superftecninad as "+CONSTANTS.SUPERFICIE_TECNINAD_COLUMN_NAME+
                                                                        " from (c4_3_ap_talas inner join municipios on c4_3_ap_talas.municipio=municipios.id) " +
                                                                        " inner join c4_3_talas on c4_3_ap_talas.id_talas=c4_3_talas.id " +
                                                                        "where c4_3_ap_talas.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c4_3_talas.nombre, superftot, superftecninad " +
                                                                        "order by municipios.nombre, anno, c4_3_talas.nombre, superftot, superftecninad";
//----------------------------------------------------------------------------------
 public static final String SQL_4_3_US                              = "select municipios.nombre as municipio, anno as Año , c4_3_talas.nombre as "+CONSTANTS.TALA_COLUMN_NAME +
                                                                        ", superftot as "+CONSTANTS.SUPERFICIE_TOTAL_COLUMN_NAME+", superftecninad as "+CONSTANTS.SUPERFICIE_TECNADEC_COLUMN_NAME+", superftot-superftecninad as "+CONSTANTS.SUPERFICIE_TECNINAD_COLUMN_NAME+
                                                                        " from (c4_3_us_talas inner join municipios on c4_3_us_talas.municipio=municipios.id) " +
                                                                        " inner join c4_3_talas on c4_3_us_talas.id_talas=c4_3_talas.id " +
                                                                        "where c4_3_us_talas.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c4_3_talas.nombre, superftot, superftecninad " +
                                                                        "order by municipios.nombre, anno, c4_3_talas.nombre, superftot, superftecninad";
//----------------------------------------------------------------------------------
public static final String SQL_4_3_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                         "c4_3_talas.nombre as "+CONSTANTS.TALA_COLUMN_NAME+", sum(superftot) as "+CONSTANTS.SUPERFICIE_TOTAL_COLUMN_NAME+", sum(superftecninad) as "+CONSTANTS.SUPERFICIE_TECNADEC_COLUMN_NAME+", sum(superftot-superftecninad) as "+CONSTANTS.SUPERFICIE_TECNINAD_COLUMN_NAME+
                                                                         "from (((c4_3_us_talas inner join usilvicola on c4_3_us_talas.id=usilvicola.id) " +
                                                                         "inner join efi on usilvicola.efi=efi.id)inner join municipios on c4_3_us_talas.municipio=municipios.id)" +
                                                                         "inner join c4_3_talas on c4_3_us_talas.id_talas=c4_3_talas.id " +
                                                                         "where efi.id=':id' and anno=:anno " +
                                                                         "group by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c4_3_talas.nombre " +
                                                                         "order by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c4_3_talas.nombre";

public static final String SQL_4_3_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(superftot) as superftot, sum(superftecninad) as superftecninad " +
                                                                           "from (c4_3_us_talas inner join usilvicola on c4_3_us_talas.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
   public static final String SQL_4_3_MUN_SubGrupoUS                      = "select provincias.nombre, municipios.nombre, c4_3_talas.nombre" +
                                                                              ", sum(superftot), sum(superftecninad) " +
                                                                              "from ((c4_3_us_talas inner join municipios on " +
                                                                              "c4_3_us_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_3_talas on c4_3_us_talas.id_talas=c4_3_talas.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c4_3_talas.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c4_3_talas.nombre;";

   public static final String SQL_4_3_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre, c4_3_talas.nombre" +
                                                                              ", sum(superftot), sum(superftecninad) " +
                                                                              "from ((c4_3_ap_talas inner join municipios on " +
                                                                              "c4_3_ap_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_3_talas on c4_3_ap_talas.id_talas=c4_3_talas.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c4_3_talas.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c4_3_talas.nombre;";

   public static final String SQL_4_3_MUN_SubGrupoOTROS                       = "select provincias.nombre, municipios.nombre, c4_3_talas.nombre" +
                                                                              ", sum(superftot), sum(superftecninad) " +
                                                                              "from ((c4_3_otros_talas inner join municipios on " +
                                                                              "c4_3_otros_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_3_talas on c4_3_otros_talas.id_talas=c4_3_talas.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c4_3_talas.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c4_3_talas.nombre;";
 //----------------------------------------------------------------------------------
   public static final String SQL_4_3_MUN_SubGrupoUS_Entities               = "select usilvicola.nombre, c4_3_talas.nombre, superftot, superftecninad " +
                                                                              "from (((c4_3_us_talas inner join municipios on " +
                                                                              "c4_3_us_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_3_talas on c4_3_us_talas.id_talas=c4_3_talas.id)" +
                                                                              "inner join usilvicola on c4_3_us_talas.id=usilvicola.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_4_3_MUN_SubGrupoAP_Entities               = "select area_protegida.nombre, c4_3_talas.nombre, superftot, superftecninad " +
                                                                              "from (((c4_3_ap_talas inner join municipios on " +
                                                                              "c4_3_ap_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_3_talas on c4_3_ap_talas.id_talas=c4_3_talas.id)" +
                                                                              "inner join area_protegida on c4_3_ap_talas.id=area_protegida.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_4_3_MUN_SubGrupoOTROS_Entities            = "select otros.nombre, c4_3_talas.nombre, superftot, superftecninad " +
                                                                              "from (((c4_3_otros_talas inner join municipios on " +
                                                                              "c4_3_otros_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_3_talas on c4_3_otros_talas.id_talas=c4_3_talas.id)" +
                                                                              "inner join otros on c4_3_otros_talas.id=otros.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";
//----------------------------------------------------------------------------------
public static final String SQL_4_3_PROV_SubGrupoUS                     = "select provincias.nombre, c4_3_talas.nombre " +
                                                                            ", sum(superftot), sum(superftecninad) " +
                                                                            "from ((c4_3_us_talas inner join municipios on " +
                                                                            "c4_3_us_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c4_3_talas on c4_3_us_talas.id_talas=c4_3_talas.id " +
                                                                            "where anno=:anno group by provincias.nombre, c4_3_talas.nombre " +
                                                                            "order by provincias.nombre, c4_3_talas.nombre";

public static final String SQL_4_3_PROV_SubGrupoAP                     = "select provincias.nombre, c4_3_talas.nombre " +
                                                                            ", sum(superftot), sum(superftecninad) " +
                                                                            "from ((c4_3_ap_talas inner join municipios on " +
                                                                            "c4_3_ap_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c4_3_talas on c4_3_ap_talas.id_talas=c4_3_talas.id " +
                                                                            "where anno=:anno group by provincias.nombre, c4_3_talas.nombre " +
                                                                            "order by provincias.nombre, c4_3_talas.nombre";

public static final String SQL_4_3_PROV_SubGrupoOTROS                     = "select provincias.nombre, c4_3_talas.nombre " +
                                                                            ", sum(superftot), sum(superftecninad) " +
                                                                            "from ((c4_3_otros_talas inner join municipios on " +
                                                                            "c4_3_otros_talas.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c4_3_talas on c4_3_otros_talas.id_talas=c4_3_talas.id " +
                                                                            "where anno=:anno group by provincias.nombre, c4_3_talas.nombre " +
                                                                            "order by provincias.nombre, c4_3_talas.nombre";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_4_3_AP                               = "select superftot as  "+CONSTANTS.SUPERFICIE_TOTAL_COLUMN_NAME+",superftecninad as  "+CONSTANTS.SUPERFICIE_TECNINAD_COLUMN_NAME+", anno " +
                                                                           "from c4_3_ap_talas "+
                                                                           "where c4_3_ap_talas.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_4_3_AP_YEARS                         = "select distinct anno from c4_3_ap_talas order by anno";
 //......................................................................................................................
   public static final String G_SQL_4_3_US                               = "select superftot as  "+CONSTANTS.SUPERFICIE_TOTAL_COLUMN_NAME+",superftecninad as  "+CONSTANTS.SUPERFICIE_TECNINAD_COLUMN_NAME+", anno " +
                                                                           "from c4_3_us_talas "+
                                                                           "where c4_3_us_talas.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_4_3_US_YEARS                         = "select distinct anno from c4_3_us_talas order by anno";

  //......................................................................................................................
   public static final String G_SQL_4_3_OTROS_YEARS                      = "select distinct anno from c4_3_otros_talas order by anno";

  //......................................................................................................................
   public static final String G_SQL_4_3_EFI                              = "select sum(superftot) as  "+CONSTANTS.SUPERFICIE_TOTAL_COLUMN_NAME+",sum(superftecninad) as  "+CONSTANTS.SUPERFICIE_TECNINAD_COLUMN_NAME+", anno " +
                                                                           "from c4_3_us_talas inner join usilvicola on c4_3_us_talas.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_4_3_EFI_YEARS                        = "select distinct anno from c4_3_us_talas order by anno";
       //......................................................................................................................
   public static final String G_SQL_4_3_MUN_AP                           = "select sum(superftot),sum(superftecninad), anno " +
                                                                           "from c4_3_ap_talas inner join municipios on c4_3_ap_talas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_3_MUN_US                           = "select sum(superftot),sum(superftecninad), anno " +
                                                                           "from c4_3_us_talas inner join municipios on c4_3_us_talas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_3_MUN_OTROS                        = "select sum(superftot),sum(superftecninad), anno " +
                                                                           "from c4_3_otros_talas inner join municipios on c4_3_otros_talas.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_3_MUN_YEARS_AP                    = "select distinct anno from c4_3_ap_talas where municipio=':id' order by anno";

   public static final String G_SQL_4_3_MUN_YEARS_US                    = "select distinct anno from c4_3_us_talas where municipio=':id' order by anno";

   public static final String G_SQL_4_3_MUN_YEARS_OTROS                 = "select distinct anno from c4_3_otros_talas where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_4_3_PROV_AP                          = "select sum(superftot),sum(superftecninad), anno " +
                                                                           "from c4_3_ap_talas inner join municipios on c4_3_ap_talas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_3_PROV_US                           = "select sum(superftot),sum(superftecninad), anno " +
                                                                           "from c4_3_us_talas inner join municipios on c4_3_us_talas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_3_PROV_OTROS                        = "select sum(superftot),sum(superftecninad), anno " +
                                                                           "from c4_3_otros_talas inner join municipios on c4_3_otros_talas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_3_PROV_YEARS_AP                    = "select distinct anno from c4_3_ap_talas inner join municipios on c4_3_ap_talas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_4_3_PROV_YEARS_US                    = "select distinct anno from c4_3_us_talas inner join municipios on c4_3_us_talas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_4_3_PROV_YEARS_OTROS                 = "select distinct anno from c4_3_otros_talas inner join municipios on c4_3_otros_talas.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
   //......................................................................................................................
   public static final String G_SQL_4_3_Temp_Table                       = "select superftot as  "+CONSTANTS.SUPERFICIE_TOTAL_COLUMN_NAME+",superftecninad as  "+CONSTANTS.SUPERFICIE_TECNINAD_COLUMN_NAME+", anno " +
                                                                           "from c_4_3_graphicdata order by anno";

//====================== Criterio 4_4 Productos Forestales No Maderables =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_4_4_AP                              = "select municipios.nombre as municipio, anno as Año , c4_4_prodfornomad.nombre as "+CONSTANTS.PROD_FOREST_NO_MAD_COLUMN_NAME +
                                                                        ", cantidad as "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+", potencial as "+CONSTANTS.POTENCIAL_COLUMN_NAME+", valor as "+CONSTANTS.VALOR_COLUMN_NAME+" " +
                                                                        " from (c4_4_ap_prodfornomad inner join municipios on c4_4_ap_prodfornomad.municipio=municipios.id) " +
                                                                        " inner join c4_4_prodfornomad on c4_4_ap_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                        "where c4_4_ap_prodfornomad.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c4_4_prodfornomad.nombre, cantidad, potencial, valor " +
                                                                        "order by municipios.nombre, anno, c4_4_prodfornomad.nombre, cantidad, potencial, valor";
//----------------------------------------------------------------------------------
 public static final String SQL_4_4_US                              = "select municipios.nombre as municipio, anno as Año , c4_4_prodfornomad.nombre as "+CONSTANTS.PROD_FOREST_NO_MAD_COLUMN_NAME +
                                                                        ", cantidad as "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+", potencial as "+CONSTANTS.POTENCIAL_COLUMN_NAME+", valor as "+CONSTANTS.VALOR_COLUMN_NAME+" " +
                                                                        " from (c4_4_us_prodfornomad inner join municipios on c4_4_us_prodfornomad.municipio=municipios.id) " +
                                                                        " inner join c4_4_prodfornomad on c4_4_us_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                        "where c4_4_us_prodfornomad.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c4_4_prodfornomad.nombre, cantidad, potencial, valor " +
                                                                        "order by municipios.nombre, anno, c4_4_prodfornomad.nombre, cantidad, potencial, valor";
//----------------------------------------------------------------------------------
public static final String SQL_4_4_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                         "c4_4_prodfornomad.nombre as "+CONSTANTS.PROD_FOREST_NO_MAD_COLUMN_NAME+", sum(cantidad) as "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+", " +
                                                                         "sum(potencial) as "+CONSTANTS.POTENCIAL_COLUMN_NAME+", sum(valor) as "+CONSTANTS.VALOR_COLUMN_NAME+" " +
                                                                         "from (((c4_4_us_prodfornomad inner join usilvicola on c4_4_us_prodfornomad.id=usilvicola.id) " +
                                                                         "inner join efi on usilvicola.efi=efi.id)inner join municipios on c4_4_us_prodfornomad.municipio=municipios.id)" +
                                                                         "inner join c4_4_prodfornomad on c4_4_us_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                         "where efi.id=':id' and anno=:anno " +
                                                                         "group by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c4_4_prodfornomad.nombre " +
                                                                         "order by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c4_4_prodfornomad.nombre";

public static final String SQL_4_4_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(cantidad) as cantidad, sum(potencial) as potencial, sum(valor) as valor " +
                                                                           "from (c4_4_us_prodfornomad inner join usilvicola on c4_4_us_prodfornomad.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
   public static final String SQL_4_4_MUN_SubGrupoUS                      = "select provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre" +
                                                                              ", sum(cantidad), sum(potencial), sum(valor) " +
                                                                              "from ((c4_4_us_prodfornomad inner join municipios on " +
                                                                              "c4_4_us_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_4_prodfornomad on c4_4_us_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre;";

   public static final String SQL_4_4_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre" +
                                                                              ", sum(cantidad), sum(potencial), sum(valor) " +
                                                                              "from ((c4_4_ap_prodfornomad inner join municipios on " +
                                                                              "c4_4_ap_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_4_prodfornomad on c4_4_ap_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre;";

   public static final String SQL_4_4_MUN_SubGrupoOTROS                       = "select provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre" +
                                                                              ", sum(cantidad), sum(potencial), sum(valor) " +
                                                                              "from ((c4_4_otros_prodfornomad inner join municipios on " +
                                                                              "c4_4_otros_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_4_prodfornomad on c4_4_otros_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c4_4_prodfornomad.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_4_4_MUN_SubGrupoUS_Entities               = "select usilvicola.nombre, c4_4_prodfornomad.nombre, cantidad, potencial, valor " +
                                                                              "from (((c4_4_us_prodfornomad inner join municipios on " +
                                                                              "c4_4_us_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_4_prodfornomad on c4_4_us_prodfornomad.id_pfnm=c4_4_prodfornomad.id)" +
                                                                              "inner join usilvicola on c4_4_us_prodfornomad.id=usilvicola.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_4_4_MUN_SubGrupoAP_Entities               = "select area_protegida.nombre, c4_4_prodfornomad.nombre, cantidad, potencial, valor " +
                                                                              "from (((c4_4_ap_prodfornomad inner join municipios on " +
                                                                              "c4_4_ap_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_4_prodfornomad on c4_4_ap_prodfornomad.id_pfnm=c4_4_prodfornomad.id)" +
                                                                              "inner join area_protegida on c4_4_ap_prodfornomad.id=area_protegida.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_4_4_MUN_SubGrupoOTROS_Entities            = "select otros.nombre, c4_4_prodfornomad.nombre, cantidad, potencial, valor " +
                                                                              "from (((c4_4_otros_prodfornomad inner join municipios on " +
                                                                              "c4_4_otros_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c4_4_prodfornomad on c4_4_otros_prodfornomad.id_pfnm=c4_4_prodfornomad.id)" +
                                                                              "inner join otros on c4_4_otros_prodfornomad.id=otros.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";
//----------------------------------------------------------------------------------
public static final String SQL_4_4_PROV_SubGrupoUS                     = "select provincias.nombre, c4_4_prodfornomad.nombre " +
                                                                            ", sum(cantidad), sum(potencial), sum(valor) " +
                                                                            "from ((c4_4_us_prodfornomad inner join municipios on " +
                                                                            "c4_4_us_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c4_4_prodfornomad on c4_4_us_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                            "where anno=:anno group by provincias.nombre, c4_4_prodfornomad.nombre " +
                                                                            "order by provincias.nombre, c4_4_prodfornomad.nombre";

public static final String SQL_4_4_PROV_SubGrupoAP                     = "select provincias.nombre, c4_4_prodfornomad.nombre " +
                                                                            ", sum(cantidad), sum(potencial), sum(valor) " +
                                                                            "from ((c4_4_ap_prodfornomad inner join municipios on " +
                                                                            "c4_4_ap_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c4_4_prodfornomad on c4_4_ap_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                            "where anno=:anno group by provincias.nombre, c4_4_prodfornomad.nombre " +
                                                                            "order by provincias.nombre, c4_4_prodfornomad.nombre";

public static final String SQL_4_4_PROV_SubGrupoOTROS                     = "select provincias.nombre, c4_4_prodfornomad.nombre " +
                                                                            ", sum(cantidad), sum(potencial), sum(valor) " +
                                                                            "from ((c4_4_otros_prodfornomad inner join municipios on " +
                                                                            "c4_4_otros_prodfornomad.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c4_4_prodfornomad on c4_4_otros_prodfornomad.id_pfnm=c4_4_prodfornomad.id " +
                                                                            "where anno=:anno group by provincias.nombre, c4_4_prodfornomad.nombre " +
                                                                            "order by provincias.nombre, c4_4_prodfornomad.nombre";

//.//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_4_4_AP                               = "select cantidad as  "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+",potencial as  "+CONSTANTS.POTENCIAL_COLUMN_NAME+",valor as  "+CONSTANTS.VALOR_COLUMN_NAME+", anno " +
                                                                           "from c4_4_ap_prodfornomad "+
                                                                           "where c4_4_ap_prodfornomad.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_4_4_AP_YEARS                         = "select distinct anno from c4_4_ap_prodfornomad order by anno";
 //......................................................................................................................
   public static final String G_SQL_4_4_US                               = "select cantidad as  "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+",potencial as  "+CONSTANTS.POTENCIAL_COLUMN_NAME+",valor as  "+CONSTANTS.VALOR_COLUMN_NAME+", anno " +
                                                                           "from c4_4_us_prodfornomad "+
                                                                           "where c4_4_us_prodfornomad.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_4_4_US_YEARS                         = "select distinct anno from c4_4_us_prodfornomad order by anno";

  //......................................................................................................................
   public static final String G_SQL_4_4_OTROS_YEARS                      = "select distinct anno from c4_4_otros_prodfornomad order by anno";

  //......................................................................................................................
   public static final String G_SQL_4_4_EFI                              = "select sum(cantidad) as  "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+",sum(potencial) as  "+CONSTANTS.POTENCIAL_COLUMN_NAME+",sum(valor) as  "+CONSTANTS.VALOR_COLUMN_NAME+", anno " +
                                                                           "from c4_4_us_prodfornomad inner join usilvicola on c4_4_us_prodfornomad.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_4_4_EFI_YEARS                        = "select distinct anno from c4_4_us_prodfornomad order by anno";
       //......................................................................................................................
   public static final String G_SQL_4_4_MUN_AP                           = "select sum(cantidad),sum(potencial),sum(valor), anno " +
                                                                           "from c4_4_ap_prodfornomad inner join municipios on c4_4_ap_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_4_MUN_US                           = "select sum(cantidad),sum(potencial),sum(valor), anno " +
                                                                           "from c4_4_us_prodfornomad inner join municipios on c4_4_us_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_4_MUN_OTROS                        = "select sum(cantidad),sum(potencial),sum(valor), anno " +
                                                                           "from c4_4_otros_prodfornomad inner join municipios on c4_4_otros_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_4_MUN_YEARS_AP                    = "select distinct anno from c4_4_ap_prodfornomad where municipio=':id' order by anno";

   public static final String G_SQL_4_4_MUN_YEARS_US                    = "select distinct anno from c4_4_us_prodfornomad where municipio=':id' order by anno";

   public static final String G_SQL_4_4_MUN_YEARS_OTROS                 = "select distinct anno from c4_4_otros_prodfornomad where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_4_4_PROV_AP                          = "select sum(cantidad),sum(potencial),sum(valor), anno " +
                                                                           "from c4_4_ap_prodfornomad inner join municipios on c4_4_ap_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_4_PROV_US                           = "select sum(cantidad),sum(potencial),sum(valor), anno " +
                                                                           "from c4_4_us_prodfornomad inner join municipios on c4_4_us_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_4_PROV_OTROS                        = "select sum(cantidad),sum(potencial),sum(valor), anno " +
                                                                           "from c4_4_otros_prodfornomad inner join municipios on c4_4_otros_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_4_PROV_YEARS_AP                    = "select distinct anno from c4_4_ap_prodfornomad inner join municipios on c4_4_ap_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_4_4_PROV_YEARS_US                    = "select distinct anno from c4_4_us_prodfornomad inner join municipios on c4_4_us_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_4_4_PROV_YEARS_OTROS                 = "select distinct anno from c4_4_otros_prodfornomad inner join municipios on c4_4_otros_prodfornomad.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_4_4_Temp_Table                       = "select cantidad as  "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+",potencial as  "+CONSTANTS.POTENCIAL_COLUMN_NAME+",valor as  "+CONSTANTS.VALOR_COLUMN_NAME+", anno " +
                                                                           "from c_4_4_graphicdata order by anno";

//====================== Criterio 4_5 Sistema agrosilvopastoril =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_4_5_AP                                   = "select municipios.nombre as municipio, anno as Año, superf_pot_agrosilvp as "+CONSTANTS.SUP_POT_AGROSILVOPASTORIL_COLUMN_NAME+", " +
                                                                           "superf_man_agrosilvp as "+CONSTANTS.SUP_MAN_AGROSILVOPASTORIL_COLUMN_NAME+", " +
                                                                           "case when superf_pot_agrosilvp<>0 then (superf_man_agrosilvp/superf_pot_agrosilvp)*100 else 0 end as "+CONSTANTS.PORC_SM_SP_COLUMN_NAME+", " +
                                                                           "cant_fincaforst as "+CONSTANTS.CANT_FINCA_FORESTAL_COLUMN_NAME+", superf_pot_fincaforst as "+CONSTANTS.SUP_POT_FINCA_FORESTAL_COLUMN_NAME+", " +
                                                                           "superf_fincaforst as "+CONSTANTS.SUP_FINCA_FORESTAL_COLUMN_NAME+", " +
                                                                           "case when (superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (superf_fincaforst/(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end " +
                                                                           "as "+CONSTANTS.PORC_FINCA_SUP_FORESTAL_COLUMN_NAME+", area_bn_fincaforst as "+CONSTANTS.AREA_BN_FINCA_FORESTAL_COLUMN_NAME+" "+
                                                                           "from c4_5_ap_sistagrosilvopast inner join municipios on c4_5_ap_sistagrosilvopast.municipio=municipios.id " +
                                                                           "where c4_5_ap_sistagrosilvopast.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_4_5_US                                   = "select municipios.nombre as municipio, anno as Año, superf_pot_agrosilvp as "+CONSTANTS.SUP_POT_AGROSILVOPASTORIL_COLUMN_NAME+", " +
                                                                           "superf_man_agrosilvp as "+CONSTANTS.SUP_MAN_AGROSILVOPASTORIL_COLUMN_NAME+", " +
                                                                           "case when superf_pot_agrosilvp<>0 then (superf_man_agrosilvp/superf_pot_agrosilvp)*100 else 0 end as "+CONSTANTS.PORC_SM_SP_COLUMN_NAME+", " +
                                                                           "cant_fincaforst as "+CONSTANTS.CANT_FINCA_FORESTAL_COLUMN_NAME+", superf_pot_fincaforst as "+CONSTANTS.SUP_POT_FINCA_FORESTAL_COLUMN_NAME+", " +
                                                                           "superf_fincaforst as "+CONSTANTS.SUP_FINCA_FORESTAL_COLUMN_NAME+", " +
                                                                           "case when (superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (superf_fincaforst/(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end " +
                                                                           "as "+CONSTANTS.PORC_FINCA_SUP_FORESTAL_COLUMN_NAME+", area_bn_fincaforst as "+CONSTANTS.AREA_BN_FINCA_FORESTAL_COLUMN_NAME+" "+
                                                                           "from c4_5_us_sistagrosilvopast inner join municipios on c4_5_us_sistagrosilvopast.municipio=municipios.id " +
                                                                           "where c4_5_us_sistagrosilvopast.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_4_5_EFI_Subgrupo                          = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                           ", sum(superf_pot_agrosilvp) as "+CONSTANTS.SUP_POT_AGROSILVOPASTORIL_COLUMN_NAME+", " +
                                                                           "sum(superf_man_agrosilvp) as "+CONSTANTS.SUP_MAN_AGROSILVOPASTORIL_COLUMN_NAME+", " +
                                                                           "case when sum(superf_pot_agrosilvp)<>0 then (sum(superf_man_agrosilvp)/sum(superf_pot_agrosilvp))*100 else 0 end as "+CONSTANTS.PORC_SM_SP_COLUMN_NAME+", " +
                                                                           "sum(cant_fincaforst) as "+CONSTANTS.CANT_FINCA_FORESTAL_COLUMN_NAME+", sum(superf_pot_fincaforst) as "+CONSTANTS.SUP_POT_FINCA_FORESTAL_COLUMN_NAME+", " +
                                                                           "sum(superf_fincaforst) as "+CONSTANTS.SUP_FINCA_FORESTAL_COLUMN_NAME+", " +
                                                                           "case when sum(superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (sum(superf_fincaforst)/sum(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end " +
                                                                           "as "+CONSTANTS.PORC_FINCA_SUP_FORESTAL_COLUMN_NAME+", sum(area_bn_fincaforst) as "+CONSTANTS.AREA_BN_FINCA_FORESTAL_COLUMN_NAME+" "+
                                                                           "from ((c4_5_us_sistagrosilvopast inner join usilvicola on c4_5_us_sistagrosilvopast.id=usilvicola.id) " +
                                                                           "inner join efi on usilvicola.efi=efi.id)inner join municipios on c4_5_us_sistagrosilvopast.municipio=municipios.id " +
                                                                           "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_4_5_EFI_TOTAL                            = "select efi.nombre as efi, sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), " +
                                                                           "case when sum(superf_pot_agrosilvp)<>0 then (sum(superf_man_agrosilvp)/sum(superf_pot_agrosilvp))*100 else 0 end, " +
                                                                           "sum(cant_fincaforst), sum(superf_pot_fincaforst), sum(superf_fincaforst), " +
                                                                           "case when sum(superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (sum(superf_fincaforst)/sum(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end, sum(area_bn_fincaforst) " +
                                                                           "from (c4_5_us_sistagrosilvopast inner join usilvicola on c4_5_us_sistagrosilvopast.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_4_5_MUN_SubGrupoUS                        = "select municipios.nombre, sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), " +
                                                                           "case when sum(superf_pot_agrosilvp)<>0 then (sum(superf_man_agrosilvp)/sum(superf_pot_agrosilvp))*100 else 0 end, " +
                                                                           "sum(cant_fincaforst), sum(superf_pot_fincaforst), sum(superf_fincaforst), " +
                                                                           "case when sum(superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (sum(superf_fincaforst)/sum(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end, sum(area_bn_fincaforst) " +
                                                                           "from (c4_5_us_sistagrosilvopast inner join municipios on " +
                                                                           "c4_5_us_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_4_5_MUN_SubGrupoAP                        = "select municipios.nombre, sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), " +
                                                                           "case when sum(superf_pot_agrosilvp)<>0 then (sum(superf_man_agrosilvp)/sum(superf_pot_agrosilvp))*100 else 0 end, " +
                                                                           "sum(cant_fincaforst), sum(superf_pot_fincaforst), sum(superf_fincaforst), " +
                                                                           "case when sum(superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (sum(superf_fincaforst)/sum(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end, sum(area_bn_fincaforst) " +
                                                                           "from (c4_5_ap_sistagrosilvopast inner join municipios on " +
                                                                           "c4_5_ap_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_4_5_MUN_SubGrupoOTROS                     = "select municipios.nombre, sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), " +
                                                                           "case when sum(superf_pot_agrosilvp)<>0 then (sum(superf_man_agrosilvp)/sum(superf_pot_agrosilvp))*100 else 0 end, " +
                                                                           "sum(cant_fincaforst), sum(superf_pot_fincaforst), sum(superf_fincaforst), " +
                                                                           "case when sum(superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (sum(superf_fincaforst)/sum(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end, sum(area_bn_fincaforst) " +
                                                                           "from (c4_5_otros_sistagrosilvopast inner join municipios on " +
                                                                           "c4_5_otros_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_4_5_MUN_US_SubValues                     = "select sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                             "sum(superf_fincaforst), sum(area_bn_fincaforst), municipios.nombre " +
                                                                             "from ((c4_5_us_sistagrosilvopast inner join municipios on " +
                                                                             "c4_5_us_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c4_5_us_sistagrosilvopast.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_4_5_MUN_AP_SubValues                     = "select sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                             "sum(superf_fincaforst), sum(area_bn_fincaforst), municipios.nombre " +
                                                                             "from ((c4_5_ap_sistagrosilvopast inner join municipios on " +
                                                                             "c4_5_ap_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c4_5_ap_sistagrosilvopast.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_4_5_MUN_OTROS_SubValues                  = "select sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                             "sum(superf_fincaforst), sum(area_bn_fincaforst), municipios.nombre " +
                                                                             "from ((c4_5_otros_sistagrosilvopast inner join municipios on " +
                                                                             "c4_5_otros_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c4_5_otros_sistagrosilvopast.anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
 public static final String SQL_4_5_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                             "sum(superf_fincaforst), sum(area_bn_fincaforst) " +
                                                                             "from ((c4_5_us_sistagrosilvopast inner join municipios on " +
                                                                             "c4_5_us_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c4_5_us_sistagrosilvopast.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_4_5_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                             "sum(superf_fincaforst), sum(area_bn_fincaforst) " +
                                                                             "from ((c4_5_ap_sistagrosilvopast inner join municipios on " +
                                                                             "c4_5_ap_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c4_5_ap_sistagrosilvopast.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_4_5_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                             "sum(superf_fincaforst), sum(area_bn_fincaforst) " +
                                                                             "from ((c4_5_otros_sistagrosilvopast inner join municipios on " +
                                                                             "c4_5_otros_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c4_5_otros_sistagrosilvopast.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_4_5_PROV_SubGrupoUS                       = "select sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                           "sum(superf_fincaforst), sum(area_bn_fincaforst), provincias.nombre " +
                                                                           "from (c4_5_us_sistagrosilvopast inner join municipios on " +
                                                                           "c4_5_us_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_4_5_PROV_SubGrupoAP                       = "select sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                           "sum(superf_fincaforst), sum(area_bn_fincaforst), provincias.nombre " +
                                                                           "from(c4_5_ap_sistagrosilvopast inner join municipios on " +
                                                                           "c4_5_ap_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_4_5_PROV_SubGrupoOTROS                    = "select sum(superf_pot_agrosilvp), sum(superf_man_agrosilvp), sum(cant_fincaforst), sum(superf_pot_fincaforst), " +
                                                                           "sum(superf_fincaforst), sum(area_bn_fincaforst), provincias.nombre " +
                                                                           "from (c4_5_otros_sistagrosilvopast inner join municipios on " +
                                                                           "c4_5_otros_sistagrosilvopast.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_4_5_AP                               = "select superf_pot_agrosilvp as  "+CONSTANTS.SUP_POT_AGROSILVOPASTORIL_COLUMN_NAME+",superf_man_agrosilvp as  "+CONSTANTS.SUP_MAN_AGROSILVOPASTORIL_COLUMN_NAME+",case when superf_pot_agrosilvp<>0 then (superf_man_agrosilvp/superf_pot_agrosilvp)*100 else 0 end as  "+CONSTANTS.PORC_SM_SP_COLUMN_NAME+",cant_fincaforst as  "+CONSTANTS.CANT_FINCA_FORESTAL_COLUMN_NAME+",superf_pot_fincaforst as  "+CONSTANTS.SUP_POT_FINCA_FORESTAL_COLUMN_NAME+",superf_fincaforst as  "+CONSTANTS.SUP_FINCA_FORESTAL_COLUMN_NAME+",case when (superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (superf_fincaforst/(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end as  "+CONSTANTS.PORC_FINCA_SUP_FORESTAL_COLUMN_NAME+",area_bn_fincaforst as  "+CONSTANTS.AREA_BN_FINCA_FORESTAL_COLUMN_NAME+", anno " +
                                                                           "from c4_5_ap_sistagrosilvopast "+
                                                                           "where c4_5_ap_sistagrosilvopast.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_4_5_AP_YEARS                         = "select distinct anno from c4_5_ap_sistagrosilvopast order by anno";
 //......................................................................................................................
   public static final String G_SQL_4_5_US                               = "select superf_pot_agrosilvp as  "+CONSTANTS.SUP_POT_AGROSILVOPASTORIL_COLUMN_NAME+",superf_man_agrosilvp as  "+CONSTANTS.SUP_MAN_AGROSILVOPASTORIL_COLUMN_NAME+",case when superf_pot_agrosilvp<>0 then (superf_man_agrosilvp/superf_pot_agrosilvp)*100 else 0 end as  "+CONSTANTS.PORC_SM_SP_COLUMN_NAME+",cant_fincaforst as  "+CONSTANTS.CANT_FINCA_FORESTAL_COLUMN_NAME+",superf_pot_fincaforst as  "+CONSTANTS.SUP_POT_FINCA_FORESTAL_COLUMN_NAME+",superf_fincaforst as  "+CONSTANTS.SUP_FINCA_FORESTAL_COLUMN_NAME+",case when (superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (superf_fincaforst/(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end as  "+CONSTANTS.PORC_FINCA_SUP_FORESTAL_COLUMN_NAME+",area_bn_fincaforst as  "+CONSTANTS.AREA_BN_FINCA_FORESTAL_COLUMN_NAME+", anno " +
                                                                           "from c4_5_us_sistagrosilvopast "+
                                                                           "where c4_5_us_sistagrosilvopast.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_4_5_US_YEARS                         = "select distinct anno from c4_5_us_sistagrosilvopast order by anno";

  //......................................................................................................................
   public static final String G_SQL_4_5_OTROS_YEARS                      = "select distinct anno from c4_5_otros_sistagrosilvopast order by anno";

  //......................................................................................................................
   public static final String G_SQL_4_5_EFI                              = "select sum(superf_pot_agrosilvp) as  "+CONSTANTS.SUP_POT_AGROSILVOPASTORIL_COLUMN_NAME+",sum(superf_man_agrosilvp) as  "+CONSTANTS.SUP_MAN_AGROSILVOPASTORIL_COLUMN_NAME+",case when sum(superf_pot_agrosilvp)<>0 then (sum(superf_man_agrosilvp)/sum(superf_pot_agrosilvp))*100 else 0 end as  "+CONSTANTS.PORC_SM_SP_COLUMN_NAME+",sum(cant_fincaforst) as  "+CONSTANTS.CANT_FINCA_FORESTAL_COLUMN_NAME+",sum(superf_pot_fincaforst) as  "+CONSTANTS.SUP_POT_FINCA_FORESTAL_COLUMN_NAME+",sum(superf_fincaforst) as  "+CONSTANTS.SUP_FINCA_FORESTAL_COLUMN_NAME+",case when sum(superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (sum(superf_fincaforst)/sum(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end as  "+CONSTANTS.PORC_FINCA_SUP_FORESTAL_COLUMN_NAME+",sum(area_bn_fincaforst) as  "+CONSTANTS.AREA_BN_FINCA_FORESTAL_COLUMN_NAME+", anno " +
                                                                           "from c4_5_us_sistagrosilvopast inner join usilvicola on c4_5_us_sistagrosilvopast.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_4_5_EFI_YEARS                        = "select distinct anno from c4_5_us_sistagrosilvopast order by anno";
       //......................................................................................................................
   public static final String G_SQL_4_5_MUN_AP                           = "select sum(cant_fincaforst),sum(superf_fincaforst),sum(superf_pot_agrosilvp),sum(superf_man_agrosilvp),sum(superf_pot_fincaforst),sum(area_bn_fincaforst), anno " +
                                                                           "from c4_5_ap_sistagrosilvopast inner join municipios on c4_5_ap_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_5_MUN_US                           = "select sum(cant_fincaforst),sum(superf_fincaforst),sum(superf_pot_agrosilvp),sum(superf_man_agrosilvp),sum(superf_pot_fincaforst),sum(area_bn_fincaforst), anno " +
                                                                           "from c4_5_us_sistagrosilvopast inner join municipios on c4_5_us_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_5_MUN_OTROS                        = "select sum(cant_fincaforst),sum(superf_fincaforst),sum(superf_pot_agrosilvp),sum(superf_man_agrosilvp),sum(superf_pot_fincaforst),sum(area_bn_fincaforst), anno " +
                                                                           "from c4_5_otros_sistagrosilvopast inner join municipios on c4_5_otros_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_4_5_MUN_YEARS_AP                    = "select distinct anno from c4_5_ap_sistagrosilvopast where municipio=':id' order by anno";

   public static final String G_SQL_4_5_MUN_YEARS_US                    = "select distinct anno from c4_5_us_sistagrosilvopast where municipio=':id' order by anno";

   public static final String G_SQL_4_5_MUN_YEARS_OTROS                 = "select distinct anno from c4_5_otros_sistagrosilvopast where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_4_5_PROV_AP                          = "select sum(cant_fincaforst),sum(superf_fincaforst),sum(superf_pot_agrosilvp),sum(superf_man_agrosilvp),sum(superf_pot_fincaforst),sum(area_bn_fincaforst), anno " +
                                                                           "from c4_5_ap_sistagrosilvopast inner join municipios on c4_5_ap_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_5_PROV_US                           = "select sum(cant_fincaforst),sum(superf_fincaforst),sum(superf_pot_agrosilvp),sum(superf_man_agrosilvp),sum(superf_pot_fincaforst),sum(area_bn_fincaforst), anno " +
                                                                           "from c4_5_us_sistagrosilvopast inner join municipios on c4_5_us_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_5_PROV_OTROS                        = "select sum(cant_fincaforst),sum(superf_fincaforst),sum(superf_pot_agrosilvp),sum(superf_man_agrosilvp),sum(superf_pot_fincaforst),sum(area_bn_fincaforst), anno " +
                                                                           "from c4_5_otros_sistagrosilvopast inner join municipios on c4_5_otros_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_4_5_PROV_YEARS_AP                    = "select distinct anno from c4_5_ap_sistagrosilvopast inner join municipios on c4_5_ap_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_4_5_PROV_YEARS_US                    = "select distinct anno from c4_5_us_sistagrosilvopast inner join municipios on c4_5_us_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_4_5_PROV_YEARS_OTROS                 = "select distinct anno from c4_5_otros_sistagrosilvopast inner join municipios on c4_5_otros_sistagrosilvopast.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_4_5_Temp_Table                       = "select superf_pot_agrosilvp as  "+CONSTANTS.SUP_POT_AGROSILVOPASTORIL_COLUMN_NAME+",superf_man_agrosilvp as  "+CONSTANTS.SUP_MAN_AGROSILVOPASTORIL_COLUMN_NAME+",case when superf_pot_agrosilvp<>0 then (superf_man_agrosilvp/superf_pot_agrosilvp)*100 else 0 end as  "+CONSTANTS.PORC_SM_SP_COLUMN_NAME+",cant_fincaforst as  "+CONSTANTS.CANT_FINCA_FORESTAL_COLUMN_NAME+",superf_pot_fincaforst as  "+CONSTANTS.SUP_POT_FINCA_FORESTAL_COLUMN_NAME+",superf_fincaforst as  "+CONSTANTS.SUP_FINCA_FORESTAL_COLUMN_NAME+",case when (superf_pot_agrosilvp+superf_pot_fincaforst)<>0 then (superf_fincaforst/(superf_pot_agrosilvp+superf_pot_fincaforst))*100 else 0 end as  "+CONSTANTS.PORC_FINCA_SUP_FORESTAL_COLUMN_NAME+",area_bn_fincaforst as  "+CONSTANTS.AREA_BN_FINCA_FORESTAL_COLUMN_NAME+", anno " +
                                                                           "from c_4_5_graphicdata order by anno";

//====================== Criterio 5_1 Salario =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_5_1_AP                                  = "select municipios.nombre as municipio, anno as Año, case when prom_trabj<>0 then sal_devg/(prom_trabj*12) else 0 end " +
                                                                          "as "+CONSTANTS.SALARIO_MED_MENSUAL_COLUMN_NAME+" "+
                                                                          "from c5_1_ap_salario inner join municipios on c5_1_ap_salario.municipio=municipios.id " +
                                                                          "where c5_1_ap_salario.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_5_1_US                                   = "select municipios.nombre as municipio, anno as Año, case when prom_trabj<>0 then sal_devg/(prom_trabj*12) else 0 end " +
                                                                          "as "+CONSTANTS.SALARIO_MED_MENSUAL_COLUMN_NAME+" "+
                                                                          " from c5_1_us_salario inner join municipios on c5_1_us_salario.municipio=municipios.id " +
                                                                          "where c5_1_us_salario.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_5_1_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                          ", case when sum(prom_trabj)<>0 then sum(sal_devg)/sum(prom_trabj*12) else 0 end " +
                                                                          "as "+CONSTANTS.SALARIO_MED_MENSUAL_COLUMN_NAME+" "+
                                                                          "from ((c5_1_us_salario inner join usilvicola on c5_1_us_salario.id=usilvicola.id) " +
                                                                          "inner join efi on usilvicola.efi=efi.id)inner join municipios on c5_1_us_salario.municipio=municipios.id " +
                                                                          "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_5_1_EFI_TOTAL                            = "select efi.nombre, case when sum(prom_trabj)<>0 then sum(sal_devg)/sum(prom_trabj*12) else 0 end " +
                                                                           "from (c5_1_us_salario inner join usilvicola on c5_1_us_salario.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_5_1_MUN_SubGrupoUS                        = "select municipios.nombre, case when sum(prom_trabj)<>0 then sum(sal_devg)/sum(prom_trabj*12) else 0 end " +
                                                                           "from (c5_1_us_salario inner join municipios on " +
                                                                           "c5_1_us_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_5_1_MUN_SubGrupoAP                        = "select municipios.nombre, case when sum(prom_trabj)<>0 then sum(sal_devg)/sum(prom_trabj*12) else 0 end " +
                                                                           "from (c5_1_ap_salario inner join municipios on " +
                                                                           "c5_1_ap_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";

public static final String SQL_5_1_MUN_SubGrupoOTROS                     = "select municipios.nombre, case when sum(prom_trabj)<>0 then sum(sal_devg)/sum(prom_trabj*12) else 0 end " +
                                                                           "from (c5_1_otros_salario inner join municipios on " +
                                                                           "c5_1_otros_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_5_1_MUN_US_SubValues                     = "select sum(sal_devg), sum(prom_trabj), municipios.nombre " +
                                                                             "from ((c5_1_us_salario inner join municipios on " +
                                                                             "c5_1_us_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c5_1_us_salario.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_5_1_MUN_AP_SubValues                     = "select sum(sal_devg), sum(prom_trabj), municipios.nombre " +
                                                                             "from ((c5_1_ap_salario inner join municipios on " +
                                                                             "c5_1_ap_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c5_1_ap_salario.anno=:anno group by municipios.id, municipios.nombre;";

   public static final String SQL_5_1_MUN_OTROS_SubValues                  = "select sum(sal_devg), sum(prom_trabj), municipios.nombre " +
                                                                             "from ((c5_1_otros_salario inner join municipios on " +
                                                                             "c5_1_otros_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                             "where municipios.id=':id' and c5_1_otros_salario.anno=:anno group by municipios.id, municipios.nombre;";
//----------------------------------------------------------------------------------
 public static final String SQL_5_1_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, sum(sal_devg), sum(prom_trabj)" +
                                                                             "from ((c5_1_us_salario inner join municipios on " +
                                                                             "c5_1_us_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c5_1_us_salario.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by usilvicola.id, usilvicola.nombre;";

 public static final String SQL_5_1_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, sum(sal_devg), sum(prom_trabj)" +
                                                                             "from ((c5_1_ap_salario inner join municipios on " +
                                                                             "c5_1_ap_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c5_1_ap_salario.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by area_protegida.id, area_protegida.nombre;";

 public static final String SQL_5_1_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, sum(sal_devg), sum(prom_trabj) " +
                                                                             "from ((c5_1_otros_salario inner join municipios on " +
                                                                             "c5_1_otros_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c5_1_otros_salario.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno group by otros.id, otros.nombre;";
//----------------------------------------------------------------------------------
public static final String SQL_5_1_PROV_SubGrupoUS                       = "select sum(sal_devg), sum(prom_trabj), provincias.nombre " +
                                                                           "from (c5_1_us_salario inner join municipios on " +
                                                                           "c5_1_us_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_5_1_PROV_SubGrupoAP                       = "select sum(sal_devg), sum(prom_trabj), provincias.nombre " +
                                                                           "from(c5_1_ap_salario inner join municipios on " +
                                                                           "c5_1_ap_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

public static final String SQL_5_1_PROV_SubGrupoOTROS                    = "select sum(sal_devg), sum(prom_trabj), provincias.nombre " +
                                                                           "from (c5_1_otros_salario inner join municipios on " +
                                                                           "c5_1_otros_salario.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.id, provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_5_1_AP                               = "select case when prom_trabj<>0 then sal_devg/(prom_trabj*12) else 0 end as  "+CONSTANTS.SALARIO_MED_MENSUAL_COLUMN_NAME+", anno " +
                                                                           "from c5_1_ap_salario "+
                                                                           "where c5_1_ap_salario.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_5_1_AP_YEARS                         = "select distinct anno from c5_1_ap_salario order by anno";
 //......................................................................................................................
   public static final String G_SQL_5_1_US                               = "select case when prom_trabj<>0 then sal_devg/(prom_trabj*12) else 0 end as  "+CONSTANTS.SALARIO_MED_MENSUAL_COLUMN_NAME+", anno " +
                                                                           "from c5_1_us_salario "+
                                                                           "where c5_1_us_salario.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_5_1_US_YEARS                         = "select distinct anno from c5_1_us_salario order by anno";

  //......................................................................................................................
   public static final String G_SQL_5_1_OTROS_YEARS                      = "select distinct anno from c5_1_otros_salario order by anno";

  //......................................................................................................................
   public static final String G_SQL_5_1_EFI                              = "select case when sum(prom_trabj)<>0 then sum(sal_devg)/(sum(prom_trabj)*12) else 0 end as  "+CONSTANTS.SALARIO_MED_MENSUAL_COLUMN_NAME+", anno " +
                                                                           "from c5_1_us_salario inner join usilvicola on c5_1_us_salario.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_5_1_EFI_YEARS                        = "select distinct anno from c5_1_us_salario order by anno";
       //......................................................................................................................
   public static final String G_SQL_5_1_MUN_AP                           = "select sum(sal_devg),sum(prom_trabj), anno " +
                                                                           "from c5_1_ap_salario inner join municipios on c5_1_ap_salario.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_1_MUN_US                           = "select sum(sal_devg),sum(prom_trabj), anno " +
                                                                           "from c5_1_us_salario inner join municipios on c5_1_us_salario.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_1_MUN_OTROS                        = "select sum(sal_devg),sum(prom_trabj), anno " +
                                                                           "from c5_1_otros_salario inner join municipios on c5_1_otros_salario.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_1_MUN_YEARS_AP                    = "select distinct anno from c5_1_ap_salario where municipio=':id' order by anno";

   public static final String G_SQL_5_1_MUN_YEARS_US                    = "select distinct anno from c5_1_us_salario where municipio=':id' order by anno";

   public static final String G_SQL_5_1_MUN_YEARS_OTROS                 = "select distinct anno from c5_1_otros_salario where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_5_1_PROV_AP                          = "select sum(sal_devg),sum(prom_trabj), anno " +
                                                                           "from c5_1_ap_salario inner join municipios on c5_1_ap_salario.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_1_PROV_US                           = "select sum(sal_devg),sum(prom_trabj), anno " +
                                                                           "from c5_1_us_salario inner join municipios on c5_1_us_salario.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_1_PROV_OTROS                        = "select sum(sal_devg),sum(prom_trabj), anno " +
                                                                           "from c5_1_otros_salario inner join municipios on c5_1_otros_salario.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_1_PROV_YEARS_AP                    = "select distinct anno from c5_1_ap_salario inner join municipios on c5_1_ap_salario.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_5_1_PROV_YEARS_US                    = "select distinct anno from c5_1_us_salario inner join municipios on c5_1_us_salario.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_5_1_PROV_YEARS_OTROS                 = "select distinct anno from c5_1_otros_salario inner join municipios on c5_1_otros_salario.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_5_1_Temp_Table                       = "select case when prom_trabj<>0 then sal_devg/(prom_trabj*12) else 0 end as  "+CONSTANTS.SALARIO_MED_MENSUAL_COLUMN_NAME+", anno " +
                                                                           "from c_5_1_graphicdata order by anno";

//====================== Criterio 5_2 Accidentes =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_5_2_AP                                   = "select municipios.nombre as municipio, anno as Año, accidentes_par as "+CONSTANTS.ACCIDENTES_PAR_COLUMN_NAME+", " +
                                                                           "accidentes_mort as "+CONSTANTS.ACCIDENTES_MORTALES_COLUMN_NAME+
                                                                           " from c5_2_ap_accidentes inner join municipios on c5_2_ap_accidentes.municipio=municipios.id " +
                                                                           "where c5_2_ap_accidentes.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_5_2_US                                   = "select municipios.nombre as municipio, anno as Año, accidentes_par as "+CONSTANTS.ACCIDENTES_PAR_COLUMN_NAME+", " +
                                                                           "accidentes_mort as "+CONSTANTS.ACCIDENTES_MORTALES_COLUMN_NAME+
                                                                          " from c5_2_us_accidentes inner join municipios on c5_2_us_accidentes.municipio=municipios.id " +
                                                                          "where c5_2_us_accidentes.id=':id' and anno=:anno";
//----------------------------------------------------------------------------------
public static final String SQL_5_2_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+" " +
                                                                          ", sum(accidentes_par) as "+CONSTANTS.ACCIDENTES_PAR_COLUMN_NAME+", sum(accidentes_mort) as "+CONSTANTS.ACCIDENTES_MORTALES_COLUMN_NAME+
                                                                           " from ((c5_2_us_accidentes inner join usilvicola on c5_2_us_accidentes.id=usilvicola.id) " +
                                                                           "inner join efi on usilvicola.efi=efi.id)inner join municipios on c5_2_us_accidentes.municipio=municipios.id " +
                                                                           "where efi.id=':id' and anno=:anno group by municipios.nombre, anno, efi.nombre, usilvicola.nombre";

public static final String SQL_5_2_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(accidentes_par) as accidentes_par, sum(accidentes_mort) as accidentes_mort " +
                                                                           "from (c5_2_us_accidentes inner join usilvicola on c5_2_us_accidentes.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
public static final String SQL_5_2_MUN_SubGrupoUS                       = "select provincias.nombre, municipios.nombre as municipio" +
                                                                          ", sum(accidentes_par) as accidentes_par, sum(accidentes_mort) as accidentes_mort " +
                                                                           "from (c5_2_us_accidentes inner join municipios on " +
                                                                           "c5_2_us_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre;";

public static final String SQL_5_2_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre as municipio " +
                                                                          ", sum(accidentes_par), sum(accidentes_mort) " +
                                                                           "from (c5_2_ap_accidentes inner join municipios on " +
                                                                           "c5_2_ap_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre;";

public static final String SQL_5_2_MUN_SubGrupoOTROS                    = "select provincias.nombre, municipios.nombre as municipio " +
                                                                          ", sum(accidentes_par), sum(accidentes_mort) " +
                                                                           "from (c5_2_otros_accidentes inner join municipios on " +
                                                                           "c5_2_otros_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where municipios.id=':id' and anno=:anno group by provincias.nombre, municipios.nombre;";
//----------------------------------------------------------------------------------
 public static final String SQL_5_2_MUN_SubGrupoUS_Entities                = "select usilvicola.nombre, accidentes_par, accidentes_mort " +
                                                                             "from ((c5_2_us_accidentes inner join municipios on " +
                                                                             "c5_2_us_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join usilvicola on c5_2_us_accidentes.id=usilvicola.id " +
                                                                             "where municipios.id=':id' and anno=:anno;";

 public static final String SQL_5_2_MUN_SubGrupoAP_Entities                = "select area_protegida.nombre, accidentes_par, accidentes_mort " +
                                                                             "from ((c5_2_ap_accidentes inner join municipios on " +
                                                                             "c5_2_ap_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join area_protegida on c5_2_ap_accidentes.id=area_protegida.id " +
                                                                             "where municipios.id=':id' and anno=:anno;";

 public static final String SQL_5_2_MUN_SubGrupoOTROS_Entities             = "select otros.nombre, accidentes_par, accidentes_mort " +
                                                                             "from ((c5_2_otros_accidentes inner join municipios on " +
                                                                             "c5_2_otros_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                             "inner join otros on c5_2_otros_accidentes.id=otros.id " +
                                                                             "where municipios.id=':id' and anno=:anno;";
//----------------------------------------------------------------------------------
public static final String SQL_5_2_PROV_SubGrupoUS                      = "select provincias.nombre " +
                                                                          ", sum(accidentes_par), sum(accidentes_mort) " +
                                                                           "from (c5_2_us_accidentes inner join municipios on " +
                                                                           "c5_2_us_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.nombre;";

public static final String SQL_5_2_PROV_SubGrupoAP                      = "select provincias.nombre" +
                                                                          ", sum(accidentes_par), sum(accidentes_mort) " +
                                                                           "from(c5_2_ap_accidentes inner join municipios on " +
                                                                           "c5_2_ap_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.nombre;";

public static final String SQL_5_2_PROV_SubGrupoOTROS                   = "select provincias.nombre" +
                                                                          ", sum(accidentes_par), sum(accidentes_mort) " +
                                                                           "from (c5_2_otros_accidentes inner join municipios on " +
                                                                           "c5_2_otros_accidentes.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id " +
                                                                           "where anno=:anno group by provincias.nombre;";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_5_2_AP                               = "select accidentes_par as  "+CONSTANTS.ACCIDENTES_PAR_COLUMN_NAME+",accidentes_mort as  "+CONSTANTS.ACCIDENTES_MORTALES_COLUMN_NAME+", anno " +
                                                                           "from c5_2_ap_accidentes "+
                                                                           "where c5_2_ap_accidentes.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_5_2_AP_YEARS                         = "select distinct anno from c5_2_ap_accidentes order by anno";
 //......................................................................................................................
   public static final String G_SQL_5_2_US                               = "select accidentes_par as  "+CONSTANTS.ACCIDENTES_PAR_COLUMN_NAME+",accidentes_mort as  "+CONSTANTS.ACCIDENTES_MORTALES_COLUMN_NAME+", anno " +
                                                                           "from c5_2_us_accidentes "+
                                                                           "where c5_2_us_accidentes.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_5_2_US_YEARS                         = "select distinct anno from c5_2_us_accidentes order by anno";

  //......................................................................................................................
   public static final String G_SQL_5_2_OTROS_YEARS                      = "select distinct anno from c5_2_otros_accidentes order by anno";

  //......................................................................................................................
   public static final String G_SQL_5_2_EFI                              = "select sum(accidentes_par) as  "+CONSTANTS.ACCIDENTES_PAR_COLUMN_NAME+",sum(accidentes_mort) as  "+CONSTANTS.ACCIDENTES_MORTALES_COLUMN_NAME+", anno " +
                                                                           "from c5_2_us_accidentes inner join usilvicola on c5_2_us_accidentes.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_5_2_EFI_YEARS                        = "select distinct anno from c5_2_us_accidentes order by anno";
       //......................................................................................................................
   public static final String G_SQL_5_2_MUN_AP                           = "select sum(accidentes_par),sum(accidentes_mort), anno " +
                                                                           "from c5_2_ap_accidentes inner join municipios on c5_2_ap_accidentes.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_2_MUN_US                           = "select sum(accidentes_par),sum(accidentes_mort), anno " +
                                                                           "from c5_2_us_accidentes inner join municipios on c5_2_us_accidentes.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_2_MUN_OTROS                        = "select sum(accidentes_par),sum(accidentes_mort), anno " +
                                                                           "from c5_2_otros_accidentes inner join municipios on c5_2_otros_accidentes.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_2_MUN_YEARS_AP                    = "select distinct anno from c5_2_ap_accidentes where municipio=':id' order by anno";

   public static final String G_SQL_5_2_MUN_YEARS_US                    = "select distinct anno from c5_2_us_accidentes where municipio=':id' order by anno";

   public static final String G_SQL_5_2_MUN_YEARS_OTROS                 = "select distinct anno from c5_2_otros_accidentes where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_5_2_PROV_AP                          = "select sum(accidentes_par),sum(accidentes_mort), anno " +
                                                                           "from c5_2_ap_accidentes inner join municipios on c5_2_ap_accidentes.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_2_PROV_US                           = "select sum(accidentes_par),sum(accidentes_mort), anno " +
                                                                           "from c5_2_us_accidentes inner join municipios on c5_2_us_accidentes.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_2_PROV_OTROS                        = "select sum(accidentes_par),sum(accidentes_mort), anno " +
                                                                           "from c5_2_otros_accidentes inner join municipios on c5_2_otros_accidentes.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_2_PROV_YEARS_AP                    = "select distinct anno from c5_2_ap_accidentes inner join municipios on c5_2_ap_accidentes.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_5_2_PROV_YEARS_US                    = "select distinct anno from c5_2_us_accidentes inner join municipios on c5_2_us_accidentes.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_5_2_PROV_YEARS_OTROS                 = "select distinct anno from c5_2_otros_accidentes inner join municipios on c5_2_otros_accidentes.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_5_2_Temp_Table                       = "select accidentes_par as  "+CONSTANTS.ACCIDENTES_PAR_COLUMN_NAME+",accidentes_mort as  "+CONSTANTS.ACCIDENTES_MORTALES_COLUMN_NAME+", anno " +
                                                                           "from c_5_2_graphicdata order by anno";

//====================== Criterio 5_4 Servicios =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_5_4_AP                              = "select municipios.nombre as municipio, anno as Año , c5_4_servicios.nombre as "+CONSTANTS.SERVICIOS_COLUMN_NAME +
                                                                        ", cantidad as "+CONSTANTS.EXTENSION_COLUMN_NAME+", plan_manejo as "+CONSTANTS.PLAN_MANEJO_COLUMN_NAME+
                                                                        " from (c5_4_ap_servicios inner join municipios on c5_4_ap_servicios.municipio=municipios.id) " +
                                                                        " inner join c5_4_servicios on c5_4_ap_servicios.id_serv=c5_4_servicios.id " +
                                                                        "where c5_4_ap_servicios.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c5_4_servicios.nombre, cantidad, plan_manejo " +
                                                                        "order by municipios.nombre, anno, c5_4_servicios.nombre, cantidad, plan_manejo";
//----------------------------------------------------------------------------------
 public static final String SQL_5_4_US                              = "select municipios.nombre as municipio, anno as Año , c5_4_servicios.nombre as "+CONSTANTS.SERVICIOS_COLUMN_NAME +
                                                                        ", cantidad as "+CONSTANTS.EXTENSION_COLUMN_NAME+", plan_manejo as "+CONSTANTS.PLAN_MANEJO_COLUMN_NAME+
                                                                        " from (c5_4_us_servicios inner join municipios on c5_4_us_servicios.municipio=municipios.id) " +
                                                                        " inner join c5_4_servicios on c5_4_us_servicios.id_serv=c5_4_servicios.id " +
                                                                        "where c5_4_us_servicios.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c5_4_servicios.nombre, cantidad, plan_manejo " +
                                                                        "order by municipios.nombre, anno, c5_4_servicios.nombre, cantidad, plan_manejo";
//----------------------------------------------------------------------------------
public static final String SQL_5_4_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                         "c5_4_servicios.nombre as "+CONSTANTS.SERVICIOS_COLUMN_NAME+", sum(cantidad) as "+CONSTANTS.EXTENSION_COLUMN_NAME+", " +
                                                                         "sum(plan_manejo) as "+CONSTANTS.PLAN_MANEJO_COLUMN_NAME+
                                                                         " from (((c5_4_us_servicios inner join usilvicola on c5_4_us_servicios.id=usilvicola.id) " +
                                                                         "inner join efi on usilvicola.efi=efi.id)inner join municipios on c5_4_us_servicios.municipio=municipios.id)" +
                                                                         "inner join c5_4_servicios on c5_4_us_servicios.id_serv=c5_4_servicios.id " +
                                                                         "where efi.id=':id' and anno=:anno " +
                                                                         "group by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c5_4_servicios.nombre " +
                                                                         "order by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c5_4_servicios.nombre";

public static final String SQL_5_4_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(cantidad) as cantidad, sum(plan_manejo) as plan_manejo " +
                                                                           "from (c5_4_us_servicios inner join usilvicola on c5_4_us_servicios.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
   public static final String SQL_5_4_MUN_SubGrupoUS                      = "select provincias.nombre, municipios.nombre, c5_4_servicios.nombre" +
                                                                              ", sum(cantidad), sum(plan_manejo) " +
                                                                              "from ((c5_4_us_servicios inner join municipios on " +
                                                                              "c5_4_us_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_4_servicios on c5_4_us_servicios.id_serv=c5_4_servicios.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c5_4_servicios.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c5_4_servicios.nombre;";

   public static final String SQL_5_4_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre, c5_4_servicios.nombre" +
                                                                              ", sum(cantidad), sum(plan_manejo) " +
                                                                              "from ((c5_4_ap_servicios inner join municipios on " +
                                                                              "c5_4_ap_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_4_servicios on c5_4_ap_servicios.id_serv=c5_4_servicios.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c5_4_servicios.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c5_4_servicios.nombre;";

   public static final String SQL_5_4_MUN_SubGrupoOTROS                       = "select provincias.nombre, municipios.nombre, c5_4_servicios.nombre" +
                                                                              ", sum(cantidad), sum(plan_manejo) " +
                                                                              "from ((c5_4_otros_servicios inner join municipios on " +
                                                                              "c5_4_otros_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_4_servicios on c5_4_otros_servicios.id_serv=c5_4_servicios.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c5_4_servicios.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c5_4_servicios.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_5_4_MUN_SubGrupoUS_Entities               = "select usilvicola.nombre, c5_4_servicios.nombre, cantidad, plan_manejo " +
                                                                              "from (((c5_4_us_servicios inner join municipios on " +
                                                                              "c5_4_us_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_4_servicios on c5_4_us_servicios.id_serv=c5_4_servicios.id)" +
                                                                              "inner join usilvicola on c5_4_us_servicios.id=usilvicola.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_5_4_MUN_SubGrupoAP_Entities               = "select area_protegida.nombre, c5_4_servicios.nombre, cantidad, plan_manejo " +
                                                                              "from (((c5_4_ap_servicios inner join municipios on " +
                                                                              "c5_4_ap_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_4_servicios on c5_4_ap_servicios.id_serv=c5_4_servicios.id)" +
                                                                              "inner join area_protegida on c5_4_ap_servicios.id=area_protegida.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_5_4_MUN_SubGrupoOTROS_Entities            = "select otros.nombre, c5_4_servicios.nombre, cantidad, plan_manejo " +
                                                                              "from (((c5_4_otros_servicios inner join municipios on " +
                                                                              "c5_4_otros_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_4_servicios on c5_4_otros_servicios.id_serv=c5_4_servicios.id)" +
                                                                              "inner join otros on c5_4_otros_servicios.id=otros.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";
//----------------------------------------------------------------------------------
public static final String SQL_5_4_PROV_SubGrupoUS                     = "select provincias.nombre, c5_4_servicios.nombre " +
                                                                            ", sum(cantidad), sum(plan_manejo) " +
                                                                            "from ((c5_4_us_servicios inner join municipios on " +
                                                                            "c5_4_us_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c5_4_servicios on c5_4_us_servicios.id_serv=c5_4_servicios.id " +
                                                                            "where anno=:anno group by provincias.nombre, c5_4_servicios.nombre " +
                                                                            "order by provincias.nombre, c5_4_servicios.nombre";

public static final String SQL_5_4_PROV_SubGrupoAP                     = "select provincias.nombre, c5_4_servicios.nombre " +
                                                                            ", sum(cantidad), sum(plan_manejo) " +
                                                                            "from ((c5_4_ap_servicios inner join municipios on " +
                                                                            "c5_4_ap_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c5_4_servicios on c5_4_ap_servicios.id_serv=c5_4_servicios.id " +
                                                                            "where anno=:anno group by provincias.nombre, c5_4_servicios.nombre " +
                                                                            "order by provincias.nombre, c5_4_servicios.nombre";

public static final String SQL_5_4_PROV_SubGrupoOTROS                     = "select provincias.nombre, c5_4_servicios.nombre " +
                                                                            ", sum(cantidad), sum(plan_manejo) " +
                                                                            "from ((c5_4_otros_servicios inner join municipios on " +
                                                                            "c5_4_otros_servicios.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c5_4_servicios on c5_4_otros_servicios.id_serv=c5_4_servicios.id " +
                                                                            "where anno=:anno group by provincias.nombre, c5_4_servicios.nombre " +
                                                                            "order by provincias.nombre, c5_4_servicios.nombre";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_5_4_AP                               = "select cantidad as  "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+",plan_manejo as  "+CONSTANTS.PLAN_MANEJO_COLUMN_NAME+", anno " +
                                                                           "from c5_4_ap_servicios "+
                                                                           "where c5_4_ap_servicios.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_5_4_AP_YEARS                         = "select distinct anno from c5_4_ap_servicios order by anno";
 //......................................................................................................................
   public static final String G_SQL_5_4_US                               = "select cantidad as  "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+",plan_manejo as  "+CONSTANTS.PLAN_MANEJO_COLUMN_NAME+", anno " +
                                                                           "from c5_4_us_servicios "+
                                                                           "where c5_4_us_servicios.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_5_4_US_YEARS                         = "select distinct anno from c5_4_us_servicios order by anno";

  //......................................................................................................................
   public static final String G_SQL_5_4_OTROS_YEARS                      = "select distinct anno from c5_4_otros_servicios order by anno";

  //......................................................................................................................
   public static final String G_SQL_5_4_EFI                              = "select sum(cantidad) as  "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+",sum(plan_manejo) as  "+CONSTANTS.PLAN_MANEJO_COLUMN_NAME+", anno " +
                                                                           "from c5_4_us_servicios inner join usilvicola on c5_4_us_servicios.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_5_4_EFI_YEARS                        = "select distinct anno from c5_4_us_servicios order by anno";
       //......................................................................................................................
   public static final String G_SQL_5_4_MUN_AP                           = "select sum(cantidad),sum(plan_manejo), anno " +
                                                                           "from c5_4_ap_servicios inner join municipios on c5_4_ap_servicios.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_4_MUN_US                           = "select sum(cantidad),sum(plan_manejo), anno " +
                                                                           "from c5_4_us_servicios inner join municipios on c5_4_us_servicios.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_4_MUN_OTROS                        = "select sum(cantidad),sum(plan_manejo), anno " +
                                                                           "from c5_4_otros_servicios inner join municipios on c5_4_otros_servicios.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_4_MUN_YEARS_AP                    = "select distinct anno from c5_4_ap_servicios where municipio=':id' order by anno";

   public static final String G_SQL_5_4_MUN_YEARS_US                    = "select distinct anno from c5_4_us_servicios where municipio=':id' order by anno";

   public static final String G_SQL_5_4_MUN_YEARS_OTROS                 = "select distinct anno from c5_4_otros_servicios where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_5_4_PROV_AP                          = "select sum(cantidad),sum(plan_manejo), anno " +
                                                                           "from c5_4_ap_servicios inner join municipios on c5_4_ap_servicios.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_4_PROV_US                           = "select sum(cantidad),sum(plan_manejo), anno " +
                                                                           "from c5_4_us_servicios inner join municipios on c5_4_us_servicios.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_4_PROV_OTROS                        = "select sum(cantidad),sum(plan_manejo), anno " +
                                                                           "from c5_4_otros_servicios inner join municipios on c5_4_otros_servicios.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_4_PROV_YEARS_AP                    = "select distinct anno from c5_4_ap_servicios inner join municipios on c5_4_ap_servicios.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_5_4_PROV_YEARS_US                    = "select distinct anno from c5_4_us_servicios inner join municipios on c5_4_us_servicios.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_5_4_PROV_YEARS_OTROS                 = "select distinct anno from c5_4_otros_servicios inner join municipios on c5_4_otros_servicios.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_5_4_Temp_Table                       = "select cantidad as  "+CONSTANTS.CANTIDAD_4_4_COLUMN_NAME+",plan_manejo as  "+CONSTANTS.PLAN_MANEJO_COLUMN_NAME+", anno " +
                                                                           "from c_5_4_graphicdata order by anno";

//====================== Criterio 5_5 Categoría ocupacional =====================================================================================================================================================================
//----------------------------------------------------------------------------------
 public static final String SQL_5_5_AP                              = "select municipios.nombre as municipio, anno as Año , c5_5_catgocupacional.nombre as "+CONSTANTS.CATEG_OCUPACIONAL_COLUMN_NAME +
                                                                        ", cant_muj as "+CONSTANTS.CANT_MUJERES_COLUMN_NAME+
                                                                        " from (c5_5_ap_catgocupacional inner join municipios on c5_5_ap_catgocupacional.municipio=municipios.id) " +
                                                                        " inner join c5_5_catgocupacional on c5_5_ap_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                        "where c5_5_ap_catgocupacional.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c5_5_catgocupacional.nombre, cant_muj " +
                                                                        "order by municipios.nombre, anno, c5_5_catgocupacional.nombre, cant_muj";
//----------------------------------------------------------------------------------
 public static final String SQL_5_5_US                              = "select municipios.nombre as municipio, anno as Año , c5_5_catgocupacional.nombre as "+CONSTANTS.CATEG_OCUPACIONAL_COLUMN_NAME +
                                                                        ", cant_muj as "+CONSTANTS.CANT_MUJERES_COLUMN_NAME+
                                                                        " from (c5_5_us_catgocupacional inner join municipios on c5_5_us_catgocupacional.municipio=municipios.id) " +
                                                                        " inner join c5_5_catgocupacional on c5_5_us_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                        "where c5_5_us_catgocupacional.id=':id' and anno=:anno " +
                                                                        "group by municipios.nombre, anno, c5_5_catgocupacional.nombre, cant_muj " +
                                                                        "order by municipios.nombre, anno, c5_5_catgocupacional.nombre, cant_muj";
//----------------------------------------------------------------------------------
public static final String SQL_5_5_EFI_Subgrupo                         = "select municipios.nombre as municipio, anno as Año, efi.nombre as EFi, usilvicola.nombre as "+CONSTANTS.US_COLUMN_NAME+", " +
                                                                         "c5_5_catgocupacional.nombre as "+CONSTANTS.CATEG_OCUPACIONAL_COLUMN_NAME+", sum(cant_muj) as "+CONSTANTS.CANT_MUJERES_COLUMN_NAME+
                                                                         " from (((c5_5_us_catgocupacional inner join usilvicola on c5_5_us_catgocupacional.id=usilvicola.id) " +
                                                                         "inner join efi on usilvicola.efi=efi.id)inner join municipios on c5_5_us_catgocupacional.municipio=municipios.id)" +
                                                                         "inner join c5_5_catgocupacional on c5_5_us_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                         "where efi.id=':id' and anno=:anno " +
                                                                         "group by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c5_5_catgocupacional.nombre " +
                                                                         "order by municipios.nombre, anno, efi.nombre, usilvicola.nombre, c5_5_catgocupacional.nombre";

public static final String SQL_5_5_EFI_TOTAL                            = "select efi.nombre as efi" +
                                                                          ", sum(cant_muj) as cant_muj " +
                                                                           "from (c5_5_us_catgocupacional inner join usilvicola on c5_5_us_catgocupacional.id=usilvicola.id) inner join efi on usilvicola.efi=efi.id " +
                                                                           "where efi.id=':id' and anno=:anno group by efi.nombre";
//----------------------------------------------------------------------------------
   public static final String SQL_5_5_MUN_SubGrupoUS                      = "select provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre" +
                                                                              ", sum(cant_muj) " +
                                                                              "from ((c5_5_us_catgocupacional inner join municipios on " +
                                                                              "c5_5_us_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_5_catgocupacional on c5_5_us_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre;";

   public static final String SQL_5_5_MUN_SubGrupoAP                       = "select provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre" +
                                                                              ", sum(cant_muj) " +
                                                                              "from ((c5_5_ap_catgocupacional inner join municipios on " +
                                                                              "c5_5_ap_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_5_catgocupacional on c5_5_ap_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre;";

   public static final String SQL_5_5_MUN_SubGrupoOTROS                       = "select provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre" +
                                                                              ", sum(cant_muj) " +
                                                                              "from ((c5_5_otros_catgocupacional inner join municipios on " +
                                                                              "c5_5_otros_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_5_catgocupacional on c5_5_otros_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                              "where municipios.id=':id' and anno=:anno " +
                                                                              "group by provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre " +
                                                                              "order by provincias.nombre, municipios.nombre, c5_5_catgocupacional.nombre;";
//----------------------------------------------------------------------------------
   public static final String SQL_5_5_MUN_SubGrupoUS_Entities               = "select usilvicola.nombre, c5_5_catgocupacional.nombre, cant_muj " +
                                                                              "from (((c5_5_us_catgocupacional inner join municipios on " +
                                                                              "c5_5_us_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_5_catgocupacional on c5_5_us_catgocupacional.id_catg=c5_5_catgocupacional.id)" +
                                                                              "inner join usilvicola on c5_5_us_catgocupacional.id=usilvicola.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_5_5_MUN_SubGrupoAP_Entities               = "select area_protegida.nombre, c5_5_catgocupacional.nombre, cant_muj " +
                                                                              "from (((c5_5_ap_catgocupacional inner join municipios on " +
                                                                              "c5_5_ap_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_5_catgocupacional on c5_5_ap_catgocupacional.id_catg=c5_5_catgocupacional.id)" +
                                                                              "inner join area_protegida on c5_5_ap_catgocupacional.id=area_protegida.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";

   public static final String SQL_5_5_MUN_SubGrupoOTROS_Entities            = "select otros.nombre, c5_5_catgocupacional.nombre, cant_muj " +
                                                                              "from (((c5_5_otros_catgocupacional inner join municipios on " +
                                                                              "c5_5_otros_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id)" +
                                                                              "inner join c5_5_catgocupacional on c5_5_otros_catgocupacional.id_catg=c5_5_catgocupacional.id)" +
                                                                              "inner join otros on c5_5_otros_catgocupacional.id=otros.id " +
                                                                              "where municipios.id=':id' and anno=:anno;";
//----------------------------------------------------------------------------------
public static final String SQL_5_5_PROV_SubGrupoUS                     = "select provincias.nombre, c5_5_catgocupacional.nombre " +
                                                                            ", sum(cant_muj) " +
                                                                            "from ((c5_5_us_catgocupacional inner join municipios on " +
                                                                            "c5_5_us_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c5_5_catgocupacional on c5_5_us_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                            "where anno=:anno group by provincias.nombre, c5_5_catgocupacional.nombre " +
                                                                            "order by provincias.nombre, c5_5_catgocupacional.nombre";

public static final String SQL_5_5_PROV_SubGrupoAP                     = "select provincias.nombre, c5_5_catgocupacional.nombre " +
                                                                            ", sum(cant_muj) " +
                                                                            "from ((c5_5_ap_catgocupacional inner join municipios on " +
                                                                            "c5_5_ap_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c5_5_catgocupacional on c5_5_ap_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                            "where anno=:anno group by provincias.nombre, c5_5_catgocupacional.nombre " +
                                                                            "order by provincias.nombre, c5_5_catgocupacional.nombre";

public static final String SQL_5_5_PROV_SubGrupoOTROS                     = "select provincias.nombre, c5_5_catgocupacional.nombre " +
                                                                            ", sum(cant_muj) " +
                                                                            "from ((c5_5_otros_catgocupacional inner join municipios on " +
                                                                            "c5_5_otros_catgocupacional.municipio=municipios.id) inner join provincias on municipios.provincia=provincias.id) " +
                                                                            "inner join c5_5_catgocupacional on c5_5_otros_catgocupacional.id_catg=c5_5_catgocupacional.id " +
                                                                            "where anno=:anno group by provincias.nombre, c5_5_catgocupacional.nombre " +
                                                                            "order by provincias.nombre, c5_5_catgocupacional.nombre";

//...................................... SQL para gráficos ........................................................................................................................................................................................
  //......................................................................................................................
   public static final String G_SQL_5_5_AP                               = "select cant_muj as  "+CONSTANTS.CANT_MUJERES_COLUMN_NAME+", anno " +
                                                                           "from c5_5_ap_catgocupacional "+
                                                                           "where c5_5_ap_catgocupacional.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_5_5_AP_YEARS                         = "select distinct anno from c5_5_ap_catgocupacional order by anno";
 //......................................................................................................................
   public static final String G_SQL_5_5_US                               = "select cant_muj as  "+CONSTANTS.CANT_MUJERES_COLUMN_NAME+", anno " +
                                                                           "from c5_5_us_catgocupacional "+
                                                                           "where c5_5_us_catgocupacional.id=':id' and anno in(:anno) order by anno";

   public static final String G_SQL_5_5_US_YEARS                         = "select distinct anno from c5_5_us_catgocupacional order by anno";

  //......................................................................................................................
   public static final String G_SQL_5_5_OTROS_YEARS                      = "select distinct anno from c5_5_otros_catgocupacional order by anno";

  //......................................................................................................................
   public static final String G_SQL_5_5_EFI                              = "select sum(cant_muj) as  "+CONSTANTS.CANT_MUJERES_COLUMN_NAME+", anno " +
                                                                           "from c5_5_us_catgocupacional inner join usilvicola on c5_5_us_catgocupacional.id=usilvicola.id "+
                                                                           "where usilvicola.efi=':id' and anno in(:anno) " +
                                                                           "group by usilvicola.efi, anno order by anno";

   public static final String G_SQL_5_5_EFI_YEARS                        = "select distinct anno from c5_5_us_catgocupacional order by anno";
       //......................................................................................................................
   public static final String G_SQL_5_5_MUN_AP                           = "select sum(cant_muj), anno " +
                                                                           "from c5_5_ap_catgocupacional inner join municipios on c5_5_ap_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_5_MUN_US                           = "select sum(cant_muj), anno " +
                                                                           "from c5_5_us_catgocupacional inner join municipios on c5_5_us_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_5_MUN_OTROS                        = "select sum(cant_muj), anno " +
                                                                           "from c5_5_otros_catgocupacional inner join municipios on c5_5_otros_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.id=':id' and anno in(:anno) " +
                                                                           "group by municipios.id, anno order by anno";

   public static final String G_SQL_5_5_MUN_YEARS_AP                    = "select distinct anno from c5_5_ap_catgocupacional where municipio=':id' order by anno";

   public static final String G_SQL_5_5_MUN_YEARS_US                    = "select distinct anno from c5_5_us_catgocupacional where municipio=':id' order by anno";

   public static final String G_SQL_5_5_MUN_YEARS_OTROS                 = "select distinct anno from c5_5_otros_catgocupacional where municipio=':id' order by anno";
//......................................................................................................................
   public static final String G_SQL_5_5_PROV_AP                          = "select sum(cant_muj), anno " +
                                                                           "from c5_5_ap_catgocupacional inner join municipios on c5_5_ap_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_5_PROV_US                           = "select sum(cant_muj), anno " +
                                                                           "from c5_5_us_catgocupacional inner join municipios on c5_5_us_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_5_PROV_OTROS                        = "select sum(cant_muj), anno " +
                                                                           "from c5_5_otros_catgocupacional inner join municipios on c5_5_otros_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' and anno in(:anno) " +
                                                                           "group by municipios.provincia, anno order by anno";

   public static final String G_SQL_5_5_PROV_YEARS_AP                    = "select distinct anno from c5_5_ap_catgocupacional inner join municipios on c5_5_ap_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_5_5_PROV_YEARS_US                    = "select distinct anno from c5_5_us_catgocupacional inner join municipios on c5_5_us_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";

   public static final String G_SQL_5_5_PROV_YEARS_OTROS                 = "select distinct anno from c5_5_otros_catgocupacional inner join municipios on c5_5_otros_catgocupacional.municipio=municipios.id "+
                                                                           "where municipios.provincia=':id' order by anno";
       //......................................................................................................................

   public static final String G_SQL_5_5_Temp_Table                       = "select cant_muj as  "+CONSTANTS.CANT_MUJERES_COLUMN_NAME+", anno " +
                                                                           "from c_5_5_graphicdata order by anno";
}
