package br.com.uds.acaipersonalizados.api.repository;

import br.com.uds.acaipersonalizados.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    boolean existsBySize(String size);
    boolean existsById(Long id);
    Optional<Order> findOrderById(Long id);
}
