package net.thiago.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.thiago.mc.domain.Cliente;
import net.thiago.mc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new net.thiago.mc.services.exceptions.ObjectNotFoundException(
				"Cliente não encontrado para o ID: " + id));
	}

}
