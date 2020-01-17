package br.com.domain.acaipersonalizados.datasource.model;



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
public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "size")
	private String size;
	@Column(name = "flavor")
	private String flavor;
	@Column(name = "price")
	private Double price;
	@Column(name = "timer")
	private String timer;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	

	public Order(String size, String flavor, String timer, Double price) {
		this.size = size;
		this.flavor = flavor;
		this.timer = timer;
		this.price = price;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTimer() {
		return timer;
	}
	public void setTimer(String timer) {
		this.timer = timer;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
}