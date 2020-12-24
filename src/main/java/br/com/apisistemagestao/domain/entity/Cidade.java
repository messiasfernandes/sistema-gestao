package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Cidade implements Serializable {

	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_cidade;
    @Column(length = 7, nullable = false)
  	private String codigoibge;
    @Column(length = 60, nullable = false)
	private String cidadenome;
  
    @javax.validation.constraints.NotNull
    @ManyToOne()
    @JoinColumn(name = "codigoestado")
	private Estado estado;

	
	public Long getCodigo_cidade() {
		return codigo_cidade;
	}
	public void setCodigo_cidade(Long codigo_cidade) {
		this.codigo_cidade = codigo_cidade;
	}
	public String getCodigoibge() {
		return codigoibge;
	}
	public void setCodigoibge(String codigoibge) {
		this.codigoibge = codigoibge;
	}
	public String getCidadenome() {
		return cidadenome;
	}
	public void setCidadenome(String cidadenome) {
		this.cidadenome = cidadenome;
	}

	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_cidade == null) ? 0 : codigo_cidade.hashCode());
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
		Cidade other = (Cidade) obj;
		if (codigo_cidade == null) {
			if (other.codigo_cidade != null)
				return false;
		} else if (!codigo_cidade.equals(other.codigo_cidade))
			return false;
		return true;
	}
	
	
}
