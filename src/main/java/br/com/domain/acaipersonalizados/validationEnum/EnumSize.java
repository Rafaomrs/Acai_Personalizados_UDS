package br.com.domain.acaipersonalizados.validationEnum;

public enum EnumSize {
	SMALL("300ml"), MEDIUM("500ml"), LARGE("700ml");
	
	private String value;
	
	EnumSize(String value){
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
	
}
