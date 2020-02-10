package br.com.uds.acaipersonalizados.api.repository.impl;

import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.exception.OrderNotFoundException;
import br.com.uds.acaipersonalizados.api.repository.OrderJpaRepository;
import br.com.uds.acaipersonalizados.api.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private static String MENSAGEM_ORDER_NAO_ENCONTRADO = "Order não encontrado.";
    private final OrderJpaRepository jpaRepository;

    @Override
    public Order retrieveOrderById(Long id) {
        final Optional<Order> orderEncontrado = jpaRepository.findOrderById(id);
        return orderEncontrado
                .orElseThrow(() -> new OrderNotFoundException(MENSAGEM_ORDER_NAO_ENCONTRADO));
    }

    @Override
    public boolean deleteOrderById(Long id) {
        jpaRepository.deleteById(id);
        return true;
    }

    @Override
    public Order saveOrder(Order order) {
        return jpaRepository.save(order);
    }

}
