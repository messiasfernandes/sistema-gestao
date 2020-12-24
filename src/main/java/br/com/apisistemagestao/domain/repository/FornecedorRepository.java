package br.com.apisistemagestao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Fornecedor;



@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	Fornecedor findByCpfouCnpj(String cpfouCnpj);
	List <Fornecedor> findByNomePessoaStartingWith(String nomePessoa);

}
