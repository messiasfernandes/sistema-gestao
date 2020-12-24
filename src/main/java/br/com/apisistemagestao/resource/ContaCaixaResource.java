package br.com.apisistemagestao.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisistemagestao.domain.entity.ContaCaixa;
@RestController
@RequestMapping(value = "/contas-caixa")
public class ContaCaixaResource implements ResourcePadrao<ContaCaixa> {

	@Override
	public ResponseEntity<List<?>> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> salvar(ContaCaixa objeto, HttpServletResponse response) {
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
	public ResponseEntity<?> editar(ContaCaixa objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}
