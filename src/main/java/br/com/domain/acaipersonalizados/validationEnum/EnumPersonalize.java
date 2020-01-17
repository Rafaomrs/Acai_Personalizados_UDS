package br.com.domain.acaipersonalizados.validationEnum;



public enum EnumPersonalize {
	PERSONALIZE1("Leite ninho"), 
	PERSONALIZE2("Granola"), 
	PERSONALIZE3("Pacoca");
	
		
	private String value;
	private String timer;


	
	EnumPersonalize(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
		
}
	

