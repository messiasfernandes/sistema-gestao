package br.com.apisistemagestao.serivces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Banco;
import br.com.apisistemagestao.domain.repository.BancoRepository;
import br.com.apisistemagestao.serivces.exeption.EntidadeNaoEncontradaException;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;

@Service
public class BancoService implements ServicePadrao<Banco> {
	@Autowired
	private BancoRepository bancoReposittory;

	@Override
	public Banco salvar(Banco objeto) {

		return bancoReposittory.save(objeto);
	}

	@Override
	public List<Banco> pesquisar() {

		return bancoReposittory.findAll();
	}

	@Override
	public Optional<Banco> buscarporcodigo(Long id) {
		Optional<Banco> banco = bancoReposittory.findById(id);
		if (banco.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Registo não encontrado");
		}
		return banco;
	}

	@Override
	public List<Banco> buscar(String parametro) {

		return null;
	}

	@Override
	public void excluir(Long codigo) {
		try {
			bancoReposittory.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioExeption("Baanco não pode ser encotrado");
		}

	}

}
