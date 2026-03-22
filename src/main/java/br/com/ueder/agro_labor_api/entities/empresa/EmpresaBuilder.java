package br.com.ueder.agro_labor_api.entities.empresa;

public class EmpresaBuilder {

    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;

    EmpresaBuilder() {
    }

    public static EmpresaBuilder builder() {
        return new EmpresaBuilder();
    }

    public EmpresaBuilder id(final Long id) {
        this.id = id;
        return this;
    }

    public EmpresaBuilder razaoSocial(final String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public EmpresaBuilder nomeFantasia(final String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        return this;
    }

    public EmpresaBuilder cnpj(final String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public Empresa build() {
        var empresa = new Empresa();
        empresa.setId(this.id);
        empresa.setRazaoSocial(this.razaoSocial);
        empresa.setNomeFantasia(this.nomeFantasia);
        empresa.setCnpj(this.cnpj);
        return empresa;
    }
}