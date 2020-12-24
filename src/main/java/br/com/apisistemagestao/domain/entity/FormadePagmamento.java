package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class FormadePagmamento implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idformapagamento;
	@NotEmpty(message = "Campo Obrigatório")
    @Column(length = 60, nullable = false, unique = true)
    private String nomeforma;

	public String getNomeforma() {
		return nomeforma;
	}

	public void setNomeforma(String nomeforma) {
		this.nomeforma = nomeforma.toUpperCase();
	}

	public Long getIdformapagamento() {
		return idformapagamento;
	}

	public void setIdformapagamento(Long idformapagamento) {
		this.idformapagamento = idformapagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idformapagamento == null) ? 0 : idformapagamento.hashCode());
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
		FormadePagmamento other = (FormadePagmamento) obj;
		if (idformapagamento == null) {
			if (other.idformapagamento != null)
				return false;
		} else if (!idformapagamento.equals(other.idformapagamento))
			return false;
		return true;
	}
	
	
}
