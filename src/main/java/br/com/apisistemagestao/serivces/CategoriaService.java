package br.com.apisistemagestao.serivces;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.stereotype.Service;

import br.com.apisistemagestao.domain.entity.Categoria;
import br.com.apisistemagestao.domain.repository.CategoriaRepository;
import br.com.apisistemagestao.serivces.exeption.NegocioExeption;



@Service
public class CategoriaService implements ServicePadrao<Categoria> {
	@Autowired
	private CategoriaRepository categoriarepository;

	@Transactional
	@Override
	public Categoria salvar(Categoria objeto) {

		Categoria categoriaexistente = categoriarepository.findByNomecategoria(objeto.getNomecategoria());

		if (categoriaexistente != null && !categoriaexistente.equals(objeto)) {
			throw new NegocioExeption("Categoria cadastrada no banco de dados");

		}
		return categoriarepository.save(objeto);
	}

	@Override
	public List<Categoria> pesquisar() {

		return categoriarepository.findAll();
	}

	@Override
	public Optional<Categoria> buscarporcodigo(Long id) {
		Optional<Categoria> categoria = categoriarepository.findById(id);
		if (categoria.isEmpty()) {
			throw new NegocioExeption("Categoira não encotrada");
		}

		return categoria;
	}

	@Override
	public List<Categoria> buscar(String parametro) {

		return categoriarepository.findByNomecategoriaStartingWith(parametro);
	}

	@Transactional
	@Override
	public void excluir(Long codigo) {
		try {
			categoriarepository.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioExeption("Categoria não encontrada");

		}

	}

}
