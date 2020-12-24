package br.com.apisistemagestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Funcionario;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
   Funcionario findByEmail(String email);
}
