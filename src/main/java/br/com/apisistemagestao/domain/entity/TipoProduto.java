package br.com.apisistemagestao.domain.entity;

public enum TipoProduto {
	FISÍCO("FíSICO"), SERVIÇO("Serviço"), DIGITAL("Digital");

	private String descricao;

	private TipoProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
