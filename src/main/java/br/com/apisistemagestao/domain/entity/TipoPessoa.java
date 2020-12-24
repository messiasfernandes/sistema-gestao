package br.com.apisistemagestao.domain.entity;

public enum TipoPessoa {
	JURÍDiCA("Pessoa Física"), FÍSICA("Pessoa Jurídica");

	private String descricao;

	private TipoPessoa(String descricao) {

		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
