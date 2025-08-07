package com.example.inventario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Salida")
public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salida")
    @JsonProperty("id")
    private Integer idSalida;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonProperty("product")
    private Producto producto;

    @Column(name = "fecha_salida")
    @JsonProperty("date")
    private LocalDateTime fechaSalida;

    @Column(name = "cantidad")
    @JsonProperty("quantity")
    private int cantidad;

    @Column(name = "precio_unitario")
    @JsonProperty("unitPrice")
    private double precioUnitario;

    @Column(name = "metodo_inventario")
    @JsonProperty("inventoryMethod")
    private String metodoInventario;

    public Salida() {}

    // Getters y Setters
    public Integer getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(Integer idSalida) {
        this.idSalida = idSalida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getMetodoInventario() {
        return metodoInventario;
    }

    public void setMetodoInventario(String metodoInventario) {
        this.metodoInventario = metodoInventario;
    }
}