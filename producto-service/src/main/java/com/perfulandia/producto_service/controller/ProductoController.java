package com.perfulandia.producto_service.controller; // Paquete para los controladores

import com.perfulandia.producto_service.entity.Producto; // Importa la entidad
import com.perfulandia.producto_service.service.ProductoService; // Importa el servicio
import org.springframework.beans.factory.annotation.Autowired; // Para inyección de dependencias
import org.springframework.http.HttpStatus; // Para códigos de estado HTTP
import org.springframework.http.ResponseEntity; // Para construir respuestas HTTP completas
import org.springframework.web.bind.annotation.*; // Anotaciones para mapeo de URLs y parámetros

import java.util.List;

@RestController // (1) Combina @Controller y @ResponseBody. Indica que esta clase maneja peticiones REST.
@RequestMapping("/api/v1/productos") // (2) Mapea todas las peticiones que comiencen con /api/v1/productos a este controlador.
public class ProductoController {

    @Autowired // (3) Inyecta una instancia de ProductoService.
    private ProductoService productoService;

    // Endpoint para OBTENER todos los productos
    // HTTP GET a /api/v1/productos
    @GetMapping // (4) Mapea peticiones HTTP GET a este método.
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos); // (5) Devuelve 200 OK con la lista de productos en el cuerpo.
    }

    // Endpoint para OBTENER un producto por su ID
    // HTTP GET a /api/v1/productos/{id}
    @GetMapping("/{id}") // (6) Mapea GET a /api/v1/productos/{id}. {id} es una variable de ruta.
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) { // (7) @PathVariable extrae el valor de 'id' de la URL.
        return productoService.getProductoById(id) // Llama al servicio
                .map(productoEncontrado -> ResponseEntity.ok(productoEncontrado)) // Si se encuentra, 200 OK con el producto.
                .orElse(ResponseEntity.notFound().build()); // Si no, 404 Not Found.
    }

    // Endpoint para CREAR un nuevo producto
    // HTTP POST a /api/v1/productos
    @PostMapping // (8) Mapea peticiones HTTP POST.
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) { // (9) @RequestBody mapea el cuerpo JSON de la petición al objeto Producto.
        Producto nuevoProducto = productoService.saveProducto(producto);
        // Devuelve 201 Created con el nuevo producto (que ahora tiene un ID asignado) en el cuerpo.
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // Endpoint para ACTUALIZAR un producto existente
    // HTTP PUT a /api/v1/productos/{id}
    @PutMapping("/{id}") // (10) Mapea peticiones HTTP PUT.
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        try {
            Producto productoActualizado = productoService.updateProducto(id, productoDetails);
            return ResponseEntity.ok(productoActualizado); // 200 OK con el producto actualizado.
        } catch (RuntimeException e) { // Si el servicio lanza RuntimeException (producto no encontrado).
            // En una app real, sería mejor capturar una excepción más específica como ResourceNotFoundException.
            return ResponseEntity.notFound().build(); // 404 Not Found.
        }
    }

    // Endpoint para ELIMINAR un producto
    // HTTP DELETE a /api/v1/productos/{id}
    @DeleteMapping("/{id}") // (11) Mapea peticiones HTTP DELETE.
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build(); // 204 No Content (eliminación exitosa, sin cuerpo de respuesta).
        } catch (RuntimeException e) { // Si el servicio lanza RuntimeException (producto no encontrado).
            return ResponseEntity.notFound().build(); // 404 Not Found.
        }
    }
}