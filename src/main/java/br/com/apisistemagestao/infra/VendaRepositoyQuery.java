package br.com.apisistemagestao.infra;

import java.util.List;

import br.com.apisistemagestao.domain.entity.ItemVenda;




public interface VendaRepositoyQuery {
   List<ItemVenda>buscarVendas(VendaseFilter vendasfilter);
   List<ItemVenda>relvenda(VendaseFilter vendarel);
   
}
