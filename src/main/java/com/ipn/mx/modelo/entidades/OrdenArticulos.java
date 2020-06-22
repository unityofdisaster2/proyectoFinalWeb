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
@IdClass(LlaveOrdenArticulos.class)
@Table(name = "OrdenArticulos")
public class OrdenArticulos implements Serializable{
    @Id
    private int idOrden;
    @Id
    private int idProducto;
    private int iditem;
    private int cantidad;
    private double precio;

    public OrdenArticulos() {
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "OrdenArticulos{" + "idOrden=" + idOrden + ", idProducto=" + idProducto + ", iditem=" + iditem + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
    
    
}
