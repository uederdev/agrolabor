package br.com.ueder.agro_labor_api.entities.turma;

import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turmas")
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "descricao", nullable = false, length = 80)
	private String descricao;

	@Column(name = "controle", nullable = false, length = 10)
	private String controle;
	
	@Column(name = "ativo", nullable = false)
	private Boolean ativo = true;

	@Column(name = "data_criacao", nullable = false, updatable = false)
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@Column(name = "data_atualizacao", nullable = false)
	private LocalDateTime dataAtualizacao = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id", nullable = false, updatable = false )
	private Empresa empresa;

	public Turma() {
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

	public String getControle() {
		return controle;
	}

	public void setControle(String controle) {
		this.controle = controle;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void excluir() {
		this.ativo = false;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
