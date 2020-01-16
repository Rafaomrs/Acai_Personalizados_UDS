package br.com.domain.acaipersonalizados.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.domain.acaipersonalizados.entity.Order;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
