package com.example.inventario.dto;

import java.time.LocalDateTime;

public class LotePEPS {
    private String numeroLote;
    private String producto;
    private LocalDateTime fechaEntrada;
    private int cantidadOriginal;
    private int cantidadDisponible;
    private double costoUnitario;
    private double valorTotal;
    private String estado;  // "disponible", "parcial", "agotado"

    // Getters y Setters

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public int getCantidadOriginal() {
        return cantidadOriginal;
    }

    public void setCantidadOriginal(int cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}