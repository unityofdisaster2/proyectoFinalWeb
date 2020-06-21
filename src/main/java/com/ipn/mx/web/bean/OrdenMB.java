/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.OrdenDAO;
import com.ipn.mx.modelo.dto.OrdenDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author unityofdisaster
 */
@ManagedBean
@SessionScoped
public class OrdenMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of OrdenMB
     */
    
    private OrdenDTO dto;
    private OrdenDAO dao = new OrdenDAO();
    private List<OrdenDTO> listaOrdenes;
    public OrdenMB() {
    }

    public OrdenDTO getDto() {
        return dto;
    }

    public void setDto(OrdenDTO dto) {
        this.dto = dto;
    }

    public OrdenDAO getDao() {
        return dao;
    }

    public void setDao(OrdenDAO dao) {
        this.dao = dao;
    }

    public List<OrdenDTO> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(List<OrdenDTO> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }
    
    @PostConstruct
    public void init() {
        listaOrdenes = new ArrayList<>();
        listaOrdenes = dao.leerTodos();
    }

    public String prepareAdd() {
        dto = new OrdenDTO();
        setAccion(ACC_CREAR);
        return "/ordenes/ordenForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/ordenes/ordenForm?faces-redirect=true";
    }

    public String back() {
        init();
        return "/ordenes/listaOrdenes?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/ordenes/listaOrdenes?faces-redirect=true";
    }

    public String crear() {
        try {
            dao.crear(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorCrearEvento", "Error al crear el evento");
            return "/ordenes/listaOrdenes?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            dao.actualizar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorActualizarOrden", "Error al actualizar orden");
            return "/ordenes/listaOrdenes?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorBorrarOrden", "Error al borrar orden");
            return "/ordenes/listaOrdenes?faces-redirect=true";
        }
    }
    
    public void seleccionarOrden(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new OrdenDTO();
        dto.getEntidad().setIdOrden(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
}
