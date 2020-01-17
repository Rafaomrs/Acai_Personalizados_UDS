package br.com.domain.acaipersonalizados.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.acaipersonalizados.datasource.model.Order;
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
	public Order createOrder(Order order) {
		return orderRepository.saveAndFlush(order);
	}
	public Optional<Order> findOrderById(Long id) {
		return orderRepository.findById(id);
	}
	

	public List<Order> findAllOrder(){
		List<Order> listOrder = orderRepository.findAll();
		return listOrder;
	}
}
