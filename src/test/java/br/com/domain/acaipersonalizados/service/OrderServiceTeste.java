package br.com.domain.acaipersonalizados.service;


import static org.junit.Assert.assertNotNull;


import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.domain.acaipersonalizados.entity.Order;
import br.com.domain.acaipersonalizados.validationEnum.EnumFlavor;
import br.com.domain.acaipersonalizados.validationEnum.EnumPrice;
import br.com.domain.acaipersonalizados.validationEnum.EnumSize;
import br.com.domain.acaipersonalizados.validationEnum.EnumTimer;



@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTeste {
	
	@Autowired
	private OrderService orderService;
	

	
	@Test
	public void deve_criar_um_pedido() {
		Order order = orderService.createOrder(
				EnumSize.SMALL.getValue(),
				EnumFlavor.FLAVOR1.getValue(),
				EnumTimer.SMALL.getValue(),
				EnumPrice.SMALL.getValue());
		assertNotNull(order);
	}
	@Test
	public void deve_deletar_um_pedido_por_id() {
		Order order = orderService.createOrder(
				EnumSize.SMALL.getValue(),
				EnumFlavor.FLAVOR1.getValue(),
				EnumTimer.SMALL.getValue(),
				EnumPrice.SMALL.getValue());
		Long pedidoId = order.getId();
		orderService.deleteById(pedidoId);
	}
	@Test
	public void deve_encontrar_um_pedido_por_id() {
		Order order = orderService.createOrder(
				EnumSize.SMALL.getValue(),
				EnumFlavor.FLAVOR1.getValue(),
				EnumTimer.SMALL.getValue(),
				EnumPrice.SMALL.getValue());
		Long idOrder = order.getId();
		orderService.findOrderById(idOrder);
	}
}

