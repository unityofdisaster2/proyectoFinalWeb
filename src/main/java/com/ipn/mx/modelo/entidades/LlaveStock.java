/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

/**
 *
 * @author unityofdisaster
 */
public class LlaveStock implements Serializable{
    private int idProducto;
    private int idTienda;

    public LlaveStock(int idProducto, int idTienda) {
        this.idProducto = idProducto;
        this.idTienda = idTienda;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LlaveStock other = (LlaveStock) obj;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idTienda != other.idTienda) {
            return false;
        }
        return true;
    }
    
    
    
    

}
