package br.com.apisistemagestao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.CondicaoPagamento;
import br.com.apisistemagestao.infra.CondicaoPagamentoRepositoryQuery;

@Repository
public interface CondicaoPagamentoRepository
		extends JpaRepository<CondicaoPagamento, Long>, CondicaoPagamentoRepositoryQuery {

	List<CondicaoPagamento> findByDescricaoStartingWith(String descricao);

}
