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

import br.com.apisistemagestao.domain.entity.Cliente;
import br.com.apisistemagestao.dto.ClienteDTO;
import br.com.apisistemagestao.serivces.ClienteService;



@RestController
@RequestMapping("/clientes")
public class ClienteResource implements ResourcePadrao<Cliente> {
	@Autowired
	private ClienteService clienteservice;
	@Autowired
	private ModelMapper modelMapper;
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_CLIENTE')")
	@RequestMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(clienteservice.pesquisar()));
	}
    @PreAuthorize("hasAnyRole('ROLE_CADASTRAR_CLIENTE')")
	@PostMapping
	@Override
	public ResponseEntity<?> salvar(@Valid @RequestBody Cliente objeto, HttpServletResponse response) {
		Cliente clientesalvo = clienteservice.salvar(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{/codigo}")
				.buildAndExpand(clientesalvo.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(toModel(clientesalvo));
	}
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_CLIENTE')")
	@GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
		Optional<Cliente> clienteop = clienteservice.buscarporcodigo(codigo);
		if (clienteop.isPresent()) {
			CacheControl cacheControl = CacheControl.maxAge(3l, TimeUnit.MINUTES);
			return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(clienteop.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {
	
		return null;
	}
	@PreAuthorize("hasAnyRole('ROLE_REMOVER_CLIENTE')")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public void excluir(@PathVariable Long codigo) {
		clienteservice.excluir(codigo);

	}
	 @PreAuthorize("hasAnyRole('ROLE_CADASTRAR_CLIENTE')")
	@PutMapping
	@Override
	public ResponseEntity<?> editar(@Valid Cliente objeto) {
		clienteservice.salvar(objeto);
		return ResponseEntity.ok(toModel(objeto));
	}

	private List<ClienteDTO> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream().map(cliente -> toModel(cliente)).collect(Collectors.toList());
	}

	private ClienteDTO toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDTO.class);
	}

	private Cliente toEntity(ClienteDTO clienteInpu) {
		return modelMapper.map(clienteInpu, Cliente.class);
	}
}
