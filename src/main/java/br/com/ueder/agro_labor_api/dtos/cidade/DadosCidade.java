package br.com.ueder.agro_labor_api.dtos.cidade;

import br.com.ueder.agro_labor_api.entities.enums.Estado;

public record DadosCidade(
        String id,
        String nome,
        Estado estado
) {
}
