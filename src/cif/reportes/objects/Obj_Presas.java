/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Presas
{
 private double longperm;
 private double anchoref;
 private double suptotzon;

    public Obj_Presas(double longperm, double anchoref, double suptotzon)
    {
        this.longperm = longperm;
        this.anchoref = anchoref;
        this.suptotzon = suptotzon;
    }

    public double getAnchoref() {
        return anchoref;
    }

    public double getLongperm() {
        return longperm;
    }

    public double getSuptotzon() {
        return suptotzon;
    }
}