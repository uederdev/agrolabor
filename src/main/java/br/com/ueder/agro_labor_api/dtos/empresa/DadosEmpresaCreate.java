package br.com.ueder.agro_labor_api.dtos.empresa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record DadosEmpresaCreate(
        @NotBlank
        @Size(min = 3, max = 100)
        String razaoSocial,
        @NotBlank
        @Size(min = 3, max = 100)
        String nomeFantasia,
        @NotBlank
        @CNPJ
        String cnpj
) {
}
