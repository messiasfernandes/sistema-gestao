package br.com.apisistemagestao.serivces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Ncms;
import br.com.apisistemagestao.domain.repository.NcmRepository;




@Service
public class NcmService implements ServicePadrao<Ncms> {
	@Autowired
	private NcmRepository reponcms;

	@Override
	public Ncms salvar(Ncms objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ncms> pesquisar() {

		return reponcms.findAll();
	}

	@Override
	public Optional<Ncms> buscarporcodigo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ncms> buscar(String parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	public Ncms buscaCodigoNcm(String paramentro) {
///return reponcms.findByCod_ncm(paramentro);
return reponcms.buscarNcm(paramentro);
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

}
