/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.manejables.nomenc;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.NomencManejable;
import cif.adminstration.utiles.Utils;
import cif.gui.JF_Principal;
import cif.gui.nomencladores.SignifNomencPanel;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author IntelI3
 */
public class SignifManejable extends NomencManejable
{
    private static final int NAME = 0;
    public SignifManejable(JF_Principal princ)
    {
        super(princ);
    }

    @Override
    public void Insert()
    {
        SignifNomencPanel mp = new SignifNomencPanel();
        parent.setComponent(mp);
    }

    @Override
    public void Modify()
    {
        if(Utils.isTable(parent.getPrincipalComponent()))
        {
            String name;
            JTable table = (JTable)parent.getPrincipalComponent();
            int selected_row = table.getSelectedRow();
            if(selected_row<0)
            {
                return;
            }
            name = table.getModel().getValueAt(selected_row, NAME).toString();
            SignifNomencPanel mp = new SignifNomencPanel(name);
            parent.setComponent(mp);
        }
    }

    @Override
    public void Delete()
    {
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
        String id;
        String name[] = new String[selected_rows.length];
        for(int i=0; i<selected_rows.length; i++)
        {
            name[i] = table.getModel().getValueAt(selected_rows[i], NAME).toString();
        }
        for(int i=0; i<name.length; i++)
        {
            id=DBQueries.getNomencId("significacion",name[i]);
            DBQueries.UpdateNomenc("significacion",id,name[i],"false");
        //    BDUtilities.deleteNomenc("provincias", id, FieldDescriptor.STRING);
        }
    }

    @Override
    public JTable Report() {
        return DBQueries.getAllNomenc("significacion");
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
