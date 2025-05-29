package com.perfulandia.productos.producto_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "productos") // Basado en tu schema SQL original
@Data // Lombok: Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Genera un constructor sin argumentos (requerido por JPA)
@AllArgsConstructor // Lombok: Genera un constructor con todos los argumentos
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para IDs autoincrementales en MySQL
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false) // Asumimos que el nombre no puede ser nulo
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio", precision = 10, scale = 2, nullable = false) // Asumimos que el precio no puede ser nulo
    private BigDecimal precio;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "marca", length = 50)
    private String marca;
}