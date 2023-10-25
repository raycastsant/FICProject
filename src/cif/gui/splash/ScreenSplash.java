/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.gui.splash;

import cif.Main;
import cif.gui.JF_Principal;
import cif.gui.LoginDialog;
import cif.manage.Manage;
import cif.utiles.utiles;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author Raisel
 */
public class ScreenSplash
{
 final SplashScreen splash ;
  //texto que se muestra a medida que se va cargando el screensplah
  final String[] texto = {"Cargando sistema : 10%" ,"Cargando sistema : 20%", "Cargando sistema : 30%",
                          "Cargando sistema : 40%","Cargando sistema : 50%", "Cargando sistema : 60%" ,
                          "Cargando sistema : 70%", "Cargando sistema : 80%",
                          "Cargando sistema : 90%","Cargando sistema : 100%", ""};

  public ScreenSplash()
  {
    splash = SplashScreen.getSplashScreen();
  }

   public void animar()
   {
        if (splash != null)
        {
            Graphics2D g = splash.createGraphics();
            JF_Principal principal = null;
            LoginDialog ld = null;

            for(int i=1; i<texto.length; i++)
            {
                //se pinta texto del array
                g.setColor( new Color(199,214,238));//color de fondo
                g.fillRect(0,0,360,12);//para tapar texto anterior
                g.setColor(Color.BLACK);//color de texto
                g.drawString(texto[i-1]+"...", 100, 11);
                g.setColor(Color.green);//color de barra de progeso
                g.fillRect(0, 408,(i*360/texto.length), 12);//la barra de progreso
                //se pinta una linea segmentada encima de la barra verde
                float dash1[] = {2.0f};
                BasicStroke dashed = new BasicStroke(9.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash1, 0.0f);
                g.setStroke(dashed);
                g.setColor(Color.GREEN);//color de barra de progeso
                g.setColor( new Color(4,52,101));
                g.drawLine(0,414, 510, 414);

                try {
                    
                    switch(i)
                    {
                        case 1:{
                         utiles.SetParams(utiles.getPath("config", "params.xml"));
                         break;
                        }
                        case 2:{
                         principal = new JF_Principal();
                         break;
                        }
                        case 3:{
                         Manage.getInstance();
                         break;
                        }
                        case 4:{
                         ld = new LoginDialog(principal);
                         break;
                        }
//                        case 5:{
//                         principal.setVisible(true);
//                         break;
//                        }
//                        case 6:{
//                         ld.setModalityType(ModalityType.APPLICATION_MODAL);
//                         ld.setVisible(true);
//                         break;
//                        }
                        
                        default: break;
                    }

                }
                catch (XmlPullParserException ex)
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                //se actualiza todo
                splash.update();

      try {
       Thread.sleep(321);
      } catch(InterruptedException e) { }
                }

        splash.close();

         principal.setVisible(true);
         ld.setModalityType(ModalityType.APPLICATION_MODAL);
         ld.setVisible(true);
     }
   }
}
