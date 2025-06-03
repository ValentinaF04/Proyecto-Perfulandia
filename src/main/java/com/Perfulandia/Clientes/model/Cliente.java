package com.Perfulandia.Clientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "cliente_productos_comprados",
            joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "producto_id")
    private List<Long> idsProductosComprados = new ArrayList<>();
}

