package br.com.apisistemagestao.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apisistemagestao.domain.entity.Categoria;
import br.com.apisistemagestao.serivces.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResouce implements ResourcePadrao<Categoria> {
	@Autowired
	CategoriaService categoriaservice;
   /// @CrossOrigin
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(categoriaservice.pesquisar());
	}

	@PostMapping
	@Override
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria objeto, HttpServletResponse response) {

		Categoria categoriasalva = categoriaservice.salvar(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoriasalva.getIdCategoria()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(categoriasalva);
	}

	@GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
		System.out.println(codigo);
		Optional<Categoria> categoria = categoriaservice.buscarporcodigo(codigo);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		}
		return ResponseEntity.notFound().build();

	}

	@GetMapping("/por-nome-comecando")
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {
     System.out.println(parametro);
		return ResponseEntity.status(HttpStatus.OK).body(categoriaservice.buscar(parametro));
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public void excluir(@PathVariable Long codigo) {

		categoriaservice.excluir(codigo);
	}

	@PutMapping
	@Override
	public ResponseEntity<Categoria> editar(@Valid @RequestBody Categoria objeto) {
              categoriaservice.salvar(objeto);
		return ResponseEntity.ok(objeto);
	}

	

}
