package br.com.apisistemagestao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.StatusCompra;



public class CompraDTO {
     
	private Long idcompra;
	private Long numeronota;
	private StatusCompra status;
	private BigDecimal totalCompra;
	private BigDecimal totalProduto;
	private ForncedorContaDTO fornecedor;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datadeentrada;


	public StatusCompra getStatus() {
		return status;
	}
	public void setStatus(StatusCompra status) {
		this.status = status;
	}
	public BigDecimal getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}
	public BigDecimal getTotalProduto() {
		return totalProduto;
	}
	public void setTotalProduto(BigDecimal totalProduto) {
		this.totalProduto = totalProduto;
	}
	public ForncedorContaDTO getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(ForncedorContaDTO fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Long getIdcompra() {
		return idcompra;
	}
	public void setIdcompra(Long idcompra) {
		this.idcompra = idcompra;
	}
//	public List< ItemCompraDTO> getItemcompras() {
//		return itemcompras;
//	}
//	public void setItemcompras(List< ItemCompraDTO> itemcompras) {
//		this.itemcompras = itemcompras;
//	}
	public Long getNumeronota() {
		return numeronota;
	}
	public void setNumeronota(Long numeronota) {
		this.numeronota = numeronota;
	}
	public LocalDateTime getDatadeentrada() {
		return datadeentrada;
	}
	public void setDatadeentrada(LocalDateTime datadeentrada) {
		this.datadeentrada = datadeentrada;
	}
	
	
}
