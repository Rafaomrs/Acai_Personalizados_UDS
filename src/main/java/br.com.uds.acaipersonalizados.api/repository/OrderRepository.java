package br.com.uds.acaipersonalizados.api.repository;

import br.com.uds.acaipersonalizados.api.entity.Order;

public interface OrderRepository {

    Order retrieveOrderById(Long id);

    boolean deleteOrderById(Long id);

    Order saveOrder(Order order);
}
