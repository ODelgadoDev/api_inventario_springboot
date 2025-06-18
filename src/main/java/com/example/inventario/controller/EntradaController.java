package com.example.inventario.controller;

import com.example.inventario.dto.EntradaDto;
import com.example.inventario.model.Entrada;
import com.example.inventario.model.Producto;
import com.example.inventario.repository.EntradaRepository;
import com.example.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entradas")
@CrossOrigin(origins = "*")
public class EntradaController {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Entrada> listarEntradas() {
        return entradaRepository.findAll();
    }

    @PostMapping
    public Entrada crearEntrada(@RequestBody EntradaDto entradaDto) {

        Optional<Producto> productoOptional = productoRepository.findById(entradaDto.getProductoId());
        if (productoOptional.isEmpty()) {
            throw new RuntimeException("Producto con ID " + entradaDto.getProductoId() + " no encontrado.");
        }

        Producto producto = productoOptional.get();

        Entrada entrada = new Entrada();
        entrada.setProducto(producto);
        entrada.setFechaEntrada(entradaDto.getFechaEntrada());
        entrada.setCantidad(entradaDto.getCantidad());
        entrada.setCostoUnitario(entradaDto.getCostoUnitario());
        entrada.setMetodoInventario(entradaDto.getMetodoInventario());

        return entradaRepository.save(entrada);
    }
}