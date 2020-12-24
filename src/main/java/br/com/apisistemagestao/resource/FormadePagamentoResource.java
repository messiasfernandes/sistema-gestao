package br.com.apisistemagestao.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apisistemagestao.domain.entity.FormadePagmamento;
import br.com.apisistemagestao.serivces.FormadePagamentoService;


@RestController
@RequestMapping(value = "/formas-de-pagamento")
public class FormadePagamentoResource implements ResourcePadrao<FormadePagmamento> {
	@Autowired
    private FormadePagamentoService formadepagamentoService;
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {
	
		return ResponseEntity.status(HttpStatus.OK).body(formadepagamentoService.pesquisar());
	}
    @PostMapping
	@Override
	public ResponseEntity<FormadePagmamento> salvar(@Valid @RequestBody FormadePagmamento objeto, HttpServletResponse response) {
    	FormadePagmamento formadePagmamento = formadepagamentoService.salvar(objeto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{codigo}")
				.buildAndExpand(formadePagmamento.getIdformapagamento()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(formadePagmamento);
	}
    @GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo (@PathVariable Long codigo) {
		Optional<FormadePagmamento> formapagamento = formadepagamentoService.buscarporcodigo(codigo);
		if (formapagamento.isPresent()) {
			CacheControl cacheControl = CacheControl.maxAge(3l, TimeUnit.MINUTES);
			return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body((formapagamento.get()));
		}
		return ResponseEntity.notFound().build();
	}
	@GetMapping("/por-nome-comecando")
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {
		
		return ResponseEntity.status(HttpStatus.OK).body(formadepagamentoService.buscar(parametro));
	}

	@Override
	public void excluir(Long codigo) {
	

	}

	@Override
	public ResponseEntity<FormadePagmamento> editar(FormadePagmamento objeto) {
	
		return null;
	}

}
