package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class CondicaoPagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcondicao;
	@NotEmpty
	@NotNull(message = "o Campo descrição é obrigatório!!")
	@Column(length = 50)
	private String descricao;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "condicaopagamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Parcelas> colecaoparcelas = new ArrayList<>();

	private Integer qtdeparcelas;

	public Long getIdcondicao() {
		return idcondicao;
	}

	public void setIdcondicao(Long idcondicao) {
		this.idcondicao = idcondicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}

	public Integer getQtdeparcelas() {
		return qtdeparcelas;
	}

	public void setColecaoparcelas(List<Parcelas> colecaoparcelas) {
		this.colecaoparcelas = colecaoparcelas;
	}

	public void setQtdeparcelas(Integer qtdeparcelas) {
		this.qtdeparcelas = qtdeparcelas;
	}

	public List<Parcelas> getColecaoparcelas() {
		return colecaoparcelas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcondicao == null) ? 0 : idcondicao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CondicaoPagamento other = (CondicaoPagamento) obj;
		if (idcondicao == null) {
			if (other.idcondicao != null)
				return false;
		} else if (!idcondicao.equals(other.idcondicao))
			return false;
		return true;
	}

}
