package br.com.luis.Compras.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



/**
 * @author br.com.luis
 *
 */
@Service
public class FornecedorService {
	
	@Value("${application.fornecedorService.endpointConsultarFornecedor}")
	private String urlEndpointConsultarFornecedor;
	
	private RestUtils restUtils;
	
	@Autowired
	public FornecedorService(RestUtils restUtils) {
		this.restUtils = restUtils;
	}

	public Boolean isFornecedorCadastrado(String fornecedorId) {
		RestRequest restRequest = new RestRequest(HttpMethod.GET, null);
		restRequest.setContentType(MediaType.APPLICATION_JSON);
		restRequest.setAcceptable(Collections.singletonList(MediaType.APPLICATION_JSON));
		String urlComParam = urlEndpointConsultarFornecedor.replace("{id}", fornecedorId);
		ResponseEntity<Boolean> response = restUtils.execute(urlComParam, restRequest, Boolean.class);
		return response.getBody();
	}
}
