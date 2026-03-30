package br.com.ueder.agro_labor_api.mappers;

import br.com.ueder.agro_labor_api.dtos.endereco.DadosEndereco;
import br.com.ueder.agro_labor_api.entities.endereco.Endereco;
import br.com.ueder.agro_labor_api.entities.endereco.EnderecoBuilder;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper implements IModelMapper<Endereco, DadosEndereco> {

    @Override
    public Endereco toModel(DadosEndereco dto) {
        return EnderecoBuilder.builder()
                .build();
    }

    @Override
    public DadosEndereco toDTO(Endereco model) {
        return null;
    }
}
