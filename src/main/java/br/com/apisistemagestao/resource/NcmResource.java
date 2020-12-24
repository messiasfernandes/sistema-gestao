package br.com.apisistemagestao.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisistemagestao.domain.entity.Ncms;
import br.com.apisistemagestao.serivces.NcmService;
@RestController
@RequestMapping( "/ncms")
public class NcmResource implements ResourcePadrao<Ncms> {
	@Autowired
    private NcmService ncmservice;
	@GetMapping
	@Override
	public ResponseEntity<List<?>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(ncmservice.pesquisar());
	}

	@Override
	public ResponseEntity<?> salvar(Ncms objeto, HttpServletResponse response) {
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
	public ResponseEntity<?> editar(Ncms objeto) {
		// TODO Auto-generated method stub
		return null;
	}
	@GetMapping("/por-codigo-ncm")
	public ResponseEntity<?> buscarCodigoNcm(String ncm) {
	
		return ResponseEntity.status(HttpStatus.OK).body(ncmservice.buscaCodigoNcm(ncm));
	}

}
