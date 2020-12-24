package br.com.apisistemagestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Permissao;
@Repository
public interface PermissoesRpository extends JpaRepository<Permissao, Long> {

}
