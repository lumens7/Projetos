package br.com.luis.Compras.usecase;

import java.math.BigDecimal;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luis.Compras.domain.Compra;
import br.com.luis.Compras.domain.Compra.Status;
import br.com.luis.Compras.domain.Produto;
import br.com.luis.Compras.dto.CompraDTO;
import br.com.luis.Compras.exception.EntityNotFoundException;
import br.com.luis.Compras.repository.IComprasRepository;
import br.com.luis.Compras.service.FornecedorService;
import br.com.luis.Compras.service.IProdutoService;

/**
 * @author br.com.luis
 *
 */
@Service
public class CadastrarCompra {
	
	private IComprasRepository compraRepository;
	private IProdutoService produtoService;
	private FornecedorService fornecedorService;
	
	@Autowired
	public CadastrarCompra(IComprasRepository compraRepository, IProdutoService produtoService, FornecedorService fornecedorService) {
		this.compraRepository = compraRepository;
		this.produtoService = produtoService;
		this.fornecedorService = fornecedorService;
	}
	
	public Compra cadastrar(@Valid CompraDTO compraDTO) {
		Compra compra = convertToDomain(compraDTO, Status.INICIADA);
		validarFornecedor(compra.getFornecedorId());
		compra.recalcularValorTotalCompra();
		return this.compraRepository.insert(compra);
	}
	
	private void validarFornecedor(String fornecedorId) {
		Boolean isCadastrado = 
				this.fornecedorService.isFornecedorCadastrado(fornecedorId);
		if(!isCadastrado) {
			new EntityNotFoundException(Compra.class, "fornecedorId", fornecedorId);
		}
	}
	
	private Compra convertToDomain(@Valid CompraDTO compraDTO, Status status) {
		return Compra.builder()
				.fornecedorId(compraDTO.getFornecedorId())
				.dataCompra(compraDTO.getDataCompra())
				.status(status)
				.valorTotal(BigDecimal.ZERO)
				.produtos(new HashSet<>())
				.build();
	}
	
	public Compra atualizar(@Valid Compra compra) {
		return this.compraRepository.save(compra);
	}
	
	public Compra finalizar(String id) {
		Compra compra = buscarCompra(id);
		compra.validarStatus();
		compra.setStatus(Status.CONCLUIDA);
		return this.compraRepository.save(compra);
	}
	
	public Compra cancelar(String id) {
		Compra compra = buscarCompra(id);
		compra.validarStatus();
		compra.setStatus(Status.CANCELADA);
		return this.compraRepository.save(compra);
	}
	
	public Compra adicionarProduto(String id, String idProduto, Integer quantidade) {
		Compra compra = buscarCompra(id);
		Produto produto = buscarProduto(idProduto);
		compra.validarStatus();
		compra.adicionarProduto(produto, quantidade);
		return this.compraRepository.save(compra);
	}
	
	public Compra removerProduto(String id, String idProduto, Integer quantidade) {
		Compra compra = buscarCompra(id);
		Produto produto = buscarProduto(idProduto);
		compra.validarStatus();
		compra.removerProduto(produto, quantidade);
		return this.compraRepository.save(compra);
	}
	
	private Compra buscarCompra(String id) {
		return compraRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Compra.class, "id", id));
		
	}
	
	private Produto buscarProduto(String idProduto) {
		Produto prod = produtoService.buscarProduto(idProduto);
		if (prod == null) {
			throw new EntityNotFoundException(Produto.class, "id", idProduto);
		}
		return prod;
	}
	

}
