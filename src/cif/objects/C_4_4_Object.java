/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.objects;

/**
 *
 * @author Raisel
 */
public class C_4_4_Object
{
 private String id;
 private TipoProducto_Object producto;
 private double cantidad;
 private double potencial;
 private double valor;

    public C_4_4_Object()
    {
        this.id = "-1";
        this.producto = new TipoProducto_Object();
        this.cantidad = 0;
        this.potencial = 0;
        this.valor = 0;
    }

    public C_4_4_Object(String id, TipoProducto_Object producto, double cantidad, double potencial, double valor)
    {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.potencial = potencial;
        this.valor = valor;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPotencial() {
        return potencial;
    }

    public void setPotencial(double potencial) {
        this.potencial = potencial;
    }

    public TipoProducto_Object getProducto() {
        return producto;
    }

    public void setProducto(TipoProducto_Object producto) {
        this.producto = producto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

     @Override
     public String toString()
     {
      return producto.Nombre()+" --- Cantidad: "+cantidad+" --- Potencial: "+potencial+" --- Valor:"+valor;
     }
}
