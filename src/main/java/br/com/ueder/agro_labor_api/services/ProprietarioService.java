package br.com.ueder.agro_labor_api.services;

import br.com.ueder.agro_labor_api.dtos.proprietario.DadosProprietario;
import br.com.ueder.agro_labor_api.dtos.proprietario.DadosProprietarioCreate;
import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.entities.proprietario.Proprietario;
import br.com.ueder.agro_labor_api.mappers.ProprietarioMapper;
import br.com.ueder.agro_labor_api.repositories.ProprietarioRepository;
import br.com.ueder.agro_labor_api.utils.validators.ProprietarioValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioMapper mapper;
    private final ProprietarioValidator validator;

    public ProprietarioService(ProprietarioRepository proprietarioRepository, ProprietarioMapper mapper, ProprietarioValidator validator) {
        this.proprietarioRepository = proprietarioRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    public List<DadosProprietario> findAll(Long tenantId) {
        return proprietarioRepository.findAll(tenantId).stream().map(mapper::toDTO).toList();
    }

    public DadosProprietario findById(Long tenantId, Long id) {
        Proprietario proprietario = proprietarioRepository.findById(tenantId, id).orElseThrow(() -> new EntityNotFoundException("Proprietário não encontrado: id -> " + id));
        return mapper.toDTO(proprietario);
    }

    @Transactional
    public DadosProprietario create(Long tenantId, DadosProprietarioCreate dados) {
        Proprietario model = mapper.toModel(dados);
        validator.validate(model, tenantId);
        model.setEmpresa(new Empresa(tenantId));
        Proprietario save = proprietarioRepository.save(model);
        return mapper.toDTO(save);
    }
}
