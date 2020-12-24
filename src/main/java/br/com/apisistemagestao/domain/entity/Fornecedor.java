package br.com.apisistemagestao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Fornecedor extends Pessoa {
	private static final long serialVersionUID = 1L;
	@Column(length = 40)
	private String representante;
    
	@ManyToOne()
	@JoinColumn(nullable = false)
	private CondicaoPagamento condicaoPamento = new  CondicaoPagamento();
	@Column(length = 150)
	private String site;

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante.toUpperCase();
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site.toLowerCase();
	}

	public CondicaoPagamento getCondicaoPamento() {
		return condicaoPamento;
	}

	public void setCondicaoPamento(CondicaoPagamento condicaoPamento) {
		this.condicaoPamento = condicaoPamento;
	}

}
