/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.reportes.clases;

import cif.manage.BaseUnits;
import cif.manage.User;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author Raisel
 */
public interface IReportModel
{ 
 public JTable getModel(User user, Integer anno, boolean desglose, BaseUnits bu) throws SQLException;
}
