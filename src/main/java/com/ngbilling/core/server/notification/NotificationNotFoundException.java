package com.ngbilling.core.server.notification;

public class NotificationNotFoundException extends Exception{

	public NotificationNotFoundException() {
        super();
    }

    /**
     * @param message
     */
    public NotificationNotFoundException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public NotificationNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public NotificationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
