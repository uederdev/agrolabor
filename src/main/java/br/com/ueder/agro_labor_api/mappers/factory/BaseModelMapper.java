package br.com.ueder.agro_labor_api.mappers.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class BaseModelMapper {

    public static <M, D> M toModel(D dto, Class<M> modelClass) {
        try{
            M model = modelClass.getDeclaredConstructor().newInstance();
            for(Field field : dto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(dto);
                try{
                    Field fieldClass = model.getClass().getDeclaredField(field.getName());
                    fieldClass.setAccessible(true);
                    fieldClass.set(model, value);
                } catch(NoSuchFieldException e){

                }
            }
            return model;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter para Model: " + e.getMessage());
        }
    }

    public static <M, D> D toDto(M model, Class<D> dtoClass) {
        try {
            var components = dtoClass.getRecordComponents();
            Constructor<?> canonical = dtoClass.getDeclaredConstructors()[0];

            Object[] args = new Object[components.length];

            for (int i = 0; i < components.length; i++) {
                String fieldName = components[i].getName();
                Field modelField = model.getClass().getDeclaredField(fieldName);
                modelField.setAccessible(true);
                args[i] = modelField.get(model);
            }

            @SuppressWarnings("unchecked")
            D dto = (D) canonical.newInstance(args);
            return dto;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Model para DTO (record)", e);
        }
    }

}
