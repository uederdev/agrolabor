package br.com.ueder.agro_labor_api.dtos.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosTurmaCreate(
        @NotBlank
        @Size(min = 3, max = 80)
        String descricao,
        @NotBlank
        @Size(min = 1, max = 10)
        String controle
) {

}
