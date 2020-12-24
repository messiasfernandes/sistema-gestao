package br.com.apisistemagestao.serivces;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Cliente;
import br.com.apisistemagestao.domain.entity.ContaDetalheReceber;
import br.com.apisistemagestao.domain.entity.ContasaReceber;
import br.com.apisistemagestao.domain.entity.FormadePagmamento;
import br.com.apisistemagestao.domain.entity.StatuPagamento;
import br.com.apisistemagestao.domain.repository.ClienteRepository;
import br.com.apisistemagestao.domain.repository.ContaReceberRepository;
import br.com.apisistemagestao.serivces.exeption.EntidadeNaoEncontradaException;




@Service
public class ContaReceberService implements ServicePadrao<ContasaReceber> {
	@Autowired
    private ContaReceberRepository contaReceberRepository;
	@Autowired
	private ClienteRepository clienteRpositroy;
	@Override
	public ContasaReceber salvar(ContasaReceber objeto) {
		  Optional<Cliente>cliente =clienteRpositroy.findById(objeto.getCliente().getCodigo());
			objeto.setCliente(cliente.get());
			FormadePagmamento forma = null;
			
			for (int i = 0; i < cliente.get().getCondicaoPamento().getQtdeparcelas(); i++) {
				forma = new FormadePagmamento();
				forma.setIdformapagamento(cliente.get().getCondicaoPamento().getColecaoparcelas().get(i)
						.getFormadePagamento().getIdformapagamento());
			}
           
			if ((forma.getIdformapagamento() != 1l) && (forma.getIdformapagamento() != 6l)) {
				objeto.getCliente()
				.setLimecredito(objeto.getCliente().getLimecredito().subtract(objeto.getTotalconta()));
			}
			
		
				 int numparcela =0;
		         for(int pos=0; pos < objeto.getCliente().getCondicaoPamento().getQtdeparcelas() ;pos++) {
		        	 numparcela= numparcela+1; 
		        	 ContaDetalheReceber contadetlhe =new ContaDetalheReceber();
		        	 contadetlhe.setNumparcela(numparcela);
		        	 contadetlhe.setContaReceber(objeto);
	      
		        	 contadetlhe.setDatavencimento(objeto.getDatalancamento().
		        			 plusDays( objeto.getCliente().getCondicaoPamento().getColecaoparcelas().get(pos).getDia()));
		        	 contadetlhe.setValoparcela(objeto.getTotalconta().multiply(objeto.getCliente()
		        			 .getCondicaoPamento().getColecaoparcelas().get(pos).getPercentual().divide( new BigDecimal( 100d),4, RoundingMode.FLOOR)));
		        	 System.out.println("valor parcela numero  "+ numparcela + " - "+ contadetlhe.getValoparcela());
		             contadetlhe.setStatuspagmaento(StatuPagamento.PENDENTE);
		             contadetlhe.setValorapagar(contadetlhe.getValoparcela() );
		             contadetlhe.setNumtitulo(objeto.getNumtitulo());
		             contadetlhe.setFormadePagmamento(objeto.getCliente().getCondicaoPamento().getColecaoparcelas().get(pos).getFormadePagamento());
		             objeto.getContasdetalhe().add(contadetlhe);
		         	
		         }
				return contaReceberRepository.save(objeto);
	}
	@Override
	public List<ContasaReceber> pesquisar() {
	
		return contaReceberRepository.findAll();
	}
	@Override
	public Optional<ContasaReceber> buscarporcodigo(Long id) {
	     Optional<ContasaReceber> contaOptinal= contaReceberRepository.findById(id);
	     if (contaOptinal.isEmpty()) {
           throw new EntidadeNaoEncontradaException("Registro não encontrado");    	 
	     }
	 	return contaOptinal;
	}
	@Override
	public List<ContasaReceber> buscar(String parametro) {
	
		return null;
	}
	@Override
	public void excluir(Long codigo) {
	
		
	}
	
}
