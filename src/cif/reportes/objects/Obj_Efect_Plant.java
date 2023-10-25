/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Efect_Plant
{
    private double slog;
    private double splant;
    private double plantviv;
    private double plantm;
    private double perddin;
    private double perdhect;

    public Obj_Efect_Plant(double slog, double splant, double plantviv, double plantm, double perddin, double perdhect)
    {
        this.slog = slog;
        this.splant = splant;
        this.plantviv = plantviv;
        this.plantm = plantm;
        this.perddin = perddin;
        this.perdhect = perdhect;
    }

    public double getPerddin() {
        return perddin;
    }

    public void setPerddin(double perddin) {
        this.perddin = perddin;
    }

    public double getPerdhect() {
        return perdhect;
    }

    public void setPerdhect(double perdhect) {
        this.perdhect = perdhect;
    }

    public double getPlantviv() {
        return plantviv;
    }

    public void setPlantviv(double plantviv) {
        this.plantviv = plantviv;
    }

    public double getSlog() {
        return slog;
    }

    public void setSlog(double slog) {
        this.slog = slog;
    }

    public double getSplant() {
        return splant;
    }

    public void setSplant(double splant) {
        this.splant = splant;
    }

    public double getPlantm() {
        return plantm;
    }

    public void setPlantm(double plantm) {
        this.plantm = plantm;
    }
}