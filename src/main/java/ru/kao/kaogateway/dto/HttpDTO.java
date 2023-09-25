package ru.kao.kaogateway.dto;

import ru.kao.kaogateway.transport.http.HttpMethod;

import java.io.Serializable;
import java.util.Map;

public class HttpDTO implements Serializable {
    /**
     * URL-path
     */
    private String path;

    /**
     * Message
     */
    private String message;

    /**
     * HTTP method
     */
    private HttpMethod method;

    /**
     * Static headers
     */
    private Map<String, String> headers;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
