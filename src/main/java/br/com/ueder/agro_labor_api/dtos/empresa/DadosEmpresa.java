package br.com.ueder.agro_labor_api.dtos.empresa;

public record DadosEmpresa(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj
) {
}
