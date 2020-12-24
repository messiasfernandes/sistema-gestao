package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Ncms implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ncm;
	@NotEmpty
	@Column(length = 8, unique = true)
	private String cod_ncm;
	@Column(length = 512)
	private String descritvo;



	public Long getId_ncm() {
		return id_ncm;
	}

	public void setId_ncm(Long id_ncm) {
		this.id_ncm = id_ncm;
	}

	public String getCod_ncm() {
		return cod_ncm;
	}

	public void setCod_ncm(String cod_ncm) {
		this.cod_ncm = cod_ncm;
	}

	public String getDescritvo() {
		return descritvo;
	}

	public void setDescritvo(String descritvo) {
		this.descritvo = descritvo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_ncm == null) ? 0 : id_ncm.hashCode());
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
		Ncms other = (Ncms) obj;
		if (id_ncm == null) {
			if (other.id_ncm != null)
				return false;
		} else if (!id_ncm.equals(other.id_ncm))
			return false;
		return true;
	}

}
