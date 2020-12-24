package br.com.apisistemagestao.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Ncms;
import br.com.apisistemagestao.infra.NcmRepositoryQuery;
@Repository
public interface NcmRepository extends JpaRepository<Ncms, Long>,NcmRepositoryQuery {
	

	



}
