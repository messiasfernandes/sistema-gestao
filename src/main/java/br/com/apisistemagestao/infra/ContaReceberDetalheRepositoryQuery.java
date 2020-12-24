package br.com.apisistemagestao.infra;

import java.util.List;

import br.com.apisistemagestao.domain.entity.ContaDetalheReceber;



public interface ContaReceberDetalheRepositoryQuery {
   List<ContaDetalheReceber> listar();
   List<ContaDetalheReceber>listarAtrasdos();
}
