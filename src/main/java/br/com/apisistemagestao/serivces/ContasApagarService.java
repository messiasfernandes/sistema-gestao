package br.com.apisistemagestao.serivces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import br.com.apisistemagestao.domain.entity.ContaCaixa;
import br.com.apisistemagestao.domain.entity.ContaDetalheReceber;
import br.com.apisistemagestao.domain.entity.ContaMovimento;
import br.com.apisistemagestao.domain.entity.ContasPagar;
import br.com.apisistemagestao.domain.entity.Fornecedor;
import br.com.apisistemagestao.domain.entity.StatuPagamento;
import br.com.apisistemagestao.domain.repository.ContaMovimentoRepository;
import br.com.apisistemagestao.domain.repository.ContaRepository;
import br.com.apisistemagestao.domain.repository.ContaaPagarRepositoty;
import br.com.apisistemagestao.domain.repository.FornecedorRepository;
import br.com.apisistemagestao.infra.ContasFilter;
import br.com.apisistemagestao.serivces.exeption.EntidadeNaoEncontradaException;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;

@Service
public class ContasApagarService implements ServicePadrao<ContasPagar> {
	@Autowired
	private ContaaPagarRepositoty conttapagardao;
	@Autowired
	private ContaRepository contarepository;
	@Autowired
	private ContaMovimentoRepository contamovimentorepository;
	@Autowired
	private FornecedorRepository forncedorrepo;

	@Override
	public ContasPagar salvar(ContasPagar objeto) {
//		Optional<Fornecedor> fornecedoOp = forncedorrepo.findById(objeto.getFornecedor().getCodigo());
//		objeto.setFornecedor(fornecedoOp.get());
//		List<ContasPagar> contadetalhe = new ArrayList<ContasPagar>();
//		int numparcela = 0;
//		for (int pos = 0; pos < objeto.getFornecedor().getCondicaoPamento().getQtdeparcelas(); pos++) {
//			numparcela = numparcela + 1;
//			ContasPagar contadetlhe = new ContasPagar();
//		///	contadetlhe.setContasPagar(objeto);
//			contadetlhe.setDatavencimento(objeto.getDatalancamento()
//					.plusDays(objeto.getFornecedor().getCondicaoPamento().getColecaoparcelas().get(pos).getDia()));
//			contadetlhe.setValoparcela(objeto.getTotalconta().multiply(objeto.getFornecedor().getCondicaoPamento()
//					.getColecaoparcelas().get(pos).getPercentual().divide(new BigDecimal(100d))));
//			contadetlhe.setStatuspagmaento(StatuPagamento.PENDENTE);
//			contadetlhe.setValorapagar(contadetlhe.getValoparcela());
//			contadetlhe.setNumtitulo(objeto.getNumtitulo());
//			contadetlhe.setFormadePagmamento(
//					objeto.getFornecedor().getCondicaoPamento().getColecaoparcelas().get(pos).getFormadePagamento());
//			objeto.getContasdetalhe().add(contadetlhe);
//
//		}
		return  null  ;//conttapagardao.save(objeto);
	}
  public List<ContasPagar>addeslavar(List<ContasPagar> obListapagar){
	  return null;
		///	  conttapagardao.saveAll(Arrays.asList(obListapagar));
  }
	public List<ContasPagar>filtro(Long numtitulo, String pcnpj){
		return conttapagardao.filtro(numtitulo, pcnpj);
	}
	@Override
	public List<ContasPagar> pesquisar() {

		return  conttapagardao.consultar();
	}

	@Override
	public Optional<ContasPagar> buscarporcodigo(Long id) {
		Optional<ContasPagar> contas = conttapagardao.findById(id);
		if (contas.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Registro não Encontrado");

		}
		return contas;
	}

	@Override
	public List<ContasPagar> buscar(String parametro) {

		return conttapagardao.buscarPorNome(parametro);
	}

	public List<ContasPagar> buscarportitulo(Long ptitulo) {
		return null;
	}

	public List<ContasPagar> filtar(ContasFilter contafilter) {
		return  conttapagardao.filtrar(contafilter);

	}
    public List<ContasPagar>buscarCancelado(){
    	return conttapagardao.buscarCancelado();
    }
	@Override
	public void excluir(Long codigo) {
	

	}
	public List<ContasPagar>listarAtrasado(){
		return conttapagardao.listarAtrasasdas();
	}
	public ContasPagar darbaixaConta( ContasPagar contasPagardetalhe) {

		if (contasPagardetalhe.getStatuspagmaento().equals(StatuPagamento.QUITADO)
				|| (contasPagardetalhe.getStatuspagmaento().equals(StatuPagamento.CANCELADO))) {
			
			throw new NegocioExeption("Não pode baixar uma conta quitada ou cancelada");
		}
		BigDecimal aux = BigDecimal.ZERO;

		if (contasPagardetalhe.getValoprago().equals(contasPagardetalhe.getValorapagar())) {

			System.out.println(contasPagardetalhe.getValorapagar());
			contasPagardetalhe.setStatuspagmaento(StatuPagamento.QUITADO);

		} else {

			contasPagardetalhe.setStatuspagmaento(StatuPagamento.PAGOPARCIAL);
		}
		
		if (contasPagardetalhe.getValoprago().compareTo(contasPagardetalhe.getValorapagar()) > 0) {
			throw new NegocioExeption("Valor pago não pode  ser maior que valor a pagar");
		}

		contasPagardetalhe.setValorapagar(((contasPagardetalhe.getValorapagar().subtract(contasPagardetalhe.getValoprago()))));
		contasPagardetalhe.setDataDePagamento(LocalDate.now());
		Optional<ContaCaixa> conta = contarepository.findById(contasPagardetalhe.getIdcocontaMovimento());

		ContaMovimento contamovimento = new ContaMovimento();

		contamovimento.setDebito(contasPagardetalhe.getValoprago());

		contamovimento.setDocumento(contasPagardetalhe.getNumtitulo());

		contamovimento.setConta(conta.get());
		contamovimento.setDatamovimentcao(LocalDate.now());
		aux = aux.add(conta.get().getTotal());

		conta.get().setTotal(aux.subtract(contamovimento.getDebito()));
		contamovimento.setSubtotal(conta.get().getTotal());
		/// verificar quntas parcela pagas

		contamovimentorepository.save(contamovimento);
		return conttapagardao.save(contasPagardetalhe);
	}
}
