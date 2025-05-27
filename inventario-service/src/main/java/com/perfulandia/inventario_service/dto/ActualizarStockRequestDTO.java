package com.perfulandia.inventario_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarStockRequestDTO {
    private int nuevaCantidad;
    // Podrías tener otros campos, como "tipoMovimiento" (COMPRA, VENTA, AJUSTE)
    // o "cantidadDelta" (para incrementar/decrementar)
}