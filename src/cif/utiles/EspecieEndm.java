/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.utiles;

/**
 *
 * @author juliette
 */
public class EspecieEndm {
    String nombre;
    String id;
    int valor;

    public EspecieEndm(String id,String nombre, int valor)
    {
        this.nombre=nombre;
        this.id=id;
        this.valor=valor;
    }

    public void setId(String ids)
    {
        id=ids;
    }

    public void setNombre(String nombres)
    {
        nombre=nombres;
    }

    public void setValor(int valors)
    {
        valor=valors;

    }

    public String getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getValor()
    {
        return valor;
    }

    public EspecieEndm Clone()
    {
        return new EspecieEndm(id,nombre,valor);
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
