/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Cliente;
import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class ClienteDTO implements Serializable{
    private Cliente entidad;

    public ClienteDTO() {
        entidad = new Cliente();
    }

    public Cliente getEntidad() {
        return entidad;
    }

    public void setEntidad(Cliente entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "entidad=" + entidad + '}';
    }
    
    
}
