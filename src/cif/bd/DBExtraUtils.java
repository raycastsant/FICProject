package cif.bd;

import cif.objects.C_4_3_Object;
import cif.objects.C_4_4_Object;
import cif.objects.C_5_4_Object;
import cif.objects.C_5_5_Object;
import cif.objects.Tala_Object;
import cif.objects.TipoCategoria_Object;
import cif.objects.TipoProducto_Object;
import cif.objects.TipoServicio_Object;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raisel
 */
public class DBExtraUtils
{
  //----------------------------------------------------------------------------------------------------------------
    public static Tala_Object [] getAllTalas()
    {
        Tala_Object []talas = null;
        String query = "select id, nombre from c4_3_talas where active = TRUE order by nombre";
        
        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
            talas = new Tala_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             int id = Integer.parseInt(db.getValueAt(i,0).toString());
             String nombre = db.getValueAt(i,1).toString();
             talas[i] = new Tala_Object(id, nombre);
            }
        }

        return talas;
    }

     public static Tala_Object [] getTiposTalas(String ids)
    {
        Tala_Object []talas = null;
        String query = "select id, nombre from c4_3_talas where id in ("+ids+") and active = TRUE order by id";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
            talas = new Tala_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             int id = Integer.parseInt(db.getValueAt(i,0).toString());
             String nombre = db.getValueAt(i,1).toString();
             talas[i] = new Tala_Object(id, nombre);
            }
        }

        return talas;
    }

    public static Tala_Object getTipoTala(int id)
    {
        Tala_Object tala = null;
        String query = "select nombre from c4_3_talas where id="+id+" and active = TRUE order by nombre";
        System.out.println(query);

        JDBCAdapter db = new JDBCAdapter(CollectionConnection.getConnection(ClassParams.HOST).connection);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
             String nombre = db.getValueAt(0,0).toString();
             tala = new Tala_Object(id, nombre);
        }

        return tala;
    }

    public static C_4_3_Object [] getC_4_3_Objects(String tablename, int anno, String munId, String idEntidad)
    {
        C_4_3_Object []talas = null;
        String query = "select id, id_talas, superftot, superftecninad from "+tablename+" " +
                       "where id='"+idEntidad+"' and anno="+anno+" and municipio='"+munId+"' order by id_talas";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
//        JDBCAdapter dbaux = new JDBCAdapter(db.connection);
        db.executeQuery(query);
        System.out.println(query);

        if(!db.isEmpty())
        {
            String idTiposTalas = "";
            for(int i=0; i<db.getRowCount(); i++)
             idTiposTalas += db.getValueAt(i,1).toString()+",";

            idTiposTalas = idTiposTalas.substring(0, idTiposTalas.length()-1);
            Tala_Object []tipostalas = getTiposTalas(idTiposTalas);

            db.executeQuery(query);
            talas = new C_4_3_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             String id = db.getValueAt(i,0).toString();
             double suptot = Double.parseDouble(db.getValueAt(i,2).toString());
             double suptec = Double.parseDouble(db.getValueAt(i,3).toString());
             talas[i] = new C_4_3_Object(id, tipostalas[i], suptot, suptec);
            }
        }

        return talas;
    }
  //----------------------------------------------------------------------------------------------------------------
    public static TipoProducto_Object [] getTiposProductosFNM()
    {
        TipoProducto_Object []pfnm = null;
        String query = "select id, nombre from c4_4_prodfornomad where active = TRUE order by nombre";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
            pfnm = new TipoProducto_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             int id = Integer.parseInt(db.getValueAt(i,0).toString());
             String nombre = db.getValueAt(i,1).toString();
             pfnm[i] = new TipoProducto_Object(id, nombre);
            }
        }

        return pfnm;
    }

    public static C_4_4_Object [] getC_4_4_Objects(String tablename, int anno, String munId, String idEntidad)
    {
        C_4_4_Object []lista = null;
        String query = "select id, id_pfnm, cantidad, potencial, valor from "+tablename+" " +
                       "where id='"+idEntidad+"' and anno="+anno+" and municipio='"+munId+"' order by id_pfnm";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);

        db.executeQuery(query);

        if(!db.isEmpty())
        {
            String idPFNMbles = "";
            for(int i=0; i<db.getRowCount(); i++)
             idPFNMbles += db.getValueAt(i,1).toString()+",";

            idPFNMbles = idPFNMbles.substring(0, idPFNMbles.length()-1);
            TipoProducto_Object []tiposprods = getTiposProductosFNM(idPFNMbles);

            db.executeQuery(query);
            lista = new C_4_4_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             String id = db.getValueAt(i,0).toString();
             double cant = Double.parseDouble(db.getValueAt(i,2).toString());
             double pot = Double.parseDouble(db.getValueAt(i,3).toString());
             double valor = Double.parseDouble(db.getValueAt(i,4).toString());
             lista[i] = new C_4_4_Object(id, tiposprods[i], cant, pot, valor);
            }
        }

        return lista;
    }

    public static TipoProducto_Object [] getTiposProductosFNM(String ids)
    {
        TipoProducto_Object []lista = null;
        String query = "select id, nombre from c4_4_prodfornomad where id in ("+ids+") and active = TRUE order by id";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
            lista = new TipoProducto_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             int id = Integer.parseInt(db.getValueAt(i,0).toString());
             String nombre = db.getValueAt(i,1).toString();
             lista[i] = new TipoProducto_Object(id, nombre);
            }
        }

        return lista;
    }
//----------------------------------------------------------------------------------------------------------------
    public static TipoCategoria_Object [] getTiposCategoriasOcupacionales()
    {
        TipoCategoria_Object []lista = null;
        String query = "select id, nombre from c5_5_catgocupacional where active = TRUE order by nombre";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
            lista = new TipoCategoria_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             int id = Integer.parseInt(db.getValueAt(i,0).toString());
             String nombre = db.getValueAt(i,1).toString();
             lista[i] = new TipoCategoria_Object(id, nombre);
            }
        }

        return lista;
    }

//    public static C_4_4_Object [] getC_5_4_Objects(String tablename, int anno, String munId, String idEntidad)
//    {
//        C_4_4_Object []lista = null;
//        String query = "select id, id_serv, cantidad, plan_manejo from "+tablename+" " +
//                       "where id='"+idEntidad+"' and anno="+anno+" and municipio='"+munId+"' order by id_serv";
//
//        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
//
//        db.executeQuery(query);
//
//        if(!db.isEmpty())
//        {
//            String idServicios = "";
//            for(int i=0; i<db.getRowCount(); i++)
//             idServicios += db.getValueAt(i,1).toString()+",";
//
//            idServicios = idServicios.substring(0, idServicios.length()-1);
//            TipoServicio_Object []tiposServicios = getTiposProductosFNM(idServicios);
//
//            db.executeQuery(query);
//            lista = new C_4_4_Object[db.getRowCount()];
//            for(int i=0; i<db.getRowCount(); i++)
//            {
//             String id = db.getValueAt(i,0).toString();
//             double cant = Double.parseDouble(db.getValueAt(i,2).toString());
//             double pot = Double.parseDouble(db.getValueAt(i,3).toString());
//             double valor = Double.parseDouble(db.getValueAt(i,4).toString());
//             lista[i] = new C_4_4_Object(id, tiposprods[i], cant, pot, valor);
//            }
//        }
//
//        return lista;
//    }
//
//    public static TipoProducto_Object [] getTiposProductosFNM(String ids)
//    {
//        TipoProducto_Object []lista = null;
//        String query = "select id, nombre from c4_4_prodfornomad where id in ("+ids+") order by id";
//
//        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
//        db.executeQuery(query);
//
//        if(!db.isEmpty())
//        {
//            lista = new TipoProducto_Object[db.getRowCount()];
//            for(int i=0; i<db.getRowCount(); i++)
//            {
//             int id = Integer.parseInt(db.getValueAt(i,0).toString());
//             String nombre = db.getValueAt(i,1).toString();
//             lista[i] = new TipoProducto_Object(id, nombre);
//            }
//        }
//
//        return lista;
//    }
      //----------------------------------------------------------------------------------------------------------------
    public static TipoServicio_Object [] getTiposServicios()
    {
        TipoServicio_Object []lista = null;
        String query = "select id, nombre from c5_4_servicios where active = TRUE order by nombre";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
            lista = new TipoServicio_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             int id = Integer.parseInt(db.getValueAt(i,0).toString());
             String nombre = db.getValueAt(i,1).toString();
             lista[i] = new TipoServicio_Object(id, nombre);
            }
        }

        return lista;
    }

    public static C_5_4_Object [] getC_5_4_Objects(String tablename, int anno, String munId, String idEntidad)
    {
        C_5_4_Object []lista = null;
        String query = "select id, id_serv, cantidad, plan_manejo from "+tablename+" " +
                       "where id='"+idEntidad+"' and anno="+anno+" and municipio='"+munId+"' order by id_serv";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);

        db.executeQuery(query);

        if(!db.isEmpty())
        {
            String idServicios = "";
            for(int i=0; i<db.getRowCount(); i++)
             idServicios += db.getValueAt(i,1).toString()+",";

            idServicios = idServicios.substring(0, idServicios.length()-1);
            TipoServicio_Object []tiposServicios = getTiposServicios(idServicios);

            db.executeQuery(query);
            lista = new C_5_4_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             String id = db.getValueAt(i,0).toString();
             int cant = Integer.parseInt(db.getValueAt(i,2).toString());
             int planManej = Integer.parseInt(db.getValueAt(i,3).toString());
             lista[i] = new C_5_4_Object(id, cant, planManej, tiposServicios[i]);
            }
        }

        return lista;
    }

    public static TipoServicio_Object [] getTiposServicios(String ids)
    {
        TipoServicio_Object []lista = null;
        String query = "select id, nombre from c5_4_servicios where id in ("+ids+") and active = TRUE order by id";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
            lista = new TipoServicio_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             int id = Integer.parseInt(db.getValueAt(i,0).toString());
             String nombre = db.getValueAt(i,1).toString();
             lista[i] = new TipoServicio_Object(id, nombre);
            }
        }

        return lista;
    }

    //---------------------------------------------------------------------------------------
    public static C_5_5_Object [] getC_5_5_Objects(String tablename, int anno, String munId, String idEntidad)
    {
        C_5_5_Object []lista = null;
        String query = "select "+tablename+".id, id_catg, cant_muj, "+tablename+".total as idtot, "+tablename+"_total.total as totvalue " +
                       "from "+tablename+" inner join "+tablename+"_total on "+tablename+".total="+tablename+"_total.id " +
                       "where "+tablename+".id='"+idEntidad+"' and anno="+anno+" and municipio='"+munId+"' order by id_catg";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);

        db.executeQuery(query);

        if(!db.isEmpty())
        {
            String idCategorias = "";
            for(int i=0; i<db.getRowCount(); i++)
             idCategorias += db.getValueAt(i,1).toString()+",";

            idCategorias = idCategorias.substring(0, idCategorias.length()-1);
            TipoCategoria_Object []categorias = getCategoriasOcupacionales(idCategorias);

            db.executeQuery(query);
            lista = new C_5_5_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             String id = db.getValueAt(i,0).toString();
             int cantmuj = Integer.parseInt(db.getValueAt(i,2).toString());
             int totalId = Integer.parseInt(db.getValueAt(i,3).toString());
             int totalValue = Integer.parseInt(db.getValueAt(i,4).toString());

             lista[i] = new C_5_5_Object(id, cantmuj, totalValue, totalId, categorias[i]);
            }
        }

        return lista;
    }

    public static TipoCategoria_Object [] getCategoriasOcupacionales(String ids)
    {
        TipoCategoria_Object []lista = null;
        String query = "select id, nombre from c5_5_catgocupacional where id in ("+ids+") and active = TRUE order by id";

        JDBCAdapter db = CollectionConnection.getConnection(ClassParams.HOST);
        db.executeQuery(query);

        if(!db.isEmpty())
        {
            lista = new TipoCategoria_Object[db.getRowCount()];
            for(int i=0; i<db.getRowCount(); i++)
            {
             int id = Integer.parseInt(db.getValueAt(i,0).toString());
             String nombre = db.getValueAt(i,1).toString();
             lista[i] = new TipoCategoria_Object(id, nombre);
            }
        }

        return lista;
    }
}
