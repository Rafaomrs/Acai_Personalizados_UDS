package br.com.uds.acaipersonalizados.api.service;

import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.exception.OrderNotFoundException;
import br.com.uds.acaipersonalizados.api.exception.OrderResourceException;
import br.com.uds.acaipersonalizados.api.repository.OrderJpaRepository;
import lombok.AllArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private static final Logger LOG = Logger
            .getLogger(OrderService.class);
    private final OrderJpaRepository orderRepository;

    public void createOrder(OrderDTO orderResource) {
        try {
            orderRepository.saveAndFlush(Order.of(orderResource));
        } catch (OrderResourceException e) {
            LOG.error("Erro ao salvar o pedido: " + e.getMessage(), e);
        }
    }

    public Order findOrderById(Long id) throws OrderNotFoundException {
        Optional<Order> optionalOrder = getOptional(id);

        Order order = null;
        if (!optionalOrder.isPresent()) {
            throw new OrderNotFoundException(
                    "Pedido nao encontrado atraves do ID: " + id);
        } else {
            order = optionalOrder.get();
        }
        return order;
    }

    public List<Order> findAllOrder() {
        List<Order> listOrder = orderRepository.findAll();
        return listOrder;
    }

    public void deleteById(Long id) throws OrderNotFoundException {

        Optional<Order> optionalOrder = getOptional(id);
        if (!optionalOrder.isPresent()) {
            throw new OrderNotFoundException(
                    "Order nao encontrado atraves do ID: " + id);
        } else {
            orderRepository.delete(optionalOrder.get());
        }

    }

    public void personalizeOrderById(Long id, OrderDTO personalizeOrder) throws OrderNotFoundException {
        Order order = findOrderById(id);
        order.setPersonalize(personalizeOrder.getPersonalize());
        orderRepository.save(order);
    }

    private Optional<Order> getOptional(Long id) {
        Optional<Order> optionalOrder = orderRepository
                .findById(id);
        return optionalOrder;
    }
}
