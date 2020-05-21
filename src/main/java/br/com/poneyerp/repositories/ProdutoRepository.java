package br.com.poneyerp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poneyerp.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
