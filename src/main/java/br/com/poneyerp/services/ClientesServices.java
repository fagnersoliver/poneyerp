package br.com.poneyerp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.poneyerp.domain.Cliente;
import br.com.poneyerp.dto.ClienteDTO;
import br.com.poneyerp.repositories.ClienteRepository;
import br.com.poneyerp.services.exceptions.DataIntegrityViolationException;
import br.com.poneyerp.services.exceptions.ObjectNotFoundException;

@Service
public class ClientesServices {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {

		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	public Cliente update(Cliente obj) {
		Cliente atualizaCliente = find(obj.getId());

		/*Atualiza sem apagar as demais informações*/
		updateData(atualizaCliente, obj);

		return clienteRepository.save(atualizaCliente);
	}

	private void updateData(Cliente atualizaCliente, Cliente obj) {
		atualizaCliente.setNome(obj.getNome());
		atualizaCliente.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {

			clienteRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possivel excluir porque há entidades relacionadas");
		}

	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linePerPage, String orderBY, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBY);
		return clienteRepository.findAll(pageRequest);

	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

}
