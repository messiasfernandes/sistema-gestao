package br.com.apisistemagestao.serivces;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Fornecedor;

import br.com.apisistemagestao.domain.repository.FornecedorRepository;
import br.com.apisistemagestao.serivces.exeption.EntidadeNaoEncontradaException;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;

@Service
public class FornecedorService implements ServicePadrao<Fornecedor> {
	@Autowired
	private FornecedorRepository fornecedordao;

	@Transactional
	@Override
	public Fornecedor salvar(Fornecedor objeto) {

		Fornecedor fonecedorexistente = fornecedordao.findByCpfouCnpj(objeto.getCpfouCnpj());

		if (fonecedorexistente != null && !fonecedorexistente.equals(objeto)) {
			throw new NegocioExeption("Já existe cpf ou cnpj  cadastrada no banco de daods");
		}

		return fornecedordao.save(objeto);
	}



	@Override
	public List<Fornecedor> pesquisar() {

		return fornecedordao.findAll();
	}

	@Override
	public Optional<Fornecedor> buscarporcodigo(Long id) {
		Optional<Fornecedor> fornecedor = fornecedordao.findById(id);
		if (fornecedor.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Registro não encotrado");
		}
		return fornecedor;
	}

	@Override
	public List<Fornecedor> buscar(String parametro) {

		return fornecedordao.findByNomePessoaStartingWith(parametro);
	}

	public Fornecedor bucar(Fornecedor fornecedor) {
		return fornecedordao.findByCpfouCnpj(fornecedor.getCpfouCnpj());
	}

	@Override
	public void excluir(Long codigo) {
		try {
			fornecedordao.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioExeption("Forncedor não pode ser encotrado");
		}

	}

	public Fornecedor buscarcnpj(String conpj) {
		return fornecedordao.findByCpfouCnpj(conpj);
	}

}
