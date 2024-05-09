package br.com.luis.Vendas.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.luis.Vendas.domain.Venda;

/**
 * @author br.com.luis
 *
 */
@Repository
public interface IVendasRepository extends MongoRepository<Venda, String>{

	Optional<Venda> findById(String id);
}
