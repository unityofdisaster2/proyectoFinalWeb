/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.entidades.Producto;
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
public class ProductoDAO implements Serializable{
    public void crear(ProductoDTO dto){
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

    public void actualizar(ProductoDTO dto){
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

    public void eliminar(ProductoDTO dto){
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

    public ProductoDTO leerUno(ProductoDTO dto){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdProducto()));
            
            transaction.commit();
            
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
        return dto;
    }
    
    
    public byte [] obtenerImagen(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        byte [] bytes = null;
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(id);
        try{
            transaction.begin();
            
            bytes = session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdProducto()).getImagen();
            
            transaction.commit();
        }catch(HibernateException he){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }            
        }
        
        return bytes;
    }

    public List<ProductoDTO> leerTodos(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List aux = null;
        List<ProductoDTO> lista = new ArrayList<>();
        try{
            transaction.begin();
            
            Query q = session.createQuery("FROM Producto p ORDER BY p.idProducto");
            aux = q.list();
            for(int i = 0; i < aux.size(); i++){
                ProductoDTO dto = new ProductoDTO();
                dto.setEntidad((Producto)aux.get(i));
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
        ProductoDTO dto = new ProductoDTO();
        ProductoDAO dao = new ProductoDAO();
        byte [] algo = new byte[10];
        dto.getEntidad().setNombre("nike");
        dto.getEntidad().setDescripcion("nike");
        dto.getEntidad().setIdCategoria(4);
        dto.getEntidad().setImagen(algo);
        dao.crear(dto);
        System.out.println(dao.leerTodos());
    }

}
