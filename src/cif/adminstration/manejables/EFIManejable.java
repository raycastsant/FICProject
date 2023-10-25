/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.manejables;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.NomencManejable;
import cif.adminstration.utiles.Utils;
import cif.gui.JF_Principal;
import cif.gui.entidades.EFI_Panel;
import cif.manage.Manage;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Pedro
 */
public class EFIManejable extends NomencManejable
{   
    private static final int NAME = 0;
    private static final int PROVINCIA = 1;
    public EFIManejable(JF_Principal parent)
    {
        super(parent);
    }

    @Override
    public void Insert() {
        EFI_Panel up = new EFI_Panel();
        parent.setComponent(up);
    }

    @Override
    public void Modify() {
        if(Utils.isTable(parent.getPrincipalComponent()))
        {
            String name,prov;
            JTable table = (JTable)parent.getPrincipalComponent();
            int selected_row = table.getSelectedRow();
            if(selected_row<0)
            {
                return;
            }
            name = table.getModel().getValueAt(selected_row, NAME).toString();
            prov = table.getModel().getValueAt(selected_row, PROVINCIA).toString();
            EFI_Panel up = new EFI_Panel(name,prov);
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
        selected_option=JOptionPane.showInternalConfirmDialog(parent.getContentPane(), "Esta acción eliminará los usuarios relacionados con la EFI seleccionada.\n\rDesea continuar?", "Advertencia", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(selected_option != JOptionPane.OK_OPTION)
        {
            return;
        }
        String name[] = new String[selected_rows.length];
        String[] prov = new String[selected_rows.length],signif=new String[selected_rows.length],munic=new String[selected_rows.length];
        for(int i=0; i<selected_rows.length; i++)
        {
            name[i] = table.getModel().getValueAt(selected_rows[i], NAME).toString();
            prov[i] = table.getModel().getValueAt(selected_rows[i], PROVINCIA).toString();
        }
        for(int i=0; i<name.length; i++)
        {
            DBQueries.DeleteEFI(name[i],prov[i]);
        }
    }

    @Override
    public JTable Report() {
        return DBQueries.getEFI(Manage.getInstance().getUser());
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
