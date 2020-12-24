package br.com.apisistemagestao.serivces;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Compra;
import br.com.apisistemagestao.domain.entity.ContasPagar;

import br.com.apisistemagestao.domain.entity.Fornecedor;
import br.com.apisistemagestao.domain.entity.Funcionario;
import br.com.apisistemagestao.domain.entity.ItemCompra;
import br.com.apisistemagestao.domain.entity.StatuPagamento;
import br.com.apisistemagestao.domain.entity.StatusCompra;
import br.com.apisistemagestao.domain.repository.CompraRepository;

import br.com.apisistemagestao.domain.repository.ContaaPagarRepositoty;
import br.com.apisistemagestao.domain.repository.FornecedorRepository;
import br.com.apisistemagestao.domain.repository.FuncionarioRepository;
import br.com.apisistemagestao.domain.repository.ProdutoReposistory;
import br.com.apisistemagestao.infra.CompraFilter;
import br.com.apisistemagestao.serivces.exeption.EntidadeNaoEncontradaException;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;
import br.com.apisistemagestao.serivces.exeption.RegistroNaoEncontrado;

@Service
public class CompraService implements ServicePadrao<Compra> {
	@Autowired
	private CompraRepository comprarepository;
	@Autowired
	private ProdutoReposistory produtorepository;
	@Autowired
	private FornecedorRepository fornecerepo;
	@Autowired
	private ContaaPagarRepositoty contarepository;
	@Autowired
	private FuncionarioRepository funcrepo;

	@Transactional
	@Override
	public Compra salvar(Compra objeto) {
		objeto.setDatadeentrada(LocalDateTime.now());

		Optional<Fornecedor> fornecedor = fornecerepo.findById(objeto.getIdfonecedor());

		objeto.setFornecedor(fornecedor.get());


		if ((comprarepository.buscarnota(objeto.getFornecedor().getCodigo(), objeto.getNumeronota()) == true)) {

			throw new NegocioExeption("Nota  cadastrada no banco de dados");

		} else {

			objeto = calculaCustoMedio(objeto);
			for (int posicao = 0; posicao < objeto.getItemcompras().size(); posicao++) {
				objeto.getItemcompras().get(posicao).setCompra(objeto);
			}
			adcionarparcelas(objeto);
			for (int j = 0; j < objeto.getContas().size(); j++) {
				contarepository.save(objeto.getContas().get(j));
			}

			comprarepository.save(objeto);

			for (int pos = 0; pos < objeto.getItemcompras().size(); pos++) {

				objeto.getItemcompras().get(pos).getProduto().setQteestoque(objeto.getItemcompras().get(pos)
						.getProduto().getQteestoque().add(objeto.getItemcompras().get(pos).getQtde()));
				produtorepository.save(objeto.getItemcompras().get(pos).getProduto());

			}
		}

		return objeto;
	}

	private Compra adcionarparcelas(Compra objeto) {

		Funcionario func = funcrepo.findByEmail(objeto.getEmail());
		objeto.setFuncionario(func);
		Integer numparcela = 0;
		for (int pos = 0; pos < objeto.getFornecedor().getCondicaoPamento().getQtdeparcelas(); pos++) {
			ContasPagar contaaPagar = new ContasPagar();
			contaaPagar.setFornecedor(objeto.getFornecedor());
			contaaPagar.setDatalancamento(LocalDate.now());
			numparcela = pos + 1;
			contaaPagar.setFormadePagmamento(
					objeto.getFornecedor().getCondicaoPamento().getColecaoparcelas().get(pos).getFormadePagamento());

			contaaPagar.setNumtitulo(objeto.getNumeronota());
			contaaPagar.setStatuspagmaento(StatuPagamento.PENDENTE);
			contaaPagar.setNumparcela(numparcela);
			contaaPagar.setDatavencimento(objeto.getDataCompra()
					.plusDays(contaaPagar.getFornecedor().getCondicaoPamento().getColecaoparcelas().get(pos).getDia()));
			contaaPagar.setValoparcela(objeto.getTotalCompra().multiply(contaaPagar.getFornecedor().getCondicaoPamento()
					.getColecaoparcelas().get(pos).getPercentual().divide(new BigDecimal(100))));
			contaaPagar.setValorapagar(contaaPagar.getValoparcela());

			objeto.getContas().add(contaaPagar);
		}

		return objeto;	
	}

	public List<ItemCompra> buscacompra(CompraFilter pcomFilter) {

		return comprarepository.buscarCompra(pcomFilter);

	}

	private Compra calculaCustoMedio(Compra objeto) {

		BigDecimal rateiofrete = BigDecimal.ZERO;
		for (int i = 0; i < objeto.getItemcompras().size(); i++) {

			if (objeto.getValorfrete().signum() > 0) {
				rateiofrete = (objeto.getItemcompras().get(i).getSubtotal()
						.divide((objeto.getTotalproduto()), 4, RoundingMode.HALF_EVEN)
						.multiply((objeto.getValorfrete())));

			}
			BigDecimal rateioOutrasdespeza = BigDecimal.ZERO;
			if (objeto.getOutrasdepesza().signum() > 0) {
				rateioOutrasdespeza = (objeto.getItemcompras().get(i).getSubtotal()
						.divide((objeto.getTotalproduto()), 4, RoundingMode.HALF_EVEN)
						.multiply((objeto.getOutrasdepesza())));

			}
			BigDecimal rateioDesconto = BigDecimal.ZERO;
			if (objeto.getDesconto().signum() > 0) {

				rateioDesconto = (objeto.getItemcompras().get(i).getSubtotal()
						.divide((objeto.getTotalproduto()), 4, RoundingMode.HALF_EVEN)
						.multiply((objeto.getDesconto())));

			}
			BigDecimal rateioSeguro = BigDecimal.ZERO;
			if (objeto.getValorseguro().signum() > 0) {
				rateioSeguro = (objeto.getItemcompras().get(i).getSubtotal()
						.divide((objeto.getTotalproduto()), 4, RoundingMode.HALF_EVEN)
						.multiply((objeto.getValorseguro())));
			}
			BigDecimal custoTotalAnterior = BigDecimal.ZERO;
			BigDecimal estqoueanterior = BigDecimal.ZERO;
			BigDecimal subtotaatual = BigDecimal.ZERO;
			objeto.getItemcompras().get(i).setCustototal(objeto.getItemcompras().get(i).getSubtotal().add(rateioSeguro)
					.add(rateioOutrasdespeza).add(rateiofrete).subtract(rateioDesconto));

			custoTotalAnterior = objeto.getItemcompras().get(i).getProduto().getUltiMocusto_pago()
					.multiply(objeto.getItemcompras().get(i).getProduto().getQteestoque());
			estqoueanterior = objeto.getItemcompras().get(i).getProduto().getQteestoque();
			subtotaatual = subtotaatual.add(objeto.getItemcompras().get(i).getCustototal().add(custoTotalAnterior));
			estqoueanterior = estqoueanterior.add(objeto.getItemcompras().get(i).getQtde());

			objeto.getItemcompras().get(i).getProduto()
					.setCustomedio((subtotaatual).divide((estqoueanterior), 2, RoundingMode.HALF_UP));

			objeto.getItemcompras().get(i).getProduto().setPrecodevenda(
					objeto.getItemcompras().get(i).getProduto().getCustomedio().multiply((objeto.getMarkqup())));

		}

		return objeto;

	}

	@Override
	public List<Compra> pesquisar() {

		return comprarepository.buscar();
	}

	@Override
	public Optional<Compra> buscarporcodigo(Long id) {
		Optional<Compra> compra = comprarepository.findById(id);

		if (compra.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Registro não encotrado");
		}
		return compra;
	}

	@Override
	public List<Compra> buscar(String parametro) {

		return comprarepository.buscarComprasporNomeForecedor(parametro);
	}

	public List<Compra> buscarCancelada() {
		return comprarepository.buscarCancelada();
	}
	public List<Compra> buscaporCnpj(String cppj) {

		return comprarepository.buscarCompraporCnpjForncedoor(cppj);
	}

	@Override
	public void excluir(Long codigo) {

	}

	public Compra buscarCompra(Long codigofornecor, Long numeronota) {
		Compra comprapesquisa = null;
		try {
			comprapesquisa = comprarepository.buscarCompra(codigofornecor, numeronota);

		} catch (EmptyResultDataAccessException e) {

			throw new RegistroNaoEncontrado("Compra não pode ser encotrada");
		}
		return comprapesquisa;

	}

	@Transactional
	public Compra cancelarCompra(Compra objeto) {

		Optional<Compra> compraexistente = comprarepository.findById(objeto.getIdCompra());
		Funcionario func = funcrepo.findByEmail(objeto.getEmail());
		objeto.setFuncionario(func);
		
		if (compraexistente.get().getStatusCompra() == StatusCompra.Cancelado) {
			throw new NegocioExeption("Já foi cancelado esta compra ");
		}
		objeto.setStatusCompra(StatusCompra.Cancelado);
		objeto.setDatacancelamento(LocalDate.now());
        objeto.setContas( contarepository.filtro(objeto.getNumeronota(), objeto.getFornecedor().getCpfouCnpj()));

		for (int j=0; j<objeto.getContas().size();j++) {
			objeto.getContas().get(j).setStatuspagmaento(StatuPagamento.CANCELADO);
			contarepository.save(objeto.getContas().get(j));
		}
		for (int i = 0; i < objeto.getItemcompras().size(); i++) {
           
			objeto.getItemcompras().get(i).getProduto().baixarEstoque(objeto.getItemcompras().get(i).getQtde());
			produtorepository.save(objeto.getItemcompras().get(i).getProduto());

		}

		return comprarepository.save(objeto);
	}


}
