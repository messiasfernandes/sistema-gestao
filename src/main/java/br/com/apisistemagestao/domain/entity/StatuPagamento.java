package br.com.apisistemagestao.domain.entity;

public enum StatuPagamento {
	PENDENTE("Pendente"), QUITADO("Quitado"), CANCELADO("Cancelado"), PAGOPARCIAL("Pagamento Parcial");

	private String descricao;

	private StatuPagamento( String descricao) {
	
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
