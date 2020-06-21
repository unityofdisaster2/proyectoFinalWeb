/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.AdministradorDAO;
import com.ipn.mx.modelo.dto.AdministradorDTO;
import java.io.Serializable;
import java.util.List;
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
public class AdministradorMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of AdministradorMB
     */
    
    private AdministradorDTO dto;
    private AdministradorDAO dao = new AdministradorDAO();
    private List<AdministradorDTO> listaAdmins;
    
    public AdministradorMB() {
    }

    public AdministradorDTO getDto() {
        return dto;
    }

    public void setDto(AdministradorDTO dto) {
        this.dto = dto;
    }

    public AdministradorDAO getDao() {
        return dao;
    }

    public void setDao(AdministradorDAO dao) {
        this.dao = dao;
    }

    public List<AdministradorDTO> getListaAdmins() {
        return listaAdmins;
    }

    public void setListaAdmins(List<AdministradorDTO> lista) {
        this.listaAdmins = lista;
    }
    
    public String prepareAdd() {
        dto = new AdministradorDTO();
        setAccion(ACC_CREAR);
        return "/administrador/administradorForm?faces-redirect=true";

    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/administrador/administradorForm?faces-redirect=true";
    }

    public String add() {
        try {
            dao.crear(dto);
            return "/general/loginPage?faces-redirect=true";
        } catch (Exception e) {
            error("errorCrearCliente", "Error al crear cliente");
            return "/administrador/administradorForm?faces-redirect=true";
        }

    }

    public String update() {
        try {
            dao.actualizar(dto);
            return "/general/loginPage?faces-redirect=true";
        } catch (Exception e) {
            error("errorActualizarCliente", "Error al actualizar cliente");
            return "/administrador/administradorForm?faces-redirect=true";
        }

    }
    
    public String borrar() {
        try {
            dao.eliminar(dto);
            return "/general/loginPage?faces-redirect=true";
        } catch (Exception e) {
            error("errorBorrarCliente", "Error al borrar el cliente");
            return "/general/loginPage?faces-redirect=true";
        }
    }
    
    public String indexRedirect(){
        return "/general/loginPage?faces-redirect=true";
    }
    
    public void seleccionarAdministrador(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new AdministradorDTO();
        dto.getEntidad().setIdAdministrador(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
    
}
