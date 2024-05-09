package br.com.luis.Compras.controller;

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

import br.com.luis.Compras.domain.Compra;
import br.com.luis.Compras.dto.CompraDTO;
import br.com.luis.Compras.usecase.BuscarCompra;
import br.com.luis.Compras.usecase.CadastrarCompra;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * @author br.com.luis
 *
 */
@RestController
@RequestMapping(value = "/compra")
public class ComprasResource {
	
	private BuscarCompra buscarCompra;
	
	private CadastrarCompra cadastrarCompra;
	
	@Autowired
	public ComprasResource(BuscarCompra buscarCompra, 
			CadastrarCompra cadastrarCompra) {
		this.buscarCompra = buscarCompra;
		this.cadastrarCompra = cadastrarCompra;
	}
	
	@GetMapping
	@Operation(summary = "Busca uma lista paginada de vendas")
	public ResponseEntity<Page<Compra>> buscar(Pageable pageable) {
		return ResponseEntity.ok(buscarCompra.busca(pageable));
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Busca uma venda pelo id")
	public ResponseEntity<Compra> buscarPorId(String id) {
		return ResponseEntity.ok(buscarCompra.buscarPorId(id));
	}
	
	@PostMapping
	@Operation(summary = "Iniciar uma venda")
	public ResponseEntity<Compra> cadastrar(@RequestBody @Valid CompraDTO compra) {
		return ResponseEntity.ok(cadastrarCompra.cadastrar(compra));
	}
	
	@PutMapping
	@Operation(summary = "Atualiza uma venda")
	public ResponseEntity<Compra> atualizar(@RequestBody @Valid Compra compra) {
		return ResponseEntity.ok(cadastrarCompra.atualizar(compra));
	}	
	
	@PostMapping(value = "/{id}/{idProduto}/{quantidade}/adicionarProduto")
	@Operation(summary = "Adiciona produtos a uma compra")
	public ResponseEntity<Compra> adicionarProduto(
			@PathVariable(value = "id", required = true) String id,
			@PathVariable(value = "idProduto", required = true) String idProduto,
			@PathVariable(value = "quantidade", required = true) Integer quantidade) {
		return ResponseEntity.ok(cadastrarCompra.adicionarProduto(id, idProduto, quantidade));
	}	
	
	@PostMapping(value = "/{id}/{idProduto}/{quantidade}/removerProduto")
	@Operation(summary = "Remove produtos a uma venda")
	public ResponseEntity<Compra> removerProduto(
			@PathVariable(value = "id", required = true) String id,
			@PathVariable(value = "idProduto", required = true) String idProduto,
			@PathVariable(value = "quantidade", required = true) Integer quantidade) {
		return ResponseEntity.ok(cadastrarCompra.removerProduto(id, idProduto, quantidade));
	}	
	
	@PutMapping(value = "/{id}/finalizar")
	@Operation(summary = "Finaliza uma venda pelo seu identificador único")
	public ResponseEntity<Compra> finalizar(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(cadastrarCompra.finalizar(id));
	}
	
	@PutMapping(value = "/{id}/cancelar")
	@Operation(summary = "Cancela uma venda pelo seu identificador único")
	public ResponseEntity<Compra> cancelar(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(cadastrarCompra.cancelar(id));
	}
}
