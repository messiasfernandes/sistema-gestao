package br.com.apisistemagestao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByNomecategoria(String nomecategoria);

	List<Categoria> findByNomecategoriaStartingWith(String nomecategoria);

}
