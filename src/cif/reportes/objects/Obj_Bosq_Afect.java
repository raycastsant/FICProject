/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Bosq_Afect
{
    private double tac;
    private double invespex;
    private double librepast;
    private double plagas;
    private double otras;

    public Obj_Bosq_Afect(double tac, double invespex, double librepast, double plagas, double otras)
    {
        this.tac = tac;
        this.invespex = invespex;
        this.librepast = librepast;
        this.plagas = plagas;
        this.otras = otras;
    }

    public double getTac() {
        return tac;
    }

    public double getInvespex() {
        return invespex;
    }

    public double getLibrepast() {
        return librepast;
    }

    public double getOtras() {
        return otras;
    }

    public double getPlagas() {
        return plagas;
    }
}