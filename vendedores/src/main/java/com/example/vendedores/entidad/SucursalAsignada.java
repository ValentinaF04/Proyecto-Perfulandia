package com.example.vendedores.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "sucursales_asignadas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalAsignada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idSucursal; // ID de la sucursal (puede ser de otro microservicio)

    @Column(nullable = false)
    private LocalDate fechaAsignacion;

    private LocalDate fechaDesasignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    
}