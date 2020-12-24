package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class ContasaReceber implements Serializable {

	 
      	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idcontareceber;
		@ManyToOne
		@JoinColumn(nullable = false)
		private Cliente  cliente;
		@JsonFormat(pattern = "dd/MM/yyyy")
		private LocalDate datalancamento;
		@Column(length = 60, nullable = false)
		private String descricao;
		@Fetch(FetchMode.SUBSELECT)
		@OneToMany(mappedBy = "contaReceber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
		private List<ContaDetalheReceber> contasdetalhe = new ArrayList<ContaDetalheReceber>();

		@Column(columnDefinition = "DECIMAL(9,3) DEFAULT 0.000")
		@NumberFormat(pattern = "#,##0.00")
		private BigDecimal totalconta;
		@Transient
		private Long numtitulo;
		public Long getIdcontareceber() {
			return idcontareceber;
		}
		public void setIdcontareceber(Long idcontareceber) {
			this.idcontareceber = idcontareceber;
		}
		
		public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		public LocalDate getDatalancamento() {
			return datalancamento;
		}
		public void setDatalancamento(LocalDate datalancamento) {
			this.datalancamento = datalancamento;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public List<ContaDetalheReceber> getContasdetalhe() {
			return contasdetalhe;
		}
		public void setContasdetalhe(List<ContaDetalheReceber> contasdetalhe) {
			this.contasdetalhe = contasdetalhe;
		}

		public BigDecimal getTotalconta() {
			return totalconta;
		}
		public void setTotalconta(BigDecimal totalconta) {
			this.totalconta = totalconta;
		}
		public Long getNumtitulo() {
			return numtitulo;
		}
		public void setNumtitulo(Long numtitulo) {
			this.numtitulo = numtitulo;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idcontareceber == null) ? 0 : idcontareceber.hashCode());
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
			ContasaReceber other = (ContasaReceber) obj;
			if (idcontareceber == null) {
				if (other.idcontareceber != null)
					return false;
			} else if (!idcontareceber.equals(other.idcontareceber))
				return false;
			return true;
		}
		
}
