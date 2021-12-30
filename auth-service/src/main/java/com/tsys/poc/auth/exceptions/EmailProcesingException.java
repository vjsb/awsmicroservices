package com.tsys.poc.auth.exceptions;

public class EmailProcesingException extends RuntimeException{

	private static final long serialVersionUID = -4654645414370255196L;

	public EmailProcesingException(String exMessage) {
        super(exMessage);
    }
}
