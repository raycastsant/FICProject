/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.manejables;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.NomencManejable;
import cif.adminstration.utiles.Utils;
import cif.bd.BDUtilities;
import cif.gui.JF_Principal;
import cif.gui.entidades.AP_Panel;
import cif.manage.Manage;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Pedro
 */
public class APManejable extends NomencManejable
{
    private static final int NAME = 0;
    private static final int TENENTE = 1;
    private static final int SIGNIFICACION = 2;
    private static final int PLAN_MANEJO = 3;
    private static final int MUNICIPIO = 4;
    public APManejable(JF_Principal parent)
    {
        super(parent);
    }

    @Override
    public void Insert() {
        AP_Panel up = new AP_Panel();
        parent.setComponent(up);
    }

    @Override
    public void Modify() {
        if(Utils.isTable(parent.getPrincipalComponent()))
        {
            String name,tenente,signif,munic,plan_manejo;
            JTable table = (JTable)parent.getPrincipalComponent();
            int selected_row = table.getSelectedRow();
            if(selected_row<0)
            {
                return;
            }
            name = table.getModel().getValueAt(selected_row, NAME).toString();
            tenente = table.getModel().getValueAt(selected_row, TENENTE).toString();
            munic = table.getModel().getValueAt(selected_row, MUNICIPIO).toString();
            signif = table.getModel().getValueAt(selected_row, SIGNIFICACION).toString();
            plan_manejo = table.getModel().getValueAt(selected_row, PLAN_MANEJO).toString();
            AP_Panel up = new AP_Panel(name,tenente,signif,munic,plan_manejo);
            parent.setComponent(up);
        }
    }

    @Override
    public void Delete() {
        Component comp = parent.getPrincipalComponent();
        if(!(comp instanceof JTable))
        {
            return;
        }
        JTable table = (JTable)comp;
        int[] selected_rows = table.getSelectedRows();
        if(selected_rows.length == 0)
        {
            return;
        }
        int selected_option=JOptionPane.showInternalConfirmDialog(parent.getContentPane(), "Realmente desea eliminar los elementos seleccionados?", "Mensaje", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if(selected_option != JOptionPane.OK_OPTION)
        {
            return;
        } 
        selected_option=JOptionPane.showInternalConfirmDialog(parent.getContentPane(), "Esta acción eliminará los usuarios relacionados con el Área Protegida seleccionada.\n\rDesea continuar?", "Advertencia", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(selected_option != JOptionPane.OK_OPTION)
        {
            return;
        }
        String id;
        String name[] = new String[selected_rows.length];
        String[] tenente = new String[selected_rows.length],signif=new String[selected_rows.length],munic=new String[selected_rows.length];
        for(int i=0; i<selected_rows.length; i++)
        {
            name[i] = table.getModel().getValueAt(selected_rows[i], NAME).toString();
            tenente[i] = table.getModel().getValueAt(selected_rows[i], TENENTE).toString();
            munic[i] = table.getModel().getValueAt(selected_rows[i], MUNICIPIO).toString();
            signif[i] = table.getModel().getValueAt(selected_rows[i], SIGNIFICACION).toString();
        }
        for(int i=0; i<name.length; i++)
        {
            id=BDUtilities.getAPId(name[i], tenente[i],signif[i], munic[i]);
            DBQueries.DeleteAP(id);
        }
    }

    @Override
    public JTable Report() {
        return DBQueries.getAP(Manage.getInstance().getUser());
    }

    @Override
    public JTable BaseUnits_Report() {
        return Report();
    }

    @Override
    public JTable Munic_Report() {
        return Report();
    }

    @Override
    public JTable Prov_Report() {
        return Report();
    }

    @Override
    public void DrawGraphic() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NomencManejable Clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
