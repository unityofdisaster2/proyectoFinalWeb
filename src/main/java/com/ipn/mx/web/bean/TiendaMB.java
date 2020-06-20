/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;


import com.ipn.mx.modelo.dao.TiendaDAO;
import com.ipn.mx.modelo.dto.TiendaDTO;
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
public class TiendaMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of TiendaMB
     */
    private TiendaDTO dto;
    private TiendaDAO dao;
    private List<TiendaDTO> listaTiendas;
    public TiendaMB() {
    }

    public TiendaDTO getDto() {
        return dto;
    }

    public void setDto(TiendaDTO dto) {
        this.dto = dto;
    }

    public TiendaDAO getDao() {
        return dao;
    }

    public void setDao(TiendaDAO dao) {
        this.dao = dao;
    }

    public List<TiendaDTO> getListaTiendas() {
        return listaTiendas;
    }

    public void setListaTiendas(List<TiendaDTO> listaTiendas) {
        this.listaTiendas = listaTiendas;
    }
    
    
    @PostConstruct
    public void init() {
        listaTiendas = new ArrayList<>();
        listaTiendas = dao.leerTodos();
    }

    public String prepareAdd() {
        dto = new TiendaDTO();
        setAccion(ACC_CREAR);
        return "/tiendas/tiendaForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/tiendas/tiendaForm?faces-redirect=true";
    }

    public String back() {
        init();
        return "/tiendas/listaTiendas?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/tiendas/listaTiendas?faces-redirect=true";
    }

    public String crear() {
        try {
            dao.crear(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorCrearTienda", "Error al crear tienda");
            return "/tiendas/listaTiendas?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            dao.actualizar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorActualizarTienda", "Error al actualizar tienda");
            return "/tiendas/listaTiendas?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorBorrarTienda", "Error al borrar tienda");
            return "/tiendas/listaTiendas?faces-redirect=true";
        }
    }
    
    public void seleccionarTienda(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new TiendaDTO();
        dto.getEntidad().setIdTienda(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
}
