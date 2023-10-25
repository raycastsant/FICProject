/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.objects;

/**
 *
 * @author Raisel
 */
public class Obj_Salario
{
 private double saldev;
 private double promtrab;

    public Obj_Salario(double saldev, double promtrab)
    {
        this.saldev = saldev;
        this.promtrab = promtrab;
    }

    public double getPromtrab() {
        return promtrab;
    }

    public double getSaldev() {
        return saldev;
    }
}