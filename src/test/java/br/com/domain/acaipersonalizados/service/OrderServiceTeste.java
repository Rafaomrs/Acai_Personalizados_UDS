package br.com.domain.acaipersonalizados.service;


import static org.junit.Assert.assertNotNull;


import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.domain.acaipersonalizados.entity.Order;
import br.com.domain.acaipersonalizados.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTeste {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderService orderService;
	
	
	@Test
	public void deve_criar_um_pedido() {
		String size = "300ml";
		String flavor = "Morango";
		Order order = orderService.createOrder(size, flavor);
		assertNotNull(order);
		orderRepository.deleteAll();
	}
}
