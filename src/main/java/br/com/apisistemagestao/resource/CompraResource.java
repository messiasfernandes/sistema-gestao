package br.com.apisistemagestao.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apisistemagestao.domain.entity.Compra;
import br.com.apisistemagestao.domain.entity.ItemCompra;
import br.com.apisistemagestao.dto.CompraDTO;
import br.com.apisistemagestao.dto.CompraInputDTO;
import br.com.apisistemagestao.dto.CompraItensDto;
import br.com.apisistemagestao.infra.CompraFilter;
import br.com.apisistemagestao.serivces.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraResource implements ResourcePadrao<Compra> {
	@Autowired
	private CompraService compraservice;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel((compraservice.pesquisar())));
	}

	@PostMapping
///	@ResponseStatus(HttpStatus.CREATED)
	@Override
	public ResponseEntity<?> salvar(@Valid @RequestBody Compra objeto, HttpServletResponse response) {

		Compra comprasalva = compraservice.salvar(objeto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idCompra}")
				.buildAndExpand(comprasalva.getIdCompra()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(toModel(comprasalva));
	}

	@GetMapping("/{idCompra}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long idCompra) {
		Optional<Compra> compra = compraservice.buscarporcodigo(idCompra);

		if (

		compra.isPresent()) {

			return ResponseEntity.ok(toModel(compra.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/por-itens")
	public ResponseEntity<List<?>> buccarcompra(CompraFilter compfiter) {
		System.out.println("NOta fiscal " + compfiter.getNumeronta());
		CacheControl cacheControl = CacheControl.maxAge(2l, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(toCollectionModel2(compraservice.buscacompra(compfiter)));
	}

	@Override
	public void excluir(Long codigo) {

	}

	@PutMapping("{idCompra}")
	public ResponseEntity<CompraDTO> cancelarCompra( @PathVariable Long idCompra) {
		Optional<Compra> compra = compraservice.buscarporcodigo(idCompra);

		Compra compracanecelada = compraservice.cancelarCompra(compra.get());
		return ResponseEntity.ok(toModel(compracanecelada));
	}

	@Override
	public ResponseEntity<Compra> editar(@Valid @RequestBody Compra objeto) {
		compraservice.cancelarCompra(objeto);
		return ResponseEntity.ok(objeto);
	}

	@GetMapping("/buscar-por-nome")
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {
		CacheControl cacheControl = CacheControl.maxAge(2l, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(toCollectionModel(compraservice.buscar(parametro)));
	}

	@GetMapping("/buscar-por-canceladas")
	public ResponseEntity<List<?>> buscarcompraCancelada() {
		CacheControl cacheControl = CacheControl.maxAge(2l, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(toCollectionModel(compraservice.buscarCancelada()));
	}
	@GetMapping("/buscar-por-cnpj")
	public ResponseEntity<List<?>> buscaporCnpj(String cnpj) {
		CacheControl cacheControl = CacheControl.maxAge(2l, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(toCollectionModel(compraservice.buscaporCnpj(cnpj)));
	}
	@GetMapping("/buscar-compra")
	public ResponseEntity<CompraDTO> buscarcompra(Long codigofornecedor, Long numeronota) {
		Compra compra = compraservice.buscarCompra(codigofornecedor, numeronota);
		return ResponseEntity.ok(toModel(compra));
	}

	private List<CompraDTO> toCollectionModel(List<Compra> compras) {
		return compras.stream().map(compra -> toModel(compra)).collect(Collectors.toList());
	}

	private CompraDTO toModel(Compra compra) {
		return modelMapper.map(compra, CompraDTO.class);
	}

	private List<CompraItensDto> toCollectionModel2(List<ItemCompra> compras) {
		return compras.stream().map(compra -> toModel2(compra)).collect(Collectors.toList());
	}

	private CompraItensDto toModel2(ItemCompra compra) {
		return modelMapper.map(compra, CompraItensDto.class);
	}

	private Compra toEntity(CompraInputDTO compraInput) {
		return modelMapper.map(compraInput, Compra.class);
	}
}
