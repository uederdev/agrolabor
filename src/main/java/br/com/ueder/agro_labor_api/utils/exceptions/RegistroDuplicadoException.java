package br.com.ueder.agro_labor_api.utils.exceptions;

public class RegistroDuplicadoException extends RuntimeException{

    public RegistroDuplicadoException(String mensage){
        super("Registro duplicado. " + mensage);
    }
}
