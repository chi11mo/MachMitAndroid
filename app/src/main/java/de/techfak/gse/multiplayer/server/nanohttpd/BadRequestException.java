package de.techfak.gse.multiplayer.server.nanohttpd;

/**
 * Exception which indicates the request is incorrect.
 */
public class BadRequestException extends Exception {

    private static final long serialVersionUID = -191477472731050849L;

    public BadRequestException(final String message) {
        super(message);
    }

    public BadRequestException(final Throwable cause) {
        super(cause);
    }
}
