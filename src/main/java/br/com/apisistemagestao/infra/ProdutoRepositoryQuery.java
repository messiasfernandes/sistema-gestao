package br.com.apisistemagestao.infra;

import java.util.List;

import br.com.apisistemagestao.domain.entity.Produto;



public interface ProdutoRepositoryQuery {
	List<Produto > porcategoria(String nome);
	List< Produto>pesquisartodos();

}
