package br.com.apisistemagestao.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.TipoProduto;


public class ProdutosItemDTO {
	
	private Long idproduto;
	private String nomeproduto;
	private BigDecimal precocusto;
	private BigDecimal qteestoque;
	private TipoProduto tipoproduto;
	@JsonFormat(pattern = "#,##0.00")
	private BigDecimal precodevenda;


	
	public BigDecimal getPrecodevenda() {
		return precodevenda;
	}
	public void setPrecodevenda(BigDecimal precodevenda) {
		this.precodevenda = precodevenda;
	}
	public Long getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(Long idproduto) {
		this.idproduto = idproduto;
	}
	public String getNomeproduto() {
		return nomeproduto;
	}
	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}
	public BigDecimal getQteestoque() {
		return qteestoque;
	}
	public void setQteestoque(BigDecimal qteestoque) {
		this.qteestoque = qteestoque;
	}

	public TipoProduto getTipoproduto() {
		return tipoproduto;
	}
	public void setTipoproduto(TipoProduto tipoproduto) {
		this.tipoproduto = tipoproduto;
	}
	public BigDecimal getPrecocusto() {
		return precocusto;
	}
	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto;
	}


    
}
