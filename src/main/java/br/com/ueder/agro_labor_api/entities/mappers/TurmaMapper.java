package br.com.ueder.agro_labor_api.entities.mappers;

import br.com.ueder.agro_labor_api.dtos.turma.DadosTurma;
import br.com.ueder.agro_labor_api.dtos.turma.DadosTurmaCreate;
import br.com.ueder.agro_labor_api.entities.turma.Turma;
import br.com.ueder.agro_labor_api.entities.turma.TurmaBuilder;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper implements IModelMapper<Turma, DadosTurma>{

    @Override
    public Turma toModel(DadosTurma dto) {
        return TurmaBuilder.builder()
                .id(dto.id())
                .descricao(dto.descricao())
                .controle(dto.controle())
                .build();
    }

    public Turma toModel(DadosTurma dto, Long tenantId) {
        return TurmaBuilder.builder()
                .id(dto.id())
                .descricao(dto.descricao())
                .controle(dto.controle())
                .empresa(tenantId)
                .build();
    }

    public Turma toModel(DadosTurmaCreate dto) {
        return TurmaBuilder.builder()
                .descricao(dto.descricao())
                .controle(dto.controle())
                .build();
    }

    public Turma toModel(DadosTurmaCreate dto, Long tenantId) {
        return TurmaBuilder.builder()
                .descricao(dto.descricao())
                .controle(dto.controle())
                .empresa(tenantId)
                .build();
    }



    @Override
    public DadosTurma toDTO(Turma model) {
        return new DadosTurma(model.getId(), model.getDescricao(), model.getControle(), model.getEmpresa().getId());
    }
}
