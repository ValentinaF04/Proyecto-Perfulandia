package com.example.vendedores.repository;

import com.example.vendedores.entidad.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    // Método para buscar vendedores por ID de sucursal asignada
    @Query("SELECT DISTINCT v FROM Vendedor v JOIN v.sucursalesAsignadas sa WHERE sa.idSucursal = :idSucursal")
    List<Vendedor> findBySucursalAsignada(@Param("idSucursal") Long idSucursal);

    Vendedor findByCodigoEmpleado(String codigoEmpleado);
}