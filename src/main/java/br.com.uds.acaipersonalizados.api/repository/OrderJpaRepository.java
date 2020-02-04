package br.com.uds.acaipersonalizados.api.repository;

import br.com.uds.acaipersonalizados.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
