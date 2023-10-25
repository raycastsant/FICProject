/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.graficos.interfaces;

import cif.reportes.clases.*;
import cif.manage.BaseUnits;
import cif.manage.User;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author Raisel
 */
public interface IGraphic
{ 
 public void drawGraphic(User user, BaseUnits bu) throws SQLException;
}
