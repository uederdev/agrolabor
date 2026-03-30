package br.com.ueder.agro_labor_api.services;

import br.com.ueder.agro_labor_api.dtos.empresa.DadosEmpresa;
import br.com.ueder.agro_labor_api.dtos.empresa.DadosEmpresaCreate;
import br.com.ueder.agro_labor_api.dtos.empresa.DadosEmpresaUpdate;
import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.mappers.EmpresaMapper;
import br.com.ueder.agro_labor_api.repositories.EmpresaRepository;
import br.com.ueder.agro_labor_api.utils.Util;
import br.com.ueder.agro_labor_api.utils.exceptions.RNException;
import br.com.ueder.agro_labor_api.utils.exceptions.RegistroDuplicadoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository repository;
    private final EmpresaMapper mapper;

    public EmpresaService(EmpresaRepository repository, EmpresaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DadosEmpresa> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public DadosEmpresa findById(Long id) {
        Empresa empresaEncontrada = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada: " + id));
        return mapper.toDTO(empresaEncontrada);
    }

    public DadosEmpresa findByCnpj(String cnpj) {
        Empresa empresaEncontrada = repository.findByCnpj(cnpj).orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada - CNPJ: " + cnpj));
        return mapper.toDTO(empresaEncontrada);
    }

    @Transactional
    public DadosEmpresa create(DadosEmpresaCreate dados) {
        Empresa model = mapper.toModel(dados);
        if (repository.findByCnpj(model.getCnpj()).isPresent()) {
            throw new RegistroDuplicadoException("CNPJ já existe na base de dados: " + Util.formatarCnpj(model.getCnpj()));
        }
        Empresa empresa = repository.save(model);
        return mapper.toDTO(empresa);
    }

    @Transactional
    public void delete(Long id) {
        DadosEmpresa empresaEncontrada = findById(id);
        Empresa empresa = mapper.toModel(empresaEncontrada);
        empresa.excluir();
        repository.save(empresa);
    }

    @Transactional
    public DadosEmpresa update(DadosEmpresaUpdate dados) {
        DadosEmpresa empresaEncontrada = findById(dados.id());
        Empresa empresa = mapper.toModel(empresaEncontrada);
        empresa.setNomeFantasia(dados.nomeFantasia());
        empresa.setRazaoSocial(dados.razaoSocial());
        Empresa empresaEdit = repository.save(empresa);
        return mapper.toDTO(empresaEdit);
    }
}
