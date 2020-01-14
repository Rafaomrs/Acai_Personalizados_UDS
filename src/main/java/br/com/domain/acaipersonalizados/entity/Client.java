package br.com.domain.acaipersonalizados.entity;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "client")
public class Client{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> order;
	
	public Client() {
		this.order = new ArrayList<>();
	}
	public Client(String name, Order order, Long id) {
		this.name = name;
		this.id = id;
		this.order = new ArrayList<>();
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Order> getOrder(){
		return order;
	}
}
