package br.com.poneyerp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.poneyerp.domain.Pedido;
import br.com.poneyerp.services.PedidoServices;

@RestController
@RequestMapping(value = "/Pedidos")
public class PedidoResources {

	@Autowired
	private PedidoServices PedidoService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Pedido obj = PedidoService.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
