package cif.utiles;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JComboBox;

public class Adv_ComboBox extends JComboBox
{
    Hashtable<Object,Object> datos;

    public Adv_ComboBox()
    {
            super();
            datos = new Hashtable<Object,Object>();
    }

    public void addItem(Object value, Object text)
    {
            datos.put(text, value);
            super.addItem(text);
    }

    public Object getSelectedValue()
    {
        Object obj = getSelectedItem();
        if(obj == null)
        {
            return null;
        }
        return datos.get(obj);
    }

    public Object getValueOf(Object object)
    {
        if(object == null)
        {
            return null;
        }
        return datos.get(object);
    }

    public Object[] getValues()
    {
        return datos.values().toArray(new Object[0]);
    }

    public void setSelectedValue(Object obj)
    {
        Object selected;
        if(obj == null)
        {
            return;
        }
        Enumeration e = datos.keys();
        while(e.hasMoreElements())
        {
            selected = e.nextElement();
            if(datos.get(selected).equals(obj))
            {
                setSelectedItem(selected);
                return;
            }
        }
    }
}
