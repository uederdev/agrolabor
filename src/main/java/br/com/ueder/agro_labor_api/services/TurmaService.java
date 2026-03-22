package br.com.ueder.agro_labor_api.services;

import br.com.ueder.agro_labor_api.dtos.turma.DadosTurma;
import br.com.ueder.agro_labor_api.dtos.turma.DadosTurmaCreate;
import br.com.ueder.agro_labor_api.dtos.turma.DadosTurmaUpdate;
import br.com.ueder.agro_labor_api.entities.mappers.TurmaMapper;
import br.com.ueder.agro_labor_api.entities.turma.Turma;
import br.com.ueder.agro_labor_api.repositories.TurmaRepository;
import br.com.ueder.agro_labor_api.utils.exceptions.RNException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurmaService {
	
	private final TurmaRepository repository;
    private final TurmaMapper mapper;

    public TurmaService(TurmaRepository repository, TurmaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DadosTurma> findAll(Long tenantId) {
        return repository.findAll(tenantId).stream().map(mapper::toDTO).toList();
    }

    public DadosTurma findById(Long tenantId, Long id) {
        Turma turma = repository.findById(tenantId, id).orElseThrow(() -> new EntityNotFoundException("Turma não encontrada: " + id));
        return mapper.toDTO(turma);
    }

    @Transactional
    public DadosTurma create(DadosTurmaCreate dados, Long tenantId) {
        Turma model = mapper.toModel(dados, tenantId);
        if (repository.findByControle(tenantId, dados.controle()).isPresent()){
            throw new RNException("Turma já existente. Controle: " + dados.controle());
        }
        Turma turmaNew = repository.save(model);
        return mapper.toDTO(turmaNew);
    }

    @Transactional
    public void delete(Long tenantId, Long id) {
        DadosTurma turmaEncontrada = findById(tenantId, id);
        Turma model = mapper.toModel(turmaEncontrada, tenantId);
        model.excluir();
        repository.save(model);
    }

    @Transactional
    public DadosTurma update(Long tenantId, DadosTurmaUpdate dados) {
        DadosTurma turmaEncontrada = findById(tenantId, dados.id());
        Turma model = mapper.toModel(turmaEncontrada, tenantId);
        model.setControle(dados.controle());
        model.setDescricao(dados.descricao());
        Turma turmaNew = repository.save(model);
        return mapper.toDTO(turmaNew);
    }
}
