package br.com.domain.acaipersonalizados.service;

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
	public Order createOrder(String size, String flavor) {
		Order order = new Order();
		order.setSize(size);
		order.setFlavor(flavor);
		return orderRepository.save(order);
	}
}
