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

import br.com.apisistemagestao.domain.entity.ContasPagarDetalhe;



public class ContasapagarDetalheRepositorryQueryImpl implements ContasapagarDetalheRepositorryQuery {
	@PersistenceContext
	private EntityManager maneger;
	@SuppressWarnings("static-access")
	@Override
	public List<ContasPagarDetalhe> filtro(ContasFilter contasfilter) {
		
	
	
			CriteriaBuilder builder = maneger.getCriteriaBuilder();

			CriteriaQuery<ContasPagarDetalhe> criteria = builder.createQuery(ContasPagarDetalhe.class);
			Root<ContasPagarDetalhe> root = criteria.from(ContasPagarDetalhe.class);
			
			var predicates = new ArrayList<Predicate>();
			if (!StringUtils.isEmpty(contasfilter.getCnpj())) {
				System.out.println(contasfilter.getCnpj()+ "cnpj");
				predicates.add(builder.like(root.get("contasPagar").get("fornecedor").get("cpfouCnpj"), contasfilter.getCnpj() + "%"));
				predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().QUITADO));
				predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().CANCELADO));
		
			}
			if (!StringUtils.isEmpty(contasfilter.getNomeforma())) {
				predicates.add(
						builder.like(root.get("formadePagmamento").get("nomeforma"), contasfilter.getNomeforma() + "%"));
				predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().QUITADO));
				predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().CANCELADO));

			}

			if ((contasfilter.getDatadevencimentoDe() != null) && (contasfilter.getDatadevencimentoAte() != null)) {
				predicates.add(builder.between(root.get("datavencimento"), contasfilter.getDatadevencimentoDe(),
						contasfilter.getDatadevencimentoAte()));
				predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().QUITADO));
				predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().CANCELADO));
			}

			criteria.where(predicates.toArray(new Predicate[0]));
			TypedQuery<ContasPagarDetalhe> query = maneger.createQuery(criteria);
			return query.getResultList();

		}
	@Override
	  
	public ContasPagarDetalhe localizar(ContasFilter contasfilter) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();

		CriteriaQuery<ContasPagarDetalhe> criteria = builder.createQuery(ContasPagarDetalhe.class);
		Root<ContasPagarDetalhe> root = criteria.from(ContasPagarDetalhe.class);
		var predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(contasfilter.getCnpj())) {
			System.out.println(contasfilter.getCnpj()+ "cnpj");
			predicates.add(builder.like(root.get("contasPagar").get("fornecedor").get("cpfouCnpj"), contasfilter.getCnpj() + "%"));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().QUITADO));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().CANCELADO));
	
		}
		if (!StringUtils.isEmpty(contasfilter.getNomeforma())) {
			predicates.add(
					builder.like(root.get("formadePagmamento").get("nomeforma"), contasfilter.getNomeforma() + "%"));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().QUITADO));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().CANCELADO));

		}

		if ((contasfilter.getDatadevencimentoDe() != null) && (contasfilter.getDatadevencimentoAte() != null)) {
			predicates.add(builder.between(root.get("datavencimento"), contasfilter.getDatadevencimentoDe(),
					contasfilter.getDatadevencimentoAte()));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().QUITADO));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasfilter.getStatus().CANCELADO));
		}

		criteria.where(predicates.toArray(new Predicate[0]));
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ContasPagarDetalhe> query = maneger.createQuery(criteria);
		return query.getSingleResult();
	}

	}


