/**
 * @author alexandre.gaia
 */

package com.alexandreG.ClientService.usecase;

import com.alexandreG.ClientService.domain.Client;
import com.alexandreG.ClientService.repository.IClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterClient {

    private IClientRepository clienteRepository;

    @Autowired
    public RegisterClient(IClientRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Client cadastrar(@Valid Client cliente) {
        return (Client) this.clienteRepository.insert(cliente);
    }

    public Client atualizar(@Valid Client cliente) {
        return (Client) this.clienteRepository.save(cliente);
    }

    public void remover(String id) {
        this.clienteRepository.deleteById(id);
    }

}
