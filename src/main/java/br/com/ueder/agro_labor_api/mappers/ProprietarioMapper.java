package br.com.ueder.agro_labor_api.mappers;

import br.com.ueder.agro_labor_api.dtos.proprietario.DadosProprietario;
import br.com.ueder.agro_labor_api.dtos.proprietario.DadosProprietarioCreate;
import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.entities.proprietario.Proprietario;
import br.com.ueder.agro_labor_api.entities.proprietario.ProprietarioBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioMapper implements IModelMapper<Proprietario, DadosProprietario>{

    private final CidadeMapper cidadeMapper;
    private final EnderecoMapper enderecoMapper;

    public ProprietarioMapper(CidadeMapper cidadeMapper, EnderecoMapper enderecoMapper) {
        this.cidadeMapper = cidadeMapper;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    public Proprietario toModel(DadosProprietario dto) {
        return ProprietarioBuilder.builder()
                .id(dto.id())
                .nome(dto.nome())
                .cpf(dto.cpf())
                .email(dto.email())
                .telefone(dto.telefone())
                .dataNascimento(dto.dataNascimento())
                .empresa(new Empresa(dto.id()))
                .build();
    }

    @Override
    public DadosProprietario toDTO(Proprietario model) {
        return new DadosProprietario(model.getId(), model.getNome(), model.getCpf(),  model.getEmail(),
                model.getTelefone(), model.getDataNascimento());
    }

    public Proprietario toModel(DadosProprietarioCreate dados) {
        return ProprietarioBuilder.builder()
                .nome(dados.nome())
                .cpf(dados.cpf())
                .email(dados.email())
                .telefone(dados.telefone())
                .dataNascimento(dados.dataNascimento())
                .controle(dados.controle())
                .build();
    }
}
