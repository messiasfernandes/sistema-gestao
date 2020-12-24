package br.com.apisistemagestao.infra;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.StatuPagamento;



public class ContasFilter {
	private String nomeforma;
    private Long id;
	private String cnpj;

	private StatuPagamento status;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datadevencimentoDe;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datadevencimentoAte;

   private Long numeroTitulo;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeforma() {
		return nomeforma;
	}

	public void setNomeforma(String nomeforma) {
		this.nomeforma = nomeforma;
	}

	public StatuPagamento getStatus() {
		return status;
	}

	public void setStatus(StatuPagamento status) {
		this.status = status;
	}

	public LocalDate getDatadevencimentoDe() {
		return datadevencimentoDe;
	}

	public void setDatadevencimentoDe(LocalDate datadevencimentoDe) {
		this.datadevencimentoDe = datadevencimentoDe;
	}

	public LocalDate getDatadevencimentoAte() {
		return datadevencimentoAte;
	}

	public void setDatadevencimentoAte(LocalDate datadevencimentoAte) {
		this.datadevencimentoAte = datadevencimentoAte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroTitulo() {
		return numeroTitulo;
	}

	public void setNumeroTitulo(Long numeroTitulo) {
		this.numeroTitulo = numeroTitulo;
	}

}
