package br.com.domain.acaipersonalizados.service;

import org.springframework.stereotype.Component;

import br.com.domain.acaipersonalizados.datasource.model.Order;
import br.com.domain.acaipersonalizados.exception.OrderResourceException;
import br.com.domain.acaipersonalizados.resource.model.OrderResource;

@Component
public class OrderConversor {
	
	public Order conversor(OrderResource orderResource) throws OrderResourceException {
		try {
			Order order = new Order();
			
			Double price = checkPrice(
					orderResource.getPrice());
			order.setPrice(price);
			order.setSize(orderResource.getSize());
			order.setFlavor(orderResource.getFlavor());
			order.setTimer(orderResource.getTimer());
			order.setPersonalize(orderResource.getPersonalize());
			return order;
			
		} catch (Exception e) {
			throw new OrderResourceException(
					"Falha ao converter o resource para entidade,"
					+ "resource: " + orderResource);
		}
	}
	
	private Double checkPrice(String price) {
		return Double.parseDouble(price);
	}
	


}
