package com.example.inventario.controller;

import com.example.inventario.model.Producto;
import com.example.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // GET → Listar todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // POST → Crear un nuevo producto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // GET → Buscar un producto por ID
    @GetMapping("/{id}")
    public Optional<Producto> buscarProductoPorId(@PathVariable Integer id) {
        return productoRepository.findById(id);
    }

    // PUT → Actualizar un producto
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Integer id, @RequestBody Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setCantidad(productoActualizado.getCantidad());
                    producto.setCategoria(productoActualizado.getCategoria());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID " + id));
    }

    // DELETE → Eliminar producto
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoRepository.deleteById(id);
    }
}