package de.techfak.gse.multiplayer.server.nanohttpd;

/**
 * Http Content Type.
 */
public enum ContentType {

    /**
     * Plain text content Type.
     */
    PLAIN_TEXT("text/plain"),

    /**
     * Json content Type.
     */
    APPLICATION_JSON("application/json");

    private final String contentTypeString;

    ContentType(final String contentTypeString) {
        this.contentTypeString = contentTypeString;
    }

    public String asString() {
        return contentTypeString;
    }
}
