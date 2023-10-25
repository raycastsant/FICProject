/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Area_Pend_Ref
{
 private double ataldef;
 private double aquem;
 private double aest;
 private double atal;

    public Obj_Area_Pend_Ref(double ataldef, double aquem, double aest, double atal)
    {
        this.ataldef = ataldef;
        this.aquem = aquem;
        this.aest = aest;
        this.atal = atal;
    }

    public double getAest()
    {
        return aest;
    }

    public void setAest(double aest) {
        this.aest = aest;
    }

    public double getAquem() {
        return aquem;
    }

    public void setAquem(double aquem) {
        this.aquem = aquem;
    }

    public double getAtal() {
        return atal;
    }

    public void setAtal(double atal) {
        this.atal = atal;
    }

    public double getAtaldef() {
        return ataldef;
    }

    public void setAtaldef(double ataldef) {
        this.ataldef = ataldef;
    }
}