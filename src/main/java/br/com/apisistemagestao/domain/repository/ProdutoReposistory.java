package br.com.apisistemagestao.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Produto;
import br.com.apisistemagestao.infra.ProdutoRepositoryQuery;


@Repository
public interface ProdutoReposistory extends JpaRepository<Produto, Long>,ProdutoRepositoryQuery {
   List<Produto> findByCodigoEan13(String codigoEan13);
   List<Produto> findByNomeprodutoStartingWith(String nomeproduto);
   List<Produto> findByNomeprodutoLike(String nomeproduto);
   List<Produto> findByNomeprodutoContains(String nomeproduto); 
   Produto  findByCodigofabricante(String codigofabricante);
   @Query(" from Produto p where p.qteestoque between  :qtde1 and  :qtde2 and p.ativo=TRUE ")
   List<Produto>relatorioEstoque(BigDecimal qtde1, BigDecimal qtde2);
   
}
