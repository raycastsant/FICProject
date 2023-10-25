/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.adminstration.utiles;

import cif.gui.JF_Principal;
import cif.utiles.Manejable;

/**
 *
 * @author Pedro
 */
public abstract class NomencManejable extends Manejable
{
    public NomencManejable(JF_Principal parent)
    {
        super(parent);
    }

    @Override
    public NomencManejable Clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
