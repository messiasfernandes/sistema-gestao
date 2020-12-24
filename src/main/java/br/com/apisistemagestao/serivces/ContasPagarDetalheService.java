package br.com.apisistemagestao.serivces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.ContaCaixa;
import br.com.apisistemagestao.domain.entity.ContaMovimento;

import br.com.apisistemagestao.domain.entity.ContasPagarDetalhe;

import br.com.apisistemagestao.domain.entity.StatuPagamento;
import br.com.apisistemagestao.domain.repository.ContaMovimentoRepository;
import br.com.apisistemagestao.domain.repository.ContaRepository;
import br.com.apisistemagestao.domain.repository.ContaaPagarRepositoty;
import br.com.apisistemagestao.domain.repository.ContasapagarDetalheRepositorry;
import br.com.apisistemagestao.infra.ContasFilter;
import br.com.apisistemagestao.serivces.exeption.EntidadeNaoEncontradaException;



@Service
public class ContasPagarDetalheService implements ServicePadrao<ContasPagarDetalhe> {
	@Autowired
	private ContasapagarDetalheRepositorry contasdetalherepository;
	@Autowired
	private ContaRepository contarepository;
	@Autowired
	private ContaaPagarRepositoty conttapagardao;
	@Autowired
	private ContaMovimentoRepository contamovimentorepository;

	@Override
	public ContasPagarDetalhe salvar(ContasPagarDetalhe objeto) {

		return null;
	}

	@Override
	public List<ContasPagarDetalhe> pesquisar() {

		return contasdetalherepository.findAll();
	}

	@Override
	public Optional<ContasPagarDetalhe> buscarporcodigo(Long id) {
		Optional<ContasPagarDetalhe> contaDetalhe = contasdetalherepository.findById(id);
		if (contaDetalhe.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Registro não encotrado");
		}
		return contaDetalhe;
	}

	@Override
	public List<ContasPagarDetalhe> buscar(String parametro) {

		return null;
	}

	@Override
	public void excluir(Long codigo) {

	}

	public ContasPagarDetalhe darbaixaConta(BigDecimal valor, ContasPagarDetalhe contasPagardetalhe) {
	
		BigDecimal aux = BigDecimal.ZERO;
 
		if (valor.equals(contasPagardetalhe.getValorapagar())) {

			System.out.println(contasPagardetalhe.getValorapagar());
			contasPagardetalhe.setStatuspagmaento(StatuPagamento.QUITADO);

		} else {

			contasPagardetalhe.setStatuspagmaento(StatuPagamento.PAGOPARCIAL);
		}
		contasPagardetalhe.setValoprago(contasPagardetalhe.getValoprago().add(valor));
		contasPagardetalhe.setValorapagar(((contasPagardetalhe.getValorapagar().subtract(valor))));
		contasPagardetalhe.setDataDePagamento(LocalDate.now());
		Optional<ContaCaixa> conta = contarepository.findById(contasPagardetalhe.getIdcocontaMovimento());

		ContaMovimento contamovimento = new ContaMovimento();

		contamovimento.setDebito(valor);

		contamovimento.setDocumento(contasPagardetalhe.getNumtitulo());

		contamovimento.setConta(conta.get());
		contamovimento.setDatamovimentcao(LocalDate.now());
		aux = aux.add(conta.get().getTotal());

		conta.get().setTotal(aux.subtract(contamovimento.getDebito()));
		contamovimento.setSubtotal(conta.get().getTotal());
		/// verificar quntas parcela pagas

		contamovimentorepository.save(contamovimento);
		return contasdetalherepository.save(contasPagardetalhe);
	}

	public List<ContasPagarDetalhe> filtrar(ContasFilter contafilter) {
		return contasdetalherepository.filtro(contafilter);

	}
}