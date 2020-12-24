package br.com.apisistemagestao.dto;

import java.math.BigDecimal;

public class CompraItensDto {

	

	private Long id_itemcompra;

	private BigDecimal qtde;

	private BigDecimal Subtotal;
	private BigDecimal custototal;
    private ComprasDTO Compra;
	private ProdutosItemDTO produto =new ProdutosItemDTO();



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
	public ProdutosItemDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutosItemDTO produto) {
		this.produto = produto;
	}
	public BigDecimal getSubtotal() {
		return Subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		Subtotal = subtotal;
	}
	
	public ComprasDTO getCompra() {
		return Compra;
	}

	public void setCompra(ComprasDTO compra) {
		Compra = compra;
	}

	public BigDecimal getCustototal() {
		return custototal;
	}

	public void setCustototal(BigDecimal custototal) {
		this.custototal = custototal;
	}



}
