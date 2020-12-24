package br.com.apisistemagestao.dto;



public class CondicaoPagtoDTO {

	private Long idcondicao;
	private String descricao;
	private Integer qtdeparcelas;

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

	public Integer getQtdeparcelas() {
		return qtdeparcelas;
	}

	public void setQtdeparcelas(Integer qtdeparcelas) {
		this.qtdeparcelas = qtdeparcelas;
	}

	@Override
	public String toString() {
		return "CondicaoPagtoDTO [idcondicao=" + idcondicao + "]";
	}






}
