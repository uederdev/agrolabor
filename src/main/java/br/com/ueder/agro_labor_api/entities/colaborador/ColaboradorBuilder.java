package br.com.ueder.agro_labor_api.entities.colaborador;

import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.entities.turma.Turma;

public class ColaboradorBuilder {

    private Long id;
    private Long sequencial;
    private String matricula;
    private String nome;
    private Turma turma;
    private Empresa empresa;

    public ColaboradorBuilder() {
    }

    public static  ColaboradorBuilder builder() {
        return new ColaboradorBuilder();
    }
    public ColaboradorBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ColaboradorBuilder sequencial(Long sequencial) {
        this.sequencial = sequencial;
        return this;
    }

    public ColaboradorBuilder matricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

    public ColaboradorBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ColaboradorBuilder turma(Turma turma) {
        this.turma = turma;
        return this;
    }

    public ColaboradorBuilder empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public Colaborador build() {
        var colaborador = new Colaborador();
        colaborador.setId(id);
        colaborador.setSequencial(sequencial);
        colaborador.setMatricula(matricula);
        colaborador.setNome(nome);
        colaborador.setTurma(turma);
        colaborador.setEmpresa(empresa);
        return colaborador;
    }
}
