package br.com.luis.Produto.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.luis.Produto.domain.Produto;
import br.com.luis.Produto.domain.Produto.Status;

/**
 * @author br.com.luis
 *
 */
@Repository
public interface IProdutoRepository extends MongoRepository<Produto, String>{

	Optional<Produto> findById(String id);
	
	Page<Produto> findAllByStatus(Pageable pageable, Status status);
}
