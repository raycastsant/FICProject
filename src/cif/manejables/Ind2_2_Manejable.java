/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.manejables;


import cif.bd.BDUtilities;
import cif.bd.DBAccess;
import cif.graficos.GraphicManager;

import cif.gui.Criterio_2.Indicador2_2;
import cif.gui.JF_Principal;
import cif.manage.BaseUnits;
import cif.manage.Manage;
import cif.manage.User;
import cif.manage.UserTypes;
import cif.reportes.ReportManager;
import cif.utiles.Manejable;
import cif.utiles.utiles;
import java.awt.Component;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author juliette
 */
public class Ind2_2_Manejable extends Manejable{

String tag,id;
User user;
int nf,row;
Double superf;
Object[][] result22;
private static final int nameEnt=0;

    public Ind2_2_Manejable(JF_Principal parent)
    {
        //constructor del padre
        super(parent);
        Manage manager = Manage.getInstance();
        user = manager.getUser();
    }

    @Override
    public void Insert() {
        tag="insert";
        id = utiles.getId(getBaseUnits(), parent);
        if(id!=null)
        {
            Indicador2_2 indi22 = new Indicador2_2(id, parent, gettablename(), tag, null);
            parent.setComponent(indi22);
        }
    }

    @Override
    public void Modify() {
        tag="modify";        
        id=utiles.getId(getBaseUnits(), parent);
        if(id!=null)
        {
            try {
                    result22=getresult();
                    if(result22.length>0){
                        Indicador2_2 indi22= new Indicador2_2(id,parent,gettablename(),tag,result22);
                        parent.setComponent(indi22);
                    }
                    else
                    {
                        JOptionPane.showConfirmDialog(parent, "No existen datos para esta entidad en el año "+parent.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                    }
            } catch (SQLException ex) {
                Logger.getLogger(Ind1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void Delete() {
        JTable table;
        String idE,mcpio;
        String where="";
        try {
            Component comp=parent.getPrincipalComponent();
                if((comp==null) || (!(comp instanceof JTable)))
                {
                    id=utiles.getId(getBaseUnits(), parent);
                    int option = JOptionPane.showConfirmDialog(parent, "¿Desea eliminar los datos seleccionados?", "Advertencia!!!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (option == JOptionPane.OK_OPTION)
                    {
                        mcpio=DBAccess.getIdmcpio(id, utiles.gettablenamemcpio(getBaseUnits()));
                        DBAccess.delete(id, gettablename(),mcpio,parent.getSelectedYear(),where);
                    }
                }
                else
                {
                    int option = JOptionPane.showConfirmDialog(parent, "¿Desea eliminar los datos seleccionados?", "Advertencia!!!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (option == JOptionPane.OK_OPTION)
                    {
                        table=(JTable) comp;
                        int rowsSel[]=table.getSelectedRows();
                        if (user.getType()==UserTypes.Municipal)
                        {
                            for (int i = 0; i <rowsSel.length; i++)
                            {
                                if(!table.getValueAt(rowsSel[i], nameEnt).toString().equals("TOTAL") && !table.getValueAt(rowsSel[i], nameEnt).toString().equals("Sub-Total"))
                                {
                                  idE=DBAccess.getIde(table.getValueAt(rowsSel[i],nameEnt).toString(),utiles.gettableEntity(getBaseUnits()));
                                  mcpio=DBAccess.getIdmcpio(idE, utiles.gettablenamemcpio(getBaseUnits()));
                                  DBAccess.delete(idE.toString(), gettablename(),mcpio,parent.getSelectedYear(),where);
                                }
                            }
                        }
                        if (user.getType()==UserTypes.Unidad_Silvicola)
                        {
                            idE = BDUtilities.getIdEntidad(user.getUser(), user.getType());
                            mcpio=DBAccess.getIdmcpio(idE, utiles.gettablenamemcpio(getBaseUnits()));
                            DBAccess.delete(idE.toString(), gettablename(),mcpio,parent.getSelectedYear(),where);
                            System.out.println("us");
                        }
                        else if (user.getType()==UserTypes.Area_Protegida)
                        {
                            idE = BDUtilities.getIdEntidad(user.getUser(), user.getType());
                            mcpio=DBAccess.getIdmcpio(idE, utiles.gettablenamemcpio(getBaseUnits()));
                            DBAccess.delete(idE.toString(), gettablename(),mcpio,parent.getSelectedYear(),where);
                            System.out.println("ap");
                        }
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(Ind2_2_Manejable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public JTable Report()
    {
     JTable table = new JTable();
     BaseUnits bu = getBaseUnits();
     Manage manager = Manage.getInstance();
     User User = manager.getUser();
     int anno = parent.getSelectedYear();

        try {
            table = ReportManager.mostrar_Reporte(ReportManager.C_2_2_BosquesAfectados, User, anno, false, bu);

        } catch (SQLException ex) {
            Logger.getLogger(Ind1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
        }

      return table;
    }

    @Override
    public JTable BaseUnits_Report()
    {
     JTable table = new JTable();
     BaseUnits bu = getBaseUnits();
     Manage manager = Manage.getInstance();
     User User = manager.getUser();
     int anno = parent.getSelectedYear();

        try {
            table = ReportManager.mostrar_Reporte(ReportManager.C_2_2_BosquesAfectados, User, anno, true, bu);

        } catch (SQLException ex) {
            Logger.getLogger(Ind1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
        }

      return table;
    }

    @Override
    public JTable Munic_Report()
    {
     return BaseUnits_Report();
    }

    @Override
    public JTable Prov_Report()
    {
     return BaseUnits_Report();
    }

    @Override
    public void DrawGraphic()
    {
      Manage manager = Manage.getInstance();
      User usuario = manager.getUser();
      BaseUnits bu = getBaseUnits();

            try {

                 GraphicManager.mostrar_Grafico(ReportManager.C_2_2_BosquesAfectados, usuario, bu);

            } catch (SQLException ex) {
                Logger.getLogger(Ind1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private String gettablename()
    {
        String tablename;
        if (getBaseUnits()==BaseUnits.Area_Protegida)
        {
            tablename="c2_2_ap_bosquesafectados";
        } 
        else if(getBaseUnits()==BaseUnits.Otros)
        {
            tablename="c2_2_otros_bosquesafectados";
        }
        else
        {
             tablename="c2_2_us_bosquesafectados";
        }
        return tablename;
    }

    private Object[][] getresult() throws SQLException{
        Object[][] res22 = null;
        res22=DBAccess.selectBosqAfect(id,parent.getSelectedYear(),gettablename());
        return res22;
    }

    @Override
    public Manejable Clone() {
         return new Ind2_2_Manejable(parent);
    }

}

