package br.com.ueder.agro_labor_api.entities.tarefa;


import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import br.com.ueder.agro_labor_api.utils.exceptions.RNException;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 80)
    private String descricao;

    @DecimalMin(value = "0.0001")
    @Column(name = "valor_minimo", nullable = false, precision = 19, scale = 4)
    private BigDecimal valorMinimo;

    @Column(name = "valor_maximo", nullable = false, precision = 19, scale = 4)
    private BigDecimal valorMaximo;

    @DecimalMin(value = "0.0001")
    @Column(name = "quantidade_minima", nullable = false, precision = 19, scale = 4)
    private BigDecimal quantidadeMinima;

    @Column(name = "quantidade_maxima", nullable = false, precision = 19, scale = 4)
    private BigDecimal quantidadeMaxima;

    @Column(name = "controle", nullable = false, length = 10)
    private String controle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false, updatable = false)
    private Empresa empresa;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    public Tarefa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(BigDecimal valorMinimo) {
        if (valorMinimo.compareTo(this.valorMaximo) > 0){
            throw new RNException("Valor mínimo maior que o valor máximo.");
        }
        this.valorMinimo = valorMinimo;
    }

    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(BigDecimal valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public BigDecimal getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(BigDecimal quantidadeMinima) {
        if  (quantidadeMinima.compareTo(this.quantidadeMaxima) > 0){
            throw new RNException("Quantidade mínima maior que a quantidade máxima.");
        }
        this.quantidadeMinima = quantidadeMinima;
    }

    public BigDecimal getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(BigDecimal quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void excluir() {
        this.ativo = false;
    }
}
