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

import br.com.apisistemagestao.domain.entity.CondicaoPagamento;
import br.com.apisistemagestao.domain.entity.Parcelas;
import br.com.apisistemagestao.dto.CondicaoPagtoDTO;
import br.com.apisistemagestao.dto.ParcelasDTO;
import br.com.apisistemagestao.infra.CondicaoFilter;
import br.com.apisistemagestao.serivces.CondicaoPagamentoService;



@RestController
@RequestMapping(value = "/condicoes-de-pagamento")
public class CondicaoPagamentoResource implements ResourcePadrao<CondicaoPagamento> {
	@Autowired
	CondicaoPagamentoService condicaopgtoService;

	@Autowired
	private ModelMapper modelMapper;
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_CONDICAO')")
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {
		CacheControl cacheControl = CacheControl.maxAge(3l, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(toCollectionModel2(condicaopgtoService.pesquisar()));
	}
	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_CONDICAO')")
	@PostMapping
	@Override
	public ResponseEntity<?> salvar(@Valid @RequestBody CondicaoPagamento objeto, HttpServletResponse response) {

		System.out.println(objeto.getColecaoparcelas().get(0).getFormadePagamento().getNomeforma());
		CondicaoPagamento condicaosalva = condicaopgtoService.salvar(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{/codigo}")
				.buildAndExpand(condicaosalva.getIdcondicao()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(toModel2(condicaosalva));
	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_CONDICAO')")
	@GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
		Optional<CondicaoPagamento> condicaoop = condicaopgtoService.buscarporcodigo(codigo);
		if (condicaoop.isPresent()) {
			CacheControl cacheControl = CacheControl.maxAge(3l, TimeUnit.MINUTES);
			return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body((toModel2(condicaoop.get())));
		}
		return ResponseEntity.notFound().build();
	}
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_CONDICAO')")
	@GetMapping("/por-nome-comecando")
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {

		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel2(condicaopgtoService.buscar(parametro)));
	}
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_CONDICAO')")
	@GetMapping("/buscar-nome-ou-id")
	public ResponseEntity<List<?>> porCondcidicao(CondicaoFilter condicao) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(toCollectionModel(condicaopgtoService.buscarcondicao(condicao)));
	}
	

	
	@PreAuthorize("hasAnyRole('ROLE_EXCLUIR_CONDICAO')")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public void excluir(@PathVariable Long codigo) {

		condicaopgtoService.excluir(codigo);
	}
	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_CONDICAO')")
	@PutMapping
	@Override
	public ResponseEntity<?> editar(@Valid @RequestBody CondicaoPagamento objeto) {
		condicaopgtoService.salvar(objeto);
		return ResponseEntity.ok(objeto);
	}

	private ParcelasDTO toModel(Parcelas parcela) {
		return modelMapper.map(parcela, ParcelasDTO.class);
	}

	private List<ParcelasDTO> toCollectionModel(List<Parcelas> parcelas) {
		return parcelas.stream().map(parcela -> toModel(parcela)).collect(Collectors.toList());
	}

	private CondicaoPagtoDTO toModel2(CondicaoPagamento condicao) {
		return modelMapper.map(condicao, CondicaoPagtoDTO.class);
	}

	private List<CondicaoPagtoDTO> toCollectionModel2(List<CondicaoPagamento> condicoes) {
		return condicoes.stream().map(condicao -> toModel2(condicao)).collect(Collectors.toList());
	}
}
