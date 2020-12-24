package br.com.apisistemagestao.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class ItemvendasDTO {

	private Long Iditem;

	private BigDecimal qtdeItem;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal subTotal;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal desconto;
   private ProdutosItemDTO produto;
	private VendaDto venda;

	public Long getIditem() {
		return Iditem;
	}

	public void setIditem(Long iditem) {
		Iditem = iditem;
	}

	public BigDecimal getQtdeItem() {
		return qtdeItem;
	}

	public void setQtdeItem(BigDecimal qtdeItem) {
		this.qtdeItem = qtdeItem;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public VendaDto getVenda() {
		return venda;
	}

	public void setVenda(VendaDto venda) {
		this.venda = venda;
	}

	public ProdutosItemDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutosItemDTO produto) {
		this.produto = produto;
	}

}
