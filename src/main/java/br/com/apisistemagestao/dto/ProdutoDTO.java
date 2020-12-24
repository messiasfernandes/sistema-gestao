package br.com.apisistemagestao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.Categoria;
import br.com.apisistemagestao.domain.entity.TipoProduto;

public class ProdutoDTO {
	
	private  Long idproduto;
	
	private String nomeproduto;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal precocusto;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal qteestoque;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")

	private BigDecimal precodevenda;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal customedio;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataultimacmpra;
	private Boolean ativo;

	
	private String  unidede;

	private String codigoEan13;

	@Enumerated(EnumType.STRING)
	@Column(length  = 15)
	private TipoProduto tipoproduto;
	@Column(length = 25)
	private String codigofabricante;
    @Column(length = 50)	
	private String marca;
	
	private Categoria categoria;

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

	public BigDecimal getPrecocusto() {
		return precocusto;
	}

	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto;
	}

	public BigDecimal getQteestoque() {
		return qteestoque;
	}

	public void setQteestoque(BigDecimal qteestoque) {
		this.qteestoque = qteestoque;
	}

	public BigDecimal getPrecodevenda() {
		return precodevenda;
	}

	public void setPrecodevenda(BigDecimal precodevenda) {
		this.precodevenda = precodevenda;
	}

	public BigDecimal getCustomedio() {
		return customedio;
	}

	public void setCustomedio(BigDecimal customedio) {
		this.customedio = customedio;
	}

	public LocalDate getDataultimacmpra() {
		return dataultimacmpra;
	}

	public void setDataultimacmpra(LocalDate dataultimacmpra) {
		this.dataultimacmpra = dataultimacmpra;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getUnidede() {
		return unidede;
	}

	public void setUnidede(String unidede) {
		this.unidede = unidede;
	}

	public String getCodigoEan13() {
		return codigoEan13;
	}

	public void setCodigoEan13(String codigoEan13) {
		this.codigoEan13 = codigoEan13;
	}

	public TipoProduto getTipoproduto() {
		return tipoproduto;
	}

	public void setTipoproduto(TipoProduto tipoproduto) {
		this.tipoproduto = tipoproduto;
	}

	public String getCodigofabricante() {
		return codigofabricante;
	}

	public void setCodigofabricante(String codigofabricante) {
		this.codigofabricante = codigofabricante;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
