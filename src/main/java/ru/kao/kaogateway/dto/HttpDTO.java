package ru.kao.kaogateway.dto;

import ru.kao.kaogateway.transport.http.HttpMethod;

import java.io.Serializable;
import java.util.Map;

public class HttpDTO implements Serializable {
    public HttpDTO() {
    }

    public HttpDTO(Map<String, String> headers, String message) {
        this.message = message;
        this.headers = headers;
    }

    /**
     * URL-path
     */
    public String path;

    /**
     * Message
     */
    public String message;

    /**
     * HTTP method
     */
    public HttpMethod method;

    /**
     * Static headers
     */
    public Map<String, String> headers;
}
