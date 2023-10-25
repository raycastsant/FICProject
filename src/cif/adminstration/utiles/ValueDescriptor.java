package cif.adminstration.utiles;

public class ValueDescriptor 
{
	int type;
	Object value;
	public ValueDescriptor(int tipo, Object valor)
	{
		type = tipo;
		value = valor;
	}
	
	public int getType()
	{
		return type;
	}
	
	public Object getValue()
	{
		return value;
	}
}
