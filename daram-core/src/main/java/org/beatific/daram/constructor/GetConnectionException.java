package org.beatific.daram.constructor;

public class GetConnectionException extends RuntimeException {

	private static final long serialVersionUID = -3815171880356977969L;

	public GetConnectionException() {
        super();
    }
	
	public GetConnectionException(String s) {
        super(s);
    }
	
	public GetConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public GetConnectionException(Throwable cause) {
        super(cause);
    }
}
