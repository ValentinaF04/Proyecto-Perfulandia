package com.Perfulandia.Clientes.service;

import com.Perfulandia.Clientes.model.Cliente;
import com.Perfulandia.Clientes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getClienteByRut(String rut){
        return clienteRepository.findByRut(rut);
    }

    public Optional<Cliente> getClienteById(Long id){
        return clienteRepository.findById(id);
    }
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setUsuarioId(clienteDetails.getUsuarioId());
        cliente.setNombreCompleto(clienteDetails.getNombreCompleto());
        cliente.setRut(clienteDetails.getRut());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setDireccion(clienteDetails.getDireccion());

        return clienteRepository.save(cliente);
    }

    public boolean deleteCliente(Long id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }

}
