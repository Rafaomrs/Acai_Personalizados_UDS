package br.com.uds.acaipersonalizados.api.service;

import br.com.uds.acaipersonalizados.api.enums.Size;
import br.com.uds.acaipersonalizados.api.dto.AlterarPedidoDeAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.CriarPedidoAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private static final Logger LOG = Logger
            .getLogger(OrderService.class);
    private final OrderRepository orderRepository;

    public Order createOrder(CriarPedidoAcaiDTO orderResource) {
        verificaSeFoiDefinidoTamanho(orderResource);
        return orderRepository.saveOrder(Order.of(orderResource));


//        final Order order = Order.of(orderResource);
//        return orderRepository.saveAndFlush(order);
    }

    private void verificaSeFoiDefinidoTamanho(CriarPedidoAcaiDTO orderResource) {
        Size.of(orderResource.getSize());
    }

    public OrderDTO retornaPorId(Long id) {
        return OrderDTO.from(orderRepository.retrieveOrderById(id));

//        final Optional<Order> orderEncontrado = orderRepository.findOrderById(id);
//        final OrderDTO orderDTO = orderEncontrado.map(order -> OrderDTO.from(order))
//                .orElseThrow(() -> new OrderNotFoundException("Order não encontrada"));
//        return orderDTO;
    }

    public boolean deleteById(Long id) {
        return orderRepository.deleteOrderById(id);
    }

    public OrderDTO personalizeOrder(Long id, AlterarPedidoDeAcaiDTO personalize) {
        final Order orderEncontrado = orderRepository.retrieveOrderById(id);
        orderEncontrado.alterar(personalize);
        return OrderDTO.from(orderRepository.saveOrder(orderEncontrado));
    }
}
