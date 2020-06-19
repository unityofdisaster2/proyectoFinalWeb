/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import java.io.DataInputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

/**
 *
 * @author unityofdisaster
 */
@ManagedBean
@SessionScoped
public class ProductoMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ProductoMB
     */
    private ProductoDTO dto;
    private ProductoDAO dao = new ProductoDAO();
    private List<ProductoDTO> listaProductos;
    private Part archivo;
    
    
    public ProductoMB() {
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }
    
    
    public ProductoDTO getDto() {
        return dto;
    }

    public void setDto(ProductoDTO dto) {
        this.dto = dto;
    }

    public ProductoDAO getDao() {
        return dao;
    }

    public void setDao(ProductoDAO dao) {
        this.dao = dao;
    }

    public List<ProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }
    


    @PostConstruct
    public void init() {
        listaProductos = new ArrayList<>();
        listaProductos = dao.leerTodos();
    }

    public String prepareAdd() {
        dto = new ProductoDTO();
        setAccion(ACC_CREAR);
        return "/productos/productoForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/productos/productoForm?faces-redirect=true";
    }

    public String back() {
        init();
        return "/productos/listaProductos?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/productos/listaProductos?faces-redirect=true";
    }

    public String crear() {
        try {
            byte[] bytes = new byte[(int)archivo.getSize()];
            DataInputStream dis = new DataInputStream(archivo.getInputStream());
            dis.readFully(bytes);
            dto.getEntidad().setImagen(bytes);
            dao.crear(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorCrearEvento", "Error al crear el evento");
            return "/productos/listaProductos?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            dao.actualizar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorActualizarEvento", "Error al actualizar el evento");
            return "/productos/listaProductos?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorBorrarEvento", "Error al borrar el evento");
            return "/productos/listaProductos?faces-redirect=true";
        }
    }
    
    public void seleccionarProducto(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
