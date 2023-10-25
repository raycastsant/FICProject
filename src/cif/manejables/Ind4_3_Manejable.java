/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.manejables;


import cif.bd.BDUtilities;
import cif.bd.DBAccess;
import cif.bd.DBExtraUtils;
import cif.graficos.GraphicManager;

import cif.gui.Criterio_4.Indicador4_3;
import cif.gui.Criterio_4.Indicador4_3_Modify;
import cif.gui.JF_Principal;
import cif.manage.BaseUnits;
import cif.manage.Manage;
import cif.manage.User;
import cif.manage.UserTypes;
import cif.objects.C_4_3_Object;
import cif.objects.Tala_Object;
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
 * @author Raisel
 */
public class Ind4_3_Manejable extends Manejable{

String tag,id;
int nf,row;
Object[][] result43;
private User user;
private static final int temTal=1;
private static final int nameEnt=0;

    public Ind4_3_Manejable(JF_Principal parent)
    {
        super(parent);
        Manage manager = Manage.getInstance();
        user = manager.getUser();
    }

    @Override
    public void Insert()
    {
        tag = "insert";
        User user = Manage.getInstance().getUser();
        id = utiles.getId(getBaseUnits(), parent);
        if(id!=null)
        {
            Tala_Object []talas = DBExtraUtils.getAllTalas();
            Indicador4_3 indi43 = new Indicador4_3(talas, tag, id, parent.getSelectedYear(), gettablename(), parent, user.getMunicipio());
            parent.setComponent(indi43);
        }
    }

    @Override
    public void Modify()
    {
//        tag = "modify";

        User user = Manage.getInstance().getUser();
        String tableName = gettablename();
        id = utiles.getId(getBaseUnits(), parent);
        if(id!=null)
        {
            C_4_3_Object []talas = DBExtraUtils.getC_4_3_Objects(tableName, parent.getSelectedYear(), user.getIdEntidad(), id);
            Indicador4_3_Modify indi43 = new Indicador4_3_Modify(talas, id, parent.getSelectedYear(), gettablename(), parent, user.getMunicipio());
            parent.setComponent(indi43);
        }
    }

    @Override
    public void Delete() {
        JTable table;
        String idE,mcpio;
        String where="";
        Integer idTal;
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
                    table=(JTable) comp;
                    if (option == JOptionPane.OK_OPTION)
                    {
                        int rowsSel[]=table.getSelectedRows();
                        if (user.getType()==UserTypes.Municipal)
                        {
                            if(rowsSel.length>0)
                            {
                                for (int i = 0; i <rowsSel.length; i++)
                                {
                                    if(!table.getValueAt(rowsSel[i], nameEnt).toString().equals("TOTAL") && !table.getValueAt(rowsSel[i], nameEnt).toString().equals("Sub-Total"))
                                    {
                                      idE=DBAccess.getIde(table.getValueAt(rowsSel[i], nameEnt).toString(),utiles.gettableEntity(getBaseUnits()));
                                      mcpio=DBAccess.getIdmcpio(idE, utiles.gettablenamemcpio(getBaseUnits()));
                                      idTal=java.lang.Integer.parseInt(DBAccess.getIde(table.getValueAt(rowsSel[i], temTal).toString(),"c4_3_talas"));
                                      where="and id_talas="+idTal+"";
                                      DBAccess.delete(idE.toString(), gettablename(),mcpio,parent.getSelectedYear(),where);
                                    }
                                }
                            }
                            else
                            {
                                JOptionPane.showConfirmDialog(parent, "Debe seleccionar una fila de la tabla.", "Advertencia!!!", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        if (user.getType()==UserTypes.Unidad_Silvicola)
                        {
                            if(rowsSel.length>0)
                            {
                                for (int i = 0; i <rowsSel.length; i++)
                                {
                                    if(!table.getValueAt(rowsSel[i], nameEnt).toString().equals("TOTAL"))
                                    {
                                        idE = BDUtilities.getIdEntidad(user.getUser(), user.getType());
                                        mcpio=DBAccess.getIdmcpio(idE, utiles.gettablenamemcpio(getBaseUnits()));
                                        idTal=java.lang.Integer.parseInt(DBAccess.getIde(table.getValueAt(rowsSel[i], temTal).toString(),"c4_3_talas"));
                                        where="and id_talas="+idTal+"";
                                        DBAccess.delete(idE.toString(), gettablename(),mcpio,parent.getSelectedYear(),where);
                                    }
                                }
                            }
                            else
                            {
                                JOptionPane.showConfirmDialog(parent, "Debe seleccionar una fila de la tabla.", "Advertencia!!!", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        else if (user.getType()==UserTypes.Area_Protegida)
                        {
                            if(rowsSel.length>0)
                            {
                                for (int i = 0; i <rowsSel.length; i++)
                                {
                                    if(!table.getValueAt(rowsSel[i], nameEnt).toString().equals("TOTAL"))
                                    {
                                        idE = BDUtilities.getIdEntidad(user.getUser(), user.getType());
                                        mcpio=DBAccess.getIdmcpio(idE, utiles.gettablenamemcpio(getBaseUnits()));
                                        idTal=java.lang.Integer.parseInt(DBAccess.getIde(table.getValueAt(rowsSel[i], temTal).toString(),"c4_3_talas"));
                                        where="and id_talas="+idTal+"";
                                        DBAccess.delete(idE.toString(), gettablename(),mcpio,parent.getSelectedYear(),where);
                                    }
                                }
                            }
                            else
                            {
                                JOptionPane.showConfirmDialog(parent, "Debe seleccionar una fila de la tabla.", "Advertencia!!!", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(Ind4_2_Manejable.class.getName()).log(Level.SEVERE, null, ex);
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
            table = ReportManager.mostrar_Reporte(ReportManager.C_4_3_Talas, user, anno, false, bu);

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
            table = ReportManager.mostrar_Reporte(ReportManager.C_4_3_Talas, user, anno, true, bu);

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

                 GraphicManager.mostrar_Grafico(ReportManager.C_4_3_Talas, usuario, bu);

            } catch (SQLException ex) {
                Logger.getLogger(Ind1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private String gettablename()
    {
        String tablename;
        BaseUnits bu = getBaseUnits();

        if (bu == BaseUnits.Area_Protegida)
         tablename="c4_3_ap_talas";
        else
        if(bu == BaseUnits.Otros)
         tablename="c4_3_otros_talas";
        else
         tablename="c4_3_us_talas";
        
        return tablename;
    }

    @Override
    public Manejable Clone() {
         return new Ind4_3_Manejable(parent);
    }

}

