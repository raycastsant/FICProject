/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_IRS
{
 private double cortEjecBProd;
 private double cortPermBProd;
 private double cortEjecBProt;
 private double cortPermBProt;

    public Obj_IRS(double cortEjecBProd, double cortPermBProd, double cortEjecBProt, double cortPermBProt)
    {
        this.cortEjecBProd = cortEjecBProd;
        this.cortPermBProd = cortPermBProd;
        this.cortEjecBProt = cortEjecBProt;
        this.cortPermBProt = cortPermBProt;
    }

    public double getCortEjecBProd() {
        return cortEjecBProd;
    }

    public double getCortEjecBProt() {
        return cortEjecBProt;
    }

    public double getCortPermBProd() {
        return cortPermBProd;
    }

    public double getCortPermBProt() {
        return cortPermBProt;
    }
}