package br.com.apisistemagestao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apisistemagestao.domain.entity.FormadePagmamento;


public interface FormadePagamentoRepositroy
		extends JpaRepository<FormadePagmamento, Long> {

	FormadePagmamento findByNomeforma(String nomeforma);
	
	List<FormadePagmamento> findByNomeformaStartingWith(String nomeforma);

}
