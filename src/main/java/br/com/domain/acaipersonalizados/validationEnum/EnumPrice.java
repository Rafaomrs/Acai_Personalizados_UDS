package br.com.domain.acaipersonalizados.validationEnum;

public enum EnumPrice {
	SMALL(10.00), MEDIUM(13.00), LARGE(15.00);
	
	private Double value;
	
	EnumPrice(Double value){
		this.value = value;
	}
	
	public Double getValue() {
		return this.value;
	}
}
