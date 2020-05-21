package br.com.poneyerp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poneyerp.domain.Categoria;
import br.com.poneyerp.repositories.CategoriaRepository;
import br.com.poneyerp.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaServices {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {

		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

}
