package br.com.poneyerp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poneyerp.domain.Pedido;
import br.com.poneyerp.repositories.PedidoRepository;
import br.com.poneyerp.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoServices {

	@Autowired
	private PedidoRepository PedidoRepository;

	public Pedido find(Integer id) {

		Optional<Pedido> obj = PedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}

}
