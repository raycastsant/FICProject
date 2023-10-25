/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.objects;

/**
 *
 * @author Raisel
 */
public class TipoProducto_Object
{
 private int id;
 private String nombre;

 public TipoProducto_Object(int _id, String _nombre)
 {
  id = _id;
  nombre = _nombre;
 }

 public TipoProducto_Object()
 {
  id = -1;
  nombre = "";
 }

 public Integer Id()
 {
  return id;
 }

 public String Nombre()
 {
  return nombre;
 }

 @Override
 public String toString()
 {
  return Nombre();
 }
}
