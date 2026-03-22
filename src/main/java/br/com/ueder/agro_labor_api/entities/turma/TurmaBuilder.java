package br.com.ueder.agro_labor_api.entities.turma;

import br.com.ueder.agro_labor_api.entities.empresa.Empresa;

public class TurmaBuilder {

    private Long id;
    private String descricao;
    private String controle;
    private Long empresa;

    public TurmaBuilder() {
    }

    public static TurmaBuilder builder(){
        return new TurmaBuilder();
    }

    public TurmaBuilder id(Long id){
        this.id = id;
        return this;
    }

    public TurmaBuilder empresa(Long empresa){
        this.empresa = empresa;
        return this;
    }

    public TurmaBuilder descricao(String descricao){
        this.descricao = descricao;
        return this;
    }

    public TurmaBuilder controle(String controle){
        this.controle = controle;
        return this;
    }

    public Turma build(){
        Turma turma = new Turma();
        turma.setId(id);
        turma.setDescricao(descricao);
        turma.setControle(controle);
        turma.setEmpresa(new Empresa(empresa));
        return turma;
    }
}
