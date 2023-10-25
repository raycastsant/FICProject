/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.manejables;


import cif.bd.BDUtilities;
import cif.bd.DBAccess;
import cif.graficos.GraphicManager;

import cif.gui.Criterio_1.Indicador1_4;
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
public class Ind1_4_Manejable extends Manejable{

private String tag,id;
private User user;
private int nf,row;
private Double bn,plant;
private Object[][] result14;
    private double sp;
    private double sl;
    private double pv;
    private double pm;
    private double especie;
    private static final int temEsp=7;
    private static final int nameEnt=0;


    public Ind1_4_Manejable(JF_Principal parent)
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
            Indicador1_4 indi14 = new Indicador1_4(id, parent, gettablename(), tag, null);
            parent.setComponent(indi14);
        }
    }

    @Override
    public void Modify() {
        tag="modify";               
        id = utiles.getId(getBaseUnits(), parent);
        if(id!=null)
        {
           try {
                result14=getresult();
                if(result14.length>0){
                Indicador1_4 indi14= new Indicador1_4(id,parent,gettablename(),tag,result14);
                parent.setComponent(indi14);
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
        Integer isEsp;
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
                                      isEsp=java.lang.Integer.parseInt(DBAccess.getIde(table.getValueAt(rowsSel[i], temEsp).toString(),"especie"));
                                      where="and especie="+isEsp+"";
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
                                        isEsp=java.lang.Integer.parseInt(DBAccess.getIde(table.getValueAt(rowsSel[i], temEsp).toString(),"especie"));
                                        where="and especie="+isEsp+"";
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
                                        isEsp=java.lang.Integer.parseInt(DBAccess.getIde(table.getValueAt(rowsSel[i], temEsp).toString(),"especie"));
                                        where="and especie="+isEsp+"";
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
            Logger.getLogger(Ind1_4_Manejable.class.getName()).log(Level.SEVERE, null, ex);
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
            table = ReportManager.mostrar_Reporte(ReportManager.C_1_4_EfectividadPlantaciones, User, anno, false, bu);

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
            table = ReportManager.mostrar_Reporte(ReportManager.C_1_4_EfectividadPlantaciones, User, anno, true, bu);

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

                 GraphicManager.mostrar_Grafico(ReportManager.C_1_4_EfectividadPlantaciones, usuario, bu);

            } catch (SQLException ex) {
                Logger.getLogger(Ind1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private String gettablename()
    {
        String tablename;
        if (getBaseUnits()==BaseUnits.Area_Protegida)
        {
            tablename="c1_4_ap_efectividadplantaciones";
        } 
        else if(getBaseUnits()==BaseUnits.Otros)
        {
            tablename="c1_4_otros_efectividadplantaciones";
        }
        else
        {
             tablename="c1_4_us_efectividadplantaciones";
        }
        return tablename;
    }

    private Object[][] getresult() throws SQLException{
        Object[][] res14 = null;
        res14=DBAccess.selectEfectv(id,parent.getSelectedYear(),gettablename());
        return res14;
    }

    @Override
    public Manejable Clone() {
         return new Ind1_4_Manejable(parent);
    }

}

