package br.com.domain.acaipersonalizados.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.acaipersonalizados.entity.Order;
import br.com.domain.acaipersonalizados.repository.OrderRepository;






@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}
	public void deleteAll() {
		orderRepository.deleteAll();
	}
	public Order createOrder(String size, String flavor, String timer, Double price) {
		Order order = new Order();
		order.setSize(size);
		order.setFlavor(flavor);
		order.setTimer(timer);
		order.setPrice(price);
		return orderRepository.save(order);
	}
	public Optional<Order> findOrderById(Long id) {
		return orderRepository.findById(id);
	}
}
