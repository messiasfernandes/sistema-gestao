package br.com.apisistemagestao.infra;



import br.com.apisistemagestao.domain.entity.Usuario;

public interface UsuarioRepositoryQuery {
   Usuario buscarEmail(String email);
}
