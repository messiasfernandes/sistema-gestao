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

import br.com.apisistemagestao.domain.entity.Produto;
import br.com.apisistemagestao.serivces.ProdutoService;


@RestController
@RequestMapping("/produtos")
public class ProdutoResourse implements ResourcePadrao<Produto> {
	@Autowired
	private ProdutoService produtoService;
	@PutMapping("/converter")
    public ResponseEntity< Produto> converter(@Valid @RequestBody Produto objeto, HttpServletResponse response) {
    	Produto produtoconvetido = produtoService.converter(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(produtoconvetido.getIdproduto()).toUri();
		response.setHeader("Location", uri.toASCIIString());
    	return ResponseEntity.created(uri).body(produtoconvetido);
		
	}
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(produtoService.pesquisar());
	}
	@GetMapping("/que-tenha")

	public ResponseEntity<List<?>> possui(String parametro) {

		return ResponseEntity.status(HttpStatus.OK).body(produtoService.quetenha(parametro));
	}
    @GetMapping("/buscar-por-codigoean")
	public ResponseEntity<List<?>> buscarCodigoEan( String codigoEan) {
    	System.out.println(codigoEan);
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarporEan(codigoEan));
	}

	@PostMapping
	@Override
	public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto objeto, HttpServletResponse response) {
		Produto produtosalvo = produtoService.salvar(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(produtosalvo.getIdproduto()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(produtosalvo);
	}

	@GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
		Optional<Produto> produto = produtoService.buscarporcodigo(codigo);
		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}

		return ResponseEntity.notFound().build();
	}
	@GetMapping("/codigo-fabricante")

	public ResponseEntity<?> buccodigoFabricante( String codigofabricante) {
		Produto produto = produtoService.buscarcodFonecedor(codigofabricante);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		}

		return ResponseEntity.notFound().build();
	}
	@GetMapping("/buscar-nome-comecando")
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {

		return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscar(parametro));
	}
	@GetMapping("/por-categorias")
	
	public ResponseEntity<List<?>> porCategoria (String parametro) {

		return ResponseEntity.status(HttpStatus.OK).body(produtoService.produtoporCategroira(parametro));
	}
    
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public void excluir(@PathVariable Long codigo) {

		produtoService.excluir(codigo);
	}

	@PutMapping
	@Override
	public ResponseEntity<Produto> editar(@Valid @RequestBody Produto objeto) {
		produtoService.salvar(objeto);

		return ResponseEntity.ok(objeto);
	}

}
