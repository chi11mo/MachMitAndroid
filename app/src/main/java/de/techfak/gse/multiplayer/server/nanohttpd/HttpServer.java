package de.techfak.gse.multiplayer.server.nanohttpd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import de.techfak.gse.multiplayer.server.response_body.ResponseObject;
import fi.iki.elonen.NanoHTTPD;

/**
 * Http server which handles incoming requests.
 */
public class HttpServer extends NanoHTTPD {

    private static final String CONTENT_LENGTH = "content-length";
    private static final String NO_CONTENT = "No content to read!";
    private static final int NOT_FOUND = 404;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_REQUEST = 400;

    private final Map<Method, Map<String, Handler>> handlers = new HashMap<>();
    private final Map<Class<? extends Exception>, ExceptionHandler> exceptionHandlers = new HashMap<>();

    /**
     * Creates a new HttpServer with given port.
     *
     * @param port the port of the server
     */
    public HttpServer(final int port) {
        super(port);
        handlers.put(Method.GET, new HashMap<>());
        handlers.put(Method.POST, new HashMap<>());
        handlers.put(Method.DELETE, new HashMap<>());
    }

    private String getBody(final IHTTPSession session) throws BadRequestException {
        if (session.getMethod() != Method.POST && session.getMethod() != Method.PUT) {
            return "";
        }
        final String length = session.getHeaders().get(CONTENT_LENGTH);
        if (length == null) {
            throw new BadRequestException(NO_CONTENT);
        }
        final int contentLength = Integer.parseInt(length);
        if (contentLength == 0) {
            throw new BadRequestException(NO_CONTENT);
        }
        final byte[] buffer = new byte[contentLength];
        try {
            session.getInputStream().read(buffer, 0, contentLength);
        } catch (IOException e) {
            throw new BadRequestException(e);
        }
        return new String(buffer);

    }

    private Handler getHandler(final Method method, final String path) {
        final Map<String, Handler> methodHandlers = handlers.get(method);
        if (methodHandlers == null) {
            return context -> context.status(NOT_FOUND);
        }
        final Handler handler = methodHandlers.get(path);
        if (handler == null) {
            return context -> context.status(NOT_FOUND);
        }
        return handler;
    }

    @SuppressWarnings({"PMD.AvoidCatchingThrowable", "PMD.AvoidCatchingGenericException"})
    private void handleRequest(final IHTTPSession session, final Context context) {
        try {
            final Handler handler = getHandler(session.getMethod(), session.getUri());
            handler.handle(context);
        } catch (Exception e) {
            final ExceptionHandler handler = exceptionHandlers.get(e.getClass());
            if (handler == null) {
                context.status(INTERNAL_SERVER_ERROR).json(ResponseObject.error(e.getMessage()));
            } else {
                handler.handle(e, context);
            }
        } catch (Throwable e) {
            context.status(INTERNAL_SERVER_ERROR).json(ResponseObject.error(e.getMessage()));
        }
    }

    @Override
    public Response serve(final IHTTPSession session) {
        final Map<String, String> params = session.getParameters().entrySet()
                .stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));
        final Context context = new Context();
        try {
            final Request request = new Request(params, getBody(session));
            context.setRequest(request);
            handleRequest(session, context);
        } catch (BadRequestException e) {
            final ExceptionHandler handler = exceptionHandlers.getOrDefault(e, (exception, ctx) -> {
                context.status(BAD_REQUEST).json(ResponseObject.failure("Bad request:" + e.getMessage()));
            });
            assert handler != null;
            handler.handle(e, context);
        }
        final int status = context.getResponse().getStatus();
        final String contentType = context.getResponse().getContentType().asString();
        final String body = context.getResponse().getBody();
        return newFixedLengthResponse(Response.Status.lookup(status), contentType, body);
    }

    /**
     * Adds a GET request handler for the specified path to the instance.
     *
     * @param path    url path
     * @param handler the action handler
     */
    public void get(final String path, final Handler handler) {
        Objects.requireNonNull(this.handlers.get(Method.GET)).put(path, handler);
    }

    /**
     * Adds a POST request handler for the specified path to the instance.
     *
     * @param path    url path
     * @param handler the action handler
     */
    public void post(final String path, final Handler handler) {
        Objects.requireNonNull(this.handlers.get(Method.POST)).put(path, handler);
    }

    /**
     * Adds a DELETE request handler for the specified path to the instance.
     *
     * @param path    url path
     * @param handler the action handler
     */
    public void delete(final String path, final Handler handler) {
        Objects.requireNonNull(this.handlers.get(Method.DELETE)).put(path, handler);
    }

    /**
     * Adds an exception handler to the instance.
     *
     * @param exceptionClass the exception class
     * @param handler        the handler to install
     * @param <T>            type of the exception
     */
    public <T extends Exception> void exception(final Class<T> exceptionClass, final ExceptionHandler handler) {
        this.exceptionHandlers.put(exceptionClass, handler);
    }

    @Override
    public void start() throws IOException {
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }
}
