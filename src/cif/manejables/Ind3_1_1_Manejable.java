/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.manejables;


import cif.bd.BDUtilities;
import cif.bd.DBAccess;
import cif.graficos.GraphicManager;

import cif.gui.Criterio_3.Indicador3_1_1;
import cif.gui.Criterio_3.Indicador3_1_1_Mod;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author juliette
 */
public class Ind3_1_1_Manejable extends Manejable{

String tag,id;
User user;
int nf,row;
Double bn,plant;
Object[][] result311,aprotg;
private static final int temFF=1;
private static final int nameEnt=0;


    public Ind3_1_1_Manejable(JF_Principal parent)
    {
        //constructor del padre
        super(parent);
        Manage manager = Manage.getInstance();
        user = manager.getUser();
    }

    @Override
    public void Insert() {
        String idap;
        Double value;
        ArrayList<String>lista=new ArrayList<String>();
        ArrayList<String>listaerror=new ArrayList<String>();
        Object [][] AP;
        int c;
        if (user.getType()==UserTypes.Municipal)
        {
            try {
                aprotg = DBAccess.getIdentity(user.getIdEntidad(), "aprotg_mcpio");
                if(aprotg.length>0)
                {
                    c=0;
                    for (int i = 0; i < aprotg.length; i++) 
                    {
                        idap = aprotg[i][0].toString();
                        value=getresult1_1(idap);
                        if (value>0.0)
                        {
                            c=c+1;
                            lista.add(idap);
                        }
                        else
                        {
                            listaerror.add(idap);
                        }
                    }
                    if(lista.size()>0)
                    {
                        AP=new Object[lista.size()][1];
                        for (int j = 0; j < lista.size(); j++)
                        {
                            AP[j][0]=lista.get(j);
                        }
                        Indicador3_1_1 indi311 = new Indicador3_1_1(AP, parent, gettablename(), null);
                        parent.setComponent(indi311);
                    }
                    if(listaerror.size()>0)
                    {
                        String mp="El municipio debe calcular el TAC de el(las) Área(s) Protegida(s):\n\r";
                        String m1="";
                        String m2="";
                        String anno="\n\ren el año "+parent.getSelectedYear()+".";
                        String saltoLine;
                        for (Integer k = 0; k < listaerror.size(); k++)
                        {
                           m1=DBAccess.getnameIdentidad(listaerror.get(k), "area_protegida");
                           Double multp4=Double.valueOf(k.doubleValue()/4);
                           Integer multp4_I=(int) Math.floor(multp4);//integer
                           Double retornomultp4=multp4-multp4_I;//se resta el decim-integ
                           if(k!=0 && (retornomultp4).equals(0.0))//si el resultado es entero
                            {
                             saltoLine="\n\r";
                             if(k==listaerror.size()-1)
                             {
                                 saltoLine="";
                             }
                            }
                           else
                            {
                             saltoLine="-";
                            }
                            m2 = m2+m1+saltoLine;
                        }
                       JOptionPane.showMessageDialog(parent, mp+m2+anno,"Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(parent, "El municipio no tiene Áreas Protegidas. Debe crear una entidad.","Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ind3_1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(user.getType()==UserTypes.Area_Protegida)
        {
            try {
                String temp = "";
                // aprotg[0][0] = utiles.getId(getBaseUnits(), parent);
                temp = utiles.getId(getBaseUnits(), parent);
                value = getresult1_1(temp);
                if (value > 0.0)
                {
                    aprotg = new Object[1][1];
                    aprotg[0][0] = temp;
                    Indicador3_1_1 indi311 = new Indicador3_1_1(aprotg, parent, gettablename(), null);
                    parent.setComponent(indi311);
                } else {
                    JOptionPane.showMessageDialog(parent, "El Área Protegida debe calcular el TAC para el año:"+parent.getSelectedYear()+".", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ind3_1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(parent,  "Para la entidad "+user.getType()+" no está n/permitida la operación requerida.","Información!!!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
    }

    @Override
    public void Modify() {
       if (user.getType()==UserTypes.Municipal)
        {
            try {
                    aprotg = DBAccess.getIdentity(user.getIdEntidad(), "aprotg_mcpio");
                    if(aprotg.length>0)
                    {
                        result311 =getresult();
                        if(result311.length>0)
                        {
                            Indicador3_1_1_Mod indi313= new Indicador3_1_1_Mod(aprotg,parent,gettablename(),result311);
                            parent.setComponent(indi313);
                        }
                        else
                        {
                            JOptionPane.showConfirmDialog(parent, "No existen datos para esta entidad en el año "+parent.getSelectedYear()+".", "Error!!!", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(parent, "El municipio no tiene Áreas Protegidas. Debe crear una entidad.","Información", JOptionPane.INFORMATION_MESSAGE);
                    }
            } catch (SQLException ex) {
                Logger.getLogger(Ind3_1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(user.getType()==UserTypes.Area_Protegida)
        {
            try {
                aprotg[0][0] = utiles.getId(getBaseUnits(), parent);
                result311 = getresult();
                if (result311.length > 0) {
                    Indicador3_1_1_Mod indi313 = new Indicador3_1_1_Mod(aprotg, parent, gettablename(), result311);
                    parent.setComponent(indi313);
                } else {
                    JOptionPane.showConfirmDialog(parent, "No existen datos para esta entidad en el año " + parent.getSelectedYear() + ".", "Error!!!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ind3_1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showConfirmDialog(parent, "Información!!!", "Para la entidad "+user.getType()+" no está \n\rpermitida la operación requerida.", JOptionPane.OK_OPTION);
            return;
        }
    }

    @Override
    public void Delete() {
        JTable table;
        String idE,mcpio;
        String where="";
        Integer idFF;
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
                                      idFF=java.lang.Integer.parseInt(DBAccess.getIde(table.getValueAt(rowsSel[i], temFF).toString(),"c3_1_1_formacionforestal"));
                                      where="and id_formforest="+idFF+"";
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
                                        idFF=java.lang.Integer.parseInt(DBAccess.getIde(table.getValueAt(rowsSel[i], temFF).toString(),"c3_1_1_formacionforestal"));
                                        where="and id_formforest="+idFF+"";
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
            Logger.getLogger(Ind3_1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
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
            table = ReportManager.mostrar_Reporte(ReportManager.C_3_1_1_FormacionForestal, User, anno, false, bu);

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
            table = ReportManager.mostrar_Reporte(ReportManager.C_3_1_1_FormacionForestal, User, anno, true, bu);

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

                 GraphicManager.mostrar_Grafico(ReportManager.C_3_1_1_FormacionForestal, usuario, bu);

            } catch (SQLException ex) {
                Logger.getLogger(Ind1_1_Manejable.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private String gettablename()
    {
        String tablename;
        if (getBaseUnits()==BaseUnits.Area_Protegida)
        {
            tablename="c3_1_1_ap_formacionforestal";
        } 
        else if(getBaseUnits()==BaseUnits.Otros)
        {
            tablename="c3_1_1_otros_formacionforestal";
        }
        else
        {
             tablename="c3_1_1_us_formacionforestal";
        }
        return tablename;
    }

    private Object[][] getresult() throws SQLException{
        Object[][] res311 = null;
        if (aprotg.length>1) {
            tag="mun";
        }
        else
        {
            tag="";
        }
        res311=DBAccess.selectFormacForestal(id,parent.getSelectedYear(),tag,gettablename());
        return res311;
    }

    private Double getresult1_1(String idAP) throws SQLException{
        Object[][] res11 = null;
        Double valuebn = 0.0;
        res11=DBAccess.selectTAC(idAP,parent.getSelectedYear(),"c1_1_ap_totalareacubierta");
        if(res11.length==0)
        {
            System.out.println("da nulooooooooooooooooooooooo");
            valuebn = 0.0;
        }
        else
        {
            valuebn=java.lang.Double.parseDouble(res11[0][0].toString());
        }
        
        return valuebn;
    }

    @Override
    public Manejable Clone() {
         return new Ind3_1_1_Manejable(parent);
    }

}

