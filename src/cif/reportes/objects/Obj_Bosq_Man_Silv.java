/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Bosq_Man_Silv
{
 private double sejecRaleo;
 private double sejecLimp;
 private double sejecPoda;
 private double sejecReconst;
 private double necanRaleo;
 private double necanLimp;
 private double necanPoda;
 private double necanReconst;

    public Obj_Bosq_Man_Silv(double sejecRaleo, double sejecLimp, double sejecPoda, double sejecReconst, double necanRaleo, double necanLimp, double necanPoda, double necanReconst)
    {
        this.sejecRaleo = sejecRaleo;
        this.sejecLimp = sejecLimp;
        this.sejecPoda = sejecPoda;
        this.sejecReconst = sejecReconst;
        this.necanRaleo = necanRaleo;
        this.necanLimp = necanLimp;
        this.necanPoda = necanPoda;
        this.necanReconst = necanReconst;
    }

    public double getNecanLimp() {
        return necanLimp;
    }

    public double getNecanPoda() {
        return necanPoda;
    }

    public double getNecanRaleo() {
        return necanRaleo;
    }

    public double getNecanReconst() {
        return necanReconst;
    }

    public double getSejecLimp() {
        return sejecLimp;
    }

    public double getSejecPoda() {
        return sejecPoda;
    }

    public double getSejecRaleo() {
        return sejecRaleo;
    }

    public double getSejecReconst() {
        return sejecReconst;
    }
}