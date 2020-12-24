package br.com.apisistemagestao.dto;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.TipoPessoa;




public class FornecedorDTO {
	private Long codigo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNiverouFundacao;

	private String nomePessoa;

	private String cpfouCnpj;

	private String logradouro;
	private Integer numero;

	private String bairro;

	private String complemto;

	private String cep;

	private String telefone;
    private String rgouInscricao;
	private String celular;

	private String email;
	private TipoPessoa tipessoa;
	private String representante;
	private String site;
	private CondicaoPagtoDTO condicaoPamento;

	private CidadeDTO cidade;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataNiverouFundacao() {
		return dataNiverouFundacao;
	}

	public void setDataNiverouFundacao(LocalDate dataNiverouFundacao) {
		this.dataNiverouFundacao = dataNiverouFundacao;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpfouCnpj() {
		return cpfouCnpj;
	}

	public void setCpfouCnpj(String cpfouCnpj) {
		this.cpfouCnpj = cpfouCnpj;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemto() {
		return complemto;
	}

	public void setComplemto(String complemto) {
		this.complemto = complemto;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CondicaoPagtoDTO getCondicaoPamento() {
		return condicaoPamento;
	}

	public void setCondicaoPamento(CondicaoPagtoDTO condicaoPamento) {
		this.condicaoPamento = condicaoPamento;
	}

	public TipoPessoa getTipessoa() {
		return tipessoa;
	}

	public void setTipessoa(TipoPessoa tipessoa) {
		this.tipessoa = tipessoa;
	}

	public CidadeDTO getCidade() {
		return cidade;
	}

	public void setCidade(CidadeDTO cidade) {
		this.cidade = cidade;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRgouInscricao() {
		return rgouInscricao;
	}

	public void setRgouInscricao(String rgouInscricao) {
		this.rgouInscricao = rgouInscricao;
	}



}
