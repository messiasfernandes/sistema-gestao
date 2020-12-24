package br.com.apisistemagestao.dto;



public class ForncedorContaDTO {

	private Long codigo;
	private String nomePessoa;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	private String cpfouCnpj;

}
