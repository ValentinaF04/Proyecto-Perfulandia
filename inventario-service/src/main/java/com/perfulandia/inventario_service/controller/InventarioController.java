package com.perfulandia.inventario_service.controller;

import com.perfulandia.inventario_service.dto.ActualizarStockRequestDTO;
import com.perfulandia.inventario_service.entity.InventarioItem;
import com.perfulandia.inventario_service.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    /**
     * Endpoint para ser llamado por producto-service (u otros) para obtener el stock.
     * GET /api/v1/inventario/producto/{productoId}/stock
     */
    @GetMapping("/producto/{productoId}/stock")
    public ResponseEntity<Integer> getStockPorProductoId(@PathVariable Long productoId) {
        Optional<Integer> stock = inventarioService.obtenerStockPorProductoId(productoId);
        return stock.map(ResponseEntity::ok) // Si hay stock, 200 OK con la cantidad
                    .orElse(ResponseEntity.ok(0)); // Si no hay entrada de inventario, devuelve 0 stock o 404
                    // .orElse(ResponseEntity.notFound().build()); // Alternativa: 404 si no hay registro de inventario
                                                                // Devolver 0 es más amigable para el consumidor a veces.
    }

    /**
     * Endpoint para actualizar el stock de un producto.
     * POST /api/v1/inventario/producto/{productoId}/stock
     */
    @PostMapping("/producto/{productoId}/stock") // Usamos POST para actualizar o crear si no existe
    public ResponseEntity<InventarioItem> actualizarStock(
            @PathVariable Long productoId,
            @RequestBody ActualizarStockRequestDTO requestDTO) {
        try {
            InventarioItem itemActualizado = inventarioService.actualizarStockPorProductoId(productoId, requestDTO);
            return ResponseEntity.ok(itemActualizado);
        } catch (Exception e) {
            // Manejo de errores más específico podría ser necesario
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Podrías tener más endpoints:
    // GET /api/v1/inventario/item/{productoId} (para obtener el InventarioItem completo)
    // POST /api/v1/inventario/item (para crear una nueva entrada de inventario si no se hace automáticamente)
}