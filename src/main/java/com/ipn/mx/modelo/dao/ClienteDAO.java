/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ClienteDTO;
import com.ipn.mx.modelo.entidades.Cliente;
import com.ipn.mx.utilerias.HibernateUtil;
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
public class ClienteDAO {
    public void crear(ClienteDTO dto){
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

    public void actualizar(ClienteDTO dto){
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

    public void eliminar(ClienteDTO dto){
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

    public ClienteDTO leerUno(ClienteDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdCliente()));
            
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
        return dto;
    }

    public ClienteDTO leerCredenciales(ClienteDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            String hql = "FROM Cliente c WHERE c.usuario = :user";
            List result = session.createQuery(hql).setParameter("user", dto.getEntidad().getUsuario()).list();
            if(result.size() > 0 ){
                dto.setEntidad((Cliente)result.get(0));
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

    public List<ClienteDTO> leerTodos(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List aux = null;
        List<ClienteDTO> lista = new ArrayList<>();
        try{
            transaction.begin();
            
            Query q = session.createQuery("FROM Cliente");
            aux = q.list();
            for(int i = 0; i < aux.size(); i++){
                ClienteDTO dto = new ClienteDTO();
                dto.setEntidad((Cliente)aux.get(i));
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
        ClienteDTO dto = new ClienteDTO();
        ClienteDAO dao = new ClienteDAO();
        dto.getEntidad().setUsuario("fezodu");
        dto.getEntidad().setContrasena("pass");
        dto = dao.leerCredenciales(dto);
        System.out.println(dto);
    }
}
