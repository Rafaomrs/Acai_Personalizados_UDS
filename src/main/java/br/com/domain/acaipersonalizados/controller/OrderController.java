package br.com.domain.acaipersonalizados.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domain.acaipersonalizados.datasource.model.Order;
import br.com.domain.acaipersonalizados.service.OrderService;

@RestController
@RequestMapping(value = "/acai-personalizados")
public class OrderController {
	
	@Autowired
	 private OrderService orderService;

	@GetMapping(path = "/search-order/id/{id}")
	public Optional<Order> findOrderById(
			@PathVariable(name = "id", required = true)Long id) {
		return orderService.findOrderById(id);
	}
	@GetMapping(path = "/search-order")
	public List<Order> findOrder(){
		return orderService.findAllOrder();
	}
	@PostMapping(path = "/place-order/save")
	public void placeOrder(@RequestBody Order order) {
		orderService.createOrder(order);
	}
	@DeleteMapping(path = "/order/delete/id/{id}")
	public void deleteOrder(
			@PathVariable (name = "id", required = true)Long id){
		orderService.deleteById(id);
	}
}
