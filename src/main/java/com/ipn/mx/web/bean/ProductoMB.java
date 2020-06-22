/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.web.bean;

import com.ipn.mx.modelo.dao.OrdenArticulosDAO;
import com.ipn.mx.modelo.dao.OrdenDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.OrdenArticulosDTO;
import com.ipn.mx.modelo.dto.OrdenDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.utilerias.SessionUtil;
import java.io.DataInputStream;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

/**
 *
 * @author unityofdisaster
 */
@ManagedBean
@SessionScoped
public class ProductoMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ProductoMB
     */
    private ProductoDTO dto;
    private ProductoDAO dao = new ProductoDAO();
    private OrdenDAO ordenDAO = new OrdenDAO();
    private OrdenArticulosDAO ordArtDAO = new OrdenArticulosDAO();
    private List<ProductoDTO> listaProductos;
    private List<ProductoDTO> listaCarrito;
    private Part archivo;
    private SessionUtil sesion = new SessionUtil();
    private String mensaje;
    private int id;
    private int[] cantidad;
    private double total;
    private HashMap<Integer, Integer> carritoLocal;

    public HashMap<Integer, Integer> getCarritoLocal() {
        return carritoLocal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCarritoLocal(HashMap<Integer, Integer> carritoLocal) {
        this.carritoLocal = carritoLocal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ProductoMB() {
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }

    public ProductoDTO getDto() {
        return dto;
    }

    public void setDto(ProductoDTO dto) {
        this.dto = dto;
    }

    public ProductoDAO getDao() {
        return dao;
    }

    public void setDao(ProductoDAO dao) {
        this.dao = dao;
    }

    public List<ProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getCantidad() {
        return cantidad;
    }

    public void setCantidad(int[] cantidad) {
        this.cantidad = cantidad;
    }

    public List<ProductoDTO> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<ProductoDTO> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    public void clearCarrito() {
        HashMap<Integer, Integer> aux = (HashMap<Integer, Integer>) sesion.obtener("carrito");
        aux.clear();
        sesion.agregar("carrito", aux);
    }

    @PostConstruct
    public void init() {
        listaProductos = new ArrayList<>();
        listaProductos = dao.leerTodos();
        cantidad = new int[listaProductos.size()];

    }

    public String agregarProducto() {
        int i = 0;
        for (; i < cantidad.length; i++) {
            if (cantidad[i] > 0) {
                System.out.println("algo cambio");
                break;
            }
        }
        System.out.println("asociar: " + id + " con: " + cantidad[i]);
        HashMap<Integer, Integer> aux = (HashMap<Integer, Integer>) sesion.obtener("carrito");
        System.out.println("no llega aqui");
        aux.put(id, cantidad[i]);
        sesion.agregar("carrito", aux);
        mensaje = "agregado";
        return prepareClienteIndex();
    }

    public String prepararCarrito() {
        init();
        total = 0.0;
        listaCarrito = new ArrayList<>();
        carritoLocal = (HashMap<Integer, Integer>) sesion.obtener("carrito");
        for (ProductoDTO producto : listaProductos) {
            if (carritoLocal.containsKey(producto.getEntidad().getIdProducto())) {
                total += carritoLocal.get(producto.getEntidad().getIdProducto()) * producto.getEntidad().getPrecio();
                listaCarrito.add(producto);
            }
        }
        return "/clientes/carrito?faces-redirect=true";
    }

    public String finalizarPedido() {
        init();
        if (carritoLocal.size() > 0) {
            try {
                OrdenDTO orDTO = new OrdenDTO();
                orDTO.getEntidad().setIdCliente((Integer) sesion.obtener("idCliente"));
                Date hoy = new Date();
                orDTO.getEntidad().setFechaOrden(hoy);
                
                // procesando
                orDTO.getEntidad().setEstatusOrden("P");
                ordenDAO.crear(orDTO);

                List<OrdenDTO> listaAux = new ArrayList<>();
                listaAux = ordenDAO.leerOrdenesCliente((Integer) sesion.obtener("idCliente"));
                orDTO = listaAux.get(listaAux.size() - 1);

                int num = 1;
                for (ProductoDTO producto : listaProductos) {

                    if (carritoLocal.containsKey(producto.getEntidad().getIdProducto())) {

                        OrdenArticulosDTO ordArDTO = new OrdenArticulosDTO();
                        ordArDTO.getEntidad().setIdOrden(orDTO.getEntidad().getIdOrden());
                        ordArDTO.getEntidad().setIdProducto(producto.getEntidad().getIdProducto());
                        ordArDTO.getEntidad().setCantidad(carritoLocal.get(producto.getEntidad().getIdProducto()));
                        ordArDTO.getEntidad().setIditem(num);
                        ordArDTO.getEntidad().setPrecio(producto.getEntidad().getPrecio());
                        num++;
                        ordArtDAO.crear(ordArDTO);
                        
                        producto.getEntidad().setStock(producto.getEntidad().getStock() - carritoLocal.get(producto.getEntidad().getIdProducto()));
                        dao.actualizar(producto);
                    }
                }

                carritoLocal.clear();
                HashMap<Integer, Integer> carritoSesion = (HashMap<Integer, Integer>) sesion.obtener("carrito");
                carritoSesion.clear();
                sesion.agregar("carrito", carritoSesion);
                for(int i = 0; i < cantidad.length; i++){
                    cantidad[i] = 0;
                }

                return "/clientes/indexCliente?faces-redirect=true";

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }

    public String prepareAdd() {
        dto = new ProductoDTO();
        setAccion(ACC_CREAR);
        return "/productos/productoForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/productos/productoForm?faces-redirect=true";
    }

    public String back() {
        init();
        return "/productos/listaProdAdmin?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/productos/listaProductos?faces-redirect=true";
    }

    public String prepareClienteIndex() {
        init();
        return "/productos/listaProductosCompra?faces-redirect=true";
    }

    public String prepareAdminIndex() {
        init();
        return "/productos/listaProdAdmin?faces-redirect=true";
    }

    public String crear() {
        try {
            byte[] bytes = new byte[(int) archivo.getSize()];
            DataInputStream dis = new DataInputStream(archivo.getInputStream());
            dis.readFully(bytes);
            dto.getEntidad().setImagen(bytes);
            dao.crear(dto);
            return prepareAdminIndex();
        } catch (Exception e) {
            error("errorCrearEvento", "Error al crear el evento");
            return "/productos/listaProdAdmin?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            if (archivo != null) {
                byte[] bytes = new byte[(int) archivo.getSize()];
                DataInputStream dis = new DataInputStream(archivo.getInputStream());
                dis.readFully(bytes);
                dto.getEntidad().setImagen(bytes);

            }
            dao.actualizar(dto);
            return prepareAdminIndex();
        } catch (Exception e) {
            System.out.println("debe pasar algo niggaaaaa");
            e.printStackTrace();
            error("errorActualizarEvento", "Error al actualizar el evento");
            return "/productos/listaProdAdmin?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareAdminIndex();
        } catch (Exception e) {
            error("errorBorrarEvento", "Error al borrar el evento");
            return "/productos/listaProdAdmin?faces-redirect=true";
        }
    }

    public void seleccionarProducto(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void seleccionarProductoCompra(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        id = Integer.parseInt(claveSel);
    }

    public void mostrarMensaje(ActionEvent event) {
        mensaje = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("activar");

    }

}
