package br.com.ueder.agro_labor_api.services;

import br.com.ueder.agro_labor_api.dtos.tarefa.DadosTarefa;
import br.com.ueder.agro_labor_api.dtos.tarefa.DadosTarefaCreate;
import br.com.ueder.agro_labor_api.dtos.tarefa.DadosTarefaUpdate;
import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.entities.tarefa.Tarefa;
import br.com.ueder.agro_labor_api.mappers.TarefaMapper;
import br.com.ueder.agro_labor_api.repositories.TarefaRepository;
import br.com.ueder.agro_labor_api.utils.exceptions.RegistroDuplicadoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository repository;
    private final TarefaMapper mapper;

    public TarefaService(TarefaRepository repository, TarefaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DadosTarefa> findAll(Long tenantId) {
        return repository.findAll(tenantId).stream().map(mapper::toDTO).toList();
    }

    public DadosTarefa findById(Long tenantId, Long id) {
        Tarefa tarefa = repository.findById(tenantId, id).orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada: " + id));
        return mapper.toDTO(tarefa);
    }

    @Transactional
    public DadosTarefa create(DadosTarefaCreate dados, Long tenantId) {
        Optional<Tarefa> tarefaEncontrada = repository.findByControle(tenantId, dados.controle());
        if  (tarefaEncontrada.isPresent()) {
            throw new RegistroDuplicadoException("Tarefa -> Controle: " + dados.controle());
        }
        Tarefa model = mapper.toModel(dados, tenantId);
        return mapper.toDTO(repository.save(model));
    }

    @Transactional
    public void delete(Long tenantId, Long id) {
        DadosTarefa tarefaEncontrada = findById(tenantId, id);
        Tarefa model = mapper.toModel(tarefaEncontrada);
        model.setEmpresa(new Empresa(tenantId));
        model.excluir();
        repository.save(model);
    }

    @Transactional
    public DadosTarefa update(Long tenantId, @Valid DadosTarefaUpdate dados) {
        DadosTarefa dadosEncontrado = findById(tenantId, dados.id());
        Tarefa model = mapper.toModel(dadosEncontrado);
        model.setEmpresa(new Empresa(tenantId));
        model.setDescricao(dados.descricao());
        model.setQuantidadeMaxima(dados.quantidadeMaxima());
        model.setQuantidadeMinima(dados.quantidadeMinima());
        model.setValorMaximo(dados.valorMaximo());
        model.setValorMinimo(dados.valorMinimo());
        return mapper.toDTO(repository.save(model));
    }
}
