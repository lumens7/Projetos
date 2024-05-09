package br.com.luis.Vendas.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.luis.Vendas.domain.Produto;

/**
 * @author br.com.luis
 *
 */
@FeignClient(name = "produto", url = "${application.produtoService.endpointConsultarProduto}")
public interface IProdutoService {
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json", headers = "application/json")
	Produto buscarProduto(@RequestParam("id") String id);
	
}
