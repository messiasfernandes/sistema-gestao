package br.com.apisistemagestao.resource;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apisistemagestao.domain.entity.ContasPagar;

import br.com.apisistemagestao.dto.ContasaPagarDTO;
import br.com.apisistemagestao.infra.ContasFilter;
import br.com.apisistemagestao.serivces.ContasApagarService;

@RestController
@RequestMapping(value = "/contas-apagar")
public class ContasApagarResource implements ResourcePadrao<ContasPagar> {
	@Autowired
	private ContasApagarService contaparservice;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contaparservice.pesquisar()));
	}

	@PostMapping
	@Override
	public ResponseEntity<?> salvar(@Valid @RequestBody ContasPagar objeto, HttpServletResponse response) {
		ContasPagar contasalva = contaparservice.salvar(objeto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(contasalva.getIdconta()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(toModel(contasalva));
	}

	@GetMapping("{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {

		Optional<ContasPagar> conta = contaparservice.buscarporcodigo(codigo);
		if (conta.isPresent()) {
			return ResponseEntity.ok(toModel(conta.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public void excluir(Long codigo) {

	}

	@Override
	public ResponseEntity<ContasPagar> editar(ContasPagar objeto) {

		return null;
	}

	@GetMapping("/por-titulo")
	public ResponseEntity<List<?>> buscatTitulo(@RequestParam Long titulo) {

		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contaparservice.buscarportitulo(titulo)));
	}

	@GetMapping("/situacao-contas")
	public ResponseEntity<List<?>> filtrar(ContasFilter contasfilter) {
		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contaparservice.filtar(contasfilter)));
	}

	@PutMapping
	public ResponseEntity<ContasaPagarDTO> darBaixaConta(@Valid @RequestBody ContasPagar conta) {
		contaparservice.darbaixaConta(conta);
		return ResponseEntity.ok(toModel(conta));

	}

	@GetMapping("/canceladas")
	public ResponseEntity<List<?>> buscarCancelado() {

		return ResponseEntity.ok(toCollectionModel(contaparservice.buscarCancelado()));

	}

	@GetMapping("contas-forncedores")
	public ResponseEntity<List<?>> filtro(Long nuntitulo, String cnpj) {
		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contaparservice.filtro(nuntitulo, cnpj)));
	}
	@GetMapping("/contas-atrasada")
	public ResponseEntity<List<?>> filtraratrasada() {
		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contaparservice.listarAtrasado()));
	}
	@GetMapping("/contas-fornecedores-nome")
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {

		return  ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contaparservice.buscar(parametro)));
	}

	private List<ContasaPagarDTO> toCollectionModel(List<ContasPagar> contas) {
		return contas.stream().map(contasapagar -> toModel(contasapagar)).collect(Collectors.toList());
	}

	private ContasaPagarDTO toModel(ContasPagar contaApgar) {
		return modelMapper.map(contaApgar, ContasaPagarDTO.class);
	}

	private ContasPagar toEntity(ContasaPagarDTO containput) {
		return modelMapper.map(containput, ContasPagar.class);
	}

}
