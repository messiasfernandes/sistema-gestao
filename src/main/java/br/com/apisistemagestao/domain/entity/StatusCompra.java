package br.com.apisistemagestao.domain.entity;

public enum StatusCompra {
	
	Entregue("Entregue"),
	Cancelado("Cancelado");
	
	
	private StatusCompra(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;
    

}
