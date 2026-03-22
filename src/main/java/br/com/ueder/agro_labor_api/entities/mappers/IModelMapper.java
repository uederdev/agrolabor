package br.com.ueder.agro_labor_api.entities.mappers;

public interface IModelMapper<M, D> {

   M toModel(D dto);
   D toDTO(M model);

}
