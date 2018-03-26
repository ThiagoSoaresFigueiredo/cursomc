package net.thiago.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.thiago.mc.domain.Categoria;
import net.thiago.mc.repositories.CategoriaRepository;
import net.thiago.mc.services.exceptions.DateIntegrityException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscarPorId(Integer id) {
		Optional<Categoria> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new net.thiago.mc.services.exceptions.ObjectNotFoundException(
				"Categoria não encontrada para o ID: " + id));
	}

	public Categoria insert(Categoria obj) {
		return this.repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		this.buscarPorId(obj.getId());
		return this.repository.save(obj);
	}

	public void delete(Integer id) {
		this.buscarPorId(id);

		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DateIntegrityException(
					"Não foi possível excluir uma Categoria que possui Produto vinculado a ela");
		}
	}

	public List<Categoria> findAll() {
		return this.repository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return this.repository.findAll(pageRequest);
	}
}