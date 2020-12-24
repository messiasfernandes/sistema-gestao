package br.com.apisistemagestao.resource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisistemagestao.domain.entity.ContaDetalheReceber;
import br.com.apisistemagestao.dto.ContaDetalheReceberDTO;
import br.com.apisistemagestao.serivces.ContaDetalheReceberService;




@RestController
@RequestMapping("/contas-receber-detalhes")
public class ContaReceberDetalheResource implements ResourcePadrao<ContaDetalheReceber> {
	@Autowired
	private ContaDetalheReceberService contadetalheService;
	@Autowired
	private ModelMapper modelMapper;
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {
	
		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contadetalheService.pesquisar()));
	}

	@Override
	public ResponseEntity<?> salvar(ContaDetalheReceber objeto, HttpServletResponse response) {
	
		return null;
	}
    @GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
        Optional<ContaDetalheReceber> contareceber= contadetalheService.buscarporcodigo(codigo);
	      if (contareceber.isPresent()) {
	    	  return ResponseEntity.ok(toModel(contareceber.get()));
	    	  
	      }
		return ResponseEntity.notFound().build();
	}
    @GetMapping("/listar-atrasados")
    public ResponseEntity<?>litarAtrasado(){
    	System.out.println("passou aqui");
    	return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contadetalheService.listarAtrasado()));
    }
	@Override()
	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro) {
	
		return null;
	}

	@Override
	public void excluir(Long codigo) {
	

	}
	@PutMapping
    public ResponseEntity<ContaDetalheReceberDTO>  darbaixaparcela(@RequestParam BigDecimal valor , @RequestBody  ContaDetalheReceberDTO contareceberDto) {
      System.out.println(valor);
  		ContaDetalheReceber conta = toEntity(contareceberDto);

  		contadetalheService.darbaixaparcela(valor, conta);
  		return ResponseEntity.ok(toModel(conta));
    }
	@Override
	public ResponseEntity<?> editar(ContaDetalheReceber objeto) {

		return null;
	}
	private List<ContaDetalheReceberDTO> toCollectionModel(List<ContaDetalheReceber> contas) {
		return contas.stream().map(contadetalheReceber -> toModel(contadetalheReceber)).collect(Collectors.toList());
	}

	private ContaDetalheReceberDTO toModel(ContaDetalheReceber contadetalheReceber) {
		return modelMapper.map(contadetalheReceber, ContaDetalheReceberDTO.class);
	}

	private ContaDetalheReceber toEntity(ContaDetalheReceberDTO containput) {
		return modelMapper.map(containput, ContaDetalheReceber.class);
	}
}
