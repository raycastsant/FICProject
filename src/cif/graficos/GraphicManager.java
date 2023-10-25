/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.graficos;

import cif.graficos.interfaces.IGraphic;
import cif.reportes.*;
import cif.manage.BaseUnits;
import cif.manage.User;
import cif.reportes.clases.IReportModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Raisel
 */
public class GraphicManager
{
 public static final String C_1_1_TotalAreaCubierta         = "C_1_1_TotalAreaCubierta";
 public static final String C_1_2_IndiceBoscosidad          = "C_1_2_IndiceBoscosidad";
 public static final String C_1_3_RelacionTac               = "C_1_3_RelacionTac";
 public static final String C_1_4_EfectividadPlantaciones   = "C_1_4_EfectividadPlantaciones";
 public static final String C_1_5_AreasPendReforestar       = "C_1_5_AreasPendReforestar";
 public static final String C_1_6_ArbolesAislados           = "C_1_6_ArbolesAislados";
 public static final String C_2_1_BosquesAfectadosInc       = "C_2_1_BosquesAfectadosInc";
// public static final String C_2_1_2_IncendEspecie           = "C_2_1_2_IncendEspecie";
 public static final String C_2_2_BosquesAfectados          = "C_2_2_BosquesAfectados";
 public static final String C_2_3_BosquesManejosSilvicolas  = "C_2_3_BosquesManejosSilvicolas";
 public static final String C_3_1_1_FormacionForestal       = "C_3_1_1_FormacionForestal";
 public static final String C_3_1_2_EspeciesEndemicas       = "C_3_1_2_EspeciesEndemicas";
 public static final String C_3_1_3_EjecProgramaProt        = "C_3_1_3_EjecProgramaProt";
 public static final String C_3_1_4_ProgEduCext             = "C_3_1_4_ProgEduCext";
 public static final String C_3_2_1_Presas                  = "C_3_2_1_Presas";
 public static final String C_3_2_2_MicroPresas             = "C_3_2_2_MicroPresas";
 public static final String C_3_2_3_CorrienteFluvial        = "C_3_2_3_CorrienteFluvial";
 public static final String C_3_3_FajaCostera               = "C_3_3_FajaCostera";
 public static final String C_3_4_AreasAfectActvMinera      = "C_3_4_AreasAfectActvMinera";
 public static final String C_4_1_IndRendSostenido          = "C_4_1_IndRendSostenido";
 public static final String C_4_2_PorcMadeXtrBosqNat        = "C_4_2_PorcMadeXtrBosqNat";
 public static final String C_4_3_Talas                     = "C_4_3_Talas";
 public static final String C_4_4_ProductForestalNoMad      = "C_4_4_ProductForestalNoMad";
 public static final String C_4_5_SistAgrosilvopast         = "C_4_5_SistAgrosilvopast";
 public static final String C_5_1_Salario                   = "C_5_1_Salario";
 public static final String C_5_2_Accidentes                = "C_5_2_Accidentes";
 public static final String C_5_4_Servicios                 = "C_5_4_Servicios";
 public static final String C_5_5_CategoriaOcupacional      = "C_5_5_CategoriaOcupacional";


 public static void mostrar_Grafico(String criterio, User user, BaseUnits bu) throws SQLException
 {   
        try {
            IGraphic graphic = (IGraphic) Class.forName("cif.graficos.clases."+criterio+"_Graph").newInstance();
            graphic.drawGraphic(user, bu);
        }

        catch (InstantiationException ex) {
            Logger.getLogger(GraphicManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GraphicManager.class.getName()).log(Level.SEVERE, null, ex);
        }         catch (ClassNotFoundException ex) {
            Logger.getLogger(GraphicManager.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}