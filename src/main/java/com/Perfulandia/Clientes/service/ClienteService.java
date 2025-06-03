package com.Perfulandia.Clientes.service;

import com.Perfulandia.Clientes.ProductoApiClient;
import com.Perfulandia.Clientes.dto.ClienteConHistorialDTO;
import com.Perfulandia.Clientes.dto.ProductoDTO;
import com.Perfulandia.Clientes.model.Cliente;
import com.Perfulandia.Clientes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    private ProductoApiClient productoApiClient;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ProductoApiClient productoApiClient) {
        this.clienteRepository = clienteRepository;
        this.productoApiClient = productoApiClient;
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getClienteByRut(String rut) {
        return clienteRepository.findByRut(rut);
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        cliente.setUsuarioId(clienteDetails.getUsuarioId());
        cliente.setNombreCompleto(clienteDetails.getNombreCompleto());
        cliente.setRut(clienteDetails.getRut());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setDireccion(clienteDetails.getDireccion());
        return clienteRepository.save(cliente);
    }

    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Cliente registrarProductoComprado(Long clienteId, Long productoId) {
        // Opcional: Verificar que el producto exista en el catálogo
        try {
            ProductoDTO productoExistente = productoApiClient.obtenerProductoPorId(productoId)
                    .blockOptional()
                    .orElse(null);
            if (productoExistente == null) {
                throw new RuntimeException("No se puede registrar la compra: Producto con ID " + productoId + " no encontrado.");
            }
        } catch (WebClientResponseException webEx) {
            throw new RuntimeException("Error de comunicación al verificar el producto ID " + productoId + ": " + webEx.getStatusCode() + " - " + webEx.getResponseBodyAsString(), webEx);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al verificar el producto ID " + productoId + ": " + e.getMessage(), e);
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        if (cliente.getIdsProductosComprados() == null) {
            cliente.setIdsProductosComprados(new ArrayList<>());
        }
        cliente.getIdsProductosComprados().add(productoId);
        return clienteRepository.save(cliente);
    }

    public ClienteConHistorialDTO obtenerClienteConDetallesDeCompras(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        List<ProductoDTO> productosDetallados = Collections.emptyList(); // Por defecto, lista vacía

        List<Long> idsComprados = cliente.getIdsProductosComprados();
        if (idsComprados != null && !idsComprados.isEmpty()) {

            productosDetallados = idsComprados.stream()
                    .map(idProducto -> {
                        try {
                            return productoApiClient.obtenerProductoPorId(idProducto).blockOptional().orElse(null);
                        } catch (WebClientResponseException webEx) {
                            System.err.println("Error (HTTP " + webEx.getStatusCode() + ") al obtener detalle para producto ID " + idProducto + ". Omitiendo.");
                            return null;
                        } catch (Exception e) {
                            System.err.println("Error general al obtener detalle para producto ID " + idProducto + ": " + e.getMessage() + ". Omitiendo.");
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return new ClienteConHistorialDTO(cliente, productosDetallados);
    }
}
