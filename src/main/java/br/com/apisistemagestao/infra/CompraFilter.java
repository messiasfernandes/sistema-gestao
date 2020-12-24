package br.com.apisistemagestao.infra;

public class CompraFilter {

	private Long codigofornecedor;
	private Long numeronta;
	
	private Long idCompra;

	public Long getCodigofornecedor() {
		return codigofornecedor;
	}

	public void setCodigofornecedor(Long codigofornecedor) {
		this.codigofornecedor = codigofornecedor;
	}

	public Long getNumeronta() {
		return numeronta;
	}

	public void setNumeronta(Long numeronta) {
		this.numeronta = numeronta;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

}
