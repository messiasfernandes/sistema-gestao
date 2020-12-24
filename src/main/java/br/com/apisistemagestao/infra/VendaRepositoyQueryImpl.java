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

import br.com.apisistemagestao.domain.entity.ItemVenda;
import br.com.apisistemagestao.domain.entity.Venda;


public class VendaRepositoyQueryImpl implements VendaRepositoyQuery {
	@PersistenceContext
	private EntityManager maneger;

	@Override
	public List<ItemVenda> buscarVendas(VendaseFilter vendasfilter) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<ItemVenda> criteria = builder.createQuery(ItemVenda.class);
		Root<ItemVenda> root = criteria.from(ItemVenda.class);
		Join<ItemVenda, Venda> venda = (Join) root.fetch("venda").fetch("cliente");
		/// .fetch("condicaoPamento");

		var predicates = new ArrayList<Predicate>();
		if (vendasfilter == null) {
			criteria.select(root);
		}
		if (!StringUtils.isEmpty(vendasfilter.getNomePessoa())) {
			predicates.add(builder.like(root.get("venda").get("cliente").get("nomePessoa"),
					vendasfilter.getNomePessoa() + "%"));
			criteria.where(predicates.toArray(new Predicate[0]));
		}
		if (!StringUtils.isEmpty(vendasfilter.getCpfouCnpj())) {
			predicates.add(
					builder.like(root.get("venda").get("cliente").get("cpfouCnpj"), vendasfilter.getCpfouCnpj() + "%"));
			criteria.where(predicates.toArray(new Predicate[0]));
		}
		if (vendasfilter.getCodigo() != null) {
			predicates.add(builder.equal(root.get("venda").get("cliente").get("codigo"), vendasfilter.getCodigo()));
			criteria.where(predicates.toArray(new Predicate[0]));
		}

		TypedQuery<ItemVenda> query = maneger.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<ItemVenda> relvenda(VendaseFilter vendarel) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<ItemVenda> criteria = builder.createQuery(ItemVenda.class);
		Root<ItemVenda> root = criteria.from(ItemVenda.class);
		Join<ItemVenda, Venda> venda = (Join) root.fetch("venda").fetch("cliente");
		var predicates = new ArrayList<Predicate>();
		if(vendarel==null) {
			criteria.select(root);
		}
		if (vendarel != null) {
		predicates.add(builder.equal(root.get("venda").get("cliente").get("codigo"), vendarel.getCodigo()));
		predicates.add(builder.equal(root.get("venda").get("idvenda"), vendarel.getIdvenda()));
		predicates.add(builder.equal(root.get("venda").get("datavenda"), vendarel.getDatavenda()));
		criteria.where(predicates.toArray(new Predicate[0]));
		}
		TypedQuery<ItemVenda> query = maneger.createQuery(criteria);
		return query.getResultList();
	}

}
