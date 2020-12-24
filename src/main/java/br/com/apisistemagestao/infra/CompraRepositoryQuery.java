package br.com.apisistemagestao.infra;

import java.util.List;

import br.com.apisistemagestao.domain.entity.Compra;
import br.com.apisistemagestao.domain.entity.ItemCompra;



public interface CompraRepositoryQuery {
	
	Boolean buscarnota(Long codigoFonecedor , Long numeroNota);

	Compra buscarCompra(Long codigoFonecedor , Long numeroNota);
	
	List<Compra> buscarComprasporNomeForecedor(String nome );
	
	List<Compra>buscarCompraporCnpjForncedoor( String cnpj );
	List<Compra>buscar();
	List<Compra>buscarCancelada();
	
	List<ItemCompra>buscarCompra(CompraFilter compfilter);
    ItemCompra pesquisarcompra(CompraFilter compraFilter);
}
