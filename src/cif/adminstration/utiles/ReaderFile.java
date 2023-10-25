/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class ReaderFile
{
    private File file;
    FileInputStream fistream;
    public ReaderFile(File file)
    {
        try
        {
            this.file = file;
            fistream = new FileInputStream(this.file);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(ReaderFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNextLine() throws IOException
    {
        while(true)
        {
            int data = fistream.read();
            StringBuilder sb = new StringBuilder();
            while(data != 10 && data != 13)
            {
                if(data != -1)
                {
                    sb.append((char)data);
                }
                else
                {
                    if(!sb.toString().equals(""))
                    {
                        return sb.toString();
                    }
                    return null;
                }
                data = fistream.read();
            }
            if(!sb.toString().equals(""))
            {
                return sb.toString();
            }
        }
    }
}
