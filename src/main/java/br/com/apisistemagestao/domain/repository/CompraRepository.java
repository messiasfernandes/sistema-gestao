package br.com.apisistemagestao.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Compra;
import br.com.apisistemagestao.infra.CompraRepositoryQuery;



@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>, CompraRepositoryQuery{
	
	Compra findByNumeronota(Long numeronota);
	

}
