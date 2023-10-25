/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.objects;

/**
 *
 * @author Raisel
 */
public class C_4_5_Object
{
 private String id;
 private int cantFF;
 private double supFF;
 private double supPotAs;
 private double supManAs;
 private double supPotFF;
 private double supRegFF;
 private double areaBnFF;

    public C_4_5_Object()
    {
        this.id = "-1";
        this.cantFF = 0;
        this.supFF = 0;
        this.supPotAs = 0;
        this.supManAs = 0;
        this.supPotFF = 0;
        this.supRegFF = 0;
        this.areaBnFF = 0;
    }

    public C_4_5_Object(String id, int cantFF, Double supFF, Double supPotAs, Double supManAs, Double supPotFF, Double supRegFF, Double areaBnFF)
    {
        this.id = id;
        this.cantFF = cantFF;
        this.supFF = supFF;
        this.supPotAs = supPotAs;
        this.supManAs = supManAs;
        this.supPotFF = supPotFF;
        this.supRegFF = supRegFF;
        this.areaBnFF = areaBnFF;
    }

    public Double getAreaBnFF() {
        return areaBnFF;
    }

    public void setAreaBnFF(Double areaBnFF) {
        this.areaBnFF = areaBnFF;
    }

    public Integer getCantFF() {
        return cantFF;
    }

    public void setCantFF(int cantFF) {
        this.cantFF = cantFF;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getSupFF() {
        return supFF;
    }

    public void setSupFF(Double supFF) {
        this.supFF = supFF;
    }

    public Double getSupManAs() {
        return supManAs;
    }

    public void setSupManAs(Double supManAs) {
        this.supManAs = supManAs;
    }

    public Double getSupPotAs() {
        return supPotAs;
    }

    public void setSupPotAs(Double supPotAs) {
        this.supPotAs = supPotAs;
    }

    public Double getSupPotFF() {
        return supPotFF;
    }

    public void setSupPotFF(Double supPotFF) {
        this.supPotFF = supPotFF;
    }

    public Double getSupRegFF() {
        return supRegFF;
    }

    public void setSupRegFF(Double supRegFF) {
        this.supRegFF = supRegFF;
    }
}
