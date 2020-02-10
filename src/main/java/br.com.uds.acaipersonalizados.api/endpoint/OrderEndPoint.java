package br.com.uds.acaipersonalizados.api.endpoint;


import br.com.uds.acaipersonalizados.api.dto.AlterarPedidoDeAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.CriarPedidoAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/acai-personalizados/order")
@AllArgsConstructor
public class OrderEndPoint {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "{id}")
    public OrderDTO findOrderById(
            @PathVariable(name = "id", required = true) Long id) {
        return orderService.retornaPorId(id);
    }

    @PostMapping
    public ResponseEntity placeOrder(@Valid  @RequestBody CriarPedidoAcaiDTO order) {
        final Order test = orderService.createOrder(order);
        return ResponseEntity.ok(test);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteOrder(
            @PathVariable(name = "id", required = true) Long id) {
         return orderService.deleteById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity personalizeOrder(
            @PathVariable Long id,
            @RequestBody AlterarPedidoDeAcaiDTO alterarPedidoDeAcaiDTO) {
        final OrderDTO acaiAlterado = orderService.personalizeOrder(id, alterarPedidoDeAcaiDTO);
        return ResponseEntity.noContent().build();
    }
}
