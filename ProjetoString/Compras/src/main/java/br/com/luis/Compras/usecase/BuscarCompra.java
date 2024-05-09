package br.com.luis.Compras.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luis.Compras.domain.Compra;
import br.com.luis.Compras.exception.EntityNotFoundException;
import br.com.luis.Compras.repository.IComprasRepository;

/**
 * @author br.com.luis
 *
 */
@Service
public class BuscarCompra {
	
	private IComprasRepository compraRepository;
	
	@Autowired
	public BuscarCompra(IComprasRepository compraRepository) {
		this.compraRepository = compraRepository;
	}
	public Page<Compra> busca(Pageable pageable){
		return compraRepository.findAll(pageable);
	}
	
	public Compra buscarPorId(String id) {
		return compraRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException(Compra.class, "id", id));
	}

}
