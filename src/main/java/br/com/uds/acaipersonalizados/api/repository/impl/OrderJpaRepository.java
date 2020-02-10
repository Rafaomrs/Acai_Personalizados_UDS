package br.com.uds.acaipersonalizados.api.repository.impl;

import br.com.uds.acaipersonalizados.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    boolean existsById(Long id);

    Optional<Order> findOrderById(Long id);

}
