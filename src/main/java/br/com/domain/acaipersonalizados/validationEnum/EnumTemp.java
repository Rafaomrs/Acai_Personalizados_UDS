package br.com.domain.acaipersonalizados.validationEnum;



public enum EnumTemp {
	SMALL("300ml", "5min", 10.00), 
	MEDIUM("500ml", "7min", 13.00), 
	LARGE("700ml", "10min", 15.00);
		
	private EnumTemp value;

	
	EnumTemp(String size, String timer, Double price) {
		
	}
	public EnumTemp getValue() {
		return this.value;
	}
		
}
	

