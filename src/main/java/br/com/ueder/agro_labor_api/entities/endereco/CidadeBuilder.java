package br.com.ueder.agro_labor_api.entities.endereco;

import br.com.ueder.agro_labor_api.entities.enums.Estado;

public class CidadeBuilder {

    private String id;
    private String nome;
    private Estado estado;

    public CidadeBuilder() {
    }

    public static CidadeBuilder builder() {
        return new CidadeBuilder();
    }

    public CidadeBuilder id(String id) {
        this.id = id;
        return this;
    }
    public CidadeBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public CidadeBuilder estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public Cidade build() {
        Cidade cidade = new Cidade();
        cidade.setId(id);
        cidade.setNome(nome);
        cidade.setEstado(estado);
        return cidade;
    }
}
