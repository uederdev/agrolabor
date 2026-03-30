package br.com.ueder.agro_labor_api.mappers;

import br.com.ueder.agro_labor_api.dtos.colaborador.DadosColaborador;
import br.com.ueder.agro_labor_api.entities.colaborador.Colaborador;
import br.com.ueder.agro_labor_api.entities.colaborador.ColaboradorBuilder;
import org.springframework.stereotype.Component;

@Component
public class ColaboradorMapper implements IModelMapper<Colaborador, DadosColaborador> {

    private final TurmaMapper turmaMapper;

    public ColaboradorMapper(TurmaMapper turmaMapper) {
        this.turmaMapper = turmaMapper;
    }

    @Override
    public Colaborador toModel(DadosColaborador dto) {
        return ColaboradorBuilder.builder()
                .id(dto.id())
                .nome(dto.nome())
                .turma(turmaMapper.toModel(dto.turma()))
                .matricula(dto.matricula())
                .sequencial(dto.sequencial())
                .build();
    }

    @Override
    public DadosColaborador toDTO(Colaborador model) {
        return new DadosColaborador(model.getId(), model.getSequencial(), model.getMatricula(), model.getNome(),
                turmaMapper.toDTO(model.getTurma()));
    }
}
