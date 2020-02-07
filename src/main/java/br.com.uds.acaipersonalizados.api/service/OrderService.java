package br.com.uds.acaipersonalizados.api.service;

import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
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

    public Order createOrder(OrderDTO orderResource) {
        verificaSeFoiDefinidoTamanho(orderResource);
        final Order order = Order.of(orderResource);
        return orderRepository.saveAndFlush(order);
    }

    private void verificaSeFoiDefinidoTamanho(OrderDTO orderResource) {
        final boolean isUmPedidoComTamanhoValido = orderRepository.existsBySize(orderResource.getSize());
        if (!isUmPedidoComTamanhoValido) {
            throw new IllegalArgumentException("Dados do pedido estão incompletos.");
        }
    }

    public OrderDTO retornaPorId(Long id) {
        final Optional<Order> orderEncontrado = orderRepository.findOrderById(id);
        final OrderDTO orderDTO = orderEncontrado.map(order -> OrderDTO.from(order))
                .orElseThrow(() -> new RuntimeException("Ordem não encontrada"));
        return orderDTO;
    }

    public boolean deleteById(Long id) {
        this.retornaPorId(id);
        orderRepository.deleteById(id);
        return true;
    }

    public OrderDTO personalizeOrder(Long id, String personalize)  {
        final OrderDTO orderDTO = retornaPorId(id);
        final Order order = Order.of(orderDTO);
        order.setPersonalize(personalize);
        return OrderDTO.from(orderRepository.save(order));
    }
}
