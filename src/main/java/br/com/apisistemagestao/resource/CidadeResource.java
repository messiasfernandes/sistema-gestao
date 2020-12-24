package br.com.apisistemagestao.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.apisistemagestao.domain.entity.Cidade;

import br.com.apisistemagestao.serivces.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource implements ResourcePadrao<Cidade> {
	@Autowired
	private CidadeService cidadeservice;

	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(cidadeservice.pesquisar());
	}

	@Override
	public ResponseEntity<Cidade> salvar(Cidade objeto, HttpServletResponse response) {

		return null;
	}

	@GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
		Optional<Cidade> cidade = cidadeservice.buscarporcodigo(codigo);
		if (cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public void excluir(Long codigo) {

	}

	@Override
	public ResponseEntity<Cidade> editar(Cidade objeto) {

		return null;
	}

	@GetMapping("/por-nome")
	public ResponseEntity<Cidade> buscar(String nomecidade) {
		Cidade cidade = cidadeservice.find(nomecidade);
		if (cidade != null) {

			return ResponseEntity.ok(cidade);
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/por-nome-comecando-com")
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {

		return ResponseEntity.status(HttpStatus.OK).body(cidadeservice.buscar(parametro));
	}

	@GetMapping("/por-codigo-ibge")

	public ResponseEntity<?> codigoIbege(String parametro) {
		System.out.println(parametro);
		Optional<Cidade> cidade = cidadeservice.buscarcodigoIbge(parametro);
		if (cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}
		return ResponseEntity.notFound().build();
	}
}
