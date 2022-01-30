package de.techfak.gse.multiplayer.server.nanohttpd;

/**
 * Handler to handle a request.
 */
public interface Handler {

    void handle(final Context context);
}
