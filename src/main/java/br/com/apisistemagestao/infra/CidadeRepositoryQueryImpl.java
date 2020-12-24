package br.com.apisistemagestao.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.com.apisistemagestao.domain.entity.Cidade;
import br.com.apisistemagestao.domain.entity.Estado;




public class CidadeRepositoryQueryImpl implements CidadeRepositoryQuery {
	@PersistenceContext
	private EntityManager maneger;
	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> listartodas() {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		@SuppressWarnings({ "rawtypes", "unused" })
		Join<Cidade, Estado> estado= (Join)root.fetch("estado");
		criteria.select(root);
   	    TypedQuery<Cidade> query = maneger.createQuery(criteria);
		return query.setMaxResults(300).getResultList();
	}

	

}
