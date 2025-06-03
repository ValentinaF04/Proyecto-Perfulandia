package com.example.vendedores.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference; 
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
    private Long idSucursal; 

    @Column(nullable = false)
    private LocalDate fechaAsignacion;

    private LocalDate fechaDesasignacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    @JsonBackReference 
    private Vendedor vendedor;
}