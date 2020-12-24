package br.com.apisistemagestao.serivces;

import java.util.Collection;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.GrantedAuthority;

import br.com.apisistemagestao.domain.entity.Usuario;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		
		super(usuario.getFuncionario().getEmail(), usuario.getSenha(), authorities);
		System.out.println(usuario.getCodigo() + "- " + usuario.getFuncionario().getNomePessoa() +"-"+ usuario.getFuncionario().getEmail());
		this.usuario = usuario;
		System.out.println(   usuario.getFuncionario().getNomePessoa());
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
