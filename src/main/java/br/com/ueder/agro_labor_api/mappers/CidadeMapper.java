package br.com.ueder.agro_labor_api.mappers;

import br.com.ueder.agro_labor_api.dtos.cidade.DadosCidade;
import br.com.ueder.agro_labor_api.entities.endereco.Cidade;
import br.com.ueder.agro_labor_api.entities.endereco.CidadeBuilder;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper implements IModelMapper<Cidade, DadosCidade>{

    @Override
    public Cidade toModel(DadosCidade dto) {
        return CidadeBuilder.builder()
                .id(dto.id())
                .nome(dto.nome())
                .estado(dto.estado())
                .build();
    }

    @Override
    public DadosCidade toDTO(Cidade model) {
        return new DadosCidade(model.getId(), model.getNome(), model.getEstado());
    }
}
