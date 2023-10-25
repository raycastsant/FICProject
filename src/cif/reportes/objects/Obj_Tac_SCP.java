/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Tac_SCP
{
 private double tac;
 private double Scp;

    public Obj_Tac_SCP(double tac, double Scp)
    {
        this.tac = tac;
        this.Scp = Scp;
    }

    public double getScp() {
        return Scp;
    }

    public void setScp(double Scp) {
        this.Scp = Scp;
    }

    public double getTac() {
        return tac;
    }

    public void setTac(double tac) {
        this.tac = tac;
    }
}