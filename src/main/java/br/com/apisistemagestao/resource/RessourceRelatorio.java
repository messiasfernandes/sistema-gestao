package br.com.apisistemagestao.resource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.relation.RelationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisistemagestao.domain.entity.ItemVenda;
import br.com.apisistemagestao.dto.ItemvendasDTO;
import br.com.apisistemagestao.infra.VendaseFilter;
import br.com.apisistemagestao.serivces.RelatorioService;




@RequestMapping("/relatorios")
@RestController
public class RessourceRelatorio {
	@Autowired
	private RelatorioService serviceRel;
	@Autowired
	private ModelMapper modelMapper;
    @GetMapping("/estoques")
	public ResponseEntity<?>relestoque(BigDecimal pqtde1, BigDecimal pqtde2){
    	System.out.println(pqtde1 +" - " +pqtde2 );
		return ResponseEntity.status(HttpStatus.OK).body((serviceRel.relqtedEStqoue(pqtde1, pqtde2)));
	}
	@GetMapping
	public ResponseEntity<?> rerenda(VendaseFilter vendasrel) {
		return ResponseEntity.status(HttpStatus.OK).body(toCollectionModel(serviceRel.relavenda(vendasrel)));
	}

	private List<ItemvendasDTO> toCollectionModel(List<ItemVenda> itens) {
		return itens.stream().map(itemVenda -> toModel(itemVenda)).collect(Collectors.toList());
	}

	private ItemvendasDTO toModel(ItemVenda itemVenda) {
		return modelMapper.map(itemVenda, ItemvendasDTO.class);
	}
}
