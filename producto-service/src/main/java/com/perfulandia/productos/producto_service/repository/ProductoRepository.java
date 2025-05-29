package com.perfulandia.productos.producto_service.repository;

import com.perfulandia.productos.producto_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Spring Data JPA generará automáticamente la consulta para este método
    // basándose en el nombre del método.
    // Buscará productos donde el campo 'categoria' (en la entidad Producto)
    // coincida con el parámetro proporcionado.
    List<Producto> findByCategoria(String categoria);

}