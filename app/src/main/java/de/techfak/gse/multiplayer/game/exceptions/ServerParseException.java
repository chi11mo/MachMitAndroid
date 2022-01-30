package de.techfak.gse.multiplayer.game.exceptions;

/**
 * Thrown when board parsing fails.
 */
public class ServerParseException extends Exception {

    private static final long serialVersionUID = 7250134072307999749L;

    public ServerParseException(final String message) {
        super(message);
    }
}
