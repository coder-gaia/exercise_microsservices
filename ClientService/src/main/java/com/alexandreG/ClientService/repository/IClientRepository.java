/**
 * @author alexandre.gaia
 */

package com.alexandreG.ClientService.repository;

import com.alexandreG.ClientService.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends MongoRepository<Client, String> {
    @Autowired
    Optional<Client> findByCpf(Long cpf);
}
