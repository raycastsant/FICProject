package cif.bd;


import cif.manage.BaseUnits;
import cif.manage.Manage;
import cif.manage.User;
import java.sql.SQLException;


public class DBAccess 
{
	public static JDBCAdapter getconnection() throws SQLException
	{
		if(!CollectionConnection.hasConnection(ClassParams.HOST))
		{
			DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
			CollectionConnection.SetConnection(da);
		}
		JDBCAdapter con = CollectionConnection.getConnection(ClassParams.HOST);
		return con;
	}

        public static int verificarPkey(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select id,anno,municipio from "+tablename+" where id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getRowCount();
        }

        public static int verificarPkey2(String select,String tablename,String where) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            
            adapter.executeQuery(" "+select+" from "+tablename+" "+where+"");
            System.out.print("-- "+select+" from "+tablename+" "+where+"");
            return adapter.getRowCount();
        }

        public static int getnextid(BaseUnits bunits) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            String campo;
            int id;
            if (bunits==BaseUnits.Area_Protegida)
            {
                campo="ap";
            }
            else if(bunits==BaseUnits.Otros)
            {
                campo="otros";
            }
            else
            {
                campo="us";
            }
            adapter.executeQuery("select "+campo+" from tipo_id");
            id=java.lang.Integer.parseInt(adapter.getValueAt(0, 0).toString());
            adapter.executeQuery("update tipo_id set "+campo+"="+id+1+"");
            return id+1;
        }

	public static void insertTAC(String id,Integer anno,Double bosqnat,Double plant,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);

            /*adapter.executeQuery("select "+tipo+" from idEntidad");
            Integer nextid=java.lang.Integer.parseInt(adapter.getValueAt(0, 0).toString());
            if (nextid==null) 
            {
                nextid=1;
            }
            id=utiles.generate_id(nextid);*/
            Manage manager = Manage.getInstance();
            User user = manager.getUser();      
            adapter.executeQuery("insert into "+tablename+" (id,municipio,anno,bosques_nat,plantacion_estb) values ('"+id+"','"+user.getMunicipio()+"',"+anno+","+bosqnat+","+plant+")");
            /* if (nextid==1)
            {
                adapter.executeQuery("insert into idEntidad ("+tipo+") values ("+nextid+"");
            }
             else
             {
                adapter.executeQuery("update idEntidad set "+tipo+"= "+nextid+1+"");
             }*/
        }

        public static void updateTAC(String id,Integer anno,Integer annoInicial,Double bosqnat,Double plant,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET  bosques_nat="+bosqnat+", plantacion_estb="+plant+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
      System.out.println("UPDATE "+tablename+" SET  bosques_nat="+bosqnat+", plantacion_estb="+plant+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
        }

        public static Object[][] selectTAC(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select bosques_nat,plantacion_estb from "+tablename+" WHERE id='"+id+"'and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            System.out.println("select bosques_nat,plantacion_estb from "+tablename+" WHERE id='"+id+"'and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
        }

        public static void insertIndBoscosidad(String id,Integer anno,Double superfgeog,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);          
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("insert into "+tablename+" (id,municipio,anno,superficie_geog) values ('"+id+"','"+user.getMunicipio()+"',"+anno+","+superfgeog+")");
        }

        public static void updateIndBoscosidad(String id,Integer anno,Integer annoInicial,Double superfgeog,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET superficie_geog="+superfgeog+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
        }

        public static Object[][] selectIndBoscosidad(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select superficie_geog from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
        }


        public static void insertRelTACSCF(String id,Integer anno,Double bosqralo,Double calvero,Double lugtalado,Double plantjoven,Double plantmuertos,Double superfquem,Double xmogote,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("insert into "+tablename+" (id,municipio,anno,bosque_ralo,calvero,lugar_talado,plantac_joven,plantac_montes_muertos,superf_quemada,xerofilo_mogote) values ('"+id+"','"+user.getMunicipio()+"',"+anno+","+bosqralo+", "+calvero+","+lugtalado+","+plantjoven+","+plantmuertos+","+superfquem+","+xmogote+")");
        }

        public static void updateRelTACSCF(String id,Integer anno,Integer annoInicial,Double bosqralo,Double calvero,Double lugtalado,Double plantjoven,Double plantmuertos,Double superfquem,Double xmogote,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET bosque_ralo="+bosqralo+", calvero="+calvero+", lugar_talado="+lugtalado+",  plantac_joven="+plantjoven+", plantac_montes_muertos="+plantmuertos+", superf_quemada="+superfquem+",  xerofilo_mogote="+xmogote+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
        }

        public static Object[][] selectRelTACSCF(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select bosque_ralo, calvero, lugar_talado, plantac_joven, plantac_montes_muertos, superf_quemada, xerofilo_mogote from "+tablename+"  WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
        }
        public static void insertEfectv(String id,Integer anno,Double supfplant,Double supflog,Integer plantvivas,Integer plantmuertas,Integer especie,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("insert into "+tablename+" (id,municipio,anno,superf_plant,superf_log,plant_vivas,plant_muertas,especie) values ('"+id+"','"+user.getMunicipio()+"',"+anno+","+supfplant+", "+supflog+","+plantvivas+","+plantmuertas+","+especie+")");
        }

        public static void updateEfectv(String id,Integer anno,Integer annoInicial,Double supfplant,Double supflog,Integer plantvivas,Integer plantmuertas,Integer especie,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET superf_plant="+supfplant+", superf_log="+supflog+", plant_vivas="+plantvivas+", plant_muertas="+plantmuertas+", especie="+especie+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+" and especie="+especie+"");
        }

        public static Object[][] selectEfectv(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select superf_plant,superf_log,plant_vivas,plant_muertas,especie from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+" order by especie");
            System.out.println("select superf_plant,superf_log,plant_vivas,plant_muertas,especie from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+" order by especie");
            return adapter.getAllRows();
        }

        public static void insertPendref(String id,Integer anno,Double areatal_def,Double areaquem_def,Double areatal,Double areaestabl,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("insert into "+tablename+" (id,municipio,anno,area_tal_def,area_quem_def,area_tal,area_establ) values ('"+id+"','"+user.getMunicipio()+"',"+anno+","+areatal_def+", "+areaquem_def+","+areatal+","+areaestabl+")");
        }

        public static void updatePendref(String id,Integer anno,Integer annoInicial,Double areatal_def,Double areaquem_def,Double areatal,Double areaestabl,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET area_tal_def="+areatal_def+", area_quem_def="+areaquem_def+", area_tal="+areatal+",  area_establ="+areaestabl+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
        }

        public static  Object[][] selectPendref(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select area_tal_def,area_quem_def,area_tal,area_establ from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
        }

         public static void insertArbAisl(String id,Integer anno,Integer arbexiste,Integer arbfalta,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("insert into "+tablename+" (id,municipio,anno,arbol_existe,arbol_falta) values ('"+id+"','"+user.getMunicipio()+"',"+anno+","+arbexiste+", "+arbfalta+")");
        }

         public static void upddateArbAisl(String id,Integer anno,Integer annoInicial,Integer arbexiste,Integer arbfalta,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET arbol_existe="+arbexiste+", arbol_falta="+arbfalta+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
         }

         public static Object[][] selectArbAisl(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select arbol_existe,arbol_falta from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
         }

         public static void insertInc(String id,Integer anno,Integer cant,Double distrec,Double superftot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("insert into "+tablename+" (id,municipio,anno,cantidad,distanc_recorr,super_afect_tot) values ('"+id+"','"+user.getMunicipio()+"',"+anno+","+cant+", "+distrec+","+superftot+")");
         }

         public static void updateInc(String id,Integer anno,Integer annoInicial,Integer cant,Double distrec,Double superftot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET cantidad="+cant+", distanc_recorr="+distrec+", super_afect_tot="+superftot+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
         }

         public static Object[][] selectInc(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select cantidad, distanc_recorr, super_afect_tot from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            System.out.println(" --- select cantidad, distanc_recorr, super_afect_tot from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"--");

            return adapter.getAllRows();

         }

         public static void insertIncEsp(String id,Integer anno,Integer especie,Double perddir,Double perdind,Double haperd,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, especie, anno, perd_direc, perd_indir, hectar_perd, municipio)VALUES ('"+id+"',"+especie+", "+anno+", "+perddir+", "+perdind+","+haperd+",'"+user.getMunicipio()+"')");
         }


         public static Object[][] selectIncEsp(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select especie,perd_direc,perd_indir,hectar_perd from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
         }

         public static Object[][] selectComboxcEsp(String id,Integer anno,String tablename,Integer idEspc,String columns) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select "+columns+" from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+" and especie="+idEspc+"");
            System.out.println("select "+columns+" from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+" and especie="+idEspc+"");
            return adapter.getAllRows();
            //perd_direc,perd_indir,hectar_perd
         }

         public static void updateIncEsp(String id,Integer anno,Integer annoInicial,Integer especie,Double perddir,Double perdind,Double haperd,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET perd_direc="+perddir+", perd_indir="+perdind+", hectar_perd="+haperd+",anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+" and especie="+especie+"");
         }

         public static void UpdateEspc(String id,Integer anno,Integer annoInicial,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
         System.out.println("UPDATE "+tablename+" SET anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
         }

         public static void insertBosqAfect(String id,Integer anno,Double bntexo,Double peexo,Double bnpast,Double pepast,Double plagenferm,Double otrascausas,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, bosq_nat_exo, plant_estb_exo, bosq_nat_past, pant_estb_past, plagas_enferm, otras_causas)VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"', "+bntexo+", "+peexo+", "+bnpast+", "+pepast+", "+plagenferm+", "+otrascausas+")");
        }

         public static void updateBosqAfect(String id,Integer anno,Integer annoInicial,Double bntexo,Double peexo,Double bnpast,Double pepast,Double plagenferm,Double otrascausas,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET bosq_nat_exo="+bntexo+", plant_estb_exo="+peexo+",  bosq_nat_past="+bnpast+", pant_estb_past="+pepast+", plagas_enferm="+plagenferm+", otras_causas="+otrascausas+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");

         }

         public static Object[][] selectBosqAfect(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select bosq_nat_exo, plant_estb_exo, bosq_nat_past, pant_estb_past, plagas_enferm, otras_causas from "+tablename+"  WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            System.out.print("select bosq_nat_exo, plant_estb_exo, bosq_nat_past, pant_estb_past, plagas_enferm, otras_causas from "+tablename+"  WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
         }

         public static void insertBosqManSil(String id,Integer anno,Double selimp,Double nalimp,Double sepoda,Double napoda,Double seraleo,Double narealeo,Double serec,Double narec,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, superf_ejec_limp, nec_anual_limp, superf_ejec_poda, nec_anual_poda, superf_ejec_raleo, nec_anual_realeo, superf_ejec_rec, nec_anual_rec)VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+selimp+", "+nalimp+", "+sepoda+", "+napoda+", "+seraleo+", "+narealeo+", "+serec+", "+narec+")");
         }

         public static void updateBosqManSil(String id,Integer anno,Integer annoInicial,Double selimp,Double nalimp,Double sepoda,Double napoda,Double seraleo,Double narealeo,Double serec,Double narec,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET superf_ejec_limp="+selimp+", nec_anual_limp="+nalimp+", superf_ejec_poda="+napoda+", nec_anual_poda="+sepoda+", superf_ejec_raleo="+seraleo+", nec_anual_realeo="+narealeo+", superf_ejec_rec="+serec+", nec_anual_rec="+narec+", anno="+anno+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+annoInicial+"");
         }

         public static Object[][] selectBosqManSil(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select superf_ejec_limp, nec_anual_limp, superf_ejec_poda, nec_anual_poda, superf_ejec_raleo, nec_anual_realeo, superf_ejec_rec, nec_anual_rec from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            System.out.print("select superf_ejec_limp, nec_anual_limp, superf_ejec_poda, nec_anual_poda, superf_ejec_raleo, nec_anual_realeo, superf_ejec_rec, nec_anual_rec from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
         }

         public static void insertFormacForestal(String id,Integer anno,String idform,Double area,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, id_formforest, anno, municipio, area) VALUES ('"+id+"',"+idform+","+anno+",'"+user.getMunicipio()+"',"+area+")");
         }

         public static void updateFormacForestal(String id,Integer anno,Integer annoInicial,String idform,Double area,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET area="+area+", anno="+anno+" WHERE id='"+id+"' and id_formforest='"+idform+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
         }

         public static Object[][] selectFormacForestal(String id,Integer anno,String tag,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            if (tag.equals("mun"))
            {
              adapter.executeQuery("select  id,id_formforest, area from "+tablename+" WHERE municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            }
            else
            {
              adapter.executeQuery("select  id_formforest, area from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            }
            return adapter.getAllRows();
         }

         public static void insertEspEndm(String id,Integer anno,String idespcend,int progconserv,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, id_espendm, anno, municipio, progconserv) VALUES ('"+id+"',"+idespcend+","+anno+",'"+user.getMunicipio()+"',"+progconserv+")");
         }

         public static void updateEspEndm(String id,Integer anno,Integer annoInicial,String idespcend,Integer progconserv,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET progconserv="+progconserv+", anno="+anno+" WHERE id='"+id+"' and id_espendm="+idespcend+" and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
         }

          public static Object[][] selectEspEndm(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select id_espendm, progconserv from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            System.out.print("select id_espendm, progconserv from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
         }

         public static void insertEjecProgProtec(String id,Integer anno,Integer idejecprgm,Double superf,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, id_ejecprgm, anno, municipio, superficie) VALUES ('"+id+"',"+idejecprgm+","+anno+",'"+user.getMunicipio()+"',"+superf+")");
         }

         public static void updateEjecProgProtec(String id,Integer anno,Integer annoInicial,String idejecprgm,Double superf,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET superficie="+superf+" , anno="+anno+" WHERE id='"+id+"' and id_ejecprgm="+idejecprgm+" and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
         }

         public static Object[][] selectProgProtec(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select id_ejecprgm, superficie from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            System.out.println("select id_ejecprgm, superficie from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
         }

          public static void insertProgEducExt(String id,Integer anno,String idejeceduc,Integer actv,Integer particp,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, id_ejeceduc, anno, municipio, cant_actv, cant_particp) VALUES ('"+id+"',"+idejeceduc+","+anno+",'"+user.getMunicipio()+"', "+actv+", "+particp+")");
         }

          public static void updateProgEducExt(String id,Integer anno,Integer annoInicial,String idejeceduc,Integer actv,Integer particp,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET cant_actv="+actv+", cant_particp="+particp+", anno="+annoInicial+" WHERE id='"+id+"' and id_ejeceduc="+idejeceduc+" and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
          }

          public static Object[][] selectProgEducExt(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select id_ejeceduc, cant_actv,cant_particp from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            System.out.println("select id_ejeceduc, cant_actv,cant_particp from "+tablename+" WHERE id='"+id+"' and municipio='"+user.getMunicipio()+"' and anno="+anno+"");
            return adapter.getAllRows();
         }

          public static void insertPresas(String id,Integer anno,Double longperm,Double anchorefaj,Double stzprot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, long_perm, anchorefaj, suptotzonprotec) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"', "+longperm+", "+anchorefaj+", "+stzprot+")");
         }

          public static void updatePresas(String id,Integer anno,Integer annoInicial,Double longperm,Double anchorefaj,Double stzprot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET  long_perm="+longperm+", anchorefaj="+anchorefaj+", suptotzonprotec="+stzprot+", anno="+anno+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
          }

          public static Object[][] selectPresas(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select long_perm, anchorefaj, suptotzonprotec from "+tablename+" WHERE id='"+id+"'and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
          }

          public static void insertMicroPresas(String id,Integer anno,Double longperm,Double anchorefaj,Double stzprot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, long_perm, anchorefaj, suptotzonprotec) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"', "+longperm+", "+anchorefaj+", "+stzprot+")");
         }

          public static void updateMicroPresas(String id,Integer anno,Integer annoInicial,Double longperm,Double anchorefaj,Double stzprot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET long_perm="+longperm+", anchorefaj="+anchorefaj+", suptotzonprotec="+stzprot+", anno="+anno+" WHERE id='"+id+"', anno="+annoInicial+", municipio='"+user.getMunicipio()+"'");
         }

          public static Object[][] selectMicroPresas(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select long_perm, anchorefaj, suptotzonprotec from "+tablename+" WHERE id='"+id+"' and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
          }

          public static void insertCorrFluvial(String id,Integer anno,Double longprinc,Double arfprinc,Double long1er,Double arf1er,Double long2do,Double arf2do,Double stzprot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, long_princ, anchorefaj_princ, long_1er, anchorefal_1er, long_2do, anchorefaj_2do, suptotzonprotec) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+longprinc+", "+arfprinc+", "+long1er+", "+arf1er+", "+long2do+", "+arf2do+", "+stzprot+")");
          System.out.println("INSERT INTO "+tablename+"(id, anno, municipio, long_princ, anchorefaj_princ, long_1er, anchorefal_1er, long_2do, anchorefaj_2do, suptotzonprotec) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+longprinc+", "+arfprinc+", "+long1er+", "+arf1er+", "+long2do+", "+arf2do+", "+stzprot+")");
          }

          public static void updateCorrFluvial(String id,Integer anno,Integer annoInicial,Double longprinc,Double arfprinc,Double long1er,Double arf1er,Double long2do,Double arf2do,Double stzprot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET long_princ="+longprinc+", anchorefaj_princ="+arfprinc+", long_1er="+long1er+", anchorefal_1er="+arf1er+", long_2do="+long2do+", anchorefaj_2do="+arf2do+", suptotzonprotec="+stzprot+", anno="+anno+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
          }

          public static Object[][] selectCorrFluvial(String id,Integer anno,String tablename) throws SQLException
          {
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select long_princ, anchorefaj_princ, long_1er, anchorefal_1er, long_2do, anchorefaj_2do, suptotzonprotec from "+tablename+" WHERE id='"+id+"'and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
          }
          public static void insertFajaCostera(String id,Integer anno,Double longcostprot,Double arfaj,Double longtotlin,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, long_cost_prot, anchorefaj, long_tot_lin) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+longcostprot+" , "+arfaj+", "+longtotlin+")");
         }

          public static void updateFajaCostera(String id,Integer anno,Integer annoInicial,Double longcostprot,Double arfaj,Double longtotlin,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET long_cost_prot="+longcostprot+", anchorefaj="+arfaj+", long_tot_lin="+longtotlin+", anno="+anno+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
          }

        public static Object[][] selectFajaCostera(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select long_cost_prot, anchorefaj, long_tot_lin from "+tablename+" WHERE id='"+id+"' and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
        }
        
          public static void insertActvMinera(String id,Integer anno,Double superf_cultv,Double superf_afect,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, superf_cultv, superf_afect) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+superf_cultv+","+superf_afect+")");
         }

          public static void updateActvMinera(String id,Integer anno,Integer annoInicial,Double superf_cultv,Double superf_afect,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET superf_cultv="+superf_cultv+", superf_afect="+superf_afect+", anno="+anno+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
          }

          public static Object[][] selectActvMinera(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select superf_cultv, superf_afect from "+tablename+" WHERE id='"+id+"' and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
          }

          public static void insertIndRendSost(String id,Integer anno,Double caebprod,Double cap_bprod ,Double caebprot , Double cap_bprot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, cortanuejec_bprod, cortanuperm_bprod, cortanuejec_bprot, cortanuperm_bprot) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+caebprod+","+cap_bprod+" ,"+caebprot+" , "+cap_bprot+")");
         }

          public static void updateIndRendSost(String id,Integer anno,Integer annoInicial,Double caebprod,Double cap_bprod ,Double caebprot , Double cap_bprot,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET cortanuejec_bprod="+caebprod+", cortanuperm_bprod="+caebprot+",cortanuejec_bprot="+cap_bprod+", cortanuperm_bprot="+cap_bprot+", anno="+anno+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
          }

          public static Object[][] selectIndRendSost(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select cortanuejec_bprod, cortanuperm_bprod, cortanuejec_bprot, cortanuperm_bprot from "+tablename+" WHERE id='"+id+"' and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
          }

          public static void insertPorcMadExtrBosqNat(String id,Integer anno,Double volmaprov,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, volm_aprov) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+volmaprov+")");
          }

          public static void updatePorcMadExtrBosqNat(String id,Integer anno,Integer annoInicial,Double volmaprov,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET volm_aprov="+volmaprov+", anno="+anno+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
          }

          public static Object[][] selectPorcMadExtrBosqNat(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select volm_aprov from "+tablename+" WHERE id='"+id+"' and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
          }

          public static void insertTalas(String id,Integer anno,Integer idtalas,Double supfftot, Double supftecninad,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, id_talas, anno, municipio, superftot, superftecninad) VALUES ('"+id+"',"+idtalas+","+anno+",'"+user.getMunicipio()+"',"+supfftot+", "+supftecninad+")");
         }

            public static void updateTalas(String id,Integer anno,Integer annoInicial,Integer idtalas,Double supfftot, Double supftecninad,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            String sql = "UPDATE "+tablename+" SET superftot="+supfftot+", superftecninad="+supftecninad+", anno="+anno+" WHERE id='"+id+"' and id_talas="+idtalas+" and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'";
            adapter.executeQuery(sql);
            System.out.println(sql);
    }
            
         public static void insertProdForNoMad(String id,Integer anno,Integer idtalas, Double cant, Double potencial,Double valor,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, id_pfnm, anno, municipio, cantidad, potencial, valor) VALUES ('"+id+"',"+idtalas+","+anno+",'"+user.getMunicipio()+"',"+cant+", "+potencial+", "+valor+")");
         }

          public static void updateProdForNoMad(String id,Integer anno, int annoInicial, Integer idpfnm, Double cant, Double potencial, Double valor, String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET cantidad="+cant+", potencial="+potencial+", valor="+valor+", anno="+anno+" WHERE id='"+id+"' and id_pfnm="+idpfnm+" and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");

         }

          public static void insertSistAgrosilvopast(String id,Integer anno,Integer cantfincaforst,Double supffincaforst,Double supfpotagrosilvp,Double supfmanagrosilvp,Double supfpotfincaforst, Double areabncaforst,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, cant_fincaforst, superf_fincaforst, superf_pot_agrosilvp, superf_man_agrosilvp, superf_pot_fincaforst, area_bn_fincaforst) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+cantfincaforst+", "+supffincaforst+", "+supfpotagrosilvp+", "+supfmanagrosilvp+", "+supfpotfincaforst+", "+areabncaforst+")");
         }

          public static void updateSistAgrosilvopast(String id,Integer anno,Integer annoInicial,Integer cantfincaforst,Double supffincaforst,Double supfpotagrosilvp,Double supfmanagrosilvp,Double supfpotfincaforst, Double areabncaforst, String tablename) throws SQLException
	      {
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET cant_fincaforst="+cantfincaforst+", superf_fincaforst="+supffincaforst+", superf_pot_agrosilvp="+supfpotagrosilvp+", superf_man_agrosilvp="+supfmanagrosilvp+", superf_pot_fincaforst="+supfpotfincaforst+",area_bn_fincaforst="+areabncaforst+", anno="+anno+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
          }

          public static Object[][] selectSistAgrosilvopast(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select cant_fincaforst, superf_fincaforst, superf_pot_agrosilvp, superf_man_agrosilvp, superf_pot_fincaforst, area_bn_fincaforst from "+tablename+" WHERE id='"+id+"' and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return  adapter.getAllRows();
          }

          public static void insertSalario(String id,Integer anno,Double saldevg,Double promtrabj,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, sal_devg, prom_trabj) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+saldevg+", "+promtrabj+")");
         }

          public static void updateSalario(String id,Integer anno,Integer annoInicial,Double saldevg,Double promtrabj,String tablename) throws SQLException
	     {
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET sal_devg="+saldevg+", prom_trabj="+promtrabj+", anno="+anno+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
         }

          public static Object[][] selectSalario(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select sal_devg, prom_trabj from "+tablename+" WHERE id='"+id+"' and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
          }

          public static void insertAccidente(String id,Integer anno,Integer accidparc,Integer accidmort,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, anno, municipio, accidentes_par, accidentes_mort) VALUES ('"+id+"',"+anno+",'"+user.getMunicipio()+"',"+accidparc+", "+accidmort+")");
         }

           public static void updateAccidente(String id,Integer anno,Integer annoInicial,Integer accidparc,Integer accidmort,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET accidentes_par="+accidparc+", accidentes_mort="+accidmort+",anno="+annoInicial+" WHERE id='"+id+"' and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
           }

           public static Object[][] selectAccidente(String id,Integer anno,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("select accidentes_par, accidentes_mort from "+tablename+" WHERE id='"+id+"' and anno="+anno+" and municipio='"+user.getMunicipio()+"'");
            return adapter.getAllRows();
           }

          public static void insertServicios(String id,Integer anno,Integer idserv,Integer cantidad,Integer planmanejo,String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("INSERT INTO "+tablename+"(id, id_serv, anno, municipio, cantidad, plan_manejo) VALUES ('"+id+"',"+idserv+","+anno+",'"+user.getMunicipio()+"',"+cantidad+", "+planmanejo+")");
         }

          public static void updateServicios(String id,Integer anno,Integer annoInicial,Integer idserv,Integer cantidad,Integer planmanejo,String tablename) throws SQLException
	    {
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            adapter.executeQuery("UPDATE "+tablename+" SET  cantidad="+cantidad+", plan_manejo="+planmanejo+", anno="+anno+" WHERE id='"+id+"' and id_serv="+idserv+" and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
        }

        public static void insertCatgOcupacional(String id, Integer anno, Integer idcatg, Integer cantmuj, Integer totalId, String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();

            adapter.executeQuery("INSERT INTO "+tablename+"(id, id_catg, total, anno, municipio, cant_muj) VALUES('"+id+"',"+idcatg+","+totalId+","+anno+",'"+user.getMunicipio()+"',"+cantmuj+")");
         }

        public static void updateCatgOcupacional(String id,Integer anno,Integer annoInicial,Integer idcatg,Integer cantmuj, String tablename) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            Manage manager = Manage.getInstance();
            User user = manager.getUser();

//            updateCatgOcupacionalTOTAL(tablename, totalid, total);
            adapter.executeQuery("UPDATE "+tablename+" SET cant_muj="+cantmuj+", anno="+anno+" WHERE id='"+id+"' and id_catg="+idcatg+" and anno="+annoInicial+" and municipio='"+user.getMunicipio()+"'");
        }
           
        public static void delete(String id, String tablename,String mcpio,Integer anno,String where) throws SQLException
	{        
            JDBCAdapter adapter = new JDBCAdapter(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
            adapter.executeQuery("delete from "+tablename+" WHERE id='"+id+"' and municipio='"+mcpio+"' and anno="+anno+""+where);
            System.out.println("delete from "+tablename+" WHERE id='"+id+"' and municipio='"+mcpio+"' and anno="+anno+""+where);
        }

        /**Retorna el id insertado*/
        public static int insertCatgOcupacionalTOTAL(String tablename, int value, String tableid, Integer anno) throws SQLException
	{
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            
            int id = -1;
            
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);

            String sql = "select distinct total from "+tablename+" where id='"+tableid+"' and municipio='"+user.getMunicipio()+"' and anno="+anno;
            adapter.executeQuery(sql);
            if(!adapter.isEmpty())
            {
             id = Integer.parseInt(adapter.getValueAt(0,0).toString());
             updateCatgOcupacionalTOTAL(tablename, id, value);
            }
            else
            {
                adapter.executeQuery("INSERT INTO "+tablename+"_total(total) VALUES("+value+")");
                adapter.executeQuery("select max(id) from "+tablename+"_total");
                if(!adapter.isEmpty())
                 id = Integer.parseInt(adapter.getValueAt(0,0).toString());
            }

            return  id;
         }

         public static void updateCatgOcupacionalTOTAL(String tablename, int id, int value) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);

            adapter.executeQuery("UPDATE "+tablename+"_total SET total="+value+" WHERE id="+id+"");
        }

        public static int getCatgOcupacionalTOTAL(int id, String tableName) throws SQLException
	{
            int total = -1;
            
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);

            adapter.executeQuery("select total from "+tableName+" WHERE id="+id);
            if(!adapter.isEmpty())
             total = Integer.parseInt(adapter.getValueAt(0,0).toString());

            return total;
        }

        public static Object[][] getIdentity(String idmcpio, String tablename) throws SQLException
	{
            Object[][] obj;
            JDBCAdapter adapter = new JDBCAdapter(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
            adapter.executeQuery("select id from "+tablename+" WHERE id_mcpio='"+idmcpio+"'");
            obj= adapter.getAllRows();
            adapter.close();
            return obj;
        }

        public static String getIdmcpio(String id, String tablename) throws SQLException
	{
            String idmcpio="";
            JDBCAdapter adapter = new JDBCAdapter(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
            adapter.executeQuery("select id_mcpio from "+tablename+" WHERE id='"+id+"'");
            idmcpio=adapter.getValueAt(0, 0).toString();
            adapter.close();
            return idmcpio;
        }

        public static Integer getIdentidad(String name,String tablename) throws SQLException
        {
            Integer id;
            JDBCAdapter adapter = new JDBCAdapter(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
            adapter.executeQuery("select id from "+tablename+" WHERE nombre='"+name+"'");
            id=java.lang.Integer.parseInt(adapter.getValueAt(0, 0).toString());
            adapter.close();
            return id;
        }

        public static String getIde(String name,String tablename) throws SQLException
        {
            String ide;
            JDBCAdapter adapter = new JDBCAdapter(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
            adapter.executeQuery("select id from "+tablename+" WHERE nombre='"+name+"'");
            ide=adapter.getValueAt(0, 0).toString();
            adapter.close();
            return ide;
        }

        public static String getnameIdentidad(String id,String tablename) throws SQLException
        {
        if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            adapter.executeQuery("select nombre from "+tablename+" WHERE id='"+id+"'");
            return adapter.getValueAt(0, 0).toString();
        }

        public static Object[][] getComboboxEspecie(String tablename,String tag,String id,Integer anno) throws SQLException
        {
        if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            Manage manager = Manage.getInstance();
            User user = manager.getUser();
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            if(tag.equals("insert"))
            {
                adapter.executeQuery("select id,nombre from especie");
            }
            else if(tag.equals("modify"))
            {
                adapter.executeQuery("select especie.id,especie.nombre from "+tablename+",especie where "+tablename+".especie=especie.id and "+tablename+".id='"+id+"' and "+tablename+".municipio='"+user.getMunicipio()+"' and "+tablename+".anno="+anno+" order by "+tablename+".especie");
            }
            System.out.println("select especie.id,especie.nombre from "+tablename+",especie where "+tablename+".especie=especie.id and "+tablename+".id='"+id+"' and "+tablename+".municipio='"+user.getMunicipio()+"' and "+tablename+".anno="+anno+" order by "+tablename+".especie");
             System.out.println(adapter.getRowCount());
            return adapter.getAllRows();
        }

        public static Object[][] selectNomenclator(String tablename, String where) throws SQLException
	{
            if(!CollectionConnection.hasConnection(ClassParams.HOST))
            {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
            }
            JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
            adapter.executeQuery("select id,nombre from "+tablename+" "+where+"");
            return adapter.getAllRows();
        }
     

}

       
