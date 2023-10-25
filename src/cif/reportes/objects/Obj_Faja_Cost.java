/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Faja_Cost
{
 private double SProtHa;
 private double longlin;

    public Obj_Faja_Cost(double SProtHa, double longlin)
    {
        this.SProtHa = SProtHa;
        this.longlin = longlin;
    }

    public double getSProtHa() {
        return SProtHa;
    }

    public double getLongLin() {
        return longlin;
    }
}