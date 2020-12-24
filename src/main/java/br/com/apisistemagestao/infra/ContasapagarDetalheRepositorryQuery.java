package br.com.apisistemagestao.infra;

import java.util.List;

import br.com.apisistemagestao.domain.entity.ContasPagarDetalhe;



public interface ContasapagarDetalheRepositorryQuery {
   List<ContasPagarDetalhe>filtro(ContasFilter contasfilter) ;
   
   ContasPagarDetalhe localizar(ContasFilter contasFilter);
   
}
