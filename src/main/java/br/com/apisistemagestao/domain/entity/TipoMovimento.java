package br.com.apisistemagestao.domain.entity;

public enum TipoMovimento {
   Crédito("Crédito"),
   Débito("Débito"); 
	private String descricao;

	private TipoMovimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
