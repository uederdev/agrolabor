package br.com.ueder.agro_labor_api.utils.validators;

import br.com.ueder.agro_labor_api.entities.proprietario.Proprietario;
import br.com.ueder.agro_labor_api.repositories.ProprietarioRepository;
import br.com.ueder.agro_labor_api.utils.Util;
import br.com.ueder.agro_labor_api.utils.exceptions.RegistroDuplicadoException;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioValidator implements IModelValidator<Proprietario>{

    private final ProprietarioRepository repository;

    public ProprietarioValidator(ProprietarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Proprietario model, Long tenantId) {
        validateControleDuplicado(model, tenantId);
        validateCpfDuplicado(model, tenantId);
    }

    private void validateControleDuplicado(Proprietario model, Long tenantId) {
        if (repository.findByControle(tenantId, model.getControle()).isPresent()) {
            throw new RegistroDuplicadoException("Proprietario já existe na base de dados: Controle " + model.getControle());
        }
    }

    private void validateCpfDuplicado(Proprietario model, Long tenantId) {
        if (repository.findByCPF(tenantId, model.getCpf()).isPresent()) {
            throw new RegistroDuplicadoException("Proprietario já existe na base de dados: CPF " + Util.formatarCpf(model.getCpf()));
        }
    }


}
