package br.com.apisistemagestao.serivces;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.ItemVenda;
import br.com.apisistemagestao.domain.entity.Produto;
import br.com.apisistemagestao.domain.repository.ProdutoReposistory;
import br.com.apisistemagestao.domain.repository.VendaRepository;
import br.com.apisistemagestao.infra.VendaseFilter;




@Service
public class RelatorioService {
	@Autowired
	private VendaRepository vendarepository;
	@Autowired
	private ProdutoReposistory produtorepo;
	public List<ItemVenda> relavenda(VendaseFilter vendaRel){
		return vendarepository.relvenda(vendaRel);
	}
    public List<Produto>relqtedEStqoue (BigDecimal pQtde1, BigDecimal pqte2){
    	return produtorepo.relatorioEstoque(pQtde1, pqte2);
    }
}
