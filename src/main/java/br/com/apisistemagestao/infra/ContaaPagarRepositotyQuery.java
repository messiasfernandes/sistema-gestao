package br.com.apisistemagestao.infra;

import java.util.List;

import br.com.apisistemagestao.domain.entity.ContasPagar;


public interface ContaaPagarRepositotyQuery {
   List<ContasPagar> filtrar(ContasFilter contasFilter);
   List<ContasPagar>filtro(Long numtitulo, String pcnpj);
}
