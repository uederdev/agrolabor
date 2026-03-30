package br.com.ueder.agro_labor_api.dtos.proprietario;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DadosProprietarioCreate(
        @NotBlank
        @Size(min = 3, max = 80)
        String nome,
        @CPF
        @NotBlank
        String cpf,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotNull
        @Past
        LocalDate dataNascimento,
        @NotBlank
        String controle
) {
}
