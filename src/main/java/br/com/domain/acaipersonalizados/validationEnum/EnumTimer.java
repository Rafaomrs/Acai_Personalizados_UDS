package br.com.domain.acaipersonalizados.validationEnum;



public enum EnumTimer {
	SMALL("5min"), MEDIUM("7min"), LARGE("10min");
	
	private String timer;
	
	EnumTimer(String timer){
		this.timer = timer;
	}
	public String getValue() {
		return this.timer;
	}
}
