package br.com.apisistemagestao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Cliente findByCpfouCnpj(String cpfouCnpj);
	List <Cliente> findByNomePessoaStartingWith(String nomePessoa);
}
