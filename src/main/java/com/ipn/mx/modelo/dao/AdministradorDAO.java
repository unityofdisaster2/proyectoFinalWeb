/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AdministradorDTO;
import com.ipn.mx.modelo.entidades.Administrador;
import com.ipn.mx.utilerias.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author unityofdisaster
 */
public class AdministradorDAO implements Serializable{
    public void crear(AdministradorDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            session.save(dto.getEntidad());
            
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
    }

    public void actualizar(AdministradorDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            session.update(dto.getEntidad());
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
    }

    public void eliminar(AdministradorDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            session.delete(dto.getEntidad());
            
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
    }

    public AdministradorDTO leerUno(AdministradorDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdAdministrador()));
            
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
        return dto;
    }

    public AdministradorDTO leerCredenciales(AdministradorDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            String hql = "FROM Administrador a WHERE a.usuario = :user";
            List result = session.createQuery(hql).setParameter("user", dto.getEntidad().getUsuario()).list();
            if(result.size() > 0 ){
                dto.setEntidad((Administrador)result.get(0));
            }else{
                dto = null;
            }
            
            
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
        return dto;
    }

    
    
    public List<AdministradorDTO> leerTodos(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List aux = null;
        List<AdministradorDTO> lista = new ArrayList<>();
        try{
            transaction.begin();
            
            Query q = session.createQuery("FROM Administrador");
            aux = q.list();
            for(int i = 0; i < aux.size(); i++){
                AdministradorDTO dto = new AdministradorDTO();
                dto.setEntidad((Administrador)aux.get(i));
                lista.add(dto);
            }
            
            
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
        return lista;
    }
    
}
