/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.utiles;

import cif.bd.ClassParams;
import cif.bd.JDBCAdapter;
import cif.excel.XLSDocument;
import cif.manage.Manage;
import cif.manage.UserTypes;
import cif.utiles.Adv_ComboBox;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Pedro
 */
public class Utils
{
    public static JFrame getParentFrame(Container container)
    {
        Container result = container.getParent();
        if(result instanceof JFrame)
        {
            return (JFrame)result;
        }
        return getParentFrame(result);
    }

    public static void WriteFile(File dest,byte[] data, boolean append)
    {
        try
        {
            if(!dest.exists())
            {
                dest.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(dest,append);
            out.write(data);
            out.flush();
            out.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

  public static String[] ReadFile(File f)
    {
        try
        {
            FileInputStream fistream = new FileInputStream(f);
            ArrayList<String> list = new ArrayList<String>();
            while(true)
            {
                int data = fistream.read();
                StringBuilder sb = new StringBuilder();
                while(data != 10 && data != 13)
                {
                    if(data != -1)
                    {
                        sb.append((char)data);
                    }
                    else
                    {
                        if(!sb.toString().equals(""))
                        {
                            list.add(sb.toString());
                        }
                        return list.toArray(new String[0]);
                    }
                    data = fistream.read();
                }
                if(!sb.toString().equals(""))
                {
                    list.add(sb.toString());
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String DeleteSentenceToFileData(UserTypes type, String table, String aux_table)
    {
        String result;
        if(type == UserTypes.Municipal || type == UserTypes.Provincial)
        {
            result = String.format("delete from %s where id in (%s)",table,getSubquery(type, aux_table));
        }
        else
        {
            result = String.format("delete from %s where id in (select id from %s)",table,table);
        }
        return result;
    }

    private static String getSubquery(UserTypes type, String table)
    {
        String result;
        if(type==UserTypes.Municipal)
        {
            result = "select id from "+ table +" where id_mcpio='"+Manage.getInstance().getUser().getIdEntidad()+"'";
        }
        else
        {
            if(!table.equals("efi_mcpio"))
            {
                result = String.format("select id from %s inner join municipios on(%s.id_mcpio=municipios.id) where muicipios.provincia='%s'",table,table,Manage.getInstance().getUser().getIdEntidad());
            }
            else
            {
                result = String.format("select id from %s where id_prov='%s'", table,Manage.getInstance().getUser().getIdEntidad());
            }
        }
        return result;
    }

    public static String InsertSentence(String table,UserTypes type,String aux_table) throws SQLException
    {
        String result="";
        String aux;
        String query = CreateSentence(table, type, aux_table);
        String tabla=table;
        if(table == null)
        {
            tabla = aux_table;
        }
        JDBCAdapter adapter = new JDBCAdapter(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
        adapter.executeQuery(query);
        Object[][] data = adapter.getAllRows();
        for(int i=0; i<data.length; i++)
        {
            result += "insert into "+tabla+" values(";
            for(int j=0; j<data[i].length; j++)
            {
                if(adapter.getColumnClass(j).getName().equals(String.class.getName()))
                {
                    aux = "'";
                }
                else
                {
                    aux = "";
                }
                if(j==0)
                {
                    result+=aux+data[i][j].toString()+aux;
                }
                else
                {
                    result+=","+aux+data[i][j].toString()+aux;
                }
            }
            result += ")\n\r";
        }
        return result;
    }

    private static String CreateSentence(String table, UserTypes type, String aux_table)
    {
        String query="";
        String id_entidad = Manage.getInstance().getUser().getIdEntidad();
        if(aux_table == null)
        {
            if(type == UserTypes.Municipal)
            {
                query = String.format("select * from %s where municipio='%s'",table,id_entidad);
            }
            else if(type == UserTypes.Provincial)
            {
                query = String.format("select %s.* from %s inner join municipios on(%s.municipio=municipios.id) where municipios.provincia='%s'",table,table,table,id_entidad);
            }
            else if(type == UserTypes.Area_Protegida || type == UserTypes.Unidad_Silvicola)
            {
                query = String.format("select * from %s where id='%s'",table,id_entidad);
            }
            else
            {
                query = String.format("select * from %s",table);
            }
        }
        else
        {
            if(table != null)
            {
                if(type == UserTypes.Municipal)
                {
                    query = String.format("select %s.* from %s inner join %s on(%s.id=%s.id) where %s.id_mcpio='%s'",table,table,aux_table,table,aux_table,aux_table,id_entidad);
                }
                else if(type == UserTypes.Provincial)
                {
                    if(!table.equals("efi"))
                    {
                        query = String.format("select %s.* from %s inner join %s on(%s.id=%s.id) inner join municipios on(%s.id_mcpio=municipios.id) where municipios.provincia='%s'",table,table,aux_table,table,aux_table,aux_table,id_entidad);
                    }
                    else
                    {
                        query = String.format("select %s.* from %s inner join %s on(%s.id=%s.id) where %s.id_prov='%s'", table,table,aux_table,table,aux_table,aux_table,id_entidad);
                    }
                }
            }
            else
            {
               if(type == UserTypes.Municipal)
                {
                    query = String.format("select * from %s where id_mcpio='%s'",aux_table,id_entidad);
                }
                else if(type == UserTypes.Provincial)
                {
                    if(!aux_table.equals("efi_mcpio"))
                    {
                        query = String.format("select %s.* from %s inner join municipios on(%s.id_mcpio=municipios.id) where municipios.provincia='%s'",aux_table,aux_table,aux_table,id_entidad);
                    }
                    else
                    {
                        query = String.format("select * from %s where id_prov='%s'", aux_table,id_entidad);
                    }
                }
            }
        }
        return query;
    }

    public static String InsertUpdateSentence(String table) throws SQLException
    {
        String result="";
        String aux;
        String query = String.format("select * from %s",table);
        JDBCAdapter adapter = new JDBCAdapter(ClassParams.HOST,ClassParams.DRIVER,ClassParams.USER,ClassParams.PASSW);
        adapter.executeQuery(query);
        Object[][] data = adapter.getAllRows();
        String[] column_names = adapter.getColumnNames();
        for(int i=0; i<data.length; i++)
        {
            result += "insert into "+table+" values(";
            String update = "update "+table+" set ";
            for(int j=0; j<data[i].length; j++)
            {
                if(adapter.getColumnClass(j).getName().equals(String.class.getName()))
                {
                    aux = "'";
                }
                else
                {
                    aux = "";
                }
                if(j==0)
                {
                    result+=aux+data[i][j].toString()+aux;
                    update += column_names[j]+"="+aux+data[i][j].toString()+aux;
                }
                else
                {
                    result+=","+aux+data[i][j].toString()+aux;
                    update +=","+column_names[j]+"="+aux+data[i][j].toString()+aux;
                }
            }
            result += ")\n\r";
            result += update+"\n\r";
        }
        return result;
    }

    public static void CreateExcel(String file, TableModel tm) throws IOException
    {
        XLSDocument excel = new XLSDocument();
        String hoja = "Tabla";
        int row = 0;
        excel.addSheet(hoja);
        ValueDescriptor[] fila = new ValueDescriptor[tm.getColumnCount()];
        int[] types = new int[tm.getColumnCount()];
        for(int i=0; i<fila.length; i++)
        {
            fila[i] = new ValueDescriptor(FieldDescriptor.STRING, tm.getColumnName(i));
            if(tm.getRowCount()>0)
            {
                try
                {
                    String aux = (String)tm.getValueAt(0, i);
                    types[i] = FieldDescriptor.STRING;
                }
                catch(ClassCastException cast_ex)
                {
                    types[i] = FieldDescriptor.NUMBER;
                }
            }
        }
        excel.WriteRow(row, hoja, fila);
        row++;
        for(int i=0; i<tm.getRowCount(); i++)
        {
            fila = new ValueDescriptor[tm.getColumnCount()];
            for(int j=0; j<tm.getColumnCount(); j++)
            {
                fila[j] = new ValueDescriptor(types[j], tm.getValueAt(i, j));
            }
            excel.WriteRow(row, hoja, fila);
            row++;
        }
        excel.Save(file);
    }

    public static void FillCombo(Object[][] types, Adv_ComboBox combo)
    {
        combo.addItem(-1, "<Seleccionar>");
        for(int i=0; i<types.length; i++)
        {
            Object[] aux = types[i];
            combo.addItem(aux[0], aux[1]);
        }
    }

    public static boolean isTable(Component comp)
    {
        return comp instanceof JTable;
    }
}
