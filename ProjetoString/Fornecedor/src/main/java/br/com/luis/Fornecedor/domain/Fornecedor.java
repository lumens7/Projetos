package br.com.luis.Fornecedor.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "fornecedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name="Fornecedor", description="Fornecedor") 
public class Fornecedor {
	
	@Id
	@Schema(description="Identificador único") 
	private String id;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="nome", minLength = 1, maxLength=50, nullable = false)
	private String nome;
	
	@NotNull
	@Indexed(unique = true, background = true)
	@Schema(description="cnpj", nullable = false) 
	private String cnpj;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Indexed(unique = true, background = true)
	@Schema(description="email", minLength = 1, maxLength=50, nullable = false)
	@Pattern(regexp = ".+@.+\\..+", message = "Email inválido")
	private String email;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="endereco", minLength = 1, maxLength=50, nullable = false)
	private String endereco;
	
	@NotNull
	@Schema(description="telefone", nullable = false) 
	private String telefone;
	

}
