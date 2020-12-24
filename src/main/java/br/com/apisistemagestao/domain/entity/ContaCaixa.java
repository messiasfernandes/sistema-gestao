package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import org.springframework.format.annotation.NumberFormat;

@Entity
public class ContaCaixa implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long numeroconta;
	private Long agencia;
	@ManyToOne
	@JoinColumn
	private Banco banco;
	
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal total;

	public ContaCaixa() {
		total = BigDecimal.ZERO;
		banco = new Banco();
	}

	public Long getNumeroconta() {
		return numeroconta;
	}

	public void setNumeroconta(Long numeroconta) {
		this.numeroconta = numeroconta;
	}


	public Long getAgencia() {
		return agencia;
	}

	public void setAgencia(Long agencia) {
		this.agencia = agencia;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroconta == null) ? 0 : numeroconta.hashCode());
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
		ContaCaixa other = (ContaCaixa) obj;
		if (numeroconta == null) {
			if (other.numeroconta != null)
				return false;
		} else if (!numeroconta.equals(other.numeroconta))
			return false;
		return true;
	}



	

}
