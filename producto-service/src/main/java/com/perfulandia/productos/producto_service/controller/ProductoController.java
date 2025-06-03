package com.perfulandia.productos.producto_service.controller;

import com.perfulandia.productos.producto_service.model.Producto;
import com.perfulandia.productos.producto_service.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos") 
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Endpoint: GET /api/v1/productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(productos); // 200 OK
    }

    // Endpoint: POST /api/v1/productos
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto); // 201 Created
    }

    // Endpoint: GET /api/v1/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoService.obtenerProductoPorId(id);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.get()); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // Endpoint: GET /api/v1/productos/categoria/{nombre}
    @GetMapping("/categoria/{nombre}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable("nombre") String nombreCategoria) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(nombreCategoria);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(productos); // 200 OK
    }
}