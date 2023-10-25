/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Act_Min
{
 private double safect;
 private double scult;

    public Obj_Act_Min(double safect, double scult)
    {
        this.safect = safect;
        this.scult = scult;
    }

    public double getSafect() {
        return safect;
    }

    public double getScult() {
        return scult;
    }
}