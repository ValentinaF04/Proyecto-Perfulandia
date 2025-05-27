package com.perfulandia.inventario.inventario_service.controller;

import com.perfulandia.inventario.inventario_service.model.Inventario;
import com.perfulandia.inventario.inventario_service.service.InventarioService;
// import com.perfulandia.inventario.inventario_service.dto.AjusteInventarioRequest; // Futuro DTO
// import com.perfulandia.inventario.inventario_service.dto.MovimientoInventarioRequest; // Futuro DTO

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping; // Para futuro POST
// import org.springframework.web.bind.annotation.PutMapping; // Para futuro PUT
// import org.springframework.web.bind.annotation.RequestBody; // Para futuros DTOs
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario") // Base path para los endpoints de inventario
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    /**
     * Endpoint para obtener el inventario de un producto específico por su ID.
     * GET /api/v1/inventario/producto/{id}
     * @param productoId El ID del producto.
     * @return ResponseEntity con la lista de inventarios o noContent si no hay.
     */
    @GetMapping("/producto/{id}")
    public ResponseEntity<List<Inventario>> obtenerInventarioPorProducto(@PathVariable("id") Long productoId) {
        List<Inventario> inventarios = inventarioService.obtenerInventarioPorProductoId(productoId);
        if (inventarios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(inventarios); // 200 OK
    }

    /*
    // --- ENDPOINTS COMENTADOS PARA FUTURA IMPLEMENTACIÓN DE AJUSTES Y MOVIMIENTOS ---

    /**
     * Endpoint para realizar un ajuste de inventario.
     * PUT /api/v1/inventario/ajuste
     * Se espera un cuerpo de solicitud con los detalles del ajuste.
     *
     * @param ajusteRequest DTO con la información del ajuste.
     * @return ResponseEntity indicando el resultado de la operación.
     */
    // @PutMapping("/ajuste")
    // public ResponseEntity<Void> ajustarInventario(@RequestBody AjusteInventarioRequest ajusteRequest) {
    //     // TODO: Descomentar y llamar al servicio cuando esté implementado
    //     // inventarioService.realizarAjusteInventario(ajusteRequest);
    //     System.out.println("Endpoint PUT /ajuste llamado, pero la lógica de servicio no está implementada.");
    //     // return ResponseEntity.ok().build(); // O podría ser noContent()
    //     return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build(); // Temporalmente
    // }

    /**
     * Endpoint para registrar un movimiento de inventario.
     * POST /api/v1/inventario/movimiento
     * Se espera un cuerpo de solicitud con los detalles del movimiento.
     *
     * @param movimientoRequest DTO con la información del movimiento.
     * @return ResponseEntity indicando el resultado de la operación.
     */
    // @PostMapping("/movimiento")
    // public ResponseEntity<Void> registrarMovimiento(@RequestBody MovimientoInventarioRequest movimientoRequest) {
    //     // TODO: Descomentar y llamar al servicio cuando esté implementado
    //     // inventarioService.registrarMovimientoInventario(movimientoRequest);
    //     System.out.println("Endpoint POST /movimiento llamado, pero la lógica de servicio no está implementada.");
    //     // return ResponseEntity.status(HttpStatus.CREATED).build();
    //     return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build(); // Temporalmente
    // }
    
}