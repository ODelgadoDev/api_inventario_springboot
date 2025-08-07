package com.example.inventario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    @JsonProperty("id")
    private Integer idProducto;

    @Column(nullable = false)
    @JsonProperty("name")
    private String nombre;

    @JsonProperty("description")
    private String descripcion;

    @Column(name = "precio_unitario", nullable = false)
    @JsonProperty("unitPrice")
    private Double precioUnitario;

    @Column(name = "costo_unitario", nullable = false)
    @JsonProperty("unitCost")
    private Double costoUnitario;

    @Column(nullable = false)
    @JsonProperty("cantidad")
    private Integer cantidad;

    @JsonProperty("category")
    private String categoria;

    // Constructor vacío
    public Producto() {}

    // Getters y Setters
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}