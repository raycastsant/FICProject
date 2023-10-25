/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.manejables;


import cif.bd.BDUtilities;
import cif.bd.DBAccess;
import cif.graficos.GraphicManager;

import cif.gui.Criterio_5.Indicador5_1;
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
public class Ind5_1_Manejable extends Manejable{

String tag,id;
User user;
int nf,row;
Double bn,plant;
Object[][] result51;
private static final int nameEnt=0;

    public Ind5_1_Manejable(JF_Principal parent)
    {
        //constructor del padre
        super(parent);
        Manage manager = Manage.getInstance();
        user = manager.getUser();
    }

    @Override
    public void Insert() {
        tag = "insert";
        id = utiles.getId(getBaseUnits(), parent);
        if(id!=null)
        {
            Indicador5_1 indi51 = new Indicador5_1(id, parent, gettablename(), tag, null);
            parent.setComponent(indi51);
        }
    }

    @Override
    public void Modify() {
        tag="modify";       
        
        id=utiles.getId(getBaseUnits(), parent);
        if(id!=null)
        {
           try {
                result51=getresult();
                if(result51.length>0){
                    Indicador5_1 indi51= new Indicador5_1(id,parent,gettablename(),tag,result51);
                    parent.setComponent(indi51);
                }
                else
                    {
                        JOptionPane.showConfirmDialog(parent, "No existen datos para esta entidad en el año "+parent.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                    }
            } catch (SQLException ex) {
                Logger.getLogger(Ind5_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Ind5_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       @Override
    public JTable Report()
    {
     JTable table = new JTable();
     BaseUnits bu = getBaseUnits();
     Manage manager = Manage.getInstance();
     User user = manager.getUser();
     int anno = parent.getSelectedYear();

        try {
            table = ReportManager.mostrar_Reporte(ReportManager.C_5_1_Salario, user, anno, false, bu);

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
     User user = manager.getUser();
     int anno = parent.getSelectedYear();

        try {
            table = ReportManager.mostrar_Reporte(ReportManager.C_5_1_Salario, user, anno, true, bu);

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

                 GraphicManager.mostrar_Grafico(ReportManager.C_5_1_Salario, usuario, bu);

            } catch (SQLException ex) {
                Logger.getLogger(Ind1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private String gettablename()
    {
        String tablename;
        if (getBaseUnits()==BaseUnits.Area_Protegida)
        {
            tablename="c5_1_ap_salario";
        } 
        else if(getBaseUnits()==BaseUnits.Otros)
        {
            tablename="c5_1_otros_salario";
        }
        else
        {
             tablename="c5_1_us_salario";
        }
        return tablename;
    }

    private Object[][] getresult() throws SQLException{
        Object[][] res51 = null;
        res51=DBAccess.selectSalario(id,parent.getSelectedYear(),gettablename());
        return res51;
    }

    @Override
    public Manejable Clone() {
         return new Ind5_1_Manejable(parent);
    }

}

