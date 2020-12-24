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

import br.com.apisistemagestao.domain.entity.ContasPagar;





public class ContaaPagarRepositotyQueryImpl implements ContaaPagarRepositotyQuery {
	@PersistenceContext
	private EntityManager maneger;

	@SuppressWarnings("static-access")
	@Override
	public List<ContasPagar> filtrar(ContasFilter contasFilter) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();

		CriteriaQuery<ContasPagar> criteria = builder.createQuery(ContasPagar.class);
		Root<ContasPagar> root = criteria.from(ContasPagar.class);
		
		var predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(contasFilter.getCnpj())&&(contasFilter.getNumeroTitulo()==null) ){
			System.out.println(contasFilter.getCnpj());
			predicates.add(builder.like(root.get("fornecedor").get("cpfouCnpj"), contasFilter.getCnpj() + "%"));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasFilter.getStatus().QUITADO));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasFilter.getStatus().CANCELADO));
	
		}
		if (!StringUtils.isEmpty(contasFilter.getNomeforma())) {
			predicates.add(
					builder.like(root.get("formadepagamento").get("nomeforma"), contasFilter.getNomeforma() + "%"));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasFilter.getStatus().QUITADO));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasFilter.getStatus().CANCELADO));

		}

		if ((contasFilter.getDatadevencimentoDe() != null) && (contasFilter.getDatadevencimentoAte() != null)) {
			predicates.add(builder.between(root.get("datavencimento"), contasFilter.getDatadevencimentoDe(),
					contasFilter.getDatadevencimentoAte()));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasFilter.getStatus().QUITADO));
			predicates.add(builder.notEqual(root.get("statuspagmaento"), contasFilter.getStatus().CANCELADO));
		}
	
		if ((contasFilter.getCnpj() !=null)&&(contasFilter.getNumeroTitulo()!=null)){
			predicates.add(builder.equal(root.get("fornecedor").get("cpfouCnpj"), contasFilter.getCnpj() ));
			predicates.add(builder.equal(root.get("numtitulo"), contasFilter.getNumeroTitulo()));
			
		}

		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ContasPagar> query = maneger.createQuery(criteria);
		return query.getResultList();

	}

	@Override
	public List<ContasPagar> filtro(Long numtitulo, String pcnpj) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();

		CriteriaQuery<ContasPagar> criteria = builder.createQuery(ContasPagar.class);
		Root<ContasPagar> root = criteria.from(ContasPagar.class);
		
		var predicates = new ArrayList<Predicate>();
		if ((numtitulo !=null)&&(pcnpj!=null)){
			predicates.add(builder.equal(root.get("fornecedor").get("cpfouCnpj"), pcnpj));
			predicates.add(builder.equal(root.get("numtitulo"), numtitulo));
			
		}

		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ContasPagar> query = maneger.createQuery(criteria);
		return query.getResultList();

	}



}
