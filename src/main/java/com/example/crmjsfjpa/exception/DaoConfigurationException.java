package com.example.crmjsfjpa.exception;

public class DaoConfigurationException extends RuntimeException {

	public DaoConfigurationException(String message) {
		super(message);
	}

	public DaoConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoConfigurationException(Throwable cause) {
		super(cause);
	}

}
