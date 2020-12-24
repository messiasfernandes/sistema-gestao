package br.com.apisistemagestao.dto;

import java.math.BigDecimal;


import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;


public class ItemComprasInputDTO {
	

	private Long id_itemcompra;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal qtde;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal Subtotal;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal custototal;
	
	private CompraInputDTO compra;

	private ProdutoDTO produto;
	@Transient
	private Integer numItem;
	public Long getId_itemcompra() {
		return id_itemcompra;
	}
	public void setId_itemcompra(Long id_itemcompra) {
		this.id_itemcompra = id_itemcompra;
	}
	public BigDecimal getQtde() {
		return qtde;
	}
	public void setQtde(BigDecimal qtde) {
		this.qtde = qtde;
	}
	public BigDecimal getSubtotal() {
		return Subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		Subtotal = subtotal;
	}
	public BigDecimal getCustototal() {
		return custototal;
	}
	public void setCustototal(BigDecimal custototal) {
		this.custototal = custototal;
	}
	public CompraInputDTO getCompra() {
		return compra;
	}
	public void setCompra(CompraInputDTO compra) {
		this.compra = compra;
	}
	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
	public Integer getNumItem() {
		return numItem;
	}
	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}
	
	

}
