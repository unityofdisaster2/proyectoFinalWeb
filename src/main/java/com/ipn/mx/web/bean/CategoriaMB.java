/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
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
public class CategoriaMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of CategoriaMB
     */
    
    private CategoriaDTO dto;
    private CategoriaDAO dao = new CategoriaDAO();
    private List<CategoriaDTO> listaCategorias;

    public CategoriaDTO getDto() {
        return dto;
    }

    public void setDto(CategoriaDTO dto) {
        this.dto = dto;
    }

    public CategoriaDAO getDao() {
        return dao;
    }

    public void setDao(CategoriaDAO dao) {
        this.dao = dao;
    }

    public List<CategoriaDTO> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaDTO> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    public CategoriaMB() {
    }
    
    @PostConstruct
    public void init(){
        listaCategorias = new ArrayList<>();
        listaCategorias = dao.leerTodos();
        
    }
    
    public String prepareAdd() {
        dto = new CategoriaDTO();
        setAccion(ACC_CREAR);
        return "/categorias/categoriaForm?faces-redirect-true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/categorias/categoriaForm?faces-redirect-true";
    }

    public String back() {
        init();
        return "/categorias/listaCategorias?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/categorias/listaCategorias?faces-redirect=true";
    }

    public String crear() {
        try {
            dao.crear(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorCrearEvento", "Error al crear el evento");
            return "/categorias/listaCategorias?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            dao.actualizar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorActualizarEvento", "Error al actualizar el evento");
            return "/categorias/listaCategorias?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorBorrarEvento", "Error al borrar el evento");
            return "/categorias/listaCategorias?faces-redirect=true";
        }
    }

    public void seleccionarCategoria(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
    
}
