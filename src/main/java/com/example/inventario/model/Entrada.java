package com.example.inventario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer idEntrada;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonProperty("product")
    private Producto producto;

    @JsonProperty("date")
    private LocalDateTime fechaEntrada;

    @JsonProperty("quantity")
    private int cantidad;

    @JsonProperty("unitCost")
    private double costoUnitario;

    @JsonProperty("inventoryMethod")
    private String metodoInventario;

    public Entrada() {}

    // Getters and Setters
    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getMetodoInventario() {
        return metodoInventario;
    }

    public void setMetodoInventario(String metodoInventario) {
        this.metodoInventario = metodoInventario;
    }
}