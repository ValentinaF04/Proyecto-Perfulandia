package com.example.vendedores.service;

import com.example.vendedores.entidad.Vendedor;
import com.example.vendedores.repository.VendedorRepository;
import com.example.vendedores.repository.SucursalAsignadaRepository; // Importar
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importar
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final SucursalAsignadaRepository sucursalAsignadaRepository; 

    @Autowired
    public VendedorService(VendedorRepository vendedorRepository, SucursalAsignadaRepository sucursalAsignadaRepository) {
        this.vendedorRepository = vendedorRepository;
        this.sucursalAsignadaRepository = sucursalAsignadaRepository;
    }

    @Transactional
    public Vendedor crearVendedor(Vendedor vendedor) {
        // agregar validaciones, lógica de negocio antes de guardar
        // Por ejemplo, verificar si el código de empleado ya existe
        if (vendedorRepository.findByCodigoEmpleado(vendedor.getCodigoEmpleado()) != null) {
            throw new IllegalArgumentException("El código de empleado ya existe: " + vendedor.getCodigoEmpleado());
        }
        // Asegurar que las relaciones bidireccionales están bien configuradas si es necesario
        if (vendedor.getSucursalesAsignadas() != null) {
            vendedor.getSucursalesAsignadas().forEach(sa -> sa.setVendedor(vendedor));
        }
        if (vendedor.getMetas() != null) {
            vendedor.getMetas().forEach(meta -> meta.setVendedor(vendedor));
        }
        return vendedorRepository.save(vendedor);
    }

    @Transactional(readOnly = true)
    public Optional<Vendedor> obtenerVendedorPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    @Transactional
    public Optional<Vendedor> actualizarVendedor(Long id, Vendedor vendedorActualizado) {
        return vendedorRepository.findById(id)
            .map(vendedorExistente -> {
                vendedorExistente.setNombre(vendedorActualizado.getNombre());
                vendedorExistente.setEmail(vendedorActualizado.getEmail());
                return vendedorRepository.save(vendedorExistente);
            });
    }

    @Transactional(readOnly = true)
    public List<Vendedor> obtenerVendedoresPorSucursal(Long idSucursal) {
        // Esta es la query directa que definimos en el VendedorRepository
        return vendedorRepository.findBySucursalAsignada(idSucursal);
    }

    // Método para eliminar vendedores si es necesario
     @Transactional
     public void eliminarVendedor(Long id) {
         vendedorRepository.deleteById(id);
     }
}
