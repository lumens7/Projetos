package br.com.luis.Produto.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luis.Produto.domain.Produto;
import br.com.luis.Produto.domain.Produto.Status;
import br.com.luis.Produto.exception.EntityNotFoundException;
import br.com.luis.Produto.repository.IProdutoRepository;

/**
 * @author br.com.luis
 *
 */
@Service
public class BuscaProduto {
	private IProdutoRepository produtoRepository;
	
	@Autowired
	public BuscaProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Page<Produto> buscar(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}
	
	public Page<Produto> buscar(Pageable pageable, Status status) {
		return produtoRepository.findAllByStatus(pageable, status);
	}

	public Produto buscarPorId(String id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Produto.class, "id", id));
	}
}
