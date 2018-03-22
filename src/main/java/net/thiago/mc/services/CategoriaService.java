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
		return obj.orElse(null);
	}

}