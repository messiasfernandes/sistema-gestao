package br.com.apisistemagestao.serivces;



import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Usuario;
import br.com.apisistemagestao.domain.repository.UsuarioRepository;
@Service
public class UserDestailServiceImpl implements UserDetailsService {
	@Autowired
    private UsuarioRepository usuarioREpository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("passou aqui" + email);
	Usuario	 usuario = usuarioREpository.buscarEmail(email);
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		   System.out.println("Email do funcionario "+ usuario.getFuncionario().getEmail());
	   System.out.println("Id do usuario "+ usuario.getCodigo());
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
     	System.out.println(	authorities.size());
		return authorities;
	}
	


}
