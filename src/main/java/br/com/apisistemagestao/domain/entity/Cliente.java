package br.com.apisistemagestao.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cliente extends Pessoa {

	
	private static final long serialVersionUID = 1L;
	@Column(precision=8, scale=3) 
	private BigDecimal limecredito;
	public Cliente() {
		limecredito= BigDecimal.ZERO;
		condicaoPamento= new CondicaoPagamento();
	}
	public BigDecimal getLimecredito() {
		return limecredito;
	}
	public void setLimecredito(BigDecimal limecredito) {
		this.limecredito = limecredito;
	}
	@ManyToOne()
	@JoinColumn(nullable = false)
	private CondicaoPagamento condicaoPamento;
	public CondicaoPagamento getCondicaoPamento() {
		return condicaoPamento;
	}
	public void setCondicaoPamento(CondicaoPagamento condicaoPamento) {
		this.condicaoPamento = condicaoPamento;
	}
	
   
}
