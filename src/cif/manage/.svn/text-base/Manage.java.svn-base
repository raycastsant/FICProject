/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.manage;

import cif.bd.BDUtilities;
import java.sql.SQLException;

/**
 *
 * @author Pedro
 */
public class Manage
{
    private static Manage manage = null;
    private static User user = null;
    private static boolean created = false;

    public static Manage getInstance()
    {
        if(manage == null)
        {
            manage = new Manage();
        }
        return manage;
    }

    private Manage()
    {
        created = true;
    }

    public static boolean isCreated()
    {
        return created;
    }
    
    public User initUser(String login, String password) throws SQLException
    {
        user = BDUtilities.getUser(login, password);
        return user;
    }

    public User getUser()
    {
        return user;
    }

}
