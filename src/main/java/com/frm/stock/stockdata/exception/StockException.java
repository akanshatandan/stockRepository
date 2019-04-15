package com.frm.stock.stockdata.exception;

import org.springframework.http.HttpStatus;

public class StockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Throwable cause;

	private String message;

	private HttpStatus errorStatus;
	
	private int errorCode;

	private String errorName;

	
	public StockException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockException(String message) {
		super();
		this.message = message;
	}

	public StockException(HttpStatus errorStatus, String message) {
		super();
		this.message = message;
		this.errorStatus = errorStatus;
	}

	public StockException(Throwable cause, String message) {
		super();
		this.cause = cause;
		this.message = message;
	}

	public StockException(Throwable cause, String message, HttpStatus errorStatus) {
		super();
		this.cause = cause;
		this.message = message;
		this.errorStatus = errorStatus;
	}

	public StockException(Throwable cause, String message, HttpStatus errorStatus, String errorName) {
		super();
		this.cause = cause;
		this.message = message;
		this.errorStatus = errorStatus;
		this.errorName = errorName;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(HttpStatus errorStatus) {
		this.errorStatus = errorStatus;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

}
