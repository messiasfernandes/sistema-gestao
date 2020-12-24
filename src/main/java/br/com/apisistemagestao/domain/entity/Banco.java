package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Banco implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	private Long codigobanco;
	private String nomebanco;

	public Long getCodigobanco() {
		return codigobanco;
	}

	public void setCodigobanco(Long codigobanco) {
		this.codigobanco = codigobanco;
	}

	public String getNomebanco() {
		return nomebanco;
	}

	public void setNomebanco(String nomebanco) {
		this.nomebanco = nomebanco.toUpperCase();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigobanco == null) ? 0 : codigobanco.hashCode());
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
		Banco other = (Banco) obj;
		if (codigobanco == null) {
			if (other.codigobanco != null)
				return false;
		} else if (!codigobanco.equals(other.codigobanco))
			return false;
		return true;
	}

}
