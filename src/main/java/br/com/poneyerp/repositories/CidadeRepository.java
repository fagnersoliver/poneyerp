package br.com.poneyerp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poneyerp.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
