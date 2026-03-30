package br.com.ueder.agro_labor_api.services;

import br.com.ueder.agro_labor_api.dtos.colaborador.DadosColaborador;
import br.com.ueder.agro_labor_api.dtos.colaborador.DadosColaboradorCreate;
import br.com.ueder.agro_labor_api.dtos.colaborador.DadosColaboradorUpdate;
import br.com.ueder.agro_labor_api.dtos.turma.DadosTurma;
import br.com.ueder.agro_labor_api.entities.colaborador.Colaborador;
import br.com.ueder.agro_labor_api.entities.colaborador.ColaboradorBuilder;
import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.entities.turma.Turma;
import br.com.ueder.agro_labor_api.mappers.ColaboradorMapper;
import br.com.ueder.agro_labor_api.mappers.TurmaMapper;
import br.com.ueder.agro_labor_api.repositories.ColaboradorRepository;
import br.com.ueder.agro_labor_api.utils.exceptions.RegistroDuplicadoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repository;
    private final ColaboradorMapper mapper;
    private final TurmaService turmaService;
    private final TurmaMapper turmaMapper;

    public ColaboradorService(ColaboradorRepository repository, ColaboradorMapper mapper, TurmaService turmaService, TurmaMapper turmaMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.turmaService = turmaService;
        this.turmaMapper = turmaMapper;
    }

    public List<DadosColaborador> findAll(Long tenantId) {
        return repository.findAll(tenantId).stream().map(mapper::toDTO).toList();
    }

    public DadosColaborador findById(Long tenantId, Long id) {
        Colaborador colaborador = repository.findById(tenantId, id).orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado: Id -> " + id));
        return mapper.toDTO(colaborador);
    }

    public DadosColaborador findByMatricula(Long tenantId, String matricula) {
        Colaborador colaborador = repository.findByMatricula(tenantId, matricula)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado: Matricula -> " + matricula));
        return mapper.toDTO(colaborador);
    }

    public List<DadosColaborador> findByTurmaId(Long tenantId, Long id) {
        return repository.findByTurmaId(tenantId, id).stream().map(mapper::toDTO).toList();
    }

    @Transactional
    public DadosColaborador create(Long tenantId, DadosColaboradorCreate dados) {
        Optional<Colaborador> colaboradorEncontrado = repository.findByMatricula(tenantId, dados.matricula());
        if (colaboradorEncontrado.isPresent()){
            throw new RegistroDuplicadoException("Matrícula já existe na base de dados: " + dados.matricula());
        }
        DadosTurma dadosTurma = turmaService.findById(tenantId, dados.turma());
        Turma turma = turmaMapper.toModel(dadosTurma);

        Colaborador colaborador = ColaboradorBuilder.builder()
                .matricula(dados.matricula())
                .nome(dados.nome())
                .empresa(new Empresa(tenantId))
                .turma(turma)
                .sequencial(0l)
                .build();
        Colaborador newColaborador = repository.save(colaborador);
        return mapper.toDTO(newColaborador);
    }

    @Transactional
    public void delete(Long tenantId, Long id) {
        DadosColaborador dadosColaborador = findById(tenantId, id);
        Colaborador colaborador = mapper.toModel(dadosColaborador);
        colaborador.setEmpresa(new Empresa(tenantId));
        colaborador.excluir();
        repository.save(colaborador);
    }

    @Transactional
    public DadosColaborador update(Long tenantId, DadosColaboradorUpdate dados) {
        DadosColaborador dadosColaborador = findById(tenantId, dados.id());
        DadosTurma dadosTurma = turmaService.findById(tenantId, dados.turma());
        Turma turma = turmaMapper.toModel(dadosTurma);
        Colaborador colaborador = ColaboradorBuilder.builder()
                .id(dadosColaborador.id())
                .nome(dados.nome())
                .sequencial(dados.sequencial())
                .matricula(dadosColaborador.matricula())
                .empresa(new Empresa(tenantId))
                .turma(turma)
                .build();
        return mapper.toDTO(repository.save(colaborador));
    }
}
