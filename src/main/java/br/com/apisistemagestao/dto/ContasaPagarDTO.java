package br.com.apisistemagestao.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.StatuPagamento;

public class ContasaPagarDTO {
 
	private long idconta;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datalancamento;
	private ForncedorContaDTO fornecedor;
	private Long numtitulo;

	private Integer numparcela;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoparcela;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoprago;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorapagar;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDePagamento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datavencimento;
	private StatuPagamento statuspagmaento;

	private FormaPagamentoDTO formadePagmamento;
      private Long idcocontaMovimento;
      
      
	public Long getNumtitulo() {
		return numtitulo;
	}
	public void setNumtitulo(Long numtitulo) {
		this.numtitulo = numtitulo;
	}
	public Integer getNumparcela() {
		return numparcela;
	}
	public void setNumparcela(Integer numparcela) {
		this.numparcela = numparcela;
	}
	public BigDecimal getValoparcela() {
		return valoparcela;
	}
	public void setValoparcela(BigDecimal valoparcela) {
		this.valoparcela = valoparcela;
	}
	public BigDecimal getValoprago() {
		return valoprago;
	}
	public void setValoprago(BigDecimal valoprago) {
		this.valoprago = valoprago;
	}
	public BigDecimal getValorapagar() {
		return valorapagar;
	}
	public void setValorapagar(BigDecimal valorapagar) {
		this.valorapagar = valorapagar;
	}
	public LocalDate getDataDePagamento() {
		return dataDePagamento;
	}
	public void setDataDePagamento(LocalDate dataDePagamento) {
		this.dataDePagamento = dataDePagamento;
	}
	public LocalDate getDatavencimento() {
		return datavencimento;
	}
	public void setDatavencimento(LocalDate datavencimento) {
		this.datavencimento = datavencimento;
	}
	public StatuPagamento getStatuspagmaento() {
		return statuspagmaento;
	}
	public void setStatuspagmaento(StatuPagamento statuspagmaento) {
		this.statuspagmaento = statuspagmaento;
	}
	public FormaPagamentoDTO getFormadePagmamento() {
		return formadePagmamento;
	}
	public void setFormadePagmamento(FormaPagamentoDTO formadePagmamento) {
		this.formadePagmamento = formadePagmamento;
	}
	public Long getIdcocontaMovimento() {
		return idcocontaMovimento;
	}
	public void setIdcocontaMovimento(Long idcocontaMovimento) {
		this.idcocontaMovimento = idcocontaMovimento;
	}
	public long getIdconta() {
		return idconta;
	}
	public void setIdconta(long idconta) {
		this.idconta = idconta;
	}

	public LocalDate getDatalancamento() {
		return datalancamento;
	}
	public void setDatalancamento(LocalDate datalancamento) {
		this.datalancamento = datalancamento;
	}
	public ForncedorContaDTO getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(ForncedorContaDTO fornecedor) {
		this.fornecedor = fornecedor;
	}
	

	
	
}
