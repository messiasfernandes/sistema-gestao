package br.com.apisistemagestao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



public class CompraInputDTO {

	private Long idCompra;

	private Long numeronota;
	@Transient
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal markqup;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCompra;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorseguro;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorfrete;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal outrasdepesza;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal desconto;
	@JsonFormat(pattern = "dd/MM/yyyy HH:MM")
	private LocalDateTime datadeentrada;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datacancelamento;

	private String motivoCancelamento;

	private ForncedorContaDTO fornecedor;
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal totalCompra;

	@NumberFormat(pattern = "#,##0.00")

	private BigDecimal totalproduto;
	private List<ItemComprasInputDTO> itemcompras = new ArrayList<>();
	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Long getNumeronota() {
		return numeronota;
	}

	public void setNumeronota(Long numeronota) {
		this.numeronota = numeronota;
	}

	public BigDecimal getMarkqup() {
		return markqup;
	}

	public void setMarkqup(BigDecimal markqup) {
		this.markqup = markqup;
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

	public LocalDateTime getDatadeentrada() {
		return datadeentrada;
	}

	public void setDatadeentrada(LocalDateTime datadeentrada) {
		this.datadeentrada = datadeentrada;
	}

	public LocalDate getDatacancelamento() {
		return datacancelamento;
	}

	public void setDatacancelamento(LocalDate datacancelamento) {
		this.datacancelamento = datacancelamento;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public ForncedorContaDTO getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(ForncedorContaDTO fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}

	public BigDecimal getTotalproduto() {
		return totalproduto;
	}

	public void setTotalproduto(BigDecimal totalproduto) {
		this.totalproduto = totalproduto;
	}

	public List<ItemComprasInputDTO> getItemcompras() {
		return itemcompras;
	}

	public void setItemcompras(List<ItemComprasInputDTO> itemcompras) {
		this.itemcompras = itemcompras;
	}
	
	

}
