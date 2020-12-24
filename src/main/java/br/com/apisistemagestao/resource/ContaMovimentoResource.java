package br.com.apisistemagestao.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisistemagestao.domain.entity.ContaMovimento;
import br.com.apisistemagestao.serivces.ContaMovimentoService;



@RestController
@RequestMapping(value = "contas-movimento")
public class ContaMovimentoResource implements ResourcePadrao<ContaMovimento> {
	@Autowired
	private ContaMovimentoService ContaMovimentoService;
    @GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {
		
		return ResponseEntity.status(HttpStatus.OK).body(ContaMovimentoService.pesquisar());
	}

	@Override
	public ResponseEntity<?> salvar(ContaMovimento objeto, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> buscapelocodigo(Long codigo) {
		// TODO Auto-generated method stub
		return null;
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
	 @PutMapping
	 public ContaMovimento moviemtar(@RequestParam BigDecimal valor, @RequestBody ContaMovimento contaMoVimento) {
		 System.out.println(valor);
		 ContaMovimentoService.moviemtar(valor, contaMoVimento);
		return contaMoVimento;
		 
	 }
	@Override
	public ResponseEntity<?> editar(ContaMovimento objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}
