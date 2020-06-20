/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author unityofdisaster
 */
@Entity
@IdClass(LlaveStock.class)
@Table(name = "Stock")
public class Stock implements Serializable{
    @Id
    private int idProducto;
    @Id
    private int idTienda;
    private int cantidad;

    public Stock() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Stock{" + "idProducto=" + idProducto + ", idTienda=" + idTienda + ", cantidad=" + cantidad + '}';
    }
    
    
}
