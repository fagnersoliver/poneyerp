package br.com.poneyerp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.poneyerp.domain.Categoria;
import br.com.poneyerp.dto.CategoriaDTO;
import br.com.poneyerp.repositories.CategoriaRepository;
import br.com.poneyerp.services.exceptions.DataIntegrityViolationException;
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

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria atualizaCliente = find(obj.getId());

		/* Atualiza sem apagar as demais informações */
		updateData(atualizaCliente, obj);

		return categoriaRepository.save(atualizaCliente);
	}

	private void updateData(Categoria atualizaCliente, Categoria obj) {
		atualizaCliente.setNome(obj.getNome());
	}

	public void delete(Integer id) {
		find(id);
		try {

			categoriaRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possivel excluir uma categoria que possui produtos");
		}

	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linePerPage, String orderBY, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBY);
		return categoriaRepository.findAll(pageRequest);

	}

	public Categoria fromDTO(CategoriaDTO objDto) {

		return new Categoria(objDto.getId(), objDto.getNome());

	}

}
