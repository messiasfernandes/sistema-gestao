package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;


import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class ContaDetalheReceber implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcontadetalhe;
	@NotNull
	private Long numtitulo;
	@NotNull
	private Integer numparcela;
	@Column(columnDefinition  = "DECIMAL(9,3) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoparcela;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoprago;
	@Column(precision=9, scale=3) 
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorapagar;
	private LocalDate dataDePagamento;

	
	@ManyToOne
	@JoinColumn
	private FormadePagmamento formadePagmamento;
	private LocalDate datavencimento;
    @NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 25)   
	private StatuPagamento statuspagmaento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private ContasaReceber contaReceber;
	@Transient
	private Long idcocontaMovimento;
	public ContaDetalheReceber() {
		valoprago=BigDecimal.ZERO;
	}
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
	public FormadePagmamento getFormadePagmamento() {
		return formadePagmamento;
	}
	public void setFormadePagmamento(FormadePagmamento formadePagmamento) {
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
	public ContasaReceber getContaReceber() {
		return contaReceber;
	}
	public void setContaReceber(ContasaReceber contaReceber) {
		this.contaReceber = contaReceber;
	}
	public Long getIdcocontaMovimento() {
		return idcocontaMovimento;
	}
	public void setIdcocontaMovimento(Long idcocontaMovimento) {
		this.idcocontaMovimento = idcocontaMovimento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcontadetalhe == null) ? 0 : idcontadetalhe.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaDetalheReceber other = (ContaDetalheReceber) obj;
		if (idcontadetalhe == null) {
			if (other.idcontadetalhe != null)
				return false;
		} else if (!idcontadetalhe.equals(other.idcontadetalhe))
			return false;
		return true;
	}
	
	
}
