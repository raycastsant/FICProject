/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Arb_Aisl
{
 private double arbExist;
 private double arbFalt;

    public Obj_Arb_Aisl(double arbExist, double arbFalt)
    {
        this.arbExist = arbExist;
        this.arbFalt = arbFalt;
    }

    public double getArbExist() {
        return arbExist;
    }

    public void setArbExist(double arbExist) {
        this.arbExist = arbExist;
    }

    public double getArbFalt() {
        return arbFalt;
    }

    public void setArbFalt(double arbFalt) {
        this.arbFalt = arbFalt;
    }
}