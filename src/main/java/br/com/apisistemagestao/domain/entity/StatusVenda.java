package br.com.apisistemagestao.domain.entity;

public enum StatusVenda {
   Finalizada ("Finalizada"),
   Cancelada ("Cancelada");
	private String descricao;

	private StatusVenda(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
