package cif.bd;

import java.sql.SQLException;
import java.util.Hashtable;

public class CollectionConnection 
{
	private static Hashtable<String,DataAccess> table = null;
	
	public static void SetConnection(DataAccess da)
	{
		if(table == null)
		{
			table = new Hashtable<String,DataAccess>();
		}
		if(!table.containsKey(da.Host()))
		{
			table.put(da.Host(), da);
		}
	}
	
	public static JDBCAdapter getConnection(String host)
	{
		if(table.containsKey(host))
		{
			return ((DataAccess)table.get(host)).GetConnection();
		}
		return null;
	}
	
	public static String getDriver(String host)
	{
		if(table.containsKey(host))
		{
			return ((DataAccess)table.get(host)).Driver();
		}
		return null;
	}
	
	public static String getUser(String host)
	{
		if(table.containsKey(host))
		{
			return ((DataAccess)table.get(host)).User();
		}
		return null;
	}
	
	public static String getPassw(String host)
	{
		if(table.containsKey(host))
		{
			return ((DataAccess)table.get(host)).Password();
		}
		return null;
	}
	
	public static boolean hasConnection(String host)
	{
		if(table == null)
		{
			return false;
		}
		return table.containsKey(host);
	}

        public static void closeConection(String host) throws SQLException
	{
		if(table != null)
		{
			if(table.containsKey(host))
			{
				((DataAccess)table.get(host)).GetConnection().close();
				table.remove(host);
			}
		}
	}
}
