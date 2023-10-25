/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.objects;

/**
 *
 * @author Raisel
 */
public class C_5_5_Object
{
 private String id;
 private int cantMuj;
 private int totalValue;
 private int totalId;
 private TipoCategoria_Object categ;

    public C_5_5_Object()
    {
        this.id = "-1";
        this.cantMuj = 0;
        this.totalValue = 0;
        this.totalId = 0;
        this.categ = new TipoCategoria_Object();
    }

    public C_5_5_Object(String id, int cantMuj, TipoCategoria_Object categ)
    {
        this.id = id;
        this.cantMuj = cantMuj;
//        this.total = total;
        this.categ = categ;
    }

    public C_5_5_Object(String id, int cantMuj, int total, int totalId, TipoCategoria_Object categ)
    {
        this.id = id;
        this.cantMuj = cantMuj;
        this.totalValue = total;
        this.totalId = totalId;
        this.categ = categ;
    }

    public int getTotalId() {
        return totalId;
    }

    public void setTotalId(int totalId) {
        this.totalId = totalId;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public Integer getCantMuj() {
        return cantMuj;
    }

    public void setCantMuj(int cantMuj) {
        this.cantMuj = cantMuj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoCategoria_Object getCateg() {
        return categ;
    }

    public void setCateg(TipoCategoria_Object categ) {
        this.categ = categ;
    }

     @Override
     public String toString()
     {
      return categ.Nombre()+" --- Cantidad de mujeres: "+cantMuj;
     }
}