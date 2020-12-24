package br.com.apisistemagestao.serivces;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Produto;
import br.com.apisistemagestao.domain.entity.TipoProduto;
import br.com.apisistemagestao.domain.repository.ProdutoReposistory;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;


@Service
public class ProdutoService implements ServicePadrao<Produto> {
	@Autowired
	private ProdutoReposistory produtoRepository;

   public Produto converter(Produto objeto) {
		if(objeto.getTipoproduto().equals(TipoProduto.FISÍCO)&&(objeto.getUnidede().equals("KG"))) {
			objeto.converter(objeto.getQteestoque());
		///	objeto.covetervalorvenda(objeto.getPrecodevenda());
				System.out.println("Novo qute "+objeto.getQteestoque());
				
			}
			 System.out.println("estoque  "  +objeto.getQteestoque());
	   return produtoRepository.save(objeto);
   }
   public Produto  buscarcodFonecedor(String codigofab) {
	return produtoRepository.findByCodigofabricante(codigofab);
}
	@Transactional
	@Override
	public Produto salvar(Produto objeto) {
	
      return produtoRepository.save(objeto);

	}
    public List<Produto> buscarporEan(String ean13){
    	return produtoRepository.findByCodigoEan13(ean13);
    }
	@Override
	public List<Produto> pesquisar() {

		return produtoRepository.pesquisartodos();
	}
    public List<Produto>produtoporCategroira(String Pnome){
	  return produtoRepository.porcategoria(Pnome);
  }
	@Override
	public Optional<Produto> buscarporcodigo(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty()) {
			throw new NegocioExeption("Produto não Encontrado");
		}
		return produto;
	}

	@Override
	public List<Produto> buscar(String parametro) {

		return produtoRepository.findByNomeprodutoStartingWith(parametro);
	}
   public List<Produto>quetenha(String parametro){
	return produtoRepository.findByNomeprodutoContains(parametro);
	   
   }
	@Transactional
	@Override
	public void excluir(Long codigo) {
		try {
			produtoRepository.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioExeption("Produto Inexistente");
		}

	}

}
