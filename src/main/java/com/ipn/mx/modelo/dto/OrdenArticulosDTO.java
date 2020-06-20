/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.OrdenArticulos;
import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class OrdenArticulosDTO implements Serializable{
    private OrdenArticulos entidad;

    public OrdenArticulosDTO() {
        entidad = new OrdenArticulos();
    }

    public OrdenArticulos getEntidad() {
        return entidad;
    }

    public void setEntidad(OrdenArticulos entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "OrdenArticulosDTO{" + "entidad=" + entidad + '}';
    }
    
    
    
    
}
