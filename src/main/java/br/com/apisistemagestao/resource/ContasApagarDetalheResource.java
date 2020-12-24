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


import br.com.apisistemagestao.domain.entity.ContasPagarDetalhe;
import br.com.apisistemagestao.dto.ContasApagarDetalheDTO;
import br.com.apisistemagestao.infra.ContasFilter;
import br.com.apisistemagestao.serivces.ContasPagarDetalheService;



@RestController
@RequestMapping(value = "/contas-a-apagar-detelhes")
public class ContasApagarDetalheResource implements ResourcePadrao<ContasPagarDetalhe> {
	@Autowired
	private ContasPagarDetalheService contaPagarDetalheService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

	return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(contaPagarDetalheService.pesquisar()));
	}

	@Override
	public ResponseEntity<?> salvar(ContasPagarDetalhe objeto, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
    @GetMapping("/{codigo}")
	@Override
	public ResponseEntity<?> buscapelocodigo(@PathVariable Long codigo) {
		Optional<ContasPagarDetalhe> contaDetalhe = contaPagarDetalheService.buscarporcodigo(codigo);

		if (contaDetalhe.isPresent()) {

			return ResponseEntity.ok(toModel(contaDetalhe.get()));
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

	@GetMapping("/situcao-contas")
	public ResponseEntity<List<?>> filtrar(ContasFilter contasfilter) {
	
	return ResponseEntity.status(HttpStatus.OK)
				.body(toCollectionModel((contaPagarDetalheService.filtrar(contasfilter))));
	}

	@PutMapping
	public ResponseEntity<ContasApagarDetalheDTO> darBaixaConta(@RequestParam BigDecimal valopago,
			@RequestBody ContasApagarDetalheDTO contaDTO) {
          
         System.out.println(contaDTO.getIdcontadetalhe());
		ContasPagarDetalhe conta = toEntity(contaDTO);

    	contaPagarDetalheService.darbaixaConta(valopago, conta);
		return ResponseEntity.ok(toModel(conta));

	}

	//@PutMapping
	@Override
	public ResponseEntity<?> editar(ContasPagarDetalhe objeto) {

		return null;
	}

	private List<ContasApagarDetalheDTO> toCollectionModel(List<ContasPagarDetalhe> contas) {
		return contas.stream().map(contaApgarDetalhe -> toModel(contaApgarDetalhe)).collect(Collectors.toList());
	}

	private ContasApagarDetalheDTO toModel(ContasPagarDetalhe contaApgarDetalhe) {
		return modelMapper.map(contaApgarDetalhe, ContasApagarDetalheDTO.class);
	}

	private ContasPagarDetalhe toEntity(ContasApagarDetalheDTO containput) {
		return modelMapper.map(containput, ContasPagarDetalhe.class);
	}

}
