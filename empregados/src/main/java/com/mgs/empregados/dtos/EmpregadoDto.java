package com.mgs.empregados.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmpregadoDto ( @NotBlank String nome,
		@NotBlank String telefone,
		@NotBlank String email,
		@NotBlank String alergias,
		@NotBlank String problemasmedicos) {
	
}
