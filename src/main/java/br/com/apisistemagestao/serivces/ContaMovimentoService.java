package br.com.apisistemagestao.serivces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.ContaCaixa;
import br.com.apisistemagestao.domain.entity.ContaMovimento;
import br.com.apisistemagestao.domain.entity.TipoMovimento;
import br.com.apisistemagestao.domain.repository.ContaCaixaRepository;
import br.com.apisistemagestao.domain.repository.ContaMovimentoRepository;


@Service
public class ContaMovimentoService implements ServicePadrao<ContaMovimento> {
	@Autowired
	private ContaMovimentoRepository contaMoviemtnoRpository;
	@Autowired
	private ContaCaixaRepository contaCaixarepository;

	@Override
	public ContaMovimento salvar(ContaMovimento objeto) {

		return null;
	}

	@Override
	public List<ContaMovimento> pesquisar() {

		return contaMoviemtnoRpository.findAll();
	}

	@Override
	public Optional<ContaMovimento> buscarporcodigo(Long id) {
             Optional<ContaMovimento> contaVovimento =contaMoviemtnoRpository.findById(id);
		return contaVovimento;
	}

	@Override
	public List<ContaMovimento> buscar(String parametro) {

		return null;
	}

	public ContaMovimento moviemtar(BigDecimal valor, ContaMovimento contaMoVimento) {
		BigDecimal aux = BigDecimal.ZERO;

		Optional<ContaCaixa> contaCaixa = contaCaixarepository.findById(contaMoVimento.getConta().getNumeroconta());

		contaMoVimento.setConta(contaCaixa.get());
		aux = aux.add(contaCaixa.get().getTotal());
		if (contaMoVimento.getTipoMovimento().equals(TipoMovimento.Crédito)) {

			contaMoVimento.setCredito((valor));
			contaCaixa.get().setTotal(aux.add(contaMoVimento.getCredito()));

		} else {
			contaMoVimento.setDebito((valor));
			contaCaixa.get().setTotal(aux.subtract(contaMoVimento.getDebito()));
		}
		contaMoVimento.setDatamovimentcao(LocalDate.now());

		contaMoVimento.setSubtotal(contaCaixa.get().getTotal());
		return   contaMoviemtnoRpository.save(contaMoVimento);
	}

	@Override
	public void excluir(Long codigo) {

	}

}
