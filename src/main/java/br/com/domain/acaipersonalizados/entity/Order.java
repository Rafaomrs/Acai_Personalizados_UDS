package br.com.domain.acaipersonalizados.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "size")
	private String size;
	@Column(name = "flavor")
	private String flavor;
	@Column(name = "price")
	private Double price;
	@Column(name = "preparation_time")
	private Time preparationTime;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public Order(Long id, String size, String flavor, Double price, Time preparationTime) {
		
	}
	public Order(String size, String flavor, Double price, Time preparationTime) {
		this.size = size;
		this.flavor = flavor;
		this.preparationTime = preparationTime;
		this.price = price;
	}
	public Order() {
		
	}
	public Long getId() {
		return id;
	}
	
	public String getSize() {
		return size;
	}
	public String getFlavor() {
		return flavor;
	}
	public Double getPrice() {
		return price;
	}
	public Time getPreparationTime() {
		return preparationTime;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
}
