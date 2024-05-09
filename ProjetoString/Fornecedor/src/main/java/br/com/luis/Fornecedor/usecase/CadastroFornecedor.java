package br.com.luis.Fornecedor.usecase;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luis.Fornecedor.domain.Fornecedor;
import br.com.luis.Fornecedor.repository.IFornecedorRepository;

/**
 * @author br.com.luis
 *
 */
@Service
public class CadastroFornecedor {
	private IFornecedorRepository fornecedorRepository;
	
	@Autowired
	public CadastroFornecedor(IFornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}
	
	public Fornecedor cadastrar(@Valid Fornecedor fornecedor) {
		return this.fornecedorRepository.insert(fornecedor);
	}
	
	public Fornecedor atualizar(@Valid Fornecedor fornecedor) {
		return this.fornecedorRepository.save(fornecedor);
	}
	
	public void remover(String id) {
		this.fornecedorRepository.deleteById(id);
	}
}
