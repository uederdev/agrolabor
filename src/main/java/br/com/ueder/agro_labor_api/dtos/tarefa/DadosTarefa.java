package br.com.ueder.agro_labor_api.dtos.tarefa;

import java.math.BigDecimal;

public record DadosTarefa(
        Long id,
        String descricao,
        BigDecimal valorMinimo,
        BigDecimal valorMaximo,
        BigDecimal quantidadeMinima,
        BigDecimal quantidadeMaxima,
        String controle,
        Long empresa
) {
}
