/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.TarjetaDAO;
import com.ipn.mx.modelo.dto.TarjetaDTO;
import java.io.Serializable;
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
public class TarjetaMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of TarjetaMB
     */
    
    private TarjetaDTO dto;
    private TarjetaDAO dao = new TarjetaDAO();
    private List<TarjetaDTO> listaTarjetas;
    public TarjetaMB() {
    }

    public TarjetaDTO getDto() {
        return dto;
    }

    public void setDto(TarjetaDTO dto) {
        this.dto = dto;
    }

    public TarjetaDAO getDao() {
        return dao;
    }

    public void setDao(TarjetaDAO dao) {
        this.dao = dao;
    }

    public List<TarjetaDTO> getListaTarjetas() {
        return listaTarjetas;
    }

    public void setListaTarjetas(List<TarjetaDTO> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }


    public String prepareAdd() {
        dto = new TarjetaDTO();
        setAccion(ACC_CREAR);
        return "/tarjetas/tarjetaForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/tarjetas/tarjetaForm?faces-redirect=true";
    }

    public String back() {
        return "/tarjetas/listaTarjetas?faces-redirect=true";
    }

    public String prepareIndex() {
        return "/tarjetas/listaTarjetas?faces-redirect=true";
    }

    public String crear() {
        try {
            dao.crear(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorCrearTarjeta", "Error al crear tarjeta");
            return "/tarjeta/listaTarjetas?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            dao.actualizar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorActualizarTarjeta", "Error al actualizar tarjeta");
            return "/tarjetas/listaTarjetas?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorBorrarTarjeta", "Error al borrar tarjeta");
            return "/tarjetas/listaTarjetas?faces-redirect=true";
        }
    }
    
    public void seleccionarTarjeta(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new TarjetaDTO();
        dto.getEntidad().setIdTarjeta(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
}
