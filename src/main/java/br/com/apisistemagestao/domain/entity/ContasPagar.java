package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ContasPagar implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idconta;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datalancamento;

	@NotNull
	private Long numtitulo;
	@NotNull
	private Integer numparcela;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoparcela;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoprago;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorapagar;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDePagamento;

	@ManyToOne
	@JoinColumn
	private FormadePagmamento formadePagmamento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datavencimento;
	                 
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private StatuPagamento statuspagmaento;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	@Transient
	private Long idcocontaMovimento;

	public ContasPagar() {
		valoparcela = BigDecimal.ZERO;
		valoprago = BigDecimal.ZERO;
		valorapagar = BigDecimal.ZERO;
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

	public Long getIdcocontaMovimento() {
		return idcocontaMovimento;
	}

	public void setIdcocontaMovimento(Long idcocontaMovimento) {
		this.idcocontaMovimento = idcocontaMovimento;
	}

	// @Fetch(FetchMode.SUBSELECT)
//	@ElementCollection
//	@CollectionTable(name = "ContasPagarDetalhe", joinColumns = @JoinColumn(name = "idcontadetalhe"))
//	@AttributeOverrides({ @AttributeOverride(name = "numparcela", column = @Column(name = "numparcela")) })
//	private List<ContasPagarDetalhe> contasdetalhe = new ArrayList<ContasPagarDetalhe>();
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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idconta ^ (idconta >>> 32));
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
		ContasPagar other = (ContasPagar) obj;
		if (idconta != other.idconta)
			return false;
		return true;
	}

}
