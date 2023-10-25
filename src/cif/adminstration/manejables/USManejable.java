/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.manejables;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.NomencManejable;
import cif.adminstration.utiles.Utils;
import cif.gui.JF_Principal;
import cif.gui.entidades.US_Panel;
import cif.manage.Manage;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Pedro
 */
public class USManejable extends NomencManejable
{
    private static final int NAME = 0;
    private static final int EFI = 1;
    private static final int MUNICIPIO = 2;
    public USManejable(JF_Principal parent)
    {
        super(parent);
    }
    @Override
    public void Insert() {
        US_Panel up = new US_Panel();
        parent.setComponent(up);
    }

    @Override
    public void Modify() {
        if(Utils.isTable(parent.getPrincipalComponent()))
        {
            String name,efi,munic;
            JTable table = (JTable)parent.getPrincipalComponent();
            int selected_row = table.getSelectedRow();
            if(selected_row<0)
            {
                return;
            }
            name = table.getModel().getValueAt(selected_row, NAME).toString();
            efi = table.getModel().getValueAt(selected_row, EFI).toString();
            munic = table.getModel().getValueAt(selected_row, MUNICIPIO).toString();
            US_Panel up = new US_Panel(name,efi,munic);
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
        selected_option=JOptionPane.showInternalConfirmDialog(parent.getContentPane(), "Esta acción eliminará los usuarios relacionados con la Unidad Silvícola seleccionada.\n\rDesea continuar?", "Advertencia", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(selected_option != JOptionPane.OK_OPTION)
        {
            return;
        }
        String[] names = new String[selected_rows.length];
        String[] efis = new String[selected_rows.length];
        for(int i=selected_rows.length-1; i>=0; i--)
        {
            names[i]=table.getModel().getValueAt(selected_rows[i], NAME).toString();
            efis[i]=table.getModel().getValueAt(selected_rows[i], EFI).toString();
        }
        for(int i=0; i<names.length; i++)
        {
            DBQueries.DeleteUS(names[i],efis[i]);
        }
    }

    @Override
    public JTable Report() {
        return DBQueries.getUS(Manage.getInstance().getUser());
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
