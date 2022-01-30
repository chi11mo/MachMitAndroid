package de.techfak.gse.multiplayer.server.nanohttpd;

/**
 * Holding core information of the response.
 */
public class Response {

    private int status;
    private String body;
    private ContentType contentType;

    public Response(final int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(final ContentType contentType) {
        this.contentType = contentType;
    }
}
