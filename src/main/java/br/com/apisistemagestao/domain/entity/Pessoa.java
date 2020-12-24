package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.grupos.PessoaFisica;
import br.com.apisistemagestao.domain.grupos.PessoaJuridica;
import br.com.apisistemagestao.domain.validacao.PessoaGroupSequenceProvider;



@MappedSuperclass
@GroupSequenceProvider( PessoaGroupSequenceProvider.class)
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNiverouFundacao;
	@NotEmpty
	@Column(length = 80, nullable = false)
	private String nomePessoa;
	@Column( length = 14)
	@CPF(groups = PessoaFisica.class,message = "Cpf Inválido !!!")
	@CNPJ(groups = PessoaJuridica.class, message = "Cnpj Inváldo !!!")
	private String cpfouCnpj;
	@Column(length = 20)
	private String rgouInscricao;
	@Column(length = 80)
	private String logradouro;
	private Integer numero;
	@Column(length = 40)
	private String bairro;
	@Column(length = 30)
	private String complemto;
	@Column(length = 9)
	private String cep;
	@Column(length = 12)
	private String telefone;
	@Column(length = 14)
	private String celular;
	@Email
	@Column(length = 100, unique = true)
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Cidade cidade;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private TipoPessoa tipessoa;
	public Pessoa() {
		cidade=new Cidade();
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setDataNiverouFundacao(LocalDate dataNiverouFundacao) {
		this.dataNiverouFundacao = dataNiverouFundacao;
	}
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa.toUpperCase();
	}
	public String getCpfouCnpj() {
		return cpfouCnpj;
	}
	public void setCpfouCnpj(String cpfouCnpj) {
		this.cpfouCnpj = cpfouCnpj.replace(".", "").replace("/", "").replace("-", "");;
	}
	
	public String getRgouInscricao() {
		return rgouInscricao;
	}
	public void setRgouInscricao(String rgouInscricao) {
		this.rgouInscricao = rgouInscricao;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro.toUpperCase();
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
		this.bairro = bairro.toUpperCase();
	}
	public String getComplemto() {
		return complemto;
	}
	public void setComplemto(String complemto) {
		this.complemto = complemto.toUpperCase();
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep.replace(".", "").replace("-", "");
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone.replace("(", "").replace(")", "").replace("-", "");
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular.replace("(", "").replace(")", "").replace("-", "");
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public TipoPessoa getTipessoa() {
		return tipessoa;
	}
	public void setTipessoa(TipoPessoa tipessoa) {
		this.tipessoa = tipessoa;
	}
	
	public LocalDate getDataNiverouFundacao() {
		return dataNiverouFundacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
