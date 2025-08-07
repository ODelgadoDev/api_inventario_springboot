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

    @GetMapping("/{id}")
    public Entrada buscarEntradaPorId(@PathVariable Integer id) {
        return entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID " + id));
    }

    @DeleteMapping("/{id}")
    public void eliminarEntrada(@PathVariable Integer id) {
        entradaRepository.deleteById(id);
    }

    @PostMapping
    public Entrada crearEntrada(@RequestBody EntradaDto entradaDto) {
        // 1. Buscar producto
        Producto producto = productoRepository.findById(entradaDto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto con ID " + entradaDto.getProductoId() + " no encontrado."));

        // 2. Validar campos obligatorios para evitar error de integridad
        if (producto.getCostoUnitario() == null || producto.getPrecioUnitario() == null) {
            throw new RuntimeException("El producto no tiene definidos costoUnitario o precioUnitario.");
        }

        // 3. Actualizar stock del producto
        producto.setCantidad(producto.getCantidad() + entradaDto.getCantidad());
        productoRepository.save(producto);

        // 4. Crear entrada y asignar solo el ID del producto (evita errores por mapeo incompleto)
        Entrada entrada = new Entrada();
        entrada.setProducto(new Producto());
        entrada.getProducto().setIdProducto(producto.getIdProducto()); // solo el ID
        entrada.setFechaEntrada(entradaDto.getFechaEntrada());
        entrada.setCantidad(entradaDto.getCantidad());
        entrada.setCostoUnitario(entradaDto.getCostoUnitario());
        entrada.setMetodoInventario(entradaDto.getMetodoInventario());

        return entradaRepository.save(entrada);
    }
}