package com.perfulandia.producto_service.repository; // Paquete para las interfaces de repositorio

import com.perfulandia.producto_service.entity.Producto; // Importa la entidad Producto
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz base de Spring Data JPA
import org.springframework.stereotype.Repository; // Anotación para marcar como componente de repositorio

@Repository // (1) Indica que esta interfaz es un componente de repositorio de Spring.
public interface ProductoRepository extends JpaRepository<Producto, Long> { // (2)
    // (3) JpaRepository<Producto, Long>
    //     - Producto: Es la entidad que este repositorio gestionará.
    //     - Long: Es el tipo de dato de la clave primaria (ID) de la entidad Producto.

    // (4) Spring Data JPA provee automáticamente la implementación para métodos CRUD básicos:
    //     - save(Producto entity): Guarda o actualiza una entidad.
    //     - findById(Long id): Busca una entidad por su ID.
    //     - findAll(): Devuelve todas las entidades.
    //     - deleteById(Long id): Elimina una entidad por su ID.
    //     - count(): Cuenta el número de entidades.
    //     - existsById(Long id): Verifica si una entidad existe por su ID.
    //     - ... y muchos más.

    // (5) También puedes definir métodos de consulta personalizados siguiendo convenciones de nombres,
    //     o usando la anotación @Query. Por ejemplo:
    //     List<Producto> findByMarca(String marca);
    //     List<Producto> findByPrecioLessThan(double precio);
    //     @Query("SELECT p FROM Producto p WHERE p.familiaOlfativa = :familia AND p.precio < :precioMax")
    //     List<Producto> findByFamiliaAndPrecioMax(@Param("familia") String familia, @Param("precioMax") double precioMax);
}