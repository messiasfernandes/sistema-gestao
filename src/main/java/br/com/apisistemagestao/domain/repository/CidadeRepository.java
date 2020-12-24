package br.com.apisistemagestao.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Cidade;
import br.com.apisistemagestao.infra.CidadeRepositoryQuery;



@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> ,CidadeRepositoryQuery {

	List<Cidade> findByCidadenomeStartingWith(String cidadenome);
	Cidade  findByCidadenome(String cidadenome);
    Optional<Cidade > findByCodigoibge(String codigoibge);
	
	

}
