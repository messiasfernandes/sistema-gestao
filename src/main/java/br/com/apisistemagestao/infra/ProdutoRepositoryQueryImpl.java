package br.com.apisistemagestao.infra;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.com.apisistemagestao.domain.entity.Categoria;
import br.com.apisistemagestao.domain.entity.Produto;




public class ProdutoRepositoryQueryImpl implements ProdutoRepositoryQuery {
	@PersistenceContext
	private EntityManager maneger;
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	public List<Produto> porcategoria(String nome) {
		
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);
		Join<Produto, Categoria> categoria= (Join)root.fetch("categoria");
		var predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(nome)) {
			predicates.add(builder.like(root.get("categoria").get("nomecategoria"), nome + "%"));
			
		}
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Produto> query = maneger.createQuery(criteria);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> pesquisartodos() {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);
		@SuppressWarnings({ "unused", "rawtypes" })
		Join<Produto, Categoria> categoria= (Join)root.fetch("categoria");
		
		criteria.select(root);
   	    TypedQuery<Produto> query = maneger.createQuery(criteria);
		return query.getResultList();
	}

}
