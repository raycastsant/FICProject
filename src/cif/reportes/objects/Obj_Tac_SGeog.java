/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Tac_SGeog
{
 private double tac;
 private double sgeog;

    public Obj_Tac_SGeog(double tac, double sgeog)
    {
        this.tac = tac;
        this.sgeog = sgeog;
    }

    public double getSgeog() {
        return sgeog;
    }

    public void setSgeog(double sgeog) {
        this.sgeog = sgeog;
    }

    public double getTac() {
        return tac;
    }

    public void setTac(double tac) {
        this.tac = tac;
    }
}
