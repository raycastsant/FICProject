/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.objects;

/**
 *
 * @author Raisel
 */
public class C_5_4_Object
{
 private String id;
 private int cantidad;
 private int plan_manejo;
 private TipoServicio_Object tipoServicio;

    public C_5_4_Object()
    {
        this.id = "-1";
        this.cantidad = 0;
        this.plan_manejo = 0;
        this.tipoServicio = new TipoServicio_Object();
    }

    public C_5_4_Object(String id, int cantidad, int plan_manejo, TipoServicio_Object tipoServicio)
    {
        this.id = id;
        this.cantidad = cantidad;
        this.plan_manejo = plan_manejo;
        this.tipoServicio = tipoServicio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPlan_manejo() {
        return plan_manejo;
    }

    public void setPlan_manejo(int plan_manejo) {
        this.plan_manejo = plan_manejo;
    }

    public TipoServicio_Object getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio_Object tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

     @Override
     public String toString()
     {
      return tipoServicio.Nombre()+" --- Cantidad: "+cantidad+" --- Plan manejo: "+plan_manejo;
     }
}