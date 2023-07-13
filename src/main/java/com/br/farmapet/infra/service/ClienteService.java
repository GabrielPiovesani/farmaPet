package com.br.farmapet.infra.service;

import com.br.farmapet.domain.model.Cliente;
import com.br.farmapet.domain.dto.request.ClienteDTO;
import com.br.farmapet.infra.exception.ClienteNotFoundException;
import com.br.farmapet.infra.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());

        Cliente savedCliente = clienteRepository.save(cliente);

        ClienteDTO savedClienteDTO = new ClienteDTO();
        savedClienteDTO.setNome(savedCliente.getNome());
        savedClienteDTO.setEmail(savedCliente.getEmail());

        return savedClienteDTO;
    }

    public ClienteDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com o ID: " + id));

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());

        return clienteDTO;
    }

    // Outros métodos de serviço conforme necessário
}