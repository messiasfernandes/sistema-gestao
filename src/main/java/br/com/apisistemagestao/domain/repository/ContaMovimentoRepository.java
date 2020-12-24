package br.com.apisistemagestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.ContaMovimento;


@Repository
public interface ContaMovimentoRepository extends JpaRepository<ContaMovimento, Long> {

}
