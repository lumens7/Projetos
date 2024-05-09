package br.com.luis.Compras.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.luis.Compras.domain.Compra;

/**
 * @author br.com.luis
 *
 */
@Repository
public interface IComprasRepository extends MongoRepository<Compra, String>{
	
	Optional<Compra> findById(String id);
}
