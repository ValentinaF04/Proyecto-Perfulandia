package com.perfulandia.producto_service.service; // Paquete para las clases de servicio

import com.perfulandia.producto_service.entity.Producto; // Importa la entidad
import com.perfulandia.producto_service.repository.ProductoRepository; // Importa el repositorio
import org.springframework.beans.factory.annotation.Autowired; // Para inyección de dependencias
import org.springframework.stereotype.Service; // Anotación para marcar como componente de servicio
import org.springframework.transaction.annotation.Transactional; // Para la gestión de transacciones

import java.util.List;
import java.util.Optional; // Para manejar resultados que pueden ser nulos

@Service // (1) Indica que esta clase es un componente de servicio de Spring.
public class ProductoService {

    @Autowired // (2) Inyecta una instancia de ProductoRepository gestionada por Spring.
    private ProductoRepository productoRepository;

    // (3) Obtiene todos los productos.
    @Transactional(readOnly = true) // (4) Transacción de solo lectura, puede ser optimizada por el proveedor de persistencia.
    public List<Producto> getAllProductos() {
        return productoRepository.findAll(); // Utiliza el método findAll() heredado de JpaRepository.
    }

    // (5) Obtiene un producto por su ID.
    @Transactional(readOnly = true)
    public Optional<Producto> getProductoById(Long id) {
        // findById devuelve un Optional para manejar elegantemente el caso en que el producto no exista.
        return productoRepository.findById(id);
    }

    // (6) Guarda un nuevo producto o actualiza uno existente si ya tiene ID.
    @Transactional // (7) Transacción de escritura (lectura/escritura).
    public Producto saveProducto(Producto producto) {
        // save() puede usarse tanto para crear (si el producto no tiene ID o el ID no existe)
        // como para actualizar (si el producto tiene un ID que ya existe en la BD).
        return productoRepository.save(producto);
    }

    // (8) Actualiza un producto existente.
    @Transactional
    public Producto updateProducto(Long id, Producto productoDetails) {
        // 1. Busca el producto existente por su ID.
        Producto producto = productoRepository.findById(id)
                // 2. Si no se encuentra, lanza una excepción.
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
                // (En una aplicación real, usarías una excepción más específica, ej: ResourceNotFoundException)

        // 3. Actualiza los campos del producto encontrado con los detalles del producto proporcionado.
        producto.setNombre(productoDetails.getNombre());
        producto.setMarca(productoDetails.getMarca());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(productoDetails.getStock());
        producto.setFamiliaOlfativa(productoDetails.getFamiliaOlfativa());

        // 4. Guarda el producto actualizado. Hibernate detectará los cambios y generará el UPDATE SQL.
        return productoRepository.save(producto);
    }

    // (9) Elimina un producto por su ID.
    @Transactional
    public void deleteProducto(Long id) {
        // 1. (Opcional pero recomendado) Verifica si el producto existe antes de intentar eliminarlo.
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id + ". No se puede eliminar.");
        }
        // 2. Elimina el producto.
        productoRepository.deleteById(id);
    }
}