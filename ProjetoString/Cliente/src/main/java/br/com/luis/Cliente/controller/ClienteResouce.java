package br.com.luis.Cliente.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luis.Cliente.Domain.Cliente;
import br.com.luis.Cliente.usecase.BuscaCliente;
import br.com.luis.Cliente.usecase.CadastroCliente;

/**
 * @author br.com.luis
 *
 */
@RestController
@RequestMapping(value = "/cliente")
public class ClienteResouce {
	
	private BuscaCliente buscaCliente;
	private CadastroCliente cadastroCliente;
	
	@Autowired
	public ClienteResouce(BuscaCliente buscarCliente, CadastroCliente cadastroCliente) {
		this.buscaCliente = buscarCliente;
		this.cadastroCliente = cadastroCliente;
	}
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> buscar(Pageable pageable) {
		return ResponseEntity.ok(buscaCliente.buscar(pageable));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente){
		return ResponseEntity.ok(cadastroCliente.cadastrar(cliente));
	}
	

}
