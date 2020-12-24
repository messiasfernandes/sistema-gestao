package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import javax.persistence.Transient;

import javax.validation.constraints.NotNull;


@Entity
public class Parcelas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idparcela;
	private Integer numeroparcela;
    private Integer dia;
  
    @Column(nullable = false, columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	private BigDecimal percentual;
	@NotNull(message = "O Campo forma de pagamento e obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private FormadePagmamento formadePagamento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private CondicaoPagamento condicaopagamento;
	@Transient
    private BigDecimal totalpercentual;
	public Parcelas() {
	
		formadePagamento= new FormadePagmamento();

	}

	public Integer getNumeroparcela() {
		return numeroparcela;
	}

	public void setNumeroparcela(Integer numeroparcela) {
		this.numeroparcela = numeroparcela;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public FormadePagmamento getFormadePagamento() {
		return formadePagamento;
	}

	public void setFormadePagamento(FormadePagmamento formadePagamento) {
		this.formadePagamento = formadePagamento;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public BigDecimal getTotalpercentual() {
		return totalpercentual;
	}

	public void setTotalpercentual(BigDecimal totalpercentual) {
		this.totalpercentual = totalpercentual;
	}

	public CondicaoPagamento getCondicaopagamento() {
		return condicaopagamento;
	}

	public void setCondicaopagamento(CondicaoPagamento condicaopagamento) {
		this.condicaopagamento = condicaopagamento;
	}

	public Long getIdparcela() {
		return idparcela;
	}

	public void setIdparcela(Long idparcela) {
		this.idparcela = idparcela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idparcela == null) ? 0 : idparcela.hashCode());
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
		Parcelas other = (Parcelas) obj;
		if (idparcela == null) {
			if (other.idparcela != null)
				return false;
		} else if (!idparcela.equals(other.idparcela))
			return false;
		return true;
	}
  
}
