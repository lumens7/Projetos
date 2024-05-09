package br.com.luis.Fornecedor.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luis.Fornecedor.domain.Fornecedor;
import br.com.luis.Fornecedor.exception.EntityNotFoundException;
import br.com.luis.Fornecedor.repository.IFornecedorRepository;

/**
 * @author br.com.luis
 *
 */
@Service
public class BuscaFornecedor {
	
	private IFornecedorRepository fornecedorRepository;
	
	@Autowired
	public BuscaFornecedor(IFornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}
	
	public Page<Fornecedor> buscar(Pageable pageable){
		return fornecedorRepository.findAll(pageable);
	}
	
	public Fornecedor buscarPOrId(String id) {
		return fornecedorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Fornecedor.class, "id", id));
	}
	
	public Boolean isCadastrado(String id) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		return fornecedor.isPresent() ? true : true;
	}
	
	public Fornecedor buscarPorCnpj(Long cnpj) {
		return fornecedorRepository.findByCnpj(cnpj)
				.orElseThrow(()-> new EntityNotFoundException(Fornecedor.class, "cnpj", String.valueOf(cnpj)));
	}
}
