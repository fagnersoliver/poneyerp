package br.com.poneyerp.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.poneyerp.domain.Cliente;
import br.com.poneyerp.dto.ClienteDTO;
import br.com.poneyerp.services.ClientesServices;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	private ClientesServices clientesService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente obj = clientesService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {

		Cliente obj = clientesService.fromDTO(objDto);
		obj.setId(id);
		obj = clientesService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		clientesService.delete(id);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> list = clientesService.findAll();

		/* Faço a leitura da lista para que ela preencha os campos do DTO */
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage,
			@RequestParam(value = "orderby", defaultValue = "nome") String orderBY,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> pageList = clientesService.findPage(page, linePerPage, orderBY, direction);

		/* Faço a leitura da lista para que ela preencha os campos do DTO */
		Page<ClienteDTO> pagelistDTO = pageList.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(pagelistDTO);
	}

}
