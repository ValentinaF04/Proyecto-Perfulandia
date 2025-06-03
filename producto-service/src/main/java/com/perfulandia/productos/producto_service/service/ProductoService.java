package com.perfulandia.productos.producto_service.service;

import com.perfulandia.productos.producto_service.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> obtenerTodosLosProductos();

    Optional<Producto> obtenerProductoPorId(Long id);

    List<Producto> obtenerProductosPorCategoria(String categoria);

    Producto guardarProducto(Producto producto);

    // Podríamos añadir más métodos aquí si fueran necesarios, como actualizar o eliminar
    // Producto actualizarProducto(Long id, Producto producto);
    // void eliminarProducto(Long id);
}