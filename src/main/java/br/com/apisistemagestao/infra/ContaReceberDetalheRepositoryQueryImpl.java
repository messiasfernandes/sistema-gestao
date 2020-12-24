package br.com.apisistemagestao.infra;

import java.time.LocalDate;
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

import br.com.apisistemagestao.domain.entity.ContaDetalheReceber;
import br.com.apisistemagestao.domain.entity.ContasaReceber;
import br.com.apisistemagestao.domain.entity.StatuPagamento;





public class ContaReceberDetalheRepositoryQueryImpl implements ContaReceberDetalheRepositoryQuery{
	@PersistenceContext
	private EntityManager maneger;
	@Override
	public List<ContaDetalheReceber> listar() {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();

		CriteriaQuery<ContaDetalheReceber> criteria = builder.createQuery(ContaDetalheReceber.class);
		Root<ContaDetalheReceber> root = criteria.from(ContaDetalheReceber.class);
		var predicates = new ArrayList<Predicate>();
		predicates.add(builder.notEqual(root.get("statuspagmaento"), StatuPagamento.QUITADO));
		predicates.add(builder.notEqual(root.get("statuspagmaento"), StatuPagamento.CANCELADO));
		Join<ContaDetalheReceber, ContasaReceber> contareceber= (Join)root.fetch("contaReceber");
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ContaDetalheReceber> query = maneger.createQuery(criteria);
		return query.getResultList();
	}
	@Override
	public List<ContaDetalheReceber> listarAtrasdos() {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
         LocalDate data= LocalDate.now();
		CriteriaQuery<ContaDetalheReceber> criteria = builder.createQuery(ContaDetalheReceber.class);
		Root<ContaDetalheReceber> root = criteria.from(ContaDetalheReceber.class);
		var predicates = new ArrayList<Predicate>();
		predicates.add(builder.notEqual(root.get("statuspagmaento"), StatuPagamento.QUITADO));
		predicates.add(builder.notEqual(root.get("statuspagmaento"), StatuPagamento.CANCELADO));
		predicates.add(builder.lessThan(root.get("datavencimento"), data));
		Join<ContaDetalheReceber, ContasaReceber> contareceber= (Join)root.fetch("contaReceber");
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ContaDetalheReceber> query = maneger.createQuery(criteria);
		return query.getResultList();
	}

}
