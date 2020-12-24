package br.com.apisistemagestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.ContaDetalheReceber;
import br.com.apisistemagestao.infra.ContaReceberDetalheRepositoryQuery;


@Repository
public interface ContaReceberDetalheRepository extends JpaRepository<ContaDetalheReceber, Long> , ContaReceberDetalheRepositoryQuery{

}
