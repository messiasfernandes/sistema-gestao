package br.com.apisistemagestao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.StatuPagamento;



public class ContaDetalheReceberDTO {

	private Long idcontadetalhe;

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
    private ContaReceberDTO contaReceber = new ContaReceberDTO();
	private FormaPagamentoDTO formadePagmamento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datavencimento;
	@NumberFormat(pattern = "#,##0.00")
	
	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private StatuPagamento statuspagmaento;
	

	private Long idcocontaMovimento;

	public Long getIdcontadetalhe() {
		return idcontadetalhe;
	}

	public void setIdcontadetalhe(Long idcontadetalhe) {
		this.idcontadetalhe = idcontadetalhe;
	}

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

	public FormaPagamentoDTO getFormadePagmamento() {
		return formadePagmamento;
	}

	public void setFormadePagmamento(FormaPagamentoDTO formadePagmamento) {
		this.formadePagmamento = formadePagmamento;
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

	public Long getIdcocontaMovimento() {
		return idcocontaMovimento;
	}

	public void setIdcocontaMovimento(Long idcocontaMovimento) {
		this.idcocontaMovimento = idcocontaMovimento;
	}

	public ContaReceberDTO getContaReceber() {
		return contaReceber;
	}

	public void setContaReceber(ContaReceberDTO contaReceber) {
		this.contaReceber = contaReceber;
	}

}
