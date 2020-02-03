package br.com.uds.acaipersonalizados.api.exception;

public class OrderResourceException extends Exception{
	
	private static final long serialVersionUID = 1818552286447268880L;

	public OrderResourceException() {
		super();
	}

	public OrderResourceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderResourceException(String message) {
		super(message);
	}

	public OrderResourceException(Throwable cause) {
		super(cause);
	}



}
