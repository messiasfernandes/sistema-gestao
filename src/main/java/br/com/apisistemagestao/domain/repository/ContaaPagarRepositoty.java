package br.com.apisistemagestao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.ContasPagar;
import br.com.apisistemagestao.infra.ContaaPagarRepositotyQuery;


@Repository
public interface ContaaPagarRepositoty	extends JpaRepository<ContasPagar, Long>,ContaaPagarRepositotyQuery {
	@Query("from ContasPagar c where c.statuspagmaento<>'CANCELADO' and c.statuspagmaento <>'QUITADO'")
   List<ContasPagar>consultar();
	@Query("from ContasPagar c where c.statuspagmaento ='CANCELADO' ")
     List<ContasPagar>buscarCancelado();
	@Query("from ContasPagar c where c.statuspagmaento<>'CANCELADO' and c.statuspagmaento <>'QUITADO' and c.datavencimento < CURRENT_DATE")
	List<ContasPagar>listarAtrasasdas();
	@Query("from ContasPagar c where c.fornecedor.nomePessoa like %:nome% and c.statuspagmaento<>'CANCELADO' and c.statuspagmaento <>'QUITADO'")
	List<ContasPagar>buscarPorNome(String nome);
}
