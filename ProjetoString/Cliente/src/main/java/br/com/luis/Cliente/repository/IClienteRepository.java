package br.com.luis.Cliente.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.luis.Cliente.Domain.Cliente;

/**
 * @author br.com.luis
 *
 */
@Repository
public interface IClienteRepository extends MongoRepository<Cliente, String>{
	Optional<Cliente> findByCpf(Long cpf);
}
