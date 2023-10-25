/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.bd;

import cif.bd.BDUtilities;
import cif.bd.ClassParams;
import cif.bd.CollectionConnection;
import cif.bd.JDBCAdapter;
import cif.bd.TableSorter;
import cif.manage.User;
import cif.manage.UserTypes;
import javax.swing.JTable;

/**
 *
 * @author Pedro
 */
public class DBQueries
{
    public static JTable getAllUsers()
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select usuarios.login as Usuario, tipo_usuario.nombre as Entidad, municipios.nombre as Municipio, provincias.nombre as Provincia" +
                ", usuarios.nombre as Nombre from ((usuarios inner join tipo_usuario on(usuarios.tipo = tipo_usuario.id)) inner join municipios on(usuarios.municipio = municipios.id))" +
                " inner join provincias on(municipios.provincia = provincias.id)");
        adapter.executeQuery(query);
        return new JTable(new TableSorter(adapter));
    }

    public static String getEFIbyLogin(String login)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id_efi from usuario_efi where login='%s'",login);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getUSbyLogin(String login)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id_us from usuario_us where login='%s'",login);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getAPbyLogin(String login)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id_ap from usuario_ap where login='%s'",login);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static int getUserTypeId(String type)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id from tipo_usuario where nombre='%s'",type);
        adapter.executeQuery(query);
        return Integer.parseInt(adapter.getValueAt(0, 0).toString());
    }

    public static void DeleteUser(String login)
    {
        String query;
     //   boolean delete = false;
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
    /*    query = String.format("select tipo from usuarios where login='%s'",login);
        adapter.executeQuery(query);
      //  System.out.println(adapter.getValueAt(0, 0).toString());
        int tipo = (Integer)adapter.getValueAt(0, 0);
        UserTypes type = UserTypes.values()[tipo-1];
        if(type == UserTypes.Area_Protegida)
        {
            table = "usuario_ap";
            delete = true;
        }
        else if(type == UserTypes.EFI)
        {
            table = "usuario_efi";
            delete = true;
        }
        else if(type == UserTypes.Unidad_Silvicola)
        {
            table = "usuario_us";
            delete = true;
        }
        if(delete)
        {
            query = String.format("delete from %s where login='%s'",table,login);
            adapter.executeQuery(query);
        }*/
        query = String.format("delete from usuarios where login='%s'",login);
        adapter.executeQuery(query);
    }

    public static boolean ExistUS(String name,String efi, String munic)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select usilvicola.id from usilvicola inner join usilv_mcpio on(usilvicola.id=usilv_mcpio.id) where usilv_mcpio.id_mcpio='%s' and usilvicola.efi='%s' and usilvicola.nombre='%s'",munic,efi,name);
        adapter.executeQuery(query);
        if(adapter.getRowCount() != 0)
        {
            return true;
        }
        return false;
    }

    public static void InsertUS(String name,String efi, String munic,String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String id = getNextID(UserTypes.Unidad_Silvicola, munic, prov);
        String query = String.format("insert into usilvicola values('%s','%s','%s')",id,name,efi);
        adapter.executeQuery(query);
        query = String.format("insert into usilv_mcpio values('%s','%s')", munic, id);
        adapter.executeQuery(query);
    }

    public static String getNextID(UserTypes type,String munic, String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String column = null;
        if(type == UserTypes.Unidad_Silvicola)
        {
            column = "us";
        }
        else if(type == UserTypes.Area_Protegida)
        {
            column = "ap";
        }
        else if(type == UserTypes.EFI)
        {
            column = "efi";
        }
        else if(type == null)
        {
            column = "otros";
        }
        String query = String.format("select %s from tipo_id ",column);
        adapter.executeQuery(query);
        int id = (Integer)adapter.getValueAt(0, 0);
        query = String.format("update tipo_id set %s = %d", column, id+1);
        adapter.executeQuery(query);
        if(munic == null)
        {
            munic = "000";
        }
        String real_id="";
        if (id<10)
        {
            real_id="00"+id;
        }
        if ((id>=10)&&(id<100))
        {
            real_id="0"+id;
        }
        String result = prov+munic+real_id;
        return result;
    }
    
    public static JTable getUS(User user)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query;
        if(user.getType() == UserTypes.Municipal)
        {
            String munic = user.getMunicipio();
            query = String.format("select usilvicola.nombre as nombre, efi.nombre as efi, municipios.nombre as municipio from (usilvicola inner join usilv_mcpio " +
                    "on(usilvicola.id = usilv_mcpio.id)) inner join efi on(efi.id=usilvicola.efi), municipios where usilv_mcpio.id_mcpio = '%s' and municipios.id='%s'", munic,munic);
            adapter.executeQuery(query);
        }
        else if(user.getType() == UserTypes.Provincial)
        {
            String prov = user.getProvincia();
            query = String.format("select usilvicola.nombre as nombre, efi.nombre as efi,municipios.nombre as municipio from (((usilvicola inner join usilv_mcpio "+
                    "on(usilvicola.id = usilv_mcpio.id)) inner join efi on(efi.id=usilvicola.efi)) inner join municipios on(usilv_mcpio.id_mcpio=municipios.id))"+
                    "inner join provincias on(municipios.provincia = provincias.id)"+
                    "where provincias.id = '%s'", prov);
            adapter.executeQuery(query);
        }
        else
        {
            query = String.format("select usilvicola.nombre as nombre, efi.nombre as efi, municipios.nombre as municipio from ((usilvicola inner join efi on(usilvicola.efi=efi.id)) inner join usilv_mcpio" +
                    " on(usilv_mcpio.id = usilvicola.id)) inner join municipios on(municipios.id = usilv_mcpio.id_mcpio)");
            adapter.executeQuery(query);
        }
        return new JTable(new TableSorter(adapter));
    }

    public static void UpdateUS(String name,String efi,String munic,String id)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("update usilvicola set nombre='%s', efi='%s' where usilvicola.id='%s'",name,efi,id);
        adapter.executeQuery(query);
        query = String.format("update usilv_mcpio set id_mcpio='%s' where id='%s'", munic, id);
        adapter.executeQuery(query);
    }

    public static void DeleteUS(String name, String efi_name)
    {
        //munic es el nombre y nesecito el id
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String efi_id = BDUtilities.getEfiId(efi_name);
        String query = String.format("delete from usilvicola where nombre='%s' and efi='%s'", name, efi_id);
        adapter.executeQuery(query);
    /*    String query = String.format("select id from usilvicola where nombre='%s' and efi='%s'", name, efi_id);
        adapter.executeQuery(query);
        String us_id = adapter.getValueAt(0, 0).toString();
        query = String.format("delete from usuario_us where id_us='%s'", us_id);
        adapter.executeQuery(query);
        query = String.format("delete from usuarios where id_us='%s'", us_id);
        adapter.executeQuery(query);
        query = String.format("delete from usilv_mcpio where id='%s'", us_id);
        adapter.executeQuery(query);
        query = String.format("delete from usilvicola where id='%s'", us_id);
        adapter.executeQuery(query);*/
    }

    public static JTable getAP(User user)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query;
        if(user.getType() == UserTypes.Municipal)
        {
            String munic = user.getMunicipio();
            query = String.format("select area_protegida.nombre as nombre, area_protegida.tenente as tenente, significacion.nombre as significación" +
                    " , si_no.nombre as \"Plan de Manejo\" , municipios.nombre as municipio from (area_protegida inner join aprotg_mcpio " +
                    "on(area_protegida.id = aprotg_mcpio.id)) inner join municipios on(aprotg_mcpio.id_mcpio=municipios.id) inner join significacion" +
                    " on(area_protegida.significacion=significacion.id) inner join si_no on(area_protegida.proyecto_planmanejo=si_no.id) where aprotg_mcpio.id_mcpio = '%s'", munic);
            adapter.executeQuery(query);
        }
        else if(user.getType() == UserTypes.Provincial)
        {
            String prov = user.getProvincia();
            query = String.format("select area_protegida.nombre as nombre, area_protegida.tenente as tenente, significacion.nombre as significación" +
                    " , si_no.nombre as \"Plan de Manejo\" , municipios.nombre as municipio from ((((area_protegida inner join aprotg_mcpio " +
                    "on(area_protegida.id = aprotg_mcpio.id)) inner join municipios on(aprotg_mcpio.id_mcpio=municipios.id))) inner join significacion" +
                    " on(area_protegida.significacion=significacion.id)) inner join provincias on(municipios.provincia = provincias.id) inner join si_no on(area_protegida.proyecto_planmanejo=si_no.id)"+
                    "where provincias.id = '%s'", prov);
            adapter.executeQuery(query);
        }
        else
        {
            query = String.format("select area_protegida.nombre as nombre, area_protegida.tenente as tenente, significacion.nombre as significación" +
                    " , si_no.nombre as \"Plan de Manejo\" , municipios.nombre as municipio from (((area_protegida inner join aprotg_mcpio " +
                    "on(area_protegida.id = aprotg_mcpio.id)) inner join municipios on(aprotg_mcpio.id_mcpio=municipios.id))) inner join significacion" +
                    " on(area_protegida.significacion=significacion.id) inner join si_no on(area_protegida.proyecto_planmanejo=si_no.id)");
            adapter.executeQuery(query);
        }
        return new JTable(new TableSorter(adapter));
    }

    public static boolean ExistAP(String name, String munic, String tenente, String signif)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select area_protegida.id from area_protegida inner join aprotg_mcpio on(area_protegida.id=aprotg_mcpio.id)" +
                " where aprotg_mcpio.id_mcpio='%s' and area_protegida.nombre='%s' and area_protegida.tenente='%s' and area_protegida.significacion=%s",munic,name,tenente,signif);
        adapter.executeQuery(query);
        if(adapter.getRowCount() != 0)
        {
            return true;
        }
        return false;
    }

    public static void InsertAP(String name,String tenente, String munic,String prov,String signif, int plan_manejo)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String id = getNextID(UserTypes.Area_Protegida, munic, prov);
        String query = String.format("insert into area_protegida values('%s','%s',%s,'%s',%d)",id,tenente,signif,name,plan_manejo);
        adapter.executeQuery(query);
        query = String.format("insert into aprotg_mcpio values('%s','%s')", munic, id);
        adapter.executeQuery(query);
    }

    public static void UpdateAP(String name,String tenente, String munic,String signif, int plan_manejo,String id)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("update area_protegida set nombre='%s', tenente='%s', significacion=%s, proyecto_planmanejo=%d where id='%s'",name,tenente,signif,plan_manejo,id);
        adapter.executeQuery(query);
        query = String.format("update aprotg_mcpio set id_mcpio='%s' where id='%s'", munic, id);
        adapter.executeQuery(query);
    }

    public static void DeleteAP(String id)
    {
        //munic es el nombre y nesecito el id
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("delete from area_protegida where id='%s'", id);
        adapter.executeQuery(query);
    }

    public static boolean ExistEFI(String name, String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id from efi" +
                " where prov='%s' and nombre='%s'",prov,name);
        adapter.executeQuery(query);
        if(adapter.getRowCount() != 0)
        {
            return true;
        }
        return false;
    }

    public static void InsertEFI(String name,String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String id = getNextID(UserTypes.EFI, null, prov);
        String query = String.format("insert into efi values('%s','%s','%s')",id,name,prov);
        adapter.executeQuery(query);
     //   query = String.format("insert into efi_mcpio values('%s','%s')", id, prov);
     //   adapter.executeQuery(query);
    }

    public static void UpdateEFI(String name,String prov,String id)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("update efi set nombre='%s', prov='%s' where id='%s'",name,prov,id);
        adapter.executeQuery(query);
     //   query = String.format("update efi_mcpio set id_prov='%s' where id='%s'", prov, id);
     //   adapter.executeQuery(query);
    }

    public static void DeleteEFI(String name, String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("delete from efi where name='%s' and prov='%s'", name, prov);
        adapter.executeQuery(query);
    }

    public static JTable getEFI(User user)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query;
        if(user.getType() == UserTypes.Provincial)
        {
            String prov = user.getProvincia();
            query = String.format("select efi.nombre, provincias.nombre as provincia from efi inner join provincias on(efi.prov=provincias.id) where provincias.id = '%s'", prov);
       //     query = String.format("select efi.nombre as nombre, provincias.nombre as provincia from (efi inner join efi_mcpio " +
       //             "on(efi.id = efi_mcpio.id)) inner join provincias on(efi_mcpio.id_prov=provincias.id) where provincias.id = '%s'", prov);
            adapter.executeQuery(query);
        }
        else
        {
            query = String.format("select efi.nombre, provincias.nombre as provincia from efi inner join provincias on(efi.prov=provincias.id)");
          //  query = String.format("select efi.nombre as nombre, provincias.nombre as provincia from (efi inner join efi_mcpio " +
          //          "on(efi.id = efi_mcpio.id)) inner join provincias on(efi_mcpio.id_prov=provincias.id)");
            adapter.executeQuery(query);
        }
        return new JTable(new TableSorter(adapter));
    }

    public static void InsertMunic(String name, String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select max(id) from municipios");
        adapter.executeQuery(query);
        int id = Integer.parseInt(adapter.getValueAt(0, 0).toString());
        id++;
        String munic_id;
        if(id>=0 && id<10)
        {
            munic_id = "00"+id;
        }
        else if(id>=10 && id<100)
        {
            munic_id = "0"+id;
        }
        else
        {
            munic_id = String.valueOf(id);
        }
        query = String.format("insert into municipios values('%s','%s','%s')",munic_id,name,prov);
        adapter.executeQuery(query);
    }

    public static boolean ExistMunic(String name, String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id from municipios where nombre='%s' and provincia='%s'",name,prov);
        adapter.executeQuery(query);
        if(adapter.getRowCount()>0)
        {
            return true;
        }
        return false;
    }

    public static void UpdateMunic(String id,String name, String prov,String active)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("update municipios set nombre='%s', provincia='%s', active=%s where id='%s'",name,prov,active,id);
        adapter.executeQuery(query);
    }

    public static String getMunicId(String name, String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id from municipios where nombre='%s' and provincia='%s'",name,prov);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getMunicIdNProv(String name, String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select municipios.id from municipios inner join provincias on(municipios.provincia=provincias.id) where municipios.nombre='%s' and provincias.nombre='%s'",name,prov);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static JTable getAllMunic()
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select municipios.nombre,provincias.nombre as Provincia from municipios inner join provincias on(municipios.provincia=provincias.id) where municipios.active=true");
        adapter.executeQuery(query);
        return new JTable(new TableSorter(adapter));
    }

    public static String getNomencId(String table,String name)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id from %s where nombre='%s'",table,name);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static boolean ExistNomenc(String table,String name)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id from %s where nombre='%s'",table,name);
        adapter.executeQuery(query);
        if(adapter.getRowCount()>0)
        {
            return true;
        }
        return false;
    }

    public static void InsertProv(String name)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select max(id) from provincias");
        adapter.executeQuery(query);
        int id = Integer.parseInt(adapter.getValueAt(0, 0).toString());
        id++;
        String prov_id;
        if(id>=0 && id<10)
        {
            prov_id = "0"+id;
        }
        else
        {
            prov_id = String.valueOf(id);
        }
        query = String.format("insert into provincias values('%s','%s')",prov_id,name);
        adapter.executeQuery(query);
    }

    public static void UpdateNomenc(String table, String id,String name, String active)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("update %s set nombre='%s', active=%s where id='%s'",table,name,active,id);
        adapter.executeQuery(query);
    }

    public static JTable getAllNomenc(String table)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select nombre from %s where active=true",table);
        adapter.executeQuery(query);
        return new JTable(new TableSorter(adapter));
    }

    public static void InsertNomenc(String table,String nombre)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("insert into %s(nombre) values('%s')",table,nombre);
        adapter.executeQuery(query);
    }

    public static JTable getAllEspecies()
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select nombre, precio from especie where active=true");
        adapter.executeQuery(query);
        return new JTable(new TableSorter(adapter));
    }

    public static void InsertEspecie(String nombre, double price)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("insert into especie(nombre,precio) values('%s',"+price+")",nombre);
        adapter.executeQuery(query);
    }

    public static void UpdateEspecie(String id,String name, double price, String active)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("update especie set nombre='%s', precio="+price+", active=%s where id='%s'",name,active,id);
        adapter.executeQuery(query);
    }
}
