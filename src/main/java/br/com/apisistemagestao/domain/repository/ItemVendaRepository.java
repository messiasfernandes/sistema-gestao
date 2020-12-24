package br.com.apisistemagestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.ItemVenda;


@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

}
