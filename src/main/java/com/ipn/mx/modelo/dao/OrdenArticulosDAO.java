/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.OrdenArticulosDTO;
import com.ipn.mx.modelo.entidades.OrdenArticulos;
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
public class OrdenArticulosDAO implements Serializable{
    public void crear(OrdenArticulosDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            System.out.println("llega aqui");
            session.save(dto.getEntidad());
            System.out.println("aqui no");
            transaction.commit();
            
        }
        catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void actualizar(OrdenArticulosDTO dto){
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

    public void eliminar(OrdenArticulosDTO dto){
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

    public OrdenArticulosDTO leerUno(OrdenArticulosDTO dto){
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

    public List<OrdenArticulosDTO> leerTodos(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List aux = null;
        List<OrdenArticulosDTO> lista = new ArrayList<>();
        try{
            transaction.begin();
            
            Query q = session.createQuery("FROM OrdenArticulos");
            aux = q.list();
            for(int i = 0; i < aux.size(); i++){
                OrdenArticulosDTO dto = new OrdenArticulosDTO();
                dto.setEntidad((OrdenArticulos)aux.get(i));
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

    public List<OrdenArticulosDTO> leerRegistrosOrden(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List aux = null;
        List<OrdenArticulosDTO> lista = new ArrayList<>();
        try{
            transaction.begin();
            
            Query q = session.createQuery("FROM OrdenArticulos WHERE idOrden= :idOrd").setParameter("idOrd", id);
            aux = q.list();
            for(int i = 0; i < aux.size(); i++){
                OrdenArticulosDTO dto = new OrdenArticulosDTO();
                dto.setEntidad((OrdenArticulos)aux.get(i));
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
    
    
    
    
    public static void main(String[] args) {
        OrdenArticulosDTO dto = new OrdenArticulosDTO();
        OrdenArticulosDAO dao = new OrdenArticulosDAO();
        
        dto.getEntidad().setCantidad(1);
        dto.getEntidad().setIdOrden(2);
        dto.getEntidad().setIdProducto(2);
        dto.getEntidad().setIditem(2);
        dto.getEntidad().setPrecio(100);
        
        dao.crear(dto);
        
        System.out.println(dao.leerTodos());
        
    }
}
