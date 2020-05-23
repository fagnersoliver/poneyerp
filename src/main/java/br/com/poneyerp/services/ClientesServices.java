package br.com.poneyerp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poneyerp.domain.Cliente;
import br.com.poneyerp.repositories.ClienteRepository;
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

}
