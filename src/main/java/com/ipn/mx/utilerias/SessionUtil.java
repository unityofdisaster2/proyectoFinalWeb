/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author unityofdisaster
 */
public class SessionUtil implements Serializable{
    public void agregar(String llave, Object valor){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(llave, valor);
    }
    
    public void obtener(String llave){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(llave);
    }
    
    public void borrar(String llave){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(llave);
    }
}
