package br.com.poneyerp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poneyerp.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
