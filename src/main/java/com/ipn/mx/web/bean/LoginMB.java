/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.AdministradorDAO;
import com.ipn.mx.modelo.dao.ClienteDAO;
import com.ipn.mx.modelo.dto.AdministradorDTO;
import com.ipn.mx.modelo.dto.ClienteDTO;
import com.ipn.mx.utilerias.SessionUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author unityofdisaster
 */
@ManagedBean
@RequestScoped
public class LoginMB {

    /**
     * Creates a new instance of LoginMB
     */
    private SessionUtil session = new SessionUtil();
    private String usuario;
    private String pass;
    private ClienteDTO dtoCliente;
    private AdministradorDTO dtoAdmin;
    private final ClienteDAO daoCliente = new ClienteDAO();
    private final AdministradorDAO daoAdmin = new AdministradorDAO();

    public LoginMB() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    


    
    public String loginUsuario(){
        //comprobar credenciales
        dtoCliente = new ClienteDTO();
        dtoCliente.getEntidad().setUsuario(usuario);
        dtoCliente.getEntidad().setContrasena(pass);
        System.out.println(usuario + pass);
        ClienteDTO auxDto;
        auxDto = daoCliente.leerCredenciales(dtoCliente);
        if(auxDto != null){
            if(usuario.equals(auxDto.getEntidad().getUsuario()) && pass.equals(auxDto.getEntidad().getContrasena())){
                session.agregar("idCliente", dtoCliente.getEntidad().getIdCliente());
                session.agregar("nombreCliente", dtoCliente.getEntidad().getNombre());
                return "/clientes/indexCliente?faces-redirect=true";
            }            
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acceso denegado", "Credenciales incorrectas");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }

    public String loginAdmin(){
        //comprobar credenciales
        dtoAdmin = new AdministradorDTO();
        dtoAdmin.getEntidad().setUsuario(usuario);
        dtoAdmin.getEntidad().setContrasena(pass);
        System.out.println(usuario + pass);
        AdministradorDTO auxDto;
        auxDto = daoAdmin.leerCredenciales(dtoAdmin);
        if(auxDto != null){
            if(usuario.equals(auxDto.getEntidad().getUsuario()) && pass.equals(auxDto.getEntidad().getContrasena())){
                session.agregar("idAdmin", dtoAdmin.getEntidad().getIdAdministrador());
                session.agregar("nombreAdmin", dtoAdmin.getEntidad().getNombre());
                return "/administrador/indexAdministrador?faces-redirect=true";
            }            
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acceso denegado", "Credenciales incorrectas");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
    }
    
    public String logoutCliente(){
        session.borrar("idCliente");
        session.borrar("nombreCliente");
        return "/index?faces-redirect=true";
    }
    public String logoutAdmin(){
        session.borrar("idAdmin");
        session.borrar("nombreAdmin");
        return "index?faces-redirect=true";        
    }
    
}
