package br.com.domain.acaipersonalizados.validationEnum;

public enum EnumFlavor {
	FLAVOR1("Morango"),FLAVOR2("Banana"),FLAVOR3("Kiwi");
	
	private String value;
	
	EnumFlavor(String value){
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}

}
