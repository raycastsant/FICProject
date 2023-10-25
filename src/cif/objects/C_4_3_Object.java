/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.objects;

import javax.swing.JOptionPane;

/**
 *
 * @author Raisel
 */
public class C_4_3_Object
{
 private String id;
 private Tala_Object tala;
 private double supTotal;
 private double supTecAdec;

    public C_4_3_Object()
    {
        this.id = "-1";
        this.tala = new Tala_Object();
        this.supTotal = 0;
        this.supTecAdec = 0;
    }

    public C_4_3_Object(String id, Tala_Object tala, double supTotal, double supTecAdec)
    {
        this.id = id;
        this.tala = tala;
        this.supTotal = supTotal;
        this.supTecAdec = supTecAdec;
    }

    public String Id() {
        return id;
    }

    public Double getSupTecAdec() {
        return supTecAdec;
    }

    public Double getSupTotal() {
        return supTotal;
    }

    public Tala_Object getTala() {
        return tala;
    }

    public void setSupTecAdec(double supTecAdec) 
    {
      this.supTecAdec = supTecAdec;
    }

    public void setSupTotal(double supTotal)
    {
      this.supTotal = supTotal;
    }

     @Override
     public String toString()
     {
      return tala.Nombre()+" --- Sup. total: "+supTotal+" --- Sup. Técn. Adec: "+supTecAdec;
     }
}
