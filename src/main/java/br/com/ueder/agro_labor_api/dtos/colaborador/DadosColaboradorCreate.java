package br.com.ueder.agro_labor_api.dtos.colaborador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosColaboradorCreate(
        @NotBlank
        @Size(min = 3, max = 80)
        String nome,
        @NotNull
        Long turma,
        @NotBlank
        @Size(min = 1, max = 10)
        String matricula
) {

}
