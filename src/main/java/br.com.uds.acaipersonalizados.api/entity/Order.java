package br.com.uds.acaipersonalizados.api.entity;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
@Entity
@Table(name = "pedido")
public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "size")
	private String size;
	@Column(name = "flavor")
	private String flavor;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "timer")
	private LocalDateTime timer;
	@Column(name = "personalize")
	private String personalize;
	
	public Order(String size, String flavor, LocalDateTime timer, BigDecimal price, String personalize) {
		this.size = size;
		this.flavor = flavor;
		this.timer = timer;
		this.price = price;
		this.personalize = personalize;
	}
	public Order() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public LocalDateTime getTimer() {
		return timer;
	}
	public void setTimer(LocalDateTime timer) {
		this.timer = timer;
	}
	public String getPersonalize() {
		return personalize;
	}
	public void setPersonalize(String personalize) {
		this.personalize = personalize;
	}
	
}
