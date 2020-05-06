package br.com.poneyerp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poneyerp.domain.Categoria;
import br.com.poneyerp.repositories.CategoriaRepository;

@Service
public class CategoriaServices {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Categoria buscar(Integer id) {

		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElse(null);

	}

}
