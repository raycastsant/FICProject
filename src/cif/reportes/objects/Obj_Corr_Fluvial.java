/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Corr_Fluvial
{
 private double SProtHa;
 private double suptotzon;

    public Obj_Corr_Fluvial(double SProtHa, double suptotzon)
    {
        this.SProtHa = SProtHa;
        this.suptotzon = suptotzon;
    }

    public double getSProtHa() {
        return SProtHa;
    }

    public double getSuptotzon() {
        return suptotzon;
    }
}