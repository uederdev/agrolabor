package br.com.ueder.agro_labor_api.dtos.empresa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosEmpresaUpdate(
        @NotNull
        Long id,
        @NotBlank
        @Size(min = 3, max = 100)
        String razaoSocial,
        @NotBlank
        @Size(min = 3, max = 100)
        String nomeFantasia
) {
}
