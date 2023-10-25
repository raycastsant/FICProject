/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.utiles;

/**
 *
 * @author juliette
 */
public class ProgramaProtec {
    String nombre;
    String id;
    Double valor;

    public ProgramaProtec(String id,String nombre, Double valor)
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

    public void setValor(Double valors)
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

    public Double getValor()
    {
        return valor;
    }

    public ProgramaProtec Clone()
    {
        return new ProgramaProtec(id,nombre,valor);
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
