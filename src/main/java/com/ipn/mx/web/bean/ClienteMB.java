/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.ClienteDAO;
import com.ipn.mx.modelo.dto.ClienteDTO;

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
public class ClienteMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ClienteMB
     */
    private ClienteDTO dto;
    private ClienteDAO dao = new ClienteDAO();
    private List<ClienteDTO> listaClientes;

    public ClienteMB() {
    }

    public ClienteDTO getDto() {
        return dto;
    }

    public void setDto(ClienteDTO dto) {
        this.dto = dto;
    }

    public ClienteDAO getDao() {
        return dao;
    }

    public void setDao(ClienteDAO dao) {
        this.dao = dao;
    }

    public List<ClienteDTO> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<ClienteDTO> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String prepareAdd() {
        dto = new ClienteDTO();
        setAccion(ACC_CREAR);
        return "/clientes/clienteForm";

    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/clientes/clienteForm?faces-redirect=true";
    }

    public String add() {
        try {
            dao.crear(dto);
            return "/general/loginPage?faces-redirect=true";
        } catch (Exception e) {
            error("errorCrearCliente", "Error al crear cliente");
            return "/clientes/clienteForm?faces-redirect=true";
        }

    }

    public String update() {
        try {
            dao.actualizar(dto);
            return "/general/loginPage?faces-redirect=true";
        } catch (Exception e) {
            error("errorActualizarCliente", "Error al actualizar cliente");
            return "/clientes/clienteForm?faces-redirect=true";
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
    
    public void seleccionarCliente(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new ClienteDTO();
        dto.getEntidad().setIdCliente(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
}