package com.example.vendedores.entidad;

import com.fasterxml.jackson.annotation.JsonManagedReference; 
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "vendedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, nullable = false, length = 50)
    private String codigoEmpleado;

    @Column(length = 100)
    private String email;

    // Relación uno a muchos con SucursalAsignada
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference 
    private List<SucursalAsignada> sucursalesAsignadas;

    // Relación uno a muchos con Metas
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference 
    private List<Metas> metas;
}