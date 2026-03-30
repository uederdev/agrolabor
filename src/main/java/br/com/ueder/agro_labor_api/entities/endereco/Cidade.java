package br.com.ueder.agro_labor_api.entities.endereco;

import br.com.ueder.agro_labor_api.entities.enums.Estado;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cidades")
public class Cidade {

    @Id
    @Column(name = "id", nullable = false, length = 7, unique = true)
    private String id;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "estado", nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column(name = "data_desativacao")
    private LocalDate data_desativacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public Cidade() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getData_desativacao() {
        return data_desativacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
