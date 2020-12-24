package br.com.apisistemagestao.dto;



public class ClienteDTO {
	
	private Long codigo;
	private String nomePessoa;
	private String cpfouCnpj;
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
	private CondicaoPagtoDTO condicaoPamento;

	private CidadeDTO cidade;
	public CondicaoPagtoDTO getCondicaoPamento() {
		return condicaoPamento;
	}
	public void setCondicaoPamento(CondicaoPagtoDTO condicaoPamento) {
		this.condicaoPamento = condicaoPamento;
	}
	public CidadeDTO getCidade() {
		return cidade;
	}
	public void setCidade(CidadeDTO cidade) {
		this.cidade = cidade;
	}

	
}
