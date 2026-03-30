package br.com.ueder.agro_labor_api.mappers;

import br.com.ueder.agro_labor_api.dtos.tarefa.DadosTarefa;
import br.com.ueder.agro_labor_api.dtos.tarefa.DadosTarefaCreate;
import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.entities.tarefa.Tarefa;
import br.com.ueder.agro_labor_api.entities.tarefa.TarefaBuilder;
import org.springframework.stereotype.Component;


@Component
public class TarefaMapper implements IModelMapper<Tarefa, DadosTarefa> {

    @Override
    public Tarefa toModel(DadosTarefa dto) {
        return TarefaBuilder.builder()
                .id(dto.id())
                .descricao(dto.descricao())
                .quantidadeMinima(dto.quantidadeMinima())
                .quantidadeMaxima(dto.quantidadeMaxima())
                .valorMinimo(dto.valorMinimo())
                .valorMaximo(dto.valorMaximo())
                .controle(dto.controle())
                .build();
    }

    @Override
    public DadosTarefa toDTO(Tarefa model) {
        return new DadosTarefa(model.getId(), model.getDescricao(), model.getValorMinimo(), model.getValorMaximo(),
                model.getQuantidadeMinima(), model.getQuantidadeMaxima(), model.getControle(), model.getEmpresa().getId());
    }

    public Tarefa toModel(DadosTarefaCreate dados, Long tenantId) {
        return TarefaBuilder.builder()
                .descricao(dados.descricao())
                .quantidadeMaxima(dados.quantidadeMaxima())
                .quantidadeMinima(dados.quantidadeMinima())
                .valorMaximo(dados.valorMaximo())
                .valorMinimo(dados.valorMinimo())
                .controle(dados.controle())
                .empresa(new Empresa(tenantId))
                .build();
    }
}
