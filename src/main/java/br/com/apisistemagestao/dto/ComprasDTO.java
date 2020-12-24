package br.com.apisistemagestao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.StatusCompra;



public class ComprasDTO {
	private Long idcompra;
	private Long numeronota;
	private StatusCompra status;
	private BigDecimal totalCompra;
	private BigDecimal totalProduto;
	private ForncedorContaDTO fornecedor;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")

	private LocalDateTime datadeentrada;
	@JsonFormat(pattern = "dd/MM/yyyy ")
	private LocalDate dataCompra;
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorseguro;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorfrete;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal outrasdepesza;
	@Transient
	private Long idfonecedor;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal desconto;
	public Long getIdcompra() {
		return idcompra;
	}
	public void setIdcompra(Long idcompra) {
		this.idcompra = idcompra;
	}
	public Long getNumeronota() {
		return numeronota;
	}
	public void setNumeronota(Long numeronota) {
		this.numeronota = numeronota;
	}
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
	public LocalDateTime getDatadeentrada() {
		return datadeentrada;
	}
	public void setDatadeentrada(LocalDateTime datadeentrada) {
		this.datadeentrada = datadeentrada;
	}
	public LocalDate getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	public BigDecimal getValorseguro() {
		return valorseguro;
	}
	public void setValorseguro(BigDecimal valorseguro) {
		this.valorseguro = valorseguro;
	}
	public BigDecimal getValorfrete() {
		return valorfrete;
	}
	public void setValorfrete(BigDecimal valorfrete) {
		this.valorfrete = valorfrete;
	}
	public BigDecimal getOutrasdepesza() {
		return outrasdepesza;
	}
	public void setOutrasdepesza(BigDecimal outrasdepesza) {
		this.outrasdepesza = outrasdepesza;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public Long getIdfonecedor() {
		return idfonecedor;
	}
	public void setIdfonecedor(Long idfonecedor) {
		this.idfonecedor = idfonecedor;
	}

}
