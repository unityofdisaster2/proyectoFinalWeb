/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.OrdenDTO;
import com.ipn.mx.modelo.entidades.Orden;
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
public class OrdenDAO implements Serializable{
    public void crear(OrdenDTO dto){
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

    public void actualizar(OrdenDTO dto){
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

    public void eliminar(OrdenDTO dto){
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

    public OrdenDTO leerUno(OrdenDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdOrden()));
            
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
        return dto;
    }

    public List<OrdenDTO> leerTodos(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List aux = null;
        List<OrdenDTO> lista = new ArrayList<>();
        try{
            transaction.begin();
            
            Query q = session.createQuery("FROM Orden");
            aux = q.list();
            for(int i = 0; i < aux.size(); i++){
                OrdenDTO dto = new OrdenDTO();
                dto.setEntidad((Orden)aux.get(i));
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
