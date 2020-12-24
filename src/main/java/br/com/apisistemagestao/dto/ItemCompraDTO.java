package br.com.apisistemagestao.dto;

import java.math.BigDecimal;

public class ItemCompraDTO {
	
	private Long id_itemcompra;
	private BigDecimal qtde;
	private BigDecimal subtotal;
	private CompraContasDto compra;
	private ProdutosItemDTO produto;
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
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public CompraContasDto getCompra() {
		return compra;
	}
	public void setCompra(CompraContasDto compra) {
		this.compra = compra;
	}
	public ProdutosItemDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutosItemDTO produto) {
		this.produto = produto;
	}

}
