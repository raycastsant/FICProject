/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.task;

import cif.adminstration.utiles.Utils;
import cif.manage.UserTypes;
import com.twmacinta.util.MD5;
import java.io.File;
import java.sql.SQLException;

/**
 *
 * @author Pedro
 */
public class WriteFileNomencTask extends RunnableTask
{
    String[] iu_tables;
    String[] delete_tables;
    String[] insert_tables;
    UserTypes type;
    File file;
    public WriteFileNomencTask(File file)
    {
        iu_tables=new String[]{"c3_1_1_formacionforestal","c3_1_2_especendem","c3_1_3_ejecprogprotec","c3_1_4_progeducext","c4_3_talas","c4_4_prodfornomad","c5_4_servicios","c5_5_catgocupacional","especie","provincias","municipios","significacion"};
        delete_tables = new String[]{"tipo_usuario"};
        insert_tables = new String[]{"tipo_usuario","usuarios","usuario_ap","usuario_us","usuario_efi"};
        this.file = file;
        SetStatusMessage("Exportando nomencladores .....");
        SetInitialStep(0);
        SetFinalStep(iu_tables.length+insert_tables.length);
        SetDeterminatedProcess(true);
        setValue(0);
        showProgressBar();
    }

    public void run() 
    {
        String aux = new MD5(NOMENC_FILE).asHex();
        aux += "\n\r";
        for(int i=0; i<delete_tables.length; i++)
        {
            aux += String.format("delete from %s",delete_tables[i]);
            aux += "\n\r";
        }
        Utils.WriteFile(file, aux.getBytes(), false);
        aux="";
        try
        {
            for(int i=0; i<iu_tables.length; i++)
            {
                aux += Utils.InsertUpdateSentence(iu_tables[i]);
                reportStep();
            }
            Utils.WriteFile(file, aux.getBytes(), true);
            aux="";
            for(int i=0; i<insert_tables.length; i++)
            {
                aux += Utils.InsertSentence(insert_tables[i],type,null);
                reportStep();
            }
            Utils.WriteFile(file, aux.getBytes(), true);
            Close();
        }
        catch(SQLException exc)
        {
            throwError(exc.getMessage());
        }
    }
}
