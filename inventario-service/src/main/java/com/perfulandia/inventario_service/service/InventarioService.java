package com.perfulandia.inventario_service.service;

import com.perfulandia.inventario_service.dto.ActualizarStockRequestDTO;
import com.perfulandia.inventario_service.entity.InventarioItem;
import com.perfulandia.inventario_service.repository.InventarioItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioItemRepository inventarioItemRepository;

    @Transactional(readOnly = true)
    public Optional<Integer> obtenerStockPorProductoId(Long productoId) {
        return inventarioItemRepository.findByProductoId(productoId)
                .map(InventarioItem::getCantidadEnStock); // Extrae solo la cantidad
    }

    @Transactional
    public InventarioItem actualizarStockPorProductoId(Long productoId, ActualizarStockRequestDTO requestDTO) {
        InventarioItem item = inventarioItemRepository.findByProductoId(productoId)
                .orElseGet(() -> {
                    // Si no existe, crea una nueva entrada de inventario para este productoId
                    InventarioItem nuevoItem = new InventarioItem();
                    nuevoItem.setProductoId(productoId);
                    // nuevoItem.setCantidadEnStock(0); // Se establecerá abajo
                    // nuevoItem.setUltimaActualizacion(LocalDateTime.now()); // @UpdateTimestamp lo hará
                    return nuevoItem;
                });

        item.setCantidadEnStock(requestDTO.getNuevaCantidad());
        return inventarioItemRepository.save(item);
    }

    // Podrías tener métodos más específicos como:
    // public void crearEntradaInventarioInicial(Long productoId, int cantidadInicial) { ... }
    // public InventarioItem incrementarStock(Long productoId, int cantidadAAgregar) { ... }
    // public InventarioItem decrementarStock(Long productoId, int cantidadARestar) { ... }
}