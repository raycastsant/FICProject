package cif.bd;

import java.sql.SQLException;

public class DataAccess 
{
	private String user;
	private String passw;
	private String driver;
	private String host;
	private boolean enable;
	private JDBCAdapter conn;
	
	public DataAccess(String server,String driv,String usuario,String contr) throws SQLException
	{
		try 
		{
			conn = new JDBCAdapter(server,driv,usuario,contr);
			enable = true;
			host = server;
			driver = driv;
			user = usuario;
			passw = contr;
		}
		catch(SQLException ex)
		{
			throw new SQLException("No se pudo establecer la connexión a la Base de Datos.\nVerifique que los parámetros de conexión estén correctos.");
		}
	}
	
	public String User()
	{
		return user;
	}
	
	public String Password()
	{
		return passw;
	}
	
	public String Driver()
	{
		return driver;
	}
	
	public String Host()
	{
		return host;
	}
	
	public boolean IsEnabled()
	{
		return enable;
	}
	
	public JDBCAdapter GetConnection()
	{
		return conn;
	}
	
	public void CloseConnection()
	{
		try
		{
			conn.close();
			enable = false;
		}
		catch(SQLException e)
		{
			System.err.println("Cannot closed connection.");
        	System.err.println(e);
		}
	}
	
	public void ReConnect()
	{
		try 
		{
			conn = new JDBCAdapter(Host(),Driver(),User(),Password());
			enable = true;
		}
		catch(SQLException ex)
		{
			System.err.println("Cannot connect to this database.");
        	System.err.println(ex);
        	enable = false;
		}
	}
}
