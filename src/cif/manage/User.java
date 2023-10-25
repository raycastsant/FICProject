/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cif.manage;

import cif.bd.BDUtilities;

/**
 *
 * @author Pedro
 */
public class User
{
    private String login = null;
    private UserTypes type = null;
    private String municipio = null;
    private String provincia = null;
    private String entidad = null; // si entidad es null es porque el usuario es nacional

    public User(String user, int tipo, String munic, String prov)
    {
        login = user;
        type = UserTypes.values()[tipo-1];
        municipio = munic;
        provincia = prov;
        entidad = BDUtilities.getIdEntidad(login, type);
        if(type==UserTypes.Municipal)
        {
            entidad = municipio;
        }
        else if(type==UserTypes.Provincial)
        {
            entidad = provincia;
        }
    }
    public String getUser()
    {
        return login;
    }

    public UserTypes getType()
    {
        return type;
    }

    public String getMunicipio()
    {
        return municipio;
    }

    public String getProvincia()
    {
        return provincia;
    }

    public String getIdEntidad()
    {
        return entidad;
    }
}
