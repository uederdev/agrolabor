package br.com.ueder.agro_labor_api.entities.endereco;

import br.com.ueder.agro_labor_api.entities.enums.TipoEndereco;
import br.com.ueder.agro_labor_api.entities.proprietario.Proprietario;

public class EnderecoBuilder {

    private String id;
    private TipoEndereco tipoEndereco;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private Cidade cidade;
    private String cep;
    private Proprietario proprietario;

    public EnderecoBuilder() {
    }

    public static EnderecoBuilder builder() {
        return new EnderecoBuilder();
    }

    public EnderecoBuilder id(String id) {
        this.id = id;
        return this;
    }

    public EnderecoBuilder tipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
        return this;
    }

    public EnderecoBuilder logradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public EnderecoBuilder numero(String numero) {
        this.numero = numero;
        return this;
    }

    public EnderecoBuilder bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }
    public EnderecoBuilder complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public EnderecoBuilder cidade(Cidade cidade) {
        this.cidade = cidade;
        return this;
    }
    public EnderecoBuilder cep(String cep) {
        this.cep = cep;
        return this;
    }

    public EnderecoBuilder proprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
        return this;
    }

    public Endereco build() {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setTipoEndereco(tipoEndereco);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setComplemento(complemento);
        endereco.setCidade(cidade);
        endereco.setCep(cep);
        return endereco;
    }
}
