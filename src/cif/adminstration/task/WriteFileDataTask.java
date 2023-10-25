/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.task;

import cif.adminstration.utiles.Utils;
import cif.manage.Manage;
import cif.manage.UserTypes;
import com.twmacinta.util.MD5;
import java.io.File;
import java.sql.SQLException;

/**
 *
 * @author Pedro
 */
public class WriteFileDataTask extends RunnableTask
{
    String[] data_tables,secondary_tables;
    UserTypes type;
    File file;
    public WriteFileDataTask(File file)
    {
        this.file = file;
        type = Manage.getInstance().getUser().getType();
        if(type == UserTypes.Municipal)
        {
            data_tables = new String[]{"c1_1_ap_totalareacubierta","c1_1_otros_totalareacubierta","c1_1_us_totalareacubierta","c1_2_ap_indiceboscosidad","c1_2_otros_indiceboscosidad","c1_2_us_indiceboscosidad","c1_3_ap_relaciontac_scp","c1_3_otros_relaciontac_scp","c1_3_us_relaciontac_scp",
            "c1_4_ap_efectividadplantaciones","c1_4_otros_efectividadplantaciones","c1_4_us_efectividadplantaciones","c1_5_ap_areaspendreforestar","c1_5_otros_areaspendreforestar","c1_5_us_areaspendreforestar","c1_6_ap_arbolesaislados","c1_6_otros_arbolesaislados","c1_6_us_arbolesaislados","c2_1_ap_bosquesafectinc",
            "c2_1_ap_incespecie","c2_1_otros_bosquesafectinc","c2_1_otros_incespecie","c2_1_us_bosquesafectinc","c2_1_us_incespecie","c2_2_ap_bosquesafectados","c2_2_otros_bosquesafectados","c2_2_us_bosquesafectados","c2_3_ap_bosquesmanejslvicolas","c2_3_otros_bosquesmanejslvicolas","c2_3_us_bosquesmanejslvicolas",
            "c3_1_1_ap_formacionforestal","c3_1_1_otros_formacionforestal","c3_1_1_us_formacionforestal","c3_1_2_ap_especendem","c3_1_2_otros_especendem","c3_1_2_us_especendem","c3_1_3_ap_ejecprogprotecc","c3_1_3_otros_ejecprogprotec","c3_1_3_us_ejecprogprotec","c3_1_4_ap_progeducext","c3_1_4_otros_progeducext",
            "c3_1_4_us_progeducext","c3_2_1_ap_presas","c3_2_1_otros_presas","c3_2_1_us_presas","c3_2_2_ap_micropresas","c3_2_2_otros_micropresas","c3_2_2_us_micropresas","c3_2_3_ap_corrfluvial","c3_2_3_otros_corrfluvial","c3_2_3_us_corrfluvial","c3_3_ap_fajacostera","c3_3_otros_fajacostera","c3_3_us_fajacostera",
            "c3_4_ap_areasafectactvminera","c3_4_otros_areasafectactvminera","c3_4_us_areasafectactvminera","c4_1_ap_indrendsostenido","c4_1_otros_indrendsostenido","c4_1_us_indrendsostenido","c4_2_ap_porcmadextrbosqnat","c4_2_otros_porcmadextrbosqnat","c4_2_us_porcmadextrbosqnat","c4_3_ap_talas","c4_3_otros_talas","c4_3_us_talas",
            "c4_4_ap_prodfornomad","c4_4_otros_prodfornomad","c4_4_us_prodfornomad","c4_5_ap_sistagrosilvopast","c4_5_otros_sistagrosilvopast","c4_5_us_sistagrosilvopast","c5_1_ap_salario","c5_1_otros_salario","c5_1_us_salario","c5_2_ap_accidentes","c5_2_otros_accidentes","c5_2_us_accidentes","c5_4_ap_servicios","c5_4_otros_servicios",
            "c5_4_us_servicios","c5_5_ap_catgocupacional","c5_5_otros_catgocupacional","c5_5_us_catgocupacional"};
            secondary_tables = new String[]{"area_protegida","aprotg_mcpio","otros","otros_mcpio","usilvicola","usilv_mcpio"};
        }
        if(type == UserTypes.Provincial)
        {
            data_tables = new String[]{"c1_1_ap_totalareacubierta","c1_1_otros_totalareacubierta","c1_1_us_totalareacubierta","c1_2_ap_indiceboscosidad","c1_2_otros_indiceboscosidad","c1_2_us_indiceboscosidad","c1_3_ap_relaciontac_scp","c1_3_otros_relaciontac_scp","c1_3_us_relaciontac_scp",
            "c1_4_ap_efectividadplantaciones","c1_4_otros_efectividadplantaciones","c1_4_us_efectividadplantaciones","c1_5_ap_areaspendreforestar","c1_5_otros_areaspendreforestar","c1_5_us_areaspendreforestar","c1_6_ap_arbolesaislados","c1_6_otros_arbolesaislados","c1_6_us_arbolesaislados","c2_1_ap_bosquesafectinc",
            "c2_1_ap_incespecie","c2_1_otros_bosquesafectinc","c2_1_otros_incespecie","c2_1_us_bosquesafectinc","c2_1_us_incespecie","c2_2_ap_bosquesafectados","c2_2_otros_bosquesafectados","c2_2_us_bosquesafectados","c2_3_ap_bosquesmanejslvicolas","c2_3_otros_bosquesmanejslvicolas","c2_3_us_bosquesmanejslvicolas",
            "c3_1_1_ap_formacionforestal","c3_1_1_otros_formacionforestal","c3_1_1_us_formacionforestal","c3_1_2_ap_especendem","c3_1_2_otros_especendem","c3_1_2_us_especendem","c3_1_3_ap_ejecprogprotecc","c3_1_3_otros_ejecprogprotec","c3_1_3_us_ejecprogprotec","c3_1_4_ap_progeducext","c3_1_4_otros_progeducext",
            "c3_1_4_us_progeducext","c3_2_1_ap_presas","c3_2_1_otros_presas","c3_2_1_us_presas","c3_2_2_ap_micropresas","c3_2_2_otros_micropresas","c3_2_2_us_micropresas","c3_2_3_ap_corrfluvial","c3_2_3_otros_corrfluvial","c3_2_3_us_corrfluvial","c3_3_ap_fajacostera","c3_3_otros_fajacostera","c3_3_us_fajacostera",
            "c3_4_ap_areasafectactvminera","c3_4_otros_areasafectactvminera","c3_4_us_areasafectactvminera","c4_1_ap_indrendsostenido","c4_1_otros_indrendsostenido","c4_1_us_indrendsostenido","c4_2_ap_porcmadextrbosqnat","c4_2_otros_porcmadextrbosqnat","c4_2_us_porcmadextrbosqnat","c4_3_ap_talas","c4_3_otros_talas","c4_3_us_talas",
            "c4_4_ap_prodfornomad","c4_4_otros_prodfornomad","c4_4_us_prodfornomad","c4_5_ap_sistagrosilvopast","c4_5_otros_sistagrosilvopast","c4_5_us_sistagrosilvopast","c5_1_ap_salario","c5_1_otros_salario","c5_1_us_salario","c5_2_ap_accidentes","c5_2_otros_accidentes","c5_2_us_accidentes","c5_4_ap_servicios","c5_4_otros_servicios",
            "c5_4_us_servicios","c5_5_ap_catgocupacional","c5_5_otros_catgocupacional","c5_5_us_catgocupacional"};
            secondary_tables = new String[]{"area_protegida","aprotg_mcpio","otros","otros_mcpio","efi","efi_mcpio","usilvicola","usilv_mcpio"};
        }
        if(type == UserTypes.Unidad_Silvicola)
        {
            data_tables = new String[]{"c1_1_us_totalareacubierta","c1_2_us_indiceboscosidad","c1_3_us_relaciontac_scp","c1_4_us_efectividadplantaciones","c1_5_us_areaspendreforestar","c1_6_us_arbolesaislados","c2_1_us_bosquesafectinc","c2_1_us_incespecie","c2_2_us_bosquesafectados","c2_3_us_bosquesmanejslvicolas",
            "c3_1_1_us_formacionforestal","c3_1_2_us_especendem","c3_1_3_us_ejecprogprotec","c3_1_4_us_progeducext","c3_2_1_us_presas","c3_2_2_us_micropresas","c3_2_3_us_corrfluvial","c3_3_us_fajacostera","c3_4_us_areasafectactvminera","c4_1_us_indrendsostenido","c4_2_us_porcmadextrbosqnat","c4_3_us_talas",
            "c4_4_us_prodfornomad","c4_5_us_sistagrosilvopast","c5_1_us_salario","c5_2_us_accidentes","c5_4_us_servicios","c5_5_us_catgocupacional"};
            secondary_tables = new String[0];
        }
        if(type == UserTypes.Area_Protegida)
        {
            data_tables = new String[]{"c1_1_ap_totalareacubierta","c1_2_ap_indiceboscosidad","c1_3_ap_relaciontac_scp","c1_4_ap_efectividadplantaciones","c1_5_ap_areaspendreforestar","c1_6_ap_arbolesaislados","c2_1_ap_bosquesafectinc","c2_1_ap_incespecie","c2_2_ap_bosquesafectados","c2_3_ap_bosquesmanejslvicolas",
            "c3_1_1_ap_formacionforestal","c3_1_2_ap_especendem","c3_1_3_ap_ejecprogprotec","c3_1_4_ap_progeducext","c3_2_1_ap_presas","c3_2_2_ap_micropresas","c3_2_3_ap_corrfluvial","c3_3_ap_fajacostera","c3_4_ap_areasafectactvminera","c4_1_ap_indrendsostenido","c4_2_ap_porcmadextrbosqnat","c4_3_ap_talas",
            "c4_4_ap_prodfornomad","c4_5_ap_sistagrosilvopast","c5_1_ap_salario","c5_2_ap_accidentes","c5_4_ap_servicios","c5_5_ap_catgocupacional"};
            secondary_tables = new String[0];
        }
        SetStatusMessage("Exportando datos .....");
        SetInitialStep(0);
        SetFinalStep(data_tables.length);
        SetDeterminatedProcess(true);
        setValue(0);
        showProgressBar();
    }

    public void run()
    {
        String aux = new MD5(DATA_FILE).asHex();
        aux += "\n\r";
        if(type == UserTypes.Municipal)
        {
            aux += Utils.DeleteSentenceToFileData(type, "area_protegida","aprotg_mcpio");
            aux += "\n\r";
            aux += Utils.DeleteSentenceToFileData(type, "otros","otros_mcpio");
            aux += "\n\r";
            aux += Utils.DeleteSentenceToFileData(type, "usilvicola","usilv_mcpio");
            aux += "\n\r";
        }
        if(type == UserTypes.Provincial)
        {
            aux += Utils.DeleteSentenceToFileData(type, "area_protegida","aprotg_mcpio");
            aux += "\n\r";
            aux += Utils.DeleteSentenceToFileData(type, "otros","otros_mcpio");
            aux += "\n\r";
            aux += Utils.DeleteSentenceToFileData(type, "efi","efi_mcpio");
            aux += "\n\r";
        }
        if(type == UserTypes.Unidad_Silvicola)
        {
            for(int i=0; i<data_tables.length; i++)
            {
                aux += Utils.DeleteSentenceToFileData(type, data_tables[i], null);
                aux += "\n\r";
            }
        }
        if(type == UserTypes.Area_Protegida)
        {
            for(int i=0; i<data_tables.length; i++)
            {
                aux += Utils.DeleteSentenceToFileData(type, data_tables[i], null);
                aux += "\n\r";
            }
        }
        Utils.WriteFile(file, aux.getBytes(),false);
        try
        {
            //a partir de aqui se comienza a insertar
            for(int i=0; i<secondary_tables.length; i+=2)
            {
                aux = Utils.InsertSentence(secondary_tables[i],type,secondary_tables[i+1]);
                aux += Utils.InsertSentence(null,type,secondary_tables[i+1]);
                Utils.WriteFile(file, aux.getBytes(),true);
                reportStep();
            }
            for(int i=0; i<data_tables.length; i++)
            {
                aux = Utils.InsertSentence(data_tables[i],type,null);
                Utils.WriteFile(file, aux.getBytes(),true);
                reportStep();
            }
            Close();
        }
        catch(SQLException exc)
        {
            throwError(exc.getMessage());
        }
        
    }
}
