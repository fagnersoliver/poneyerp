package br.com.poneyerp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poneyerp.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
