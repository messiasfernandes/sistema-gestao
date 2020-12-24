package br.com.apisistemagestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Venda;
import br.com.apisistemagestao.infra.VendaRepositoyQuery;



@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>,VendaRepositoyQuery {

}
