package br.com.apisistemagestao.dto;

public class FormaPagamentoDTO {

	private Long idformapagamento;
	private String nomeforma;

	public String getNomeforma() {
		return nomeforma;
	}

	public void setNomeforma(String nomeforma) {
		this.nomeforma = nomeforma;
	}

	public void setIdformapagamento(Long idformapagamento) {
		this.idformapagamento = idformapagamento;
	}

	public Long getIdformapagamento() {
		return idformapagamento;
	}
}
