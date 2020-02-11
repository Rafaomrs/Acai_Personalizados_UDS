package br.com.uds.acaipersonalizados.api.endpoint;


import br.com.uds.acaipersonalizados.api.dto.AlterarPedidoDeAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.CriarPedidoAcaiDTO;
import br.com.uds.acaipersonalizados.api.dto.OrderDTO;
import br.com.uds.acaipersonalizados.api.entity.Order;
import br.com.uds.acaipersonalizados.api.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(
            notes = "Retorna um Pedido",
            value = "Encontra um pedido por id",
            nickname = "findOrderById",
            response = OrderDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pedido retornado.", response = OrderDTO.class),
            @ApiResponse(code = 404, message = "Pedido não encontrado."),
            @ApiResponse(code = 401, message = "Sem autorização para efetuar pedido."),
            @ApiResponse(code = 403, message = "Tipo de pedido proíbido.")
    })
    public OrderDTO findOrderById(
            @PathVariable(name = "id", required = true) Long id) {
        return orderService.retornaPorId(id);
    }

    @PostMapping
    @ApiOperation(
            notes = "Realiza um Pedido.",
            value = "Realiza um Pedido. Necessário passar o tamanho do Açaí.",
            nickname = "placeOrder",
            response = CriarPedidoAcaiDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido Criado com sucesso.", response = CriarPedidoAcaiDTO.class),
            @ApiResponse(code = 404, message = "Pedido não efetuado."),
            @ApiResponse(code = 201, message = "Pedido está sendo processado."),
            @ApiResponse(code = 401, message = "Sem autorização para efetuar pedido."),
            @ApiResponse(code = 403, message = "Tipo de pedido proíbido.")

    })
    public ResponseEntity placeOrder(@Valid  @RequestBody CriarPedidoAcaiDTO order) {
        final Order test = orderService.createOrder(order);
        return ResponseEntity.ok(test);
    }


    @DeleteMapping(path = "{id}")
    @ApiOperation(value = "Remove um Pedido por id.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido excluído."),
            @ApiResponse(code = 401, message = "Sem autorização para efetuar pedido."),
            @ApiResponse(code = 403, message = "Tipo de pedido proíbido.")
    })
    public boolean deleteOrder(
            @PathVariable(name = "id", required = true) Long id) {
         return orderService.deleteById(id);
    }

    @PutMapping(path = "{id}")
    @ApiOperation(
            notes = "Adiciona elementos ao Açai.",
            value = "Adiciona elementos ao Açaí. Deve passar o tipo de adicional",
            nickname = "personalizeOrder",
            response = AlterarPedidoDeAcaiDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 204, message = "Incremento adicionado com sucesso.", response = AlterarPedidoDeAcaiDTO.class),
            @ApiResponse(code = 404, message = "Pedido não encontrado. Não podemos adicionar no seu pedido."),
            @ApiResponse(code = 401, message = "Sem autorização para efetuar pedido."),
            @ApiResponse(code = 403, message = "Tipo de pedido proíbido.")
    })
    public ResponseEntity personalizeOrder(
            @PathVariable Long id,
            @RequestBody AlterarPedidoDeAcaiDTO alterarPedidoDeAcaiDTO) {
        final OrderDTO acaiAlterado = orderService.personalizeOrder(id, alterarPedidoDeAcaiDTO);
        return ResponseEntity.noContent().build();
    }
}
