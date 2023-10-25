/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.task;

import cif.adminstration.utiles.ReaderFile;
import cif.bd.BDUtilities;
import com.twmacinta.util.MD5;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Pedro
 */
public class LoadFileTask extends RunnableTask
{
    protected File file;
    protected int file_type;
    protected ReaderFile rf;
    public static final int DATOS=0;
    public static final int NOMENCLADORES=1;

    public LoadFileTask(File file,int f_type)
    {
        this.file = file;
        file_type = f_type;
        rf = new ReaderFile(this.file);
        SetStatusMessage("Importando datos .....");
        SetInitialStep(0);
        SetFinalStep(1);
        SetDeterminatedProcess(false);
        setValue(0);
        showProgressBar();
    }

    public void run()
    {
        try
        {
            String line = rf.getNextLine();
            if(line != null)
            {
                if(file_type == DATOS)
                {
                    String s = new MD5(DATA_FILE).asHex();
                    if(!line.equals(s))
                    {
                        throwError("El fichero seleccionado no es un fichero de actualización de datos");
                        return;
                    }
                }
                else
                {
                    if(!line.equals(new MD5(NOMENC_FILE).asHex()))
                    {
                        throwError("El fichero seleccionado no es un fichero de actualización de nomencladores");
                        return;
                    }
                }
            }
            line = rf.getNextLine();
            while(line != null)
            {
                BDUtilities.executeQuery(line);
                line = rf.getNextLine();
            }
            reportStep();
            Close();
        }
        catch (Exception ex)
        {
            throwError(ex.getMessage());
        }
    }
}
