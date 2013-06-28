package edu.iitdu.jdgen.exception;

public class JDGenException extends RuntimeException {

	private static final long serialVersionUID = -726378825248467651L;

	public JDGenException() {
	}

	public JDGenException(String message) {
		super(message);
	}

	public JDGenException(Throwable cause) {
		super(cause);
	}

	public JDGenException(String message, Throwable cause) {
		super(message, cause);
	}
}
