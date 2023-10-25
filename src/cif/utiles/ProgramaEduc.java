/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.utiles;

/**
 *
 * @author juliette
 */
public class ProgramaEduc {
    String nombre;
    String id;
    Integer valor1,valor2;

    public ProgramaEduc(String id,String nombre, Integer valor1,Integer valor2)
    {
        this.nombre=nombre;
        this.id=id;
        this.valor1=valor1;
        this.valor2=valor2;
    }

    public void setId(String ids)
    {
        id=ids;
    }

    public void setNombre(String nombres)
    {
        nombre=nombres;
    }

    public void setValor2(Integer valors2)
    {
        valor2=valors2;

    }

     public void setValor1(Integer valors1)
    {
        valor1=valors1;

    }

    public String getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public Integer getValor1()
    {
        return valor1;
    }

    public Integer getValor2()
    {
        return valor2;
    }

    public ProgramaEduc Clone()
    {
        return new ProgramaEduc(id,nombre,valor1,valor2);
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}
