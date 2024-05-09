package br.com.luis.Compras.dto;

import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author br.com.luis
 *
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CompraDTO {
	
	@NotNull
	@Size(min = 2, max = 10)
	private String id;
	
	@NotNull
	private String fornecedorId;
	
	@NotNull
	private Instant dataCompra;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(String fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public Instant getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Instant dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	

}
