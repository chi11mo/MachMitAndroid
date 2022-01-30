package de.techfak.gse.multiplayer.server.nanohttpd;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * The Request Response context to build the response.
 */
public class Context {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final int OK = 200;

    private Request request;
    private final Response response;

    public Context() {
        this.response = new Response(OK);
    }

    public String queryParam(final String name) {
        return request.getQueryParams().get(name);
    }

    /**
     * Sets the status for the response.
     *
     * @param status to set.
     * @return this instance
     */
    public Context status(final int status) {
        this.response.setStatus(status);
        return this;
    }

    /**
     * Sets Object as json body.
     *
     * @param object to set as body.
     * @return this instance
     */
    public Context json(final Object object) {
        try {
            this.response.setBody(OBJECT_MAPPER.writeValueAsString(object));
            this.response.setContentType(ContentType.APPLICATION_JSON);
            return this;
        } catch (IOException e) {
            throw new BodyParseException(e);
        }
    }

    /**
     * Parsing the body as json string.
     *
     * @param bodyClass class to parse body.
     * @param <T>       type of the body
     * @return this instance
     */
    public <T> T bodyAsClass(final Class<T> bodyClass) {
        try {
            return OBJECT_MAPPER.readValue(this.request.getBody(), bodyClass);
        } catch (IOException e) {
            throw new BodyParseException(e);
        }
    }

    public void result(final String text) {
        this.response.setBody(text);
        this.response.setContentType(ContentType.PLAIN_TEXT);
    }

    public void setRequest(final Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }
}
