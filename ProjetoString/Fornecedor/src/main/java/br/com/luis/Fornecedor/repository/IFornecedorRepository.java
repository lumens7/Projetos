package br.com.luis.Fornecedor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.luis.Fornecedor.domain.Fornecedor;

/**
 * @author br.com.luis
 *
 */
@Repository
public interface IFornecedorRepository extends MongoRepository<Fornecedor, String>{
	Optional<Fornecedor> findByCnpj(Long cnpj);
}
