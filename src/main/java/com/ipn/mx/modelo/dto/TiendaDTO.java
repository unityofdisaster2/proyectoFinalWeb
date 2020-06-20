/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Tienda;
import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class TiendaDTO implements Serializable{
    private Tienda entidad;

    public TiendaDTO() {
        entidad = new Tienda();
    }

    public Tienda getEntidad() {
        return entidad;
    }

    public void setEntidad(Tienda entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "TiendaDTO{" + "entidad=" + entidad + '}';
    }
    
    
    
    
    
}
