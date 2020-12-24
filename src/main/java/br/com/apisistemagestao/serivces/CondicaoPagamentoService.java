package br.com.apisistemagestao.serivces;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.CondicaoPagamento;
import br.com.apisistemagestao.domain.entity.Parcelas;
import br.com.apisistemagestao.domain.repository.CondicaoPagamentoRepository;
import br.com.apisistemagestao.infra.CondicaoFilter;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;




@Service
public class CondicaoPagamentoService implements ServicePadrao<CondicaoPagamento> {
	@Autowired
	private CondicaoPagamentoRepository condicaoPagmentodao;

	@Transactional
	@Override
	public CondicaoPagamento salvar(CondicaoPagamento objeto) {
   CondicaoPagamento condicao = new CondicaoPagamento();
   if (objeto.getIdcondicao()>0){
	   condicao.setIdcondicao(objeto.getIdcondicao()); 
   }
 
   condicao.setDescricao(objeto.getDescricao());
   condicao.setQtdeparcelas(objeto.getQtdeparcelas());				
		for (int i=0; i<condicao.getQtdeparcelas() ;i++) {
			 Parcelas parcela = new Parcelas();
			 parcela.setDia(objeto.getColecaoparcelas().get(i).getDia());
			 parcela.setFormadePagamento(objeto.getColecaoparcelas().get(i).getFormadePagamento());
			 parcela.setNumeroparcela(objeto.getColecaoparcelas().get(i).getNumeroparcela());
			 parcela.setPercentual(objeto.getColecaoparcelas().get(i).getPercentual());
			 parcela.setCondicaopagamento(condicao);
		     condicao.getColecaoparcelas().add(parcela);
	}
      
        
		return condicaoPagmentodao.save(condicao);
	}

	@Override
	public List<CondicaoPagamento> pesquisar() {

		return condicaoPagmentodao.findAll();
	}

	@Override
	public Optional<CondicaoPagamento> buscarporcodigo(Long id) {
		Optional<CondicaoPagamento> condicaopagamento = condicaoPagmentodao.findById(id);
		return condicaopagamento;
	}

	public List<Parcelas> buscarcondicao(CondicaoFilter condicao) {

		return condicaoPagmentodao.buscar(condicao);

	}

	@Override
	public List<CondicaoPagamento> buscar(String parametro) {

		return condicaoPagmentodao.findByDescricaoStartingWith(parametro);
	}

	@Override
	public void excluir(Long codigo) {
		System.out.println("codigo"+codigo);
		try {
			
			condicaoPagmentodao.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioExeption("Condição não encontrada");

		}

	}

}
