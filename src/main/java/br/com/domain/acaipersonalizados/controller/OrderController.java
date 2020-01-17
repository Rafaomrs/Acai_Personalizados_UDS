package br.com.domain.acaipersonalizados.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.domain.acaipersonalizados.datasource.model.Order;
import br.com.domain.acaipersonalizados.exception.OrderNotFoundException;
import br.com.domain.acaipersonalizados.resource.model.OrderResource;
import br.com.domain.acaipersonalizados.service.OrderService;

@RestController
@RequestMapping(value = "/acai-personalizados")
public class OrderController {
	
	@Autowired
	 private OrderService orderService;

	@GetMapping(path = "/order/search/by-id/{id}")
	public Order findOrderById(
			@PathVariable(name = "id", required = true)Long id) 
					throws OrderNotFoundException {
		return orderService.findOrderById(id);
	}
	@GetMapping(path = "/order/search")
	public List<Order> findOrder(){
		return orderService.findAllOrder();
	}
	@PostMapping(path = "/order/save")
	public void placeOrder(@RequestBody OrderResource order) {
		orderService.createOrder(order);
	}
	@DeleteMapping(path = "/order/delete/by-id/{id}")
	public void deleteOrder(
			@PathVariable (name = "id", required = true)Long id) 
					throws OrderNotFoundException{
		orderService.deleteById(id);
	}
	@PutMapping(path = "/order/personalize")
	public void personalizeOrder(@RequestBody OrderResource order) {
		orderService.createOrder(order);
	}
}
