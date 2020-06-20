/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Staff;
import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class StaffDTO implements Serializable{
    private Staff entidad;

    public StaffDTO() {
        entidad = new Staff();
    }

    public Staff getEntidad() {
        return entidad;
    }

    public void setEntidad(Staff entidad) {
        this.entidad = entidad;
    }
    
    
    
    
}
