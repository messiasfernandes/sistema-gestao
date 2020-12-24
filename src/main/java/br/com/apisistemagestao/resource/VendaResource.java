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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apisistemagestao.domain.entity.ItemVenda;
import br.com.apisistemagestao.domain.entity.Venda;
import br.com.apisistemagestao.dto.ItemvendasDTO;
import br.com.apisistemagestao.dto.VendaDto;
import br.com.apisistemagestao.infra.VendaseFilter;
import br.com.apisistemagestao.serivces.VendaService;


@RestController
@RequestMapping( "/vendas")
public class VendaResource implements ResourcePadrao<Venda> {
	@Autowired
     private VendaService vendaservice;
	@Autowired
	private ModelMapper modelMapper;
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(vendaservice.pesquisar()));
	}
    @PostMapping
	@Override
	public ResponseEntity<?> salvar(@Valid @RequestBody Venda objeto, HttpServletResponse response) {
    	System.out.println(objeto.getCliente().getCodigo());
       Venda vendaSalva = vendaservice.salvar(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(vendaSalva.getIdvenda()).toUri();
		response.setHeader("LoCation", uri.toASCIIString());

		return ResponseEntity.created(uri).body(vendaSalva);
	}
    @GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo( @PathVariable Long codigo) {
		Optional<Venda> venda= vendaservice.buscarporcodigo(codigo);
		if (venda.isPresent()) {
			return ResponseEntity.ok(toModel(venda.get()));
		}else
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {
		
		return null;
	}

	@Override
	public void excluir(Long codigo) {
	

	}
	@RequestMapping("buscar-por-itens")
     public ResponseEntity<?>buscar(VendaseFilter vendasfilter){
    	return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel2(vendaservice.buscar(vendasfilter))) ;
     }
	@Override
	public ResponseEntity<?> editar(Venda objeto) {
		
		return null;
	}
	
	private VendaDto toModel(Venda venda) {
		return modelMapper.map(venda, VendaDto.class);
	}

	private List<VendaDto> toCollectionModel(List<Venda> vendas) {
		return vendas.stream()
				.map(venda -> toModel(venda))
				.collect(Collectors.toList());
	}
	
	private ItemVenda toEntity2(ItemvendasDTO vendaInput) {
		return modelMapper.map(vendaInput, ItemVenda.class);
	}
	private ItemvendasDTO toModel2(ItemVenda venda) {
		return modelMapper.map(venda, ItemvendasDTO.class);
	}

	private List<ItemvendasDTO> toCollectionModel2(List<ItemVenda> vendas) {
		return vendas.stream()
				.map(venda -> toModel2(venda))
				.collect(Collectors.toList());
	}
	
	private Venda toEntity(VendaDto vendaInput) {
		return modelMapper.map(vendaInput, Venda.class);
	}
}
