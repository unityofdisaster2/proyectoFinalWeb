/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Stock;
import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class StockDTO implements Serializable{
    private Stock entidad;

    public StockDTO() {
        entidad = new Stock();
    }

    public Stock getEntidad() {
        return entidad;
    }

    public void setEntidad(Stock entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "StockDTO{" + "entidad=" + entidad + '}';
    }
    
    
    
    
}
