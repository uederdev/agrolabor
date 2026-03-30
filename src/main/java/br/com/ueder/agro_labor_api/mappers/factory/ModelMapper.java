package br.com.ueder.agro_labor_api.mappers.factory;

import br.com.ueder.agro_labor_api.mappers.IModelMapper;
import org.springframework.stereotype.Component;

@Component
public abstract class ModelMapper<M, D> implements IModelMapper<M, D> {

    public final Class<D> dtoClass;
    public final Class<M> entityClass;

    protected ModelMapper(Class<D> dtoClass, Class<M> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    @Override
    public M toModel(D dto) {
        return BaseModelMapper.toModel(dto, entityClass);
    }

    @Override
    public D toDTO(M model) {
        return BaseModelMapper.toDto(model, dtoClass);
    }
}
