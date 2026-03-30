package br.com.ueder.agro_labor_api.dtos.colaborador;

import br.com.ueder.agro_labor_api.dtos.turma.DadosTurma;

public record DadosColaborador (
        Long id,
        Long sequencial,
        String matricula,
        String nome,
        DadosTurma turma
){
}