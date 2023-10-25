/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.manejables.nomenc;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.NomencManejable;
import cif.adminstration.utiles.Utils;
import cif.gui.JF_Principal;
import cif.gui.nomencladores.MunicNomencPanel;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Pedro
 */
public class MunicManejable extends NomencManejable
{
    private static final int NAME = 0;
    private static final int PROVINCIA = 1;
    public MunicManejable(JF_Principal princ)
    {
        super(princ);
    }

    @Override
    public void Insert()
    {
        MunicNomencPanel mp = new MunicNomencPanel();
        parent.setComponent(mp);
    }

    @Override
    public void Modify()
    {
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
            MunicNomencPanel mp = new MunicNomencPanel(name, prov);
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
        int selected_option=JOptionPane.showConfirmDialog(parent.getContentPane(), "Realmente desea eliminar los elementos seleccionados?", "Pregunta", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if(selected_option != JOptionPane.OK_OPTION)
        {
            return;
        }
        selected_option=JOptionPane.showInternalConfirmDialog(parent.getContentPane(), "Esta acción eliminará los datos relacionados con el Municipio seleccionado.\n\rDesea continuar?", "Pregunta", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(selected_option != JOptionPane.OK_OPTION)
        {
            return;
        }
        String id;
        String name[] = new String[selected_rows.length];
        String provincias[] = new String[selected_rows.length];
        for(int i=0; i<selected_rows.length; i++)
        {
            name[i] = table.getModel().getValueAt(selected_rows[i], NAME).toString();
            provincias[i] = table.getModel().getValueAt(selected_rows[i], PROVINCIA).toString();
        }
        for(int i=0; i<name.length; i++)
        {
            id=DBQueries.getMunicIdNProv(name[i], provincias[i]);
            DBQueries.UpdateMunic(id,name[i],DBQueries.getNomencId("provincias",provincias[i]),"false");
         //   BDUtilities.deleteNomenc("municipios", id, FieldDescriptor.STRING);
        }
    }

    @Override
    public JTable Report() {
        return DBQueries.getAllMunic();
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
