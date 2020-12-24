package br.com.apisistemagestao.serivces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.FormadePagmamento;
import br.com.apisistemagestao.domain.entity.ItemVenda;
import br.com.apisistemagestao.domain.entity.TipoProduto;
import br.com.apisistemagestao.domain.entity.TipoVenda;
import br.com.apisistemagestao.domain.entity.Venda;
import br.com.apisistemagestao.domain.repository.ProdutoReposistory;
import br.com.apisistemagestao.domain.repository.VendaRepository;
import br.com.apisistemagestao.infra.VendaseFilter;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;



@Service
public class VendaService implements ServicePadrao<Venda> {
	@Autowired
	private VendaRepository vendarepositrory;
	@Autowired
	private ProdutoReposistory produtoRepository;

	@Override
	public Venda salvar(Venda objeto) {

		Venda venda = new Venda();

		FormadePagmamento forma = null;
		venda.setCliente(objeto.getCliente());
		venda.setDatavenda(LocalDate.now());
		venda.setDatahoravenda(LocalDateTime.now());
		venda.setToTalVenda(objeto.getToTalVenda());

		for (int i = 0; i < venda.getCliente().getCondicaoPamento().getQtdeparcelas(); i++) {
			forma = new FormadePagmamento();
			forma.setIdformapagamento(venda.getCliente().getCondicaoPamento().getColecaoparcelas().get(i)
					.getFormadePagamento().getIdformapagamento());
		}

		if ((forma.getIdformapagamento() != 1l) && (forma.getIdformapagamento() != 6l))
			if (venda.getCliente().getLimecredito().compareTo((venda.getToTalVenda())) < 0) {

				throw new NegocioExeption("Cliente não tem limite disponível");

			}

		venda.setStatusVenda(objeto.getStatusVenda());
		venda.setTipovenda(objeto.getTipovenda());
        BigDecimal totaldesconto = BigDecimal.ZERO;
		for (int pos = 0; pos < objeto.getItens().size(); pos++) {

			ItemVenda itemvenda = new ItemVenda();
			itemvenda.setVenda(venda);

			itemvenda.setQtdeItem(objeto.getItens().get(pos).getQtdeItem());
			itemvenda.setProduto(objeto.getItens().get(pos).getProduto());
			itemvenda.setDesconto(objeto.getItens().get(pos).getDesconto());
			totaldesconto= totaldesconto.add(itemvenda.getDesconto());
            itemvenda.setSubTotal(itemvenda.getQtdeItem().multiply(objeto.getItens().get(pos).getProduto().getPrecodevenda()).subtract(itemvenda.getDesconto()));
           venda.setToTalVenda(objeto.getToTalVenda().subtract(totaldesconto));
           System.out.println("Desconto "+ itemvenda.getDesconto());
           System.out.println("Sub total  "+ itemvenda.getSubTotal());
          System.out.println("total venda "+venda.getToTalVenda());
			venda.getItens().add(itemvenda);
         
		}
		

		if (venda.getTipovenda().equals(TipoVenda.Venda)) {

			for (int j = 0; j < venda.getItens().size(); j++) {
				if(venda.getItens().get(j).getProduto().getTipoproduto().equals(TipoProduto.FISÍCO)) {
					venda.getItens().get(j).getProduto().baixarEstoque(venda.getItens().get(j).getQtdeItem());
				produtoRepository.save((venda.getItens().get(j).getProduto()));
				}
				
			}
		}
		vendarepositrory.save(venda);
		return venda;

	}

	@Override
	public List<Venda> pesquisar() {

		return vendarepositrory.findAll();
	}

	@Override
	public Optional<Venda> buscarporcodigo(Long id) {

		return vendarepositrory.findById(id);
	}

	@Override
	public List<Venda> buscar(String parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	public List<ItemVenda> buscar(VendaseFilter vendasfilter) {
		return vendarepositrory.buscarVendas(vendasfilter);
	}
}
