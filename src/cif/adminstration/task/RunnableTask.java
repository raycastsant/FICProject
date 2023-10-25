/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.task;

import javax.swing.JOptionPane;

/**
 *
 * @author Pedro
 */
public abstract class RunnableTask implements Runnable
{
    private int step;
    protected static final String DATA_FILE = "Fichero de datos";
    protected static final String NOMENC_FILE = "Fichero de nomencladores";
    private ProgressObj progress = new ProgressObj();

    protected void showProgressBar()
    {
        progress.setVisible(true);
    }

    protected void SetInitialStep(int init_step)
    {
        progress.setMinimum(init_step);
    }

    protected void SetFinalStep(int final_step)
    {
        progress.setMaximum(final_step);
    }

    protected void SetDeterminatedProcess(boolean determinated)
    {
        progress.setIndeterminate(!determinated);
    }

    protected void SetStatusMessage(String message)
    {
        progress.setMessage(message);
    }

    protected void setValue(int value)
    {
        step = value;
        progress.setValue(step);
    }

    protected void reportStep()
    {
        step++;
        progress.setValue(step);
    }

    protected void Close()
    {
        progress.setVisible(false);
        progress.dispose();
    }

    protected void throwError(String message)
    {
        JOptionPane.showInternalMessageDialog(progress.getContentPane(),message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
