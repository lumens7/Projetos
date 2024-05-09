package br.com.luis.Vendas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.Vendas.domain.Venda;
import br.com.luis.Vendas.dto.VendaDTO;
import br.com.luis.Vendas.usecase.BuscaVenda;
import br.com.luis.Vendas.usecase.CadastroVenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * @author br.com.luis
 *
 */
@RestController
@RequestMapping(value = "/venda")
public class VendaResource {
	
	private BuscaVenda buscaVenda;
	
	private CadastroVenda cadastroVenda;
	
	@Autowired
	public VendaResource(BuscaVenda buscaProduto,
			CadastroVenda cadastroProduto) {
		this.buscaVenda = buscaProduto;
		this.cadastroVenda = cadastroProduto;
	}
	
	@GetMapping
	@Operation(summary = "Busca uma lista paginada de vendas")
	public ResponseEntity<Page<Venda>> buscar(Pageable pageable) {
		return ResponseEntity.ok(buscaVenda.buscar(pageable));
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Busca uma venda pelo id")
	public ResponseEntity<Venda> buscarPorCodigo(String id) {
		return ResponseEntity.ok(buscaVenda.buscarPorId(id));
	}
	
	@PostMapping
	@Operation(summary = "Iniciar uma venda")
	public ResponseEntity<Venda> cadastrar(@RequestBody @Valid VendaDTO venda) {
		return ResponseEntity.ok(cadastroVenda.cadastrar(venda));
	}
	
	@PutMapping
	@Operation(summary = "Atualiza uma venda")
	public ResponseEntity<Venda> atualizar(@RequestBody @Valid Venda venda) {
		return ResponseEntity.ok(cadastroVenda.atualizar(venda));
	}	
	
	@PostMapping(value = "/{id}/{idProduto}/{quantidade}/adicionarProduto")
	@Operation(summary = "Adiciona produtos a uma venda")
	public ResponseEntity<Venda> adicionarProduto(
			@PathVariable(value = "id", required = true) String id,
			@PathVariable(value = "idProduto", required = true) String idProduto,
			@PathVariable(value = "quantidade", required = true) Integer quantidade) {
		return ResponseEntity.ok(cadastroVenda.adicionarProduto(id, idProduto, quantidade));
	}	
	
	@PostMapping(value = "/{id}/{idProduto}/{quantidade}/removerProduto")
	@Operation(summary = "Remove produtos a uma venda")
	public ResponseEntity<Venda> removerProduto(
			@PathVariable(value = "id", required = true) String id,
			@PathVariable(value = "idProduto", required = true) String idProduto,
			@PathVariable(value = "quantidade", required = true) Integer quantidade) {
		return ResponseEntity.ok(cadastroVenda.removerProduto(id, idProduto, quantidade));
	}	
	
	@PutMapping(value = "/{id}/finalizar")
	@Operation(summary = "Finaliza uma venda pelo seu identificador único")
	public ResponseEntity<Venda> finalizar(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(cadastroVenda.finalizar(id));
	}
	
	@PutMapping(value = "/{id}/cancelar")
	@Operation(summary = "Cancela uma venda pelo seu identificador único")
	public ResponseEntity<Venda> cancelar(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(cadastroVenda.cancelar(id));
	}
}
