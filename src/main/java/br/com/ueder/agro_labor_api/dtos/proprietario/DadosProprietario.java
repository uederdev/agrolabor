package br.com.ueder.agro_labor_api.dtos.proprietario;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosProprietario(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento
) {

}
