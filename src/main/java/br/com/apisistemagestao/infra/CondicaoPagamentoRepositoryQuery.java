package br.com.apisistemagestao.infra;

import java.util.List;


import br.com.apisistemagestao.domain.entity.Parcelas;



public interface CondicaoPagamentoRepositoryQuery {
   List<Parcelas> buscar(CondicaoFilter condicaoFilter);
 
   
}
