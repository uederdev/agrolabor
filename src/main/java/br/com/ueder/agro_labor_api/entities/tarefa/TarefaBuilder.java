package br.com.ueder.agro_labor_api.entities.tarefa;

import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TarefaBuilder {

    private Long id;
    private String descricao;
    private BigDecimal valorMinimo;
    private BigDecimal valorMaximo;
    private BigDecimal quantidadeMinima;
    private BigDecimal quantidadeMaxima;
    private String controle;
    private Empresa empresa;

    TarefaBuilder () {}

    public static TarefaBuilder builder() {
        return new TarefaBuilder();
    }

    public TarefaBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TarefaBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public TarefaBuilder valorMinimo(BigDecimal valorMinimo) {
        this.valorMinimo = valorMinimo;
        return this;
    }
    public TarefaBuilder valorMaximo(BigDecimal valorMaximo) {
        this.valorMaximo = valorMaximo;
        return this;
    }

    public TarefaBuilder quantidadeMinima(BigDecimal quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
        return this;
    }

    public TarefaBuilder quantidadeMaxima(BigDecimal quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
        return this;
    }

    public TarefaBuilder controle(String controle) {
        this.controle = controle;
        return this;
    }

    public TarefaBuilder empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public Tarefa build() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setDescricao(descricao);
        tarefa.setValorMaximo(valorMaximo);
        tarefa.setValorMinimo(valorMinimo);
        tarefa.setQuantidadeMaxima(quantidadeMaxima);
        tarefa.setQuantidadeMinima(quantidadeMinima);
        tarefa.setControle(controle);
        tarefa.setEmpresa(empresa);
        return tarefa;
    }
}
