/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.utiles;

import cif.gui.JF_Principal;
import cif.manage.BaseUnits;
import javax.swing.JTable;

/**
 *
 * @author Pedro
 */
public abstract class Manejable
{
    protected JF_Principal parent = null;
    BaseUnits b_units;
    protected Manejable(JF_Principal parent)
    {
        this.parent = parent;
    }
    public void setBaseUnits(BaseUnits bu)
    {
        b_units = bu;
    }

    public BaseUnits getBaseUnits()
    {
        return b_units;
    }
    public abstract void Insert();
    public abstract void Modify();
    public abstract void Delete();
    public abstract JTable Report(); // Reporte por defecto de la entidad
    public abstract JTable BaseUnits_Report();//Reporte desglosado por unidades básicas
    public abstract JTable Munic_Report();//Reportes desglosados por municipios
    public abstract JTable Prov_Report();//Reportes desglosados por provincias
    public abstract void DrawGraphic();//Para dibujar los graficos
    public abstract Manejable Clone(); // crea un clon del objeto
}
