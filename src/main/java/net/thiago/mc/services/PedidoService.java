package net.thiago.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.thiago.mc.domain.Pedido;
import net.thiago.mc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new net.thiago.mc.services.exceptions.ObjectNotFoundException("Pedido não encontrado para o ID: " + id));
	}

}
