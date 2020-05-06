package br.com.poneyerp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poneyerp.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
