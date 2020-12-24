package br.com.apisistemagestao.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apisistemagestao.domain.entity.ContasaReceber;
import br.com.apisistemagestao.dto.ContaReceberDTO;
import br.com.apisistemagestao.serivces.ContaReceberService;



@RestController
@RequestMapping("/contas-receber")
public class ContaReceberResoucce implements ResourcePadrao<ContasaReceber> {
	@Autowired
	private ContaReceberService ContareceberSercice;
	@Autowired
	private ModelMapper modelMapper;
	 @PreAuthorize("hasAnyRole('ROLE_PESQUISAR_CONTASRECEBER')")
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {
		return ResponseEntity.ok(toCollectionModel(ContareceberSercice.pesquisar()));
	}
	 @PreAuthorize("hasAnyRole('ROLE_CADASTRAR_CONTASRECEBER')")
    @PostMapping
	@Override
	public ResponseEntity<?> salvar(@Valid @RequestBody ContasaReceber objeto, HttpServletResponse response) {
		ContasaReceber contasalva = ContareceberSercice.salvar(objeto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(contasalva.getIdcontareceber()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(toModel(contasalva));
	}
	 @PreAuthorize("hasAnyRole('ROLE_PESQUISAR_CONTASRECEBER')")
   @GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo( @PathVariable Long codigo) {
	      Optional<ContasaReceber> contareceber= ContareceberSercice.buscarporcodigo(codigo);
	      if (contareceber.isPresent()) {
	    	  return ResponseEntity.ok(toModel(contareceber.get()));
	    	  
	      }
		return ResponseEntity.notFound().build();
	}
  
	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResponseEntity<?> editar(ContasaReceber objeto) {
		// TODO Auto-generated method stub
		return null;
	}
	private List<ContaReceberDTO> toCollectionModel(List<ContasaReceber> contas) {
		return contas.stream()
				.map(contasreceber -> toModel(contasreceber))
				.collect(Collectors.toList());
	}
	private ContaReceberDTO toModel(ContasaReceber contasreceber) {
		return modelMapper.map(contasreceber, ContaReceberDTO.class);
	}
	
	private ContasaReceber toEntity(ContaReceberDTO containput) {
		return modelMapper.map(containput, ContasaReceber.class);
	}
}
