package net.idj.onlineshoppingstore.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ProductNotFoundException() {
		this("Product is not available!");
	}
	
	public ProductNotFoundException(String massage) {
		this.message = System.currentTimeMillis()+ ": " +message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
