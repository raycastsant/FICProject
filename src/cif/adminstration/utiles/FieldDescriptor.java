package cif.adminstration.utiles;

public class FieldDescriptor 
{
	private String name;
	private int type;
	public static int NUMBER = 0;
	public static int STRING = 1;
	
	public FieldDescriptor(String nombre, String tipo)
	{
		name = nombre;
		if(tipo.equals("Double") || tipo.equals("Integer"))
		{
			type = NUMBER;
		}
		else
		{
			type = STRING;
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getType()
	{
		return type;
	}
}
