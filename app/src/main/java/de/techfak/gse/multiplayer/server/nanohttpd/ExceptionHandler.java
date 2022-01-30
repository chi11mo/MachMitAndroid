package de.techfak.gse.multiplayer.server.nanohttpd;

/**
 * Handler to handle a exception thrown in a response process.
 */
@FunctionalInterface
public interface ExceptionHandler {

    void handle(final Exception exception, final Context context);
}
