package net.thiago.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.thiago.mc.domain.Cliente;
import net.thiago.mc.dto.ClienteDTO;
import net.thiago.mc.repositories.ClienteRepository;
import net.thiago.mc.services.exceptions.DateIntegrityException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new net.thiago.mc.services.exceptions.ObjectNotFoundException(
				"Cliente não encontrado para o ID: " + id));
	}

	public Cliente insert(Cliente obj) {
		return this.repository.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = this.buscarPorId(obj.getId());

		this.updateData(newObj, obj);

		return this.repository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		this.buscarPorId(id);

		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DateIntegrityException("Não foi possível excluir um Cliente que possui vinculado a ele");
		}
	}

	public List<Cliente> findAll() {
		return this.repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return this.repository.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

}
