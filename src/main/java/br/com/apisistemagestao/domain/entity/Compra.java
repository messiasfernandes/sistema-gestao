package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compra")
	private Long idCompra;
	@NotNull
	private Long numeronota;
	@Transient
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal markqup;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCompra;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorseguro;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorfrete;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal outrasdepesza;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal desconto;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datadeentrada;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datacancelamento;
	@Column(length = 100)
	private String motivoCancelamento;
	@Column(nullable = false, columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@JsonFormat(pattern = "#,##0.00", shape = Shape.STRING)
	@NumberFormat(pattern = "#,##0.00")

	private BigDecimal totalCompra;
	@Column(nullable = false, columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	@JsonFormat(pattern = "#,##0.00", shape = Shape.STRING)
	private BigDecimal totalproduto;
	@Transient
	private Long idfonecedor;

	public Long getIdfonecedor() {
		return idfonecedor;
	}

	public void setIdfonecedor(Long idfonecedor) {
		this.idfonecedor = idfonecedor;
	}

	@ManyToOne
	@JoinColumn
	private Fornecedor fornecedor;
	@ManyToOne
	@JoinColumn
	Funcionario funcionario;
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private StatusCompra statusCompra;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemCompra> itemcompras = new ArrayList<>();
	@Transient
	private String email;
	@Transient
	private List<ContasPagar> contas = new ArrayList<>();

	public Compra() {
		valorfrete = BigDecimal.ZERO;
		valorseguro = BigDecimal.ZERO;
		outrasdepesza = BigDecimal.ZERO;
		desconto = BigDecimal.ZERO;
		totalCompra = BigDecimal.ZERO;
		totalproduto = BigDecimal.ZERO;
		fornecedor = new Fornecedor();

	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<ContasPagar> getContas() {
		return contas;
	}

	public void setContas(List<ContasPagar> contas) {
		this.contas = contas;
	}

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

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public LocalDateTime getDatadeentrada() {
		return datadeentrada;
	}

	public void setDatadeentrada(LocalDateTime datadeentrada) {
		this.datadeentrada = datadeentrada;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ItemCompra> getItemcompras() {
		return itemcompras;
	}

	public void setItemcompras(List<ItemCompra> itemcompras) {
		this.itemcompras = itemcompras;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getMarkqup() {
		return markqup;
	}

	public void setMarkqup(BigDecimal markqup) {
		this.markqup = markqup;
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

	public LocalDate getDatacancelamento() {
		return datacancelamento;
	}

	public StatusCompra getStatusCompra() {
		return statusCompra;
	}

	public void setStatusCompra(StatusCompra statusCompra) {
		this.statusCompra = statusCompra;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCompra == null) ? 0 : idCompra.hashCode());
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
		Compra other = (Compra) obj;
		if (idCompra == null) {
			if (other.idCompra != null)
				return false;
		} else if (!idCompra.equals(other.idCompra))
			return false;
		return true;
	}

	private BigDecimal casasDecimais(int casas, BigDecimal valor) {
		String quantCasas = "%." + casas + "f", textoValor = "0";
		try {
			textoValor = String.format(Locale.getDefault(), quantCasas, valor);
		} catch (java.lang.IllegalArgumentException e) {
			// Quando os digitos com 2 casas decimais forem Zeros, exemplo: 0.000001233888.
			// Nao existe valor 0,00 , logo entra na java.lang.IllegalArgumentException.
			if (e.getMessage().equals("Digits < 0"))
				textoValor = "0";
			System.out.println(e.getMessage());
		}
		return new BigDecimal(textoValor.replace(",", "."));
	}
}
