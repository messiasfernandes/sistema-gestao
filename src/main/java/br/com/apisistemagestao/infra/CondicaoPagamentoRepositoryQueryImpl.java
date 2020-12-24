package br.com.apisistemagestao.infra;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;


import br.com.apisistemagestao.domain.entity.Parcelas;



public class CondicaoPagamentoRepositoryQueryImpl implements CondicaoPagamentoRepositoryQuery {
	@PersistenceContext
	private EntityManager maneger;

	@Override
	public List<Parcelas> buscar(CondicaoFilter condicaoFilter) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Parcelas> criteria = builder.createQuery(Parcelas.class);
		Root<Parcelas> root = criteria.from(Parcelas.class);
		var predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(condicaoFilter.getDescricao())) {
			predicates.add(
					builder.like(root.get("condicaopagamento").get("descricao"), condicaoFilter.getDescricao() + "%"));

		}
		if (StringUtils.isEmpty(condicaoFilter.getDescricao())) {
			predicates.add(
					builder.equal(root.get("condicaopagamento").get("idcondicao"), condicaoFilter.getIdcondicao()));

		}
		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Parcelas> query = maneger.createQuery(criteria);
		return query.getResultList();
	}
	


}
