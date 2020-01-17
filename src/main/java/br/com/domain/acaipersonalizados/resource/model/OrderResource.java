package br.com.domain.acaipersonalizados.resource.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResource {
	
	@JsonProperty("acai_size")
	private String size;
	@JsonProperty("acai_flavor")
	private String flavor;
	
	
	

	public OrderResource(String size, String flavor) {
		this.size = size;
		this.flavor = flavor;
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
}
