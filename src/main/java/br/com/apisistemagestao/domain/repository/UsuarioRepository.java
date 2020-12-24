package br.com.apisistemagestao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisistemagestao.domain.entity.Usuario;
import br.com.apisistemagestao.infra.UsuarioRepositoryQuery;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>,UsuarioRepositoryQuery{

   
}
