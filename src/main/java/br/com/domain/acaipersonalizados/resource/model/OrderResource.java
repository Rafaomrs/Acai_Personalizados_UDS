package br.com.domain.acaipersonalizados.resource.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResource {
	
	@JsonProperty("acai_size")
	private String size;
	@JsonProperty("acai_flavor")
	private String flavor;
	@JsonProperty("acai_price")
	private String price;
	@JsonProperty("acai_prepare_timer")
	private String timer;
	
	
	

	public OrderResource(String size, String flavor, String price, String timer) {
		this.size = size;
		this.flavor = flavor;
		this.price = price;
		this.timer = timer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
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

	@Override
	public String toString() {
		return "OrderResource [size=" + size + ", flavor=" 
				+ flavor + ", price=" + price + ", timer=" 
				+ timer + "]";
	}
	
}
