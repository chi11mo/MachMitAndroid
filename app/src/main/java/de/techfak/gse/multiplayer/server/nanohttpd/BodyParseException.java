package de.techfak.gse.multiplayer.server.nanohttpd;

/**
 * Exception when parsing json objects fails.
 */
public class BodyParseException extends RuntimeException {

    private static final long serialVersionUID = 8821045004649995037L;

    public BodyParseException(final String message) {
        super(message);
    }

    public BodyParseException(final Throwable cause) {
        super(cause);
    }
}
