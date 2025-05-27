package com.perfulandia.producto_service.entity; // Paquete para las clases entidad

import jakarta.persistence.Entity;         // Anotación para marcar como entidad JPA
import jakarta.persistence.GeneratedValue; // Para la generación de valores de ID
import jakarta.persistence.GenerationType; // Estrategia de generación de ID
import jakarta.persistence.Id;             // Anotación para la clave primaria
import jakarta.persistence.Table;          // Para especificar el nombre de la tabla
import lombok.Data;                        // Lombok: genera getters, setters, toString, etc.
import lombok.NoArgsConstructor;           // Lombok: genera constructor sin argumentos
import lombok.AllArgsConstructor;          // Lombok: genera constructor con todos los argumentos

@Entity // (1) Marca esta clase como una entidad JPA.
@Table(name = "productos") // (2) Mapea esta entidad a la tabla "productos" en la base de datos.
@Data // (3) Lombok: Genera automáticamente getters, setters, toString(), equals(), y hashCode().
@NoArgsConstructor // (4) Lombok: Genera un constructor sin argumentos (requerido por JPA).
@AllArgsConstructor // (5) Lombok: Genera un constructor con todos los campos como argumentos.
public class Producto {

    @Id // (6) Designa el campo 'id' como la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // (7) Configura el ID para que sea autogenerado por la base de datos (MySQL usa IDENTITY para auto_increment).
    private Long id;

    private String nombre;
    private String marca;
    private String descripcion;
    private double precio;
    private int stock;
    private String familiaOlfativa;
}