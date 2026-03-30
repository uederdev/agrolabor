package br.com.ueder.agro_labor_api.entities.proprietario;

import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.entities.endereco.Endereco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioBuilder {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private Empresa empresa;
    private String controle;

    public ProprietarioBuilder() {
    }

    public static ProprietarioBuilder builder(){
        return new ProprietarioBuilder();
    }

    public ProprietarioBuilder id(Long id){
        this.id = id;
        return this;
    }

    public ProprietarioBuilder nome(String nome){
        this.nome = nome;
        return this;
    }

    public ProprietarioBuilder cpf(String cpf){
        this.cpf = cpf;
        return this;
    }

    public ProprietarioBuilder email(String email){
        this.email = email;
        return this;
    }
    public ProprietarioBuilder telefone(String telefone){
        this.telefone = telefone;
        return this;
    }

    public ProprietarioBuilder dataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
        return this;
    }

    public ProprietarioBuilder empresa(Empresa empresa){
        this.empresa = empresa;
        return this;
    }

    public ProprietarioBuilder controle(String controle){
        this.controle = controle;
        return this;
    }

    public Proprietario build(){
        Proprietario proprietario = new Proprietario();
        proprietario.setId(id);
        proprietario.setNome(nome);
        proprietario.setCpf(cpf);
        proprietario.setEmail(email);
        proprietario.setTelefone(telefone);
        proprietario.setDataNascimento(dataNascimento);
        proprietario.setEmpresa(empresa);
        proprietario.setControle(controle);
        return proprietario;
    }
}
