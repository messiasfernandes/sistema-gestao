package br.com.apisistemagestao.infra;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import br.com.apisistemagestao.domain.entity.Ncms;

public class NcmRepositoryQueryImpl implements NcmRepositoryQuery {
	@PersistenceContext
	private EntityManager maneger;
	@Override
	public Ncms buscarNcm(String parametro) {
		System.out.println(parametro);
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Ncms> criteria = builder.createQuery(Ncms.class);
		Root<Ncms> root = criteria.from(Ncms.class);
		var predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get("cod_ncm"), parametro));
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Ncms> query = maneger.createQuery(criteria);
		return query.getSingleResult();
	}

}
