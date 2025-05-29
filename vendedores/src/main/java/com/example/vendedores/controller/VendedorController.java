package com.example.vendedores.controller;

import com.example.vendedores.entidad.Vendedor;
import com.example.vendedores.service.VendedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors; 

@RestController
@RequestMapping("/vendedores") // Ruta base para todos los endpoints de este controlador
public class VendedorController {

    private final VendedorService vendedorService;
    

    @Autowired
    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    /**
     * Endpoint para crear un nuevo vendedor.
     * POST /vendedores
     */
    @PostMapping
    public ResponseEntity<?> crearVendedor(@RequestBody Vendedor vendedor) { 
        try {
            
            Vendedor nuevoVendedor = vendedorService.crearVendedor(vendedor);
            
            return new ResponseEntity<>(nuevoVendedor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para obtener un vendedor por su ID.
     * GET /vendedores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> obtenerVendedorPorId(@PathVariable Long id) { 
        return vendedorService.obtenerVendedorPorId(id)
                
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para actualizar un vendedor existente.
     * PUT /vendedores/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> actualizarVendedor(@PathVariable Long id, @RequestBody Vendedor vendedor) { 
        return vendedorService.actualizarVendedor(id, vendedor)
                
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para obtener todos los vendedores asignados a una sucursal específica.
     * GET /vendedores/sucursal/{idSucursal}
     */
    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<List<Vendedor>> obtenerVendedoresPorSucursal(@PathVariable Long idSucursal) { 
        List<Vendedor> vendedores = vendedorService.obtenerVendedoresPorSucursal(idSucursal);
        if (vendedores.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        
        return ResponseEntity.ok(vendedores);
    }
}