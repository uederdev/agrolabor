package br.com.ueder.agro_labor_api.dtos.colaborador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosColaboradorUpdate(
        @NotNull
        Long id,
        @NotBlank
        @Size(min = 3, max = 80)
        String nome,
        @NotNull
        Long turma,
        @NotNull
        Long sequencial
) {
}
