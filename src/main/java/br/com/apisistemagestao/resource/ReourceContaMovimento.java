package br.com.apisistemagestao.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisistemagestao.domain.entity.ContaMovimento;
import br.com.apisistemagestao.serivces.ContaMovimentoService;



@RestController
@RequestMapping(value = "contas-movimento")
public class ReourceContaMovimento implements ResourcePadrao<ContaMovimento> {
	@Autowired
    private ContaMovimentoService contaMoviemtnoService;
	@Override
	public ResponseEntity<List<?>> listar() {
	
		return ResponseEntity.status(HttpStatus.OK).body(contaMoviemtnoService.pesquisar());
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

	@Override
	public ResponseEntity<?> editar(ContaMovimento objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}
