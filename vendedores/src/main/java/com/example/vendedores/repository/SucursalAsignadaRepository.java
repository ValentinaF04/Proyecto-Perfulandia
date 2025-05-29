package com.example.vendedores.repository;

import com.example.vendedores.entidad.SucursalAsignada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SucursalAsignadaRepository extends JpaRepository<SucursalAsignada, Long> {
    List<SucursalAsignada> findByIdSucursal(Long idSucursal);
    List<SucursalAsignada> findByVendedorId(Long vendedorId);
}