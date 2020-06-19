/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Administrador;
import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class AdministradorDTO implements Serializable{
    private Administrador entidad;

    public AdministradorDTO() {
        entidad = new Administrador();
    }

    public Administrador getEntidad() {
        return entidad;
    }

    public void setEntidad(Administrador entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "AdministradrorDTO{" + "entidad=" + entidad + '}';
    }
    
    
    
    
}
