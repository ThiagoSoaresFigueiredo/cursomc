package net.thiago.mc.services.exceptions;

public class DateIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DateIntegrityException(String mensagem) {
		super(mensagem);
	}

	public DateIntegrityException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
