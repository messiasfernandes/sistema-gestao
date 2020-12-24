package br.com.apisistemagestao.dto;

import java.math.BigDecimal;

public class ParcelasDTO {
	private Long idparcela;
	private Integer numeroparcela;
	private Integer dia;

	private BigDecimal percentual;

	private FormaPagamentoDTO formadePagamento;

	private CondicaoPagtoDTO condicaopagamento;

	public Long getIdparcela() {
		return idparcela;
	}

	public void setIdparcela(Long idparcela) {
		this.idparcela = idparcela;
	}

	public Integer getNumeroparcela() {
		return numeroparcela;
	}

	public void setNumeroparcela(Integer numeroparcela) {
		this.numeroparcela = numeroparcela;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public FormaPagamentoDTO getFormadePagamento() {
		return formadePagamento;
	}

	public void setFormadePagamento(FormaPagamentoDTO formadePagamento) {
		this.formadePagamento = formadePagamento;
	}

	public CondicaoPagtoDTO getCondicaopagamento() {
		return condicaopagamento;
	}

	public void setCondicaopagamento(CondicaoPagtoDTO condicaopagamento) {
		this.condicaopagamento = condicaopagamento;
	}

}
