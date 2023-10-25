/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Sist_ASilv
{
    private double sPotAs;
    private double sManAs;
    private double cantFinc;
    private double sPotFinc;
    private double sFinc;
    private double area;

    public Obj_Sist_ASilv(double sPotAs, double sManAs, double cantFinc, double sPotFinc, double sFinc, double area) 
    {
        this.sPotAs = sPotAs;
        this.sManAs = sManAs;
        this.cantFinc = cantFinc;
        this.sPotFinc = sPotFinc;
        this.sFinc = sFinc;
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public double getCantFinc() {
        return cantFinc;
    }

    public double getsFinc() {
        return sFinc;
    }

    public double getsManAs() {
        return sManAs;
    }

    public double getsPotAs() {
        return sPotAs;
    }

    public double getsPotFinc() {
        return sPotFinc;
    }
}