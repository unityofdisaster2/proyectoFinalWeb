/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.StockDAO;
import com.ipn.mx.modelo.dto.StockDTO;
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
public class StockMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of StockMB
     */
    
    private StockDTO dto;
    private StockDAO dao = new StockDAO();
    private List<StockDTO> listaStock;
    public StockMB() {
    }

    public StockDTO getDto() {
        return dto;
    }

    public void setDto(StockDTO dto) {
        this.dto = dto;
    }

    public StockDAO getDao() {
        return dao;
    }

    public void setDao(StockDAO dao) {
        this.dao = dao;
    }

    public List<StockDTO> getListaStock() {
        return listaStock;
    }

    public void setListaStock(List<StockDTO> listaStock) {
        this.listaStock = listaStock;
    }
    

    
    @PostConstruct
    public void init() {
        listaStock = new ArrayList<>();
        listaStock = dao.leerTodos();
    }

    public String prepareAdd() {
        dto = new StockDTO();
        setAccion(ACC_CREAR);
        return "/stock/stockForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/stock/stockForm?faces-redirect=true";
    }

    public String back() {
        init();
        return "/stock/listaStock?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/stock/listaStock?faces-redirect=true";
    }

    public String crear() {
        try {
            dao.crear(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorCrearStock", "Error al crear stock");
            return "/stock/listaStock?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            dao.actualizar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorActualizarStock", "Error al actualizar Stock");
            return "/stock/listaStock?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorBorrarStock", "Error al borrar Stock");
            return "/stock/listaStock?faces-redirect=true";
        }
    }
    
    public void seleccionarStock(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new StockDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    
}
