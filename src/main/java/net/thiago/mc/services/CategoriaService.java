package net.thiago.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.thiago.mc.domain.Categoria;
import net.thiago.mc.repositories.CategoriaRepository;

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

}
