package br.com.ueder.agro_labor_api.utils.validators;

public interface IModelValidator<T>{

    void validate(T model, Long tenantId);

}
