package com.example.vendedores.entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Table(name = "metas_vendedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private YearMonth periodo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montoMeta;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoAlcanzado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;
    }