/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.manejables;

import cif.adminstration.bd.DBQueries;
import cif.adminstration.utiles.NomencManejable;
import cif.adminstration.utiles.Utils;
import cif.gui.JF_Principal;
import cif.gui.usuarios.UserPanel;
import cif.manage.UserTypes;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Pedro
 */
public class UserManejable extends NomencManejable
{
    private static final int LOGIN = 0;
    private static final int TYPE = 1;
    private static final int MUNICIPIO = 2;
    private static final int PROVINCIA = 3;
    private static final int PROPIETARIO = 4;
    public UserManejable(JF_Principal parent)
    {
        super(parent);
    }
    @Override
    public void Insert() {
        UserPanel up = new UserPanel();
        parent.setComponent(up);
    }

    @Override
    public void Modify() 
    {
        if(Utils.isTable(parent.getPrincipalComponent()))
        {
            String login,prop,munic,prov,tipo;
            JTable table = (JTable)parent.getPrincipalComponent();
            int selected_row = table.getSelectedRow();
            if(selected_row<0)
            {
                return;
            }
            login = table.getModel().getValueAt(selected_row, LOGIN).toString();
            prop = table.getModel().getValueAt(selected_row, PROPIETARIO).toString();
            munic = table.getModel().getValueAt(selected_row, MUNICIPIO).toString();
            prov = table.getModel().getValueAt(selected_row, PROVINCIA).toString();
            tipo = table.getModel().getValueAt(selected_row, TYPE).toString();
            UserTypes type = UserTypes.values()[DBQueries.getUserTypeId(tipo)-1];
            String id_efi = null;
            String id_us = null;
            String id_ap = null;
            if(type == UserTypes.EFI)
            {
                id_efi = DBQueries.getEFIbyLogin(login);
            }
            else if(type == UserTypes.Unidad_Silvicola)
            {
                id_us = DBQueries.getUSbyLogin(login);
            }
            else if(type == UserTypes.Area_Protegida)
            {
                id_ap = DBQueries.getAPbyLogin(login);
            }
            UserPanel u_panel = new UserPanel(login,prop,tipo,prov,munic,id_us,id_ap,id_efi);
            parent.setComponent(u_panel);
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
        String[] logins = new String[selected_rows.length];
        for(int i=selected_rows.length-1; i>=0; i--)
        {
            logins[i]=table.getModel().getValueAt(selected_rows[i], LOGIN).toString();
        }
        for(int i=0; i<logins.length; i++)
        {
            DBQueries.DeleteUser(logins[i]);
        }
    }

    @Override
    public JTable Report()
    {
        return DBQueries.getAllUsers();
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
      //  throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NomencManejable Clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
