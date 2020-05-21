package br.com.poneyerp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poneyerp.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
