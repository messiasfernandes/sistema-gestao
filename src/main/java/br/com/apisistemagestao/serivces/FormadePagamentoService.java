package br.com.apisistemagestao.serivces;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.FormadePagmamento;
import br.com.apisistemagestao.domain.repository.FormadePagamentoRepositroy;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;



@Service
public class FormadePagamentoService  implements ServicePadrao<FormadePagmamento>{
	@Autowired
   private FormadePagamentoRepositroy formadepagamentoDao;
	@Transactional
	@Override
	public FormadePagmamento salvar(FormadePagmamento objeto) {
		FormadePagmamento formaexistente = formadepagamentoDao.findByNomeforma(objeto.getNomeforma());

		if (formaexistente != null && !formaexistente.equals(objeto)) {
			throw new NegocioExeption("Forma já cadastrada no banco de dados");

		}
		
		return formadepagamentoDao.save(objeto);
	}

	@Override
	public List<FormadePagmamento> pesquisar() {
		
		return formadepagamentoDao.findAll();
	}

	@Override
	public  Optional< FormadePagmamento> buscarporcodigo(Long id) {
		Optional<FormadePagmamento>formadepagamento = formadepagamentoDao.findById(id);
		return formadepagamento;
	}

	@Override
	public List<FormadePagmamento> buscar(String parametro) {
		
		return formadepagamentoDao.findByNomeformaStartingWith(parametro);
	}


	@Override
	public void excluir(Long codigo) {

		
	}

}
