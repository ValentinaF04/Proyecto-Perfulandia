package com.perfulandia.inventario_service.repository;

import com.perfulandia.inventario_service.entity.InventarioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioItemRepository extends JpaRepository<InventarioItem, Long> {

    // Método para encontrar un item de inventario por el productoId
    Optional<InventarioItem> findByProductoId(Long productoId);

    // También podrías tener un método para eliminar por productoId si fuera necesario
    // void deleteByProductoId(Long productoId);
}