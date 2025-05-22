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

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getCliente(String rut){
        return clienteRepository.findByRut(rut);
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

}
