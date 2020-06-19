/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Orden;
import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class OrdenDTO implements Serializable{
    private Orden entidad;

    public OrdenDTO() {
        entidad = new Orden();
    }

    public Orden getEntidad() {
        return entidad;
    }

    public void setEntidad(Orden entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "OrdenDTO{" + "entidad=" + entidad + '}';
    }
    
    
    
}
