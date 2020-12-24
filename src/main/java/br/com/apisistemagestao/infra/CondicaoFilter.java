package br.com.apisistemagestao.infra;

public class CondicaoFilter {
	private Long idcondicao;
	private String descricao;
	
	private Long idfornecedor;
	public Long getIdcondicao() {
		return idcondicao;
	}
	public void setIdcondicao(Long idcondicao) {
		this.idcondicao = idcondicao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getIdfornecedor() {
		return idfornecedor;
	}
	public void setIdfornecedor(Long idfornecedor) {
		this.idfornecedor = idfornecedor;
	}
	

}
