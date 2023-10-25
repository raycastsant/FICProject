/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.utiles;

import cif.bd.BDUtilities;
import cif.bd.ClassParams;
import cif.bd.DBAccess;
import cif.gui.JF_Principal;
import cif.gui.McpioEntidad;
import cif.manage.BaseUnits;
import cif.manage.Manage;
import cif.manage.User;
import cif.manage.UserTypes;
import cif.manejables.Ind1_1_Manejable;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author juliette
 */
public class utiles {
    static String  entidad;
    public static Boolean panelMcpioShown=false;
    
    
    

    public static String generate_id(Integer nextid)
    {
        String id=null;
        if (nextid<10)
        {
            id="00"+nextid.toString();
        }
        if ((nextid>=10)&&(nextid<100))
        {
            id="0"+nextid.toString();
        }
        Manage manager = Manage.getInstance();
        User user = manager.getUser();
        id=user.getProvincia()+user.getMunicipio()+id;
        return id;
    }

    public static void SetParams(String path) throws XmlPullParserException, IOException
    {
        FileInputStream fi = new FileInputStream(path);
        KXmlParser kxml = new KXmlParser();
        kxml.setInput(fi, null);
        String host = "",port = "",BD = "",user = "",passw = "";
        int tagType = kxml.next();
        while(tagType != KXmlParser.END_DOCUMENT)
        {
            if(tagType == KXmlParser.START_TAG)
            {
                String tagName = kxml.getName();
                if(tagName.equals("host"))
                {
                    host = kxml.getAttributeValue(null, "value");
                }
                else if(tagName.equals("port"))
                {
                    port = kxml.getAttributeValue(null, "value");
                }
                else if(tagName.equals("name"))
                {
                    BD = kxml.getAttributeValue(null, "value");
                }
                else if(tagName.equals("user"))
                {
                    user = kxml.getAttributeValue(null, "value");
                }
                else if(tagName.equals("passw"))
                {
                    passw = kxml.getAttributeValue(null, "value");
                }
            }
            tagType = kxml.next();
        }
        ClassParams.HOST = "jdbc:postgresql://"+host+":"+port+"/"+BD;
        ClassParams.USER = user;
        ClassParams.PASSW = passw;
    }

    public static String getPath(String dir, String name)
    {
        String home_dir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String result = "";
        if(separator.equals("/"))
        {
            result = home_dir+separator+dir+separator+name;
        }
        else
        {
            result = home_dir+"\\"+dir+"\\"+name;
        }
        return result;
    }

    public static String getId(BaseUnits bu,JF_Principal jfp)
    {  
        
        Object[][] arrayid = null;
        Manage manager = Manage.getInstance();
        User user = manager.getUser();
        String iduser = null;
        McpioEntidad mcpientpanel;
        
        try {
                if (user.getType()==UserTypes.Municipal)
                {                    
                    arrayid=DBAccess.getIdentity(user.getIdEntidad(), gettablenamemcpio(bu));
                    System.out.println("START____nro de entidades por mcpio--"+arrayid.length);
                    if (arrayid.length==0)
                    {
                        System.out.println("1");
                        JOptionPane.showInternalMessageDialog(jfp.getContentPane(), "El municipio no tiene "+entidad+". Debe crear una entidad.","Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (arrayid.length==1)
                    {
                        System.out.println("2");
                        iduser=arrayid[0][0].toString();
                    }
                    else
                    {
                        System.out.println("3");
                        mcpientpanel=new McpioEntidad(arrayid,gettableEntity(bu));
                        mcpientpanel.setModalityType(ModalityType.APPLICATION_MODAL);
                        mcpientpanel.setVisible(true);
                        System.out.println("pasoooooooosijn BBBB--");
                        if(mcpientpanel.okPressed)
                        {
                            iduser=mcpientpanel.getId();
                        }
                    }
                } else if (user.getType()==UserTypes.Unidad_Silvicola)
                {
                    iduser = BDUtilities.getIdEntidad(user.getUser(), user.getType());
                    System.out.println("us");
                }
                else if (user.getType()==UserTypes.Area_Protegida)
                {
                    iduser = BDUtilities.getIdEntidad(user.getUser(), user.getType());
                    System.out.println("ap");
                }
        } catch (SQLException ex) {
                Logger.getLogger(utiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        return iduser;
    }

    public static String gettablenamemcpio(BaseUnits bu)
    {
        String tablename;
        if (bu==BaseUnits.Area_Protegida)
        {
            tablename="aprotg_mcpio";
            entidad="Áreas Protegidas";
        }
        else if(bu==BaseUnits.Otros)
        {
            tablename="otros_mcpio";
            entidad="Otros";
        }
        else
        {
             tablename="usilv_mcpio";
             entidad="Unidades Silvícolas";
        }
        return tablename;
    }
    
    public static String gettableEntity(BaseUnits bu)
    {
        String tablename;
        if (bu==BaseUnits.Area_Protegida)
        {
            tablename="area_protegida";
        }
        else if(bu==BaseUnits.Otros)
        {
            tablename="otros";
        }
        else
        {
             tablename="usilvicola";
        }
        return tablename;
    }


    public static void SoloNumber(java.awt.event.KeyEvent e, String number)
    {
      char caracter = e.getKeyChar();
      // Verificar si la tecla pulsada no es un digito
      if(((caracter < '0') ||(caracter > '9'))&&(caracter != KeyEvent.VK_BACK_SPACE)&&(caracter !='.'))
      {
         e.consume();  // ignorar el evento de teclado
         return;
      }
      if(number.contains(".") && caracter == '.')
      {
          e.consume();
      }
    }

    public static void SoloNumberInteger(java.awt.event.KeyEvent e)
    {
      char caracter = e.getKeyChar();
      // Verificar si la tecla pulsada no es un digito
      if(((caracter < '0') ||(caracter > '9'))&&(caracter != KeyEvent.VK_BACK_SPACE))
      {
         e.consume();  // ignorar el evento de teclado
      }
    }



}
