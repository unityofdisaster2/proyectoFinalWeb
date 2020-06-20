/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Tarjeta;
import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class TarjetaDTO implements Serializable{
    private Tarjeta entidad;

    public TarjetaDTO() {
        entidad = new Tarjeta();
    }

    public Tarjeta getEntidad() {
        return entidad;
    }

    public void setEntidad(Tarjeta entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "TarjetaDTO{" + "entidad=" + entidad + '}';
    }
    
    
    
}
