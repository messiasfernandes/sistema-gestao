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
import org.springframework.security.access.prepost.PreAuthorize;

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


import br.com.apisistemagestao.domain.entity.Fornecedor;
import br.com.apisistemagestao.dto.FornecedorDTO;

import br.com.apisistemagestao.serivces.FornecedorService;

///@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorResource implements ResourcePadrao<Fornecedor> {
	@Autowired
	private FornecedorService forncecedorservice;
	@Autowired
	private ModelMapper modelMapper;

	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_FORNECEDOR')")
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(forncecedorservice.pesquisar()));
	}

	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_FORNECEDOR')")
	@PostMapping
	@Override
	public ResponseEntity<?> salvar(@Valid @RequestBody Fornecedor objeto, HttpServletResponse response) {
		Fornecedor fornecedor = forncecedorservice.salvar(objeto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{codigo}")
				.buildAndExpand(fornecedor.getCodigo()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(toModel((fornecedor)));
	}

	public ResponseEntity<FornecedorDTO> aidicionar(@Valid @RequestBody Fornecedor objeto,
			HttpServletResponse response) {
		Fornecedor fornecedor = forncecedorservice.salvar(objeto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{codigo}")
				.buildAndExpand(fornecedor.getCodigo()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(toModel(fornecedor));
	}

	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_FORNECEDOR')")
	@GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
		Optional<Fornecedor> fornecedor = forncecedorservice.buscarporcodigo(codigo);
		if (fornecedor.isPresent()) {
			CacheControl cacheControl = CacheControl.maxAge(3l, TimeUnit.MINUTES);
			return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body((toModel(fornecedor.get())));
		}
		return ResponseEntity.notFound().build();
	}

	@PreAuthorize("hasAnyRole('ROLE_EXCLUIR_FORNECEDOR')")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public void excluir(@PathVariable Long codigo) {
		forncecedorservice.excluir(codigo);

	}

	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_FORNECEDOR')")
	@Override
	public ResponseEntity<Fornecedor> editar(@Valid @RequestBody Fornecedor objeto) {
		forncecedorservice.salvar(objeto);
		return ResponseEntity.ok(objeto);
	}

	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_FORNECEDOR')")
	@PutMapping
	public ResponseEntity<FornecedorDTO> alterar(@RequestBody FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = toEntity(fornecedorDTO);
		fornecedor = forncecedorservice.salvar(fornecedor);

		return ResponseEntity.ok(toModel(fornecedor));
	}

	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_FORNECEDOR')")
	@GetMapping("/buscar-nome-comecando")
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {

		return ResponseEntity.ok(toCollectionModel((forncecedorservice.buscar(parametro))));
	}

	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_FORNECEDOR')")
	@GetMapping("/buscar-por-cnpj-ou-cpf")
	public ResponseEntity<FornecedorDTO> buscarporcpf(String cnpj) {

		Fornecedor fornecedor = forncecedorservice.buscarcnpj(cnpj);
		if (fornecedor != null) {

			return ResponseEntity.ok(toModel(fornecedor));
		}
		return ResponseEntity.notFound().build();
	}

	private List<FornecedorDTO> toCollectionModel(List<Fornecedor> fornecedores) {
		return fornecedores.stream().map(fornecedor -> toModel(fornecedor)).collect(Collectors.toList());
	}

	private FornecedorDTO toModel(Fornecedor fornecedor) {
		return modelMapper.map(fornecedor, FornecedorDTO.class);
	}

	private Fornecedor toEntity(FornecedorDTO fornecedorInput) {
		return modelMapper.map(fornecedorInput, Fornecedor.class);
	}

}
