package br.com.apisistemagestao.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface ResourcePadrao <T> {
	
	public ResponseEntity<List<?>> listar();

	public ResponseEntity<?> salvar(T objeto, HttpServletResponse response);

	public ResponseEntity<?> buscapelocodigo(Long codigo);

	public ResponseEntity<List<?>> porNomeComecandoCom(String parametro);

	public void excluir(Long codigo);

	public ResponseEntity<?> editar(T objeto);
//
//	public List<?> toCollectionModel(List<?> objeto);
//
//	public Object toModel(T objeto);
//
//	public T toEntity(Object objetoInput);


}
