package com.frm.stock.stockdata.exception;

import org.springframework.http.HttpStatus;

public class StockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Throwable cause;

	private String message;

	private HttpStatus errorCode;

	private String errorName;

	public StockException(String message) {
		super();
		this.message = message;
	}

	public StockException(Throwable cause, String message) {
		super();
		this.cause = cause;
		this.message = message;
	}

	public StockException(Throwable cause, String message, HttpStatus errorCode) {
		super();
		this.cause = cause;
		this.message = message;
		this.errorCode = errorCode;
	}

	public StockException(Throwable cause, String message, HttpStatus errorCode, String errorName) {
		super();
		this.cause = cause;
		this.message = message;
		this.errorCode = errorCode;
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

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

}
