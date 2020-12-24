package br.com.apisistemagestao.serivces;

import java.util.List;
import java.util.Optional;

public interface ServicePadrao<T> {

	public T salvar(T objeto);

	public List<T> pesquisar();

	public Optional<T> buscarporcodigo(Long id);

	public List<T> buscar(String parametro);

	public void excluir(Long codigo);
}
