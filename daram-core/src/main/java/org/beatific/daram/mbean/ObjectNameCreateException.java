package org.beatific.daram.mbean;

public class ObjectNameCreateException extends RuntimeException {
	
	private static final long serialVersionUID = -2384218878964140618L;

	public ObjectNameCreateException() {
        super();
    }
	
	public ObjectNameCreateException(String s) {
        super(s);
    }
	
	public ObjectNameCreateException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public ObjectNameCreateException(Throwable cause) {
        super(cause);
    }
}
