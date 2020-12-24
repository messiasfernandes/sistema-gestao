package br.com.apisistemagestao.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apisistemagestao.domain.entity.Banco;
import br.com.apisistemagestao.serivces.BancoService;



@RequestMapping("/bancos")
@RestController
public class BancoResource implements ResourcePadrao<Banco> {
	@Autowired
    private BancoService bancoservice;
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_BANCO')")
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {
	System.out.println("pasou aqui");
		return ResponseEntity.status(HttpStatus.OK).body(bancoservice.pesquisar());
	}
	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_BANCO')")
   @PostMapping
	@Override
	public ResponseEntity<Banco> salvar(@Valid @RequestBody Banco objeto, HttpServletResponse response) {
	   Banco bancosalvo = bancoservice.salvar(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(bancosalvo.getCodigobanco()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(bancosalvo);
	}
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_BANCO')")
    @GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
    	Optional<Banco> banco = bancoservice.buscarporcodigo(codigo);
		if (banco.isPresent()) {
			return ResponseEntity.ok(banco.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {
		
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		

	}

	@Override
	public ResponseEntity<Banco> editar(Banco objeto) {
		
		return null;
	}

}
