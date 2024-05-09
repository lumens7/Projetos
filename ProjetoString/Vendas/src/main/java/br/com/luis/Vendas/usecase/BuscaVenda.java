package br.com.luis.Vendas.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luis.Vendas.domain.Venda;
import br.com.luis.Vendas.exception.EntityNotFoundException;
import br.com.luis.Vendas.repository.IVendasRepository;

/**
 * @author br.com.luis
 *
 */
@Service
public class BuscaVenda {

	private IVendasRepository vendaRepository;
	
	@Autowired
	public BuscaVenda(IVendasRepository produtoRepository) {
		this.vendaRepository = produtoRepository;
	}
	
	public Page<Venda> buscar(Pageable pageable) {
		return vendaRepository.findAll(pageable);
	}

	public Venda buscarPorId(String id) {
		return vendaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Venda.class, "id", id));
	}
}
