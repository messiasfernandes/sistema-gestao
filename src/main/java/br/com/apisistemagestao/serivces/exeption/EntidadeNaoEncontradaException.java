package br.com.apisistemagestao.serivces.exeption;

public class EntidadeNaoEncontradaException extends NegocioExeption {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}

}
