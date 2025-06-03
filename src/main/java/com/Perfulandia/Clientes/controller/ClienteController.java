package com.Perfulandia.Clientes.controller;

import com.Perfulandia.Clientes.dto.ClienteConHistorialDTO; // Asegúrate de que este DTO exista
import com.Perfulandia.Clientes.model.Cliente;
import com.Perfulandia.Clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.saveCliente(cliente);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Cliente> getClienteByRut(@PathVariable String rut) {
        Optional<Cliente> cliente = clienteService.getClienteByRut(rut);
        return cliente.map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        try {
            Cliente clienteActualizado = clienteService.updateCliente(id, clienteDetails);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        boolean deleted = clienteService.deleteCliente(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{clienteId}/compras/{productoId}")
    public ResponseEntity<?> registrarProductoComprado(
            @PathVariable Long clienteId,
            @PathVariable Long productoId) {
        try {
            Cliente clienteActualizado = clienteService.registrarProductoComprado(clienteId, productoId);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la compra: " + e.getMessage());
        }
    }


    @GetMapping("/{clienteId}/compras-detalladas")
    public ResponseEntity<?> obtenerClienteConComprasDetalladas(@PathVariable Long clienteId) {
        try {
            ClienteConHistorialDTO respuesta = clienteService.obtenerClienteConDetallesDeCompras(clienteId);
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado: " + e.getMessage());
        }
    }
}