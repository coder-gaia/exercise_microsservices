/**
 * @author alexandre.gaia
 */

package com.alexandreG.ClientService.usecase;

import com.alexandreG.ClientService.domain.Client;
import com.alexandreG.ClientService.exception.EntityNotFoundException;
import com.alexandreG.ClientService.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SearchClient {

    private IClientRepository clienteRepository;

    @Autowired
    public SearchClient(IClientRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public Page<Client> buscar(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Client buscarPorId(String id) throws Throwable {
        return (Client) clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Client.class, "id", id));
    }

    public Boolean isCadastrado(String id) {
        Optional<Client> cliente = clienteRepository.findById(id);
        return cliente.isPresent() ? true : false;
    }

    public Client buscarPorCpf(Long cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException(Client.class, "cpf", String.valueOf(cpf)));
    }


}
