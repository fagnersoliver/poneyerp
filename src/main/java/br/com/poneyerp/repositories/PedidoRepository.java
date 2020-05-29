package br.com.poneyerp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.poneyerp.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
