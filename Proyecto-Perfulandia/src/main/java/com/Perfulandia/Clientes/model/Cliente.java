package com.Perfulandia.Clientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nombre_completo", length = 100)
    private String nombreCompleto;

    @Column(unique = true, length = 12, nullable = false)
    private String rut;

    @Column(length = 15)
    private String telefono;

    @Column(columnDefinition = "TEXT")
    private String direccion;

}
