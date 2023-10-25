package cif.bd;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.FieldDescriptor;
import cif.manage.Manage;
import cif.manage.User;
import cif.manage.UserTypes;
import com.twmacinta.util.MD5;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro
 */
public class BDUtilities
{
    public static User getUser(String login, String password) throws SQLException
    {
        if(login.isEmpty() && password.isEmpty())
        {
            User user = new User(login,UserTypes.values().length,"","");
            return user;
        }
        if(!CollectionConnection.hasConnection(ClassParams.HOST))
        {
                DataAccess da = new DataAccess(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
                CollectionConnection.SetConnection(da);
        }
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String passw = new MD5(password).asHex();
        String query = String.format("select tipo, provincias.id, usuarios.municipio from (usuarios inner join municipios on(usuarios.municipio=municipios.id)) inner join provincias on(municipios.provincia = provincias.id) where login='%s' and contrasenna='%s'",login,passw);
        adapter.executeQuery(query);
        if(adapter.getRowCount()==0)
        {
            return null;
        }
        User user = new User(login,(Integer)adapter.getValueAt(0, 0),adapter.getValueAt(0, 2).toString(),adapter.getValueAt(0, 1).toString());
        return user;
    }

    public static boolean existUser(String login) throws SQLException
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select login from usuarios where login='%s'",login);
        adapter.executeQuery(query);
        if(adapter.getRowCount()==0)
        {
            return false;
        }
        return true;
    }

    public static String getEfiName(String id)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select nombre from efi where id='%s'",id);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getEfiId(String name)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id from efi where nombre='%s'",name);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String[] getUSData(String id)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select nombre, efi from usilvicola where id='%s'",id);
        adapter.executeQuery(query);
        String[] result = new String[]{adapter.getRow(0)[0].toString(),adapter.getRow(0)[1].toString()};
        return result;
    }

    public static String getAPName(String id)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select nombre from area_protegida where id='%s'",id);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getIdEntidad(String login, UserTypes type)
    {
        String query;
        if(type == UserTypes.Area_Protegida)
        {
            query = String.format("select id_ap from usuario_ap where login='%s'",login);
        }
        else if(type == UserTypes.EFI)
        {
            query = String.format("select id_efi from usuario_efi where login='%s'",login);
        }
        else if(type == UserTypes.Unidad_Silvicola)
        {
            query = String.format("select id_us from usuario_us where login='%s'",login);
        }
        else
        {
            return null;
        }
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static Object[][] getUserType()
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        UserTypes type = Manage.getInstance().getUser().getType();
        int tipo=0;
        for(int i=0; i<UserTypes.values().length; i++)
        {
            if(type == UserTypes.values()[i])
            {
                tipo = i+1;
                if(type == UserTypes.Nacional)
                {
                    tipo--;
                }
                break;
            }
        }
        String query = String.format("select * from tipo_usuario where active=TRUE and id>%d",tipo);
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getProvincias()
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);

        String query = String.format("select * from provincias where active=TRUE");
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getMunicipios(String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id, nombre from municipios where provincia ='%s' and active = TRUE",prov);
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getUSFromMunic(String munic)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select usilvicola.id, usilvicola.nombre from usilvicola inner join usilv_mcpio on(usilvicola.id = usilv_mcpio.id) where usilv_mcpio.id_mcpio ='%s'",munic);
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getUSFromProv(String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select usilvicola.id, usilvicola.nombre from (usilvicola inner join usilv_mcpio on(usilvicola.id = usilv_mcpio.id)) " +
                "inner join municipios on(usilv_mcpio.id_mcpio = municipios.id) where municipios.provincia ='%s'",prov);
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getUSFromEFI(String efi)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id, nombre from usilvicola where efi ='%s'",efi);
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getAPFromMunic(String munic)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select area_protegida.id, area_protegida.nombre from area_protegida inner join aprotg_mcpio on(area_protegida.id = aprotg_mcpio.id) where aprotg_mcpio.id_mcpio ='%s'",munic);
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getAPFromProv(String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select area_protegida.id, area_protegida.nombre from (area_protegida inner join aprotg_mcpio on(area_protegida.id = aprotg_mcpio.id)) " +
                "inner join municipios on(aprotg_mcpio.id_mcpio = municipios.id) where municipios.provincia ='%s'",prov);
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getEFIFromProv(String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select efi.id, efi.nombre from efi where efi.prov='%s'",prov);
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static Object[][] getAllEFI()
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id, nombre from efi");
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static String getProvFromEFI(String efi)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select efi.prov from efi where efi.id = '%s'",efi);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getProvFromNameEFI(String efi)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select efi.prov from efi where efi.nombre = '%s'",efi);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static void InsertUser(String login, String passw, String propietario, int tipo, String prov, String munic,String id_entidad) throws Exception
    {
        if(existUser(login))
        {
            throw new Exception("Ya existe un usuario con el login '"+ login+"'");
        }
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("insert into usuarios(nombre,contrasenna,tipo,municipio,login) values('%s','%s',%d,'%s','%s')",propietario, new MD5(passw).asHex(),tipo,munic,login);
        adapter.executeQuery(query);
        UserTypes type = UserTypes.values()[tipo-1];
        if(type == UserTypes.Municipal)
        {
            if(!ExistOtros(munic))
            {
                String otros_id = DBQueries.getNextID(null, munic, prov);
                query = String.format("insert into otros values('%s','Otros')", otros_id);
                adapter.executeQuery(query);
                query = String.format("insert into otros_mcpio values('%s','%s')", otros_id, munic);
                adapter.executeQuery(query);
            }
        }
        if(type == UserTypes.EFI || type == UserTypes.Area_Protegida || type == UserTypes.Unidad_Silvicola)
        {
            String table = null;
            if(type == UserTypes.EFI)
            {
                table = "usuario_efi";
            }
            else if(type == UserTypes.Unidad_Silvicola)
            {
                table = "usuario_us";
            }
            else if(type == UserTypes.Area_Protegida)
            {
                table = "usuario_ap";
            }
            query = String.format("insert into %s values('%s','%s')", table,id_entidad,login);
            adapter.executeQuery(query);
        }
    }

    public static void ModifyUser(String login, String passw, String propietario, int tipo, String prov, String munic,String id_entidad, String old_login, int old_tipo, boolean change_passw) throws Exception
    {
        if(!old_login.equals(login))
        {
            if(existUser(login))
            {
                throw new Exception("Ya existe un usuario con el login '"+ login+"'");
            }
        }
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query;
        if(change_passw)
        {
            query = String.format("update usuarios set nombre='%s', contrasenna='%s', tipo=%d, municipio='%s', login='%s' where login='%s'",propietario, new MD5(passw).asHex(),tipo,munic,login,old_login);
        }
        else
        {
            query = String.format("update usuarios set nombre='%s', tipo=%d, municipio='%s', login='%s' where login='%s'",propietario, tipo,munic,login,old_login);
        }
        adapter.executeQuery(query);
        UserTypes type = UserTypes.values()[tipo-1];
        UserTypes old_type = UserTypes.values()[old_tipo-1];
        if((type == UserTypes.EFI || type == UserTypes.Area_Protegida || type == UserTypes.Unidad_Silvicola)
                || (old_type == UserTypes.EFI || old_type == UserTypes.Area_Protegida || old_type == UserTypes.Unidad_Silvicola))
        {
            boolean insert = false;
            boolean delete = false;
            boolean update = false;
            String table = null, delete_table = null;
            String column_name = null;
            if(old_tipo == tipo)
            {
                update = true;
                if(type == UserTypes.EFI)
                {
                    table = "usuario_efi";
                    column_name = "id_efi";
                }
                else if(type == UserTypes.Unidad_Silvicola)
                {
                    table = "usuario_us";
                    column_name = "id_us";
                }
                else if(type == UserTypes.Area_Protegida)
                {
                    table = "usuario_ap";
                    column_name = "id_ap";
                }
            }
            else
            {
                if(type==UserTypes.EFI || type==UserTypes.Unidad_Silvicola || type==UserTypes.Area_Protegida)
                {
                    insert = true;
                    if(old_type==UserTypes.EFI || old_type==UserTypes.Unidad_Silvicola || old_type==UserTypes.Area_Protegida)
                    {
                        delete = true;
                        if(old_type == UserTypes.EFI)
                        {
                            delete_table = "usuario_efi";
                        }
                        else if(old_type == UserTypes.Unidad_Silvicola)
                        {
                            delete_table = "usuario_us";
                        }
                        else if(old_type == UserTypes.Area_Protegida)
                        {
                            delete_table = "usuario_ap";
                        }
                    }
                    if(type == UserTypes.EFI)
                    {
                        table = "usuario_efi";
                        column_name = "id_efi";
                    }
                    else if(type == UserTypes.Unidad_Silvicola)
                    {
                        table = "usuario_us";
                        column_name = "id_us";
                    }
                    else if(type == UserTypes.Area_Protegida)
                    {
                        table = "usuario_ap";
                        column_name = "id_ap";
                    }
                }
                else if(old_type==UserTypes.EFI || old_type==UserTypes.Unidad_Silvicola || old_type==UserTypes.Area_Protegida)
                {
                    delete = true;
                    if(old_type == UserTypes.EFI)
                    {
                        delete_table = "usuario_efi";
                    }
                    else if(old_type == UserTypes.Unidad_Silvicola)
                    {
                        delete_table = "usuario_us";
                    }
                    else if(old_type == UserTypes.Area_Protegida)
                    {
                        delete_table = "usuario_ap";
                    }
                }
            }
            if(update)
            {
                query = String.format("update %s set %s='%s', login='%s' where login = '%s'", table,column_name,id_entidad,login,old_login);
            }
            else if(insert)
            {
                query = String.format("insert into %s values('%s', '%s')", table,id_entidad,login);
                //System.out.println(query);
                adapter.executeQuery(query);
            }
            if(delete)
            {
                query = String.format("delete from %s where login='%s'", delete_table,old_login);
                //System.out.println(query);
                adapter.executeQuery(query);
            }   
        }
    }

    public static String getMunicFromEntity(UserTypes type, String id_entity)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String table = "";
        if(type == UserTypes.Area_Protegida)
        {
            table = "aprotg_mcpio";
        }
        if(type == UserTypes.Unidad_Silvicola)
        {
            table = "usilv_mcpio";
        }
        String query = String.format("select id_mcpio from %s where id='%s'",table, id_entity);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getUSId(String name, String efi_name, String munic)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select usilvicola.id from (usilvicola inner join usilv_mcpio on(usilvicola.id=usilv_mcpio.id)) " +
                "inner join efi on(usilvicola.efi=efi.id) where usilv_mcpio.id_mcpio='%s' and efi.nombre='%s' and usilvicola.nombre='%s'",munic,efi_name,name);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static boolean ExistOtros(String munic)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id from otros_mcpio where id_mcpio='%s'",munic);
        adapter.executeQuery(query);
        if(adapter.getRowCount()>0)
        {
            return true;
        }
        return false;
    }

    public static Object[][] getSignificacion()
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select id, nombre from significacion where active=TRUE");
        adapter.executeQuery(query);
        return adapter.getAllRows();
    }

    public static String getAPId(String name, String tenente, String signif,String munic)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select area_protegida.id from ((area_protegida inner join aprotg_mcpio on(area_protegida.id=aprotg_mcpio.id)) " +
                "inner join municipios on(aprotg_mcpio.id_mcpio=municipios.id)) inner join significacion" +
                " on(area_protegida.significacion=significacion.id) where municipios.nombre='%s' and area_protegida.nombre='%s' and area_protegida.tenente='%s' and significacion.nombre='%s'",munic,name,tenente,signif);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getProvFromIdAP(String id)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select provincias.id from (provincias inner join municipios on(provincias.id=municipios.provincia)) " +
                "inner join aprotg_mcpio on(municipios.id=aprotg_mcpio.id_mcpio) where aprotg_mcpio.id = '%s'",id);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static String getEFIId(String name, String prov)
    {
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query = String.format("select efi.id from efi inner join provincias on(efi.prov=provincias.id) where efi.nombre='%s' and provincias.nombre='%s'",name,prov);
        adapter.executeQuery(query);
        return adapter.getValueAt(0, 0).toString();
    }

    public static void executeQuery(String query) throws SQLException
    {
        JDBCAdapter adapter = new JDBCAdapter(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
        adapter.executeQuery(query);
    }

    public static boolean isActive(String table, String id, int tipo_id)
    {
        //tipo_id dice si el id es String o int
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query;
        if(tipo_id == FieldDescriptor.STRING)
        {
            query = String.format("select id from %s where id='%s' and active='TRUE'",table,id);
        }
        else
        {
            query = String.format("select id from %s where id=%s and active='TRUE'",table,id);
        }
        adapter.executeQuery(query);
        if(adapter.getRowCount() > 0)
        {
            return true;
        }
        return false;
    }

    public static void deleteNomenc(String table, String id, int tipo_id)
    {
        //tipo_id dice si el id es String o int
        JDBCAdapter adapter = CollectionConnection.getConnection(ClassParams.HOST);
        String query;
        if(tipo_id == FieldDescriptor.STRING)
        {
            query = String.format("delete from %s where id='%s'",table,id);
        }
        else
        {
            query = String.format("delete from %s where id=%s",table,id);
        }
        adapter.executeQuery(query);
    }
}
