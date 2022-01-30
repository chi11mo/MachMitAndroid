package de.techfak.gse.multiplayer.server.nanohttpd;

import java.util.Map;

/**
 * Holding core information of the request.
 */
public class Request {

    private final Map<String, String> queryParams;
    private final String body;

    public Request(final Map<String, String> queryParams, final String body) {
        this.queryParams = queryParams;
        this.body = body;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public String getBody() {
        return body;
    }
}
