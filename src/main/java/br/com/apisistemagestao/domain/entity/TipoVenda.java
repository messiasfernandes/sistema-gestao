package br.com.apisistemagestao.domain.entity;

public enum TipoVenda {

	Venda("Vanda"), Orçameento("Orçamento");

	private String descricao;

	private TipoVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
