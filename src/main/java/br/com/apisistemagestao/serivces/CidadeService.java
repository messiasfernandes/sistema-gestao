package br.com.apisistemagestao.serivces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Cidade;
import br.com.apisistemagestao.domain.repository.CidadeRepository;




@Service
public class CidadeService implements ServicePadrao<Cidade> {
	@Autowired
    private CidadeRepository cidaddedao;
	@Override
	public Cidade salvar(Cidade objeto) {
		
		return null;
	}

	@Override
	public List<Cidade> pesquisar() {
		
		return cidaddedao.listartodas();
	}

	@Override
	public Optional< Cidade> buscarporcodigo(Long id) {
	Optional<Cidade>cidade= cidaddedao.findById(id);
		return cidade;
	}
    public Optional<Cidade>buscarcodigoIbge(String codigo){
    	Optional<Cidade>cidade =cidaddedao.findByCodigoibge(codigo);
    	return cidade;
    }
	@Override
	public List<Cidade> buscar(String parametro) {
		
		return cidaddedao.findByCidadenomeStartingWith(parametro);
	}

	 public Cidade find(String cidade) {
		 return cidaddedao.findByCidadenome(cidade);
   
    }

	@Override
	public void excluir(Long codigo) {
	
		
	}

}
