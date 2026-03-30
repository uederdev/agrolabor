package br.com.ueder.agro_labor_api.mappers;

import br.com.ueder.agro_labor_api.dtos.empresa.DadosEmpresa;
import br.com.ueder.agro_labor_api.dtos.empresa.DadosEmpresaCreate;
import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.entities.empresa.EmpresaBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper implements IModelMapper<Empresa, DadosEmpresa> {

    @Override
    public Empresa toModel(DadosEmpresa dto) {
        return EmpresaBuilder.builder()
                    .id(dto.id())
                    .razaoSocial(dto.razaoSocial())
                    .nomeFantasia(dto.nomeFantasia())
                    .cnpj(dto.cnpj())
                    .build();
    }

    public Empresa toModel(DadosEmpresaCreate dto) {
        return EmpresaBuilder.builder()
                .razaoSocial(dto.razaoSocial())
                .nomeFantasia(dto.nomeFantasia())
                .cnpj(dto.cnpj())
                .build();
    }

    @Override
    public DadosEmpresa toDTO(Empresa model) {
        return new DadosEmpresa(model.getId(), model.getRazaoSocial(),model.getNomeFantasia(),
                model.getCnpj());
    }
}
