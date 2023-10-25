/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Porc_Mad_Ext
{
 private double sumCortas;
 private double vol;

    public Obj_Porc_Mad_Ext(double sumCortas, double vol)
    {
        this.sumCortas = sumCortas;
        this.vol = vol;
    }

    public double getSumCortas() {
        return sumCortas;
    }

    public double getVol() {
        return vol;
    }
}