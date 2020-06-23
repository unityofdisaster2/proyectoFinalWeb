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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author unityofdisaster
 */
@ManagedBean
@SessionScoped
public class OrdenArticulosMB extends BaseBean implements Serializable {

    /**
     * Creates a new instance of OrdenArticulosMB
     */
    private OrdenArticulosDTO dto;
    private OrdenArticulosDAO dao = new OrdenArticulosDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private OrdenDAO ordenDAO = new OrdenDAO();
    private List<OrdenArticulosDTO> listaOrdenArticulos;
    private HashMap<Integer, ProductoDTO> relacionProductos;
    private LineChartModel modelo;

    public LineChartModel getModelo() {
        return modelo;
    }

    public OrdenArticulosMB() {
    }

    public OrdenArticulosDTO getDto() {
        return dto;
    }

    public void setDto(OrdenArticulosDTO dto) {
        this.dto = dto;
    }

    public OrdenArticulosDAO getDao() {
        return dao;
    }

    public void setDao(OrdenArticulosDAO dao) {
        this.dao = dao;
    }

    public List<OrdenArticulosDTO> getListaOrdenArticulos() {
        return listaOrdenArticulos;
    }

    public void setListaOrdenArticulos(List<OrdenArticulosDTO> listaOrdenArticulos) {
        this.listaOrdenArticulos = listaOrdenArticulos;
    }

    public HashMap<Integer, ProductoDTO> getRelacionProductos() {
        return relacionProductos;
    }

    public void setRelacionProductos(HashMap<Integer, ProductoDTO> relacionProductos) {
        this.relacionProductos = relacionProductos;
    }

    public String generarGrafica() {
        List<OrdenDTO> ordenes = new ArrayList<>();
        List<OrdenArticulosDTO> ordenArt = new ArrayList<>();
        LinkedHashMap<String,Double> mapaValores = new LinkedHashMap<>();
        try {
            ordenes = ordenDAO.leerTodos();
            for(OrdenDTO dto: ordenes){
                ordenArt = dao.leerRegistrosOrden(dto.getEntidad().getIdOrden());
                for(OrdenArticulosDTO auxDTO : ordenArt){
                    if(dto.getEntidad().getFechaEntrega() == null){
                        continue;
                    }
                    if(!mapaValores.containsKey(dto.getEntidad().getFechaEntrega().toString())){
                        mapaValores.put(dto.getEntidad().getFechaEntrega().toString(), auxDTO.getEntidad().getCantidad()*auxDTO.getEntidad().getPrecio());
                    }else{
                        mapaValores.put(
                                dto.getEntidad().getFechaEntrega().toString(),
                                mapaValores.get(dto.getEntidad().getFechaEntrega().toString()) + 
                                        auxDTO.getEntidad().getCantidad()*auxDTO.getEntidad().getPrecio());
                    }
                }

            }
            modelo = new LineChartModel();
            ChartSeries ventas = new ChartSeries();
            ventas.setLabel("ventas");
            for(String llaves: mapaValores.keySet()){
                ventas.set(llaves, mapaValores.get(llaves));
            }
            modelo.addSeries(ventas);
            modelo.setTitle("Ventas diarias (entregadas)");
            modelo.setLegendPosition("e");
            modelo.setShowPointLabels(true);
            modelo.getAxes().put(AxisType.X, new CategoryAxis("Dias"));
            Axis yAxis = modelo.getAxis(AxisType.Y);
            yAxis.setLabel("$");
            return "/ordenes/graficaVentas?faces-redirect=true";

        } catch (Exception e) {
            return null;
        }
    }

    @PostConstruct
    public void init() {
        listaOrdenArticulos = new ArrayList<>();
        listaOrdenArticulos = dao.leerTodos();
    }

    public String prepareAdd() {
        dto = new OrdenArticulosDTO();
        setAccion(ACC_CREAR);
        return "/ordenArticulos/ordenArticulosForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/ordenArticulos/ordenArticulosForm?faces-redirect=true";
    }

    public String goToDetalles() {
        return "/ordenes/detalleOrden?faces-redirect=true";
    }

    public String back() {
        init();
        return "/ordenArticulos/ordenArticulosForm?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/ordenArticulos/listaOrdenArticulos?faces-redirect=true";
    }

    public String crear() {
        try {
            dao.crear(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorCrearEvento", "Error al crear el evento");
            return "/ordenArticulos/listaOrdenArticulos?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            dao.actualizar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorActualizarOrden", "Error al actualizar orden");
            return "/ordenArticulos/listaOrdenArticulos?faces-redirect=true";
        }
    }

    public String borrar() {
        try {
            dao.eliminar(dto);
            return prepareIndex();
        } catch (Exception e) {
            error("errorBorrarOrden", "Error al borrar orden");
            return "/ordenArticulos/listaOrdenArticulos?faces-redirect=true";
        }
    }

    public void seleccionarOrden(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveSel");
        dto = new OrdenArticulosDTO();
        dto.getEntidad().setIdOrden(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void seleccionarOrdenIndividual(ActionEvent event) {
        String idOrd = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("detalle");
        try {
            relacionProductos = new HashMap<>();
            listaOrdenArticulos = dao.leerRegistrosOrden(Integer.parseInt(idOrd));

            for (OrdenArticulosDTO item : listaOrdenArticulos) {
                ProductoDTO aux = new ProductoDTO();
                aux.getEntidad().setIdProducto(item.getEntidad().getIdProducto());
                aux = productoDAO.leerUno(aux);
                relacionProductos.put(item.getEntidad().getIdProducto(), aux);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
