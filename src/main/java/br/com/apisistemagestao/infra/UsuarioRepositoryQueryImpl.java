package br.com.apisistemagestao.infra;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.apisistemagestao.domain.entity.Funcionario;
import br.com.apisistemagestao.domain.entity.Usuario;

public class UsuarioRepositoryQueryImpl implements UsuarioRepositoryQuery {
	@PersistenceContext
	private EntityManager maneger;
	@Override
	public Usuario buscarEmail(String email) {
		CriteriaBuilder builder = maneger.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		Join<Usuario, Funcionario> usuario = (Join)root.fetch("funcionario");
		
		var predicates = new ArrayList<Predicate>();
		predicates.add((builder.equal(root.get("funcionario").get("email"), email)));
		
		
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Usuario> query = maneger.createQuery(criteria);
		return query.getSingleResult();
	}

}
