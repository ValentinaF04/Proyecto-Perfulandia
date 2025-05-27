package com.perfulandia.inventario_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp; // Para la fecha de actualización automática

import java.time.LocalDateTime;

@Entity
@Table(name = "inventario_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false, unique = true) // Importante: unique = true
    private Long productoId; // Este es el ID del producto en el 'producto-service'

    @Column(name = "cantidad_en_stock", nullable = false)
    private int cantidadEnStock;

    @UpdateTimestamp // Hibernate actualiza este campo automáticamente al modificar la entidad
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
}