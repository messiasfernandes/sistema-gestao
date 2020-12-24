package br.com.apisistemagestao.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
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

import org.springframework.format.annotation.NumberFormat;

@Entity
public class ContaMovimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idmovimento;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal credito;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal debito;
	@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal subtotal;
	@Column(length = 100)
	private String historico;
	private Long documento;
	private LocalDate datamovimentcao;
	@Enumerated(EnumType.STRING)
	@Transient
	private TipoMovimento tipoMovimento;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn()
	private ContaCaixa conta;

	public ContaMovimento() {
		credito = BigDecimal.ZERO;
		debito = BigDecimal.ZERO;
		conta =new ContaCaixa();

	}

	public Long getIdmovimento() {
		return idmovimento;
	}

	public void setIdmovimento(Long idmovimento) {
		this.idmovimento = idmovimento;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	public BigDecimal getDebito() {
		return debito;
	}

	public void setDebito(BigDecimal debito) {
		this.debito = debito;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico.toUpperCase();
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public LocalDate getDatamovimentcao() {
		return datamovimentcao;
	}

	public void setDatamovimentcao(LocalDate datamovimentcao) {
		this.datamovimentcao = datamovimentcao;
	}

	public ContaCaixa getConta() {
		return conta;
	}

	public void setConta(ContaCaixa conta) {
		this.conta = conta;
	}

	public TipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idmovimento == null) ? 0 : idmovimento.hashCode());
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
		ContaMovimento other = (ContaMovimento) obj;
		if (idmovimento == null) {
			if (other.idmovimento != null)
				return false;
		} else if (!idmovimento.equals(other.idmovimento))
			return false;
		return true;
	}

}
