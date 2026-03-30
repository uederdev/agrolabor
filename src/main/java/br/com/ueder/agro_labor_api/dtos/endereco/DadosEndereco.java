package br.com.ueder.agro_labor_api.dtos.endereco;

import br.com.ueder.agro_labor_api.dtos.cidade.DadosCidade;
import br.com.ueder.agro_labor_api.entities.enums.TipoEndereco;

public record DadosEndereco(
        TipoEndereco tipoEndereco,
        String logradouro,
        String numero,
        String bairro,
        String complemento,
        DadosCidade cidade,
        String cep
) {
}
