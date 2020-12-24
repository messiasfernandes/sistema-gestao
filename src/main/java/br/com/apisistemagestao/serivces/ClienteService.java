package br.com.apisistemagestao.serivces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Cliente;
import br.com.apisistemagestao.domain.repository.ClienteRepository;
import br.com.apisistemagestao.serivces.exeption.EntidadeNaoEncontradaException;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;



@Service
public class ClienteService implements ServicePadrao<Cliente> {
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente salvar(Cliente objeto) {

		return clienteRepository.save(objeto);
	}

	@Override
	public List<Cliente> pesquisar() {

		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> buscarporcodigo(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Registro não encotrado");
		}

		return cliente;
	}

	@Override
	public List<Cliente> buscar(String parametro) {

		return clienteRepository.findByNomePessoaStartingWith(parametro);
	}

	public Cliente bucar(Cliente cliente) {
		return clienteRepository.findByCpfouCnpj(cliente.getCpfouCnpj());
	}

	@Override
	public void excluir(Long codigo) {
		try {
			clienteRepository.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioExeption("Registro não foi encotrado");
		}

	}

}
