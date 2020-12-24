package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import br.com.apisistemagestao.domain.grupos.PessoaJuridica;


public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idEmpresa;
	private String nomeEpresa;
	private String email;
	@CNPJ(groups = PessoaJuridica.class)
	@CPF(groups = PessoaJuridica.class)
	private String cnpouCpf;
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNomeEpresa() {
		return nomeEpresa;
	}
	public void setNomeEpresa(String nomeEpresa) {
		this.nomeEpresa = nomeEpresa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCnpouCpf() {
		return cnpouCpf;
	}
	public void setCnpouCpf(String cnpouCpf) {
		this.cnpouCpf = cnpouCpf;
	}

}
