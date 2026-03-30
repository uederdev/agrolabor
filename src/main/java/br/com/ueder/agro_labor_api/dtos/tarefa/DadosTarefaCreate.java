package br.com.ueder.agro_labor_api.dtos.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DadosTarefaCreate(
        @NotBlank
        @Size(min = 3, max = 80)
        String descricao,
        @NotNull
        BigDecimal valorMinimo,
        @NotNull
        BigDecimal valorMaximo,
        @NotNull
        BigDecimal quantidadeMinima,
        @NotNull
        BigDecimal quantidadeMaxima,
        @NotBlank
        @Size(min = 1, max = 10)
        String controle
) {
}
