package br.com.apisistemagestao.serivces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Cliente;
import br.com.apisistemagestao.domain.entity.ContaCaixa;
import br.com.apisistemagestao.domain.entity.ContaDetalheReceber;
import br.com.apisistemagestao.domain.entity.ContaMovimento;
import br.com.apisistemagestao.domain.entity.FormadePagmamento;
import br.com.apisistemagestao.domain.entity.StatuPagamento;
import br.com.apisistemagestao.domain.repository.ClienteRepository;
import br.com.apisistemagestao.domain.repository.ContaCaixaRepository;
import br.com.apisistemagestao.domain.repository.ContaMovimentoRepository;
import br.com.apisistemagestao.domain.repository.ContaReceberDetalheRepository;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;



@Service
public class ContaDetalheReceberService implements ServicePadrao<ContaDetalheReceber> {
	@Autowired
	private ContaCaixaRepository contarepository;
	@Autowired
	private ClienteRepository clienterepository;
	@Autowired
	private ContaMovimentoRepository contamovimentorepository;
	@Autowired
	private ContaReceberDetalheRepository contadetalheRepository;

	@Override
	public ContaDetalheReceber salvar(ContaDetalheReceber objeto) {

		return null;
	}

	@Override
	public List<ContaDetalheReceber> pesquisar() {

		return contadetalheRepository.listar();
	}

	@Override
	public Optional<ContaDetalheReceber> buscarporcodigo(Long id) {

		return contadetalheRepository.findById(id);
	}

	@Override
	public List<ContaDetalheReceber> buscar(String parametro) {

		return null;
	}

	public List<ContaDetalheReceber> listarAtrasado() {
		return contadetalheRepository.listarAtrasdos();
	}

	@Override
	public void excluir(Long codigo) {

	}

	public ContaDetalheReceber darbaixaparcela(BigDecimal valor, ContaDetalheReceber objeto) {
		FormadePagmamento forma =null;
		if (objeto.getStatuspagmaento().equals(StatuPagamento.QUITADO)
				|| (objeto.getStatuspagmaento().equals(StatuPagamento.CANCELADO))) {
			
			throw new NegocioExeption("Não pode baixar uma conta quitada ou cancelada");
		}

		BigDecimal aux = BigDecimal.ZERO;
		if (valor.equals(objeto.getValorapagar())) {

			objeto.setStatuspagmaento(StatuPagamento.QUITADO);

		} else {

			objeto.setStatuspagmaento(StatuPagamento.PAGOPARCIAL);
		}
		if (valor.compareTo(objeto.getValorapagar()) > 0) {
			throw new NegocioExeption("Valor pago não pode  ser maior que valor a pagar");
		}
		
		objeto.setValoprago(objeto.getValoprago().add(valor));
		objeto.setValorapagar(objeto.getValorapagar().subtract(valor));

		objeto.setDataDePagamento(LocalDate.now());
		Optional<ContaCaixa> conta = contarepository.findById(objeto.getIdcocontaMovimento());

		ContaMovimento contamovimento = new ContaMovimento();
		contamovimento.setCredito(valor);
		contamovimento.setDocumento(objeto.getNumtitulo());

		contamovimento.setConta(conta.get());
		contamovimento.setDatamovimentcao(LocalDate.now());
		aux = aux.add(conta.get().getTotal());
		conta.get().setTotal(aux.add(contamovimento.getCredito()));
		contamovimento.setSubtotal(conta.get().getTotal());
		Optional<Cliente> cliente = clienterepository.findById(objeto.getContaReceber().getCliente().getCodigo());
		for(int i=0; i <cliente.get().getCondicaoPamento().getQtdeparcelas();i++) {
			forma = new FormadePagmamento();
			forma.setIdformapagamento( cliente.get().getCondicaoPamento().getColecaoparcelas().get(i).getFormadePagamento().getIdformapagamento());
		}
		if ((forma.getIdformapagamento() != 1l)
				&& (forma.getIdformapagamento() != 5)) {

		System.out.println("passou aqui");
			cliente.get().setLimecredito(cliente.get().getLimecredito().add(valor));
			clienterepository.save(cliente.get());
		}

		contamovimentorepository.save(contamovimento);

		contadetalheRepository.save(objeto);
		return objeto;
	}

}
