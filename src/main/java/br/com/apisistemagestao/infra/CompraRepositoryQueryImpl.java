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

import br.com.apisistemagestao.domain.entity.Compra;
import br.com.apisistemagestao.domain.entity.Fornecedor;
import br.com.apisistemagestao.domain.entity.ItemCompra;
import br.com.apisistemagestao.domain.entity.StatusCompra;




public class CompraRepositoryQueryImpl implements CompraRepositoryQuery {
	@PersistenceContext
	private EntityManager maneger;

	@Override
	public Boolean buscarnota(Long codigoFonecedor, Long numeroNota) {

		CriteriaBuilder builder = maneger.getCriteriaBuilder();

		CriteriaQuery<Compra> criteria = builder.createQuery(Compra.class);
		Root<Compra> root = criteria.from(Compra.class);
		Join<Compra, Fornecedor> fornecedor= (Join)root.fetch("fornecedor");
		Join<Compra, ItemCompra> itemcompra= (Join)root.fetch("itemcompras");
		var predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get("fornecedor").get("codigo"), codigoFonecedor));
		predicates.add(builder.equal(root.get("numeronota"), numeroNota));
		criteria.where(predicates.toArray(new Predicate[0]));
		return !maneger.createQuery(criteria).getResultList().isEmpty();
	}

	@Override
	public Compra buscarCompra(Long codigoFonecedor, Long numeroNota) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Compra> criteria = builder.createQuery(Compra.class);
		Root<Compra> root = criteria.from(Compra.class);
		Join<Compra, Fornecedor> fornecedor= (Join)root.fetch("fornecedor");
		Join<Compra, ItemCompra> itemcompra= (Join)root.fetch("itemcompras").fetch("produto");
		var predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get("fornecedor").get("codigo"), codigoFonecedor));
		predicates.add(builder.equal(root.get("numeronota"), numeroNota));
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Compra> query = maneger.createQuery(criteria);
		return query.getSingleResult();
	}

	@Override
	public List<Compra> buscarComprasporNomeForecedor(String nome) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Compra> criteria = builder.createQuery(Compra.class);
		Root<Compra> root = criteria.from(Compra.class);
		Join<Compra, Fornecedor> fornecedor= (Join)root.fetch("fornecedor");


		var predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(nome)) {
			predicates.add(builder.like(root.get("fornecedor").get("nomePessoa"), nome + "%"));
		///	predicates.add(builder.notEqual(root.get("statusCompra"), StatusCompra.Cancelado));
		}
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Compra> query = maneger.createQuery(criteria);
		return query.getResultList();

	}

	@Override
	public List<Compra> buscarCompraporCnpjForncedoor(String cnpj) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Compra> criteria = builder.createQuery(Compra.class);
		Root<Compra> root = criteria.from(Compra.class);
		var predicates = new ArrayList<Predicate>();
		if (!StringUtils.isEmpty(cnpj)) {
			predicates.add(builder.like(root.get("fornecedor").get("cpfouCnpj"), cnpj + "%"));
		}
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Compra> query = maneger.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Compra> buscarCancelada() {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Compra> criteria = builder.createQuery(Compra.class);
		Root<Compra> root = criteria.from(Compra.class);
		var predicates = new ArrayList<Predicate>();

		predicates.add(builder.equal(root.get("statusCompra"), StatusCompra.Cancelado));

		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Compra> query = maneger.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<ItemCompra> buscarCompra(CompraFilter compfilter) {

		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<ItemCompra> criteria = builder.createQuery(ItemCompra.class);
		Root<ItemCompra> root = criteria.from(ItemCompra.class);
		var predicates = new ArrayList<Predicate>();
		if(!StringUtils.isEmpty(compfilter.getIdCompra())) {
			predicates.add(
					builder.equal(root.get("compra").get("idCompra"),compfilter.getIdCompra()));
		//	predicates.add(builder.notEqual(root.get("compra").get("statusCompra"), StatusCompra.Cancelado));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ItemCompra> query = maneger.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public ItemCompra pesquisarcompra(CompraFilter compraFilter) {
		
		return null;
	}

	@Override
	public List<Compra> buscar() {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Compra> criteria = builder.createQuery(Compra.class);
		Root<Compra> root = criteria.from(Compra.class);
		var predicates = new ArrayList<Predicate>();
		predicates.add(builder.notEqual(root.get("statusCompra"), StatusCompra.Cancelado));
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Compra> query = maneger.createQuery(criteria);
		return query.getResultList();
	}

}
