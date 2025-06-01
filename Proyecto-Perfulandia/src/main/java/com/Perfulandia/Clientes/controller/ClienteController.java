package com.Perfulandia.Clientes.controller;

import ch.qos.logback.core.net.server.Client;
import com.Perfulandia.Clientes.model.Cliente;
import com.Perfulandia.Clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Clientes")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

        @PostMapping //Solicitudes POST
        public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
            Cliente savedCliente = clienteService.saveCliente(cliente);
            return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
        }

        @GetMapping("/rut/{rut}")
        public ResponseEntity<Cliente> getClienteByRut(@PathVariable String rut) {
            Optional<Cliente> cliente = clienteService.getClienteByRut(rut);
            return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
            Optional<Cliente> cliente = clienteService.getClienteById(id);
            return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping // Obtener todos los clientes
        public ResponseEntity<List<Cliente>> getAllClientes() {
            List<Cliente> clientes = clienteService.getAllClientes();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }

        @PutMapping("/{id}") //Update
        public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
            try {
                Cliente clienteActualizado = clienteService.updateCliente(id, clienteDetails);
                return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}") //Delete
        public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
            boolean deleted = clienteService.deleteCliente(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }


