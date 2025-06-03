package com.Perfulandia.Clientes.dto;


import com.Perfulandia.Clientes.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteConHistorialDTO {
    private Long id;
    private Long usuarioId;
    private String nombreCompleto;
    private String rut;
    private String telefono;
    private String direccion;
    private List<ProductoDTO> productosCompradosDetallados; // Lista de productos con sus detalles

    // Constructor para mapear desde la entidad Cliente y la lista de ProductoDTO
    public ClienteConHistorialDTO(Cliente cliente, List<ProductoDTO> productosCompradosDetallados) {
        this.id = cliente.getId();
        this.usuarioId = cliente.getUsuarioId();
        this.nombreCompleto = cliente.getNombreCompleto();
        this.rut = cliente.getRut();
        this.telefono = cliente.getTelefono();
        this.direccion = cliente.getDireccion();
        this.productosCompradosDetallados = productosCompradosDetallados;
    }
}
