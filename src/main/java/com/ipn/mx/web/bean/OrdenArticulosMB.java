/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.OrdenArticulosDAO;
import com.ipn.mx.modelo.dto.OrdenArticulosDTO;
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
public class OrdenArticulosMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of OrdenArticulosMB
     */
    private OrdenArticulosDTO dto;
    private OrdenArticulosDAO dao = new OrdenArticulosDAO();
    private List<OrdenArticulosDTO> listaOrdenArticulos;
    public OrdenArticulosMB() {
    }

    public OrdenArticulosDTO getDto() {
        return dto;
    }

    public void setDto(OrdenArticulosDTO dto) {
        this.dto = dto;
    }

    public OrdenArticulosDAO getDao() {
        return dao;
    }

    public void setDao(OrdenArticulosDAO dao) {
        this.dao = dao;
    }

    public List<OrdenArticulosDTO> getListaOrdenArticulos() {
        return listaOrdenArticulos;
    }

    public void setListaOrdenArticulos(List<OrdenArticulosDTO> listaOrdenArticulos) {
        this.listaOrdenArticulos = listaOrdenArticulos;
    }
    
    
    
    @PostConstruct
    public void init() {
        listaOrdenArticulos = new ArrayList<>();
        listaOrdenArticulos = dao.leerTodos();
    }

    public String prepareAdd() {
        dto = new OrdenArticulosDTO();
        setAccion(ACC_CREAR);
        return "/ordenArticulos/ordenArticulosForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/ordenArticulos/ordenArticulosForm?faces-redirect=true";
    }

    public String back() {
        init();
        return "/ordenArticulos/ordenArticulosForm?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/ordenArticulos/listaOrdenArticulos?faces-redirect=true";
    }

    public String crear() {
        try {
            dao.crear(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorCrearEvento", "Error al crear el evento");
            return "/ordenArticulos/listaOrdenArticulos?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            dao.actualizar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorActualizarOrden", "Error al actualizar orden");
            return "/ordenArticulos/listaOrdenArticulos?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorBorrarOrden", "Error al borrar orden");
            return "/ordenArticulos/listaOrdenArticulos?faces-redirect=true";
        }
    }
    
    public void seleccionarOrden(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new OrdenArticulosDTO();
        dto.getEntidad().setIdOrden(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
