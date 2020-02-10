package br.com.uds.acaipersonalizados.api.service;

import br.com.uds.acaipersonalizados.api.br.com.uds.acaipersonalizados.api.enums.Size;
import br.com.uds.acaipersonalizados.api.dto.AlterarPedidoDeAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.CriarPedidoAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.exception.OrderNotFoundException;
import br.com.uds.acaipersonalizados.api.repository.OrderJpaRepository;
import lombok.AllArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private static final Logger LOG = Logger
            .getLogger(OrderService.class);
    private final OrderJpaRepository orderRepository;

    public Order createOrder(CriarPedidoAcaiDTO orderResource) {
        verificaSeFoiDefinidoTamanho(orderResource);
        final Order order = Order.of(orderResource);
        return orderRepository.saveAndFlush(order);
    }

    private void verificaSeFoiDefinidoTamanho(CriarPedidoAcaiDTO orderResource) {
        Size.of(orderResource.getSize());
    }

    public OrderDTO retornaPorId(Long id) {
        final Optional<Order> orderEncontrado = orderRepository.findOrderById(id);
        final OrderDTO orderDTO = orderEncontrado.map(order -> OrderDTO.from(order))
                .orElseThrow(() -> new OrderNotFoundException("Order não encontrada"));
        return orderDTO;
    }

    public boolean deleteById(Long id) {
        this.retornaPorId(id);
        orderRepository.deleteById(id);
        return true;
    }

    public OrderDTO personalizeOrder(Long id, AlterarPedidoDeAcaiDTO personalize)  {
        final OrderDTO orderDTO = retornaPorId(id);
        final Order order = Order.of(orderDTO);
        order.alterar(personalize);
        return OrderDTO.from(orderRepository.save(order));
    }
}
